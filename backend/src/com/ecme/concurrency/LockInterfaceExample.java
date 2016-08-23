package com.ecme.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterfaceExample {
	private static int counter=0;
	private static Lock lock = new ReentrantLock();
	
	public static void increment(){
		lock.lock();
		counter++;
		lock.unlock();
	}
	public static void first(){
		for (int i = 0; i < 100; i++) {
			increment();
			
		}
	}
	public static void second(){
		for (int i = 0; i < 100; i++) {
			increment();
			
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t1= new Thread(new Runnable() {
			
			@Override
			public void run() {
				first();
				
			}
		});
		Thread t2= new Thread(new Runnable() {
			
			@Override
			public void run() {
				second();
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(counter);
	}
}
