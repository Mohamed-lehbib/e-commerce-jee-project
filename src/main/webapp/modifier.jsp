<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/includes/head.jsp"%>
</head>
<body>
    <%@include file="/includes/navbar.jsp"%>
    <div class="container">
		<div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">Modifier le produit</div>
        <div class="card-body">
        <form method="post">
            <div class="form-group">
                <label for="name">Nom:</label>
                <input type="text" class="form-control" id="name" name="name" value="${produit.getName()}" />
            </div>
            <div class="form-group">
                <label for="categorie">Catégorie:</label>
                <input type="text" class="form-control" id="categorie" name="categorie" value="${produit.getCategory()}" />
            </div>
     
            <div class="form-group">
                <label for="price">Prix:</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" value="${produit.getPrice()}" />
            </div>
            <div class="text-center">
            <button type="submit" class="btn btn-info">Modifier</button>
            </div>
        </form>
        </div>
        </div>
    </div>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>
