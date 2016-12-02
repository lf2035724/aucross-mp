<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>新增企业客户</title>
<script type="text/javascript" src="<s:url value="/pages/mp/cust/enterprise/operate.js" />"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/ui/i18n/jquery.ui.datepicker_zh_CN.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#licenseLegalFoundDate").datepicker({dateFormat: 'yy-mm-dd',changeYear:true});
	});
</script>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>企业客户</li>
		<li>新增企业客户</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form id="frm" action="input" method="post" enctype="multipart/form-data">
		<table class="profile">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col>
			</colgroup>
			<tr>
				<th><label class="required">企业名称</label></th>
				<td><s:textfield name="vo.name" cssClass="required default-input" maxlength="100" /></td>
				<th><label for="shortName">企业简称</label></th>
				<td><s:textfield name="vo.shortName" cssClass="default-input" maxlength="100" /></td>
			</tr>
			<tr> 
				<th><label class="requigd">英文名称</label>
				<td><s:textfield name="vo.enName" cssClass="default-input" maxlength="100"/></td>
				<th><label class="required">支付密码</label>
				<td>
					<s:password name="vo.payPassword" cssClass="required default-input" maxlength="100"/>
				</td>
			</tr>
			<tr>
				<th><label class="required">开户科目</th>
				<td colspan="3">
					<s:checkboxlist list="@com.ylink.ylpay.common.project.account.constant.CustSubject@reservesValues()"
						name="vo.subjects" listKey="value" listValue="displayName" cssClass="required"></s:checkboxlist>
				</td>
			</tr>
			<tr>
				<th><label>是否是供应链客户</label></th>
				<td colspan="1">
					<s:checkbox name="vo.isSupply"></s:checkbox>
				</td>
			</tr>
			<tr>
				<th><label class="required">联系电话</label></th>
				<td><s:textfield name="vo.phone" id="phone" cssClass="required default-input" maxlength="200" /></td>
				<th><label class="required">传真</label></th>
				<td><s:textfield name="vo.fax" id="fax" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">注册地址及邮编</label></th>
				<td><s:textfield name="vo.addrReg" id="addrReg" cssClass="required default-input" maxlength="200" /></td>
				<th><label class="required">通讯地址及邮编</label></th>
				<td><s:textfield name="vo.addrContact" id="addrContact" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">企业电子邮箱</label></th>
				<td><s:textfield name="vo.email" id="email" cssClass="required email default-input" maxlength="200" /></td>
				<th><label class="required">有权确认人姓名</label></th>
				<td><s:textfield name="vo.confirmPersonName" id="confirmPersonName" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">移动电话</label></th>
				<td><s:textfield name="vo.mobile" id="mobile" cssClass="required default-input" maxlength="200" /></td>
				<th><label class="required">办公电话</label></th>
				<td><s:textfield name="vo.officePhone" id="officePhone" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">银行卡行别</label></th>
				<td>
					<s:select list="@com.ylink.ylpay.common.project.channel.constant.BankType@values()"
						listKey="value" listValue="displayName" cssClass="required default-input"
						id="bankType" name="vo.bankType"></s:select>
				</td>
				<th><label class="required">银行卡账号</label></th>
				<td><s:textfield name="vo.accountNo" id="accountNo" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">营业执照-文件</label></th>
				<td>
					<s:file name="listBean[0].upload" cssClass="required default-input"/>
				</td>
				<th><label class="required">营业执照-注册号</label>
				<td><s:textfield name="vo.licenseNo" id="licenseNo" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">营业执照-法人</label>
				<td><s:textfield name="vo.licenseLegalPerson" id="licenseLegalPerson" cssClass="required default-input" maxlength="200" /></td>
				<th><label class="required">营业执照-注册资本</label>
				<td><s:textfield name="vo.licenseRegCapital" id="licenseRegCapital" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">营业执照-公司类型</label>
				<td><s:textfield name="vo.licenseLegalEntType" id="licenseLegalEntType" cssClass="required default-input" maxlength="200" /></td>
				<th><label class="required">营业执照-经营范围</label>
				<td><s:textfield name="vo.licenseLegalScope" id="licenseLegalScope" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">营业执照-成立日期</label>
				<td>
					<input type="text" name="vo.licenseLegalFoundDate" id="licenseLegalFoundDate" value="${vo.licenseLegalFoundDate}" class="default-input" size="10">
				</td>
				<th><label class="required">营业执照-营业期限</label>
				<td><s:textfield name="vo.licenseLegalValidTerm" id="licenseLegalValidTerm" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">组织机构-文件</label></th>
				<td>
					<s:file name="listBean[1].upload" cssClass="required default-input"/>
				</td>
				<th><label class="required">组织机构-代码</label>
				<td><s:textfield name="vo.orgNo" id="orgNo" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">组织机构-有效期</label>
				<td colspan="3"><s:textfield name="vo.orgValidTerm" id="orgValidTerm" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			
			<tr>
				<th><label class="required">地税-文件</label></th>
				<td>
					<s:file name="listBean[2].upload" cssClass="required default-input"/>
				</td>
				<th><label class="required">地税-号码</label>
				<td><s:textfield name="vo.landtaxNo" id="landtaxNo" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label>国税-文件</label></th>
				<td>
					<s:file name="listBean[4].upload" cssClass="default-input"/>
				</td>
				<th><label>国税-号码</label>
				<td><s:textfield name="vo.nationaltaxNo" id="nationaltaxNo" cssClass="default-input" maxlength="200" /></td>
				
			</tr>
			<tr>
				<th><label class="required">法人-文件</label></th>
				<td>
					<s:file name="listBean[3].upload" cssClass="required default-input"/>
				</td>
				<th><label class="required">法人-姓名</label>
				<td><s:textfield name="vo.legalpersonName" id="legalpersonName" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label class="required">法人-证件类型</label>
				<td>
					<s:select list="@com.ylink.ylpay.common.project.fund.constant.CertType@values()"
						listKey="value" listValue="displayName" cssClass="required default-input"
						id="legalpersonCertType" name="vo.legalpersonCertType"></s:select>
				</td>
				<th><label class="required">法人-证件号码</label>
				<td><s:textfield name="vo.legalpersonCertNo" id="legalpersonCertNo" cssClass="required default-input" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label>财务负责人-姓名</label>
				<td><s:textfield name="vo.financeName" cssClass="default-input" id="financeName" maxlength="200" /></td>
				<th><label>财务负责人-证件类型</label>
				<td>
					<s:select list="@com.ylink.ylpay.common.project.fund.constant.CertType@values()"
						listKey="value" listValue="displayName" 
						id="financeCertType" name="vo.financeCertType" cssClass="default-input"/>
				</td>
			</tr>
			<tr>
				<th><label>财务负责人-证件号码</label>
				<td colspan="3"><s:textfield name="vo.financeCertNo"  cssClass="default-input" id="financeCertNo" maxlength="200" /></td>
			</tr>
			<tr>
				<th><label>企业简介</label></th>
				<td colspan="3">
					<s:textarea rows="4" cols="50" name="vo.summary" cssStyle="margin-bottom:20px"/>
				</td>
			</tr>
		</table>

		<p class="submit">
			<s:submit value="保存" cssClass="action save"></s:submit>
		</p>

		<s:token />

	</s:form>

</body>