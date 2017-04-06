<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<div id="p" class="easyui-panel" style="background:#f2f2f2;" data-options="fit:true">
	<div style="width: 780px;height: 80px;">
		<%-- <div style="width: 80px;height: 80px;float: left;">
			<img alt="" src="<%=path%>/images/home/ucai_logo.png" height="80px" width="160px" style="margin: 5px 0px 0px 5px">
		</div> --%>
		<div style="width: 600px;height: 80px;float: left;">
			<h1 style="margin: 18px 0px 0px 5px;font-size: 28px;">优才学院Android教学部招生管理系统</h1>
		</div>
	</div>
	<!-- 右下菜单按钮 -->
	<div style="width: 600px; position: absolute;bottom: 5px;right: 10px;text-align: right;">
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="userinfo()" data-options="plain:true,iconCls:'icon-man'">用户:&nbsp;${user_info.username}&nbsp&nbsp<%-- <c:if test="${enterprise_info ne null}">企业:&nbsp${enterprise_info.name}&nbsp&nbsp</c:if> --%>角色:&nbsp;${role_info.name}
		</a>&nbsp&nbsp<a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-comturn'">控制面板</a> <a href="javascript:void(0)" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-exit'">注销</a>
		<div id="mm1">
			<div data-options="iconCls:'icon-man'" onclick="userinfo()" >个人信息</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-lock'" id="userupd">修改密码</div>
		</div>
		<div id="mm2">
			<div data-options="iconCls:'icon-help'" id="qwe">关于</div>
			<div class="menu-sep"></div>
			<div data-options="iconCls:'icon-redo'" id="exe">退出系统</div>
		</div>
	</div>
</div>
<!-- 右上滚动 -->
<%-- <div class="scrolldiv">
	<div class="scrollimg">
		<img src="<%=path%>/images/home/noticeico.gif" alt="图挂了" title="公告">
	</div>
	<div class="scrollcontent" id="scrollcontent">
		<ul>
			<li><a href="#">今天下午拆解厂开会</a></li>
			<li><a href="#">陈主任在一楼会议室捡到1美元</a></li>
			<li><a href="#">龙院长问什么是java app</a></li>
			<li><a href="#">今天饿了吗?</a></li>
		</ul>
	</div>
</div> --%>
<!-- 右下菜单按钮 -->
