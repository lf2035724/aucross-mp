<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>日志列表</title>
</head>

<body>
	<ul class="path">
		<li>门户管理</li>
		<li>门户用户</li>
		<li>登录失败日志列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form name="list" method="post">
		<table class="profile">
				<tr>
				<th><label for="name">客户编码</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_account.customerId" value="${param['filter_LIKE_S_account.customerId'] }" size="20" maxlength="100" />
				</td>
				<th><label for="name">登录名称</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_loginName" value="${param['filter_LIKE_S_loginName'] }" size="20" maxlength="100" />
				</td>
				<th><label for="name">用户手机号</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_account.mobile" value="${param['filter_LIKE_S_account.mobile'] }" size="20" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th><label for="name">用户邮件地址</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_account.email" value="${param['filter_LIKE_S_account.email'] }" size="20" maxlength="100" />
				</td>
				<td colspan="4"><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="50px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="客户编码" value="account.customerId" sortable="true" width="100px" />
		<mys:column title="客户名称" sortable="true" width="100px">
			<s:property value="@com.ylink.ylpay.project.mp.cust.util.CustomerHelper@getCustName(account.customerId)"/>
		</mys:column>
		<mys:column title="登录名称" value="loginName" sortable="true" width="200px" />
		<mys:column title="用户手机号" value="account.mobile" sortable="true" width="200px" />
		<mys:column title="用户邮件地址" value="account.email" sortable="true" width="200px" />
		<mys:column title="IP地址" value="ip" sortable="true" width="200px" />
		<mys:column title="失败次数" value="failureCount" sortable="true" width="200px" />
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>