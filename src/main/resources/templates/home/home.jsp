<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../headers/head.jsp" %>

<body>

<%@ include file="../headers/home-header.jsp" %>

<div class="content">
    <sec:authorize access="isAuthenticated()">
        <p>Zalogowany jako: <sec:authentication property="principal.username"/></p>
        <p>Posiada role: <sec:authentication property="authorities"/></p>
    </sec:authorize>
</div>

<%@ include file="../headers/home-footer.jsp" %>

</body>
</html>