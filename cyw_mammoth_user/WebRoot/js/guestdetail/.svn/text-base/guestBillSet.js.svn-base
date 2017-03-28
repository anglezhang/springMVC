define(function (require, exports, module) 
{
	var t = require("../frame/Tools");
	var ajaxM = require("../frame/AjaxManager");
	var template = require("template"); 
	var abillArray = new Array();//A账信息
	var bbillArray = new Array();//b账务信息
	var basePath = $("#path").val();
	/**
	*@描述 点击后 添加背景色
	*/
	var setLiColor = function()
	{
		$("[guest-bill-a],[guest-bill-b]").unbind("click");
		$("[guest-bill-a],[guest-bill-b]").bind("click",function(event)
		{
			var tag = $(this).attr('tag');
			$(this).css("background",tag=='false'?'#96bc5b':'white');
			$(this).attr('tag',tag=='false'?'true':'false');
		});
	};

	/**
	*@描述 移动
	*/
	var moveBillInfo = function(ulId,data)
	{
		$("[guest-bill-a],[guest-bill-b]").unbind("dblclick");
		$("[guest-bill-a],[guest-bill-b]").bind("dblclick",function(event)
		{
			var id = $(this).attr('guest-bill-a');
			var name = $(this).html();
			var type='B';
			var ulId = 'guest_bill_set_consumeB_ID';
			if(t.isEmpty(id))
			{
				id = $(this).attr('guest-bill-b');
				ulId = 'guest_bill_setconsumeA_ID';
				type = 'A';
			}
			var data = {'type':type,'id':id,'codeNamec':name};
			var ulObj = $("#" + ulId);
			var ulHtml = template('guest_bill_billoption',data);
			ulObj.append(ulHtml);
			$(this).remove();
		});
		
	}

	/**
	*@描述 移动全部
	*/
	var moveAllBtn = function()
	{
		$("#guest_bill_goleft,#guest_bill_goright,#guest_bill_goleftAll,#guest_bill_gorightAll").unbind('click');
		$("#guest_bill_goleft,#guest_bill_goright,#guest_bill_goleftAll,#guest_bill_gorightAll").bind('click',function(event)
		{
			var id = $(this).prop('id');
			var ulId = null;
			var type='A';
			var node = new Array();//保存节点
			if(id==='guest_bill_goleft' || id==='guest_bill_goleftAll')
			{
				ulId = 'guest_bill_set_consumeB_ID';
				type='B';
			}else
			{
				ulId = 'guest_bill_setconsumeA_ID';
			}
			//移动节点
			if(type==='B')
			{
				$("[guest-bill-a]").each(function(index, el) 
				{
					var tag = id==='guest_bill_goleftAll' ? 'true' : $(el).attr("tag");
					if(tag==='true')
					{
						var nodeId = $(el).attr("guest-bill-a");
						var name = $(el).html();
						var data = {'type':type,'id':nodeId,'codeNamec':name};
						node.push(data);
						$(el).remove();
					}
				});
			}else
			{
				$("[guest-bill-b]").each(function(index, el) 
				{
					var tag = id==='guest_bill_gorightAll' ? 'true' : $(el).attr("tag");
					if(tag==='true')
					{
						var nodeId = $(el).attr("guest-bill-a");
						var name = $(el).html();
						var data = {'type':type,'id':nodeId,'codeNamec':name};
						node.push(data);
						$(el).remove();
					}
				});
			}
			// 循环添加
			for(var i=0;i<node.length;i++)
			{
				var data = node[i];
				var ulObj = $("#" + ulId);
				var ulHtml = template('guest_bill_billoption',data);
				ulObj.append(ulHtml);
			}
			setLiColor();//点击后色彩
		});
	};

	var serEndDate = function()
	{
		$("#guest_bill_set_split_endDate").unbind('click');
		$("#guest_bill_set_split_endDate").bind('click',function(event)
		{
			//开始日期 终止日期判空
			var startDate = $("#split_startDate").val();//截至日期
			WdatePicker({minDate:startDate});
		});
	};
	/**
	*@描述 设置分账开始日期
	*/
	var setStartDate = function()
	{
		$("#split_startDate").unbind('click');
		$("#split_startDate").bind('click',function(event)
		{
			//开始日期 终止日期判空
			var endDate = $("#guest_bill_set_split_endDate").val();//截至日期
			var reachDate = $("#guestinfo_reachDate").val();
			if(t.isEmpty(endDate))
			{
				WdatePicker({minDate:reachDate});
			}else if(!t.isEmpty(endDate))
			{
				WdatePicker({minDate:reachDate,maxDate:reachDate});
			}
			
		});
	};
	/**
	*@描述 关闭窗口
	*/
	var closeWindow = function()
	{
		$("#guest_bill_set_quit").unbind("click");
		$("#guest_bill_set_quit").bind("click",function()
		{
			$("#closeWindowsguestdetal_subbill_set").trigger('click');
		});
	};

	/**
	*@描述 放弃
	*/
	var dropBtn = function()
	{
		$("#guest_bill_set_drop").unbind('click');
		$("#guest_bill_set_drop").bind('click',function(event)
		{
			var ulAObj = $("#guest_bill_setconsumeA_ID");
			var ulBObj = $("#guest_bill_set_consumeB_ID");
			ulAObj.empty();
			ulBObj.empty();
			for(var i=0;i<abillArray.length;i++)
			{
				var data = abillArray[i];
				var ulHtml = template('guest_bill_billoption',data);
				ulAObj.append(ulHtml);
			}
			for(var i=0;i<bbillArray.length;i++)
			{
				var data = bbillArray[i];
				var ulHtml = template('guest_bill_billoption',data);
				ulBObj.append(ulHtml);
			}
			setLiColor();//点击后色彩
		});
	};

	/**
	*@描述 保存账务信息
	*/
	var cachBillInfo = function()
	{
		$("[guest-bill-a]").each(function(index, el) 
		{
			var type = 'A';
			var id = $(el).attr("guest-bill-a");
			var name = $(el).html();
			var data = {'id':id,'type':type,'codeNamec':name};
			abillArray.push(data);
		});
		$("[guest-bill-b]").each(function(index, el) 
		{
			var type = 'B';
			var id = $(el).attr("guest-bill-b");
			var name = $(el).html();
			var data = {'id':id,'type':type,'codeNamec':name};
			bbillArray.push(data);
		});
	};

	/**
	*@描述 提交
	*/
	var submitBillInfo = function()
	{
		$("#guest_bill_set_confirm").unbind('click');
		$("#guest_bill_set_confirm").bind('click',function(event)
		{
			//判空
			var startDate = $("#split_startDate").val();//起始日期
			var endDate = $("#guest_bill_set_split_endDate").val();//终止日期
			var checkId = $("#guest_bill_set_bpaiedcheckid").val();
			var ifBate = $("#guest_bill_set_if_bate").prop("checked");
			if(t.isEmpty(startDate))
			{
				altWaringMsg("请选择起始日期");
				return;
			}
			if(t.isEmpty(endDate))
			{
				altWaringMsg("请选择终止日期");
				return;
			}
			var ifBateVal = null;
			if(ifBate)
			{
				ifBateVal = 1;
			}
			var billIds = "";
			$("[guest-bill-b]").each(function(index, el)
			{
				billIds += $(el).attr("guest-bill-b") + ",";
			});
			if(billIds.indexOf(",") != -1)
			{
				billIds = billIds.substring(0,billIds.length-1);
			}
			var data = {'checkId':checkId,'cons':billIds,'beginDate':startDate
						,'endDate':endDate};
			var url = basePath + "/guestdetail/saveBpaiedInfo.do";
			ajaxM.ajaxAction($,url,function(data)
			{	
				if(data.msg==='ok')
				{
					var param = {type:'succsess',content:"分账设置成功",title:'消息提示'
		 				,id:'maipage_tips_successmsg',
		 				confirm:function()
		 				{
		 					$("#accountSetId").prop("checked",true);
		 					$("#closeWindowsguestdetal_subbill_set").trigger('click');
		 				}}
					$.fn.alertDialog(param); 
					$.fn.alertDialogShow(param.id);
				}else
				{
					altWaringMsg("分账设置失败");
				}
			},data);
		});
	};

	/**
	*@描述 这是默认日期
	*/
	var setDefultDate = function()
	{
		var startObj = $("#split_startDate");
		var endDateObj = $("#guest_bill_set_split_endDate");
		var startDate = startObj.val();//起始日期
		var endDate = endDateObj.val();//终止日期
		if(t.isEmpty(startDate))
		{
			startObj.val($("#guestinfo_reach_date").val());
		}
		if(t.isEmpty(endDate))
		{
			endDateObj.val($("#guestinfo_leave_date").val());
		}
	};

	$(function()
	{
		setTimeout(function()
		{
			setDefultDate();//日期默认值
			moveBillInfo();//双击移动
			cachBillInfo();//缓存数据
			setLiColor();//点击后色彩
			moveAllBtn();//移动节点
			closeWindow();//退出
			dropBtn();//放弃操作
			setStartDate();//起始日期
			serEndDate();//终止日期
			submitBillInfo();//提交数据
		},150);
	});
});