package com.ecme.jms;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class SyncQueueReceiver {

	public static void main(String[] args) throws Exception {
		InitialContext ctx= new InitialContext();
		Queue queue = (Queue) ctx.lookup("MyQueue");
		QueueConnectionFactory connFactory= (QueueConnectionFactory) ctx.lookup("jms/MyConnectionFactory");
		QueueConnection queueCon = connFactory.createQueueConnection();
		QueueSession qSession = queueCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		javax.jms.QueueReceiver queueReceiver = qSession.createReceiver(queue);
		queueCon.start();
		
		TextMessage msg = (TextMessage)queueReceiver.receive();
		System.out.println("Receive: "+msg.getText());
		
		queueCon.close();
		

	}

}
