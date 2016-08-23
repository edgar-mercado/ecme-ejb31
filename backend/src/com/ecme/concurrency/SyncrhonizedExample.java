package com.ecme.concurrency;

public class SyncrhonizedExample {
	
	private static int count=0;
	
	private static synchronized void increment(){
		count++;
	}
	
	public static void process(){
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					increment();

				}			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					increment();

				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		process();
		System.out.println(count);
	}
}
