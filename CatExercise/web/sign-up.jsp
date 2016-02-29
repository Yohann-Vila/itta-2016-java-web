<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>

<c:url value="/login" var="url" />
<h1>Cr�er un compte</h1>
<form action="${url}" method="post">
    <c:if test="${param.error=='exist'}">
        <div class="alert alert-danger">
        <span >Compte existe d�ja</span>
        </div>
    </c:if>
    <c:if test="${param.error=='password'}">
        <div class="alert alert-danger">
        <span >Les mots de pass ne sont pas les m�mes</span>
        </div>
    </c:if>
     <c:if test="${param.error=='field'}">
        <div class="alert alert-danger">
            <span >Merci de remplir tous les champs</span>
        </div>
    </c:if>
    <label for="login">Login :</label><input name="login" type="text" />
    <label for="password">Password :</label><input name="password" type="password" />
    <label for="repassword">RePassword :</label><input name="repassword" type="password" />
    <input name="add" type="hidden" value="1"/>
    <button type="submit">Cr�er le compte</button>
</form>


<%@include file="include/footer.jspf" %>

