// 修改房间方案列表时 记录行数
var updRoomPlanRowArray = [];
// 修改房间方案列表时 记录id号
var updRoomPlanIds = "" ;
var flexGrid ;
var rmplanTypeArr = [{codeId:"1" , codeName:"普通"},
                {codeId:"2" , codeName:"合约"},
                {codeId:"3" , codeName:"钟点"},
                {codeId:"4" , codeName:"免费"},
                {codeId:"5" , codeName:"自用"}] ;
var checkedArr = [{codeId:"0" , codeName:"否"},
                     {codeId:"1" , codeName:"是"}] ;
var editableArr = [{codeId:"0" , codeName:"否"},
                     {codeId:"1" , codeName:"是"}] ;
var status_search ;
var initialCellVal;
function fillHroomPlanTable(listJson , _status_search){
	status_search = _status_search;
	var data = listJson ;
	var count = data.length;
	// 列表初始化数组
	var array = [];
	for ( var i = 0; i < count; i++) {
		var status = "";
		var checked = "";
		var editable = "";
		if (data[i].hroomPlan.status == 0) {
			status = "有效";
		} else if (data[i].hroomPlan.status == 1) {
			status = "无效";
		}
		
		var startDate = new Date((data[i].hroomPlan.startDate)) ;
		startDate = startDate.format("yyyy-MM-dd");
		var endDate = new Date((data[i].hroomPlan.endDate)) ;
		endDate = endDate.format("yyyy-MM-dd");
		console.log(data[i]);
		array.push({
			"index": i+1,
			"codeId": data[i].hroomPlan.codeId,
			"codeNamec": data[i].hroomPlan.codeNamec,
			"startDate": startDate,	
			"endDate": endDate,
			"hroomPlanHolidayNames": data[i].hroomPlanHolidayNames,
			"hroomPlanListVOs": data[i].hroomPlanListVOs,
			"rmplanType": data[i].hroomPlan.rmplanType,
			"status": status,
			"editFlag": data[i].editFlag,
			"checked": data[i].hroomPlan.checked,
			"editable": data[i].hroomPlan.editable,
			"id": data[i].hroomPlan.id
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
			    { header:"editFlag",binding:"editFlag",name:"editFlag" ,visible:false}, 
			    { header:"hroomPlanListVOs",binding:"hroomPlanListVOs",name:"hroomPlanListVOs" ,visible:false}, 
			    { header:"序号",binding:"index",name:"h_index", align:"center",cssClass:"fg_column_readOnly",isReadOnly:true, minWidth:50,width:50},           
			    { header:"代码",binding:"codeId",name:"h_codeId", minWidth:40 , width:60, align:"center",isReadOnly:!status_search,mask:'AAA'},
			    { header:"方案名称", binding:"codeNamec",name:"h_codeNamec" , align:"left", minWidth:40,width:160,isReadOnly:!status_search},
			    { header:"起始日期", binding:"startDate",name:"h_startDate" , align:"center", minWidth:80,width:100,isReadOnly:!status_search,format:"yyyy-MM-dd"}, 
			    { header:"截止日期", binding:"endDate",name:"h_endDate", align:"center", minWidth:80 ,width:100,isReadOnly:!status_search,format:"yyyy-MM-dd" }, 
			    { header:"内容提要",binding:"hroomPlanHolidayNames",name:"h_hroomPlanHolidayNames", width:188 ,minWidth:80,isReadOnly:true,allowSorting:false},
			    { header:"类型",binding:"rmplanType",name:"h_rmplanType", align:"center", width:80 ,minWidth:60,isReadOnly:!status_search},
			    { header:"选中启用",binding:"checked",name:"h_checked", align:"center", width:90 ,minWidth:80,isReadOnly:!status_search},
			    { header:"允许修改",binding:"editable",name:"h_editable", align:"center", width:90 ,minWidth:80,isReadOnly:!status_search},
			    { header:"状态",binding:"status", name:"h_status", align:"center",cssClass:"fg_column_readOnly_cell" , minWidth:40,width:60,isReadOnly:true,allowSorting:false}
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
	
	// 设置入住类型下拉代码
	var col_rmplanType = flexGrid.columns.getColumn('rmplanType');
	col_rmplanType.dataMap = new wijmo.grid.DataMap(rmplanTypeArr, 'codeId', 'codeName');
	
	// 选中启用
	var col_checked = flexGrid.columns.getColumn('checked');
	col_checked.dataMap = new wijmo.grid.DataMap(checkedArr, 'codeId', 'codeName');
	
	// 允许修改
	var col_editable = flexGrid.columns.getColumn('editable');
	col_editable.dataMap = new wijmo.grid.DataMap(editableArr, 'codeId', 'codeName');
	
	
    // 单元格编辑模式判定
	flexGrid.beginningEdit.addHandler(function(s, e){
		var fgCol = s.columns[e.col];
		var fgRow = s.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
		// 该列为代码大于0 则不允许修改
		if((fgCol.binding == "codeId" 
			|| fgCol.binding == "startDate" 
			|| fgCol.binding == "endDate" 
			|| fgCol.binding == "rmplanType" 
			|| fgCol.binding == "checked" 
			|| fgCol.binding == "editable" 
			|| fgCol.binding == "hroomPlanHolidayNames") && (dataItem && dataItem.index)){
			// 修改状态时
			var index = dataItem.index.toString() ;
			index = index.replace(/#/g,"") ;
			if(parseInt(index) > 0 && (dataItem.editFlag && dataItem.editFlag == '1'))
				e.cancel = true;
		}
        initialCellVal = s.getCellData(e.row, e.col, true);
        if(fgCol.binding == "codeId" && !isNull(initialCellVal)){
        	initialCellVal = initialCellVal.toString().trim().replace(/_/g , '');
        }
    });
    
    flexGrid.cellEditEnded.addHandler(function (s, e) {
        var fgRow = s.rows[e.row] ;
        var fgCol = s.columns[e.col];
        var dataItem = fgRow.dataItem ;
		var indexVal = fgRow.dataItem.index ;
        var finalCellVal = s.getCellData(e.row, e.col, true);
        
        if(fgCol.binding == "codeId" && !isNull(finalCellVal)){
        	finalCellVal = finalCellVal.toString().trim().replace(/_/g , '');
        	s.setCellData(e.row, e.col, finalCellVal);
        }
        
        // 校验代码唯一性
		if(fgCol.binding == "codeId" && !isNull(finalCellVal)){
			$.ajax({
				url:"/hroomPlan/findBy.do",
				type:"post",
				data:{id : dataItem.id , codeId : finalCellVal.toUpperCase()},
				dataType:"json",
				success:function(data){
					if(!data.success){
						s.setCellData(e.row,fgCol.binding,initialCellVal, true);
						finalCellVal = initialCellVal;
						altWaringMsg('代码重复，请重新编辑',function(){
							goShowButton(false , false );
					    	flexGrid.startEditing(false , e.row , e.col);
						    e.cancel = true;
						});
					}
				},
				error:function(err){
					altEerrMsg('出现异常',function(){
						goShowButton(false , false );
				    	flexGrid.startEditing(false , e.row , e.col);
					    e.cancel = true;
					});
				}
			});
		}
		// 起始、截止日期
        if (fgCol.binding == 'startDate'||fgCol.binding == 'endDate'){
            var startDate = dataItem.startDate;
            var endDate = dataItem.endDate;
            if(isNull(startDate) && isNull(endDate)){}
            else if(!isNull(startDate)&&!isSimpleDate(startDate) ){
            	s.setCellData(e.row,fgCol.binding,initialCellVal, true);
            	altWaringMsg("起始日期不合法",function(){
            		goShowButton(false , false );
        			flexGrid.startEditing(false , e.row , e.col);
        			e.cancel = true;
        		});
            }
            else if(!isNull(endDate)&&!isSimpleDate(endDate)){
            	s.setCellData(e.row,fgCol.binding,initialCellVal, true);
            	altWaringMsg("截止日期不合法",function(){
            		goShowButton(false , false );
        			flexGrid.startEditing(false , e.row , e.col);
        			e.cancel = true;
        		});
            }
            else{
            	if(!isNull(startDate) && !isNull(endDate) && getDateType(startDate.toString(),'-') > getDateType(endDate.toString(),'-')){
            		s.setCellData(e.row,fgCol.binding,initialCellVal, true);
            		//finalCellVal = initialCellVal ;
                	var content = (fgCol.binding == 'startDate') ? "起始日期不能大于截止日期" : "截止日期不能小于起始日期";
                	altWaringMsg(content,function(){
                		goShowButton(false , false );
            			flexGrid.startEditing(false , e.row , e.col);
            			e.cancel = true;
            		});
                }
            }
            
        }
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
  			// 起始日期 和 截止日期 单元格选中后进入编辑模式
  			if(fgColumn && (fgColumn.binding == "startDate" || fgColumn.binding == "endDate")){
				flexGrid.startEditing(true , e.row , e.col);
  			}
  	  		// 是否显示 取消，批量改期按钮
      		var temp = true ;
			var selection = getSelectionRanges();
			if(selection.length == 0){
				temp = false ;
			}else{
				for(var i= 0;i<selection.length;i++){
					var dataItem = selection[i].dataItem ;
					if((!dataItem 
							|| (dataItem.editFlag&&dataItem.editFlag == '1' ) 
							||(dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ))){
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
                if (dataItem && status_search && ht.cellType == wijmo.grid.CellType.Cell && col.binding=='hroomPlanHolidayNames') {
                	initHroomPlanList(ht.range.row , dataItem.id , dataItem.hroomPlanListVOs,(dataItem.editFlag && dataItem.editFlag == '0')) ;
            	}
            }
        }
   	});
   	
   	createEditor(flexGrid.columns.getColumn('startDate'));
   	createEditor(flexGrid.columns.getColumn('endDate'));
}
function createEditor(editColumn) {
    var grid = editColumn.grid;

    grid.formatItem.addHandler(function (s, e) {
        var editRange = grid.editRange,
            column = e.panel.columns[e.col];
        // check whether this is an editing cell of the wanted column
        if (!(e.panel.cellType === wijmo.grid.CellType.Cell && column === editColumn && editRange && editRange.row === e.row && editRange.col === e.col)) {
            return;
        }
        // hide standard editor (don't remove!)
        if (e.cell.firstChild) {
            e.cell.firstChild.style.display = 'none';
        }
        // add custom editor
		var editorRoot_id = "date_"+e.row+"_"+e.col ;
		var editorRoot = "<input type=\"text\" id=\""+editorRoot_id+"\" class=\" wj-grid-editor wj-form-control\" style=\"text-align: center;\">";
		e.cell.innerHTML = editorRoot;
		var ttrr = $("#"+editorRoot_id).inputmask("yyyy-mm-dd");
		$("#"+editorRoot_id).val(grid.getCellData(e.row, e.col, true));

        // cellEditEnding that updates cell with user's input
        var editEndingEH = function (s, args) {
            grid.cellEditEnding.removeHandler(editEndingEH);
            if (!args.cancel) {
                args.cancel = true;
                grid.setCellData(e.row, e.col, $("#"+editorRoot_id).val());
            }
        };
        // subscribe the handler to the cellEditEnding event
        grid.cellEditEnding.addHandler(editEndingEH);
    });
}
var _obj ;
//初始化房价方案列表
function initHroomPlanList(row , id , objJson , editFlag){
	_obj = isNull(objJson)? "" : objJson;
	$("#currUpdId").val(isNull(id)? "" : id );
	$("#currUpdRow").val(row);
	$("#roomPlanListFrame").prop("src" , "/hroomPlan/hroomPlanList.do?editFlag="+(isNull(editFlag)? true : editFlag)) ;
}
//显示房价方案列表
function showHroomPlanList(){
	$(".alertDivBg").fadeIn();
	$("#roomPlanListAlert").fadeIn();
}
// 返回当前选中条目的房价方案列表对象
function getFillObj(){
	return _obj ;
}
//根据id关闭弹出窗口
function closeDiv(alertId){
	$("#"+alertId).fadeOut(function(){
		$(".alertDivBg").fadeOut(function(){
			if(alertId == "roomPlanListAlert")
				$("#roomPlanListFrame").prop("src","");
		});
	});
}
//确定操作
function goOk(){
	outCursor();
	var addOrEditItemsArray = [];
	var hroomPlan ;
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
			var codeId = addItems[i].codeId ;
			var codeNamec = addItems[i].codeNamec;
			var startDate = addItems[i].startDate ;
			var endDate = addItems[i].endDate;
			var rmplanType = addItems[i].rmplanType;
			var checked = addItems[i].checked;
			var editable = addItems[i].editable;
			var hroomPlanListVOs = addItems[i].hroomPlanListVOs;
			hroomPlanListVOs = isNull(hroomPlanListVOs) ? (new Array()) : hroomPlanListVOs ;
			var msg = "" ;
			if(isNull(codeId)){
				msg+='代码,';
			}
			if(isNull(codeNamec)){
				msg+='方案名称,';
			}
			if(isNull(startDate)){
				msg+='起始日期,';
			}else{
				startDate =  (startDate.length == 10 ? startDate : startDate.format("yyyy-MM-dd"));
			}
			if(isNull(endDate)){
				msg+='截止日期,';
			}else{
				endDate =  (endDate.length == 10 ? endDate : endDate.format("yyyy-MM-dd"));
			}
			if(isNull(rmplanType)){
				msg+='类型,';
			}
			if(isNull(checked)){
				msg+='选中启用,';
			}
			if(isNull(editable)){
				msg+='允许修改,';
			}
			
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}else{
				 if(msg != ""){
					 altWaringMsg(msg.substring(0, msg.length-1),function(){});
					 return ;
				 }
			}
			hroomPlan = new Object;
			hroomPlan.codeId = codeId ;
			hroomPlan.codeNamec = codeNamec ;
			hroomPlan.status = 0 ;
			hroomPlan.checked = checked ;
			hroomPlan.editable = editable ;
			hroomPlan.rmplanType = rmplanType ;
			
			addOrEditItemsArray.push({
				"editFlag":"a",
				"startDate":startDate,
				"endDate":endDate,
				"hroomPlan": hroomPlan,
				"hroomPlanListVOs": hroomPlanListVOs
			});
		}
	}
	// 修改房间特征数组   优先级高于view.itemsEdited 
	if(updRoomPlanRowArray.length > 0){
		for(var i=0 ; i < updRoomPlanRowArray.length ; i++){
			var rowItem = flexGrid.rows[updRoomPlanRowArray[i]].dataItem;
			var id = rowItem.id ;
			var codeId = rowItem.codeId ;
			var codeNamec = rowItem.codeNamec;
			var startDate = rowItem.startDate ;
			var endDate = rowItem.endDate;
			var rmplanType = rowItem.rmplanType;
			var checked = rowItem.checked;
			var editable = rowItem.editable;
			var hroomPlanListVOs = rowItem.hroomPlanListVOs;
			hroomPlanListVOs = isNull(hroomPlanListVOs) ? (new Array()) : hroomPlanListVOs ;
			var msg = "" ;
			if(isNull(codeId)){
				msg+='代码,';
			}
			if(isNull(codeNamec)){
				msg+='方案名称,';
			}
			if(isNull(startDate)){
				msg+='起始日期,';
			}else{
				startDate =  (startDate.length == 10 ? startDate : startDate.format("yyyy-MM-dd"));
			}
			if(isNull(endDate)){
				msg+='截止日期,';
			}else{
				endDate =  (endDate.length == 10 ? endDate : endDate.format("yyyy-MM-dd"));
			}
			if(isNull(rmplanType)){
				msg+='类型,';
			}
			if(isNull(checked)){
				msg+='选中启用,';
			}
			if(isNull(editable)){
				msg+='允许修改,';
			}
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}else{
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1),function(){});
					return ;
				}
			}
			hroomPlan = new Object;
			hroomPlan.id = id ;
			hroomPlan.codeId = codeId ;
			hroomPlan.codeNamec = codeNamec ;
			hroomPlan.status = 0 ;
			hroomPlan.checked = checked ;
			hroomPlan.editable = editable ;
			hroomPlan.rmplanType = rmplanType ;
			addOrEditItemsArray.push({
				"editFlag":"u",
				"startDate":startDate,
				"endDate":endDate,
				"hroomPlan": hroomPlan,
				"hroomPlanListVOs": hroomPlanListVOs
			});
		}
	}
	// 修改
	var editItems = view.itemsEdited;
	if(editItems && editItems.length > 0){
		for(var i=0 ; i < editItems.length ; i++){
			var id = editItems[i].id ;
			// 如果该条目之前修改过房间特征  则不重复更新操作
			if(updRoomPlanIds.indexOf(id) != -1)
				continue ;
			var codeId = editItems[i].codeId ;
			var codeNamec = editItems[i].codeNamec;
			var startDate = editItems[i].startDate ;
			var endDate = editItems[i].endDate;
			var rmplanType = editItems[i].rmplanType;
			var checked = editItems[i].checked;
			var editable = editItems[i].editable;
			var hroomPlanListVOs = editItems[i].hroomPlanListVOs;
			hroomPlanListVOs = isNull(hroomPlanListVOs) ? (new Array()) : hroomPlanListVOs ;
				
			var msg = "" ;
			if(isNull(codeId)){
				msg+='代码,';
			}
			if(isNull(codeNamec)){
				msg+='方案名称,';
			}
			if(isNull(startDate)){
				msg+='起始日期,';
			}else{
				startDate =  (startDate.length == 10 ? startDate : startDate.format("yyyy-MM-dd"));
			}
			if(isNull(endDate)){
				msg+='截止日期,';
			}else{
				endDate =  (endDate.length == 10 ? endDate : endDate.format("yyyy-MM-dd"));
			}
			if(isNull(rmplanType)){
				msg+='类型,';
			}
			if(isNull(checked)){
				msg+='选中启用,';
			}
			if(isNull(editable)){
				msg+='允许修改,';
			}
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}else{
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1),function(){});
					return ;
				}
			}
			hroomPlan = new Object;
			hroomPlan.id = id ;
			hroomPlan.codeId = codeId ;
			hroomPlan.codeNamec = codeNamec ;
			hroomPlan.status = 0 ;
			hroomPlan.checked = checked ;
			hroomPlan.editable = editable ;
			hroomPlan.rmplanType = rmplanType ;	
			addOrEditItemsArray.push({
				"editFlag":"u",
				"startDate":startDate,
				"endDate":endDate,
				"hroomPlan": hroomPlan,
				"hroomPlanListVOs": hroomPlanListVOs
			});
		}
	}	
	// 如果无任何操作 则返回	
	if(isNull(delIds) && (addOrEditItemsArray.length==0)){
		return;
	}else{
		var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
		$.ajax({
			url:"/hroomPlan/saveOrUpdateOrDel.do",
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
// 缓存房价方案列表
function saveHroomPlanList(holidayNames , hrpList){
	var id = $("#currUpdId").val();
	var row = $("#currUpdRow").val();
	row = parseInt(row);
	var fgRow = flexGrid.rows[row] ;
	// 修改
	if(id){
		// 如果当前已修改过  则不记录
		if(updRoomPlanIds.indexOf(id) == -1){
			updRoomPlanRowArray.push(row);
			updRoomPlanIds += (id + ",");
		}
		// 改变修改样式
		var indexVal = fgRow.dataItem.index ;
		fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
	}else{
		flexGrid.setCellData(row,"index","#", true , true);
	}
	fgRow.cssClass="eidt-row-blue";
	goShowButton(true, false) ;
	// 设置房价方案列表的节假日名称集合
	var holidayNamesStr = holidayNames.join(",") ;
	flexGrid.setCellData(row,"hroomPlanHolidayNames" , holidayNamesStr , true , true) ;
	fgRow.dataItem.hroomPlanListVOs = hrpList;
}
//批量改期
function batchUpdateDate(){
	var selection = getSelectionRanges();
	var len = selection.length ;
	if(len > 0 ){
		$("#batchEndDate,#batchStartDate").val("");
		$(".alertDivBg").fadeIn();
		$("#batchUpdDateAlert").fadeIn();
		//$("#batchEndDate,#batchStartDate").inputmask("yyyy-mm-dd");
		// 抵达日期
		$("#batchStartDate").focus(function() {
			WdatePicker({
				lang : 'zh-cn',
				isShowOthers : true,//是否显示其他月份
				dateFmt:"yyyy-MM-dd",
				//minDate : '#F{\'%y-%M-%d\'}' , 
				maxDate : '#F{$dp.$D(\'batchEndDate\')}',
				onpicked:function(){
					batchEndDate.focus();
				}
			});
		});
		// 离店日期
		$("#batchEndDate").focus(function() {
			WdatePicker({
				lang : 'zh-cn',
				isShowOthers : true,//是否显示其他月份
				dateFmt:"yyyy-MM-dd",
				minDate:'#F{$dp.$D(\'batchStartDate\')}',
				onpicked:function(){
					var bsd = '#F{$dp.$D(\'batchStartDate\')}';
				}
				//maxDate : '#F{$dp.$D(\'endTime\')||\'%y-%M-%d %H:%m:%s\'}'
			});
		});
	}else{
		altWaringMsg('请选择条目',function(){});
	}
}
// 执行批量改期
function goBatchUpdateDate(){
	var batchStartDate = $("#batchStartDate").val();
	var batchEndDate = $("#batchEndDate").val();
	if(isNull(batchStartDate) && isNull(batchEndDate)){
		altWaringMsg('请输入起始日期和截止日期',function(){});
	}else if(isNull(batchStartDate)){
		altWaringMsg('请输入起始日期',function(){});
	}else if(isNull(batchEndDate)){
		altWaringMsg('请输入截止日期',function(){});
	}else{
		var selection = getSelectionRanges();
		for(var i= 0;i<selection.length;i++){
			var rowNo = selection[i].rowNo ;
			var currentRow = flexGrid.rows[rowNo] ;
			var dataItem = currentRow.dataItem; 
			var indexVal = dataItem.index ;
			if(!isNull(batchStartDate))
				flexGrid.setCellData(rowNo,"startDate" , batchStartDate , true , true) ;
			if(!isNull(batchEndDate))
				flexGrid.setCellData(rowNo,"endDate" , batchEndDate , true , true) ;
			
			dataItem.index = indexVal ? ((((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal))) : "#" ;
			currentRow.cssClass="eidt-row-blue";
			
			// 如果当前已修改过  则不记录
			if(dataItem.hasOwnProperty("id")&& dataItem.id && updRoomPlanIds.indexOf(dataItem.id) == -1){
				updRoomPlanRowArray.push(rowNo);
				updRoomPlanIds += (dataItem.id + ",");
			}
		}
		goShowButton(true , false);
		closeDiv('batchUpdDateAlert');
	}
}