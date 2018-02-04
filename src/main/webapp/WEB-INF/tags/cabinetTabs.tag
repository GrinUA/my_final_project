<%@ tag body-content="empty" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>


<ul class="nav nav-tabs">
    <c:if test="${sessionScope.s_user.role == 'ADMIN'}">
        <c:if test="${requestScope.activeTab == 'usersTab'}">
            <li role="presentation" class="active"><a href="cabinet.do?activeTab=usersTab">Users</a></li>
            <li role="presentation"><a href="cabinet.do?activeTab=booksTab">Books</a></li>
            <li role="presentation"><a href="cabinet.do?activeTab=genre">Add new genre</a>
            </li>
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
                                        <tr class="success">
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="active">
                                    </c:otherwise>
                                </c:choose>
                                <td>${user.email}</td>
                                <td>${user.lastName}</td>
                                <td>${user.firstName}</td>
                                <td>${user.role}</td>
                                <td>${user.blocked}</td>

                                <td> <form action="userStatus.do" method="post">
                                <input type="hidden" name="email" value="${user.email}"/>
                                <c:choose>
                                    <c:when test="${user.blocked}">
                                        <input type="hidden" name="userStatus" value="unblock"/>
                                        <button type="submit" class="btn btn-success">Unblock</button>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="userStatus" value="block"/>
                                        <button type="submit" class="btn btn-danger">&ensp;Block&ensp;
                                        </button>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                                </form>
                                <td>
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
            <li role="presentation"><a href="cabinet.do?activeTab=genre">Add new genre</a></li>
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
                                <td><fmt:formatDate pattern="MMM yyyy"
                                                    value="${book.publicationDate}"/></td>
                                <td>${book.genre.name}</td>
                                <td>${book.price}</td>
                                <td>
                                    <a href="editBook.do?articul=${book.id}"
                                       class="btn btn-primary">Edit</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </c:when>
                <c:otherwise>
                    <h2 class="text-center">Books not found!</h2>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${requestScope.activeTab == 'genre'}">
            <li role="presentation"><a href="cabinet.do?activeTab=usersTab">Users</a></li>
            <li role="presentation"><a href="cabinet.do?activeTab=usersTab">Books</a></li>
            <li role="presentation" class="active"><a href="cabinet.do?activeTab=genre">Add new genre</a>
            </li>
            <form action=""add new genre></form>
        </c:if>
    </c:if>
    <c:if test="${sessionScope.s_user.role == 'CLIENT'}">
        <c:choose>
            <c:when test="${not empty userOrders}">
                <table class="table">
                    <th>Id</th>
                    <th>Name</th>
                    <th>Author</th>
                    <th>Place</th>
                    <th>Penalty</th>
                    <th>Status</th>
                    <c:forEach items="${userOrders}" var="order">
                        <c:choose>
                            <c:when test="${order.status =='CLOSED' or order.status == 'CANCELED'}">
                                <tr class="success">
                            </c:when>
                            <c:otherwise>
                                <c:if test="${order.status == 'WAITING'}">
                                    <tr class="info">
                                </c:if>
                                <c:if test="${order.status == 'OPEN'}">
                                    <c:choose>
                                        <c:when test="${order.penalty > 0} ">
                                            <tr class="danger">
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                        </c:otherwise>
                                    </c:choose>

                                </c:if>

                            </c:otherwise>
                        </c:choose>
                        <td>${order.guId}</td>
                        <td>${order.bookGroup.name}</td>
                        <td>${order.bookGroup.author}</td>
                        <c:choose>
                            <c:when test="${order.place == 'true'}">
                                <td>On hands</td>
                            </c:when>
                            <c:otherwise>
                                <td>Reading room</td>
                            </c:otherwise>
                        </c:choose>
                        <td>${order.penalty}</td>
                        <td>${order.status}</td>
                        <c:if test="${order.status == 'WAITING'}">
                       <td> <form action="orderStatus.do" method="post">
                            <input hidden value="${order.guId}" name="guId">
                            <input hidden value="${order.bookGroup.price}" name="price">
                            <input hidden name="orderStatus" value="CANCELED">
                            <button type="submit">Canceled</button>
                        </form></td></c:if>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                Choose a book!
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${sessionScope.s_user.role == 'OPERATOR'}">
        <c:choose>
            <c:when test="${not empty userOrders}">
                <c:if test="${requestScope.activeTab == 'openTab'}">
                    <li role="presentation" class="active"><a href="cabinet.do?activeTab=openTab">Open</a></li>
                    <li role="presentation"><a href="cabinet.do?activeTab=closedTab">Closed</a></li>
                    <li role="presentation"><a href="cabinet.do?activeTab=canceledTab">Canceled</a></li>
                    <li role="presentation"><a href="cabinet.do?activeTab=canceledTab">Waiting</a></li>
                    <table class="table">
                        <th>Id</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Book</th>
                        <th>Author</th>
                        <th>Place</th>
                        <th>Penalty</th>
                        <th>Status</th>
                        <c:forEach items="${userOrders}" var="order">
                            <c:if test="${order.status == 'OPEN'}">
                                <td>${order.guId}</td>
                                <td>${order.user.firstName}</td>
                                <td>${order.user.lastName}</td>
                                <td>${order.bookGroup.name}</td>
                                <td>${order.bookGroup.author}</td>
                                <c:choose>
                                    <c:when test="${order.place == 'true'}">
                                        <td>On hands</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Reading room</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${order.penalty}</td>
                                <td>${order.status}</td>
                                <td>
                                    <form action="orderStatus.do" method="post">
                                        <input hidden value="${order.guId}" name="guId">
                                        <input hidden value="${order.bookGroup.price}" name="price">
                                        <div class="input-group">
                                            <div class="input-group-append">

                                                <select name="orderStatus" class="custom-<select name=">
                                                    <option selected value="CLOSED">Closed</option>
                                                    <button type="submit">Change</button>
                                                </select>

                                            </div>
                                        </div>
                                    </form>
                                </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${requestScope.activeTab == 'closedTab'}">
                <li role="presentation" ><a href="cabinet.do?activeTab=openTab">Open</a></li>
                <li role="presentation" class="active"><a href="cabinet.do?activeTab=closedTab">Closed</a></li>
                <li role="presentation"><a href="cabinet.do?activeTab=canceledTab">Canceled</a></li>
                <li role="presentation"><a href="cabinet.do?activeTab=canceledTab">Waiting</a></li>
                <table class="table">
                    <th>Id</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Book</th>
                    <th>Author</th>
                    <th>Place</th>
                    <th>Penalty</th>
                    <th>Status</th>
                    <c:forEach items="${userOrders}" var="order">
                        <c:if test="${order.status == 'CLOSED'}">
                            <td>${order.guId}</td>
                            <td>${order.user.firstName}</td>
                            <td>${order.user.lastName}</td>
                            <td>${order.bookGroup.name}</td>
                            <td>${order.bookGroup.author}</td>
                            <c:choose>
                                <c:when test="${order.place == 'true'}">
                                    <td>On hands</td>
                                </c:when>
                                <c:otherwise>
                                    <td>Reading room</td>
                                </c:otherwise>
                            </c:choose>
                            <td>${order.penalty}</td>
                            <td>${order.status}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </table>
            </c:if>
                <c:if test="${requestScope.activeTab == 'canceledTab'}">
                    <li role="presentation" ><a href="cabinet.do?activeTab=openTab">Open</a></li>
                    <li role="presentation"><a href="cabinet.do?activeTab=closedTab">Closed</a></li>
                    <li role="presentation" class="active"><a href="cabinet.do?activeTab=canceledTab">Canceled</a></li>
                    <li role="presentation"><a href="cabinet.do?activeTab=canceledTab">Waiting</a></li>
                    <table class="table">
                        <th>Id</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Book</th>
                        <th>Author</th>
                        <th>Place</th>
                        <th>Penalty</th>
                        <th>Status</th>
                        <c:forEach items="${userOrders}" var="order">
                            <c:if test="${order.status == 'CANCELED'}">
                                <td>${order.guId}</td>
                                <td>${order.user.firstName}</td>
                                <td>${order.user.lastName}</td>
                                <td>${order.bookGroup.name}</td>
                                <td>${order.bookGroup.author}</td>
                                <c:choose>
                                    <c:when test="${order.place == 'true'}">
                                        <td>On hands</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Reading room</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${order.penalty}</td>
                                <td>${order.status}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${requestScope.activeTab == 'waitingTab'}">
                    <li role="presentation" ><a href="cabinet.do?activeTab=openTab">Open</a></li>
                    <li role="presentation"><a href="cabinet.do?activeTab=closedTab">Closed</a></li>
                    <li role="presentation"><a href="cabinet.do?activeTab=canceledTab">Canceled</a></li>
                    <li role="presentation" class="active"><a href="cabinet.do?activeTab=canceledTab">Waiting</a></li>
                    <table class="table">
                        <th>Id</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Book</th>
                        <th>Author</th>
                        <th>Place</th>
                        <th>Penalty</th>
                        <th>Status</th>
                        <c:forEach items="${userOrders}" var="order">
                            <c:if test="${order.status == 'WAITING'}">
                                <td>${order.guId}</td>
                                <td>${order.user.firstName}</td>
                                <td>${order.user.lastName}</td>
                                <td>${order.bookGroup.name}</td>
                                <td>${order.bookGroup.author}</td>
                                <c:choose>
                                    <c:when test="${order.place == 'true'}">
                                        <td>On hands</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>Reading room</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${order.penalty}</td>
                                <td>${order.status}</td>
                                <td>
                                    <form action="orderStatus.do" method="post">
                                        <input hidden value="${order.guId}" name="guId">
                                        <input hidden value="${order.bookGroup.price}" name="price">
                                        <div class="input-group">
                                            <div class="input-group-append">

                                                <select name="orderStatus" class="custom-<select name=">
                                                    <option selected value="OPEN">Open</option>
                                                    <option selected value="CANCELED">Canceled</option>
                                                    <button type="submit">Change</button>
                                                </select>

                                            </div>
                                        </div>
                                    </form>
                                </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </c:if>
            </c:when>
            <c:otherwise>
                Choose a book!
            </c:otherwise>
        </c:choose>
    </c:if>


</ul>



