package com.ecme.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

@Stateless
public class SynchConsumer implements ISynchConsumer {
	@Resource(lookup = "jms/MyConnectionFactory")
	private static ConnectionFactory connectionFactory;
	@Resource(lookup = "MyQueue")
	private static Queue queue;
	@Resource(lookup = "MyTopic")
	private static Topic topic;
	
	/* (non-Javadoc)
	 * @see com.ecme.jms.ISynchConsumer#consumeQueue()
	 */
	@Override
	public void consumeQueue() throws JMSException{
		Destination  dest = (Destination) queue; 
		Connection connection = connectionFactory.createConnection(); 
		Session session = connection.createSession(
		            false, 
		            Session.AUTO_ACKNOWLEDGE);
		TextMessage message = null;
		MessageConsumer consumer = session.createConsumer(dest);
		connection.start();
		while (true) {
		    Message m = consumer.receive(1); 
		    if (m != null) { 
		        if (m instanceof TextMessage) { 
		            message = (TextMessage) m; 
		            System.out.println("Reading message: " + message.getText()); 
		        } else { 
		            break; 
		        } 
		    }
		}
		
	}
}
