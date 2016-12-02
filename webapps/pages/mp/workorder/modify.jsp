<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head> 
<meta name="decorator" content="background" />
<title>修改工单</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/ui/i18n/jquery.ui.datepicker_zh_CN.js"></script>
 <script type="text/javascript">
	$(document).ready(function(){
		$("#distributioDate").datepicker({
			dateFormat: 'yy-mm-dd',
			changeYear:true
		});
		$("#expiryDate").datepicker({
			dateFormat: 'yy-mm-dd',
			changeYear:true
		});
		
		$( '#frm' ).validate( {
			rules: {
				'vo.contactUserPhone': {
					required: true,
				},
				'vo.ascendingNodeName': {
					required: true,
				},
				'vo.ipAlready': {
					required: true,
				},
				'vo.pcAmount': {
					required: true,
				},
				'vo.newIpDistribution': {
					required: true,
				},
				'vo.distributionSign': {
					required: true,
				},
				'distributioDate': {
					required: true,
				},
				'vo.lineTerminalDevice': {
					required: true,
				},
				'vo.originalDeviceModel': {
					required: true,
				},
				'vo.originalDeviceAmount': {
					required: true,
				}
			}
		} );
	});
</script>
</head>

<body>
	<ul class="path">
		<li>业务管理</li>
		<li>工单管理</li>
		<li>修改工单</li>
	</ul>
	<%@ include file="/pages/common/messages.jsp"%>
	<s:form id="frm" action="modify" method="post" enctype="multipart/form-data">
		<table class="profile">
			<colgroup>
				<col width="15%">
				<col width="35%">
				<col width="15%">
				<col>
			</colgroup>
			<tr>
				<th><label>用户名称</label></th>
				<td><s:textfield name="workOrder.userName" cssClass="default-input" maxlength="100" /></td>
				<th><label for="shortName">线路分类</label></th>
				<td><s:textfield name="workOrder.lineClassification" cssClass="default-input" maxlength="100" /></td>
			</tr>
			<tr> 
				<th><label>通讯地址</label>
				<td><s:textfield name="workOrder.forwardingAddress" cssClass="default-input" maxlength="100"/></td>
				<th><label>接入端地址</label>
				<td>
					<s:textfield name="workOrder.accessTerminalAddress" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>用户联系人</label>
				<td><s:textfield name="workOrder.contactUserName" cssClass="default-input" maxlength="100"/></td>
				<th><label class="required">用户联系电话</label>
				<td>
					<s:textfield id="workOrder.contactUserPhone" name="workOrder.contactUserPhone" cssClass="required default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>用户移动电话</label>
				<td><s:textfield name="workOrder.contactUserMobile" cssClass="default-input" maxlength="100"/></td>
				<th><label>接入速率</label>
				<td>
					<s:textfield name="workOrder.accessRate" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>接入方式</label>
				<td><s:textfield name="workOrder.accessMethod" cssClass="default-input" maxlength="100"/></td>
				<th><label>业务类别</label>
				<td>
					<s:textfield name="workOrder.businessType" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>工单类型</label>
				<td><s:textfield name="workOrder.orderType" cssClass="default-input" maxlength="100"/></td>
				<th><label class="required">上行节点名称</label>
				<td>
					<s:textfield id="workOrder.ascendingNodeName" name="workOrder.ascendingNodeName" cssClass="required default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label class="required">已有ip地址</label>
				<td><s:textfield id="workOrder.ipAlready" name="workOrder.ipAlready" cssClass="required default-input" maxlength="100"/></td>
				<th><label>申请ip数量</label>
				<td>
					<s:textfield name="ipAmount" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label class="required">计算机台数</label>
				<td><s:textfield id="pcAmount" name="workOrder.pcAmount" cssClass="required default-input" maxlength="100"/></td>
				<th><label class="required">新ip分配</label>
				<td>
					<s:textfield id="workOrder.newIpDistribution" name="workOrder.newIpDistribution" cssClass="required default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label class="required">分配人签字</label>
				<td><s:textfield id="workOrder.distributionSign" name="workOrder.distributionSign" cssClass="required default-input" maxlength="100"/></td>
				<th><label class="required">分配日期</label>
				<td>
					<s:textfield id="distributioDate" name="workOrder.distributioDate" cssClass="required default-input" maxlength="10" size="10"/>
				</td>
			</tr>
			<tr> 
				<th><label>流量费</label>
				<td><s:textfield id="workOrder.flowFee" name="workOrder.flowFee" cssClass="default-input" maxlength="100"/></td>
				<th><label>月租费</label>
				<td>
					<s:textfield id="monthlyFee" name="workOrder.monthlyFee" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>截止日期</label>
				<td><s:textfield id="expiryDate" name="workOrder.expiryDate" cssClass="default-input" maxlength="100"/></td>
				<th><label>计费周期</label>
				<td>
					<s:textfield id="billingCycleee" name="workOrder.billingCycleee" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label class="required">线路终端设备</label>
				<td><s:textfield id="workOrder.lineTerminalDevice" name="workOrder.lineTerminalDevice" cssClass="required default-input" maxlength="100"/></td>
				<th><label class="required">原设备型号</label>
				<td>
					<s:textfield id="workOrder.originalDeviceModel" name="workOrder.originalDeviceModel" cssClass="required default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label class="required">原设备数量</label>
				<td><s:textfield id="originalDeviceAmount" name="workOrder.originalDeviceAmount" cssClass="required default-input" maxlength="100"/></td>
				<th><label>部门名称</label>
				<td>
					<s:textfield name="workOrder.departmentName" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>业务经办人</label>
				<td><s:textfield name="workOrder.businessAgent" cssClass="default-input" maxlength="100"/></td>
				<th><label>销售业务员</label>
				<td>
					<s:textfield name="workOrder.salesClerk" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>客服联系人</label>
				<td><s:textfield name="workOrder.customerServiceContactPerson" cssClass="default-input" maxlength="100"/></td>
				<th><label>销售业务员移动电话</label>
				<td>
					<s:textfield name="workOrder.salesClerkPhone" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>客服联系人移动电话</label>
				<td><s:textfield name="workOrder.customerServiceContactPhone" cssClass="default-input" maxlength="100"/></td>
				<th><label>业务经办人移动电话</label>
				<td>
					<s:textfield name="workOrder.businessAgentPhone" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>销售业务员联系电话</label>
				<td><s:textfield name="workOrder.salesClerkContactPhone" cssClass="default-input" maxlength="100"/></td>
				<th><label>客服联系人联系电话</label>
				<td>
					<s:textfield name="workOrder.customerServiceContactPersonPhone" cssClass="default-input" maxlength="100"/>
				</td>
			</tr>
			<tr> 
				<th><label>业务经办人联系电话</label>
				<td><s:textfield name="workOrder.businessAgentContactPhone" cssClass="default-input" maxlength="100"/></td>
			</tr>
			
			<tr>
				<th><label>营业执照-文件</label></th>
				<td>
					<s:file name="listBean[0].upload" cssClass="default-input"/>
					<s:hidden name="workOrder.businessLicenseCertId" value="%{workOrder.businessLicenseCertId}"/>
				</td>
			</tr>
			<tr>
				<th><label>法人身份证</label></th>
				<td>
					<s:file name="listBean[1].upload" cssClass="default-input"/>
					<s:hidden name="workOrder.idCardCertId" value="%{workOrder.idCardCertId}"/>
				</td>
			</tr>
			<tr>
				<th><label>备注</label></th>
				<td colspan="3">
					<s:textarea rows="4" cols="50" name="workOrder.remark" cssStyle="margin-bottom:20px"/>
				</td>
			</tr>
		</table>

		<p class="submit">
			<s:hidden name="workOrder.id" id="id"></s:hidden>
			<s:submit value="保存" cssClass="action save"></s:submit>
		</p>
		<s:token />
	</s:form>
</body>