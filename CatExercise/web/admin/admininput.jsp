<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/include/header.jspf" %>

<c:url value="/admincontroller?choix=createflush"  var="url" />
<h2>Ajouter un utilisateur</h2> <br>

<form action="${url}" method="get">
    <label for="login">Login :</label><input name="login" type="text" /><br>
    <label for="Mot de passe">Mot de passe :</label><input name="password" type="password" /><br>    
    <label for="pseudo">Pseudo :</label><input name="pseudo" type="text" /><br>
    <label for="seclevel">Niveau de sécurité :></label><input name="seclevel" type="number" /><br><br>
    <button type="submit">Ajouter</button>
</form>
    
<%@include file="/include/footer.jspf" %>