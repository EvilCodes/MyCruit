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
				<span class="spanr"> 关键字:<input type="text" name="keyword">&nbsp;&nbsp;<a id="btn" onclick="btn()" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;<a id="btn" onclick="reload()" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置 </a></span>
			</p>
		</form>
	</div>
	<table id="tt">
	</table>
	<script type="text/javascript">
	var role;
	function getAllRole() {
		$.ajax({
			type : "POST",
			async : false,
			url : "/busi/common/findAllRoleGroups.do",
			data : {
				groups : '2'
			},
			dataType : "json",
			success : function(json) {
				role = json;
			}
		});
	}
		var datagrid;
		$(function() {
			getAllRole();
			datagrid = $("#tt").datagrid({
				url : '<%=path%>/busi/PartTimersManager.do',
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
					field : 'username',
					title : '用户名',
					width : 300,
					sortable : true
				}, {
					field : 'tel',
					title : '电话',
					width : 300,
					sortable : true
				}, {
					field : 'roleid',
					title : '角色',
					width : 300,
					sortable : true,
					formatter : function(value, row, index) {
						for (var i = 0; i < role.length; i++) {
							if (role[i].id == value) {
								return role[i].name;
							}
						}
						return "未知";
					}
				}, {
					field : 'status',
					title : '状态',
					width : 300,
					sortable : true,
					formatter : function(value, row, index) {
						if (value == 'Y') {
							return "<font color='green'>启用</font>";
						} else if (value == 'N') {
							return "<font color='red'>禁用</font>";
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
				keyword : ff.find('[name="keyword"]').val()
			});
		}
		function reload() {
			ff[0].reset();
			btn();
		}
		function PartTimersManagerUpd() {
			updateobj(gb("PartTimersManagerUpd").name, gb("PartTimersManagerUpd").link, "", "");
		}
		function PartTimersManagerAdd() {
			addobj(gb("PartTimersManagerAdd").name, gb("PartTimersManagerAdd").link, "", "");
		}
		function PartTimersManagerDel() {
			delobj("/busi/PartTimersManagerDel.do");
		}
		function PartTimersManagerUpdPassword() {
			updateobj(gb("PartTimersManagerUpdPassword").name, gb("PartTimersManagerUpdPassword").link, "", "");
		}
		function PartTimersManagerUpdStatus() {
			if (selectonly() == true) {
				var datas = datagrid.datagrid('getSelections');
				var data = {
					id : datas[0].id,
					status : datas[0].status
				};
				updateo("/busi/PartTimersManagerUpdStatus.do", data);
			}
		}
	</script>
</body>
</html>
