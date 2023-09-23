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
        <form:form method="post" modelAttribute="car" id="carAddForm">
            <h1 class="h3 mb-3 fw-normal">Add new car</h1> <br>
            <form:hidden path="id"/>
            <div class="form-floating">
                <form:input path="brand" class="form-control" id="floatingBrand"/>
                <label for="floatingBrand">Brand</label>
            </div><br>
            <div class="form-floating">
                <form:input path="model" class="form-control" id="floatingModel"/>
                <label for="floatingModel">Model</label>
            </div><br>
            <div class="form-floating">
                <form:select path="type" class="form-control" id="floatingType">
                    <form:option value="" label="--Select type of car--"/>
                    <form:options items="${types}"/>
                </form:select>
                <label for="floatingType">Type</label>
            </div><br>
            <div class="form-floating">
                <form:input path="productionDate" type="date" class="form-control" id="floatingProdDate"/>
                <label for="floatingProdDate">Production Date</label>
            </div><br>
            <div class="form-floating">
                <form:input path="plates" class="form-control" id="floatingPlates"/>
                <label for="floatingPlates">Plates</label>
            </div><br>
            <div class="form-floating">
                <form:input path="pricePerDay" class="form-control" id="floatingPrice"/>
                <label for="floatingPrice">Price per day [PLN]</label>
            </div><br>
            <form:hidden path="status"/>
            <div class="form-floating">
                <form:input path="mileage" type="number" class="form-control" id="floatingMileage"/>
                <label for="floatingMileage">Mileage [km]</label>
            </div><br>
            <div class="form-floating">
                <form:input path="nextInspection" type="date" class="form-control" id="floatingInspection"/>
                <label for="floatingInspection">Next inspection date</label>
            </div><br>
            <div class="form-floating">
                <form:input path="nextInsurance" type="date" class="form-control" id="floatingInsurance"/>
                <label for="floatingInsurance">Next insurance date</label>
            </div><br>
            <div class="form-floating">
                <form:input path="capacity" class="form-control" id="floatingCapacity"/>
                <label for="floatingCapacity">Capacity</label>
            </div><br>
            <div class="form-floating">
                <form:input path="vin" class="form-control" id="floatingVin"/>
                <label for="floatingVin">VIN</label>
            </div><br>
            <button class="btn btn-primary w-100 py-2" type="submit">Add</button>
            <div id="error-message" class="alert alert-danger d-none"></div>
        </form:form>
    </div>
</div>

<%--<script src="/js/dashboard.js"></script>--%>

</body>
</html>