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


    <div>
        <div class="container">
            <a class="button-add-car" href="/admin/car/add">
                <div class="add-new-car">Dodaj samochód</div>
            </a>
        </div>
        <div class="container">
            <table class="table table-striped">
                <thead>
                <tr>

                    <th scope="col">id</th>
                    <th scope="col">Brand</th>
                    <th scope="col">Model</th>
                    <th scope="col">Type</th>
                    <th scope="col">Production Date</th>
                    <th scope="col">Plates</th>
                    <th scope="col">Price/day</th>
                    <th scope="col">Status</th>
                    <th scope="col">Mileage</th>
                    <th scope="col">Next Inspection</th>
                    <th scope="col">Next Insurance</th>
                    <th scope="col">Średnia ocen</th>
                    <th scope="col">Action</th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cars.content}" var="car">
                    <tr>
                        <th id="carId" scope="row">${car.id}</th>
                        <td id="carBrand">${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.type}</td>
                        <td>${car.productionDate}</td>
                        <td>${car.plates}</td>
                        <td>${car.pricePerDay}</td>
                        <td>${car.status}</td>
                        <td>${car.mileage}</td>
                        <td>${car.nextInspection}</td>
                        <td>${car.nextInsurance}</td>
                        <td>${car.ratingAverage}</td>
                        <td>
                            <a href="/admin/car/delete?id=${car.id}" id="deleteButton" data-toggle="modal"
                               data-target="#deleteMessage">
                                <button type="button" class="btn btn-outline-danger">Usuń</button>
                            </a>
                            <a href="/admin/car/edit?id=${car.id}">
                                <button type="button" class="btn btn-outline-warning">Edytuj</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- Wyświetl paginację -->
        <div class="pagination">
            <c:if test="${cars.totalPages > 1}">
                <c:forEach begin="0" end="${cars.totalPages - 1}" varStatus="page">
                    <a href="?page=${page.index}">
                        <div class="add-new-car">${page.index + 1}</div>
                    </a>
                </c:forEach>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>