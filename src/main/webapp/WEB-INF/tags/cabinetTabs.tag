<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<ul class="nav nav-tabs">
    <c:if test="${sessionScope.s_user.role == 'ADMIN'}">
        <c:if test="${requestScope.activeTab == 'usersTab'}">
            <li role="presentation" class="active"><a href="cabinet.do?activeTab=usersTab">Users</a></li>
            <li role="presentation"><a href="cabinet.do?activeTab=booksTab">Books</a></li>
            <c:choose>
                <c:when test="${not empty userList}">
                    <%--<t:pages />--%>
                    <table class="table">
                        <th>Email</th>
                        <th>Surname</th>
                        <th>Name</th>
                        <th>Role</th>
                        <th>Block</th>

                        <c:forEach items="${userList}" var="user">
                            <c:if test="${user.email != sessionScope.s_user.email}">
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
                            </c:if>
                        </c:forEach>
                    </table>

                </c:when>
                <c:otherwise>
                    <h2 class="text-center">Users not found!</h2>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${requestScope.activeTab == 'booksTab'}">
            <li role="presentation"><a href="cabinet.do?activeTab=usersTab">Users</a></li>
            <li role="presentation" class="active"><a href="cabinet.do?activeTab=usersTab">Books</a></li>
            <c:choose>
                <c:when test="${not empty bookList}">
                    <%--<t:pages />--%>
                    <table class="table">
                        <th>Name</th>
                        <th>Author</th>
                        <th>Edition</th>
                        <th>Publication Date</th>
                        <th>Genre</th>
                        <th>Price</th>

                        <c:forEach items="${bookList}" var="book">
                            <tr class="success">
                                <td>${book.name}</td>
                                <td>${book.author}</td>
                                <td>${book.edition}</td>
                                <td>${book.publicationDate}</td>
                                <td>${book.genre}</td>
                                <td>${book.price}</td>
                            </tr>
                        </c:forEach>
                    </table>

                </c:when>
                <c:otherwise>
                    <h2 class="text-center">Books not found!</h2>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:if>
</ul>

