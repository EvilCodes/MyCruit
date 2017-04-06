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
		valform("gnum", formdata[0].gnum, "");
		valform("gname", formdata[0].gname, "");
		valform("message", formdata[0].message, "textarea");
		valform("uid", formdata[0].uid, "");
	});
</script>
</head>
<body>
	<form class="formobj" method="post"  action="<%=path %>/busi/SendMessageManagerUpd.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>QQ群号码:</th>
				<td><input type="text" name="gnum" datatype="n2-19"><span
					class="Validform_checktip">QQ群号码</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>QQ群名称:</th>
				<td><input type="text" name="gname" datatype="s2-30" style="width:80%"><span
					class="Validform_checktip">QQ群名称</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>发送消息内容:</th>
				<td><textarea name="message" datatype="*2-499"  cols="70" rows="15"></textarea>
				<br/><span class="Validform_checktip">发送的内容</span></td>
			</tr>
		</table>
		<span class="thidden">
			<input type="hidden" name="id" type="text">
			<input type="hidden" name="uid" type="text">
			<input type="button" id="btn_sub">
		</span>
	</form>
</body>
</html>