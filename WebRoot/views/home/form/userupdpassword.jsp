<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/views/menu/formBase.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改密码</title>
<style type="text/css">
.vtb {
	width: 100%;
}

.vtb tr td {
	width: 75%;
}

.vtb tr th {
	width: 25%;
}
</style>
</head>
<body>
	<form class="formobj" method="post" action="/busi/common/UserUpdPassword.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>密码:</th>
				<td><input type="password" name="passworda" datatype="*6-28" errormsg="密码至少6个字符,最多28个符！" /><span class="Validform_checktip">密码</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>请输入新密码:</th>
				<td><input type="password" name="password" plugin="passwordStrength" datatype="*6-28" errormsg="密码至少6个字符,最多28个符！" /> <span class="passwordStrength"> <span>弱</span><span>中</span><span class="last">强</span>
				</span><span class="Validform_checktip">密码长度在6-28位字符</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>再次输入新密码:</th>
				<td><input type="password" name="repassword" recheck="password" datatype="*6-28" errormsg="两次输入的密码不一致！" /><span class="Validform_checktip"> 重复密码</span></td>
			</tr>
		</table>
		<span class="thidden"><input type="button" id="btn_sub"></span>
	</form>
</body>
</html>
