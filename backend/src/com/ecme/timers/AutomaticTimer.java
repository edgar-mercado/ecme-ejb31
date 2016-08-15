package com.ecme.timers;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class AutomaticTimer {
	  @Schedule(second="*", minute="1",hour="*", persistent=false)
	  public void doSomething(){
		 System.out.println("Scheduled Job executed from com.ecme.timers.AutomaticTimer: " + System.currentTimeMillis());
		  
	  }
}
