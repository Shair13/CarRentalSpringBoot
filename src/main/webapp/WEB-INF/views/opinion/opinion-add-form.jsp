<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en" data-bs-theme="auto">

<%@ include file="../headers/head.jsp" %>

<body>
<header>
    <%@ include file="../headers/current-user.jsp" %>
</header>
<div class="container">

    <%@ include file="../headers/dashboard-header.jsp" %>

    <div class="content">
        <form:form method="post" modelAttribute="opinion" id="opinionAddForm">
            <h1 class="h3 mb-3 fw-normal">Dodaj opinię</h1> <br>
            <form:hidden path="id"/>
            <div class="form-floating">
                <form:select path="user" class="form-control" id="floatingUser">
                    <form:option value="" label="--Wybierz użytkownika--"/>
                    <form:options items="${users}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingUser">Użytkownik</label>
            </div><br>
            <div class="form-floating">
                <form:select path="car" class="form-control" id="floatingCar">
                    <form:option value="" label="--Wybierz samochód--"/>
                    <form:options items="${cars}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <label for="floatingCar">Samochód</label>
            </div><br>
            <div class="form-floating">
                <form:textarea path="content" class="form-control" id="floatingContent"/>
                <label for="floatingContent">Opinia</label>
            </div><br>
            <div class="form-floating">
                <form:select path="rating" class="form-control" id="floatingRating">
                    <form:option value="0" label="--Ocena--"/>
                    <form:options items="${rating}" type="number"/>
                </form:select>
                <label for="floatingRating">Ocena</label>
            </div><br>
            <button class="btn btn-primary w-100 py-2" type="submit">Dodaj</button>
            <div id="error-message" class="alert alert-danger d-none"></div>
        </form:form>
    </div>
</div>

<%@ include file="../headers/footer.jsp" %>

</body>
</html>