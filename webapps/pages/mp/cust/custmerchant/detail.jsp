<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<head>
<meta name="decorator" content="background" />
<title>商户明细</title>
<%-- <script type="text/javascript" src="<s:url value="/pages/mp/cust/custmerchant/operate.js"/>"></script> --%>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>商户管理</li>
		<li>商户明细</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<table class="profile">
		<caption>商户详情</caption>
		<colgroup>
			<col class="element" width="15%" />
			<col class="element" width="30%" />
			<col class="element" width="15%" />
			<col class="element" />
		</colgroup>
		<tr>
			<th><label>商户名称</label></th>
			<td>${vo.name }</td>
			<th><label>商户编号</label></th>
			<td>${vo.code }</td>
		</tr>
		<tr>
			<th><label>企业用户名称</label></th>
			<td>${vo.cust.name }</td>
			<th><label>企业用户编号</label></th>
			<td>${vo.cust.id }</td>
		</tr>
		<tr>
			<th><label>机构名称</label></th>
			<td>${vo.agency.name }</td>
			<th><label>机构编号</label></th>
			<td>${vo.agency.code }</td>
		</tr>
		<tr>
			<th><label>商户类型</label></th>
			<td>${vo.type.displayName }</td>
			<th><label>链接</label></th>
			<td>${vo.url }</td>
		</tr>
		<tr>
			<th><label>商户状态</label></th>
			<td>${vo.status.displayName }</td>
			<th><label>审核状态</label></th>
			<td>${vo.auditStatus.displayName }</td>
		</tr>
		<tr>
			<th><label>银联商户号</label></th>
			<td>${vo.CUPMerchantNO }</td>
			<th><label>商户简称</label></th>
			<td>${vo.merAbbr }</td>
		</tr>
		<tr>
			<th><label>银联mcc码</label></th>
			<td>${vo.CUPMCCCode }</td>
			<th><label>是否直投融资商户</label></th>
				<s:if test="%{vo.isFundManage == \"1\"}">
				<td>是</td>
				</s:if>
				<s:else>
				<td>否</td>
				</s:else>
		</tr>
		<tr>
			<th><label>交易类型</label></th>
			<td>
				<s:select list="vo.custMerchantBusinesses" listValue="#this.businessType.displayName"
					multiple="multiple" size="5" cssStyle="width:200px; height: 100px;"></s:select>
			</td>
			<th><label>描述</label></th>
			<td>${vo.description }</td>
		</tr>
		<tr>
			<th><label>交易接口</label></th>
			<td>
				<s:select list="vo.merchantProjectOrders" listValue="#this.projectOrderType.displayName"
					multiple="multiple" size="5" cssStyle="width:200px; height: 100px;"></s:select>
			</td>
		</tr>
	</table>
	
	<table class="profile">
		<caption>商户银行卡</caption>
		<colgroup>
			<col class="element" width="15%" />
			<col class="element" width="30%" />
			<col class="element" width="15%" />
			<col class="element" />
		</colgroup>
		<tr>
			<th><label>行别</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(vo.bankcard.bankType).displayName" /></td>
			<th><label>账户类型</label></th>
			<td>${vo.bankcard.accountType.name }</td>
		</tr>
		<tr>
			<%-- 
			<th><label>银行卡类型</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.portal.constant.CardType@returnEnum(vo.bankcard.cardType).displayName" /></td>
			--%>
			<th><label>银行户名</label></th>
			<td><s:property value="vo.bankcard.cardName"/></td>
			<th><label>银行卡号</label></th>
			<td>${vo.bankcard.cardNo }</td>
		</tr>
	</table>

	<p class="submit">
		<input type="button" class="action back" value="返回" onclick="backPage();" />
	</p>

</body>