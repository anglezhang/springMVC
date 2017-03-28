define(function(require, exports, module)
{
	var t = require("../frame/Tools");
	var roomUtil = require("../rooms/plugins/RoomsUtils"); 
	var basePath = $("#path").val(); 
	var ajaxM = require("../frame/AjaxManager");
	var DataT = require("../frame/DateTool");
	var DateUtil = require('../frame/DateTool');
	var view = null;

	/**
	*@描述 是否刷新主界面
	*@param isf true 刷新 false  不刷新
	*/
	var isRefreshMainPage = function(isf)
	{
		$("#rooms_btn_refresh").attr("isCommit",isf);
	}

	/**
	*@描述 查询
	*@param flexGrid grid对象
	*/
	var queryBtnClick = function(flexGrid)
	{
		$("#fixfrozen_btn_query").unbind("click");
		$("#fixfrozen_btn_query").bind("click",function(event)
		{
			
			var roomId = $("#fixfrozen_roomid").val();
			if(t.isEmpty(roomId))
				roomId = "null";
			var type = $("[name='fixfrozeninf_type']:checked").val();
			var active = $("[name='fixfrozeninf_active']:checked").val();
			var startDate = $("#fixfrozeninf_startdate").val();
			var endDate = $("#fixfrozeninf_enddate").val();
			var url = basePath + "/roomFixFrozen/updateFixFrozenInfo/"
					+ roomId + "/" 
					+ type + "/"
					+ active + "/"
					+ startDate + "/"
					+ endDate;
			ajaxM.ajaxAction($,url,function(listData)
			{
				var array = createListDeta(listData);
				view = roomUtil.UpdateWijomFlexGrid(flexGrid,array);
				view.moveCurrentToFirst();
			});
			//closeWindows();
		});
	};

	/**
	*@描述 关闭窗口
	*/
	var closeWindows = function()
	{
		$("#closeWindowsrooms_fixfrozen_info").trigger('click');
	};

	/**
	*@描述 退出按钮
	*/
	var cancleBtnClick = function()
	{
		$("#fixfrozen_btn_exit").unbind("click");
		$("#fixfrozen_btn_exit").bind("click",function()
		{
			closeWindows();
		});
	};

	/**
	*@描述 取消维修
	*/
	var cancleFixBtn = function(flexGrid)
	{
		$("#fixfrozen_btn_cancle").unbind("click");
		$("#fixfrozen_btn_cancle").bind("click",function(event)
		{
			var ableStat = $(this).attr("able-stat");
			if(ableStat==='true')
			{
				for(var i=0;i<flexGrid.rows.length;i++)
				{
					var row = flexGrid.rows[i];
					if(row.isSelected)
					{
						var index = i;
						var roomData = row['_data'];
						var colIndex = i + 1;
						var fgRow = flexGrid.rows[index];
						fgRow.dataItem.index = (((colIndex.toString()).indexOf("*")==-1) ? ("*"+colIndex) : (colIndex));
						fgRow.dataItem.status = 1;
						fgRow.cssClass="select-row-yellow";
						setUsefull(true);
					}
					
				}
			}
		});
	};

	/**
	*@描述 处理list列表数据
	*/
	var createListDeta = function(listData)
	{
		var array = new Array();//grid数据
		for(var i=0;i<listData.length;i++)
		{
			var data = listData[i];
			var obj = new Object();
			for(k in data)
			{
				/**解决bug#1906   思小鹏**/
				var val = data[k] ;
				if("leaveDate" == k)
					obj[k] = val ? val.substring(0,10) : "";
				else if("reachDate" == k)
					obj[k] = val ? val.substring(0,10) : "";
				else if("operTime" == k)
					obj[k] = val ? val.substring(0,16) : "";
				else
					obj[k] = val;
				/**解决bug#1906   思小鹏**/
			}
			obj['index'] = i+1;
			array.push(obj);
		}
		return array;
	};
	/**
	*@描述
	*/
	/**
	*初始化grid list 必须有vijim环境
	*/
	var initGridList = function(divId,listData)
	{
		
		var active = $("#fixfrozeninf_type_active").val();
		active = active - 0;
		var isColReadonly = true;
		if(active==0)
		{
			isColReadonly = false;
		}
		var array = createListDeta(listData);
		//设置列明
		var clos = [
					{header:"序号",binding:"index",cssClass:"fg_column_readOnly",name:"h_index", align:"center", minWidth:40,width:60 ,isReadOnly:true}         
					,{header:'房号',binding:'roomId',width : 60,minWidth:60,isReadOnly : true}
					,{header:'开始日期',binding:'reachDate',width : 108,minWidth:108,align:"center",format : 'yyyy-MM-dd',isReadOnly : isColReadonly}
					,{header:'结束日期',binding:'leaveDate',width : 108,minWidth:108,align:"center",format : 'yyyy-MM-dd',isReadOnly : isColReadonly}
					,{header:'维修原因',binding:'note',align:"left",width : '*',isReadOnly : isColReadonly}
					,{header:'操作时间',binding:'operTime',width : 108,minWidth:108,align:"center",format : 'yyyy-MM-dd hh:mm',isReadOnly : true}
					,{header:'操作员',binding:'operId',width : '*',align:"left",isReadOnly : true}];
		view = new wijmo.collections.CollectionView(array);
		//view.pageSize = 10;
		var grid = new wijmo.grid.FlexGrid('#' + divId, {
			autoGenerateColumns : false,
			allowMerging : wijmo.grid.AllowMerging.All,
			isReadOnly : false,
			selectionMode : wijmo.grid.SelectionMode.ListBox,
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			columns :clos,itemsSource : view});
		//限制日期格式
		roomUtil.SetUtilGridDataFmt(grid.columns.getColumn('reachDate'),'reachDate','yyyy-mm-dd');
		roomUtil.SetUtilGridDataFmt(grid.columns.getColumn('leaveDate'),'leaveDate','yyyy-mm-dd');
		view.trackChanges = true;
		dataItemCheck(grid);//监控改变
		cancleFixBtn(grid);//取消
		//selectOnchange(grid);//监听是否修改
		confirmBtnClick();//监听确定
		queryBtnClick(grid);//查询按钮
	};

	/**
	*@描述 数据修改事件监听
	*/
	var selectOnchange = function(grid)
	{
		grid.selectionChanged.addHandler(function(s,e)
		{
			var cbtnObj = $("#fixfrozen_btn_confirm");
			var ableStat = cbtnObj.attr("able-stat");
			var dropBtnobj = $("#fixfrozen_btn_drop");
			if(ableStat==='true')
			{
				return;
			}else
			{
				cbtnObj.attr("able-stat",'true');
				cbtnObj.removeAttr('style');
				dropBtnobj.attr("able-stat",'true');
				dropBtnobj.removeAttr('style');
			}
			
		});
	};

	/**
	*@描述 设置确定按钮可用 or 不可用
	*/
	var setUsefull = function(isUse)
	{
		var cbtnObj = $("#fixfrozen_btn_confirm");
		var dropObj = $("#fixfrozen_btn_drop");
		if(isUse)
		{
			cbtnObj.attr("able-stat",'true');
			cbtnObj.removeAttr('style');
			dropObj.attr("able-stat",'true');
			dropObj.removeAttr('style');
			
		}else
		{
			cbtnObj.attr("style","color: grey;cursor:not-allowed;");
			cbtnObj.attr("able-stat",'false');
			dropObj.attr("style","color: grey;cursor:not-allowed;");
			dropObj.attr("able-stat",'false');
		}
	};
	
	/**
	*@描述修改数据校验
	*@param editArray 修改的数据
	*/
	var dataItemCheck = function(flexGrid)
	{
		//初始数据保存
		var initialCellVal;
		flexGrid.beginningEdit.addHandler(function(s,e)
		{
			initialCellVal = s.getCellData(e.row, e.col, true);
		});
		flexGrid.cellEditEnded.addHandler(function(s,e)
		{
			//设置样式 判断日期合法性
			var fgRow = s.rows[e.row];
			var fgCol = s.columns[e.col];
			var finalCellVal = s.getCellData(e.row, e.col, true);
			var index = fgRow.dataItem.index;
			//判断是否改变
			if(index && finalCellVal!=initialCellVal)
			{
				fgRow.dataItem.index = (((index.toString()).indexOf("#")==-1) ? ("#"+index) : (index)) ;
				fgRow.cssClass="eidt-row-blue";
				var startDate = t.ReplaceAll(fgRow.dataItem.reachDate,"-","") + "000000";
				var endDate = t.ReplaceAll(fgRow.dataItem.leaveDate,"-","")  + "000000";
				var sDate = new DataT(new Date()).setDate(startDate);
				var eDate = new DataT(new Date()).setDate(endDate);
				//初始开始日期
				var initBeginDate = $("#roomssearchvo_startdate").val();
				initBeginDate = t.ReplaceAll(initBeginDate,"-","") + "000000";
				var intdDate = new DataT(new Date()).setDate(initBeginDate);
				if(eDate.getTime() < sDate.getTime())
				{
					var params = {type:'error',content:'开始日期不能晚于结束日期',title:'警告提示'
			                ,id:'fixfrozen_dataerror2_message'};
						$.fn.alertDialog(params); 
					    $.fn.alertDialogShow(params.id);
					    setUsefull(false);
					return;
				}
				setUsefull(true);
			}
			
		});
	};

	/**
	*@描述 点击确定按钮
	*/
	var confirmBtnClick = function()
	{
		$("#fixfrozen_btn_confirm").unbind("click");
		$("#fixfrozen_btn_confirm").bind("click",function(event)
		{
			var ableStat = $(this).attr("able-stat");
			if(ableStat==='true')
			{
				var editArray = view.itemsEdited;
				//可以修改时间 日期 和取消
				var editRoomNum = new Array();//修改
				var allArray = view._pgView;
				var cancleIds = "";
				//取消
				for(var i=0;i<allArray.length;i++)
				{
					var rowData = allArray[i];
					var status = rowData['status'];
					if(status==1)
					{
						var dataId = rowData['id'];
						cancleIds += dataId + ",";
						var reachDate = rowData['reachDate'];
						var leaveDate = rowData['leaveDate'];
						var note = rowData['note'];
						var id = rowData['id'];
						var eidtObj = {'reachDate':reachDate,'leaveDate':leaveDate,'note':note,'id':id};
						editRoomNum.push(eidtObj);
					}
				}
				//非空判断
				if(!t.isEmpty(cancleIds))
				{
					cancleIds = cancleIds.substring(0,cancleIds.length-1);
				}else
				{
					cancleIds = "-1";
				}
				var url = basePath + "/roomFixFrozen/editFixfrozen/" + cancleIds;
				ajaxM.ajaxAction($,url,function(data)
				{
					if(data)
					{
						isRefreshMainPage('true');
						$("#fixfrozen_btn_query").trigger('click');
					}
					
				},{"roomNum":t.jsonToStr(editRoomNum)});
			}
			
		});
		
	};

	/**
	*@描述 监听快速查询
	*/
	var quicklyQuery = function()
	{
		$("#fixfrozen_roomid").unbind('keyup');
		$("#fixfrozen_roomid").bind("keyup",function(event)
		{
			if(event.keyCode==13)
			{
				$("#fixfrozen_btn_query").trigger('click');
			}
		});
	};

	/**
	*@描述 维修 和 有效 状态切换
	*/
	var refreshPage = function()
	{
		$("[name='fixfrozeninf_type'],[name='fixfrozeninf_active']").unbind('click');
		$("[name='fixfrozeninf_type'],[name='fixfrozeninf_active']").bind('click',function(event)
		{
			$("#fixfrozen_btn_query").trigger('click');
			var type = $(this).val();
			if(type==='3')
			{
				$("#fixfrozen_btn_cancle").html("取消冻结");
			}
			else if(type==='2')
			{
				$("#fixfrozen_btn_cancle").html("取消维修");
			}
		});
	};
	
	/**
	*@描述 ：打印按钮
	*/
	var printBtnClick = function()
	{
		$("#fixfrozen_btn_print").off("click");
		$("#fixfrozen_btn_print").on("click",function(){
			window.print();
		});
	};
	

	/**
	*@描述 ：退出按钮
	*/
	var exitBtnClick = function()
	{
		$("#fixfrozen_btn_exit").unbind("click");
		$("#fixfrozen_btn_exit").bind("click",function(event)
		{
			closeWindows();
		});
	};

	/**
	*@描述 放弃按钮
	*/
	var dropBtnClick = function()
	{
		$("#fixfrozen_btn_drop").unbind("click");
		$("#fixfrozen_btn_drop").bind("click",function(event)
		{
			var ableStat = $(this).attr("able-stat");
			if(ableStat==='false')
			{
				return;
			}else
			{
				$("#fixfrozen_btn_query").trigger('click');
			}
			
		});
	};

	/**
	*@描述 点击清空按钮
	*/
	var clearBtnClick = function()
	{
		$("#fixfrozen_btn_clear").unbind('click');
		$("#fixfrozen_btn_clear").bind("click",function(event)
		{
			$("#fixfrozen_roomid").val('');
			var startDate = $("#fixfrozeninf_remeber_startdate").val();
			$("#fixfrozeninf_startdate").val(startDate);
			var endDate = $("#fixfrozeninf_remeber_enddate").val();
			$("#fixfrozeninf_enddate").val(endDate);
		});
	};

	/**
	*@描述 开始日期设置的验证格式
	*/
	var setStartDateClick = function()
	{
		$("#fixfrozeninf_startdate").unbind("click");
		$("#fixfrozeninf_startdate").bind("click",function(event)
		{
			//去当前状态
			var active = $("#fixfrozeninf_type_active").val();
			active = active - 0;
			//有效
			if(active==0)
			{
				var nowDate = new Date();
				var beginDate = $("#fixfrozeninf_begindate").val();
				beginDate = t.ReplaceAll(beginDate,"-","") + "0000";
				var bDate = new DateUtil(nowDate).setDate(beginDate);
				bDate.setDate(bDate.getDate());
				endDatelimt = new DateUtil(bDate).format('yyyy-MM-dd');
				//调用日期控件
				WdatePicker({minDate:endDatelimt});
			}else
			{
				WdatePicker();
			}
		});
	};

	/**
	*@描述 结束日期验证格式 (动态根据开始日期的变更而变更验证规则)
	*/
	var setEndDateClick = function()
	{
		$("#fixfrozeninf_enddate").unbind('click');
		$("#fixfrozeninf_enddate").bind("click",function(event)
		{
			var nowDate = new Date();
			var beginDate = $("#fixfrozeninf_startdate").val();
			beginDate = t.ReplaceAll(beginDate,"-","") + "0000";
			var bDate = new DateUtil(nowDate).setDate(beginDate);
			bDate.setDate(bDate.getDate()+1);
			endDatelimt = new DateUtil(bDate).format('yyyy-MM-dd');
			//调用日期控件
			WdatePicker({minDate:endDatelimt});
		});
	};
	//初始化动作
	$(function()
	{
		cancleBtnClick();//取消按钮
		/*var listData = $("#fixfrozen_info_rooms").attr("data-list");
		t.debug("listData = " + listData);*/
		initGridList('fixfrozen_info_rooms',dataList);
		refreshPage();//刷新页面
		quicklyQuery();//快速查询
		exitBtnClick();//退出
		dropBtnClick();//放弃
		setStartDateClick();//开始日期
		setEndDateClick();//结束日期
		clearBtnClick();//清空按钮
		printBtnClick();//打印按钮
		//设置 取消
		var active = $("#fixfrozeninf_type_active").val();
		active = active - 0;
		var cancleBtnObj = $("#fixfrozen_btn_cancle");
		if(active==1)
		{
			cancleBtnObj.attr("able-stat",'false');
			cancleBtnObj.attr("style","color: grey;cursor:not-allowed;");
		}else
		{
			cancleBtnObj.attr("able-stat",'true');
		}
		//判断是否刷新界面
		$("#closeWindowsrooms_fixfrozen_info").bind("click",function(event)
		{
			var refreshBtn = $("#rooms_btn_refresh");
			var isCommit = refreshBtn.attr("isCommit");
			if(!t.isEmpty(isCommit) && isCommit==='true')
			{
				refreshBtn.trigger('click');
			}
		});
	});
});