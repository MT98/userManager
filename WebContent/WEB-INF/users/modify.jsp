<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Modification d'un utilisateur</title>
	<link rel="stylesheet" href="<c:url value='../style.css'/>">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:import url="../inc/entete.jsp"/>
	<c:import url="../inc/menu.jsp"/>
	<form method="post" action="modify">
		<fieldset>
			<legend>Modification d'un utilisateur</legend>
			<input type="text" hidden name="lastLogin" value="${ empty param.lastLogin ? requestScope.utilisateur.login : param.lastLogin  }">
			<label>Nom :</label>
			<input type="text" name="nom" value="${ requestScope.utilisateur.nom }">
			<span>${ messageErreurs.nom }</span><br>
			<label>Prénom :</label>
			<input type="text" name="prenom" value="${ requestScope.utilisateur.prenom }">
			<span>${ messageErreurs.prenom }</span><br>
			<label>Login :</label>
			<input type="text" name="login" value="${ requestScope.utilisateur.login }">
			<span>${ messageErreurs.login }</span><br>
			<label>Password :</label>
			<input type="password" name="password">
			<span>${ messageErreurs.password }</span><br>
			<label>Password (bis) :</label>
			<input type="password" name="passwordBis">
			<span>${ messageErreurs.passwordBis }</span><br>
			<input type="submit" value="Modifier">
			<span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
			
		</fieldset>
	</form>
</body>
</html>