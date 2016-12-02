<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
	<meta name="decorator" content="background" />
	<title>融资供给信息审核</title>
	<script language="javascript" src="<%= request.getContextPath() %>/scripts/jquery/jquery.cookie.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("#addForm").validate({
					submitHandler: function(form) {
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
		<li>供应链管理</li>
		<li>融资供给信息审核</li>
	</ul>
	<%@ include file="/pages/common/messages.jsp"%>
	<table class="profile">
		<caption>融资供给信息</caption>
		<colgroup>
			<col class="element" width="15%" />
			<col class="element" width="30%" />
			<col class="element" width="15%" />
			<col class="element" />
		</colgroup>
		<tr>
			<th><label>消息序号</label></th>
			<td>${capitalSupplyDTO.messageSeq}</td>
			<th><label>供给方客户号</label></th>
			<td>${capitalSupplyDTO.supplyCustId}</td>
		</tr>
		<tr>
			<th><label>供给方客户名称</label></th>
			<td>${capitalSupplyDTO.supplyCustName}</td>
			<th><label>产品名称</label></th>
			<td>${capitalSupplyDTO.productName}</td>
		</tr>
		<tr>
			<th><label>消息类型</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.MessageType@parseOf(capitalSupplyDTO.messageType).displayName" /></td>
			<th><label>申请条件</label></th>
			<td>${capitalSupplyDTO.applyCondition}</td>
		</tr>
		<tr>
			<th><label>资金额度</label></th>
			<td>
				<s:text name="format.money">
					<s:param value="capitalSupplyDTO.amount/100d"></s:param>
				</s:text>
			</td>
			<th><label>期限单</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.LimitUnit@parseOf(capitalSupplyDTO.limitUnit).displayName" /></td>
		</tr>
		<tr>
			<th><label>期限</label></th>
			<td>${capitalSupplyDTO.limit}</td>
			<th><label>利率</label></th>
			<td>${capitalSupplyDTO.rate}</td>
		</tr>
		<tr>
			<th><label>使用方式</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.UseCycleType@parseOf(capitalSupplyDTO.useCycleType).displayName" /></td>
			<th><label>联系人</label></th>
			<td>${capitalSupplyDTO.contactPersonName}</td>
		</tr>
		<tr>
			<th><label>联系方式</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.ContactType@parseOf(capitalSupplyDTO.contactType).displayName" /></td>
			<th><label>邮件</label></th>
			<td>${capitalSupplyDTO.conntactEmail}</td>
		</tr>
		<tr>
			<th><label>电话</label></th>
			<td>${capitalSupplyDTO.conntactPhone}</td>
			<th><label>预约次数</label></th>
			<td>${capitalSupplyDTO.reserverTimes}</td>
		</tr>
		<tr>
			<th><label>发布时间</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(capitalSupplyDTO.createDate,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
	</table>
	<s:form id="addForm" action="supplyMessageAudit" namespace="/mp/cust/supplychain" method="post">
			<p class="submit">
				<s:hidden name="capitalSupplyDTO.messageSeq"/>
				<input type="submit" class="action audit" name="passed" value="审核通过"/>
				<input type="submit" class="action audit reject" name="reject" value="审核拒绝"/>
				<input type="button" class="action back" value="返回" onclick="backPage();" />
			</p>
	</s:form>
</body>
</html>