package com.example.student_management.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.student_management.email.EmailService;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.model.Student;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @Service      — Marks this class as a Spring Service bean
// @Transactional — All methods are wrapped in database transactions
// @Slf4j         — Lombok: creates a "log" variable for logging (SLF4J)
// @RequiredArgsConstructor — Lombok: generates constructor for final fields (for DI)
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	// Spring automatically injects these beans (Constructor Injection)
	private final StudentRepository studentRepository;
	private final EmailService emailService;

	@Override
	public Student saveStudent(Student student) {
		log.info("Saving new student: {} {}", student.getFirstName(), student.getLastName());

		// Check if email already exists
		if (studentRepository.existsByEmail(student.getEmail())) {
			throw new RuntimeException("Email already registered: " + student.getEmail());
		}

		// Save to database
		Student savedStudent = studentRepository.save(student);
		log.info("Student saved with ID: {}", savedStudent.getId());

		// Send welcome email asynchronously
		try {
			emailService.sendWelcomeEmail(savedStudent);
			log.info("Welcome email sent to: {}", savedStudent.getEmail());
		} catch (Exception e) {
			// Email failure should not prevent student from being saved
			log.error("Failed to send welcome email: {}", e.getMessage());
		}

		return savedStudent;
	}

	@Override
	@Transactional(readOnly = true) // readOnly = true optimizes read performance
	public Page<Student> getAllStudents(int pageNo, int pageSize, String sortField, String sortDir) {
		// Determine sort direction
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		// Create Pageable object (0-based page index)
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return studentRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Student getStudentById(Long id) {
		// orElseThrow — if not found, throw our custom exception
		return studentRepository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + id));
	}

	@Override
	public Student updateStudent(Student student) {
		log.info("Updating student with ID: {}", student.getId());
		// Verify student exists before updating
		getStudentById(student.getId()); // throws exception if not found
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		log.info("Deleting student with ID: {}", id);
		Student student = getStudentById(id); // throws exception if not found
		studentRepository.delete(student);
		log.info("Student deleted successfully");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Student> searchStudents(String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("firstName").ascending());
		return studentRepository.searchStudents(keyword, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public long getTotalStudents() {
		return studentRepository.count();
	}
}
