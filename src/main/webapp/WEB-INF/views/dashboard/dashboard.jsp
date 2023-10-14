<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">

<%@ include file="../headers/head.jsp" %>

<body>
<header>
    <sec:authorize access="isAuthenticated()">
    <div class="user-name"><sec:authentication property="principal.username"/> : <sec:authentication property="authorities"/></div>
    <div class="logo"></div>
    </sec:authorize>
</header>
<div class="container">

    <%@ include file="../headers/dashboard-header.jsp" %>

    <sec:authorize access="hasRole('ADMIN')">
    <div class="content">
        <h1>Witaj potężny Adminie</h1>
    </div>
    </sec:authorize>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>