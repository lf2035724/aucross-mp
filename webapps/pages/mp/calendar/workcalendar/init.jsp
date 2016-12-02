<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>工作日历初始化</title>
<script type="text/javascript" src="<s:url value="/scripts/jquery/ui/i18n/jquery.ui.datepicker_zh_CN.js"/>"></script>
<script type="text/javascript">
$( function() {
	$( '#year' ).datepicker( {
		dateFormat: 'yy',
		changeMonth: false
	} );

	$( '#frm' ).validate();

	$( '#subBtn' ).click( function() {
		$( '#frm' )[ 0 ].submit();
		// 加载中.
		$.loading();
	} );
} );
</script>
</head>

<body>
	<ul class="path">
		<li>基础管理</li>
		<li>工作日历</li>
		<li>初始化日历</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form id="frm" action="init" method="post">
		<table class="profile">
			<colgroup>
				<col width="10%">
				<col width="30%">
				<col>
			</colgroup>

			<tr>
				<th><label for="year">年份</label></th>
				<td>
					<s:textfield id="year" name="year" size="20" maxlength="4" cssClass="required" />
				</td>
				<th><label for="system">系统</label></th>
				<td>
					<s:select list="@com.ylink.ylpay.common.project.mp.constant.WorkCalendarSystemType@values()"
						headerKey="" headerValue="" listKey="name()" listValue="getDisplayName()"
						name="system" value="#parameters['system']"></s:select>
				</td>
				<td><input type="button" id="subBtn" class="action save" value="初始化" /></td>
			</tr>
		</table>
	</s:form>

</body>
</html>