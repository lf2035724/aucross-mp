<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<head>
<meta name="decorator" content="background" />
<title>禁用/激活用户</title>
</head>

<body>
	<ul class="path">
		<li>门户管理</li>
		<li>用户列表</li>
		<li>禁用/激活用户</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form action="activateOrDisable" method="post">
		<table class="profile">
			<tr>
				<th><label>用户编号</label></th>
				<td>${user.loginName }</td>
			</tr>
			<tr>
				<th><label>启用状态</label></th>
				<td><s:radio name="user.enabled" list="%{#{false:'禁用', true:'激活' } }"></s:radio></td>
			</tr>
			<tr>
				<th><label>邮箱</label></th>
				<td>${user.email }</td>
			</tr>
			<tr>
				<th><label>手机号</label></th>
				<td>${user.mobile }</td>
			</tr>
			<tr>
				<th><label>描述</label></th>
				<td>
					<textarea name="remark" rows="3" cols="30"></textarea>
				</td>
			</tr>
		</table>

		<p class="submit">
			<s:submit value="保存" cssClass="action save"></s:submit>
			<s:hidden name="user.id"></s:hidden>
		</p>

		<s:token />

	</s:form>
</body>