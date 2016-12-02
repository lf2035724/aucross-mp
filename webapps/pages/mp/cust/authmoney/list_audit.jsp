<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>审核结果</title>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>金汇宝授信管理</li>
			<li>审核结果</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="list" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label>客户编号</label></th>
						<td>
							<s:textfield name="authMoneyAudit.custId" value="%{#parameters['authMoneyAudit.custId']}"/>
						</td>
						<td><input type="submit" class="action search right" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="auditPage" status="loop">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="客户编号" value="authMoneyChange.custId" sortable="true" width="220px"/>
			<mys:column title="操作类型" value="authMoneyChange.type" width="60px"/>
			<mys:column title="审核人" value="user.loginName" sortable="true" width="140px"/>
			<mys:column title="审核结果" value="result" sortable="true" width="80px"/>
			<mys:column title="审核时间" value="createdTime" sortable="true" width="160px"/>
			<mys:column title="审核备注" value="description"/>
		</mys:table>
		<mys:pagination value="auditPage" pageParamPrefix="auditPage" />
	</body>
</html>