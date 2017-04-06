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
	var sendmessage;
	function findSendMessageByGnum(vargnum) {
		$.ajax({
			type : "POST",
			async : false,
			url : "/busi/common/findSendMessageByGnum.do",
			dataType : "json",
			data:{
				gnum:vargnum
			},
			success : function(result) {
				sendmessage = result;
			}
		});
	}

	$(function() {
		$("#gnum").blur(function() {
			var gnum = $("#gnum").val();
			findSendMessageByGnum(gnum);
			if(sendmessage!=null){
				$("#gnumvalue").html("QQ群号码已存在");
				$("#gnumvalue").css("background-color","red");
			}
		});
	});
</script>
</head>
<body>
	<form class="formobj" method="post"  action="<%=path %>/busi/SendMessageManagerAdd.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>QQ群号码:</th>
				<td><input id="gnum" type="text" name="gnum" datatype="n2-19"><span id="gnumvalue"
					class="Validform_checktip">QQ群号码</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>QQ群名称:</th>
				<td><input type="text" name="gname" datatype="*1-30" style="width:80%"><span
					class="Validform_checktip">QQ群名称</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>发送消息内容:</th>
				<td><textarea name="message" cols="70" rows="15"></textarea>
				<br/><span class="Validform_checktip">发送的内容</span></td>
			</tr>
		</table>
		<span class="thidden"><input type="button" id="btn_sub"></span>
	</form>
</body>
</html>