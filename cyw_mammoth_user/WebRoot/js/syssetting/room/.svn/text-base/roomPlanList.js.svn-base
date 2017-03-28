var array = [];
var flexGrid ;
var view ;
var status_search;  // 可操作   true  不可操作 false 
function fillHroomPlanListTable(data_hcodes , data_hroomTypeId , _status_search){
	console.log(data_hcodes);
	status_search = _status_search;
	var data = parent.getFillObj();
	var count = 0 ;
	if(data)
		count = data.length;
	array.length = 0;
	for ( var i = 0; i < count; i++) {
		array.push({
			"index": i+1,
			"roomType": data[i].hroomPlanList.rmtypeId,
			"holidayName": data[i].hroomPlanList.holidayId,
			"price": data[i].hroomPlanList.price,
			"flag": data[i].flag,
			"id": data[i].hroomPlanList.id
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
			    { header:"序号",binding:"index",name:"h_index", align:"center",cssClass:"fg_column_readOnly" , minWidth:40,width:60,isReadOnly:true},           
			    { header:"ID号", binding:"id",name:"id" , align:"center",cssClass:"fg_column_readOnly_cell", minWidth:50,width:60,isReadOnly:true }, 
			    { header:"房型", binding:"roomType",name:"h_roomType" , align:"left", minWidth:60,width:160,isReadOnly:!status_search}, 
			    { header:"假日类型", binding:"holidayName",name:"h_holidayName", align:"center" , minWidth:80,width:110,isReadOnly:!status_search},
			    { header:"价格",binding:"price",name:"h_price", align:"right", minWidth:60 , width:110 , format:"n2",isReadOnly:!status_search}
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
	flexGrid.selection =(new wijmo.grid.CellRange(-1,-1,-1,-1));
	// 设置节假日下拉代码
	var col_hcodes = flexGrid.columns.getColumn('holidayName');
	col_hcodes.dataMap = new wijmo.grid.DataMap(data_hcodes, 'codeId', 'codeNamec');
	
	// 设置房型下拉代码
	var col_hroomTypeId = flexGrid.columns.getColumn('roomType');
	col_hroomTypeId.dataMap = new wijmo.grid.DataMap(data_hroomTypeId, 'hroomTypeCodeId', 'hroomTypeCcodeName');
	
	// 监测 单元个是否发生改变
	var initialCellVal;
	flexGrid.beginningEdit.addHandler(function(s, e){
		var fgCol = e.panel.columns[e.col];
		var fgRow = e.panel.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
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
        // 房型和假日类型 组合唯一性校验
        if(!isNull(finalCellVal) && (fgCol.binding == "roomType" || fgCol.binding == "holidayName")){
        	var roomType = fgRow.dataItem.roomType ;
        	var holidayName = fgRow.dataItem.holidayName ;
        	if(!isNull(roomType) && !isNull(holidayName)){
        		var selectArr = getAllSelectRoomTypeAndHolidayNameBy(e.row);
        		if(selectArr.length > 0){
        			var val = roomType +"-=-"+ holidayName ;
                	var pos = $.inArray(val , selectArr);
                	if(pos != -1) {
                		s.setCellData(e.row,fgCol.binding,initialCellVal, true);
                		//altWaringMsg('房型和假日类型不允许重复，重复项：第'+(parseInt(pos)+1)+'行',function(){
            			altWaringMsg('房型和假日类型不允许重复',function(){
    						goShowButton(false, false);
    				    	flexGrid.startEditing(true , e.row , e.col);
    					    e.cancel = true;
    					});
                	}
        		}
        	}
        }
	});
	flexGrid.selectionChanged.addHandler(function(s, e){
		if(!status_search)
			return ;
		//选中区域是否包含 不可修改的行或者空行  true 包含 否则不包含
		var temp = true ;
		var selection = getSelectionRanges();
		if(selection.length == 0){
			temp = false ;
		}else{
			for(var i= 0;i<selection.length;i++){
				var dataItem = selection[i].dataItem ;
				if((!dataItem || (dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ))){
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
	var col_hcodes = flexGrid.columns.getColumn('holidayName');
	var hcodes_dataMap = col_hcodes.dataMap;
	var hcodes_sourceViewArray = hcodes_dataMap.collectionView.sourceCollection ;
	for ( var i = 0; i < flexGrid.rows.length; i++) {
		var fgRow = flexGrid.rows[i] ;
		
		var dataItem = fgRow.dataItem ;
		if(isNull(dataItem))continue;
		var index = dataItem.index ;
		if(isNull(index))continue;
		
		index = index.toString();
		var id = dataItem.id ;
		var roomType = dataItem.roomType ;
		var holidayName = dataItem.holidayName ;
		var price = dataItem.price ;
		
		var msg = "" ;
		if(isNull(roomType)){
			msg+='房型,';
		}
		if(isNull(holidayName)){
			msg+='假日类型,';
		}
		if(msg != ""){
			altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
			return ;
		}
		
		
		var hroomPlanList = new Object;
		if(index.indexOf("*") == -1){
			A:for (var j = 0; j < hcodes_sourceViewArray.length; j++) {
				if(hcodes_sourceViewArray[j].codeId == holidayName){
					holidayNamesArr.push(hcodes_sourceViewArray[j].codeNamec);
					break A ;
				}
			}
		}
		
		
		if(index.indexOf("*") != -1){
			if(isNull(id))
				continue ;
			hroomPlanList.id = id;
			hroomPlanList.rmtypeId = roomType;
			hroomPlanList.holidayId = holidayName;
			hroomPlanList.price = price;
			hroomPlanList.status = 1;
			addOrEditItemsArray.push({
				"hroomPlanList":hroomPlanList,
				"flag":"d"
			});
		}else if(index == "#"){
			hroomPlanList.id = "";
			hroomPlanList.rmtypeId = roomType;
			hroomPlanList.holidayId = holidayName;
			hroomPlanList.price = price;
			hroomPlanList.status = 0;
			addOrEditItemsArray.push({
				"hroomPlanList":hroomPlanList,
				"flag":"a"
			});
		}else if(index.indexOf("#") != -1){
			hroomPlanList.id = id;
			hroomPlanList.rmtypeId = roomType;
			hroomPlanList.holidayId = holidayName;
			hroomPlanList.price = price;
			hroomPlanList.status = 0;
			addOrEditItemsArray.push({
				"hroomPlanList":hroomPlanList,
				"flag":"u"
			});
		}else{
			hroomPlanList.id = id;
			hroomPlanList.rmtypeId = roomType;
			hroomPlanList.holidayId = holidayName;
			hroomPlanList.price = price;
			hroomPlanList.status = 0;
			addOrEditItemsArray.push({
				"hroomPlanList":hroomPlanList,
				"flag":"n"
			});
		}
	}
	parent.saveHroomPlanList(arrayUnique(holidayNamesArr),addOrEditItemsArray) ;
	parent.closeDiv('roomPlanListAlert');
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
		$("#ok").attr("href","javascript:goOk();");
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
function getAllSelectRoomTypeAndHolidayNameBy(currentRow) {
	var selectArr = [] ;
	for(var r=0;r<flexGrid.rows.length;r++)
	{
		if(!isNull(currentRow) && r === currentRow)
			continue ;
		var dataItem = flexGrid.rows[r].dataItem ;
		if(dataItem){
			selectArr.push(dataItem.roomType+"-=-"+dataItem.holidayName);
		}
	}
	return selectArr ;
}