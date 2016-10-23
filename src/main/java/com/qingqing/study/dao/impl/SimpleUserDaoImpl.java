package com.qingqing.study.dao.impl;

import com.qingqing.study.dao.BaseDao;
import com.qingqing.study.dao.SimpleUserDao;
import com.qingqing.study.domain.SimpleUser;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;

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
    private static final String INSERT_SQL = "insert into t_simple_user(id, name, age, is_deleted) VALUE (\":id\", \":name\", \":age\", 0)";
    private static final String UPDATE_SQL = "update t_simple_user set name = \":name\", age = \":age\" where id=\":id\"";
    private static final String DELETE_SQL = "update t_simple_user set is_deleted = 1 where id=\":id\"";

    public void insert(SimpleUser simpleUser) throws DataAccessException {

        Object execute = getJdbcTemplate().execute(INSERT_SQL, getParams(simpleUser), new PreparedStatementCallback() {
            public Object doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
                System.out.println("update count:" + preparedStatement.getUpdateCount());
                return null;
            }
        });
    }

    public void update(SimpleUser simpleUser) {
        getJdbcTemplate().update(UPDATE_SQL, getParams(simpleUser));
    }

    public void deleteById(Long id) {
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        getJdbcTemplate().update(DELETE_SQL, params);
    }

    public List<SimpleUser> findAll() {
        return getJdbcTemplate().query(FINDALL_SQL, new HashMap<String, Object>(0), SIMPLE_USER_MAPPER);
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
