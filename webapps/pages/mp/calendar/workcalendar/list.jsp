<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>工作日历列表</title>
</head>
<body>
	<ul class="path">
		<li>基础管理</li>
		<li>工作日历</li>
		<li>工作日历列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form name="list" method="post">
		<table class="profile">
			<tr>
				<th><label for="filter_LIKESTART_S_identity">日期</label></th>
				<td>
					<input type="text" name="filter_LIKESTART_S_identity" value="${param['filter_LIKESTART_S_identity'] }" size="40" maxlength="100" />
				</td>
				<th><label for="workDayType">是否工作日</label></th>
				<td>
					<s:select list="@com.ylink.ylpay.common.project.mp.constant.WorkDayType@values()"
						headerKey="" headerValue="" listKey="name()" listValue="getDisplayName()"
						name="workDayType" value="#parameters['workDayType']"></s:select>
				</td>
				<th><label for="workSystem">所属系统</label></th>
				<td>
					<s:select list="@com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType@values()"
						headerKey="" headerValue="" listKey="name()" listValue="getDisplayName()"
						name="system" value="#parameters['system']"></s:select>
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="50px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="系统" value="workSystem" sortable="true" width="100px" />
		<mys:column title="日期" value="identity" sortable="true" width="200px" />
		<mys:column title="星期" value="week" width="200px" />
		<mys:column title="是否工作日" value="workDayType" sortable="true" width="200px" />
		<shiro:hasPermission name="MP_CALENDAR_WORKCALENDAR_EDITDETAIL">
		<mys:column title="操作" width="50px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<shiro:hasPermission name="MP_CALENDAR_WORKCALENDAR_GOTOMODIFY">
					<li>
					<a href="
						<s:url value="/mp/calendar/workcalendar/editDetail.do?vo.identity=%{identity}&vo.workSystemTypeName=%{workSystem.value}">
							<s:param name="systemSearch" value="#parameters['system']"></s:param>   
						    <s:param name="workDayTypeSearch" value="#parameters['workDayType']"></s:param>   
						    <s:param name="identitySearch" value="#parameters['filter_LIKESTART_S_identity']"></s:param>
						    <s:param name="pageNumber" value="#parameters['page.number']"></s:param> 
						</s:url>"
					>
					修改
					</a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</mys:column>
		</shiro:hasPermission>
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>