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

        <a class="button-add-car" href="/admin/rent/add">
            <div class="add-new-car">Dodaj najem</div>
        </a>

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
                <th scope="col">Status</th>
                <th scope="col">Do zapłaty</th>
                <th scope="col">Action</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${rentals.content}" var="rent">
                <tr>
                    <th id="carId" scope="row">${rent.id}</th>
                    <td>
                        <a href="/admin/user/details?id=${rent.customer.id}">
                            <div class="info">info</div>
                        </a>
                            ${rent.customer.fullName}
                    </td>
                    <td>
                        <a href="/admin/user/details?id=${rent.employee.id}">
                            <div class="info">info</div>
                        </a>
                            ${rent.employee.fullName}
                    </td>
                    <td>
                        <a href="/admin/car/details?id=${rent.car.id}">
                            <div class="info">info</div>
                        </a>
                            ${rent.car.fullName}
                    </td>
                    <td>${rent.startDate}</td>
                    <td>${rent.returnDate}</td>
                    <td>
                        <a href="/admin/department/details?id=${rent.takingPlace.id}">
                            <div class="info">info</div>
                        </a>
                            ${rent.takingPlace.fullName}
                    </td>
                    <td>
                        <a href="/admin/department/details?id=${rent.returningPlace.id}">
                            <div class="info">info</div>
                        </a>
                            ${rent.returningPlace.fullName}
                    </td>
                    <td>${rent.car.pricePerDay}</td>
                    <td>${rent.status}</td>
                    <td>${rent.price}</td>
                    <td>
                        <a href="/admin/rent/delete?id=${rent.id}" id="deleteButton" data-toggle="modal"
                           data-target="#deleteMessage">
                            <button type="button" class="btn btn-outline-danger">Usuń</button>
                        </a>
                        <c:if test="${rent.status == 'process'}">
                            <a href="/admin/rent/edit?id=${rent.id}">
                                <button type="button" class="btn btn-outline-warning">Edytuj</button>
                            </a>
                            <a href="/admin/rent/end?rentId=${rent.id}">
                                <button type="button" class="btn btn-outline-warning">Zakończ</button>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <!-- Wyświetl paginację -->
        <div class="pagination">
            <c:if test="${rentals.totalPages > 1}">
                <c:forEach begin="0" end="${rentals.totalPages - 1}" varStatus="page">
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




