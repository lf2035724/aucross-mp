<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
	
<head>
	<meta name="decorator" content="background"/>
	<title>查看客户</title>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>个人客户</li>
		<li>查看客户</li>
	</ul>
		
	<%@ include file="/pages/common/messages.jsp" %>
	<table class="profile">
		<caption>客户基本信息</caption>
		<colgroup>
			<col width="15%"/>
			<col width="15%"/>
			<col width="25%"/>
			<col width="15%"/>
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th rowspan="12"><img src="<s:url value="/images/default_avatar.png"/>" height="150px"/></th>
				<th>姓名</th>
				<td><s:property value="%{personal.name}"/></td>
				<th>生日</th>
				<td><s:property value="%{personal.birthday}"/></td>
			</tr>
			<tr>
				<s:set name="credentialsType" value="@com.ylink.ylpay.common.project.mp.constant.CertificationType@parseOf(personal.credentialsType)"/>
				<th>证件类型(<s:property value="#credentialsType.displayName"/>)</th>
				<td><s:property value="%{personal.identityCardNumber}"/></td>
				<th>客户创建时间</th>
				<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(personal.certExpiryDate,'yyyy-MM-dd HH:mm:ss')"/></td>
				<!-- <td><s:property value="%{personal.certExpiryDate}"/></td> -->
			</tr>
			<tr>
				<th>注册类型</th>
				<td>
					<s:property value="@com.ylink.ylpay.common.project.mp.constant.RegisteredType@parseOf(personal.registeredType).displayName"/>
				</td>
				<th>职业</th>
				<td><s:property value="@com.ylink.ylpay.common.project.portal.constant.Profession@returnEnum(personal.profession).displayName}"/></td>
			</tr>
			<tr>
				<th>状态</th>
				<td>
					<s:set name="status" value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(personal.status)"/>
					<s:property value="#status.displayName"/>
				</td>
				<th>认证状态</th>
				<td>
					<s:set name="certStatus" value="@com.ylink.ylpay.common.project.mp.constant.AuthenticationStatus@parseOf(personal.certStatus)"/>
					<s:property value="#certStatus.displayName"/>
				</td>
			</tr>
			<tr>
				<th>证券投资经验</th>
				<td><s:property value="@com.ylink.ylpay.common.project.portal.constant.InvestmentExp@returnEnum(personal.investmentExp).displayName"/></td>
				<th>年收入</th>
				<td><s:property value="@com.ylink.ylpay.common.project.portal.constant.AnnualIncome@returnEnum(personal.annualIncome).displayName"/></td>
			</tr>
			<tr>
				<th>性别</th>
				<td><s:property value="@com.ylink.ylpay.common.project.portal.constant.SexType@returnEnum(personal.gender).displayName"/></td>
				<th>婚况</th>
				<td><s:property value="@com.ylink.ylpay.common.project.portal.constant.MaritalStatus@returnEnum(personal.maritalStatus).displayName"/></td>
			</tr>
			<tr>
				<th>民族</th>
				<td><s:property value="%{personal.ethnicGroup}"/></td>
				<th>政治面貌</th>
				<td><s:property value="%{personal.personalAffiliation}"/></td>
			</tr>
			<tr>
				<th>籍贯</th>
				<td>
					<s:property value="%{personal.country.name}"/>
					<s:property value="%{personal.secondaryGeo.name}"/>
					<s:property value="%{personal.thirdGeo.name}"/>
					<s:property value="%{personal.fourthGeo.name}"/>
				</td>
				<th>血型</th>
				<td><s:property value="%{personal.bloodType}"/></td>
			</tr>
			
			<tr>
				<th>身高</th>
				<td>
					<s:if test="personal.height != null && personal.height != 0">
						<s:property value="%{personal.height}"/>&nbsp;CM
					</s:if>
				</td>
				<th>体重</th>
				<td>
					<s:if test="personal.weight != null && personal.weight != 0">
						<s:property value="%{personal.weight}"/>&nbsp;KG
					</s:if>
				</td>
			</tr>
			<tr>
				<th>别名</th>
				<td><s:property value="%{personal.nickName}"/></td>
				<th>描述</th>
				<td><s:property value="%{personal.description}"/></td>
			</tr>
		</tbody>
	</table>
	
</body>