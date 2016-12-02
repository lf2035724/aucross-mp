<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>融资供给信息列表</title>
		
		<script type="text/javascript">
			$(document).ready(function(){

			});
		</script>
	</head>
	
	<body>
		<ul class="path">
			<li>客户管理</li>
			<li>供应链管理</li>
			<li>融资供给信息列表</li>
		</ul>
		
		
		<%@ include file="/pages/common/messages.jsp" %>
		
				
		<s:form name="listSupplyMessage" namespace="/mp/cust/supplychain" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label for="time">消息序号</label></th>
						<td>
							<input name="capitalSupplyDTO.messageSeq" id="messageSeq" value="${capitalSupplyDTO.messageSeq}" size="10" />
						</td>
						<th><label for="time">供给方客户号</label></th>
						<td>
							<input name="capitalSupplyDTO.supplyCustId" id="supplyCustId" value="${capitalSupplyDTO.supplyCustId}" size="10" />
						</td>
						<th><label for="messageType">消息类型</label></th>
						<td>
							<s:select id="messageType" name="capitalSupplyDTO.messageType" value="capitalSupplyDTO.messageType"
								headerKey="" headerValue=""
								listKey="getValue()" listValue="getDisplayName()"
								list="@com.ylink.ylpay.common.project.supplychain.constant.MessageType@values()"/>
						</td>
						<th><label for="useCycleType">使用方式</label></th>
						<td>
							<s:select id="useCycleType" name="capitalSupplyDTO.useCycleType" value="capitalSupplyDTO.useCycleType"
								headerKey="" headerValue=""
								listKey="getValue()" listValue="getDisplayName()"
								list="@com.ylink.ylpay.common.project.supplychain.constant.UseCycleType@values()"/>
						</td>
						<th><label for="auditStatus">审核状态</label></th>
						<td>
							<s:select id="auditStatus" name="capitalSupplyDTO.auditStatus" value="capitalSupplyDTO.auditStatus"
								headerKey="" headerValue=""
								listKey="getValue()" listValue="getDisplayName()"
								list="@com.ylink.ylpay.common.project.mp.constant.AuditStatus@values()"/>
						</td>
					</th>
						<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
					</tr>
				</tbody>
			</table>
		</s:form>
		
		<mys:table cssClass="list" value="capitalSupplyPage" status="loop">
			<mys:column title="序号" width="50px">
				<s:property value="#loop.index + 1"/>
			</mys:column>
			<mys:column title="消息序号" value="messageSeq" width="160px"/>
			<mys:column title="供给方客户号" value="supplyCustId" width="160px"/>
			<mys:column title="供给方客户名称" value="supplyCustName" width="160px"/>
			<mys:column title="消息类型" sortKey="messageType" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.MessageType@parseOf(messageType).displayName" />
			</mys:column>
			<mys:column title="申请条件" value="applyCondition" width="160px"/>
			<mys:column title="资金额度" width="120px">
			<s:text name="format.money">
				<s:param value="amount/100d"></s:param>
			</s:text>
			</mys:column>
			<mys:column title="期限单" sortKey="limitUnit" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.LimitUnit@parseOf(limitUnit).displayName" />
			</mys:column>
			<mys:column title="期限" value="limit" width="160px"/>
			<mys:column title="利率" value="rate" width="160px"/>
			<mys:column title="使用方式" sortKey="useCycleType" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.UseCycleType@parseOf(useCycleType).displayName" />
			</mys:column>
			<mys:column title="联系人" value="contactPersonName" width="160px"/>
			<mys:column title="邮件" value="conntactEmail" width="160px"/>
			<mys:column title="电话" value="conntactPhone" width="160px"/>
			<mys:column title="预约次数" value="reserverTimes" width="160px"/>
			<mys:column title="发布时间">
				<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(createDate,'yyyy-MM-dd HH:mm:ss')"/>
			</mys:column>
			<mys:column title="审核状态" sortKey="auditStatus" sortable="false" width="50px">
				<s:property value="@com.ylink.ylpay.common.project.supplychain.constant.AuditStatus@parseOf(auditStatus).displayName" />
			</mys:column>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<li><a href="<s:url action="supplyMessageDetail.do?capitalSupplyDTO.messageSeq=%{messageSeq}" />">明细</a></li>
					</ul>
					<s:if test="%{auditStatus == 0}">
						<ul class="dropdown-menu">
							<li><a href="<s:url action="supplyMessageToAudit.do?capitalSupplyDTO.messageSeq=%{messageSeq}" />">审核</a></li>
						</ul>
					</s:if>
				</div>
			</mys:column>
		</mys:table>
		<mys:pagination value="capitalSupplyPage" pageParamPrefix="capitalSupplyPage"/>
		
		
		
	</body>
</html>