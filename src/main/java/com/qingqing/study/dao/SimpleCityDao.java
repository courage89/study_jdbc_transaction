package com.qingqing.study.dao;

import com.qingqing.study.domain.SimpleCity;

/**
 * Created by xuya on 2016/11/6.
 */
public interface SimpleCityDao {

    void insertWithIdGenerate(SimpleCity simpleCity);

    void update(SimpleCity simpleCity);

    void deleteById(Long id);

    SimpleCity findById(Long id);
}
