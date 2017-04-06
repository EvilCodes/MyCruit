<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../formBase.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<style type="text/css">
.vtb {
	width: 100%;
}

.vtb tr td {
	width: 80%;
}

.vtb tr th {
	width: 20%;
}
</style>
</head>
<body>
	<form class="formobj" method="post" action="/busi/ResourceManagerAdd.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>资源类型:</th>
				<td><select name="level">
						<option value="1">一级菜单</option>
						<option value="2">二级菜单</option>
						<option value="3">按钮</option>
				</select> <span class="Validform_checktip">选择需要的资源类型（模块、菜单或按钮）</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>名称:</th>
				<td><input type="text" name="name" datatype="zh2-10"
					errormsg="请输入正确的名称" nullmsg="请填写信息！"><span
					class="Validform_checktip">模块、菜单或按钮名称</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>链接:</th>
				<td><input type="text" name="link" datatype="s1-30"
					errormsg="请输入正确的链接地址" nullmsg="请填写信息！"><span
					class="Validform_checktip">链接地址，如RoleManager</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>顺序值:</th>
				<td><input type="text" name="orderby" datatype="n1-10"
					errormsg="请输入正确的顺序值" nullmsg="请填写信息！"><span
					class="Validform_checktip">顺序值决定菜单或按钮的顺序</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>父菜单ID:</th>
				<td><input type="text" name="superid" datatype="n1-10"
					errormsg="请输入正确的父菜单ID" nullmsg="请填写信息！"><span
					class="Validform_checktip">该值决定菜单或按钮的所属</span></td>
			</tr>
			<tr>
				<th>是否可用:</th>
				<td><input type="radio" name="status" value="Y"
					checked="checked">可用 <input type="radio" name="status"
					value="N">禁用</td>
			</tr>
		</table>
		<span class="thidden"><input type="button" id="btn_sub"></span>
	</form>
</body>
</html>