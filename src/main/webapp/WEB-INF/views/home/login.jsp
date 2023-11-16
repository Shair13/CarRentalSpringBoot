<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../headers/head.jsp" %>

<body>

<%@ include file="../headers/home-header.jsp" %>

<div class="content bgc-img1">
        <form method="post" id="loginForm">
            <h1>Logowanie</h1> <br>
            <div class="form-floating">
                <input type="email" name="email" class="form-control" id="floatingEmail" placeholder="name@example.com">
            </div>
            <br>
            <div class="form-floating">
                <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Hasło">
            </div>
            <br>
            <input type="submit" value="OK">
            <div id="error-message" class="error d-none"></div>
        </form>
    </main>
</div>

<%@ include file="../headers/home-footer.jsp" %>
<script src="/js/validation.js"></script>

</body>
</html>