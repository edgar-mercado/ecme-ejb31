package com.ecme.stateless.ws;


import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@Stateless
@WebService
public class WsInStateless {
	@WebMethod
	public String sayHello(String name) {
		return "Hello "+name;
	}
}
