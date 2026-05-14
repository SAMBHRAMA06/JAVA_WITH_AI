package com.typecasting;

public class TypeCasting {
	public static void main(String[] args) {
		// implicit
		byte age = 20;
		int age1 = age;

		System.out.println("age: " + age);
		System.out.println("age1:" + age1);

		// explicit
		int age2 = 20;
		byte age3 = (byte) age2;

		System.out.println("age2: " + age2);
		System.out.println("age3: " + age3);
	}

}
