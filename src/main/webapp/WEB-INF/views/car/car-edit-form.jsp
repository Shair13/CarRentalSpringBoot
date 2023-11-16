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
<div class="container bgc-img3">

    <%@ include file="../headers/admin-dashboard-header.jsp" %>

    <div class="content">
        <form:form method="post" modelAttribute="car" id="carUpdateForm">
            <h1 class="h3 mb-3 fw-normal">Edytuj samochód</h1> <br>
            <form:hidden path="id"/>
            <div class="form-floating">
                <form:input path="brand" class="form-control" id="floatingBrand"/>
                <label for="floatingBrand">Marka</label>
            </div><br>
            <div class="form-floating">
                <form:input path="model" class="form-control" id="floatingModel"/>
                <label for="floatingModel">Model</label>
            </div><br>
            <div class="form-floating">
                <form:select path="type" class="form-control" id="floatingType">
                    <form:options items="${types}" itemValue="id" itemLabel="type"/>
                </form:select>
                <label for="floatingType">Typ</label>
            </div><br>
            <div class="form-floating">
                <form:input path="productionDate" type="date" class="form-control" id="floatingProdDate"/>
                <label for="floatingProdDate">Data produkcji</label>
            </div><br>
            <div class="form-floating">
                <form:input path="plates" class="form-control" id="floatingPlates"/>
                <label for="floatingPlates">Rejestracja</label>
            </div><br>
            <div class="form-floating">
                <form:input path="pricePerDay" class="form-control" id="floatingPrice"/>
                <label for="floatingPrice">Cena za dzień [PLN]</label>
            </div><br>
            <div class="form-floating">
                <form:select path="status" class="form-control" id="floatingStatus">
                    <form:options items="${statuses}"/>
                </form:select>
                <label for="floatingType">Status</label>
            </div><br>
            <div class="form-floating">
                <form:input path="mileage" type="number" class="form-control" id="floatingMileage"/>
                <label for="floatingMileage">Przebieg [km]</label>
            </div><br>
            <div class="form-floating">
                <form:input path="nextInspection" type="date" class="form-control" id="floatingInspection"/>
                <label for="floatingInspection">Data przeglądu</label>
            </div><br>
            <div class="form-floating">
                <form:input path="nextInsurance" type="date" class="form-control" id="floatingInsurance"/>
                <label for="floatingInsurance">Data ubezpieczenia</label>
            </div><br>
            <div class="form-floating">
                <form:input path="capacity" class="form-control" id="floatingCapacity"/>
                <label for="floatingCapacity">Pojemność</label>
            </div><br>
            <div class="form-floating">
                <form:input path="vin" class="form-control" id="floatingVin"/>
                <label for="floatingVin">VIN</label>
            </div><br>
            <button class="btn btn-primary w-100 py-2" type="submit">Aktualizuj</button>
            <a href="/admin/cars">Cofnij</a>
            <div id="error-message" class="alert alert-danger d-none"></div>
        </form:form>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>