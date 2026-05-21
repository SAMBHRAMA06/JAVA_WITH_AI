package com.student;

import java.sql.*;
import java.util.*;

public class StudentDAO {

	// ── CREATE ────────────────────────────────────
	public void insertStudent(Student s) throws SQLException {
		String sql = "INSERT INTO students (name, email, marks) VALUES (?, ?, ?)";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setDouble(3, s.getMarks());
			ps.executeUpdate();
		}
	}

	// ── READ ALL ──────────────────────────────────
	public List<Student> getAllStudents() throws SQLException {
		List<Student> list = new ArrayList<>();
		String sql = "SELECT * FROM students ORDER BY id";
		try (Connection con = DBConnection.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
			while (rs.next()) {
				list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getDouble("marks")));
			}
		}
		return list;
	}

	// ── READ ONE ──────────────────────────────────
	public Student getStudentById(int id) throws SQLException {
		String sql = "SELECT * FROM students WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
							rs.getDouble("marks"));
				}
			}
		}
		return null;
	}

	// ── UPDATE ────────────────────────────────────
	public void updateStudent(Student s) throws SQLException {
		String sql = "UPDATE students SET name=?, email=?, marks=? WHERE id=?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, s.getName());
			ps.setString(2, s.getEmail());
			ps.setDouble(3, s.getMarks());
			ps.setInt(4, s.getId());
			ps.executeUpdate();
		}
	}

	// ── DELETE ────────────────────────────────────
	public void deleteStudent(int id) throws SQLException {
		String sql = "DELETE FROM students WHERE id = ?";
		try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}
}