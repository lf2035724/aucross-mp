<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>>企业客户明细</title>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>企业客户</li>
		<li>企业客户明细</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<table class="profile">
		<colgroup>
			<col class="element" width="15%" />
			<col class="element" width="30%" />
			<col class="element" width="15%" />
			<col class="element" />
		</colgroup>
		<tr>
			<th><label>企业简称</label></th>
			<td>${vo.shortName }</td>
			<th><label>英文名称</label></th>
			<td>${vo.enName }</td>
		</tr>
		<tr>
			<th><label>注册类型</label></th>
			<td><s:property value="vo.registeredType.displayName" /></td>
			<th><label>开户科目</label></th>
			<td>${vo.subject }</td>
		</tr>
		<tr>
			<th><label>状态</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(vo.status).displayName" /></td>
			<th><label>审核类型</label></th>
			<td>${vo.auditStatus.displayName }</td>
		</tr>
		<tr>
			<th><label>创建时间</label></th>
			<td>${vo.createDate }</td>
			<th><label>企业电子邮箱</label></th>
			<td>${vo.email }</td>
		</tr>
		<tr>
			<th><label>联系电话</label></th>
			<td>${vo.phone }</td>
			<th><label>传真</label></th>
			<td>${vo.fax }</td>
		</tr>
		<tr>
			<th><label>注册地址及邮编</label></th>
			<td>${vo.addrReg }</td>
			<th><label>通讯地址及邮编</label></th>
			<td>${vo.addrContact }</td>
		</tr>
		<tr>
			<th><label>有权确认人姓名</label></th>
			<td>${vo.confirmPersonName }</td>
			<th><label>移动电话</label></th>
			<td>${vo.mobile }</td>
		</tr>
		<tr>
			<th><label>办公电话</label></th>
			<td>${vo.officePhone }</td>
			<th><label>银行卡行别</label></th>
			<td><s:property value="@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(vo.bankType).displayName"/></td>
		</tr>
		<tr>
			<th><label>银行卡账号</label></th>
			<td>${vo.accountNo }</td>
			<th><label>营业执照-注册号</label></th>
			<td>${vo.licenseNo }</td>
		</tr>
		<tr>
			<th><label>营业执照-法人</label></th>
			<td>${vo.licenseLegalPerson }</td>
			<th><label>营业执照-注册资本</label></th>
			<td>${vo.licenseRegCapital}</td>
		</tr>
		<tr>
			<th><label>营业执照-公司类型</label></th>
			<td>${vo.licenseLegalEntType }</td>
			<th><label>营业执照-经营范围</label></th>
			<td>${vo.licenseLegalScope}</td>
		</tr>
		<tr>
			<th><label>营业执照-成立日期</label></th>
			<td>${vo.licenseLegalFoundDate }</td>
			<th><label>营业执照-营业期限</label></th>
			<td>${vo.licenseLegalValidTerm}</td>
		</tr>
		<tr>
			<th><label> 组织机构-代码</label></th>
			<td>${vo.orgNo }</td>
			<th><label>组织机构-有效期</label></th>
			<td>${vo.orgValidTerm}</td>
		</tr>
		<tr>
			<th><label>地税-号码</label></th>
			<td>${vo.landtaxNo }</td>
			<th><label>国税-号码</label></th>
			<td>${vo.nationaltaxNo}</td>
		</tr>
		<tr>
			<th><label>法人-姓名</label></th>
			<td>${vo.legalpersonName }</td>
			<th><label>法人-证件类型</label></th>
			<td>
				<s:property value="@com.ylink.ylpay.common.project.fund.constant.CertType@parseOf(vo.legalpersonCertType).displayName"/>
			</td>
		</tr>
		<tr>
			<th><label>法人-证件号码</label></th>
			<td colspan="3">${vo.legalpersonCertNo }</td>
		</tr>
		<tr>
			<th><label>财务负责人-姓名</label></th>
			<td>${vo.legalpersonName }</td>
			<th><label>财务负责人-证件类型</label></th>
			<td>
				<s:property value="@com.ylink.ylpay.common.project.fund.constant.CertType@parseOf(vo.financeCertType).displayName"/>
			</td>
		</tr>
		<tr>
			<th><label> 财务负责人-证件号码</label></th>
			<td colspan="3">${vo.financeCertNo}</td>
		</tr>
		<tr>
			<th><label>简介</label></th>
			<td colspan="3">${vo.summary }</td>
		</tr>
		<tr>
			<th  style="padding: 10px;">
				<label>营业执照-文件</label>
			</th>
			<td >
				<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{vo.licenseId}"/>' width="300" height="300">
				</a>
			</td>
			<th style="padding: 10px;">
				<label>组织机构-文件</label>
			</th>
			<td>
				<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{vo.orgId}"/>' width="300" height="300">
				</a>
			</td>
		</tr>
		<tr>
			<th style="padding: 10px;">
				<label>地税-文件</label>
			</th>
			<td >
				<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{vo.landtaxId}"/>' width="300" height="300">
				</a>
			</td>
			<th  style="padding: 10px;">
				<label>法人-文件</label>
			</th>
			<td>
				<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{vo.legalpersonId}"/>' width="300" height="300">
			</a>
		</tr>
		<s:if test="vo.nationaltaxId != null">
			<tr>
				<th  style="padding: 10px;">
					<label>国税-文件</label>
				</th>
				<td colspan="3">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{vo.nationaltaxId}"/>' width="300" height="300">
					</a>
				</td>
			</tr>
		</s:if>
	</table>

	<p class="submit">
		<input type="button" class="action back" value="返回" onclick="backPage();" />
	</p>

</body>