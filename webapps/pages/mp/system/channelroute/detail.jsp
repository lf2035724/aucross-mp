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
		<li>渠道路由列表</li>
		<li>明细</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<table class="profile">
		<colgroup>
			<col width="15%">
			<col width="30%">
			<col width="15%">
			<col>
		</colgroup>
		<tr>
			<th><label>行别</label></th>
			<td>${vo.bankType.displayName }</td>
			<th><label>产品类型</label></th>
			<td>${vo.optType.displayName }</td>
		</tr>
		<tr>
			<th><label>跨行类型</label></th>
			<td>${vo.interBankType.displayName }</td>
			<th><label>是否默认</label></th>
			<td>${vo.isDefault.displayName }</td>
		</tr>
		<tr>
			<th><label>渠道接口</label></th>
			<td>${vo.channelApi }</td>
			<th><label>创建日期</label></th>
			<td>${vo.createdTime }</td>
		</tr>
		<tr>
			<th><label>描述</label></th>
			<td colspan="3">${vo.description }</td>
		</tr>
	</table>

	<p class="submit">
		<input type="button" class="action back" value="返回" onclick="backPage();" />
	</p>

</body>
</html>