<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>

<html>
	<head>
		<meta name="decorator" content="background"/>
		<title>邮件发送队列</title>
		
		<script src="<%= request.getContextPath() %>/scripts/jquery/plugins/cluetip/jquery.cluetip.all.js"></script>		
		<link href="<%=request.getContextPath() %>/scripts/jquery/plugins/cluetip/jquery.cluetip.css"	rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
		
			$(document).ready(function(){
				$('a.title').cluetip({splitTitle: '|'});
			});
			
		</script>
	</head>
	
	<body>
		<ul class="path">
			<li>基础数据</li>
			<li>邮件服务</li>
			<li>邮件队列</li>
		</ul>
		
		<%@ include file="/pages/common/messages.jsp" %>
		
		<s:form name="queue" namespace="/mp/settings/email" method="post">
			<table class="profile">
				<tbody>
					<tr>
						<th><label for="type">邮件类型</label></th>
						<td>
							<s:select id="type" name="emailContent.type" value="emailContent.type.name()"
								headerKey="" headerValue=""
								listKey="name()" listValue="getValue()"
								list="@com.ylink.ylpay.project.mp.email.entity.EmailContent$Type@values()"/>
						</td>
						<th><label for="status">状态</label></th>
						<td>
							<s:select id="status" name="emailContent.status" value="emailContent.status.name()"
								headerKey="" headerValue=""
								listKey="name()" listValue="getValue()"
								list="@com.ylink.ylpay.project.mp.email.entity.Status@values()"/>
						</td>
						<th><label for="addressee">收件人</label></th>
						<td>
							<s:textfield name="emailContent.addressee" size="40"/>
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
			<mys:column title="标题" value="emailContent.subject" sortable="true"/>
			<mys:column title="收件人" sortable="false" width="100px">
				<s:if test="emailContent.addressees.length == 1">
					<s:property value="%{emailContent.addressee}"/>
				</s:if>
				<s:else>
					<a class="title" href="#" title="<s:property value="%{emailContent.addressees}"/>">
						<s:property value="%{emailContent.addressees[0]}"/>
					</a>
				</s:else>
			</mys:column>
			<mys:column title="失败/重发次数" sortable="false" width="60px">
				<s:property value="failureCount" />/<s:property value="maxSendCount==null?0:maxSendCount" />
			</mys:column>
			<mys:column title="状态" value="status" sortable="true" width="60px"/>
			<mys:column title="异常信息" sortable="false" width="200px">
				<s:if test="errMsg.length() <= 30">
					<s:property value="%{errMsg}"/>
				</s:if>
				<s:else>
					<a class="title" href="#" title="<s:property value="%{errMsg}"/>">
						<s:property value="%{@com.google.code.lightssh.common.util.TextFormater@format(errMsg,30)}"/>
					</a>
				</s:else>
			</mys:column>
			<mys:column title="创建时间" value="createdTime" width="160px"/>
			<mys:column title="操作" width="40px" cssClass="action">
				<span>&nbsp;</span>
				<div class="popup-menu-layer">
					<ul class="dropdown-menu">
						<li><a href="<s:url value="view.do?emailContent.id=%{emailContent.id}"/>">邮件内容详情</a></li>
						<li class="section"></li>
					</ul>
				</div>
			</mys:column>
		</mys:table>
	
		<mys:pagination value="page" />
	</body>
</html>