define(function(require, exports, module)
{
	var t = require("../../frame/Tools");
	var basePath = $("#path").val();
	var template = require("template");
	var ajaxM = require("../../frame/AjaxManager");
	var DateUtil = require('../../frame/DateTool');
	var roomUtil = require("./RoomsUtils");
	var roomStatusUtil = require("./roomStatuUtil");

	/**
	*@描述 点击选中框放号 移除选中
	*/
	var removeBoxRoom = function()
	{
		$(".roomNum li").unbind('dblclick');
		$(".roomNum li").bind('dblclick',function(event)
		{
			var roomId = $(this).html();
			var colorBloclObj = $("[room-id='" + roomId + "']");
			colorBloclObj.trigger('click');
		});
	};

	/**
	*@描述 取选中的房间ID 多个ID 用‘,’隔开 
	*/
	var getRoomsIDs = function()
	{
		var roomIds = "";
		$("[isshow='true']").each(function(index, el) {
			var roomId = $(el).parent().attr('room-id');
			roomIds += roomId + ",";
		});
		if(roomIds.length > 1)
		{
			roomIds = roomIds.substring(0,roomIds.length-1);
		}
		return roomIds;
	};

	/**
	*@描述 修改清洁状态
	*@param roomId 房间ID
	*@param temId 模版id
	*/
	var changeStat = function(roomId,temId)
	{
		$("[room-id='" + roomId + "']").each(function(index, el) 
		{
			var statusObj = $(el).find("div.statusBlock").find("div.statusImg");
			statusObj.empty();
			if(!t.isEmpty(temId))
			{
				var appHtml = template(temId,null);
				statusObj.append(appHtml);
			}
			switch(temId)
			{
				case 'clear_uncheck'://清洁未查
					$(el).attr("data-duty","C");
					break;
				case 'clear_unclean'://脏房
					$(el).attr("data-duty","D");
					break;
			}
		});
		
		
	};

	/**
	*@描述 设置清洁状态 修改为不洁 清洁未查 清洁已查
	*/
	var setClearStat = function(url,roomsIds,temID)
	{
		ajaxM.ajaxAction($,url,function(data)
		{
			if(data)
			{
				if(roomsIds.indexOf(",")!=-1)
				{
					var roomIdArr = roomsIds.split(",");
					$.each(roomIdArr,function(index)
					{
						var roomId = roomIdArr[index];
						changeStat(roomId,temID);
					});
				}
					else
						changeStat(roomsIds,temID);
				//清除选中状态
				clearSelectedStat();
			}
		});
	};

	/**
	*@描述 设置维修冻结
	*@param url 路径
	*@param title 标题
	*/
	var setFixFrozen = function(url,title)
	{
		var params = {type:'page',title:title
					,id:'rooms_set_fixfrozen',content:url
					,isUpdate:true
					,width:365
					,show:function()
					{
						$.fn.alertDialogShow(params.id);
					}};
		$.fn.alertDialog(params); 
	};

	/**
	* @描述 设置维修冻结 提供其他模块调用接口
	*@param url 路径
	*@param title 标题
	*/
	module.exports.SetRoomFixFrozen = function(url,title)
	{
		setFixFrozen(url,title);
	};

	/**
	*@描述 右键事件
	*/
	var rightClick = function(event)
	{
		var ul = $(".clickRight");
		//可视区域高和宽
		var windowHeight = $(window).height();
		var windowWidth = $(window).width();
		//控件的高度和宽度
		var heigth = ul.height();
		var width = ul.width();
		//这里可得到鼠标X坐标
		var pointX = event.pageX;
		//这里可以得到鼠标Y坐标
		var pointY = event.pageY;
		var baseWith = 250;
		var baseHeight = 130;
		var pointX1, pointY1;
		if (pointY > windowHeight) {
			pointY1 = pointY - width - baseWith;
		} else {
			pointY1 = pointY;
		}
		if (pointX > windowWidth) {
			pointX1 = pointX - heigth - baseHeight;
		} else {
			pointX1 = pointX;
		}
		//判断是否到底部 x的坐标 + 菜单高度 大于 界面高度
		var menuHeight = ul.height();
		var mainHeight = $(".mainInfo").height();
		if((pointX + menuHeight)>mainHeight)
		{
			pointX1 = pointX - 30;
		}

		pointX1 -= 10;
		pointY1 -= 10;
		ul.css("left", pointX1 + "px").css("top", pointY1 + "px").show(300);
		//点击选择
		$("[click-type]").bind("click",function(e)
		{
			e.stopPropagation();//防止冒泡
			var clickType = $(this).attr('click-type');
			var roomIds = getRoomsIDs();
			var url = "";
			var temID = "";
			switch(clickType)
			{
				case 'unclean':
					url = basePath + "/rooms/setUnclear/" + roomIds;
					temID = 'clear_unclean';
					setClearStat(url,roomIds,temID);
					break;
				case 'clean-nocheck':
					url = basePath + "/rooms/setClearUncheck/" + roomIds;
					temID = 'clear_uncheck';
					setClearStat(url,roomIds,temID);
					break;
				case 'clean-check':
					url = basePath + "/rooms/setClearCheck/" + roomIds;
					temID = null;
					setClearStat(url,roomIds,temID);
					break;
				case 'check-in':
					$("#rooms_btn_checkin").trigger('click');
					break;
				case 'guest-info':
					$("#rooms_btn_guestinf").trigger('click');
					break;
				case 'setfrozeninf':
					var beginDate = $("#roomssearchvo_startdate").val();
					var endDate = $("#roomssearchvo_enddate").val();
					url = basePath + "/roomFixFrozen/setFix/" + roomIds + "/" + beginDate + "/" + endDate + "/2";
					var title = "设为维修";
					setFixFrozen(url,title);
					break;
				case 'setfix':
					var beginDate = $("#roomssearchvo_startdate").val();
					var endDate = $("#roomssearchvo_enddate").val();
					url = basePath + "/roomFixFrozen/setFix/" + roomIds + "/" + beginDate + "/" + endDate + "/2";
					var title = "设为维修";
					setFixFrozen(url,title);
					break;
				case 'setfrozen':
					var beginDate = $("#roomssearchvo_startdate").val();
					var endDate = $("#roomssearchvo_enddate").val();
					url = basePath + "/roomFixFrozen/setFix/" + roomIds + "/" + beginDate + "/" + endDate + "/3";
					var title = "设为维修";
					setFixFrozen(url,title);
					break;
			}
			$(".clickRight").remove();
		});
		$(".clickRight").bind('mouseleave', function(event) {
			$(this).fadeOut();
			$(this).remove();
		});
	};
	/**
	*@描述：多混右键菜单
	*@param cliclRoom
	*/
	var multipleMixedRight = function(cliclRoom,event)
	{
		var appHtml = template('multiple_mixed_right',null);
		cliclRoom.append(appHtml);
		rightClick(event);
	};
	/**
	*@描述 多空右键
	*@param cliclRoom当前右键对象
	*/
	var multipleEmptyRight = function(cliclRoom,event)
	{
		var appHtml = template('multiple_empty_right',null);
		cliclRoom.append(appHtml);
		rightClick(event);
	};

	/**
	*@描述：单住右键
	*@param cliclRoom当前右键对象
	*/
	var singleRight = function(cliclRoom,modelID,event)
	{
		var appHtml = template(modelID,null);
		cliclRoom.append(appHtml);
		rightClick(event);
	};

	/**
	*@描述 获取选中的类型
	*/
	var getSelectTypes = function()
	{
		var showdoms = $("[isshow='true']");
		var types = new Array();//类型
		var count = showdoms.length;
		for(var i=0;i<count;i++)
		{
			var selDiv = $(showdoms[i]).parent();
			//判断是否空不可用
			var usefullObj = selDiv.find("[isusefull-roomid]");
			var type = null;
			if(t.isEmpty(usefullObj.html()))
			{
				type = selDiv.attr("room-stat");
			}else
			{
				type = "VNOT";
			}
			if($.inArray(type,types)==-1)
			{
				types.push(type);
			}

		}
		return types;
	};
	/**
	*@描述 右键菜单功能
	*/
	var roomRightClick = function()
	{
		$("[room]").unbind('mousedown');
		$("[room]").bind('mousedown',function(event)
		{
			var cliclRoom = $('body');
			//根据选中状态是否显示 判断 多空 or 多混
			var showdoms = $("[isshow='true']");
			var count = showdoms.length;
			var roomShow = $(this).find("[isshow]").attr("isshow");
			if(event.button==2 && count>0 && roomShow==='true')
			{
				clearRightMenu();//清除其他右键菜单
				//多选
				if(count > 1)
				{
					var types = getSelectTypes();//类型
					//多空 只有一种类型 V
					if($.inArray("V",types)==0 && types.length==1)
					{
						multipleEmptyRight(cliclRoom,event);
					}
					else//多混
					{
						multipleMixedRight(cliclRoom,event);
					}
				}else if(count == 1)//单选
				{
					var selDiv = showdoms.parent();
					var type = selDiv.attr("room-stat");
					switch(type)
					{	
						case "V":
							singleRight(cliclRoom,'single_empty_right',event);
							break;
						case "O":
							singleRight(cliclRoom,'single_live_right',event);
							break;

					}
				}
				/*var rightMenu = $(".clickRight");
				rightMenu.css("margin",topOff + "px 0 0 " + leftOff + "px");*/
			}
		});
	};

	/**
	*@描述 ：鼠标移开 清除右键菜单
	*/
	var colorBlockMouse = function()
	{
		$("[room-isfloor='false']").unbind("mousemove");
		$("[room-isfloor='false']").bind("mousemove",function(event)
		{
			//找到父元素 根据roomid判断是否当前
			var roomId = $(this).attr("room-id");
			$(".clickRight").each(function(index, el) {
				var thisUl = $(el);
				var blockObj = thisUl.parent();
				var blockRoomId = blockObj.attr("room-id");
				if(roomId!==blockRoomId)
				{
					thisUl.remove();
				}
			});
		});
	};
	/**
	*@描述 选中效果
	*@param thisRoom当前 li对象
	*/
	var roomSelShow = function(thisRoom)
	{
		var selDiv = thisRoom.find(".statusBlock").next(".pointStatus");
		var selImg = selDiv.next(".pointStatusRight");
		var isShow = selDiv.attr("isshow");
		if(isShow==='true')
		{
			selDiv.hide();
			selImg.hide();
			isShow = 'false';
		}else
		{
			selDiv.show();
			selImg.show();
			isShow = 'true';
		}
		selDiv.attr("isshow",isShow);
		setSelRooms();
	};

	/**
	*@描述选定房间处理 1.修改选定房间的个数 2.将房号加入选中房间区域
	*/
	var setSelRooms = function()
	{
		var roomsIdS = getRoomsIDs();
		//判断 如果选中房间个数不为空 则将自动排房设置为可点击状态
		if(!t.isEmpty(roomsIdS))
		{
			var rowhouseBtn = $("#rooms_btn_rowhouse");
			rowhouseBtn.attr("able-stat",'true');
			rowhouseBtn.removeAttr("style");
		}else
		{
			var rowhouseBtn = $("#rooms_btn_rowhouse");
			rowhouseBtn.attr("able-stat",'false');
			rowhouseBtn.css("color","gray");
			rowhouseBtn.css("cursor","not-allowed");
		}
		var roomsArray = new Array();
		if(!t.isEmpty(roomsIdS))
			roomsArray = roomsIdS.split(",");
		var roomsUlObj = $("#rooms_roomid_div");
		roomsUlObj.empty();
		$.each(roomsArray,function(index)
		{
			var roomid = roomsArray[index];
			roomsUlObj.append("<li>" + roomid + "</li>");
		});
		$("#rooms_roomid_count").html("选定房间："+roomsArray.length);
		removeBoxRoom();
	};
	/**
	*@描述 选中效果和处理
	*       1.维修和冻结状态不能选择
	*       2.点击楼层 可以选择该楼层下全部
	*       3.选中后才有右键菜单
	*/
	var selectedRoomStat = function()
	{
		$("[room-isfloor='true']").unbind('click');
		$("[room-isfloor='true']").bind('click', function(event)
		{
			var thisFloorNo = $(this).attr("floor-no");
			
			$("[room-isfloor='false']").each(function(index, el) {
				var thisRoom = $(el);
				var roomFloorNo = thisRoom.attr("floor-no");
				if(thisFloorNo===roomFloorNo)
				{
					roomSelShow(thisRoom);
				}
			});

		});
	};

	/**
	*@描述 ：清除右键菜单
	*/
	var clearRightMenu = function()
	{
		$(".clickRight").remove();
	};

	/**
	*@描述 联房信息
	*/
	var mouseoverUnionInf = function()
	{
		$("[status-type='st-09.png']").unbind('mouseover');
		$("[status-type='st-09.png']").bind('mouseover',function(event)
		{
			event.stopPropagation();
			var imgObj = $(this);
			var checkId = imgObj.attr("guest-checkid");
			var sendData = {'checkId':checkId};
			var url = basePath + "/rooms/getUnionInf.do";
			var groupTip = imgObj.next("div.userTips");
			//是否已经加载过
			if(t.isEmpty(groupTip.html()))
			{
				ajaxM.ajaxAction($,url,function(data)
				{
					var groupHtml = template('rooms_unioninf_tips',data);
					imgObj.after(groupHtml);
					//setTips();
				}
				,sendData);
			}else
				groupTip.show();
		});
	};

	/**
	*@描述：团队悬浮信息
	* 鼠标悬浮 提示团队信息
	*/
	var mouseoverGroupInf = function()
	{
		$("[status-type='st-03.png']").unbind('mouseover');
		$("[status-type='st-03.png']").bind('mouseover',function()
		{
			event.stopPropagation();
			var imgObj = $(this);
			var checkId = imgObj.attr("guest-checkid");
			var sendData = {'checkId':checkId};
			var url = basePath + "/rooms/getUnionInf.do";
			var groupTip = imgObj.next("div.userTips");
			//是否已经加载过
			if(t.isEmpty(groupTip.html()))
			{
				ajaxM.ajaxAction($,url,function(data)
				{
					var groupHtml = template('rooms_groupinf_tips',data);
					imgObj.after(groupHtml);
				}
				,sendData);
			}else
				groupTip.show();
		});
	};

	/**
	*@描述 清空条件 楼名为默认值 楼层为默认值 取消房类选中 取消特征选中
	*/
	var clearConditionBtn = function()
	{
		$("#rooms_btn_clear").unbind("click");
		$("#rooms_btn_clear").bind("click",function(event)
		{
			typeValues = null;
			clearSelectedStat();
			$("#rooms_buildId").find("option[value='']").prop("selected",true);
			var roomsFloorNoObj = $("#rooms_floorNo");
			roomsFloorNoObj.find("option[value='']").prop("selected",true);
			roomsFloorNoObj.find("option[value!='']").remove();
			//把选中的房类和特征取消选中状态
			$("#rooms_type_selecall").prop('checked',false);
			$("#rooms_hroomchr_value_checkall").prop("checked",false);
			$("[name='rooms_types_value']").prop('checked',false);
			$("[name='rooms_hroomchr_value']").prop('checked',false);
			//清空text框
			$("#rooms_roomchoose").val('');
			$("#rooms_features").val('');
			$("#rooms_roomchoose_value").val('');
			$("#rooms_features_veiw").val('');
			$("#roomstat_clearstatu").val('');
			$("#rooms_roomchoose_typevalues").val('');
			//房类数量设置为0 选中状态为未选中
			$("[name='rooms_types_value']").each(function(index, el)
			{
				$(el).prop("checked",false);
			});
			$("#rooms_roomid_count").html('选定房间：0');
			$("[name='rooms_types_number']").each(function(index, el)
			{
				$(el).val('0');
			});
		});
	};

	/**
	*@描述 调用杨军鹏方法 入住登记
	*/
	var CheckinYangjunpeng = function(actionUrl)
	{
		roomStatusUtil.GoToCheckIn(actionUrl);
	};

	/**
	*@描述：入住登记
	*/
	var checkInBtnClick = function()
	{
		$("#rooms_btn_checkin").unbind("click");
		$("#rooms_btn_checkin").bind('click',function(event)
		{
			//判断是否同一类型
			rooms_model_cach['roomId'] = null;
			var types = getSelectTypes();
			if($.inArray("V",types)!=-1 && types.length==1)
			{
			    //空赃房判断
			    var isDuty = false;//脏房
			    var dutyCount = 0;
			    var uncheck = false;//未查
			    var actionUrl = "/guestdetail/getGuestdetail.do";
			    $("[isshow='true']").each(function(index, el) {
					var duty_statu = $(el).parent().attr('data-duty');
					if(duty_statu==="D")
					{
						isDuty = true;
						dutyCount++;
					}
					var imgSrc = $(el).prev().find(".statusImg").find("img").prop("src");
					if(!t.isEmpty(imgSrc) && imgSrc.indexOf("ft-03")!=-1)
					{
						uncheck = true;
					}
				});
				if(isDuty && uncheck)
				{
					altInfMsg("所选房间中有清洁未查空房和不洁空房，确定要办理入住吗？"
					,function()
					{
						CheckinYangjunpeng(actionUrl);
					},function()
					{
						clearSelectedStat();
					});
				}else if(isDuty)
				{
					altInfMsg("所选房间中有不洁空房，确定要办理入住吗？"
					,function()
					{
						CheckinYangjunpeng(actionUrl);
					},function()
					{
						clearSelectedStat();
					});
				}else if(uncheck)
				{
					altInfMsg("所选房间中有清洁未查空房，确定要办理入住吗？"
					,function()
					{
						CheckinYangjunpeng(actionUrl);
					}
					,function()
					{
						clearSelectedStat();
					});
				}else
				{
					CheckinYangjunpeng(actionUrl);
				}
				
				
			}else if(types.length > 1)
			{
				var params = {type:'error',content:'选中多种房态,请重选。',title:'警告提示'
                ,id:'checkin_tips_erroemsg'};
				$.fn.alertDialog(params); 
			    $.fn.alertDialogShow(params.id);
			}else if(types.length==0)
			{
				var params = {type:'error',content:'请选中空房。',title:'警告提示',id:'checkin_tips_erroemsg'};
				$.fn.alertDialog(params); 
			    $.fn.alertDialogShow(params.id);
			}
		});
		
	};

	/**
	*@描述：客单详情点击 选中色块为一个在住房
	*/
	var guestInfBtnClick = function()
	{
		$("#rooms_btn_guestinf").unbind("click");
		$("#rooms_btn_guestinf").bind("click",function(event)
		{
			var actionUrl = "/guestdetail/getLodgerGuestInfo.do";
			CheckinYangjunpeng(actionUrl);
		});
	};

	/**
	*@描述 关闭客户信息刷新界面
	*/
	var closeWindowGuestDetail = function()
	{
		$("#guestdetail_closewindow").unbind("click");
		$("#guestdetail_closewindow").bind("click",function(event)
		{
			$("#rooms_btn_refresh").trigger('click');
		});
	};
	/**
	*@描述 选定房间的信息
	*/
	var selectRoomsInf = function()
	{
		$("#rooms_roomid_count").unbind('click');
		$("#rooms_roomid_count").bind("click",function(event)
		{
			var roomIds = getRoomsIDs();
			if(t.isEmpty(roomIds)){
				var params = {type:'error',content:'请先选中房间。',title:'警告提示'
					,id:'rooms_selected_erroemsg'};
				$.fn.alertDialog(params);
				$.fn.alertDialogShow(params.id);
				return;
			}
			var url = basePath + "/rooms/getSelRoomsInf/" + roomIds;
			var params = {type:'page',title:'选定房间明细表。',id:'rooms_selected_list'
						,content:url,width:500
						,isUpdate:true
						,show:function()
						{
							$.fn.alertDialogShow(params.id);
						}};
			$.fn.alertDialog(params); 
		});
		
	};

	/**
	*@描述 清除选中状态
	*/
	var clearSelectedStat = function()
	{
		$(".pointStatus").each(function(index, el) {
			var ishow = $(el).attr("isshow");
			var pointDiv = $(el).next()
			if(ishow==='true')
			{
				$(el).attr("isshow",'false');
				$(el).hide();
				pointDiv.hide();
			}		
		});
		$("#rooms_roomid_div").empty();
		$("#rooms_roomid_count").html("选定房间：0");
	};

	/**
	*@描述 清除选中状态
	*/
	module.exports.ClearSelectColorStat = function()
	{
		clearSelectedStat();
	};
	
	/**
	*@描述获取当前选中房间id
	*/
	module.exports.GetSelRoomIds = function()
	{
		return getRoomsIDs();
	};

	/**
	*@描述 (提供外部调用)设置清洁状态 修改为不洁 清洁未查 清洁已查
	*/
	module.exports.setClearStat = function(url,roomsIds,temID)
	{
		ajaxM.ajaxAction($,url,function(data)
		{
			if(data)
			{
				if(roomsIds.indexOf(",")!=-1)
				{
					var roomIdArr = roomsIds.split(",");
					$.each(roomIdArr,function(index)
					{
						var roomId = roomIdArr[index];
						changeStat(roomId,temID);
					});
				}
					else
						changeStat(roomsIds,temID);
				//清除选中状态
				clearSelectedStat();
			}
		});
	};

	/**
	*@描述 自动排房按钮监听
	*/
	var rowHouseBtnClick = function()
	{
		$("#rooms_btn_rowhouse").unbind("click");
		$("#rooms_btn_rowhouse").bind("click",function(event)
		{
			var ableStat = $(this).attr("able-stat");
			if(ableStat==='true')
			{
				var radioInput = $("[name='isAll']:first");
				radioInput.prop("checked",true);
				$("[name='rowHouse']").val(true);
				$("#rooms_btn_refresh").trigger('click');
			}
			
		});
	}; 

	/**
	*@描述 设置自动排房
	*/
	var setRowhouseValue = function()
	{
		var rowhouseObj = $("[name='rowHouse']");
		var rowhouse = rowhouseObj.val();
		if(!t.isEmpty(rowhouse) && rowhouse==='true' && !t.isEmpty(typeValues))
		{
			var array = t.strToJson(typeValues);
			for(var i=0;i<array.length;i++)
			{
				var obj = array[i];
				for(k in obj)
				{
					var count = obj[k];//房屋数量 k类型
					var rooms = $("[room-type='" + k + "']");
					for(var j=0;j<count;j++)
					{
						var room = rooms[j];
						$(room).trigger('click');
					}
				}
			}
		}
		rowhouseObj.val(false);
	};

	/**
	*@描述 设置 刷新后房类数量
	*/
	var setRoomTypeChecked = function()
	{
		$("#rooms_roomchoose_typevalues").val(typeValues);
	};

	/**
	*@描述 打开熟客查询页面
	*客户资料可以共用
	*/
	var openFguestSearch = function()
	{
		$("#rooms_btn_vipquery").unbind('click');
		$("#rooms_btn_vipquery").bind("click",function(event)
		{
			var url = basePath + "/fguest/searchFguest.do"
			var params = {type:'page',title:'熟客查询'
					,id:'rooms_openfguestsearch',content:url
					,isUpdate:true
					,titleClass:'alertDiv moveBar2 alertDiv2 RCQueryDiv'
					,width:750
					,show:function()
					{
						$.fn.alertDialogShow(params.id);
					}};
			$.fn.alertDialog(params); 
		});
	};

	/**
	*@初始化 添加各种事件
	*/
	$(function()
	{
		selectedRoomStat();//房态选中 非选中状态改变
		//点击单个房间效果
		$("[room-isfloor='false']").unbind('click');
		$("[room-isfloor='false']").bind('click',function(e)
		{
			var thisRoom = $(this);
			roomSelShow(thisRoom);
		});
		//自动排房
		rowHouseBtnClick();
		//右键菜单
		roomRightClick();
		//清空
		clearConditionBtn();
		//入住登记
		checkInBtnClick();
		//住客资料点击
		guestInfBtnClick();
		//选定房间
		selectRoomsInf();
		//设置房类 和房型的值 rooms_hroomchr_value rooms_features_veiw
		setTimeout(function()
		{
			roomUtil.SetDefultInputValue('rooms_roomchoose','rooms_types_value');
			roomUtil.SetDefultInputValue('rooms_features_veiw','rooms_hroomchr_value');

			//切换 全部 or 可用房
			$("[name='isAll']").unbind('click change');
			$("[name='isAll']").bind("click change",function(event)
			{
				$("#rooms_btn_refresh").trigger('click');
			});
			
		},700);
		$("#rooms_buildId").trigger('change');
		//自动排房
		setRowhouseValue();
		//关闭客户详情
		closeWindowGuestDetail();
		//设置房类隐藏域值
		setRoomTypeChecked();
		//打开熟客查询
		openFguestSearch();
	});
});