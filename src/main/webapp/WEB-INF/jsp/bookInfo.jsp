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
                        <p>Available: <b style="color: limegreen">${booksData.get("availableBookCount") - booksData.get("orderedBookCount")}</b>/<b>${booksData.get("availableBookCount")}</b></p>
                        <h3>DESCRIPTION</h3>
                        <p><i>${bookInfo.description}</i></p>
                        </p>
                        <!-- Split button -->
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary">
                                Order ->
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>
