var isSearch = true;
$(function() {
		/* 遮罩层DIV高度 */
		$(".alertDivBg").css("height", $(document).height());
		$(".alertDivBg").css("width", $(document).width());
		/* 遮罩层DIV高度 */
		/* 二级菜单点击选中事件 */
		$(".secondMenu li a").click(function() {
			$(".secondMenu li a").removeClass('thisSecMenu');
			$(this).addClass('thisSecMenu');
		});
		/* 二级菜单点击选中事件 结束 */

		/* table点击换行颜色 */

// 		$(".tabChangBg tr").click(function() {
// 			$(".tabChangBg tr td").removeClass("thisTrTd");
// 			$(this).find("td").addClass("thisTrTd");
// 		});
		/* /table点击换行颜色 */

	});

	$(".closeDiv").click(function() {
// 		$(".alertMain").fadeOut();
		$(".buildingAdd").fadeOut();
		window.location.href = $("#path").val()+"/rooms.do";
	});
	$(function() {
		setSecondMenuStat(3);
	});
	function exitdiv(){
//	$(".buildingAdd").fadeOut();
		window.location.href = $("#path").val()+"/rooms.do";
	}
	function goShowButtonOk(bool) {
		if (bool) {
			$("a#ok").css({
				"cursor" : "pointer",
				"color" : "inherit"
			});

			$("#ok").attr("href", "javascript:toChange();");
		} else {
			$("a#ok").css({
				"cursor" : "not-allowed",
				"color" : "grey"
			});

			$("#ok").attr("href", "javascript:;");
		}
	}
	function goShowButtonCancel(bool) {
		if (bool) {
			$("a#cancel").css({
				"cursor" : "pointer",
				"color" : "inherit"
			});

			$("#cancel").attr("href", "javascript:reload();");
		} else {
			$("a#cancel").css({
				"cursor" : "not-allowed",
				"color" : "grey"
			});

			$("#cancel").attr("href", "javascript:;");
		}
	}
	//---------------------------------------查询开始--------------------------------------------
	/**
	 *左边查询按键按下
	 */
	function searchL() {
		document.onkeyup = function(event) {
			var e = event || window.event;
			var keyCode = e.keyCode || e.which;
			switch (keyCode) {
			case 13:
				if(!isSearch){
					return;
				}
				var grp_id_nameL = $("#grp_id_nameL").val();
				var grp_id_nameR = $("#grp_id_nameR").val();
				var roomidL = $("#roomidL").val();
				var roomidR = $("#roomidR").val();
				if (roomidL == "") {
					if (grp_id_nameL != "" && grp_id_nameL != grp_id_nameR) {
						searchToDataL();
					} else if (grp_id_nameL == "") {
						return;
					} else {
						altWaringMsg("你输入的条件查询出的结果和右侧重复！");
						$("#formL").clearForm();
						document.getElementById("roomidL").readOnly = false;
						$("#roomidL").addClass("gry_9");
						document.getElementById("grp_id_nameL").readOnly = false;
						$("#grp_id_nameL").addClass("gry_9");
					}
				}

				if (grp_id_nameL == "") {
					if (roomidL != "" && roomidL != roomidR) {
						searchToDataL();
					} else if (roomidL == "") {
						return;
					} else {
						altWaringMsg("你输入的条件查询出的结果和右侧重复！");
						$("#formL").clearForm();
						document.getElementById("roomidL").readOnly = false;
						document.getElementById("grp_id_nameL").readOnly = false;
					}
				}
				break;
			default:
				break;
			}
		};
	}

	/**
	 *左边查询
	 */
	function searchToDataL() {
		$("#div_gridL").empty();
		$("#grp_id_nameL").blur();
		$("#roomidL").blur();
		var grp_id_nameL = $("#grp_id_nameL").val();// 团代码/团名
		var roomidL = $("#roomidL").val();// 相关房号
		if (roomidL != "" || grp_id_nameL != "") {
			$.ajax({
				url : $("#path").val() + "/rooms/grp_tochange1.do",
				type : "post",
				dataType : "json",
				data : {
					grp_id_name1 : grp_id_nameL,
					roomid1 : roomidL
				},
				success : function(tempdata) {
					if(!tempdata.issuccess){
						altWaringMsg(tempdata.msg);
						document.getElementById("roomidL").readOnly = false;
						$("#roomidL").removeClass("gry_9");
						$("#roomidL").val("");
						document.getElementById("grp_id_nameL").readOnly = false;
						$("#grp_id_nameL").removeClass("gry_9");
						$("#grp_id_nameL").val("");
						return;
					}
					var data = tempdata.dlist;
					var temprp = tempdata.grpIdList;
					var bill_num = tempdata.bill_num;
					if(data.length != 0){
						if(data[0].with_id != "0" && data[0].with_id == $("#with_idR").val()){
							altWaringMsg("你输入的条件查询出的结果和右侧重复！");
							return;
						}
						$("#billL").val(bill_num);
						if(temprp.length != 0){
							$("#grp_id_nameL").val(temprp[0].grp_id);
						}
						document.getElementById("roomidL").readOnly = true;
						$("#grp_id_nameL").addClass("gry_9");
						document.getElementById("grp_id_nameL").readOnly = true;
						$("#grp_id_nameL").addClass("gry_9");
						goShowButtonCancel(true);
					}else{
						altWaringMsg("没有查询到住客信息。");
						document.getElementById("roomidL").readOnly = false;
						$("#roomidL").removeClass("gry_9");
						$("#roomidL").val("");
						document.getElementById("grp_id_nameL").readOnly = false;
						$("#grp_id_nameL").removeClass("gry_9");
						$("#grp_id_nameL").val("");
					}
					
					for ( var i = 0; i < data.length; i++) {
						//缓存左边团代码、同来人id、B帐号
						if (i == 0) {
							$("#grp_chkidL").val(data[i].grp_chkid);
							$("#with_idL").val(data[i].with_id);
							$("#billb_idL").val(data[i].billb_id);
							$("#gst_typeL").val(data[i].gst_type);
						}
						var t = data[i].payman_flag == "1" ? "是" : "否";
						var html = "<tr id=\""+data[i].check_id+"\" checkid=\""+data[i].check_id+"\" billbid=\""+data[i].billb_id+"\" paymanflag=\""+data[i].payman_flag+"\" roomid=\""+data[i].room_id+"\" gsttype=\""+data[i].gst_type+"\" chkext=\""+data[i].chk_ext+"\" onclick=\"selectSigle('"+data[i].check_id+"', 'left', '"+data[i].billb_id+"', '"+data[i].payman_flag+"', '"+data[i].room_id+"');\" ondblclick=\"dbMove('"+data[i].check_id+"', 'left', '"+data[i].billb_id+"', '"+data[i].payman_flag+"', '"+data[i].room_id+"');\"><td width=\"50px\" id=\""+data[i].check_id+"0\">"+data[i].room_id+"</td><td id=\""+data[i].check_id+"1\" title=\""+data[i].gst_namec+"\">"+data[i].gst_namec+"</td><td width=\"80px\" id=\""+data[i].check_id+"2\" title=\""+data[i].gst_namee+"\">"+data[i].gst_namee+"</td><td width=\"60px\" id=\""+data[i].check_id+"3\">"+t+"</td></tr>";
						$("#div_gridL").append(html);

					}
				}
			});
		}
	}
	
	/**
	 *右边查询按键按下
	 */
	function searchR() {
		document.onkeyup = function(event) {
			var e = event || window.event;
			var keyCode = e.keyCode || e.which;
			switch (keyCode) {
			case 13:
				if(!isSearch){
					return;
				}
				var grp_id_nameL = $("#grp_id_nameL").val();
				var grp_id_nameR = $("#grp_id_nameR").val();
				var roomidL = $("#roomidL").val();
				var roomidR = $("#roomidR").val();
				if (roomidR == "") {
					if (grp_id_nameR != "" && grp_id_nameR != grp_id_nameL) {
						searchToDataR();
					} else if (grp_id_nameR == "") {
						return;
					} else {
						altWaringMsg("你输入的条件查询出的结果和左侧重复！");
						$("#formR").clearForm();
						document.getElementById("roomidR").readOnly = false;
						$("#roomidR").removeClass("gry_9");
						document.getElementById("grp_id_nameR").readOnly = false;
						$("#grp_id_nameR").removeClass("gry_9");
					}
				}

				if (grp_id_nameR == "") {
					if (roomidR != "" && roomidR != roomidL) {
						searchToDataR();
					} else if (roomidR == "") {
						return;
					} else {
						altWaringMsg("你输入的条件查询出的结果和左侧重复！");
						$("#formR").clearForm();
						document.getElementById("roomidR").readOnly = false;
						$("#roomidR").removeClass("gry_9");
						document.getElementById("grp_id_nameR").readOnly = false;
						$("#grp_id_nameR").removeClass("gry_9");
					}
				}
				break;
			default:
				break;
			}
		};
	}

	/**
	 * 右边查询
	 */
	function searchToDataR() {
		$("#div_gridR").empty();
		$("#grp_id_nameR").blur();
		$("#roomidR").blur();
		var grp_id_nameR = $("#grp_id_nameR").val();// 团代码/团名
		var roomidR = $("#roomidR").val();// 相关房号
		if ($("#roomidR").val() != "" || $("#grp_id_nameR").val() != "") {
			$.ajax({
				url : $("#path").val() + "/rooms/grp_tochange1.do",
				type : "post",
				dataType : "json",
				data : {
					grp_id_name1 : grp_id_nameR,
					roomid1 : roomidR
				},
				success : function(tempdata) {
					if(!tempdata.issuccess){
						altWaringMsg(tempdata.msg);
						document.getElementById("roomidR").readOnly = false;
						$("#roomidR").removeClass("gry_9");
						$("#roomidR").val("");
						document.getElementById("grp_id_nameR").readOnly = false;
						$("#grp_id_nameR").removeClass("gry_9");
						$("#grp_id_nameR").val("");
						return;
					}
					var data = tempdata.dlist;
					var temprp = tempdata.grpIdList;
					var bill_num = tempdata.bill_num;
					if(data.length != 0){
						if(data[0].with_id != 0 && data[0].with_id == $("#with_idL").val()){
							altWaringMsg("你输入的条件查询出的结果和左侧重复！");
							return;
						}
						$("#billR").val(bill_num);
						if(temprp.length != 0){
							$("#grp_id_nameR").val(temprp[0].grp_id);
						}
						document.getElementById("roomidR").readOnly = true;
						$("#roomidR").addClass("gry_9");
						document.getElementById("grp_id_nameR").readOnly = true;
						$("#grp_id_nameR").addClass("gry_9");
						goShowButtonCancel(true);
					}else{
						altWaringMsg("没有查询到住客信息。");
						document.getElementById("roomidR").readOnly = false;
						$("#roomidR").removeClass("gry_9");
						$("#roomidR").val("");
						document.getElementById("grp_id_nameR").readOnly = false;
						$("#grp_id_nameR").removeClass("gry_9");
						$("#grp_id_nameR").val("");
					}
					for ( var i = 0; i < data.length; i++) {
						//缓存左边团代码、同来人id、B帐号
						if (i == 0) {
							$("#grp_chkidR").val(data[i].grp_chkid);
							$("#with_idR").val(data[i].with_id);
							$("#billb_idR").val(data[i].billb_id);
							$("#gst_typeR").val(data[i].gst_type);
						}
						var t = data[i].payman_flag == "1" ? "是" : "否";
						var html = "<tr id=\""+data[i].check_id+"\" checkid=\""+data[i].check_id+"\" billbid=\""+data[i].billb_id+"\" paymanflag=\""+data[i].payman_flag+"\" roomid=\""+data[i].room_id+"\" gsttype=\""+data[i].gst_type+"\" chkext=\""+data[i].chk_ext+"\" onclick=\"selectSigle('"+data[i].check_id+"', 'right', '"+data[i].billb_id+"', '"+data[i].payman_flag+"', '"+data[i].room_id+"');\" ondblclick=\"dbMove('"+data[i].check_id+"', 'right', '"+data[i].billb_id+"', '"+data[i].payman_flag+"', '"+data[i].room_id+"');\"><td width=\"50px\" id=\""+data[i].check_id+"0\">"+data[i].room_id+"</td><td id=\""+data[i].check_id+"1\" title=\""+data[i].gst_namec+"\">"+data[i].gst_namec+"</td><td width=\"80px\"  id=\""+data[i].check_id+"2\" title=\""+data[i].gst_namee+"\">"+data[i].gst_namee+"</td><td width=\"60px\" id=\""+data[i].check_id+"3\">"+t+"</td></tr>";
						$("#div_gridR").append(html);

					}
				}
			});
		}
	}
	
	//------------------------------------------行选中-----------------------------------------------
	//选中数据临时存放
	var tempArray = new Array();
	/**
	 * 选中行
	 * @param check_id 住客id
	 * @param flag left/right表示从那边开始移动
	 * @param billb_id 要移动的B帐号信息
	 * @param payman_flag 要移动的人是否是付款人
	 */
	function selectSigle(check_id, flag, billb_id, payman_flag, room_id, gst_type){
		if(tempArray.length != 0){
			for ( var i = 0; i < tempArray.length; i++) {
				if(tempArray[i].flag != flag){//若选了左边没有做其他操作然后选右边，则左边全部重置，右侧相反
					tempArray = new Array();
					var tempObj = {};
					tempObj.check_id = check_id;
					tempObj.flag = flag;
					tempObj.billb_id = billb_id;
					tempObj.payman_flag = payman_flag;
					tempObj.room_id = room_id;
					tempArray.push(tempObj);
					changeSelectTrStyle();
					return;
				}
				if(tempArray[i].check_id == check_id){//若已经选中，则再次点击删除
					tempArray.splice(i, 1);
					changeSelectTrStyle();
					return;
				}
			}
			var tempObj = {};
			tempObj.check_id = check_id;
			tempObj.flag = flag;
			tempObj.billb_id = billb_id;
			tempObj.payman_flag = payman_flag;
			tempObj.room_id = room_id;
			tempArray.push(tempObj);
			changeSelectTrStyle();
		}else{
			var tempObj = {};
			tempObj.check_id = check_id;
			tempObj.flag = flag;
			tempObj.billb_id = billb_id;
			tempObj.payman_flag = payman_flag;
			tempObj.room_id = room_id;
			tempArray.push(tempObj);
			changeSelectTrStyle();
		}
	}
	
	/**
	 * 双击移动
	 * @param check_id 住客id
	 * @param flag left/right表示从那边开始移动
	 * @param billb_id 要移动的B帐号信息
	 * @param payman_flag 要移动的人是否是付款人
	 */
	function dbMove(check_id, flag, billb_id, payman_flag, room_id, gst_type){
		//验证是否是付款人
		var gst_type_flag = flag == "left" ? $("#gst_typeL").val() : $("#gst_typeR").val();
		if(gst_type_flag != "S" && payman_flag == "1"){
			altWaringMsg("选中住客是付费人，不能被转。");
			return;
		}
		

		if($("#gst_typeL").val() == $("#gst_typeR").val() && $("#gst_typeL").val() == "S"){
			altWaringMsg("散客不能转为散客。");
			return;
		}
		//验证是否有B帐加授权
		var roomArr = new Array();
		var temp = true;
		if(flag == "left"){
			if($("#billL").val() != "0" && $("#billL").val() != ""){
				altWaringMsg("选中住客有B帐或授权存在，不能进行转换。");
				return;
			}
			$("#div_gridL tr").each(function() {
				if (room_id == $(this).attr("roomid")) {
					if($(this).attr("paymanflag") == "1"){
						temp = false;
					}
					var tempObj = {};
					tempObj.check_id = $(this).attr("checkid");
					tempObj.flag = flag;
					tempObj.billb_id = $(this).attr("billbid");
					if($("#gst_typeL").val() == "S"){
						tempObj.payman_flag = "0";
					}else{
						if($("#gst_typeR").val() == "S"){
							tempObj.payman_flag = $(this).attr("chkext") == "1" ? "1" : "0";
						}else{
							tempObj.payman_flag = $(this).attr("paymanflag");
						}
					}
					tempObj.room_id = $(this).attr("roomid");
					roomArr.push(tempObj);
				}
			});
		}else{
			if($("#billR").val() != "0" && $("#billR").val() != ""){
				altWaringMsg("选中住客有B帐或授权存在，不能进行转换。");
				return;
			}
			$("#div_gridR tr").each(function() {
				if (room_id == $(this).attr("roomid")) {
					if($(this).attr("paymanflag") == "1"){
						temp = false;
					}
					var tempObj = {};
					tempObj.check_id = $(this).attr("checkid");
					tempObj.flag = flag;
					tempObj.billb_id = $(this).attr("billbid");
					if($("#gst_typeR").val() == "S"){
						tempObj.payman_flag = "0";
					}else{
						if($("#gst_typeL").val() == "S"){
							tempObj.payman_flag = $(this).attr("chkext") == "1" ? "1" : "0";
						}else{
							tempObj.payman_flag = $(this).attr("paymanflag");
						}
					}
					tempObj.room_id = $(this).attr("roomid");
					roomArr.push(tempObj);
				}
			});
		}
		if(!temp && gst_type_flag != "S"){
			altWaringMsg("选中住客所在房间有付费人，不能转换。");
			return;
		}
		//先重置缓存
		tempArray = new Array();
		for(var i = 0; i <roomArr.length; i++){
			move(flag, roomArr[i]);
		}
		changeSelectTrStyle();
		goShowButtonOk(true);
	}
	
	/**
	 *单击行修改样式
	 */
	function changeSelectTrStyle(){
		//先全部去掉样式
		$("#div_gridL tr td").each(function() {
			$(this).removeClass("thisTrTd");
		});
		$("#div_gridR tr td").each(function() {
			$(this).removeClass("thisTrTd");
		});
		//还选中的添加样式
		for(var i = 0; i < tempArray.length; i++){
			$("#"+tempArray[i].check_id+"0").addClass("thisTrTd");
			$("#"+tempArray[i].check_id+"1").addClass("thisTrTd");
			$("#"+tempArray[i].check_id+"2").addClass("thisTrTd");
			$("#"+tempArray[i].check_id+"3").addClass("thisTrTd");
		}
	}
	
	/**
	 *单行迁移
	 *flag=left:左移右，flag=rigth:右移左
	 */
	function singleMove(flag){
		//未选中不能转，直接反悔？
		if(tempArray.length == 0){
			altWaringMsg("请先选中要转换的住客。");
			return;
		}
		if($("#gst_typeL").val() == $("#gst_typeR").val() && $("#gst_typeL").val() == "S"){
			altWaringMsg("散客不能转为散客。");
			return;
		}
		
		var roomIdArr = new Array();
		for(var i = 0; i < tempArray.length; i++){
			var t = true;
			for(var j = 0; j < roomIdArr.length; j++){
				if(roomIdArr[j] == tempArray[i].room_id){
					t = false;
					break;
				}
			}
			if(t){
				roomIdArr.push(tempArray[i].room_id);
			}
		}
		
		//验证是否有B帐加授权
		var roomArr = new Array();
		var temp = true;
		if(flag == "left"){
			if($("#billL").val() != "0" && $("#billL").val() != ""){
				altWaringMsg("选中住客有B帐或授权存在，不能进行转换。");
				return;
			}
			for(var x = 0; x < roomIdArr.length; x++){
				$("#div_gridL tr").each(function() {
					if (roomIdArr[x] == $(this).attr("roomid")) {
						if($(this).attr("paymanflag") == "1"){
							temp = false;
						}
						var tempObj = {};
						tempObj.check_id = $(this).attr("checkid");
						tempObj.flag = flag;
						tempObj.billb_id = $(this).attr("billbid");
						if($("#gst_typeL").val() == "S"){
							tempObj.payman_flag = "0";
						}else{
							if($("#gst_typeR").val() == "S"){
								tempObj.payman_flag = $(this).attr("chkext") == "1" ? "1" : "0";
							}else{
								tempObj.payman_flag = $(this).attr("paymanflag");
							}
						}
						tempObj.room_id = $(this).attr("roomid");
						roomArr.push(tempObj);
						
					}
				});
			}
		}else{
			if($("#billR").val() != "0" && $("#billR").val() != ""){
				altWaringMsg("选中住客有B帐或授权存在，不能进行转换。");
				return;
			}
			for(var x = 0; x < roomIdArr.length; x++){
				$("#div_gridR tr").each(function() {
					if (roomIdArr[x] == $(this).attr("roomid")) {
						if($(this).attr("paymanflag") == "1"){
							temp = false;
						}
						var tempObj = {};
						tempObj.check_id = $(this).attr("checkid");
						tempObj.flag = flag;
						tempObj.billb_id = $(this).attr("billbid");
						if($("#gst_typeR").val() == "S"){
							tempObj.payman_flag = "0";
						}else{
							if($("#gst_typeL").val() == "S"){
								tempObj.payman_flag = $(this).attr("chkext") == "1" ? "1" : "0";
							}else{
								tempObj.payman_flag = $(this).attr("paymanflag");
							}
						}
						tempObj.room_id = $(this).attr("roomid");
						roomArr.push(tempObj);
					}
				});
			}
		}

		var gst_type_flag = flag == "left" ? $("#gst_typeL").val() : $("#gst_typeR").val();
		if(!temp && gst_type_flag != "S"){
			altWaringMsg("选中住客所在房间有付费人，不能转换。");
			return;
		}
		//选中行迁移
		for ( var i = 0; i < roomArr.length; i++) {
			move(flag, roomArr[i]);
		}
		
		tempArray = new Array();
		changeSelectTrStyle();//迁移后修改选中状态
		goShowButtonOk(true);
	}
	
	/**
	 * 全部迁移
	 * @param flag left/right表示从那边开始移动
	 */
	function allMove(flag){

		if($("#gst_typeL").val() == $("#gst_typeR").val() && $("#gst_typeL").val() == "S"){
			altWaringMsg("散客不能转为散客。");
			return;
		}
		//验证是否有B帐加授权
		var tempArr = new Array();
		if(flag == "left"){
			if($("#billL").val() != "0" && $("#billL").val() != ""){
				altWaringMsg("选中住客有B帐或授权存在，不能进行转换。");
				return;
			}
			if($("#gst_typeL").val() != "S"){
				$("#div_gridL tr").each(function() {
					if($(this).attr("paymanflag") == "1"){
						tempArr.push($(this).attr("roomid"));
					}
				});
			}
		}else{
			if($("#billR").val() != "0" && $("#billR").val() != ""){
				altWaringMsg("选中住客有B帐或授权存在，不能进行转换。");
				return;
			}
			if($("#gst_typeR").val() != "S"){
				$("#div_gridR tr").each(function() {
					if($(this).attr("paymanflag") == "1"){
						tempArr.push($(this).attr("roomid"));
					}
				});
			}
		}
		
		//迁移
		if(flag == "left"){
			$("#div_gridL tr").each(function() {
				if($("#gst_typeL").val() != "S"){
					for(var i = 0; i < tempArr.length; i++){
						if(tempArr[i] == $(this).attr("roomid")){
							return true;
						}
					}
				}
				var tempObj = {};
				tempObj.check_id = $(this).attr("checkid");
				tempObj.flag = flag;
				tempObj.billb_id = $(this).attr("billbid");
				if($("#gst_typeL").val() == "S"){
					tempObj.payman_flag = "0";
				}else{
					if($("#gst_typeR").val() == "S"){
						tempObj.payman_flag = $(this).attr("chkext") == "1" ? "1" : "0";
					}else{
						tempObj.payman_flag = $(this).attr("paymanflag");
					}
				}
				tempObj.room_id = $(this).attr("roomid");
				move(flag, tempObj);
			});
		}else{
			$("#div_gridR tr").each(function() {
				for(var i = 0; i < tempArr.length; i++){
					if(tempArr[i] == $(this).attr("roomid")){
						return true;
					}
				}
				var tempObj = {};
				tempObj.check_id = $(this).attr("checkid");
				tempObj.flag = flag;
				tempObj.billb_id = $(this).attr("billbid");
				if($("#gst_typeR").val() == "S"){
					tempObj.payman_flag = "0";
				}else{
					if($("#gst_typeL").val() == "S"){
						tempObj.payman_flag = $(this).attr("chkext") == "1" ? "1" : "0";
					}else{
						tempObj.payman_flag = $(this).attr("paymanflag");
					}
				}
				tempObj.room_id = $(this).attr("roomid");
				move(flag, tempObj);
			});
		}
		tempArray = new Array();
		changeSelectTrStyle();//迁移后修改选中状态

		goShowButtonOk(true);
	}

	var json = new Array();//修改过的对象数组（每个对象包含：check_id:guestdoc对象的check_id,flag:-1表示改变过1表示未改变过，flagsign:表示原本属于left还是right）
	var jsonL = new Array();//左边的团对信息数组（每个对象包含：grp_chkid团id，with_id同来人id，billb_idB帐号）
	var jsonR = new Array();//右边的团对信息数组（每个对象包含：grp_chkid团id，with_id同来人id，billb_idB帐号）
	/**
	 * 单个迁移dom
	 * @param flag left/right表示从那边开始移动
	 * @param obj 要移动的对象
	 */
	function move(flag, obj){
		$("#" + obj.check_id).removeAttr("onclick");
		$("#" + obj.check_id).removeAttr("ondblclick");
		var temp = false;//假设转入没有付费人
		if(flag == "left"){
			$("#div_gridR tr").each(function() {
				if($(this).attr("paymanflag") == "1"){
					temp = true;//判断当前里面有没有付费人
					return false;
				}
			});
			if($("#with_idR").val() == "0" || $("#with_idL").val() == "0"){
				$("#" + obj.check_id + "3").text(obj.payman_flag == "1" ? "是" : "否");
				$("#" + obj.check_id).attr("paymanflag", obj.payman_flag);
			}else{
				if(temp){//如果有付费人，其他转入的都设置成不是付费人
					$("#" + obj.check_id + "3").text("否");
					$("#" + obj.check_id).attr("paymanflag", "0");
					obj.payman_flag = "0";
				}else{//如果没有付费人，转入的人按payman_flag设置
					$("#" + obj.check_id + "3").text(obj.payman_flag == "1" ? "是" : "否");
					$("#" + obj.check_id).attr("paymanflag", obj.payman_flag);
				}
			}
			$("#" + obj.check_id).attr("onclick", "selectSigle(\""+obj.check_id+"\",\"right\",\""+obj.billb_id+"\",\""+obj.payman_flag+"\", \""+obj.room_id+"\")");
			$("#" + obj.check_id).attr("ondblclick", "dbMove(\""+obj.check_id+"\",\"right\",\""+obj.billb_id+"\",\""+obj.payman_flag+"\", \""+obj.room_id+"\")");
			$("#div_gridL tr").each(function() {
				if (obj.check_id == $(this).attr("checkid")) {
					$("#div_gridR").append($("#" + obj.check_id));
				}
			});
			
		}else{
			$("#div_gridL tr").each(function() {
				if($(this).attr("paymanflag") == "1"){
					temp = true;
					return false;
				}
			});
			if($("#with_idL").val() == "0" || $("#with_idL").val() == "0"){
				$("#" + obj.check_id + "3").text(obj.payman_flag == "1" ? "是" : "否");
				$("#" + obj.check_id).attr("paymanflag", obj.payman_flag);
			}else{
				if(temp){//如果有付费人，其他转入的都设置成不是付费人
					$("#" + obj.check_id + "3").text("否");
					$("#" + obj.check_id).attr("paymanflag", "0");
					obj.payman_flag = "0";
				}else{//如果没有付费人，转入的人按payman_flag设置
					$("#" + obj.check_id + "3").text(obj.payman_flag == "1" ? "是" : "否");
					$("#" + obj.check_id).attr("paymanflag", obj.payman_flag);
				}
			}
			$("#" + obj.check_id).attr("onclick", "selectSigle(\""+obj.check_id+"\",\"left\",\""+obj.billb_id+"\",\""+obj.payman_flag+"\", \""+obj.room_id+"\")");
			$("#" + obj.check_id).attr("ondblclick", "dbMove(\""+obj.check_id+"\",\"left\",\""+obj.billb_id+"\",\""+obj.payman_flag+"\", \""+obj.room_id+"\")");
			$("#div_gridR tr").each(function() {
				if (obj.check_id == $(this).attr("checkid")) {
					$("#div_gridL").append($("#" + obj.check_id));
				}
			});
		}
		if (json.length == 0) {
			jsonObj = {};
			jsonObj.check_id = obj.check_id;
			jsonObj.flag = -1;
			jsonObj.flagSign = flag;
			jsonObj.payman_flag = obj.payman_flag;
			jsonObj.room_id = obj.room_id;
			json.push(jsonObj);
		} else {
			for ( var i = 0; i < json.length; i++) {
				if (obj.check_id == json[i].check_id) {
					json.splice(i, 1);
					return;
				}
			}
			jsonObj = {};
			jsonObj.check_id = obj.check_id;
			jsonObj.flag = -1;
			jsonObj.flagSign = flag;
			jsonObj.payman_flag = obj.payman_flag;
			jsonObj.room_id = obj.room_id;
			json.push(jsonObj);
		}
		isSearch = false;
	}
	
	/**
	 *确定按钮保存改变
	 */
	function toChange(){
		if(json.length == 0){
			return;
		}
		jsonObj1 = {};
		jsonObj1.grp_chkid1 = $('#grp_chkidL').val();
		jsonObj1.with_id1 = $('#with_idL').val();
		jsonObj1.billb_id1 = $('#billb_idL').val();
		jsonObj1.gst_type1 = $("#gst_typeL").val();
	
		jsonObj2 = {};
		jsonObj2.grp_chkid2 = $('#grp_chkidR').val();
		jsonObj2.with_id2 = $('#with_idR').val();
		jsonObj2.billb_id2 = $('#billb_idR').val();
		jsonObj2.gst_type2 = $("#gst_typeR").val();
		jsonL.push(jsonObj1);
		jsonR.push(jsonObj2);
		var str = JSON.stringify(json);
		var str2 = JSON.stringify(jsonL);
		var str3 = JSON.stringify(jsonR);
		$.ajax({
			url : $("#path").val() + "/rooms/grp_changetodata.do",
			type : "post",
			dataType : 'json',
			data : {
				changeData : str,
				changeCondLft : str2,
				changeCondRight : str3
			},
			success : function(data) {
				if (data.isSuccess) {
					altSuccessMsg("操作成功！");
//					$("#div_gridR tr").each(function(){
//						$(this).remove();
//					});
//					$("#div_gridL tr").each(function(){
//						$(this).remove();
//					});
//					$(".input[type='text']").val("");
//					$("#grp_chkidL").val("0");
//					$("#with_idL").val("0");
//					$("#billb_idL").val("0");
//					$("#gst_typeL").val("S");
//					$("#grp_chkidR").val("0");
//					$("#with_idR").val("0");
//					$("#billb_idR").val("0");
//					$("#gst_typeR").val("S");
//					$("#grp_id_nameL").attr("readonly", false);
//					$("#roomidL").attr("readonly", false);
//					$("#grp_id_nameR").attr("readonly", false);
//					$("#roomidR").attr("readonly", false);
//					goShowButtonCancel(false);
					goShowButtonOk(false);
//					isSearch = true;
					
					json = new Array();
					jsonL = new Array();
					jsonR = new Array();
					tempArray = new Array();
				} else {
					altWaringMsg(data.msg);
				}
			}
		});
	}
	
	/**
	 *点击放弃页面
	 */
	function reload() {
		window.location.href = $("#path").val() + "/rooms/groupToChange.do";
	}
	
	/**
	 * 根据id设置文本框只读
	 * @param domSelf 当前操作的dom对象
	 * @param domOther 需要改变的dom对象
	 */
	function lockInput(domSelf, domOther){
		if($("#"+domSelf).val() == ""){
			$("#"+domOther).attr("readonly",false);
			$("#"+domOther).removeClass("gry_9");
		}else{
			$("#"+domOther).attr("readonly",true);
			$("#"+domOther).addClass("gry_9");
		}
	}