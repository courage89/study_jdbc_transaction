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
        SimpleUser su = getSimpleUser();
        simpleUserDao.insert(su);

        List<SimpleUser> list = simpleUserDao.findAll();
        printJson(list);
    }

    public SimpleUser getSimpleUser() {
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setAge(100);
        simpleUser.setId(100);
        simpleUser.setName("name100");
        return simpleUser;
    }
}
