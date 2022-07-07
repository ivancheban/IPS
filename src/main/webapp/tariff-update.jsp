<%@ page import="com.dto.TariffDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<jsp:useBean id="TariffService" class="com.service.TariffServiceImpl"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html lang="${sessionScope.lang}">
<%
    TariffDto tariffDto = (TariffDto) session.getAttribute("tariffDto");
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
        <form action="/update/tariff" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1>Update Tariff</h1>
                </div>
                <div class="panel-body">

                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="id" name="id" id="id"
                               value="<%=tariffDto!=null?tariffDto.getId():""%>" hidden="hidden">
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
                                       value="<%=tariffDto!=null?tariffDto.getName():""%>"  required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label" for="service_type"><fmt:message key="list.type.list"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" placeholder="service_type" name="service_type" id="service_type"
                                   value="<%=tariffDto!=null?tariffDto.getType():""%>"  required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label" for="price_per_day"><fmt:message key="list.price.list"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="number" class="form-control" placeholder="price_per_day" name="price_per_day" id="price_per_day"
                                   value="<%=tariffDto!=null?tariffDto.getPricePerDay():""%>" required>
                        </div>
                    </div>
                </div>
            </div>
            <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">

        </form>
    </div>
</div>

</html>
