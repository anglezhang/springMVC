define(function (require, exports, module) 
{
	/**
	*@描述 房价列表前端代码
	*/
	var t = require("../frame/Tools");
	var template = require("template");

	/**
	*@房价列表点击
	*/
	var pricelistClick = function()
	{
		$("[room-data]").unbind("click");
		$("[room-data]").bind("click",function(event)
		{
			var dataStr = $(this).attr("room-data");
			var data = eval("(" + dataStr + ")");
			var tableHtml = template('guestinfo_pricelist_option',data);
			var table = $("#guestinfo_pricelist_table");
			table.empty();
			table.append(tableHtml);
			$(this).css("background" , "#e4f1d0");
			$("[room-data]").css("background","");
			$("#roomId_roomType_h5").html(data['roomId'] + " | " + data['roomTypeName']);
			$("#doPriceTotal").html(t.ChangeTwoDecimalNumber(data['doPrice']));
		});
	};

	//初始完成后执行
	$(function()
	{
		setTimeout(function()
		{
			pricelistClick();//房间列表点击
			$("#guestinfo_pricelist_quit").unbind('click');
			$("#guestinfo_pricelist_quit").bind('click',function(event)
			{
				$("#closeWindowsguestdetail_roompricelist").trigger('click');
			});
		},100);
	});
});