/**
*自定义弹出框 
*@author zhangzhenxing
*Date 2015-10-30
* eg1:
* var param = {type:'tip',content:'测试',title:'测试title'
*                ,id:'testMsgDIv'
*                ,confirm:function()
*                {
*                    alert("测试");
*				 }
*			}
*    var altDialog = $.fn.alertDialog(param); 
*    $.fn.alertDialogShow(param.id);
*  eg2:如果弹出界面是 action 则需要 show 函数
*    var param = {type:'page',title:'测试title'
*		,id:'testMsgDIv'//divID
*		,content:'${pageContext.request.contextPath}/noguest/noguestIndex.do'
*		,isUpdate:true//true 每次更新 false 不更新
*		,show:function()//显示
*		{
*          $.fn.alertDialogShow(param.id);
*		}
*      params.titleClass page样式
*		var altDialog = $.fn.alertDialog(param); 
*/
(function($) {        
	$.fn.alertDialog = function(params) {   
		//succsess
		var succsessHtml = "<div class=\"alertDiv moveBar2 alertDiv3 successDiv\" style=\"display: none;z-index:999;\" id=\"" + params.id + "\">"
			+			   "	<div class=\"alertMain greyBg\" style=\"width:600px;margin-top:310px;\">"
			+			   "	<h4 class=\"moveDivAlert2\">" + params.title 
			+ 			   "		<a id=\"closeWindows" + params.id + "\" href=\"javascript:;\" class=\"closeDiv2 closeAlertTips\"></a></h4>"
			+ 			   "	<div class=\"borderSolid padding-0 whiteBackground\">"
			+			   "		<table class=\"margin-bottom-50 margin-top-50 widthB90\">"
			+			   "		<tbody><tr>"
			+			   "			<td width=\"100\" align=\"right\"><img src=\"../img/tipsImg_02.png\"></td>"
			+			   "			<td><span class=\"font-18\" id=\"content_" + params.id + "\">"+ params.content + "</span></td>"
			+			   "			</tr>"
			+			   "		</tbody></table>"
			+ 			   "			<div class=\"alertTipsBottom\">     "
			+ 			   "				<a id=\"confirm_" + params.id + "\" class=\"button_06 floatR margin-right-15\" href=\"javascript:;\">确定</a>"
			+ 			   "			</div>"
			+			   "	</div>"
			+   		   " </div>"
			+   		   "</div>";
		//tip   
	    var tipHtml = "<div class=\"alertDiv moveBar2 alertDiv3 ConfirmDiv\" style=\"display: none;z-index:999;\" id=\"" + params.id + "\">"
			+ "		<div class=\"alertMain greyBg\" style=\"width:600px;margin-top:310px;\">"
			+ "		<h4 class=\"moveDivAlert2\">" + params.title + "<a id=\"closeWindows"+ params.id + "\" href=\"javascript:;\" class=\"closeDiv2 closeAlertTips\"></a></h4>"
			+ "		<div class=\"borderSolid padding-0 whiteBackground\">"
			+ "			<table class=\"margin-bottom-50 margin-top-50 widthB90\">"
			+ " 			<tbody><tr>"
			+ "				<td width=\"100\" align=\"right\"><img src=\"../img/tipsImg_01.png\"></td>"
			+ "				<td><span class=\"font-18\" id=\"content_" + params.id + "\">" + params.content + "</span></td>"
			+ "				</tr>"
			+ "			</tbody></table>"
			+ "			<div class=\"alertTipsBottom\">     ";
			//判读是否有取消
			if(!_isEmpty(params.cancel))
			{
				tipHtml += "				<a id=\"closeBtn_" + params.id + "\" class=\"button_06 floatR margin-right-15\" href=\"javascript:;\">取消</a>"
			}
			tipHtml = tipHtml
			+ "				<a id=\"confirm_" + params.id + "\" class=\"button_06 floatR margin-right-15\" href=\"javascript:;\">确定</a>"
			+ "			</div>"
			+ "		</div>"
			+ "	</div>"
			+ "</div>";
		//warning
		var waringHtml = "<div class=\"alertDiv moveBar2 alertDiv3 ConfirmDiv\" style=\"display: none;z-index:999;\" id=\"" + params.id + "\">"
			+ "		<div class=\"alertMain greyBg\" style=\"width:600px;margin-top:310px;\">"
			+ "		<h4 class=\"moveDivAlert2\">" + params.title + "<a id=\"closeWindows"+ params.id + "\" href=\"javascript:;\" class=\"closeDiv2 closeAlertTips\"></a></h4>"
			+ "		<div class=\"borderSolid padding-0 whiteBackground\">"
			+ "			<table class=\"margin-bottom-50 margin-top-50 widthB90\">"
			+ " 			<tbody><tr>"
			+ "				<td width=\"100\" align=\"right\"><img src=\"../img/tipsImg_03.png\"></td>"
			+ "				<td><span class=\"font-18\" id=\"content_" + params.id + "\">" + params.content + "</span></td>"
			+ "				</tr>"
			+ "			</tbody></table>"
			+ "			<div class=\"alertTipsBottom\">     ";
			//判读是否有取消
			if(!_isEmpty(params.cancel))
			{
				waringHtml += "				<a id=\"closeBtn_" + params.id + "\" class=\"button_06 floatR margin-right-15\" href=\"javascript:;\">取消</a>"
			}
			waringHtml = waringHtml
			+ "				<a id=\"confirm_" + params.id + "\" class=\"button_06 floatR margin-right-15\" href=\"javascript:;\">确定</a>"
			+ "			</div>"
			+ "		</div>"
			+ "	</div>"
			+ "</div>";
		//error
		var errorHtml = "<div class=\"alertDiv moveBar2 alertDiv3 ConfirmDiv\" tiptype='page' style=\"display: none;z-index:999;\" id=\"" + params.id + "\">"
			+ "		<div class=\"alertMain greyBg\" style=\"width:600px;margin-top:310px;\">"
			+ "		<h4 class=\"moveDivAlert2\">" + params.title + "<a id=\"closeWindows"+ params.id + "\" href=\"javascript:;\" class=\"closeDiv2 closeAlertTips\"></a></h4>"
			+ "		<div class=\"borderSolid padding-0 whiteBackground\">"
			+ "			<table class=\"margin-bottom-50 margin-top-50 widthB90\">"
			+ " 			<tbody><tr>"
			+ "				<td width=\"100\" align=\"right\"><img src=\"../img/tipsImg_03.png\"></td>"
			+ "				<td><span class=\"font-18\"  id=\"content_" + params.id + "\">"  + params.content + "</span></td>"
			+ "				</tr>"
			+ "			</tbody></table>"
			+ 			   "			<div class=\"alertTipsBottom\">     "
			+ 			   "				<a id=\"confirm_" + params.id + "\" class=\"button_06 floatR margin-right-15\" href=\"javascript:;\">确定</a>"
			+ 			   "			</div>"
			+ "		</div>"
			+ "	</div>"
			+ "</div>";
		switch(params.type)
		{
			case "tip":
				_setDialogSetting(params,tipHtml);
				break;
			case "succsess":
				_setDialogSetting(params,succsessHtml);
				break;
			case "error":
				_setDialogSetting(params,errorHtml);
				break;
			case "warning":
				_setDialogSetting(params,waringHtml);
				break;
		}
		if(params.type==="page")
		{
			var altDialog = $("#" + params.id);
            if(!_isEmpty(params.isUpdate) && params.isUpdate)
			{
				altDialog.remove();
				altDialog=null;
			}	
			if(_isEmpty(params.width))
			{
				params.width = 500;
			}
			//判断是否存在
			if(altDialog==null || _isEmpty(altDialog.html()))
			{
				$.ajax({
		        url:params.content
		        ,async:true
		        ,data:params.data
		        ,type: 'post'
		        ,dataType:'html'
		        ,success:function(data)
			        {
			            var page_Html = data.substring(data.indexOf("<body>"),data.indexOf("</body"));
			            var tRegExp=new RegExp("<body>","g");
			            page_Html = page_Html.replace(tRegExp,"");
						var moveBarObj = $("#moveBar");
						if(!_isEmpty(moveBarObj.html()))
							baseIndex++;
			            //页面类型
						var pageHtml = "<div id=\"" + params.id ;
							
						if(_isEmpty(params.titleClass))
						{
							pageHtml += "\" class=\"alertDiv checkInDiv moveBar\" style=\"display:none;z-index:" + baseIndex + ";\">";
						}else
						{
							pageHtml += "\" class=\"" + params.titleClass + "\" style=\"display:none;z-index:" + baseIndex + ";\">";
						}
						if(_isEmpty(params.top))
						{
							pageHtml +=           "	<div class=\"alertMain greyBg rzLogin\" style=\"margin-top:160px;width:" + params.width + "px;\">";  
						}else
						{
							pageHtml +=           "	<div class=\"alertMain greyBg rzLogin\" style=\"margin-top:" + params.top + "px;width:" + params.width + "px;\">"  
						}
							
							pageHtml +=			"		<h4 class=\"moveDivAlert\" > <span id=\"page_title" + params.id + "\">"+ params.title + "</span><a id=\"closeWindows" + params.id + "\" href=\"javascript:;\" class=\"closeDiv\"></a></h4>"
							+			"		<div class=\"borderSolid\">" + page_Html + "</div>"
							+			"	</div>"
							+			"</div>";
						_setDialogSetting(params,pageHtml);
						if(params.show)
						{
							params.show();
						}
			        }
		    	});
			}else
			{
				if(params.show)
					params.show();
			}
			
		}
	};  
	$.fn.alertDialogShow = function(id)
	{
		var altDialog = $("#"+id);
		var bg = $(".alertDivBg");
		altDialog.fadeIn();
		bg.fadeIn();
	};    
})(jQuery); 
/**
*非空判断
*/
function _isEmpty (obj)
{
try
    {
        if(obj===null)return true;
        if(obj==="")return true;
        if(obj==="undefined")return true;
        if(obj===undefined)return true;
        if(obj==="null")return true;
        if(obj.toString().trim()===null)return true;
        if(obj.toString().trim().length===0)return true;
        return false;
    }catch(e)
    {
        return false;
    }
}

/**
*描述 浮层 基础值
*/
var baseIndex = 30;
function _setDialogSetting (params,html) {
	var body = $("body");
	var altDialog = $("#" + params.id);
	if(_isEmpty(altDialog.html()))
	{
		body.append(html);
	}else
	{
		$("#content_" + params.id).html(params.content);
	}
	
	//遮罩层添加
	var alertDivBg = "<div author='zhangzhenxing' class=\"alertDivBg\" id=\"alertDivBg_" + params.id + "\"></div>";
	var alertDivBg2 = "<div class=\"alertDivBg2\"></div>";
	var alertDivBg3 = "<div class=\"alertDivBg3\"></div>";

	var bg = $(".alertDivBg");
	if(bg.html()===undefined)
	{
		body.append(alertDivBg);
		/*body.appned(alertDivBg2);
		body.appned(alertDivBg3);*/
		bg = $(".alertDivBg");
		bg.css("height",$(document).height());
		bg.css("width",$(document).width());
	}
	/*var bg2 = $(".alertDivBg2");
	var bg3 = $(".alertDivBg3");*/
	altDialog = $("#"+params.id);
	//关闭
	$("#closeWindows"+ params.id).unbind('click');
	$("#closeWindows"+ params.id).bind('click',  function(event) {
		var array = 0;
		if(params==='page')
		{
			$("[tiptype='page']").each(function(index, el) 
			{
				var style = $(el).css("display");
				if("block"===style)
				{
					array++;
				}
			});
			if(array==1)
			{
				$("#alertDivBg_" + params.id).fadeOut();
			}
		}else
		{
			$(".alertDivBg").fadeOut();
		}
		
		$("#alertDivBg_" + params.id).fadeOut();
		altDialog.fadeOut();
		if(!_isEmpty(params.cancel))
		{
			params.cancel();
		}
	});  
	//点击关闭按钮
	$("#closeBtn_" + params.id).unbind('click');
	$("#closeBtn_" + params.id).bind('click',function(e)
	{

		$("#closeWindows"+ params.id).trigger('click');
	});
	//执行确定函数
	if(params.type==="tip" || params.type==="succsess" || params.type==="warning" || params.type==="error")
	{
		$("#confirm_" + params.id).unbind('click');
		$("#confirm_" + params.id).bind('click',function(event)
		{
			altDialog.fadeOut();
			bg.fadeOut();
			if(params.confirm)
			{
				params.confirm();
			}
		});
	}
}  