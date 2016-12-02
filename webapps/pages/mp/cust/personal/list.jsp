<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/ui/i18n/jquery.ui.datepicker_zh_CN.js"></script>
		<title>个人客户列表</title>
		
		<script type="text/javascript">
			function doRemove( id,name ){
				var url = '<s:url value="/party/person/remove.do"/>?party=person&party.id=' + id ;
				if( confirm('确认删除人员[' + name + ']'))
					location.href=url;
			}
			$(document).ready(function(){
				$("#createDate_start").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#createDate_end").datepicker({dateFormat: 'yymmdd',changeYear:true});
				});
		</script>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>个人客户</li>
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
						<s:textfield name="filter_LIKE_S_id" value="%{#parameters['filter_LIKE_S_id']}"/>
					</td>
					<th><label>证件号码</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_identityCardNumber" value="%{#parameters['filter_LIKE_S_identityCardNumber']}"/>
					</td>
					<th><label>姓名</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_name" value="%{#parameters['filter_LIKE_S_name']}"/>
					</td>
				</tr>
				<tr>
					<th><label>注册类型</label></th>
					<td>
						<s:select name="filter_EQ_S_registeredType"  value="#parameters['filter_EQ_S_registeredType']" 
							list="#{'PROTAL':'门户','GATEWAY':'网关'}" headerKey="" headerValue=""></s:select>
					</td>
					<th><label for="time">创建时间</label></th>
						<td>
							<input name="filter_GE_D_createDate" id="createDate_start" value="${param['filter_GE_D_createDate'] }" size="10" /> -
							<input name="filter_LE_D_createDate" id="createDate_end" value="${param['filter_LE_D_createDate'] }" size="10"/>
						</td>
					</th>
					<th><label>认证状态</label></th>
					<td>
						<s:select name="filter_EQ_S_certStatus" value="#parameters['filter_EQ_S_certStatus']" headerKey="" headerValue=""
							list="@com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus@values()" listKey="name()" listValue="displayName"></s:select>
						<input type="submit" class="action search right" value="查询" />
					</td>
				</tr>
			</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="page" status="loop">
			<mys:column title="序号" width="40px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="编号" value="identity" width="60px"/>
			
			<mys:column title="证件类型" width="120px">
			    <s:set name="credentialsType" value="@com.ylink.ylpay.common.project.mp.constant.CertificationType@parseOf(credentialsType)"/>
				<s:property value="#credentialsType.displayName"/>
			</mys:column>
			
			<mys:column title="证件号码" value="identityCardNumber" width="180px"/>
			<mys:column title="姓名" value="name"/>
			
			<mys:column title="注册类型" width="50px">
			    <s:set name="registeredType" value="@com.ylink.ylpay.common.project.mp.constant.RegisteredType@parseOf(registeredType)"/>
				<s:property value="#registeredType.displayName"/>
			</mys:column>
			
			<mys:column title="认证状态" width="70px">
			    <s:set name="certStatus" value="@com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus@parseOf(certStatus)"/>
				<s:property value="#certStatus.displayName"/>
			</mys:column>
			
			<mys:column title="创建时间" sortable="false" width="160px">
				<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(createDate,'yyyy-MM-dd HH:mm:ss')"/>
			</mys:column>
			<%-- 
			<mys:column title="有效" value="enabled?'是':'否'" sortable="true" width="50px"/>
			--%>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<li>
							<a href="<s:url value="/cust/person/view.do?party=personal&personal.id=%{id}"/>">查看人员</a>
						</li>
						
						<li class="section"/>
						
						<li>
							<a href="<s:url value="/mp/user/user/list.do?userVo.customerId=%{id}"/>">用户信息</a>
						</li>
					</ul>
				</div>
			</mys:column>
		</mys:table>
		
		<mys:pagination value="page"/>
	</body>
</html>