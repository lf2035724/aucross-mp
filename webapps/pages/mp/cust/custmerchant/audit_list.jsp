<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>商户修改待审核结果</title>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>商户管理</li>
			<li>商户修改待审核结果</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="auditlistCustMerchant" namespace="/mp/user/user" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label for="name">商户编号</label></th>
						<td><input type="text" name="vo.code" value="${vo.code}" size="20" maxlength="60" /></td>
						<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="custMerchantAduitPage" status="loop">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="商户编号" value="custMerchantChange.custMerchant.code" sortable="false" width="100px"/>
			<mys:column title="商户名称" value="custMerchantChange.custMerchant.name" sortable="false" width="200px"/>
			<mys:column title="修改后商户名称" value="custMerchantChange.name" sortable="false" width="200px"/>
			<mys:column title="商户类型" value="custMerchantChange.custMerchant.type" sortable="false" width="60px"/>
			<mys:column title="操作日期" value="custMerchantChange.createdTime" sortable="false" width="160px"/>
			<mys:column title="操作类型" value="custMerchantChange.type" sortable="false" width="60px"/>
			<mys:column title="操作人" value="custMerchantChange.operator.loginName" sortable="false" width="120px"/>
			<mys:column title="审核描述" value="description" sortable="false"  />
			<mys:column title="审核结果" value="result.value" sortable="false" width="120px"/>
			<mys:column title="审核人" value="user.loginName" sortable="false" width="60px"/>
<%-- 			<mys:column title="审核" width="40px" cssClass="action"> --%>
<!-- 				<span>&nbsp;</span> -->
<!-- 				<div class="popup-menu-layer"> -->
<!-- 					<ul class="dropdown-menu"> -->
<%-- 						<li><a href="<s:url value="todoaudit.do?vo.code=%{id}"/>">审核商户修改内容</a></li> --%>
<!-- 					</ul> -->
<!-- 				</div> -->
<%-- 			</mys:column> --%>
		</mys:table>
	
		<mys:pagination value="custMerchantAduitPage" pageParamPrefix="custMerchantAduitPage" />
	</body>
</html>