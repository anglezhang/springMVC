define(function(require, exports, module)
{
	var t = require("../frame/Tools");
	var rooms = require("../rooms/plugins/rooms");
	var roomUtil = require("../rooms/plugins/RoomsUtils"); 
	var basePath = $("#path").val(); 
	var ajaxM = require("../frame/AjaxManager");

	/**
	* @描述  移除 or 添加 选中状态
	*@param obj操作对象
	*@param isAdd 是否添加 
	*/
	var removeSelStat = function(obj,isAdd)
	{
		if(isAdd)
		{
			obj.addClass("stateLBPoint");
			obj.children().css("-webkit-filter","brightness(1000%)");//css滤镜，谷歌下图片变白色
			obj.css("color","#fff");
			obj.attr("type-check","true");
		}else
		{
			obj.removeClass('stateLBPoint');
			obj.children().css("-webkit-filter","brightness(100%)");
			obj.css("color","#000");
			obj.attr("type-check","false");
		}
	};

	/**
	*@描述 清洁状态选中和未选中
	*/
	var setClearStatu = function()
	{
		$(".stateLB").unbind("click");
		$(".stateLB").bind("click",function(event)
		{
			var statu = $(this).attr("type-check");
			switch(statu)
			{
				case "false":
					$(".stateLBPoint").each(function(index, el) {
						removeSelStat($(el),false);
					});
					removeSelStat($(this),true);
					break;
				case "true":
					removeSelStat($(this),false);
					break;
			} 
			var value = '';
			$("[type-check]").each(function(index, el) 
			{
				var ischeck = $(el).attr("type-check");
				var objValue = $(el).attr("data-value");
				if(ischeck==='true')
				{
					value = objValue;
				}
			});
			$("#roomstat_clearstatu").val(value);
			//$("#rooms_searchvo").submit();
		});
	};

	/**
	*@描述：置为不洁
	*/
	var unclearBtnClick = function()
	{
		$("#roomstat_btn_unclear").unbind("click");
		$("#roomstat_btn_unclear").bind("click",function(event)
		{
			var roomIds = rooms.GetSelRoomIds();
			if(t.isEmpty(roomIds))
			{
				var params = {type:'error',content:'请先选中房间。',title:'警告提示'
                ,id:'roomstat_tips_erroemsg'};
				$.fn.alertDialog(params); 
			    $.fn.alertDialogShow(params.id);
				return;
			}
			var url = basePath + "/rooms/setUnclear/"+roomIds;
			var tempId = "clear_unclean";
			rooms.setClearStat(url,roomIds,tempId);
		});
	};

	/**
	*@描述 置为清洁未查
	*/
	var clearuncheckBtnClick = function()
	{
		$("#roomstat_btn_clearuncheck").unbind('click');
		$("#roomstat_btn_clearuncheck").bind("click",function(event)
		{
			var roomIds = rooms.GetSelRoomIds();
			var roomIds = rooms.GetSelRoomIds();
			if(t.isEmpty(roomIds))
			{
				var params = {type:'error',content:'请先选中房间。',title:'警告提示'
                ,id:'roomstat_tips_erroemsg'};
				$.fn.alertDialog(params); 
			    $.fn.alertDialogShow(params.id);
				return;
			}
			var url = basePath + "/rooms/setClearUncheck/"+roomIds;
			var tempId = "clear_uncheck";
			rooms.setClearStat(url,roomIds,tempId);
		});
	};

	/**
	*@描述 置为清洁已查
	*/
	var clearcheckBtnClick = function()
	{	
		$("#roomstat_btn_clearcheck").unbind('click');
		$("#roomstat_btn_clearcheck").bind("click",function(event)
		{
			var roomIds = rooms.GetSelRoomIds();
			var roomIds = rooms.GetSelRoomIds();
			if(t.isEmpty(roomIds))
			{
				var params = {type:'error',content:'请选中房间',title:'警告提示'
                ,id:'roomstat_tips_erroemsg'};
				$.fn.alertDialog(params); 
			    $.fn.alertDialogShow(params.id);
				return;
			}
			var url = basePath + "/rooms/setClearCheck/"+roomIds;
			var tempId = null;
			rooms.setClearStat(url,roomIds,tempId);
		});
		
	};

	/**
	*
	*@描述 点击查询按钮
	*/
	var refushBtnClick = function()
	{
		$("#rooms_btn_refresh").unbind('click');
		$("#rooms_btn_refresh").bind('click', function(event) 
		{
			$("#rooms_searchvo").submit();
		});
	};

	/**
	*@描述 设置上次查询值
	*/
	var setDefultValue = function()
	{
		var setVal = $("#roomstat_clearstatu").val();
		var clearObj = $("[data-value='"+ setVal +"']");
		clearObj.addClass("stateLBPoint");
		clearObj.children().css("-webkit-filter","brightness(1000%)");//css滤镜，谷歌下图片变白色
		clearObj.css("color","#fff");
		clearObj.attr("type-check","true");
		var roomId = $("#roomstat_roomid").val();
		if(!t.isEmpty(roomId))
		{
			var obj = $("[room-id='" + roomId + "']");
			obj.trigger('click');
		}
	};

	/**
	*@描述 快速查询
	*/
	var fastSearchRoom = function()
	{
		$("#roomstat_roomid").unbind("keyup");
		$("#roomstat_roomid").bind("keyup",function(event)
		{
			var roomId = $("#roomstat_roomid").val();
			if(!t.isEmpty(roomId))
			{
				if(event.keyCode==13)
				{
					var sendData = new Object();
					$("[name]").each(function(index, el) {
						var name = $(el).prop("name");
						var value = $(el).val();
						sendData[name]=value;
					});
					//取在住房空房
					var roomTypeInput = $("[name='roomStatu']:checked");
					var roomType = null;
					if(roomTypeInput.length==2)
					{
						roomType = "V,O";
					}
					if(t.isEmpty(roomType))
						roomType='';
					sendData.roomStatu = roomType;
					sendData['roomId'] = roomId;
					var form = $("#roomstat_fastsearch");
					ajaxM.submitForm($,form,sendData);
				}
			}
		});
	};	

	/**
	*@描述 切换状态、刷新界面
	*/
	var switchStat = function()
	{
		$("[name='roomStatu']").unbind("click");
		$("[name='roomStatu']").bind("click",function(event)
		{
			$("#rooms_btn_refresh").trigger('click');
		});
	};

	$(function()
	{
		roomUtil.SetRoomMunu(5);//设置二级菜单
		//rooms.SetInit();//初始化数据
		setClearStatu();//清洁状态选中
		unclearBtnClick();//不洁
		clearuncheckBtnClick();//清洁未查
		clearcheckBtnClick();//清洁已查
		refushBtnClick();//刷新
		setDefultValue();//设置默认值
		fastSearchRoom();//回车键监听
		//switchStat();//切换房态刷新
	});
});