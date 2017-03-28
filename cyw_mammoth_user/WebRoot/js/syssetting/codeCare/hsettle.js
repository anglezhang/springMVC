/*************start**************特别代码分类table填充*************start*****************/
//国家
function fillHsettleTable(listJson , status){
	
	status_search = (status == 0 ? true : false );
	var array = [];	
	var data = eval('('+listJson.listJson+')') ;
	var count = data ? data.length : 0;
	for ( var i = 0; i < count; i++) {
		var codeKind = "";
		if (data[i].codeKind == 0) {
			codeKind = "可修改";
		} else if (data[i].codeKind == 1) {
			codeKind = "不可修改";
		}
		array.push({
			"index": i+1,
			"codeId": data[i].codeId,
			"codeNamee": data[i].codeNamee,
			"codeNamec": data[i].codeNamec,
			"limit": data[i].limit,
			"kind": data[i].kind,
			"moneyId": data[i].moneyId,
			"arNum": data[i].arNum,
			"codeId_old": data[i].codeId,
			"codeKind": data[i].codeKind,
			"codeKindName": codeKind,
			"status": data[i].status,
			"id": data[i].id
		});
	}
	// create CollectionView on the data (to get events)
	view = new wijmo.collections.CollectionView(array);
	view.trackChanges = true;
	//Disposes of the control by removing its association with the host element.
	if(flexGrid)
		flexGrid.dispose();
	
	// initialize the grid
	flexGrid = new wijmo.grid.FlexGrid('#theGrid', {
			autoGenerateColumns: false,
			selectionMode : wijmo.grid.SelectionMode.ListBox,
			autoClipboard:true,
			columns:[
			    { header:"id",binding:"id",name:"id",isReadOnly:true,visible:false}, 
			    { header:"status",binding:"status",name:"status" ,isReadOnly:true,visible:false}, 
			    { header:"codeKind",binding:"codeKind",name:"codeKind" ,isReadOnly:true,visible:false}, 
			    { header:"codeId_old", binding:"codeId_old",name:"h_codeId_old",isReadOnly:true,visible:false}, 
			    { header:"序号",binding:"index",cssClass:"fg_column_readOnly",name:"h_index", align:"center", minWidth:40,width:60 ,isReadOnly:true},           
			    { header:"ID号", binding:"id",cssClass:"fg_column_readOnly_cell",name:"h_id" , align:"center", minWidth:50,width:60,isReadOnly:true }, 
			    { header:"代码", binding:"codeId",name:"h_codeId", align:"center", minWidth:40 ,width:60,isReadOnly:!status_search,mask:'AAAA'}, 
			    { header:"英文名", binding:"codeNamee",name:"h_codeNamee" , minWidth:60,width:90,isReadOnly:!status_search},
			    { header:"中文名",binding:"codeNamec",name:"h_codeNamec" , minWidth:60, width:100,isReadOnly:!status_search},
			    { header:"信用限额",binding:"limit",name:"h_limit" , minWidth:80, width:80,isReadOnly:!status_search,format:"n2"},
			    { header:"付款方式类型",binding:"kind",name:"h_kind" , align:"center", minWidth:100, width:110,isReadOnly:!status_search},
			    { header:"币种",binding:"moneyId",name:"h_moneyId" , minWidth:40, width:88,isReadOnly:!status_search},
			    { header:"AR账号",binding:"arNum",name:"h_arNum" , minWidth:60, width:80,isReadOnly:!status_search},
			    { header:"状态",binding:"codeKindName",cssClass:"fg_column_readOnly_cell", name:"h_codeKindName", align:"center", minWidth:40 ,width:80,isReadOnly:true},
			    { header:"",binding:"", name:"", align:"center" ,width:'*',isReadOnly:true}
			],
			allowAddNew: status_search,
			// 拖拽
			allowDragging:wijmo.grid.AllowDragging.None,
			// 设置列/行的显示
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			itemsSource : view
		}
	);
	// 禁止默认选中第一行
	flexGrid.selection = status_search ? new wijmo.grid.CellRange(-1,-1,-1,-1) : new wijmo.grid.CellRange(0,0,0,0);
	
	// 设置付款方式类型下拉代码
	var data_hsetlKindId= eval('(' + listJson.hsetlKinds + ')');
	var col_kindId = flexGrid.columns.getColumn('kind');
	col_kindId.dataMap = new wijmo.grid.DataMap(data_hsetlKindId, 'codeId', 'codeName');
	
	// 设币种代码下拉代码
	var data_hexchangeId= eval('(' + listJson.hexchanges + ')');
	var col_moneyId = flexGrid.columns.getColumn('moneyId');
	col_moneyId.dataMap = new wijmo.grid.DataMap(data_hexchangeId, 'codeId', 'codeName');
	
	
	// 格式化自定义处理每一个单元的内容及样式
	flexGrid.itemFormatter = function(panel, r, c, cell) {
		if (panel.cellType == wijmo.grid.CellType.Cell) {
			var col = panel.columns[c];
			if (col.name == 'h_codeKindName' && cell.innerHTML == "不可修改"){
				// 设置当前行只读；
				panel.rows[r].isReadOnly=true;
			}
		}
	}; 
	// 监测 单元个是否发生改变
	var initialCellVal;
	// 单元格编辑模式判定
	flexGrid.beginningEdit.addHandler(function(s, e){
		var fgRow = s.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
		var fgCol = s.columns[e.col];
		// 该列为代码大于0 则不允许修改
		if(fgCol.binding == "codeId" && (dataItem && dataItem.index)){
			// 修改状态时
			var index = dataItem.index.toString() ;
			index = index.replace(/#/g,"") ;
			if(parseInt(index) > 0)
				e.cancel = true;
		}
		initialCellVal = s.getCellData(e.row, e.col, true);
	});
	flexGrid.cellEditEnded.addHandler(function(s, e){
		var fgRow = s.rows[e.row] ;
		var fgCol = s.columns[e.col];
		var finalCellVal = s.getCellData(e.row, e.col, true);
		finalCellVal = finalCellVal.replace(/_/g , '');
		s.setCellData(e.row, e.col, finalCellVal);
		var indexVal = fgRow.dataItem.index ;
		// 校验代码唯一性
		if(fgCol.binding == "codeId" && !isNull(finalCellVal)){
			var codeId = (finalCellVal.toString()).toUpperCase() ;
			$.ajax({
				url:"/hsettle/findBy.do",
				type:"post",
				data:{id : fgRow.dataItem.id , codeId : codeId},
				dataType:"json",
				success:function(data){
					if(!data.success){
						s.setCellData(e.row,fgCol.binding,initialCellVal, true);
						altWaringMsg('代码重复，请重新编辑',function(){
							goShowButton(false);
					    	flexGrid.startEditing(false , e.row , e.col);
						    e.cancel = true;
						});
					}else{
						// 判断修改状态
				        if(indexVal && finalCellVal != initialCellVal){
							// 设置样式
							fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
							fgRow.cssClass="eidt-row-blue";
							goShowButton(true);
						}else if(isNull(initialCellVal)&&!isNull(finalCellVal)){
							fgRow.dataItem.index = "#";
							fgRow.cssClass="eidt-row-blue";
							goShowButton(true);
						}
					}
				},
				error:function(err){
					altEerrMsg('出现异常',function(){});
				}
			});
		}else{
			// 判断修改状态
	        if(indexVal && finalCellVal != initialCellVal){
				// 设置样式
				fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
				fgRow.cssClass="eidt-row-blue";
				goShowButton(true);
			}else if(isNull(initialCellVal)&&!isNull(finalCellVal)){
				fgRow.dataItem.index = "#";
				fgRow.cssClass="eidt-row-blue";
				goShowButton(true);
			}
		}
		
	});
	// 选中区域后改变样式
	flexGrid.selectionChanged.addHandler(function(s, e){
		if(status_search){
			//选中区域是否包含 不可修改的行或者空行  true 包含 否则不包含
			var temp = true ;
			var selection = getSelectionRanges();
			for(var i= 0;i<selection.length;i++){
				var dataItem = selection[i].dataItem ;
				if((!dataItem || (dataItem.codeKind&&dataItem.codeKind == 1))){
					temp =false ;
					break ;
				}
			}
			if(temp){
				$("a#del").css({"cursor":"pointer","color":"inherit"});
				$("#del").attr("href","javascript:goDel(true);");
			}else{
				$("a#del").css({"cursor":"not-allowed","color":"grey"});
				$("#del").attr("href","javascript:;");
			}
			sortEndShowStartCss();
		}
	});
	// 排序结束  还原排序之前的样式
	flexGrid.onSortedColumn=function(e){
		sortEndShowStartCss();
	} ;
	
}

/*************end**************特别代码分类table填充***************end***************/

/*************start**************特别代码分类编辑数据封装***************start***************/
//国家
function packHsettleData(addItems , editItems){
	var addOrEditItemsArray = [];
	if(addItems && addItems.length > 0){
		for(var i=0 ; i < addItems.length ; i++){
			if($.isEmptyObject(addItems[i]))continue;
			var codeId = addItems[i].codeId ;
			var codeNamee = addItems[i].codeNamee ;
			var codeNamec = addItems[i].codeNamec ;
			var limit = addItems[i].limit ;
			var kind = addItems[i].kind ;
			var moneyId = addItems[i].moneyId ;
			var arNum = addItems[i].arNum ;
			
			if(isNull(codeId) || isNull(codeNamee) || isNull(codeNamec)
					|| isNull(limit)|| isNull(kind)|| isNull(moneyId)|| isNull(arNum))
				continue ;
			addOrEditItemsArray.push({
				"codeId": codeId.toString(),
				"codeNamee": codeNamee,
				"codeNamec": codeNamec,
				"limit": limit,
				"kind": kind,
				"moneyId": moneyId,
				"arNum": arNum,
				"codeKind": 0,
				"status": 0
			});
		}
	}
	if(editItems && editItems.length > 0){
		for(var i=0 ; i < editItems.length ; i++){
		
			var codeId = editItems[i].codeId ;
			var codeNamee = editItems[i].codeNamee ;
			var codeNamec = editItems[i].codeNamec ;
			var limit = editItems[i].limit ;
			var kind = editItems[i].kind ;
			var moneyId = editItems[i].moneyId ;
			var arNum = editItems[i].arNum ;
			
			if(isNull(codeId) || isNull(codeNamee) || isNull(codeNamec)
					|| isNull(limit)|| isNull(kind)|| isNull(moneyId)|| isNull(arNum))
				continue ;
		
			addOrEditItemsArray.push({
				"codeId": codeId.toString(),
				"codeNamee": codeNamee,
				"codeNamec": codeNamec,
				"limit": limit,
				"kind": kind,
				"moneyId": moneyId,
				"arNum": arNum,
				"codeKind": 0,
				"status": 0,
				"id":editItems[i].id
			});
		}
	}
	return addOrEditItemsArray ;
}
/*************end**************特别代码分类编辑数据封装***************end***************/