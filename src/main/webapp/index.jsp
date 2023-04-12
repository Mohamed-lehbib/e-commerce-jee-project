<%@page import="commerce.connection.DbCon"%>
<%@page import="commerce.dao.ProduitDao"%>
<%@page import="commerce.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<% 
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}

ProduitDao pd = new ProduitDao(DbCon.getConnection());
ArrayList<Panier> panier_list = (ArrayList<Panier>) session.getAttribute("panier-list");
if (panier_list != null) {
  request.setAttribute("panier_list", panier_list);
}

List<Produit> produits;
String selectedCategory = request.getParameter("category");
if (selectedCategory == null || selectedCategory.isEmpty()) {
  produits = pd.getAllProduits();
} else {
  produits = pd.getProduitsByCategory(selectedCategory);
}
request.setAttribute("produits", produits);

List<String> categories = pd.getAllCategories();

// Check if user role is "admin", and only display "Ajouter un produit" button in that case
boolean isAdmin = auth != null && auth.getRole().equals("admin");
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
		<div class="card-header my-3" ><h3 style="text-align:center">Produits</h3> 
<% if (isAdmin) { %>
		<a class="mx-3 btn btn-info float-right" href="ajouterProduit.jsp" >Ajouter un produit</a>
		<%}%>


		
	
                
                    <button class="btn btn-outline-secondary dropdown-toggle mid-right" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
  							Filter par categorie
				</button>
				<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				  <%-- Create a dropdown option for each category --%>
				  <a class="dropdown-item" href="index.jsp?selectedCategory = null">tous les Categories</a>
				  <% for (String category : categories) { %>
				    <a class="dropdown-item" href="index.jsp?category=<%= category %>"><%= category %></a>
				  <% } %>
				  
				</div>

                
           
        </div>
		<div class="row">
			<%
			if (!produits.isEmpty()) {
				for (Produit p : produits) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="image-produit/<%=p.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Prix: <%=p.getPrice() %> MRU</h6>
						<h6 class="category">Categorie: <%=p.getCategory() %></h6>
						<% if (!isAdmin) { %>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-info btn-sm" href="ajouter-panier-servlet?id=<%=p.getId()%>">Ajouter au panier</a> <a
								class="btn btn-secondary  btn-sm" href="passer-commande-servlet?quantity=1&id=<%=p.getId()%>">Acheter</a>
						</div>
						<%}%>
						<% if (isAdmin) { %>
						<div class="mt-3 d-flex justify-content-between">
						<a class="btn btn-info" href="modifier-produit?id=<%=p.getId()%>">Modifier</a>
							<a class="btn btn-danger" href="sup-produit?id=<%=p.getId()%>">Supprimer</a> 
						</div>
						<%}%>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("il n'y a pas de produit ");
			}
			%>

		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>