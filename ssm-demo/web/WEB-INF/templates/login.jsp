<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>

<h1>登录页面</h1>
<hr>
<body>
<form action="/login" method="post">
    <spring:message code="userName"/>：<input type="text" name="name">${errorInfo.name}<br>
    <spring:message code="password"/>：<input type="password" name="password">${errorInfo.password} <br>
    <spring:message code="telephone"/>：<input type="text" name="telephone">${errorInfo.telephone} <br>
    <spring:message code="email"/>：<input type="text" name="email">${errorInfo.email} <br>
    <input type="submit" value="提交">
</form>
Language:
<a href="?locale=zh_CN">中文</a>&nbsp;&nbsp;
<a href="?locale=en_US">英文</a><br/>
当前语言: ${pageContext.response.locale}
</body>
</html>