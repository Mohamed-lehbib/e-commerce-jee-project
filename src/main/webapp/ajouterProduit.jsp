<%@page import="commerce.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%/***
	User auth = (User) request.getSession().getAttribute("auth");
	if (auth != null) {
		response.sendRedirect("index.jsp");
	}
	ArrayList<Panier> panier_list = (ArrayList<Panier>) session.getAttribute("panier-list");
	if (panier_list != null) {
		request.setAttribute("panier_list", panier_list);
	}**/
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
			<div class="card-header text-center">Ajouter un produit</div>
			<div class="card-body">
				<form action="ajouter-nouveau-produit" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label>Nom du produit </label> 
						<input type="text" name="nom" class="form-control" placeholder="Enter le nom du produit" required>
					</div>
					<div class="form-group">
						<label>Categorie </label> 
						<input type="text" name="categorie" class="form-control" placeholder="Enter le categorie du produit" required>
					</div>
					<div class="form-group">
						<label>Prix </label> 
						<input type="number" name="prix" class="form-control" placeholder="Enter le prix du produit" required>
					</div>
					<div class="form-group">
						<label>Image </label> 
						<input type="file" name="image" class="form-control" placeholder="Enter le nom du produit" required>
					</div>
					
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Ajouter le produit </button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>