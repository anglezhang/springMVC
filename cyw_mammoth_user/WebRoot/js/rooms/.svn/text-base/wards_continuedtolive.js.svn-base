//----------------------------------------comment-----------------------------------------
//匹配日期格式的正则表达式
var reg = new RegExp(
		"^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$");
var arrayA = [];// 换房Agrid数据
var flexGridA;// 换房Agrid对象
var viewA;// 换房Agrid视图
var arrayB = [];// 换房Bgrid数据
var flexGridB;// 换房Bgrid对象
var viewB;// 换房Bgrid视图
var arrayP = [];// 续住grid数据
var flexGridP;// 续住grid对象
var viewP;// 续住grid视图
var isSearch = true;
$(function() {
	arrayA = [];
	viewA = new wijmo.collections.CollectionView(arrayA);
	flexGridA = createFlexGrid("#roomsAdiv", viewA, "A");

	arrayB = [];
	viewB = new wijmo.collections.CollectionView(arrayB);
	flexGridB = createFlexGrid("#roomsBdiv", viewB, "B");

	/* 遮罩层DIV高度 */
	$(".alertDivBg").css("height", $(document).height());
	$(".alertDivBg").css("width", $(document).width());
	/* 遮罩层DIV高度 */

	/* table点击换行颜色 */

	$(".tabChangBg tr").click(function() {
		$(".tabChangBg tr td").removeClass("thisTrTd");
		$(this).find("td").addClass("thisTrTd");
	});
	/* /table点击换行颜色 */
	/* 关闭换房续住 */
	$(".closeDiv").click(function() {
//		$(".changeRoomDiv").fadeOut();

		document.location.href = $("#path").val()+"/rooms.do";
	});
	/* 关闭换房续住END */
	$(".chaRomPoint1").click(function() {
		if($("#radio_check").val() == "P"){
			giveUp();
		}
		$("#radio_check").val("H");
		$(".chaRom1").css("display", "block");
		$(".chaRom2").css("display", "none");
	});
	$(".chaRomPoint2").click(function() {
		if($("#radio_check").val() == "H"){
			giveUpP();
		}
		$("#radio_check").val("P");
		$(".chaRom2").css("display", "block");
		$(".chaRom1").css("display", "none");
		viewP = new wijmo.collections.CollectionView(arrayP);
		viewP.trackChanges = true;
		flexGridP = createFlexGridP("#divchangetoroom", viewP);
	});
	/* 修改离店时间 */
	$("#changeLeaveTime").click(function() {
		// 点击批量续住验证有没有选中住客
		var rowsP = flexGridP.rows;
		var temp = 0;
		for ( var i = 0; i < rowsP.length; i++) {
			if (rowsP[i].isSelected) {
				temp++;
			}
		}
		if (temp == 0) {
			altWaringMsg("请选中需要续住的住客。");
			return;
		}
		$(".changeLeaveTimeDiv").css("display", "block");
		$(".alertDivBg2").css("display", "block");
		$.ajax({
			url : $("#path").val() + "/rooms/hoteldate.do",
			type : "post",
			data : {
			},
			success : function(data) {
				$("#newleavedate").focus(function() {
					WdatePicker({
						lang : 'zh-cn',
						readOnly : true,
						isShowOthers : true,//是否显示其他月份
						dateFmt:"yyyy-MM-dd",
						minDate:data
					});
				});
				var str = data.replace(/-/g, '/');
				var newDate = new Date(str);
				newDate.setDate(newDate.getDate() + 1);
				str = newDate.getFullYear() + "-" + (newDate.getMonth() > 8 ? (newDate.getMonth()+1)+"" : "0"+(newDate.getMonth() + 1)) + "-" + (newDate.getDate() > 9 ? newDate.getDate()+"" : "0"+newDate.getDate()) ;
				$("#newleavedate").val(str);
			}
		});
	});
	/* 修改离店时间END */
	$(".closeAlertTips").click(function() {
		$(".alertDivBg2").fadeOut();
		$(".changeLeaveTimeDiv").fadeOut();
	});
	$("#cancel").click(function() {
		$('#newleavedate').val("");
		$(".alertDivBg2").fadeOut();
		$(".changeLeaveTimeDiv").fadeOut();
	});
	setSecondMenuStat(4);
});
function exitdiv(){

	document.location.href = $("#path").val()+"/rooms.do";
}
// ----------------------------------------续住操作--------------------------------------------
function goShowButtonP(bool) {
	if (bool) {
		$("a#okP,a#cancelP").css({
			"cursor" : "pointer",
			"color" : "inherit"
		});

		$("#okP").attr("href", "javascript:saveDatalive();");
		$("#cancelP").attr("href", "javascript:giveUpP();");
	} else {
		$("a#okP,a#cancelP").css({
			"cursor" : "not-allowed",
			"color" : "grey"
		});

		$("#okP").attr("href", "javascript:;");
		$("#cancelP").attr("href", "javascript:;");
	}
}
function giveUpP() {
	// window.location.href = $("#path").val() + "/rooms/wardtolive.do";
	$(".chaRom2 input:text").val("");
	$(".chaRom2 input:checkbox").removeAttr("checked");

	if (typeof flexGridP !== "undefined") {
		flexGridP.dispose();
		flexGridP = undefined;
	}
	arrayP = [];
	viewP = new wijmo.collections.CollectionView(arrayP);
	flexGridP = createFlexGridP("#divchangetoroom", viewP);

	$("#room_idP").attr("readonly", false);
	$("#grp_id_nameP").attr("readonly", false);
	$("#room_idP").removeClass("gry_9");
	$("#grp_id_nameP").removeClass("gry_9");
	goShowCheckBox(true);

	goShowButtonP(false);
}

function createFlexGridP(divid, view) {
	var flexGrid = new wijmo.grid.FlexGrid(divid, {
		autoGenerateColumns : false,
		headersVisibility : wijmo.grid.HeadersVisibility.Column,
		selectionMode : wijmo.grid.SelectionMode.ListBox,
		columns : [ {
			header : "check_id",
			binding : "check_id",
			minWidth : 30,
			width : 50,
			visible : false,
			align : "center"
		}, {
			header : "序号",
			binding : "index",
			width : "*",
			width : 50,
			isReadOnly : true,
			align : "center"
		}, {
			header : "中文名",
			binding : "gst_namec",
			width : "*",
			isReadOnly : true,
			align : "center"
		}, {
			header : "英文名",
			binding : "gst_namee",
			width : 70,
			isReadOnly : true,
			align : "center"
		}, {
			header : "抵店日期",
			binding : "reach_date",
			width : "*",
			isReadOnly : true,
			align : "center"
		}, {
			header : "离店日期",
			binding : "leave_date",
			width : "*",
			isReadOnly : false,
			align : "center",
			format:"yyyy-MM-dd"
		}, {
			header : "leave_date_old",
			binding : "leave_date_old",
			width : "*",
			visible : false,
			align : "center"
		}, {
			header : "房间号",
			binding : "room_id",
			width : 60,
			isReadOnly : true,
			align : "center"
		}, {
			header : "房租",
			binding : "room_price",
			width : 60,
			isReadOnly : true,
			align : "center"
		}, {
			header : "身份",
			binding : "chk_ext_show",
			width : 50,
			isReadOnly : true,
			align : "center"
		}, {
			header : "If_bdate",
			binding : "If_bdate",
			width : 60,
			visible : false
		} ],
		itemsSource : view
	});

	// 禁止默认选中第一行
	flexGrid.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);

	// 单元格修改完成后检查修改的日期是否符合要求
	flexGrid.cellEditEnded.addHandler(function(s, e) {
		//当前修改的日期
		var item = view.currentItem;
		var curr_leave_date = item.leave_date.getFullYear()+"-"+(item.leave_date.getMonth() > 8 ? item.leave_date.getMonth()+1 : "0" + (item.leave_date.getMonth() +1))+"-"+(item.leave_date.getDate()>9?item.leave_date.getDate() : "0"+item.leave_date.getDate());

		var leave_date_old = item.leave_date_old;
		//原离开日期
		var dtArr = leave_date_old.split("-");
		var leave_date_old = new Date(dtArr[0], parseInt(dtArr[1])-1, dtArr[2]) ;
		if (!reg.test(curr_leave_date)) {
			altWaringMsg("离店日期格式不正确。");
			item.leave_date = leave_date_old;
			return;
		} 
		// 同房间一个人的离店日期修改，其他的也修改（同一个房间所有人的离店日期相同）
		var pitems = view.items;
		var prows = flexGrid.rows;
		for ( var i = 0; i < pitems.length; i++) {
			if (pitems[i].room_id == item.room_id
					&& pitems[i].check_id != item.check_id) {
				pitems[i].leave_date = item.leave_date;
			}
		}
		view.refresh();
		flexGrid.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
		refreshColor();
	});
	createEditor(flexGrid.columns.getColumn('leave_date'));
	return flexGrid;
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

		var editorRoot = "<input type=\"text\" id=\"holidayDate_"+e.row+"\"class=\" wj-grid-editor wj-form-control\" style=\"text-align: center;\">";
		e.cell.innerHTML = editorRoot;
		var ttrr = $("#holidayDate_"+e.row).inputmask("yyyy-mm-dd");
		$("#holidayDate_"+e.row).val(grid.getCellData(e.row, e.col, true));

        // cellEditEnding that updates cell with user's input
        var editEndingEH = function (s, args) {
            grid.cellEditEnding.removeHandler(editEndingEH);
            if (!args.cancel) {
                args.cancel = true;
                    grid.setCellData(e.row, e.col, $("#holidayDate_"+e.row).val());
            }
        };

        grid.cellEditEnding.addHandler(editEndingEH);
    });
}

// 在房号输入框和团代码和团名输入框中输入条件敲击回车
function selectRoom(event) {
	if ($("#room_idP").val() == "" && $("#grp_id_nameP").val() == "") {
		$("#room_idP").attr("readonly", false);
		$("#grp_id_nameP").attr("readonly", false);
	}

	var e = event || window.event;
	var keyCode = e.keyCode || e.which;
	switch (keyCode) {
	case 13:
		searchToData(false);
		break;
	default:
		break;
	}
}

function modifyLeave(){
	// 点击批量预离验证有没有选中住客
	var rowsP = flexGridP.rows;
	var temp = 0;
	for ( var i = 0; i < rowsP.length; i++) {
		if (rowsP[i].isSelected) {
			temp++;
		}
	}
	if (temp == 0) {
		altWaringMsg("请选中需要预离的住客。");
		return;
	}
	$.ajax({
		url : $("#path").val() + "/rooms/hoteldate.do",
		type : "post",
		data : {
		},
		success : function(data) {
			var rowsP = flexGridP.rows;
			var itemsP = viewP.items;

			var dtArr = data.split("-");
			var leave_date = new Date(dtArr[0], parseInt(dtArr[1])-1, dtArr[2]) ;
			for ( var i = 0; i < rowsP.length; i++) {
				if (rowsP[i].isSelected) {
					itemsP[i].leave_date = leave_date;
					for ( var j = 0; j < itemsP.length; j++) {
						if (itemsP[i].room_id == itemsP[j].room_id
								&& itemsP[i].check_id != itemsP[j].check_id) {
							itemsP[j].leave_date = leave_date;
						}
					}
				}
			}
			viewP.commitEdit();
			flexGridP.refresh();

			goShowButtonP(true);

			flexGridP.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
			refreshColor();
		}
	});
}

function searchToData(checked) {
	$("#room_idP").blur();
	$("#grp_id_nameP").blur();
	// 取查询条件
	var room_idP = $("#room_idP").val();
	var grp_id_nameP = $("#grp_id_nameP").val();
	// 查询条件必须有一个
	if (room_idP == "" && grp_id_nameP == "") {
		if(!checked){
			altWaringMsg("请输入房号或团代码/团名。");
		}
		return;
	}
	// 查询条件只读
	if (room_idP == "") {
		$("#room_idP").attr("readonly", true);
		$("#room_idP").addClass("gry_9");
		goShowCheckBox(false);
	}
	if (grp_id_nameP == "") {
		$("#grp_id_nameP").attr("readonly", true);
		$("#grp_id_nameP").addClass("gry_9");
		goShowCheckBox(true);
	}
	// 是否显示同来房
	var with_roomP = $("#with_roomP").is(":checked");

	$.ajax({
		url : $("#path").val() + "/rooms/selectroomtolive.do",
		type : "post",
		dataType : "json",
		data : {
			room_idP : room_idP,
			grp_id_nameP : grp_id_nameP,
			with_roomP : with_roomP
		},
		success : function(result) {
			if(!result.issuccess){
				$("#room_idP").attr("readonly", false);
				$("#grp_id_nameP").attr("readonly", false);
				$("#room_idP").removeClass("gry_9");
				$("#grp_id_nameP").removeClass("gry_9");
				$("#room_idP").val("");
				$("#grp_id_nameP").val("");
				altWaringMsg(result.msg);
				return;
			}
			var data = result.datalist;
			if (data.length == 0) {
				$("#room_idP").attr("readonly", false);
				$("#grp_id_nameP").attr("readonly", false);
				$("#room_idP").removeClass("gry_9");
				$("#grp_id_nameP").removeClass("gry_9");
				$("#room_idP").val("");
				$("#grp_id_nameP").val("");
				altWaringMsg("没有来客信息。");
				
				return;
			}
			if(checked){
				if(with_roomP){
					$("#grp_id_nameP").val(data[0].grp_id);
				}else{
					$("#grp_id_nameP").val("");
				}
			}
			arrayP = [];
			if (typeof flexGridP !== "undefined") {
				flexGridP.dispose();
				flexGridP = undefined;
			}
			goShowButtonP(true);
			var if_bdate = false;
			for ( var i = 0; i < data.length; i++) {
				var dtArr = data[i].leave_date.split("-");
				var leave_date = new Date(dtArr[0], parseInt(dtArr[1])-1, dtArr[2]) ;
				if(data[i].If_bdate){
					if_bdate = true;
				}
				arrayP.push({
					"check_id" : data[i].check_id,
					"index" :(i + 1)+"",
					"gst_namec" : data[i].gst_namec,
					"gst_namee" : data[i].gst_namee,
					"reach_date" : data[i].reach_date,
					"leave_date" : leave_date,
					"leave_date_old" : data[i].leave_date,
					"room_id" : data[i].room_id,
					"selection_stat" : true,
					"room_price" : data[i].room_price,
					"chk_ext_show" : data[i].chk_ext_show,
					"chk_ext" : data[i].chk_ext,
					"If_bdate" : data[i].If_bdate
				});
			}
			if(if_bdate){
				$("#modify_bpaidP").prop("checked", true);
			}

			viewP = new wijmo.collections.CollectionView(arrayP);
			viewP.trackChanges = true;
			flexGridP = createFlexGridP("#divchangetoroom", viewP);
		}
	});
}


// 保存操作
function saveDatalive() {
	var liveArr = [];
	var datas = viewP.items;
	var obj;
	for ( var i = 0; i < datas.length; i++) {
		var leave_date = datas[i].leave_date;
		var curr_leave_date = leave_date.getFullYear()+"-"+(leave_date.getMonth() > 8 ? leave_date.getMonth()+1 : "0" + (leave_date.getMonth()+1))+"-"+(leave_date.getDate()>9?leave_date.getDate() : "0"+leave_date.getDate());
		if (curr_leave_date == datas[i].leave_date_old) {
			if (!reg.test(curr_leave_date)) {
				altWaringMsg("住客" + datas[i].gst_namec + "的离店日期格式不正确。");
				return;
			}
			continue;
		}
		obj = {};
		obj.check_id = datas[i].check_id;
		obj.room_id = datas[i].room_id;
		obj.leave_date = curr_leave_date;
		obj.leave_date_old = datas[i].leave_date_old;
		obj.chk_ext = datas[i].chk_ext;
		obj.gst_namec = datas[i].gst_namec;
		liveArr.push(obj);
	}

	if (liveArr.length == 0) {
		altWaringMsg("没有续住任何住客。");
		return;
	}
	var if_bdate_flag = $("#modify_bpaidP").is(":checked");
	$.ajax({
		url : $("#path").val() + "/rooms/saveRoomToleave.do",
		type : "post",
		dataType : 'json',
		data : {
			dataView : JSON.stringify(liveArr),
			if_bdate_flag : if_bdate_flag
		},
		success : function(data) {
			if (data.isSuccess) {
				altSuccessMsg("操作成功！");
//				giveUpP();
					$("a#cancelP").css({
						"cursor" : "pointer",
						"color" : "inherit"
					});

					$("#cancelP").attr("href", "javascript:giveUpP();");
					$("a#okP").css({
						"cursor" : "not-allowed",
						"color" : "grey"
					});

					$("#okP").attr("href", "javascript:;");
					searchToData(false);
			} else {
				altWaringMsg(data.msg);
			}
		}
	});
}

// 批量续住弹出框，确定按钮事件
function queding() {
	var new_leave_date = $("#newleavedate").val();
	if (!reg.test(new_leave_date)) {
		altWaringMsg("新离店日期格式不正确。");
		return;
	}
	var dtArr = new_leave_date.split("-");
	var leave_date = new Date(dtArr[0], parseInt(dtArr[1])-1, dtArr[2]) ;
	var rowsP = flexGridP.rows;
	var itemsP = viewP.items;
	for ( var i = 0; i < rowsP.length; i++) {
		if (rowsP[i].isSelected) {
			itemsP[i].leave_date = leave_date;
			for ( var j = 0; j < itemsP.length; j++) {
				if (itemsP[i].room_id == itemsP[j].room_id
						&& itemsP[i].check_id != itemsP[j].check_id) {
					itemsP[j].leave_date = leave_date;
				}
			}
		}
	}
	$("#newleavedate").val("");
	goShowButtonP(true);
	viewP.refresh();
	$(".alertDivBg2").fadeOut();
	$(".changeLeaveTimeDiv").fadeOut();
	flexGridP.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
	refreshColor();
}

function refreshColor(){
	var rowsP = flexGridP.rows;
	var ind = "";
	for ( var i = 0; i < rowsP.length; i++) {
		var leave_date = rowsP[i].dataItem.leave_date;
		var curr_leave_date = leave_date.getFullYear()+"-"+(leave_date.getMonth() > 8 ? leave_date.getMonth()+1 : "0" + (leave_date.getMonth()+1))+"-"+(leave_date.getDate()>9?leave_date.getDate() : "0"+leave_date.getDate());
		ind = rowsP[i].dataItem.index;
		if (curr_leave_date != rowsP[i].dataItem.leave_date_old) {
			rowsP[i].cssClass="eidt-row-blue";
			if(ind.indexOf("#") < 0){
				rowsP[i].dataItem.index = "#" + ind;
			}
		}else{
			rowsP[i].cssClass="eidt-row-back";
			if(ind.indexOf("#") >= 0){
				rowsP[i].dataItem.index = ind.replace("#", "");
			}
		}
	}
	flexGridP.refresh();
}

function goShowCheckBox(bool){
	if(bool){
		$("#with_roomP").removeAttr("disabled");
	}else{
		$("#with_roomP").attr("disabled","disabled");
	}
}

// -------------------------------------------------换房操作---------------------------------------------------


function goShowButton(bool) {
	if (bool) {
		$("a#okH,a#cancelH").css({
			"cursor" : "pointer",
			"color" : "inherit"
		});

		$("#okH").attr("href", "javascript:saveChangeRoom();");
		$("#cancelH").attr("href", "javascript:giveUp();");
	} else {
		$("a#okH,a#cancelH").css({
			"cursor" : "not-allowed",
			"color" : "grey"
		});

		$("#okH").attr("href", "javascript:;");
		$("#cancelH").attr("href", "javascript:;");
	}
}

function giveUp() {
	// window.location.href = $("#path").val() + "/rooms/wardtolive.do";
	isSearch = true;
	$(".chaRom1 input:text").val("");
	$("#with_id_A").val("");
	$("#with_id_B").val("");
	$("#room_id_A").val("");
	$("#room_id_B").val("");
	$("#gst_type_A").val("");
	$("#gst_type_B").val("");
	$("#curr_statA").text("");
	$("#curr_statB").text("");

	$("#room_idA").attr("readonly", false);
	$("#room_idB").attr("readonly", false);
	if (typeof flexGridA !== "undefined") {
		flexGridA.dispose();
		flexGridA = undefined;
	}
	arrayA = [];
	viewA = new wijmo.collections.CollectionView(arrayA);
	flexGridA = createFlexGrid("#roomsAdiv", viewA, "A");

	if (typeof flexGridB !== "undefined") {
		flexGridB.dispose();
		flexGridB = undefined;
	}
	arrayB = [];
	viewB = new wijmo.collections.CollectionView(arrayB);
	flexGridB = createFlexGrid("#roomsBdiv", viewB, "B");

	goShowButton(false);
}

// item：要换的对象，fromStr：来自于A/B，toStr：换到A/B，fromObj：来自于viewA/viewB，
// toObj：换到viewA/viewB，isSingle：单换还是多换（单换：true，多换：false）
function signMove(item, fromStr, toStr, fromObj, toObj, isSingle) {
	if (!item) {
		altWaringMsg("请选中需要换房的住客。");
		return;
	}
	if (item.chk_ext == "1" && isSingle) {
		if (fromObj.items.length > 1) {
			altWaringMsg("房主不能换房。");
			return;
		}
	}

	// 不能转入散客房间
	if ($("#gst_type_" + toStr).val() == "S") {
		altWaringMsg($("#room_id_" + toStr).val() + "房间为散客房间，不能换入。");
		return;
	} else if ($("#gst_type_" + toStr).val() == "K") {// 如果B房间是空房，就都可以转
		var length = toObj.items.length;

		toObj.addNew();
		var newItem = toObj.currentAddItem;
		newItem.check_id = item.check_id;
		newItem.room_id = $("#room_id_" + toStr).val();
		newItem.room_id_old = item.room_id_old;
		newItem.gst_type = item.gst_type;
		newItem.gst_namec = item.gst_namec;
		newItem.gst_namee = item.gst_namee;
		newItem.reach_date = item.reach_date;
		newItem.leave_date = item.leave_date;
		newItem.room_price = item.room_price;

		if (isSingle) {// 单换：如果转入房间没有人，当前转入人变为主人，否则变成同住
			newItem.chk_ext = length == 0 ? "1" : "0";
		} else {// 多换：保持不变
			newItem.chk_ext = length == 0 ? item.chk_ext : "0";
		}
		newItem.chk_ext_old = item.chk_ext_old;
		fromObj.remove(item);
		toObj.commitNew();
		fromObj.commitEdit();
	} else {
		if ($("#with_id_" + fromStr).val() != ""
				&& $("#with_id_" + toStr).val() != ""
				&& $("#with_id_" + fromStr).val() != $("#with_id_" + toStr)
						.val()) {
			altWaringMsg("不是同一个" + (item.gst_type == "T" ? "团" : "联房") + "，不能换房。");
			return;
		}
		toObj.addNew();
		var newItem = toObj.currentAddItem;
		newItem.check_id = item.check_id;
		newItem.room_id = $("#room_id_" + toStr).val();
		newItem.room_id_old = item.room_id_old;
		newItem.gst_type = item.gst_type;
		newItem.gst_namec = item.gst_namec;
		newItem.gst_namee = item.gst_namee;
		newItem.reach_date = item.reach_date;
		newItem.leave_date = item.leave_date;
		newItem.room_price = item.room_price;
		// 团房转团房，联房转联房，直接变成同住
		newItem.chk_ext = "0";
		newItem.chk_ext_old = item.chk_ext_old;
		fromObj.remove(item);
		toObj.commitNew();
		fromObj.commitEdit();
	}
	$("#room_idA").attr("readonly", true);
	$("#room_idB").attr("readonly", true);
	isSearch = false;
	goShowButton(true);
}

function moveAtoB() {
	if ($("#room_id_B").val() == undefined || $("#room_id_B").val() == "") {
		altWaringMsg("需要换入房间信息。");
		return;
	}
	var rowsA = flexGridA.rows;
	var itemsA = new Array();
	var items = viewA.items;
	for ( var i = 0; i < rowsA.length; i++) {
		if (rowsA[i].isSelected) {
			itemsA.push(items[i]);
		}
	}
	if (itemsA.length == 0) {
		altWaringMsg("请选中要换房的住客。");
		return;
	}

	var length = itemsA.length;
	for ( var i = 0; i < length; i++) {
		signMove(itemsA[i], "A", "B", viewA, viewB, length == 1 ? true : false);
	}

	// 禁止默认选中第一行
	flexGridA.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
	flexGridB.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
}
function moveBtoA() {
	if ($("#room_id_A").val() == undefined || $("#room_id_A").val() == "") {
		altWaringMsg("需要换入房间信息。");
		return;
	}
	var rowsB = flexGridB.rows;
	var itemsB = new Array();
	var items = viewB.items;
	for ( var i = 0; i < rowsB.length; i++) {
		if (rowsB[i].isSelected) {
			itemsB.push(items[i]);
		}
	}
	if (itemsB.length == 0) {
		altWaringMsg("请选中要换房的住客。");
		return;
	}

	var length = itemsB.length;
	for ( var i = 0; i < length; i++) {
		signMove(itemsB[i], "B", "A", viewB, viewA, length == 1 ? true : false);
	}

	// 禁止默认选中第一行
	flexGridA.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
	flexGridB.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
}

// 上面输入框中输入房号敲回车查询
function selectA(e) {
	var keyCode = e.keyCode || e.which;
	if(keyCode == 13 && isSearch){
		searchToDataA();
	}
}

// 上面给表格绑定Wijmo数据源
function searchToDataA() {
	$("#room_idA").blur();
	var rooms_idA = $("#room_idA").val();
	var rooms_idB = $("#room_idB").val();
	if (rooms_idA == "") {
		altWaringMsg("请输入房号。");
		return;
	}
	if (rooms_idA == rooms_idB) {
		altWaringMsg("输入的房号重复!");
	} else {
		$
				.ajax({
					url : $("#path").val() + "/rooms/selectwardrooms.do",
					type : "post",
					dataType : "json",
					data : {
						rooms_id : rooms_idA
					},
					success : function(data) {
						if (data.length == 0) {
							altWaringMsg("系统中不存在房间号为：" + rooms_idA + "的房间信息。");
							$("#room_idA").val("");
							return;
						}
						$("#curr_statA").text(data[0].curr_stat);
						$("#with_id_A").val(data[0].with_id);
						$("#room_id_A").val(data[0].room_id);
						$("#gst_type_A").val(data[0].gst_type);
						arrayA = [];
						if (typeof flexGridA !== "undefined") {
							flexGridA.dispose();
							flexGridA = undefined;
						}
						if (data.length == 1
								&& (data[0].check_id == undefined
										|| data[0].check_id == "null" || data[0].check_id == "")) {
							viewA = new wijmo.collections.CollectionView(arrayA);
							flexGridA = createFlexGrid("#roomsAdiv", viewA, "A");
							return;
						}

						for ( var i = 0; i < data.length; i++) {
							arrayA.push({
								"check_id" : data[i].check_id,
								"room_id" : data[i].room_id,
								"room_id_old" : data[i].room_id,
								"gst_type" : data[i].gst_type,
								"gst_namec" : data[i].gst_namec,
								"gst_namee" : data[i].gst_namee,
								"reach_date" : data[i].reach_date,
								"leave_date" : data[i].leave_date,
								"room_price" : data[i].room_price,
								"chk_ext" : data[i].chk_ext,
								"chk_ext_old" : data[i].chk_ext
							});
						}
						viewA = new wijmo.collections.CollectionView(arrayA);
						viewA.trackChanges = true;
						flexGridA = createFlexGrid("#roomsAdiv", viewA, "A");
					}
				});
	}
}

function selectB(e) {

	var keyCode = e.keyCode || e.which;
	if(keyCode == 13 && isSearch){
		searchToDataB();
	}
}
// 上面给表格绑定Wijmo数据源
function searchToDataB() {
	$("#room_idB").blur();
	var rooms_idA = $("#room_idA").val();
	var rooms_idB = $("#room_idB").val();
	if (rooms_idB == "") {
		altWaringMsg("请输入房号。");
		return;
	}
	if (rooms_idA == rooms_idB) {
		altWaringMsg("输入的房号重复!");
	} else {
		$
				.ajax({
					url : $("#path").val() + "/rooms/selectwardrooms.do",
					type : "post",
					dataType : "json",
					data : {
						rooms_id : rooms_idB
					},
					success : function(data) {
						if (data.length == 0) {
							altWaringMsg("系统中不存在房间号为：" + rooms_idB + "的房间信息。");
							$("#room_idB").val("");
							return;
						}
						$("#curr_statB").text(data[0].curr_stat);
						$("#with_id_B").val(data[0].with_id);
						$("#room_id_B").val(data[0].room_id);
						$("#gst_type_B").val(data[0].gst_type);
						arrayB = [];
						if (typeof flexGridB !== "undefined") {
							flexGridB.dispose();
							flexGridB = undefined;
						}
						if (data.length == 1
								&& (data[0].check_id == undefined
										|| data[0].check_id == "null" || data[0].check_id == "")) {
							viewB = new wijmo.collections.CollectionView(arrayB);
							flexGridB = createFlexGrid("#roomsBdiv", viewB, "B");
							return;
						}

						for ( var i = 0; i < data.length; i++) {
							arrayB.push({
								"check_id" : data[i].check_id,
								"room_id" : data[i].room_id,
								"room_id_old" : data[i].room_id,
								"gst_type" : data[i].gst_type,
								"gst_namec" : data[i].gst_namec,
								"gst_namee" : data[i].gst_namee,
								"reach_date" : data[i].reach_date,
								"leave_date" : data[i].leave_date,
								"room_price" : data[i].room_price,
								"chk_ext" : data[i].chk_ext,
								"chk_ext_old" : data[i].chk_ext
							});
						}
						viewB = new wijmo.collections.CollectionView(arrayB);
						viewB.trackChanges = true;
						flexGridB = createFlexGrid("#roomsBdiv", viewB, "B");

					}
				});
	}
}

function createFlexGrid(divid, view, objFrom) {
	var flexGrid = new wijmo.grid.FlexGrid(divid, {
		autoGenerateColumns : false,
		headersVisibility : wijmo.grid.HeadersVisibility.Column,
		selectionMode : wijmo.grid.SelectionMode.ListBox,
		columns : [ {
			header : "check_id",
			binding : "check_id",
			name : "check_id",
			isReadOnly : true,
			visible : false
		}, {
			header : "room_id",
			binding : "room_id",
			name : "room_id",
			isReadOnly : true,
			visible : false
		}, {
			header : "room_id_old",
			binding : "room_id_old",
			name : "room_id_old",
			isReadOnly : true,
			visible : false
		}, {
			header : "gst_type",
			binding : "gst_type",
			name : "gst_type",
			isReadOnly : true,
			visible : false
		}, {
			header : "中文名",
			binding : "gst_namec",
			align : "center",
			width : "*",
			isReadOnly : true
		}, {
			header : "英文名",
			binding : "gst_namee",
			align : "center",
			width : "*",
			isReadOnly : true
		}, {
			header : "抵店日期",
			binding : "reach_date",
			align : "center",
			width : "*",
			isReadOnly : true
		}, {
			header : "离店日期",
			binding : "leave_date",
			align : "center",
			width : "*",
			isReadOnly : true
		}, {
			header : "房租",
			binding : "room_price",
			align : "center",
			width : "*",
			isReadOnly : true
		}, {
			header : "身份",
			binding : "chk_ext",
			align : "center",
			width : "*",
			isReadOnly : false
		}, {
			header : "chk_ext_old",
			binding : "chk_ext_old",
			name : "chk_ext_old",
			isReadOnly : true,
			visible : false
		} ],
		itemsSource : view
	});

	// 禁止默认选中第一行
	flexGrid.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);

	// 设置摘要下拉代码
	var extArr = new Array();
	var obj = {};
	obj.codeId = "1";
	obj.codeName = "主人";
	extArr.push(obj);
	var obj1 = {};
	obj1.codeId = "0";
	obj1.codeName = "同住";
	extArr.push(obj1);
	var col_buildingId = flexGrid.columns.getColumn("chk_ext");
	col_buildingId.dataMap = new wijmo.grid.DataMap(extArr, "codeId",
			"codeName");
	flexGrid.cellEditEnded.addHandler(function(s, e) {
		var item = view.currentItem;
		var items = view.items;
		if (items.length == 2) {
			for ( var i = 0; i < 2; i++) {
				if (items[i].check_id != item.check_id) {
					items[i].chk_ext = item.chk_ext == "1" ? "0" : "1";
				}
			}
			view.commitEdit();
		}
		if (items.length > 2 && item.chk_ext == "1") {
			for ( var i = 0; i < items.length; i++) {
				if (items[i].check_id != item.check_id) {
					items[i].chk_ext = "0";
				}
			}
			view.commitEdit();
		}
		flexGrid.selection = new wijmo.grid.CellRange(-1, -1, -1, -1);
		goShowButton(true);
	});
	var host = flexGrid.hostElement;
    //双击移动
    host.addEventListener('dblclick', function (e) {
        if(objFrom == "A"){
        	moveAtoB();
        }else{
        	moveBtoA();
        }
    });
	return flexGrid;
}

function saveChangeRoom() {
	var resultArr = new Array();
	var temp = 0;

	var tempA = viewA.items;
	var roomIdA = $("#room_id_A").val();
	var guestNumA = tempA.length;
	var currStatA = $("#curr_statA").text();
	
	var resultRoomA = new Array();
	var objA = {};
	objA.room_id = roomIdA;
	objA.guest_num = guestNumA;
	objA.curr_stat = currStatA;
	resultRoomA.push(objA);

	for ( var i = 0; i < tempA.length; i++) {
		resultArr.push(tempA[i]);
		if (tempA[i].chk_ext == "1") {
			temp++;
		}
	}
	if (temp == 0 && tempA.length != 0) {
		altWaringMsg("请设置" + $("#room_id_A").val() + "房间的主人。");
		return;
	}
	if (temp > 1) {
		altWaringMsg($("#room_id_A").val() + "房间只能有一个主人。");
		return;
	}

	var tempB = viewB.items;
	var roomIdB = $("#room_id_B").val();
	var guestNumB = tempB.length + "";
	var currStatB = $("#curr_statB").text();

	var resultRoomB = new Array();
	var objB = {};
	objB.room_id = roomIdB;
	objB.guest_num = guestNumB + "";
	objB.curr_stat = currStatB;
	resultRoomB.push(objB);
	temp = 0;
	for ( var i = 0; i < tempB.length; i++) {
		resultArr.push(tempB[i]);
		if (tempB[i].chk_ext == "1") {
			temp++;
		}
	}
	if (temp == 0 && tempB.length != 0) {
		altWaringMsg("请设置" + $("#room_id_B").val() + "房间的主人。");
		return;
	}
	if (temp > 1) {
		altWaringMsg($("#room_id_B").val() + "房间只能有一个主人。");
		return;
	}

	var dataStr = JSON.stringify(resultArr);
	var roomAStr = JSON.stringify(resultRoomA);
	var roomBStr = JSON.stringify(resultRoomB);
	
	if ($("#gst_type_A").val() == "K" && guestNumA > 0) {
		if (currStatA.indexOf("VDP") >= 0 || currStatA.indexOf("VD") >= 0) {
			altInfMsg(roomIdA + "房间为不洁空房，是否确定换房?", function(){ajaxRequest(dataStr, roomAStr, roomBStr);}, function(){});
		}else if (currStatA.indexOf("VCP") >= 0) {
			altInfMsg(roomIdA + "房间为清洁未查空房，是否确定换房?", function(){ajaxRequest(dataStr, roomAStr, roomBStr);}, function(){});
		}else{
			ajaxRequest(dataStr, roomAStr, roomBStr);
		}
	}else if ($("#gst_type_B").val() == "K" && guestNumB > 0) {
		if (currStatB.indexOf("VDP") >= 0 || currStatB.indexOf("VD") >= 0) {
			altInfMsg(roomIdB + "房间为不洁空房，是否确定换房?", function(){ajaxRequest(dataStr, roomAStr, roomBStr);}, function(){});
		}else if (currStatB.indexOf("VCP") >= 0) {
			altInfMsg(roomIdB + "房间为清洁未查空房，是否确定换房?", function(){ajaxRequest(dataStr, roomAStr, roomBStr);}, function(){});
		}else{
			ajaxRequest(dataStr, roomAStr, roomBStr);
		}
	}else{
		ajaxRequest(dataStr, roomAStr, roomBStr);
	}
}

function ajaxRequest(dataStr, roomAStr, roomBStr){
	$.ajax({
		url : $("#path").val() + "/rooms/changeRoom.do",
		type : "post",
		dataType : "json",
		async : false,
		data : {
			changeData : dataStr,
			roomAData : roomAStr,
			roomBData : roomBStr
		},
		success : function(data) {
			if (data.isSuccess) {
				altSuccessMsg("操作成功！");
//				giveUp();
				$("a#cancelH").css({
					"cursor" : "pointer",
					"color" : "inherit"
				});

				$("#cancelH").attr("href", "javascript:giveUp();");
				$("a#okH").css({
					"cursor" : "not-allowed",
					"color" : "grey"
				});

				$("#okH").attr("href", "javascript:;");
			} else {
				altWaringMsg(data.msg);
			}
		}
	});
}

function provideRoomCard(position){
	var checkIdAndroomId = "";
	var rows_gst;
	var items_gst;
	if(position == 1){
		var roomId = $("#room_idA").val();
		if(roomId == ""){
			return;
		}
		rows_gst = flexGridA.rows;
		items_gst = viewA.items;
		for ( var i = 0; i < rows_gst.length; i++) {
			if (rows_gst[i].isSelected) {
				if(checkIdAndroomId == ""){
					checkIdAndroomId += (items_gst[i].check_id + "," + roomId);
				}else{
					checkIdAndroomId += ("|" + items_gst[i].check_id + "," + roomId);
				}
			}
		}
		if(checkIdAndroomId == ""){
			return;
		}
	}else if(position == 2){
		var roomId = $("#room_idB").val();
		if(roomId == ""){
			return;
		}
		rows_gst = flexGridB.rows;
		items_gst = viewB.items;
		for ( var i = 0; i < rows_gst.length; i++) {
			if (rows_gst[i].isSelected) {
				if(checkIdAndroomId == ""){
					checkIdAndroomId += (items_gst[i].check_id + "," + roomId);
				}else{
					checkIdAndroomId += ("|" + items_gst[i].check_id + "," + roomId);
				}
			}
		}
		if(checkIdAndroomId == ""){
			return;
		}
	}else{
		rows_gst = flexGridP.rows;
		items_gst = viewP.items;
		for ( var i = 0; i < rows_gst.length; i++) {
			if (rows_gst[i].isSelected) {
				if(checkIdAndroomId == ""){
					checkIdAndroomId += (items_gst[i].check_id + "," + items_gst[i].room_id);
				}else{
					checkIdAndroomId += ("|" + items_gst[i].check_id + "," + items_gst[i].room_id);
				}
			}
		}
		if(checkIdAndroomId == ""){
			return;
		}
	}
	idcard.regUserInfo($("#userid").val(), $("#username").val());
	var a = idcard.runADELCard(checkIdAndroomId);
}