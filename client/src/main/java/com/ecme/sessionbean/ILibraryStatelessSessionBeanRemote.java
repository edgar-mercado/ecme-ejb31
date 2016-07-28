package com.ecme.sessionbean;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface ILibraryStatelessSessionBeanRemote {
	  void addBook(String bookName);
	  List<String> getBooks();
}
