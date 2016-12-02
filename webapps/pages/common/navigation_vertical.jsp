<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<title>index page</title>
		<link rel="stylesheet" type="text/css" media="all" href="<%= request.getContextPath() %>/styles/<mys:theme />/theme.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/jquery.min.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/jquery.cookie.js"></script>
		<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/my/layout/vertical_menu.js"></script>
		<style type="text/css">	
			body{
				padding:0;
				background-color: #FCFFEF;
			}
			
			#navigation{
			}
			
			ul#vertical_navigation{
				margin:0;/* fixed IE bug*/
				padding:0;/* fixed IE bug*/
			}
			
			ul#vertical_navigation,ul#vertical_navigation li a{
				width:100%;
			}
			
		</style>
		
		<script>
			/**
			 * init vertical menu
			 */
			$(document).ready(function(){
				initVerticalMenu( "vertical_navigation" );
			})
		</script>
	</head>
	
	<body>
	<div id="navigation">
		
		<ul id="vertical_navigation" class="menu vertical-menu">
			<shiro:hasPermission name="SYS_MGR">
			<li>
				<a href="#">系统管理</a>
				<ul id="system_mgr">
				
				 	<shiro:hasPermission name="ORG_MGR">
				    <shiro:hasPermission name="SETTINGS_ORGANIZATION_VIEWPARENT">
					<li><a href="<s:url value="/settings/organization/viewparent.do"/>" target="main_frame">企业资料</a></li>
					</shiro:hasPermission>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="LOGIN_ACCOUNT_MGR">
					<li>
						<a href="#">登录账号</a>
						<ul id="login_account">
							<shiro:hasPermission name="SECURITY_ACCOUNT_PASSWORD">
							<li><a href="<s:url value="/security/account/toEditPassword.do"/>" target="main_frame">修改密码</a></li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="SECURITY_ACCOUNT_EDIT">
							<li><a href="<s:url value="/security/account/edit.do"/>" target="main_frame">新增用户</a></li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="SECURITY_ACCOUNT_LIST">
							<li><a href="<s:url value="/security/account/list.do"/>" target="main_frame">用户列表</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="SECURITY_ACCOUNT_TODOAUDITLIST">
							<li><a href="<s:url value="/security/account/todoauditlist.do"/>" target="main_frame">待审核列表</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="SECURITY_ACCOUNT_AUDITLIST">
							<li><a href="<s:url value="/security/account/auditlist.do"/>" target="main_frame">审核结果</a></li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
					
					<shiro:hasPermission name="ROLE_MGR">
					<li>
						<a href="#">角色管理</a>
						<ul id="security">
							<shiro:hasPermission name="SECURITY_ROLE_EDIT">
							<li><a href="<s:url value="/security/role/edit.do"/>" target="main_frame">新增角色</a></li>
							</shiro:hasPermission>
							
							<shiro:hasPermission name="SECURITY_ROLE_LIST">
							<li><a href="<s:url value="/security/role/list.do"/>" target="main_frame">角色查询</a></li>
							</shiro:hasPermission>
							
							<li><a href="<s:url value="/security/role/todoauditlist.do"/>" target="main_frame">待审核列表</a></li>
							<shiro:hasPermission name="SECURITY_ROLE_AUDITLIST">
							<li><a href="<s:url value="/security/role/auditlist.do"/>" target="main_frame">审核结果</a></li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
				</ul>
			</li>
			</shiro:hasPermission>
			
			<shiro:hasPermission name="MP_BUSINESS">
			<li>
				<a href="#">业务管理</a>
				<ul id="portal_mgr">
					<shiro:hasPermission name="MP_WORKORDER">
					<li>
						<a href="#">工单管理</a>
						<ul id="portal_info_mgr">
							<shiro:hasPermission name="MP_WORKORDER_SAVE">
							<li><a href="<s:url value="/mp/workorder/toSave.do" />" target="main_frame">新增工单</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="MP_WORKORDER_SAVE">
							<li><a href="<s:url value="/mp/workorder/listWaitAudit.do" />" target="main_frame">新增工单审核</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="MP_WORKORDER_LIST">
							<li><a href="<s:url value="/mp/workorder/list.do" />" target="main_frame">工单列表</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="MP_WORKORDER_EDITAUDIT">
							<li><a href="<s:url value="/mp/workorder/todoAuditList.do" />" target="main_frame">变更工单审核</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="MP_WORKORDER_AUDIT_RESULT">
							<li><a href="<s:url value="/mp/workorder/auditList.do" />" target="main_frame">工单审核结果</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="MP_WORKORDER_IMPORT">
							<li><a href="<s:url value="/mp/workorder/toImport.do" />" target="main_frame">工单导入</a></li>
							</shiro:hasPermission>
						</ul>
					</li>
					</shiro:hasPermission>
				</ul>
			</li>
			</shiro:hasPermission>
		</ul>
		<div class="spliter">
		</div>
	</div>
	</body>
</html>