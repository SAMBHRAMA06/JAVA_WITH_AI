package com.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

	private final StudentDAO dao = new StudentDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		if (action == null)
			action = "list";

		try {
			switch (action) {

			// ── LIST ──────────────────────────────
			case "list": {
				List<Student> students = dao.getAllStudents();
				req.setAttribute("students", students);
				req.getRequestDispatcher("/student-list.jsp").forward(req, res);
				break;
			}

			// ── SHOW ADD FORM ─────────────────────
			case "new":
				req.getRequestDispatcher("/student-form.jsp").forward(req, res);
				break;

			// ── SHOW EDIT FORM ────────────────────
			case "edit": {
				int id = Integer.parseInt(req.getParameter("id"));
				Student s = dao.getStudentById(id);
				req.setAttribute("student", s);
				req.getRequestDispatcher("/student-form.jsp").forward(req, res);
				break;
			}

			// ── DELETE ────────────────────────────
			case "delete": {
				int id = Integer.parseInt(req.getParameter("id"));
				dao.deleteStudent(id);
				res.sendRedirect("student?action=list");
				break;
			}
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String action = req.getParameter("action");
		String name = req.getParameter("name").trim();
		String email = req.getParameter("email").trim();
		double marks = Double.parseDouble(req.getParameter("marks"));

		try {
			if ("insert".equals(action)) {
				// ── CREATE ────────────────────────────
				dao.insertStudent(new Student(0, name, email, marks));

			} else if ("update".equals(action)) {
				// ── UPDATE ────────────────────────────
				int id = Integer.parseInt(req.getParameter("id"));
				dao.updateStudent(new Student(id, name, email, marks));
			}
			res.sendRedirect("student?action=list");

		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
}