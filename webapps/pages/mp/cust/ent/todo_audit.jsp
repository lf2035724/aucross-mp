<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>企业客户银行卡审核</title>
		
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
						"bcca.auditMsg":{required:true,maxlength:200}
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
			<li>客户管理</li>
			<li>汇款实名认证</li>
			<li>待汇款银行卡</li>
			<li>审核数据</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<table class="profile">
			<caption>审核信息</caption>
			<tbody>
				<tr>
					<th><label>客户编号</label></th>
					<td><s:property value="bcca.enterprise.id"/></td>
				</tr>
				<tr>
					<th><label>客户名称</label></th>
					<td><s:property value="bcca.enterprise.name"/></td>
				</tr>
				<tr>
					<th><label>银行</label></th>
					<td>
						<s:set name="bank" value="@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(bcca.bankCard.bankType)"/>
						<s:property value="%{#bank==null?'--(未知)--':#bank.getDisplayName()}"/>
					</td>
				</tr>
				<tr>
					<th><label>银行帐户名</label></th>
					<td><s:property value="bcca.bankCard.cardName"/></td>
				</tr>
				<tr>
					<th><label>银行帐户号</label></th>
					<td><s:property value="bcca.bankCard.cardNo"/></td>
				</tr>
				<tr>
					<th><label>银行帐户创建时间</label></th>
					<td><s:property value="bcca.bankCard.createDate"/></td>
				</tr>
			</tbody>
		</table>
		
		<s:form id="profileForm" action="bcaudit" method="post">
			<s:hidden name="bcca.id"></s:hidden>
			<table class="profile">
				<caption>审核信息</caption>
				<tbody>
					<tr>
						<th><label class="required" for="desc">审核备注</label></th>
						<td><s:textarea id="desc" name="bcca.auditMsg" cols="60" rows="5"/></td>
					</tr>
				</tbody>
			</table>
			
			<p class="submit">
				<s:hidden name="accountChange.id"/>
				<s:hidden name="accountChange.loginAccount.id"/>
				<input type="submit" class="action audit" name="passed" value="审核通过"/>
				<input type="submit" class="action audit reject" name="reject" value="审核拒绝"/>
			</p>
		</s:form>
	</body>
</html>