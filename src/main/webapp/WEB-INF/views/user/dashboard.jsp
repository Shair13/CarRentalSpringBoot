<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en" data-bs-theme="auto">

<%@ include file="../headers/head.jsp" %>

<body>
<header>
    <div class="user-name">${user.email}</div>
    <div class="logo"></div>
</header>
<div class="container">

    <%@ include file="../headers/user-dashboard-header.jsp" %>


    <div class="content">
        <h1>CONTENT</h1>
        <%--    <table class="table table-striped">--%>
        <%--        <thead>--%>
        <%--        <tr>--%>
        <%--            <th scope="col">id przejazdu</th>--%>
        <%--            <th scope="col">Samochód</th>--%>
        <%--            <th scope="col">Data wynajęcia</th>--%>
        <%--            <th scope="col">Data zwrotu</th>--%>
        <%--            <th scope="col">Pracownik wynajmujący</th>--%>
        <%--            <th scope="col">Cena za dzień</th>--%>
        <%--        </tr>--%>
        <%--        </thead>--%>
        <%--        <tbody>--%>
        <%--        <c:forEach items="${lastFive}" var="rent">--%>
        <%--        <tr>--%>
        <%--            <th id="carId" scope="row">${rent.id}</th>--%>
        <%--            <td>${rent.car.fullName}</td>--%>
        <%--            <td>${rent.startDate}</td>--%>
        <%--            <td>${rent.returnDate}</td>--%>
        <%--            <td>${rent.employee.fullName}</td>--%>
        <%--            <td>${rent.price}</td>--%>
        <%--        </tr>--%>
        <%--        </c:forEach>--%>
    </div>
</div>

<%--<script src="/js/dashboard.js"></script>--%>
</body>
</html>