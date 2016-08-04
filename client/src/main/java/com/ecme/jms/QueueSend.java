package com.ecme.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ecme.app.CalculatorClient;

public class QueueSend {

		 // Defines the JMS context factory.
		 public final static String JMS_FACTORY="jms/MyConnectionFactory";

		 // Defines the queue.
		 public final static String QUEUE="MyQueue";

		 private QueueConnectionFactory qconFactory;
		 private QueueConnection qcon;
		 private QueueSession qsession;
		 private QueueSender qsender;
		 private Queue queue;
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
		 public void init(Context ctx, String queueName)
		    throws NamingException, JMSException
		 {
		    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
		    qcon = qconFactory.createQueueConnection();
		    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		    queue = (Queue) ctx.lookup(queueName);
		    qsender = qsession.createSender(queue);
		    qsender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		    msg = qsession.createTextMessage();
		    qcon.start();
		 }

		 /**
		  * Sends a message to a JMS queue.
		  *
		  * @param message  message to be sent
		  * @exception JMSException if JMS fails to send message due to internal error
		  */
		 public void send(String message) throws JMSException {
		    msg.setText(message);
		    qsender.send(msg);
		 }

		 /**
		  * Closes JMS objects.
		  * @exception JMSException if JMS fails to close objects due to internal error
		  */
		 public void close() throws JMSException {
		    qsender.close();
		    qsession.close();
		    qcon.close();
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
		    QueueSend qs = new QueueSend();
		    qs.init(ic, QUEUE);
		    readAndSend(qs);
		    qs.close();
		 }

		 private static void readAndSend(QueueSend qs)
		    throws IOException, JMSException
		 {
		    BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
		    String line=null;
		    boolean quitNow = false;
		    do {
		     System.out.print("Enter message (\"quit\" to quit): \n");
		     line = msgStream.readLine();
		     if (line != null && line.trim().length() != 0) {
		    	 for (int i=0; i<1000 ; i++)
		    		 qs.send(line+i);
		       System.out.println("JMS Message Sent: "+line+"\n");
		       quitNow = line.equalsIgnoreCase("quit");
		     }
		    } while (! quitNow);

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
