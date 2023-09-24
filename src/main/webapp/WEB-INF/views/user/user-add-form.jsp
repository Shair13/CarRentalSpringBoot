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

    <%@ include file="../headers/admin-dashboard-header.jsp" %>

    <div class="content">
        <form:form method="post" modelAttribute="user" id="userForm">
        <h1 class="h3 mb-3 fw-normal">Dodaj użytkownika</h1> <br>
        <form:hidden path="id"/>
        <div class="form-floating">
            <form:input path="firstName" class="form-control" id="floatingFirstName"/>
            <label for="floatingFirstName">Imię</label>
        </div><br>
        <div class="form-floating">
            <form:input path="lastName" class="form-control" id="floatingLastName"/>
            <label for="floatingLastName">Nazwisko</label>
        </div><br>
        <div class="form-floating">
            <form:input path="email" class="form-control" id="floatingEmail"/>
            <label for="floatingEmail">Email</label>
        </div><br>
        <div class="form-floating">
            <form:input path="password" type="password" class="form-control" id="floatingPassword"/>
            <label for="floatingPassword">Hasło</label>
        </div><br>
        <div class="form-floating">
            <input type="password" name="pass" class="form-control" id="floatingRepeatPassword"/>
            <label for="floatingRepeatPassword">powtórz hasło</label>
        </div><br>
        <button class="btn btn-primary w-100 py-2" type="submit">Dodaj</button>
        <div id="error-message" class="alert alert-danger d-none">
            </form:form>
    </div>
</div>

<%--<script src="/js/dashboard.js"></script>--%>

</body>
</html>