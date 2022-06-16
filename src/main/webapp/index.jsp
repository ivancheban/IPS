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
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

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




                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       Послуги
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/internet.jsp">Інтернет</a>
                        <a class="dropdown-item" href="#">Мобільний звя'зок</a>
                        <a class="dropdown-item" href="#">Кабельне телебачення</a>
                        <a class="dropdown-item" href="#">Цифрове телебачення</a>
                        <div class="dropdown-divider"></div>

                    </div>
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

