define(function (require, exports, module) 
{
	/**
	*@描述 界面逻辑处理
	*@auther zhangzhenxing
	*@Date 2015-12-11
	*/
	var t = require("../frame/Tools");
	var ajaxM = require("../frame/AjaxManager");
	var basePath = $("#path").val();
	var DateUtil = require("../frame/DateTool");
	var RoomsUtil = require("../rooms/plugins/RoomsUtils");
	var gridFlex = null;
	guest_room_utils = RoomsUtil;
	frame_utils = t;
	/**
	*@描述 根据客源类型筛选客人来源选中
	*/
	var fiterGuestOrc = function()
	{
		$("[name='inType']").unbind("click change");
		$("[name='inType']").bind("click change",function(event)
		{
			var checkVal = $("[name='inType']:checked").val();
			checkVal = checkVal-0;
			//选中与客源了下相等的值
			$("[src-type]").each(function(index, el) 
			{
				var thisType = $(el).attr("src-type");
				thisType = thisType - 0;
				if(thisType!=checkVal)
				{
					$(el).hide();
				}else
				{
					$(el).show();
				}
			});
		});
	};

	/**
	*@描述 设置颜色
	*/
	var setRoomListClass = function()
	{
		//解决偶尔初始为 1 的问题
		$("[guestinfo-roomid]").each(function(index, el) 
		{
			var thisRoomId = $(el).attr("guestinfo-roomid");	
			thisRoomId = t.ReplaceAll(thisRoomId," ","");
			var roomId = $("#guestinfo_room_id").val();
			roomId = t.ReplaceAll(roomId," ","");
			if(roomId===thisRoomId)
			{
				if(!$(el).hasClass('roomListLi'))
				{
					$(el).addClass('roomListLi');
				}
				$(el).trigger('click');
			}
		});
	};
	/**
	*@描述 切换界面
	*/
	var switchTab = function()
	{
		$("#guestdetal_tabguestinfo,#guestdetal_tabguestibill").unbind("click");
		$("#guestdetal_tabguestinfo,#guestdetal_tabguestibill").bind("click",function(event)
		{
			var showTabID = $(this).attr("id");
			var showDIV = $("[datashow-id='" + showTabID + "']");
			$("[datashow-id]").hide();
			showDIV.show();
			$(".GuestTab li").removeClass('point');
			$(this).addClass('point');
			//如果是客户资料 重新初始客户资料
			if(showTabID==='guestdetal_tabguestinfo')
			{
				setRoomListClass();
				//$(".roomListLi").trigger('click');
			}else if(showTabID==='guestdetal_tabguestibill')
			{
				var checkID = $("#account_namec").val();
				$(".roomListLi").trigger('click');
				getBillInf(checkID);
			}
		});
	};

	/**
	*@描述 退出界面
	*/
	var quitWindow = function()
	{
		if($("#guestinfo_guestDetailQuit").hasClass('disabledAButton')){
			return;
		}
		$("#closeWindowsroom_guestdetail_info").trigger('click');
	}


	/**
	*@描述 初始化list列表
	*@dataList 列表数据
	*@divID grid的div id 
	*@return accountGrid flex列表对象
	*/
	var billGridInit = function(dataList)
	{
		var accountGrid =  new wijmo.grid.FlexGrid('#guestdetail_accountGrid', {
		autoGenerateColumns: false,
		columns: [
				{ header: 'setlId', binding: 'setlId',visible:false}, 
	  	         { header: 'operId', binding: 'operId',visible:false}, 
	  	         { header: 'okFlag', binding: 'okFlag',visible:false}, 
	             { header: '序号', binding: 'sortNum',width:50 }, 
	             { header: 'ID号', binding: 'ID',width:100 },
	             { header: '单号', binding: 'acco_id' ,width:100},
	             { header: '借项', binding: 'cname' ,width:100}, 
	             { header: '贷项', binding: 'sname',width:100},
	             { header: '金额', binding: 'balance', width:100 },
	             { header: '服务费', binding: 'svc_charge',width:100 },
	             { header: '房号', binding: 'room_id',width:70 },
	             { header: '时间', binding: 'oper_time',width:100 },
	             { header: '外币', binding: 'foreignm',width:100 },
	             { header: '币种', binding: 'code_namec',width:100 },
	             { header: '状态', binding: 'status',width:100 },
	             { header: '操作员', binding: 'oper_name',width:100 },
	             { header: '摘要', binding: 'ext_name',width:100 },
	             { header: '备注', binding: 'notes',width:100 },
	             { header: '结账号', binding: 'payNum',width:100 }
			],
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			selectionMode:'Row',
			isReadOnly:true
		});
		var view = new wijmo.collections.CollectionView(dataList);
		accountGrid.itemsSource = view;
		view = RoomsUtil.UpdateWijomFlexGrid(accountGrid,dataList);
		accountGrid.onSelectionChanging = function(e){
			return true;
		};
		
		guestDetaiAccFlexGrid = accountGrid;
		//加载账务相关功能
		RoomsUtil.UpdateWijomFlexGrid(accountGrid,dataList);
		var guestAccout = require("./guestaccount");
		guestAccout.load();
		return accountGrid;
	};

	/**
	*@描述 获取账务信息
	*
	*/
	var getBillInf = function(checkID)
	{
		$("#account_namee").val(checkID);
		$("#account_namec").val(checkID);
		var startDate = $("#account_startDate").val();
		var endDate = $("#account_endDate").val();
		var billType = $("[name='radioAccount']:checked").val();//账务类型
		var showType = $("[name='radioType']:checked").val();//显示类型 明细（A）、汇总（B）、分类（C）
		var okFlag = $("[name='disturb']:checked").val();//未结（N）、已结(Y)
		var sendDate = {'checkId':checkID,'startDate':startDate
						,'endDate':endDate,'billType':billType
						,'showType':showType,'okFlag':okFlag};
		var url = basePath + "/guestdetail/getGuestDocDetal.do";
		ajaxM.ajaxAction($,url,function(data)
		{
			if(data.msg==='01')
			{
				//客户信息
				var guestDoc = data.guestDoc;
				$("#account_roomprice").val(guestDoc['roomPrice']);
				var reachDate = guestDoc['reachDate'];
			//	reachDate = reachDate - 0;
				$("#account_inDate").val(guestDoc['reachDate']);
				var leaveDate = guestDoc['leaveDate'];
			//	leaveDate = leaveDate - 0;
				$("#account_leavedate").val(guestDoc['leaveDate']);	
				$("#account_notice").val(guestDoc['notice']);	
				$("#account_paytype").val(data.payType);
				//A账信息
				if(t.isEmpty(data.aBill))
				{
					$("#account_billaid").val(guestDoc['billaId']);
					$("#account_borrowA").val("0.00");
					$("#account_lentA").val("0.00")
					$("#account_remainA").val("0.00");
					$("#account_totalA").val("0.00");
				}else
				{
					var aBillDate = data.aBill;
					$("#account_billaid").val(aBillDate['billId']);
					$("#account_borrowA").val(t.ChangeTwoDecimalNumber(aBillDate['borrow']));
					$("#account_lentA").val(t.ChangeTwoDecimalNumber(aBillDate['lent']));
					$("#account_remainA").val(t.ChangeTwoDecimalNumber(aBillDate['borrow'] - aBillDate['lent']));
					$("#account_totalA").val(t.ChangeTwoDecimalNumber(aBillDate['limit'] + aBillDate['authBalance']));
				}
				//B账信息
				if(t.isEmpty(data.bBill))
				{
					$("#account_billbid").val(guestDoc['billbId']);
					$("#account_borrowB").val("0.00");
					$("#account_lentB").val("0.00")
					$("#account_remainB").val("0.00");
					$("#account_totalB").val("0.00");
				}else
				{
					var bBillDate = data.bBill;
					$("#account_billbid").val(bBillDate['billId']);
					$("#account_borrowB").val(t.ChangeTwoDecimalNumber(bBillDate['borrow']));
					$("#account_lentB").val(t.ChangeTwoDecimalNumber(bBillDate['lent']));
					$("#account_remainB").val(t.ChangeTwoDecimalNumber(bBillDate['borrow'] - bBillDate['lent']));
					$("#account_totalB").val(t.ChangeTwoDecimalNumber(bBillDate['limit'] + bBillDate['authBalance']));
				}
				//判断列表数据是否为空
				var isInit = $("#guestinfo_accoutlistinit").val();
				if(isInit==='false')
				{
					$("#guestinfo_accoutlistinit").val("true");
					gridFlex = billGridInit(data.girdData);
				}else
				{
					RoomsUtil.UpdateWijomFlexGrid(gridFlex,data.girdData);
				}
				
				var chkStat = guestDoc.chkStat;
				if(chkStat == 'O'){
					//$("#preAuthorization").addClass('disabledAButton');
					//离店人员 离店及退房按钮置灰 预授权 新增置灰 2016-1-5
					$("#leavingHotel").addClass('disabledAButton');
					$("#checkOutOperation").addClass('disabledAButton');
					$("#addPreAward").addClass('disabledAButton');
					//setButtonEnable(false);
				}else{
					//$("#preAuthorization").removeClass('disabledAButton');
					$("#leavingHotel").removeClass('disabledAButton');
					$("#checkOutOperation").removeClass('disabledAButton');
					$("#addPreAward").removeClass('disabledAButton');
					//setButtonEnable(true);
				}
			}
		},sendDate);
	};

	/**
	*@ 将按钮设置为可用
	*@param isEnable 是否可用 true 不可用 false 可用
	*/
	var setButtonEnable = function(isEnable)
	{
		var btnId = "#guestinfo_addGuest"
					+ ",#guestinfo_copyGuest,#guestinfo_scanCardbtn,#guestinfo_changeRoom"
					+",#guestinfo_sendRoomCard,#changeRoom,#guestinfo_guestDetailQuit";
					+",#guest_bill_refush_smbtn,#checkOut,#deposit";
		var roomsSelect = "#guestinfo_roomList [guestinfo-roomid]";
		if(isEnable)
		{
			$(btnId).removeClass('disabledAButton');
			$("#guestinfo_confirm").addClass("disabledAButton");
			$("#guestinfo_giveup").addClass("disabledAButton");
			$(roomsSelect).css("cursor" , "");
			$(roomsSelect).removeClass('clickNo');
		}else
		{
			$(btnId).addClass('disabledAButton');
			$("#guestinfo_confirm").removeClass("disabledAButton");
			$("#guestinfo_giveup").removeClass("disabledAButton");
			$(roomsSelect).css("cursor" , "not-allowed");
			$(roomsSelect).addClass("clickNo");
		}
	};

	/**
	*@描述 设置弹出div移动
	*/
	var setMoveDiv = function(moveDivClassName,moveBarClassname)
	{
		$("." + moveBarClassname).css({'left':-1,'top':0});
	};
	/**
	*@描述 客户账务页面 中文名和英文名change事件 
	*/
	var nameOnChange = function()
	{
		$("#account_namec,#account_namee").unbind("change");
		$("#account_namec,#account_namee").bind("change",function(event)
		{
			var checkID = $(this).val();
			getBillInf(checkID);
		});
	};

	/**
	*@描述 打开分账界面
	*/
	var openSpitBillView = function()
	{
		$("#guestinfo_split").unbind("click");
		$("#guestinfo_split").bind('click',function(event)
		{
			var url = basePath + "/guestdetail/getSubBill.do";
			var checkID = $("#guestinfo_checkId").val();
			var sendData = {'checkId':checkID};
			var params = {type:'page',title:'分账设置'
						,id:'guestdetal_subbill_set'
						,content:url
						,data:sendData
						,isUpdate:true
						,width:555
						,show:function()
						{
							$.fn.alertDialogShow(params.id);
						}};
			$.fn.alertDialog(params);
		});
	};

	/**
	*@描述 打开房价列表
	*/
	var openRoomPriceList = function()
	{
		$("#priceList_2").unbind("click");
		$("#priceList_2").bind("click",function()
		{
			$("#guestinfo_pricesList").trigger('click');
		});
	};

	/**
	*@描述 获取身份证信息
	*/
	var creatCardInfo = function(datasource)
	{
		var cardInf = "";
		cardInf = $("#guestinfo_scanidinf").val();
		return cardInf;
	};

	/**
	*@描述 账务刷新按钮
	*/
	var billRefulsh = function()
	{
		$("#guest_bill_refush_smbtn").unbind('click');
		$("#guest_bill_refush_smbtn").bind('click',function(event)
		{
			$("#guestdetal_tabguestibill").trigger('click');
		});
	};

	/**
	*@ 将按钮设置为可用
	*@param isEnable 是否可用 true 不可用 false 可用
	*/
	module.exports.setBtnEnable = function(isEnable)
	{
		setButtonEnable(isEnable);
	};

	var setbillbtnEnable = function(isEnable)
	{
		var btnId = "#leavingHotel,#receivables,#checkOutOperation"
					+",#preAuthorization,#guestSplitInfo,#accountedFor"
					+",#customAccounts,#cancleBill,#transferAccounts"
					+",#cancleBill,#guest_bill_contract,#guest_bill_print"
					+",#forALongTime,#accountsQuit";
		if(isEnable)
		{
			$(btnId).removeClass('disabledAButton');
		}else
		{
			$(btnId).addClass('disabledAButton');
		}
	};

	/**
	*@描述 设置按钮客户账务信息不可用
	*@param isEnable 是否可用 true 不可用 false 可用
	*/
	module.exports.SetBillBtnEnable = function(isEnable)
	{
		setbillbtnEnable(isEnable);
	}

	/**
	*@描述 住客资料信息初始化
	*/
	var initRoomInf = function()
	{
		$.fn.alertDialogShow("room_guestdetail_info");
		setRoomListClass();
		$(".roomListLi").trigger('click');
	};
	/**
	*@描述 绑定合约单位  sxp 2015-12-31
	*/
	var hyUnitDataBind = function (){
		var tadVo={
			symbol:1	
		};
		$.ajax({
			url:"/marketing/searchTadoclist.do",
			type:"post",
			dataType:'json',
			data:tadVo,
			success:function(data){
				if(data!=undefined && data!=null){
					$('#guestinfo_theCompany').autocomplete(data,   
				         { 
							minChars: 0,
							max: 12,
							autoFill: true,
							mustMatch: false,
							matchContains: false,
							scrollHeight: 220,
							width: 260,
							formatItem: function(row, i, total) {
								return row.namec;
							},
							formatMatch: function(row) {
								return row.namec;
							},
							formatResult: function(row){
								return row.namecc;
							}
					    }).result(function(event, row, formatted){
					    	  // 销售员
					    	  $("#guestdetail_saler").val(row.salemanName);
					    	  $("#guestinfo_company_id").val(row.id);
					    	  $("#guestinfo_ta_type").val(row.taType);
					    	  var rateCode = row.rateCode ;
					    	  // 加载放假方案列表
					    	  initPrcSchemeIdSelect(rateCode , null, $("#guestinfo_room_id").val());
					    });  
				}
			}
		});
	}
	
	/**
	 *@描述 房价方案的onchange事件 
	 * */
	var roomPriceScheme = function()
	{
		$("#guestdetail_prcSchemeId").unbind('change');
		$("#guestdetail_prcSchemeId").bind('change', function(e,initFlag)
		{
			var selectedOption = $(this).find("option:selected") ;
			// 当前房间方案入住类型
			var pt = selectedOption.attr("data-roomplantype");
			$(".roomplanType[value='"+(pt&&pt!="null"?pt:1)+"']").prop("checked",true);
				
			
			if(selectedOption.attr("value") != ""){
				// 是否选中 0 否  1是
				var checked = selectedOption.attr("data-checked");
				// 是否允许修改  0 否  1是
				var editable = selectedOption.attr("data-editable");
				// 放假方案  价格
				if(checked == '0'){
					// 默认填充放假方案的价格
					if(isNull(initFlag)){
						$("#guestinfo_nowPrice").val("-1");
						$("#guestinfo_enableRoomPlan").attr("data-roomPlan_price" , "-1");
						$("#guestinfo_enableRoomPlan").prop("checked" , false);
					}
					if(editable == '0'){
						$("#guestinfo_nowPrice").prop("readonly" , false);
						$("#guestinfo_enableRoomPlan").prop("disabled" , true);
						$("#guestinfo_enableRoomPlan_span").css("color" , "grey");
					}else if(editable == '1'){
						$("#guestinfo_nowPrice").prop("readonly" , false);
						$("#guestinfo_enableRoomPlan").prop("disabled" , false);
						$("#guestinfo_enableRoomPlan_span").css("color" , "");
						if(isNull(initFlag)){
							$.ajax({
								url:"/hroomPlan/findHroomPlanPrice.do",
								type:"post",
								async:false,
								dataType:'json',
								data:{roomplanCode:selectedOption.val(),roomId:$("#guestinfo_room_id").val()},
								success:function(data){
									var _data = (data == "-1" ? data : getDoubleStr(data));
									// 默认赋值放假方案的价格
									$("#guestinfo_enableRoomPlan").attr("data-roomPlan_price" , _data);
								}
							});
						}
					}
				}else if(checked == '1'){
					// 默认填充放假方案的价格
					if(isNull(initFlag)){
						$.ajax({
							url:"/hroomPlan/findHroomPlanPrice.do",
							type:"post",
							async:false,
							dataType:'json',
							data:{roomplanCode:selectedOption.val(),roomId:$("#guestinfo_room_id").val()},
							success:function(data){
								var _data = (data == "-1" ? data : getDoubleStr(data));
								$("#guestinfo_nowPrice").val(_data);
								$("#guestinfo_enableRoomPlan").attr("data-roomPlan_price" , _data);
							}
						});
						$("#guestinfo_enableRoomPlan").prop("checked" , true);
					}
					
					if(editable == '0'){
						$("#guestinfo_enableRoomPlan").prop("disabled" , true);
						$("#guestinfo_enableRoomPlan_span").css("color" , "grey");
						$("#guestinfo_nowPrice").prop("readonly" , true);
						$("#guestinfo_nowPrice").css("color" , "grey");
					}else if(editable == '1'){
						$("#guestinfo_enableRoomPlan").prop("disabled" , false);
						$("#guestinfo_enableRoomPlan_span").css("color" , "");
						
						$("#guestinfo_nowPrice").prop("readonly" , false);
						$("#guestinfo_nowPrice").css("color" , "");
					}
				}
				// 赋值 是否启用放假方案 允许修改
				$("#guestinfo_enableRoomPlan").attr("data-editable" , editable);
			}else{
				// 默认填充放假方案的价格
				if(isNull(initFlag)){
					$("#guestinfo_enableRoomPlan").prop("checked" , false);
					$("#guestinfo_enableRoomPlan").prop("disabled" , true);
					$("#guestinfo_enableRoomPlan_span").css("color" , "grey");
					$("#guestinfo_enableRoomPlan").attr("data-roomPlan_price" , "-1");
					$("#guestinfo_nowPrice").val("-1");
				}
			}
		});
	};
	/**
	 *@描述 是否启用放假方案的click事件 
	 * */
	var enableRoomPlanScheme = function()
	{
		$("#guestinfo_enableRoomPlan").unbind('click');
		$("#guestinfo_enableRoomPlan").bind('click',function(e)
		{
			$("#guestinfo_isRoomPlan").val($(this).prop("checked"));
			if($(this).prop("checked") == true){
				// 默认填充放假方案的价格
				$("#guestinfo_nowPrice").val($(this).attr("data-roomPlan_price"));
				$("#guestinfo_nowPrice").prop("readonly" , true);
				$("#guestinfo_nowPrice").css("color" , "grey");
			}else{
				// 默认填充放假方案的价格
				$("#guestinfo_nowPrice").val("-1");
				$("#guestinfo_nowPrice").prop("readonly" , false);
				$("#guestinfo_nowPrice").css("color" , "");
			}
		});
	};
	/**
	 *@描述 入住类型的click事件 
	 * */
	var roomPlanTypeScheme = function()
	{
		$(".roomplanType").unbind('click');
		$(".roomplanType").bind('click',function(e)
		{
			// 加载放假方案列表
			initPrcSchemeIdSelect(null , $(this).val() , $("#guestinfo_room_id").val());
			
		});
	};
	var initPrcSchemeIdSelect = function(rateCode , roomplanType , roomId){
		$.ajax({
			url:"/hroomPlan/hroomPlanData.do",
			type:"post",
			data:{roomplanType : roomplanType,rateCode : rateCode , roomId : roomId},
			dataType:'json',
			success:function(data){
				var option_html = "<option value='' data-roomplanType='"+roomplanType+"'></option>" ;
				if(data && data.length > 0){
					for ( var v = 0 ; v < data.length ; v++) {
						console.log(data[v]);
						option_html += "<option value='"+data[v].hroomPlan.codeId+"' "+
						(rateCode? (data[v].hroomPlan.codeId.trim() ==rateCode.trim()  ? "selected='selected'" : "") : "")
						+" data-roomplanType='"+data[v].hroomPlan.rmplanType+"'"
						+" data-checked='"+data[v].hroomPlan.checked+"'"
						+" data-editable='"+data[v].hroomPlan.editable+"'"
						+">"+data[v].hroomPlan.codeNamec+"</option>" ;
					}
					
				}
				$("#guestdetail_prcSchemeId").html(option_html) ;
				roomPriceScheme();//房价方案
				$("#guestdetail_prcSchemeId").trigger('change');
			}
		});
	};
	var getDoubleStr=function(num){
		num=num+"";
		if(num=="") return "0.00";
		var index=num.indexOf(".");
		if(index!=-1){//有点号
			if((index+1)==num.length){
				num+='00';
			}else if((index+2)==num.length){
				num+='0';
			}
		}else{//没有点好
			num+='.00';
		}
		return num;
	}
	/**
	 * 描述 绑定房价方案onChange事件 给外部调用
	 * */
	module.exports.RoomGuestPriceScheme = function()
	{
		roomPriceScheme();
	};
	//初始化
	//初始完成后执行
	module.exports.load = function()
	{
		switchTab();//界面切换效果
		fiterGuestOrc();//客源筛选
		$("#guestinfo_guestDetailQuit").unbind("click");
		$("#guestinfo_guestDetailQuit").bind("click",function(event)
		{
			quitWindow();
		});
		$("[name='inType']:checked").trigger('click');
		//如果是住客详情进入 则 切换至账单 pageType 0 预定入住 1 住客详情 2 双击住客资料
		var pageType = $("#guestinfo_pagetype").val();
		var hairRoomCard = $("#guestinfo_hairRoomCard").val()==='true' ? true : false;
		var scanCard = $("#guestinfo_scanCard").val()==='true' ? true : false;
		pageType = pageType - 0;
		switch(pageType)
		{
			case 0:
				if(scanCard)
				{
					var result = RoomsUtil.ScanOutIdCard(creatCardInfo(guests),hairRoomCard);
					if(result==='true')
					{
						initRoomInf();
					}else if(result==='false')
					{
						altWaringMsg("身份证扫描失败"
							,function()
							{
								initRoomInf();
							},function()
							{
								initRoomInf();
							});
					}
				}
				else
				{
					$.fn.alertDialogShow("room_guestdetail_info");
				}
				var checkId = $("#guestinfo_checkId").val();
				RoomsUtil.InitPictureSrc("crdPhoto",checkId);
				break;
			case 1:
				$.fn.alertDialogShow("room_guestdetail_info");
				$("#guestdetal_tabguestibill").trigger('click');
				break;
			case 2:
				$.fn.alertDialogShow("room_guestdetail_info");
				break;
		}
		nameOnChange();//监听姓名下拉框
		setMoveDiv('moveDivAlert2','moveBar2');//设置div移动
		setMoveDiv('moveDivAlert3','moveBar3');//设置div移动
		openSpitBillView();//分账设置
		openRoomPriceList();//房价列表
		billRefulsh();//账务刷新
		hyUnitDataBind();// 合约单位  sxp 2015-12-31
		roomPlanTypeScheme();// 入住类型  sxp 2015-12-31
		enableRoomPlanScheme() ; // 是否启用放假方案
	};
});