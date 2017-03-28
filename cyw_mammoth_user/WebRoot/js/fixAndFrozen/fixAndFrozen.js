define(function(require, exports, module)
{
	var t = require("../frame/Tools");
	var rooms = require("../rooms/plugins/rooms");
	var roomUtil = require("../rooms/plugins/RoomsUtils"); 
	var basePath = $("#path").val(); 
	var ajaxM = require("../frame/AjaxManager");
	var DateUtil = require('../frame/DateTool');

	/**
	*开始日期设置
	*/
	var beginDateSet = function()
	{
		//判断
		$("#roomssearchvo_startdate").unbind('click');
		$("#roomssearchvo_startdate").bind('click',function(event)
		{
			//分别取 开始时间 截至时间 并且 初始为日期类型
			var beginDate = $("#roomssearchvo_startdate_minDate").val();
			var nowDate = new Date();
			beginDate = t.ReplaceAll(beginDate,"-","") + "0000";
			var bDate = new DateUtil(nowDate).setDate(beginDate);
			endDatelimt = new DateUtil(bDate).format('yyyy-MM-dd');
			//调用日期控件
			WdatePicker({minDate:endDatelimt});
		});	
	};

	/**
	*@描述 设置维修冻结
	*@param type 类型 fix 维修; frozen 冻结
	*/
	var setFixOrFrozen = function(type,title)
	{
		var roomIds = rooms.GetSelRoomIds();
		//非空验证
		if(t.isEmpty(roomIds))
		{
			var params = {type:'error',content:'请先选中房间。',title:'警告提示'
				,id:'fixfrozen_selected_erroemsg'};
			$.fn.alertDialog(params);
			$.fn.alertDialogShow(params.id);
		}else
		{
			var beginDate = $("#roomssearchvo_startdate_old_minDate").val();
			var endDate = $("#roomssearchvo_enddate_olddate").val(); 
			var url = basePath + "/roomFixFrozen/setFix/" 
					+ roomIds + "/" + beginDate + "/" 
					+ endDate + "/" + type;
			rooms.SetRoomFixFrozen(url,title);
		}
	};
	/**
	*@描述 维修设置
	*/
	var setfixBtnClick = function()
	{
		$("#fixfrozen_btn_setfix").unbind("click");
		$("#fixfrozen_btn_setfix").bind('click',function(event)
		{
			setFixOrFrozen("2","设置维修");
		});
	};

	/**
	*描述  冻结设置
	*/
	var setFronzenBtnClick = function()
	{
		$("#fixfrozen_btn_setfrozen").unbind('click');
		$("#fixfrozen_btn_setfrozen").bind('click',function(event)
		{
			setFixOrFrozen("3","设置冻结");
		});
	};

	/**
	*@描述  切换状态 刷新
	*/
	var switchStat = function()
	{
		$("[name='roomStatu']").unbind('click');
		$("[name='roomStatu']").bind("click",function(event)
		{
			$("#rooms_btn_refresh").trigger('click');
		});
	};
	

	/**
	*@描述 点击维修冻结
	*/
	var fixFronzeBtnClick = function()
	{
		$("#fixfrozen_btn_fixfrozeninf").unbind("click");
		$("#fixfrozen_btn_fixfrozeninf").bind("click",function(event)
		{	
			var startDate = $("#roomssearchvo_startdate").val();
			var endDate = $("#roomssearchvo_enddate").val();
			roomUtil.ViewFixFrozenInfOpen('null','null',0,startDate,endDate);
		});
	};

	//加载完成执行
	$(function()
	{
		roomUtil.SetRoomMunu(6);//设置二级菜单
		beginDateSet();
		setfixBtnClick();//绑定维修冻结
		setFronzenBtnClick();//绑定冻结
		switchStat();//刷新
		fixFronzeBtnClick();//设置维修冻结
	});
});