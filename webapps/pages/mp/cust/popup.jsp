<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<script type="text/javascript">
	function select( span ){
		var id = $( span ).attr("id");
		var name = $( span ).attr("name");
		var clazz = $( span ).attr("clazz");
		var customer = {"id":id,"name":name,"clazz":clazz};
		callbackSelectCustomer( customer );
	}
	
	function query( url ){
		popupCustomer( encodeURI(url) ); //父窗体方法
	}

	function postQuery( ){
		<s:url var="root_url"/>
		var url = '<s:property value="%{root_url}"/>';
		popupCustomer( url ,{'customer':''
			,'customer.id':$('#popup_cust_id').val()
			,'customer.name':$('#popup_cust_name').val()
			,'customer.status':$("input[name='customer.status']").val()
			,'customer._queryType':$("input[name='customer._queryType']").val()
		} );
		return false;
	}
</script>

<div>
	<s:form name="popup" namespace="/customer" method="post" onsubmit="return postQuery();">
		<table class="profile">
			<tbody>
				<tr>
					<th><label for="popup_cust_id">客户编号</label></th>
					<td>
						<s:hidden name="customer.status" />
						<s:hidden name="customer._queryType" />
						<s:textfield id="popup_cust_id" name="customer.id" size="20" maxlength="100"/>
					</td>
					<th><label for="popup_cust_name">客户名称</label></th>
					<td><s:textfield id="popup_cust_name" name="customer.name" size="30" maxlength="100"/></td>
					<td><input class="action search right" type="submit" value="查询"/></td>
				</tr>
			</tbody>
		</table>
	</s:form>
	
	<table class="list">
		<colgroup>
			<col class="element" width="200px" />
			<col class="element" />
			<col class="element" width="100px" />
			<col class="element" />
			<col class="element" width="40px" />
		</colgroup>
		<thead>
			<tr>
				<th>客户编号</th>
				<th>客户名称</th>
				<th>手机号</th>
				<th>状态</th>
				<th>类型</th>
			</tr>
		</thead>
		
		<s:iterator value="#request.page.list" status="loop">
		<s:set name="clazz" value="(#request.page.list[#loop.index] instanceof com.ylink.ylpay.project.mp.cust.entity.Personal)?'PERSONAL':'ENTERPRISE'"/>
		<tr class="<s:property value="#loop.odd?\"odd\":\"even\""/>"
			id="<s:property value="id"/>" 
			name="<s:property value="name"/>" 
			clazz="<s:property value="#clazz"/>" 
			style="cursor: pointer;" onclick="select(this)">
			<td>
				<s:property value="id"/>
			</td>
			<td><s:property value="name"/></td>
			<td><s:property value="@com.ylink.ylpay.project.mp.user.util.UserHelper@getMobile( id )" /></td>
			<td>
				<s:property value="@com.ylink.ylpay.common.project.mp.constant.MerchantStatus@parseOf(status).displayName"/>
			</td>
			<td><s:property value="#clazz=='PERSONAL'?'个人':'企业'"/></td>
		</tr>
		</s:iterator>
	</table>
		
	<s:set name="pagination" value="%{#request.page}"/>
	<jsp:include page="/pages/common/paginationAJAX.jsp"/>
</div>