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
        <div class="container margin-left">
            <a class="button-add-car" href="/admin/department/add">
                <div class="add-new-car">Dodaj oddział</div>
            </a>
        </div>
        <div class="container ">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">City</th>
                    <th scope="col">Street</th>
                    <th scope="col">Address number</th>
                    <th scope="col">ZIP code</th>
                    <th scope="col">Phone no.</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${departments.content}" var="dep">
                    <tr>
                        <th scope="row">${dep.id}</th>
                        <td>${dep.city}</td>
                        <td>${dep.street}</td>
                        <td>${dep.number}</td>
                        <td>${dep.zipCode}</td>
                        <td>${dep.phone}</td>
                        <td><a href="/admin/department/delete?id=${dep.id}">
                            <button type="button" class="btn btn-outline-danger">Usuń</button>
                        </a>
                            <a href="/admin/department/edit?id=${dep.id}">
                                <button type="button" class="btn btn-outline-warning">Edytuj</button>
                            </a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="pagination">
        <c:if test="${departments.totalPages > 1}">
            <c:forEach begin="0" end="${departments.totalPages - 1}" varStatus="page">
                <a href="?page=${page.index}">
                    <div class="add-new-car">${page.index + 1}</div>
                </a>
            </c:forEach>
        </c:if>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>