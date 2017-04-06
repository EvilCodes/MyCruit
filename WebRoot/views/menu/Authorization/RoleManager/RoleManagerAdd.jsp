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
	<form class="formobj" method="post" action="/busi/RoleManagerAdd.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>角色名称:</th>
				<td><input type="text" name="name" datatype="zh2-6"
					errormsg="请输入正确的中文名" nullmsg="请填写信息！"><span
					class="Validform_checktip">中文名</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>用户组:</th>
				<td><select name="groups"><option value="1">管理员</option>
						<option value="2">招生人员</option></select><span class="Validform_checktip">用户组</span></td>
			</tr>
		</table>
		<span class="thidden"><input type="button" id="btn_sub"></span>
	</form>
</body>
</html>