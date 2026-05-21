package com.example.student_management.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    // @Id — This field is the Primary Key
    // @GeneratedValue — MySQL auto-increments this ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank — Validation: field cannot be empty or whitespace
    // @Size     — Validation: controls min/max length
    // @Column   — Maps this field to a specific database column
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    // @Email   — Validates proper email format (abc@domain.com)
    // UNIQUE   — No two students can have the same email in the database
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Enter a valid phone number")
    @Column(name = "phone", length = 20)
    private String phone;

    @NotBlank(message = "Course is required")
    @Column(name = "course", length = 100)
    private String course;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    // @CreationTimestamp — Automatically set when record is created
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // @UpdateTimestamp — Automatically updated every time record changes
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Helper method — Not stored in DB, just useful for display
    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
}