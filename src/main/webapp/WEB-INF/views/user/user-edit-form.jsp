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
        <form:form method="post" modelAttribute="user">
        <h1 class="h3 mb-3 fw-normal">Update user</h1><br>
        <form:hidden path="id"/>
        <div class="form-floating">
            <form:input path="firstName" class="form-control" id="floatingFirstName"/>
            <label for="floatingFirstName">First Name</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="lastName" class="form-control" id="floatingLastName"/>
            <label for="floatingLastName">Last Name</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="username" class="form-control" id="floatingUsername"/>
            <label for="floatingUsername">Nazwa użytkownika</label>
        </div>
        <br>
        <div class="form-floating">
            <form:select path="roles" class="form-control" id="floatingUserRole">
                <form:options items="${userRoles}" itemValue="id" itemLabel="name"/>
            </form:select>
            <label for="floatingUserRole">Typ użytkownika</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="email" class="form-control" id="floatingEmail"/>
            <label for="floatingEmail">Email</label>
        </div>
        <br>
        <button class="btn btn-primary w-100 py-2" type="submit">Update</button>
        <div id="error-message" class="alert alert-danger d-none">
            </form:form>
        </div>
    </div>

    <%@ include file="../headers/footer.jsp" %>

</body>
</html>