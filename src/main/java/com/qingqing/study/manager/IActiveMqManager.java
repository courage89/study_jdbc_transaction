package com.qingqing.study.manager;

import com.qingqing.study.domain.SimpleCity;
import com.qingqing.study.domain.SimpleUser;

import javax.jms.JMSException;

/**
 * Created by xuya on 2016/11/13.
 */
public interface IActiveMqManager {

    public void doXaInDbAndMq(SimpleUser simpleUser, SimpleCity simpleCity, boolean execFail) throws JMSException;
}
