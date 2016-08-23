package com.ecme.concurrency;

import java.util.ArrayList;
import java.util.List;

public class LowLevelProducerConsumer {

	static Processator processor= new Processator();
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		t1.start();
		t2.start();
		
		
		
	}
}

class Processator{
	private List<Integer> list = new ArrayList<>();
	private final int LIMIT=10;
	private Object lock= new Object();
	
	public void produce() throws InterruptedException{
		int value=0;
		while (true) {
			synchronized (lock) {
				while (this.list.size()==LIMIT){
					lock.wait();
				}
				this.list.add(value);
				System.out.println("Producer method added:"+value);
				value++;
				lock.notify();
			}
		}
	}
	public void consume() throws InterruptedException{
		while (true){
			synchronized (lock) {
				while (this.list.size()==0) {
					lock.wait();
				}
				int value=this.list.remove(0);
				System.out.println("Cobsuner method removed "+value);
				lock.notify();
			}
			Thread.sleep(1000);
		}
	}
	
}
