<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理--房间特征定义</title>
</head>

<body>
<!--banner & menu  xingli  2015-09-08-->
<%@ include file="../../header.jsp" %>
<!--secondMenu-->
<%@ include file="../secondMenu.jsp" %>
<!--secondMenu -end- -->
<!--banner & menu -END -->
<!--center xingli 2015-09-08-->
<div class="center">
    	<!--mainInformation-->
    	<div class="mainInfo">
        	<h4 class="fontWeight green margin-top-10">房间特征定义</h4>
            <div class="mainMation margin-top-5">
            	<!--table-->
            	<div class="tableDiv tabInformation" id="theGrid">
                </div>
                <!--table -END- -->
            </div>
            <!-- choice -->
			<form id="searchId" action="${ctx}/hroomCharacters/list.do" method="post">
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
			                <a class="button_03 marginLRAuto widthB60" href="javascript:my_goAdd(6);" id="add">新&nbsp;&nbsp;增</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="del">取&nbsp;&nbsp;消</a>
	                	</c:when>
	                	<c:otherwise>
	                		<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
			                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
			                <a class="button_03 marginLRAuto widthB60" 
		                	 	<c:choose>
	                				<c:when test="${empty listJson || fn:length(listJson) == 0  || fn:length(listJson) == 2 }">
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
            <!-- choice END -->
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<%@ include file="right.jsp" %>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
</div>
<!--center -END -->
<%@ include file="../../footer.jsp" %>
<script type="text/javascript">
	var array = [];
	var flexGrid ;
	// 监测 单元个是否发生改变
	var initialCellVal;
	// 有效  true 否则 无效
	var status_search = ( "${status eq 1}" == "false" ) ;
	$(function() {
		var dataJson = "" ;
		var data = eval('(' + '${listJson}' + ')');
		var count = data.length;
		for ( var i = 0; i < count; i++) {
			var codeKind = "";
			if (data[i].hroomCharacters.codeKind == 0) {
				codeKind = "可修改";
			} else if (data[i].hroomCharacters.codeKind == 1) {
				codeKind = "不可修改";
			}
			array.push({
				"index": i+1,
				"placeholderId": data[i].hroomCharacters.placeholderId,
				"placeholderId_old": data[i].hroomCharacters.placeholderId,
				//"英文名": data[i].codeNamee,
				"codeNamec": data[i].hroomCharacters.codeNamec,
				"codeKindName": codeKind,
				"codeKind": data[i].hroomCharacters.codeKind,
				"status": data[i].hroomCharacters.status,
				"referCount": data[i].referCount,
				"id": data[i].hroomCharacters.id
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
				    { header:"status",binding:"status",name:"status" ,isReadOnly:true,visible:false}, 
				    { header:"codeKind",binding:"codeKind",name:"codeKind" ,isReadOnly:true,visible:false}, 
				    { header:"placeholderId_old", binding:"placeholderId_old",name:"h_placeholderId_old",isReadOnly:true,visible:false}, 
				    { header:"序号",binding:"index",minWidth:40,width:80,name:"h_index", align:"center" ,cssClass:"fg_column_readOnly",isReadOnly:true},           
				    { header:"ID号", binding:"id",name:"h_id" , align:"center",cssClass:"fg_column_readOnly_cell",minWidth:50,width:80,isReadOnly:true }, 
				    { header:"代码", binding:"placeholderId",name:"h_placeholderId" , align:"center",minWidth:50,width:100,isReadOnly:!status_search}, 
				    //{ header:"英文名", binding:"英文名",name:"h_codeNamee" ,width:'*',isReadOnly:!status_search},
				    { header:"特征名称",binding:"codeNamec",name:"h_codeNamec" , minWidth:80,width:120,isReadOnly:!status_search},
				    { header:"状态",binding:"codeKindName", name:"h_codeKindName" , align:"center",cssClass:"fg_column_readOnly_cell",minWidth:40,width:100,isReadOnly:true},
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
		// 单元格编辑模式判定
		flexGrid.beginningEdit.addHandler(function(s, e){
			var fgCol = e.panel.columns[e.col];
			var fgRow = e.panel.rows[e.row] ;
			var dataItem = fgRow.dataItem ;
			// 该列为代码且相关房间（包括无效的房间）已应用该特征 则不允许修改
			if(fgCol.binding == "placeholderId" && (dataItem && dataItem.index)){
				// 修改状态时
				var index = dataItem.index.toString() ;
				index = index.replace(/#/g,"") ;
				if(parseInt(index) > 0 && dataItem.referCount > 0)
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
			if(fgCol.binding == "placeholderId" && !isNull(finalCellVal) && finalCellVal != '0'){
				$.ajax({
					url:"${ctx}/hroomCharacters/findBy.do",
					type:"post",
					data:{id : fgRow.dataItem.id , codeId : finalCellVal.toUpperCase()},
					dataType:"json",
					success:function(data){
						if(!data.success){
							s.setCellData(e.row,fgCol.binding,initialCellVal, true);
							altWaringMsg('代码重复，请重新编辑',function(){
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
				var fgRow = s.rows[e.row] ;
				var fgCol = s.columns[e.col];
				var temp = true ;
				var selection = getSelectionRanges();
				if(selection.length == 0){
					temp = false ;
				}else{
					for(var i= 0;i<selection.length;i++){
						var dataItem = selection[i].dataItem ;
						if((!dataItem || (dataItem.codeKind&&dataItem.codeKind == 1)|| (dataItem.referCount&&dataItem.referCount > 0) || (dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ) )){
							temp =false ;
							break ;
						}
					}
				}
				goShowButton_2(temp);
				sortEndShowStartCss();
				if(fgCol && fgCol.binding == "placeholderId"){
					flexGrid.startEditing(true , e.row , e.col);
				}
			}
		});
		// 排序结束  还原排序之前的样式
		flexGrid.onSortedColumn=function(e){
			sortEndShowStartCss();
		} ;
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
		// 双击添加行
		flexGrid.onRowAdded = function(e){
			e.panel.rows[e.row].dataItem.placeholderId = 0 ;
		};
		/* // 设置建筑物下拉代码
		var data_buildingId= eval('(' + '${buildingIds}' + ')');
		var col_buildingId = flexGrid.columns.getColumn('建筑物代码');
		col_buildingId.dataMap = new wijmo.grid.DataMap(data_buildingId, 'codeId', 'codeName'); */
		createEditor(flexGrid.columns.getColumn('placeholderId'));
	});
	function createEditor(editColumn) {
        var grid = editColumn.grid;
        grid.formatItem.addHandler(function (s, e) {
            var editRange = grid.editRange,
                column = e.panel.columns[e.col],
                row = s.rows[e.row] ;;
            // check whether this is an editing cell of the wanted column
            if (!(e.panel.cellType === wijmo.grid.CellType.Cell && column === editColumn && editRange && editRange.row === e.row && editRange.col === e.col)) {
                return;
            }

            // hide standard editor (don't remove!)
            if (e.cell.firstChild) {
                e.cell.firstChild.style.display = 'none';
            }
            // add custom editor
            var editorRoot = document.createElement('div');
            var input = new wijmo.input.InputNumber(editorRoot);
            input.min=0;
            input.max=62;
            input.format = "n0";
            input.value = grid.getCellData(e.row, e.col, false);
            input.focus();
            e.cell.appendChild(editorRoot);
            
            // cellEditEnding that updates cell with user's input
            var editEndingEH = function (s, args) {
                grid.cellEditEnding.removeHandler(editEndingEH);
                if (!args.cancel) {
                    args.cancel = true;
                    grid.setCellData(e.row, e.col, input.value);
                }
            };

            // subscribe the handler to the cellEditEnding event
            grid.cellEditEnding.addHandler(editEndingEH);
        });
    } 
	function my_goAdd(selectCol){
		var item = goAdd(selectCol);
		item.placeholderId=0;
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
				var placeholderId = addItems[i].placeholderId ;
				//var codeNamee = addItems[i].英文名 ;
				var codeNamec = addItems[i].codeNamec ;
				
				var msg = "" ;
				if(isNull(placeholderId)){
					msg+='代码,';
				}
				if(isNull(codeNamec)){
					msg+='特征名称,';
				}
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}
				addOrEditItemsArray.push({
					"placeholderId": placeholderId,
					//"codeNamee": codeNamee,
					"codeNamec": codeNamec,
					"codeKind": 0,
					"status": 0
				});
			}
		}
		
		// 修改
		var editItems = view.itemsEdited;
		if(editItems && editItems.length > 0){
			for(var i=0 ; i < editItems.length ; i++){
				var placeholderId_old = editItems[i].placeholderId_old ;
				var placeholderId = editItems[i].placeholderId ;
				var codeNamec = editItems[i].codeNamec;
				
				var msg = "" ;
				if(isNull(placeholderId)){
					msg+='代码,';
				}
				if(isNull(codeNamec)){
					msg+='特征名称,';
				}
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}
				addOrEditItemsArray.push({
					"id":editItems[i].id,
					"placeholderId": placeholderId,
					//"codeNamee": codeNamee,
					"codeNamec": codeNamec,
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
				url:"${ctx}/hroomCharacters/saveOrUpdateOrDel.do",
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
			$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/hroomCharacters/updateStatus.do');" );
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
<style>
.wj-input-group .wj-form-control.wj-numeric{
	text-align: center;
}
</style>
</html>
