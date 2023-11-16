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

    <%@ include file="../headers/user-dashboard-header.jsp" %>

    <div class="content">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Klient</th>
                <th scope="col">Pracownik</th>
                <th scope="col">Samochód</th>
                <th scope="col">Data wypożyczenia</th>
                <th scope="col">Data zwrotu</th>
                <th scope="col">Miejsce wypożyczenia</th>
                <th scope="col">Miejsce zwrotu</th>
                <th scope="col">Cena za dzień</th>
                <th scope="col">Zapłacono</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${rentals}" var="rent">
                <tr>
                    <th id="carId" scope="row">${rent.id}</th>
                    <td>${rent.customer.fullName}</td>
                    <td>${rent.employee.fullName}</td>
                    <td>
                            ${rent.car.fullName} : ${rent.car.plates}
                    </td>
                    <td>${rent.startDate}</td>
                    <td>${rent.returnDate}</td>
                    <td>${rent.takingPlace.fullName}</td>
                    <td>${rent.returningPlace.fullName}</td>
                    <td>${rent.car.pricePerDay}</td>
                    <td>${rent.price}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="../../../js/dashboard.js"></script>
</body>
</html>