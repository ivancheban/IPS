<%@ page import="com.dto.CustomerCreateRequestDto" %>
<%@ page import="com.dto.CustomerDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>

<html lang="${sessionScope.lang}">

<head>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">

    <div class="container-fluid">
        <a class="navbar-brand" href="">Personal Cabinet</a>

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


                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="/subscriptions" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="index.service"/>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/open/service?id=21"><fmt:message key="service.internet"/></a>
                        <a class="dropdown-item" href="/open/service?id=14"><fmt:message key="service.mobile"/></a>
                        <a class="dropdown-item" href="/open/service?id=16"><fmt:message key="service.IPTV"/></a>
                        <a class="dropdown-item" href="/open/service?id=20"><fmt:message key="service.cableTv"/></a>
                        <div class="dropdown-divider"></div>
                    </div>
            </ul>
        </li>
        <a href="/IPS" class="btn btn-danger"><fmt:message key="button.logout"/></a>
        <a href="/update/profile/customer?id=${sessionScope.get('customerId')}" class="btn btn-warning"><fmt:message key="button.updateProfile"/></a>
        <a href="/add/balance" class="btn btn-success"><fmt:message key="add.balance"/></a>
    </div>

</nav>

<div class="card">
    <div class="card-body"><!-- Начало текстового контента -->
        <div class="row row-cols-2 row-cols-md-5 g-3">

            <c:if test="${sessionScope.get('fullName')!=null}">
            <div>
                    ${sessionScope.get('fullName')}
            </div>

            <div>
                    ${sessionScope.get('phoneNumber')}
            </div>
            <div>
                    ${sessionScope.get('email')}
            </div>
            <div>
                    ${sessionScope.get('balance')}

            </div>

            </c:if>

    </div>
</div>
</div>
<c:if test="${sessionScope.get('SubscriptionErrorMessage') != null
        && sessionScope.get('SubscriptionErrorMessage').contains('NotEnoughMoney')}">
    <div class="alert alert-warning alert-dismissible fade show mt-3" role="alert">
        You haven't enough money. Please top up your balance!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    ${sessionScope.remove("SubscriptionErrorMessage") }
</c:if>
<c:if test="${sessionScope.get('SubscriptionErrorMessage') != null
        && sessionScope.get('SubscriptionErrorMessage').contains('youAreAlreadySubscribed')}">
    <div class="alert alert-warning alert-dismissible fade show mt-3" role="alert">
        You are already subscribed to this service!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    ${sessionScope.remove("SubscriptionErrorMessage") }
</c:if>
<form>
    <div class="row row-cols-2 row-cols-md-5 g-3">
        <c:forEach var="tariff" items="${sessionScope.get('tariffsSubscriptions')}">

            <div class="col">
                <div class="card">
                    <div class="card-header">
                            ${tariff.name}
                    </div>
                    <div class="card-body">
                    </div>
                    <div class="card-footer text-muted">
                        <h6> ${tariff.getPricePerDay()} гривень за один місяц</h6>
                        <h1> ${tariff.getType()}</h1>
                        <a href="/delete/tariff-customer?tariffs_id=${tariff.id}">Bідключитись</a>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</form>

</body>
</html>

