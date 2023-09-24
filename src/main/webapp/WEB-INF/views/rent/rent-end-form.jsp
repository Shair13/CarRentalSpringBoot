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
        <form:form method="post" modelAttribute="rent" id="rentAddForm">
        <h1 class="h3 mb-3 fw-normal">Zwróć samochód: ${rent.car.fullName} (id wypożyczenia: ${rent.id})</h1> <br>
        <div class="form-floating">
            <input name="mileage" type="number" placeholder="${rent.car.mileage}" class="form-control"
                   id="floatingMileage"/>
            <label for="floatingMileage">Mileage [km]</label>
        </div>
        <br>
        <form:hidden path="id"/>
        <form:hidden path="customer"/>
        <form:hidden path="employee" />
        <form:hidden path="car" />
        <form:hidden path="startDate" />
        <form:hidden path="takingPlace" />
        <form:hidden path="returnDate" />
        <form:hidden path="returningPlace" />
        <button class="btn btn-primary w-100 py-2" type="submit">Zwróć samochód</button>
        <div id="error-message" class="alert alert-danger d-none"></div>
        </form:form>
    </div>
</div>

<%--<script src="/js/dashboard.js"></script>--%>

</body>
</html>