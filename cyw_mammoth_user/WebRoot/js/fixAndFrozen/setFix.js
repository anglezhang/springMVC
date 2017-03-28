define(function(require, exports, module)
{
	var t = require("../frame/Tools");
	var ajaxM = require("../frame/AjaxManager");
	var basePath = $("#path").val(); 

	/**
	*描述 ：设置维修原因
	*/
	var setFixReason = function()
	{
		$("#setfix_fix_fixreasonsel").unbind("change");
		$("#setfix_fix_fixreasonsel").bind("change",function(event)
		{
			var textArea = $("#setfix_fix_fixreasontxt")
			var reason = $(this).val();
			var oldVal = textArea.val();
			textArea.val(oldVal + reason);
		});
	};

	/**
	*@描述 关闭窗口
	*/
	var closeWindow = function()
	{
		var windowObj = $("#fix_rooms_roomsid").parent().prev().find(".closeDiv");
		windowObj.trigger('click');
	};

	/**
	*@描述 确定
	*/
	var okBtnClick = function()
	{
		$("#setfix_fix_okbtn").unbind("click");
		$("#setfix_fix_okbtn").bind("click",function(event)
		{
			var roomsIds = "";
			var typeObj = $("[data-type]");
			var type = typeObj.attr("data-type");
			var error = typeObj.html();
			$("[data-roomid]").each(function(index, el) 
			{
				roomsIds += $(el).attr("data-roomid") + ",";
			});
			roomsIds = roomsIds.substring(0,roomsIds.length-1);
			var reanson = $("#setfix_fix_fixreasontxt").val();
			if(t.isEmpty(reanson))
			{
				var params = {type:'error',content:'请输入维修冻结原因。',title:'警告提示'
		                ,id:'fixreason_ortips_message'};
					$.fn.alertDialog(params); 
				    $.fn.alertDialogShow(params.id);
				    return;
			}
			//长度超过255 判断 并且提醒
			var startDate = $("#setfix_start").val()
			var endDate = $("#setfix_end").val();
			var sendData = {'roomIds':roomsIds,'strat':startDate
							,'end':endDate,'reanson':reanson
							,'type':type};
			var url = basePath + "/roomFixFrozen/setFixRoomNum.do";
			ajaxM.ajaxAction($,url,function(data)
			{
				t.debug("data=" + data + " type=" + typeof(data)); 
				if(data)
				{
					closeWindow();
					$("#rooms_btn_refresh").trigger('click');
				}else
				{
					var params = {type:'error',content:'房间状态可能被修改，请刷新页面。',title:'警告提示'
		                ,id:'fix_errortips_message'};
					$.fn.alertDialog(params); 
				    $.fn.alertDialogShow(params.id);
				}
			},sendData);
		});
	};

	/**
	*@描述 访问后台加载维修冻结原因
	*@param type 设置类型 2维修 3冻结
	*/
	var getFixReason = function(type)
	{
		var url = basePath + "/roomFixFrozen/getFixReason/" + type;
		ajaxM.ajaxAction($,url,function(data)
		{
			var selectObj = $("#setfix_fix_fixreasonsel");
			selectObj.find("option[value!='']").remove();
			for(var i=0;i<data.length;i++)
			{
				var optionDate = data[i];
				var optionHtml = "<option value=\"" + optionDate.codeNamec +"\">" + optionDate.codeNamec + "</option>";
				selectObj.append(optionHtml);
			}
		});
	};

	/**
	*@ 描述 切换维修冻结状态
	*/
	var switchSetType = function()
	{
		$("[name='setfix_type']").unbind("click");
		$("[name='setfix_type']").bind("click",function(event)
		{
			var clickType = $(this).val();
			var reasonObj = $("#setfix_reason");
			var titleObj = $("#page_titlerooms_set_fixfrozen");
			var titleHtml = titleObj.html();
			var typeUl = $("#fix_rooms_roomsid");
			$("#setfix_reason").empty();
			typeUl.attr("data-type",clickType);
			//修改title
			if(clickType==='2')
			{
				reasonObj.html("维修原因");
				titleObj.html(t.ReplaceAll(titleHtml,"冻结","维修"));
			}else
			{
				reasonObj.html("冻结原因");
				titleObj.html(t.ReplaceAll(titleHtml,"维修","冻结"));
			}
			getFixReason(clickType);
		});
	};
	
	/**
	*@描述 取消
	*/
	var cancleBtnClick = function()
	{
		$("#setfix_fix_cancelbtn").unbind('click');
		$("#setfix_fix_cancelbtn").bind("click",function(event)
		{
			closeWindow();
		});
	};

	$(function()
	{
		setFixReason();//设置原因
		cancleBtnClick();//点击取消
		okBtnClick();//点击确定
		switchSetType();//维修和冻结切换
	});
});