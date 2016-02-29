<%-- 
    Document   : threaddetail
    Created on : 26-Feb-2016, 11:34:48
    Author     : Yohann
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>


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

<c:forEach items="${currentCatThread.comments}" var="comment" varStatus="s" step="1">
    <div class="row">        
        <div class="col-md-4">${comment.login} <br><small>Le ${comment.creationDate}</small></div>
        <div class="col-md-8">${comment.content}</div>          
    </div>
    <div><br></div>
</c:forEach>


<%-- Nouveau commentaires --%>
<div class="row">
    <div>
        <c:if test="${user == null}" var="nouser" >
            
        </c:if>
        <c:url value="/threaddetails?idthread=${currentCatThread.catThreadId}" var="url" />
        <%-- TODO : <%= java.net.URLEncoder.encode((String)pageContext.getAttribute("url")) %> --%>
        <c:set var="redirecturl" value="/login?redirect=/find.jsp" />
        <br>
        <p><c:if test="${! nouser}"> Composez un nouveau commentaire : </c:if> <c:if test="${nouser}"> Veuillez vous <a href="${redirecturl}" > connecter </a> avec votre compte pour poster un commentaire </c:if></p>
        <form action="${url}">
            <textarea class="form-control" name="newcomment" cols="60" rows="5" id="commentfield" <c:if test="${nouser}">disabled</c:if>></textarea>
            <button type="button" class="btn btn-primary" <c:if test="${nouser}">disabled</c:if>>Envoyer</button>
        </form>
    </div>
</div>







<div>
    <p></p>
</div>

<%@include file="include/footer.jspf" %>
