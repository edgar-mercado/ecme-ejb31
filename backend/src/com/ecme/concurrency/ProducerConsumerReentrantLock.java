package com.ecme.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerReentrantLock {
	
	static Worker worker = new Worker();
	
	public static void main(String[] args) {
		Thread t1= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t2= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					worker.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t3= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					worker.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread t4= new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class Worker{
	private Lock lock = new ReentrantLock();
	private Condition condition= lock.newCondition();
	
	private List<Integer> list= new ArrayList<>();
	public void produce() throws InterruptedException{
		lock.lock();
		System.out.println("This is the producer method");
		condition.await();
		System.out.println("This is the producer AGAIN");
		lock.unlock();
	}
	public void consume() throws InterruptedException{
		lock.lock();
		Thread.sleep(2000);
		System.out.println("Consumer method");
		Thread.sleep(1000);
		condition.signal();
		lock.unlock();
	}
	
}
