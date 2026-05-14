package com.classobject;

class Employee {
	String name;
	int salary;

	Employee(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Salary: " + this.salary);

	}
}

public class ClassAndObject3 {
	public static void main(String[] args) {
		Employee emp1 = new Employee("sam", 12000);
		emp1.display();
		Employee emp2 = new Employee("sky", 15000);
		emp2.display();
	}

}
