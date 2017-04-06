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
	<form class="formobj" method="post" action="/busi/StudentManagerAdd.do">
		<table class="vtb">
			<tr>
				<th><span class="t_t">*</span>姓名:</th>
				<td><input type="text" name="name" datatype="*1-20"
					errormsg="请输入真实姓名，长度2-5个汉字" nullmsg="请填写信息！"><span
					class="Validform_checktip">真实姓名，例如张三</span></td>
			</tr>
			<tr>
				<th><span class="t_t">*</span>QQ号码:</th>
				<td><input id="qq" type="text" name="qq" datatype="n8-12"
					errormsg="请输入正确的QQ号码" nullmsg="请填写信息！"><span id="qqvalue"
					class="Validform_checktip">QQ号码，例如632977922</span></td>
			</tr>
			<tr>
				<th>电话号码:</th>
				<td><input type="text" name="mobile"><span
					class="Validform_checktip">电话号码，例如18601942391</span></td>
			</tr>
			<!-- <tr>
				<th><span class="t_t">*</span>学生状态:</th>
				<td><select name="status">
						<option value="1">考虑中</option>
						<option value="2">已入学</option>
						<option value="3">已放弃</option>
				</select> <span class="Validform_checktip">选择当前的学生状态（考虑中，已入学，已放弃，默认考虑中）</span></td>
			</tr> -->
			<!-- 			<tr>
				<th><span class="t_t">*</span>学生状态:</th>
				<td>考虑中</td>
			</tr> -->
			<tr>
				<th><span class="t_t">*</span>转化设置:</th>
				<td><select name="conver">
						<option value="1">我自己转化</option>
						<option value="0">任意人可转化</option>
				</select> <span class="Validform_checktip">选择"我可以转化",则有30天的转化时间。</span></td>
			</tr>
		</table>
		<span class="thidden"><input type="button" id="btn_sub"></span>
	</form>
</body>
</html>