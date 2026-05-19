package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.html");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();

		if ("admin".equals(userName) && "1234".equals(password)) {

			pw.println("<html><body>");
			pw.println("<h2>Welcome, " + userName + "!</h2>");
			pw.println("</body></html>");

		} else {

			pw.println("<html><body>");
			pw.println("<h2>Invalid Credentials!</h2>");
			pw.println("</body></html>");
		}
	}
}