var isPassed;
$(document).ready(function(){
	$("input[name='reject']").attr("disabled",true);
	jQuery.validator.addMethod("errorInfo", function(value, element) {
		var check = $(element).parent().parent().prev().contents().find("input[type='checkbox']");
		if($(check).attr("checked") == 'checked'){
			return !(/.{200}|^\s*$/g.test(value));
		}else{
			return true;
		}
	},jQuery.format("必填项目，字符不能超过200"));
	$("#portal_enterprise_form").validate({
		rules:{
			"details[0].description":{errorInfo:true},
			"details[1].description":{errorInfo:true},
			"details[2].description":{errorInfo:true},
			"details[3].description":{errorInfo:true}
		},
		submitHandler : function(form) {
			$.lightssh.checkAuth(
					{'contextPath': path
					,'checkPassword': true
					,'targetUrl':actionUrl}
					,function(succes,ticket,msg){ 
						if( !succes )
							return;

						if( ticket != null || ticket != '' ){
							$('<input>').attr({
							    type:'hidden',name:'auth.ticket',value: ticket,
							}).appendTo( form );
							$('<input>').attr({
							    type:'hidden',name:'isPassed',value: isPassed,
							}).appendTo( form );
						}
						
						form.submit(); 
					}
				);//end $.lightssh.checkAuth
		}
	});
});

function checkLind(data){
	if(checkSelect()){
		$("input[name='passed']").attr("disabled",true);
		$("input[name='reject']").attr("disabled",false);
	}else{
		$("input[name='passed']").attr("disabled",false);
		$("input[name='reject']").attr("disabled",true);
	}
	var text = $(data).parent().parent().next();
	if($(data).attr("checked") == "checked"){
		$(text).removeClass();
		$(text).contents().find(".is_error").val("false");
	}else{
		$(text).addClass("error_text");
		$(text).contents().find(".is_error").val("true");
	}
}
function checkSelect(){
	var checks = $("input[type='checkbox']");
	var isSelect = false;
	$.each(checks,function(i,val){
		if($(val).attr("checked") == "checked"){
			isSelect = true;
		}
	});
	return isSelect;
}
function isPassedOrReject(data){
	isPassed = data;
	$("#portal_enterprise_form").submit()[0];
}