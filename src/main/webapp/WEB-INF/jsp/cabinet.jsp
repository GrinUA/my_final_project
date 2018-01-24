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
        <div class="col-md-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <a href="#">Sections of personal cabinet
                        </a>
                    </h4>
                </div>
                <div class="panel-collapse">
                    <ul class="list-group list-unstyled">
                        <c:if test="${sessionScope.s_user.role == 'CLIENT'}">
                            <li class="list-group-item"><a href="#">Personal data</a></li>
                            <li class="list-group-item"><a href="#">Orders</a></li>
                            <li class="list-group-item"><a href="#">Change password</a></li>
                            </c:if>
                        <c:if test="${sessionScope.s_user.role == 'ADMIN'}">
                            <li class="list-group-item"><a href="#">Personal data</a></li>
                            <li class="list-group-item"><a href="#">Books</a></li>
                            <li class="list-group-item"><a href="#">Clients</a></li>
                        </c:if>
                        <c:if test="${sessionScope.s_user.role == 'OPERATOR'}">
                            <li class="list-group-item"><a href="#">Personal data</a></li>
                            <li class="list-group-item"><a href="#">Orders</a></li>
                            <li class="list-group-item"><a href="#"></a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-9">
        <div class="panel panel-primary">
            <div class="panel-heading">Cabinet</div>
            <div class="panel-body panel-profile">
                <div class="row">
                    <div class="col-md-6">
                        <div class="profile-item profile-item-user">
                            <div class="col-md-9">
                                <div class="panel panel-primary">
                                    <div class="panel-heading">Личный кабинет</div>
                                    <div class="panel-body panel-profile">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="profile-item profile-item-user">
                                                    <a href="#">
                                                        <span>Личные данные</span>
                                                    </a>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="profile-item profile-item-orders">
                                                    <small>
                                                        <c:choose>
                                                        <c:when test="${not empty productList}">

                                                        <br>
                                                        <c:forEach items="${productList}" var="product">
                                                        <div class="col-sm-4">
                                                            <div class="product-image-wrapper">
                                                                <div class="single-products">
                                                                    <div class="productinfo text-center">
                                                                        <h2>$${product.price}</h2>
                                                                        <p>${product.name}</p>
                                                                        <p>${product.manufacturer}</p>
                                                                        <p>${product.category}</p>
                                                                        <input type="button" onclick="addToCart(${product.id})"
                                                                               class="btn btn-default add-to-cart action" value="Add to cart">
                                                                        <br>
                                                                    </div>
                                                                </div>
                                                                <div class="choose">
                                                                    <ul class="nav nav-pills nav-justified">
                                                                        <li><a href=""><i class="fa fa-plus-square"></i>Add
                                                                            to wishlist</a></li>
                                                                        <li><a href=""><i class="fa fa-plus-square"></i>Add
                                                                            to compare</a></li>
                                                                    </ul>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        </c:forEach>
                                                </div>
                                                </c:when>
                                                <c:otherwise>
                                                <h2 class="text-center">Products not found!</h2>
                                            </div>
                                            </c:otherwise>
                                            </c:choose>
                                            </small>
                                                </div>
                                            </div>
                                        </div>


                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="profile-item profile-item-orders">
                            <a href="#">
                                <span>Заказы</span>
                            </a>
                            <small>Информация о всех ваших заказах: номера, даты, состав заказов и их статусы.</small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
