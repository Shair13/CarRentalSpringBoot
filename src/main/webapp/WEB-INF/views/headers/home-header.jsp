<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header>
    <div class="logo">Car rental project</div>
    <div class="header-space"></div>
    <nav class="navigation">
        <a href="/">
            <div class="menu-element">Strona główna</div>
        </a>
        <sec:authorize access="isAuthenticated()">
            <a href="/dashboard">
                <div class="menu-element">Aplikacja</div>
            </a>
        </sec:authorize>
        <a href="/fleet">

            <div class="menu-element">Nasza flota</div>
        </a>
        <a href="/available">
            <div class="menu-element">Dostępne samochody</div>
        </a>
        <a href="/contact">
            <div class="menu-element">Kontakt</div>
        </a>
    </nav>
    <div class="header-space"></div>
    <nav class="navigation">
        <a href="/login">
            <div class="menu-element login-element">Logowanie</div>
        </a>
        <a href="/registration">
            <div class="menu-element register-element">Rejestracja</div>
        </a>
        <sec:authorize access="isAuthenticated()">
            <form action="<c:url value="/logout"/>" method="post">
                <input type="submit" value="Wyloguj">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </sec:authorize>

    </nav>
</header>
