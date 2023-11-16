<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../headers/head.jsp" %>

<body>

<%@ include file="../headers/home-header.jsp" %>

<div class="bgc-img2">
    <h3 class="ceo-color">Phone numbers & e-mail addresses.</h3>
    <br>
    <br>
    <p class="ceo-color">CEO's:</p> <br>
    <c:forEach items="${CEO}" var="c">
        <h1 class="ceo-color">${c.fullName} - ${c.email}</h1> <br>
    </c:forEach></div>
</div>
<%@ include file="../headers/home-footer.jsp" %>

</body>
</html>