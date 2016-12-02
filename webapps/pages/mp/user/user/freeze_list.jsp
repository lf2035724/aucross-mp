<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>用户冻结管理</title>
<script type="text/javascript">
function checkAuth(url){
	//检查是否可授权
	$.lightssh.checkAuth(
		{'contextPath':'<%= request.getContextPath() %>'
		,'checkPassword': false
		,'targetUrl':url}
		,function(succes,ticket,msg){ 
			if( !succes )
				return;
			if( ticket != null || ticket != '' ) {
				url += '&auth.ticket=' + ticket;
			}
			window.location.href=url;
		}
	);//end $.lightssh.checkAuth
}
function doFreeze(id,name){
	var url = '<s:url value="/mp/user/user/activateOrFreezeUser.do"/>?userVo.id='+id+'&userVo.enabled=false'+'&userVo.status=0';
	if(name==null)
		name =id;
	if( confirm('确认冻结子账户[' + name + ']'))
		checkAuth(url);
		//location.href=url;
	
}

function doUnfreeze(id,name){
	var url = '<s:url value="/mp/user/user/activateOrFreezeUser.do"/>?userVo.id='+id+'&userVo.enabled=true'+'&userVo.status=1';
	if(name==null)
		name =id;
	if( confirm('确认解冻子账户[' + name + ']'))
		checkAuth(url);
		//location.href=url;
}

function doCancel(id,name){
	var url = '<s:url value="/mp/user/user/cancelUser.do"/>?userVo.id='+id+'&userVo.enabled=false'+'&userVo.status=2';
	if(name==null)
		name =id;
	if( confirm('确认注销子账户[' + name + ']'))
		checkAuth(url);
		//location.href=url;
	
}

</script>
</head>

<body>
	<ul class="path">
		<li>冻结管理</li>
		<li>用户冻结管理</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form name="list" method="post">
		<table class="profile">
		     <colgroup>
				<col width="15%">
				<col width="30%">
				<col width="15%">
				<col>
			</colgroup>
			<tbody>
			<tr>
				<th><label for="name">用户编号</label></th>
				<td>
					<input type="text" name="userVo.loginName" value="${userVo.loginName}" size="20" maxlength="60" />
				</td>
				<th><label for="name">客户编号</label></th>
				<td colspan="2">
					<input type="text" name="userVo.customerId" value="${userVo.customerId}" size="20" maxlength="60" />
				</td>
			</tr>
			<tr>
				<th><label for="name">手机</label></th>
				<td>
					<input type="text" name="userVo.mobile" value="${userVo.mobile}" size="20" maxlength="60" />
				</td>
				<th><label for="name">邮箱</label></th>
				<td colspan="2">
					<input type="text" name="userVo.email" value="${userVo.email }" size="20" maxlength="60" />
				</td>
			</tr>
			<tr>
				<th><label for="name">客户名称</label></th>
				<td>
					<input type="text" name="userVo.name" value="${userVo.name}" size="20" maxlength="60" />
				</td>
				<th><label for="name">客户状态</label></th>
				<td>
					<select name="userVo.status">
						<option <s:if test="userVo.status==null || %{userVo.status==\"\"}">selected="selected" </s:if>value="">全部</option>
						<option <s:if test="%{userVo.status==\"1\"}">selected="selected" </s:if>value="1">有效</option>
						<option <s:if test="%{userVo.status==\"0\"}">selected="selected" </s:if>value="0">冻结</option>
						<option <s:if test="%{userVo.status==\"2\"}">selected="selected" </s:if>value="2">注销</option>
					</select>
				</td>
				<td  colspan="2">
					<input type="submit" class="action search left" value="查询" />
				</td>
			</tr>
		</tbody>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="用户编号" value="loginName" sortable="true" width="130px" />
		<mys:column title="客户编号" value="customerId" sortable="true" width="110px" />
		<mys:column title="客户名称" sortable="false" width="110px" >
			<s:set name="name" value="@com.ylink.ylpay.project.mp.cust.util.CustomerHelper@getCustName(customerId)"/>
			<s:property value="name"/>
		</mys:column>
		<mys:column title="手机" value="mobile" sortable="true" width="120px" />
		<mys:column title="邮箱" value="email" sortable="true" width="200px" />
		<mys:column title="状态" width="60px" >
			<s:if test="%{status==\"0\"}">
				冻结
			</s:if>
			<s:elseif test="%{status==\"1\"}">
				有效
			</s:elseif>
			<s:elseif test="%{status==\"2\"}">
				注销
			</s:elseif>
		</mys:column>
		<mys:column title="有效期(起)" value="period.start" sortable="false" width="80px" />
		<mys:column title="有效期(止)" value="period.end" sortable="false" width="80px" />
		<mys:column title="创建日期" sortable="true" sortKey="createDate" width="160px">
			<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(createDate,'yyyy-MM-dd HH:mm:ss')"/>
		</mys:column>
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<s:if test="%{status==\"0\"}">
						<li><a href="#" onclick="doUnfreeze('<s:property value="id"/>','<s:property value="name"/>')">解冻账户</a></li>
						<li><a href="#" onclick="doCancel('<s:property value="id"/>','<s:property value="name"/>')">注销账户</a></li>
					</s:if>
					<s:elseif test="%{status==\"1\"}">
						<li><a href="#" onclick="doFreeze('<s:property value="id"/>','<s:property value="name"/>')">冻结账户</a></li>
						<li><a href="#" onclick="doCancel('<s:property value="id"/>','<s:property value="name"/>')">注销账户</a></li>
					</s:elseif>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>