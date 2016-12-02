<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>账号列表</title>
		
		<script type="text/javascript">
			function doRemove( id,name ){
				var url = '<s:url value="/security/account/remove.do?account.id="/>' + id ;
				if( confirm('确认停用用户账号[' + name + ']'))
					location.href=url;
			}
		</script>
	</head>
	
	<body>
		<ul class="path">
			<li>系统管理</li>
			<li>登录账号</li>
			<li>用户列表</li>
		</ul>
		<shiro:hasPermission name="SECURITY_ACCOUNT_SAVE">
		<input type="button" class="action new" value="新增用户" onclick="location.href='<s:url value="edit.do"/>'"/>
		</shiro:hasPermission>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="list" namespace="/system/account" method="post">
			<table class="profile">
				<colgroup>
					<col width="10%"/>
					<col width="20%"/>
					<col width="10%"/>
					<col width="20%"/>
					<col width="10%"/>
					<col width="20%"/>
					<col/>
				</colgroup>
				<tbody>
					<tr>
						<th><label for="name">登录账号</label></th>
						<td><s:textfield id="name" name="account.loginName" value="%{cachedParams['account.loginName']}" size="30" maxlength="100"/></td>
						<th><label>姓名</label></th>
						<td><s:textfield name="account.realName" value="%{cachedParams['account.realName'] }" size="30" maxlength="100" /></td>
						<th><label for="name">状态</label></th>
						<td>
							<s:select name="account.status" value="%{cachedParams['account.status']}"
								list="@com.google.code.lightssh.project.util.constant.AuditStatus@values()"
								listKey="name()" listValue="getValue()" headerKey="" headerValue=""/>
						</td>
					</tr>
					<tr>
						<th><label>邮箱</label></th>
						<td><s:textfield name="account.email" value="%{cachedParams['account.email'] }" size="30" maxlength="100" /></td>
						<th><label>手机</label></th>
						<td colspan="3">
							<s:textfield name="account.mobile" value="%{cachedParams['account.mobile'] }" size="30" maxlength="100" />
							<input type="submit" class="action search right" value="查询"/>
						</td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="page" status="loop">
			<mys:column title="序号" width="30px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="登录账号" value="loginName" sortable="true" width="150px"/>
			<mys:column title="姓名" value="realName" sortable="true" width="100px" />
			<mys:column title="邮箱" value="email" sortable="true" width="100px" />
			<mys:column title="手机" value="mobile" sortable="true" width="100px" />
			<mys:column title="所属关系" sortKey="partyId" sortable="true" width="150px">
				<s:property value="@com.google.code.lightssh.project.party.service.PartyHelper@getParty(partyId).name"/>
			</mys:column>
			<mys:column title="有效期(起)" value="period.start" sortable="true" width="90px" />
			<mys:column title="有效期(止)" value="period.end" sortable="true" width="90px"/>
			<mys:column title="状态" value="status" sortable="true" width="50px"/>
			<mys:column title="角色" width="200px">
				<s:iterator value="roles" status="loop">
					<font style="color:<s:property value="effective?'green':''"/>;">
						<s:property value="#loop.first?'':' , '"/><s:property value="%{name}"/>
					</font>
				</s:iterator>
			</mys:column>
<%-- 			<mys:column title="描述" value="description"/> --%>
			<mys:column title="创建日期" value="createDate" sortable="true" width="90px"/>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						
						<li class="view">
							<a href="<s:url value="view.do?account.id=%{id}"/>">账号详情</a>
						</li>
						
						<shiro:hasPermission name="SECURITY_ACCOUNT_EDIT">
						<li class="section"/>
						<li class="edit">
							<a href="<s:url value="/security/account/edit.do?account.id=%{id}"/>">编辑账号</a>
						</li>
						</shiro:hasPermission>
						
						<shiro:hasPermission name="SECURITY_ACCOUNT_REMOVE">
						<s:if test="loginName != @com.google.code.lightssh.project.security.service.LoginAccountManagerImpl@ROOT_LOGIN_NAME">
							<s:if test="removed">
								<li class="disabled">
									<a href="#">停用账号</a>
								</li>
							</s:if>
							<s:else>
								<li class="remove">
									<a href="#" onclick="javascript:doRemove('<s:property value="%{id}"/>','<s:property value="%{loginName}"/>')">停用账号</a>
								</li>
							</s:else>
						</s:if>
						</shiro:hasPermission>
						
						
						<shiro:hasPermission name="SECURITY_ACCOUNT_PRERESET">
						<li class="section"/>
						
						<li class="password">
							<a href="<s:url value="/security/account/prereset.do?account.loginName=%{loginName}"/>">重设密码</a>
						</li>
						</shiro:hasPermission>
						
					</ul>
				</div>
			</mys:column>
		</mys:table>
	
		<mys:pagination value="page" />
	
		<%-- 
		<s:url var="report_url_param" value="/security/account/report.do"/>
		<s:set name="REPORT_URL" value="%{report_url_param}"/>
		<s:set name="pagination" value="page"/>
		<jsp:include page="/pages/common/report.jsp"/>
		--%>
			
	</body>
</html>