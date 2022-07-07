<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<jsp:useBean id="userService" class="com.service.UserServiceImpl"/>
<jsp:useBean id="userDao" class="com.dao.UserDao"/>
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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/list.css">
</head>
<body>
<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-xs-5">
                        <h2><fmt:message key="list.users.list"/></h2>
                        <h4><a href="/admin.jsp">Back</a></h4>
                    </div>

                    <div class="col-xs-7">
                        <a href="/registration.jsp" class="btn btn-primary"><i class="material-icons"
                                                                               style="color:green">&#xE147;</i>
                            <span><fmt:message key="button.addUser.list"/></span></a>
                    </div>
                </div>
            </div>

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th><fmt:message key="list.phone.list"/></th>
                    <th><fmt:message key="list.password.list"/></th>
                    <th><fmt:message key="list.active.list"/></th>
                    <th><fmt:message key="list.role.list"/></th>
                    <th><fmt:message key="list.created.list"/></th>
                    <th><fmt:message key="list.updated.list"/></th>

                    <th><fmt:message key="list.update.list"/></th>
                    <th><fmt:message key="list.delete.list"/></th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="user" items="${userList}">

                    <tr>
                        <td>${user.getPhone()}</td>
                        <td>${user.getPassword()}</td>
                        <td>${user.isActive()}</td>
                        <td>${user.getRole()}</td>
                        <td>${user.getCreated()}</td>
                        <td>${user.getUpdated()}</td>

                        <td><a href="/user/update?id=${user.id}" class="btn btn-info">Update</a></td>
                        <td><a href="/user/delete?id=${user.id}" class="btn btn-danger">Delete</a></td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>


            <c:if test="${currentPage != 1}">
                <td><a href="user.do?page=${currentPage - 1}">Previous</a></td>
            </c:if>
            <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="user.do?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="user.do?page=${currentPage+ 1}">Next</a></td>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
