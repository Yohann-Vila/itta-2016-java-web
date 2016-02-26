<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/include/header.jspf" %>

<h2> User list</h2> <br>

<table>
    <colgroup>
        <col span="15"></col>
    </colgroup>
    <th>   Login<span>  </span></th>
    <th>   Pseudo        </th>
    <th>   Creation date </th>    
    <th>   Banish/Active </th>
    <br>
    <c:forEach items="${Listusers}" var="user" >    
        <tr>
            <td>${user.login}     </td>
            <td>${user.pseudo}    </td>
            <td>${user.creationdate} </td>
            <td> <button>${(user.banish eq flase)?"Actif":"Banish"}</button></td>
        </tr>
    </c:forEach>
</table>

<%@include file="/include/footer.jspf" %>