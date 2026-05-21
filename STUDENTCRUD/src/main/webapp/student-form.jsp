<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>${empty student ? 'Add Student' : 'Edit Student'}</title>
<style>
body {
	font-family: Arial, sans-serif;
	padding: 32px;
	max-width: 480px;
}

h2 {
	color: #2c3e50;
	margin-bottom: 20px;
}

label {
	display: block;
	margin-bottom: 6px;
	font-weight: bold;
	font-size: 14px;
	color: #555;
}

input {
	width: 100%;
	padding: 9px 12px;
	border: 1px solid #ccc;
	border-radius: 6px;
	font-size: 14px;
	margin-bottom: 16px;
	box-sizing: border-box;
}

input:focus {
	border-color: #3498db;
	outline: none;
}

button {
	padding: 10px 24px;
	background: #2c3e50;
	color: #fff;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	font-size: 14px;
}

button:hover {
	background: #3d5166;
}

a.back {
	margin-left: 12px;
	font-size: 14px;
	color: #3498db;
}
</style>
</head>
<body>

	<h2>${empty student ? '➕ Add Student' : '✏️ Edit Student'}</h2>

	<form action="student" method="post">

		<!-- Hidden: insert or update -->
		<input type="hidden" name="action"
			value="${empty student ? 'insert' : 'update'}" />

		<!-- Hidden ID for update -->
		<c:if test="${not empty student}">
			<input type="hidden" name="id" value="${student.id}" />
		</c:if>

		<label>Name</label> <input type="text" name="name"
			value="${student.name}" placeholder="Full name" required /> <label>Email</label>
		<input type="email" name="email" value="${student.email}"
			placeholder="email@example.com" required /> <label>Marks</label> <input
			type="number" name="marks" value="${student.marks}" step="0.01"
			min="0" max="100" placeholder="0 – 100" required />

		<button type="submit">${empty student ? 'Add Student' : 'Update Student'}
		</button>
		<a class="back" href="student?action=list">← Back to list</a>

	</form>
</body>
</html>