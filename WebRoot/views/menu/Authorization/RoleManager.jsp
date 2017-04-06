<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../menuBase.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
</head>
<body class="easyui-layout" id="cc">
	<div data-options="region:'center',title:'',collapsible:false">
		<div class="p">
			<form id="ff" method="post">
				<p>
					<span class="spanr">名称:<input type="text" name="name">&nbsp;&nbsp;<a id="btn" onclick="btn()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;<a id="btn" onclick="reload()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置 </a></span>
				</p>
			</form>
		</div>
		<table id="tt">
		</table>
	</div>
	<div data-options="region:'east',title:'权限设置',split:true" style="width:280px;">
		<div id="p">
			<ul id="tr"></ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	var datagrid;
	$(function() {
		$('#cc').layout('collapse', 'east');
		datagrid = $("#tt").datagrid({
			url : '/busi/RoleManager.do',
			fitColumns : true,//设置为true将自动使列适应表格宽度以防止出现水平滚动 */
			pagination : true,// 分页控件
			pageSize : 10,//默认显示大小
			fit : true,// 自动大小
			rownumbers : true,// 行号
			singleSelect : true,//只能选中一行
			pageList : [ 10, 20, 50 ],
			sortName : 'id',//排序
			sortOrder : 'asc',// asc desc
			idField : 'id',
			columns : [ [ {
				field : 'name',
				title : '角色名称',
				width : 300,
				sortable : true
			}, {
				field : 'groups',
				title : '角色所属组',
				width : 300,
				sortable : true,
				formatter : function(value, row, index) {
					if (value == 1) {
						return '管理组';
					} else if (value == 2) {
						return '招生人员';
					} else {
						return '未知';
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
		});
	}
	function reload() {
		ff[0].reset();
		btn();
	}
	function RoleManagerUpd() {
		updateobj(gb("RoleManagerUpd").name, gb("RoleManagerUpd").link, "", "");
	}
	function RoleManagerAdd() {
		addobj(gb("RoleManagerAdd").name, gb("RoleManagerAdd").link, "", "");
	}
	function RoleManagerDel() {
		delobj("/busi/RoleManagerDel.do");
	}
	var tr;
	var tid;
	function RoleManagerUpdResources() {
		$('#cc').layout('expand', 'east');
		if (selectonly()) {
			var rowsData = datagrid.datagrid('getSelections');
			$('#p').panel({
				fit : true,
				title : rowsData[0].name + ': 权限管理',
				tools : [ {
					iconCls : 'icon-reload',
					handler : function() {
						tr.tree('reload');
					}
				}, {
					iconCls : 'icon-save',
					handler : function() {
						savetree();
					}
				} ]
			});
			tr = $('#tr').tree({
				checkbox : true,
				animate : true,//显示动画效果
				lines : true,//定义是否显示树控件上的虚线。
				url : '/busi/RoleManagerUpdResources.do?method=getRoleResource&id=' + rowsData[0].id
			});
			tid = rowsData[0].id;
		}
	}
	function savetree() {
		if (selectonly()) {
			var checked = tr.tree('getChecked');
			var unchecked = tr.tree('getChecked', 'unchecked');
			var indeterminate = tr.tree('getChecked', 'indeterminate');
			/* 		alert(JSON.stringify(checked.toString())); */
			$.ajax({
				type : "POST",
				url : "/busi/RoleManagerUpdResources.do?method=updRoleResource",
				data : {
					id : tid,
					checked : JSON.stringify(checked),
					unchecked : JSON.stringify(unchecked),
					indeterminate : JSON.stringify(indeterminate)
				},
				dataType : "json",
				success : function(json) {
					tip(json.msg);
				}
			});
		}
	}
</script>
</html>
