<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>工单变更审核结果列表</title>
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
			<li>变更审核结果</li>
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
						<s:textfield name="workOrderAudit.workOrderChange.workOrder.orderNo"  size="15"/>
					</td>
					<th><label for="time">创建时间</label></th>
					<td>
						<input name="workOrderAudit.createdTimeStart" id="createDate_start"  size="10" /> -
						<input name="workOrderAudit.createdTimeEnd" id="createDate_end"  size="10"/>
					</td>
					<th><label>创建者</label></th>
					<td>
						<s:textfield name="workOrderAudit.workOrderChange.workOrder.creator" size="15"/>
					</td>
		
					<td><input type="submit" class="action search right" value="查询" /></td>
				</tr>
			</tbody>
			</table>
		</s:form>
		
		
		
		
		
		
		
		<mys:table cssClass="list" value="eaPage" status="loop" pageParamPrefix="eaPage">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="工单编号" value="workOrderChange.workOrder.orderNo" sortable="true" width="220px"/>
			<mys:column title="操作类型" value="workOrderChange.type" width="60px"/>
			<mys:column title="审核人" value="user.loginName" sortable="true" width="140px"/>
			<mys:column title="审核结果" value="result" sortable="true" width="80px"/>
			<mys:column title="审核时间" value="createdTime" sortable="true" width="160px"/>
			<mys:column title="审核备注" value="description"/>
			<mys:column title="操作" width="10px" cssClass="action">
				<s:if test="%{workOrderChange.type.value != \"删除\"}">
					<span>&nbsp;</span>
					<div class="popup-menu-layer">
						<ul class="dropdown-menu">
							<li><a href="<s:url action="view.do?workOrder.id=%{workOrderChange.workOrder.id}" />">详细信息</a></li>
						</ul>
					</div>
				</s:if>
			</mys:column>
		</mys:table>
	
		<mys:pagination value="eaPage" pageParamPrefix="eaPage"/>
	</body>
</html>