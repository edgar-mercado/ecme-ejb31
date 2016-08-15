package com.ecme.jms;

import javax.ejb.Remote;
import javax.jms.JMSException;
@Remote
public interface ISynchConsumer {

	void consumeQueue() throws JMSException;

}