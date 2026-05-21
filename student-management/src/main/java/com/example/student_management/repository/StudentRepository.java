package com.example.student_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.student_management.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	// Spring Data JPA "Query Method" — generates SQL from method name!
	// SQL: SELECT * FROM students WHERE first_name = ?
	List<Student> findByFirstName(String firstName);

	// SQL: SELECT * FROM students WHERE email = ?qqqqqqqqqqqqqqqqqqqqqqqqqq
	Optional<Student> findByEmail(String email);

	// Case-insensitive search with pagination
	// SQL: SELECT * FROM students WHERE LOWER(first_name) LIKE % ? % OR
//LOWER(last_name) LIKE % ? %
	Page<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName,
			Pageable pageable);

	// Custom JPQL query for searching by name or email
	@Query("SELECT s FROM Student s WHERE " + "LOWER(s.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(s.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
			+ "LOWER(s.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Student> searchStudents(@Param("keyword") String keyword, Pageable pageable);

	// Check if email already exists (for validation)
	boolean existsByEmail(String email);

	// Count students by course
	long countByCourse(String course);
}