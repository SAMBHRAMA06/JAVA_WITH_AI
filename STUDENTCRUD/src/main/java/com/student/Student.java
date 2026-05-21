package com.student;

public class Student {

	private int id;
	private String name;
	private String email;
	private double marks;

	// ── Constructors ──────────────────────────────
	public Student() {
	}

	public Student(int id, String name, String email, double marks) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.marks = marks;
	}

	// ── Getters & Setters ─────────────────────────
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String n) {
		this.name = n;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String e) {
		this.email = e;
	}

	public double getMarks() {
		return marks;
	}

	public void setMarks(double m) {
		this.marks = m;
	}
}