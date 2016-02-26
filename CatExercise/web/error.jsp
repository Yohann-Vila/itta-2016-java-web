<%-- 
    Document   : error
    Created on : 26-Feb-2016, 09:46:03
    Author     : Yohann
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feulement</title>
    </head>
    <body>
        <h2>Erreur !</h2>
        <p><%=exception.getMessage()%></p>
    </body>
</html>
