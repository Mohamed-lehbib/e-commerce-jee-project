<%@page import="java.text.DecimalFormat"%>
<%@page import="commerce.dao.CommandeDao"%>
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
	List<Commande> commandes = null;
	if (auth != null) {
	    request.setAttribute("person", auth);
	    CommandeDao commandeDao  = new CommandeDao(DbCon.getConnection());
		commandes = commandeDao.getAllCommandes();
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
		<div class="card-header my-3">Commandes</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">email utilisateur</th>
					
					
					<th scope="col">Quantite</th>
					<th scope="col">Prix</th>
					<th scope="col">Supprimer</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(commandes != null){
				for(Commande o:commandes){%>
					<tr>
						<td><%=o.getDate() %></td>
						<td><%=o.getUid() %></td>
						<td><%=o.getQunatity() %></td>
						<td><%=dcf.format(o.getPrice()) %></td>
						<td><a class="btn btn-sm btn-danger" href="annuler-commande-servlet?id=<%=o.getCommandeId()%>">Annuler la commande</a></td>
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