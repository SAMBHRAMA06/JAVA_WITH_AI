package com.jdbcconnection2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class FetchAllEmployees {

	// FETCH ALL EMPLOYEES
	private static void fetchAllEmployee() {

		String sql = "SELECT * FROM employee1";

		try (Connection conn = DBConnection.getConnection();
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql)) {

			System.out.println("\nID\tNAME\tDEPARTMENT\tSALARY");

			while (rs.next()) {

				int id = rs.getInt("id");
				String name = rs.getString("name");
				String department = rs.getString("department");
				double salary = rs.getDouble("salary");

				System.out.println(id + "\t" + name + "\t\t" + department + "\t\t" + salary);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// MAIN METHOD
	public static void main(String[] args) {

		// FETCH
		fetchAllEmployee();

	}
}
