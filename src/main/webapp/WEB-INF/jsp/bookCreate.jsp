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
                    <form action="createBook.do" method="post">
                        <div class="col-sm-6 col-md-4">
                            <img class="img-rounded img-responsive" src="/images/${bookInfo.image}?owner=user" alt="">
                            <a href="#"  class="btn btn-lg btn-primary btn-block">Change image</a>
                        </div>
                        <div class="col-sm-6 col-md-8">
                            <h4>
                                Name: <input name="name" type="text" class="form-control" placeholder="Name" required="">
                            </h4>
                            <p>
                            <p>Author: <input name="author" type="text" class="form-control" placeholder="Author" required=""></p>
                            <p>Edition: <input name="edition" type="text" class="form-control" placeholder="Edition" required=""></p>
                            <p>Publication date: <input name="publicationDate" type="date" class="form-control" required=""></p>
                            <p>Genre: <select class="form-control"  name="genreName">
                                    <option  selected value="">Select Genre</option>
                                    <c:forEach items="${genres}" var="genre">
                                        <option value="${genre.name}">${genre.name}</option>
                                    </c:forEach>
                                </select></div>
                           </p>
                            <p>Price: <input name="price" type="text" class="form-control" placeholder="Price" required=""></p>
                        <p><input name="count" type="number" class="form-control" placeholder="Amount" required=""></p>
                        <h3>DESCRIPTION</h3>
                            <p><i><input name="description" type="text" class="form-control" placeholder="Description" required=""></i></p>
                             <input class="btn btn-lg btn-primary btn-block" type="submit">
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>
