/*************start**************特别代码分类table填充*************start*****************/
// 消费点
function fillHConsumeTable(listJson , status){
	
	status_search = (status == 0 ? true : false );
	var array = [];	
	var data = eval('('+listJson.listJson+')')  ;
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
			"codeId_old": data[i].codeId,
			"codeNamee": data[i].codeNamee,
			"codeNamec": data[i].codeNamec,
			"svcRate": data[i].svcRate,
			"kind": data[i].kind,
			"integral": data[i].integral,
			"codeKindName": codeKind,
			"moneyId": data[i].moneyId,
			"codeKind": data[i].codeKind,
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
			    { header:"ID号", binding:"id",cssClass:"fg_column_readOnly_cell",name:"h_id" , align:"center", minWidth:50,width:60,isReadOnly:true}, 
			    { header:"代码", binding:"codeId",name:"h_codeId", align:"center", minWidth:40 ,width:60,isReadOnly:!status_search,mask:'AAAA'}, 
			    { header:"英文名", binding:"codeNamee",name:"h_codeNamee" , minWidth:60,width:140,isReadOnly:!status_search},
			    { header:"中文名",binding:"codeNamec",name:"h_codeNamec" , minWidth:60, width:120,isReadOnly:!status_search},
			    { header:"消费点类型",binding:"kind",name:"h_kind" , minWidth:100, width:120,isReadOnly:!status_search},
			    { header:"币种",binding:"moneyId",name:"h_moneyId" , minWidth:40, width:90,isReadOnly:!status_search},
			    { header:"服务费率",binding:"svcRate",name:"h_svcRate" , align:"right", minWidth:80, width:90,isReadOnly:!status_search,format:"n2"},
			    { header:"会员积分比例",binding:"integral",name:"h_integral" , align:"center", minWidth:120, width:130,isReadOnly:!status_search},
			    { header:"状态",binding:"codeKindName",cssClass:"fg_column_readOnly_cell", name:"h_codeKindName", align:"center", minWidth:40 ,width:80,isReadOnly:true}
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
	var data_kindId= eval('(' + listJson.kindIds + ')');
	var col_kind = flexGrid.columns.getColumn('kind');
	col_kind.dataMap = new wijmo.grid.DataMap(data_kindId, 'codeId', 'codeName');
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
		var fgCol = s.columns[e.col];
		var fgRow = s.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
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
		var fgCol = s.columns[e.col] ;
		var finalCellVal = s.getCellData(e.row, e.col, true);
		finalCellVal = finalCellVal.replace(/_/g , '');
		s.setCellData(e.row, e.col, finalCellVal);
		var indexVal = fgRow.dataItem.index ;
		
		// 校验代码唯一性
		if(fgCol.binding == "codeId" && !isNull(finalCellVal)){
			var codeId = (finalCellVal.toString()).toUpperCase() ;
			$.ajax({
				url:"/hconsume/findBy.do",
				type:"post",
				data:{id : fgRow.dataItem.id , codeId : codeId},
				dataType:"json",
				async:false ,
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
	// 双击添加行
	/*flexGrid.onRowAdded = function(e){
		e.panel.rows[e.row].dataItem.codeKindName = "可修改" ;
	};*/
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
//外汇汇率
function packHConsumeData(addItems , editItems){
	var addOrEditItemsArray = [];
	if(addItems && addItems.length > 0){
		for(var i=0 ; i < addItems.length ; i++){
			if($.isEmptyObject(addItems[i]))continue;
			var codeId = addItems[i].codeId ;
			var codeNamee = addItems[i].codeNamee ;
			var codeNamec = addItems[i].codeNamec ;
			var svcRate = addItems[i].svcRate ;
			var integral = addItems[i].integral ;
			var kind = addItems[i].kind ;
			var moneyId = addItems[i].moneyId ;
			
			if(isNull(codeId) || isNull(codeNamee) || isNull(codeNamec)
				    || isNull(svcRate) || isNull(integral) || isNull(kind)|| isNull(moneyId))
				continue ;
			addOrEditItemsArray.push({
				"codeId": codeId.toString(),
				"codeNamee": codeNamee,
				"codeNamec": codeNamec,
				"svcRate": svcRate,
				"integral": integral,
				"kind": kind,
				"moneyId": moneyId,
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
			var svcRate = editItems[i].svcRate ;
			var integral = editItems[i].integral ;
			var kind = editItems[i].kind ;
			var moneyId = editItems[i].moneyId ;
			
			if(isNull(codeId) || isNull(codeNamee) || isNull(codeNamec)
				    || isNull(svcRate) || isNull(integral)|| isNull(kind)|| isNull(moneyId))
				continue ;
		
			addOrEditItemsArray.push({
				"codeId": codeId.toString(),
				"codeNamee": codeNamee,
				"codeNamec": codeNamec,
				"svcRate": svcRate,
				"integral": integral,
				"moneyId": moneyId,
				"status": 0,
				"codeKind": editItems[i].codeKind,
				"kind": kind,
				"id":editItems[i].id
			});
		}
	}
	return addOrEditItemsArray ;
}
/*************end**************特别代码分类编辑数据封装***************end***************/