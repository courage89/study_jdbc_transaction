package com.qingqing.study.activemq.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qingqing.study.domain.MsgDemo;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;

/**
 * Created by xuya on 2016/11/13.
 */
public class QQMsgJsonConverter extends MappingJackson2MessageConverter {

    @Override
    protected JavaType getJavaTypeForMessage(Message message) throws JMSException {
        return TypeFactory.defaultInstance().constructType(MsgDemo.class);
    }
}



