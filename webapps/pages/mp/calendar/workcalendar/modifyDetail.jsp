<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>工作日历初始化</title>
<script type="text/javascript" src="<s:url value="/scripts/jquery/ui/i18n/jquery.ui.datepicker_zh_CN.js"/>"></script>
<script type="text/javascript" src="<s:url value="/pages/mp/calendar/workcalendar/modifyDetail.js"/>"></script>
</head>

<body>
	<ul class="path">
		<li>门户管理</li>
		<li>工作日历修改</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<br />
	<br />
	<center>
		橘红色为非工作日.
		<div id="yearDatepicker"></div>
	</center>
	<br />
	<br />

	<input type="hidden" id="currentYear" value="${param['year'] }" />
	<input type="hidden" id="workSystem" value="${param['system'] }" />

	<div id="paramArray" style="display: none;">
		<s:form id="frm" action="modify" method="post"></s:form>
		<c:forEach items="${vos }" var="list">
			<div key="${list.identity }" value="${list.workDayType }"></div>
		</c:forEach>
	</div>

</body>
</html>