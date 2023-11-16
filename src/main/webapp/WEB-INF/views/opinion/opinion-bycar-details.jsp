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

        <a class="button-add-car" href="/admin/opinion/add">
            <div class="add-new-car">Dodaj opinię</div>
        </a>
        <a class="button-add-car" href="/admin/opinions/bycars">
            <div class="add-new-car">Powrót</div>
        </a>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Użytkownik</th>
                <th scope="col">Samochód</th>
                <th scope="col">Opinia</th>
                <th scope="col">Ocena</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${opinions}" var="opinion">
                <tr>
                    <th scope="row">${opinion.id}</th>
                    <td>${opinion.user.fullName}</td>
                    <td>${opinion.car.fullName}</td>
                    <td>${opinion.content}</td>
                    <td>${opinion.rating} / 5</td>
                    <td>
                        <a href="/admin/opinion/delete?id=${opinion.id}">
                            <button type="button" class="btn btn-outline-danger">Usuń</button>
                        </a>
                        <a href="/admin/opinion/edit?id=${opinion.id}">
                            <button type="button" class="btn btn-outline-warning">Edytuj</button>
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