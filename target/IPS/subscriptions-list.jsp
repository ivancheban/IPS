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
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Subscriptions Management Data Table</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/list.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-xs-5">

                        <h2><fmt:message key="list.subscriptions.list"/></h2>
                    </div>
                    <div class="col-xs-7">
                        <a href="#addSubscriptionsModal" class="btn btn-primary" data-toggle="modal"><i
                                class="material-icons">&#xE147;</i>
                            <span><fmt:message key="new.subscriptions.add"/></span></a>
                    </div>
                </div>
            </div>
            <div>

                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th><fmt:message key="list.name.list"/></th>
                        <th><fmt:message key="list.active.list"/></th>
                        <th><fmt:message key="list.created.list"/></th>
                        <th><fmt:message key="list.updated.list"/></th>
                        <th><fmt:message key="menu.service"/></th>
                        <th><fmt:message key="list.update.list"/></th>
                        <th><fmt:message key="list.delete.list"/></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="subscription" items="${subscriptionsList}">

                        <tr>
                            <td>${subscription.getName()}</td>
                            <td>

                                <c:if test="${subscription.isActive()}">
                                    enabled

                                </c:if>

                                <c:if test="${!subscription.isActive()}">
                                    disabled

                                </c:if>
                            </td>
                            <td>${subscription.getCreated()}</td>
                            <td>${subscription.getUpdated()}</td>
                            <td><a href="/open/service?id=${subscription.id}" class="btn btn-success">Menu Service</a>
                            </td>
                            <td><a href="/update/subscription?id=${subscription.id}" class="btn btn-primary">Update</a></td>
                            <td><a href="/delete/subscription?id=${subscription.id}" class="btn btn-danger">Delete</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<c:if test="${currentPage != 1}">
    <td><a href="subscriptions?page=${currentPage - 1}">Previous</a></td>
</c:if>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="subscriptions?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>


<c:if test="${currentPage lt noOfPages}">
    <td><a href="subscriptions?page=${currentPage+ 1}">Next</a></td>
</c:if>

<form action="/add/subscription" method="post">
    <div id="addSubscriptionsModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Add Subscription</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times
                    </button>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label>Name Subscription</label>
                        <input type="text" name="name" id="name" class="form-control" required>
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" value="Add">
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
