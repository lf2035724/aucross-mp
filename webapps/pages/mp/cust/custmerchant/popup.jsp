<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<script type="text/javascript">
	function select( span ){
		var id = $( span ).attr("merchantid");
		var code = $( span ).attr("code");
		var name = $( span ).attr("name");
		var custid = $( span ).attr("custid");
		var custname = $( span ).attr("custname");
		var type = $( span ).attr("type");
		var status = $( span ).attr("status");
		var url = $( span ).attr("url");
		var description = $( span ).attr("description");
		var value = $( span ).attr("typevalue");
		var merchant = {"id":id,"code":code,"name":name,"custid":custid,"custname":custname,"type":type,"status":status,"url":url,"description":description,"value":value};
		callbackSelectMerchant( merchant );
	}
	
	function query( url ){
		popupMerchant( encodeURI(url) ); //父窗体方法
	}

	function postQuery( ){
		<s:url var="root_url"/>
		var url = '<s:property value="%{root_url}"/>';
		popupMerchant( url ,{
			'filter_LIKE_S_name':$('#popup_name').val()
			,'filter_LIKE_S_code':$('#popup_code').val()
			,'filter_LIKE_S_cust.name':$('#popup_cust_name').val()
			,'filter_LIKE_S_cust.id':$('#popup_cust_id').val()
		} );
		return false;
	}
</script>
<div>
	<s:form name="popup" namespace="/mp/cust/custmerchant" method="post" onsubmit="return postQuery();">
		<table class="profile">
			<tr>
				<th><label>商户名称</label></th>
				<td>
					<input type="text" id="popup_name" name="filter_LIKE_S_name" value="${param['filter_LIKE_S_name'] }" size="20" maxlength="100" />
				</td>
				<th><label>商户编号</label></th>
				<td colspan="2">
					<input type="text" id="popup_code" name="filter_LIKE_S_code" value="${param['filter_LIKE_S_code'] }" size="20" maxlength="100" />
				</td>
			</tr>
			<tr>
				<th><label>企业客户名称</label></th>
				<td>
					<input type="text" id="popup_cust_name" name="filter_LIKE_S_cust.name" value="${param['filter_LIKE_S_cust.name'] }" size="20" maxlength="100" />
				</td>
				<th><label>企业客户编号</label></th>
				<td>
					<input type="text" id="popup_cust_id" name="filter_LIKE_S_cust.id" value="${param['filter_LIKE_S_cust.id'] }" size="20" maxlength="100" />
				</td>
				<td><input type="submit" class="action search right" value="查询" /></td>
			</tr>
		
		</table>
	</s:form>
	
	<table class="list">
		<colgroup>
			<col class="element" width="100px" />
			<col class="element" />
			<col class="element" width="100px" />
			<col class="element" />
			<col class="element" width="40px" />
		</colgroup>
		<thead>
			<tr>
				<th>商户编号</th>
				<th>商户名称</th>
				<th>客户编号</th>
				<th>客户名称</th>
				<th>类型</th>
				<th>状态</th>
				<th>描述</th>
			</tr>
		</thead>
		
		<s:iterator value="#request.list.list" status="loop">
		<tr class="<s:property value="#loop.odd?\"odd\":\"even\""/>"
			id="<s:property value="identity"/>" 
			code="<s:property value="code"/>" 
			name="<s:property value="name"/>" 
			custid="<s:property value="cust.id"/>"
			custname="<s:property value="cust.name"/>"
			type="<s:property value="type.displayName"/>"
			status="<s:property value="status.displayName"/>"
			url="<s:property value="url"/>"
			description="<s:property value="description"/>"
			merchantid="<s:property value="identity"/>"
			typevalue ="<s:property value="type.value"/>"
			style="cursor: pointer;" onclick="select(this)">
			<td>
				<s:property value="code"/>
			</td>
			<td><s:property value="@com.google.code.lightssh.common.util.TextFormater@format(name, 20, true)" /></td>
			<td><s:property value="cust.id" /></td>
			<td><s:property value="cust.name"/></td>
			<td><s:property value="type.displayName"/></td>
			<td><s:property value="status.displayName"/></td>
			<td><s:property value="description"/></td>
		</tr>
		</s:iterator>
	</table>
	<s:set name="pagination" value="%{#request.list}"/>
	<jsp:include page="/pages/common/paginationAJAX.jsp"/>
</div>