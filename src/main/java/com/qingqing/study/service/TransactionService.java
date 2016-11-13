package com.qingqing.study.service;

import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;

/**
 * Created by xuya on 2016/11/6.
 */
public interface TransactionService {

    void insert(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail);

    void insertWithCatch(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail);

    void update(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail);

    void delete(Long userId, Long cityId, boolean execFail);

    void nestedOperate(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail);

    SimpleUser findSimpleUserById(Long id);

    SimpleCity findSimpleCityById(Long id);
}
