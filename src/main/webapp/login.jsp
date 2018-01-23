<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/directive/head.jspf" %>

<t:header/>
<body>

<div class="container">

    <form class="form-signin login" role="form" method="post" action="login.do">
        <c:choose>
            <c:when test="${empty sessionScope.success_message}">
                <h2 class="form-signin-heading">Please sign in</h2>
            </c:when>
            <c:otherwise>
                <h3 style="color: green;">${sessionScope.success_message}</h3>
            </c:otherwise>
        </c:choose>

        <input name="email" type="email" class="form-control" placeholder="Email address" required="" autofocus="">
        <input name="password" type="password" class="form-control" placeholder="Password" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
    <c:remove var="success_message" scope="session"/>
</div> <!-- /container -->

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>