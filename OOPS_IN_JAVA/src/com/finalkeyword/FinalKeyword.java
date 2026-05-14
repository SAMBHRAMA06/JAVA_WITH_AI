package com.finalkeyword;

class Parent{
	public 
	void display() {
		System.out.println("parent method");
	}
}
class Child extends Parent{

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
	}
	
}
public class FinalKeyword {
	public static void main(String[] args) {
		final double pi=3.14;
		System.out.println("PI: "+pi);
		
		final int marks;
		marks =245;
		System.out.println("Marks: "+marks);
	}

}
