<!--
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Register</title>
	<link rel="stylesheet" type="text/css" href="/WEB-INF/register.css">
</head>
<body>
	<h1>Register</h1>
	<form method="post" action="register-servlet">
		<label for="username">Username:</label>
		<input type="text" id="name" name="name" required><br><br>
		<label for="email">Email:</label>
		<input type="email" id="email" name="email" required><br><br>
		<label for="password">Password:</label>
		<input type="password" id="password" name="password" required><br><br>
		<label for="confirmPassword">Confirm Password:</label>
		<input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
		<input type="submit" value="Register">
	</form>
</body>
</html>-->
<%@page import="commerce.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		response.sendRedirect("index.jsp");
	}
	ArrayList<Panier> panier_list = (ArrayList<Panier>) session.getAttribute("panier-list");
	if (panier_list != null) {
		request.setAttribute("panier_list", panier_list);
	}
	%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>TESEWOUGHI</title>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Register</div>
			<div class="card-body">
				<form action="register-servlet" method="post">
				<div class="form-group">
						<label>Username </label> 
						<input type="text" name="name" class="form-control" placeholder="Enter votre Nom" required>
					</div>
					<div class="form-group">
						<label>Email </label> 
						<input type="email" name="email" class="form-control" placeholder="Enter votre email" required>
					</div>
					<div class="form-group">
						<label>mot de passe</label> 
						<input type="password" name="password" class="form-control" placeholder="Password" required>
					</div>
					<div class="form-group">
						<label>confirmation de mot de passe</label> 
						<input type="password" name="confirmPassword" class="form-control" placeholder="Password" required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-info">créer une compte</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>