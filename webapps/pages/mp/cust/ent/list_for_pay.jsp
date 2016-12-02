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
		<li>汇款企业审核</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp" %>

	<mys:table cssClass="list" value="entPage" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="客户编号" value="identity" sortKey="id" sortable="true" width="120px" />
		<mys:column title="企业名称" value="name" />
		<mys:column title="营业执照" value="licenseNo" sortable="true" width="120px" />
		<mys:column title="法人名称" value="legalpersonName" sortable="true" width="160px" />
		<mys:column title="联系电话" value="phone" sortable="true" width="100px" />
		<%-- 
		<mys:column title="状态" sortKey="status" sortable="true" width="50px">
			<s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(status).displayName" />
		</mys:column>
		--%>
		<mys:column title="审核状态" value="auditStatus" sortable="true" width="50px" />
		<mys:column title="创建标志" value="registeredType" sortable="false" width="50px" />
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li class="view">
						<a href="<s:url value="/cust/enterprise/detail.do?vo.id=%{id}"/>">客户信息</a>
					</li>
					<li class="section" />
					<li class="money"><a href="<s:url action="pay.do?customer.id=%{id}" />">打款验证</a></li>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="entPage"/>

</body>
