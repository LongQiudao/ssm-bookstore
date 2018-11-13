<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
<script language="javascript">
var bar1 = new Q6MenuBar("bar1", "破益达网络图书商城");
function load() {
	bar1.colorStyle = 3  ;
	bar1.config.imgDir = "<c:url value='/menu/img/'/>";
	bar1.config.radioButton=false;
	bar1.add("分类管理", "查看分类", "${pageContext.request.contextPath }/findCategory.action", "body");
	bar1.add("分类管理", "添加分类", "<c:url value='/adminjsps/admin/category/add.jsp'/>", "body");

	bar1.add("图书管理", "查看图书", "${pageContext.request.contextPath }/findBook.action", "body");
	bar1.add("图书管理", "添加图书", "${pageContext.request.contextPath }/loadAddBook.action", "body");

	bar1.add("订单管理", "所有订单", "${pageContext.request.contextPath }/findAllOrder.action", "body");
	bar1.add("订单管理", "未付款订单", "${pageContext.request.contextPath }/findUnpaid.action", "body");
	bar1.add("订单管理", "已付款订单", "${pageContext.request.contextPath }/findPaid.action", "body");
	bar1.add("订单管理", "未收货订单", "${pageContext.request.contextPath }/findUnreceive.action", "body");
	bar1.add("订单管理", "已完成订单", "${pageContext.request.contextPath }/findSuccess.action", "body");

	var d = document.getElementById("menu");
	d.innerHTML = bar1.toString();
}
</script>

</head>

<body onload="load()" style="margin: 0px; background: rgb(254,238,189);">
<div id="menu"></div>

</body>
</html>
