<%@page import="commerce.connection.DbCon"%>
<%@page import="commerce.dao.ProduitDao"%>
<%@page import="commerce.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}
ArrayList<Panier > panier_list = (ArrayList<Panier >) session.getAttribute("panier-list");
List<Panier > panierProduit = null;
if (panier_list != null) {
	ProduitDao pDao = new ProduitDao(DbCon.getConnection());
	panierProduit = pDao.getPanierProduits(panier_list);
	double total = pDao.getTotalPanierPrice(panier_list);
	request.setAttribute("total", total);
	request.setAttribute("panier_list", panier_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
<title>TESEWOUGHI</title>
<style type="text/css">

.table tbody td{
vertical-align: middle;
}
.btn-incre, .btn-decre{
box-shadow: none;
font-size: 25px;
}
</style>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container my-3">
		<div class="d-flex py-3"><h3>Prix Total:  ${(total>0)?dcf.format(total):0} MRU</h3> <a class="mx-3 btn btn-info" href="verifier-servlet">passer commande</a></div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">NOM</th>
					<th scope="col">Categorie</th>
					<th scope="col">Prix</th>
					<th scope="col">Acheter</th>
					<th scope="col">Annuler</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (panier_list != null) {
					for (Panier  c : panierProduit) {
				%>
				<tr>
					<td><%=c.getName()%></td>
					<td><%=c.getCategory()%></td>
					<td><%= dcf.format(c.getPrice())%></td>
					<td>
						<form action="passer-commande-servlet" method="post" class="form-inline">
						<input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn bnt-sm btn-incre " href="quantityIncDec-servlet?action=inc&id=<%=c.getId()%>"><i class="fas fa-plus-square"></i></a> 
								<input type="text" name="quantity" class="quantityIncDec"  value="<%=c.getQuantity()%>" readonly> 
								<a class="btn btn-sm btn-decre" href="quantityIncDec-servlet?action=dec&id=<%=c.getId()%>"><i class="fas fa-minus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-info btn-sm">Acheter</button>
						</form>
					</td>
					<td><a href="supProduitPanier-servlet?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Supprimer</a></td>
				</tr>

				<%
				}}%>
			</tbody>
		</table>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>