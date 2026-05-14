package com.assignments;

import java.util.Scanner;

public class ArmstrongNumber {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter a number: ");
		int num = sc.nextInt();

		int original = num;
		int sum = 0;

		while (num > 0) {

			int digit = num % 10;
			sum += digit * digit * digit;
			num /= 10;
		}

		if (sum == original) {
			System.out.println("Armstrong Number");
		} else {
			System.out.println("Not Armstrong Number");
		}

		sc.close();
	}
}