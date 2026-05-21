package com.example.student_management.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.student_management.model.Student;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
	// JavaMailSender — Spring's abstraction for sending emails
	private final JavaMailSender mailSender;
	// @Value — Injects values from application.properties
	@Value("${spring.mail.username}")
	private String fromEmail;

	// @Async — Sends email in a separate thread (non-blocking)
	// Main thread continues without waiting for email to send
	@Async
	public void sendWelcomeEmail(Student student) {
		try {
			// MimeMessage supports HTML, attachments, and rich content
			MimeMessage message = mailSender.createMimeMessage();
			// MimeMessageHelper(message, true) — true = multipart (needed for OHTML)
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom(fromEmail, "Student Management System");
			helper.setTo(student.getEmail());
			helper.setSubject("🎓 Welcome to Student Management System, " + student.getFirstName() + "!");
			// Build the HTML email body
			String htmlContent = buildWelcomeEmailHtml(student);
			// true = send as HTML (false would send as plain text)
			helper.setText(htmlContent, true);
			mailSender.send(message);
			log.info("Welcome email sent successfully to: {}", student.getEmail());
		} catch (MessagingException e) {
			log.error("Failed to send email to {}: {}", student.getEmail(), e.getMessage());
			throw new RuntimeException("Email sending failed", e);
		} catch (Exception e) {
			log.error("Unexpected error sending email: {}", e.getMessage());
		}
	}

	// Builds the complete HTML email as a String
	private String buildWelcomeEmailHtml(Student student) {
		return "<!DOCTYPE html>" + "<html lang='en'>" + "<head>" + " <meta charset='UTF-8'>"
				+ " <meta name='viewport' content='width=device-width,initial-scale=1.0'>"
				+ " <title>Welcome Email</title>" + "</head>"
				+ "<body style='margin:0;padding:0;font-family:Arial,Helvetica,sans-serif;"
				+ "background-color:#f0f4f8;'>" + " <table width='100%' cellpadding='0' cellspacing='0'>"
				+ " <tr><td align='center' style='padding:40px 20px;'>"
				+ " <table width='600' style='max-width:600px;background:#ffffff;"
				+ "border-radius:16px;overflow:hidden;box-shadow:0 10px 40px rgba(0,0,0,0.12);'>" +
				// ── Gradient Header ──
				" <tr><td style='background:linear-gradient(135deg,#1565C0 0%,#0D47A1 50%,#1a237e 100%);"
				+ "padding:48px 40px;text-align:center;'>" + " <div style='font-size:56px;margin-bottom:16px;'>🎓</div>"
				+ " <h1 style='color:#ffffff;margin:0;font-size:28px;font-weight:700;"
				+ "letter-spacing:-0.5px;'>Welcome to SMS!</h1>"
				+ " <p style='color:#90CAF9;margin:8px 0 0;font-size:16px;'>Student Management System</p>"
				+ " </td></tr>" +
				// ── Greeting ──
				" <tr><td style='padding:40px 40px 24px;'>"
				+ " <h2 style='color:#1565C0;font-size:22px;margin:0 0 12px;'>Hello, " + student.getFirstName()
				+ "! 👋</h2>" + " <p style='color:#546E7A;line-height:1.7;margin:0;font-size:15px;'>Congratulations! "
				+ "Your student profile has been successfully created in our system. "
				+ "We're excited to have you on board.</p>" + " </td></tr>" +
				// ── Student Details Card ──
				" <tr><td style='padding:0 40px 32px;'>"
				+ " <div style='background:linear-gradient(135deg,#E3F2FD,#EDE7F6);"
				+ "border-radius:12px;padding:28px;border-left:5px solid #1565C0;'>"
				+ " <h3 style='color:#1565C0;margin:0 0 20px;font-size:17px;"
				+ "text-transform:uppercase;letter-spacing:1px;'>📋 Your Student Details</h3>"
				+ buildDetailRow("👤 Full Name", student.getFullName()) + buildDetailRow("📧 Email", student.getEmail())
				+ buildDetailRow("📱 Phone", student.getPhone() != null ? student.getPhone() : "Not provided")
				+ buildDetailRow("📚 Course", student.getCourse()) + " </div>" + " </td></tr>" +
				// ── Success Banner ──
				" <tr><td style='padding:0 40px 32px;'>"
				+ " <div style='background:#E8F5E9;border-radius:10px;padding:20px;"
				+ "text-align:center;border:1px solid #A5D6A7;'>" + " <span style='font-size:28px;'>✅</span>"
				+ " <p style='color:#2E7D32;font-weight:600;margin:8px 0 0;font-size:15px;'>"
				+ " Registration Successful — Your profile is now active!</p>" + " </div>" + " </td></tr>" +
				// ── CTA Button ──
				" <tr><td style='padding:0 40px 40px;text-align:center;'>"
				+ " <a href='http://localhost:8080/students' " +

				"style='display:inline-block;background:linear-gradient(135deg,#1565C0,#0D47A1);"
				+ "color:#ffffff;text-decoration:none;padding:15px 40px;border-radius:50px;"
				+ "font-weight:600;font-size:15px;letter-spacing:0.5px;"
				+ "box-shadow:0 4px 20px rgba(21,101,192,0.4);'>" + " 🎓 View Your Profile</a>" + " </td></tr>" +
				// ── Footer ──
				" <tr><td style='background:#F5F5F5;padding:24px 40px;text-align:center;"
				+ "border-top:1px solid #E0E0E0;'>"
				+ " <p style='color:#90A4AE;font-size:13px;margin:0;line-height:1.6;'>"
				+ " This email was sent by Student Management System.<br>"
				+ " &copy; 2024 SMS. All rights reserved.</p>" + " </td></tr>" + " </table>" + " </td></tr>"
				+ " </table>" + "</body></html>";
	}

	private String buildDetailRow(String label, String value) {
		return "<div style='display:flex;margin-bottom:14px;align-items:flex-start;'>"
				+ " <span style='color:#1565C0;font-weight:600;min-width:140px;" + "font-size:14px;'>" + label
				+ ":</span>" + " <span style='color:#37474F;font-size:14px;'>" + value + "</span>" + "</div>";
	}
}
