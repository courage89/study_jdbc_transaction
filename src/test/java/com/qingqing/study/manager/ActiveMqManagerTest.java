package com.qingqing.study.manager;

import com.qingqing.study.TestBase;
import com.qingqing.study.activemq.producer.IMessageProducer;
import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuya on 2016/11/13.
 */
public class ActiveMqManagerTest extends TestBase {

    @Autowired
    private IMessageProducer messageProducer;

    @Autowired
    private IActiveMqManager activeMqManager;

    @Test
    public void testSendMsg() throws InterruptedException, JMSException {
        messageProducer.sendMessages(true);
        TimeUnit.SECONDS.sleep(5);
    }

    @Test
    public void testXa() throws JMSException, InterruptedException {
        SimpleUser su = getSimpleUser(2);
        SimpleCity sc = getSimpleCity(2);
        activeMqManager.doXaInDbAndMq(su, sc, false);

        TimeUnit.SECONDS.sleep(5);
    }
}
