<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
	
	<head>
		<meta name="decorator" content="background"/>
		
		<title>供应链协议详情</title>
		
	
		<script type="text/javascript">
			$(document).ready(function(){
				
			});
			
		</script>
	</head>
	
<body>
	<ul class="path">
		<li>客户管理</li>
		<li>供应链管理</li>
		<li>供应链协议详情</li>
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
			<th><label>协议号</label></th>
			<td>${contractDTO.contractSeq}</td>
			<th><label>协议类型</label></th>
			<s:if test="%{contractDTO.contractType == 1}">
				<td>两方协议</td>
			</s:if>
			<s:else>
				<td>三方协议</td>
			</s:else>
		</tr>
		<tr>
			<th><label>协议发起人类型</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SponsorType@parseOf(contractDTO.sponsorType).displayName" /></td>
			<th><label>协议发起时间</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.startTime,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
		<tr>
			<th><label>协议创建时间</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.createTime,'yyyy-MM-dd HH:mm:ss')"/></td>
			<th><label>债权方客户ID</label></th>
			<td>${contractDTO.creditorCustId}</td>
		</tr>
		<tr>
			<th><label>债权方客户名称</label></th>
			<td>${contractDTO.creditorCustName}</td>
			<th><label>债权方签约时间</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.creditorSignTime,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
		<tr>
			<th><label>债权方法人</label></th>
			<td>${contractDTO.creditorLegalPerson}</td>
			<th><label>债权人证钱账户</label></th>
			<td>${contractDTO.creditorEmail}</td>
		</tr>
		<tr>
			<th><label>债权人注册地址及邮编</label></th>
			<td>${contractDTO.creditorAddrReg}</td>
			<th><label>债权人联系电话</label></th>
			<td>${contractDTO.creditorPhone}</td>
		</tr>
		<tr>
			<th><label>债权人签约状态</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SignStatus@parseOf(contractDTO.creditorSignStatus).displayName" /></td>
			<th><label>债权人电子签名</label></th>
			<td>${contractDTO.creditorSignEncry}</td>
		</tr>
		<tr>
			<th><label>债务方客户ID</label></th>
			<td>${contractDTO.debtorCustId}</td>
			<th><label>债务方客户名称</label></th>
			<td>${contractDTO.debtorCustName}</td>
		</tr>
		<tr>
			<th><label>债务方签约时间</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.debtorSignTime,'yyyy-MM-dd HH:mm:ss')"/></td>
			<th><label>债务方法人</label></th>
			<td>${contractDTO.debtorLegalPerson}</td>
		</tr>
		<tr>
			<th><label>债务人证钱账户</label></th>
			<td>${contractDTO.debtorEmail}</td>
			<th><label>债务人注册地址及邮编</label></th>
			<td>${contractDTO.debtorAddrReg}</td>
		</tr>
		<tr>
			<th><label>债务人联系电话</label></th>
			<td>${contractDTO.debtorPhone}</td>
			<th><label>债务人签约状态</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SignStatus@parseOf(contractDTO.debtorSignStatus).displayName" /></td>
		</tr>
		<tr>
			<th><label>债务人电子签名</label></th>
			<td>${contractDTO.debtorSignEncry }</td>
			<th><label>受让方方客户ID</label></th>
			<td>${contractDTO.acceptorCustId}</td>
		</tr>
		<tr>
			<th><label>受让方客户名称</label></th>
			<td>${contractDTO.acceptorCustName}</td>
			<th><label>受让方签约时间</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.acceptorSignTime,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
		<tr>
			<th><label>受让方法人</label></th>
			<td>${contractDTO.acceptorLegalPerson}</td>
			<th><label>受让方证钱账户</label></th>
			<td>${contractDTO.acceptorEmail}</td>
		</tr>
		<tr>
			<th><label>受让方注册地址及邮编</label></th>
			<td>${contractDTO.acceptorAddrReg}</td>
			<th><label>受让方联系电话</label></th>
			<td>${contractDTO.acceptorPhone}</td>
		</tr>
		<tr>
			<th><label>受让方签约状态</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SignStatus@parseOf(contractDTO.acceptorSignStatus).displayName" /></td>
			<th><label>清偿款支付成功返回的tradeID</label></th>
			<td>${contractDTO.clearPayTradeId}</td>
		</tr>
		<tr>
			<th><label>受让方电子签名</label></th>
			<td>${contractDTO.acceptorSignEncry}</td>
			<th><label>受让金额</label></th>
			<td>
				<s:text name="format.money">
					<s:param value="contractDTO.transferFeeMoney/100d"></s:param>
				</s:text>
			</td>
		</tr>
		<tr>
			<th><label>协议状态</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.ContractStatus@parseOf(contractDTO.contractStatus).displayName" /></td>
			<th><label>执行状态</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.supplychain.constant.ExecuteStatus@parseOf(contractDTO.executeStatus).displayName" /></td>
		</tr>
		<tr>
			<th><label>标的产生日期</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.tenderCreateDate,'yyyy-MM-dd HH:mm:ss')"/></td>
			<th><label>标的偿还日期</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.tenderRepayDate,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
		<tr>
			<th><label>标的价格</label></th>
			<td>
				<s:text name="format.money">
					<s:param value="contractDTO.tenderPrice/100d"></s:param>
				</s:text>
			</td>
			<th><label>标物的描述</label></th>
			<td>${contractDTO.tenderGoodsDesc}</td>
		</tr>
		<tr>
			<th><label>标的转让价格</label></th>
			<td>
				<s:text name="format.money">
					<s:param value="contractDTO.tenderTransferPrice/100d"></s:param>
				</s:text>
			</td>
			<th><label>标的转让支付日期</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.tenderTransferPayDate,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
		<tr>
			<th><label>标的转让支付日期(实际)</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.tenderTransferPaySucDate,'yyyy-MM-dd HH:mm:ss')"/></td>
			<th><label>标的转让清偿日期</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.tenderTransferClearDate,'yyyy-MM-dd HH:mm:ss')"/></td>
		</tr>
		<tr>
			<th><label>标的转让清偿日期(实际)</label></th>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(contractDTO.tenderTransferClearSucDate,'yyyy-MM-dd HH:mm:ss')"/></td>
			<th><label>转让款支付流水</label></th>
			<td>${contractDTO.transferPaySn}</td>
		</tr>
		<tr>
			<th><label>转让款支付成功返回的tradeID</label></th>
			<td>${contractDTO.transferPayTradeId}</td>
			<th><label>清偿款支付流水</label></th>
			<td>${contractDTO.clearPaySn}</td>
		</tr>
	</table>

	<p class="submit">
		<input type="button" class="action back" value="返回" onclick="backPage();" />
	</p>
</body>