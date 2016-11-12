package com.qingqing.study.service.impl;

import com.qingqing.study.dao.SimpleCityDao;
import com.qingqing.study.dao.SimpleUserDao;
import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;
import com.qingqing.study.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuya on 2016/11/6.
 */
@Transactional(value = "springTransactionManager")
public class XaTransactionServcieImpl implements TransactionService {

    @Autowired
    private SimpleUserDao simpleUserDao;

    @Autowired
    private SimpleCityDao simpleCityDao;

    private TransactionService transactionServcie;

    public void insert(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail) {
        simpleCityDao.insertWithIdGenerate(simpleCity);
        simpleUserDao.insertWithIdGenerate(simpleUser);

        if (execFail) {
            throw new RuntimeException("XaTransactionServcieImpl exec insert fail");
        }
    }

    public void update(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail) {
        simpleUserDao.update(simpleUser);
        simpleCityDao.update(simpleCity);

        if (execFail) {
            throw new RuntimeException("XaTransactionServcieImpl exec update fail");
        }
    }

    public void delete(Long userId, Long cityId, boolean execFail) {
        simpleUserDao.deleteById(userId);
        simpleCityDao.deleteById(cityId);

        if (execFail) {
            throw new RuntimeException("XaTransactionServcieImpl exec delete fail");
        }
    }

    public void nestedOperate(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail){
        this.insert(simpleUser, simpleCity, false);
        simpleUser.setAge(simpleUser.getAge() + 100);
        simpleCity.setProvinceId(simpleCity.getProvinceId() + 100);
        transactionServcie.update(simpleUser, simpleCity, false);
        if (execFail) {
            throw new RuntimeException("XaTransactionServcieImpl exec nextedOperate fail");
        }
    }

    public SimpleUser findSimpleUserById(Long id) {
        return simpleUserDao.findById(id);
    }

    public SimpleCity findSimpleCityById(Long id) {
        return simpleCityDao.findById(id);
    }

    public void setTransactionServcie(TransactionService transactionServcie) {
        this.transactionServcie = transactionServcie;
    }
}
