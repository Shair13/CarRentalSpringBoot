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

        <a class="button-add-car" href="/admin/opinions">
            <div class="add-new-car">Powrót</div>
        </a>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Samochód</th>
                <th scope="col">Rejestracja</th>
                <th scope="col">Średnia ocen</th>
                <th scope="col">Szczegóły</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${carsAvg}" var="car">
                <tr>
                    <th scope="row">${car.id}</th>
                    <td>${car.fullName}</td>
                    <td>${car.plates}</td>
                    <td>${car.ratingAverage} / 5</td>
                    <td>
                        <a href="/admin/opinions/bycar/details?id=${car.id}">
                            <button type="button">Szczegóły</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>