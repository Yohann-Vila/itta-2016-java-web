<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>

<c:url value="/thread" var="url" />
<h1>Ajouter un fil</h1>

<form action="${url}" method="get">
    <label for="title">Titre :</label><input name="title" type="text" />
    <label for="urlphoto">Url de la photo :</label><input name="urlphoto" type="text" />
    <input name="addthread" type="hidden" value="true"/>
    <button type="submit">Ajouter</button>
</form>


<%@include file="include/footer.jspf" %>

