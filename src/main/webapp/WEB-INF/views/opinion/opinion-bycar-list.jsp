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


    <div class="content">

        <a class="button-add-car" href="/admin/opinion/add">
            <div class="add-new-car">Dodaj opinię</div>
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

            <%--            <div class="modal fade" id="deleteMessage" tabindex="-1" role="dialog"--%>
            <%--                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">--%>
            <%--                <div class="modal-dialog modal-dialog-centered" role="document">--%>
            <%--                    <div class="modal-content">--%>
            <%--                        <div class="modal-header">--%>
            <%--                            <h5 class="modal-title" id="exampleModalLongTitle">Usuń pojazd</h5>--%>
            <%--                            <button type="button" class="btn btn-outline-light" data-dismiss="modal"--%>
            <%--                                    aria-label="Close">--%>
            <%--                                <span aria-hidden="true">&times;</span>--%>
            <%--                            </button>--%>
            <%--                        </div>--%>
            <%--                        <div class="modal-body" id="deleteMessageBody">--%>
            <%--                            Czy na pewno chcesz usunąć ten pojazd?--%>
            <%--                        </div>--%>
            <%--                        <div class="modal-footer" id="deleteMessageFooter">--%>
            <%--                            <button type="button" class="btn btn-outline-light"--%>
            <%--                                    data-dismiss="modal">Anuluj--%>
            <%--                            </button>--%>
            <%--                            <a href=""--%>
            <%--                               class="btn btn-outline-danger"--%>
            <%--                               data-target="#deleteMessage">Usuń</a>--%>
            <%--                        </div>--%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <%--            </div>--%>
            </tbody>
        </table>
        <!-- Wyświetl paginację -->
        <%--        <div class="pagination">--%>
        <%--            <c:if test="${opinions.totalPages > 1}">--%>
        <%--                <c:forEach begin="0" end="${opinions.totalPages - 1}" varStatus="page">--%>
        <%--                    <a href="?page=${page.index}"><div class="add-new-car">${page.index + 1}</div></a>--%>
        <%--                </c:forEach>--%>
        <%--            </c:if>--%>
        <%--        </div>--%>
    </div>
</div>

<%--<script src="/js/dashboard.js"></script>--%>

</body>
</html>