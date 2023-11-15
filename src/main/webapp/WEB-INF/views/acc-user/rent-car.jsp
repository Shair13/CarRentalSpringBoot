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
        <form:form method="post" modelAttribute="rent" id="rentAddForm">
            <h1 class="h3 mb-3 fw-normal">Formularz wypożyczenia samochodu</h1> <br>
            <form:hidden path="id"/>
            <form:hidden path="employee"/>
            <form:hidden path="customer"/>
            <div class="form-floating">
                <form:select path="car" class="form-control" id="floatingCar">
                    <form:option value="" label="--Wybierz samochód--"/>
                    <form:options items="${freeCars}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingCar">Samochód</label>
            </div>
            <br>
            <div class="form-floating">
                <form:input path="startDate" type="date" class="form-control" id="floatingStartDate"/>
                <label for="floatingStartDate">Data wynajęcia</label>
            </div>
            <br>
            <div class="form-floating">
                <form:select path="takingPlace" class="form-control" id="floatingTakingPlace">
                    <form:option value="" label="--Wybierz miejsce odbioru--"/>
                    <form:options items="${departments}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingTakingPlace">Miejsce wynajęcia</label>
            </div>
            <br>
            <div class="form-floating">
                <form:input path="returnDate" type="date" class="form-control" id="floatingReturnDate"/>
                <label for="floatingReturnDate">Data zwrotu</label>
            </div>
            <br>
            <div class="form-floating">
                <form:select path="returningPlace" class="form-control" id="floatingReturningPlace">
                    <form:option value="" label="--Wybierz miejsce zwrotu--"/>
                    <form:options items="${departments}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingReturningPlace">Miejsce zwrotu</label>
            </div>
            <br>
            <button class="btn btn-primary w-100 py-2" type="submit">Zarezerwuj samochód</button>
            <div id="error-message" class="alert alert-danger d-none"></div>
        </form:form>
    </div>
</div>
<%--<script src="/js/dashboard.js"></script>--%>
</body>
</html>