<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<head>
<meta name="decorator" content="background" />

<title>导入数据</title>

<script type="text/javascript">
			$(document).ready(function(){

				
			});
		</script>
</head>

<body>
	<ul class="path">
		<li>业务管理</li>
		<li>工单管理</li>
		<li>工单导入</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form id="profile_form" enctype="multipart/form-data" action="importWorkOrder"
		namespace="/mp/workorder" method="post">
		<table class="profile">
			<tbody>
				<tr>
					<th><label for="upload" class="required">工单文件(excel)</label></th>
					<td><s:file name="upload" id="upload" size="10" /></td>
				</tr>
			</tbody>
		</table>
		<s:token />
		<p class="submit">
			<input id="submit" type="submit" class="action save" name="Submit"
				value="导入数据" />
		</p>
	</s:form>
</body>