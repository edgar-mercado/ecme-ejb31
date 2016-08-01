package com.ecme.jms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;



public class JmsStatelessClient {

	 BufferedReader brConsoleReader = null; 
	   Properties props;
	   InitialContext ctx;
	   {
		      props = new Properties();
		      InputStream input = null;
		      try {
		    	  String filename = "jndi.properties";
		    		input = JmsStatelessClient.class.getClassLoader().getResourceAsStream(filename);
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
		      brConsoleReader = 
		      new BufferedReader(new InputStreamReader(System.in));
		   }
	   private void showGUI(){
		      System.out.println("**********************");
		      System.out.println("JMS Operation");
		      System.out.println("**********************");
		      System.out.print("Options \n1. Produce\n2. Consume \n3. Exit\nEnter Choice: ");
		   }
		   
		   private void testStatelessEjb(){
		 
		      try {
		         int choice = 1; 
		 
		         IProducer producer =
		        		(IProducer)ctx.lookup("java:global/ecme-ejb31/Producer!com.ecme.jms.IProducer");
//		         ISynchConsumer consumer = (ISynchConsumer)ctx.lookup("java:global/ecme-ejb31/SynchConsumer!com.ecme.app.jms.ISynchConsumer");
		         while (choice != 3) {
		            showGUI();
		            String strChoice = brConsoleReader.readLine();
	            	choice = Integer.parseInt(strChoice);
	            	
		            if (choice == 1) {
		            	producer.produceMessage("queue");
		            } else if (choice == 2) {
//		            	consumer.consumeQueue();
		            
		            } else if (choice == 3) {
			               break;
			        }
		         }
	 
		      } catch (Exception e) {
		         System.out.println(e.getMessage());
		         e.printStackTrace();
		      }finally {
		         try {
		            if(brConsoleReader !=null){
		               brConsoleReader.close();
		            }
		         } catch (IOException ex) {
		            System.out.println(ex.getMessage());
		         }
		      }
		   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JmsStatelessClient client = new JmsStatelessClient();
		client.testStatelessEjb();
	}
}
