<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<header id="header">
    <nav class="navbar navbar-inverse navbar-fixed-top text-20" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="main.do">Library</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown"><fmt:message key="language"/> <span class="caret"></span></button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="locale?lang=en">English</a></li>
                            <li><a href="locale?lang=ru">Русский</a></li>
                        </ul>
                    </div></li>
                    <c:choose>
                    <c:when test="${not empty sessionScope.s_user}">

                        <li
                                class="login"><a>${sessionScope.s_user.firstName}<i class="fa fa-user"></i></a>
                        </li>
                        <li
                                class="login"><a href="cabinet.do?activeTab=default"><fmt:message key="cabinet"/> <i class="fa fa-user"></i></a>
                        </li>
                        <li>
                            <a href="logout.do"><i class="fa fa-lock"></i> <fmt:message key="logout"/> </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="register.do"><fmt:message key="register"/> </a>
                        </li>
                        <li>
                            <a href="login.do"><fmt:message key="login"/> </a>
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