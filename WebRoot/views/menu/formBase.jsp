<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title></title>
<link href="<%=path%>/plug-in/Validform/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/Validform/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/Validform/js/Validform_Datatype.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/Validform/js/passwordStrength-min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/My97DatePicker/WdatePicker.js"></script>
<%-- <script type="text/javascript" src="<%=path%>/plug-in/swfupload/swfuploadv2.2-min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/swfupload/Validform.swfupload.handler.js"></script> --%>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}

.t_t {
	color: red;
}

.thidden {
	display: none;
}

.formobj {
	margin: 0px;
	padding: 0px;
}
/* .vtb table {
	border-collapse: collapse;
	border: none;
	width: 200px;
} */
.vtb tr th {
	text-align: right;
	border: solid #B8CCE2 1px;
	background-color: #F2F7FE;
	color: #5E7595;
	padding-right: 5px;
	height: 40px;
}

.vtb tr td {
	border: solid #B8CCE2 1px;
	padding-left: 5px;
}

.vtb select {
	padding: 1px 1px 1px 1px;
	font-size: 18px;
	width: 160px;
	font-size: 18px;
}

.vtb input {
	vertical-align: middle;
}

.vtb input[type="password"] {
	padding: 5px 5px 5px 5px;
	width: 160px;
}

.vtb input[type="text"] {
	padding: 5px 5px 5px 5px;
	width: 160px;
}

.vtb input[type="radio"] {
	
}

.vtb input[type="checkbox"] {
	
}

.passwordStrength b {
	font-weight: normal;
}

.passwordStrength b,.passwordStrength span {
	display: inline-block;
	vertical-align: middle;
	line-height: 16px;
	line-height: 18px\9;
	height: 16px;
}

.passwordStrength span {
	width: 45px;
	text-align: center;
	background-color: #d0d0d0;
	border-right: 1px solid #fff;
}

.passwordStrength .last {
	border-right: none;
}

.passwordStrength .bgStrength {
	color: #fff;
	background-color: #71b83d;
}
</style>
<script type="text/javascript">
	var win = frameElement.api.opener;
	var formdata = "";
	$(function() {
		formdata = win.datagrids;
		$(".formobj").Validform({
			tiptype : function(msg, o, cssctl) {
				//msg：提示信息;
				//o:{obj:*,type:*,curform:*}, obj指向的是当前验证的表单元素（或表单对象），type指示提示的状态，值为1、2、3、4， 1：正在检测/提交数据，2：通过验证，3：验证失败，4：提示ignore状态, curform为当前form对象;
				//cssctl:内置的提示信息样式控制函数，该函数需传入两个参数：显示提示信息的对象 和 当前提示的状态（既形参o中的type）;
				if (!o.obj.is("form")) {//验证表单元素时o.obj为该表单元素，全部验证通过提交表单时o.obj为该表单对象;
					var objtip = o.obj.siblings(".Validform_checktip");
					cssctl(objtip, o.type);
					objtip.text(msg);
				}
			},
			btnSubmit : "#btn_sub",
			btnReset : "#btn_reset",
			ajaxPost : true,
			datatype : {
				"zh2-6" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,6}$/,
				"db" : /^[-\+]?\d+(\.\d+)?$/
			},
			usePlugin : {
				passwordstrength : {
					minLen : 6,
					maxLen : 18
				}
			},
			callback : function(data) {
				var win = frameElement.api.opener;
				if (data.success == true) {
					frameElement.api.close();
					win.tip(data.msg);
				} else {
					if (data.responseText == '' || data.responseText == undefined) {
						alert(data.msg);
					} else {
						$(".formobj").html(data.responseText);
					}
					return false;
				}
				win.reloadTable();
			}
		});
	});
	//更新时候赋值
	function valform(name, value, type) {
		if (type == "" || type == "text") {
			$("input[name='" + name + "']").val(value);
			return;
		}
		if (type == "checkbox") {
			if (value == "1") {
				$("input[name='" + name + "']").attr("checked", true);
			} else if (value == "0") {
				$("input[name='" + name + "']").attr("checked", false);
			}
			return;
		}
		if (type == "" || type == "textarea") {
			$("textarea[name='" + name + "']").val(value);
			return;
		}
		if (type == "radio") {
			// $("input[name='" + name + "'+']:eq(0)'").attr("checked", 'checked');
			// $("input[name=isenable]:eq(0)").attr("checked",'checked');
			$("input:radio[value='" + value + "']").attr('checked', 'true');
			return;
		}
		// 资源类型
		if (type == "select") {
			$("select[name='" + name + "'] option[value='" + value + "']").attr("selected", true);
			/* $("#select").val(value); */
			return;
		}
	}
</script>
</head>
<body>
</body>
</html>
