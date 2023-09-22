
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
    <div class="logo">Car rental project</div>
    <div class="header-space"></div>
    <nav class="navigation">
        <a href="/"><div class="menu-element">Strona główna</div></a>
        <a href="${sessionScope.user.type}/dashboard"><div class="menu-element">Aplikacja</div></a>
        <a href="#"><div class="menu-element">Nasza flota</div></a>
        <a href="#"><div class="menu-element">Zarezerwuj samochód</div></a>
        <a href="#"><div class="menu-element">Kontakt</div></a>
    </nav>
    <div class="header-space"></div>
    <nav class="navigation">
        <a href="/login"><div class="menu-element login-element">Logowanie</div></a>
        <a href="/registration"><div class="menu-element register-element">Rejestracja</div></a>
    </nav>
</header>
