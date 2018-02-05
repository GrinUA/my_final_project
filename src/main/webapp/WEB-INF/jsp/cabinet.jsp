<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/directive/head.jspf" %>
<body>
<t:header/>
<div class="container">
    <div class="row">
        <div class="col-md-2">   <img class="img-rounded img-responsive" src="images/user.jpg?owner=user" alt="">
            <h4> ${sessionScope.s_user.firstName}</h4>
            <h4> ${sessionScope.s_user.lastName}</h4>
            <h6> ${sessionScope.s_user.email}</h6></div>
        <div class="col-md-8">
        <div class="panel panel-info">
            <div class="panel-heading">Cabinet</div>
           <t:cabinetTabs/>
        </div>
    </div>
        <div class="col-md-2"></div>
    </div>
</div>
<script src="../../js/jquery.js"></script>
<script src="../../js/bootstrap.min.js"></script>
</body>
