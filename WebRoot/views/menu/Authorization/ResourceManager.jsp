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
				<span class="spanr"> 名称:<input type="text" name="name">&nbsp;&nbsp;<a id="btn" onclick="btn()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;<a id="btn" onclick="reload()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置 </a>
				</span>
			</p>
		</form>
	</div>
	<table id="tt">
	</table>
	<script type="text/javascript">
		var vtype = [ "是", "否" ];
		var datagrid;
		$(function() {
			datagrid = $("#tt").datagrid({
				url : '/busi/ResourceManager.do',
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
					field : 'id',
					title : '资源ID',
					width : 200,
					sortable : true
				}, {
					field : 'level',
					title : '资源类型',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						if (value == 1) {
							return "一级菜单";
						} else if (value == 2) {
							return "二级菜单";
						} else if (value == 3) {
							return "按钮";
						} else {
							return "-";
						}
					}
				}, {
					field : 'name',
					title : '资源名称',
					width : 200,
					sortable : true
				}, {
					field : 'link',
					title : '资源链接',
					width : 220,
					sortable : true
				}, {
					field : 'orderby',
					title : '资源顺序',
					width : 150,
					sortable : true
				}, {
					field : 'superid',
					title : '父资源编号',
					width : 150,
					sortable : true
				}, {
					field : 'status',
					title : '是否可用',
					width : 150,
					sortable : true,
					formatter : function(value, row, index) {
						if (value == "y") {
							return "是";
						} else {
							return "否";
						}
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
		function btn() {
			datagrid.datagrid("load", {
				name : ff.find('[name="name"]').val(),
				code : ff.find('[name="code"]').val()
			});
		}
		function reload() {
			ff[0].reset();
			btn();
		}
		function ResourceManagerUpd() {
			updateobj(gb("ResourceManagerUpd").name, gb("ResourceManagerUpd").link, "", "");
		}
		function ResourceManagerAdd() {
			addobj(gb("ResourceManagerAdd").name, gb("ResourceManagerAdd").link, "", "");
		}
		function ResourceManagerDel() {
			delobj("/busi/ResourceManagerDel.do");
		}
	</script>
</body>
</html>
