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
import javax.ejb.Stateless;

import com.ecme.sessionbean.ILibraryStatelessSessionBeanRemote;

@Stateless
public class LibraryStatelessSessionBean implements ILibraryStatelessSessionBeanRemote {

	 List<String> bookShelf;    
	 
	   public LibraryStatelessSessionBean(){
	      bookShelf = new ArrayList<String>();
	   }
	 
	   public void addBook(String bookName) {
	      bookShelf.add(bookName);
	      System.out.println("!!!!!Agregando libro stateless:"+bookName);
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
