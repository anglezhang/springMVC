define(function(require, exports, module)
{
	var t = require("../../frame/Tools");
	//房屋类型 该界面直接调用它的方法
	var roomType = require("./roomsType");

	$(function()
	{
		setTimeout(function()
		{
			roomType.btnCancle('rooms_selectedroom_cancel');
		},200);
		
	});
});