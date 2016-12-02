<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<head>
	<meta name="decorator" content="background"/>
	<title>工单列表</title>
	
		<script type="text/javascript">
			function doRemove( id,name ){
				var url = '<s:url value="/party/person/remove.do"/>?party=person&party.id=' + id ;
				if( confirm('确认删除人员[' + name + ']'))
					location.href=url;
			}
			
			function doDelete(id,orderNo){
				var url = '<s:url value="delete.do"/>?workOrder.id='+id;
				if(name==null)
					name =id;
				if( confirm('确认删除工单[' + orderNo + ']?')){
					checkAuth(url);
				}
			}
			
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
							url += '&auth_ticket=' + ticket;
						}
						window.location.href=url;
					}
				);//end $.lightssh.checkAuth
			}
			
			$(document).ready(function(){
				$("#createDate_start").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#createDate_end").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#distributioDate_start").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#distributioDate_end").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#updateTime_start").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#updateTime_end").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#expiryDate_start").datepicker({dateFormat: 'yymmdd',changeYear:true});
				$("#expiryDate_").datepicker({dateFormat: 'yymmdd',changeYear:true});
				
				/** 导出按钮. */
				$( '#exportBtn' ).click( function() {
					$( '#list' ).attr( 'action', "<%= request.getContextPath() %>/mp/workorder/export.do" ).submit();
				} );
				
				/** 查询按钮. */
				$( '#listBtn' ).click( function() {
					$( '#list' ).attr( 'action', "<%= request.getContextPath() %>/mp/workorder/list.do" ).submit();
				} );
			});
		</script>
</head>

<body>
	<ul class="path">
		<li>业务管理</li>
		<li>工单管理</li>
		<li>工单列表</li>
	</ul>

	<%@ include file="/pages/common/messages.jsp" %>
	
	<s:form id="list" name="list" action="/mp/workorder/list.do" method="post">
			<table class="profile">
			 <colgroup>
				<col width="10%">
				<col width="20%">
				<col width="10%">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th><label>编号</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_orderNo" value="%{#parameters['filter_LIKE_S_orderNo']}" size="15"/>
					</td>
					<th><label>接入速率</label></th>
					<td>
						<input name="filter_LIKE_S_accessRate" value="${param['filter_LIKE_S_accessRate'] }" size="15" />
					</td>
				</tr>
				
				<tr>
					<th><label>用户名称</label></th>
					<td>
						<input name="filter_LIKE_S_userName" value="${param['filter_LIKE_S_userName'] }" size="15" />
					</td>
					<th><label>接入端地址</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_accessTerminalAddress" value="%{#parameters['filter_LIKE_S_accessTerminalAddress']}" size="15"/>
					</td>
				</tr>
				
				<tr>
					<th><label>销售业务员</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_salesClerk" value="%{#parameters['filter_LIKE_S_salesClerk']}" size="15"/>
					</td>
					<th><label>客服联系人</label></th>
					<td>
						<s:textfield name="filter_LIKE_S_customerServiceContactPerson" value="%{#parameters['filter_LIKE_S_customerServiceContactPerson']}" size="15"/>
					</td>
					<th><label for="time">截止时间</label></th>
					<td>
						<input name="filter_GE_D_expiryDate" id="expiryDate_start" value="${param['filter_GE_D_expiryDate'] }" size="10" /> -
						<input name="filter_LE_D_expiryDate" id="expiryDate_end" value="${param['filter_LE_D_expiryDate'] }" size="10"/>
					</td>
				</tr>
				<tr>
					<th><label for="time">创建时间</label></th>
					<td>
						<input name="filter_GE_D_createTime" id="createDate_start" value="${param['filter_GE_D_createTime'] }" size="10" /> -
						<input name="filter_LE_D_createTime" id="createDate_end" value="${param['filter_LE_D_createTime'] }" size="10"/>
					</td>
					<th><label for="updatetime">更新时间</label></th>
					<td>
						<input name="filter_GE_D_updatedTime" id="updateTime_start" value="${param['filter_GE_D_updatedTime'] }" size="10" /> -
						<input name="filter_LE_D_updatedTime" id="updateTime_end" value="${param['filter_LE_D_updatedTime'] }" size="10"/>
					</td>
					<th><label>创建者</label></th>
					<td>
						<s:textfield name="filter_EQ_S_creator" value="%{#parameters['filter_EQ_S_creator']}" size="15"/>
					</td>
				</tr>
				<tr>
				<th><label>状态</label></th>
					<td>
						<s:select name="filter_EQ_S_status" value="#parameters['filter_EQ_S_status']" headerKey="" headerValue=""
							list="@com.ylink.ylpay.common.project.portal.constant.EntityStatus@values()" listKey="value" listValue="displayName"></s:select>
					</td>
					<th><label>审核状态</label></th>
					<td>
						<s:select name="filter_EQ_S_workOrderAuditStatus" value="#parameters['filter_EQ_S_workOrderAuditStatus']" headerKey="" headerValue=""
							list="@com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus@values()" listKey="value" listValue="displayName"></s:select>
					</td>
					<td><input type="button" id="listBtn" class="action search right" value="查询" /></td>
					<shiro:hasPermission name="MP_WORKORDER_EXPORT">
					<td><input type="button" id="exportBtn" class="action ok" value="导出excel" /></td>
					</shiro:hasPermission>
				</tr>
			</tbody>
			</table>
		</s:form>

	<mys:table cssClass="list" value="page" status="loop">
		<mys:column title="序号" width="10px">
			<s:property value="#loop.index + 1"/>
		</mys:column>
		<mys:column title="工单编号">
			<s:property value="%{orderNo}"/>
		</mys:column>
		<mys:column title="用户名称" value="userName" width="100px">
		</mys:column>
		<mys:column title="接入端地址" value="accessTerminalAddress" width="100px">
		</mys:column>
		<mys:column title="接入速率" value="accessRate" width="100px">
		</mys:column>
		<mys:column title="截止时间" value="expiryDate" width="160px">
		</mys:column>
		<mys:column title="销售业务员" value="salesClerk" width="100px">
		</mys:column>
		<mys:column title="客服联系人" value="customerServiceContactPerson" width="100px">
		</mys:column>
		<mys:column title="创建者" value="creator"/>
		<mys:column title="状态"  width="100px">
			<s:property value="@com.ylink.ylpay.common.project.portal.constant.EntityStatus@parseOf(status.value)"/>
		</mys:column>
		<mys:column title="审核状态"  width="100px">
			<s:property value="@com.google.code.lightssh.project.util.constant.WorkOrderAuditStatus@parseOf(workOrderAuditStatus.value)"/>
		</mys:column>
		<mys:column title="创建时间" value="createTime" width="160px"/>
		<mys:column title="更新时间" value="updatedTime" width="160px"/>
		<mys:column title="操作" width="10px" cssClass="action">
			<span>&nbsp;</span>
			<div class="popup-menu-layer">
				<ul class="dropdown-menu">
				<li><a href="<s:url action="view.do?workOrder.id=%{id}" />">详细信息</a></li>
				<shiro:hasPermission name="MP_WORKORDER_EDIT">
				<li><a href="<s:url action="modifyDetail.do?workOrder.id=%{id}" />">编辑</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="MP_WORKORDER_DELETE">
				<li><a href="#" onclick="doDelete('<s:property value="id"/>','<s:property value="orderNo"/>')"/>删除</a></li>
				</shiro:hasPermission>
				</ul>
			</div>
		</mys:column>
	</mys:table>
	
	<mys:pagination value="page"/>
</body>

	
