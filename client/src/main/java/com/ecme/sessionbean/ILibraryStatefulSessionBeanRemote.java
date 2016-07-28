package com.ecme.sessionbean;

import java.util.List;


public interface ILibraryStatefulSessionBeanRemote {
	  void addBook(String bookName);
	  List<String> getBooks();
	  void remove();

}
