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

        <a class="button-add-car" href="/admin/user/add">
            <div class="add-new-car">Dodaj użytkownika</div>
        </a>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">id</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Email</th>
                <th scope="col">Type</th>
                <th scope="col">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users.content}" var="user">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>${user.type}</td>
                    <td>
                        <a href="/admin/user/delete?id=${user.id}">
                            <button type="button" class="btn btn-outline-danger">Delete</button>
                        </a>
                        <a href="/admin/user/edit?id=${user.id}">
                            <button type="button" class="btn btn-outline-warning">Update</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="pagination">
        <c:if test="${users.totalPages > 1}">
            <c:forEach begin="0" end="${users.totalPages - 1}" varStatus="page">
                <a href="?page=${page.index}">
                    <div class="add-new-car">${page.index + 1}</div>
                </a>
            </c:forEach>
        </c:if>
    </div>
</div>

<%--<script src="/js/dashboard.js"></script>--%>

</body>
</html>