<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../headers/head.jsp" %>

<body>

<%@ include file="../headers/home-header.jsp" %>

<div class="content bgc-img1">
    <form:form method="post" modelAttribute="registration" id="userAdminAddForm">
        <h1 class="h3 mb-3 fw-normal">Rejestracja</h1> <br>
        <form:hidden path="id"/>
        <div class="form-floating">
            <form:input path="firstName" class="form-control" id="floatingFirstName" placeholder="Imię"/>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="lastName" class="form-control" id="floatingLastName" placeholder="Nazwisko"/>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="email" class="form-control" id="floatingEmail" placeholder="Email"/>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="password" type="password" class="form-control" id="floatingPassword" placeholder="Hasło"/>
        </div>
        <br>
        <div class="form-floating">
            <input type="password" name="pass" class="form-control" id="floatingRepeatPassword" placeholder="Powtórz hasło"/>
        </div>
        <br>
        <button class="btn btn-primary w-100 py-2" type="submit">Zarejestruj się</button>
        <div id="error-message" class="error d-none"></div>
    </form:form>
</div>

<%@ include file="../headers/home-footer.jsp" %>
<script src="/js/validation.js"></script>


</body>
</html>