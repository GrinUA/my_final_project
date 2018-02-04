<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form action="searchBook.do" method="get">
    <div class="form-group">
        <label for="name">Name:</label>
        <input type="text" class="form-control" id="name" name="name">
    </div>
    <div class="form-group">
        <label for="author">Author:</label>
        <input type="text" class="form-control" id="author" name="author">
    </div>
    <div class="form-group">
        <label for="edition">Edition:</label>
        <input type="text" class="form-control" id="edition" name="edition">
    </div>
    <div class="form-group">
        <label for="publicationDate">Publication date:</label>
        <input type="date" class="form-control" id="publicationDate" name="publicationDate">
    </div>
    <div class="form-group">
        <label for="genre">Genre:</label>
        <select class="form-control" id="genre" name="genreId">
            <option selected value="">Select Genre</option>
            <c:forEach items="${genres}" var="genre">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select></div>
    <input type="submit" class="btn btn-default" value="Search"/>
</form>