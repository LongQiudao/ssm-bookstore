<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>注册</h1>
<p style="color: red; font-weight: 900">${msg }</p>
<p style="color: red; font-weight: 900">
	<c:forEach items="${errors}" var="errors">
		${errors.defaultMessage }
	</c:forEach>
</p>
<form action="${pageContext.request.contextPath }/regist.action" method="post">
	<input type="hidden" name="method" value="regist"/>
	
	用户名：<input type="text" name="username" value="${form.username }"/>
	<br/>
	密　码：<input type="password" name="password"value="${form.password}"/>
	<br/>
	邮　箱：<input type="text" name="email" value="${form.email }"/>
	<br/>
	<input type="submit" value="注册"/>
</form>
  </body>
</html>
