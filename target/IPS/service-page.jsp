<%@ page import="com.dto.SubscriptionDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<jsp:useBean id="TariffService" class="com.service.TariffServiceImpl"/>
<jsp:useBean id="TariffDao" class="com.dao.TariffDao"/>
<jsp:useBean id="SubscriptionService" class="com.service.SubscriptionServiceImpl"/>
<jsp:useBean id="SubscriptionDao" class="com.dao.SubscriptionDao"/>
<jsp:useBean id="SubscriptionDto" class="com.dto.SubscriptionDto"/>


<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap User Management Data Table</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

</head>

<br>

<nav class="navbar navbar-expand-lg navbar-light bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href=""></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                 <h1>${subscriptionDto.getName()}</h1>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/sort/tariffs?sortBy=name">Sort By Name</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/sort/tariffs?sortBy=price">Price Sorting</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled"></a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<br>
    <div class="row row-cols-2 row-cols-md-5 g-3">
        <c:forEach var="tariff" items="${subscription}">

            <div class="col">
                <div class="card">
                    <div class="card-header">
                            ${tariff.name}

                    </div>
                    <div class="card-body">
                        <h5 class="card-title"></h5>

                    </div>
                    <div class="card-footer text-muted">
                        <h6> ${tariff.getPricePerDay()} гривень за один місяц</h6>
                        <a href="/add/tariff/customer?customer_id=${sessionScope.get('customerId')}&tariff_id=${tariff.id}" >Підключитись</a>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</body>
</html>
