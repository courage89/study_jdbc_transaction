package com.qingqing.study.activemq.producer;

import javax.jms.JMSException;

/**
 * Created by xuya on 2016/11/13.
 */
public interface IMessageProducer {

    public void sendMessages(boolean execFail) throws JMSException;

}