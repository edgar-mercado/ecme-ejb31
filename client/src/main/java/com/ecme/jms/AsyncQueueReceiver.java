package com.ecme.jms;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class AsyncQueueReceiver implements MessageListener, ExceptionListener{

	public static void main(String[] args) throws Exception {
		InitialContext ctx= new InitialContext();
		Queue queue = (Queue) ctx.lookup("MyQueue");
		QueueConnectionFactory connFactory= (QueueConnectionFactory) ctx.lookup("jms/MyConnectionFactory");
		QueueConnection queueCon = connFactory.createQueueConnection();
		QueueSession qSession = queueCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		javax.jms.QueueReceiver queueReceiver = qSession.createReceiver(queue);
		AsyncQueueReceiver asyncReceiver = new  AsyncQueueReceiver();
		queueReceiver.setMessageListener(asyncReceiver);
		queueCon.setExceptionListener(asyncReceiver);
		
		queueCon.start();
		System.out.println("Waiting for messages: ");
		
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			System.out.println("...");
			
		}
		
		queueCon.close();
		

	}

	@Override
	public void onException(JMSException arg0) {
		arg0.printStackTrace();
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(Message arg0) {
		TextMessage msg = (TextMessage)arg0;
		try {
			System.out.println("Receive: "+msg.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
