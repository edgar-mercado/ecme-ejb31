package com.ecme.algorithms;

import java.util.Arrays;
import java.util.Scanner;

public class CircularRotationArray {
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int q = sc.nextInt();
        sc.nextLine();
        String[] arr = sc.nextLine().split(" ");
        int[] indexes = new int[q];
        for (int i=0; i<q; i++){
            indexes[i] = sc.nextInt();
        }
        rotate(arr);
        System.out.println(Arrays.toString(arr));        
        System.out.println(Arrays.toString(indexes));
    }
    public static String[] rotate (String[] arr){
        String last=arr[arr.length-1];
        String firs = arr[0];
        String tmp;
        String tmp2= arr[0];
        for (int i=1; i<arr.length-2; i++){
            tmp = arr[i+1];
            tmp = arr[i+1];
            arr[i+1]=arr[i];
            arr[i-1] = tmp2;
        }
        return arr;
    }
}
