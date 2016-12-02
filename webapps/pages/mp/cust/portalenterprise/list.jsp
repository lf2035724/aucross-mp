<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
<head>
<meta name="decorator" content="background"/>
<title>企业客户审核列表</title>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>门户注册企业客户</li>
		<li>审核列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp" %>

	<s:form name="list" method="post">
		<table class="profile">
			<tr>
				<th><label for="name">企业名称</label></th>
				<td>
					<input type="text" name="enterprise.name" value="${enterprise.name}" size="20" maxlength="50" />
				</td>
				<th><label for="name">客户编号</label></th>
				<td>
					<input type="text" name="enterprise.id" value="${enterprise.id}" size="20" maxlength="50" />
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>
	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="企业名称" value="name"/>
		<mys:column title="客户编号" value="identity" sortKey="id" sortable="false" />
		<mys:column title="状态" sortKey="status" sortable="false" width="50px">
			<s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(status).displayName" />
		</mys:column>
		<mys:column title="审核状态" value="auditStatus" sortable="false" />
		<mys:column title="创建标志" value="registeredType" sortable="false" />
		<mys:column title="创建时间">
			<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(createDate,'yyyy-MM-dd HH:mm:ss')"/>
		</mys:column>
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li><a href="<s:url value="/mp/cust/portalenterprise/goAudit.do?enterprise.id=%{id}"/>">审核信息</a></li>
					<li><a href="<s:url value="/mp/cust/portalenterprise/detail.do?enterprise.id=%{id}"/>">明细</a></li>
				</ul>
			</div>
		</mys:column>
	</mys:table>
	<mys:pagination value="page"/>
</body>
</html>