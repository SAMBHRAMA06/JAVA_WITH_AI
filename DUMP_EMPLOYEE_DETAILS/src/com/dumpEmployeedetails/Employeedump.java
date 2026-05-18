package com.dumpEmployeedetails;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employeedump {

	// How many rows to group into one batch before sending to DB
	private static final int BATCH_SIZE = 100;

	/**
	 * @param filePath
	 */
	public static void loadFromFile(String filePath) {

		String sql = "INSERT INTO employee (EmpID, EmpName, Salary, Department) VALUES (?,?,?,?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			con.setAutoCommit(false);

			BufferedReader reader = new BufferedReader(new FileReader(filePath));

			String line;
			int count = 0;
			int total = 0;

			while ((line = reader.readLine()) != null) {

				if (line.trim().isEmpty())
					continue;

				// CSV format: EmpID,EmpName,Salary,Department
				String[] parts = line.split(",");
				if (parts.length < 4)
					continue;

				ps.setInt(1, Integer.parseInt(parts[0].trim())); // EmpID
				ps.setString(2, parts[1].trim()); // EmpName
				ps.setInt(3, Integer.parseInt(parts[2].trim())); // Salary
				ps.setString(4, parts[3].trim()); // Department

				ps.addBatch();
				count++;
				total++;

				if (count == BATCH_SIZE) {
					ps.executeBatch();
					ps.clearBatch();
					count = 0;
					System.out.println("Inserted " + total + " rows so far...");
				}
			}
			reader.close();

			if (count > 0) {
				ps.executeBatch();
				ps.clearBatch();
			}

			con.commit();
			System.out.println("✅ Done! Total rows inserted: " + total);

		} catch (SQLException e) {
			System.err.println("❌ DB Error: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("❌ File Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		System.out.println("=== JDBC Batch Processing Demo ===");
		System.out.println("Starting file read and batch insert...");

		String filePath = "employee.csv";

		loadFromFile(filePath);

		System.out.println("=== Program Complete ===");
	}
}
