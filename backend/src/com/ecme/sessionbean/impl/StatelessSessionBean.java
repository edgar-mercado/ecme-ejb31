package com.ecme.sessionbean.impl;



import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.ecme.sessionbean.IStatelessSessionBean;

/**
 * Session Bean implementation class StatefulSessionBean
 */
@Stateless
@LocalBean
public class StatelessSessionBean implements IStatelessSessionBean{

    /**
     * Default constructor. 
     */
	public String someData="";
	public int counter;
	
    public StatelessSessionBean() {
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

}
