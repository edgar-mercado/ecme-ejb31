package com.ecme.sessionbean;

import javax.ejb.Remote;

@Remote
public interface IStatefulSessionBean {
	public String sayHello(String value);
	
	public void updateData(String data);
	public String getSomeData();
	public int getCounter();
}
