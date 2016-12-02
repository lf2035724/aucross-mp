<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>用户列表</title>
</head>

<body>
	<ul class="path">
		<li>门户管理</li>
		<li>门户用户</li>
		<li>用户列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form name="list" method="post">
		<table class="profile">
			<tbody>
			<tr>
				<th><label for="name">用户编号</label></th>
				<td>
					<input type="text" name="userVo.loginName" value="${userVo.loginName}" size="20" maxlength="60" />
				</td>
				<th><label for="name">客户编号</label></th>
				<td>
					<input type="text" name="userVo.customerId" value="${userVo.customerId}" size="20" maxlength="60" />
				</td>
			</tr>
			<tr>
				<th><label for="name">手机</label></th>
				<td>
					<input type="text" name="userVo.mobile" value="${userVo.mobile}" size="20" maxlength="60" />
				</td>
				<th><label for="name">邮箱</label></th>
				<td>
					<input type="text" name="userVo.email" value="${userVo.email }" size="20" maxlength="60" />
				</td>
			</tr>
			<tr>
				<th><label for="name">客户名称</label></th>
				<td>
					<input type="text" name="userVo.name" value="${userVo.name}" size="20" maxlength="60" />
				</td>
				<td  colspan="2">
					<input type="submit" class="action search left" value="查询" />
				</td>
			</tr>
		</tbody>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="用户编号" value="loginName" sortable="true" width="130px" />
		<mys:column title="客户编号" value="customerId" sortable="true" width="110px" />
		<mys:column title="客户名称" sortable="false" width="110px" >
			<s:property value="@com.ylink.ylpay.project.mp.cust.util.CustomerHelper@getCustName(customerId)"/>
		</mys:column>
		<mys:column title="手机" value="mobile" sortable="true" width="120px" />
		<mys:column title="邮箱" value="email" sortable="true" width="200px" />
		<mys:column title="状态" value="enabled?'有效':'无效'" width="60px" />
		<mys:column title="有效期(起)" value="period.start" sortable="false" width="80px" />
		<mys:column title="有效期(止)" value="period.end" sortable="false" width="80px" />
		<mys:column title="创建日期" sortable="true" sortKey="createDate" width="160px">
			<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(createDate,'yyyy-MM-dd HH:mm:ss')"/>
		</mys:column>
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li class="disabled"><a href="#">用户详情</a></li>
					<s:if test="customerId.startsWith('CB')">
						<li><a href="<s:url value="/cust/enterprise/detail.do?vo.id=%{customerId}"/>">企业客户信息</a></li>
					</s:if>
					<s:else>
						<li><a href="<s:url value="/cust/person/view.do?personal.id=%{customerId}"/>">个人客户信息</a></li>
					</s:else>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>