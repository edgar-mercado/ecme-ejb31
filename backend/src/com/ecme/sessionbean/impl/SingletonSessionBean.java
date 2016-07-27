package com.ecme.sessionbean.impl;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.ecme.sessionbean.ISingletonSessionBean;

@Singleton
@AccessTimeout(value=2000)
public class SingletonSessionBean implements ISingletonSessionBean{
		private List<String> status = new  ArrayList<String>();

		@Lock(LockType.READ)
		public List<String> getStatus() {
			return status;
		}

		//@Lock(LockType.WRITE)
		public void setStatus(String status) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.status.add(status);
		}
		
		@Lock(LockType.WRITE)
		public void doSomething(String status) {

			setStatus(status);
		}
		

}
