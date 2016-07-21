package com.ecme.sessionbean.impl;

import java.util.ArrayList;
import java.util.List;

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
	   }    
	 
	   public List<String> getBooks() {
	      return bookShelf;
	   }

}