package com.qingqing.study.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuya on 2016/10/23.
 */
public abstract class BaseDao {

    protected NamedParameterJdbcTemplate jdbcTemplate;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setDataSource(DataSource  dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    protected final static Map<String, Long> getIdMap(Long id){
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("id", id);
        return map;
    }
}
