package com.jdbcconnection2;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class deleteEmployeeUsingId {

	private static void deleteEmployeeUsingId(int id) {
		String sql = "DELETE FROM employee1 WHERE id = ?";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id); // safely bind the parameter
			int rows = ps.executeUpdate();

			System.out.println(rows + " row(s) deleted");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		deleteEmployeeUsingId(4); // deletes employee with ID 4
	}
}
