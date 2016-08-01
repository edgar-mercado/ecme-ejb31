package com.ecme.jms;

import javax.jms.JMSException;


public interface IProducer {

	void produceMessage(String destType) throws JMSException;

}