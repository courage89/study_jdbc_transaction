package com.qingqing.study.manager;

import com.qingqing.study.activemq.producer.IMessageProducer;
import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;
import com.qingqing.study.service.TransactionService;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;

/**
 * Created by xuya on 2016/11/13.
 */
@Transactional(value = "atomikosTransactionManager")
public class AtomikosActiveMqManager implements  IActiveMqManager {

    private IMessageProducer messageProducer;

    private TransactionService transactionService;

    public void doXaInDbAndMq(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail) throws JMSException {

        messageProducer.sendMessages(false);

        transactionService.insert(simpleUser, simpleCity, false);

        if(execFail){
            throw new RuntimeException("doXaInDbAndMq fail");
        }
    }

    public void setMessageProducer(IMessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
}
