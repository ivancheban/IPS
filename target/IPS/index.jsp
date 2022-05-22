<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />

<html lang="${sessionScope.lang}">


<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary" >

    <div class="container-fluid">

        <a class="navbar-brand" href="#"><fmt:message key="index.button.navbar" /></a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <li class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="?lang=ua" class="btn btn-info"><fmt:message key="label.lang.ua" /></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="?lang=en" class="btn btn-success"><fmt:message key="label.lang.en" /></a>
                </li>







<%--             </li>--%>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link disabled">Disabled</a>--%>
<%--                </li>--%>
            </ul>
        </li>
            <div>
                <a href="/registration.jsp" class="btn btn-success"><fmt:message key="index.button.signUP" /></a>
                <a href="/login.jsp" class="btn btn-dark"><fmt:message key="index.button.login" /></a>
            </div>
        </div>

</nav>

<img src="https://img1.akspic.ru/crops/5/1/2/0/80215/80215-priroda-atmosfera-mir-zemlya-liniya-1920x1080.jpg" width="1600" height="650">
</body>
</html>

