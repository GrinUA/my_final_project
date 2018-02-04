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
                            <button class="btn btn-lg btn-primary btn-block">Change image</button>
                        </div>
                        <div class="col-sm-6 col-md-8">
                            <h4>
                                Name: <input name="name" type="text" class="form-control" placeholder="Name" required="">
                            </h4>
                            <p>
                            <p>Author: <input name="author" type="text" class="form-control" placeholder="Author" required=""></p>
                            <p>Edition: <input name="edition" type="text" class="form-control" placeholder="Edition" required=""></p>
                            <p>Publication date: <input name="publicationDate" type="date" class="form-control" required=""></p>
                            <p>Genre: <select name="genre" class="form-control">

                            </select> </p>
                            <p>Price: <input name="price" type="text" class="form-control" placeholder="Price" required=""></p>
                            <h3>DESCRIPTION</h3>
                            <p><i><input name="description" type="text" class="form-control" placeholder="Description" required=""></i></p>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Create</button>
                            <!-- Split button -->
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>
</div>
</body>
</html>
