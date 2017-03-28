define(function(require, exports, module)
{
	var t = require("../../frame/Tools");
	var basePath = $("#path").val();
	var ajaxM = require("../../frame/AjaxManager");
	var template = require("template");

	template.helper('setSex',function(data)
	{
		if(data==='0261')
		{
			return '女';
		}else
		{
			return '男';
		}
	});

	/**
	*@描述 初始化gird
	*@param  gridId id
	*@param initDate初始化数据
	*/
	var initGrid = function(gridId,initDate,gridName,girdBindId)
	{
		var array = new Array();//grid数据
		for(var i=0;i<initDate.length;i++)
		{
			var data = initDate[i];
			var obj = new Object();
			for(k in data)
			{
				obj[k] = data[k];

			}
			obj['index'] = i+1;
			array.push(obj);
		}
		var clos = new Array();
		for(var i=0;i<girdBindId.length;i++)
		{
			var listline = {header:gridName[i],binding:girdBindId[i],align:"left",width : '*',isReadOnly:true};
			clos.push(listline);
		}
		var view = new wijmo.collections.CollectionView(array);
		//view.pageSize = 10;
		var grid = new wijmo.grid.FlexGrid('#' + gridId, {
			autoGenerateColumns : false,
			allowMerging : wijmo.grid.AllowMerging.All,
			isReadOnly : false,
			selectionMode : wijmo.grid.SelectionMode.RowRange,
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			columns :clos,itemsSource : view});
		view.trackChanges = true;
		$("#" + gridId).show();
	}

	/**
	*@移动选中 根据移动操作选中客户信息
	*@param isUp true 上 false 下
	*/
	var selectedFguestInfo = function(divId,isUp)
	{
		var guestInf = $("[data-selected='true']");
		var chooseGuest = null;
		if(isUp)
		{
			chooseGuest = guestInf.prev();
		}else
		{
			chooseGuest = guestInf.next();
		}
		if(t.isEmpty(chooseGuest.html()))
		{
			return;
		}
		$("#"+divId).find(".tableMain").find("tbody").find("tr").attr('data-selected','false');
		$("#"+divId).find(".tableMain").find("tbody").find("tr").removeClass('fguestTtableSlected');
		$("#"+divId).find(".tableMain").find("tbody").find("tr").removeAttr('style');
		chooseGuest.attr("data-selected",'true');
		chooseGuest.addClass('fguestTtableSlected');
		chooseGuest.attr("style","background-color:#b6d1a6;");
	}

	/**
	*@描述 初始化搜索table
	*/
	var initSearchTable = function(tableDta,templetId,divId)
	{
		var tableDiv = $("#"+divId);
		tableDiv.attr("is-show",'false');
		tableDiv.empty();
		var tableHtml = template(templetId,tableDta);
		tableDiv.append(tableHtml);
		tableDiv.show();
		if(tableDta['list'].length==0)
		{
			tableDiv.attr("is-show",'true');
			return null;
		}
		
		$("#"+divId).find(".tableMain").find("tbody").find("tr").each(function(index, el) 
		{
			$(el).unbind("dblclick");	
			$(el).bind("dblclick",function(event)
			{
				var thisLineDate = tableDta.list[index];
				for(k in thisLineDate)
				{
					$("[fguest-name='" + k  + "']").val(t.ReplaceAll(thisLineDate[k]," ",""));
				}
				$('[fguest-gstid]').attr('fguest-gstid',thisLineDate['gstId']);
				tableDiv.hide();
				tableDiv.empty();
			});
			//上下按钮
			$("#rooms_openfguestsearch").unbind("keyup");
			$("#rooms_openfguestsearch").bind("keyup",function(event)
			{
				var key = event.keyCode;
				switch(key)
				{
					case 38://上
						selectedFguestInfo(divId,true);
						break;
					case 40://下
						selectedFguestInfo(divId,false);
					 	break;
					case 13:// enter
						tableDiv.hide();
						tableDiv.attr("is-show",'true');
						var selectedGuest = $("[data-selected='true']");
						selectedGuest.trigger('dblclick');
						break;
				}
			});
		});
	};

	/**
	* @描述 在输入框按回车按钮
	*/
	var enterPress = function()
	{
		//监听的输入框id
		var inputTxts = "#roomfguest_chinaesename"
						+",#roomfguest_mobile"
						+",#roomfguest_membercard"
						+",#roomfguest_englishname"
						+",#roomfguest_idcard"
						+",#roomfguest_vipno";
		$(inputTxts)
			.unbind('keyup');
		$(inputTxts)
			.bind('keyup',function(event)
			{	
				if(event.keyCode==13)
				{
					var divShow = $("#room_fguest_list").attr("is-show");
					if(divShow==='true')
					{
						var paramName = $(this).attr("name");
						var paramValue = $(this).val();
						if(t.isEmpty(paramValue))
						{
							return;
						}
						var fguestVo = new Object();
						fguestVo[paramName] = paramValue;
						var url = basePath + "/fguest/getFguest.do";
						ajaxM.ajaxAction($,url,function(data)
						{
							//如果数据为空 则不处理
							var tableData = {'list':data};
							initSearchTable(tableData,'rooms_fguest_searchtable','room_fguest_list');
						},fguestVo);
					}
					
				}
			});
		$(inputTxts).unbind('click');
		$(inputTxts).bind("click",function()
		{
			var gridDiv = $("#room_fguest_list");
			gridDiv.hide();
		});
	};

	/**
	*@描述 设置模块选中统计类型选中颜色
	*/
	var setTypeColor = function()
	{
		$("[is-selected]").unbind('click');
		$("[is-selected]").bind('click',function(event)
		{
			$("[is-selected]").attr('is-selected',false);
			$("[is-selected]").css("color","#0025ca");
			$(this).attr('is-selected',true);
			$(this).css('color','#12aa53');
		});
		$("[is-selected='true']").css('color','#12AA53');
	};

	/**
	*@描述 点击取消按钮
	*/
	var exitBtn = function()
	{
		$("#room_fguestcancle_btn").unbind('click');
		$("#room_fguestcancle_btn").bind("click",function(event)
		{
			$("#closeWindowsrooms_openfguestsearch").trigger('click');
		});
	};
	/**
	*@描述 熟客查询 
	*/
	$(function()
	{
		setTimeout(function()
		{
			exitBtn();//退出按钮
			enterPress();//回车
			setTypeColor();//选中统计类型样式
		},100);
	});
});