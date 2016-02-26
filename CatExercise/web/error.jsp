<%-- 
    Document   : error
    Created on : 26-Feb-2016, 09:46:03
    Author     : Yohann
--%>
<%@include file="include/header.jspf" %>
        <h2>Erreur !</h2>
        <p><%=exception.getMessage()%></p>
<%@include file="include/footer.jspf" %>