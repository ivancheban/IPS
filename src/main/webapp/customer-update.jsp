
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
    <link rel="stylesheet" href="/css/style-for-update.css">

</head>

<div class="container">
    <div class="row main-form">
        <form action="/update-form/customer" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1><fmt:message key="customer.update"/></h1>
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
            <a type="submit" id="submit" value="submit"  class="btn btn-info"><fmt:message key="button.update"/></a>
        </form>
    </div>
</div>
</html>
