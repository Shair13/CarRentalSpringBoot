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
        <table class="table table-striped">
            <tr>
                <th scope="col">id</th>
                <td id="carId">${dep.id}</td>
            </tr>
            <tr>
                <th scope="col">Miasto</th>
                <td>${dep.city}</td>
            </tr>
            <tr>
                <th scope="col">Ulica</th>
                <td>${dep.street}</td>
            </tr>
            <tr>
                <th scope="col">Numer</th>
                <td>${dep.number}</td>
            </tr>
            <tr>
                <th scope="col">Kod pocztowy</th>
                <td>${dep.zipCode}</td>
            </tr>
            <tr>
                <th scope="col">Telefon</th>
                <td>${dep.phone}</td>
            </tr>
        </table>
        <a href="/admin/rentals">
            <button type="button" class="btn btn-primary"><-- Powrót</button>
        </a>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>

