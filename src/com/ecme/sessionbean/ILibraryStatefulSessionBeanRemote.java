package com.ecme.sessionbean;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface ILibraryStatefulSessionBeanRemote {
	  void addBook(String bookName);
	   List getBooks();
}
