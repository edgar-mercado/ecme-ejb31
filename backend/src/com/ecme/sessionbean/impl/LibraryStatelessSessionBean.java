package com.ecme.sessionbean.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

import com.ecme.sessionbean.ILibraryStatelessSessionBeanRemote;

@Stateless
public class LibraryStatelessSessionBean implements ILibraryStatelessSessionBeanRemote {

	 List<String> bookShelf;    
	 
	   public LibraryStatelessSessionBean(){
	      bookShelf = new ArrayList<String>();
	   }
	   @Asynchronous
	   public Future<String> addBook(String bookName) {
		  System.out.println("!!!!!Agregando libro stateless:"+bookName+" from thread: "+Thread.currentThread().getName());
	      bookShelf.add(bookName);
		    try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      System.out.println("Agregado ----------> "+bookName);
	      return new AsyncResult<String>("RESULT_"+bookName+Thread.currentThread().getName());
	    		  
	   }    
	 
	   public List<String> getBooks() {
	      return bookShelf;
	   }
		@PostConstruct
		public void init(){
			System.out.println("_______________@PostConstruct EXECUTED ___________________"+Thread.currentThread().getName());
		}
		@PreDestroy
		public void destroy(){
			System.out.println("_______________@PreDestroy EXECUTED ___________________"+Thread.currentThread().getName());
		}
}
