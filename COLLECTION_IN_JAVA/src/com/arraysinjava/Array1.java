package com.arraysinjava;

import java.util.Arrays;
import java.util.Scanner;

public class Array1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the size:");
		int size = sc.nextInt();
		int arr[] = new int[3];
		arr[0] = 120;
		arr[1] = 130;
		arr[2] = 140;
		System.out.println(arr[1]);
		System.out.println(Arrays.toString(arr));
		for (int ele : arr) {
			System.out.println(ele);
		}
	}

}
