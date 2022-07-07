<%@ page import="com.dto.CustomerDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />
<html lang="${sessionScope.lang}">
<%CustomerDto customerDto = ((CustomerDto) session.getAttribute("customerDto"));%>

<head>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style-for-update.css">
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<div class="container">
    <div class="row main-form">
        <form action="/add/balance" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1><fmt:message key="add.balance"/></h1>
                </div>
                <div class="form-group">
                        <input type="number" class="form-control" placeholder="id" name="id" id="id"
                               value="<%=customerDto!=null?customerDto.getId():""%>" hidden="hidden">
                        <div class="cols-sm-10">
                            <div class="input-group">
                            </div>
                        </div>
                    </div>
                <div class="form-group">
                <label class="form-label" for="balance"><fmt:message key="balance"/></label>
                <div class="cols-sm-10">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                <input type="number" class="form-control" placeholder="balance" name="balance" id="balance"
                       value="<%=customerDto!=null?customerDto.getBalance():""%>" required>
            </div>
            </div>
            </div>
            </br>
            <div alight="right">
                <button class="btn btn-primary" type="submit"><fmt:message key="add.button"/></button>
                <button class="btn btn-warning" type="reset"><fmt:message key="reset.button"/></button>
            </div>
            </div>
        </form>
    </div>
</div>
</html>

