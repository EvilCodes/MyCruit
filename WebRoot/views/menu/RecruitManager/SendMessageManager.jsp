<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../menuBase.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
</head>
<body>
	<div class="p">
		<form id="ff" method="post">
			<p>
				<span class="spanr"> QQ群号码:<input type="text" name="gnum">&nbsp;&nbsp;
				QQ群名称:<input type="text" name="gname">&nbsp;&nbsp;
				消息内容:<input type="text" name="message">&nbsp;&nbsp;
				<a id="btn" onclick="btn()" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;<a id="btn"
					onclick="reload()" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'">重置 </a></span>
			</p>
		</form>
	</div>
	<table id="tt">
	</table>
	<script type="text/javascript">
		var users;
		function getAllUsers() {
			$.ajax({
				type : "POST",
				async : false,
				url : "/busi/common/findAllUsers.do",
				dataType : "json",
				success : function(json) {
					users = json;
				}
			});
		}
		var datagrid;
		$(function() {
			getAllUsers();
			datagrid = $("#tt").datagrid({
				url : '<%=path%>/busi/SendMessageManager.do',
				/* 	fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动 */
				pagination : true,// 分页控件
				pageSize : 20,//默认显示大小
				fit : true,// 自动大小
				rownumbers : true,// 行号
				singleSelect : true,//只能选中一行
				pageList : [ 10, 20, 50 ],
				sortName : 'id',//排序
				sortOrder : 'asc',// asc desc
				idField : 'id',
				columns : [ [ {
					field : 'gnum',
					title : 'QQ群号码',
					width : 100,
					sortable : true
				}, {
					field : 'gname',
					title : 'QQ群名称',
					width : 200,
					sortable : true
				}, {
					field : 'message',
					title : '消息',
					width : 450,
					sortable : true
				},{
					field : 'mtime',
					title : '时间',
					width : 200,
					sortable : true,
					formatter : function(value, row, index) {
						return getLocalTime(value);
					}
				},{
					field : 'uid',
					title : '发布人',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						for (var i = 0; i < users.length; i++) {
							if (users[i].id == value) {
								return users[i].username;
							}
						}
						return "未知";
					}
				} ] ],
				toolbar : ".p",
				onRowContextMenu : function(e, rowIndex, rowData) { // 右击事件
					e.preventDefault(); // 阻止浏览器自带的右键菜单弹出
					$('#menu').menu('show', {
						left : e.pageX, // 弹出窗口的方位坐标
						top : e.pageY
					});
				}
			});
		});
		
		function getLocalTime(x) {
			var now = new Date(x);
			var year = now.getYear() + 1900;
			var month = now.getMonth() + 1;
			var date = now.getDate();
			var hour = now.getHours();
			var minute = now.getMinutes();
			var second = now.getSeconds();
			return year + "-" + month + "-" + date + " " + hour + ":" + minute
					+ ":" + second;
		}
		
		function btn() {
			datagrid.datagrid("load", {
				gnum : ff.find('[name="gnum"]').val(),
				gname : ff.find('[name="gname"]').val(),
				message : ff.find('[name="message"]').val()
			});
		}
		function reload() {
			ff[0].reset();
			btn();
		}
		function SendMessageManagerUpd() {
			updateobj(gb("SendMessageManagerUpd").name,
					gb("SendMessageManagerUpd").link, "", "");
		}
		function SendMessageManagerAdd() {
			addobj(gb("SendMessageManagerAdd").name,
					gb("SendMessageManagerAdd").link, "", "");
		}
		function SendMessageManagerDel() {
			delobj("/busi/SendMessageManagerDel.do");
		}
		function SendMessageManagerSearch() {

		}
	</script>
</body>
</html>
