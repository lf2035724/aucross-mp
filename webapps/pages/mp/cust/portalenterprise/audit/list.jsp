<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
<head>
<meta name="decorator" content="background"/>
<title>审核结果列表</title>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>门户注册企业客户</li>
		<li>审核结果</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp" %>

	<s:form name="list" method="post">
		<table class="profile">
			<tr>
				<th><label for="name">企业名称</label></th>
				<td>
					<input type="text" name="portalEnterpriseAudit.enterprise.name" value="${portalEnterpriseAudit.enterprise.name}" size="20" maxlength="50" />
				</td>
				<th><label for="name">客户编号</label></th>
				<td>
					<input type="text" name="portalEnterpriseAudit.enterprise.id" value="${portalEnterpriseAudit.enterprise.id}" size="20" maxlength="50" />
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>
	<mys:table cssClass="list" value="resultPage" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="企业名称" value="enterprise.name" width="50px" />
		<mys:column title="客户编号" value="enterprise.identity" sortKey="id" sortable="false" width="50px" />
		<mys:column title="创建标志" value="enterprise.registeredType" sortable="false" width="50px" />
		<mys:column title="审核状态" value="result.displayName" sortable="false" width="50px" />
		<mys:column title="审核人员" value="operator.loginName" sortable="false" width="50px" />
		<mys:column title="创建时间" value="createdTime" sortable="false" width="50px" />
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li><a href="<s:url value="/mp/cust/portalenterprise/detail.do?enterprise.id=%{enterprise.id}"/>">企业信息明细</a></li>
					<li><a href="<s:url value="/mp/cust/portalenterprise/auditDetail.do?portalEnterpriseAudit.id=%{id}"/>">审核明细</a></li>
				</ul>
			</div>
		</mys:column>
	</mys:table>
	<mys:pagination value="resultPage" pageParamPrefix="resultPage"/>
</body>
</html>