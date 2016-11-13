package com.qingqing.study.activemq.producer;

import com.qingqing.study.domain.MsgDemo;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.util.Date;
import java.util.List;

/**
 * Created by xuya on 2016/11/12.
 */
public class SimpleMessageProducer {

    @Autowired
    protected JmsTemplate jmsTemplate;

    protected int numberOfMessages = 100;

    private List<String> destQueueNames;

    private static final Logger logger = LoggerFactory.getLogger(SimpleMessageProducer.class);

    public void sendMessages() throws JMSException {
        for (int i = 1; i <= numberOfMessages; ++i) {
            final int index = i;
            final String payload = "Message [" + i + "] sent at: " + (new Date());
            jmsTemplate.convertAndSend(getDestination(index), getMessage(index));
        }
    }

    private MsgDemo getMessage(int index) {
        MsgDemo msg = new MsgDemo();
        msg.setSequenceNo(System.currentTimeMillis());
        msg.setText("MsgText, index:" + index);
        return msg;
    }

    private Destination getDestination(int index) {
        return new ActiveMQQueue(destQueueNames.get(index % destQueueNames.size()));
    }

    public void setDestQueueNames(List<String> destQueueNames) {
        this.destQueueNames = destQueueNames;
    }
}
