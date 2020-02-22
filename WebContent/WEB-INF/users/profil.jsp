<%@page import="beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mon profil</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:import url="../inc/entete.jsp"/>
	<c:import url="../inc/menu.jsp"/>
	<h1>Mon Profil</h1>
	<div>
		<c:if test="${ !empty requestScope.utilisateur}">
					<span>Nom:</span> ${requestScope.utilisateur.nom} <br>
					<span>Prénom:</span>${requestScope.utilisateur.prenom}<br>
					<span>Login:</span>${requestScope.utilisateur.login}<br>
					<span>Password:</span>${requestScope.utilisateur.password}<br>		
		</c:if>
		<c:if test=" ${ empty requestScope.utilisateur}">
					<p>Erreur, veuillez contacter l'administrateur du site.</p>		
		</c:if>

	</div>
</body>
</html>