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
        <h2> Welcome to cabinet ${sessionScope.s_user.firstName} ${sessionScope.s_user.lastName}!</h2>
    </div>
    <div class="col-md-9">
        <div class="panel panel-info">
            <div class="panel-heading">Cabinet</div>
           <t:cabinetTabs/>
        </div>
    </div>
</div>

</body>
