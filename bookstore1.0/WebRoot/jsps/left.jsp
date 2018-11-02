<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			font-size:10pt;
			text-align: center;
		}
		div {
			background: #87CEFA; 
			margin: 3px; 
			padding: 3px;
		}
		a {
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>
<div>
<<<<<<< HEAD
	<a href="<c:url value='/BookServlet?method=findAll'/>">全部分类</a>
=======
	<a href="${pageContext.request.contextPath }/findAllBook.action">全部分类</a>
>>>>>>> 用户登录，图书查询，图书加载，购物车等功能的完成
</div>
	<c:forEach items="${categoryList }" var="category">
		


<div>
<<<<<<< HEAD
	<a href="<c:url value='/BookServlet?method=findByCategory&cid=${category.cid }'/>">${category.cname }</a>
=======
	<a href="${pageContext.request.contextPath }/findByCategory.action?cid=${category.cid }">${category.cname }</a>
>>>>>>> 用户登录，图书查询，图书加载，购物车等功能的完成
</div>
	</c:forEach>
  </body>
</html>
