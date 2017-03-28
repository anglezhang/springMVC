var formOptions ;
var flexGrid ;
var view ;
$(function(){
	/*小键盘点击切换背景*/
    $(".cabDiv a").click(function() {
        $(".cabDiv a").removeClass("pointCab");
        $(this).addClass("pointCab");
    });
    /*小键盘点击切换背景END*/
	// 设置当前二级标题的选中
	setSecondMenuStat(2);
	
	/*遮罩层DIV高度*/
	$(".alertDivBg").css("height",$(document).height());     
    $(".alertDivBg").css("width",$(document).width());  
	/*遮罩层DIV高度*/
    
	//房态右Tab页  右侧的
	$(".roomStatusTitle li").click(function(){
		// 如果当前页签选中则返回
		if($(this).hasClass("point"))
			return false ;
		$(".roomStatusTitle li").removeClass("point");
		$(this).addClass("point");
		
		// 清除列表表单元素
		clearSearchForm();
		// 清除办理入住页面查询条件
		clearCheckInputForm();
		$("form#checkInputForm input#reachDate_hidden").val($("#bookRegistration input#reachDate_first").val());
		$("form#checkInputForm input#leaveDate_hidden").val($("#bookRegistration input#leaveDate_first").val());
		$("#bookRegistration #checkInputForm input#bookId").val($("#bookRegistration input#bookId_first").val());
		
		if(this.id == "groupBookCheckInTitle"){
			$("#bookCheckInForm,#bookCheckInForm1").prop("action" , "/bookCheckIn/findGroupOrderList.do") ;
			$(".fitBookCheckInSearchItem").css("display" , "none");
			$(".groupBookCheckInSearchItem").css("display" , "");
			$("#currentItemFlag").val("group");
			// 初始化默认展示团队订单
			fillGroupOrderListTable(null);
		}else{
			$("#bookCheckInForm,#bookCheckInForm1").prop("action" , "/bookCheckIn/findFitOrderList.do") ;
			$(".groupBookCheckInSearchItem").css("display" , "none");
			$(".fitBookCheckInSearchItem").css("display" , "");
			$("#currentItemFlag").val("fit");
			// 初始化默认展示散客订单
			fillFitOrderListTable(null);
		}
	});
	/*选定房间*/
	// 办理入住 右上角关闭按钮
	$("#bookRegistration h4 a").click(function(){
		// 清除表单元素
		clearCheckInputForm();
		$(".alertDivBg").fadeOut();
		$("#bookRegistration").fadeOut();
	});
	// 抵达日期
	/*$("#reachDate1").focus(function() {
		WdatePicker({
			lang : 'zh-cn',
			readOnly : true,
			isShowOthers : true,//是否显示其他月份
			dateFmt:"yyyy-MM-dd",
			minDate : '#F{\'%y-%M-%d\'}' , 
			maxDate : '#F{$dp.$D(\'leaveDate1\')}'
		});
	});*/
	// 离店日期
	$("#leaveDate1").focus(function() {
		WdatePicker({
			lang : 'zh-cn',
			readOnly : true,
			isShowOthers : true,//是否显示其他月份
			dateFmt:"yyyy-MM-dd",
			minDate:'#F{$dp.$D(\'reachDate1\')}'
			//minDate : '#F{$dp.$D(\'orderTime\')}' , 
			//maxDate : '#F{$dp.$D(\'endTime\')||\'%y-%M-%d %H:%m:%s\'}'
		});
	});
	// 初始化默认展示散客订单
	fillFitOrderListTable(null);
	//异步提交表单的options对象
	formOptions = {
		success:ajaxSubmitSuccess,  // 提交后的回调函数
		dataType:'json'
		//clearForm : true  // 清除表单元素
	} ;
	
	$("#bookCheckInForm input").inputmask();
	// ^[\u2E80-\u9FFF]+$        onkeyup="value=value.replace(/[ -~]/g,'')"
	//$("#bookCheckInForm input#gstName").inputmask('Regex', { regex: "^[\Α-\￥]+$" });
});
// 查询散客列表
function fitBookCheckInList(){
	
	$.ajax({
		type: "POST",
		url:"/bookCheckIn/findFitOrderList.do",
		dataType:"json",
		async:false,
		success:function(data){
			if(data.listJson){
			    fillFitOrderListTable(data.listJson);
			}
		}
	});
}
//表单提交后响应的内容 和状态（success，error等）
function ajaxSubmitSuccess(reponseText , statusText){
	if(statusText == "success"){
		if(reponseText.flag == "fit"){
			fillFitOrderListTable(reponseText.listJson);
		}
		else if(reponseText.flag == "group"){
			fillGroupOrderListTable(reponseText.listJson);
		}
	}
	else{
		console.log("查询失败");
	}
}
//条件查询
function searchForm(){
	$("#bookCheckInForm").ajaxSubmit(formOptions);	
}
//键盘 条件查询
function searchFormForKeys(codeLetter){
	if(codeLetter)
		if (codeLetter != "*" && codeLetter != "#"){
			$("#codeLetter").val(codeLetter);
			$("#symbol").val("");
		}
        else if (codeLetter == "*"){
        	//无效
        	$("#symbol").val(1);
        	$("#codeLetter").val("");
        }
        else if (codeLetter == "#"){
        	//有效
        	$("#symbol").val("");
        	$("#codeLetter").val("");
        }
            
	$("#bookCheckInForm1").ajaxSubmit(formOptions);	
}
//清空条件
function clearSearchForm(){
	$('#bookCheckInForm')[0].reset();
}
//订单详情
function orderDetail(){
	
	var selection = flexGrid.selection ;
	if(selection && selection.bottomRow >=0){
		var fgRow = flexGrid.rows[selection.bottomRow] ;
		var bookOrderNo = fgRow ? (fgRow.dataItem ? fgRow.dataItem.bookOrderNo : null) : null ;
		var checkId = fgRow ? (fgRow.dataItem ? fgRow.dataItem.checkId : null) : null ;
		var withId = fgRow ? (fgRow.dataItem ? fgRow.dataItem.withId : null) : null ;
		if(bookOrderNo && checkId){
			var currentItemFlag = $("#currentItemFlag").val();
			if(currentItemFlag == "fit"){
				$("#bookDetail span#orderDetailTitle").html("散客预定");
				$("#bookDetail").fadeIn();
				$(".alertDivBg").fadeIn();
			}else{
				$("#bookDetail span#orderDetailTitle").html("团队预定");
				$("#bookDetail").fadeIn();
				$(".alertDivBg").fadeIn();
			}
		}else{
			altWaringMsg('订单号或者chekId为空',function(){});
		}
	}else{
		altWaringMsg('请先选中一个预订单',function(){});
	}
}
//办理入住
function checkIn(){
	var selection = flexGrid.selection ;
	if(selection && selection.bottomRow >=0){
		var fgRow = flexGrid.rows[selection.bottomRow] ;
		var bookOrderNo = fgRow ? (fgRow.dataItem ? fgRow.dataItem.bookOrderNo : null) : null ;
		var checkId = fgRow ? (fgRow.dataItem ? fgRow.dataItem.checkId : null) : null ;
		var withId = fgRow ? (fgRow.dataItem ? fgRow.dataItem.withId : null) : null ;
		if(bookOrderNo && checkId){
			var currentItemFlag = $("#currentItemFlag").val();
			if(currentItemFlag == "fit")
				fitCheckIn(bookOrderNo , checkId , withId);
			else
				groupCheckIn(bookOrderNo , checkId, withId);
		}else{
			altWaringMsg('订单号或者chekId为空',function(){});
		}
	}else{
		altWaringMsg('请先选中一个预订单',function(){});
	}
	
	
	
}
// 散客办理预定入住
function fitCheckIn(bookOrderNo , checkId , withId){
	$.ajax({
		type: "POST",
		url:"/bookCheckIn/fitCheckIn.do",
		dataType:"json",
		data:{checkId:checkId,bookList:bookOrderNo},
		async:false,
		success:function(data){
			if(data)
				dataToList(data,bookOrderNo , checkId , withId , 'fit');
		}
	});
}
//团体办理预定入住
function groupCheckIn(bookOrderNo , checkId, withId){
	$.ajax({
		type: "POST",
		url:"/bookCheckIn/groupCheckIn.do",
		dataType:"json",
		data:{checkId:checkId,bookList:bookOrderNo},
		async:false,
		success:function(data){
			if(data)
				dataToList(data,bookOrderNo , checkId , withId , 'group');
		}
	});
}
function dataToList(data, bookOrderNo , checkId , withId , flag){
	// 预留信息列表
	var bookCheckInInfoListJson = data.bookCheckInInfoListJson;
	fillBookCheckInInfoList(bookCheckInInfoListJson);
	// 房态区列表
	fillBookCheckInRoomsList(data.listJson,false);
   
    // 隐藏域赋值
    $("#bookRegistration #checkInputForm #checkId").val(checkId);
    if(flag == 'group')
    	$("#bookRegistration #checkInputForm #grpChkid").val(checkId);
    $("#bookRegistration #checkInputForm #bookList").val(bookOrderNo);
    $("#bookRegistration #checkInputForm #withId").val(withId);
    $("#bookRegistration #checkInputForm #bookItemNums").val(data.bookRoomListSize);
    
    // 建筑物
    var buildList = data.buildListJson ; 
    var option_html = "<option value=\"\" data-name=\"\">全部</option>" ;
    if(buildList && buildList.length > 0){
    	for(var o = 0 ; o < buildList.length ; o++){  
	    	option_html += "<option value=\""+buildList[o].codeId+"\" data-name=\""+buildList[o].floors+"\">"+buildList[o].codeNamec+"</option>" ;
	    }
    }
    $("#bookRegistration #buildId").html(option_html);
    // 初始化楼层select
    buildChange(null);
    // 已入住人员信息列表
    var alreadyCheckInPersonList = data.alreadyCheckInPersonList ;
    var alreadyCheckInPerson_li_html = "" ;
    var alreadyCheckInPerson_li_index = 1 ;
    if(alreadyCheckInPersonList && alreadyCheckInPersonList.length > 0){
    	for(var o = 0 ; o < alreadyCheckInPersonList.length ; o++){  
    		alreadyCheckInPerson_li_html += "<li>";
    		alreadyCheckInPerson_li_html += "	<table width=\"300\">";
    		alreadyCheckInPerson_li_html += "		<tr>";
    		alreadyCheckInPerson_li_html += "			<td width=\"10%\">"+alreadyCheckInPerson_li_index+"</td>";
    		alreadyCheckInPerson_li_html += "			<td width=\"20%\">"+alreadyCheckInPersonList[o].roomId+"</td>";
    		alreadyCheckInPerson_li_html += "			<td>"+alreadyCheckInPersonList[o].gstNamec+"/"+alreadyCheckInPersonList[o].gstNamee+"</td>";
    		alreadyCheckInPerson_li_html += "		</tr>";
    		alreadyCheckInPerson_li_html += "	</table>";
    		alreadyCheckInPerson_li_html += "</li>";
    		alreadyCheckInPerson_li_index++;
    	}
    }
    $("#alreadyCheckInPersonListUL").html(alreadyCheckInPerson_li_html);
    // 打开弹出层
    $("#bookRegistration").fadeIn();
    $(".alertDivBg").fadeIn();
}
function fillFitOrderListTable(listJson){
	var array = [];	
	var data = listJson ;
	var count = data ? data.length : 0;
	var index = 0 ;
	var _bookOrderNo = "" ;
	for ( var i = 0; i < count; i++) {
		var bookStat = "";
		var _bookStat = data[i].bookStat ;
		if (_bookStat == 'B') {
			bookStat = "未确认";
		} else if (_bookStat == 'C') {
			bookStat = "取消";
		}else if (_bookStat == 'N') {
			bookStat = "Noshow";
		}else if (_bookStat == 'R') {
			bookStat = "部分抵达";
		}else if (_bookStat == 'A') {
			bookStat = "全部抵达";
		}else if (_bookStat == 'O') {
			bookStat = "已确认";
		}
		var bookOrderNo = data[i].bookOrderNo ;
		if(_bookOrderNo != bookOrderNo){
			index++;
		}
		_bookOrderNo = bookOrderNo ;
		array.push({
			"index": index,
			"bookOrderNo": data[i].bookOrderNo,
			"gstNamec": data[i].gstNamec,
			"gstNamee": data[i].gstNamee,
			"roomTypeNamec": data[i].roomTypeNamec,
			"reachDate": data[i].reachDate,
			"leaveDate": data[i].leaveDate,
			"saveNum": data[i].saveNum,
			"reachNum": data[i].reachNum,
			"bookNum": data[i].bookNum,
			"bookStat": bookStat,
			"withId": data[i].withId,
			"checkId": data[i].checkId
		});
	}
	// create CollectionView on the data (to get events)
	view = new wijmo.collections.CollectionView(array);
	view.trackChanges = true;
	//Disposes of the control by removing its association with the host element.
	if(flexGrid)
		flexGrid.dispose();
	
	// initialize the grid
	flexGrid = new wijmo.grid.FlexGrid('#orderList', {
			autoGenerateColumns: false,
			autoClipboard:true,
			selectionMode : wijmo.grid.SelectionMode.RowRange,
			allowMerging: wijmo.grid.AllowMerging.All,
			columns:[
			    { header:"序号",binding:"index",cssClass:"fg_column_readOnly",name:"h_index", align:"center", minWidth:40,width:60 ,isReadOnly:true, allowMerging: true,allowSorting:false},           
			    { header:"订单号", binding:"bookOrderNo",name:"h_bookOrderNo" , align:"center", minWidth:60,width:100,isReadOnly:true, allowMerging: true }, 
			    { header:"中文名", binding:"gstNamec",name:"h_gstNamec", align:"left", minWidth:60 ,width:90,isReadOnly:true, allowMerging: true }, 
			    { header:"英文名", binding:"gstNamee",name:"h_gstNamee" , minWidth:60,width:90,isReadOnly:true, allowMerging: true },
			    { header:"房类",binding:"roomTypeNamec",name:"h_roomTypeNamec" , minWidth:60, width:90,isReadOnly:true,allowSorting:false},
			    { header:"抵店日期",binding:"reachDate",name:"h_reachDate" , align:"center", minWidth:80, width:100,isReadOnly:true,allowSorting:false},
			    { header:"离店日期",binding:"leaveDate",name:"h_leaveDate" , align:"center", minWidth:80, width:100,isReadOnly:true,allowSorting:false},
			    { header:"订房数",binding:"bookNum",name:"h_bookNum" , align:"center", minWidth:60, width:80,isReadOnly:true,allowSorting:false},
			    { header:"留房数",binding:"saveNum",name:"h_saveNum" , align:"center", minWidth:60, width:80,isReadOnly:true,allowSorting:false},
			    { header:"抵达数",binding:"reachNum",name:"h_reachNum" , align:"center", minWidth:60, width:80,isReadOnly:true,allowSorting:false},
			    { header:"状态",binding:"bookStat", name:"h_bookStat", align:"center", minWidth:60 ,width:80,isReadOnly:true, allowMerging: true},
			    { header:"登记号",binding:"checkId",name:"h_checkId" ,format:'f0', align:"center", minWidth:80, width:103,isReadOnly:true, allowMerging: true },
			    { header:"withId",binding:"withId",name:"h_withId" , align:"center",isReadOnly:true,visible:false}
			],
			allowAddNew: false,
			// 拖拽
			allowDragging:wijmo.grid.AllowDragging.None,
			// 设置列/行的显示
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			itemsSource : view
		}
	);
	flexGrid.select(-1,-1);
	flexGrid.mergeManager = new wijmo.grid.CustomMergeManager(flexGrid, 'checkId');
	flexGrid.selectionChanged.addHandler(function (s, e) {
         if (flexGrid.getMergedRange(e.panel, e.row, e.col) != null) {
             if (flexGrid.getMergedRange(e.panel, e.row, e.col).isSingleCell == false) {
            	 flexGrid.select(flexGrid.getMergedRange(e.panel, e.row, e.col), true);
             }
         }else{
             for(var c=0;c<flexGrid.columns.length;c++){
                 if(flexGrid.getMergedRange(e.panel,e.row,c)!=null){
                     if (flexGrid.getMergedRange(e.panel, e.row, c).isSingleCell == false) {
                    	 flexGrid.select(flexGrid.getMergedRange(e.panel, e.row, c), true);
                     }
                 }
             }
         }
	});
	var host = flexGrid.hostElement;
    //双击进入 办理入住页面
    host.addEventListener('dblclick', function (e) {
        var ht = flexGrid.hitTest(e);
        if (ht.cellType == wijmo.grid.CellType.Cell) {
        	checkIn();
        }
        e.preventDefault();
    });
    // 设置居中
    flexGrid.itemFormatter = function(panel, r, c, cell) {
    	// reset attributes we are about to customize
		var s = cell.style;
		// 由于pading = 3px; 所以 -6
		s.lineHeight = (parseInt(s.height.replace("px")) - 6)+"px";
    }
}
function fillGroupOrderListTable(listJson){
	var array = [];	
	var data = listJson ;
	var count = data ? data.length : 0;
	var index = 0 ;
	var _bookOrderNo = "" ;
	for ( var i = 0; i < count; i++) {
		var bookStat = "";
		var _bookStat = data[i].bookStat ;
		if (_bookStat == 'B') {
			bookStat = "未确认";
		} else if (_bookStat == 'C') {
			bookStat = "取消";
		}else if (_bookStat == 'N') {
			bookStat = "Noshow";
		}else if (_bookStat == 'R') {
			bookStat = "部分抵达";
		}else if (_bookStat == 'A') {
			bookStat = "全部抵达";
		}else if (_bookStat == 'O') {
			bookStat = "已确认";
		}
		var bookOrderNo = data[i].bookOrderNo ;
		if(_bookOrderNo != bookOrderNo){
			index++;
		}
		_bookOrderNo = bookOrderNo ;
		array.push({
			"index": index,
			"bookOrderNo": data[i].bookOrderNo,
			"grpId": data[i].grpId,
			"grpName": data[i].grpName,
			"roomTypeNamec": data[i].roomTypeNamec,
			"reachDate": data[i].reachDate,
			"leaveDate": data[i].leaveDate,
			"saveNum": data[i].saveNum,
			"reachNum": data[i].reachNum,
			"bookNum": data[i].bookNum,
			"bookStat": bookStat,
			"withId": data[i].withId,
			"checkId": data[i].checkId
		});
	}
	// create CollectionView on the data (to get events)
	view = new wijmo.collections.CollectionView(array);
	view.trackChanges = true;
	//Disposes of the control by removing its association with the host element.
	if(flexGrid)
		flexGrid.dispose();
	
	// initialize the grid
	flexGrid = new wijmo.grid.FlexGrid('#orderList', {
			autoGenerateColumns: false,
			autoClipboard:true,
			selectionMode : wijmo.grid.SelectionMode.RowRange,
			allowMerging: wijmo.grid.AllowMerging.All,
			columns:[
			    { header:"序号",binding:"index",cssClass:"fg_column_readOnly",name:"h_index", align:"center", minWidth:40,width:60 ,isReadOnly:true,allowMerging: true,allowSorting:false},    
			    { header:"订单号", binding:"bookOrderNo",name:"h_bookOrderNo" , align:"center", minWidth:60,width:100,isReadOnly:true, allowMerging: true}, 
			    { header:"团代码", binding:"grpId",name:"h_grpId", align:"left", minWidth:60 ,width:90,isReadOnly:true, allowMerging: true}, 
			    { header:"团名", binding:"grpName",name:"h_grpName" , minWidth:60,width:90,isReadOnly:true, allowMerging: true},
			    { header:"房类",binding:"roomTypeNamec",name:"h_roomTypeNamec" , minWidth:60, width:90,isReadOnly:true,allowSorting:false},
			    { header:"抵店日期",binding:"reachDate",name:"h_reachDate" , align:"center", minWidth:80, width:100,isReadOnly:true,allowSorting:false},
			    { header:"离店日期",binding:"leaveDate",name:"h_leaveDate" , align:"center", minWidth:80, width:100,isReadOnly:true,allowSorting:false},
			    { header:"订房数",binding:"bookNum",name:"h_bookNum" , align:"center", minWidth:60, width:80,isReadOnly:true,allowSorting:false},
			    { header:"留房数",binding:"saveNum",name:"h_saveNum" , align:"center", minWidth:60, width:80,isReadOnly:true,allowSorting:false},
			    { header:"抵达数",binding:"reachNum",name:"h_reachNum" , align:"center", minWidth:60, width:80,isReadOnly:true,allowSorting:false},
			    { header:"状态",binding:"bookStat", name:"h_bookStat", align:"center", minWidth:60 ,width:80,isReadOnly:true, allowMerging: true},
			    { header:"登记号",binding:"checkId",name:"h_checkId" , align:"center", minWidth:80, width:103,isReadOnly:true, allowMerging: true,format:'f0'},
			    { header:"withId",binding:"withId",name:"h_withId" , align:"center",isReadOnly:true,visible:false}
			],
			allowAddNew: false,
			// 拖拽
			allowDragging:wijmo.grid.AllowDragging.None,
			// 设置列/行的显示
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			itemsSource : view
		}
	);
	flexGrid.select(-1,-1);
	flexGrid.mergeManager = new wijmo.grid.CustomMergeManager(flexGrid, 'checkId');
	flexGrid.selectionChanged.addHandler(function (s, e) {
         if (flexGrid.getMergedRange(e.panel, e.row, e.col) != null) {
             if (flexGrid.getMergedRange(e.panel, e.row, e.col).isSingleCell == false) {
            	 flexGrid.select(flexGrid.getMergedRange(e.panel, e.row, e.col), true);
             }
         }else{
             for(var c=0;c<flexGrid.columns.length;c++){
                 if(flexGrid.getMergedRange(e.panel,e.row,c)!=null){
                     if (flexGrid.getMergedRange(e.panel, e.row, c).isSingleCell == false) {
                    	 flexGrid.select(flexGrid.getMergedRange(e.panel, e.row, c), true);
                     }
                 }
             }
         }
	});
	var host = flexGrid.hostElement;
    //双击进入 办理入住页面
    host.addEventListener('dblclick', function (e) {
        var ht = flexGrid.hitTest(e);
        if (ht.cellType == wijmo.grid.CellType.Cell) {
        	checkIn();
        }
        e.preventDefault();
    });
    // 设置居中
    flexGrid.itemFormatter = function(panel, r, c, cell) {
    	// reset attributes we are about to customize
		var s = cell.style;
		// 由于pading = 3px; 所以 -6
		s.lineHeight = (parseInt(s.height.replace("px")) - 6)+"px";
    }
}
	