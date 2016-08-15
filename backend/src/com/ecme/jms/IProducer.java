package com.ecme.jms;

import javax.ejb.Remote;
import javax.jms.JMSException;
@Remote
public interface IProducer {

	void produceMessage(String destType) throws JMSException;

}