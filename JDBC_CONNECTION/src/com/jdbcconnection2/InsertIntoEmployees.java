package com.jdbcconnection2;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertIntoEmployees {

	private static void InsertIntoEmployees(String name, String dept, double salary) {

		String sql = "INSERT INTO employee1(name, department, salary) VALUES (?, ?, ?)";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, name);
			ps.setString(2, dept);
			ps.setDouble(3, salary);

			int rows = ps.executeUpdate();

			System.out.println(rows + " row affected");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		InsertIntoEmployees("pavan", "IT", 12000);
		InsertIntoEmployees("rahul", "HR", 25000);
	}
}