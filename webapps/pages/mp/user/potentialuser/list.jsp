<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>潜在客户列表</title>
</head>

<body>
	<ul class="path">
		<li>门户管理</li>
		<li>潜在客户列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form name="list" method="post">
		<table class="profile">
			<tr>
				<th><label for="name">邮箱</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_email" value="${param['filter_LIKE_S_email'] }" size="40" maxlength="100" />
				</td>
				<th><label for="name">手机</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_mobile" value="${param['filter_LIKE_S_mobile'] }" size="40" maxlength="100" />
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="50px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="邮箱" value="email" sortable="true" width="200px" />
		<mys:column title="手机" value="mobile" sortable="true" width="200px" />
		<mys:column title="状态" value="status" sortable="true" width="50px" />
		<mys:column title="创建日期" value="createdTime" sortable="true" width="200px" />
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>