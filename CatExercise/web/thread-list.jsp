<%-- 
    Document   : thread-list
    Created on : Feb 26, 2016, 11:29:21 AM
    Author     : Administrator
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="include/header.jspf" %>
<h1>Liste des fils :</h1>
<ul>
    <c:forEach items="${catThreadList}" var="catThread" >
        <c:url value="/threaddetails" var="url">
            <c:param name="idthread" value="${catThread.catThreadId}"></c:param>
        </c:url>
        <li><a href="${url}"><c:out value="${catThread.titre}" /></a></li>

    </c:forEach>
</ul>   
<c:forEach items="${pageList}" var="pageNumber">
    <c:url value="/thread" var="url">
        <c:param name="page" value="${pageNumber}"></c:param>
        <c:param name="what" value="${what}"></c:param>

    </c:url>
    <span>
        <a href="${url}" >
            <c:if test="${pageNumber.equals(currentPage)}">
                <strong><c:out value="${pageNumber}"></c:out></strong>
            </c:if>
            <c:if test="${!pageNumber.equals(currentPage)}">
                <c:out value="${pageNumber}"></c:out>
            </c:if>
        </a>
    </span>
</c:forEach>  
<div>
    <c:if test="${sessionScope.user != null}">
        <c:url value="/add-thread.jsp" var="url" />
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <c:url value="/login.jsp?redirect=/add-thread.jsp" var="url" />
    </c:if>

    <a href="${url}" type="button" class="btn btn-primary">Créer un nouveau fil</a>
</div>
<%@include file="include/footer.jspf" %>