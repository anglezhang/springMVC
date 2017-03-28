<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="../../include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>房间管理--房间定义</title>
</head>

<body>
<%@ include file="../../header.jsp" %>
<%@ include file="../secondMenu.jsp" %>
<div class="center">
    	<!--mainInformation-->
    	<div class="mainInfo">
        	<h4 class="fontWeight green margin-top-10">房间定义</h4>
            <div class="mainMation margin-top-5">
            	<!--table-->
            	<div class="tableDiv tabInformation" id="theGrid">
                </div>
                <!--table -END- -->
            </div>
            <!-- choice -->
			<form id="searchId" action="${ctx}/hroomDefine/list.do" method="post">
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
			                <a class="button_03 marginLRAuto widthB60" href="javascript:goAdd(10);" id="add">新&nbsp;&nbsp;增</a>
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
        </div>
        <!--mainInformation END-->
        <!--mainRightMenu-->
    	<%@ include file="right.jsp" %>
        <div class="clearBoth"></div>
        <!--mainRightMenu END-->
</div>
<!--center -END -->
<%@ include file="../../footer.jsp" %>

<!--弹出层阴影-->
<div class="alertDivBg"></div>
<!--弹出层阴影结束-->
<!--房间特征弹出-->
<%@ include file="../roomCharaterAlert.jspf" %>
<!--/房间特征弹出-->
<script type="text/javascript">
	// 列表初始化数组
	var array = [];
	// 修改房间特征时 记录行数
	var updCharaterRowArray = [];
	// 修改房间特征时 记录房间号
	var updCharaterRoomIds = "" ;
	var flexGrid ;
	var one = parseInt(1);
	// 有效  true 否则 无效
	var status_search = ( "${status eq 1}" === "false" ) ;
	$(function() {
		var dataJson = "" ;
		var data = eval('(' + '${listJson}' + ')');
		var count = data.length;
		for ( var i = 0; i < count; i++) {
			var status = "";
			if (data[i].room.status == 0) {
				status = "有效";
			} else if (data[i].room.status == 1) {
				status = "无效";
			}
			array.push({
				"index": i+1,
				"roomId": data[i].room.roomId,
				"roomType": data[i].room.buildId+"--"+data[i].room.roomType,
				"floorNo": data[i].room.floorNo,
				"buildId": data[i].buildingName,
				"buildMinNum": data[i].buildMinNum,
				"buildMaxNum": data[i].buildMaxNum,
				"roomCharacter": data[i].room.roomCharacter,
				"roomCharacter_temp": data[i].room.roomCharacter,
				"roomCharaterNames": data[i].roomCharaterNames,
				"currStat": data[i].room.currStat,
				"status": status,
				"id": data[i].room.roomId
			});
		}
		// create CollectionView on the data (to get events)
		view = new wijmo.collections.CollectionView(array);
		
		view.trackChanges = true;
		view.isUpdating= true;
		// initialize the grid
		flexGrid = new wijmo.grid.FlexGrid('#theGrid', {
				autoGenerateColumns: false,
				autoClipboard:true,
				selectionMode : wijmo.grid.SelectionMode.ListBox,
				columns:[
				    { header:"id",binding:"id",name:"id" , isReadOnly:true,visible:false}, 
				    { header:"currStat",binding:"currStat",name:"currStat" ,isReadOnly:true,visible:false}, 
				    { header:"buildMinNum",binding:"buildMinNum",name:"buildMinNum" ,isReadOnly:true,visible:false}, 
				    { header:"buildMaxNum",binding:"buildMaxNum",name:"buildMaxNum" ,isReadOnly:true,visible:false}, 
				    { header:"roomCharacter",binding:"roomCharacter",name:"roomCharacter" ,isReadOnly:true,visible:false}, 
				    { header:"roomCharacter_temp",binding:"roomCharacter_temp",name:"roomCharacter_temp" ,isReadOnly:true,visible:false}, 
				    { header:"序号",binding:"index",name:"h_index", align:"center",cssClass:"fg_column_readOnly",isReadOnly:true, minWidth:40,width:80},           
				    { header:"建筑",binding:"buildId",name:"h_buildId", minWidth:40 , width:90,isReadOnly:true},
				    { header:"楼层", binding:"floorNo",name:"h_floorNo" , align:"center", minWidth:40,width:60,isReadOnly:!status_search},
				    { header:"房号", binding:"roomId",name:"h_roomId" , align:"center", minWidth:40,width:60,isReadOnly:!status_search,mask:'A00000'}, 
				    { header:"房型", binding:"roomType",name:"h_roomType", minWidth:40 ,width:180,isReadOnly:!status_search }, 
				    { header:"房间特征",binding:"roomCharaterNames",name:"h_roomCharaterNames", width:80 ,minWidth:200,isReadOnly:!status_search,isReadOnly:true},
				    { header:"状态",binding:"status", name:"h_status", align:"center",cssClass:"fg_column_readOnly_cell" , minWidth:40,width:80,isReadOnly:true},
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
		// 有效 禁止默认选中行
		status_search ? flexGrid.select(-1,-1) : flexGrid.select(0,0);
		// 设置建筑物下拉代码
		/* var data_buildingId= eval('(' + '${buildingIds}' + ')');
		var col_buildingId = flexGrid.columns.getColumn('buildId');
		col_buildingId.dataMap = new wijmo.grid.DataMap(data_buildingId, 'buildingCodeId', 'buildingCodeName'); */
		
		// 设置房型下拉代码
		var data_hroomTypeId= eval('(' + '${hroomTypeIds}' + ')');
		var col_hroomTypeId = flexGrid.columns.getColumn('roomType');
		col_hroomTypeId.dataMap = new wijmo.grid.DataMap(data_hroomTypeId, 'hroomTypeCodeId', 'hroomTypeCcodeName');
		
		var initialVal;
		var data_buildingId= eval('(' + '${buildingIds}' + ')');
        // 单元格编辑模式判定
		flexGrid.beginningEdit.addHandler(function(s, e){
			var fgCol = s.columns[e.col];
			var fgRow = s.rows[e.row] ;
			var dataItem = fgRow.dataItem ;
			// 该列为代码大于0 则不允许修改
			if(fgCol.binding == "roomId" && (dataItem && dataItem.index)){
				// 修改状态时
				var index = dataItem.index.toString() ;
				index = index.replace(/#/g,"") ;
				if(parseInt(index) > 0)
					e.cancel = true;
			}
            // 楼层
            if (fgCol.binding == 'floorNo'){
                if(dataItem && dataItem.buildId){}
	            else{
//      			altWaringMsg('请先选择房型');
	            }
            }
            initialVal = s.getCellData(e.row, e.col, true);
            if(fgCol.binding == "roomId" && !isNull(initialVal))
				initialVal = initialVal.toString().trim().replace(/_/g , '');
        });
        
        flexGrid.cellEditEnded.addHandler(function (s, e) {
            var fgRow = s.rows[e.row] ;
            var fgCol = s.columns[e.col];
			var indexVal = fgRow.dataItem.index ;
            var finalVal = s.getCellData(e.row, e.col, true);
            if(fgCol.binding == "roomId" && !isNull(finalVal)){
	            finalVal = finalVal.toString().trim().replace(/_/g , '');
				s.setCellData(e.row, e.col, finalVal);
            }
			// 房型
	        if (fgCol.binding == 'roomType'&& initialVal != finalVal){
                var keyValue = s.columns.getColumn("roomType").dataMap.getKeyValue(finalVal);
                var arrVal;
                for (var v in data_buildingId)  
			    {  
			    	arrVal = data_buildingId[v][keyValue.split("--")[0]] ;
			        if(typeof arrVal != "undefined" )
			        	break;
			    }  
			    var _arrVal = arrVal.split("-=-");
                s.setCellData(e.row,"buildId" , _arrVal[0] , true , true) ;
                s.setCellData(e.row,"buildMinNum" , _arrVal[1] , true , true) ;
                s.setCellData(e.row,"buildMaxNum" , _arrVal[2] , true , true) ;
            }
            // 楼层
            if (fgCol.binding == 'floorNo' && !isNull(finalVal)){
                var buildMinNum = fgRow.dataItem.buildMinNum;
                var buildMaxNum = fgRow.dataItem.buildMaxNum;
                var floorNo = fgRow.dataItem.floorNo;
                // if 是否已经选择房型
                if(fgRow.dataItem && fgRow.dataItem.buildId){
                	// if 是否在已选择房型的正确楼层范围内
                	if(floorNo && parseInt(floorNo) >= parseInt(buildMinNum) && parseInt(floorNo) <= parseInt(buildMaxNum)){
	                }else{
	                	s.setCellData(e.row,fgCol.binding,initialVal, true);
	                	finalVal = initialVal ;
	                	var content = "输入楼层不正确。楼层范围为："+buildMinNum+"~" + buildMaxNum;
	                	altWaringMsg(content,function(){});
	                }
                }
//                 else{
//                 	s.setCellData(e.row,"floorNo",initialVal, true);
//                 	finalVal = initialVal ;
//                 	altWaringMsg('请先选择房型');
//                 }
            }
            // 校验房间唯一性
			if(fgCol.binding == "roomId" && !isNull(finalVal)){
				$.ajax({
					url:"${ctx}/hroomDefine/findBy.do",
					type:"post",
					data:{id : fgRow.dataItem.roomId , codeId : finalVal.toUpperCase()},
					dataType:"json",
					success:function(data){
						if(!data.success){
							s.setCellData(e.row,fgCol.binding,initialVal, true);
							altWaringMsg('房号重复，请重新编辑',function(){
						    	flexGrid.startEditing(false , e.row , e.col);
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
            if(indexVal && finalVal != initialVal){
				// 设置样式
				fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
				fgRow.cssClass="eidt-row-blue";
				goShowButton(true, true);
			}else if(isNull(initialVal)&&!isNull(finalVal)){
				fgRow.dataItem.index = "#";
				fgRow.cssClass="eidt-row-blue";
				goShowButton(true, true);
			}
        });
		//选中区域是否包含 非空净房的行或者空行  true 包含 否则不包含
      	flexGrid.selectionChanged.addHandler(function(s, e){
      		if(status_search){
	      		var temp = true ;
				var selection = getSelectionRanges();
				if(selection.length == 0){
					temp = false ;
				}else{
					for(var i= 0;i<selection.length;i++){
						var dataItem = selection[i].dataItem ;
						if((!dataItem || (dataItem.currStat && dataItem.currStat != "VCI"&&dataItem.currStat != "VC") || (dataItem.index && dataItem.index.toString().indexOf("*") !=-1 ))){
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
		            if (dataItem && status_search && ht.cellType == wijmo.grid.CellType.Cell && col.binding=='roomCharaterNames') {
		            	// 第二个参数 ： 如果第一次修改则为原来的值，否则传修改后的值   回显
		            	ajaxRoomCharacter(dataItem ? row.dataItem.id : null ,dataItem ? (row.dataItem.roomCharacter_temp ? row.dataItem.roomCharacter_temp:row.dataItem.roomCharacter) : null ,ht.range.row);
		        	}
		        }
            }
       	});
	});
	function closeDiv(bool){
		$(".roomDefinition").fadeOut(function(){
			$(".alertDivBg").fadeOut(function(){
				// 清除原来html值
				$("div#whitBlock").empty();
				if(bool == true)
					window.location.reload();
			});
		});
	}
	// 异步请求房间特征列表
	function ajaxRoomCharacter(id , roomCharater , row){
		$.ajax({
			type: "POST",
			url:"/hroomCharacters/findListBy.do",
			dataType:"json",
			data:{roomId:id},
			async:false,
			success:function(data){
				if(data){
					// 清除原来html值
					$("div#whitBlock").empty();
					
					var ul_html = "<ul class=\"FTsx\">";
					for(var i = 0 ; i < data.length ; i++){  
						var codeNamec = data[i].codeNamec;
						var pi = data[i].placeholderId ;
					    ul_html += "<li><label>";
					    ul_html += "	<input class=\"checkbox sonChk checkbox margin-right-5\" type=\"checkbox\" value=\""+pi+"\" data-name=\""+codeNamec+"\" "
					    		+(roomCharater ? ((roomCharater & (one << pi)) != 0 ? "checked = \"checked\"":"" ): "")+" />"+codeNamec;
					    ul_html += "</label></li>";
						/* if(i%2 == 0)
							ul_html += "<tr>";
	                    ul_html += "    <td align=\"right\" width=\"25%\" align=\"right\">" ;
						ul_html += "		<input class=\"checkbox sonChk\" type=\"checkbox\" value=\""+pi+"\" data-name=\""+codeNamec+"\" "+(roomCharater ? ((roomCharater & (one << pi)) != 0 ? "checked = \"checked\"":"" ): "")+" />";
	                    ul_html += "	</td>";
	                    ul_html += "    <td width=\"25%\">"+codeNamec+"</td>";
	                    if(i%2 == 1)
	                    	ul_html += "</tr>"; */
				    }  
				    ul_html += "</ul>";
					$("div#whitBlock").append(ul_html);
					chkAll();
					$("#saveCharaters").unbind("click",saveCharaters);
					$("#saveCharaters").bind("click",{roomId:id,row:row} , saveCharaters);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				altEerrMsg('出现异常');
			}
		});
		$(".alertDivBg").fadeIn();
		$(".roomDefinition").fadeIn();
	}
	// 保存选择的房间特征信息
	function saveCharaters(event){
	    // 选择的房间特征综合
		var totalVal;
		// 循环判断选中的特征
		$(".sonChk").each(function(index, element){
			if($(element).prop("checked") == true){
				var val = $(element).val();
				if(totalVal){
					totalVal = totalVal + (one << val) ;
				}else{
					totalVal = one << val ;
				}
			}
		});
		// if  有选中  else  无选中
		if(totalVal){
			var _roomId = event.data.roomId;
			var _row = event.data.row;
			// 记录选中的房间特征名称
			var rcName = "" ;
			$(".sonChk").each(function(index, element) 
			{
				var check = $(element).prop("checked");
				if(check){
					rcName += ($(element).attr("data-name")+",");
				}
			});
			flexGrid.setCellData(_row,"roomCharaterNames" , (rcName == "" ? "无" : rcName.substring(0, rcName.length - 1)) , true , true) ;
			flexGrid.setCellData(_row,"roomCharacter_temp" , totalVal , true , true) ;
			var fgRow = flexGrid.rows[_row] ;
			// 修改
			if(_roomId){
				// 如果当前已修改过  则不记录
				if(updCharaterRoomIds.indexOf(_roomId.trim()) == -1){
					updCharaterRowArray.push(_row);
					updCharaterRoomIds += (_roomId.trim() + ",");
				}
				// 改变修改样式
				var indexVal = fgRow.dataItem.index ;
				fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
			}else{
				flexGrid.setCellData(_row,"index","#", true , true);
			}
			fgRow.cssClass="eidt-row-blue";
			goShowButton(true, true);
			closeDiv(false);
			
		}else{
			altWaringMsg('请选择房间特征',function(){});
			return ;
		}
	}
	// 房间特征 checkbox 点击事件
	function chkAll(){
		var $chkeach = $(".sonChk");
		var $chkall = $("#chkall");
		// 全选按钮 checkbox 点击事件
		$chkall.unbind("click");
		$chkall.bind("click",function(event){
			var check = $(this).prop("checked");
			$chkeach.each(function(index, element) 
			{
				if(check){
					$(element).prop("checked",true);
				}else{
					$(element).prop("checked",false);
				}
			});
		});
		// 子项 checkbox 点击事件
		$chkeach.unbind("click");
		$chkeach.bind("click",function(event)
		{
			var $chks = $(".sonChk:checked");
			$chkall.prop("checked", $chks.length == $chkeach.length);
		});
		
		// 修改时，如果全部选中的时候，则选中  全选按钮
		var $chks = $(".sonChk:checked");
		$chkall.prop("checked", $chks.length == $chkeach.length);
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
				var roomId = addItems[i].roomId ;
				var roomType = addItems[i].roomType;
				var floorNo = addItems[i].floorNo ;
				/* var buildId = addItems[i].buildId; */
				var roomCharacter_temp = addItems[i].roomCharacter_temp;
				var msg = "" ;
				if(isNull(roomId)){
					msg+='房间号,';
				}
				if(isNull(floorNo)){
					msg+='楼层,';
				}
				if(isNull(roomType)){
					msg+='房间类型,';
				}
				if(isNull(roomCharacter_temp)){
					msg+='房间特征,';
				}
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}
				addOrEditItemsArray.push({
					"editFlag":"a",
					"roomId": roomId,
					"roomType": roomType,
					"floorNo": floorNo,
					/* "buildId": buildId, */
					"roomCharacter": roomCharacter_temp,
					"status": 0
				});
			}
		}
		// 修改房间特征数组   优先级高于view.itemsEdited 
		if(updCharaterRowArray.length > 0){
			for(var i=0 ; i < updCharaterRowArray.length ; i++){
				var rowItem = flexGrid.rows[updCharaterRowArray[i]].dataItem;
				var roomId = rowItem.roomId ;
				var roomType = rowItem.roomType ;
				var floorNo = rowItem.floorNo ;
				var roomCharacter_temp = rowItem.roomCharacter_temp;
				var currStat = rowItem.currStat;
				var msg = "" ;
				if(isNull(roomId)){
					msg+='房间号,';
				}
				if(isNull(floorNo)){
					msg+='楼层,';
				}
				if(isNull(roomType)){
					msg+='房间类型,';
				}
				if(isNull(roomCharacter_temp)){
					msg+='房间特征,';
				}
				
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}
				addOrEditItemsArray.push({
					"editFlag":"u",
					"roomId": roomId,
					"roomType": roomType,
					"floorNo": floorNo,
					"roomCharacter": roomCharacter_temp,
					"currStat": currStat,
					"status": 0
				});
			}
		}
		// 修改
		var editItems = view.itemsEdited;
		if(editItems && editItems.length > 0){
			for(var i=0 ; i < editItems.length ; i++){
				var roomId = editItems[i].roomId ;
				// 如果该条目之前修改过房间特征  则不重复更新操作
				if(updCharaterRoomIds.indexOf(roomId.trim()) != -1)continue ;
				var roomType = editItems[i].roomType ;
				var floorNo = editItems[i].floorNo ;
				/* var buildId = editItems[i].buildId; */
				var roomCharacter_temp = editItems[i].roomCharacter_temp;
				var currStat = editItems[i].currStat;
				var msg = "" ;
				if(isNull(roomId)){
					msg+='房间号,';
				}
				if(isNull(floorNo)){
					msg+='楼层,';
				}
				if(isNull(roomType)){
					msg+='房间类型,';
				}
				if(isNull(roomCharacter_temp)){
					msg+='房间特征,';
				}
				
				if(msg != ""){
					altWaringMsg(msg.substring(0, msg.length-1)+"不能为空",function(){});
					return ;
				}
					
				addOrEditItemsArray.push({
					"editFlag":"u",
					"roomId": roomId,
					"roomType": roomType,
					"floorNo": floorNo,
					/* "buildId": buildId, */
					"roomCharacter": roomCharacter_temp,
					"currStat": currStat,
					"status": 0
				});
			}
		}	
		// 如果无任何操作 则返回	
		if(isNull(delIds) && (addOrEditItemsArray.length==0)){
			return;
		}else{
			var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
			$.ajax({
				url:"${ctx}/hroomDefine/saveOrUpdateOrDel.do",
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
			$("#ok").attr("href",status_search ? "javascript:goOk();" : "javascript:goOkForBack('/hroomDefine/updateStatus.do');" );
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