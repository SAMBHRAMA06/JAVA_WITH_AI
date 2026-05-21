package com.uncheckedException;

public class UncheckedExcpetion1 {

	public static void main(String[] args) {
		System.out.println("Programm Start");
		int arr[] = { 1, 2, 3, 4, 5, 6 };
		System.out.println("Accessing 6th index");
		try {
			System.out.println("ele: " + arr[6]);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Program Closed");
	}
}
