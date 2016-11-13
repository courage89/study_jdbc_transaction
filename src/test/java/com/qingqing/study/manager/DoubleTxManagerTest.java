package com.qingqing.study.manager;

import com.qingqing.study.TestBase;
import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xuya on 2016/11/13.
 */
public class DoubleTxManagerTest extends TestBase {

    @Autowired
    private DoubleTxManager doubleTxManager;

    @Test
    public void testTx() {

        SimpleUser su = getSimpleUser(2);
        SimpleCity sc = getSimpleCity(2);

        doubleTxManager.doDoubleTx(su, sc);
    }
}
