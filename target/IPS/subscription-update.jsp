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
    <link rel="stylesheet" href="/css/style-for-update.css">
</head>
<div class="container">
    <div class="row main-form">
        <form action="/update/form" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1>Update Service</h1>
                </div>

                <div class="form-group">
                    <input type="number" class="form-control" placeholder="id" name="id" id="id"
                           value="<%=subscriptionDto!=null?subscriptionDto.getId():""%>" hidden="hidden">
                    <div class="cols-sm-10">
                        <div class="input-group">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label" for="name"><fmt:message key="name.label.name"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" placeholder="name" name="name" id="name"
                                   value="<%=subscriptionDto != null ? subscriptionDto.getName() : ""%>" required>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="form-label" for="isActive">Amount</label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-phone fa" aria-hidden="true"></i></span>
                        <input type="text" class="form-control" placeholder="days_amount" name="days_amount"
                               id="days_amount"
                               value="<%=subscriptionDto!=null?subscriptionDto.getDays_amount():""%>" required>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="isActive">Status</label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-phone fa" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" placeholder="isActive" name="isActive"
                                       id="isActive"
                                       value="<%=subscriptionDto!=null?subscriptionDto.isActive():""%>" required>
                            </div>
                        </div>
                    </div>

                    </br>
                    <div alight="right">
                        <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                        <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</html>
