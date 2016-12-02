<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
<s:set name="originalObject" value="#request.originalObject"/>
<s:set name="newObject" value="#request.newObject"/>
<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>审核商户数据</title>
		
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
			<li>客户管理</li>
			<li>商户修改待审核列表</li>
			<li>审核商户数据</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<table class="profile">
			<caption>被审核商户信息</caption>
			<tbody>
		<tr>
			<th><label>编号</label></th>
			<td>
				<s:property value="custMerchantChange.custMerchant.code"/>
			</td>
		</tr>	
		<tr>
			<th><label>名称</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(custMerchantChange.custMerchant,custMerchantChange,'name')"/>" 
					title="<s:property value="custMerchantChange.custMerchant.name"/>">
				</span>
				<s:property value="custMerchantChange.name"/>
			</td>
		</tr>
		<tr>
			<th><label>手续费计费方式</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(custMerchantChange.custMerchant,custMerchantChange,'feeInvokeMode')"/>" 
					title="<s:property value="custMerchantChange.custMerchant.feeInvokeMode.getDisplayName()"/>">
				</span>
				<s:property value="custMerchantChange.feeInvokeMode"/>
			</td>
		</tr>
		<tr>
			<th><label>链接</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(custMerchantChange.custMerchant,custMerchantChange,'url')"/>" 
					title="<s:property value="custMerchantChange.custMerchant.url"/>">
				</span>
				<s:property value="custMerchantChange.url"/>		
			</td>
		</tr>
		<tr>
			<th><label>企业客户</label></th>
			<td>
				<s:property value="custMerchantChange.custMerchant.cust.name"/>(  <s:property value="custMerchantChange.custMerchant.cust.id"/>  )
			</td>
		</tr>
		<tr>
			<th><label>银行卡</label></th>
			<td>
				<s:property value="custMerchantChange.custMerchant.bankcard.cardName"/>(  <s:property value="custMerchantChange.custMerchant.bankcard.cardNo"/>  )
			</td>
		</tr>
		<tr>
			<th><label>机构</label></th>
			<td>
				<s:property value="custMerchantChange.custMerchant.agency.name"/>(  <s:property value="custMerchantChange.custMerchant.agency.code"/>  )
			</td>
		</tr>
		<tr>
			<th><label>商户类型</label></th>
			<td><s:property value="custMerchantChange.custMerchant.type.displayName"/></td>
		</tr>
		<tr>
			<th><label>是否直投融资商户</label></th>
			<s:if test="%{custMerchantChangevo.custMerchant.isFundManage == \"1\"}">
				<td>是</td>
				</s:if>
				<s:else>
				<td>否</td>
				</s:else>
		</tr>
		<tr>
			<th><label>修改前状态</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(custMerchantChange.custMerchant,custMerchantChange,'status','custStatus')"/>" 
					title="<s:property value="custMerchantChange.custMerchant.status"/>"
				</span>
				<s:property value="custMerchantChange.custStatus"/>				
			</td>
		</tr>
		 <tr>
			<th><label>交易类型</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(optTypeVo,optTypeVo,'typeo','typen')"/>" 
					title="<s:property value="optTypeVo.typeo"/>">
				</span>
				<s:property value="optTypeVo.typen"/>		
			</td>
					
		</tr>
		<tr>
			<th><label>修改前描述</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(custMerchantChange.custMerchant,custMerchantChange,'description')"/>" 
					title="<s:property value="custMerchantChange.custMerchant.description"/>">
				</span>
				<s:property value="custMerchantChange.description"/>		
			</td>
		</tr>
		</table>
		<table class="profile">
			<caption>操作信息</caption>
			<tbody>
				<tr>
					<th><label for="desc">操作人</label></th>
					<td><s:property value="custMerchantChange.operator.loginName"/></td>
				</tr>
				<tr>
					<th><label>操作类型</label></th>
					<td><s:property value="custMerchantChange.type"/></td>
				</tr>
				<tr>
					<th><label>操作时间</label></th>
					<td><s:property value="custMerchantChange.createdTime"/></td>
				</tr>
			</tbody>
		</table>
		
		<s:form id="profileForm" action="audit" namespace="/mp/cust/custmerchant" method="post">
			<table class="profile">
				<caption>审核信息</caption>
				<tbody>
					<tr>
						<th><label class="required" for="desc">审核备注</label></th>
						<td><s:textarea id="desc" name="vo.description" cols="60" rows="5"/></td>
					</tr>
				</tbody>
			</table>
			
			<p class="submit">
				<input type="hidden" name="vo.code"  value="${custMerchantChange.id}">
				<input type="submit" class="action audit" name="passed" value="审核通过"/>
				<input type="submit" class="action audit reject" name="reject" value="审核拒绝"/>
			</p>
		</s:form>
	</body>
</html>