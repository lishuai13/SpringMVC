<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书籍列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <style id="picture">
        .imgBox {
            width: 100px;
            height: 130px;
            margin: 0 auto;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>书籍列表 —— 显示所有书籍</small>
                </h1>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 column">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/toAddBook">新增</a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/goOut">退出</a>
        </div>
    </div>
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-hover table-striped">
                <thead>
                <tr>
                    <td>封面</td>
                    <th>书籍编号</th>
                    <th>书籍名字</th>
                    <th>书籍数量</th>
                    <th>书籍详情</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${requestScope.get('list')}">
                    <tr>
                        <td><img class="imgBox" src="../../static/img/${book.image}" alt="${book.image}"></td>
                        <td style="padding-top: 60px">${book.bookID}</td>
                        <td style="padding-top: 60px">${book.bookName}</td>
                        <td style="padding-top: 60px">${book.bookCount}</td>
                        <td style="padding-top: 60px">${book.detail}</td>
                        <td style="padding-top: 60px">
                            <a href="${pageContext.request.contextPath}/toUpdateBook/${book.bookID}">更改</a> |
                            <a href="${pageContext.request.contextPath}/del/${book.bookID}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>