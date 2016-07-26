package com.ecme.sessionbean.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IWsInStateless extends java.rmi.Remote {
	@WebMethod
	public String sayHello(String name) throws java.rmi.RemoteException;
}
