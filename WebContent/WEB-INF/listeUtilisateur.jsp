<%@page import="java.util.ArrayList"%>
<%@page import="beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs</title>
<link rel="stylesheet" href="<c:url value='/style.css'/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:import url="inc/entete.jsp"/>
	<c:import url="inc/menu.jsp"/>
	<c:choose>
		<c:when test="${ empty utilisateurs }">
			<p>La liste des utilisateurs est vide.</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="0">
				<tr>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Login</th>
					<th>Password</th>
					<th>Actions</th>
				</tr>
				<c:forEach items="${ utilisateurs }" var="utilisateur">
					<tr>
						<td><c:out value="${ utilisateur.nom }"/></td>
						<td><c:out value="${ utilisateur.prenom }"/></td>
						<td><c:out value="${ utilisateur.login }"/></td>
						<td><c:out value="${ utilisateur.password }"/></td>
						<td><a href="<c:url value='/users/view?login=${utilisateur.login}' />">Voir</a> | <a href="<c:url value='/users/modify?login=${utilisateur.login}'/>">Modifier</a> | <a href="<c:url value='/users/delete?login=${utilisateur.login}'/>">Supprimer</a> </td>
					</tr>
				</c:forEach>
			</table>
				<p> <span class="${param.statut}">${param.message }</span> </p>
	
		</c:otherwise>
	</c:choose>
	
</body>
</html>