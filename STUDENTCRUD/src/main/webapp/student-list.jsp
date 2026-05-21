<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Student List</title>
<style>
body {
	font-family: Arial, sans-serif;
	padding: 24px;
}

h2 {
	color: #2c3e50;
}

table {
	border-collapse: collapse;
	width: 100%;
	margin-top: 16px;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px 14px;
	text-align: left;
}

th {
	background: #2c3e50;
	color: #fff;
}

tr:nth-child(even) {
	background: #f5f6fa;
}

a.btn {
	padding: 5px 12px;
	border-radius: 4px;
	text-decoration: none;
	font-size: 13px;
	font-weight: bold;
}

a.edit {
	background: #3498db;
	color: #fff;
	margin-right: 4px;
}

a.delete {
	background: #e74c3c;
	color: #fff;
}

a.add {
	background: #27ae60;
	color: #fff;
	display: inline-block;
	padding: 8px 18px;
	border-radius: 4px;
	margin-bottom: 16px;
}
</style>
</head>
<body>

	<h2>Student Records</h2>
	<a class="add" href="student?action=new">+ Add Student</a>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Marks</th>
			<th>Actions</th>
		</tr>

		<c:forEach var="s" items="${students}">
			<tr>
				<td>${s.id}</td>
				<td>${s.name}</td>
				<td>${s.email}</td>
				<td>${s.marks}</td>
				<td><a class="btn edit" href="student?action=edit&id=${s.id}">Edit</a>
					<a class="btn delete" href="student?action=delete&id=${s.id}"
					onclick="return confirm('Delete this student?')">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>s