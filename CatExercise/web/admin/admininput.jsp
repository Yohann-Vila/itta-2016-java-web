<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/include/header.jspf" %>

<c:url value="/admin/admininput" var="url" />
<h2> Add User</h2> <br>

<form action="${url}" method="get">
    <label for="title">Login :</label><input name="login" type="text" />
    <label for="pseudo">Pseudo :</label><input name="pseudo" type="text" />
    <label for="seclevel">Security level :></label><input name="seclevel" type="number" />
    <button type="submit">Submit</button>
</form>

<%@include file="/include/footer.jspf" %>