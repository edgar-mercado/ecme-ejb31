package com.ecme.sessionbean.impl;



import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.AfterBegin;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;

import com.ecme.sessionbean.IStatefulSessionBean;
import javax.ejb.Stateful;;

/**
 * Session Bean implementation class StatefulSessionBean
 */
@Stateful
@LocalBean
public class StatefulSessionBean implements IStatefulSessionBean{

    /**
     * Default constructor. 
     */
	public String someData="";
	public int counter;
	
    public StatefulSessionBean() {
        // TODO Auto-generated constructor stub
    }

	public String sayHello(String value) {
		// TODO Auto-generated method stub
		counter++;
		return "Hello "+value;
	}

	public void updateData(String data) {
			someData=data;
	}

	public String getSomeData() {
		// TODO Auto-generated method stub
		return someData;
	}
	public int getCounter(){
		return counter;
		
	}
	@PostConstruct
	public void init(){
		System.out.println("_______________@PostConstruct EXECUTED ___________________");
	}
	@PreDestroy
	public void destroy(){
		System.out.println("_______________@PreDestroy EXECUTED ___________________");
	}
	@Remove
	public void remove(){
		System.out.println("_______________@Remove EXECUTED ___________________");
	}
	@PrePassivate
	public void prePassivate(){
		System.out.println("_______________@PrePassivate EXECUTED ___________________");
	}
	@PostActivate
	public void postActivate(){
		System.out.println("_______________@PostActivate EXECUTED ___________________");
	}
	@AfterBegin
	public void afterBegin(){
		System.out.println("_______________@AfterBegin EXECUTED ___________________");
	}	

}
