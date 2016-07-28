package com.ecme.sessionbean.impl;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.AccessTimeout;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.DependsOn;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.ecme.sessionbean.ISingletonSessionBean2;

@Singleton
@AccessTimeout(value=2000)
@ConcurrencyManagement(value = ConcurrencyManagementType.BEAN)
@DependsOn("SingletonSessionBean")
public class SingletonSessionBean2 implements ISingletonSessionBean2{
		private List<String> status = new  ArrayList<String>();

		@Lock(LockType.READ)
		public List<String> getStatus() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return status;
		}

		@Lock(LockType.WRITE)
		@AccessTimeout(value=2000)
		public void setStatus(String status) {
			try {
				Thread.sleep(130000);
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
