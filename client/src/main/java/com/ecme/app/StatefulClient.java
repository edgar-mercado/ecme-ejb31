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

public class StatefulClient {

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
	   private void showGUI(){
		      System.out.println("**********************");
		      System.out.println("Welcome to Book Store");
		      System.out.println("**********************");
		      System.out.print("Options \n1. Add Book\n2. Exit \nEnter Choice: ");
		   }
		   
		   private void testStatelessEjb(){
		 
		      try {
		         int choice = 1; 
		 
		         ILibraryStatefulSessionBeanRemote libraryBean =
		        		(ILibraryStatefulSessionBeanRemote)ctx.lookup("java:global/ecme-ejb31/LibraryStatefulSessionBean!com.ecme.sessionbean.ILibraryStatefulSessionBeanRemote");
		 
		         while (choice != 2) {
		            String bookName;
		            showGUI();
		            String strChoice = brConsoleReader.readLine();
		            choice = Integer.parseInt(strChoice);
		            if (choice == 1) {
		               System.out.print("Enter book name: ");
		               bookName = brConsoleReader.readLine();
		               Book book = new Book();
		               book.setName(bookName);
		               libraryBean.addBook(book.getName());          
		            } else if (choice == 2) {
		               break;
		            }
		         }
		 
		         List<Book> booksList = libraryBean.getBooks();
		 
		         System.out.println("Book(s) entered so far: " + booksList.size());
		         int i = 0;
		         for (Book book:booksList) {
		            System.out.println((i+1)+". " + book.getName());
		            i++;
		         }       
		         ILibraryStatefulSessionBeanRemote libraryBean1 = 
		            (ILibraryStatefulSessionBeanRemote)ctx.lookup("LibraryStatefulSessionBean/remote");
		         List<String> booksList1 = libraryBean1.getBooks();
		         System.out.println(
		            "***Using second lookup to get library stateful object***");
		         System.out.println(
		            "Book(s) entered so far: " + booksList1.size());
		         for (int j = 0; j < booksList1.size(); ++j) {
		            System.out.println((i+1)+". " + booksList1.get(i));
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
		StatefulClient client = new StatefulClient();
		client.testStatelessEjb();
	}

}
class Book{
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
