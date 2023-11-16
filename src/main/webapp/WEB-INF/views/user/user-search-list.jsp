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

        <a class="button-add-car" href="/admin/users">
            <div class="add-new-car">Cofnij</div>
        </a>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">Imię</th>
                <th scope="col">Nazwisko</th>
                <th scope="col">Email</th>
                <th scope="col">Uprawnienia</th>
                <th scope="col">Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.type}</td>
                    <td>
                        <a href="/admin/user/delete?id=${user.id}">
                            <button type="button" class="btn btn-outline-danger">Usuń</button>
                        </a>
                        <a href="/admin/user/edit?id=${user.id}">
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