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
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <img class="img-rounded img-responsive" src="/images/${bookInfo.image}?owner=user" alt="">
                    </div>
                    <div class="col-sm-6 col-md-8">
                        <h4>${bookInfo.name}</h4>
                        <p>
                        <p>Author: ${bookInfo.author}</p>
                        <p>Edition: ${bookInfo.edition} / <fmt:formatDate pattern="MMM yyyy"
                                                                         value="${bookInfo.publicationDate}"/></p>
                        <p>Genre: ${bookInfo.genre.name}</p>
                        <h3>DESCRIPTION</h3>
                        <p><i>${bookInfo.description}</i></p>
                        </p>
                        <!-- Split button -->
                        <c:choose>
                            <c:when test="${not empty sessionScope.s_user}">
                                <c:if test="${sessionScope.s_user.role == 'CLIENT'}">
                                <c:choose>
                                    <c:when test="${booksData.availableBookCount > 0}">
                                        <form action="order.do" method="post" >
                                            <input hidden name="articul" value="${bookInfo.id}">
                                            <div class="btn-group">
                                                <button type="submit" class="btn btn-primary">
                                                    Order this book!
                                                </button>
                                            </div>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                            <div class="btn-group">
                                                <button class="btn btn-primary">
                                                   This book not available now
                                                </button>
                                            </div>
                                    </c:otherwise>

                                </c:choose>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                    <div class="btn-group">
                                        <a href="login.do" class="btn btn-primary">
                                            Please login
                                        </a>
                                    </div>
                            </c:otherwise>

                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>
