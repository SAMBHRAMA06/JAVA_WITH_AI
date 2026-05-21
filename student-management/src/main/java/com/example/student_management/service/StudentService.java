package com.example.student_management.service;

import org.springframework.data.domain.Page;

import com.example.student_management.model.Student;

// Interface defines WHAT operations are available
// The implementation (StudentServiceImpl) defines HOW they work
public interface StudentService {
	// CREATE — save a new student
	Student saveStudent(Student student);

	// READ ALL — get all students with pagination
	Page<Student> getAllStudents(int pageNo, int pageSize, String sortField, String sortDir);

	// READ ONE — get student by ID
	Student getStudentById(Long id);

	// UPDATE — update an existing student
	Student updateStudent(Student student);

	// DELETE — remove student by ID
	void deleteStudent(Long id);

	// SEARCH — search students by keyword with pagination
	Page<Student> searchStudents(String keyword, int pageNo, int pageSize);

	// Count total students
	long getTotalStudents();
}
