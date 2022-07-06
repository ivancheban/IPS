
<%@ page import="com.dto.CustomerDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<jsp:useBean id="CustomerServise" class="com.service.CustomerServiceImpl"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html lang="${sessionScope.lang}">
<%
    CustomerDto customerDto = (CustomerDto) session.getAttribute("customerDto");
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
        background: rgba(0, 0, 0, .6);
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
        <form action="/update-form/customer" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1>Update User</h1>
                </div>
                <div class="panel-body">


                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="id" name="id" id="id"
                               value="<%=customerDto!=null?customerDto.getId():""%>" hidden="hidden">
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
                                       value="<%=customerDto != null ? customerDto.getName() : ""%>" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">

                    <label class="form-label" for="surname"><fmt:message key="surname.label.surname"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" placeholder="surname" name="surname" id="surname"
                                   value="<%=customerDto!= null ? customerDto.getSurname(): ""%>" required>
                        </div>
                    </div>
                </div>
                <div class="form-group">

                    <label class="form-label" for="phone"><fmt:message key="phone.label.phone"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" placeholder="phone" name="phone" id="phone"
                                   value="<%=customerDto != null ? customerDto.getPhone() : ""%>" required>
                        </div>
                    </div>
                </div>
            </div>

                <div class="form-group">

                            <label class="form-label" for="email"><fmt:message key="email.label.email"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" placeholder="role" name="email" id="email"
                                           value="<%=customerDto!=null?customerDto.getEmail():""%>" required>
                                </div>
                            </div>
                        </div>


                        <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">

        </form>
    </div>
</div>


</html>
