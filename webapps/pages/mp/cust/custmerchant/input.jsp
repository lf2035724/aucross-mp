<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<head>
<meta name="decorator" content="background" />
<title>新增商户</title>
<script type="text/javascript" src="<s:url value="/pages/mp/cust/custmerchant/operate.js" />"></script>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>商户管理</li>
		<li>创建商户</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form id="frm" action="input" method="post" enctype="multipart/form-data">
		<table class="profile">
			<tr>
				<th><label class="required">名称</label></th>
				<td><s:textfield name="vo.name" cssClass="required default-input" maxlength="100" /></td>
			</tr>
			<tr>
				<th><label class="required">链接</label></th>
				<td><s:textfield name="vo.url" cssClass="required url default-input" /></td>
			</tr>
			<tr>
				<th><label class="required">企业客户</label></th>
				<td>
					<span class="popup" onclick="popupCustomer( null, null )">&nbsp;</span>
					<span id="custContent"></span>
					<input type="hidden" id="custId" name="vo.cust.id" class="required" />
				</td>
			</tr>
			<tr>
				<th><label class="required">机构</label></th>
				<td>
					<span class="popup" onclick="popupAgency( null, null )">&nbsp;</span>
					<span id="agencyContent"></span>
					<input type="hidden" id="agencyId" name="vo.agency.identity" class="required" />
				</td>
			</tr>
			<tr>
				<th><label class="required">银行卡</label></th>
				<td>
					<select id="bankcard" name="vo.bankcard.identity" class="default-input required">
						<option value=""></option>
					</select>
				</td>
			</tr>
			<tr>
				<th><label class="required">商户类型</label></th>
				<td>
					<s:select cssClass="default-input required" list="@com.ylink.ylpay.common.project.mp.constant.CustType@values()"
						id="merchantType" name="vo.type" listKey="name()" listValue="getDisplayName()"></s:select>
				</td>
			</tr>
			<tr>
				<th><label class="required">是否直投融资商户</label></th>
				<td>
					<s:checkbox name="vo.isFundManage"></s:checkbox>
				</td>
			</tr>
			<tr>
				<th><label class="required">交易类型</label></th>
				<td>
					<div class="FUND sib" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.OptType@fundValues()"
							name="optTypeFund" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
					<div class="MALL sib" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.OptType@mallValues()"
							name="optTypeMall" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
					<div class="OTCBB sib" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.OptType@otcbbValues()"
							name="optTypeOtcbb" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
				</td>
			</tr>
			<tr>
				<th><label class="required">交易接口</label></th>
				<td>
					<div class="FUND sic" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.ProjectOrderType@fundValues()"
							name="merchantProjectOrders" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
					<div class="MALL sic" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.ProjectOrderType@mallValues()"
							name="merchantProjectOrders" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
					<div class="OTCBB sic" style="display: none;">
						<s:checkboxlist list="@com.ylink.ylpay.common.project.mp.constant.ProjectOrderType@otcbbValues()"
							name="merchantProjectOrders" listKey="name()" listValue="getDisplayName()"></s:checkboxlist>
					</div>
				</td>
			</tr>
			<tr>
				<th><label>手续费收取方式</label></th>
				<td>
					<s:select name="vo.feeInvokeMode" 
						list="@com.ylink.ylpay.common.project.pay.constant.InvokeMode@onlineOfflines()"
						listKey="name()" listValue="getDisplayName()"/>
				</td>
			</tr>
			<tr>
				<th><label>描述</label></th>
				<td><s:textarea cssClass="default-input {maxlength: 200}" name="vo.description" /></td>
			</tr>
		</table>

		<p class="submit">
			<s:submit value="保存" cssClass="action save"></s:submit>
		</p>

		<s:token />

	</s:form>

	<s:set name="status" value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@EFFECTIVE.getValue()" />
	<input type="hidden" id="url" value="<s:url value="/customer/popup.do?customer=&customer._queryType=2&customer.status=%{#status }" />" />
	<div id="customer_popup" title="企业客户" style="display: none;">&nbsp;</div>

	<s:set name="agencyStatus" value="@com.ylink.ylpay.common.project.mp.constant.StatusMark@VALID.getValue()" />
	<input type="hidden" id="agencyUrl" value="<s:url value="/mp/cust/agency/popup.do?page.size=10" />" />
	<div id="agency_popup" title="机构" style="display: none;">&nbsp;</div>

</body>