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
        <table class="table table-striped">
            <tr>
                <th scope="col">id</th>
                <td id="carId">${user.id}</td>
            </tr>
            <tr>
                <th scope="col">Miasto</th>
                <td>${user.city}</td>
            </tr>
            <tr>
                <th scope="col">Ulica</th>
                <td>${user.street}</td>
            </tr>
            <tr>
                <th scope="col">Numer</th>
                <td>${user.number}</td>
            </tr>
            <tr>
                <th scope="col">Kod pocztowy</th>
                <td>${user.zipCode}</td>
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

