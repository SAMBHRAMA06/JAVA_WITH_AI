package com.polymorphism;

class Calculation {
	public int add(int a, int b) {
		return a + b;
	}

	public int add(int a, int b, int c) {
		return a + b + c;
	}

	public double add(double a, double b) {
		return a + b;
	}

}
//complit time polymorphism
public class MethodOverloading {
	public static void main(String[] args) {
		Calculation c = new Calculation();
		int res = c.add(10, 20);
		System.out.println("res: " + res);
		int res1 = c.add(20, 20, 20);
		System.out.println("res: " + res1);
		double res2 = c.add(20.4, 30.4);
		System.out.println("res: " + res2);
	}

}
