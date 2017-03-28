
var one = parseInt(1);
var flexGrid_bookCheckInInfo ;
var view_bookCheckInInfo ;
var flexGridSelectIndex = 0 ; 
$(function(){
	$(".bookRegistration h4 a").unbind("click");
	$("#bookRegistration a.rightMove").hover(
		function () {
			if($(this).hasClass("forSN2_hover")){
				
			}
			else{
				var lg = $("#saveRoom_ul li:not(:hidden)").length ;
				if(lg > 0)
					$(this).addClass("forSN2_hover");
			}
		},
		function () {
			$(this).removeClass("forSN2_hover");
		}
	);
})
//关闭订单详情页面
function closeBookRegistration(){
	$("#selectRoom_span").html("0");
	$("#selectRoom_ul").html("");
	$("#grpChkid").val("");
	
	// 清除办理入住页面查询条件
	clearCheckInputForm();
	
	searchForm();
	$(".alertDivBg").fadeOut();
	$("#bookRegistration").fadeOut();
	return false;
}
function closeBookRegistration_quit(){
	var arr =getSelectRoomIds();
	if(!$.isEmptyObject(arr) && arr.length > 0 ){
		altInfMsg("确定要退出？",function(){
			closeBookRegistration();
		},function(){return false ;}) ;
	}else{
		closeBookRegistration();
	}
}
//订单详情页面--打开其他信息
function otherInfoDetail(){
	$("#otherInfoDiv").fadeIn();
	$(".alertDivBg2").fadeIn();
}
//订单详情页面--关闭其他信息
function closeOtherInfoDiv(){
	$("#otherInfoDiv").fadeOut();
	$(".alertDivBg2").fadeOut();
}

// 异步请求房间特征列表
function ajaxRoomCharacter(){
	var roomCharater = $("#roomCharaterTotalVal_1").val();
	$.ajax({
		type: "POST",
		url:"/hroomCharacters/findListBy.do",
		dataType:"json",
		async:false,
		success:function(data){
			if(data){
				// 清除原来html值
				$("#roomCharaterAlert div#whitBlock").empty();
				var ul_html = "<ul class=\"FTsx\">";
				for(var i = 0 ; i < data.length ; i++){  
					var codeNamec = data[i].codeNamec;
					var pi = data[i].placeholderId ;
				    ul_html += "<li><label>";
				    ul_html += "	<input class=\"checkbox sonChk margin-right-5\" type=\"checkbox\" value=\""+pi+"\" data-name=\""+codeNamec+"\" "
				    		+(roomCharater ? ((roomCharater & (one << pi)) != 0 ? "checked = \"checked\"":"" ): "")+" />"+codeNamec;
				    ul_html += "</label></li>";
			    }  
			    ul_html += "</ul>";
				$("#roomCharaterAlert div#whitBlock").append(ul_html);
				chkAll();
				$("#saveCharaters").unbind("click",saveCharaters);
				$("#saveCharaters").bind("click", saveCharaters);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			altEerrMsg('出现异常',function(){});
		}
	});
	$(".alertDivBg3").fadeIn();
	$("#roomCharaterAlert").fadeIn();
}
// 保存选择的房间特征信息
function saveCharaters(event){
    // 选择的房间特征综合
	var rmCharVal="";
	// 记录选中的房间特征名称
	var rcName = "" ;
	var totalVal ;
	$("#roomCharaterAlert .sonChk").each(function(index, element) 
	{
		var check = $(element).prop("checked");
		if(check){
			var val = $(element).val() ;
			rmCharVal+= (val+",");
			rcName += ($(element).attr("data-name")+",");
			
			if(totalVal){
				totalVal = totalVal + (one << val) ;
			}else{
				totalVal = one << val ;
			}
		}
	});
		
	if(rcName){
		$("#roomCharater").val(rcName.substring(0, rcName.length - 1));
		$("#roomCharaterTotalVal").val(rmCharVal.substring(0, rmCharVal.length - 1));
		$("#roomCharaterTotalVal_1").val(totalVal);
		closeDiv("roomCharaterAlert");
	}else{
		$("#roomCharater").val("");
		$("#roomCharaterTotalVal").val("");
		$("#roomCharaterTotalVal_1").val("");
		closeDiv("roomCharaterAlert");
		return ;
	}
}
// 根据id关闭弹出层
function closeDiv(id){
	$(".alertDivBg3").fadeOut(function(){
		$("#"+id).fadeOut(function(){
			// 清除房间特征原来html值
			$("div#roomCharaterAlert div#whitBlock").empty();
			// 清除房间类型原来html值
			$("div#roomTypeAlertDiv table#roomTypeAlertTable").empty();
		});
	});
}
// 房间特征 checkbox 点击事件
function chkAll(){
	var $chkeach = $("div#roomCharaterAlert .sonChk");
	var $chkall = $("div#roomCharaterAlert #chkall");
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
		var $chks = $("div#roomCharaterAlert .sonChk:checked");
		$chkall.prop("checked", $chks.length == $chkeach.length);
	});
	
	// 修改时，如果全部选中的时候，则选中  全选按钮
	var $chks = $("div#roomCharaterAlert .sonChk:checked");
	$chkall.prop("checked", $chks.length == $chkeach.length);
}
////办理入住弹出层页面--选择房类
function ajaxRoomType(){
	var buildId = $("#bookRegistration #buildId").val();
	var reachDate = $("form#checkInputForm #reachDate_hidden").val();
	var leaveDate = $("form#checkInputForm #leaveDate_hidden").val();
	var roomTypeVal = $("#roomSateDiv #roomTypeVal").val();
	$.ajax({
		type: "POST",
		url:"/hroomType/findAvailableRoomTypeListBy.do",
		dataType:"json",
		data:{startDate:reachDate , endDate:leaveDate ,buildId : buildId},
		async:false,
		success:function(data){
			if(data && data.length > 0){
				// 清除原来html值
				$("div#roomTypeAlertDiv table#roomTypeAlertTable").empty();
				// 已选中房间类型
				var bool_roomTypeVal = isNull(roomTypeVal);
				var arr_roomTypeVal = roomTypeVal.split(",");
				var ul_html = "";
				for(var o = 0 ; o < data.length ; o++){  
					var name = data[o].name ;
					name = name.trim();
					var id = data[o].id ;
					id = id.trim();
					var count = data[o].count ;
					if(!count || (count && count == 0))
						continue ;
					ul_html+="<tr>";
					ul_html+="	<td width=\"150\" align=\"right\">";
					ul_html+="		"+name+"<span class=\"margin-left-10\">"+id+"</span>";
					ul_html+="	</td>";
					ul_html+="	<td width=\"30\" align=\"center\">";
					ul_html+="		<input class=\"sonChk roomTypeChk\" type=\"checkbox\" data-roomTypeNum=\""+count+"\" data-name=\""+name+"\" value=\""+id+"\" "+(roomTypeVal.indexOf(id)!=-1 ? " checked=\"checked\"" : "")+" >";
					ul_html+="	</td>";
					ul_html+="	<td width=\"40\">";
					if(bool_roomTypeVal){
						ul_html+="		<input class=\"smailInputT rommTypeInputNum\" name=\"rommTypeInputNum\" data-roomTypeNum=\""+count+"\" type=\"text\" value=\"0\" >";
					}else{
						var _arr ;
						var bool =true ;
						A:for ( var v = 0 ; v < arr_roomTypeVal.length ; v++) {
							_arr = arr_roomTypeVal[v].split("-=-");
							var v0 = _arr[0] ;
							var v1 = _arr[1] ;
							if(v0 == id){
								bool =false ;
								ul_html+="		<input class=\"smailInputT rommTypeInputNum\" name=\"rommTypeInputNum\" data-roomTypeNum=\""+count+"\" type=\"text\" value=\""+v1+"\" >";
								break A;
							}
						}
						if(bool)
							ul_html+="		<input class=\"smailInputT rommTypeInputNum\" name=\"rommTypeInputNum\" data-roomTypeNum=\""+count+"\" type=\"text\" value=\"0\" >";
					}
					ul_html+="	</td>";
					ul_html+="	<td>("+count+")</td>";
					ul_html+="</tr>";
			    }  
				if(ul_html !=""){
					$("div#roomTypeAlertDiv table#roomTypeAlertTable").append(ul_html);
					// 在次打开是否全选
					$("div#roomTypeAlertDiv #chkall").prop("checked", data.length == roomTypeVal.split(",").length);
					// 选择房间类型
					chkAll_roomType();
					// 房间类型选择事件
					roomTypeNumberCheck();
					$(".alertDivBg3").fadeIn();
					$("#roomTypeAlertDiv").fadeIn();
				}else{
					altInfMsg("该建筑下无可用房类",function(){});
				}
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			altEerrMsg('出现异常',function(){});
		}
	});
}
//房间类型checkbox 点击事件
function chkAll_roomType(){
	var $chkeach = $("div#roomTypeAlertDiv .sonChk");
	var $chkall = $("div#roomTypeAlertDiv #chkall");
	// 全选按钮 checkbox 点击事件
	$chkall.unbind("click");
	$chkall.bind("click",function(event){
		var check = $(this).prop("checked");
		$chkeach.each(function(index, element) 
		{
			/**自动排房**/
			var roomTypeNum = $(element).attr("data-roomTypeNum");
			roomTypeNum = isNull(roomTypeNum) ? 0 : roomTypeNum;
			var rommTypeInputNum = $(element).parent("td").next("td").children(".rommTypeInputNum") ;
			var rommTypeInputNumVal = rommTypeInputNum.val() ;
			/**自动排房**/
			if(check){
				$(element).prop("checked",true);
				$(".rommTypeInputNum").removeClass('errorColor');
				rommTypeInputNum.val((roomTypeNum == 0 ? 0 :(isNull(rommTypeInputNumVal)?1:((rommTypeInputNumVal==0 || parseInt(rommTypeInputNumVal)>parseInt(roomTypeNum))? 1 : rommTypeInputNumVal))));
			}else{
				$(element).prop("checked",false);
				$(".rommTypeInputNum").removeClass('errorColor');
				rommTypeInputNum.val(0);
			}
		});
	});
	// 子项 checkbox 点击事件
	$chkeach.unbind("click");
	$chkeach.bind("click",function(event)
	{
		var roomTypeNum = $(this).attr("data-roomTypeNum");
		roomTypeNum = isNull(roomTypeNum) ? 0 : roomTypeNum;
		var rommTypeInputNum = $(this).parent("td").next("td").children(".rommTypeInputNum") ;
		/**自动排房**/
		if($(this).is(":checked")){
			rommTypeInputNum.val(roomTypeNum == 0 ? 0 : 1);
		}else{
			rommTypeInputNum.val(0);
			$(this).parent("td").next("td").children(".rommTypeInputNum").removeClass('errorColor');
		}
		/**自动排房**/
		var $chks = $("div#roomTypeAlertDiv .sonChk:checked");
		$chkall.prop("checked", $chks.length == $chkeach.length);
	});
	
	// 修改时，如果全部选中的时候，则选中  全选按钮
	var $chks = $("div#roomTypeAlertDiv .sonChk:checked");
	$chkall.prop("checked", $chks.length == $chkeach.length);
}
/**
* 房间类型选择数量输入校验 只可输入数字
*/
function roomTypeNumberCheck()
{
	var allCheckBox = $("#rooms_type_selecall");
	//输入验证
	$(".rommTypeInputNum").unbind("keyup");
	$(".rommTypeInputNum").bind("keyup",function(event)
	{
		var regex = /^\s*(\+|-)?((\d+([\.,]\d+)?)|([\.,]\d+))\s*$/;//数字验证
		var inputObj = $(this);
		var count = inputObj.attr("data-roomTypeNum");
		if(!regex.test(inputObj.val()) && inputObj.val() != '')
		{
			inputObj.val("");
		}
		//输入数字 大于 总数
		var txtVal = inputObj.val();
		txtVal = txtVal - 0;//转整数
		if(txtVal<=count){
			inputObj.val(txtVal);
			//inputObj.removeClass('errorColor');
		}
		else{
			//inputObj.addClass('errorColor');
			inputObj.val(count);
		}
		inputObj.focus();
	});
	//选中未选中状态切换
	$(".rommTypeInputNum").unbind("focusout");
	$(".rommTypeInputNum").bind("focusout",function(event)
	{
		var inputObj = $(this);
		if(inputObj.hasClass("errorColor")){
			inputObj.focus();
			return false ;
		}
		var numberVal = inputObj.val();
		var count = inputObj.attr("data-roomTypeNum");
		var inputCheckBox = inputObj.parent("td").prev("td").children(".roomTypeChk");
		if(isNull(numberVal) || (numberVal =="0"&& numberVal != count)){
			inputCheckBox.prop("checked",false);
		}else{
			inputCheckBox.prop("checked",true);
		}
		$("#roomTypeAlertDiv #chkall").prop("checked",($("div#roomTypeAlertDiv .sonChk:checked").length == $("div#roomTypeAlertDiv .sonChk").length));
	});
};
//办理入住弹出层页面--选择房类后保存
function saveRoomType(){
	var errorImput = $(".rommTypeInputNum.errorColor").length;
	if(errorImput > 0){
		altWaringMsg("有错误信息，输入数量太大",function(){});
		return false ;
	}
	// 选择
	var roomType="";
	var roomTypeVal="";
	// 循环判断选中的特征
	$("div#roomTypeAlertDiv .sonChk").each(function(index, element){
		if($(element).prop("checked") == true){
			var $ele= $(element);
			var val = $ele.val();
			var rommTypeInputNum = $ele.parent("td").next("td").children(".rommTypeInputNum") ;
			roomType += (val+",") ;
			roomTypeVal += (val+"-=-"+rommTypeInputNum.val()+",") ;
			//roomType += ($ele.attr("data-name")+",");
		}
	});
	// if  有选中  else  无选中
	if(roomType){
		roomType = roomType.substring(0, roomType.length - 1) ;
		roomTypeVal = roomTypeVal.substring(0, roomTypeVal.length - 1) ;
		$("#roomType").val(roomType);
		$("#roomTypeVal").val(roomTypeVal);
		autoSelectRoom_css(true) ;
		closeDiv("roomTypeAlertDiv");
	}else{
		$("#roomType").val("");
		$("#roomTypeVal").val("");
		autoSelectRoom_css(false) ;
		closeDiv("roomTypeAlertDiv");
		return ;
	}
	
}
//　办理入住弹出层页面--建筑选择事件
function buildChange(obj){
	var numArr = $("#bookRegistration #buildId").find("option:selected").attr("data-name").split(",");
	var floorHtml = "<option value=\"\" >全部</option>" ;
	if(numArr && numArr.length > 0 ){
		for ( var i = 0; i < numArr.length; i++) {
			if(isNull(numArr[i]))
				continue ;
			var floor = numArr[i].toString().trim();
			floorHtml += "<option value=\""+floor+"\" >"+floor+"层</option>" ;
		}
	}
    $("#bookRegistration #floorNo").html(floorHtml);
}

// 办理入住弹出层--填充房态区房间列表
function fillBookCheckInRoomsList(_listJson,autoSelectRoom){
	var selectRoomsIdArr = [] ;
	if(_listJson && _listJson.length > 0){
		var listJson = _listJson;
		var li_html = "" ;
		var currentFloorNo = "-99"; 
		// 获取当前选中的房间号数组，做状态选中
		var arr = getSelectRoomIds();
		var arr1 = getSelectRoomIds_clickNo();
		// 已选中房间类型
		var roomTypeVal = $("#roomSateDiv #roomTypeVal").val();
		var bool_roomTypeVal = autoSelectRoom ? isNull(roomTypeVal) :true ;
		var arr_roomTypeVal = isNull(roomTypeVal) ? null : roomTypeVal.split(",");
		var fillSelectRoomsUlValArr = [] ;
		for(var o = 0 ; o<listJson.length ; o++){ 
			if(listJson[o].ifSaveRoom == "2")
				continue ;
			var floorNo = listJson[o].floorNo ;
			floorNo = floorNo.trim();
			var roomId = listJson[o].roomId ;
			roomId = roomId.trim();
			var currStat = listJson[o].currStat ;
			currStat = currStat.trim();
			var roomType = listJson[o].roomType ;
			roomType = roomType.trim();
			var ifSaveRoom = listJson[o].ifSaveRoom ;
			var roomTypeName = listJson[o].roomTypeName ;
			roomTypeName = roomTypeName ? roomTypeName.substr(0,6) : "未知" ;
			// (floorNo.length=1?("0"+floorNo) : floorNo) 
			if(currentFloorNo != floorNo){
				li_html += "<li class=\"statusLi2 floor\" data-floorNo=\""+floorNo+"\"> ";
				li_html += "	<img class=\"floorImg\" width=\"45\" src=\"/img/Floor_01.png\">";
				li_html += "    <span class=\"floorWord2\">"+floorNo+"</span>";
				li_html += "</li>";
			}
			currentFloorNo = floorNo ;
			
			li_html += "<li class=\"statusLi2 idle\">";
			li_html += "	<div id=\"statusBlock_"+roomId+"\" class=\"statusBlock floorNo_"+floorNo+"\" data-currStat=\""+currStat+"\" data-roomId=\""+roomId+"\" data-ifSaveRoom=\""+ifSaveRoom+"\">";
			li_html += "        <h5>"+roomId+"</h5>";
			li_html += "        <h6>"+roomTypeName+"</h6>";
			//不洁空房
			if(currStat == "VDP"||currStat == "VD"){
				li_html += "<div class=\"statusImg\">";
				li_html += "	<img src=\"/img/ft-02.png\">";
				li_html += "</div>";
			//清洁未查空房
			}else if(currStat == "VCP"){
				li_html += "<div class=\"statusImg\">";
				li_html += "	<img src=\"/img/ft-03.png\">";
				li_html += "</div>";
			}
			// 是否留房
			if(listJson[o].ifSaveRoom == "0"){
				li_html += "        <ul class=\"userStatusSmall\">";
				li_html += "            <li><img src=\"/img/st-02.png\"></li>";
				li_html += "        </ul>";
			}
			li_html += "	</div>";
			// 如果选定房间区包含该房间则全状态显示，并且禁止点击
			var isInArr = ($.inArray(roomId, arr) != -1);
			// 定义此数组的原因是因为如果选定留房后  并且 未点击确认选中 此时点击条件查询后，取消选定区房间后，房态区对应的房号仍不可取消--禁止点击操作
			var isInArr1 = ($.inArray(roomId, arr1) != -1);
			if(bool_roomTypeVal){
				li_html += "    <div class=\""+(isInArr ? (isInArr1 ? "pointStatus_block" : "pointStatus_block_av") : (isInArr1 ? "pointStatus_block_av" : "pointStatus"))+"\" data-roomId=\""+roomId+"\" data-ifSaveRoom=\""+ifSaveRoom+"\" ></div>";
				li_html += "    <img class=\""+(isInArr ? (isInArr1 ? "pointStatusRight_block" : "pointStatusRight_block_av") : (isInArr1 ? "pointStatusRight_block_av" : "pointStatusRight"))+"\" src=\"/img/point-right.png\">";
			}else{
				if(arr_roomTypeVal && arr_roomTypeVal.length > 0){
					var bool = true ;
					// 判断已选择的房间类型（BSDK-=-2   房型和数量）数组
					A:for ( var i = 0; i < arr_roomTypeVal.length; i++) {
						var arri = arr_roomTypeVal[i] ;
						var arr = arri.split("-=-");
						// 房类型
						var arr0 = arr[0] ;
						// 房类型选择的数量
						var arr1 = arr[1] ;
						if(arr0 == roomType && arr1.length > 0 && !isInArr && !isInArr1 ){
							bool =false ;
							// 如果是留房，留房区display==none  选定房区append
							selectRoomsIdArr.push(roomId);
							fillSelectRoomsUlValArr.push(roomId+","+ifSaveRoom+","+bookId+","+currStat);
							//fillSelectRoomsUl(roomId,ifSaveRoom,bookId,currStat);
							li_html += "    <div class=\"pointStatus_block_av\" data-roomId=\""+roomId+"\" data-ifSaveRoom=\""+ifSaveRoom+"\" ></div>";
							li_html += "    <img class=\"pointStatusRight_block_av\" src=\"/img/point-right.png\">";
							// 选中后重新更新数组，选中一次后该类型的数量 -1
							if(arr1 > 1){
								arr_roomTypeVal.splice(i,1,(arr0+"-=-"+(arr1-1))) ;
							}else{
								arr_roomTypeVal.splice(i,1) ;
							}
							break A ;
						}
					}
					if(bool){
						li_html += "    <div class=\""+(isInArr ? (isInArr1 ? "pointStatus_block" : "pointStatus_block_av") : (isInArr1 ? "pointStatus_block_av" : "pointStatus"))+"\" data-roomId=\""+roomId+"\" data-ifSaveRoom=\""+ifSaveRoom+"\" ></div>";
						li_html += "    <img class=\""+(isInArr ? (isInArr1 ? "pointStatusRight_block" : "pointStatusRight_block_av") : (isInArr1 ? "pointStatusRight_block_av" : "pointStatusRight"))+"\" src=\"/img/point-right.png\">";
					}
				}else{
					li_html += "    <div class=\""+(isInArr ? (isInArr1 ? "pointStatus_block" : "pointStatus_block_av") : (isInArr1 ? "pointStatus_block_av" : "pointStatus"))+"\" data-roomId=\""+roomId+"\" data-ifSaveRoom=\""+ifSaveRoom+"\" ></div>";
					li_html += "    <img class=\""+(isInArr ? (isInArr1 ? "pointStatusRight_block" : "pointStatusRight_block_av") : (isInArr1 ? "pointStatusRight_block_av" : "pointStatusRight"))+"\" src=\"/img/point-right.png\">";
				}
			}
			li_html += "</li>";
		}
		li_html += "<div class=\"clearBoth\"></div>";
		$("#bookRegistration #roomStateList").html(li_html);
		if(fillSelectRoomsUlValArr.length > 0){
			$("#selectRoom_ul li").each(function(index,element){
				if($(element).hasClass(".clickNo")){}else{
					$(element).remove();
				}
			});
			for ( var fi = 0; fi < fillSelectRoomsUlValArr.length; fi++) {
				var arr = fillSelectRoomsUlValArr[fi].split(",");
				fillSelectRoomsUl(arr[0], arr[1], arr[2], arr[3]);
			}
		}
		
		
		/*房态点击*/
		currentRoomClick();
	}else{
		$("#bookRegistration #roomStateList").html("");
	}
	
	return selectRoomsIdArr ;
}
//办理入住弹出层--填充留房列表
function fillSaveRoomsList(_listJson , selectRoomsIdArr){
	$("#saveRoom_ul").html("");
	var saveRoom_li_html = "" ;
	if(_listJson && _listJson.length > 0){
		var listJson = _listJson;
		for(var o = 0 ; o<listJson.length ; o++){  
	    	var roomId = listJson[o].roomId ;
	    	roomId = roomId.toString().trim();
	    	saveRoom_li_html += "	<li id=\""+roomId+"\" style=\"display:"+((selectRoomsIdArr&&selectRoomsIdArr.length>0) ? (selectRoomsIdArr.indexOf(roomId) != -1 ? "none" : "") : "")+"\" > ";
	    	saveRoom_li_html +=(roomId+"</li> ");
	    }		
	}
	$("#saveRoom_ul").html(saveRoom_li_html);
	
	countSaveOrSelectRoomNum();
}
//展示该订单下预留信息列表
function fillBookCheckInInfoList(bciiList){
	// 初始化 预留房列表的索引
	flexGridSelectIndex = 0 ;
	var array = [];	
	var data = bciiList ;
	var count = data ? data.length : 0;
	for ( var i = 0; i < count; i++) {
		array.push({
			"index": i+1,
			"roomTypeNamec0": (data[i].roomTypeNamec),
			"roomTypeNamec": (data[i].roomTypeCodeId+"："+data[i].roomTypeNamec),
			"roomPrice": toDecimal2(data[i].roomPrice),
			"bookNum": data[i].bookNum,
			"saveNum": data[i].saveNum,
			"reachNum": data[i].reachNum,
			"reachDate": data[i].reachDate, //(data[i].reachDate).replace(new RegExp(/(-)/g),'')
			"leaveDate": data[i].leaveDate,
			"status": (data[i].checkInNum == 0 ? "" : "部分"),
			"bookId": data[i].bookRoomId,
			"buttons": "",
			"ifSelect": "1",
			"checkId": data[i].checkId
		});
	}
	// create CollectionView on the data (to get events)
	view_bookCheckInInfo = new wijmo.collections.CollectionView(array);
	view_bookCheckInInfo.trackChanges = true;
	//Disposes of the control by removing its association with the host element.
	if(flexGrid_bookCheckInInfo)
		flexGrid_bookCheckInInfo.dispose();
	
	// initialize the grid
	flexGrid_bookCheckInInfo = new wijmo.grid.FlexGrid('#bookCheckInInfoList', {
			autoGenerateColumns: false,
			selectionMode : wijmo.grid.SelectionMode.Row,
			autoClipboard:true,
			columns:[
		        { header:"checkId",binding:"checkId",name:"h_checkId",visible:false},
		        { header:"bookId",binding:"bookId",name:"h_bookId",visible:false},
		        { header:"ifSelect",binding:"ifSelect",name:"h_ifSelect" ,visible:false},
		        { header:"roomTypeNamec0",binding:"roomTypeNamec0",name:"h_roomTypeNamec0" ,visible:false},
			    { header:"序号",binding:"index",cssClass:"fg_column_readOnly",name:"h_index", align:"center", minWidth:40,width:40},           
			    { header:"房类", binding:"roomTypeNamec",name:"h_roomTypeNamec" , align:"left", minWidth:40,width:120}, 
			    { header:"房价", binding:"roomPrice",name:"h_roomPrice", align:"right", minWidth:40 ,width:60,format:"n2"}, 
			    { header:"订", binding:"bookNum",name:"h_bookNum", align:"center" , minWidth:20,width:20},
			    { header:"留",binding:"saveNum",name:"h_saveNum" , align:"center", minWidth:20, width:20},
			    { header:"抵",binding:"reachNum",name:"h_reachNum" , align:"center", align:"center", minWidth:20, width:20},
			    { header:"抵店日期",binding:"reachDate",name:"h_reachDate" , align:"center", minWidth:80, width:100},
			    { header:"离店日期",binding:"leaveDate",name:"h_leaveDate" , align:"center", minWidth:80, width:100},
			    { header:"状态",binding:"status", name:"status", align:"center", minWidth:40 ,width:40},
			    { header:" ",binding:"buttons", name:"h_buttons", align:"center", minWidth:30 ,width:30}
			],
			allowAddNew: false,
			allowSorting:false,
			// 拖拽
			allowDragging:wijmo.grid.AllowDragging.None,
			// 设置列/行的显示
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			itemsSource : view_bookCheckInInfo,
            isReadOnly: true,
            itemFormatter: itemFormatter
		}
	);
	if(count > 0){
		// 设置预留信息列表第一条记录的抵/离店日期
		$("form#checkInputForm input#reachDate_hidden,table#roomSateTable input#reachDate_readonly,#bookRegistration input#reachDate_first").val(data[0].reachDate);
	    $("form#checkInputForm input#leaveDate_hidden,table#roomSateTable input#leaveDate_readonly,#bookRegistration input#leaveDate_first").val(data[0].leaveDate);
	    $("#bookRegistration #checkInputForm input#bookId , #bookRegistration input#bookId_first").val(data[0].bookRoomId);
		// 设置预留信息列表第一条记录的留房号码
	    fillSaveRoomsList(data[0].saveRoomInfoList , null ) ;
	}
	flexGrid_bookCheckInInfo.selectionChanged.addHandler(function(s, e){
		// 首先清空
		clearCheckInputForm();
		var fgRow = flexGrid_bookCheckInInfo.rows[e.row] ;
		var dataItem = fgRow.dataItem;
		flexGridSelectIndex = e.row ;
		$("form#checkInputForm input#reachDate_hidden,table#roomSateTable input#reachDate_readonly").val(dataItem.reachDate);
	    $("form#checkInputForm input#leaveDate_hidden,table#roomSateTable input#leaveDate_readonly").val(dataItem.leaveDate);
	    $("#bookRegistration #checkInputForm input#bookId").val(dataItem.bookId);
	    // 刷新房态区 和 留房区
	    checkInputFormSearch(true , true , false);
	});
	flexGrid_bookCheckInInfo.selectionChanging.addHandler(function(s, e){
		if(e.row == flexGridSelectIndex){
			e.cancel=true ;
			return ;
		}
		var fgRow = flexGrid_bookCheckInInfo.rows[e.row] ;
		var dataItem = fgRow.dataItem;
		// 如果切换选中条目前，确认选中按钮 处于非激活状态则允许切换  否则 返回  $("#bookRegistration a#confirmSelect").prop("href").trim() == "javascript:;" && 
		var arr = getSelectRoomIds_not_clickNo();
		if(!dataItem.ifSelect || dataItem.ifSelect == "1")
			if(arr.length == 0){
				e.cancel=false ;
			}else{
				e.cancel=true;
			}
		else
			e.cancel=true;
	});
}
function itemFormatter(panel, r, c, cell) {
    if (panel.cellType == wijmo.grid.CellType.Cell) {
        var col = panel.columns[c],
        row = panel.rows[r],
        html = cell.innerHTML;
        var dataItem = row.dataItem ;
        switch (col.name) {
            case "h_buttons":
                html = "<div id=\"operateButtons\"><img src=\"/img/lock.png\" alt=\"全部抵达\" height=\"20px\" onclick=confirmAllReach('"+dataItem.bookId+"',"+JSON.stringify(dataItem)+"); /></div>";
                break;
        }
        cell.innerHTML = html;
    }
}
// 留房区 房号单击事件
function saveRoomLiClick(event){
	// ctrl 多选
	if(event.ctrlKey){
		if($(event.target).hasClass("selectedCurrentLi"))
			$(event.target).removeClass("selectedCurrentLi");
		else
			$(event.target).addClass("selectedCurrentLi");
	}
	else{
		if($(event.target).hasClass("selectedCurrentLi"))
			$(event.target).removeClass("selectedCurrentLi");
		else{
			$(event.target).parent("ul").children("li").removeClass("selectedCurrentLi");
			$(event.target).addClass("selectedCurrentLi");
		}
	}
		
}
// 封装选定房区li html 并追加
function fillSelectRoomsUl(roomId,ifSaveRoom,bookId,currStat){
	$("#selectRoom_ul").append("   <li id=\""+roomId+"\" ondblclick=\"selectRoomLiDBLClick(this)\" data-currStat=\""+currStat+"\" data-ifSaveRoom=\""+ifSaveRoom+"\" data-bookId=\""+bookId+"\"> " +roomId+"</li> ");
}
//留房区 房号双击事件
function saveRoomLiDBLClick(event){
	var cuurObj = $(event.target) ;
	
	var roomId = cuurObj.prop("id");
	var bookId = $("#bookId").val();
	cuurObj.css("display","none");
	fillSelectRoomsUl(roomId,0,bookId,'')
	var statusBlock_div = $("#roomStateList div#statusBlock_"+roomId) ;
	if(statusBlock_div.length > 0){
		statusBlock_div.nextAll(".pointStatus,.pointStatus_block_av").eq(0).show();
		statusBlock_div.nextAll(".pointStatusRight,.pointStatusRight_block_av").eq(0).show();
	}
	countSaveOrSelectRoomNum();
}
// 右移选中房间
function rightMoveRoom(){
	var bookId = $("#bookRegistration #checkInputForm input#bookId").val();
	if($("#saveRoom_ul li:not(:hidden)").length > 0){
		$("#saveRoom_ul li:not(:hidden)").each(function(index, element) 
		{
			if($(this).hasClass("selectedCurrentLi")){
				var roomId = $(this).prop("id");
				$(this).css("display","none");
				$(this).removeClass("selectedCurrentLi");
				fillSelectRoomsUl(roomId,0,bookId,'')
				var statusBlock_div = $("#roomStateList div#statusBlock_"+roomId) ;
				if(statusBlock_div.length > 0){
					statusBlock_div.nextAll(".pointStatus,.pointStatus_block_av").eq(0).show();
					statusBlock_div.nextAll(".pointStatusRight,.pointStatusRight_block_av").eq(0).show();
				}
			}
		});
		countSaveOrSelectRoomNum();
	}
}
//右移全部选中房间
function rightMoveAllRoom(){
	var bookId = $("#bookRegistration #checkInputForm input#bookId").val();
	if($("#saveRoom_ul li:not(:hidden)").length > 0){
		$("#saveRoom_ul li:not(:hidden)").each(function(index, element) 
		{
			var roomId = $(this).prop("id");
			$(this).css("display","none");
			fillSelectRoomsUl(roomId,0,bookId,'')
			var statusBlock_div = $("#roomStateList div#statusBlock_"+roomId) ;
			if(statusBlock_div.length > 0){
				statusBlock_div.nextAll(".pointStatus,.pointStatus_block_av").eq(0).show();
				statusBlock_div.nextAll(".pointStatusRight,.pointStatusRight_block_av").eq(0).show();
			}
		});
		countSaveOrSelectRoomNum();
	}
}
// 选定房间区 房号双击事件
function selectRoomLiDBLClick(obj){
	// 如果为已选定房间则禁止双击取消
	if($(obj).hasClass("clickNo")){
		return ;
	}
	else{
		var ifSaveRoom = $(obj).attr("data-ifSaveRoom") ;
		var roomId = $(obj).attr("id") ;
		var statusBlock_div = $("#roomStateList div#statusBlock_"+roomId) ;
		if(statusBlock_div.length > 0){
			statusBlock_div.nextAll(".pointStatus,.pointStatus_block_av").eq(0).hide();
			statusBlock_div.nextAll(".pointStatusRight,.pointStatusRight_block_av").eq(0).hide();
		}
		$(obj).remove();
		if(ifSaveRoom == "0"){
			$("#saveRoom_ul #"+roomId).removeClass("selectedCurrentLi");
			$("#saveRoom_ul #"+roomId).css("display","");
		}
		countSaveOrSelectRoomNum();
	}
}
// 显示当前房间号列表对应的房间信息
function showCurrentRoomInfo(ul_id){
	var roomIds = "" ;
	$("#"+ul_id + "_ul li:not(:hidden)").each(function(index, element) 
	{
		roomIds += $(element).prop("id") + ",";
	});
	if(roomIds){
		$.ajax({
			type: "POST",
			url:"/hroomDefine/findRoomsBy.do",
			dataType:"json",
			data:{roomIds:roomIds} ,
			async:false,
			success:function(data){
				var listJson = data.listJson ;
				var selectRoomsTr_html = "" ;
				var selectRoomsTr_index = 1 ;
				if(listJson && listJson.length > 0){
					for(var o = 0 ; o < listJson.length ; o++){  
						selectRoomsTr_html += "<tr>";
						selectRoomsTr_html += "    <td width=\"15%\">"+selectRoomsTr_index+"</td>";
						selectRoomsTr_html += "    <td width=\"15%\">"+listJson[o].buildName+"</td>";
						selectRoomsTr_html += "    <td width=\"15%\">"+listJson[o].floorNo+"</td>";
						selectRoomsTr_html += "    <td width=\"20%\">"+listJson[o].roomId+"</td>";
						selectRoomsTr_html += "    <td>"+listJson[o].roomTypeName+"</td>";
	                    selectRoomsTr_html += "</tr>";
	                    selectRoomsTr_index++ ;
				    }
					$("#selectRoomsAlert table#selectRoomsTable tbody").html(selectRoomsTr_html);
					$("#selectRoomsAlert #selectRoomsNum_span").html(selectRoomsTr_index-1) ; 
					$("#selectRoomsAlert").fadeIn();
					$(".alertDivBg3").fadeIn();
				}
			}
		});
	}
}
//房态区房间点击
function currentRoomClick(){
	
	var bookId = $("#bookRegistration #checkInputForm input#bookId").val();
	// 选中
	$("#bookRegistration #roomStateList .statusBlock").unbind("click");
	$("#bookRegistration #roomStateList .statusBlock").click(function(){
		var ifSaveRoom = $(this).attr("data-ifSaveRoom") ;
		var roomId = $(this).attr("data-roomId") ;
		var currStat = $(this).attr("data-currStat") ;
		$(this).nextAll(".pointStatus,.pointStatus_block_av").eq(0).show();
		$(this).nextAll(".pointStatusRight,.pointStatusRight_block_av").eq(0).show();
		
		if(ifSaveRoom == "0"){
			$("#saveRoom_ul #"+roomId).css("display","none");
		}
		fillSelectRoomsUl(roomId,ifSaveRoom,bookId,currStat);
		countSaveOrSelectRoomNum();
	});
	// 取消
	$("#bookRegistration #roomStateList .pointStatus").unbind("click");
	$("#bookRegistration #roomStateList .pointStatus").click(function(){
		
		var ifSaveRoom = $(this).attr("data-ifSaveRoom") ;
		var roomId = $(this).attr("data-roomId") ;
		var selectRoom_ul_roomId = $("#selectRoom_ul #"+roomId) ;
		var saveRoom_ul_roomId = $("#saveRoom_ul #"+roomId) ;
		
		$(this).hide();
		$(this).next().hide();
		if(selectRoom_ul_roomId&&selectRoom_ul_roomId.length>0)
			selectRoom_ul_roomId.remove();
		if(saveRoom_ul_roomId && saveRoom_ul_roomId.length>0)
			saveRoom_ul_roomId.css("display","");
		countSaveOrSelectRoomNum();
	});	
	// 取消
	$("#bookRegistration #roomStateList .pointStatus_block_av").unbind("click");
	$("#bookRegistration #roomStateList .pointStatus_block_av").click(function(){
		
		var ifSaveRoom = $(this).attr("data-ifSaveRoom") ;
		var roomId = $(this).attr("data-roomId") ;
		var selectRoom_ul_roomId = $("#selectRoom_ul #"+roomId) ;
		var saveRoom_ul_roomId = $("#saveRoom_ul #"+roomId) ;
		
		$(this).hide();
		$(this).next().hide();
		if(selectRoom_ul_roomId&&selectRoom_ul_roomId.length>0)
			selectRoom_ul_roomId.remove();
		if(saveRoom_ul_roomId && saveRoom_ul_roomId.length>0)
			saveRoom_ul_roomId.css("display","");
		countSaveOrSelectRoomNum();
	});	
	// 对钩取消
	$("#bookRegistration #roomStateList .pointStatusRight").unbind("click");
	$("#bookRegistration #roomStateList .pointStatusRight").click(function(){
		var prevObj = $(this).prev(".pointStatus");
		var ifSaveRoom = prevObj.attr("data-ifSaveRoom") ;
		var roomId = prevObj.attr("data-roomId") ;
		var selectRoom_ul_roomId = $("#selectRoom_ul #"+roomId) ;
		var saveRoom_ul_roomId = $("#saveRoom_ul #"+roomId) ;
		
		$(this).hide();
		prevObj.hide();
		if(selectRoom_ul_roomId&&selectRoom_ul_roomId.length>0)
			selectRoom_ul_roomId.remove();
		if(saveRoom_ul_roomId && saveRoom_ul_roomId.length>0)
			saveRoom_ul_roomId.css("display","");
		countSaveOrSelectRoomNum();
	});
	// 点击对钩  取消
	$("#bookRegistration #roomStateList .pointStatusRight_block_av").unbind("click");
	$("#bookRegistration #roomStateList .pointStatusRight_block_av").click(function(){
		var prevObj = $(this).prev(".pointStatus_block_av");
		var ifSaveRoom = prevObj.attr("data-ifSaveRoom") ;
		var roomId = prevObj.attr("data-roomId") ;
		var selectRoom_ul_roomId = $("#selectRoom_ul #"+roomId) ;
		var saveRoom_ul_roomId = $("#saveRoom_ul #"+roomId) ;
		
		$(this).hide();
		prevObj.hide();
		if(selectRoom_ul_roomId&&selectRoom_ul_roomId.length>0)
			selectRoom_ul_roomId.remove();
		if(saveRoom_ul_roomId && saveRoom_ul_roomId.length>0)
			saveRoom_ul_roomId.css("display","");
		countSaveOrSelectRoomNum();
	});
	// 楼层点击
	$("#bookRegistration #roomStateList .floor").unbind("click");
	$("#bookRegistration #roomStateList .floor").click(function(){
		
		var floorNo = $(this).attr("data-floorNo") ;
		var $roomsObjByClass = $("#bookRegistration #roomStateList div.floorNo_"+floorNo) ;
		if($roomsObjByClass && $roomsObjByClass.length > 0){
			$roomsObjByClass.each(function(index, element) 
			{
				// 如果当前楼层有已经确定选中的房间，排除
				var pb = $(this).nextAll(".pointStatus_block").eq(0) ;
				if(pb.length == 0){
					var roomId = $(this).attr("data-roomId") ;
					var ifSaveRoom = $(this).attr("data-ifSaveRoom") ;
					var currStat = $(this).attr("data-currStat") ;
					var selectRoom_ul_roomId = $("#selectRoom_ul #"+roomId) ;
					var saveRoom_ul_roomId = $("#saveRoom_ul #"+roomId) ;
					
					if($(this).nextAll(".pointStatus,.pointStatus_block_av").eq(0).is(":hidden")){
						$(this).nextAll(".pointStatus,.pointStatus_block_av").eq(0).show();
						$(this).nextAll(".pointStatusRight,.pointStatusRight_block_av").eq(0).show();
						
						if(ifSaveRoom == "0"){
							saveRoom_ul_roomId.css("display","none");
						}
						fillSelectRoomsUl(roomId,ifSaveRoom,bookId,currStat)
					}else{
						$(this).nextAll(".pointStatus,.pointStatus_block_av").eq(0).hide();
						$(this).nextAll(".pointStatusRight,.pointStatusRight_block_av").eq(0).hide();
						
						if(selectRoom_ul_roomId&&selectRoom_ul_roomId.length>0)
							selectRoom_ul_roomId.remove();
						if(saveRoom_ul_roomId && saveRoom_ul_roomId.length>0)
							saveRoom_ul_roomId.css("display","");
					}
				}
			});
			countSaveOrSelectRoomNum();
		}
	});	
}
// 判定留房Or选定数量
function countSaveOrSelectRoomNum(){
	//var saveRoom_li_not_hidden_length = $("#saveRoom_ul li:not(:hidden)").length ;
	var saveRoom_li_not_hidden_length = 0;
	$("#saveRoom_ul li").each(function(index , element){
		if($(element).css("display") != "none")
			saveRoom_li_not_hidden_length++;
	});
	var selectRoom_li_length = $("#selectRoom_ul li").length ;
	var selectRoom_li_clickNo_length = $("#selectRoom_ul li.clickNo").length ;
	$("#saveRoom_span").html(saveRoom_li_not_hidden_length);
	$("#selectRoom_span").html(selectRoom_li_length);
	// 右移 全部右移样式
	if(saveRoom_li_not_hidden_length > 0){
		$("#bookRegistration a.rightMove").css("cursor","pointer");
		$("#saveRoom_ul li").unbind("dblclick");
		$("#saveRoom_ul li").bind("dblclick",saveRoomLiDBLClick);
		$("#saveRoom_ul li").unbind("click");
		$("#saveRoom_ul li").bind("click",saveRoomLiClick);
		
	}else{
		$("#bookRegistration a.rightMove").css("cursor","not-allowed");
		$("#saveRoom_ul li").unbind("dblclick");
		$("#saveRoom_ul li").unbind("click");
	}
	// 判断有无权限
	var $noauthed_confirmSelect=$("#bookRegistration a#confirmSelect").attr("noauthed");
	if(typeof($noauthed_confirmSelect)=="undefined"){
		// 确认选中
		if((selectRoom_li_length-selectRoom_li_clickNo_length) > 0){
			$("#bookRegistration a#confirmSelect").attr("href" , "javascript:confirmSelect();");
			$("#bookRegistration a#confirmSelect").css({"cursor":"pointer", "color":""});
		}else{
			$("#bookRegistration a#confirmSelect").attr("href" , "javascript:;");
			$("#bookRegistration a#confirmSelect").css({"cursor":"not-allowed","color":"grey"});
		}
	}
	// 判断有无权限
	var $noauthed_confirmGiveUpSelect=$("#bookRegistration a#confirmGiveUpSelect").attr("noauthed");
	if(typeof($noauthed_confirmGiveUpSelect)=="undefined"){
		// 全部放弃
		if(selectRoom_li_length > 0){
			$("#bookRegistration a#confirmGiveUpSelect").attr("href" , "javascript:confirmGiveUpSelect();");
			$("#bookRegistration a#confirmGiveUpSelect").css({"cursor":"pointer","color":""});
			
		}else{
			$("#bookRegistration a#confirmGiveUpSelect").attr("href" , "javascript:;");
			$("#bookRegistration a#confirmGiveUpSelect").css({"cursor":"not-allowed","color":"grey"});
		}
	}
	// 办理入住
	if(selectRoom_li_clickNo_length !=0 && selectRoom_li_length == selectRoom_li_clickNo_length){
		$("#bookRegistration a#saveCheckIn").attr("href" , "javascript:saveCheckInBy();");
		$("#bookRegistration a#saveCheckIn").css({"cursor":"pointer","color":""});
	}else{
		$("#bookRegistration a#saveCheckIn").attr("href" , "javascript:;");
		$("#bookRegistration a#saveCheckIn").css({"cursor":"not-allowed","color":"grey"});
	}
}
//办理入住弹出层页面--条件查询
function checkInputFormSearch(bool , ifFillSaveRooms,autoSelectRoom){
	$.ajax({
		type: "POST",
		url:"/bookCheckIn/findRoomsBy.do",
		dataType:"json",
		data:$("#bookRegistration #checkInputForm").serialize() ,
		async:false,
		success:function(data){
			//填充房态区房间列表
			var selectRoomsIdArr = fillBookCheckInRoomsList(data.listJson , autoSelectRoom);
			if(bool == false)
				//预留房型信息列表
				fillBookCheckInInfoList(data.bookCheckInInfoListJson);
			if(ifFillSaveRooms == true)
				//填充留房列表
				fillSaveRoomsList(data.saveRoomListJson , selectRoomsIdArr);
			/*房态点击*/
		    currentRoomClick();
		}
	});
}
// 办理入住弹出层页面--清空条件
function clearCheckInputForm(){
	$("#bookRegistration #checkInputForm #roomCharaterTotalVal").val("");
	$("#bookRegistration #checkInputForm #roomCharaterTotalVal_1").val("");
	$("#bookRegistration #checkInputForm #roomTypeVal").val("");
	// 置灰  自动排房 按钮
	autoSelectRoom_css(false) ;
	$("#bookRegistration #checkInputForm")[0].reset();
}
// 确认选中
function confirmSelect(){
	flexGridSelectIndex = parseInt(flexGridSelectIndex);
	var _flexGridSelectIndex = flexGridSelectIndex ;
	var fgRow = flexGrid_bookCheckInInfo.rows[flexGridSelectIndex] ;
	var dataItem = fgRow.dataItem ;
	var roomTypeNamec0 = dataItem.roomTypeNamec0 ;
	var reachDate = dataItem.reachDate ;
	var leaveDate = dataItem.leaveDate ;
	var _reachDate = reachDate.substring(5,7) +"."+ reachDate.substring(8,10) ;
	var _leaveDate = leaveDate.substring(5,7) +"."+ leaveDate.substring(8,10) ;
	//altInfMsg("您预订的 "+dataItem.bookNum+" 间"+roomTypeNamec0+"（"+_reachDate+"~"+_leaveDate+"）<br />是否确认选房完毕？",function(){

		// 设置选中条目的样式
		fgRow.cssClass = "forbid_select_yellow" ;
		dataItem.ifSelect = "0" ;
		//设置选中区 房间禁止点击
		$("#selectRoom_ul li").css("cursor" , "not-allowed") ;
		$("#selectRoom_ul li:not(.clickNo)").addClass("clickNo");
		// 设置选中下一条数据
		var index = fgRow.index+1;
		var itemTotal = view_bookCheckInInfo.itemCount;
		for (var i = 0; i < itemTotal; i++) {
			if(flexGridSelectIndex == i)
				continue;
			var _fgRow = flexGrid_bookCheckInInfo.rows[i] ;
			var _dataItem = _fgRow.dataItem;
			// 选中
			if(_dataItem.ifSelect == "1"){
				flexGrid_bookCheckInInfo.select(i,1);
				//_fgRow.cssClass = "forbid_select_green" ;
				
				// TODO 选中后验证是否调用grid.selectionChanged事件
				// 首先清空
				clearCheckInputForm();
				$("form#checkInputForm input#reachDate_hidden,table#roomSateTable input#reachDate_readonly").val(_dataItem.reachDate);
			    $("form#checkInputForm input#leaveDate_hidden,table#roomSateTable input#leaveDate_readonly").val(_dataItem.leaveDate);
			    $("#bookRegistration #checkInputForm input#bookId").val(_dataItem.bookId);
			    // 刷新房态区 和 留房区
			    checkInputFormSearch(true , true , false);
			    flexGridSelectIndex = i ;
			    break ;
			}
		}
		// 置灰 确认选中
		$("#bookRegistration a#confirmSelect").attr("href" , "javascript:;");
		$("#bookRegistration a#confirmSelect").css({"cursor":"not-allowed","color":"grey"});
		// 激活 办理入住
		$("#bookRegistration a#saveCheckIn").attr("href" , "javascript:saveCheckInBy();");
		$("#bookRegistration a#saveCheckIn").css({"cursor":"pointer","color":""});
		// 最后一天记录选择房间结束后,设置 禁止点击样式和事件
		$("#roomStateList .pointStatus,#roomStateList .pointStatus_block_av").each(function(index , element){
			if(!$(this).is(":hidden")){
				$(this).addClass("pointStatus_block");
				$(this).nextAll(".pointStatusRight,.pointStatus_block_av").eq(0).addClass("pointStatusRight_block");
				$(this).nextAll(".pointStatusRight").eq(0).removeClass("pointStatusRight");
				$(this).nextAll(".pointStatusRight_block_av").eq(0).removeClass("pointStatusRight_block_av");
				$(this).removeClass("pointStatus");
				$(this).removeClass("pointStatus_block_av");
				
				$(this).unbind("click");
				$(this).nextAll(".pointStatusRight,.pointStatusRight_block,.pointStatus_block_av").unbind("click");
			}
		});
	//},function(){});
	return _flexGridSelectIndex ;
}
//确认全部放弃
function confirmGiveUpSelect(){
	// 清空选定房区
	$("#selectRoom_ul").html("");
	$("#selectRoom_span").html("0");
	// 清空查询条件
	clearCheckInputForm();
	$("form#checkInputForm input#reachDate_hidden").val($("#bookRegistration input#reachDate_first").val());
	$("form#checkInputForm input#leaveDate_hidden").val($("#bookRegistration input#leaveDate_first").val());
	$("#bookRegistration #checkInputForm input#bookId").val($("#bookRegistration input#bookId_first").val());
	checkInputFormSearch(false,true , false);
	countSaveOrSelectRoomNum();
}
//自动排房--css
function autoSelectRoom_css(bool){
	var $noauthed_confirmSelect=$("#bookRegistration a#confirmSelect").attr("noauthed");
	if(typeof($noauthed_confirmSelect)=="undefined"){
		if(bool){
			$("#bookRegistration #autoSelectRooom").prop("href" , "javascript:autoSelectRoom();").css({"cursor":"pointer","color":""});
		}else{
			$("#bookRegistration #autoSelectRooom").prop("href","javascript:;").css({"cursor":"not-allowed","color":"grey"});
		}
	}
}
// 自动排房
function autoSelectRoom(){
	$("#selectRoom_ul li").each(function(index,element){
		if($(element).hasClass(".clickNo")){}else{
			$(element).remove();
		}
	});
	checkInputFormSearch(true , true , true);
}
//确认全部抵达
function confirmAllReach(bookId,dataItem){
	
	// 首先确认全部选中
	var _flexGridSelectIndex = confirmSelect() ;
	
	var msg = "" ;
	// 获取 确认选中的房间
	var $selectRoom_lis = $("#selectRoom_ul li.clickNo");
	
	if(dataItem){
		var bookId_total = 0 ;
		if($selectRoom_lis && $selectRoom_lis.length > 0){
			$selectRoom_lis.each(function(index, element){
				if(bookId && $(element).attr("data-bookId") == bookId)
					bookId_total ++; 
			});
		}
		
		var roomTypeNamec0 = dataItem.roomTypeNamec0 ;
    	var reachDate = dataItem.reachDate ;
    	var leaveDate = dataItem.leaveDate ;
    	var _reachDate = reachDate.substring(5,7) +"."+ reachDate.substring(8,10) ;
    	var _leaveDate = leaveDate.substring(5,7) +"."+ leaveDate.substring(8,10) ;
    	var reachNum = parseInt(dataItem.reachNum)+bookId_total ;
    	msg = "本条记录预订"+roomTypeNamec0+dataItem.bookNum+"间"+"（"+_reachDate+"至"+_leaveDate+"），实际入住数为"+reachNum+"间，是否确认为全部抵达？" ;	
	}else{
		var sel_total = $selectRoom_lis ? $selectRoom_lis.length : 0 ;
		var itemCount = view_bookCheckInInfo.itemCount ;
		var bookNum = 0 ;
		var reachNum = 0 ;
		var fgRows = flexGrid_bookCheckInInfo.rows;
		for ( var i = 0; i < itemCount; i++) {
			var dataItem = fgRows[i].dataItem ;
			bookNum += dataItem.bookNum ;
			reachNum += dataItem.reachNum ;
		}
		var bookItemNums = $("#bookRegistration #checkInputForm #bookItemNums").val() ;
		bookItemNums = bookItemNums ? bookItemNums : itemCount ;
		msg = "本订单共有"+bookItemNums+"条预订记录，预定房间数共"+bookNum+"间，<br />实际入住数为"+(parseInt(reachNum)+sel_total)+"间，是否确认为全部抵达？" ;	
	}
	//判断是否有留房，如果有留房需要取消所有留房
	altInfMsg((isNull(msg)?"本订单是否确认全部抵达？":msg),function(){
		var checkId = $("#bookRegistration #checkInputForm input#checkId").val();
		var grpChkid = $("#bookRegistration #checkInputForm input#grpChkid").val();
		$.ajax({
			type: "POST",
			url:"/bookCheckIn/confirmAllReach.do",
			dataType:"json",
			data:{checkId:checkId,bookId:bookId,grpChkid:grpChkid},
			async:false,
			success:function(data){
				if(data.success){
					/*if(!bookId){
						// 1：关闭页面 
						closeBookRegistration();
						// 2：刷新父列表页面
						searchForm();
					}
					else{*/
						// currentItemFlag == fit  散客   group 团队
						var currentItemFlag = $("#currentItemFlag").val() ;
						var url = (currentItemFlag == 'fit' ? "/bookCheckIn/fitCheckIn.do" : "/bookCheckIn/groupCheckIn.do" ) ;
						$("#bookRegistration #checkInputForm input#bookId").val("") ;
						$.ajax({
							type: "POST",
							url:url,
							dataType:"json",
							data:$("#bookRegistration #checkInputForm").serialize() ,
							async:false,
							success:function(data){
								var list = data.bookCheckInInfoListJson ;
								// if 预留信息列表为空  则 关闭浮层并刷新父页面
								if(list && list.length>0){
									//填充房态区房间列表
									fillBookCheckInRoomsList(data.listJson , false);
									//预留房型信息列表
									fillBookCheckInInfoList(data.bookCheckInInfoListJson);
									//填充留房列表
									fillSaveRoomsList(data.saveRoomListJson , null );
									/*房态点击*/
									currentRoomClick();
								}else{
									//填充房态区房间列表
									fillBookCheckInRoomsList(null , false);
									//预留房型信息列表
									fillBookCheckInInfoList(null);
									// 关闭
									//closeBookRegistration() ;
								}
							    // 2：刷新父列表页面
								//searchForm();
							}
						});
					//}
					
				}else{
					altEerrMsg('操作异常',function(){});
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				altEerrMsg('出现异常',function(){});
			}
		});
	},function(){
		// 取消后的操作
		
		// 1.获取全部抵达的条目
		var fgRow = flexGrid_bookCheckInInfo.rows[_flexGridSelectIndex] ;
		// 2.还原全部抵达条目的样式
		fgRow.cssClass = "" ;
		// 3.获取全部抵达的条目的数据
		var dataItem = fgRow.dataItem ;
		// 4.设置该条目可选
		dataItem.ifSelect = "1" ;
		// 5.默认光标移入该条目
		flexGrid_bookCheckInInfo.select(_flexGridSelectIndex,1);
		// 6.还原当前选中的条目标识
		flexGridSelectIndex = _flexGridSelectIndex ;
		flexGrid_bookCheckInInfo.refresh();
	});
}
// 办理入住
function saveCheckInBy(){
	// currentItemFlag == fit  散客   group 团队
	var currentItemFlag = $("#currentItemFlag").val() ;
	var currStat1 = "所选房间中有清洁未查空房，确定要办理入住吗？" ;
	var currStat2 = "所选房间中有不洁空房，确定要办理入住吗？" ;
	var currStat3 = "所选房间中有清洁未查空房和不洁空房，确定要办理入住吗？" ;
	var currStat_VDP = 0 ;
	var currStat_VCP = 0 ;
	$("#selectRoom_ul li").each(function(index, element){
		var _currStat = $(element).attr('data-currStat') ;
		var roomId = $(element).prop("id") ;
		if(_currStat){
			switch (_currStat) {
			case 'VDP':
				currStat_VDP++ ;
			case 'VD':
				currStat_VDP++ ;
				break;
			case 'VCP':
				currStat_VCP++ ;
				break;
			default:
				break;
			}
		}
	});	
	var currStat_msg = (currStat_VDP > 0 && currStat_VCP > 0 ? currStat3 : (currStat_VDP > 0 ? currStat2 : (currStat_VCP > 0 ? currStat1 : ""))) ;
	if(currStat_msg != ""){
		altInfMsg(currStat_msg,function(){
			if(currentItemFlag == 'fit'){
				openGuestDetail('fit');
			}else if(currentItemFlag == 'group'){
				openGuestDetail('group');
			}
		},function(){});
	}else{
		if(currentItemFlag == 'fit'){
			openGuestDetail('fit');
		}else if(currentItemFlag == 'group'){
			openGuestDetail('group');
		}
	}
}

/**
*@描述 打开预定入住
*/
var openGuestDetail = function(type)
{
	
	var roomId = "";
	$("#selectRoom_ul").find("li").each(function(index, el)
	{
		roomId += $(el).prop("id") + "|" + $(el).attr("data-ifsaveroom") + ",";
	});
	//截取roomId
	if(roomId.indexOf(",") != -1)
	{
		roomId = roomId.substring(0,roomId.length-1);
	}
	var bookId = $("#selectRoom_ul li:first").attr("data-bookid");
	var checkId = $("#bookRegistration #checkInputForm #checkId").val() ;
	var withId = $("#bookRegistration #checkInputForm #withId").val() ;
	var sendData = {'roomId':roomId,'withId':withId
					,'checkId':checkId,'bookId':bookId,'openType':type};
	var basePath = $("#path").val();
	var url = basePath + "/guestdetail/reserveCheckIn.do";
	var params = {type:'page',title:'客单详情（在住）'
				,id:'room_guestdetail_info'
				,content:url
				,titleClass:"alertDiv checkInDiv moveBar checkDetailsDiv"
				,data:sendData
				,isUpdate:true
				,show:function()
				{
					closeBookRegistration();
				}
				,width:1072
				,top:5};
	$.fn.alertDialog(params);
	//closeBookRegistration();
};

// 获取选定房间的房间号
function getSelectRoomIds(){
	var $selectRoom_lis = $("#selectRoom_ul li");
	var arr = [] ;
	if($selectRoom_lis && $selectRoom_lis.length > 0){
		$selectRoom_lis.each(function(index, element){
			arr.push($.trim($(element).prop("id")));
		});
	}
	return arr ;
}
//获取已经选定房间的房间号
function getSelectRoomIds_clickNo(){
	var $selectRoom_lis = $("#selectRoom_ul li.clickNo");
	var arr = [] ;
	if($selectRoom_lis && $selectRoom_lis.length > 0){
		$selectRoom_lis.each(function(index, element){
			arr.push($.trim($(element).prop("id")));
		});
	}
	return arr ;
}
//获取已经选定房间的房间号
function getSelectRoomIds_not_clickNo(){
	var $selectRoom_lis = $("#selectRoom_ul li");
	var arr = [] ;
	if($selectRoom_lis && $selectRoom_lis.length > 0){
		$selectRoom_lis.each(function(index, element){
			if($(this).hasClass("clickNo")){}else{
				arr.push($.trim($(element).prop("id")));
			}
		});
	}
	return arr ;
}
//强制保留2位小数，如：2，会在2后面补上00.即2.00    
function toDecimal2(num) {    
    var f = parseFloat(num);    
    if (isNaN(f)) {    
        return "0.00";    
    }    
    var f = Math.round(num*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }    
    while (s.length <= rs + 2) {    
        s += '0';    
    }    
    return s;    
}