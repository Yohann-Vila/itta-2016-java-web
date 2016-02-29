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
            <td>${user.pseudo}  ${user.banish}  </td>
            <td>${user.creationdate} </td>
            <c:url var="url" value="/admincontroller">
                <c:param name="login" value="${user.login}"/>
                <c:param name="choix" value="affiche"/>
            </c:url>
            <td> <a href="${url}" type="button" class="btn btn-primary">${(user.banish eq false)?"Actif":"Banish"}</a></td>
        </tr>
    </c:forEach>
</table>
<br>

<c:url var="url" value="/admincontroller">
    <c:param name="choix" value="create"/>
</c:url>
<a href="${url}" type="button" class="btn btn-primary">Ajouter un utilisateur</a>

<%@include file="/include/footer.jspf" %>