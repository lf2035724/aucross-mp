<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<head>
	<meta name="decorator" content="background"/>
</head>

<body>
	<ul class="path">
		<li>我的首页</li>
	</ul>
	
	<%@ include file="/pages/common/messages.jsp" %>
	
	<br/>
	<shiro:principal/>，您好！
	
</body>