package com.qingqing.study.dao.impl;

import com.qingqing.study.dao.BaseDao;
import com.qingqing.study.dao.SimpleUserDao;
import com.qingqing.study.domain.SimpleUser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuya on 2016/10/23.
 */
public class SimpleUserDaoImpl extends BaseDao implements SimpleUserDao {

    private static final String FINDALL_SQL = "select id, name, age from t_simple_user where is_deleted = 0";
    private static final String FIND_BY_ID_SQL = "select id, name, age from t_simple_user where id = :id";
    private static final String INSERT_SQL = "insert into t_simple_user(name, age, is_deleted) VALUE (:name, :age, 0)";
    private static final String INSERT_SQL_PARAM = "insert into t_simple_user(name, age, is_deleted) VALUE (?, ?, 0)";
    private static final String UPDATE_SQL = "update t_simple_user set name = :name, age = :age where id=:id";
    private static final String DELETE_SQL = "update t_simple_user set is_deleted = 1 where id=:id";

    public void insert(final SimpleUser simpleUser) throws DataAccessException {
        Object execute = getJdbcTemplate().execute(INSERT_SQL, new MapSqlParameterSource(getParams(simpleUser)), new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
                return preparedStatement.execute();
            }
        });
    }

    public  void insertWithIdGenerate(final SimpleUser simpleUser){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.getJdbcTemplate().getJdbcOperations().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_SQL_PARAM, new String[]{"id"});
                preparedStatement.setString(1, simpleUser.getName());
                preparedStatement.setInt(2, simpleUser.getAge());
                return preparedStatement;
            }
        }, keyHolder);
        simpleUser.setId(keyHolder.getKey().longValue());
    }

    /**
    * 如下方法待调试通过
    */
    public void insertWithParam(final SimpleUser simpleUser) throws DataAccessException {
        super.getJdbcTemplate().execute(INSERT_SQL_PARAM, new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, simpleUser.getName());
                preparedStatement.setInt(2, simpleUser.getAge());
                return preparedStatement.execute();
            }
        });
    }

    public void update(SimpleUser simpleUser) {
        getJdbcTemplate().update(UPDATE_SQL, new MapSqlParameterSource(getParams(simpleUser)));
    }

    public void deleteById(Long id) {
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        getJdbcTemplate().update(DELETE_SQL, new MapSqlParameterSource(params));
    }

    public List<SimpleUser> findAll() {
        return getJdbcTemplate().query(FINDALL_SQL, new HashMap<String, Object>(0), SIMPLE_USER_MAPPER);
    }

    public SimpleUser findById(Long id) {
        List<SimpleUser> list = getJdbcTemplate().query(FIND_BY_ID_SQL, getIdMap(id), SIMPLE_USER_MAPPER);
        return list.isEmpty() ? null : list.get(0);
    }

    private final static RowMapper<SimpleUser> SIMPLE_USER_MAPPER = new RowMapper<SimpleUser>() {
        public SimpleUser mapRow(ResultSet resultSet, int i) throws SQLException {
            SimpleUser su = new SimpleUser();
            su.setId(resultSet.getLong("id"));
            su.setAge(resultSet.getInt("age"));
            su.setName(resultSet.getString("name"));
            return su;
        }
    };

    private Map<String, ?> getParams(SimpleUser simpleUser) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", simpleUser.getId());
        params.put("name", simpleUser.getName());
        params.put("age", simpleUser.getAge());
        return params;
    }
}
