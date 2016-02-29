<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>

<c:url value="/login" var="url" />
<h1>Connectez-vous</h1>

<form action="${url}" method="post">
    <c:if test="${param.error=='1'}">
        <div class="alert alert-danger">
        <span >Login ou mot de passe incorrect !</span>
        </div>
    </c:if>
    <label for="login">Login :</label><input name="login" type="text" />
    <label for="password">Password :</label><input name="password" type="password" />
    <input name="redirect" type="hidden" value="${param.redirect}"/>
    <button type="submit">Se connecter</button>
</form>


<%@include file="include/footer.jspf" %>

