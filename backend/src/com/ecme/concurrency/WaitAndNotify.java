package com.ecme.concurrency;

public class WaitAndNotify {
	static Processor processor = new Processor();

	public static void main(String[] args) throws InterruptedException {
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
			t1.join();
			t2.join();
	}
}

class Processor{
	public void produce() throws InterruptedException{
		synchronized (this) {
			System.out.println("Producer method");
			wait();
			System.out.println("Producer method again");
		}
	}
	
	public void consume() throws InterruptedException{
		Thread.sleep(2000);

		synchronized (this) {
			System.out.println("Consumer method...");
			Thread.sleep(3000);
			notify();
		}
	}
}