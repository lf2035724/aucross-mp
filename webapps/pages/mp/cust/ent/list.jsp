<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<head>
	<meta name="decorator" content="background"/>
	<title>企业客户列表</title>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>汇款实名认证</li>
		<li>汇款记录</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp" %>
	
	<s:form name="list" method="post">
		<table class="profile">
			<tbody>
				<tr>
					<th><label for="cust_id">客户编号</label></th>
					<td>
						<s:textfield id="cust_id" name="payCheck.enterprise.id" size="20"/>
					</td>
					<th><label for="banktype">行别</label></th>
					<td>
						<s:select name="payCheck.bankType" value="payCheck.bankType"
							headerKey="" headerValue="" id="banktype"
							listKey="getValue()" listValue="getDisplayName()"
							list="@com.ylink.ylpay.common.project.channel.constant.BankType@values()"/>
				
					</td>
					<th><label for="">卡号</label></th>
					<td>
						<s:textfield name="payCheck.cardNo" size="30"/>
					</td>
					<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
				</tr>
			</tbody>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="客户编号" value="enterprise.id" width="50px" sortable="true"/>
		<mys:column title="客户名称" value="enterprise.name"/>
		<mys:column title="行别" sortable="true" sortKey="bankType" width="100px">
			<s:set name="bank" value="@com.ylink.ylpay.common.project.channel.constant.BankType@parseOf(bankType)"/>
			<s:property value="%{#bank==null?'--(未知)--':#bank.getDisplayName()}"/>
		</mys:column>
		<mys:column title="户名" value="cardName" width="180px"/>
		<mys:column title="卡号" value="cardNo" width="130px" sortable="true"/>
		<mys:column title="类型" value="type" width="50px" sortable="false"/>
		<mys:column title="金额" width="50px" sortKey="amount" sortable="true" >
			<s:text name="format.money">
				<s:param value="amount/100d"/>
			</s:text>
		</mys:column>
		<mys:column title="支付状态" value="payStatus" width="70px" sortable="false"/>
		<mys:column title="验证状态" value="checkStatus" width="50px" sortable="false"/>
		<mys:column title="验证次数" value="checkCount" width="50px" sortable="true"/>
		<mys:column title="创建时间" value="createdTime" width="160px"/>
		<%-- 
		<mys:column title="支付订单" value="payRefPayment" width="100px"/>
		--%>
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li class="disabled"><a href="#">详细信息</a></li>
					
					<s:if test="@com.ylink.ylpay.project.mp.cust.entity.PayCheck$PayStatus@PAYING.equals(payStatus)">
					<li class="section"/>
					
					<li class="refresh"><a href="<s:url value="refresh.do?payCheck.id=%{id}"/>">更新支付状态</a></li>
					</s:if>
				</ul>
			</div>
		</mys:column>
	</mys:table>
	
	<mys:pagination value="page"/>
</body>

	
