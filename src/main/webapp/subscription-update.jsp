<%@ page import="com.dto.SubscriptionDto" %>
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
    SubscriptionDto subscriptionDto = (SubscriptionDto) session.getAttribute("subscriptionDto");
%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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
<form action="/update/form" method="post">
    <form>
    <h4 class="modal-title">Update Subscription</h4>


    <div class="form-group">
        <label>ID Subscription</label>
        <input type="number" name="id" id="id" class="form-control" value="<%=subscriptionDto!=null?subscriptionDto.getId():""%>" required>
    </div>

        <div class="form-group">
            <label>Name Subscription</label>
            <input type="text" name="name" id="name" class="form-control" value="<%=subscriptionDto!=null?subscriptionDto.getName():""%>" required>

        </div>
        <div class="form-group">
            <label>Days Amount</label>
            <input type="number" name="days_amount" id="days_amount" class="form-control" value="<%=subscriptionDto!=null?subscriptionDto.getDays_amount():""%>""
                   required>
        </div>
    <div class="form-group">
        <label>Status</label>
        <input type="text" name="isActive" id="isActive" class="form-control"value="<%=subscriptionDto!=null?subscriptionDto.isActive():""%>" required>

    </div>

        <div>
            <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
            <input type="submit" class="btn btn-success" value="Save">
        </div>
    </form>

</form>
</body>
</html>
