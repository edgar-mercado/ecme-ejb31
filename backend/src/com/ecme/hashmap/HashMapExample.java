package com.ecme.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Random;

public class HashMapExample {
	HashMap<String, Integer> cache = new HashMap<String, Integer>();
	public void test(){
		Hashtable<Integer, Integer> source = new Hashtable<Integer,Integer>();
		HashMap<Integer, Integer>  map = new HashMap(source);
		Random rdm = new Random();
		
		int c= 10;
		int a[]= new int[c];
		int b[]= new int[c];
		for (int i=0; i<c; i++ ){
			a[i]= rdm.nextInt();
		}
		for (int i=0; i<c; i++){
			b[i]= rdm.nextInt();
		}
		
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(b));
		long start = System.currentTimeMillis();
		calculate(a,b);
		long end = System.currentTimeMillis();
		System.out.println("Time taken "+(end-start)+" millis");
	/*	
		map.put(21, "Twenty One");
		map.put(21, "Twenty One");
		
		Integer key = 21;
		String value = map.get(key);
		System.out.println("Key: " + key +" value: "+ value);
		*/ 
	}
	void calculate(int a[], int b[]){
		boolean exist=false;
		
		int count=0;
		
		
		for (int i=0; i<b.length; i++){
			for (int j=0;j<a.length; j++){
				if (b[j]==a[i]){
					exist=true;
					count++;
					break;
				} else
					exist=true;
			}
		}
		if (count>=b.length){
			System.out.println("All items in the array exist");
		} else
			System.out.println("Not all elements in");
		
	}
	
	public static void main(String[] args) {
		HashMapExample hme= new HashMapExample();
		hme.test();
	}
}
