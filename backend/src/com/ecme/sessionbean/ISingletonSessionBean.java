package com.ecme.sessionbean;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ISingletonSessionBean {
	public List<String> getStatus();
	public void setStatus(String status);
}
