package com.ecme.sessionbean.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.BeforeCompletion;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.ecme.sessionbean.ILibraryStatefulSessionBeanRemote;

@Stateful
public class LibraryStatefulSessionBean implements ILibraryStatefulSessionBeanRemote {

	 List<String> bookShelf;    
	 
	   public LibraryStatefulSessionBean(){
	      bookShelf = new ArrayList<String>();
	   }
	 
	   public void addBook(String bookName) {
	      bookShelf.add(bookName);
	      System.out.println("Agregando libro stateful:"+bookName);
	   }    
	 
	   public List<String> getBooks() {
	      return bookShelf;
	   }
		@PostConstruct
		public void init(){
			System.out.println("_______________@PostConstruct EXECUTED ___________________");
		}
		@PreDestroy
		public void destroy(){
			System.out.println("_______________@PreDestroy EXECUTED ___________________");
		}
		@Remove
		public void invalidateSession(){
			System.out.println("_______________@Remove EXECUTED ___________________");
		}
		@PrePassivate
		public void prePassivate(){
			System.out.println("_______________@PrePassivate EXECUTED ___________________");
		}
		@PostActivate
		public void postActivate(){
			System.out.println("_______________@PostActivate EXECUTED ___________________");
		}
		@AfterBegin
		public void afterBegin(){
			System.out.println("_______________@AfterBegin -A new transaction has started-. ___________________");
		}
		@BeforeCompletion
		private void beforeCompletion(){
		         System.out.println("_______________@BeforeCompletion -A transaction is about to be committed ___________________");
		}

		@AfterCompletion
		private void afterCompletion(boolean commited){
		         System.out.println("_______________@AfterCompletion -a transaction commit protocol has completed, and tells the instance whether the transaction has been committed or rolled back , based on committed value : " + commited);
		}
}
