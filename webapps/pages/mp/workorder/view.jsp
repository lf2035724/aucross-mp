<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<head>
	<meta name="decorator" content="background"/>
	
	<title>详细信息</title>
</head>

<body>

	<ul class="path">
		<li>业务管理</li>
		<li>工单管理</li>
		<li>详细信息</li>
	</ul>
		
	<%@ include file="/pages/common/messages.jsp" %>
	
	<table class="profile">
		<colgroup>
			<col class="element" width="10%" />
			<col class="element" width="30%" />
			<col class="element" width="10%" />
			<col class="element" width="30%" />
		</colgroup>
		<tbody>
			<tr>
				<th for="workOrder.orderNo">工单编号</th>
				<td>
					<s:property value="workOrder.orderNo"/>
				</td>
				
				<th for="workOrder.userName">用户名称</th>
				<td>
					<s:property value="workOrder.userName"/>
				</td>
			</tr>
			
			<tr>
				<th for="workOrder.lineClassification">线路分类</th>
				<td>
					<s:property value="workOrder.lineClassification"/>
				</td>
				<th for="workOrder.forwardingAddress">通讯地址</th>
				<td>
					<s:property value="workOrder.forwardingAddress"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.accessTerminalAddress">接入端地址</th>
				<td>
					<s:property value="workOrder.accessTerminalAddress"/>
				</td>
				<th for="workOrder.contactUserName">用户联系人</th>
				<td>
					<s:property value="workOrder.contactUserName"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.contactUserPhone">用户联系电话</th>
				<td>
					<s:property value="workOrder.contactUserPhone"/>
				</td>
				<th for="workOrder.contactUserMobile">用户移动电话</th>
				<td>
					<s:property value="workOrder.contactUserMobile"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.accessRate">接入速率</th>
				<td>
					<s:property value="workOrder.accessRate"/>
				</td>
				<th for="workOrder.accessMethod">接入方式</th>
				<td>
					<s:property value="workOrder.accessMethod"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.businessType">业务类别</th>
				<td>
					<s:property value="workOrder.businessType"/>
				</td>
				<th for="workOrder.orderType">工单类型</th>
				<td>
					<s:property value="workOrder.orderType"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.ascendingNodeName">上行节点名称</th>
				<td>
					<s:property value="workOrder.ascendingNodeName"/>
				</td>
				<th for="workOrder.ipAlready">已有ip地址</th>
				<td>
					<s:property value="workOrder.ipAlready"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.ipAmount">申请ip数量</th>
				<td>
					<s:property value="workOrder.ipAmount"/>
				</td>
				<th for="workOrder.pcAmount">计算机台数</th>
				<td>
					<s:property value="workOrder.pcAmount"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.newIpDistribution">新ip分配</th>
				<td>
					<s:property value="workOrder.newIpDistribution"/>
				</td>
				<th for="workOrder.distributionSign">分配人签字</th>
				<td>
					<s:property value="workOrder.distributionSign"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.distributioDate">分配日期</th>
				<td>
					<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(workOrder.distributioDate,'yyyy-MM-dd HH:mm:ss')"/>
				</td>
				<th for="workOrder.lineTerminalDevice">线路终端设备</th>
				<td>
					<s:property value="workOrder.lineTerminalDevice"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.flowFee">流量费</th>
				<td>
					<s:property value="workOrder.flowFee"/>
				</td>
				<th for="workOrder.monthlyFee">月租费</th>
				<td>
					<s:property value="workOrder.monthlyFee"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.expiryDate">截止日期</th>
				<td>
					<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(workOrder.expiryDate,'yyyy-MM-dd HH:mm:ss')"/>
				</td>
				<th for="workOrder.billingCycleee">计费周期</th>
				<td>
					<s:property value="workOrder.billingCycleee"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.departmentName">部门名称</th>
				<td>
					<s:property value="workOrder.departmentName"/>
				</td>
				<th for="workOrder.businessAgent">业务经办人</th>
				<td>
					<s:property value="workOrder.businessAgent"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.salesClerk">销售业务员</th>
				<td>
					<s:property value="workOrder.salesClerk"/>
				</td>
				<th for="workOrder.customerServiceContactPerson">客服联系人</th>
				<td>
					<s:property value="workOrder.customerServiceContactPerson"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.salesClerkPhone">销售业务员移动电话</th>
				<td>
					<s:property value="workOrder.salesClerkPhone"/>
				</td>
				<th for="workOrder.customerServiceContactPhone">客服联系人移动电话</th>
				<td>
					<s:property value="workOrder.customerServiceContactPhone"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.salesClerkContactPhone">销售业务员联系电话</th>
				<td>
					<s:property value="workOrder.salesClerkContactPhone"/>
				</td>
				<th for="workOrder.customerServiceContactPersonPhone">客服联系人联系电话</th>
				<td>
					<s:property value="workOrder.customerServiceContactPersonPhone"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.businessAgentContactPhone">业务经办人联系电话</th>
				<td>
					<s:property value="workOrder.businessAgentContactPhone"/>
				</td>
				<th for="workOrder.customerServiceContactPersonPhone">创建时间</th>
				<td>
					<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(workOrder.createTime,'yyyy-MM-dd HH:mm:ss')"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.updatedTime">更新时间</th>
				<td>
					<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(workOrder.updatedTime,'yyyy-MM-dd HH:mm:ss')"/>
				</td>
				<th for="workOrder.status">状态</th>
				<td>
					<s:property value="@com.ylink.ylpay.common.project.portal.constant.EntityStatus@parseOf(workOrder.status.value)"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.creator">创建者</th>
				<td>
					<s:property value="workOrder.creator"/>
				</td>
				<th for="workOrder.workOrderAuditStatus">审核状态</th>
				<td>
					<s:property value="@com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus@parseOf(workOrder.workOrderAuditStatus.value)"/>
				</td>
			</tr>
			<tr>
				<th for="workOrder.remark">备注</th>
				<td>
					<s:property value="workOrder.remark"/>
				</td>
			</tr>
		</tbody>
	</table>
	<table class="profile">
		<caption>证件信息</caption>
		<colgroup>
			<col width="33%"/>
			<col width="33%"/>
			<col width="33%"/>
		</colgroup>
		<tbody>
			<tr>
				<th  style="padding: 10px;">
					<label>客户营业执照</label>
				</th>
				<td colspan="3">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url value="/mp/workorder/imageDetail.do?imageId=%{workOrder.businessLicenseCertId}"/>' width="300" height="300">
					</a>
				</td>
			</tr>
			<tr>
				<th  style="padding: 10px;">
					<label>客户身份证照片</label>
				</th>
				<td colspan="3">
					<a id="imageDetailBtn" title="放大" style="cursor: help;">
						<img alt="证件信息" src='<s:url action="/mp/workorder/imageDetail.do?imageId=%{workOrder.idCardCertId}" />'  width="300" height="300">
					</a>
				</td>
			</tr>
		</tbody>
	</table>
	<p class="submit">
		<input type="button" class="action back" value="返回" onclick="backPage();" />
	</p>
</body>