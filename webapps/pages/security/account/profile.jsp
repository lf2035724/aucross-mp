<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
	
<head>
	<meta name="decorator" content="background"/>
	
	<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/ui/i18n/jquery.ui.datepicker_<s:property value="locale"/>.js"></script>
	
	<title>编辑登录账号</title>
	
	<script type="text/javascript">
		
		$(document).ready(function(){
			//登录名称校验
			jQuery.validator.addMethod("loginName", function(value, element) {    
				return this.optional(element) ||  /^[\w\u4e00-\u9fa5]*[0-9]*[a-z]*[A-Z]*$/.test(value);   
			},"只能包含中文,数字[0-9],英文字母[a-z,A-Z]！");    

			var accountId = $( '#accountId' ).val();
			var suffixUrl = '?account.id=' + accountId;
			var checkLoginNameUrl = '<%= request.getContextPath() %>/security/account/checkLoginName.do' + suffixUrl;
			var checkMobileUrl = '<%= request.getContextPath() %>/security/account/checkMobile.do' + suffixUrl;
			var checkEmailUrl = '<%= request.getContextPath() %>/security/account/checkEmail.do' + suffixUrl;
			/**
			 * 数据校验
			 */
			$( '#profile_form' ).validate( {
				rules: {
					'account.realName': { required: true },
					'account.loginName': {
						required: true,
						remote: {
							url: checkLoginNameUrl,
							type: 'post',
							dataType: 'json',
							data: {
								'loginName': function() {
									return $( '#name' ).val();
								}
							}
						}
					},
					'account.mobile': {
						required: true,
						remote: {
							url: checkMobileUrl,
							type: 'post',
							dataType: 'json',
							data: {
								'mobile': function() {
									return $( '#mobile' ).val();
								}
							}
						}
					},
					'account.email': {
						required: true,
						email: true,
						remote: {
							url: checkEmailUrl,
							type: 'post',
							dataType: 'json',
							data: {
								'email': function() {
									return $( '#email' ).val();
								}
							}
						}
					},
				},
				messages: {
					'account.loginName': {
						remote: '该登录账号已经存在'
					},
					'account.mobile': {
						remote: '该手机已经存在'
					},
					'account.email': {
						remote: '该邮箱已经存在'
					}
				}
			});
			
			$("#account_start_date").datepicker( );
			$("#account_end_date").datepicker( );
		});
		
		/**
		 * 选择Party弹出框
		 */		
		function popup(){
			popupParty( '<s:url value="/party/popup.do"/>',{"party_type":"org"});
		}
		
		//$(popup).html('') fixed IE bug
		function popupParty( url ,param){
			var popup = $("#party_popup");
			//$( popup ).html( '<div><img id=\'loading\' src=\'<%= request.getContextPath() %>/images/loading.gif\'/>' );
			$( popup ).dialog({
				resizable: true,modal: true,height:500,width: 700,
				close: function(event, ui) {$(this).dialog('destroy'); },				
				buttons: {				
					"关闭": function() {$(this).dialog('destroy');}
				}
			});
			
			$.post(url,param,function(data){$( popup ).html( data );});
		}
		
		/**
		 * 弹出框回调
		 */
		function callbackSelectParty( party ){
			$("#span_party_name").html( party.name );
			$("#party_name").val( party.name );
			$("#party_id").val( party.id );
			$("#party_clazz").val( party.clazz );
			$("#party_popup").dialog('destroy').html('');
			$("label[for='party_id']").remove(); //移除样式
		}

		/**
		 * 选择角色
		 */		
		function popupRole(){
			var popup = $("#role_popup");
			$(popup).dialog({
				resizable: true,
				height:400,
				width: 700,
				modal: true,
				close: function(event, ui) { $(this).dialog('destroy'); },				
				buttons: {					
					"关闭": function() {
						$(this).dialog('destroy');
					}
					,"选择": selectRoles
				}
			});
			
			var req_url = '<s:url value="/security/role/popup.do"/>';
			$.post(req_url,{'page.size':'1024','role.status':'EFFECTIVE'}
				,function(json){showRoleList( $(popup),json.page.list )});

			/**
			 * 显示角色列表
			 */
			function showRoleList( popup,list ){
				$(popup).html('');
				var table = $("<table class='list'><tr><th></th><th>名称</th><th>描述</th></tr></table>");
				$(popup).append(table)
				$.each( list , function( index,role ){
					var item = "<td><input type='checkbox' value='"+role.id+"' rolename='"+role.name+"'/></td>"
					item += "<td>"+role.name+"</td>";
					item += "<td>" + (role.description==null?'':role.description) + "</td>";
					$(table).append( "<tr>"+ item + "</tr>"  );
				});
				
				$("#loading").fadeOut();
			}
			
			/**
			 * 选择角色
			 */
			function selectRoles( ){
				$("#selected_roles").html('');
				$("input[name='account.roles']").remove();
				$.each( $("input:checked"), function( index, checkbox ){
					var rolename = $(checkbox).attr('rolename');
					$("#selected_roles").append( (index==0?'':' , ')+ rolename );
					$("#selected_roles").append("<input type='hidden' name='account.roles' value='"+$(checkbox).attr('value')+"'/>" );
				});
				$(this).dialog('destroy');
				$("input[type=submit]").removeAttr("disabled");
			}
		}
	</script>
</head>
	
<body>
	<s:set name="isInsert" value="%{(account==null||account.id==null)}"/>
	<ul class="path">
		<li>系统管理</li>
		<li>登录账号</li>
		<li><s:property value="%{#isInsert?\"新增用户\":\"修改账号\"}"/></li>
	</ul>
	
	<input type="button" class="action list" value="用户列表" onclick="location.href='<s:url value="list.do"/>'"/>
		
	<%@ include file="/pages/common/messages.jsp" %>
	
	<s:form id="profile_form" action="save" namespace="/security/account" method="post">
		<table class="profile">
			<tbody>
				<tr>
					<th><label for="name" class="required">登录账号</label></th>
					<td>
						<s:hidden id="accountId" name="account.id"/>
						
						<s:if test="#isInsert">
							<s:textfield id="name" name="account.loginName" size="40" maxlength="300"/>
						</s:if>
						<s:else>
							<s:property value="account.loginName"/>
							<s:hidden id="name" name="account.loginName"/>
						</s:else>
					</td>
				</tr>
				<tr>
					<th><label for="realName" class="required">姓名</label></th>
					<td><s:textfield id="realName" name="account.realName" size="40" maxlength="20" /></td>
				</tr>
				<tr>
					<th><label for="email" class="required">邮箱</label></th>
					<td><s:textfield id="email" name="account.email" size="40" maxlength="100" /></td>
				</tr>
				<tr>
					<th><label for="mobile" class="required">手机</label></th>
					<td><s:textfield id="mobile" name="account.mobile" size="40" maxlength="20" /></td>
				</tr>
				<tr>
					<th><label for="phone">电话</label></th>
					<td><s:textfield id="phone" name="account.phone" size="40" maxlength="20" /></td>
				</tr>
				<tr>
					<th><label for="account_party">部门</label></th>
					<td>
						<span class="popup party" onclick="popup();">&nbsp;</span>
						<s:hidden name="account.party.id" id="party_id"/>
						<s:hidden name="account.party.name" id="party_name"/>
						<s:hidden name="account.party" value="%{account.party.id.startsWith('ORG')?'organization':'person'}" id="party_clazz"/>
						<span id="span_party_name"><s:property value="%{account.party.name}"/></span>
					</td>
				</tr>
				<tr>
					<th><label for="account_start_date">有效期</label></th>
					<td>
						<s:textfield name="account.period.start" id="account_start_date" size="10" /> -
						<s:textfield name="account.period.end" id="account_end_date" size="10"/>
					</td>
				</tr>
				<tr>
					<th><label for="role">权限</label></th>
					<td>
						<span class="popup" onclick="popupRole();">&nbsp;</span>
						
						<span id="selected_roles">
							<s:iterator value="account.roles" status="loop">
								<s:hidden name="account.roles" value="%{id}"/>
								<s:property value="#loop.first?'':' , '"/><s:property value="name"/>
							</s:iterator>
						</span>
					</td>
				</tr>
				<tr>
					<th><label for="desc">描述</label></th>
					<td><s:textarea id="desc" name="account.description" cols="60" rows="5"/></td>
				</tr>
			</tbody>
		</table>
	
		<s:token />
		
		<p class="submit">
			<input type="submit" class="action save" name="Submit" 
				value="<s:property value="%{#isInsert?\"新增账号\":\"修改账号\"}"/>"/>
				
<!-- 			<input type="submit" class="action save" name="saveAndNext" value="保存&新增下一个"/> -->
		</p>
	</s:form>

	<div id="party_popup" title="所属关系" style="display: none;">&nbsp;</div>
	<div id="role_popup" title="角色列表" style="display: none;">&nbsp;</div>
</body>