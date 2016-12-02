<%@ include file="/pages/common/taglibs.jsp" %>
<%@ page pageEncoding="utf-8" isErrorPage="true"%>

<html>
	<head>
		<title>系统异常</title>
		<link rel="stylesheet" type="text/css" media="all" href="<%= request.getContextPath() %>/styles/<mys:theme />/theme.css" />
	</head>

	<body>
		<h3> Application Exception!</h3>
		
		<s:property value="exception.message"/>
		<div>
			<s:property value="exception"/>
			<s:property value="exception.cause"/>
		</div>
		
	</body>
</html>