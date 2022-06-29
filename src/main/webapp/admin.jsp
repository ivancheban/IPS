<%@ page import="com.dto.TariffDto" %>
<%@ page import="com.dto.CustomerCreateRequestDto" %>
<%@ page import="com.dto.SubscriptionDto" %>
<% request.setCharacterEncoding("UTF-8");%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<jsp:useBean id="SubscriptionService" class="com.service.SubscriptionServiceImpl"/>
<jsp:useBean id="SubscriptionDao" class="com.dao.SubscriptionDao"/>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.lang}">

    <%

TariffDto tariffDto = ((TariffDto) session.getAttribute("tariffDto"));
%>
        <%

SubscriptionDto subscriptionDto = ((SubscriptionDto) session.getAttribute("subscriptionDto"));
%>


<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">

    <div class="container-fluid">

        <a class="navbar-brand" href="#"><fmt:message key="admin.panel"/></a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <li class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="?lang=ua" class="btn btn-info"><fmt:message
                            key="label.lang.ua"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="?lang=en" class="btn btn-success"><fmt:message
                            key="label.lang.en"/></a>
                </li>


                <%--             </li>--%>
                <%--                <li class="nav-item">--%>
                <%--                    <a class="nav-link disabled">Disabled</a>--%>
                <%--                </li>--%>
            </ul>
        </li>
        <div><a href="/tariffs" class="btn btn-success"><fmt:message key="all.wallets.admin"/></a>
            <a href="/user.do" class="btn btn-success"><fmt:message key="all.users.admin"/></a>
            <a href="/subscriptions" class="btn btn-success"><fmt:message key="all.subscriptions.admin"/></a>



        </div>
    </div>

</nav>

<%--#Tariff--%>
<form action="/addTariff" method="post">
<div id="addTariffModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form>
                <div class="modal-header">
                    <h4 class="modal-title">Add Tariff</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label>Name Tariff</label>
                        <input type="text" name="name" id="name" class="form-control" required>
                        <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').contains('name') }">
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label>Type Tariff</label>
                        <input type="text" name="type" id="type" class="form-control" required>
                        <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').contains('type') }">
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label>Price Tariff</label>
                        <input type="text" name="price" id="price" class="form-control" required>
                        <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').contains('price') }">
                        </c:if>
                    </div>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>

<%--    #Subscription--%>

    <form action="/add/subscription" method="post">
        <div id="addSubscriptionModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form>
                        <div class="modal-header">
                            <h4 class="modal-title">Add Subscription</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Name Subscription</label>
                                <input type="text" name="name" id="name" class="form-control" required>
                                <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').contains('name') }">
                                </c:if>
                            </div>
                            <div class="form-group">
                                <label>Days Amount</label>
                                <input type="text" name="days_amount" id="days_amount" class="form-control" required>
                                <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').contains('days_amount') }">
                                </c:if>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                            <input type="submit" class="btn btn-success" value="Add">
                        </div>
                    </form>
                </div>
            </div>
        </div>


<!DOCTYPE html>
<html>
<head>
    <title>Responsive Our Service Section Design with html css and Bootstrap | Website Design tutorial</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

</head>
<body>
<%--<div class="pt-5 pb-5">--%>
<%--    <div class="container">--%>
        <div class="row">
            <div class="section-head col-sm-12">

            </div>
            <div class="col-lg-4 col-sm-6">
                <div class="item"><span class="icon feature_box_col_one"><i class="fa fa-globe"></i></span>
                    <h6>Пакет Базовий</h6>
                    <p>250 грн</p>
                    <a href="">Підключити</a>
                </div>
            </div>
            <div class="col-lg-4 col-sm-6">
                <div class="item">

                                      <span class="icon feature_box_col_two"><i class="fa fa-home"></i></span>
                    <h6>Пакет Домашній</h6>
                    <p>300 грн</p>
                    <a href="">Підключити</a>
                </div>
            </div>
<%--            <div class="col-lg-4 col-sm-6">--%>
<%--                <div class="item"><span class="icon feature_box_col_three"><i class="fa fa-users"></i></span>--%>
<%--                    <h6>Пакет Сімейний</h6>--%>
<%--                    <p>350 грн</p>--%>
<%--                    <a href="">Підключити</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="col-lg-4 col-sm-6">--%>
<%--                <div class="item"><span class="icon feature_box_col_four"><i class="fa fa-phone"></i></span>--%>
<%--                    <h6>Phone</h6>--%>
<%--                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor Aenean--%>
<%--                        massa.</p>--%>
<%--                </div>--%>
<%--            </div>--%>


            <div class="col-lg-4 col-sm-6">
                <div class="item"><span class="icon feature_box_col_six"><i class="fa fa-tv"></i></span>

                    <c:forEach var="subscriptions" items="${subscriptionsList}" >
                        ${subscriptions.getName()}
                        ${subscriptions.getDays_amount()}
                    </c:forEach>

                </div>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx"
        crossorigin="anonymous"></script>


<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    span {
        display: inline-block;
        text-decoration: none;
        color: inherit;
    }

    .section-head {
        margin-bottom: 60px;
    }

    .section-head h4 {
        position: relative;
        padding: 0;
        color: lightblue;
        line-height: 1;
        letter-spacing: 0.3px;
        font-size: 34px;
        font-weight: 700;
        text-align: center;
        text-transform: none;
        margin-bottom: 30px;
    }

    .section-head h4::before {
        content: '';
        width: 60px;
        height: 3px;
        background: lightblue;
        position: absolute;
        bottom: -10px;
        left: 0;
        right: 0;
        margin: 0 auto;
    }

    .section-head h4 span {
        font-weight: 700;
        padding-bottom: 5px;
        color: #2f2f2f;
    }

    .section-head p {
        color: #818181;
        font-size: 16px;
        line-height: 28px;
        text-align: center;
    }

    .item {
        background: #fff;
        text-align: center;
        padding: 30px 25px;
        box-shadow: 0 0 25px rgba(0, 0, 0, 0.07);
        border-radius: 20px;
        margin-bottom: 30px;
        border: 5px solid rgba(0, 0, 0, 0.07);
        -webkit-transition: all 0.5s ease 0s;
        transition: all 0.5s ease 0s;
    }

    .item:hover {
        background: lightblue;
        box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.2);
        -webkit-transition: all 0.5s ease 0s;
        transition: all 0.5s ease 0s;
    }

    .item:hover .item,
    .item:hover span.icon {
        background: #fff;
        border-radius: 10px;
        -webkit-transition: all 0.5s ease 0s;
        transition: all 0.5s ease 0s;
    }

    .item:hover h6,
    .item:hover p {
        color: #fff;
        -webkit-transition: all 0.5s ease 0s;
        transition: all 0.5s ease 0s;
    }

    .item .icon {
        font-size: 40px;
        margin-bottom: 25px;
        color: lightblue;
        width: 90px;
        height: 90px;
        line-height: 96px;
        border-radius: 50px;
    }

    .item .feature_box_col_one {
        background: rgba(247, 198, 5, 0.2);
        color: lightblue;
    }

    .item .feature_box_col_two {
        background: rgba(255, 77, 28, 0.15);
        color: lightblue;
    }

    .item .feature_box_col_three {
        background: rgba(0, 147, 38, 0.15);
        color: lightblue;
    }

    .item .feature_box_col_four {
        background: rgba(0, 108, 255, 0.15);
        color: lightblue;
    }

    .item .feature_box_col_five {
        background: rgba(146, 39, 255, 0.15);
        color: lightblue;
    }

    .item .feature_box_col_six {
        background: rgba(23, 39, 246, 0.15);
        color: lightblue;
    }

    .item p {
        font-size: 15px;
        line-height: 26px;
    }

    .item h6 {
        margin-bottom: 20px;
        color: #2f2f2f;
    }


</style>

</body>
</html>


</form>
