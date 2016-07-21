package com.ecme.sessionbean.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
	      System.out.println("Agregando libro stateles:"+bookName);
	   }    
	 
	   public List<String> getBooks() {
	      return bookShelf;
	   }
	   @PostConstruct
	   public void init(){
		   System.out.println("Creando STATELESS BEAN:");
	   }

}
