<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
</head>

<body>
	<ul class="path">
		<li>基础管理</li>
		<li>基础数据</li>
		<li>渠道路由</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form action="list" method="post">
		<table class="profile">
			<tr>
				<th><label>行别</label></th>
				<td>
					<s:select cssStyle="width: 250px;" list="@com.ylink.ylpay.common.project.channel.constant.BankType@values()"
						name="bankType" value="%{#parameters['bankType'] }" listKey="name()" listValue="getDisplayName()"
						headerKey="" headerValue=""></s:select>
				</td>
				<th><label>产品类型</label></th>
				<td>
					<s:select cssStyle="width: 250px;" list="@com.ylink.ylpay.common.project.mp.constant.OptType@values()"
						name="optType" value="%{#parameters['optType'] }" listKey="name()" listValue="getDisplayName()"
						headerKey="" headerValue=""></s:select>
				</td>
			</tr><tr>
				<th><label>跨行类型</label></th>
				<td>
					<s:select cssStyle="width: 250px;" list="@com.ylink.ylpay.common.project.mp.constant.InterBankType@values()"
						name="interBankType" value="%{#parameters['interBankType'] }" listKey="name()" listValue="getDisplayName()"
						headerKey="" headerValue=""></s:select>
				</td>
				<th><label>渠道接口</label></th>
				<td>
					<s:textfield name="filter_LIKE_S_channelApi" value="%{#parameters['filter_LIKE_S_channelApi'] }" maxlength="100" cssStyle="width: 250px;" />
					<input type="submit" class="action search right" value="查询" />
				</td>
			</tr>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="行别" value="bankType" sortable="true" width="100px" />
		<mys:column title="产品类型" value="optType" sortable="true" width="150px" />
		<mys:column title="跨行类型" value="interBankType" sortable="true" width="100px" />
		<mys:column title="是否默认" value="isDefault" sortable="true" width="100px" />
		<mys:column title="渠道接口" value="channelApi" width="150px" />
		<mys:column title="创建日期" value="createdTime" width="100px" />
		<mys:column title="描述" width="200px">
			<s:property value="@com.google.code.lightssh.common.util.TextFormater@format(description, 20, true)" />
		</mys:column>
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li><a href="<s:url action="detail.do?vo.identity=%{identity }" />">明细</a></li>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="page" />

</body>
</html>