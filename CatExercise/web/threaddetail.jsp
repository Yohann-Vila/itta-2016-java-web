<%-- 
    Document   : threaddetail
    Created on : 26-Feb-2016, 11:34:48
    Author     : Yohann
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>

<div class="row">
    <h1>TITRE</h1>
</div>

<div class="row">
    <div class="col-sm-8">
        <div>
            <img src="http://fakeimg.pl/600x480/" class="img-responsive" alt="photo du chat" >
        </div>        
    </div>
    
    <div class="col-sm-4">
        <div>
            <p>Crée par : AUTEUR</p>
            <p>Le : DATE</p>    
        </div>           
    </div>
    <div>
        <c:forEach items="" var="p" varStatus="s" step="2">
            ${s.first?"<div><p>Commentaires :</p></div><br><div>":""}
            <p>
                Écrit par : AUTEUR_COMMENT 
            </p>
                
            ${s.last?"</div>":""}
        </c:forEach>
    </div> 
</div>

<div class="row">
    <div>
        <p>
            Composer un nouveau commentaire :
        </p>
        <form>
            <textarea name="newcomment" cols="60" rows="5">
            champ de texte                
            </textarea>
        </form>
    </div>
</div>







<div>
    <p></p>
</div>

<%@include file="include/footer.jspf" %>
