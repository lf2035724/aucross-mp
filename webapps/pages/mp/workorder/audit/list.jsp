<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
<head>
<meta name="decorator" content="background"/>
<title>工单复核列表</title>

<script type="text/javascript" src="<s:url value="/pages/mp/workorder/audit/list.js" />"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#createDate_start").datepicker({dateFormat: 'yymmdd',changeYear:true});
		$("#createDate_end").datepicker({dateFormat: 'yymmdd',changeYear:true});
	});
</script>
</head>

<body>
	<ul class="path">
		<li>业务管理</li>
		<li>工单管理</li>
		<li>工单复核列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp" %>

	<s:form name="list" method="post">
			<table class="profile">
			 <colgroup>
				<col width="10%">
				<col width="20%">
				<col width="10%">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label>编号</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_orderNo" value="%{#parameters['filter_LIKE_S_id']}" size="15"/>
					</td>
					<th><label>用户联系人</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_contactUserName" value="%{#parameters['filter_LIKE_S_contactUserName']}" size="15"/>
					</td>
						<th><label>上行节点名称</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_ascendingNodeName" value="%{#parameters['filter_LIKE_S_ascendingNodeName']}" size="15"/>
					</td>
				</tr>
				<tr>
					<th><label>销售业务员</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_salesClerk" value="%{#parameters['filter_LIKE_S_salesClerk']}" size="15"/>
					</td>
					<th><label>客服联系人</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_customerServiceContactPerson" value="%{#parameters['filter_LIKE_S_customerServiceContactPerson']}" size="15"/>
					</td>
					<th><label for="time">创建时间</label></th>
					<td>
						<input name="filter_GE_D_createTime" id="createDate_start" value="${param['filter_GE_D_createTime'] }" size="10" /> -
						<input name="filter_LE_D_createTime" id="createDate_end" value="${param['filter_LE_D_createTime'] }" size="10"/>
					</td>
				</tr>
				<tr>
					<th><label>创建者</label></th>
					<td>
						<s:textfield name="filter_EQ_S_creator" value="%{#parameters['filter_EQ_S_creator']}" size="15"/>
					</td>
					<td><input type="submit" class="action search right" value="查询" /></td>
				</tr>
			</tbody>
			</table>
		</s:form>
	
	

	<s:form id="frm" action="" method="post">
	<input type="button" id="passBtn" class="action ok" value="通过" />
	<input type="button" id="refuseBtn" class="action remove" value="拒绝" />

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="" width="10px">
			<input type="radio" name="workOrder.id" value="${id}" />
		</mys:column>
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="工单编号" value="orderNo" width="150px" />
		<mys:column title="用户名称" value="userName" width="150px" />
		<mys:column title="用户联系人" value="contactUserName" width="150px" />
		<mys:column title="用户联系电话" value="contactUserPhone" width="150px" />
		<mys:column title="上行节点名称" value="ascendingNodeName" width="150px" />
		<mys:column title="计算机台数" value="pcAmount" width="150px" />
		<mys:column title="创建时间" value="createTime" width="150px" />
		<mys:column title="销售业务员" value="salesClerk" width="150px" />
		<mys:column title="客服联系人" value="customerServiceContactPerson" width="150px" />
		<mys:column title="创建者" value="creator" width="150px" />
		<mys:column title="状态" sortKey="status" sortable="false" width="50px">
			<s:property value="@com.ylink.ylpay.common.project.portal.constant.EntityStatus@parseOf(status.value).displayName" />
		</mys:column>
		<mys:column title="审核状态" value="@com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus@parseOf(workOrderAuditStatus.value).displayName" sortable="false" width="50px" />
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li><a href="<s:url action="view.do?workOrder.id=%{id}"/>">明细</a></li>
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