package com.ecme.timers;

import javax.ejb.Remote;

@Remote
public interface ITimerSessionBeanRemote {
	 public void createTimer(long milliseconds);
}
