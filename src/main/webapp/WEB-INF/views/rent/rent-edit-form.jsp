<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">

<%@ include file="../headers/head.jsp" %>

<body>
<header>
    <%@ include file="../headers/current-user.jsp" %>
</header>
<div class="container">

    <%@ include file="../headers/dashboard-header.jsp" %>

    <div class="content">
        <form:form method="post" modelAttribute="rent" id="rentUpdateForm">
            <h1 class="h3 mb-3 fw-normal">Edytuj wynajem</h1> <br>
            <form:hidden path="id"/>
            <div class="form-floating">
                <form:select path="customer" class="form-control" id="floatingCustomer">
                    <form:options items="${users}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingCustomer">Klient</label>
            </div><br>
            <div class="form-floating">
                <form:select path="employee" class="form-control" id="floatingEmployee">
                    <form:options items="${users}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingEmployee">Pracownik</label>
            </div><br>
            <div class="form-floating">
                <form:select path="status" class="form-control" id="floatingRentStatus">
                    <form:options items="${rentStatuses}"/>
                </form:select>
                <label for="floatingRentStatus">Status wypożyczenia</label>
            </div><br>
            <div class="form-floating">
                <form:select path="car" class="form-control" id="floatingCar">
                    <form:options items="${cars}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingCar">Samochód</label>
            </div><br>
            <div class="form-floating">
                <form:input path="startDate" type="date" class="form-control" id="floatingStartDate"/>
                <label for="floatingStartDate">Data wynajęcia</label>
            </div><br>
            <div class="form-floating">
                <form:select path="takingPlace" class="form-control" id="floatingTakingPlace">
                    <form:options items="${departments}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingTakingPlace">Miejsce wynajęcia</label>
            </div><br>
            <div class="form-floating">
                <form:input path="returnDate" type="date" class="form-control" id="floatingReturnDate"/>
                <label for="floatingReturnDate">Data zwrotu</label>
            </div><br>
            <div class="form-floating">
                <form:select path="returningPlace" class="form-control" id="floatingReturningPlace">
                    <form:options items="${departments}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingReturningPlace">Miejsce zwrotu</label>
            </div><br>
            <button class="btn btn-primary w-100 py-2" type="submit">Aktualizuj</button>
            <a href="/admin/departments">Cofnij</a>
            <div id="error-message" class="alert alert-danger d-none"></div>
        </form:form>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>