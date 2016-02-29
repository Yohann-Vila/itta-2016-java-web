<%-- 
    Document   : threaddetail
    Created on : 26-Feb-2016, 11:34:48
    Author     : Yohann
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>

<c:url value="/threaddetails?idthread=${currentCatThread.catThreadId}" var="url" />

<c:if test="${user == null}" var="nouser" />
<c:if test="${not nouser and user.isAdmin()}" var="isadmin" />

<div class="row">
    <h1>${currentCatThread.titre}</h1>
</div>

<div class="row">
    <div class="col-sm-8">
        <div>
            <img src="${currentCatThread.uriPhoto}" class="img-responsive" alt="photo du chat" >
        </div>        
    </div>
    <%-- Meta info --%>
    <div class="col-sm-4">
        <div>
            <p>Crée par : ${currentCatThread.login}</p>
            <p>Le : ${currentCatThread.creationDate}</p>    
        </div>           
    </div>
</div>

<%-- Commentaires --%>
<div class="row">
    <div>
        <p>Commentaires :</p><br>
    </div> 
</div>        

<c:forEach items="${currentCatThread.comments}" var="comment">
    <c:if test="${isadmin or not comment.deleted}">
        <div class="row">        
            <div class="col-md-4">${comment.login} <br><small>Le ${comment.creationDate}</small></div>
            <div class="col-md-7">${comment.content}</div>
            <c:if test="${isadmin}">
                <c:set value="${url}&commentid=${comment.commentId}" var="urlcomment" />
                <div class="col-md-1"><a href="${urlcomment}" type="button" class="btn btn-primary">${(comment.deleted eq false)?"Supprimer":"Rétablir"}</a></div>
            </c:if>
        </div>
        <div><br></div>
    </c:if>
</c:forEach>


<%-- Nouveau commentaires --%>
<div class="row">
    <div>
        <%-- TODO : <%= java.net.URLEncoder.encode((String)pageContext.getAttribute("url")) %> --%>
        <c:url value="/login.jsp" var="redirecturl" />
        <br>
        <p><c:if test="${! nouser}"> Composez un nouveau commentaire : </c:if> <c:if test="${nouser}"> Veuillez vous <a href="${redirecturl}" > connecter </a> avec votre compte pour poster un commentaire </c:if></p>
        <form action="${url}" method="POST">
            <textarea class="form-control" name="commentcontent" cols="60" rows="5" id="commentfield" <c:if test="${nouser}">disabled</c:if>></textarea>
            <button type="submit" class="btn btn-primary" <c:if test="${nouser}">disabled</c:if>>Envoyer</button>
        </form>
    </div>
</div>







<div>
    <p></p>
</div>

<%@include file="include/footer.jspf" %>
