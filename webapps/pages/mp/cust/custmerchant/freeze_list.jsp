<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp"%>

<html>
<head>
<meta name="decorator" content="background" />
<title>商户冻结管理</title>
<script type="text/javascript">
function checkAuth(url){
	//检查是否可授权
	$.lightssh.checkAuth(
		{'contextPath':'<%= request.getContextPath() %>'
		,'checkPassword': false
		,'targetUrl':url}
		,function(succes,ticket,msg){ 
			if( !succes )
				return;
			if( ticket != null || ticket != '' ) {
				url += '&auth.ticket=' + ticket;
			}
			window.location.href=url;
		}
	);//end $.lightssh.checkAuth
}
function doFreeze(id,name,status,statusName){
	var url = '<s:url value="/mp/cust/custmerchant/activateOrFreeze.do"/>?vo.identity='+id+'&vo.status='+status;
	if(name==null)
		name =id;
	if( confirm('确认'+statusName+'子账户[' + name + ']'))
		checkAuth(url);
}
function removeblank(arg){
	var obj=arg.value;
	if(obj.replace(/^\s+|\s+$/g,'')!=''){
		arg.value = obj.replace(/^\s+|\s+$/g,'');
	}
}
</script>
</head>

<body>
	<ul class="path">
		<li>冻结管理</li>
		<li>商户冻结管理</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp"%>

	<s:form name="list" method="post">
		<table class="profile">
			<tr>
				<th><label>商户名称</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_name" value="${param['filter_LIKE_S_name'] }" size="20" maxlength="100" onblur="removeblank(this);"/>
				</td>
				<th><label>商户编号</label></th>
				<td colspan="2">
					<input type="text" name="filter_LIKE_S_code" value="${param['filter_LIKE_S_code'] }" size="20" maxlength="100"  onblur="removeblank(this);"/>
				</td>
				<th><label>商户状态</label></th>
				<td colspan="2">
					<s:select cssStyle="width: 150px;" list="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@values()"
								name="vo.status" value="vo.status.value" listKey="getValue()" listValue="getDisplayName()" headerKey="" headerValue=""></s:select>
				</td>
			</tr>
			<tr>
				<th><label>企业客户名称</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_cust.name" value="${param['filter_LIKE_S_cust.name'] }" size="20" maxlength="100"  onblur="removeblank(this);"/>
				</td>
				<th><label>企业客户编号</label></th>
				<td>
					<input type="text" name="filter_LIKE_S_cust.id" value="${param['filter_LIKE_S_cust.id'] }" size="20" maxlength="100"  onblur="removeblank(this);"/>
				</td>
				<td colspan="3"><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		</table>
	</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1" />
		</mys:column>
		<mys:column title="商户名称" width="150px">
			<s:set name="name" value="@com.google.code.lightssh.common.util.TextFormater@format(name, 20, true)" />
			<s:property value="name"/>
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
					<s:if test="'EFFECTIVE' != status.value">
						<li><a href="#" onclick="doFreeze('<s:property value="identity"/>','<s:property value="name"/>','EFFECTIVE','解冻')">解冻账户</a></li>
						</s:if>
					<s:elseif test="status.value != 'FROZEN'">
						<li><a href="#" onclick="doFreeze('<s:property value="identity"/>','<s:property value="name"/>','FROZEN','冻结')">冻结账户</a></li>
					</s:elseif>
				</ul>
			</div>
		</mys:column>
	</mys:table>

	<mys:pagination value="page" />
</body>
</html>