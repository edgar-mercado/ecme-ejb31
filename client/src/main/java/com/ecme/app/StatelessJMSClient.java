package com.ecme.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.ecme.sessionbean.ILibraryStatelessSessionBeanRemote;

public class StatelessJMSClient {

	 BufferedReader brConsoleReader = null; 
	   Properties props;
	   InitialContext ctx;
	   {
		      props = new Properties();
		      InputStream input = null;
		      try {
		    	  String filename = "jndi.properties";
		    		input = StatelessJMSClient.class.getClassLoader().getResourceAsStream(filename);
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
		      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
		   }
		   
		   private void testStatelessEjb(){
		 
		      try {
		         int choice = 1; 
		 
		         ILibraryStatelessSessionBeanRemote libraryBean =
		        		(ILibraryStatelessSessionBeanRemote)ctx.lookup("java:global/ecme-ejb31/LibraryStatelessSessionBean!com.ecme.sessionbean.ILibraryStatelessSessionBeanRemote");
		 
		         while (choice != 2) {
		            String bookName;
		            showGUI();
		            String strChoice = brConsoleReader.readLine();
		            try{
		            	choice = Integer.parseInt(strChoice);
		            } catch(NumberFormatException nfe){
		            	System.out.print(strChoice+" - No es una opcion válida.");
		            }
		            if (choice == 1) {
		               System.out.print("Enter book name: ");
		               bookName = brConsoleReader.readLine();

		               Future<String> response= libraryBean.addBook(bookName);
		               Thread.sleep(4000);
		               System.out.print("Esperando respuesta .... "+response.get());
		            } else if (choice == 2) {
		               break;
		            }
		         }
		         System.out.println("Listando libros ");
		         List<String> booksList = libraryBean.getBooks();
		 
		         System.out.println("Book(s) entered so far: " + booksList.size());
		         int i = 0;
		         for (String book:booksList) {
		            System.out.println((i+1)+". " + book);
		            i++;
		         }       
		         ILibraryStatelessSessionBeanRemote libraryBean1 = 
		            (ILibraryStatelessSessionBeanRemote)ctx.lookup("java:global/ecme-ejb31/LibraryStatelessSessionBean!com.ecme.sessionbean.ILibraryStatelessSessionBeanRemote");
		         List<String> booksList1 = libraryBean1.getBooks();
		         System.out.println(
		            "***Using second lookup to get library stateful object***");
		         System.out.println(
		            "Book(s) entered so far: " + booksList1.size());
		         for (int j = 0; j < booksList1.size(); j++) {
		            System.out.println((i+1)+". " + booksList1.get(j));
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
		StatelessJMSClient client = new StatelessJMSClient();
		client.testStatelessEjb();
	}

}

