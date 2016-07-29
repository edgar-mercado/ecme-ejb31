package com.ecme.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ecme.sessionbean.CalculatorRemote;

public class CalculatorClient {

	 BufferedReader brConsoleReader = null; 
	   Properties props;
	   InitialContext ctx;
	   {
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
		      brConsoleReader = 
		      new BufferedReader(new InputStreamReader(System.in));
		   }
	   private void showGUI(){
		      System.out.println("**********************");
		      System.out.println("Calculator");
		      System.out.println("**********************");
		      System.out.print("Options \n1. Sum\n2. Multiply \n3. Exit\nEnter Choice: ");
		   }
		   
		   private void testStatelessEjb(){
		 
		      try {
		         int choice = 1; 
		         int a = 0; 
		         int b = 0; 
		 
		         CalculatorRemote calculatorRemote =
		        		(CalculatorRemote)ctx.lookup("java:global/ecme-ejb31/CalculatorImpl!com.ecme.sessionbean.CalculatorRemote");
		 
		         while (choice != 3) {
		            showGUI();
		            String strChoice = brConsoleReader.readLine();
	            	choice = Integer.parseInt(strChoice);
	            	
		            if (choice == 1) {
			            System.out.println("Value for a:");
			            String strA = brConsoleReader.readLine();
			            System.out.println("Value for b:");
			            String strB = brConsoleReader.readLine();
		            	a = Integer.parseInt(strA);
		            	b = Integer.parseInt(strB);

		               System.out.println("sum: "+calculatorRemote.sum(a,b));
		            } else if (choice == 2) {
			            System.out.println("Value for a:");
			            String strA = brConsoleReader.readLine();
			            System.out.println("Value for b:");
			            String strB = brConsoleReader.readLine();
		            	a = Integer.parseInt(strA);
		            	b = Integer.parseInt(strB);

		            	System.out.println("multiply: "+calculatorRemote.multiply(a,b));
		            
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
		CalculatorClient client = new CalculatorClient();
		client.testStatelessEjb();
	}

}

