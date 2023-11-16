<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../headers/head.jsp" %>

<body>

<%@ include file="../headers/home-header.jsp" %>
<div class="bgc-img1">
    <div class="space"></div>
    <div class="cont">
        <c:forEach items="${cars.content}" var="car">
            <a href="/opinions?carId=${car.id}">
                <div class="fleet-container">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">id</th>
                            <th scope="col">Marka</th>
                            <th scope="col">Model</th>
                            <th scope="col">Typ</th>
                            <th scope="col">Data produkcji</th>
                            <th scope="col">Rejestracja</th>
                            <th scope="col">Cena za dzień</th>
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
            </a>
        </c:forEach>

    </div>
</div>
<%@ include file="../headers/home-footer.jsp" %>

</body>
</html>