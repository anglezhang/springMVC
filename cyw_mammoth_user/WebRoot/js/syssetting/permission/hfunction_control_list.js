var array = [];
var flexGrid ;
var view ;
var status_search = true ;
function fillHfunctionControlListTable(controlDataMap , functionTypeDataMap){
	var data = parent.getFillObj();
	var count = 0 ;
	if(data)
		count = data.length;
	array.length = 0;
	for ( var i = 0; i < count; i++) {
		array.push({
			"index": i+1,
			"fgroup": data[i].hfunctionControl.fgroup,
			"ctrlType": data[i].hfunctionControl.ctrlType,
			"functionName": data[i].hfunctionControl.functionName,
			"functionId": data[i].hfunctionControl.functionId,
			"flag": data[i].flag,
			"refCount": data[i].refCount,
			"memo": data[i].hfunctionControl.memo,
			"id": data[i].hfunctionControl.id
		});
	}
	if(flexGrid){
		view = null ;
		flexGrid.dispose();
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
	         	{ header:"flag",binding:"flag",name:"flag" , isReadOnly:true,visible:false}, 
	         	{ header:"refCount",binding:"refCount",name:"refCount" , isReadOnly:true,visible:false}, 
			    { header:"序号",binding:"index",name:"h_index", align:"center",cssClass:"fg_column_readOnly" , minWidth:50,width:60,isReadOnly:true},           
			    { header:"ID号", binding:"id",name:"id" , align:"center",cssClass:"fg_column_readOnly_cell", minWidth:50,width:60,isReadOnly:true }, 
			    { header:"控件名称", binding:"functionName",name:"h_functionName" , align:"left", minWidth:60,width:150}, 
			    { header:"控件类型", binding:"ctrlType",name:"h_ctrlType", align:"left" , minWidth:100,width:120},
			    { header:"控件ID", binding:"functionId",name:"functionId", align:"center" , minWidth:80,width:120,isReadOnly:true},
			    { header:"功能类型", binding:"fgroup",name:"h_fgroup", align:"left" , minWidth:80,width:120},
			    { header:"备注",binding:"memo",name:"h_memo", align:"left", minWidth:60 , width:109}
			],
			allowAddNew: true,
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
	flexGrid.selection =(new wijmo.grid.CellRange(-1,-1,-1,-1));
	// 设置控件类型下拉代码
	var col_ctrlType = flexGrid.columns.getColumn('ctrlType');
	col_ctrlType.dataMap = new wijmo.grid.DataMap(controlDataMap, 'codeId', 'codeName');
	
	// 设置功能类型下拉代码
	var col_fgroup = flexGrid.columns.getColumn('fgroup');
	col_fgroup.dataMap = new wijmo.grid.DataMap(functionTypeDataMap, 'codeId', 'codeName');
	
	// 监测 单元个是否发生改变
	var initialCellVal;
	flexGrid.beginningEdit.addHandler(function(s, e){
		var fgCol = e.panel.columns[e.col];
		var fgRow = e.panel.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
		// 该列不允许修改
		if((fgCol.binding == "ctrlType"||fgCol.binding == "fgroup") && (dataItem && dataItem.index)){
			// 修改状态时
			var index = dataItem.index.toString() ;
			index = index.replace(/#/g,"") ;
			if(parseInt(index) > 0 && dataItem.refCount && dataItem.refCount > 0)
				e.cancel = true;
		}
		initialCellVal = s.getCellData(e.row, e.col, true);
	});
	flexGrid.cellEditEnded.addHandler(function(s, e){
		var fgRow = s.rows[e.row] ;
		var fgCol = s.columns[e.col] ;
		var finalCellVal = s.getCellData(e.row, e.col, true);
		finalCellVal = finalCellVal.replace(/_/g , '');
		s.setCellData(e.row, e.col, finalCellVal);
		var indexVal = fgRow.dataItem.index ;
        // 判断修改状态
        if(indexVal && finalCellVal != initialCellVal){
			// 设置样式
			fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true , true);
		}else if(isNull(initialCellVal)&&!isNull(finalCellVal)){
			fgRow.dataItem.index = "#";
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true , true );
		}
	});
	flexGrid.selectionChanged.addHandler(function(s, e){
		//选中区域是否包含 不可修改的行或者空行  true 包含 否则不包含
		var temp = true ;
		var selection = getSelectionRanges();
		if(selection.length == 0){
			temp = false ;
		}else{
			for(var i= 0;i<selection.length;i++){
				var dataItem = selection[i].dataItem ;
				if((!dataItem || (dataItem.refCount && dataItem.refCount !=0) ||(dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ))){
					temp =false ;
					break ;
				}
			}
		}
		goShowButton_2(temp) ;
		sortEndShowStartCss();
	});
	// 排序结束  还原排序之前的样式
	flexGrid.onSortedColumn=function(e){
		sortEndShowStartCss();
	} ;
	flexGrid.itemFormatter = function(panel, r, c, cell) {
		if (panel.cellType == wijmo.grid.CellType.Cell) {
			var col = panel.columns[c];
			var row = panel.rows[r];
			var dataItem = row.dataItem;
			if (dataItem && col.name == 'h_index'){
				var indexVal = dataItem.index ;
				if(dataItem.flag == "d"){
					// 设置当前行只读；
					row.isReadOnly=true;
					dataItem.index = (((indexVal.toString()).indexOf("*")==-1) ? ("*"+indexVal) : (indexVal)) ;;
					row.cssClass="select-row-yellow";
					goShowButton(true , false );
				}else if(dataItem.flag == "a"){
					dataItem.index = "#" ;
					row.cssClass = "eidt-row-blue" ;
					goShowButton(true , true);
				}else if(dataItem.flag == "u"){
					dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ; ;
					row.cssClass = "eidt-row-blue" ;
					goShowButton(true , true);
				}
			}
		}
	}; 
}
function goOk(){
	outCursor();
	var addOrEditItemsArray = [];
	var holidayNamesArr = [];
	var col_ctrlType = flexGrid.columns.getColumn('ctrlType');
	var ctrlType_dataMap = col_ctrlType.dataMap;
	var ctrlType_sourceViewArray = ctrlType_dataMap.collectionView.sourceCollection ;
	
	var col_fgroup = flexGrid.columns.getColumn('fgroup');
	var fgroup_dataMap = col_fgroup.dataMap;
	var fgroup_sourceViewArray = fgroup_dataMap.collectionView.sourceCollection ;
	
	
	
	for ( var i = 0; i < flexGrid.rows.length; i++) {
		var fgRow = flexGrid.rows[i] ;
		
		var dataItem = fgRow.dataItem ;
		if(isNull(dataItem))continue;
		var index = dataItem.index ;
		if(isNull(index))continue;
		
		index = index.toString();
		var id = dataItem.id ;
		var fgroup = dataItem.fgroup ;
		var ctrlType = dataItem.ctrlType ;
		var functionName = dataItem.functionName ;
		var memo = dataItem.memo ;
		
		var msg = "" ;
		if(isNull(fgroup)){
			msg+='功能类型,';
		}
		if(isNull(ctrlType)){
			msg+='控件类型,';
		}
		if(isNull(functionName)){
			msg+='控件名称,';
		}
		if(msg != ""){
			altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
			return ;
		}
		
		
		var hfunctionControl = new Object;
		
		
		if(index.indexOf("*") != -1){
			if(isNull(id))
				continue ;
			hfunctionControl.id = id;
			hfunctionControl.fgroup = fgroup;
			hfunctionControl.ctrlType = ctrlType;
			hfunctionControl.functionName = functionName;
			hfunctionControl.memo = memo;
			
			addOrEditItemsArray.push({
				"hfunctionControl":hfunctionControl,
				"flag":"d"
			});
		}else if(index == "#"){
			hfunctionControl.id = "";
			hfunctionControl.fgroup = fgroup;
			hfunctionControl.ctrlType = ctrlType;
			hfunctionControl.functionName = functionName;
			hfunctionControl.memo = memo;
			addOrEditItemsArray.push({
				"hfunctionControl":hfunctionControl,
				"flag":"a"
			});
		}else if(index.indexOf("#") != -1){
			hfunctionControl.id = id;
			hfunctionControl.fgroup = fgroup;
			hfunctionControl.ctrlType = ctrlType;
			hfunctionControl.functionName = functionName;
			hfunctionControl.memo = memo;
			addOrEditItemsArray.push({
				"hfunctionControl":hfunctionControl,
				"flag":"u"
			});
		}else{
			hfunctionControl.id = id;
			hfunctionControl.fgroup = fgroup;
			hfunctionControl.ctrlType = ctrlType;
			hfunctionControl.functionName = functionName;
			hfunctionControl.memo = memo;
			addOrEditItemsArray.push({
				"hfunctionControl":hfunctionControl,
				"flag":"n"
			});
		}
	}
	parent.saveHfunctionControlList(addOrEditItemsArray) ;
	parent.closeDiv('hfunctionControlListAlert');
}

//显示按钮区域
/**
 * bool1 确定，放弃按钮组  显示(true)/置灰(false)
 * bool2 取消按钮组  显示(true)/置灰(false)
 */
function goShowButton(bool1 , bool2){
	goShowButton_1(bool1) ;
	goShowButton_2(bool2) ;
}
function goShowButton_1(bool1){
	if(bool1){
		$("a#ok,a#cancel").css({"cursor":"pointer","color":"inherit"});
		$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/hfunction/updateStatus.do');" );
		$("#cancel").prop("href","javascript:giveUpAndRefresh();");
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