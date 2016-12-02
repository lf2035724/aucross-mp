<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
	
	<head>
		<meta name="decorator" content="background"/>
		
		<title>融资需求信息详情</title>
		
	
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
			
		</script>
	</head>
	
<body>
	<ul class="path">
		<li>客户管理</li>
		<li>供应链管理</li>
		<li>融资需求信息详情</li>
	</ul>
	
	<%@ include file="/pages/common/messages.jsp" %>
	
	<table class="profile">
		<colgroup>
			<col class="element" width="15%" />
			<col class="element" width="30%" />
			<col class="element" width="15%" />
			<col class="element" />
		</colgroup>
		<tr>
			<th><label>消息序号</label></th>
			<td>${capitalRequirementsDTO.messageSeq}</td>
			<th><label>客户号</label></th>
			<td>${capitalRequirementsDTO.requirementsCustId}</td>
		</tr>
		<tr>
			<th><label>客户名称</label></th>
			<td>${capitalRequirementsDTO.requirementsCustName}</td>
			<th><label>联系人名称</label></th>
			<td>${capitalRequirementsDTO.contactPersonName}</td>
		</tr>
		<tr>
			<th><label>资金用途</label></th>
			<td>${capitalRequirementsDTO.useOfFunds}</td>
			<th><label>担保条件</label></th>
			<td>${capitalRequirementsDTO.guaranteeCondition}</td>
		</tr>
		<tr>
			<th><label>资金额度</label></th>
			<td>
				<s:text name="format.money">
					<s:param value="capitalRequirementsDTO.amount/100d"></s:param>
				</s:text>
			</td>
			<th><label>期限单</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.LimitUnit@parseOf(capitalRequirementsDTO.limitUnit).displayName" /></td>
		</tr>
		<tr>
			<th><label>期限</label></th>
			<td>${capitalRequirementsDTO.limit}</td>
			<th><label>利率</label></th>
			<td>${capitalRequirementsDTO.rate}</td>
		</tr>
		<tr>
			<th><label>使用方式</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.UseCycleType@parseOf(capitalRequirementsDTO.useCycleType).displayName" /></td>
		</tr>
		<tr>
			<th><label>联系方式</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.ContactType@parseOf(capitalRequirementsDTO.contactType).displayName" /></td>
			<th><label>邮件</label></th>
			<td>${capitalRequirementsDTO.conntactEmail}</td>
		</tr>
		<tr>
			<th><label>电话</label></th>
			<td>${capitalRequirementsDTO.conntactPhone}</td>
			<th><label>预约次数</label></th>
			<td>${capitalRequirementsDTO.reserverTimes}</td>
		</tr>
		<tr>
			<th><label>发布时间</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(capitalRequirementsDTO.publishDate,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
	</table>

	<p class="submit">
		<input type="button" class="action back" value="返回" onclick="backPage();" />
	</p>
</body>