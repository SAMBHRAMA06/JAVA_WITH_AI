package com.aceessmodifiers;

class Person {
	public String name;
	private int age;
	int salary;
	protected void display() {
		System.out.println("this is person display");
	}
}
public class PublicAcessModifier {
	public static void main(String[] args) {
		Person p = new Person();
		p.name="Sam";
		System.out.println("Name: "+p.name);
	}

}
