package com.ecme.sessionbean;

import java.util.List;
import java.util.concurrent.Future;

import javax.ejb.Remote;


@Remote
public interface ILibraryStatelessSessionBeanRemote {
	  Future<String> addBook(String bookName);
	  List<String> getBooks();
}
