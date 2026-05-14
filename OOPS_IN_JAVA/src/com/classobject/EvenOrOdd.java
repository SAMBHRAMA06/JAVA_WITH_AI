package com.classobject;

public class EvenOrOdd {
	static boolean isEven(int a) {
		return a % 2 == 0;
	}

	public static void main(String[] args) {
		boolean res = isEven(20);
		System.out.println(res);
	}

}
