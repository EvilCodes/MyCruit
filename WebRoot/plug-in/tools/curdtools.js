//﻿var jq = jQuery.noConflict();
/**
 * 增删改工具栏
 */
window.onerror = function() {
	return true;
};
var iframe;// iframe操作对象
var obj;
var windowapi = frameElement.api, W = windowapi.opener;// 内容页中调用窗口实例对象接口
var datagrids;
/**
 * 判断是否只选择一个
 */
function selectonly() {
	var rowsData = datagrid.datagrid('getSelections');
	if (!rowsData || rowsData.length == 0) {
		tip('请选择编辑项目');
		return false;
	}
	if (rowsData.length > 1) {
		tip('请选择一条记录再编辑');
		return false;
	}
	datagrids = rowsData;
	return true;
}
/**
 * 添加事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 */
function addobj(title, addurl, width, height) {
	createwindow(title, addurl, width, height);
}
/**
 * 更新事件打开窗口
 * 
 * @param title
 *            编辑框标题
 * @param addurl//目标页面地址
 * @param id//主键字段
 */
function updateobj(title, url, width, height) {
	if (selectonly() == true) {
		createwindow(title, url, width, height);
	}
	return;
}
/**
 * 单删除
 * 
 * @param url
 * @param id
 */
function delobj(url) {
	if (selectonly() == true) {
		$.dialog.confirm("您确认要执行删除操作?", function() {
			$.ajax({
				url : url, // 后台处理程序" +
				type : 'post', // 数据发送方式
				dataType : 'json', // 接受数据格式
				data : {
					"id" : datagrids[0].id
				}, // 要传递的数据
				success : function(data) {
					if (data.success) {
						tip(data.msg);
						reloadTable();
					} else {
						tip(data.msg);
					}
				}
			});
		}, function() {

		});
	}
}
/**
 * 只做查看
 * 
 * @param title
 * @param addurl
 * @param datagrid
 * @param width
 * @param height
 */
function check(title, addurl, width, height) {
	if (selectonly() == true) {
		if (addurl.indexOf(".jsp") > -1) {
		} else {
			addurl = form + addurl + ".jsp";
		}
		width = width ? width : 700;
		height = height ? height : 400;
		if (width == "100%" || height == "100%") {
			width = document.body.offsetWidth;
			height = document.body.offsetHeight - 100;
		}
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			button : [ {
				name : '关闭'
			} ]
		});
	}
}
/**
 * 查看and打印
 * 
 * @param title
 * @param addurl
 * @param datagrid
 * @param width
 * @param height
 */
function checkandprint(title, addurl, datagrid, width, height) {
	var rowsData = datagrid.datagrid('getSelections');
	if (!rowsData || rowsData.length == 0) {
		tip('请选择编辑项目');
		return;
	}
	if (rowsData.length > 1) {
		tip('请选择一条记录再编辑');
		return;
	}
	if (rowsData.length == 1) {
		width = width ? width : 700;
		height = height ? height : 400;
		if (width == "100%" || height == "100%") {
			width = document.body.offsetWidth;
			height = document.body.offsetHeight - 100;
		}
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			button : [ {
				name : '打印',
				callback : function() {
					iframe = this.iframe.contentWindow;
					iframe.print();
					return false;
				},
				focus : true
			}, {
				name : '关闭'
			} ]
		});
	}
}

/**
 * 自定义更新字段
 */
function updateo(url, data) {
	$.dialog.confirm("您确认要执行操作?", function() {
		$.ajax({
			url : url, // 后台处理程序"
			type : 'post', // 数据发送方式
			dataType : 'json', // 接受数据格式
			data : data, // 要传递的数据
			success : function(data) {
				if (data.success) {
					tip(data.msg);
					reloadTable();
				} else {
					tip(data.msg);
				}
			}
		});
	}, function() {

	});
}
function updateo(url, data, msg) {
	$.dialog.confirm(msg, function() {
		$.ajax({
			url : url, // 后台处理程序"
			type : 'post', // 数据发送方式
			dataType : 'json', // 接受数据格式
			data : data, // 要传递的数据
			success : function(data) {
				if (data.success) {
					tip(data.msg);
					reloadTable();
				} else {
					tip(data.msg);
				}
			}
		});
	}, function() {

	});
}
/**
 * 批量更新或删除
 * 
 * @param url
 * @param datagrid
 */
function updateids(url, datagrid, msg) {
	var rowsData = datagrid.datagrid('getSelections');
	if (rowsData.length >= 1) {
		var ids = [];
		for (var i = 0; i < rowsData.length; i++) {
			ids.push(rowsData[i].id);
		}
		// 创建询问窗口
		$.dialog.confirm(msg, function() {
			$.ajax({
				url : url, // 后台处理程序" +
				type : 'post', // 数据发送方式
				dataType : 'json', // 接受数据格式
				data : {
					"ids" : ids.join(',')
				}, // 要传递的数据
				success : function(data) {
					if (data.success) {
						tip(data.msg);
						reloadTable();
					} else {
						tip("操作失败");
					}
				}
			});
		}, function() {
		});
	} else {
		tip("请选择");
	}
}
/**
 * 创建添加或编辑窗口
 * 
 * @param title
 * @param addurl
 * @param saveurl
 */
function createwindow(title, addurl, width, height) {
	if (addurl.indexOf(".jsp") > -1) {
	} else {
		addurl = form + addurl + ".jsp";
	}
	width = width ? width : 700;
	height = height ? height : 400;
	if (width == "100%" || height == "100%") {
		width = document.body.offsetWidth;
		height = document.body.offsetHeight - 100;
	}
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				return false;
			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		W.$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width,
			height : height,
			parent : windowapi,
			title : title,
			opacity : 0.3,
			cache : false,
			ok : function() {
				iframe = this.iframe.contentWindow;
				saveObj();
				return false;
			},
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	}
}
function createcheckcwindow(title, addurl, width, height) {
	width = width ? width : 700;
	height = height ? height : 400;
	if (width == "100%" || height == "100%") {
		width = document.body.offsetWidth;
		height = document.body.offsetHeight - 100;
	}
	if (typeof (windowapi) == 'undefined') {
		$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width,
			height : height,
			title : title,
			opacity : 0.3,
			cache : false,
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	} else {
		W.$.dialog({
			content : 'url:' + addurl,
			lock : true,
			width : width,
			height : height,
			parent : windowapi,
			title : title,
			opacity : 0.3,
			cache : false,
			cancelVal : '关闭',
			cancel : true
		/* 为true等价于function(){} */
		});
	}
}

/**
 * 提示信息
 */
function tip(msg) {
	$.dialog.setting.zIndex = 1980;
	$.messager.show({
		title : '提示信息',
		msg : msg,
		timeout : 1000 * 5
	});
}
/**
 * 下载确认通用方法
 */
function downalert(action, x, txt) {
	$.dialog.confirm(txt, function() {
		window.location = action + "?x=" + x;
	}, function() {
	});
}
/**
 * 提示信息像alert一样
 */
function alertTip(msg, title) {
	$.dialog.setting.zIndex = 1980;
	title = title ? title : "提示信息";
	$.dialog({
		lock : true,
		title : title,
		icon : 'tips.gif',
		content : msg
	});
}
/**
 * 执行保存
 * 
 * @param url
 * @param gridname
 */
function saveObj() {
	$('#btn_sub', iframe.document).click();
}
/**
 * 刷新table
 */
function reloadTable() {
	if (document.getElementById("datatb") != undefined) {
		datatb();
	}
	if (datagrid != "tt") {
		datagrid.datagrid('reload');
	}
}
