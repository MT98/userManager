<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ajout d'un utilisateur</title>
	<link rel="stylesheet" href="<c:url value='/style.css'/>">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
	<c:import url="inc/entete.jsp"/>
	<c:import url="inc/menu.jsp"/>
	<div class="container">
	  <h2>Ajout d'un client</h2>
	  <form method="post" action="add">
	    <div class="form-group">
	      <label for="nom">Nom:</label>
	      <input type="text" class="form-control" id="nom"  name="nom" value="${ requestScope.utilisateur.nom }">
	      <span>${ messageErreurs.nom }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="prenom">Prenom:</label>
	      <input type="text" class="form-control" id="prenom"  name="prenom" value="${ requestScope.utilisateur.prenom }">
	      <span>${ messageErreurs.prenom }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="login">Login:</label>
	      <input type="text" class="form-control" id="login"  name="login" value="${ requestScope.utilisateur.login }">
	      <span>${ messageErreurs.login }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="password">Mot de passe:</label>
	      <input type="password" class="form-control" id="password"  name="password">
	      <span>${ messageErreurs.password }</span><br>
	    </div>
	    <div class="form-group">
	      <label for="passwordBis">Mot de passe:</label>
	      <input type="password" class="form-control" id="passwordBis"  name="passwordBis">
	      <span>${ messageErreurs.passwordBis }</span><br>
	    </div>
	
	    <button type="submit" class="btn btn-primary">Submit</button>
	    <span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
	  </form>
	</div>
	<!--
	<form method="post" action="add">
		<fieldset>
			<legend>Ajout d'un utilisateur</legend>
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
			<input type="submit" value="Ajouter">
			<span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
		</fieldset>
	</form>
	-->
</body>
</html>