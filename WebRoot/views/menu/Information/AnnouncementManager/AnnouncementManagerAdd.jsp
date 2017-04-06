<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" src="<%=path%>/plug-in/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/plug-in/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/plug-in/ueditor/ueditor.all.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="<%=path%>/plug-in/ueditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}
</style>
</head>
<body>
	<div>
		<div style="width: 100%;height: 30px;line-height: 30px">
			<span><font style="font-weight: bold;">文章标题:</font><input type="text" name="title" id="title" style="height: 26px;"></span><span></span>
		</div>
		<div style="width:100%;height:460px;">
			<script id="editor" type="text/plain" style="width:980px;;height:450px;"></script>
		</div>
	</div>
	<span class="thidden"><input type="button" id="btn_sub"></span>
	<script type="text/javascript">
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		var ue = UE.getEditor('editor');
		$(function() {
			$("#btn_sub").click(function() {
				var title = $("#title").val();
				var hasContents = ue.hasContents();
				if (title == '' || title == undefined) {
					alert("标题不能为空");
					return;
				}
				if (hasContents != true) {
					alert("内容不能为空");
					return;
				}
				$.ajax({
					type : "POST",
					url : "/busi/AnnouncementManagerAdd.do",
					data : {
						title : title,
						content : ue.getContent()
					},
					dataType : "json",
					success : function(data) {
						var win = frameElement.api.opener;
						if (data.success == true) {
							frameElement.api.close();
							win.tip(data.msg);
						} else {
							if (data.responseText == '' || data.responseText == undefined) {
								alert(data.msg);
							} else {
								alert(data.responseText);
							}
							frameElement.api.close();
							return false;
						}
						win.reloadTable();
					}
				});
			});
		});
	</script>
</body>
</html>