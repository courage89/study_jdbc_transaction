package com.qingqing.study;

import com.qingqing.common.util.JsonUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xuya on 2016/10/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:applicationContext.xml",
        "classpath:applicationContext-activemq.xml",
        "classpath:applicationContext-db-study-1.xml",
        "classpath:applicationContext-db-study-2.xml"
})
public class TestBase {
    protected void printJson(Object object) {
        System.out.println(JsonUtil.getJsonFromObject(object));
    }
}
