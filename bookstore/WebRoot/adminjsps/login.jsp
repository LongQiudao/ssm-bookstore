<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
<h1>管理员登录页面</h1>
<hr/>
  <p style="font-weight: 900; color: red">${msg }</p>
<form action="<c:url value='/AdminServlet'/>" method="post">
<input type="hidden" name="method" value="login"/>
	管理员账户：<input type="text" name="adminname" value="${form.adminname }"/>
	<span style="color: red; font-weight: 900">${errors.adminname }</span><br/>
	密　　　码：<input type="password" name="password"value="${form.password }"/>
	<span style="color: red; font-weight: 900">${errors.password }</span><br/>
	<input type="submit" value="管理员登录"/>
</form>
  </body>
</html>
