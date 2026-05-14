package com.arraysinjava;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class Array3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		int size = sc.nextInt();
		int arr[] = new int[size];
		for (int i = 0; i < size; i++) {
			System.out.println("Enter " + (i + 1) + "elements :");
			arr[i] = sc.nextInt();
		}
		OptionalInt max = Arrays.stream(arr).max();
		System.out.println(max.getAsInt());
	}
}
