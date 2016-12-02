<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<script type="text/javascript">
	function select( span ){
		var id = $( span ).attr("id");
		var name = $( span ).attr("name");
		var party = {"bankType":id,"bankName":name};
		callbackSelectBank( party );
	}
	
	function query( url ){
		popupBank( url ); //父窗体方法
	}
	
</script>

<div>
	
	<table class="list">
		<colgroup>
			<col class="element" width="200px"/>
			<col class="element" />
		</colgroup>
		<thead>
			<tr>
				<th>银行类别</th>
				<th>银行名称</th>
			</tr>
		</thead>
		
		<s:iterator value="#request.page.list" status="loop">
		<tr class="<s:property value="#loop.odd?\"odd\":\"even\""/>"
			id="<s:property value="bankType"/>" name="<s:property value="bankName"/>" 
			style="cursor: pointer;" onclick="select(this)">
			<td>
				<s:property value="bankType"/>
			</td>
			<td><s:property value="bankName"/></td>
		</tr>
		</s:iterator>
	</table>
		
	<s:set name="pagination" value="%{#request.page}"/>
	<jsp:include page="/pages/common/paginationAJAX.jsp"/>
</div>