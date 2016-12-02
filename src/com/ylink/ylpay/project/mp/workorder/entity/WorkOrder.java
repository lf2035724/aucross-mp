package com.ylink.ylpay.project.mp.workorder.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import com.google.code.lightssh.common.entity.base.BaseModel;
import com.google.code.lightssh.common.model.Sequenceable;
import com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus;
import com.ylink.modules.orm.hibernate.type.CustomEnumType;
import com.ylink.ylpay.common.project.portal.constant.EntityStatus;
import com.ylink.ylpay.project.constants.EncodeRulesConstant;
import com.ylink.ylpay.project.utils.FieldNameAnnotation;

/**
 * @author feng.li
 */
@Entity
@Table( name = "T_WORK_ORDER" )
public class WorkOrder extends BaseModel implements Sequenceable, Cloneable{

	private static final long serialVersionUID = 4245572814121984693L;

	/**
	 * 工单编号
	 */
	@Column(name = "ORDER_NO")
	@FieldNameAnnotation(name = "工单编号")
	private String orderNo;
	
	/**
	 * 用户名称
	 */
	@Column(name = "USER_NAME")
	@FieldNameAnnotation(name = "用户名称")
	private String userName;
	
	/**
	 * 线路分类
	 */
	@Column(name = "LINE_CLASSIFICATION")
	@FieldNameAnnotation(name = "线路分类")
	private String lineClassification;
	
	/**
	 * 通讯地址
	 */
	@Column(name = "FORWARDING_ADDRESS")
	@FieldNameAnnotation(name = "通讯地址")
	private String forwardingAddress;
	
	/**
	 * 接入端地址
	 */
	@Column(name = "ACCESS_TERMINAL_ADDRESS")
	@FieldNameAnnotation(name = "接入端地址")
	private String accessTerminalAddress;
	
	/**
	 * 用户联系人
	 */
	@Column(name = "CONTACT_USER_NAME")
	@FieldNameAnnotation(name = "用户联系人")
	private String contactUserName;
	
	/**
	 * 用户联系电话
	 */
	@Column(name = "CONTACT_USER_PHONE")
	@FieldNameAnnotation(name = "用户联系电话")
	private String contactUserPhone;
	
	/**
	 * 用户移动电话
	 */
	@Column(name = "CONTACT_USER_MOBILE")
	@FieldNameAnnotation(name = "用户移动电话")
	private String contactUserMobile;
	
	/**
	 * 接入速率
	 */
	@Column(name = "ACCESS_RATE")
	@FieldNameAnnotation(name = "接入速率")
	private String accessRate;
	
	/**
	 * 接入方式
	 */
	@Column(name = "ACCESS_METHOD")
	@FieldNameAnnotation(name = "接入方式")
	private String accessMethod;
	
	/**
	 * 业务类别
	 */
	@Column(name = "BUSINESS_TYPE")
	@FieldNameAnnotation(name = "业务类别")
	private String businessType;
	
	/**
	 * 工单类型
	 */
	@Column(name = "ORDER_TYPE")
	@FieldNameAnnotation(name = "工单类型")
	private String orderType;
	
	/**
	 * 上行节点名称
	 */
	@Column(name = "ASCENDING_NODE_NAME")
	@FieldNameAnnotation(name = "上行节点名称")
	private String ascendingNodeName;
	
	/**
	 * 已有ip地址
	 */
	@Column(name = "IP_ALREADY")
	@FieldNameAnnotation(name = "已有ip地址")
	private String ipAlready;
	
	/**
	 * 申请ip数量
	 */
	@Column(name = "IP_AMOUNT")
	@FieldNameAnnotation(name = "申请ip数量")
	private int ipAmount;
	
	/**
	 * 计算机台数
	 */
	@Column(name = "PC_AMOUNT")
	@FieldNameAnnotation(name = "计算机台数")
	private int pcAmount;
	
	/**
	 * 新ip分配
	 */
	@Column(name = "NEW_IP_DISTRIBUTION")
	@FieldNameAnnotation(name = "新ip分配")
	private String newIpDistribution;
	
	/**
	 * 分配人签字
	 */
	@Column(name = "DISTRIBUTION_SIGN")
	@FieldNameAnnotation(name = "分配人签字")
	private String distributionSign;
	
	/**
	 * 分配日期
	 */
	@Column(name = "DISTRIBUTIO_DATE")
	@FieldNameAnnotation(name = "分配日期")
	private Date distributioDate;
	
	/**
	 * 线路终端设备
	 */
	@Column(name = "LINE_TERMINAL_DEVICE")
	@FieldNameAnnotation(name = "线路终端设备")
	private String lineTerminalDevice;
	
	/**
	 * 原设备型号
	 */
	@Column(name = "ORIGINAL_DEVICE_MODEL")
	@FieldNameAnnotation(name = "原设备型号")
	private String originalDeviceModel;
	
	/**
	 * 原设备数量
	 */
	@Column(name = "ORIGINAL_DEVICE_AMOUNT")
	@FieldNameAnnotation(name = "原设备数量")
	private int originalDeviceAmount;
	
	/**
	 * 部门名称
	 */
	@Column(name = "DEPARTMENT_NAME")
	@FieldNameAnnotation(name = "部门名称")
	private String departmentName;
	
	/**
	 * 业务经办人
	 */
	@Column(name = "BUSINESS_AGENT")
	@FieldNameAnnotation(name = "业务经办人")
	private String businessAgent;
	
	/**
	 * 销售业务员
	 */
	@Column(name = "SALES_CLERK")
	@FieldNameAnnotation(name = "销售业务员")
	private String salesClerk;
	
	/**
	 * 客服联系人
	 */
	@Column(name = "CUSTOMER_SERVICE_CONTACT_PERSON")
	@FieldNameAnnotation(name = "客服联系人")
	private String customerServiceContactPerson;
	
	/**
	 * 销售业务员移动电话
	 */
	@Column(name = "SALES_CLERK_PHONE")
	@FieldNameAnnotation(name = "销售业务员移动电话")
	private String salesClerkPhone;
	
	/**
	 * 客服联系人移动电话
	 */
	@Column(name = "CUSTOMER_SERVICE_CONTACT_PHONE")
	@FieldNameAnnotation(name = "客服联系人移动电话")
	private String customerServiceContactPhone;
	
	/**
	 * 业务经办人移动电话
	 */
	@Column(name = "BUSINESS_AGENT_PHONE")
	@FieldNameAnnotation(name = "业务经办人移动电话")
	private String businessAgentPhone;
	
	/**
	 * 销售业务员联系电话
	 */
	@Column(name = "SALES_CLERK_CONTACT_PHONE")
	@FieldNameAnnotation(name = "销售业务员联系电话")
	private String salesClerkContactPhone;
	
	/**
	 * 流量费
	 */
	@Column(name = "FLOW_FEE")
	@FieldNameAnnotation(name = "流量费")
	private String flowFee;
	
	/**
	 * 月租费
	 */
	@Column(name = "MONTHLY_FEE")
	@FieldNameAnnotation(name = "月租费")
	private String monthlyFee;
	
	/**
	 * 截止日期
	 */
	@Column(name = "EXPIRY_DATE")
	@FieldNameAnnotation(name = "截止日期")
	private Date expiryDate;
	
	/**
	 * 计费周期
	 */
	@Column(name = "BILLING_CYCLEEE")
	@FieldNameAnnotation(name = "计费周期")
	private String billingCycleee;
	
	/**
	 * 客服联系人联系电话
	 */
	@Column(name = "CUSTOMER_SERVICE_CONTACT_PERSON_PHONE")
	@FieldNameAnnotation(name = "客服联系人联系电话")
	private String customerServiceContactPersonPhone;
	
	/**
	 * 业务经办人联系电话
	 */
	@Column(name = "BUSINESS_AGENT_CONTACT_PHONE")
	@FieldNameAnnotation(name = "业务经办人联系电话")
	private String businessAgentContactPhone;
	
	/**
	 * 上传的身份证证件对象ID
	 */
	@Column(name = "ID_CARD_CERT_ID")
	private long idCardCertId;
	
	/**
	 * 上传的营业执照证件对象ID
	 */
	@Column(name = "BUSINESS_LICENSE_CERT_ID")
	private long businessLicenseCertId;
	
	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME")
	@FieldNameAnnotation(name = "创建时间")
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	@Column(name = "UPDATED_TIME")
	@FieldNameAnnotation(name = "更新时间")
	private Date updatedTime;
	
	/**
	 * 状态
	 */
	@Column(name = "STATUS")
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.ylink.ylpay.common.project.portal.constant.EntityStatus" ) } )
	@FieldNameAnnotation(name = "状态")
	private EntityStatus status;
	
	/**
	 * 审核状态
	 */
	@Column(name = "AUDIT_STATUS")
	@Type( type = CustomEnumType.ENUM_TYPE, parameters = { @Parameter( name = "enumClass", value = "com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus" ) } )
	@FieldNameAnnotation(name = "审核状态")
	private WorkOrderAuditStatus workOrderAuditStatus;
	
	/**
	 * 创建者
	 */
	@Column(name = "CREATOR")
	@FieldNameAnnotation(name = "创建者")
	private String creator;
	
	/**
	 * 备注
	 */
	@Column(name = "REMARK")
	@FieldNameAnnotation(name = "备注")
	private String remark;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLineClassification() {
		return lineClassification;
	}

	public void setLineClassification(String lineClassification) {
		this.lineClassification = lineClassification;
	}

	public String getForwardingAddress() {
		return forwardingAddress;
	}

	public void setForwardingAddress(String forwardingAddress) {
		this.forwardingAddress = forwardingAddress;
	}

	public String getAccessTerminalAddress() {
		return accessTerminalAddress;
	}

	public void setAccessTerminalAddress(String accessTerminalAddress) {
		this.accessTerminalAddress = accessTerminalAddress;
	}

	public String getContactUserName() {
		return contactUserName;
	}

	public void setContactUserName(String contactUserName) {
		this.contactUserName = contactUserName;
	}

	public String getContactUserPhone() {
		return contactUserPhone;
	}

	public void setContactUserPhone(String contactUserPhone) {
		this.contactUserPhone = contactUserPhone;
	}

	public String getContactUserMobile() {
		return contactUserMobile;
	}

	public void setContactUserMobile(String contactUserMobile) {
		this.contactUserMobile = contactUserMobile;
	}

	public String getAccessRate() {
		return accessRate;
	}

	public void setAccessRate(String accessRate) {
		this.accessRate = accessRate;
	}

	public String getAccessMethod() {
		return accessMethod;
	}

	public void setAccessMethod(String accessMethod) {
		this.accessMethod = accessMethod;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAscendingNodeName() {
		return ascendingNodeName;
	}

	public void setAscendingNodeName(String ascendingNodeName) {
		this.ascendingNodeName = ascendingNodeName;
	}

	public String getIpAlready() {
		return ipAlready;
	}

	public void setIpAlready(String ipAlready) {
		this.ipAlready = ipAlready;
	}

	public int getIpAmount() {
		return ipAmount;
	}

	public void setIpAmount(int ipAmount) {
		this.ipAmount = ipAmount;
	}

	public int getPcAmount() {
		return pcAmount;
	}

	public void setPcAmount(int pcAmount) {
		this.pcAmount = pcAmount;
	}

	public String getNewIpDistribution() {
		return newIpDistribution;
	}

	public void setNewIpDistribution(String newIpDistribution) {
		this.newIpDistribution = newIpDistribution;
	}

	public String getDistributionSign() {
		return distributionSign;
	}

	public void setDistributionSign(String distributionSign) {
		this.distributionSign = distributionSign;
	}

	public Date getDistributioDate() {
		return distributioDate;
	}

	public void setDistributioDate(Date distributioDate) {
		this.distributioDate = distributioDate;
	}

	public String getLineTerminalDevice() {
		return lineTerminalDevice;
	}

	public void setLineTerminalDevice(String lineTerminalDevice) {
		this.lineTerminalDevice = lineTerminalDevice;
	}

	public String getOriginalDeviceModel() {
		return originalDeviceModel;
	}

	public void setOriginalDeviceModel(String originalDeviceModel) {
		this.originalDeviceModel = originalDeviceModel;
	}

	public int getOriginalDeviceAmount() {
		return originalDeviceAmount;
	}

	public void setOriginalDeviceAmount(int originalDeviceAmount) {
		this.originalDeviceAmount = originalDeviceAmount;
	}

	public String getBusinessAgent() {
		return businessAgent;
	}

	public void setBusinessAgent(String businessAgent) {
		this.businessAgent = businessAgent;
	}

	public String getSalesClerk() {
		return salesClerk;
	}

	public void setSalesClerk(String salesClerk) {
		this.salesClerk = salesClerk;
	}

	public String getCustomerServiceContactPerson() {
		return customerServiceContactPerson;
	}

	public void setCustomerServiceContactPerson(String customerServiceContactPerson) {
		this.customerServiceContactPerson = customerServiceContactPerson;
	}

	public String getSalesClerkPhone() {
		return salesClerkPhone;
	}

	public void setSalesClerkPhone(String salesClerkPhone) {
		this.salesClerkPhone = salesClerkPhone;
	}

	public String getCustomerServiceContactPhone() {
		return customerServiceContactPhone;
	}

	public void setCustomerServiceContactPhone(String customerServiceContactPhone) {
		this.customerServiceContactPhone = customerServiceContactPhone;
	}

	public String getBusinessAgentPhone() {
		return businessAgentPhone;
	}

	public void setBusinessAgentPhone(String businessAgentPhone) {
		this.businessAgentPhone = businessAgentPhone;
	}

	public String getSalesClerkContactPhone() {
		return salesClerkContactPhone;
	}

	public void setSalesClerkContactPhone(String salesClerkContactPhone) {
		this.salesClerkContactPhone = salesClerkContactPhone;
	}

	public String getCustomerServiceContactPersonPhone() {
		return customerServiceContactPersonPhone;
	}

	public void setCustomerServiceContactPersonPhone(
			String customerServiceContactPersonPhone) {
		this.customerServiceContactPersonPhone = customerServiceContactPersonPhone;
	}

	public String getBusinessAgentContactPhone() {
		return businessAgentContactPhone;
	}

	public void setBusinessAgentContactPhone(String businessAgentContactPhone) {
		this.businessAgentContactPhone = businessAgentContactPhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public EntityStatus getStatus() {
		return status;
	}

	public void setStatus(EntityStatus status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public WorkOrderAuditStatus getWorkOrderAuditStatus() {
		return workOrderAuditStatus;
	}

	public void setWorkOrderAuditStatus(WorkOrderAuditStatus workOrderAuditStatus) {
		this.workOrderAuditStatus = workOrderAuditStatus;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String getSequenceKey() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		return sdf.format(date);
	}

	@Override
	public int getSequenceStep() {
		return EncodeRulesConstant.DEFAULT_SPIKE;
	}

	@Override
	public int getSequenceLength() {
		return 4;
	}
	
	public WorkOrder clone() {
		try {
			return ( WorkOrder ) super.clone();
		} catch ( CloneNotSupportedException e ) {
			return null;
		}
	}

	public long getIdCardCertId() {
		return idCardCertId;
	}

	public void setIdCardCertId(long idCardCertId) {
		this.idCardCertId = idCardCertId;
	}

	public long getBusinessLicenseCertId() {
		return businessLicenseCertId;
	}

	public void setBusinessLicenseCertId(long businessLicenseCertId) {
		this.businessLicenseCertId = businessLicenseCertId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getFlowFee() {
		return flowFee;
	}

	public void setFlowFee(String flowFee) {
		this.flowFee = flowFee;
	}

	public String getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(String monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public String getBillingCycleee() {
		return billingCycleee;
	}

	public void setBillingCycleee(String billingCycleee) {
		this.billingCycleee = billingCycleee;
	}
}
