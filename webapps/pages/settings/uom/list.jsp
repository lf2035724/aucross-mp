<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<head>
	<meta name="decorator" content="background"/>

	<title>计量单位列表</title>
	
	<script type="text/javascript">
		$(document).ready(function(){
		});
	</script>
	
</head>
	
<body>
	<ul class="path">
		<li>基础管理</li>
		<li>计量单位</li>
		<li>搜索列表</li>
	</ul>
	
	<%@ include file="/pages/common/messages.jsp" %>
	
	<s:form name="list" namespace="/settings/uom" method="post">
		<table class="profile">
			<tbody>
				<tr>
					<th><label for="type">类型</label></th>
					<td>
						<s:select id="type" list="@com.google.code.lightssh.project.uom.entity.UnitOfMeasure$UomType@values()"
						 listKey="name()" headerKey="" headerValue="" value="uom.type.name()" name="uom.type"/>
					</td>
					
					<th><label for="isocode">ISO编码</label></th>
					<td>
						<s:textfield name="uom.isoCode" id="isocode"/>
					</td>
					
					<th><label for="active">状态</label></th>
					<td>
						<s:select headerKey="" headerValue="" list="#{true:'活动的',false:'已冻结'}" 
							name="uom.active" value="uom.getActive()" id="active"/>
					</td>
					
					<td colspan="2"><input type="submit" class="action search right" value="查询"/></td>
				</tr>
			</tbody>
		</table>
	</s:form>
	
	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="50px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="类型" value="type" sortable="true" width="80px"/>
		<mys:column title="系统编号" value="code" sortable="true" width="100px"/>
		<mys:column title="ISO编号" value="isoCode" sortable="true" width="100px"/>
		<mys:column title="状态" value="active?'活动的':'已冻结'" sortKey="active" sortable="true" width="50px"/>
		<mys:column title="描述" value="description"/>
		<mys:column title="操作" width="40px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
					<li>
						<a href="<s:url value="/settings/uom/toggle.do?uom.code=%{code}"/>">
							<s:property value="%{active?'冻结':'激活'}"/>
						</a>
					</li>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="page" />
</body>
