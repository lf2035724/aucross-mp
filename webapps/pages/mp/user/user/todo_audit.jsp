<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>审核用户数据</title>
		
		<script language="javascript" src="<%= request.getContextPath() %>/scripts/jquery/jquery.cookie.js"></script>
		<script language="javascript" src="<%= request.getContextPath() %>/scripts/jquery/plugins/treeview/jquery.treeview.js"></script>
		<link rel="stylesheet" href="<%= request.getContextPath() %>/scripts/jquery/plugins/treeview/jquery.treeview.css" type="text/css">
		
		<script type="text/javascript">
			
			$(document).ready(function(){
				/**
				 * 数据校验
				 */
				$("#profileForm").validate({
					rules:{
						"accountAudit.description":{required:true,maxlength:200}
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
			<li>门户管理</li>
			<li>用户待审核列表</li>
			<li>审核用户数据</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<table class="profile">
			<caption>被审核人员信息</caption>
			<tbody>
		<tr>
			<th><label>名称</label></th>
			<td>
				<s:property value="userChange.user.loginName"/>
			</td>
		</tr>
		<tr>
			<th><label>类型</label></th>
			<td>
			<s:property value="userChange.user.userType"/>				
			</td>
		</tr>
		<tr>
			<th><label>创建时间</label></th>
			<td>
				<s:property value="userChange.user.createDate"/>
			</td>
		</tr>
		<tr>
			<th><label>电子邮箱</label></th>
			<td>
				<s:property value="userChange.user.email"/>
			</td>
		</tr>
		<tr>
			<th><label>状态</label></th>
			<td>
				<s:if test="userChange.user.enabled">启用</s:if>
				<s:else>禁用</s:else>
			</td>
		</tr>
		<tr>
			<th><label>描述</label></th>
			<td>
				<s:property value="userChange.user.description"/>
			</td>
		</tr>
		</table>
		<table class="profile">
			<caption>操作信息</caption>
			<tbody>
				<tr>
					<th><label for="desc">操作人</label></th>
					<td><s:property value="userChange.operator.loginName"/></td>
				</tr>
				<tr>
					<th><label>操作类型</label></th>
					<td><s:property value="userChange.type"/></td>
				</tr>
				<tr>
					<th><label>操作时间</label></th>
					<td><s:property value="userChange.createdTime"/></td>
				</tr>
				<tr>
					<th><label>操作备注</label></th>
					<td><s:property value="userChange.description"/></td>
				</tr>
			</tbody>
		</table>
		
		<s:form id="profileForm" action="audit" namespace="/mp/user/user" method="post">
			<table class="profile">
				<caption>审核信息</caption>
				<tbody>
					<tr>
						<th><label class="required" for="desc">审核备注</label></th>
						<td><s:textarea id="desc" name="description" cols="60" rows="5"/></td>
					</tr>
				</tbody>
			</table>
			
			<p class="submit">
				<input type="hidden" name="userChangeId"  value="${userChange.id}">
				<input type="submit" class="action audit" name="passed" value="审核通过"/>
				<input type="submit" class="action audit reject" name="reject" value="审核拒绝"/>
			</p>
		</s:form>
	</body>
</html>