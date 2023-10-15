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
        <form:form method="post" modelAttribute="department" id="departmentUpdateForm">
        <h1 class="h3 mb-3 fw-normal">Edytuj oddział</h1> <br>
        <div class="form-floating">
            <form:input path="city" class="form-control" id="floatingCity"/>
            <label for="floatingCity">Miasto</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="street" class="form-control" id="floatingStreet"/>
            <label for="floatingStreet">Ulica</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="number" class="form-control" id="floatingNumber"/>
            <label for="floatingNumber">Numer budynku / lokalu</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="zipCode" class="form-control" id="floatingZipCode"/>
            <label for="floatingZipCode">Kod pocztowy</label>
        </div>
        <br>
        <div class="form-floating">
            <form:input path="phone" class="form-control" id="floatingPhone"/>
            <label for="floatingPhone">Numer telefonu</label>
        </div>
        <br>
        <button class="btn btn-primary w-100 py-2" type="submit">Aktualizuj</button>
        <a href="/admin/departments">Cofnij</a>
        <div id="error-message" class="alert alert-danger d-none">
            </form:form>
        </div>
    </div>

    <%@ include file="../headers/footer.jsp" %>

</body>
</html>


