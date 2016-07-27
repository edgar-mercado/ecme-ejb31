package com.ecme.sessionbean;

import java.util.List;

import javax.ejb.Singleton;

@Singleton
public interface ISingletonSessionBean {
	public List<String> getStatus();
	public void setStatus(String status);
	public void doSomething(String status);
}
