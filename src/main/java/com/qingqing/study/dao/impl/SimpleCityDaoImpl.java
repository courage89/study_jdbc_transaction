package com.qingqing.study.dao.impl;

import com.qingqing.study.dao.BaseDao;
import com.qingqing.study.dao.SimpleCityDao;
import com.qingqing.study.domain.SimpleCity;
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
 * Created by xuya on 2016/11/6.
 */
public class SimpleCityDaoImpl extends BaseDao implements SimpleCityDao {

    private static final String INSERT_SQL_PARAM = "insert into t_simple_city(province_id, city_name, is_deleted) VALUE (?, ?, 0)";
    private static final String UPDATE_SQL = "update t_simple_city set city_name = :cityName, province_id = :provinceId where id=:id";
    private static final String DELETE_SQL = "update t_simple_city set is_deleted = 1 where id=:id";
    private static final String FIND_SQL = "select id, province_id, city_name from t_simple_city where id=:id";

    public void insertWithIdGenerate(final SimpleCity simpleCity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.getJdbcTemplate().getJdbcOperations().update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_SQL_PARAM, new String[]{"id"});
                preparedStatement.setLong(1, simpleCity.getProvinceId());
                preparedStatement.setString(2, simpleCity.getCityName());
                return preparedStatement;
            }
        }, keyHolder);
        simpleCity.setId(keyHolder.getKey().longValue());
    }

    public void update(SimpleCity simpleCity) {
        getJdbcTemplate().update(UPDATE_SQL, new MapSqlParameterSource(getParams(simpleCity)));
    }

    public void deleteById(Long id) {
        Map<String, Long> params = new HashMap<String, Long>();
        params.put("id", id);
        getJdbcTemplate().update(DELETE_SQL, new MapSqlParameterSource(params));
    }

    public SimpleCity findById(Long id) {
        List<SimpleCity> scs = getJdbcTemplate().query(FIND_SQL, getIdMap(id), SIMPLE_CITY_MAPPER);
        return scs.isEmpty() ? null : scs.get(0);
    }

    private final static RowMapper<SimpleCity> SIMPLE_CITY_MAPPER = new RowMapper<SimpleCity>() {
        public SimpleCity mapRow(ResultSet resultSet, int i) throws SQLException {
            SimpleCity su = new SimpleCity();
            su.setId(resultSet.getLong("id"));
            su.setProvinceId(resultSet.getLong("province_id"));
            su.setCityName(resultSet.getString("city_name"));
            return su;
        }
    };

    private Map<String, ?> getParams(SimpleCity simpleCity) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", simpleCity.getId());
        params.put("provinceId", simpleCity.getProvinceId());
        params.put("cityName", simpleCity.getCityName());
        return params;
    }


}
