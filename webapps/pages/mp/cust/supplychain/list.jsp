<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>供应链协议列表</title>
		
		<script type="text/javascript">
			$(document).ready(function(){
				$("#createDate_start").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#createDate_end").datepicker({dateFormat: 'yymmdd',changeYear:true});
			});
			
		</script>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>供应链管理</li>
			<li>供应链协议列表</li>
		</ul>
		
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="list" namespace="/mp/cust/supplychain" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label for="time">协议编号</label></th>
						<td>
							<input name="contractDTO.contractSeq" id="contractSeq" value="${contractDTO.contractSeq}" size="10" />
						</td>
						<th><label for="contractStatus">协议状态</label></th>
						<td>
							<s:select id="contractStatus" name="contractDTO.contractStatus" value="contractDTO.contractStatus"
								headerKey="" headerValue=""
								listKey="getValue()" listValue="getDisplayName()"
								list="@com.ylink.ylpay.common.project.supplychain.constant.ContractStatus@values()"/>
						</td>
						<th><label for="executeStatus">执行状态</label></th>
						<td>
							<s:select id="executeStatus" name="contractDTO.executeStatus" value="contractDTO.executeStatus"
								headerKey="" headerValue=""
								listKey="getValue()" listValue="getDisplayName()"
								list="@com.ylink.ylpay.common.project.supplychain.constant.ExecuteStatus@values()"/>
						</td>
					</th>
						<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="page" status="loop">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="协议号" value="contractSeq" width="160px"/>
			<mys:column title="发起人" sortKey="sponsorType" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SponsorType@parseOf(sponsorType).displayName" />
			</mys:column>
			<mys:column title="创建时间">
				<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(createTime,'yyyy-MM-dd HH:mm:ss')"/>
			</mys:column>
			<mys:column title="债权方ID" value="creditorCustId" width="160px"/>
			<mys:column title="债权方名称" value="creditorCustName" width="160px"/>
			<mys:column title="债权方签约时间">
				<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(creditorSignTime,'yyyy-MM-dd HH:mm:ss')"/>
			</mys:column>
			<mys:column title="债权人签约状态" sortKey="creditorSignStatus" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SignStatus@parseOf(creditorSignStatus).displayName" />
			</mys:column>
			<mys:column title="债务方ID" value="debtorCustId" width="160px"/>
			<mys:column title="债务方名称" value="debtorCustName" width="160px"/>
			<mys:column title="债务人签约状态" sortKey="debtorSignStatus" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SignStatus@parseOf(debtorSignStatus).displayName" />
			</mys:column>
			<mys:column title="受让方ID" value="acceptorCustId" width="160px"/>
			<mys:column title="受让方名称" value="acceptorCustName" width="160px"/>
			<mys:column title="受让方签约状态" sortKey="acceptorSignStatus" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.SignStatus@parseOf(acceptorSignStatus).displayName" />
			</mys:column>
			<mys:column title="受让金额" width="120px">
			<s:text name="format.money">
				<s:param value="transferFeeMoney/100d"></s:param>
			</s:text>
			</mys:column>
			<mys:column title="协议状态" sortKey="contractStatus" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.ContractStatus@parseOf(contractStatus).displayName" />
			</mys:column>
			<mys:column title="执行状态" sortKey="executeStatus" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.ExecuteStatus@parseOf(executeStatus).displayName" />
			</mys:column>
			<mys:column title="标的价格" width="120px">
			<s:text name="format.money">
				<s:param value="tenderPrice/100d"></s:param>
			</s:text>
			</mys:column>
			<mys:column title="标的转让价格" width="120px">
			<s:text name="format.money">
				<s:param value="tenderTransferPrice/100d"></s:param>
			</s:text>
			</mys:column>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<li><a href="<s:url action="detail.do?contractDTO.contractSeq=%{contractSeq}" />">明细</a></li>
					</ul>
				</div>
			</mys:column>
		</mys:table>
	
		<mys:pagination value="page" />
	</body>
</html>