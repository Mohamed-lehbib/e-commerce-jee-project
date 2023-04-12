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
			<div class="card-header text-center">Connection</div>
			<div class="card-body">
				<form action="login-servlet" method="post">
					<div class="form-group">
						<label>Email </label> 
						<input type="email" name="login-email" class="form-control" placeholder="Enter votre email" required>
					</div>
					<div class="form-group">
						<label>mot de passe</label> 
						<input type="password" name="login-password" class="form-control" placeholder="Password" required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-info">connecter</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>