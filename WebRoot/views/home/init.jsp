<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" href="<%=path%>/plug-in/easyui/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="<%=path%>/plug-in/easyui/themes/icon.css" type="text/css"></link>
<link href="<%=path%>/plug-in/lhgDialog/skins/iblue.css">
<script type="text/javascript" src="<%=path%>/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/tools/easyui-tabs.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/lhgDialog/lhgdialog.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug-in/tools/curdtools.js"></script>
<style type="text/css">
body {
	font-family: Microsoft YaHei;
	font-size: 14px;
}

.as {
	height: 20px;
	margin: 2px 2px 2px 2px;
	padding-left: 20px;
	line-height: 20px;
}

.as span {
	margin: 1px 0px 1px 0px;
}

.as span a {
	margin-left: 2px;
	text-decoration: none;
	font-size: 14px;
	color: #444444;
	text-decoration: none;
}

/* 右上滚动CSS */
.scrolldiv {
	position: absolute;
	top: 0px;
	right: 5px;
}

.scrollimg {
	float: left;
	padding-top: 1px;
}

.scrollcontent {
	float: right;
	height: 25px; /* 必要元素 */
	line-height: 25px;
	overflow: hidden; /* 必要元素 */
}

.scrollDiv li {
	height: 25px;
}

ul,li {
	list-style-type: none;
	padding: 0px;
	margin: 0px;
}

.as_click {
	background-color: #DBEAF9;
}
</style>
<body>
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate" data-options="iconCls:'icon-reload'">刷新页面</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose" data-options="iconCls:'icon-remove'">关闭</div>
		<div id="mm-tabcloseleft" data-options="iconCls:'icon-undo'">左侧的全部</div>
		<div id="mm-tabcloseright" data-options="iconCls:'icon-redo'">右侧的全部</div>
		<div id="mm-tabcloseother" data-options="iconCls:'icon-no'">关闭其他</div>
		<div id="mm-tabcloseall" data-options="iconCls:'icon-cancel'">关闭全部</div>
	</div>
</body>
<script type="text/javascript">
	/* 右上滚动菜单控制 */
	/* 	function AutoScroll(obj) {
	 $(obj).find("ul:first").animate({
	 marginTop : "-25px"
	 }, 500, function() {
	 $(this).css({
	 marginTop : "0px"
	 }).find("li:first").appendTo(this);
	 });
	 } */

	$(document).ready(function() {
		initbutton();//初始化按钮
		addhomeTab("主页", "views/home/news.jsp");
		/* setInterval('AutoScroll("#scrollcontent")', 3000); */
	});
	function addT(y, x) {
		var url = 'views/menu/' + x.substring(0, x.indexOf('&')) + '.jsp?code=' + x.substring(x.indexOf('&') + 1, x.length);
		addTab(y, url);
	}
	function userinfo() {
		createwindow('个人信息', '/views/home/form/userinfo.jsp', 400, 200);
	}
	function initbutton() {
		$(".as").live("mouseover", function() {
			$(this).css("background-color", "#DBEAF9");
			$(this).css("border", "dotted 1px #2A74B6");
		});
		$(".as").live("mouseout", function() {
			$(this).css("background-color", "");
			$(this).css("border", "");
		});
		$(".as").live("click", function() {
			$(".as").removeClass("as_click");
			$(this).addClass("as_click");
		});
		/* 	$(".userinfo").live("click", function() {
				createwindow('个人信息', '/views/home/form/userinfo.jsp', 400, 200);
			}); */
		$("#userupd").live("click", function() {
			createwindow('用户密码修改', '/views/home/form/userupdpassword.jsp', 600, 300);
		});
		$("#qwe").live("click", function() {
			$.dialog({
				title : '关于,5秒后自动关闭！',
				time : 5,
				content : '<font style="font-size: 14px;">技术支持: Android教学部-陈军老师<br>联系电话: 18601942391<br>联系QQ: 632977922<br>邮箱: chenjun@ucai.cn<br>系统版本: v1.3</font>',
				cancel : false,
				lock : true,
				max : false,
				min : false
			});
		});
		$("#exe").live("click", function() {
			$.dialog({
				title : '退出',
				content : '您真的要退出么亲！',
				ok : function() {
					location.href = "/common/quit.do";
					return false;
				},
				cancelVal : '关闭',
				cancel : true,
				lock : true,
				max : false,
				min : false
			});
		});
	}
</script>