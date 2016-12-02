<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<head>
<meta name="decorator" content="background" />
<title>修改商户</title>
<script type="text/javascript" src="<s:url value="/pages/mp/cust/custmerchant/operate.js"/>"></script>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>商户管理</li>
		<li>修改商户</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form id="frm" action="modify" method="post" enctype="multipart/form-data">
		<table class="profile">
			<tr>
				<th><label>编号</label></th>
				<td>${vo.code }<input type="hidden" name="vo.code" value="${vo.code }"/><input type="hidden" name="vo.status" value="${vo.status}"/> </td>
			</tr>
			<tr>
				<th><label class="required">名称</label></th>
				<td><s:textfield name="vo.name" cssClass="required default-input" maxlength="100" /></td>
			</tr>
			<tr>
				<th><label class="required">链接</label></th>
				<td><s:textfield name="vo.url" cssClass="required url default-input" /></td>
			</tr>
			<tr>
				<th><label class="required">手续费计费方式</label></th>
				<td>
				<s:select list="@com.ylink.ylpay.common.project.pay.constant.InvokeMode@onlineOfflines()"
					name="vo.feeInvokeMode" listKey="name()" listValue="getDisplayName()" value="vo.feeInvokeMode.name()" >
				</s:select>
			</tr>
			<tr>
				<th><label>企业客户</label></th>
				<td>${vo.cust.name }（${vo.cust.id }）
					<s:hidden name="vo.cust.id" />
<!-- 					<input type="text" id="custId" name="vo.cust.id" class="required default-input" readonly="readonly" -->
<%-- 						onclick="popupCustomer( null, null )" cssStyle="cursor: pointer;" value="${vo.cust.id }" /> --%>
<!-- 					<span class="popup" onclick="popupCustomer( null, null )">&nbsp;</span> -->
				</td>
			</tr>
			<tr>
				<th><label>机构</label></th>
				<td>${vo.agency.name }（${vo.agency.code }）
					<s:hidden name="vo.agency.code" />
				</td>
			</tr>
			<tr>
				<th><label>银行卡</label></th>
				<td>${vo.bankcard.cardName }（${vo.bankcard.cardNo }）
					<s:hidden name="vo.bankcard.cardNo" />
				</td>
			</tr>
			<tr>	
				<th><label>商户类型</label></th>
				<td>${vo.type.displayName }</td>
			</tr>
			<tr>
				<th><label>是否直投融资商户</label></th>
				<s:if test="%{vo.isFundManage == \"1\"}">
				<td>是</td>
				</s:if>
				<s:else>
				<td>否</td>
				</s:else>
			</tr>
			<tr>
				<th><label>审核状态</label></th>
				<td>${vo.auditStatus.displayName }</td>
			</tr>
		<s:if test="@com.ylink.ylpay.common.project.mp.constant.AuditStatus@AUDIT_PASS == vo.auditStatus">
			<tr>
				<th><label class="required">商户状态</label></th>
				<td>${vo.status.displayName }
				<input type="hidden"  name="vo.status" value="${vo.status }"  />
				</td>
			</tr>
		</s:if>
			<tr>
				<th><label class="required">交易类型</label></th>
				<td>
					<input type="hidden" id="merchantType" value="${vo.type }" />
					<div class="FUND sib" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.OptType@fundValues()"
							value="vo.custMerchantBusinesses.{#this.businessType.name()}"
							name="optTypeFund" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
					<div class="MALL sib" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.OptType@mallValues()"
							value="vo.custMerchantBusinesses.{#this.businessType.name()}"
							name="optTypeMall" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
				</td>
			</tr>
			<tr>
				<th><label class="required">交易接口</label></th>
				<td>
					<div class="FUND sic" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.ProjectOrderType@fundValues()"
							value="vo.merchantProjectOrders.{#this.projectOrderType.name()}"
							name="merchantProjectType" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
					<div class="MALL sic" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.ProjectOrderType@mallValues()"
							value="vo.merchantProjectOrders.{#this.projectOrderType.name()}"
							name="merchantProjectType" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
				</td>
			</tr>
			<tr>
				<th><label>银联mcc码</label></th>
				<td><s:textfield name="vo.CUPMCCCode" cssClass="default-input" maxlength="100" /></td>
			</tr>
			<tr>
				<th><label>银联商户号</label></th>
				<td><s:textfield name="vo.CUPMerchantNO" cssClass="default-input" maxlength="100" /></td>
			</tr>
			<tr>
				<th><label>银联商户简称</label></th>
				<td><s:textfield name="vo.merAbbr" cssClass="default-input" maxlength="100" /></td>
			</tr>
			<tr>
				<th><label>商户在银联的备案名称</label></th>
				<td><s:textfield name="vo.CUPMerchantName" cssClass="default-input" maxlength="100" /></td>
			</tr>
			<tr>
				<th><label>商户在银联的商户类型</label></th>
				<td>
				<s:select list="@com.ylink.ylpay.common.project.mp.constant.CUPMerchantType@values()"
					name="vo.CUPMerchantType" listKey="name()" listValue="getDisplayName()" value="vo.CUPMerchantType.name()" >
				</s:select>
			</tr>
			<tr>
				<th><label>描述</label></th>
				<td><s:textarea cssClass="default-input {maxlength: 200}" name="vo.description" /></td>
			</tr>
		</table>

		<p class="submit">
			<s:submit value="保存" cssClass="action save"></s:submit>
			<input type="button" class="action back" value="返回" onclick="backPage();" />
			<s:hidden name="vo.identity"></s:hidden>
		</p>

		<s:token />

	</s:form>

	<s:set name="status" value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@EFFECTIVE.value" />
	<input type="hidden" id="url" value="<s:url value="/customer/popup.do?customer=&customer._queryType=2&customer.status=%{#status }" />" />
	<div id="customer_popup" title="企业客户" style="display: none;">&nbsp;</div>

</body>