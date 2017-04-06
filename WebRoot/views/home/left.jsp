<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${MENU eq null}">
	<jsp:forward page="/busi/common/getMenu.do"></jsp:forward>
</c:if>
<html>
<head>
<title></title>
<script type="text/javascript">
	function addT(y, x) {
		alert("d");
	}
</script>
</head>
<body>
	<div class="easyui-tabs" data-options="fit:true">
		<div title="功能资源" data-options="iconCls:'icon-tip'">
			<div class="easyui-layout" data-options="fit:true">
				<div data-options="region:'center'">
					<div class="easyui-accordion" data-options="fit:true">
						<c:forEach items="${MENU}" var="i">
							<div title="${i.name}" data-options="iconCls:'icon-qm'">
								<c:forEach items="${i.submenus}" var="y">
									<div class="as">
										<span class="icon-default tree-file pictures"></span><span><a href="javascript:addT('${y.name}','${i.link}/${y.link}&${y.id}')">${y.name}</a></span>
									</div>
								</c:forEach>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- <div style="height: 150px;" data-options="region:'south',split:true">空地待开发</div> -->
			</div>
		</div>
		<!-- <div title="资源开发" data-options="iconCls:'icon-tip'">空地待开发</div> -->
	</div>
</body>
</html>
