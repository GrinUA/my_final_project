<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<header id="header">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="main.do">Library</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <c:if test="${sessionScope.s_user.role == 'ADMIN'}">
                            <div class="btn-group">
                                <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">Inverse <span class="caret"></span></button>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Action</a></li>
                                    <li><a href="#">Another action</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li class="divider"></li>
                                    <li><a href="#">Separated link</a></li>
                                </ul>
                            </div><!-- /btn-group -->
                        </c:if>
                    </li>
                    <c:choose>
                    <c:when test="${not empty sessionScope.s_user}">
                        <li
                                class="login"><a href="#">${sessionScope.s_user.email} <i class="fa fa-user"></i></a>
                        </li>
                        <li>
                            <a href="logout.do"><i class="fa fa-lock"></i> LogOut</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="register.do">Register</a>
                        </li>
                        <li>
                            <a href="login.do">Login</a>
                        </li>
                    </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
</header>