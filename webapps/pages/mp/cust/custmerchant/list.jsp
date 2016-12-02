<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>商户列表</title>
</head>

<body>
	<ul class="path">
		<li>客户管理</li>
		<li>商户管理</li>
		<li>商户列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form name="list" method="post">
		<table class="profile">
			<tr>
				<th><label>商户名称</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_name" value="${param['filter_LIKE_S_name'] }" size="20" maxlength="100" />
				</td>
				<th><label>商户编号</label></th>
				<td colspan="2">
					<input type="text" name="filter_LIKE_S_code" value="${param['filter_LIKE_S_code'] }" size="20" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th><label>企业客户名称</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_cust.name" value="${param['filter_LIKE_S_cust.name'] }" size="20" maxlength="100" />
				</td>
				<th><label>企业客户编号</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_cust.id" value="${param['filter_LIKE_S_cust.id'] }" size="20" maxlength="100" />
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="商户名称" width="150px">
			<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(name, 20, true)" />
		</mys:column>
		<mys:column title="商户编号" value="code" sortable="true" width="100px" />
		<mys:column title="企业客户名称" width="150px">
			<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(cust.name, 20, true)" />
		</mys:column>
		<mys:column title="企业客户编号" value="cust.id" sortable="true" width="100px" />
		<mys:column title="商户状态" value="status" sortable="true" width="50px" />
		<mys:column title="审核状态" value="auditStatus" sortable="true" width="50px" />
		<mys:column title="商户类型" value="type" sortable="true" width="50px" />
		<mys:column title="数字证书更新时间" sortable="true" value="certUpdateTime" width="100px" />
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<shiro:hasPermission name="CUST_CUSTMERCHANT_MODIFYDETAIL">
					<li><a href="<s:url action="modifyDetail.do?vo.identity=%{identity }" />">修改</a></li>
					</shiro:hasPermission>
					<li><a href="<s:url action="detail.do?vo.identity=%{identity }" />">明细</a></li>
					<li class="section"></li>
					<s:if test="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@EFFECTIVE == status">
						<li><a href="<s:url action="updateCert.do?vo.identity=%{identity }" />">更新证书</a></li>
					</s:if>
					<li><a href="<s:url action="downloadJks.do?vo.identity=%{identity }" />">下载私钥</a></li>
					<li><a href="<s:url action="downloadCer.do?vo.identity=%{identity }" />">下载公钥</a></li>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>