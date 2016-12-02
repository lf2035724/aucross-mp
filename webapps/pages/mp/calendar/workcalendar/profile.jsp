<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<head>
<meta name="decorator" content="background" />
<title>修改工作日历</title>
<script type="text/javascript" src="<s:url value="/scripts/jquery/ui/i18n/jquery.ui.datepicker_zh_CN.js"/>"></script>
<script type="text/javascript" src="<s:url value="/pages/mp/calendar/workcalendar/modifyDetail.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
	
		$("#workcalendar_from").validate({
			 submitHandler : function(form) {
						$.lightssh.checkAuth(
						{'contextPath': path
						,'checkPassword': true
						,'targetUrl':path+'/mp/calendar/workcalendar/edit.do'}
						,function(succes,ticket,msg){ 
							if( !succes )
								return;
							if( ticket != null || ticket != '' ){
								$('<input>').attr({
								    type:'hidden',name:'auth.ticket',value: ticket,
								}).appendTo( form );
							}
							form.submit(); 
						});
					}
		});
	});
</script>
</head>

<body>
	<ul class="path">
		<li>门户管理</li>
		<li>修改工作日历</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form id="workcalendar_from" action="edit" method="post">
		<table class="profile">
			<tr>
				<th><label>系统</label></th>
				<td>
				${vo.workSystem.displayName }
				</td>
			</tr>
			<tr>
				<th><label>日期</label></th>
				<td>${vo.identity }</td>
			</tr>
			<tr>
				<th><label>星期</label></th>
				<td>${vo.week }</td>
			</tr>
			<tr>
				<th><label>工作日状态</label></th>
				<td>
					<s:radio list="@com.ylink.ylpay.common.project.mp.constant.WorkDayType@values()"
						listKey="name()" listValue="getDisplayName()"
						name="vo.workDayTypeName" value="vo.workDayTypeName"></s:radio>
					<s:textfield type="hidden" name="vo.workSystemTypeName" />
					<s:textfield type="hidden" name="systemSearch" />
					<s:textfield type="hidden" name="workDayTypeSearch" />
					<s:textfield type="hidden" name="identitySearch" />
					<s:textfield type="hidden" name="pageNumber" />
				</td>
			</tr>
		</table>

		<p class="submit">
			<s:submit value="保存" cssClass="action save"></s:submit>
			<s:hidden name="vo.identity"></s:hidden>
		</p>

		<s:token />

	</s:form>
</body>