<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="sidebar">
    <div class="ul-container">


        <ul>
            <sec:authorize access="isAuthenticated()">

                <li class="menu-list dashboard-list"><a href="/">
                    <div class="nav-btn">▶ Home</div>
                </a></li>
                <br>
                <li class="menu-list dashboard-list"><a href="/admin/dashboard">
                    <div class="nav-btn">▶ Dashboard</div>
                </a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
                <li class="menu-list"><a href="/admin/cars">
                    <div class="nav-btn">▶ Zarządzaj flotą</div>
                </a></li>
                <li class="menu-list"><a href="/admin/departments">
                    <div class="nav-btn">▶ Zarządzaj oddziałami</div>
                </a></li>
                <li class="menu-list"><a href="/admin/users">
                    <div class="nav-btn">▶ Zarządzaj użytkownikami</div>
                </a></li>
                <li class="menu-list"><a href="/admin/opinions">
                    <div class="nav-btn">▶ Zarządzaj opiniami</div>
                </a></li>
                <li class="menu-list"><a href="/admin/rentals">
                    <div class="nav-btn">▶ Zarządzaj wypożyczeniami</div>
                </a></li>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                <li class="menu-list"><a href="/user/rent">▶ Wypożycz samochód</a></li>
                <li class="menu-list"><a href="/user/rentals">▶ Historia wypożyczeń</a></li>
                <li class="menu-list"><a href="/user/edit/password">▶ Zmień hasło</a></li>
                <li class="menu-list"><a href="/user/edit/data">▶ Edytuj dane</a></li>
            </sec:authorize>
        </ul>

        <sec:authorize access="isAuthenticated()">
            <form action="<c:url value="/logout"/>" method="post">
                <input class="fa fa-id-badge" type="submit" value="Wyloguj">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </sec:authorize>
    </div>
    <div>
    </div>
</div>