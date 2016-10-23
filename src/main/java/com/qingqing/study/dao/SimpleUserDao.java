package com.qingqing.study.dao;

import com.qingqing.study.domain.SimpleUser;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by xuya on 2016/10/23.
 */
public interface SimpleUserDao {

    void insert(SimpleUser simpleUser) throws DataAccessException;

    void insertWithIdGenerate(SimpleUser simpleUser);

    void insertWithParam(SimpleUser simpleUser) throws DataAccessException;

    void update(SimpleUser simpleUser);

    void deleteById(Long id);

    List<SimpleUser> findAll();
}
