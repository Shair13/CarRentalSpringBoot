<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../headers/head.jsp" %>

<body>

<%@ include file="../headers/home-header.jsp" %>

<div class="cont">
    <div class="car-opinion-container">
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
                <th scope="col">Średnia ocen</th>

            </tr>
            </thead>
            <tbody>
            <tr>
                <th id="carId" scope="row">${car.id}</th>
                <td id="carBrand">${car.brand}</td>
                <td>${car.model}</td>
                <td>${car.type}</td>
                <td>${car.productionDate}</td>
                <td>${car.plates}</td>
                <td>${car.pricePerDay}</td>
                <td>${car.ratingAverage} / 5</td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="car-opinion-container">
        <sec:authorize access="isAuthenticated()">
            <form:form method="post" modelAttribute="opinion" id="opinionAddForm">
                <h2 class="h3 mb-3 fw-normal">Dodaj opinię</h2> <br>
                <form:hidden path="id"/>
                <form:hidden path="user"/>
                <form:hidden path="car"/>
                <br>
                <div class="form-floating">
                    <form:textarea path="content" class="form-control" id="floatingContent" rows="4" cols="50"/>
                </div>
                <br>
                <div class="form-floating">
                    <form:select path="rating" class="form-control" id="floatingRating">
                        <form:option value="0" label="--Ocena--"/>
                        <form:options items="${rating}" type="number"/>
                    </form:select>
                    <label for="floatingRating">Ocena</label>
                </div>
                <br>
                <button class="btn btn-primary w-100 py-2" type="submit">Dodaj</button>
                <div id="error-message" class="alert alert-danger d-none"></div>
            </form:form>
        </sec:authorize>
    </div>
    <div class="car-opinion-container">
        <c:if test="${opinions.size() > 0}">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Użytkownik</th>
                <th scope="col">Opinia</th>
                <th scope="col">Ocena</th>
                <th scope="col">Dodano</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${opinions}" var="opinion">
            <tr>
                <td>${opinion.user.email}</td>
                <td>${opinion.content}</td>
                <td>${opinion.rating} / 5</td>
                <td>${opinion.created}</td>
                </c:forEach>
            </tbody>
        </table>
        </c:if>
    </div>
</div>

<%@ include file="../headers/home-footer.jsp" %>

</body>
</html>