<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${Content eq null }">
	<jsp:forward page="/common/findAnnouncementTopOne.do"></jsp:forward>
</c:if>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script src="/plug-in/ueditor/ueditor.parse.min.js"></script>
<head>
<title>新闻</title>
<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
}
#content {
	padding: 4px 2px 2px 2px;
}
</style>
</head>
<body>
	<div id="content">${Content}</div>
	<script>
		uParse('#content', {
			rootPath : '../'
		});
	</script>
</body>
</html>
