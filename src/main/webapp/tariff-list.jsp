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
    <style>
        body {
            color: #566787;
            background: #f5f5f5;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
        }
        .table-responsive {
            margin: 30px 0;
        }
        .table-wrapper {
            min-width: 1000px;
            background: #fff;
            padding: 20px 25px;
            border-radius: 3px;
            box-shadow: 0 1px 1px rgba(0,0,0,.05);
        }
        .table-title {
            padding-bottom: 15px;
            background: #299be4;
            color: #fff;
            padding: 16px 30px;
            margin: -20px -25px 10px;
            border-radius: 3px 3px 0 0;
        }
        .table-title h2 {
            margin: 5px 0 0;
            font-size: 24px;
        }
        .table-title .btn {
            color: #566787;
            float: right;
            font-size: 13px;
            background: #fff;
            border: none;
            min-width: 50px;
            border-radius: 2px;
            border: none;
            outline: none !important;
            margin-left: 10px;
        }
        .table-title .btn:hover, .table-title .btn:focus {
            color: #566787;
            background: #f2f2f2;
        }
        .table-title .btn i {
            float: left;
            font-size: 21px;
            margin-right: 5px;
        }
        .table-title .btn span {
            float: left;
            margin-top: 2px;
        }
        table.table tr th, table.table tr td {
            border-color: #e9e9e9;
            padding: 12px 15px;
            vertical-align: middle;
        }
        table.table tr th:first-child {
            width: 60px;
        }
        table.table tr th:last-child {
            width: 100px;
        }
        table.table-striped tbody tr:nth-of-type(odd) {
            background-color: #fcfcfc;
        }
        table.table-striped.table-hover tbody tr:hover {
            background: #f5f5f5;
        }
        table.table th i {
            font-size: 13px;
            margin: 0 5px;
            cursor: pointer;
        }
        table.table td:last-child i {
            opacity: 0.9;
            font-size: 22px;
            margin: 0 5px;
        }
        table.table td a {
            font-weight: bold;
            color: #566787;
            display: inline-block;
            text-decoration: none;
        }
        table.table td a:hover {
            color: #2196F3;
        }
        table.table td a.settings {
            color: #2196F3;
        }
        table.table td a.delete {
            color: #F44336;
        }
        table.table td i {
            font-size: 19px;
        }
        table.table .avatar {
            border-radius: 50%;
            vertical-align: middle;
            margin-right: 10px;
        }
        .status {
            font-size: 30px;
            margin: 2px 2px 0 0;
            display: inline-block;
            vertical-align: middle;
            line-height: 10px;
        }
        .text-success {
            color: #10c469;
        }
        .text-info {
            color: #62c9e8;
        }
        .text-warning {
            color: #FFC107;
        }
        .text-danger {
            color: #ff5b5b;
        }
        .pagination {
            float: right;
            margin: 0 0 5px;
        }
        .pagination li a {
            border: none;
            font-size: 13px;
            min-width: 30px;
            min-height: 30px;
            color: #999;
            margin: 0 2px;
            line-height: 30px;
            border-radius: 2px !important;
            text-align: center;
            padding: 0 6px;
        }
        .pagination li a:hover {
            color: #666;
        }
        .pagination li.active a, .pagination li.active a.page-link {
            background: #03A9F4;
        }
        .pagination li.active a:hover {
            background: #0397d6;
        }
        .pagination li.disabled i {
            color: #ccc;
        }
        .pagination li i {
            font-size: 16px;
            padding-top: 6px
        }
        .hint-text {
            float: left;
            margin-top: 10px;
            font-size: 13px;
        }
    </style>
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
<%--#ADD Tariff--%>
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

                        <td><a href="#editTariffModal" class="edit" data-toggle="modal">
                            <i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a></td>
                        <td> <a href="/tariff/delete" class="btn btn-danger">Delete</a></td>

                    </tr>

                </c:forEach>
                </tbody>
            </table>
            <%--            <div class="clearfix">--%>
            <%--                <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>--%>
            <%--                <ul class="pagination">--%>
            <%--                    <li class="page-item disabled"><a href="#">Previous</a></li>--%>
            <%--                    <li class="page-item"><a href="#" class="page-link">1</a></li>--%>
            <%--                    <li class="page-item"><a href="#" class="page-link">2</a></li>--%>
            <%--                    <li class="page-item active"><a href="#" class="page-link">3</a></li>--%>
            <%--                    <li class="page-item"><a href="#" class="page-link">4</a></li>--%>
            <%--                    <li class="page-item"><a href="#" class="page-link">5</a></li>--%>
            <%--                    <li class="page-item"><a href="#" class="page-link">Next</a></li>--%>
            <%--                </ul>--%>
            <%--            </div>--%>
            <%--For displaying Previous link except for the 1st page --%>




            <c:if test="${currentPage != 1}">
                <td><a href="tariffs?page=${currentPage - 1}">Previous</a></td>
            </c:if>

            <%--For displaying Page numbers.
            The when condition does not display a link for the current page--%>
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

            <%--For displaying Next link --%>
            <c:if test="${currentPage lt noOfPages}">
                <td><a href="tariffs?page=${currentPage+ 1}">Next</a></td>
            </c:if>

        </div>
    </div>
</div>



</body>


</html>
