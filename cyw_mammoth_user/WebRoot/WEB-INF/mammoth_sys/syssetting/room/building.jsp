<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理--建筑定义</title>
</head>

<body>
	<%@ include file="../../header.jsp"%>
	<!--secondMenu-->
	<%@ include file="../secondMenu.jsp"%>
	<!--secondMenu -end- -->
	<div class="center">
		<!--mainInformation-->
		<div class="mainInfo">
			<h4 class="fontWeight green margin-top-10">建筑定义</h4>
			<div class="mainMation margin-top-5">
				<!--table-->
				<div class="tableDiv tabInformation" id="theGrid"></div>
				<!--table -END- -->
			</div>
			<!-- choice -->
			<form id="searchId" action="${ctx}/building/list.do" method="post">
            <div class="choice">
                <table class="widthB70 marginLRAuto margin-top-30 margin-bottom-30">
                    <tr>
                        <td width="50%" align="center">
                        	<label>
                        		<input name="status" type="radio" value="0"
									<c:if test="${status eq 0 or empty status}">checked</c:if>
									onclick="getSearch()" />有效
							</label>
                        </td>
                        <td align="center">
                        	<label>
                        		<input name="status" type="radio" value="1"
									<c:if test="${status eq 1}">checked</c:if>
									onclick="getSearch()">无效
                        	</label>
                        </td>
                    </tr>
                </table>
                <c:choose>
                	<c:when test="${status eq 0 or empty status}">
	                	<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
		                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
		                <a class="button_03 marginLRAuto widthB60" href="javascript:goAdd(6);" id="add">新&nbsp;&nbsp;增</a>
		                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="del">取&nbsp;&nbsp;消</a>
                	</c:when>
                	<c:otherwise>
                		<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
		                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
        			    <a class="button_03 marginLRAuto widthB60" 
		                	 <c:choose>
                				<c:when test="${empty buildListJson || fn:length(buildListJson) == 0  || fn:length(buildListJson) == 2 }">
                					style="color: grey;cursor:not-allowed;"
                					href="javascript:;"
                				</c:when>
                				<c:otherwise>
                					href="javascript:goBack();"
		                		</c:otherwise>
	                		</c:choose>
		                	id="back">恢&nbsp;&nbsp;复</a>
                	</c:otherwise>
                </c:choose>
            </div>
			</form>
			<input type="hidden" id="delIds" name="delIds" />
			<input type="hidden" id="backIds" name="backIds" />
		</div>
		<!--mainInformation END-->
		<!--mainRightMenu-->
		<%@ include file="right.jsp"%>
		<div class="clearBoth"></div>
		<!--mainRightMenu END-->
	</div>
	<!--center -END -->
	<%@ include file="../../footer.jsp"%>
	<script type="text/javascript">
	var array = [];
	var view ;
	var flexGrid ;
	// 有效  true 否则 无效
	var status_search = ( "${status eq 1}" == "false" ) ;
	var count = 0 ;
	$(function() {
		//var count = '${fn:length(buildList)}';
		var dataJson = "" ;
		var data = eval('(' + '${buildListJson}' + ')');
		count = data.length;
		for ( var i = 0; i < count; i++) {
			var codeKind = "";
			if (data[i].hbuilding.codeKind == 0) {
				codeKind = "可修改";
			} else if (data[i].hbuilding.codeKind == 1) {
				codeKind = "不可修改";
			}
			array.push({
				"index": i+1,
				"codeId": data[i].hbuilding.codeId,
				"codeId_old": data[i].hbuilding.codeId,
				"codeNamee": data[i].hbuilding.codeNamee,
				"codeNamec": data[i].hbuilding.codeNamec,
				"minNum": data[i].hbuilding.minNum,
				"maxNum": data[i].hbuilding.maxNum,
				"codeKindName": codeKind,
				"codeKind": data[i].hbuilding.codeKind,
				"status": data[i].hbuilding.status,
				"roomTypeNum": data[i].roomTypeNum,
				"referCount": data[i].referCount,
				"id": data[i].hbuilding.id
			});
		}
		// create CollectionView on the data (to get events)
		view = new wijmo.collections.CollectionView(array);
		
		view.trackChanges = true;
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
				    { header:"代码", binding:"codeId",name:"h_codeId", align:"center", minWidth:40 ,width:60,isReadOnly:!status_search,mask:'00'}, 
				    { header:"英文名", binding:"codeNamee",name:"h_codeNamee" , minWidth:60,width:160,isReadOnly:!status_search},
				    { header:"中文名",binding:"codeNamec",name:"h_codeNamec" , minWidth:60, width:120,isReadOnly:!status_search},
				    { header:"最小楼层",binding:"minNum",name:"h_minNum", align:"center" , minWidth:80,width:90,isReadOnly:!status_search,mask:'000'},
				    { header:"最大楼层", binding: "maxNum",name:"h_maxNum", align:"center" , minWidth:80,width:90,isReadOnly:!status_search,mask:'000'},
				    { header:"状态",binding:"codeKindName",cssClass:"fg_column_readOnly_cell", name:"h_codeKindName", align:"center", minWidth:40 ,width:100,isReadOnly:true},
				    { header:"roomTypeNum", binding:"roomTypeNum",name:"h_roomTypeNum",isReadOnly:true,visible:false}, 
				    { header:"referCount", binding:"referCount",name:"h_referCount",isReadOnly:true,visible:false}, 
				    { header:"",binding:"", name:"", align:"center" ,width:'*',isReadOnly:true}
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
		// 监测 单元个是否发生改变
		var initialCellVal;
		// 单元格编辑模式判定
		flexGrid.beginningEdit.addHandler(function(s, e){
			var fgCol = e.panel.columns[e.col];
			var fgRow = e.panel.rows[e.row] ;
			var dataItem = fgRow.dataItem ;
			// 该建筑下相关房型数量（包含无效）> 0  则不允许修改
			if(fgCol.binding == "codeId" && (dataItem && dataItem.index)){
				// 修改状态时
				var index = dataItem.index.toString() ;
				index = index.replace(/#/g,"") ;
				if(parseInt(index) > 0 && dataItem.referCount > 0)
					e.cancel = true;
			}
			
			initialCellVal = s.getCellData(e.row, e.col, true);
			if((fgCol.binding == "codeId"||fgCol.binding == 'minNum'||fgCol.binding == 'maxNum') && !isNull(initialCellVal))
				initialCellVal = initialCellVal.toString().trim().replace(/_/g , '');
			/* var fgRow = s.rows[e.row] ;
			var fgCol = s.columns[e.col] ;
			var colName = fgCol.name ;
			var dataItem = fgRow.dataItem ;
			// 根据key获取对象中的值 1.eval("obj."+key) 2.dataItem[colName]*/
		});
		flexGrid.cellEditEnded.addHandler(function(s, e){
			var fgRow = s.rows[e.row] ;
			var fgCol = s.columns[e.col] ;
			var finalCellVal = s.getCellData(e.row, e.col, true);
			if((fgCol.binding == "codeId"||fgCol.binding == 'minNum'||fgCol.binding == 'maxNum') && !isNull(finalCellVal)){
				finalCellVal = finalCellVal.toString().trim().replace(/_/g , '');
				s.setCellData(e.row, e.col, finalCellVal);
			}
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
				goShowButton(true , true);
			}
			// 校验代码唯一性
			if(fgCol.binding == "codeId" && !isNull(finalCellVal)){
				if(finalCellVal.toString().trim().length == 2){
					var flag = false ;
					$.ajax({
						url:"${ctx}/building/findBy.do",
						type:"post",
						data:{id : fgRow.dataItem.id , codeId : finalCellVal.toUpperCase()},
						dataType:"json",
						success:function(data){
							if(!data.success){
								//s.setCellData(e.row,fgCol.binding,initialCellVal, true);
								altWaringMsg('代码重复，请重新编辑',function(){
								    //flexGrid.select(e.row,e.col);
								    goShowButton_1(false);
							    	flexGrid.startEditing(false , e.row , e.col);
								    e.cancel = true;
								});
							}
						},
						error:function(err){
							altEerrMsg('出现异常',function(){});
						}
					});
				}else{
					altWaringMsg('代码只允许输入两位数字，请重新编辑',function(){
					    //flexGrid.select(e.row,e.col);
				    	flexGrid.startEditing(false , e.row , e.col);
					    e.cancel = true;
					});
				}
				
			}
			// 最小、最大楼层
	        if (fgCol.binding == 'minNum'||fgCol.binding == 'maxNum'){
	            var minNum = fgRow.dataItem.minNum;
	            minNum = parseInt(minNum);
	            var maxNum = fgRow.dataItem.maxNum;
	            maxNum = parseInt(maxNum);
	            if(isNull(minNum) || isNull(maxNum)){}else{
	            	if(minNum > maxNum){
	            		//s.setCellData(e.row,fgCol.binding,initialCellVal, true);
	            		flexGrid.startEditing(false , e.row , e.col);
	            		finalCellVal = initialCellVal ;
	                	var content = (fgCol.binding == 'minNum') ? "最小楼层不能大于最大楼层" : "最大楼层不能小于最小楼层";
	                	altWaringMsg(content,function(){});
	                }
	            }
	            
	        }
		});
		flexGrid.selectionChanged.addHandler(function(s, e){
			if(status_search){
				//选中区域是否包含 不可修改的行或者空行  true 包含 否则不包含
				var temp = true ;
				var selection = s.selection;
				if(selection.length == 0){
					temp = false ;
				}else{
					for(var i= selection.topRow;i<=selection.bottomRow;i++){
						var dataItem = flexGrid.rows[i].dataItem ;
						// 有效状态下 状态为不可修改或者房型(有效的房型)数量大于0时候  不允许取消
						if((!dataItem 
							|| (dataItem.codeKind&&dataItem.codeKind == 1) 
							|| (dataItem.roomTypeNum&& dataItem.roomTypeNum > 0) 
							|| (dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ))){
							temp =false ;
							break ;
						}
					}
				}
				goShowButton_2(temp);
				sortEndShowStartCss();
			} 
		});
		/* flexGrid.onRowAdded=function(e){
			e.panel.columns.getColumn("codeId").isReadOnly=false;
		}; */
		/* flexGrid.onDeletingRow=function(e){
			for(var r=0;r<flexGrid.rows.length;r++)
            {
                if(flexGrid.rows[r].isSelected)
                {
                    var fgRow=flexGrid.rows[r];
                    fgRow.isReadOnly=true;
                    fgRow.cssClass="select-row-yellow";
                }
            }
			e.cancel=true;
			//var fgRow = e.panel.rows[0] ;
			
		}; */
		// 格式化自定义处理每一个单元的内容及样式
		flexGrid.itemFormatter = function(panel, r, c, cell) {
			if (panel.cellType == wijmo.grid.CellType.Cell) {
				var col = panel.columns[c];
				if (col.name == 'h_codeKindName' && cell.innerHTML == "不可修改"){
					// 设置当前行只读；
					panel.rows[r].isReadOnly=true;
					// 设置当前行禁止选中；
					//panel.rows[r].isSelected=false;
				}
			}
		}; 
		
		// 排序结束  还原排序之前的样式
		flexGrid.onSortedColumn=function(e){
			sortEndShowStartCss();
		} ;
		//设置下拉菜单
		//var codeKindArray = [{id:0,codeKindName:"可修改"},{id:1,codeKindName:"不可修改"}];
		//var col_codeKind = flexGrid.columns.getColumn('状态');
		//col_codeKind.dataMap = new wijmo.grid.DataMap(codeKindArray, 'id', 'codeKindName');
		
		/* flexGrid.gotFocus.addHandler(function (e) {
		}); */
		
		/* flexGrid.itemFormatter = function(panel, r, c, cell) {
		}; */
		
		/* flexGrid.hostElement.addEventListener('click', function (e) {
			var ht = flexGrid.hitTest(e.pageX, e.pageY);
		}); */
	});
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
			
				var codeId = editItems[i].codeId ;
				var codeNamee = editItems[i].codeNamee ;
				var codeNamec = editItems[i].codeNamec ;
				var minNum = editItems[i].minNum ;
				var maxNum = editItems[i].maxNum ;
				var index = editItems[i].index ;
				index = index.replace(/#/g, "");
				if(!requireFieldValidate((index - 1) , codeId , codeNamee , codeNamec , minNum , maxNum ))
					return ;
				
				addOrEditItemsArray.push({
					"codeId": codeId,
					"codeNamee": codeNamee,
					"codeNamec": codeNamec,
					"minNum": minNum,
					"maxNum": maxNum,
					"codeKind": editItems[i].codeKind,
					"status": 0,
					"id":editItems[i].id
				});
			}
		}
		// 添加
		var addItems = view.itemsAdded;
		if(addItems && addItems.length > 0){
			for(var i=0 ; i < addItems.length ; i++){
				if($.isEmptyObject(addItems[i]))continue;
				var codeId = addItems[i].codeId ;
				var codeNamee = addItems[i].codeNamee ;
				var codeNamec = addItems[i].codeNamec ;
				var minNum = addItems[i].minNum ;
				var maxNum = addItems[i].maxNum ;
				
				if(!requireFieldValidate((count+i) , codeId , codeNamee , codeNamec , minNum , maxNum ))
					return ;
				addOrEditItemsArray.push({
					"codeId": codeId,
					"codeNamee": codeNamee,
					"codeNamec": codeNamec,
					"minNum": minNum,
					"maxNum": maxNum,
					//"codeKind": addItems[i].codeKind,
					"codeKind": 0,
					"status": 0
				});
			}
		}
		if(isNull(delIds) && (addOrEditItemsArray.length==0)){
			return;
		}else{
			var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
			$.ajax({
				url:"${ctx}/building/saveOrUpdateOrDel.do",
				type:"post",
				data:{params : addOrEditItemsData , delIds : delIds.replace(/undefined/g, "") },
				dataType:"json",
				success:function(data){
					if(data.success){
						getSearch();
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
	function requireFieldValidate(i, codeId , codeNamee , codeNamec , minNum , maxNum){
		var msg = "" ;
		var cellNo = 0 ;
		if(isNull(codeId)){
			msg+='代码,';
			if(cellNo == 0 )
				cellNo = 6 ;
		}
		if(isNull(codeNamee)){
			msg+='英文名,';
			if(cellNo == 0 )
				cellNo = 7 ;
		}
		if(isNull(codeNamec)){
			msg+='中文名,';
			if(cellNo == 0 )
				cellNo = 8 ;
		}
		if(isNull(minNum)){
			msg+='最小楼层,';
			if(cellNo == 0 )
				cellNo = 9 ;
		}
		if(isNull(maxNum)){
			msg+='最大楼层,';
			if(cellNo == 0 )
				cellNo = 10 ;
		}
		if(msg != ""){
			altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){
				flexGrid.select(i, cellNo);
			});
			return false ;
		}
		return true ;
	}
	function goUpdate(){
		var item = view.itemsEdited;
		$.ajax({
			url:"${ctx}/building/update.do",
			type:"post",
			data:{id:item.id},
			dataType:"json",
			success:function(data){
				if(data.success){
					alertBuildingEditDiv();
				}else{
					altEerrMsg('操作失败',function(){});
				}
			},
			error:function(err){
				altEerrMsg('出现异常',function(){});
			}
		});
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
			$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/building/updateStatus.do');" );
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
</script>
<script src="${ctx}/js/syssetting/flexGridEditComm.js" type="text/javascript" ></script>
<link href="${ctx}/js/syssetting/flexGridEditComm.css" rel="stylesheet" type="text/css" />
</body>
</html>