<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/ui/i18n/jquery.ui.datepicker_<s:property value="locale"/>.js"></script>

<s:set name="originalObject" value="#request.originalObject"/>
<s:set name="newObject" value="#request.newObject"/>

<table class="profile">
	<caption>工单信息</caption>
	<tbody>
		<tr>
			<th><label>用户名称</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'userName')"/>" 
					title="<s:property value="%{#originalObject.userName}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'userName')=='delete'">
					<s:property value="%{#originalObject.userName}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.userName}"/>
				</s:else>
			</td>
			<th><label>线路分类</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'lineClassification')"/>" 
					title="<s:property value="%{#originalObject.lineClassification}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'lineClassification')=='delete'">
					<s:property value="%{#originalObject.lineClassification}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.lineClassification}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>通讯地址</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'forwardingAddress')"/>" 
					title="<s:property value="%{#originalObject.forwardingAddress}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'forwardingAddress')=='delete'">
					<s:property value="%{#originalObject.forwardingAddress}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.forwardingAddress}"/>
				</s:else>
			</td>
			<th><label>接入端地址</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'accessTerminalAddress')"/>" 
					title="<s:property value="%{#originalObject.accessTerminalAddress}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'accessTerminalAddress')=='delete'">
					<s:property value="%{#originalObject.accessTerminalAddress}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.accessTerminalAddress}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>用户联系人</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'contactUserName')"/>" 
					title="<s:property value="%{#originalObject.contactUserName}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'contactUserName')=='delete'">
					<s:property value="%{#originalObject.contactUserName}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.contactUserName}"/>
				</s:else>
			</td>
			<th><label>用户联系电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'contactUserPhone')"/>" 
					title="<s:property value="%{#originalObject.contactUserPhone}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'contactUserPhone')=='delete'">
					<s:property value="%{#originalObject.contactUserPhone}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.contactUserPhone}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>用户移动电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'contactUserMobile')"/>" 
					title="<s:property value="%{#originalObject.contactUserMobile}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'contactUserMobile')=='delete'">
					<s:property value="%{#originalObject.contactUserMobile}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.contactUserMobile}"/>
				</s:else>
			</td>
			<th><label>接入速率</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'accessRate')"/>" 
					title="<s:property value="%{#originalObject.accessRate}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'accessRate')=='delete'">
					<s:property value="%{#originalObject.accessRate}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.accessRate}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>接入方式</th>
			<td>
			<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'accessMethod')"/>" 
					title="<s:property value="%{#originalObject.accessMethod}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'accessMethod')=='delete'">
					<s:property value="%{#originalObject.accessMethod}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.accessMethod}"/>
				</s:else>
			</td>
			<th><label>业务类别</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessType')"/>" 
					title="<s:property value="%{#originalObject.businessType}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessType')=='delete'">
					<s:property value="%{#originalObject.businessType}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.businessType}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>工单类型</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'orderType')"/>" 
					title="<s:property value="%{#originalObject.orderType}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'orderType')=='delete'">
					<s:property value="%{#originalObject.orderType}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.orderType}"/>
				</s:else>
			</td>
			<th><label>上行节点名称</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'ascendingNodeName')"/>" 
					title="<s:property value="%{#originalObject.ascendingNodeName}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'ascendingNodeName')=='delete'">
					<s:property value="%{#originalObject.ascendingNodeName}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.ascendingNodeName}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>已有ip地址</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'ipAlready')"/>" 
					title="<s:property value="%{#originalObject.ipAlready}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'ipAlready')=='delete'">
					<s:property value="%{#originalObject.ipAlready}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.ipAlready}"/>
				</s:else>
			</td>
			<th><label>申请ip数量</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'ipAmount')"/>" 
					title="<s:property value="%{#originalObject.ipAmount}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'ipAmount')=='delete'">
					<s:property value="%{#originalObject.ipAmount}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.ipAmount}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>计算机台数</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'pcAmount')"/>" 
					title="<s:property value="%{#originalObject.pcAmount}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'pcAmount')=='delete'">
					<s:property value="%{#originalObject.pcAmount}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.pcAmount}"/>
				</s:else>
			</td>
			<th><label>新ip分配</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'newIpDistribution')"/>" 
					title="<s:property value="%{#originalObject.newIpDistribution}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'newIpDistribution')=='delete'">
					<s:property value="%{#originalObject.newIpDistribution}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.newIpDistribution}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>分配人签字</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'distributionSign')"/>" 
					title="<s:property value="%{#originalObject.distributionSign}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'distributionSign')=='delete'">
					<s:property value="%{#originalObject.distributionSign}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.distributionSign}"/>
				</s:else>
			</td>
			<th><label>分配日期</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'distributioDate')"/>" 
					title="<s:property value="%{#originalObject.distributioDate}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'distributioDate')=='delete'">
					<s:property value="%{#originalObject.distributioDate}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.distributioDate}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>流量费</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'flowFee')"/>" 
					title="<s:property value="%{#originalObject.flowFee}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'flowFee')=='delete'">
					<s:property value="%{#originalObject.flowFee}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.flowFee}"/>
				</s:else>
			</td>
			<th><label>月租费</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'monthlyFee')"/>" 
					title="<s:property value="%{#originalObject.monthlyFee}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'monthlyFee')=='delete'">
					<s:property value="%{#originalObject.monthlyFee}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.monthlyFee}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>计费周期</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'billingCycleee')"/>" 
					title="<s:property value="%{#originalObject.billingCycleee}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'billingCycleee')=='delete'">
					<s:property value="%{#originalObject.billingCycleee}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.billingCycleee}"/>
				</s:else>
			</td>
			<th><label>截止日期</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'expiryDate')"/>" 
					title="<s:property value="%{#originalObject.expiryDate}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'expiryDate')=='delete'">
					<s:property value="%{#originalObject.expiryDate}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.expiryDate}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>线路终端设备</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'lineTerminalDevice')"/>" 
					title="<s:property value="%{#originalObject.lineTerminalDevice}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'lineTerminalDevice')=='delete'">
					<s:property value="%{#originalObject.lineTerminalDevice}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.lineTerminalDevice}"/>
				</s:else>
			</td>
			<th><label>原设备型号</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'originalDeviceModel')"/>" 
					title="<s:property value="%{#originalObject.originalDeviceModel}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'originalDeviceModel')=='delete'">
					<s:property value="%{#originalObject.originalDeviceModel}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.originalDeviceModel}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>原设备数量</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'originalDeviceAmount')"/>" 
					title="<s:property value="%{#originalObject.originalDeviceAmount}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'originalDeviceAmount')=='delete'">
					<s:property value="%{#originalObject.originalDeviceAmount}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.originalDeviceAmount}"/>
				</s:else>
			</td>
			<th><label>部门名称</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'departmentName')"/>" 
					title="<s:property value="%{#originalObject.departmentName}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'departmentName')=='delete'">
					<s:property value="%{#originalObject.departmentName}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.departmentName}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>业务经办人</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessAgent')"/>" 
					title="<s:property value="%{#originalObject.businessAgent}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessAgent')=='delete'">
					<s:property value="%{#originalObject.businessAgent}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.businessAgent}"/>
				</s:else>
			</td>
			<th><label>销售业务员</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'salesClerk')"/>" 
					title="<s:property value="%{#originalObject.salesClerk}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'salesClerk')=='delete'">
					<s:property value="%{#originalObject.salesClerk}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.salesClerk}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>客服联系人</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'customerServiceContactPerson')"/>" 
					title="<s:property value="%{#originalObject.customerServiceContactPerson}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'customerServiceContactPerson')=='delete'">
					<s:property value="%{#originalObject.customerServiceContactPerson}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.customerServiceContactPerson}"/>
				</s:else>
			</td>
			<th><label>销售业务员移动电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'salesClerkPhone')"/>" 
					title="<s:property value="%{#originalObject.salesClerkPhone}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'salesClerkPhone')=='delete'">
					<s:property value="%{#originalObject.salesClerkPhone}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.salesClerkPhone}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>客服联系人移动电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'customerServiceContactPhone')"/>" 
					title="<s:property value="%{#originalObject.customerServiceContactPhone}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'customerServiceContactPhone')=='delete'">
					<s:property value="%{#originalObject.customerServiceContactPhone}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.customerServiceContactPhone}"/>
				</s:else>
			</td>
			<th><label>业务经办人移动电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessAgentPhone')"/>" 
					title="<s:property value="%{#originalObject.businessAgentPhone}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessAgentPhone')=='delete'">
					<s:property value="%{#originalObject.businessAgentPhone}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.businessAgentPhone}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>销售业务员联系电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'salesClerkContactPhone')"/>" 
					title="<s:property value="%{#originalObject.salesClerkContactPhone}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'salesClerkContactPhone')=='delete'">
					<s:property value="%{#originalObject.salesClerkContactPhone}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.salesClerkContactPhone}"/>
				</s:else>
			</td>
			<th><label>客服联系人联系电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'customerServiceContactPersonPhone')"/>" 
					title="<s:property value="%{#originalObject.customerServiceContactPersonPhone}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'customerServiceContactPersonPhone')=='delete'">
					<s:property value="%{#originalObject.customerServiceContactPersonPhone}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.customerServiceContactPersonPhone}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th><label>业务经办人联系电话</label></th>
			<td>
				<span class="compare 
					<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessAgentContactPhone')"/>" 
					title="<s:property value="%{#originalObject.businessAgentContactPhone}"/>">
				</span>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessAgentContactPhone')=='delete'">
					<s:property value="%{#originalObject.businessAgentContactPhone}"/>
				</s:if>
				<s:else>
					<s:property value="%{#newObject.businessAgentContactPhone}"/>
				</s:else>
			</td>
		</tr>
	</tbody>
</table>
	<table class="profile">
		<caption>工单证件信息</caption>
		<colgroup>
			<col width="33%"/>
			<col width="33%"/>
			<col width="33%"/>
		</colgroup>
		<tbody>
			<tr>
				<th>营业执照证件<span class="compare
				<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessLicenseCertId')"/>" 
					title="<s:property value="%{#originalObject.businessLicenseCertId}"/>">
				</span></th>
				<th>身份证证件<span class="compare
				<s:property value="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'idCardCertId')"/>" 
					title="<s:property value="%{#originalObject.idCardCertId}"/>">
				</span></th>
			</tr>
			<tr>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'businessLicenseCertId')==\"delete\"">
					<td><img src="<s:url value="/mp/workorder/imageDetail.do?imageId=%{#originalObject.businessLicenseCertId}"/>" width="100%" height="350px"/></td>
				</s:if>
				<s:else>
					<td><img src="<s:url value="/mp/workorder/imageDetail.do?imageId=%{#newObject.businessLicenseCertId}"/>" width="100%" height="350px"/></td>
				</s:else>
				<s:if test="@com.google.code.lightssh.project.util.CompareUtil@style(#originalObject,#newObject,'idCardCertId')==\"delete\"">
					<td><img src="<s:url value="/mp/workorder/imageDetail.do?imageId=%{#originalObject.idCardCertId}"/>" width="100%" height="350px"/></td>
				</s:if>
				<s:else>
					<td><img src="<s:url value="/mp/workorder/imageDetail.do?imageId=%{#newObject.idCardCertId}"/>" width="100%" height="350px"/></td>
				</s:else>
			</tr>
		</tbody>
	</table>
	</table>