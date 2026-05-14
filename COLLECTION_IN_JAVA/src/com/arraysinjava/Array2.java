package com.arraysinjava;

import java.util.Arrays;
import java.util.Scanner;

public class Array2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		int siz=sc.nextInt();
		int arr[]=new int[siz];
		System.out.println("Enter the elements: ");
		for(int i=0;i<siz;i++) {
			int userin=sc.nextInt();
			arr[i]=userin;
		}
		System.out.println(Arrays.toString(arr));
		System.out.println("maximun ele:"+siz);
}
}
