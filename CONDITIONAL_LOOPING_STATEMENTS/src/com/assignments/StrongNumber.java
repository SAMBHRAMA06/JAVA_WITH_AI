package com.assignments;

import java.util.Scanner;

public class StrongNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int num = sc.nextInt();

		int original = num;
		int sum = 0;

		int[] fact = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

		while (num > 0) {
			int digit = num % 10;
			sum += fact[digit];
			num /= 10;
		}

		if (sum == original) {
			System.out.println("Strong Number");
		} else {
			System.out.println("Not Strong Number");
		}

		sc.close();
	}
}