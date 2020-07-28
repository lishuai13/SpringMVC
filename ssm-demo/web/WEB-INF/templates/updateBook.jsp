<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改信息</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/updateBook" enctype="multipart/form-data" method="post">
        书籍封面：<input type="file" name="file"/><img class="imgBox" src="../../static/img/${book.image}" alt="${book.image}"><br><br><br>
        书籍编号：<input type="text" name="bookID" value="${book.bookID}"/><br><br><br>
        书籍名称：<input type="text" name="bookName" value="${book.bookName}"/><br><br><br>
        书籍数量：<input type="text" name="bookCount" value="${book.bookCount}"/><br><br><br>
        书籍详情：<input type="text" name="detail" value="${book.detail}"/><br><br><br>
        <input type="hidden" name="image" value="${book.image}"/>
        <input type="submit" value="提交"/>
    </form>
</div>