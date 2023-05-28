<%@page import="java.text.DecimalFormat"%>
<%@page import="commerce.dao.UserDao"%>
<%@page import="commerce.connection.DbCon"%>
<%@page import="commerce.dao.ProduitDao"%>
<%@page import="commerce.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	User auth = (User) request.getSession().getAttribute("auth");
	List<User> users = null;
	if (auth != null) {
	    request.setAttribute("person", auth);
	    UserDao userDao  = new UserDao(DbCon.getConnection());
		users = userDao.getAllUsers();
	}else{
		response.sendRedirect("login.jsp");
	}

	
	%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>TESEWOUGHI</title>
</head>
<body>
	<%@include file="/includes/navbar2.jsp"%>
	<div class="container">
		<div class="card-header my-3">Users</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Nom</th>
					<th scope="col">Email utilisateur</th>
					<th scope="col">Password</th>
					<th scope="col">Supprimer</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(users != null){
				for(User user:users){%>
					<tr>
				
						<td><%=user.getName() %></td>
						<td><%=user.getEmail() %></td>
						<td><%=user.getPassword() %></td>
						<td><a class="btn btn-sm btn-danger" href="supprimeruser-servlet?id=<%=user.getId()%>">Supprimer l'utilisateur</a></td>
				
					</tr>
				<%}
			}
			%>
			
			</tbody>
		</table>
	</div>
	<%@include file="/includes/footer.jsp"%>
</body>
</html>
