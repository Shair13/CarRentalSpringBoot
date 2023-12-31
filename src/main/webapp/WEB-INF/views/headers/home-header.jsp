<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="logo">Car rental project</div>
    <div class="header-space"></div>
    <nav class="navigation">
        <a href="/">
            <div class="menu-element">Strona główna</div>
        </a>
        <c:if test="${sessionScope.user != null}">
            <a href="${sessionScope.user.type}/dashboard">
                <div class="menu-element">Aplikacja</div>
            </a>
        </c:if>
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
    </nav>
</header>
