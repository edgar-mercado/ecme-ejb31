package com.ecme.stateless.ws;


import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ecme.sessionbean.impl.IWsInStateless;

@Stateless
@WebService
public class WsInStateless {
	@WebMethod
	public String sayHello(String name) {
		return "Hello "+name;
	}
}
