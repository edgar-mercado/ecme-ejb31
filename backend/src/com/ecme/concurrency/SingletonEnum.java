package com.ecme.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum SingletonExample{
	INSTANCE;
	private int counter=0;
	private Lock lock=new ReentrantLock();
	
	public void increment(){
		lock.lock();
		counter++;
		lock.unlock();
	}
	public int getCounter(){
		return counter;
	}
}
public class SingletonEnum {
	public static void main(String[] args) throws InterruptedException {
		Thread t1= new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					SingletonExample.INSTANCE.increment();
				}
				
			}
		});
		Thread t2= new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					SingletonExample.INSTANCE.increment();
				}
				
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(SingletonExample.INSTANCE.getCounter());
	}
}
