<%@ page import="com.dto.CustomerCreateRequestDto" %>
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
<%

    CustomerDto customerDto = ((CustomerDto) session.getAttribute("customerDto"));
%>

<head>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div class="row">
    <div class="col-sm-4">
        <form  method="POST" action="/customer/update/form" >
            <input type="number" class="form-control" placeholder="id" name="id" id="id" value="<%=customerDto!=null?customerDto.getId():""%>" hidden="hidden">

            <div alight="left">
                <label class="form-label">balance</label>
                <input type="number" class="form-control" placeholder="balance" name="balance" id="balance" value="<%=customerDto!=null?customerDto.getBalance():""%>" required>
            </div>
            </br>
            <div alight="right">
                <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
            </div>

        </form>
    </div>
</div>

<img src="https://img1.akspic.ru/crops/5/1/2/0/80215/80215-priroda-atmosfera-mir-zemlya-liniya-1920x1080.jpg" width="1600" height="650">
</body>
</html>

