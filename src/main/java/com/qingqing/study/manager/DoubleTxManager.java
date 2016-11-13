package com.qingqing.study.manager;

import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;
import com.qingqing.study.service.TransactionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xuya on 2016/11/13.
 */
public class DoubleTxManager {

    private TransactionService transactionService1;

    private TransactionService transactionService2;

    @Transactional(value = "transactionManager1")
    public void doDoubleTx(SimpleUser simpleUser, SimpleCity simpleCity) {
        transactionService1.insert(simpleUser, simpleCity, false);
        simpleUser.setName(simpleUser.getName() + 100);
        simpleCity.setProvinceId(simpleCity.getProvinceId() + 100);
        transactionService2.insertWithCatch(simpleUser, simpleCity, true);

//        throw new RuntimeException("DoubleTxManager fail");
    }

    public void setTransactionService1(TransactionService transactionService1) {
        this.transactionService1 = transactionService1;
    }

    public void setTransactionService2(TransactionService transactionService2) {
        this.transactionService2 = transactionService2;
    }
}
