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

    <a href="/admin/rentals">
        <button type="button" class="btn btn-primary"><-- Powrót</button>
    </a>

    <div class="content">
        <table class="table table-striped">
            <tr>
                <th scope="col">id</th>
                <td id="carId">${car.id}</td>
            </tr>
            <tr>
                <th scope="col">Marka</th>
                <td id="carBrand">${car.brand}</td>
            </tr>
            <tr>
                <th scope="col">Model</th>
                <td>${car.model}</td>
            </tr>
            <tr>
                <th scope="col">Typ</th>
                <td>${car.type}</td>
            </tr>
            <tr>
                <th scope="col">Data produkcji</th>
                <td>${car.productionDate}</td>
            </tr>
            <tr>
                <th scope="col">Rejestracja</th>
                <td>${car.plates}</td>
            </tr>
            <tr>
                <th scope="col">Cene za dzień</th>
                <td>${car.pricePerDay}</td>
            </tr>
            <tr>
                <th scope="col">Status</th>
                <td>${car.status}</td>
            </tr>
            <tr>
                <th scope="col">Przebieg</th>
                <td>${car.mileage}</td>
            </tr>
            <tr>
                <th scope="col">Przegląd tech.</th>
                <td>${car.nextInspection}</td>
            </tr>
            <tr>
                <th scope="col">Ubezpieczenie</th>
                <td>${car.nextInsurance}</td>
            </tr>
            <tr>
                <th scope="col">Status</th>
                <td>${car.status}</td>
            </tr>
        </table>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>

