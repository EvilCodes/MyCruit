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
	var student;
	function getStudentByQQ(varqq) {
		$.ajax({
			type : "POST",
			async : false,
			url : "/busi/common/findStudentByQQ.do",
			dataType : "json",
			data:{
				qq:varqq
			},
			success : function(result) {
				student = result;
			}
		});
	}

	$(function() {
		$("#qq").blur(function() {
			var qq = $("#qq").val();
			getStudentByQQ(qq);
			if(student!=null){
				$("#qqvalue").html("QQ号码已存在");
				$("#qqvalue").css("background-color","red");
			}
		});
	});
</script>
</head>
<body>
	<form class="formobj" method="post" action="/busi/TrainTicketsManagerAdd.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>出发地:</th>
				<td><input type="text" name="tfrom" datatype="zh1-10"
					errormsg="请输入真实地名，长度1-10个汉字" nullmsg="请填写信息！"><span
					class="Validform_checktip">出发地，比如北京</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>目的地:</th>
				<td><input id="tto" type="text" name="tto" datatype="zh1-10"
					errormsg="请输入正确的目的地，长度1-10个汉字" nullmsg="请填写信息！"><span id="qqvalue"
					class="Validform_checktip">目的地</span></td>
			</tr>
			<tr>
				<th>出发时间:</th>
				<td><input type="text" name="ttime"><span
					class="Validform_checktip">出发时间，比如2016-12-12</span></td>
			</tr>
		</table>
		<span class="thidden"><input type="button" id="btn_sub"></span>
	</form>
</body>
</html>