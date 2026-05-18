package com.jdbcconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class JDBCConnection3 {

	public static void main(String[] args) {
		try {
			// 1. load driver -optional from +6
			Class.forName("com.mysql.cj.jdbc.Driver");
			// connections
			String url = "jdbc:mysql://localhost:3306/company";
			String user = "root";
			String password = "Varsha@2110";
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("connection successful!!");
			System.out.println(connection.getCatalog());

			// create statement
			Statement st = connection.createStatement();

			String sql = "create table Employee1("
					+"Id int primary key auto_increment,"
					+ "Name varchar(100),"
					+"department varchar(100),"
					+"salary double);\n";

			// execute
			int executeUpdate = st.executeUpdate(sql);

			// display
			System.out.println("SuccessFull: "+ executeUpdate+"effected");
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}