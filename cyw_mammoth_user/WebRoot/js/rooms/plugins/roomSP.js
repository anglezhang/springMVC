define(function(require, exports, module)
{
	/**
	*@ 描述 为防止room 扩展room 
	*/	
	var time = 10*1000;//自动刷新时间
	var t = require("../../frame/Tools");
	var basePath = $("#path").val();
	/**
	*@描述 刷新页面
	*/
	var refresh = function()
	{
		setInterval(function()
		{
			$("#rooms_btn_refresh").trigger('click');
		},time);
	};

	/**
	*@描述 切换界面
	*/
	var switchTab = function()
	{
		$("#userCheckIn,#userCurrentState").unbind('click');
		$("#userCheckIn,#userCurrentState").bind('click', function(event)
		{
			var formObj = $("#rooms_searchvo");
			var action = formObj.prop("action");
			var divID = $(this).prop("id");
			$("#rooms_btn_clear").trigger('click');//切换时 清空条件先
			if(divID==='userCheckIn')
			{
				window.location.href = basePath + "/rooms.do";
				return;
			}else
			{
				action += "?type=1";
			}
			formObj.prop("action",action);
			formObj.submit();
		});
	};

	$(function()
	{
		switchTab();
	});
});