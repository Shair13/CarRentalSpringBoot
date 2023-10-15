<sec:authorize access="isAuthenticated()">
    <div class="user-name"><sec:authentication property="principal.username"/> : <sec:authentication property="authorities"/></div>
    <div class="logo"></div>
</sec:authorize>