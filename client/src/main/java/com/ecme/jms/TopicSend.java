package com.ecme.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
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
		 private TextMessage msg;

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
		    message.setString("Subject", "TITULO del TOPIC");
		    message.setString("Content", "Contenidoooooooooo");
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
		//    if (args.length != 1) {
		//     System.out.println("Usage: java examples.jms.queue.QueueSend WebLogicURL");
		//     return;
		//    }
		    InitialContext ic = getInitialContext("");
		    TopicSend qs = new TopicSend();
		    qs.init(ic, TOPIC);
		//    readAndSend(qs);
		    qs.close();
		 }

	/*	 private static void readAndSend(TopicSend qs)
		    throws IOException, JMSException
		 {
		    BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
		    String line=null;
		    boolean quitNow = false;
		    do {
		     System.out.print("Enter message (\"quit\" to quit): \n");
		     line = msgStream.readLine();
		     if (line != null && line.trim().length() != 0) {
		       qs.send(line);
		       System.out.println("JMS Message Sent: "+line+"\n");
		       quitNow = line.equalsIgnoreCase("quit");
		     }
		    } while (! quitNow);

		 }
*/
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
