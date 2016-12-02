<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/ui/i18n/jquery.ui.datepicker_zh_CN.js"></script>
		<title>授信管理列表</title>
		
		<script type="text/javascript">
			$(document).ready(function(){
				});
		</script>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>金汇宝授信管理</li>
			<li>授信管理列表</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="list" method="post">
			<table class="profile">
			<tbody>
				<tr>
					<th><label>客户编号</label></th>
					<td>
						<s:textfield name="dto.custId" value="%{#parameters['custId']}"/>
					</td>
					<td><input type="submit" class="action search right" value="查询" /></td>
				</tr>
			</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="page" status="loop">
			<mys:column title="序号" width="40px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="编号"  width="60px">
				<s:property value="id" />
			</mys:column>
			<mys:column title="客户编号" value="custId" width="60px"/>
			<mys:column title="赎回授信状态" width="120px">
			    <s:set name="redemStatus" value="@com.ylink.ylpay.common.project.account.constant.AuthMoneyStatus@parseOf(redemStatus)"/>
				<s:property value="#redemStatus.displayName"/>
			</mys:column>
			<mys:column title="赎回授信额度" width="120px">
			<s:text name="format.money">
				<s:param value="authMoneyReDem/100d"></s:param>
			</s:text>
			</mys:column>
			<mys:column title="赎回已用额度" width="120px">
			<s:text name="format.money">
				<s:param value="redemUsed/100d"></s:param>
			</s:text>
			</mys:column>
			
			<mys:column title="支付授信状态" width="120px">
			    <s:set name="buyStatus" value="@com.ylink.ylpay.common.project.account.constant.AuthMoneyStatus@parseOf(buyStatus)"/>
				<s:property value="#buyStatus.displayName"/>
			</mys:column>
			<mys:column title="支付授信额度" width="120px">
			<s:text name="format.money">
				<s:param value="authMoneyPay/100d"></s:param>
			</s:text>
			</mys:column>
			<mys:column title="支付已用额度" width="120px">
			<s:text name="format.money">
				<s:param value="payUsed/100d"></s:param>
			</s:text>
			</mys:column>
			
			<mys:column title="审核状态" width="50px">
			    <s:set name="auditStatus" value="@com.google.code.lightssh.project.util.constant.AuditStatus@parseOf(auditStatus)"/>
				<s:property value="#auditStatus.displayName"/>
			</mys:column>
			
			<mys:column title="创建时间" sortable="false" width="160px">
				<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(createTime,'yyyy-MM-dd HH:mm:ss')"/>
			</mys:column>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<s:if test="%{redemStatus eq '00'}">
							<li>
								<a href="<s:url value="/mp/cust/authmoney/freezeRedeem.do?dto.custId=%{custId}"/>">冻结赎回</a>
							</li>
						</s:if>
						<s:else>
							<li>
								<a href="<s:url value="/mp/cust/authmoney/unfreezeRedeem.do?dto.custId=%{custId}"/>">解冻赎回</a>
							</li>
						</s:else>
						<li class="section"/>
						<s:if test="%{buyStatus eq '00'}">
							<li>
								<a href="<s:url value="/mp/cust/authmoney/freezeBuy.do?dto.custId=%{custId}"/>">冻结支付</a>
							</li>
						</s:if>
						<s:else>
							<li>
								<a href="<s:url value="/mp/cust/authmoney/unfreezeBuy.do?dto.custId=%{custId}"/>">解冻支付</a>
							</li>
						</s:else>
					</ul>
				</div>
			</mys:column>
		</mys:table>
		
		<mys:pagination value="page"/>
	</body>
</html>