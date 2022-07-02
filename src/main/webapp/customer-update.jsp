
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
<body>


<div class="row">
    <div class="col-sm-4">
        <form  method="POST" action="/update-form/customer" >
            <input type="number" class="form-control" placeholder="id" name="id" id="id" value="<%=customerDto!=null?customerDto.getId():""%>" hidden="hidden">

            <div alight="left">
                <label class="form-label">Name</label>
            <input type="text" class="form-control" placeholder="name" name="name" id="name" value="<%=customerDto!=null?customerDto.getName():""%>"  required>
            </div>

            <div alight="left">
                <label class="form-label">SurName</label>
                <input type="text" class="form-control" placeholder="surname" name="surname" id="surname" value="<%=customerDto!=null?customerDto.getSurname():""%>"  required>
            </div>

            <div alight="left">
                <label class="form-label">Phone</label>
                <input type="text" class="form-control" placeholder="phone" name="phone" id="phone" value="<%=customerDto!=null?customerDto.getPhone():""%>" required>
            </div>

            <div alight="left">
                <label class="form-label">Email</label>
                <input type="text" class="form-control" placeholder="email" name="email" id="email" value="<%=customerDto!=null?customerDto.getEmail():""%>" required>
            </div>

            <div alight="right">
                <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
            </div>

        </form>
    </div>
</div>

</body>
</html>
