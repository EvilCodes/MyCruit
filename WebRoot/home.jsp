<%@ page language="java" import="java.util.*" pageEncoding="UTf-8"%>
<%@ include file="views/home/init.jsp"%> 
<html>
<head>
<title>优才学院Android教学部招生管理系统</title>
</head>
<body class="easyui-layout">
	<!--头部 -->
	<div data-options="region:'north',split:true,collapsible:false,href:'views/home/top.jsp'" style="height: 90px;background-color:#f2f2f2;"></div>
	<!-- 左 -->
	<div data-options="region:'west',split:true,collapsible:false,href:'views/home/left.jsp'" style="width: 200px;" class="easyui-accordion"></div>
	<!-- 内容数据 -->
	<div data-options="region:'center',collapsible:false" class="easyui-tabs tabs-container" id="tabs"></div>
	<!-- 下部 -->
	<div data-options="region:'south',split:true,collapsible:false,href:'views/home/foot.jsp'" style="height: 24px;background-color:#f2f2f2;"></div>
</body>
</html>
