<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../headers/head.jsp" %>

<body>

<%@ include file="../headers/home-header.jsp" %>

<div class="content">
    <form:form method="post" modelAttribute="registration" id="userForm">
        <h1 class="h3 mb-3 fw-normal">Registration</h1> <br>
        <form:hidden path="id"/>
        <div class="form-floating">
            <form:input path="firstName" class="form-control" id="floatingFirstName"/>
            <label for="floatingFirstName">First Name</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="lastName" class="form-control" id="floatingLastName"/>
            <label for="floatingLastName">Last Name</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="email" class="form-control" id="floatingEmail"/>
            <label for="floatingEmail">Email</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="password" type="password" class="form-control" id="floatingPassword"/>
            <label for="floatingPassword">Password</label>
        </div>
        <br>
        <div class="form-floating">
            <input type="password" name="pass" class="form-control" id="floatingRepeatPassword"/>
            <label for="floatingRepeatPassword">Repeat password</label>
        </div>
        <br>
        <button class="btn btn-primary w-100 py-2" type="submit">Register</button>
        <div id="error-message" class="alert alert-danger d-none"></div>
    </form:form>
</div>
</body>
</html>