<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
<head>
	<meta name="decorator" content="background"/>
	<title>企业客户审核信息</title>
	<script type="text/javascript" src="<s:url value="/pages/mp/cust/portalenterprise/audit/audit.js"/>"></script>
	<style type="text/css">
		.error_text{
			display: none;
		}
	</style>
	<script type="text/javascript">
		var path='<%=request.getContextPath()%>';
		var actionUrl = '<s:url value="/mp/cust/portalenterprise/audit.do"/>'
	</script>
</head>
<body>
	<ul class="path">
		<li>客户管理</li>
		<li>注册企业客户</li>
		<li>审核信息</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>
	<table class="profile">
		<caption>企业信息</caption>
		<colgroup>
		 <col width="10%">
		 <col width="40%">
		 <col width="10%">
		 <col width="40%">
		</colgroup>
		<tbody>
			<tr>
				<th><label>企业名称</label></th>
				<td>${ enterprise.name }</td>
				<th><label>营业执照注册号</label></th>
				<td>${ enterprise.licenseNo }</td>
			</tr>
			<tr>
				<th><label>营业执照所在地</label></th>
				<td>${ enterprise.licenseGeo.name }</td>
				<th><label>营业执照期限</label></th>
				<s:set name="licenseLegalValidTerm" value="enterprise.licenseIsLongterm == @com.ylink.ylpay.common.project.mp.constant.WhetherStatus@NO ? enterprise.licenseLegalValidTerm : '长期'"/>
				<td>
					<s:property value="#licenseLegalValidTerm"/>
				</td>
			</tr>
			<tr>
				<th><label>营业执照住所</label></th>
				<td>${ enterprise.licenseResidence }</td>
				<th><label>营业执照经营范围</label></th>
				<td>${ enterprise.licenseLegalScope }</td>
			</tr>
			<tr>
				<th><label>营业执照注册资本</label></th>
				<td>${ enterprise.licenseRegCapital }</td>
				<th><label>组织机构代码</label></th>
				<td>${ enterprise.orgNo }</td>
			</tr>
			<tr>
				<th><label>联系电话</label></th>
				<td>${ enterprise.phone }</td>
				<th><label>传真</label></th>
				<td>${ enterprise.fax }</td>
			</tr>
			 <tr>
				<th  style="padding: 10px;">
					<label>营业执照-文件</label>
				</th>
				<td >
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
							<img alt="无图片" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{enterprise.licenseId}"/>' width="300" height="300">
					</a>
				</td>
				<th style="padding: 10px;">
					<label>组织机构-文件</label>
				</th>
				<td>
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="无图片" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{enterprise.orgId}"/>' width="300" height="300">
					</a>
				</td>
			 <tr>
		</tbody>
	</table>
	<table class="profile">
		<caption>对公账户</caption>
		<colgroup>
		 <col width="10%">
		 <col width="40%">
		 <col width="10%">
		 <col width="40%">
		</colgroup>
		<tbody>
			<tr>
				<th><label>户名</label></th>
				<td>${ custBankcard.cardName }</td>
				<th><label>银行卡号</label></th>
				<td>${ custBankcard.cardNo }</td>
			</tr>
			<tr>
				<th><label>行别</label></th>
				<td>
					<s:property value="@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(custBankcard.bankType).displayName"/>
				</td>
				<th><label>支行名称</label></th>
				<td>${ custBankcard.branchName }</td>
			</tr>
			
		</tbody>
	</table>
	<table class="profile">
		<caption>法人信息</caption>
		<colgroup>
		 <col width="10%">
		 <col width="40%">
		 <col width="10%">
		 <col width="40%">
		</colgroup>
		<tbody>
			<tr>
				<th><label>法人归属地</label></th>
				<td>${ enterprise.legalpersonGeo.name }</td>
				<th><label>法人姓名</label></th>
				<td>${ enterprise.legalpersonName }</td>
			</tr>
			<tr>
				<th><label>法人证件类型</label></th>
				<td>
					<s:property value="@com.ylink.ylpay.common.project.mp.constant.CertificationType@parseOf(enterprise.legalpersonCertType).displayName"/>
				</td>
				<th><label>法人证件号码</label></th>
				<td>${ enterprise.legalpersonCertNo }</td>
			</tr>
			<tr>
				<th><label>手机号码</label></th>
				<td colspan="3">${ enterprise.legalpersonMobile }</td>
			</tr>
			<tr>
				<th  style="padding: 10px;">
					<label>法人-证件正面</label>
				</th>
				<td >
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="无图片" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{enterprise.legalpersonId}"/>' width="300" height="300">
					</a>
				</td>
				<th style="padding: 10px;">
					<label>法人-证件背面</label>
				</th>
				<td>
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="无图片" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{enterprise.legalpersonBackId}"/>' width="300" height="300">
					</a>
				</td>
			</tr>
		</tbody>
	</table>
	<s:set name="isAgent" value="enterprise.authApplicantType == @com.ylink.ylpay.common.project.mp.constant.AuthApplicantType@THE_AGENT"/>
	<s:if test="#isAgent">
	<table class="profile">
		<caption>代理人信息</caption>
		<colgroup>
		 <col width="10%">
		 <col width="40%">
		 <col width="10%">
		 <col width="40%">
		</colgroup>
		<tbody>
			<tr>
				<th><label>代理人归属地</label></th>
				<td>${ enterprise.agentGeo.name }</td>
				<th><label>代理人姓名</label></th>
				<td>${ enterprise.agentName }</td>
			</tr>
			<tr>
				<th><label>代理人证件类型</label></th>
				<td>
					<s:property value="@com.ylink.ylpay.common.project.mp.constant.CertificationType@paresOf(enterprise.agentCertType).displayName"/>
				</td>
				<th><label>代理人证件号码</label></th>
				<td>${ enterprise.agentCertNo }</td>
			</tr>
			<tr>
				<th><label>手机号码</label></th>
				<td colspan="3">${ enterprise.agentMobile }</td>
			</tr>
			<tr>
				<th  style="padding: 10px;">
					<label>代理人-证件正面</label>
				</th>
				<td >
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="无图片" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{enterprise.agentId}"/>' width="300" height="300">
					</a>
				</td>
				<th style="padding: 10px;">
					<label>代理人-证件背面</label>
				</th>
				<td>
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="无图片" src='<s:url value="/mp/cust/auth/nameauth/imageDetail.do?vo.identity=%{enterprise.agentBackId}"/>' width="300" height="300">
					</a>
				</td>
			</tr>
		</tbody>
	</table>
	</s:if>
	<s:form id="portal_enterprise_form" action="audit" namespace="/mp/cust/portalenterprise" method="post">
		<table class="profile">
			<caption>审核信息</caption>
			 <col width="10%">
			 <col width="90%">
			 <tbody>
			 	<tr>
			 		<th><label>企业信息是否错误:</label></th>
			 		<td>
			 		  <input type="checkbox" onclick="checkLind(this)">
			 		</td>
			 	</tr>
			 	<tr class="error_text">
			 		<th><label class="required">企业信息错误描述:</label></th>
			 		<td>
			 			<input type="hidden" class="is_error" name="details[0].isPassed" value="true">
			 			<input type="hidden" name="details[0].type" value='<s:property value="@com.ylink.ylpay.common.project.mp.constant.PortalEnterpriseAuditErrorType@ENT_INFO.name()"/>'>
			 			<input type="hidden" name="details[0].isModify" value="false">
			 			<s:textarea cols="50" rows="3" name="details[0].description"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th><label>对公账户是否错误:</label></th>
			 		<td>
			 		  <input type="checkbox" onclick="checkLind(this)">
			 		</td>
			 	</tr>
			 	<tr class="error_text">
			 		<th><label class="required">对公账户错误描述:</label></th>
			 		<td>
			 			<input type="hidden" class="is_error" name="details[1].isPassed" value="true">
			 			<input type="hidden" name="details[1].type" value='<s:property value="@com.ylink.ylpay.common.project.mp.constant.PortalEnterpriseAuditErrorType@ACC_INFO.name()"/>'>
			 			<input type="hidden" name="details[1].isModify" value="false">
			 			<s:textarea cols="50" rows="3" name="details[1].description"/>
			 		</td>
			 	</tr>
			 	<tr>
			 		<th><label>法人信息是否错误:</label></th>
			 		<td>
			 		  <input type="checkbox" onclick="checkLind(this)">
			 		</td>
			 	</tr>
			 	<tr class="error_text">
			 		<th><label class="required">法人信息错误描述:</label></th>
			 		<td>
			 			<input type="hidden" class="is_error" name="details[2].isPassed" value="true">
			 			<input type="hidden" name="details[2].type" value='<s:property value="@com.ylink.ylpay.common.project.mp.constant.PortalEnterpriseAuditErrorType@LEGAL_INFO.name()"/>'>
			 			<input type="hidden" name="details[2].isModify" value="false">
			 			<s:textarea cols="50" rows="3" name="details[2].description"/>
			 		</td>
			 	</tr>
			 	<s:if test="#isAgent">
			 	<tr>
			 		<th><label>代理人信息是否错误:</label></th>
			 		<td>
			 		  <input type="checkbox" onclick="checkLind(this)">
			 		</td>
			 	</tr>
			 	<tr class="error_text">
			 		<th><label class="required">代理人信息错误描述:</label></th>
			 		<td>
			 			<input type="hidden" class="is_error" name="details[3].isPassed" value="true">
			 			<input type="hidden" name="details[3].type" value='<s:property value="@com.ylink.ylpay.common.project.mp.constant.PortalEnterpriseAuditErrorType@AGENCY_INFO.name()"/>'>
			 			<input type="hidden" name="details[3].isModify" value="false">
			 			<s:textarea cols="50" rows="3" name="details[3].description"/>
			 		</td>
			 	</tr>
			 	</s:if>
			 </tbody>
		</table>
		<p class="submit">
				<s:hidden name="enterprise.id"/>
				<s:hidden name="enterprise.batchNo"></s:hidden>
				<input type="button" class="action audit" onclick="isPassedOrReject(true);" value="审核通过"/>
				<input type="button" class="action audit reject" onclick="isPassedOrReject(false);" value="审核拒绝"/>
				<input type="button" class="action back" value="返回" onclick="backPage();" />
		</p>
	</s:form>
</body>
</html>