<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>待审核列表</title>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>企业客户</li>
			<li>变更待审核列表</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="list" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label for="name">企业客户名称</label></th>
						<td><s:textfield id="id" name="enterpriseChange.enterprise.name" size="40" maxlength="100"/></td>
						<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="ecPage" status="loop">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="企业客户名称" value="enterprise.name" sortable="false" width="260px"/>
			<mys:column title="企业客户状态" sortable="false" width="60px">
				<s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(enterprise.status).displayName"/>
			</mys:column>
			<mys:column title="创建日期" value="createdTime" sortable="false" width="160px"/>
			<mys:column title="操作备注" value="description"/>
			<mys:column title="操作类型" value="type" sortable="false" width="60px"/>
			<mys:column title="操作人" value="operator.loginName" sortable="false" width="120px"/>
			<mys:column title="状态" value="status" sortable="false" width="60px"/>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<li><a href="<s:url value="/cust/enterprise/todoaudit.do?enterpriseChange.id=%{id}&enterpriseChange.enterprise.id=%{enterprise.id}"/>">审核企业客户</a></li>
						<li class="section"/>
					</ul>
				</div>
			</mys:column>
		</mys:table>
	
		<mys:pagination value="ecPage" pageParamPrefix="ecPage" />
	</body>
</html>