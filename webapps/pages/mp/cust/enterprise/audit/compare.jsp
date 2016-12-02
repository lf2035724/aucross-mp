<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/ui/i18n/jquery.ui.datepicker_<s:property value="locale"/>.js"></script>
<script type="text/javascript">
function popupBill(id){
	var popup = $(id);
	var name = id + '1';
	var popup1 = $(name);
	popup.show();
	popup1.hide();
}
function closePopup(id){
	var popup = $(id);
	var name = id + '1';
	var popup1 = $(name);
	popup.hide();
	popup1.show();
}
</script>

<s:set name="originalObject" value="#request.originalObject"/>
<s:set name="newObject" value="#request.newObject"/>

<table class="profile">
	<caption>企业客户信息</caption>
	<tbody>
		<tr>
			<th><label>客户编号</label></th>
			<td colspan="3">
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'id')"/>" 
					title="<s:property value="%{#originalObject.id}"/>">
				</span>
				<s:property value="%{#newObject.id}"/>
			</td>
		</tr>
		<tr>
			<th><label>企业名称</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'name')"/>" 
					title="<s:property value="%{#originalObject.name}"/>">
				</span>
				<s:property value="%{#newObject.name}"/>
			</td>
			<th><label>企业简称</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'shortName')"/>" 
					title="<s:property value="%{#originalObject.shortName}"/>">
				</span>
				<s:property value="%{#newObject.shortName}"/>
			</td>
		</tr>
		<tr> 
			<th><label>英文名称</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'enName')"/>" 
					title="<s:property value="%{#originalObject.enName}"/>">
				</span>
				<s:property value="%{#newObject.enName}"/>
			</td>
			<th><label>开户科目</th>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<s:checkboxlist list="@com.ylink.ylpay.common.project.account.constant.CustSubject@reservesValues()"
				 value="%{#newObject.subjects}"	name="newObject.subjects" listKey="value" listValue="displayName" cssClass="required" disabled="true"></s:checkboxlist>
			</td>
		</tr>
		<tr>
			<th><label>联系电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'phone')"/>" 
					title="<s:property value="%{#originalObject.phone}"/>">
				</span>
				<s:property value="%{#newObject.phone}"/>
			</td>
			<th><label>传真</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'fax')"/>" 
					title="<s:property value="%{#originalObject.fax}"/>">
				</span>
				<s:property value="%{#newObject.fax}"/>
			</td>
		</tr>
		<tr>
			<th><label>注册地址及邮编</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'addrReg')"/>" 
					title="<s:property value="%{#originalObject.addrReg}"/>">
				</span>
				<s:property value="%{#newObject.addrReg}"/>
			</td>
			<th><label>通讯地址及邮编</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'addrContact')"/>" 
					title="<s:property value="%{#originalObject.addrContact}"/>">
				</span>
				<s:property value="%{#newObject.addrContact}"/>
			</td>
		</tr>
		<tr>
			<th><label>企业电子邮箱</label></th>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="%{#newObject.email}"/></td>
			<th><label>有权确认人姓名</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'confirmPersonName')"/>" 
					title="<s:property value="%{#originalObject.confirmPersonName}"/>">
				</span>
				<s:property value="%{#newObject.confirmPersonName}"/>
			</td>
		</tr>
		<tr>
			<th><label>移动电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'mobile')"/>" 
					title="<s:property value="%{#originalObject.mobile}"/>">
				</span>
				<s:property value="%{#newObject.mobile}"/>
			</td>
			<th><label>办公电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'officePhone')"/>" 
					title="<s:property value="%{#originalObject.officePhone}"/>">
				</span>
				<s:property value="%{#newObject.officePhone}"/>
			</td>
		</tr>
		<tr>
			<th><label>银行卡行别</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'bankType')"/>" 
					title="<s:property value="%{@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(#originalObject.bankType).displayName}"/>">
				</span>
				<s:property value="%{@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(#newObject.bankType).displayName}"/>
			</td>
			<th><label>银行卡账号</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'accountNo')"/>" 
					title="<s:property value="%{#originalObject.accountNo}"/>">
				</span>
				<s:property value="%{#newObject.accountNo}"/>
			</td>
		</tr>
		<tr>
			<th><label>营业执照-注册号</label>
			<td colspan="3">
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseNo')"/>" 
					title="<s:property value="%{#originalObject.licenseNo}"/>">
				</span>
				<s:property value="%{#newObject.licenseNo}"/>
			</td>
		</tr>
		<tr>
			<th><label>营业执照-法人</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseLegalPerson')"/>" 
					title="<s:property value="%{#originalObject.licenseLegalPerson}"/>">
				</span>
				<s:property value="%{#newObject.licenseLegalPerson}"/>
			</td>
			<th><label>营业执照-注册资本</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseRegCapital')"/>" 
					title="<s:property value="%{#originalObject.licenseRegCapital}"/>">
				</span>
				<s:property value="%{#newObject.licenseRegCapital}"/>
			</td>
		</tr>
		<tr>
			<th><label>营业执照-公司类型</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseLegalEntType')"/>" 
					title="<s:property value="%{#originalObject.licenseLegalEntType}"/>">
				</span>
				<s:property value="%{#newObject.licenseLegalEntType}"/>
			</td>
			<th><label>营业执照-经营范围</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseLegalScope')"/>" 
					title="<s:property value="%{#originalObject.licenseLegalScope}"/>">
				</span>
				<s:property value="%{#newObject.licenseLegalScope}"/>
			</td>
		</tr>
		<tr>
			<th><label>营业执照-成立日期</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseLegalFoundDate')"/>" 
					title="<s:property value="%{#originalObject.licenseLegalFoundDate}"/>">
				</span>
				<s:property value="%{#newObject.licenseLegalFoundDate}"/>
			</td>
			<th><label>营业执照-营业期限</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseLegalValidTerm')"/>" 
					title="<s:property value="%{#originalObject.licenseLegalValidTerm}"/>">
				</span>
				<s:property value="%{#newObject.licenseLegalValidTerm}"/>
			</td>
		</tr>
		<tr>
			<th><label>组织机构-代码</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'orgNo')"/>" 
					title="<s:property value="%{#originalObject.orgNo}"/>">
				</span>
				<s:property value="%{#newObject.orgNo}"/>
			</td>
			<th><label>组织机构-有效期</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'orgValidTerm')"/>" 
					title="<s:property value="%{#originalObject.orgValidTerm}"/>">
				</span>
				<s:property value="%{#newObject.orgValidTerm}"/>
			</td>
		</tr>
		<tr>
			<th><label>地税-号码</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'landtaxNo')"/>" 
					title="<s:property value="%{#originalObject.landtaxNo}"/>">
				</span>
				<s:property value="%{#newObject.landtaxNo}"/>
			</td>
			<th><label>国税-号码</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'nationaltaxNo')"/>" 
					title="<s:property value="%{#originalObject.nationaltaxNo}"/>">
				</span>
				<s:property value="%{#newObject.nationaltaxNo}"/>
			</td>
		</tr>
		<tr>
			<th><label>法人-姓名</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'legalpersonName')"/>" 
					title="<s:property value="%{#originalObject.legalpersonName}"/>">
				</span>
				<s:property value="%{#newObject.legalpersonName}"/>
			</td>
			<th><label>法人-证件类型</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'legalpersonCertType')"/>" 
					title="<s:property value="%{@com.ylink.ylpay.common.project.fund.constant.CertType@parseOf(#originalObject.legalpersonCertType).displayName}"/>">
				</span>
				<s:property value="%{@com.ylink.ylpay.common.project.fund.constant.CertType@parseOf(#newObject.legalpersonCertType).displayName}"/>
			</td>
		</tr>
		<tr>
			<th><label>法人-证件号码</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'legalpersonCertNo')"/>" 
					title="<s:property value="%{#originalObject.legalpersonCertNo}"/>">
				</span>
				<s:property value="%{#newObject.legalpersonCertNo}"/>
			</td>
			<th><label>财务负责人-姓名</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'financeName')"/>" 
					title="<s:property value="%{#originalObject.financeName}"/>">
				</span>
				<s:property value="%{#newObject.financeName}"/>
			</td>
		</tr>
		<tr>
			<th><label>财务负责人-证件类型</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'financeCertType')"/>" 
					title="<s:property value="%{@com.ylink.ylpay.common.project.fund.constant.CertType@parseOf(#originalObject.financeCertType).displayName}"/>">
				</span>
				<s:property value="%{@com.ylink.ylpay.common.project.fund.constant.CertType@parseOf(#newObject.financeCertType).displayName}"/>
			</td>
			<th><label>财务负责人-证件号码</label>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'financeCertNo')"/>" 
					title="<s:property value="%{#originalObject.financeCertNo}"/>">
				</span>
				<s:property value="%{#newObject.financeCertNo}"/>
			</td>
		</tr>
		<tr>
			<th><label>企业简介</label></th>
			<td colspan="3">
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'summary')"/>" 
					title="<s:property value="%{#originalObject.summary}"/>">
				</span>
				<s:property value="%{#newObject.summary}"/>
			</td>
		</tr>
		<tr>
			<th><label>营业执照-文件</label></th>
			<td>
				<span
					class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'licenseId')"/>"
					 onmouseover="popupBill('#licenseId')" onmouseout="closePopup('#licenseId')" title="原文件" style="float: left;vertical-align: middle;height: 300px;">
				</span>
				<div id="licenseId" style="display: none;float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#originalObject.licenseId}"/>' width="300" height="300">
					</a>
				</div>
				<div id="licenseId1" style="float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#newObject.licenseId}"/>' width="300" height="300">
					</a>
				</div>
			</td>
			<th><label>组织机构-文件</label></th>
			<td>
				<span
					class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'orgId')"/>"
					 onmouseover="popupBill('#orgId')" onmouseout="closePopup('#orgId')" title="原文件" style="float: left;vertical-align: middle;height: 300px;">
				</span>
				<div id="orgId" style="display: none;float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#originalObject.orgId}"/>' width="300" height="300">
					</a>
				</div>
				<div id="orgId1" style="float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#newObject.orgId}"/>' width="300" height="300">
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<th><label>地税-文件</label></th>
			<td>
				<span
					class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'landtaxId')"/>"
					 onmouseover="popupBill('#landtaxId')" onmouseout="closePopup('#landtaxId')" title="原文件" style="float: left;vertical-align: middle;height: 300px;">
				</span>
				<div id="landtaxId" style="display: none;float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#originalObject.landtaxId}"/>' width="300" height="300">
					</a>
				</div>
				<div id="landtaxId1" style="float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#newObject.landtaxId}"/>' width="300" height="300">
					</a>
				</div>
			</td>
			<th><label>国税-文件</label></th>
			<td>
				<span
					class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'nationaltaxId')"/>"
					 onmouseover="popupBill('#nationaltaxId')" onmouseout="closePopup('#nationaltaxId')" title="原文件" style="float: left;vertical-align: middle;height: 300px;" >
				</span>
				<div id="nationaltaxId" style="display: none;float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#originalObject.nationaltaxId}"/>' width="300" height="300">
					</a>
				</div> 
				<div id="nationaltaxId1" style="float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#newObject.nationaltaxId}"/>' width="300" height="300">
					</a>
				</div>
			</td>
		</tr>
		<tr>
			<th><label>法人-文件</label></th>
			<td colspan="3">
				<span
					class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'legalpersonId')"/>"
					 onmouseover="popupBill('#legalpersonId')" onmouseout="closePopup('#legalpersonId')" title="原文件" style="float: left;vertical-align: middle;height: 300px;">
				</span>
				<div id="legalpersonId" style="display: none;float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#originalObject.legalpersonId}"/>' width="300" height="300">
					</a>
				</div>
				<div id="legalpersonId1" style="float: left;">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{#newObject.legalpersonId}"/>' width="300" height="300">
					</a>
				</div>
			</td>
		</tr>
	</tbody>
</table>