<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>
            <c:url value="/thread" var="url" ></c:url>
            <form action="${url}" method="get">
                <input name="what" type="text" /><button type="submit">Rechercher</button>
            </form>
<%@include file="include/footer.jspf" %>

