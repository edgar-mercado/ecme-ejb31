package com.ecme.sessionbean.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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
		   System.out.println("Creando STATEFULL BEAN:");
	   }
	   @Remove
	public void remove() {
		System.out.println("Ejecutando metodo REMOVE de STATEFULL session bean");
		
	}
}
