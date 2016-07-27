package com.ecme.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ecme.sessionbean.ILibraryStatefulSessionBeanRemote;
import com.ecme.sessionbean.ILibraryStatelessSessionBeanRemote;
import com.ecme.sessionbean.ISingletonSessionBean;

public class SingletonClient {

	 BufferedReader brConsoleReader = null; 
	   Properties props;
	   InitialContext ctx;
	   {
		      props = new Properties();
		      InputStream input = null;
		      try {
		    	  String filename = "jndi.properties";
		    		input = SingletonClient.class.getClassLoader().getResourceAsStream(filename);
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
		      System.out.println("Welcome to Book Store");
		      System.out.println("**********************");
		      System.out.print("Options \n1. Add data\n2. Read data\n3. Exit \nEnter Choice: ");
		   }
		   
		   private void testStatelessEjb(){
			  showGUI();
		      try {
		         int choice = 1; 
		 
		         ISingletonSessionBean libraryBean =
		        		(ISingletonSessionBean)ctx.lookup("java:global/ecme-ejb31/SingletonSessionBean!com.ecme.sessionbean.ISingletonSessionBean");
		 
		         while (choice != 3) {
		        	 showGUI();
		        	 String strChoice = brConsoleReader.readLine();
		        	 try{
			            String bookName;
			           // 
			           
		            
		            	choice = Integer.parseInt(strChoice);
		           
			            if (choice == 1) {
			               System.out.print("Enter some data: ");
			               bookName = brConsoleReader.readLine();	
			               libraryBean.setStatus(bookName);      
			               System.out.println("___________________ Inserted");
			            } else if (choice == 2) {
					         System.out.println("Listando de datos ");
					         	List<String> data = libraryBean.getStatus();
					         	for (String string : data) {
					         		 System.out.println("data readed: " + string);
								}
					         	System.out.println("___________________ Listed");
			            } else if (choice == 3)
			            	break;
		        	 } catch(NumberFormatException nfe){
			            	System.out.print(strChoice+" - No es una opcion válida.");
		        	 } catch (Exception ex){
		        		 ex.printStackTrace();
		        		 showGUI();
		        	 }
		         }
		         ISingletonSessionBean libraryBean1 = 
		            (ISingletonSessionBean)ctx.lookup("java:global/ecme-ejb31/SingletonSessionBean!com.ecme.sessionbean.ISingletonSessionBean");
		         List<String>  booksList1 = libraryBean1.getStatus();
		         System.out.println(
		            "***Using second lookup to get singleton bean object***");
		         System.out.println("Listando de datos ");
		         	List<String> data = libraryBean.getStatus();
		         	for (String string : data) {
		         		 System.out.println("data readed: " + string);
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
		SingletonClient client = new SingletonClient();
		client.testStatelessEjb();
	}

}

