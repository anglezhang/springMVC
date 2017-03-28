define(function(require, exports, module)
{
	var t = require("../../frame/Tools");
	var basePath = $("#path").val();
	var template = require("template");
	var ajaxM = require("../../frame/AjaxManager");
	var DateUtil = require('../../frame/DateTool');
	var roomUtil = require("./RoomsUtils");
	var roomStatusUtil = require("./roomStatuUtil");
	var initPanel = function()
	{
		var liTab = $("#userCurrentState");
		liTab.addClass('point');
		$("#userCheckIn").removeClass('point');
		$("[show-id='userCheckIn']").hide();
		$("[show-id='userCurrentState']").show();
	};

	/**
	*@描述 选中效果
	*/
	var setRoomStat = function()
	{
		$("#kongfang,#zaizhu,#weixiu,#dongjie").unbind('click');
		$("#kongfang,#zaizhu,#weixiu,#dongjie").bind('click',function(event)
		{
			var temp = new Array();
			for(var i=0;i<=10;i++)
			{
				var number = 1<<i;
				temp.push(number);
			}
			var ftnum = 65535 ;
			var flag = false;
			var temin;
			$("#f_div input[type='checkbox']").each(function(){
				flag = flag || $(this).is(":checked");
				//alert(flag);
				if($(this).is(":checked")){
					ftnum = (parseInt(ftnum) & parseInt($(this).attr("data-v"))); 
				}
			});
			var tempi = parseInt(ftnum);
			for (var int = 0; int < temp.length; int++) {
				temin = parseInt(temp[int]);
				if(flag){
					if((tempi & temin) != 0){//和0比较，不等于0，可以点击的房态
						$("#kt_"+temin).removeClass("stateDis");
					}else{
						$("#kt_"+temin).addClass("stateDis");//白色可以点击，灰色不可点击
						$("#kt_"+temin).removeClass('stateSel');//移除状态
						$("#kt_"+temin).find("input").removeAttr('name');//移除name属性
					}
				}else{
					$("#kt_"+temin).removeClass("stateDis");
				}
			}
		});
		
		/*当前房态点击选中*/
		$(".stateLB").off("click");
		$(".stateLB").on("click",function(){	
			//添加属性值，给后台数据筛选提供name
	        var eleId = $(this).prop("id");
	        var inputObj = $(this).find("input");
        	var inputVal = $(this).attr("input-value");		
	        if($(this).hasClass("stateDis"))
        	{
        		return;
        	} 
	        $(this).toggleClass("stateSel");
	        if($(this).hasClass('stateSel'))
	        {
	        	if(eleId==='kt_1' || eleId==='kt_2')
		        {
		        	inputObj.prop("name","clearStatu");
		        	
		        }else
		        {
		        	inputObj.prop("name","guestStatus");
		        }
	        	inputObj.val(inputVal);
	        }else
	        {
	        	inputObj.removeAttr('name');
        		inputObj.val('');
	        }
	        
	    });
	};

	/**
	*@描述 初始化日期
	*/
	var initStartDate = function()
	{
		$("#roomssearchvo_startdate").unbind('click');
		$("#roomssearchvo_startdate").bind('click',function(event)
		{
			WdatePicker();
		});
	};

	/**
	*@描述 打开住客详情界面
	*/
	var roomInDBclick = function()
	{
		$(".using").unbind('dblclick');
		$(".using").bind('dblclick',function(event)
		{
			var roomID = $(this).attr("room-id");
			var url = basePath + "/guestdetail/getLodgerGuestInfo.do";
			roomStatusUtil.OpenCheckInPannel(url,roomID);
		});
	};

	/**
	*@描述 打开不可售界面
	*/
	var openNoSalePannel = function()
	{
		$("[cannotuse]").unbind("dblclick");
		$("[cannotuse]").bind("dblclick",function(event)
		{
			var roomID = $(this).attr("room-id");
			var startDate = $("#roomssearchvo_startdate").val();
			var endDate = $("#roomssearchvo_enddate").val();
			var typeName = $(this).find('h6').html();
			var sendData = {'roomId':roomID,'startDate':startDate,'endDate':endDate,'typeName':typeName};
			var url = basePath + "/roomsManager/openNoSalePannel.do";
			var params = {type:'page',title:'房间不可售原因'
						,id:'room_nonesale_roomreason'
						,content:url
						,data:sendData
						,isUpdate:true
						,width:650
						,show:function()
						{
							$.fn.alertDialogShow(params.id);
						}};
			$.fn.alertDialog(params);
		});
	};

	/**
	*@描述 清空条件
	*/
	var clearConditionBtn = function()
	{
		$("#rooms_btn_clear").unbind("click");
		$("#rooms_btn_clear").bind("click",function(event)
		{
			typeValues = null;
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
	*@描述 设置 刷新后房类数量
	*/
	var setRoomTypeChecked = function()
	{
		$("#rooms_roomchoose_typevalues").val(typeValues);
	};

	//初始操作
	$(function()
	{
		initPanel();//初始化面板
		setRoomStat();//房态选择
		var checkedObj = $("[name='roomStat']:checked");
		checkedObj.trigger('click');
		checkedObj.trigger('click');
		initStartDate();//开始日期
		roomInDBclick();//在住双击
		openNoSalePannel();//不可用双击
		clearConditionBtn();//清空
		setRoomTypeChecked();//设置房类数量和类型
		//设置房类 和房型的值 rooms_hroomchr_value rooms_features_veiw
		setTimeout(function()
		{
			roomUtil.SetDefultInputValue('rooms_roomchoose','rooms_types_value');
			roomUtil.SetDefultInputValue('rooms_features_veiw','rooms_hroomchr_value');
		},700);
	});
});