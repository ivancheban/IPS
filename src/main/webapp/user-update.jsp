<%@ page import="com.dto.UserDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<jsp:useBean id="UserService" class="com.service.UserServiceImpl"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="resources"/>
<html lang="${sessionScope.lang}">
<%
    UserDto userDto = (UserDto) session.getAttribute("userDto");
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
        <form action="/edit/form" method="post">
            <div class="panel panel-primary">
                <div class="panel-heading text-center">
                    <h1><fmt:message key="user.update"/></h1>
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <input type="number" class="form-control" placeholder="id" name="id" id="id"
                               value="<%=userDto!=null?userDto.getId():""%>" hidden="hidden">
                        <div class="cols-sm-10">
                            <div class="input-group">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-label" for="phone"><fmt:message key="phone.label.phone"/></label>
                        <div class="cols-sm-10">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                <input type="text" class="form-control" placeholder="phone" name="phone" id="phone"
                                       value="<%=userDto != null ? userDto.getPhone() : ""%>" required>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label" for="password"><fmt:message key="password.label.password"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" placeholder="password" name="password" id="password"
                                   value="<%=userDto != null ? userDto.getPassword() : ""%>" required>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label" for="isActive"><fmt:message key="list.active.list"/></label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-phone fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" placeholder="isActive" name="isActive" id="isActive"
                                   value="<%=userDto!=null?userDto.isActive():""%>" required>
                        </div>

                        <div class="form-group">
                            <label class="form-label" for="role"><fmt:message key="user.role"/></label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-lock" aria-hidden="true"></i></span>
                                    <input type="text" class="form-control" placeholder="role" name="role" id="role"
                                           value="<%=userDto!=null?userDto.getRole():""%>" required>
                                </div>
                            </div>
                        </div>
                        <button type="submit" id="submit" value="submit"  class="btn btn-info"><fmt:message key="button.update"/></button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
