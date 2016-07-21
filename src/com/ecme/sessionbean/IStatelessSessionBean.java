package com.ecme.sessionbean;


public interface IStatelessSessionBean {
	public String sayHello(String value);
	
	public void updateData(String data);
	public String getSomeData();
	public int getCounter();
}
