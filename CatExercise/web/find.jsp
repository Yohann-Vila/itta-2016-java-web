<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>
<c:url value="/thread" var="url" />
<h1>Rechercher photo de chat</h1>
<h2>Vous pouvez utiliser * pour tous les afficher</h2>
            <form action="${url}" method="get">
                <input name="what" type="text" /><button type="submit">Rechercher</button>
            </form>
            
<%@include file="include/footer.jspf" %>

