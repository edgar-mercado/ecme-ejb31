package com.ecme.timers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ecme.app.StatefulClient;

public class EJBTester {

	 BufferedReader brConsoleReader = null; 
	   Properties props;
	   InitialContext ctx;
	   {
		      props = new Properties();
		      InputStream input = null;
		      try {
		    	  String filename = "jndi.properties";
		    		input = StatefulClient.class.getClassLoader().getResourceAsStream(filename);
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
	   
	   public static void main(String[] args) {

	      EJBTester ejbTester = new EJBTester();

	      ejbTester.testTimerService();
	   }
	   
	   private void showGUI(){
	      System.out.println("**********************");
	      System.out.println("Welcome to Book Store");
	      System.out.println("**********************");
	      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
	   }
	   
	   private void testTimerService(){
	      try {
	         ITimerSessionBeanRemote timerServiceBean = (ITimerSessionBeanRemote)ctx.lookup("java:global/ecme-ejb31/TimerSessionBean!com.ecme.timers.ITimerSessionBeanRemote");

	         System.out.println("["+(new Date()).toString()+ "]" + "timer created.");
	         timerServiceBean.createTimer(2000);            

	      } catch (NamingException ex) {
	         ex.printStackTrace();
	      }
	   }

}
