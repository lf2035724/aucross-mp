<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>待审核列表</title>
		<script type="text/javascript">
			
			$(document).ready(function(){
				$("#addForm").validate({
					rules:{
						"roleAudit.description":{required:true,maxlength:200}
					}
					,submitHandler: function(form) {
						if( confirm('确认进行审核操作?') )
							form.submit();
					}
				});
			});
			
		</script>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>金汇宝授信管理</li>
			<li>待审核列表</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="auditList" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label>客户编号</label></th>
						<td>
							<s:textfield name="authMoneyChange.custId" value="%{#parameters['authMoneyChange.custId']}"/>
						</td>
						<td><input type="submit" class="action search right" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="changePage" status="loop">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="客户编号" value="custId" sortable="false" width="260px"/>
			<mys:column title="创建日期" value="createdTime" sortable="false" width="160px"/>
			<mys:column title="操作备注" value="description"/>
			<mys:column title="操作类型" value="type" sortable="false" width="60px"/>
			<mys:column title="操作人" value="operator.loginName" sortable="false" width="120px"/>
			<mys:column title="状态" value="status" sortable="false" width="60px"/>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<li><a href="<s:url value="/mp/cust/authmoney/audit.do?authMoneyChange.id=%{id}&passed=1"/>">审核通过</a></li>
						<li class="section"/>
						<li><a href="<s:url value="/mp/cust/authmoney/audit.do?authMoneyChange.id=%{id}&reject=1"/>">审核拒绝</a></li>
					</ul>
				</div>
			</mys:column>
		</mys:table>
	
		<mys:pagination value="changePage" pageParamPrefix="changePage" />
	</body>
</html>