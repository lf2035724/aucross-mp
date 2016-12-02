<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>企业客户变更审核结果列表</title>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>企业客户</li>
			<li>变更审核结果</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="list" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label for="name">企业客户名称</label></th>
						<td><s:textfield id="name" name="enterpriseAudit.enterpriseChange.enterprise.name" size="40" maxlength="100"/></td>
						<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="eaPage" status="loop" pageParamPrefix="eaPage">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="企业客户名称" value="enterpriseChange.enterprise.name" sortable="true" width="220px"/>
			<mys:column title="操作类型" value="enterpriseChange.type" width="60px"/>
			<mys:column title="审核人" value="user.loginName" sortable="true" width="140px"/>
			<mys:column title="审核结果" value="result" sortable="true" width="80px"/>
			<mys:column title="审核时间" value="createdTime" sortable="true" width="160px"/>
			<mys:column title="审核备注" value="description"/>
			<%-- 
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
					</ul>
				</div>
			</mys:column>
			--%>
		</mys:table>
	
		<mys:pagination value="eaPage" pageParamPrefix="eaPage"/>
	</body>
</html>