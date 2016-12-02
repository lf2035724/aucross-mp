<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/pages/common/taglibs.jsp" %>
	
	<head>
		<meta name="decorator" content="background"/>
		
		<title>触发器时间</title>
		
		        <link rel="stylesheet" type="text/css" media="all" href="<%= request.getContextPath() %>/scripts/jquery/plugins/timepicker/timepicker.css" />
		<script type="text/javascript" src="<%= request.getContextPath() %>/scripts/jquery/plugins/timepicker/timepicker.js"></script>
	
		<script type="text/javascript">
			$(document).ready(function(){
				$('#timepicker').timepicker({
				    hourText: '小时',
				    minuteText: '分钟',
				    amPmText: ['上午', '下午'],

				    defaultTime: '0:0',
				    
				    minutes: {
				        starts:0,
				        ends: 59,
				        interval:1
				    },
				});
				$("#profile_form").validate({
					submitHandler : function(form) {
						form.submit();
					}
				});
			});

			
			function dosubmit( ){
				var form = $("#profile_form");
				return true;
			}
		</script>
	</head>
	
<body>
	<ul class="path">
		<li>基础管理</li>
		<li>计划任务</li>
		<li>触发器时间</li>
	</ul>
	
	<%@ include file="/pages/common/messages.jsp" %>
	<!-- onsubmit="return dosubmit();" -->
	<s:form id="profile_form" action="saveTrigger" namespace="/settings/scheduler" method="post" onsubmit="return dosubmit();">
		<table class="profile">
			<caption>编辑触发器</caption>
			<tbody>
				<tr>
					<th><label>组名</label></th>
					<td>
						<s:hidden name="group"/>
						<s:property value="group"/>
					</td>
				</tr>
				<tr>
					<th><label>名称</label></th>
					<td>
						<s:hidden name="name"/>
						<s:property value="name"/>
					</td>
				</tr>
				<tr>
					<th><label>描述</label></th>
					<td>
						<s:property value="#request.trigger.description"/>
					</td>
				</tr>
				
				<tr>
					<th><label>CRON 表达式</label></th>
					<td>
						<s:property value="#request.trigger.cronExpression"/>
					</td>
				</tr>
				
				<tr>
					<th><label class="required">时钟</label></th>
					<td>
						秒：<input name="second" class="bx" /><br>
						分：<input name="minute" class="bx" /><br>
						时：<input name="hour" class="bx" /><br>
						日期：<input name="dayOfMonth" class="bx" /><br>
						月份：<input name="month" class="bx" /><br>
						星期：<input name="dayOfWeek" class="bx" />
					</td>
				</tr>
				
			</tbody>
		</table>
	
		<s:token/>
	
		<p class="submit">
			<input type="submit" class="action save" name="Submit" value="修改时钟"/>
		</p>
	</s:form>
</body>