<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
<head>
<meta name="decorator" content="background"/>
<title>企业客户列表</title>
</head>
<body>
	<ul class="path">
		<li>客户管理</li>
		<li>企业客户</li>
		<li>企业客户列表</li>
	</ul>
	
	<%@ include file="/pages/common/messages.jsp" %>
	<s:form name="list" method="post">
	<select style=""/>
		<table class="profile">
			<tr>
				<th><label for="name">企业名称</label></th>
				<td>
					<s:textfield name="vo.name" />
				</td>
				<th><label for="name">客户编号</label></th>
				<td>
					<s:textfield name="vo.id" />
				</td>
				<th><label for="type">创建标志</label></th>
				<td>
					<s:select list="@com.ylink.ylpay.common.project.mp.constant.RegisteredType@values()" 
						name="vo.registeredType" listKey="getValue()" value="vo.registeredType.getValue()"
						headerKey="" headerValue=""/>
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="企业名称" value="name" width="250px" />
		<mys:column title="客户编号" value="identity" sortKey="id" sortable="true" width="150px" />
		<mys:column title="联系电话" value="phone" sortable="true" width="150px" />
		<mys:column title="企业电子邮箱" value="email" sortable="true" width="150px" />
		<mys:column title="状态" sortKey="status" sortable="true" width="150px">
			<s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(status).displayName" />
		</mys:column>
		<mys:column title="审核状态" value="auditStatus" sortable="true" width="150px" />
		<mys:column title="创建标志" value="registeredType" sortable="true" width="150px" />
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
						<shiro:hasPermission name="CUST_ENTERPRISE_MODIFYDETAIL">
						<li><a href="<s:url action="modifyDetail.do?vo.id=%{id }" />">修改</a></li>
						</shiro:hasPermission>
					<li><a href="<s:url action="detail.do?vo.id=%{id }" />">明细</a></li>
					</ul>
				</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="page"/>

</body>
</html>