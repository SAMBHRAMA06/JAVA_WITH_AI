package com.example.student_management.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/students")
@Slf4j
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;
	// Default pagination constants
	private static final int DEFAULT_PAGE = 1;
	private static final int PAGE_SIZE = 5;
	private static final String SORT_FIELD = "firstName";
	private static final String SORT_DIR = "asc";

	// ─── Dashboard ────────────────────────────────────────
	// @GetMapping — Handles HTTP GET requests
	// URL: GET /students
	@GetMapping
	public String listStudents(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "firstName") String sortField,
			@RequestParam(defaultValue = "asc") String sortDir, @RequestParam(required = false) String keyword,
			Model model) {
		log.debug("Listing students - page: {}, keyword: {}", page, keyword);
		Page<Student> studentPage;
		if (keyword != null && !keyword.trim().isEmpty()) {
			studentPage = studentService.searchStudents(keyword, page, PAGE_SIZE);
		} else {
			studentPage = studentService.getAllStudents(page, PAGE_SIZE, sortField, sortDir);
		}
//Model — a Map that holds data to pass to the HTML template
		model.addAttribute("students", studentPage.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", studentPage.getTotalPages());
		model.addAttribute("totalItems", studentPage.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("keyword", keyword);
		model.addAttribute("totalStudents", studentService.getTotalStudents());
// Returns the template name: templates/students/list.html
		return "students/list";
	}

// ─── Show Add Form ────────────────────────────────────
// URL: GET /students/add
	@GetMapping("/add")
	public String showAddForm(Model model) {
// Pass empty Student object to pre-bind the form
		model.addAttribute("student", new Student());
		model.addAttribute("pageTitle", "Add New Student");
		return "students/add";
	}

// ─── Process Add Form ─────────────────────────────────
// @PostMapping — Handles HTTP POST requests (form submissions)
// @Valid — Triggers Bean Validation on the Student object
// BindingResult — Contains validation errors (must come right after @Valid)
// RedirectAttributes — Passes messages through redirect (flash attributes)
// URL: POST /students/add
	@PostMapping("/add")
	public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {
// If validation failed, show the form again with errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", "Add New Student");
			return "students/add";
		}
		try {
			studentService.saveStudent(student);
// Flash attribute survives the redirect
			redirectAttributes.addFlashAttribute("successMessage",
					"Student " + student.getFullName() + " added successfully! Welcome email sent.");
			return "redirect:/students";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("pageTitle", "Add New Student");
			return "students/add";
		}
	}

// ─── Show Edit Form ───────────────────────────────────
// @PathVariable — Gets {id} from the URL
// URL: GET /students/edit/5
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			Student student = studentService.getStudentById(id);
			model.addAttribute("student", student);
			model.addAttribute("pageTitle", "Edit Student: " + student.getFullName());
			return "students/edit";
		} catch (StudentNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
			return "redirect:/students";
		}
	}

// ─── Process Edit Form ────────────────────────────────
// URL: POST /students/edit/{id}
	@PostMapping("/edit/{id}")
	public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute("student") Student student,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", "Edit Student");
			return "students/edit";
		}
		try {
			student.setId(id); // ensure correct ID
			studentService.updateStudent(student);
			redirectAttributes.addFlashAttribute("successMessage", "Student updated successfully!");
			return "redirect:/students";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "students/edit";
		}
	}

// ─── Delete Student ───────────────────────────────────
// URL: GET /students/delete/{id}
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		try {
			Student student = studentService.getStudentById(id);
			studentService.deleteStudent(id);
			redirectAttributes.addFlashAttribute("successMessage",
					"Student " + student.getFullName() + " deleted successfully!");
		} catch (StudentNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		return "redirect:/students";
	}

// ─── Dashboard Home ───────────────────────────────────
// URL: GET /
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("totalStudents", studentService.getTotalStudents());
		return "index";
	}
}