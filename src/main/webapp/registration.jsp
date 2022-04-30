<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${param.lang}" />
<fmt:setBundle basename="resources" />

<html lang="${param.lang}">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

<style>
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
        margin: 0 auto;
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
        <c:if test="${requestScope.get('validation_message')!=null}">
            <div class="alert alert-danger" role="alert">
                <%=request.getAttribute("validation_message")%>
            </div>
        </c:if>

        <form action="/user/registration" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1><fmt:message key="label.form" /></h1>
                </div>
                <div class="panel-body">

                    <div class="form-group">
                        <label for="phone"><fmt:message key="phone.label.phone"/></label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" name="phone" id="phone"
                                       placeholder="<fmt:message key="phone.placeholder.phone"/>"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password"><fmt:message key="password.label.password"/></label>

                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                <input type="password" class="form-control" name="password" id="password"
                                       placeholder="<fmt:message key="password.placeholder.password"/>"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="confirm password"><fmt:message key="confirm_password.label.confirm_password"/></label>

                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                <input type="password" class="form-control" name="conf_password"
                                       id="confirm password"
                                       placeholder="<fmt:message key="confirm_password.placeholder.confirm_password"/>"/>
                            </div>
                        </div>
                    </div>

                    <button class="btn btn-primary" type="submit"><fmt:message key="save.button.save"/></button>

                    <div>

                        <li><a href="?lang=en"class="btn btn-danger"><fmt:message key="label.lang.en" /></a></li>
                       </div>
                    <div>
                        <li><a href="?lang=de"class="btn btn-success"><fmt:message key="label.lang.ua" /></a></li>

                    </div>
                </div>
            </div>
        </form>


    </div>
</div>
