<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>操作日志列表</title>
</head>

<body>
	<ul class="path">
		<li>门户管理</li>
		<li>操作日志</li>
		<li>操作日志列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>
    <s:form name="list" namespace="/mp/log/logportal" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label for="ip">IP</label></th>
						<td>
							<%--<input type="text" name="filter_LIKE_S_ip" value="${param['filter_LIKE_S_ip'] }" size="20" maxlength="100" />--%>
							<s:textfield name="logPortal.ip" size="20" maxlength="100"/>
						</td>
						<th><label for="operator">客户编号</label></th>
						<td>
							<%--<input type="text" name="filter_EQ_L_operator" value="${param['filter_EQ_L_operator'] }" size="20" maxlength="100" />--%>
							<s:textfield name="logPortal.custId" size="20" maxlength="100"/>	
						</td>
						<th><label for="type">操作类型</label></th>
<%--						<td><input type="text" name="filter_LIKE_S_type." value="${param['filter_LIKE_S_type'] }" size="20" maxlength="100" /></td>--%>
						<td>
							<s:select list="@com.ylink.ylpay.common.project.mp.constant.OperateType@values()"
								name="logPortal.type" value="logPortal.type"
								headerKey="" headerValue="" listKey="value" listValue="displayName"></s:select>
						</td>
					</tr>
					<tr>
						<th><label for="operator">客户手机号</label></th>
						<td>
							<s:textfield name="logPortal.custMoblie" size="20" maxlength="100"/>	
						</td>
						<th><label for="operator">客户邮箱</label></th>
						<td>
							<s:textfield name="logPortal.custEmail" size="20" maxlength="100"/>	
						</td>
						<td colspan="2"><input type="submit" class="action search right" value="查询" /></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="50px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="操作时间" sortable="true" sortKey="time" width="160px">
			<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(time,'yyyy-MM-dd HH:mm:ss')"/>
		</mys:column>
		<mys:column title="IP" value="ip" width="120px" />
		<s:set name="user" value="@com.ylink.ylpay.project.mp.user.util.UserHelper@getUser(operator)"/>
		<mys:column title="客户编号" width="100px">
			<s:property value="#user.customerId"/>
		</mys:column>
		<mys:column title="客户手机号" width="100px">
			<s:property value="#user.mobile"/>
		</mys:column>
		<mys:column title="客户邮箱" width="100px">
			<s:property value="#user.email"/>
		</mys:column>
		<mys:column title="操作类型" value="type" width="120px" />
		<mys:column title="备注" value="description" />
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>