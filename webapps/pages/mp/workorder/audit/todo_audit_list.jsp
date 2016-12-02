<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>变更工单待审核列表</title>
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
			<li>变更待审核列表</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="list" action="/mp/workorder/todoAuditList.do" method="post">
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
						<s:textfield name="workOrderChange.workOrder.orderNo" value="" size="15"/>
					</td>
					<th><label>用户联系人</label></th>
					<td>
						<s:textfield name="workOrderChange.workOrder.contactUserName" value="" size="15"/>
					</td>
					<th><label>销售业务员</label></th>
					<td>
						<s:textfield name="workOrderChange.workOrder.salesClerk" value="" size="15"/>
					</td>
				</tr>
				<tr>
					<th><label for="time">创建时间</label></th>
					<td>
						<input name="workOrderChange.createdTimeStart" id="createDate_start" size="10" /> -
						<input name="workOrderChange.createdTimeEnd" id="createDate_end"  size="10"/>
					</td>
					<td><input type="submit" class="action search right" value="查询" /></td>
				</tr>
			</tbody>
			</table>
		</s:form>
	
		
		
		
		
		
		<mys:table cssClass="list" value="ecPage" status="loop">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="工单编号" value="workOrder.orderNo" />
			<mys:column title="用户联系人" value="workOrder.contactUserName" width="100px" />
			<mys:column title="销售业务员" value="workOrder.salesClerk" width="100px" />
			<mys:column title="工单状态" sortable="false" width="60px">
				<s:property value="@com.ylink.ylpay.common.project.portal.constant.EntityStatus@parseOf(workOrder.status.value).displayName"/>
			</mys:column>
			<mys:column title="创建日期" value="createdTime" sortable="false" width="160px"/>
			<mys:column title="操作备注" value="description"/>
			<mys:column title="操作类型" value="type" sortable="false" width="60px"/>
			<mys:column title="操作人" value="operator.loginName" sortable="false" width="120px"/>
			<mys:column title="审核状态" value="status" sortable="false" width="60px"/>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<li><a href="<s:url value="/mp/workorder/todoAudit.do?workOrderChange.id=%{id}&workOrderChange.workOrder.id=%{workOrder.id}"/>">审核工单</a></li>
					</ul>
				</div>
			</mys:column>
		</mys:table>
	
		<mys:pagination value="ecPage" pageParamPrefix="ecPage" />
	</body>
</html>