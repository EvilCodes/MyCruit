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
		valform("status", formdata[0].status, "select");
	});
</script>
</head>
<body>
	<form class="formobj" method="post" action="/busi/StudentManagerUpdStatus.do">
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
				<td><input type="text" name="mobile" ><span
					class="Validform_checktip">电话号码，例如18601942391</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>学生状态:</th>
				<td><select name="status">
						<option value="2">录入审核通过</option>
						<option value="3">录入审核驳回</option>
						<option value="5">转化审核通过</option>
						<option value="6">转化审核驳回</option>
				</select> <span class="Validform_checktip">选择当前的学生状态（通过审核，驳回审核）</span></td>
			</tr>
		</table>
		<span class="thidden">
			<input type="hidden" name="id" type="text">
			<input type="hidden" name="uid" type="text">
			<input type="hidden" name="conver" type="text">
			<input type="hidden" name="days" type="text">
			<input type="button" id="btn_sub">
		</span>
	</form>
</body>
</html>