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

<body>
<div class="container">
    <div class="col-xs-7">
        <a href="#addTariffModal"  class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xE147;</i>
            <span><fmt:message key="new.tariffs.add"/></span></a>
    </div>
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

                            </div>
                            <div class="form-group">
                                <label>Type Tariff</label>
                                <input type="text" name="type" id="type" class="form-control" required>

                            </div>

                            <div class="form-group">
                                <label>Price Tariff</label>
                                <input type="text" name="price" id="price" class="form-control" required>

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

    <div class="row row-cols-1 row-cols-md-2 g-4">
        <c:forEach var="tariff" items="${subscription}">

            <div class="col">
                <div class="card">
                    <div class="card-header">
                            ${tariff.name}
                    </div>
                    <div class="card-body">
                        <h5 class="card-title"></h5>
                        <p class="card-text">Знижка 25% відсотків на будь-яку послугу при оплаті за рік одним платежем</p>
                        <a href="#" class="btn btn-primary">Підключитись</a>
                    </div>
                    <div class="card-footer text-muted">
                        <h6>вартість користування за один місяц - ${tariff.getPricePerDay()} гривень</h6>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    </form>
</div>
</body>
</html>
