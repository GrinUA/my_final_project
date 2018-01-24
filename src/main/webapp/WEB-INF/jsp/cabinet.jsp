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
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a href="#">Sections of personal cabinet
                        </a>
                    </h4>
                </div>
                <div class="panel-collapse">
                    <ul class="list-group list-unstyled">
                        <c:if test="${sessionScope.s_user.role == 'CLIENT'}">
                            <li class="list-group-item"><a href="#">Personal data</a></li>
                            <li class="list-group-item"><a href="#">Orders</a></li>
                            <li class="list-group-item"><a href="#">Change password</a></li>
                            </c:if>
                        <c:if test="${sessionScope.s_user.role == 'ADMIN'}">
                            <li class="list-group-item"><a href="#">Personal data</a></li>
                            <li class="list-group-item"><a href="#">Books</a></li>
                            <li class="list-group-item"><a href="#">Clients</a></li>
                        </c:if>
                        <c:if test="${sessionScope.s_user.role == 'OPERATOR'}">
                            <li class="list-group-item"><a href="#">Personal data</a></li>
                            <li class="list-group-item"><a href="#">Orders</a></li>
                            <li class="list-group-item"><a href="#"></a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
