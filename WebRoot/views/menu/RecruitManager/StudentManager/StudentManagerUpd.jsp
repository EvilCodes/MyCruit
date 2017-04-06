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
<script type="text/javascript">
	$(function() {
		valform("id", formdata[0].id, "");
		valform("name", formdata[0].name, "");
		valform("qq", formdata[0].qq, "");
		valform("mobile", formdata[0].mobile, "");
		valform("uid", formdata[0].uid, "");
		valform("conver", formdata[0].conver, "");
		valform("days", formdata[0].days, "");
		valform("status", formdata[0].status, "");
	});
</script>
</head>
<body>
	<form class="formobj" method="post" action="/busi/StudentManagerUpd.do">
		<span>注意：只能修改自己录入的数据，并且数据需要是录入状态（也即审核之后不再能修改）</span>
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>姓名:</th>
				<td><input type="text" name="name" datatype="*1-20"
					errormsg="请输入真实姓名，长度2-5个汉字" nullmsg="请填写信息！"><span
					class="Validform_checktip">真实姓名，例如张三</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>QQ号码:</th>
				<td><input type="text" name="qq" datatype="n8-12"
					errormsg="请输入正确的QQ号码" nullmsg="请填写信息！"><span
					class="Validform_checktip">QQ号码，例如632977922</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>电话号码:</th>
				<td><input type="text" name="mobile"><span
					class="Validform_checktip">电话号码，例如18601942391</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>转化设置:</th>
				<td><select name="conver">
						<option value="1">我自己转化</option>
						<option value="0">任意人可转化</option>
				</select> <span class="Validform_checktip">选择"我可以转化",则有30天的转化时间。</span></td>
			</tr>
		</table>
		<span class="thidden">
			<input type="hidden" name="id" type="text">
			<input type="hidden" name="uid" type="text">
			<input type="hidden" name="status" type="text">
			<input type="hidden" name="days" type="text">
			<input type="button" id="btn_sub">
		</span>
	</form>
</body>
</html>