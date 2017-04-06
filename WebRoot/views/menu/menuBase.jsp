<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" href="<%=path%>/plug-in/easyui/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=path%>/plug-in/easyui/themes/icon.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/plug-in/lhgDialog/skins/iblue.css" type="text/css">
<script type="text/javascript" src="<%=path%>/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/tools/curdtools.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	color: #f60;
}

.p {
	background: #F9F9F9;
}

.p form {
	margin: 0px;
	padding: 0px;
}

.p p {
	width: 100%;
	height: 32px;
	line-height: 32px;
	margin: 0px;
	padding: 0px;
}

.p .spanl {
	float: left;
	margin: 0px 0px 0px 8px;
}
.p .spanl a{
   margin-top: 2px;
}
.p .spanr {
	float: right;
	margin-right: 6px;
}

.p input {
	vertical-align: middle;
}

.p select {
	height: 22px;
	width: 80px;
}

.p input {
	width: 120px;
	padding: 2px 2px 2px 2px;
}
.p table tr th{
    width:65px;
   	font-size: 14px;
   	font-weight: normal;
   	height: 32px;
   	line-height: 32px;
}

.p table tr td{
	height: 32px;
   	line-height: 32px;
}
</style>
</head>
<body>
	<div id="menu" class="easyui-menu" style="width:150px;display: none;">
		<div id="m-refresh">刷新数据</div>
		<div id="m-close">关闭</div>
	</div>
	<script type="text/javascript">
		var ff;
		$(function() {
			ff = $("#ff").form();
			$("#m-refresh").bind("click", function() {
				datagrid.datagrid('reload');
			});
			$("#m-close").bind("click", function() {
				$("#menu").css("display", "none");
			});
		});
		function initform() {
			var url = location.href.substring(0, location.href.lastIndexOf(".")) + "/";
			if (url.indexOf("-") > -1) {
				form = url.substring(0, url.indexOf("-")) + "/";
			} else {
				form = url;
			}
		}
		var form = "";
		var botton = "";
		$(document).ready(function() {
			initform();
			$.ajax({
				type : "POST",
				url : "/busi/common/getButton.do",
				data : {
					code : request("code")
				},
				dataType : "json",
				success : function(json) {
					var str = '';
					if (json.success) {
						botton = json.obj;
						$.each(json.obj, function(i, data) {
							str += '<a id="bt' + i + '" href="javascript:void(0)" onclick="' + data.link + '()" iconCls="icon-edit" plain="true">' + data.name + '</a>';
						});
						$('#ff').html($("#ff").html() + '<p><span class="spanl">' + str + '</span></p>');
						$.each(json.obj, function(i, data) {
							if (data.link.indexOf("Add") > -1) {
								$("#bt" + i).linkbutton({
									iconCls : 'icon-add'
								});
							}
							if (data.link.indexOf("Upd") > -1) {
								$("#bt" + i).linkbutton({
									iconCls : 'icon-edit'
								});
							}
							if (data.link.indexOf("Del") > -1) {
								$("#bt" + i).linkbutton({
									iconCls : 'icon-remove'
								});
							}
							if (data.link.indexOf("Search") > -1) {
								$("#bt" + i).linkbutton({
									iconCls : 'icon-search'
								});
							}
							if (data.link.indexOf("Print") > -1) {
								$("#bt" + i).linkbutton({
									iconCls : 'icon-print'
								});
							}

							if (data.link.indexOf("Upds") > -1 || data.link.indexOf("Dels") > -1) {
								$("#bt" + i).linkbutton({
									iconCls : 'icon-edit'
								});
							}
						});
					}
				}
			});
		});
		function request(paras) {
			var url = location.href;
			var paraString = url.substring(url.indexOf("?") + 1, url.length).split("&");
			var paraObj = {};
			for (var i = 0; j = paraString[i]; i++) {
				paraObj[j.substring(0, j.indexOf("=")).toLowerCase()] = j.substring(j.indexOf("=") + 1, j.length);
			}
			var returnValue = paraObj[paras.toLowerCase()];
			if (typeof (returnValue) == "undefined") {
				return "";
			} else {
				return decodeURI(returnValue);
			}
		}
		function undo() {
			var rows = datagrid.datagrid('getSelections');
			if (rows.length <= 0)
				$.messager.alert('提示', '亲,您没有选中任何数据!', 'error');
			datagrid.datagrid('unselectAll');
		}
		function gb(x) {
			for (var i = 0; i < botton.length; i++) {
				if (x == botton[i].link) {
					return botton[i];
				}
			}
		}
	</script>
</body>
</html>
