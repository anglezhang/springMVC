var flexGrid ;
var view ;
var status_search ;
var initialCellVal;
function fillWorkGroupTable(listJson , _status_search){
	status_search = _status_search;
	var data = listJson ;
	var count = data.length;
	// 列表初始化数组
	var array = [];
	for ( var i = 0; i < count; i++) {
		array.push({
			"index": i+1,
			"groupName": data[i].workGroup.groupName,
			"memo": data[i].workGroup.memo,
			"ps": "点击设置",
			"status": data[i].workGroup.status,
			"groupId": data[i].workGroup.groupId,
			"relFlag": data[i].relFlag,
			"id": data[i].workGroup.groupId
		});
	}
	// create CollectionView on the data (to get events)
	view = new wijmo.collections.CollectionView(array);
	view.trackChanges = true;
	// initialize the grid
	flexGrid = new wijmo.grid.FlexGrid('#theGrid', {
			autoGenerateColumns: false,
			autoClipboard:true,
			selectionMode : wijmo.grid.SelectionMode.ListBox,
			columns:[
		        { header:"id",binding:"id",name:"id" , isReadOnly:true,visible:false}, 
			    { header:"序号",binding:"index",name:"h_index", align:"center",cssClass:"fg_column_readOnly",isReadOnly:true, minWidth:50,width:50},           
			    { header:"代码",binding:"groupId",name:"h_groupId", minWidth:50 , width:50, align:"center",isReadOnly:!status_search , mask:"AAA"},
			    { header:"工作组名称", binding:"groupName",name:"h_groupName" , align:"left", minWidth:40,width:218,isReadOnly:!status_search},
			    { header:"权限设置", binding:"ps",name:"h_ps" , align:"center", minWidth:80,width:120,isReadOnly:true}, 
			    { header:"备注说明", binding:"memo",name:"h_memo", align:"left", minWidth:80 ,width:300,isReadOnly:!status_search}, 
			    { header:"relFlag",binding:"relFlag",name:"relFlag" , isReadOnly:true,visible:false}
			    //{ header:"状态",binding:"status", name:"h_status", align:"center",cssClass:"fg_column_readOnly_cell" , minWidth:40,width:60,isReadOnly:true}
			],
			allowAddNew: status_search,
			//allowDelete: status_search,
			// 拖拽
			allowDragging:wijmo.grid.AllowDragging.None,
			// resize
			//allowResizing:wijmo.grid.AllowResizing.None,
			// 设置列/行的显示
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			itemsSource : view
		}
	);
	// 有效时  禁止默认选中第一行
	flexGrid.selection =( status_search ? new wijmo.grid.CellRange(-1,-1,-1,-1) : new wijmo.grid.CellRange(0,0,0,0));
	// 单元格编辑模式判定
	flexGrid.beginningEdit.addHandler(function(s, e){
		var fgCol = e.panel.columns[e.col];
		var fgRow = e.panel.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
		// 该列不允许修改
		if(fgCol.binding == "groupId" && (dataItem && dataItem.index)){
			// 修改状态时
			var index = dataItem.index.toString() ;
			index = index.replace(/#/g,"") ;
			if(parseInt(index) > 0 && dataItem.relFlag && dataItem.relFlag != 0)
				e.cancel = true;
		}
		initialCellVal = s.getCellData(e.row, e.col, true);
	});
	flexGrid.cellEditEnded.addHandler(function(s, e){
		var fgRow = s.rows[e.row] ;
		var fgCol = s.columns[e.col] ;
		var finalCellVal = s.getCellData(e.row, e.col, true);
		var indexVal = fgRow.dataItem.index ;
		// 校验代码唯一性
		if(fgCol.binding == "groupId" && !isNull(finalCellVal)){
			$.ajax({
				url:"/workGroup/findBy.do",
				type:"post",
				data:{id : fgRow.dataItem.id , codeId : finalCellVal.toUpperCase()},
				dataType:"json",
				success:function(data){
					if(!data.success){
						s.setCellData(e.row,fgCol.binding,initialCellVal, true);
						altWaringMsg('代码重复，请重新编辑',function(){
							goShowButton(false, false);
					    	flexGrid.startEditing(true , e.row , e.col);
						    e.cancel = true;
						});
					}
				},
				error:function(err){
					altEerrMsg('出现异常',function(){});
				}
			});
		}
		// 判断修改状态
		if(indexVal && finalCellVal != initialCellVal){
			// 设置样式
			fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true, true);
		}else if(!indexVal || (isNull(initialCellVal)&&!isNull(finalCellVal))){
			fgRow.dataItem.index = "#";
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true, true);
		}
		
	});
	//选中区域是否包含 不可修改的行或者空行  true 包含 否则不包含
	flexGrid.selectionChanged.addHandler(function(s, e){
		if(status_search){
			var temp = true ;
			var selection = getSelectionRanges();
			if(selection.length == 0){
				temp = false ;
			}else{
				for(var i= 0;i<selection.length;i++){
					var dataItem = selection[i].dataItem ;
					if((!dataItem || (dataItem.relFlag && dataItem.relFlag != 0) ||(dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ) )){
						temp =false ;
						break ;
					}
				}
			}
			goShowButton_2(temp);
			sortEndShowStartCss();
		}
	});
	// 排序结束  还原排序之前的样式
	flexGrid.onSortedColumn=function(e){
		sortEndShowStartCss();
	} ;
	flexGrid.itemFormatter = function(panel, r, c, cell) {
		if (panel.cellType == wijmo.grid.CellType.Cell) {
			var col = panel.columns[c];
			if (col.name == 'h_ps'){
				cell.style.color = "blue" ;
				cell.style.textDecoration = "underline" ;
			}
		}
	}; 
	var host = flexGrid.hostElement;
    //handle the click event
    host.addEventListener('click', function (e) {
        var ht = flexGrid.hitTest(e);
        if(ht && ht.panel && ht.panel.rows){
            var row = ht.panel.rows[ht.range.row];
            var col = ht.panel.columns[ht.range.col];
            if(row.isReadOnly == true){}else{
            	var dataItem = row.dataItem ;
                //check if cell is clicked
                if (dataItem && status_search && ht.cellType == wijmo.grid.CellType.Cell && col.binding=='ps') {
                	if(isNull(dataItem.id)){}else{
                		initWorkGroupAuthList(ht.range.row , dataItem.groupId) ;
                	}
            	}
            }
        }
   	});
}
function initWorkGroupAuthList(row , groupId){
	$("#workGroupAuthListFrame").prop("src" , "/workGroup/workGroupAuthList.do?groupId="+groupId) ;
}
function showWorkGroupAuthList(){
	$(".alertDivBg").fadeIn();
	$("#workGroupAuthListAlert").fadeIn();
}
function goOk(){
		outCursor();

		var addOrEditItemsArray = [];
		// 删除
		var delIds = $("#delIds").val();
		
		var removeItems = view.itemsRemoved;
		if(removeItems && removeItems.length > 0){
			for(var i=0 ; i < removeItems.length ; i++){
				delIds+=(","+removeItems[i].id) ;
			}
		}
		
		// 添加
		var addItems = view.itemsAdded;
		if(addItems && addItems.length > 0){
			for(var i=0 ; i < addItems.length ; i++){
				if($.isEmptyObject(addItems[i]))continue;
				var groupId = addItems[i].groupId ;
				var groupName = addItems[i].groupName ;
				var memo = addItems[i].memo ;
				
				var msg = "" ;
				if(isNull(groupId)){
					msg+='代码,';
				}
				if(isNull(groupName)){
					msg+='工作组名称,';
				}
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}
				addOrEditItemsArray.push({
					"groupId": groupId,
					"groupName": groupName,
					"memo": memo,
					"status": 0
				});
			}
		}
		
		// 修改
		var editItems = view.itemsEdited;
		if(editItems && editItems.length > 0){
			for(var i=0 ; i < editItems.length ; i++){
				var groupId = editItems[i].groupId ;
				var groupName = editItems[i].groupName ;
				var memo = editItems[i].memo;
				
				var msg = "" ;
				if(isNull(groupId)){
					msg+='代码,';
				}
				if(isNull(groupName)){
					msg+='工作组名称,';
				}
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}
				addOrEditItemsArray.push({
					"groupId": groupId,
					"groupName": groupName,
					"memo": memo,
					"status": 0
				});
			}
		}
		
		if(isNull(delIds) && (addOrEditItemsArray.length==0)){
			return;
		}else{
			var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
			$.ajax({
				url:"/workGroup/saveOrUpdateOrDel.do",
				type:"post",
				data:{params : addOrEditItemsData , delIds : delIds.replace(/undefined/g, "") },
				dataType:"json",
				success:function(data){
					if(data.success){
						getSearch();
					}else{
						altInfMsg('正在夜审中',function(){});
					}
				},
				error:function(err){
					altEerrMsg('出现异常',function(){});
				}
			});
		}
	}
//显示按钮区域
/**
 * bool1 确定，放弃按钮组  显示(true)/置灰(false)
 * bool2 取消按钮组  显示(true)/置灰(false)
 */
function goShowButton(bool1 , bool2){
	goShowButton_1(bool1);
	goShowButton_2(bool2);
}
function goShowButton_1(bool1){
	if(bool1){
		$("a#ok,a#cancel").css({"cursor":"pointer","color":"inherit"});
		$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/workGroup/updateStatus.do');" );
		$("#cancel").prop("href","javascript:giveUpEdit();");
	}else{
		$("a#ok,a#cancel").css({"cursor":"not-allowed","color":"grey"});
		$("#ok,#cancel").prop("href","javascript:;");
	}
}
function goShowButton_2(bool2){
	if(bool2){
		$("a#del").css({"cursor":"pointer","color":"inherit"});
		$("#del").prop("href","javascript:goDel();");
	}else{
		$("a#del").css({"cursor":"not-allowed","color":"grey"});
		$("#del").prop("href","javascript:;");
	}
}
//根据id关闭弹出窗口
function closeDiv(alertId){
	$("#"+alertId).fadeOut(function(){
		$(".alertDivBg").fadeOut(function(){
			if(alertId == "workGroupAuthListAlert")
				$("#workGroupAuthListFrame").prop("src","");
		});
	});
}