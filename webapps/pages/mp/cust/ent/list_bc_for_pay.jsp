<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<head>
	<meta name="decorator" content="background"/>
	<title>企业客户列表</title>
	<script type="text/javascript">
		function doPay( id,name ){
			var url = '<s:url value="pay.do?customer.id="/>' + id ;
			if( confirm('确认对企业客户[' + name + ']汇款操作？'))
				location.href=url;
		}
	</script>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>汇款实名认证</li>
		<li>汇款银行卡审核</li>
	</ul>

	<s:form name="list" action="listbcforpay" method="post">
		<table class="profile">
			<tbody>
				<tr>
					<th><label for="name">企业编号</label></th>
					<td><s:textfield id="name" name="bcca.enterprise.id" size="40" maxlength="100"/></td>
					<th><label for="audit">状态</label></th>
					<td>
						<s:select list="@com.ylink.ylpay.project.mp.cust.entity.BankCardCheckApply$Status@values()"
							listKey="name()" name="bcca.status" value="bcca.status.name()"
							headerKey="" headerValue=""
						/>
					</td>
					<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
				</tr>
			</tbody>
		</table>
	</s:form>

	<%@ include file="/pages/common/messages.jsp" %>

	<mys:table cssClass="list" value="applyPage" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="客户编号" value="enterprise.identity" sortKey="id" sortable="true" width="120px" />
		<mys:column title="企业名称" value="enterprise.name" />
		<%-- 
		<mys:column title="状态" sortKey="status" sortable="true" width="50px">
			<s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(status).displayName" />
		</mys:column>
		--%>
		<mys:column title="行别" width="80px">
			<s:set name="bank" value="@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(bankCard.bankType)"/>
			<s:property value="%{#bank==null?'--(未知)--':#bank.getDisplayName()}"/>
		</mys:column>
		
		<mys:column title="银行户名" value="bankCard.cardName" sortable="true" width="200px"/>
		<mys:column title="银行卡号" value="bankCard.cardNo" sortable="true"  width="200px"/>
		<%-- 
		<mys:column title="银行卡类型" value="bankCard.cardType" sortable="true" width="20px"/>
		<mys:column title="创建标志" sortable="true" sortKey="createType" width="20px">
			<s:if test="createType==\"1\"">门户</s:if>
			<s:elseif test="createType==\"2\"">网关</s:elseif>
			<s:elseif test="createType==\"3\"">管理平台</s:elseif>
			<s:else>未知</s:else>
		</mys:column>
		--%>
		
		<mys:column title="审核状态" value="status" sortable="true" width="50px" />
		<mys:column title="创建标志" value="enterprise.registeredType" sortable="false" width="50px" />
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li class="view">
						<a href="<s:url value="/cust/enterprise/detail.do?vo.id=%{enterprise.id}"/>">客户信息</a>
					</li>
					<li class="view">
						<a href="<s:url value="/mp/custbankcard/binding/detail.do?vo.identity=%{bankCard.identity}"/>">银行卡信息</a>
					</li>
					
					<li class="section" />
					<s:if test="@com.ylink.ylpay.project.mp.cust.entity.BankCardCheckApply$Status@NEW.equals(status)">
						<li class="audit"><a href="<s:url value="bctodoaudit.do?bcca.id=%{id}"/>">审核数据</a></li>
					</s:if>
					<s:else>
						<li class="disabled"><a href="#">审核数据</a></li>
					</s:else>
					
					<li class="section" />
					<s:if test="@com.ylink.ylpay.project.mp.cust.entity.BankCardCheckApply$Status@AUDIT_PASSED.equals(status) && payCheck == null">
						<li class="money"><a href="<s:url action="bcpay.do?bcca.id=%{id}" />">打款验证</a></li>
					</s:if>
					<s:else>
						<li class="disabled"><a href="#"><s:property value="payCheck != null?'已打款':'打款验证'"/></a></li>
					</s:else>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="applyPage"/>

</body>
