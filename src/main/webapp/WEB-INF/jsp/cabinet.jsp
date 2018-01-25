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
        <h2> Welcome to cabinet ${sessionScope.s_user.firstName} ${sessionScope.s_user.lastName}!</h2>
    </div>
    <div class="col-md-9">
        <div class="panel panel-primary">
            <div class="panel-heading">Cabinet</div>
            <div class="panel-body panel-profile">

                <small>
                    <c:choose>
                        <c:when test="${not empty userList}">
                            <%--<t:pages />--%>
                            <br>
                            <div class="panel panel-default">
                                <!-- Default panel contents -->
                                <div class="panel-heading">Users</div>

                                <table class="table">
                                    <th>Email</th>
                                    <th>Surname</th>
                                    <th>Name</th>
                                    <th>Role</th>
                                    <th>Block</th>

                                    <c:forEach items="${userList}" var="user">
                                        <c:choose>
                                            <c:when test="${user.blocked}">
                                                <tr class="danger">
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="success">
                                            </c:otherwise>
                                        </c:choose>
                                            <td>${user.email}</td>
                                            <td>${user.lastName}</td>
                                            <td>${user.firstName}</td>
                                            <td>${user.role}</td>
                                            <td>${user.blocked}</td>
                                        </tr>

                                    </c:forEach>
                                </table>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <h2 class="text-center">Users not found!</h2>
                        </c:otherwise>
                    </c:choose>
                </small>
            </div>
        </div>
    </div>
</div>

</body>
