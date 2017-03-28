define(function(require, exports, module)
{
	var basePath = $("#path").val();
	var ajaxM = require("../../frame/AjaxManager");
	var t = require("../../frame/Tools");
	var template = require("template");
	var DateUtil = require('../../frame/DateTool');
	var roomUtil = require("./RoomsUtils");
	//格式化日期
	template.helper('dateFormat', function (date, format) {
	    date = new Date(date);
	    var map = {
	        "M": date.getMonth() + 1, //月份 
	        "d": date.getDate(), //日 
	        "h": date.getHours(), //小时 
	        "m": date.getMinutes(), //分 
	        "s": date.getSeconds(), //秒 
	        "q": Math.floor((date.getMonth() + 3) / 3), //季度 
	        "S": date.getMilliseconds() //毫秒 
	    };
	    format = format.replace(/([yMdhmsqS])+/g, function(all, t){
	        var v = map[t];
	        if(v !== undefined){
	            if(all.length > 1){
	                v = '0' + v;
	                v = v.substr(v.length-2);
	            }
	            return v;
	        }
	        else if(t === 'y'){
	            return (date.getFullYear() + '').substr(4 - all.length);
	        }
	        return all;
	    });
	    return format;
	});
	/**
	*@描述 在住房鼠标离开时间
	*/
	var useringModel = function()
	{
		$("[room]").unbind("mouseout");
		$("[room]").bind("mouseout",function(event)
		{
			$(".userTips").hide();
			$(".userImgTips").hide();
			$(".nonSaleTips").hide();
		});
	};

	/**
	*@描述 不可用悬浮事件
	*/
	var usefullMouseover = function()
	{
		$("[isusefull-roomid]").unbind("mouseover");
		$("[isusefull-roomid]").bind("mouseover",function(event)
		{
			event.stopPropagation();
			var beginDate = new Date();
			var timer = beginDate.getTime();

			var unusefullDIV = $(this);
			var checkId = $(this).attr("isusefull-roomid");
			var startDate = $("#roomssearchvo_startdate_minDate").val();
			var endDate = $("#roomssearchvo_old_enddate").val();
			var sendData = {'roomId':checkId,'startDate':startDate
						,'endDate':endDate};
			var url = basePath + "/rooms/getNullRoomInf.do";
			var tipsObj = $(this).find(".nonSaleTips");
			var tipsHtml = tipsObj.html();
			if(t.isEmpty(tipsHtml))
			{
				ajaxM.ajaxAction($,url,function(data)
				{
					if(!t.isEmpty(data))
					{
						var appHtml = template("rooms_unuserfull_tips",{list:data});
						var pDocment = unusefullDIV.find(".nonSaleWord");
						pDocment.after(appHtml);
						//setTips();
						setNonSaleTip(unusefullDIV,timer);
						unusefullDIV.unbind("mouseout");
						unusefullDIV.bind("mouseout",function()
						{
							var endDate = new Date();
							timer = timer - endDate.getTime();
							unusefullDIV.parent().find(".nonSaleTips").hide();
						});
					}
				},sendData);
			}else
			{
				setNonSaleTip(unusefullDIV,timer);
			}
			
		});
	};

	/**
	*@描述 设置不可售浮动提示信息
	*@param nonSaleTipObj 不可售对象
	*@param 时间触发的时间毫秒数
	*/
	var setNonSaleTip = function(nonSaleTipObj,timer)
	{
		//隐藏其他
		$(".userTips").hide();
		$(".userImgTips").hide();
		$(".nonSaleTips").hide();
		if(timer<600)
		{
			return;
		}
		//计算当前位置
		var thisTop1 = nonSaleTipObj.offset().top;
		var thisLeft1 = nonSaleTipObj.offset().left;
		var scrollTop1 = $(document).scrollTop();
		var mainInfoWidth1 = $(".mainInfo").width();
		var userTipsWidth1 = nonSaleTipObj.parent().find(".nonSaleTips").width();
		var thisAllWidth1 = thisLeft1 + userTipsWidth1;
		if(thisAllWidth1 > mainInfoWidth1){
			nonSaleTipObj.parent().find(".nonSaleTips").css('left',thisLeft1-userTipsWidth1+'px');
		}else{
			nonSaleTipObj.parent().find(".nonSaleTips").css('left',thisLeft1+'px');
		}
		var mainInfoDivTop1 = $('.mainInfo')[0].scrollHeight;//高度1134
		var mainInfoScrollTop1 = $('.mainInfo')[0].scrollTop;//滚动条高
		var userTipsHeight1 = nonSaleTipObj.parent().find(".nonSaleTips").height();//tips高度
		var thisAllHeight1 = thisTop1+mainInfoScrollTop1+userTipsHeight1-122+scrollTop1;//总高度
		if(thisAllHeight1 > mainInfoDivTop1){
			nonSaleTipObj.parent().find(".nonSaleTips").css('top',thisTop1-(-40)-userTipsHeight1-scrollTop1+'px');
		}else{
			nonSaleTipObj.parent().find(".nonSaleTips").css('top',thisTop1+60-scrollTop1+'px');
		}
		nonSaleTipObj.parent().find(".nonSaleTips").show(200);
		
	};

	/**
	* @描述 ：建筑物联动下拉 初始 楼层数据
	*/
	var buildSelOnChange = function()
	{
		$("#rooms_buildId").unbind('change');
		$("#rooms_buildId").bind("change",function(e)
		{
			var buidlId = $(this).val();
			//如果选中全部，楼层默认选中全部
			if(t.isEmpty(buidlId))
			{
				var roomsFloorNoObj = $("#rooms_floorNo");
				roomsFloorNoObj.find("option[value='']").prop("selected",true);
				roomsFloorNoObj.find("option[value!='']").remove();
				return;
			}
			var url = basePath + "/rooms/getfloors/" + buidlId;
			ajaxM.ajaxAction($,url,function(data)
			{
				var roomsFloorNoObj = $("#rooms_floorNo");
				roomsFloorNoObj.find("option[value!='']").remove();
				var list = {list:data};
				var optionHtml = template('rooms_option',list);
				roomsFloorNoObj.append(optionHtml);
				//设置楼层默认值
				var floorHidden = $("#rooms_select_louceng");
				var isSet = floorHidden.attr("is-set");
				if(isSet==="false")
				{
					floorHidden.attr("is-set",'true');
					var selVal = floorHidden.val();
					roomsFloorNoObj.find("option").each(function(index, el) 
					{
						var optVal = $(el).val();
						if(selVal===optVal)
						{
							$(el).prop("selected",true);
						}
					});
				}
			});
		});
	};

	/**
	*@描述： 点击房类特征按钮
	*/
	var roomfeaturesBtn = function()
	{
		$("#rooms_roomchoosefeatures_btn").unbind('click');
		$("#rooms_roomchoosefeatures_btn").bind('click',function(event)
		{
			var url = basePath + "/rooms/getFeatures.do";
			var params = {type:'page',title:'房间特征筛选'
						,id:'rooms_features_listdiv'
						,content:url
						,width:500
						,isUpdate:true
						,show:function() 
						{
							$.fn.alertDialogShow(params.id);
						}};
			$.fn.alertDialog(params); 
		});
	};

	/**
	*@描述 点击房间类型按钮/rooms/setUnclear/
	*/
	var roomTypeBtn = function()
	{	
		$("#rooms_roomchoose_btn").unbind('click');
		$("#rooms_roomchoose_btn").bind("click",function(event)
		{
			var url = basePath + "/rooms/getRoomType.do"
			var beginDate = $("#roomssearchvo_startdate").val()
			var endDate = $("#roomssearchvo_enddate").val();
			var floorNo = $("#rooms_buildId").val();
			var date = {'startDate':beginDate,'endDate':endDate,'floorNo':floorNo};
			var params = {type:'page',title:'房类信息'
		        ,id:'room_typeinfo_list'
		        ,content:url
		    	,data:date
		    	,width:500
		    	,isUpdate:true
		    	,show:function()
		    	{
		    		$.fn.alertDialogShow(params.id);
		    	}};
		    $.fn.alertDialog(params); 	
		});
	};

	/**
	*@描述：客户信息
	*/
	var mouseoverGuestInf = function()
	{
		$("[statusImg-checkid]").unbind("mouseover");
		$("[statusImg-checkid]").bind("mouseover",function(event)
		{
			event.stopPropagation();
			var beginTime = new Date();
			var checkId =  $(this).attr('statusImg-checkid');
			var url = basePath + "/rooms/getGuestDocInf.do";
			var sendData = {checkId:checkId};
			var statusImgObj = $(this);
			var userImgTips = statusImgObj.next(".userImgTips");
			var timer = beginTime.getTime();
			//非空判断
			if(t.isEmpty(userImgTips.html()))
			{
				ajaxM.ajaxAction($,url,function(data)
				{
					var guestInf = template('rooms_guestinf_tips',data);
					statusImgObj.after(guestInf);
					setUserStatTips(statusImgObj,timer);
					//鼠标离开事件
					statusImgObj.unbind("mouseout");
					statusImgObj.bind("mouseout",function(event)
					{
						var endDate = new Date();
						timer = timer - endDate.getTime();
						statusImgObj.next().hide();
					});
				}
				,sendData);
			}
			else
			{
				setUserStatTips(statusImgObj,timer);
			}
			
		});
	}

	/**
	*@描述 客态信息悬浮查询
	*/
	var mouseoverInf = function()
	{
		$("[status-type]").unbind('mouseover');
		$("[status-type]").bind('mouseover',function(event)
		{
			event.stopPropagation();
			var beginDate = new Date();
			var timer = beginDate.getTime();
			var imgObj = $(this);
			var statusType = imgObj.attr('status-type');
			var checkId = imgObj.attr("guest-checkid");
			var sendData = {'checkId':checkId};
			var url = basePath;
			var groupTip = imgObj.next("div.userTips");
			var templeteID = "";
			var roomId = $(this).parent().parent().parent().parent().attr("room-id");
			//开始日期 结束日期
			var startDate = $("#roomssearchvo_startdate").val();
			var endDate = $("#roomssearchvo_enddate").val();
			var infDate = null;
			switch(statusType)
			{
				case 'st-09.png':
					url += "/rooms/getUnionInf.do";//联房信息
					templeteID = "rooms_unioninf_tips";
					break;
				case 'st-03.png'://团队预定
					url += "/rooms/getUnionInf.do";
					templeteID = "rooms_groupinf_tips";
					break;
				case 'st-06.png'://预离
					templeteID = "rooms_willleaveroominf_tips";
					url += "/rooms/getWillLeaveRoomInfo.do";
					sendData['roomId'] = roomId;
					break;
				case 'st-07.png'://预抵
					templeteID = "rooms_willcomeroominf_tips";
					sendData['roomId'] = roomId;
					sendData['startDate'] = startDate;
					url += "/rooms/willcomeroominf.do";
					break;
				case 'st-08.png'://欠费
					templeteID="rooms_arrears_tips";
					sendData['roomId'] = roomId;
					url += "/rooms/getArrears.do";
					break;
				case 'st-05.png'://免单
					templeteID = "rooms_free_tips";
					infDate = {'info':'免费房'};
					url = null;
					break;
				case 'st-01.png'://自留房
					templeteID = "rooms_free_tips";
					infDate = {'info':'自留房'};
					url = null;
					break;
				case 'st-04.png'://钟点房
					url = null;
					templeteID = "rooms_free_tips";
					infDate = {'info':'钟点房 12：00  到 14：00'};
					break;
				case 'st-02.png'://客户留房
					templeteID = "rooms_leaveroominf_tips";
					sendData['roomId'] = roomId;
					sendData['startDate'] = startDate;
					sendData['endDate'] = endDate;
					url += "/rooms/getLeaveRoomInfo.do";
					break;
			}	
			//是否已经加载过
			if(t.isEmpty(groupTip.html()))
			{
				if(t.isEmpty(url))
				{
					var groupHtml = template(templeteID,infDate);
						imgObj.after(groupHtml);
						setUserTips(imgObj,timer);
						imgObj.unbind("mouseout");
						imgObj.bind("mouseout",function(event)
						{
							var endDate = new Date();
							timer = timer - endDate.getTime();
							imgObj.next(".userTips").hide();
						});
				}else
				{
					ajaxM.ajaxAction($,url,function(data)
					{
						var groupHtml = template(templeteID,data);
						imgObj.after(groupHtml);
						setUserTips(imgObj,timer);
						imgObj.unbind("mouseout");
						imgObj.bind("mouseout",function(event)
						{
							var endDate = new Date();
							timer = timer - endDate.getTime();
							imgObj.next(".userTips").hide();
						});
					}
					,sendData);
				}
				
			}else
			{
				setUserTips(imgObj,timer);
			}
		});
	};

	/**
	*@描述 客户信息浮动提示
	*@param userTipsObj div对象
	*@param timer 
	*/
	var setUserTips = function(userTipsObj,timer)
	{

		//隐藏其他
		$(".userTips").hide();
		$(".userImgTips").hide();
		$(".nonSaleTips").hide();
		if(timer<600)
		{
			return;
		}
		//计算当前位置
		var thisTop = userTipsObj.offset().top;
		var thisLeft = userTipsObj.offset().left;
		var scrollTop = $(document).scrollTop();
		var mainInfoWidth = $(".mainInfo").width();
		var userTipsWidth = userTipsObj.parent().find(".userTips").width();
		var thisAllWidth = thisLeft + userTipsWidth;
		if(thisAllWidth > mainInfoWidth){
			userTipsObj.parent().find(".userTips").css('left',thisLeft-userTipsWidth+'px');
		}else{
			userTipsObj.parent().find(".userTips").css('left',thisLeft+'px');
		}
		var mainInfoDivTop = $('.mainInfo')[0].scrollHeight;//高度1134
		var mainInfoScrollTop = $('.mainInfo')[0].scrollTop;//滚动条高
		var userTipsHeight = userTipsObj.parent().find(".userTips").height();//tips高度
		var thisAllHeight = thisTop+mainInfoScrollTop+userTipsHeight-122+scrollTop;//总高度
		if(thisAllHeight > mainInfoDivTop){
			userTipsObj.parent().find(".userTips").css('top',thisTop-22-userTipsHeight-scrollTop+'px');
		}else{
			userTipsObj.parent().find(".userTips").css('top',thisTop+24-scrollTop+'px');
		}	
		userTipsObj.parent().find(".userTips").show(200);
	};

	/**
	*@描述 维修的双击事件
	*/
	var fixDbclick = function()
	{
		$(".standardBlack").unbind('dblclick');
		$(".standardBlack").bind("dblclick",function(event)
		{
			var roomId = $(this).attr("room-id");
			var type = '2';
			var active = '0';
			var startDate = $("#roomssearchvo_startdate").val();
			var endDate = $("#roomssearchvo_enddate").val();
			roomUtil.ViewFixFrozenInfOpen(roomId,type,active,startDate,endDate);
		});
	};

	/**
	*@描述 房间客态信息浮动提示
	*@param userStatObj
	*@param timer 鼠标悬浮的日期含描述
	*/
	var setUserStatTips = function(userStatObj,timer)
	{
		//隐藏其他
		$(".userTips").hide();
		$(".userImgTips").hide();
		$(".nonSaleTips").hide();
		if(timer<600)
		{
			return;
		}
		//计算当前位置
		var thisTop3 = userStatObj.offset().top;
		var thisLeft3 = userStatObj.offset().left;
		var scrollTop3 = $(document).scrollTop();
		var mainInfoWidth3 = $(".mainInfo").width();
		var userTipsWidth3 = userStatObj.parent().find(".userImgTips").width();
		var thisAllWidth3 = thisLeft3 + userTipsWidth3;
		if(thisAllWidth3 > mainInfoWidth3){
			userStatObj.parent().find(".userImgTips").css('left',thisLeft3-userTipsWidth3+'px');
		}else{
			userStatObj.parent().find(".userImgTips").css('left',thisLeft3+'px');
		}
		var mainInfoDivTop3 = $('.mainInfo')[0].scrollHeight;//高度1134
		var mainInfoScrollTop3 = $('.mainInfo')[0].scrollTop;//滚动条高
		var userTipsHeight3 = userStatObj.parent().find(".userImgTips").height();//tips高度
		var thisAllHeight3 = thisTop3+mainInfoScrollTop3+userTipsHeight3-122+scrollTop3;//总高度
		if(thisAllHeight3 > mainInfoDivTop3){
			userStatObj.parent().find(".userImgTips").css('top',thisTop3-22-userTipsHeight3-scrollTop3+'px');
		}else{
			userStatObj.parent().find(".userImgTips").css('top',thisTop3+38-scrollTop3+'px');
		}
		userStatObj.parent().find(".userImgTips").show(200);
	};

	/**
	*@截至日期校验
	*/
	var endDateCheckUtil = function()
	{
		$("#roomssearchvo_enddate").unbind('click');
		$("#roomssearchvo_enddate").bind('click',function(event)
		{
			//分别取 开始时间 截至时间 并且 初始为日期类型
			var nowDate = new Date();
			
			//判断
			var beginDate = $("#roomssearchvo_startdate").val();
			beginDate = t.ReplaceAll(beginDate,"-","") + "0000";
			var bDate = new DateUtil(nowDate).setDate(beginDate);
			bDate.setDate(bDate.getDate());
			endDatelimt = new DateUtil(bDate).format('yyyy-MM-dd');
			//调用日期控件
			WdatePicker({minDate:endDatelimt});
		});	
	};

	/**
	*@描述 冻结双击时间
	*/
	var frozenDbclick = function()
	{
		$(".standardgey").unbind('dblclick');
		$(".standardgey").bind("dblclick",function(event)
		{
			var roomId = $(this).attr("room-id");
			var type = '3';
			var active = '0';
			var startDate = $("#roomssearchvo_startdate").val();
			var endDate = $("#roomssearchvo_enddate").val();
			roomUtil.ViewFixFrozenInfOpen(roomId,type,active,startDate,endDate);
		});
	};

	/**
	*@描述 打开入住登记界面
	*@param url 
	*@param roomIds 房间号
	*/
	var openCheckInPannel = function(url,roomIds)
	{
		var startDate = $("#roomssearchvo_startdate_minDate").val();//开始日期
		var endDate = $("#roomssearchvo_old_enddate").val();//截至日期
		var sendData = {'roomIds':roomIds,'reachDate':startDate,'leaveDate':endDate};
		var params = {type:'page',title:'客单详情（在住）'
	        ,id:'room_guestdetail_info'
	        ,content:url
	        ,titleClass:"alertDiv checkInDiv moveBar checkDetailsDiv"
	    	,data:sendData
	    	,isUpdate:true
	    	,width:1072
	    	,top:5
	    	/*,
	    	show:function()
	    	{
	    		$.fn.alertDialogShow(params.id);
	    	}*/
			,cancel: function ()
			{
				$("#rooms_btn_refresh").trigger("click");
			}};
	    $.fn.alertDialog(params);
	};

	/**
	*@描述 入住登记
	*@param actionUrl 入住登记url
	*/
	var junPengCheckIn = function(actionUrl)
	{
		if($("#maipage_tips_infmsg")!=undefined){
			$("#maipage_tips_infmsg").fadeOut();
		}
		var roomIds = getRoomsIDs();//选中的房间号
		rooms_model_cach['roomId'] = roomIds;
		//调杨军鹏页面
		var url = basePath + actionUrl; 
		openCheckInPannel(url,roomIds);
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
	*@描述刷新功能
	*/
	var reFreshBtnClick = function()
	{
		$("#rooms_btn_refresh").unbind('click');
		$("#rooms_btn_refresh").bind('click', function(event) {
			$("#rooms_searchvo").submit();
		});
	};

	/**
	*@描述 入住登记
	*@param actionUrl 入住登记url
	*/
	module.exports.GoToCheckIn = function(actionUrl)
	{
		junPengCheckIn(actionUrl);
	};

	/**
	*@描述 打开入住登记页面
	*/
	module.exports.OpenCheckInPannel = function(url,roomIds)
	{
		openCheckInPannel(url,roomIds);
	};

	/**
	*@初始化
	*/
	$(function()
	{
		useringModel();//鼠标移开移除提示信息
		buildSelOnChange();//绑定楼名下拉框改变事件
		//点击房类特征
		roomfeaturesBtn();
		//点击房类
		roomTypeBtn();
		//客人信息鼠标悬浮
		mouseoverGuestInf();
		//客态悬浮信息
		mouseoverInf();
		//不可用悬浮事件
		usefullMouseover();
		//日期校验
		endDateCheckUtil();
		fixDbclick();//维修双击
		frozenDbclick();//冻结双击
		//刷新按钮
		reFreshBtnClick();
	});
});