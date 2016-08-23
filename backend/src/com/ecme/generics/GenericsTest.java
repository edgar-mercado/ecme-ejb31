package com.ecme.generics;

public class GenericsTest {
	public static <E> E test1(E e1, E e2){
		System.out.println(e1.toString());
		System.out.println(e2.toString());
		return e1;
	}
	public static <E extends Comparable<E>> E getMex(E e1, E e2){
		int result= e1.compareTo(e2);
		return (result>0 ? e1:e2);
	}	
	public static void main(String[] args) {
		String e1="String 1";
		String e2="String 2";
		int int1=1;
		int int2=2;
		System.out.println(test1(e1,e2));
		System.out.println(test1(int1,int2));
		System.out.println(getMex(int1,int2));
		System.out.println(getMex(8,2));
		System.out.println(getMex(33.3,12.5));
		System.out.println(getMex(-2,-222));
		System.out.println(getMex("World","ZHola"));
		
	}
}
