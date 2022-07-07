<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>
<jsp:useBean id="TariffService" class="com.service.TariffServiceImpl"/>
<jsp:useBean id="TariffDao" class="com.dao.TariffDao"/>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="resources" />
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap User Management Data Table</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/list.css">

    <script>
        $(document).ready(function(){
            $('[data-toggle="tooltip"]').tooltip();
        });
    </script>
</head>
<body>

<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-xs-5">

                        <h2><fmt:message key="list.tariffs.list"/></h2>
                    </div>
                    <div class="col-xs-7">
                        <a href="#addTariffModal"  class="btn btn-primary" data-toggle="modal"><i class="material-icons">&#xE147;</i>
                            <span><fmt:message key="new.tariffs.add"/></span></a>
                    </div>
                </div>
            </div>

            <form action="/addTariff" method="post">
                <div id="addTariffModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <form>
                                <div class="modal-header">
                                    <h4 class="modal-title">Add Tariff</h4>
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>Name Tariff</label>
                                        <input type="text" name="name" id="name" class="form-control" required>

                                    </div>
                                    <div class="form-group">
                                        <label>Type Tariff</label>
                                        <input type="text" name="type" id="type" class="form-control" required>

                                    </div>

                                    <div class="form-group">
                                        <label>Price Tariff</label>
                                        <input type="text" name="price" id="price" class="form-control" required>

                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                    <input type="submit" class="btn btn-success" value="Add">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


            <table class="table table-striped table-hover">

                <thead>
                <tr>
                    <th><fmt:message key="list.name.list"/></th>
                    <th><fmt:message key="list.type.list"/></th>
                    <th><fmt:message key="list.price.list"/></th>
                    <th><fmt:message key="list.active.list"/></th>
                    <th><fmt:message key="list.created.list"/></th>
                    <th><fmt:message key="list.updated.list"/></th>

                    <th><fmt:message key="list.update.list"/></th>
                    <th><fmt:message key="list.delete.list"/></th>
                </tr>
                </thead>
                <tbody>


                <c:forEach var="tariff" items="${tariffsList}" >

                    <tr>
                        <td>${tariff.getName()}</td>
                        <td>${tariff.getType()}</td>
                        <td>${tariff.getPricePerDay()}</td>
                        <td>${tariff.isActive()}</td>
                        <td>${tariff.getCreated()}</td>
                        <td>${tariff.getUpdated()}</td>

                        <td><a href="/update/tariff?id=${tariff.id}" class="edit" data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a></td>
                        <td> <a href="/delete/tariff?id=${tariff.id}" class="btn btn-danger">Delete</a></td>

                    </tr>

                </c:forEach>
                </tbody>
            </table>
                <c:if test="${currentPage != 1}">
                <td><a href="tariffs?page=${currentPage - 1}">Previous</a></td>
            </c:if>
                <table border="1" cellpadding="5" cellspacing="5">
                <tr>
                    <c:forEach begin="1" end="${noOfPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <td>${i}</td>
                            </c:when>
                            <c:otherwise>
                                <td><a href="tariffs?page=${i}">${i}</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </tr>
            </table>
                <c:if test="${currentPage lt noOfPages}">
                <td><a href="tariffs?page=${currentPage+ 1}">Next</a></td>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
