
var flexGrid ;
var view ;
var status_search ;
var initialCellVal;
//修改列表时 记录行数
var updHfunctionRowArray = [];
// 修改列表时 记录id号
var updHfunctionIds = "" ;
function fillHfunctionTable(listJson , _status_search){
	status_search = _status_search;
	var data = listJson ;
	var count = data.length;
	// 列表初始化数组
	var array = [];
	for ( var i = 0; i < count; i++) {
		array.push({
			"index": i+1,
			"functionName": data[i].hfunction.functionName,
			"cyUrl": data[i].hfunction.cyUrl,
			"ctrlType": data[i].hfunction.ctrlType,
			"functionId": data[i].hfunction.functionId,
			"hfunctionControlVOs": data[i].hfunctionControlVOs,
			"ps": "功能控件",
			"memo": data[i].hfunction.memo,
			"editFlag": data[i].editFlag,
			"id": data[i].hfunction.id
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
	         	{ header:"hfunctionControlVOs",binding:"hfunctionControlVOs",name:"hfunctionControlVOs" ,visible:false}, 
	         	{ header:"ctrlType",binding:"ctrlType",name:"ctrlType" ,visible:false}, 
	         	{ header:"editFlag",binding:"editFlag",name:"editFlag" ,visible:false}, 
			    { header:"序号",binding:"index",name:"h_index", align:"center",cssClass:"fg_column_readOnly",isReadOnly:true, minWidth:40,width:50},           
			    { header:"ID号",binding:"id",name:"h_id", minWidth:40 , width:50, align:"center",isReadOnly:true},
			    { header:"页面名称", binding:"functionName",name:"h_functionName" , align:"left", minWidth:40,width:170,isReadOnly:!status_search},
			    { header:"页面URL", binding:"cyUrl",name:"h_cyUrl" , align:"left", minWidth:80,width:308,isReadOnly:!status_search}, 
			    { header:"功能控件", binding:"ps",name:"h_ps" , align:"center", minWidth:80,width:120,isReadOnly:true}, 
			    { header:"页面ID",binding:"functionId",name:"h_functionId", align:"center", width:80 ,minWidth:120,isReadOnly:true},
			    { header:"备注",binding:"memo",name:"h_memo", align:"left", width:60 ,minWidth:100,isReadOnly:!status_search}
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
	// 有效 禁止默认选中行
	status_search ? flexGrid.select(-1,-1) : flexGrid.select(0,0);
    // 单元格编辑模式判定
	flexGrid.beginningEdit.addHandler(function(s, e){
		var fgCol = s.columns[e.col];
		var fgRow = s.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
        initialCellVal = s.getCellData(e.row, e.col, true);
    });
    
    flexGrid.cellEditEnded.addHandler(function (s, e) {
        var fgRow = s.rows[e.row] ;
        var fgCol = s.columns[e.col];
        var dataItem = fgRow.dataItem ;
		var indexVal = fgRow.dataItem.index ;
        var finalCellVal = s.getCellData(e.row, e.col, true);
        // 判断修改状态
        if(indexVal && (finalCellVal != initialCellVal)){
			// 设置样式
			fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true , true);
		}else if(isNull(initialCellVal)&&!isNull(finalCellVal)){
			fgRow.dataItem.index = "#";
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true , true);
		}
    });
	//选中区域是否包含 非空净房的行或者空行  true 包含 否则不包含
  	flexGrid.selectionChanged.addHandler(function(s, e){
  		if(status_search){
  			var fgRow = s.rows[e.row] ;
  			var fgColumn = s.columns[e.col];
  			var selection = getSelectionRanges();
  	  		// 是否显示 取消，批量改期按钮
      		var temp = true ;
			var selection = getSelectionRanges();
			if(selection.length == 0){
				temp = false ;
			}else{
				for(var i= 0;i<selection.length;i++){
					var dataItem = selection[i].dataItem ;
					if((!dataItem || (dataItem.editFlag && dataItem.editFlag == 0) || (dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ))){
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
                	initHfunctionControlList(ht.range.row , dataItem.id ,dataItem.hfunctionControlVOs) ;
            	}
            }
        }
   	});
}
var _obj ;
//初始化房价方案列表
function initHfunctionControlList(row , id , objJson){
	_obj = objJson;
	$("#currUpdId").val(id);
	$("#currUpdRow").val(row);
	$("#hfunctionControlListFrame").prop("src" , "/hfunction/hfunctionControlList.do") ;
}
//显示房价方案列表
function showHfunctionControlList(){
	$(".alertDivBg").fadeIn();
	$("#hfunctionControlListAlert").fadeIn();
}
//返回当前选中条目的房价方案列表对象
function getFillObj(){
	return _obj ;
}
//根据id关闭弹出窗口
function closeDiv(alertId){
	$("#"+alertId).fadeOut(function(){
		$(".alertDivBg").fadeOut(function(){
			if(alertId == "hfunctionControlListAlert")
				$("#hfunctionControlListFrame").prop("src","");
		});
	});
}
//确定操作
function goOk(){
	outCursor();
	var addOrEditItemsArray = [];
	var hfunction ;
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
			var functionName = addItems[i].functionName ;
			var cyUrl = addItems[i].cyUrl;
			var memo = addItems[i].memo;
			var hfunctionControlVOs = addItems[i].hfunctionControlVOs;
			hfunctionControlVOs = isNull(hfunctionControlVOs) ? (new Array()) : hfunctionControlVOs ;
			var msg = "" ;
			if(isNull(functionName)){
				msg+='页面名称,';
			}
			/*if(isNull(cyUrl)){
				msg+='页面URL,';
			}*/
			
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}
			hfunction = new Object;
			hfunction.functionName = functionName ;
			hfunction.cyUrl = cyUrl ;
			hfunction.memo = memo ;
			
			addOrEditItemsArray.push({
				"editFlag":"a",
				"hfunction": hfunction,
				"hfunctionControlVOs": hfunctionControlVOs
			});
		}
	}
	// 修改房间特征数组   优先级高于view.itemsEdited 
	if(updHfunctionRowArray.length > 0){
		for(var i=0 ; i < updHfunctionRowArray.length ; i++){
			var rowItem = flexGrid.rows[updHfunctionRowArray[i]].dataItem;
			var id = rowItem.id ;
			var functionName = rowItem.functionName ;
			var cyUrl = rowItem.cyUrl;
			var functionId = rowItem.functionId;
			var ctrlType = rowItem.ctrlType;
			var memo = rowItem.memo;
			var hfunctionControlVOs = rowItem.hfunctionControlVOs;
			hfunctionControlVOs = isNull(hfunctionControlVOs) ? (new Array()) : hfunctionControlVOs ;
			var msg = "" ;
			if(isNull(functionName)){
				msg+='页面名称,';
			}
			/*if(isNull(cyUrl)){
				msg+='页面URL,';
			}*/
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}
			hfunction = new Object;
			hfunction.id = id ;
			hfunction.functionName = functionName ;
			hfunction.cyUrl = cyUrl ;
			hfunction.functionId = functionId ;
			hfunction.ctrlType = ctrlType ;
			hfunction.memo = memo ;
			addOrEditItemsArray.push({
				"editFlag":"u",
				"hfunction": hfunction,
				"hfunctionControlVOs": hfunctionControlVOs
			});
		}
	}
	// 修改
	var editItems = view.itemsEdited;
	if(editItems && editItems.length > 0){
		for(var i=0 ; i < editItems.length ; i++){
			var id = editItems[i].id ;
			// 如果该条目之前修改过房间特征  则不重复更新操作
			if(updHfunctionIds.indexOf(id) != -1)
				continue ;
			var functionName = editItems[i].functionName ;
			var cyUrl = editItems[i].cyUrl;
			var functionId = editItems[i].functionId;
			var ctrlType = editItems[i].ctrlType;
			var memo = editItems[i].memo;
			
			var hfunctionControlVOs = editItems[i].hfunctionControlVOs;
			hfunctionControlVOs = isNull(hfunctionControlVOs) ? (new Array()) : hfunctionControlVOs ;
			
			var msg = "" ;
			if(isNull(functionName)){
				msg+='页面名称,';
			}
			/*if(isNull(cyUrl)){
				msg+='页面URL,';
			}*/
			
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}
			hfunction = new Object;
			hfunction.id = id ;
			hfunction.functionName = functionName ;
			hfunction.cyUrl = cyUrl ;
			hfunction.functionId = functionId ;
			hfunction.ctrlType = ctrlType ;
			hfunction.memo = memo ;
			addOrEditItemsArray.push({
				"editFlag":"u",
				"hfunction":hfunction,
				"hfunctionControlVOs":hfunctionControlVOs
			});
		}
	}	
	// 如果无任何操作 则返回	
	if(isNull(delIds) && (addOrEditItemsArray.length==0)){
		getSearch();
		return;
	}else{
		var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
		$.ajax({
			url:"/hfunction/saveOrUpdateOrDel.do",
			type:"post",
			data:{params : addOrEditItemsData , delIds : delIds.replace(/undefined/g, "") },
			dataType:"json",
			success:function(data){
				if(data.success == true){
					getSearch();
				}else if(data.success == false){
					altWaringMsg('正在夜审',function(){});
				}else{
					altEerrMsg('操作失败',function(){});
				}
			},
			error:function(err){
				altEerrMsg('出现异常',function(){});
			}
		});
	} 
}
//缓存房价方案列表
function saveHfunctionControlList(hfcList){
	var id = $("#currUpdId").val();
	var row = $("#currUpdRow").val();
	row = parseInt(row);
	var fgRow = flexGrid.rows[row] ;
	// 修改
	if(id){
		// 如果当前已修改过  则不记录
		if(updHfunctionIds.indexOf(id) == -1){
			updHfunctionRowArray.push(row);
			updHfunctionIds += (id + ",");
		}
		// 改变修改样式
		var indexVal = fgRow.dataItem.index ;
		fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
		
	}else{
		flexGrid.setCellData(row,"index","#", true , true);
	}
	fgRow.dataItem.hfunctionControlVOs = hfcList;
	fgRow.cssClass="eidt-row-blue";
	goShowButton(true, false) ;
}
// 显示按钮区域
function goShowButton(bool1,bool2){
	goShowButton_1(bool1);
	goShowButton_2(bool2);
}
/**
 * bool1 确定，放弃按钮组  显示(true)/置灰(false)
 */
function goShowButton_1(bool1){
	if(bool1){
		$("a#ok,a#cancel").css({"cursor":"pointer","color":"inherit"});
		$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/hroomPlan/updateStatus.do');" );
		$("#cancel").prop("href","javascript:giveUpEdit();");
	}else{
		$("a#ok,a#cancel").css({"cursor":"not-allowed","color":"grey"});
		$("#ok,#cancel").prop("href","javascript:;");
	}
}
/**
 * bool2 取消按钮组  显示(true)/置灰(false)
 */
function goShowButton_2(bool2){
	if(bool2){
		$("a#batchUpdateDate,a#del").css({"cursor":"pointer","color":"inherit"});
		$("#batchUpdateDate").prop("href","javascript:batchUpdateDate();");
		$("#del").prop("href","javascript:goDel();");
	}else{
		$("a#batchUpdateDate,a#del").css({"cursor":"not-allowed","color":"grey"});
		$("#batchUpdateDate,#del").prop("href","javascript:;");
	}
}