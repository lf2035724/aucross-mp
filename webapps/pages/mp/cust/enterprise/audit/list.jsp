<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
<head>
<meta name="decorator" content="background"/>
<title>企业客户复核列表</title>
<script type="text/javascript" src="<s:url value="/pages/mp/cust/enterprise/audit/list.js" />"></script>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>企业客户</li>
		<li>新增复核列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp" %>

	<s:form name="list" method="post">
		<table class="profile">
			<tr>
				<th><label for="name">企业名称</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_name" value="${param['filter_LIKE_S_name'] }" size="20" maxlength="50" />
				</td>
				<th><label for="name">客户编号</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_id" value="${param['filter_LIKE_S_id'] }" size="20" maxlength="50" />
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>

	<s:form id="frm" action="" method="post">
	<input type="button" id="passBtn" class="action ok" value="通过" />
	<input type="button" id="refuseBtn" class="action remove" value="拒绝" />

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="" width="10px">
			<input type="radio" name="vo.id" value="${identity }" />
		</mys:column>
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="企业名称" value="name" width="150px" />
		<mys:column title="客户编号" value="identity" sortKey="id" sortable="false" width="50px" />
		<mys:column title="状态" sortKey="status" sortable="false" width="50px">
			<s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(status).displayName" />
		</mys:column>
		<mys:column title="审核状态" value="auditStatus" sortable="false" width="50px" />
		<mys:column title="创建标志" value="registeredType" sortable="false" width="50px" />
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li><a href="<s:url action="detail.do?vo.id=%{id}"/>">明细</a></li>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<input type="hidden" id="passUrl" value="<s:url action="pass.do"/>" />
	<input type="hidden" id="refuseUrl" value="<s:url action="refuse.do"/>" />
	</s:form>

	<mys:pagination value="page"/>

</body>
</html>