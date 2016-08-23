package com.ecme.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {
	public static void main(String[] args) {
		//ExecutorService executorService = Executors.newFixedThreadPool(5);
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 5; i++) {
			executorService.execute(new Worker1());
		
		}
		executorService.shutdown();
	}
}

class Worker1 implements Runnable{

	@Override
	public void run() {
		for (int i = 0; i <5; i++) {
			try {
				Thread.sleep(300);
				System.out.println(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}