<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<form action="searchBook.do" method="get">
    <div class="form-group">
        <label for="sortBy">Sorting:</label>
        <label for="sortBy">${errors.get(sortBy)}</label>
        <select class="form-control" id="sortBy" name="sortBy" value="${filterParams.sortBy}">
            <option selected value="">Sort By</option>
            <option value="name">Name</option>
            <option value="author">Author</option>
            <option value="edition">Edition</option>
            <option value="publicationDate">Publication Date</option>
        </select>
        <div class="radio">
            <label><input type="radio" name="orderBy" value="ASC">ASC</label>
        </div>
        <div class="radio">
            <label><input type="radio" name="orderBy" value="DESC">DESC</label>
        </div>
    </div>
    <div class="form-group">
        <label for="name">Name:</label>
        <label for="name">${errors.get(name)}</label>
        <input type="text" class="form-control" id="name" name="name" value="${filterParams.name}">
    </div>
    <div class="form-group">
        <label for="author">Author:</label>
        <label for="author">${errors.get(author)}</label>
        <input type="text" class="form-control" id="author" name="author" value="${filterParams.author}">
    </div>
    <div class="form-group">
        <label for="edition">Edition:</label>
        <label for="edition">${errors.get(edition)}</label>
        <input type="text" class="form-control" id="edition" name="edition" value="${filterParams.edition}">
    </div>
    <div class="form-group">
        <label for="publicationDate">Publication date:</label>
        <label for="publicationDate">${errors.get(publicationDate)}</label>
        <input type="date" class="form-control" id="publicationDate" name="publicationDate" value="${filterParams.publicationDate}">
    </div>
    <div class="form-group">
        <label for="genre">Genre:</label>
        <label for="genre">${errors.get(genre)}</label>
        <select class="form-control" id="genre" name="genreId" value="${filterParams.name}">
            <option selected value="">Select Genre</option>
            <c:forEach items="${genres}" var="genre">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select></div>
    <input type="submit" class="btn btn-default" value="Search"/>
</form>