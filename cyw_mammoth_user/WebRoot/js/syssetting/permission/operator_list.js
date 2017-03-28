var flexGrid ;
var view ;
var status_search ;
var initialCellVal;
var zhReg = /[\u4e00-\u9fa5]/;
var count = 0 ;
var rightsArr = [{codeId:"1" , codeName:"员工"},
                     {codeId:"2" , codeName:"领班"},
                     {codeId:"3" , codeName:"主管"},
                     {codeId:"4" , codeName:"经理"}] ;
function fillOperatorTable(listJson , groupIds , _status_search){
	status_search = _status_search;
	var data = listJson ;
	count = data.length;
	// 列表初始化数组
	var array = [];
	for ( var i = 0; i < count; i++) {
		var status = "";
		if (data[i].operator.status == 0) {
			status = "有效";
		} else if (data[i].operator.status == 1) {
			status = "无效";
		}
		array.push({
			"index": i+1,
			"operId": data[i].operator.operId,
			"id": data[i].operator.operId,
			"operName": data[i].operator.operName,
			"rights": data[i].operator.rights,
			"passwd": data[i].operator.passwd,
			"groupId": data[i].operator.groupId,
			"tel": data[i].operator.tel,
			"mobile": data[i].operator.mobile,
			"createDate": data[i].createDate,
			"note": data[i].operator.note ,
			"status": status
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
		        { header:"id",binding:"id",name:"id",isReadOnly:true,visible:false}, 
			    { header:"序号",binding:"index",name:"h_index", align:"center",cssClass:"fg_column_readOnly",isReadOnly:true, minWidth:50,width:50},           
			    { header:"账号",binding:"operId",name:"h_operId", minWidth:50 , width:100, align:"left",isReadOnly:!status_search , mask:"AAAAAA"},
			    { header:"姓名", binding:"operName",name:"h_operName" , align:"left", minWidth:50,width:100,isReadOnly:!status_search},
			    { header:"级别", binding:"rights",name:"h_rights" , align:"center", minWidth:50,width:100,isReadOnly:!status_search},
			    { header:"登录密码", binding:"passwd",name:"h_passwd" , align:"left", minWidth:90,width:180,isReadOnly:!status_search},
			    { header:"所属工作组", binding:"groupId",name:"h_groupId" , align:"left", minWidth:100,width:140,isReadOnly:!status_search},
			    { header:"办公电话", binding:"tel",name:"h_tel" , align:"left", minWidth:80,width:130,isReadOnly:!status_search},
			    { header:"私人电话", binding:"mobile",name:"h_mobile" , align:"center", minWidth:80,width:130,isReadOnly:!status_search, mask:"00000000000"},
			    { header:"备注", binding:"note",name:"h_note" , align:"left", minWidth:50,width:120,isReadOnly:!status_search},
			    { header:"注册日期", binding:"createDate",name:"h_createDate" ,cssClass:"fg_column_readOnly_cell", align:"center", minWidth:80,width:100,isReadOnly:true},
			    { header:"状态",binding:"status", name:"h_status",cssClass:"fg_column_readOnly_cell", align:"center", minWidth:40 ,width:80,isReadOnly:true}
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
	
	// 职务
	var col_rights = flexGrid.columns.getColumn('rights');
	col_rights.dataMap = new wijmo.grid.DataMap(rightsArr, 'codeId', 'codeName');
	
	// 职务
	var data_groupId= groupIds;
	var col_groupId = flexGrid.columns.getColumn('groupId');
	col_groupId.dataMap = new wijmo.grid.DataMap(data_groupId, 'codeId', 'codeName');
	
	
	// 单元格编辑模式判定
	flexGrid.beginningEdit.addHandler(function(s, e){
		var fgCol = e.panel.columns[e.col];
		var fgRow = e.panel.rows[e.row] ;
		var dataItem = fgRow.dataItem ;
		if(fgCol.binding == "operId" && !isNull(dataItem.id))
			e.cancel=true ;
		
		initialCellVal = s.getCellData(e.row, e.col, true);
	});
	flexGrid.cellEditEnded.addHandler(function(s, e){
		var fgRow = s.rows[e.row] ;
		var fgCol = s.columns[e.col] ;
		var finalCellVal = s.getCellData(e.row, e.col, true);
		if(fgCol.binding == "operId" || fgCol.binding == "mobile")
			finalCellVal = finalCellVal.replace(/_/g , '');
		s.setCellData(e.row, e.col, finalCellVal);
		var indexVal = fgRow.dataItem.index ;
		// 校验代码唯一性
		if(fgCol.binding == "operId" && !isNull(finalCellVal)){
			$.ajax({
				url:"/operator/findBy.do",
				type:"post",
				data:{id : fgRow.dataItem.id , codeId : finalCellVal},
				dataType:"json",
				success:function(data){
					if(!data.success){
						//s.setCellData(e.row,fgCol.binding,initialCellVal, true);
						altWaringMsg('账号重复，请重新编辑',function(){
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
			if((indexVal.toString()).indexOf("#")==-1){
				fgRow.dataItem.index = ("#"+indexVal);
				fgRow.cssClass="eidt-row-blue";
				goShowButton(true, true);
			}
		}else if(isNull(initialCellVal)&&!isNull(finalCellVal)){
			fgRow.dataItem.index = "#";
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true , true);
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
					if((!dataItem ||(dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ) )){
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
		// 修改
		var editItems = view.itemsEdited;
		if(editItems && editItems.length > 0){
			for(var i=0 ; i < editItems.length ; i++){
				var operId = editItems[i].operId ;
				var operName = editItems[i].operName ;
				var rights = editItems[i].rights ;
				var passwd = editItems[i].passwd ;
				var groupId = editItems[i].groupId ;
				var tel = editItems[i].tel ;
				var mobile = editItems[i].mobile ;
				var note = editItems[i].note ;
				var index = editItems[i].index ;
				index = index.replace(/#/g, "");
				if(!requireFieldValidate((index - 1), operId , operName , passwd , groupId, tel , mobile )){
					return ;
				}
				addOrEditItemsArray.push({
					"operId": operId,
					"operName": operName,
					"rights": rights,
					"passwd": passwd,
					"groupId": groupId,
					"tel": tel,
					"mobile": mobile,
					"note": note,
					"status": 0
				});
			}
		}
		// 添加
		var addItems = view.itemsAdded;
		if(addItems && addItems.length > 0){
			for(var i=0 ; i < addItems.length ; i++){
				if($.isEmptyObject(addItems[i]))continue;
				var operId = addItems[i].operId ;
				var operName = addItems[i].operName ;
				var rights = addItems[i].rights ;
				var passwd = addItems[i].passwd ;
				var groupId = addItems[i].groupId ;
				var tel = addItems[i].tel ;
				var mobile = addItems[i].mobile ;
				var note = addItems[i].note ;
				
				if(!requireFieldValidate((count+i), operId , operName , passwd , groupId, tel , mobile )){
					return ;
				}
				
				addOrEditItemsArray.push({
					"operId": operId,
					"operName": operName,
					"rights": rights,
					"passwd": passwd,
					"groupId": groupId,
					"tel": tel,
					"mobile": mobile,
					"note": note,
					"status": 0
				});
			}
		}
		if(isNull(delIds) && (addOrEditItemsArray.length==0)){
			return;
		}else{
			var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
			$.ajax({
				url:"/operator/saveOrUpdateOrDel.do",
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
		$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/operator/updateStatus.do');" );
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
function requireFieldValidate(i, operId , operName , passwd , groupId , tel , mobile ){
	var msg = "" ;
	var cellNo = 0 ;
	if(isNull(operId)){
		msg+='账号不能为空，';
		if(cellNo == 0 )
			cellNo = 2 ;
	}
	if(isNull(operName)){
		msg+='姓名不能为空，';
		if(cellNo == 0 )
			cellNo = 3 ;
	}
	if(isNull(passwd)){
		msg+='登录密码不能为空，';
		if(cellNo == 0 )
			cellNo = 5 ;
	}else{
		if(passwd.length<6||passwd.length>10||zhReg.test(passwd)){
			msg+='登录密码应该由6-10位数字、字母或符号组成，';
			if(cellNo == 0 )
				cellNo = 5 ;
		}
	}
	if(isNull(groupId)){
		msg+='所属工作组不能为空，';
		if(cellNo == 0 )
			cellNo = 6 ;
	}
	if(!isNull(tel)&&!isTel(tel)){
		msg+='办公电话应该为 XXX-XXXXXXXX或者XXXX-XXXXXXX，';
		if(cellNo == 0 )
			cellNo = 7 ;
	}
	if(!isNull(mobile)&& mobile.length!=11){
		msg+='手机号码应该有11位数字组成，';
		if(cellNo == 0 )
			cellNo = 8 ;
	}
	if(msg != ""){
		msg = msg.substring(0, msg.length-1) ;
		//msg = msg.replace(/，/g, "，\n");
		msg = ("第"+(i+1)+"行："+msg );
		altWaringMsg(msg,function(){
			flexGrid.select(i, cellNo);
		});
		return false ;
	}
	return true ;
}
function isTel(str)
{
	var result=str.match(/\d{3}-\d{8}|\d{4}-\d{7}/);
	if(result==null) return false;
	return true;
}