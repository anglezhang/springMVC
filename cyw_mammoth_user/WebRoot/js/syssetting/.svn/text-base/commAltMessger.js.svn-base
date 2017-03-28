/**
*@描述 错误提示信息
*@param msg 提示信息
* eg: altEerrMsg('错误提示');
*/
var altEerrMsg = function(msg)
{
	var params = {type:'error',content:msg,title:'错误提示'
    ,id:'maipage_tips_erroemsg'};
	$.fn.alertDialog(params); 
    $.fn.alertDialogShow(params.id);
};

/**
*@描述 提示
*@param msg 提示消息
*@fun 点击确定后 执行函数 没有时确定后执行函数 不写即可 即 altInfMsg('测试消息');
*@cancel点击取消按钮执行函数
*eg: altInfMsg('次提示也！',function()
*	{
*		alert('你点击了确定');
*	});
*/
var altInfMsg = function(msg,fun,cancel)
{
	var param = {type:'tip',content:msg,title:'消息提示'
	 			,id:'maipage_tips_infmsg'
	 			,confirm:fun
	 			,cancel:cancel
			 }
	$.fn.alertDialog(param); 
	$.fn.alertDialogShow(param.id);
};

/**
*@描述 警告提示
*@param msg 提示消息
*@fun 点击确定后 执行函数 没有时确定后执行函数 不写即可 即 altWaringMsg('测试消息');
*@cancel点击取消按钮执行函数
*eg: altWaringMsg('次提示也！',function()
*	{
*		alert('你点击了确定');
*	});
*/
var altWaringMsg = function(msg,fun,cancel)
{
	var param = {type:'warning',content:msg,title:'警告提示'
	 			,id:'maipage_tips_wraingmsg'
	 			,confirm:fun
	 			,cancel:cancel
			 }
	$.fn.alertDialog(param); 
	$.fn.alertDialogShow(param.id);
};