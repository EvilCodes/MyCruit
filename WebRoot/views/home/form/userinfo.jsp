<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/views/menu/formBase.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>个人信息</title>
<style type="text/css">
table {
	width: 100%;
}

table tr {
	height: 30px;
}

tbale tr  th {
	text-align: right;
	height: 40px;
	border: solid #B8CCE2 1px;
	background-color: #F2F7FE;
	color: #5E7595;
	padding-right: 5px;
}

tbale tr td {
	text-align: left;
	border: solid #B8CCE2 1px;
	padding-left: 5px;
}
</style>
</head>
<body>
	<form class="formobj" method="post" action="/busi/common/UserUpd.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>用户角色:</th>
				<td>${role_info.name}</td>
			</tr>
			<%-- <c:if test="${enterprise_info ne null}">
				<tr>
					<th><span class="t_t">*</span>企业名称:</th>
					<td>${enterprise_info.name}</td>
				</tr>
			</c:if> --%>
			<tr>
				<th><span class="t_t">*</span>用户手机:</th>
				<td>${user_info.tel}</td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>用户姓名:</th>
				<td><input value="${user_info.username}" type="text" name="username" datatype="*2-6" errormsg="用户昵称在2-6个字符" /></td>
			</tr>
		</table>
		<span class="thidden"><input type="button" id="btn_sub"></span>
	</form>
</body>
</html>
