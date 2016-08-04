package com.ecme.jms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ecme.app.CalculatorClient;

public class TopicSend {

		 // Defines the JMS context factory.
		 public final static String JMS_FACTORY="jms/MyConnectionFactory";

		 // Defines the queue.
		 public final static String TOPIC="MyTopic";

		 private TopicConnectionFactory tconFactory;
		 private TopicConnection tcon;
		 private TopicSession tsession;
		 private TopicPublisher tPublisher;
		 private Topic topic;

		 /**
		  * Creates all the necessary objects for sending
		  * messages to a JMS queue.
		  *
		  * @param ctx JNDI initial context
		  * @param queueName name of queue
		  * @exception NamingException if operation cannot be performed
		  * @exception JMSException if JMS fails to initialize due to internal error
		  */
		 public void init(Context ctx, String topicName)
		    throws NamingException, JMSException
		 {
		    tconFactory = (TopicConnectionFactory) ctx.lookup(JMS_FACTORY);
		    tcon = tconFactory.createTopicConnection();
		    tsession = tcon.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		    topic = (Topic) ctx.lookup(topicName);
		    tPublisher = tsession.createPublisher(topic);
		    MapMessage message = tsession.createMapMessage();
		    message.setString("Subject", "This is the subject of your message");
		    message.setString("Content", "This is the content bla bla bla ...");
		    tPublisher.publish(message);
		 }

	
		 /**
		  * Closes JMS objects.
		  * @exception JMSException if JMS fails to close objects due to internal error
		  */
		 public void close() throws JMSException {
			 tPublisher.close();
		    tsession.close();
		    tcon.close();
		 }
		/** main() method.
		 *
		 * @param args WebLogic Server URL
		 * @exception Exception if operation fails
		 */
		 public static void main(String[] args) throws Exception {

		    InitialContext ic = getInitialContext("");
		    TopicSend qs = new TopicSend();
		    qs.init(ic, TOPIC);
		    qs.close();
		 }

		 private static InitialContext getInitialContext(String url)
		    throws NamingException
		 {
			 Properties props;
			  
			 InitialContext ctx=null;
				      props = new Properties();
				      InputStream input = null;
				      try {
				    	  String filename = "jndi.properties";
				    		input = CalculatorClient.class.getClassLoader().getResourceAsStream(filename);
				    		if(input==null){
				    	            System.out.println("Sorry, unable to find " + filename);
				    		}
				         props.load(input);
				      } catch (IOException ex) {
				         ex.printStackTrace();
				      }
				      try {
				         ctx = new InitialContext(props);            
				      } catch (NamingException ex) {
				         ex.printStackTrace();
				      }
			  
			   return ctx;
		 }
}
