$(function(){
	$(".closeAlertBtn").bind('click',function(){
		closeChangePwdAlert();
	});
});
function closeChangePwdAlert(){
	$(".closeDiv").trigger("click");
}
function changePwd(){
	var rst=true;
	var $oldPwd=$.trim($(".updatePwd input[name='oldPwd']").val());
	var $newPwd=$.trim($(".updatePwd input[name='newPwd']").val());
	var $reNewPwd=$.trim($(".updatePwd input[name='reNewPwd']").val());
	if($.trim($oldPwd.length)==0){
		altWaringMsg("原密码不能为空",function(){return false;});
		rst=false;
		return false;
	}
	if($newPwd!=$reNewPwd){
		altWaringMsg("两次输入的密码不一致",function(){return false;});
		rst=false;
		return false;
	}
	if($newPwd.length==0 || $newPwd.length<6 || $newPwd.length>16){
		altWaringMsg("密码应该由6-10位数字、字母或符号组成",function(){return false;});
		return false;
	}
	//console.log(Utilities.RegValidate.isChinaZH(value));
	if(Utilities.RegValidate.isChinaZH($newPwd.length)){
		altWaringMsg("密码应该由6-10位数字、字母或符号组成",function(){return false;});
		return false;
	}
	
	if(rst){
		$.ajax({
			url:'/operator/changePwd.do',
			type:'post',
			data:{'oldPwd':$oldPwd,'newPwd':$newPwd},
			dataType:'json',
			success:function(data){
				var result=data;
				if(result.success){
					$('.updatePwdBox').hide();
					$('.updatePwdOkBox').show();
					$('.updatePwdOk .buttonLikeA').click(function(){
						closeChangePwdAlert();
						window.location.href="/logout";
					});
				}else{
					altWaringMsg("修改失败,"+result.msg,function(){return false;});
				}
			}
		});
	}
}
