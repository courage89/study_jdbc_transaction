package com.qingqing.study.service.impl;

import com.qingqing.study.dao.SimpleCityDao;
import com.qingqing.study.dao.SimpleUserDao;
import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;
import com.qingqing.study.service.TransactionService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuya on 2016/11/6.
 */
@Transactional(value = "jotmTransactionManager",propagation = Propagation.REQUIRED)
public class JotmTransactionServiceImpl implements TransactionService {

    private SimpleUserDao simpleUserDao;

    private SimpleCityDao simpleCityDao;

    private TransactionService transactionService;

    public void insert(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail) {
        simpleCityDao.insert(simpleCity);
        simpleUserDao.insert(simpleUser);

        if (execFail) {
            throw new RuntimeException("transactionService exec insert fail");
        }
    }

    public void update(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail) {
        simpleUserDao.update(simpleUser);
        simpleCityDao.update(simpleCity);
        if (execFail) {
            throw new RuntimeException("transactionService exec update fail");
        }
    }

    public void delete(Long userId, Long cityId, boolean execFail) {
        simpleUserDao.deleteById(userId);
        simpleCityDao.deleteById(cityId);
        if (execFail) {
            throw new RuntimeException("transactionService exec delete fail");
        }
    }

    public void nestedOperate(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail) {
        this.insert(simpleUser, simpleCity, false);

        simpleUser.setAge(simpleUser.getAge() + 20);
        simpleCity.setProvinceId(simpleCity.getProvinceId() + 20);

        transactionService.insert(simpleUser, simpleCity, false);

        if (execFail) {
            throw new RuntimeException("TransactionServcieImpl exec nextedOperate fail");
        }
    }

    public SimpleUser findSimpleUserById(Long id) {
        return simpleUserDao.findById(id);
    }

    public SimpleCity findSimpleCityById(Long id) {
        return simpleCityDao.findById(id);
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void setSimpleUserDao(SimpleUserDao simpleUserDao) {
        this.simpleUserDao = simpleUserDao;
    }

    public void setSimpleCityDao(SimpleCityDao simpleCityDao) {
        this.simpleCityDao = simpleCityDao;
    }
}
