<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ include file="/WEB-INF/jspf/directive/head.jspf" %>
<!DOCTYPE html>
<html lang="en">
<t:header/>


<body>
<div class="container">

    <form class="form-signin" role="form" method="post" action="register.do">
        <h2 class="form-signin-heading">Registration</h2>
        <input name="email" type="text" class="form-control" placeholder="Email address"
                      value="${sessionScope.s_bean.email}" required="" autofocus="">
        <c:if test="${not empty sessionScope.s_errors.errors_2}"><a>${sessionScope.s_errors.errors_2}</a></c:if>
           <c:if test="${not empty sessionScope.s_errors.email}"><a>${sessionScope.s_errors.email}</a></c:if>
        <input name="password" type="password" class="form-control" placeholder="Password" required="">
        <c:if test="${not empty sessionScope.s_errors.password}"><a>${sessionScope.s_errors.password}</a></c:if>
        <input name="passwordRepeat" type="password" class="form-control" placeholder=" Repeat password" required="">
        <c:if test="${not empty sessionScope.s_errors.passwordRepeat}"><a>${sessionScope.s_errors.passwordRepeat}</a></c:if>
        <input name="firstName" type="text" class="form-control" placeholder="Name"
               value="${sessionScope.s_bean.firstName}" required="">
        <input name="lastName" type="text" class="form-control" placeholder="Surname"
               value="${sessionScope.s_bean.lastName}" required="">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
    <c:remove var="s_bean" scope="session"/>
    <c:remove var="s_errors" scope="session"/>
</div> <!-- /container -->

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

</body>

</html>