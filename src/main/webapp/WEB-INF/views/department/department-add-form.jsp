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
        <form:form method="post" modelAttribute="department" id="departmentAddForm">
        <h1 class="h3 mb-3 fw-normal">Dodaj oddział</h1> <br>
        <form:hidden path="id"/>
        <div class="form-floating">
            <form:input path="city" class="form-control" id="floatingCity"/>
            <label for="floatingCity">City</label>
        </div><br>
        <div class="form-floating">
            <form:input path="street" class="form-control" id="floatingStreet"/>
            <label for="floatingStreet">Street</label>
        </div><br>
        <div class="form-floating">
            <form:input path="number" class="form-control" id="floatingNumber"/>
            <label for="floatingNumber">Address number</label>
        </div><br>
        <div class="form-floating">
            <form:input path="zipCode" class="form-control" id="floatingZipCode"/>
            <label for="floatingZipCode">ZIP code</label>
        </div><br>
        <div class="form-floating">
            <form:input path="phone" class="form-control" id="floatingPhone"/>
            <label for="floatingPhone">Phone no.</label>
        </div><br>
        <button class="btn btn-primary w-100 py-2" type="submit">Add</button>
        <div id="error-message" class="alert alert-danger d-none">
            </form:form>
        </div>
    </div>

    <%--<script src="/js/dashboard.js"></script>--%>

</body>
</html>