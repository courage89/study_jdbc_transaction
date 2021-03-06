package com.qingqing.study.service.impl;

import com.qingqing.study.TestBase;
import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;
import com.qingqing.study.service.TransactionService;
import com.qingqing.study.util.UnitTestEqualsUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by xuya on 2016/11/6.
 */
public class TransactionServiceImplTest extends TestBase {

    @Autowired
    @Qualifier("transactionService")
    private TransactionService transactionService;

    @Autowired
    @Qualifier("jotmTransactionService")
    private TransactionService jotmTransactionService;

    @Autowired
    @Qualifier("atomikosTransactionService")
    private TransactionService atomikosTransactionService;

    @Test
    public void testXaTransactionInsert() throws IllegalAccessException {

        TransactionService ts = transactionService;
        boolean execFail = true;
        SimpleUser su = getSimpleUser(1);
        SimpleCity sc = getSimpleCity(1);
        ts.insert(su, sc, execFail);

        if (su.getId() != null) {
            SimpleUser su1 = ts.findSimpleUserById(su.getId());
            Assert.assertTrue(UnitTestEqualsUtil.isEquals(su, su1, "createTime", "lastUpdateTime"));
        } else {
            Assert.fail("insert simpleUser fail");
        }

        if (sc.getId() != null) {
            SimpleCity sc1 = ts.findSimpleCityById(sc.getId());
            Assert.assertTrue(UnitTestEqualsUtil.isEquals(sc, sc1, "createTime", "lastUpdateTime"));
        } else {
            Assert.fail("insert simpleCity fail");
        }
    }

    @Test
    public void testXaTransactionNestedOperate() throws IllegalAccessException {
//        TransactionService ts = transactionService;
        TransactionService ts = jotmTransactionService;
//        TransactionService ts = atomikosTransactionService;

        boolean execFail = true;
        SimpleUser su = getSimpleUser(2);
        SimpleCity sc = getSimpleCity(2);
        ts.nestedOperate(su, sc, execFail);

        if (su.getId() != null) {
            SimpleUser su1 = ts.findSimpleUserById(su.getId());
            Assert.assertTrue(UnitTestEqualsUtil.isEquals(su, su1, "createTime", "lastUpdateTime"));
        }

        if (sc.getId() != null) {
            SimpleCity sc1 = ts.findSimpleCityById(sc.getId());
            Assert.assertTrue(UnitTestEqualsUtil.isEquals(sc, sc1, "createTime", "lastUpdateTime"));
        }
    }
}
