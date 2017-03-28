<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../../include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理--钟点房设置</title>
</head>

<body>
	<%@ include file="../../header.jsp"%>
	<!--secondMenu-->
	<%@ include file="../secondMenu.jsp"%>
	<!--secondMenu -end- -->
	<div class="center">
		<!--mainInformation-->
		<div class="mainInfo">
			<h4 class="fontWeight green margin-top-10">周末及节日设置</h4>
			<div class="mainMation margin-top-5">
				<!--table-->
            	<div class="tableDiv tabInformation" id="theGrid" ></div>
                <!--table -END- -->
			</div>
			<c:set var="statusVal" value="${status eq 0 or empty status}" />
			<c:set var="mon" value="${week[0] eq 0}" />
			<c:set var="tue" value="${week[1] eq 0}" />
			<c:set var="wed" value="${week[2] eq 0}" />
			<c:set var="thu" value="${week[3] eq 0}" />
			<c:set var="fri" value="${week[4] eq 0}" />
			<c:set var="sat" value="${week[5] eq 0}" />
			<c:set var="sun" value="${week[6] eq 0}" />
			<!-- choice -->
  			<div class="choice">
       	 		<div class="tableDiv floatL margin-left-10 widthB90 margin-top-15 positionR">
                	<div class="jsWord">默认周末设置</div>
                	<form id="weekForm" action="" method="post">
					<input type="hidden" id="week" value="${week[0]},${week[1]},${week[2]},${week[3]},${week[4]},${week[5]},${week[6]}">
                	<table class="widthB90 marginLRAuto margin-top-20 margin-bottom-20">
                    	<tr>
                            <td width="58%" height="30" align="center">周一</td>
                            <td align="left">
                            	<label>
	                            	<input class="radio" type="checkbox" name="radio1" value="0"
		                            	<c:if test="${mon}"> checked="checked"</c:if> 
		                            	<c:if test="${not statusVal}"> disabled="disabled"</c:if>>平日
		                        </label>
	                        </td>
                            <%-- <td align="left">
                            	<label>
	                            	<input class="radio" type="radio" name="radio1" value="1"  
	                            		<c:if test="${not mon}"> checked="checked"</c:if> 
	                            		<c:if test="${not statusVal}"> disabled="disabled"</c:if>>周末
	                            </label>
	                        </td> --%>
                        </tr>
                        <tr>
                            <td align="center" height="30">周二</td>
                            <td align="left">
                            	<label>
	                            	<input class="radio" type="checkbox" name="radio2" value="0"
		                            	<c:if test="${tue}"> checked="checked"</c:if> 
		                            	<c:if test="${not statusVal}"> disabled="disabled"</c:if>>平日
		                        </label>
	                        </td>
                            <%-- <td align="left">
                            	<label>
	                            	<input class="radio" type="radio" name="radio2" value="1"  
	                            		<c:if test="${not tue}"> checked="checked"</c:if> 
	                            		<c:if test="${not statusVal}"> disabled="disabled"</c:if>>周末
	                            </label>
	                        </td> --%>
                        </tr>
                        <tr>
                            <td align="center" height="30">周三</td>
                            <td align="left">
                            	<label>
	                            	<input class="radio" type="checkbox" name="radio3" value="0"  
		                            	<c:if test="${wed}"> checked="checked"</c:if> 
		                            	<c:if test="${not statusVal}"> disabled="disabled"</c:if>>平日
		                        </label>
	                        </td>
                            <%-- <td align="left">
                            	<label>
	                            	<input class="radio" type="radio" name="radio3" value="1"  
	                            		<c:if test="${not wed}"> checked="checked"</c:if> 
	                            		<c:if test="${not statusVal}"> disabled="disabled"</c:if>>周末
	                            </label>
	                        </td> --%>
                        </tr>
                        <tr>
                            <td align="center" height="30">周四</td>
                            <td align="left">
                            	<label>
	                            	<input class="radio" type="checkbox" name="radio4" value="0" 
		                            	<c:if test="${thu}"> checked="checked"</c:if> 
		                            	<c:if test="${not statusVal}"> disabled="disabled"</c:if>>平日
		                        </label>
	                        </td>
                            <%-- <td align="left">
                            	<label>
	                            	<input class="radio" type="radio" name="radio4" value="1"  
	                            		<c:if test="${not thu}"> checked="checked"</c:if> 
	                            		<c:if test="${not statusVal}"> disabled="disabled"</c:if>>周末
	                            </label>
	                        </td> --%>
                        </tr>
                        <tr>
                            <td align="center" height="30">周五</td>
                            <td align="left">
                            	<label>
	                            	<input class="radio" type="checkbox" name="radio5" value="0" 
		                            	<c:if test="${fri}"> checked="checked"</c:if> 
		                            	<c:if test="${not statusVal}"> disabled="disabled"</c:if>>平日
		                        </label>
	                        </td>
                            <%-- <td align="left">
                            	<label>
	                            	<input class="radio" type="radio" name="radio5" value="1"  
	                            	<c:if test="${not fri}"> checked="checked"</c:if> 
	                            		<c:if test="${not statusVal}"> disabled="disabled"</c:if>>周末
	                            </label>
	                        </td> --%>
                        </tr>
                        <tr>
                            <td align="center" height="30">周六</td>
                            <td align="left">
                            	<label>
	                            	<input class="radio" type="checkbox" name="radio6" value="0" 
		                            	<c:if test="${sat}"> checked="checked"</c:if> 
		                            	<c:if test="${not statusVal}"> disabled="disabled"</c:if>>平日
		                        </label>
	                        </td>
                            <%-- <td align="left">
                            	<label>
	                            	<input class="radio" type="radio" name="radio6" value="1"  
	                            	<c:if test="${not sat}"> checked="checked"</c:if> 
	                            		<c:if test="${not statusVal}"> disabled="disabled"</c:if>>周末
	                            </label>
	                        </td> --%>
                        </tr>
                        <tr>
                            <td align="center" height="30">周日</td>
                            <td align="left">
                            	<label>
	                            	<input class="radio" type="checkbox" name="radio7" value="0" 
		                            	<c:if test="${sun}"> checked="checked"</c:if> 
		                            	<c:if test="${not statusVal}"> disabled="disabled"</c:if>>平日
		                        </label>
	                        </td>
                            <%-- <td align="left">
                            	<label>
	                            	<input class="radio" type="radio" name="radio7" value="1" 
	                            		<c:if test="${not sun}"> checked="checked"</c:if> 
	                            		<c:if test="${not statusVal}"> disabled="disabled"</c:if>>周末
	                            </label>
	                        </td> --%>
                        </tr>
                    </table>
                    </form>
                </div>
                <form id="searchId" action="${ctx}/holidays/list.do" method="post">
		            <div class="choice" style="width:93%;">
                        <table class="widthB70 marginLRAuto margin-top-30 margin-bottom-30">
		                    <tr>
		                        <td width="50%" align="center">
		                        	<%-- <label>
		                        		<input name="status" type="radio" value="0"
											<c:if test="${statusVal}">checked</c:if>
											onclick="getSearch()" />有效
									</label>
		                        </td>
		                        <td align="center">
		                        	<label>
		                        		<input name="status" type="radio" value="1"
											<c:if test="${not statusVal}">checked</c:if>
											onclick="getSearch()">无效
		                        	</label> --%>
		                        </td>
		                    </tr>
		                </table>
		                <c:choose>
		                	<c:when test="${statusVal}">
			                	<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
				                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
				                <a class="button_03 marginLRAuto widthB60" href="javascript:goAdd(3);" id="add">新&nbsp;&nbsp;增</a>
				                <a class="button_03 marginLRAuto widthB60" href="javascript:goDel();" id="del">取&nbsp;&nbsp;消</a>
		                	</c:when>
		                	<c:otherwise>
		                		<a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="ok">确&nbsp;&nbsp;定</a>
				                <a class="button_03 marginLRAuto widthB60" href="javascript:;" style="color: grey;cursor:not-allowed;" id="cancel">放&nbsp;&nbsp;弃</a>
				                <a class="button_03 marginLRAuto widthB60" 
				                	 <c:choose>
		                				<c:when test="${empty listJson || fn:length(listJson) == 0  || fn:length(listJson) == 2 }">
		                					style="color: grey;cursor:not-allowed;"
				                			href="javascript:goBack();"
		                				</c:when>
		                				<c:otherwise>
		                					href="javascript:;"
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
	// 列表初始化数组
	var array = [];
	var view ;
	var flexGrid ;
	// 有效  true 否则 无效
	var status_search = ( "${status eq 1}" == "false" ) ;
	// 监测 单元个是否发生改变
	var initialCellVal;
	// week defalutValue
	$(function() {
		//var count = '${fn:length(buildList)}';
		var dataJson = "" ;
		var data = eval('(' + '${listJson}' + ')');
		var count = data.length;
		for ( var i = 0; i < count; i++) {
			var dtArr = data[i].holidayDate.split("-");
			var holidayDate = new Date(dtArr[0], parseInt(dtArr[1])-1, dtArr[2]) ;
			array.push({
				"index": i+1,
				"holidayDate": holidayDate,
				"holidayWeek": getWeekBy(holidayDate.getDay()),
				"holidayId": data[i].holidayId,
				"status": data[i].status,
				"id": data[i].id
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
				    { header:"序号",binding:"index",cssClass:"fg_column_readOnly",name:"h_index", align:"center", minWidth:40,width:80 ,isReadOnly:true},           
				    { header:"日期", binding:"holidayDate",name:"h_holidayDate", align:"center", minWidth:40 ,width:120,isReadOnly:!status_search,format:"yyyy-MM-dd"}, 
				    { header:" ", binding:"holidayWeek",name:"h_holidayWeek", align:"center", minWidth:40 ,width:60,isReadOnly:true}, 
				    { header:"节日", binding:"holidayId",name:"h_holidayId" , align:"center", minWidth:60,width:100,isReadOnly:!status_search},
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
		//flexGrid.startEditing(true , 2 , 3);
		// 设置建筑物下拉代码
		var data_hcodes= eval('(' + '${hcodes}' + ')');
		var col_hcodes = flexGrid.columns.getColumn('holidayId');
		col_hcodes.dataMap = new wijmo.grid.DataMap(data_hcodes, 'codeId', 'codeNamec');
		// cell 编辑结束后 显示按钮区
		/* flexGrid.onCellEditEnding=function(sender,e){
			//goShowButton(true);
		}; */
		
		flexGrid.beginningEdit.addHandler(function(s, e){
			initialCellVal = s.getCellData(e.row, e.col, true);
		});
		flexGrid.cellEditEnded.addHandler(function(s, e){
			var fgRow = s.rows[e.row] ;
			var fgCol = s.columns[e.col];
			var finalCellVal = s.getCellData(e.row, e.col, true);
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
				goShowButton(true, true);
			}
			if(fgCol.binding == "holidayDate" && !isNull(finalCellVal)){
				$.ajax({
					url:"${ctx}/holidays/findBy.do",
					type:"post",
					data:{id : fgRow.dataItem.id , holidayDate : finalCellVal},
					dataType:"json",
					success:function(data){
						if(!data.success){
							//s.setCellData(e.row,fgCol.binding,initialCellVal, true);
							altWaringMsg('日期重复，请重新编辑',function(){
						    	flexGrid.startEditing(true , e.row , e.col);
							    e.cancel = true;
							});
						}else{
							var dtArr = finalCellVal.split("-");
							var holidayDate = new Date(dtArr[0], parseInt(dtArr[1])-1, dtArr[2]) ;
							fgRow.dataItem.holidayWeek = getWeekBy(holidayDate.getDay()) ;
						}
					},
					error:function(err){
						altEerrMsg("操作失败!",function(){});
					}
				});
			}
		});
		
		// week radio 点击事件 
		$("table tr td input.radio").click(function(){
			var week = $("#week").val();
			var weekCheck = getWeekRadioCheckedVal();
			if(week != weekCheck)
				goShowButton(true, false);
			else
				goShowButton(false, false);
		});
		flexGrid.selectionChanged.addHandler(function(s, e){
			if(status_search){
				//选中区域是否包含 不可修改的行或者空行  true 包含 否则不包含
				var temp = true ;
				var fgRow = s.rows[e.row] ;
				var fgCol = s.columns[e.col];
				
				if(fgCol && fgCol.binding == "holidayDate"){
					flexGrid.startEditing(true , e.row , e.col);
				}
				
				var selection = getSelectionRanges();
				if(selection.length == 0){
					temp = false ;
				}else{
					for(var i= 0;i<selection.length;i++){
						var dataItem = selection[i].dataItem ;
						// 有效状态下 状态为不可修改或者房型(有效的房型)数量大于0时候  不允许取消
						if((!dataItem 
							|| (dataItem.codeKind&&dataItem.codeKind == 1) 
							|| (dataItem.roomTypeNum&&dataItem.roomTypeNum > 0) 
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
		// 排序结束  还原排序之前的样式
		flexGrid.onSortedColumn=function(e){
			sortEndShowStartCss();
		} ;
		createEditor(flexGrid.columns.getColumn('holidayDate'));
	});
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
            /*var editorRoot = document.createElement('div');
            var input;
            if (column.dataType === wijmo.DataType.Date) {
                input = new wijmo.input.InputDate(editorRoot);
            } else if (column.binding == "状态") {
                // as an ICollectionView
                var countries = ['已修改','未修改'];
                var combobox = new wijmo.input.ComboBox(editorRoot, {
                    itemsSource: countries,
                });
                input = combobox;
            }
            else {
                input = new wijmo.input.InputNumber(editorRoot);
                input.step = 1;
                input.format = editColumn.format;
            }
            e.cell.appendChild(editorRoot);
            input.value = grid.getCellData(e.row, e.col, false);*/

			var editorRoot = "<input type=\"text\" id=\"holidayDate_"+e.row+"\"class=\" wj-grid-editor wj-form-control\" style=\"text-align: center;\">";
			e.cell.innerHTML = editorRoot;
			var ttrr = $("#holidayDate_"+e.row).inputmask("yyyy-mm-dd");
			$("#holidayDate_"+e.row).focus();
			$("#holidayDate_"+e.row).val(grid.getCellData(e.row, e.col, true));

            // cellEditEnding that updates cell with user's input
            var editEndingEH = function (s, args) {
                grid.cellEditEnding.removeHandler(editEndingEH);
                if (!args.cancel) {
                    args.cancel = true;
                    /*if (args.col == 4) {
                        cellValue = input.selectedItem;
                        grid.setCellData(e.row, e.col, cellValue);
                    }
                    else {*/
                        grid.setCellData(e.row, e.col, $("#holidayDate_"+e.row).val());
                    //}
                }
            };

            // subscribe the handler to the cellEditEnding event
            grid.cellEditEnding.addHandler(editEndingEH);
        });
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
				var holidayDate = addItems[i].holidayDate ;
				var holidayId = addItems[i].holidayId ;
				
				var msg = "" ;
				if(isNull(holidayDate)){
					msg+='日期,';
				}else{
					holidayDate = (holidayDate.length == 10 ? holidayDate : holidayDate.format("yyyy-MM-dd"));
				}
				if(isNull(holidayId)){
					msg+='节日,';
				}
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}else{
					if(!isSimpleDate(holidayDate)){
						msg+='日期不合法,';
					}
					if(msg != ""){
						altWaringMsg(msg.substring(0, msg.length-1),function(){});
						return ;
					}
				}
				
				addOrEditItemsArray.push({
					"holidayId": holidayId,
					"holidayDate": holidayDate,
					"status":0
				});
			}
		}
		
		// 修改
		var editItems = view.itemsEdited;
		if(editItems && editItems.length > 0){
			for(var i=0 ; i < editItems.length ; i++){
			
				var holidayDate = editItems[i].holidayDate ;
				var holidayId = editItems[i].holidayId ;
				var msg = "" ;
				if(isNull(holidayDate)){
					msg+='日期,';
				}else{
					holidayDate = (holidayDate.length == 10 ? holidayDate : holidayDate.format("yyyy-MM-dd"));
				}
				if(isNull(holidayId)){
					msg+='节日,';
				}
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}else{
					if(!isSimpleDate(holidayDate)){
						msg+='日期不合法,';
					}
					if(msg != ""){
						altWaringMsg(msg.substring(0, msg.length-1),function(){});
						return ;
					}
				}
			
				addOrEditItemsArray.push({
					"holidayDate": holidayDate,
					"status":0,
					"holidayId": holidayId,
					"id":editItems[i].id
				});
			}
		}
		
		// 默认周末设置
		var scopeWeek = $("#week").val() ; 
		var week = getWeekRadioCheckedVal();
		// 如果没有修改 则返回
		if(isNull(delIds) && (addOrEditItemsArray.length==0)&& (scopeWeek === week)){
			return;
		}else{
			var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
			$.ajax({
				url:"${ctx}/holidays/saveOrUpdateOrDel.do",
				type:"post",
				data:{params : addOrEditItemsData , delIds : delIds.replace(/undefined/g, ""), week : (scopeWeek === week ? "" : week) },
				dataType:"json",
				success:function(data){
					if(data.success){
						addOrEditItemsArray = null;
						window.location.href="${ctx}/holidays/list.do" ;
					}else{
						altEerrMsg("操作失败!",function(){});
					}
				},
				error:function(err){
					altEerrMsg("出现异常!",function(){});
				}
			});
		}
	}
	function getWeekRadioCheckedVal(){
		var week = "" ; 
		$("table tr td input.radio").each(function(index, element) 
		{
			var check = $(element).prop("checked");
			if(check){
				week += ($(element).val()+",");
			}else{
				week += ("1,");
			}
		});
		return week.substring(0, week.length-1) ;
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
			$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/holidays/updateStatus.do');" );
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