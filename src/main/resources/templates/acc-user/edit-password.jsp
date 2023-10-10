<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en" data-bs-theme="auto">

<%@ include file="../headers/head.jsp" %>

<body>
<header>
    <div class="user-name">${user.type} : ${user.email}</div>
    <div class="logo"></div>
</header>
<div class="container">

    <%@ include file="../headers/user-dashboard-header.jsp" %>

    <div class="content">
        <form:form method="post">
            <h1 class="h3 mb-3 fw-normal">Edytuj hasło</h1> <br>
            <div class="form-floating">
                <input type="password" name="pass1" id="pass1">
                <label for="pass2">Podaj hasło</label>
            </div>
            <br>
            <div class="form-floating">
                <input type="password" name="pass2" id="pass2">
                <label for="pass2">Powtórz hasło</label>
            </div>
            <br>
            <button class="btn btn-primary w-100 py-2" type="submit">Aktualizuj</button>
            <div id="error-message" class="alert alert-danger d-none"></div>
        </form:form>
    </div>
</div>
<%--<script src="/js/dashboard.js"></script>--%>
</body>
</html>