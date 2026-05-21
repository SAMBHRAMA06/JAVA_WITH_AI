package com.jdbcconnection2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class updateEmployeeSalaryUsingId {

	public static void updateEmployeeSalaryUsingId() {

		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Employee ID: ");
		int id = sc.nextInt();

		System.out.print("Enter New Salary: ");
		double salary = sc.nextDouble();

		String sql = "UPDATE employees SET salary = ? WHERE id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setDouble(1, salary);
			ps.setInt(2, id);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				System.out.println("Salary Updated Successfully");
			} else {
				System.out.println("Employee Not Found");
			}

		} catch (Exception e) {
			e.printStackTrace(); 
		} finally {
			sc.close(); // close scanner to avoid resource leak
		}
	}

	public static void main(String[] args) {
		updateEmployeeSalaryUsingId(); // call the method from main
	}
}
