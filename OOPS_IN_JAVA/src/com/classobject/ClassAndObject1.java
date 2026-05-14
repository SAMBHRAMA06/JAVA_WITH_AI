package com.classobject;

class Employee1 {
	String name;
	int salary;

	Employee1(String name, int salary) {
		this.name = name;
		this.salary = salary;
	}

	public void display() {
		System.out.println("Name: " + name);
		System.out.println("Salary: " + this.salary);
	}

	@Override
	public String toString() {
		return "Employee1 [name=" + name + ", salary=" + salary + "]";
	}
}

public class ClassAndObject1 {
	public static void main(String[] args) {
		Employee1 emp1 = new Employee1("sam", 12000);
		emp1.display();
		System.out.println(emp1);

		Employee1 emp2 = new Employee1("sky", 15000);
		emp2.display();
		System.out.println(emp2);
	}
}