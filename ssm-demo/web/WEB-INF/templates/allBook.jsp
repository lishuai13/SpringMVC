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
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <h3>
                我的书店
            </h3>
        </div>
    </div>
    <hr>
    <div class="row-fluid">
        <div class="span6">
            <button class="btn btn-large" type="button"><a href="${pageContext.request.contextPath}/toAddBook">新增</a></button>
            <button class="btn btn-large" type="button"><a href="${pageContext.request.contextPath}/goOut">退出</a></button>
            <button class="btn btn-large" type="button"><a href="${pageContext.request.contextPath}/error">触发异常</a></button>
            <form class="form-search" style="float: right" method="post" action="/find">
                <input class="input-medium search-query" type="text" placeholder="请输入书名等相关信息" name="info"/> <button type="submit" class="btn">查找</button>
            </form>
        </div>
    </div>
    <hr>
    <div class="row-fluid">
        <div class="span12">
            <table class="table">
                <thead>
                <tr>
                    <th>
                        封面
                    </th>
                    <th>
                        书籍编号
                    </th>
                    <th>
                        书籍名字
                    </th>
                    <th>
                        书籍数量
                    </th>
                    <th>
                        书籍详情
                    </th>
                    <th>
                        操作
                    </th>
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
    <hr>
    <div class="row-fluid" style="text-align:center;font-size: 20px">
        <div class="span12">
            <div class="pagination pagination-centered">
                <ul>
                    <li>
                        <a href="${pageContext.request.contextPath}/allBook/-1" style="padding: 10px">上一页</a>
                        <a href="${pageContext.request.contextPath}/allBook/1" style="padding: 10px">1</a>
                        <a href="${pageContext.request.contextPath}/allBook/2" style="padding: 10px">2</a>
                        <a href="${pageContext.request.contextPath}/allBook/3" style="padding: 10px">3</a>
                        <a href="${pageContext.request.contextPath}/allBook/4" style="padding: 10px">4</a>
                        <a href="${pageContext.request.contextPath}/allBook/5" style="padding: 10px">5</a>
                        <a href="${pageContext.request.contextPath}/allBook/10" style="padding: 10px">下一页</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>