package com.qingqing.study.dao.impl;

import com.qingqing.study.TestBase;
import com.qingqing.study.dao.SimpleUserDao;
import com.qingqing.study.domain.SimpleUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Action;
import java.util.List;

/**
 * Created by xuya on 2016/10/23.
 */
public class SimpleUserDaoImplTest extends TestBase{

    @Autowired
    private SimpleUserDao simpleUserDao;

    @Test
    public void testInsert() {
//        SimpleUser su = getSimpleUser(10);
//        simpleUserDao.insert(su);
        SimpleUser su2 = getSimpleUser(20);
        simpleUserDao.insertWithParam(su2);
        List<SimpleUser> list = simpleUserDao.findAll();
        printJson(list);
    }

    public SimpleUser getSimpleUser(int value) {
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setAge(value);
        simpleUser.setId(value);
        simpleUser.setName("name" + value);
        return simpleUser;
    }
}
