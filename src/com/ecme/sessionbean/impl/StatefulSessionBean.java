package com.ecme.sessionbean.impl;



import javax.ejb.LocalBean;

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

}
