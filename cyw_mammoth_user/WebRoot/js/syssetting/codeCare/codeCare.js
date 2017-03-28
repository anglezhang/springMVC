var flexGrid ;
var view ;
//有效  true 否则 无效
var status_search ;
$(function() {
	/*tree*/
	$(".bodyLiTitle").click(function() {
		if($(this).hasClass("point")){
			$(this).find(".titleList").attr("src","../img/tree_02.png");
			$(this).addClass("bodyLiTitle");
			$(this).removeClass("point");
			$(".point").addClass("point");
			$(".point").removeClass("bodyLiTitle");
			$(this).next().css("display", "none");
		}else{
			$(this).find(".titleList").attr("src","../img/tree_01.png");
			$(".point").addClass("bodyLiTitle");
			$(".point").removeClass("point");
			$(this).addClass("point");
			$(this).removeClass("bodyLiTitle");
			$(this).next().css("display", "block");
		}
	});
	/*$(".scdLi li a").click(function() {
		$(".scdLi li a").removeClass("pointThisA");
		$(".scdLi li a").addClass("treeLink");
		$(this).removeClass("treeLink");
		$(this).addClass("pointThisA");
		$(this).parents().parents().prev().addClass("point");
	});*/
	/*tree-end*/
	
	// 左侧通用代码树代码
	var ul_html = "<ul class=\"scdLi\">";
	$.ajax({
		type: "POST",
		url:"/codeCare/geTypeList.do",
		dataType:"json",
		async:false,
		success:function(data){
			if(data && data.length > 0 ){
				for(var o = 0 ; o < data.length ; o ++){  
					if(data[o].codeId){
						var codeId = data[o].codeId.toString().trim() ;
						ul_html += "<li id=\"ge_tree_li_"+codeId+"\">";
						ul_html += "	<a class=\"treeLink\" href=\"javascript:findList('ge_tree_li_"+codeId+"', 'GE','"+codeId+"' , '"+data[o].codeNamec+"("+codeId+")"+"');\"> ";
						ul_html += "		<img class=\"scdLiList\" src=\"../../img/scd_list.png\"> ";
						ul_html += "		<img class=\"scdLiImg\" src=\"../../img/tree_main_li_img.png\"> ";
						ul_html += "		<span class=\"scdLiName\">"+data[o].codeNamec+"("+codeId+")"+"</span> ";
						ul_html += "	</a>";
						ul_html += "</li>";
					}
			    }  
			    ul_html += "</ul>";
			    // 追加显示内容
			    $("div#GE_div").append(ul_html);
			    var codeId0 = data[0].codeId.toString().trim() ;
			    // 默认显示通用代码第一条记录
			    findList('ge_tree_li_'+codeId0,'GE' , codeId0 , data[0].codeNamec+'('+codeId0+')');
			}
		}
	});
});
/**
查询对应分类的列表信息
type SE=特别代码  GE=通用代码
flag 数字1，2，3。。。   代表子类的标识
*/
function findList(liId , type , flag , codeClassTitle){
	// 隐藏按钮区
	goShowButton(false);
	$("a#del").css({"cursor":"not-allowed","color":"grey"});
	$("a#del").prop("href" , "javascript:;");
	
	// liId 为空时代表条件检索的时候如：有效或者无效切换
	if(liId){
		$(".splitDivLeft li").removeClass("splitPoint");
		$("#"+liId).addClass("splitPoint");
		$(".splitDivLeft li a").removeClass("pointThisA");
		$("#"+liId+" a").addClass("pointThisA");
		$("div.bodyLiTitle").removeClass("point");
		$("#"+liId).parent("ul").prev("div.bodyLiTitle").addClass("point");
	}
	// 显示具体分类的标题
	if(codeClassTitle)
		$("h4#codeClassTitle").html(codeClassTitle);
	
	// 恢复 隐藏域的值
	$("#delIds").val("");
	$("#backIds").val("");
	
	var _type = type ;
	var _flag = flag ;
	// 有效 无效
	var status = $("input[name='status']:checked").val();
	// 记录类型  和 分类标识
	$("#type").val(type);
	$("#flag").val(flag);
	
	// true 通用代码  false 特别代码
	var seOrge = ("GE" == type) ;
	// 特别代码url为 flag == (mapping value)
	var url = (seOrge ? "/codeCare/sglist.do" : ("/"+flag +"/list.do")) ;
	$.ajax({
		type: "POST",
		url: url,
		data:{type:_type,flag:_flag,status:status},
		dataType:"json",
		async:false,
		success:function(data, textStatus, jqXHR){
			// 填充flexGrid    
			// @param json数据
			// @param 条件 有效或者无效
			var evalListJson = eval('('+data.listJson+')') ;
			seOrge ? 
					fillGETable( evalListJson, data.status) : 
						(flag == 'hcountry' ? 
								fillHcountryTable(evalListJson , data.status) : 
									(flag == 'hfolk' ? 
											fillHfolkTable(evalListJson, data.status) : 
												(flag == 'hconsume' ? 
														fillHConsumeTable(evalListJson, data.status) : 
															(flag == 'hexchange' ? 
																	fillHexchangeTable(evalListJson , data.status) : 
																		(flag == 'hsettle' ? 
																				fillHsettleTable(evalListJson , data.status) : 
																					(flag == 'hsetlKind' ? 
																							fillHsetlKindTable(evalListJson , data.status) : 
																								(flag == 'hgstOri' ? 
																										fillHgstOriTable(evalListJson , data.status) : 
																											(flag == 'hgstOriType' ? 
																													fillHgstOriTypeTable(evalListJson , data.status) : 
																														("")))))))));
		},
		error:function (XMLHttpRequest, textStatus, errorThrown) {
			altEerrMsg('操作失败',function(){});
		}
	});
	var num = view.totalItemCount ;
	if(status == 0){
		$("div.choice a#add").css("display" , "") ;
		$("div.choice a#del").css("display" , "") ;
		$("div.choice a#del_0").css("display","none") ;
	}else{
		$("div.choice a#add").css("display" , "none") ;
		$("div.choice a#del").css("display" , "none") ;
		if(num==0){
			$("div.choice a#del_0").css({"display":"" , "color":"grey", "cursor":"not-allowed"}) ;
			$("div.choice a#del_0").prop("href" , "javascript:;") ;
		}else{
			$("div.choice a#del_0").css({"display":"" , "color":"", "cursor":"pointer"}) ;
			$("div.choice a#del_0").prop("href" , "javascript:goBack();") ;
		}
		
	}
}
function fillGETable(listJson , status){
	status_search = (status == 0 ? true : false );
	var array = [];	
	var data = listJson ;
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
			"id": data[i].id,
			"codeId": data[i].codeId,
			"codeId_old": data[i].codeId,
			"codeNamee": data[i].codeNamee,
			"codeNamec": data[i].codeNamec,
			"codeKindName": codeKind,
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
			    { header:"ID号", binding:"id",cssClass:"fg_column_readOnly_cell",name:"h_id" , align:"center", minWidth:50,width:60,isReadOnly:true }, 
			    { header:"代码", binding:"codeId",name:"h_codeId", align:"center", minWidth:40 ,width:80,isReadOnly:!status_search,mask:'AAA'}, 
			    { header:"英文名", binding:"codeNamee",name:"h_codeNamee" , minWidth:60,width:160,isReadOnly:!status_search},
			    { header:"中文名",binding:"codeNamec",name:"h_codeNamec" , minWidth:60, width:200,isReadOnly:!status_search},
			    { header:"状态",binding:"codeKindName",cssClass:"fg_column_readOnly_cell", name:"h_codeKindName", align:"center", minWidth:40 ,width:100,isReadOnly:true},
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
			var flag = $("#flag").val();
			var codeId = (flag.toString()+finalCellVal.toString()).toUpperCase() ;
			$.ajax({
				url:"/codeCare/findBy.do",
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
					altEerrMsg("操作失败",function(){});
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
	// 双击添加行
	/*flexGrid.onRowAdded = function(e){
		e.panel.rows[e.row].dataItem.codeKindName = "可修改" ;
	};*/
}
// 通用代码数据封装
function packHCodeData(addItems , editItems , flag ){
	var addOrEditItemsArray = [];
	if(addItems && addItems.length > 0){
		for(var i=0 ; i < addItems.length ; i++){
			if($.isEmptyObject(addItems[i]))continue;
			var codeId = addItems[i].codeId ;
			var codeNamee = addItems[i].codeNamee ;
			var codeNamec = addItems[i].codeNamec ;
			var msg = "" ;
			if(isNull(codeId)){
				msg+='代码,';
			}
			if(isNull(codeNamee)){
				msg+='英文名,';
			}
			if(isNull(codeNamec)){
				msg+='中文名,';
			}
			
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}
			addOrEditItemsArray.push({
				"codeId": flag.toString()+codeId.toString(),
				"codeNamee": codeNamee,
				"codeNamec": codeNamec,
				//"codeKind": addItems[i].codeKind,
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
			
			var msg = "" ;
			if(isNull(codeId)){
				msg+='代码,';
			}
			if(isNull(codeNamee)){
				msg+='英文名,';
			}
			if(isNull(codeNamec)){
				msg+='中文名,';
			}
			
			if(msg != ""){
				altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
				return ;
			}
		
			addOrEditItemsArray.push({
				"codeId": flag.toString()+codeId.toString(),
				"codeNamee": codeNamee,
				"codeNamec": codeNamec,
				"codeKind": editItems[i].codeKind,
				"status": 0,
				"id":editItems[i].id
			});
		}
	}
	return addOrEditItemsArray ;
}

//控制显示操作按钮 true 可用  否则 不可用
function goShowButton(bool){
	if(bool){
		$("a#ok,a#cancel").css({"cursor":"pointer","color":"inherit"});
		
		$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack();" );
		$("#cancel").attr("href","javascript:giveUpEdit();");
	}else{
		$("a#ok,a#cancel").css({"cursor":"not-allowed","color":"grey"});
		
		$("#ok").attr("href","javascript:;");
		$("#cancel").attr("href","javascript:;");
	}
}
function goAdd(){
	goShowButton(true);
	var item = view.addNew();
	var itemCount = view.itemCount ;
	flexGrid.select(new wijmo.grid.CellRange(itemCount-1, 6, itemCount-1, 6), true);
	flexGrid.focus();
	//item.codeKindName="可修改";
}
function giveUpEdit(){
	view.clearChanges();
	view.cancelEdit();
	view.refresh();
	flexGrid.refresh();
	getSearch(0);
	return ;
}
function goDel(ifCodeKind) {
	var tempFlag = true ;
	// status_search   有效数据才有删除功能
	var selection = getSelectionRanges();
	// 有效  允许删除
	if(status_search){
		var _delIds = $("#delIds").val() ;
		var ids = "" ;
		if(selection.length>0){
			altInfMsg("确定取消所选择条目吗？",function(){
				for(var i= 0;i<selection.length;i++){
					var rowNo = selection[i].rowNo 
					var dataItem = selection[i].dataItem ;
					var fgRow = flexGrid.rows[rowNo] ;
					fgRow.isReadOnly=true;
					var id = dataItem ? dataItem.id : null ;
					var codeKind = dataItem ? dataItem.codeKind : null ;
					if(id){
						// TODO 整行选中并且选中行状态为 可修改即用户添加的条目
						if(fgRow.isSelected && ((ifCodeKind && codeKind == 0 ) || !ifCodeKind )){
							var indexVal = fgRow.dataItem.index ;
							indexVal = indexVal.toString().replace(/#/g , '') ;
							// 设置样式
							fgRow.dataItem.index = (((indexVal.toString()).indexOf("*")==-1) ? ("*"+indexVal) : (indexVal)) ;
							fgRow.cssClass="select-row-yellow";
							ids+= (id+",") ;
						}
					}
					// else 新增的空白行  执行删除操作
					else{
						view.removeAt(rowNo);
					}
		        }
				goShowButton(true);
				// 取消的条目禁止再次点击取消按钮
				$("a#del").css({"cursor":"not-allowed","color":"grey"});
				$("#del").attr("href","javascript:;");		
				// 记录选中删除的id
				$("#delIds").val(isNull(_delIds) ? ids : (_delIds+ids));
			},function(){});
		}
	}
}
function goBack() {
	var selection = getSelectionRanges();
	if(selection.length>0){
		var tempFlag = true ;
		var _backIds = $("#backIds").val() ;
		var ids = "" ;
		altInfMsg("确定恢复所选择条目吗？",function(){
			for(var i= 0;i<selection.length;i++){
				var rowNo = selection[i].rowNo 
				var dataItem = selection[i].dataItem ;
				var fgRow = flexGrid.rows[rowNo] ;
				fgRow.isReadOnly=true;
				var id = dataItem ? dataItem.id : null ;
				if(id){
					if(fgRow.isSelected){
						var indexVal = fgRow.dataItem.index ;
						// 设置样式
						fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
						fgRow.cssClass="eidt-row-blue";
						ids+=(id+",") ;
					}
				}
				else{
					view.removeAt(rowNo);
				}
			}
			goShowButton(true);
			// 记录选中恢复的id
			$("#backIds").val(isNull(_backIds) ? ids : (_backIds+ids));
		},function(){});
		
	}
}
function goOk(){
	
	outCursor();
	
	// 删除
	var delIds = $("#delIds").val();
	// 获取当前分类标识
	var flag = $("#flag").val();
	// 获取大类
	var type = $("#type").val();
	
	var removeItems = view.itemsRemoved;
	if(removeItems && removeItems.length > 0){
		for(var i=0 ; i < removeItems.length ; i++){
			delIds+=(","+removeItems[i].id) ;
		}
	}
	// 添加
	var addItems = view.itemsAdded;
	// 修改
	var editItems = view.itemsEdited;
	var addOrEditItemsArray = [];
	var url = "" ;
	if(type == "GE"){
		url = "/codeCare/saveOrUpdateOrDel.do" ;
		addOrEditItemsArray = packHCodeData(addItems , editItems , flag);
	}else{
		url = "/"+flag+"/saveOrUpdateOrDel.do" ;
		if(flag == "hcountry"){
			addOrEditItemsArray = packHcountryData(addItems , editItems);
		}else if(flag == "hfolk"){
			addOrEditItemsArray = packHfolkData(addItems , editItems);
		}else if(flag == "hconsume"){
			addOrEditItemsArray = packHConsumeData(addItems , editItems);
		}else if(flag == "hexchange"){
			addOrEditItemsArray = packHexchangeData(addItems , editItems);
		}else if(flag == "hsettle"){
			addOrEditItemsArray = packHsettleData(addItems , editItems);
		}else if(flag == "hsetlKind"){
			addOrEditItemsArray = packHsetlKindData(addItems , editItems);
		}else if(flag == "hgstOri"){
			addOrEditItemsArray = packHgstOriData(addItems , editItems);
		}else if(flag == "hgstOriType"){
			addOrEditItemsArray = packHgstOriTypeData(addItems , editItems);
		}
	}
	if(isNull(delIds) && (addOrEditItemsArray.length==0)){
		return;
	}else{
		var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
		$.ajax({
			url:url,
			type:"post",
			data:{params : addOrEditItemsData , delIds : delIds.replace(/undefined/g, "") },
			dataType:"json",
			success:function(data){
				if(data.success){
					addOrEditItemsArray = null;
					// 查询有效列表
					getSearch(0);
				}else{
					altWaringMsg(data.msg,function(){});
				}
			},
			error:function(err){
				altEerrMsg("操作失败!",function(){});
			}
		});
	}
}
function goOkForBack(){
	// 获取当前分类标识
	var flag = $("#flag").val();
	// 获取大类
	var type = $("#type").val();
	// 恢复id
	var backIds = $("#backIds").val();
	
	var url = "" ;
	if(type == "GE"){
		url = "/codeCare/updateStatus.do" ;
	}else{
		url = "/"+flag+"/updateStatus.do" ;
	}
	if(isNull(backIds)){
		return;
	}else{
		$.ajax({
			url:url,
			type:"post",
			data:"backIds="+backIds,
			dataType:"json",
			success:function(data){
				if(data.success){
					// 查询无效列表
					getSearch(1);
				}else{
					altEerrMsg("操作失败!",function(){});
				}
			},
			error:function(err){
				altEerrMsg("操作失败!",function(){});
			}
		});
	}
}
function getSearch(status) {
	var type = $("input#type").val() ;
	var flag = $("input#flag").val() ;
	findList(null , type, flag , null);
}
// 起初光标到第一行
function outCursor(){
	flexGrid.focus();
	var itemCount = view ? view.itemCount : 0 ;
	flexGrid.select(itemCount, 1);
}
//如果flexGrid的某列处于排序状态的话，那么排序结束后，还原排序钱的样式
function sortEndShowStartCss(){
	var rows = flexGrid.rows ;
	var dataItem ;
	var index ;
	for (var i = 0; i < view.itemCount; i++) {
		dataItem = rows[i] ? rows[i].dataItem : null ;
		index = dataItem ? (dataItem.index?dataItem.index.toString():null) : null ;
		if(index){
			if(index.indexOf("*")!=-1){
				if(rows[i].cssClass!="select-row-yellow")
					rows[i].cssClass="select-row-yellow";
			}else if(index.indexOf("#")!=-1){
				if(rows[i].cssClass!="eidt-row-blue")
					rows[i].cssClass="eidt-row-blue";
			}
		}
	}
}
// 获取选中区域
function getSelectionRanges() {
	var selectRangesArr = [] ;
	for(var r=0;r<flexGrid.rows.length;r++)
	{
	    if(flexGrid.rows[r].isSelected)
	    {
	        var item=flexGrid.rows[r].dataItem;
	        selectRangesArr.push({
	        	rowNo:r,
	        	dataItem:item
	        });
	    }
	}
	return selectRangesArr ;
}