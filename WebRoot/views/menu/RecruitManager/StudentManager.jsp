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
				<span class="spanr">QQ号码:<input type="text" name="qq">&nbsp;&nbsp;
					手机号码:<input type="text" name="mobile">&nbsp;&nbsp; 名称:<input
					type="text" name="name">&nbsp;&nbsp;<a id="btn"
					onclick="btn()" class="easyui-linkbutton"
					data-options="iconCls:'icon-search'">查询</a>&nbsp;&nbsp;<a id="btn"
					onclick="reload()" class="easyui-linkbutton"
					data-options="iconCls:'icon-reload'">重置 </a></span>
			</p>
		</form>
	</div>
	<table id="tt">
	</table>
	<script type="text/javascript">
		// 从jstl中获取当前用户ID
		var userId = "${user_info.id}";
		var uid;
		var status;
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
		var vtype = [ "是", "否" ];
		var datagrid;
		$(function() {
			getAllUsers();
			datagrid = $("#tt").datagrid({
				url : '/busi/StudentManager.do',
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
					field : 'name',
					title : '姓名',
					width : 140,
					sortable : true
				}, {
					field : 'qq',
					title : 'QQ号码',
					width : 150,
					sortable : true
				}, {
					field : 'mobile',
					title : '手机号码',
					width : 150,
					sortable : true
				}, {
					field : 'uid',
					title : '介绍人',
					width : 140,
					sortable : true,
					formatter : function(value, row, index) {
						uid = value;
						for (var i = 0; i < users.length; i++) {
							if (users[i].id == value) {
								return users[i].username;
							}
						}
						return "--";
					}
				}, {
					field : 'cuid',
					title : '转化人',
					width : 140,
					sortable : true,
					formatter : function(value, row, index) {
						for (var i = 0; i < users.length; i++) {
							if (users[i].id == value) {
								return users[i].username;
							}
						}
						return "--";
					}
				}, {
					field : 'status',
					title : '状态',
					width : 200,
					sortable : true,
					formatter : function(value, row, index) {
						status = value;
						if (value == "1") {
							return "录入状态，审核中";
						} else if (value == "2") {
							return "录入审核通过";
						} else if (value == "3") {
							return "审核不通过";
						} else if (value == "4") {
							return "转化审核中";
						} else if (value == "5") {
							return "转化审核通过";
						} else if (value == "6") {
							return "转化审核失败";
						} else if (value == "7") {
							return "转化失败";
						} else {
							return "其他状态";
						}
					}
				}, {
					field : 'conver',
					title : '是否可以转化',
					width : 100,
					sortable : true,
					formatter : function(value, row, index) {
						if(status=="2"){
							if (value == "1") {
								// 当前登录用户和本条数据所属用户一致 
								if (uid == userId) {
									return "可以";
								} else {
									return "不可以";
								}
							} else {
								return "可以";
							}
						}else{
							return "非转化阶段";
						}
					}
				}, {
					field : 'days',
					title : '转化倒计时',
					width : 70,
					sortable : true
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
				mobile : ff.find('[name="mobile"]').val(),
				qq : ff.find('[name="qq"]').val()
			});
		}
		function reload() {
			ff[0].reset();
			btn();
		}
		function StudentManagerUpdConver() {
			updateobj(gb("StudentManagerUpdConver").name,
					gb("StudentManagerUpdConver").link, "", "");
		}
		function StudentManagerUpdStatus() {
			updateobj(gb("StudentManagerUpdStatus").name,
					gb("StudentManagerUpdStatus").link, "", "");
		}
		function StudentManagerUpd() {
			updateobj(gb("StudentManagerUpd").name,
					gb("StudentManagerUpd").link, "", "");
		}
		function StudentManagerAdd() {
			addobj(gb("StudentManagerAdd").name, gb("StudentManagerAdd").link,
					"", "");
		}
		function StudentManagerDel() {
			delobj("/busi/StudentManagerDel.do");
		}
	</script>
</body>
</html>
