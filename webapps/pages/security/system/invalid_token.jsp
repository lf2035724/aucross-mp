<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/pages/common/taglibs.jsp" %>

<head>
	<meta name="decorator" content="background"/>
</head>

<body>
	<ul class="path">
		<li>系统首页</li>
	</ul>
	
	<%@ include file="/pages/common/messages.jsp" %>
	
	请不要重复提交请求！
	<p class="submit">
		<input type="button" class="action back" value="返回" onclick="backPage();" />
	</p>
</body>