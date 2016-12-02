<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
<head>
<meta name="decorator" content="background"/>
<title>审核结果明细</title>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>注册企业客户</li>
		<li>审核结果明细</li>
	</ul>
	<%@ include file="/pages/common/messages.jsp" %>
	<mys:table cssClass="list" value="details" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="企业名称" value="audit.enterprise.name" width="50px" />
		<mys:column title="客户编号" value="audit.enterprise.identity" width="50px" />
		<mys:column title="审核项目" value="type.displayName"  width="50px" />
		<s:set name="passed" value="isPassed == true ? '是':'否'"/>
		<mys:column title="是否通过" value="#passed"  width="50px" />
		<mys:column title="描述" value="description" width="50px" />
	</mys:table>
</body>
</html>