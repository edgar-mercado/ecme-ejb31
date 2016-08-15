package com.ecme.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

@Stateless
public class Producer implements IProducer {
	@Resource(lookup = "jms/MyConnectionFactory")
	private static ConnectionFactory connectionFactory;
	@Resource(lookup = "MyQueue")
	private static Queue queue;
	@Resource(lookup = "MyTopic")
	private static Topic topic;
	
	/* (non-Javadoc)
	 * @see com.ecme.jms.IProducer#produceMessage(java.lang.String)
	 */
	@Override
	public void produceMessage(String destType) throws JMSException{
		Destination dest = null;
		try { 
		    if (destType.equals("queue")) { 
		        dest = (Destination) queue; 
		    } else { 
		        dest = (Destination) topic; 
		    }
		} 
		catch (Exception e) {
		    System.err.println("Error setting destination: " + e.toString()); 
		    e.printStackTrace(); 
		    System.exit(1);
		}
		Connection connection = null;
		try{
			 connection = connectionFactory.createConnection(); 
				Session session = connection.createSession(
				            false, 
				            Session.AUTO_ACKNOWLEDGE);
				MessageProducer producer = session.createProducer(dest);
				TextMessage message = session.createTextMessage();
				
				for (int i = 0; i < 10; i++) { 
				    message.setText("This is message " + (i + 1) + " from producer"); 
				    System.out.println("Sending message: " + message.getText()); 
				    producer.send(message);
				}
				producer.send(session.createMessage());
			} finally { 
			    if (connection != null) { 
			        try { connection.close(); } 
			        catch (JMSException e) { } 
			    }
			}

		
	}
}
