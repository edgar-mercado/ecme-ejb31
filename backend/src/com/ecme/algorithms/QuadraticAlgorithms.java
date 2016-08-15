package com.ecme.algorithms;

import java.util.Arrays;

public class QuadraticAlgorithms {
	int A[];
	public  QuadraticAlgorithms (int arr[]){
		this.A=arr;
	}
	
	public int[] sortBubbleSort(){
		int tmp=0;
		for (int i=0; i<=A.length-2;i++){
			for (int j=0; j<=(A.length-2-i); j++){
				if (A[j] > A[j+1]){
					tmp = A[j+1];
					A[j+1] = A[j];
					A[j] = tmp;
				}
				System.out.println(Arrays.toString(A));
			}
		}

		return A;
	}
	
	public int[] sortSelectionSort(){
		int tmp2=0;
		int index=0;
		for (int i=0; i<=A.length-2;i++){
			index=i;
			for (int j=i+1; j<=(A.length-1); j++){
				if (A[j] < A[index]){
					index = j;
				}
			}
			tmp2 = A[index] ;
			A[index] = A[i];
			A[i] = tmp2;

			System.out.println(Arrays.toString(A));
		}
		return A;
	}
	public int[] sortInsertionSort(){
		int tmp2=0;
		int index=0;
		for (int i=0; i<=A.length-2;i++){
			index=i;
			for (int j=i+1; j<=(A.length-1); j++){
				if (A[j] < A[index]){
					index = j;
				}
			}
			tmp2 = A[index] ;
			A[index] = A[i];
			A[i] = tmp2;

			System.out.println(Arrays.toString(A));
		}
		return A;
	}
	
}
