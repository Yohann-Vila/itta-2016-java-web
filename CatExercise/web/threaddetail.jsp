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
        <div><p>Commentaires :</p></div><br>
        <div>
            <c:forEach items="${currentCatThread.comments}" var="comment" varStatus="s" step="1">
                <p> Le ${comment.creationDate}, {comment.login} a écrit : </p>
                <p>${comment.content}</p>
            </c:forEach>
        </div>
    </div> 
</div>

<%-- Nouveau commentaires --%>
<div class="row">
    <div>
        <br>
        <p>
            Composer un nouveau commentaire :
        </p>
        <form>
            <textarea class="form-control" name="newcomment" cols="60" rows="5" id="commentfield" />
            <button type="button" class="btn btn-primary">Envoyer</button>
        </form>
    </div>
</div>







<div>
    <p></p>
</div>

<%@include file="include/footer.jspf" %>
