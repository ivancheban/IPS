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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/login.css "/>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">
            <div class="row main-form">
                <c:if test="${sessionScope.get('loginError') !=null}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="label.validBlocked"/>
                    </div>
                </c:if>

                <form action="/login" class="form-horizontal" method="post" style="scroll-margin-right: 20px">
                    <span class="heading"><fmt:message key="signIn.label.signIn"/></span>
                    <div class="form-group">
                        <input type="phone" class="form-control" id="phone" name="phone"
                               placeholder="<fmt:message key="login.phone.login"/>">
                        <i class="fa fa-phone"></i>
                    </div>

                    <div class="form-group help">
                        <input type="password" class="form-control" id="inputPassword" name="password"
                               placeholder="<fmt:message key="login.password.login"/>">
                        <i class="fa fa-lock"></i>
                    </div>
                    <button type="submit"><fmt:message key="signIn.button.signIn"/></button>
                </form>
            </div>
        </div>
    </div>
    </div>

    </body>
</html>
