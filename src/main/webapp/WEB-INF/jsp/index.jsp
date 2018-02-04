<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/jspf/directive/head.jspf" %>

<body>

<t:header/>
<header class="masthead text-20" style="background-image: url('/imgs/background3.jpg')">
    <div class="overlay">

    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-10 mx-auto">
                <div class="site-heading">
                    <div class="container">
                        <div class="row carousel-holder">
                            <div class="col-md-12">
                                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                    <ol class="carousel-indicators bottom-5">
                                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                    </ol>
                                    <div class="carousel-inner">
                                        <c:set var="indexI" value="${bookGroups.size()>3?3:bookGroups.size()}"/>
                                        <c:forEach var="i" begin="0" end="${indexI-1}">
                                            <c:set var="topBook" scope="page" value="${bookGroups.get(i)}"/>

                                            <div class="item <c:if test="${i==0}">active</c:if>">
                                                <a href="/bookInfo.do?articul=${topBook.id}">
                                                    <div class="row margin-5t-5b-div">
                                                        <div class="col-md-2"></div>
                                                        <div class="col-md-4"><img class="slide-image slide-image-my"
                                                                                   src="/images/${topBook.image}?owner=user"
                                                                                   alt=""></div>
                                                        <div class="col-md-5 color-white">
                                                            <h3>${topBook.name}</h3>
                                                            <p>Author: ${topBook.author}</p>
                                                            <p>Edition: ${topBook.edition} / <fmt:formatDate pattern="MMM yyyy"
                                                                                                             value="${topBook.publicationDate}"/></p>
                                                            <p>Genre: ${topBook.genre.name}</p>
                                                            <p>${topBook.description}</p>

                                                        </div>
                                                        <div class="col-md-1"></div>
                                                    </div>
                                                </a>
                                            </div>

                                        </c:forEach>
                                    </div>
                                    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left"></span>
                                    </a>
                                    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right"></span>
                                    </a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</header>
<div class="container">

    <div class="row">

        <div class="col-md-3">
            <p class="lead">Search</p>
            <div class="list-group">
                <a href="#" class="list-group-item">Category 1</a>
                <a href="#" class="list-group-item">Category 2</a>
                <a href="#" class="list-group-item">Category 3</a>
            </div>
        </div>

        <div class="col-md-9">
            <div class="row">
                <c:choose>
                    <c:when test="${not empty bookGroups}">
                        <%--<t:pages />--%>
                        <br>
                        <c:forEach items="${bookGroups}" var="bookG">

                            <div class="col-sm-4 col-lg-4 col-md-4">
                                <div class="thumbnail">
                                    <img class="slide-image slide-image-my"
                                         src="/images/${bookG.image}?owner=user"
                                         alt="">
                                    <div class="caption">
                                        <h4><a href="/bookInfo.do?articul=${bookG.id}">${bookG.name}</a>
                                        </h4>
                                        <p>Author: ${bookG.author}</p>
                                        <p>Edition: ${bookG.edition} / <fmt:formatDate pattern="MMM yyyy"
                                                                                       value="${bookG.publicationDate}"/></p>
                                        <p>Genre: ${bookG.genre.name}</p>
                                        <p>${bookG.description}</p>
                                    </div>
                                    <div class="ratings">
                                        <p class="pull-right">12 reviews</p>
                                        <p>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star"></span>
                                            <span class="glyphicon glyphicon-star-empty"></span>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h2 class="text-center">Books not found!</h2>
                    </c:otherwise>
                </c:choose>

            </div>

        </div>

    </div>

</div>
<!-- /.container -->

<div class="container">

    <hr>

</div>
<!-- /.container -->

<!-- jQuery -->

<script src="../../js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="../../js/bootstrap.min.js"></script>

</body>

</html>