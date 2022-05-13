<%@ page import="com.dto.UserCreateRequestDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html lang="${sessionScope.lang}">

<%
UserCreateRequestDto userDto = ((UserCreateRequestDto) session.getAttribute("userCreateRequestDto"));
%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

<style>
    @import url('https://fonts.googleapis.com/css?family=Caveat:700');

    * {
        box-sizing: border-box;
    }
    body {
        background: url(https://look.com.ua/pic/201805/1920x1080/look.com.ua-282297.jpg);
        background-repeat: no-repeat;
        background-size: cover;
        font-family: 'Caveat', sans-serif;
        margin: 0;
        padding: 0;
    }
    section {
        background: rgba( 0,0,0,.6);
        border-radius: 5px;
        left: 50%;
        padding: 40px;
        position: absolute;
        transform: translate(-50%, -50%);
        top: 50%;
        width: 400px;
    }
    section h2 {
        color: #fff;
        font-family: 'Caveat', sans-serif;
        font-size: 30px;
        margin: 0 0 30px;
        padding: 0;
        text-align: center;
    }
    .form-group {
        margin-bottom: 15px;
    }

    label {
        margin-bottom: 15px;
    }

    input,
    input::-webkit-input-placeholder {
        font-size: 11px;
        padding-top: 3px;
    }

    .form-control {
        height: auto !important;
        padding: 8px 12px !important;
    }

    .input-group {
        box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.21) !important;
    }

    #button {
        border: 1px solid #ccc;
        margin-top: 28px;
        padding: 6px 12px;
        color: #666;
        text-shadow: 0 1px #fff;
        cursor: pointer;
        border-radius: 3px 3px;
        box-shadow: 0 1px #fff inset, 0 1px #ddd;
        background: #f5f5f5;
        background: -moz-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f5f5f5), color-stop(100%, #eeeeee));
        background: -webkit-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        background: -o-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        background: -ms-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        background: linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
        filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f5f5f5', endColorstr='#eeeeee', GradientType=0);
    }

    .main-form {
        margin-top: 30px;
        margin: 20px auto;
        max-width: 400px;
        padding: 10px 40px;
        background: #009edf;
        color: #FFF;
        text-shadow: none;
        box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.31);
    }

    span.input-group-addon i {
        color: #009edf;
        font-size: 17px;
    }

    .login-button {
        margin-top: 5px;
    }

</style>


<div class="container">



    <div class="row main-form">
        <c:if test="${sessionScope.get('errorMessages') !=null}">
            <div class="alert alert-danger" role="alert">

                <fmt:message key="label.invalidData" />

            </div>
        </c:if>

        <form action="/user/registration" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1><fmt:message key="label.form" /></h1>
                </div>
                <div class="panel-body">

<%--                    <div class="form-group">--%>

<%--                        <label class="form-label" for="phone"><fmt:message key="phone.label.phone" /></label>--%>
<%--                        <div class="cols-sm-10">--%>
<%--                            <div class="input-group">--%>
<%--                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>--%>
<%--                        <input type="phone" name="phone" id="phone" class="form-control form-control-lg"--%>
<%--                               placeholder="<fmt:message key="phone.placeholder.phone"/>"--%>
<%--                               value="<%=userDto!=null? userDto.getPhone():""%>"/>--%>
<%--                        <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').containsKey('phone') }">--%>
<%--                            <span class="text-danger"><fmt:message key="label.validPhone" /></span>--%>
<%--                        </c:if>--%>
<%--                    </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>

    <div class="form-outline mb-4">
        <label class="form-label" for="phone"><fmt:message key="phone.label.phone" /></label>
        <input type="phone" name="phone" id="phone" class="form-control form-control-lg"
               value="<%=userDto!=null?userDto.getPhone():""%>"/>
        <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').containsKey('phone') }">
                                        <span class="text-danger">
<%--                                        <%=((Map<String, String>) session.getAttribute("errorMessages")).get("email")%>--%>
                                            <fmt:message key="label.validPhone" />
                                        </span>
        </c:if>
    </div>
                    <div class="form-group">

                        <label class="form-label" for="phone"><fmt:message key="password.label.password" /></label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                <input type="phone" name="phone" id="phone" class="form-control form-control-lg"
                                       placeholder="<fmt:message key="password.placeholder.password"/>"
                                       value="<%=userDto!=null? userDto.getPassword():""%>"/>
                                <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').containsKey('password') }">
                                    <span class="text-danger"><fmt:message key="label.validPassword" /></span>
                                </c:if>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">

                        <label class="form-label" for="phone"><fmt:message key="confirm_password.label.confirm_password" /></label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                <input type="phone" name="phone" id="phone" class="form-control form-control-lg"
                                       placeholder="<fmt:message key="confirm_password.placeholder.confirm_password"/>"
                                       value="<%=userDto!=null? userDto.getPassword():""%>"/>
                                <c:if test="${sessionScope.get('errorMessages') != null && sessionScope.get('errorMessages').containsKey('confirm_password') }">
                                    <span class="text-danger"><fmt:message key="label.validConfirm_Password" /></span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary" type="submit"><fmt:message key="save.button.save"/></button>


                </div>
            </div>
        </form>


    </div>
</div>
