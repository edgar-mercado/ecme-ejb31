package com.ecme.jms;

import javax.jms.JMSException;

public interface ISynchConsumer {

	void consumeQueue() throws JMSException;

}