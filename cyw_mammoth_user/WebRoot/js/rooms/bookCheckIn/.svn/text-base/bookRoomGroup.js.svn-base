var grpGuestGrid,isEdit=false,checkInTag=true;	
$(function(){
	
	$("#groupForm input,select").change(function(){
		buttonChange();
	});
	$("#groupForm input").keyup(function(){
		buttonChange();
	});
	/*遮罩层DIV高度*/
	$(".alertDivBg").css("height",$(document).height());     
    $(".alertDivBg").css("width",$(document).width());  
	/*遮罩层DIV高度*/
	$("input[name=inType]").click(function(){
		var v = $(this).val();
		initGstOri(v);
	});
	/*小键盘点击切换背景*/
	$(".cabDiv a").click(function(){
		$(".cabDiv a").removeClass("pointCab");
		$(this).addClass("pointCab");
	});
	/*小键盘点击切换背景END*/
	/*table点击换行颜色*/
	
	$(".tabChangBg tr").click(function(){
		$(".tabChangBg tr td").removeClass("thisTrTd");
		$(this).find("td").addClass("thisTrTd");
	});
	/*个人入住*/
	/*关闭弹出1*/
//	$(".hideDivOne").click(function(){
//		$(".alertDivBg").fadeOut();
//		$(".TeamMecheckDetailsDiv").fadeOut();
//	});
	/*关闭弹出1*/
	/*弹出层内TAB页切换*/
	$(".userTab1").click(function(){
		if(checkInTag){
			var checkId = $("#bookRegistration #checkInputForm #checkId").val() ;
			var withId = $("#bookRegistration #checkInputForm #withId").val() ;
			var roomIds = [] ;
			var bookIdArr = [] ;
			var _bookId = -1 ;
			$("#selectRoom_ul li").each(function(index, element) 
			{
				var bookId = $(element).attr('data-bookId') ;
				var roomId = $(element).prop("id") ;
				if(bookId){
					if(_bookId != -1 && bookId != _bookId){
						bookIdArr.push({
							"bookId":_bookId,
							"roomIds":roomIds
						});
						roomIds = [] ;
						bookIdArr = [] ;
					}
					roomIds.push({
						"roomId": roomId,
						// ifSaveRoom == 0 ? 留房  : 非留房
						"ifSaveRoom": $(element).attr('data-ifSaveRoom') ? $(element).attr('data-ifSaveRoom') : 1 
					});
					_bookId = bookId ;
				}
			});
			if($("#selectRoom_ul li").length > 0 ){
				bookIdArr.push({
					"bookId":_bookId,
					"roomIds":roomIds
				});
			}
			if(bookIdArr.length == 0){
				altInfMsg("请选定房间");
				return;
			}
			showGroupDetails(checkId,withId,JSON.stringify(bookIdArr),$("#bookId").val());
		}else{
			var checkId = $("input[name=checkId]").val();
			showGroupDetails(checkId);
		}
		$(".userDetails").show();
		$(".userCatalog").hide();
		$(".userTab1").addClass("point");
		$(".userTab2").removeClass("point");
	});
	$(".userTab2").click(function(){
		$(".userDetails").hide();
		$(".userCatalog").show();
		$(".userTab1").removeClass("point");
		$(".userTab2").addClass("point");
	});
	/* /弹出层内TAB页切换*/
	
	
	/*房价列表二级弹出*/
	$("#pricesList").click(function(){
		$(".pricesList").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	$("#otherInformation").click(function(){
		if($("#otherInformation").hasClass('disabledAButton')) return;
		$(".otherDiv").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	$("#changeRoom").click(function(){
		$(".changeRoomDiv").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	$(".closeAlert").click(function(){
		$(".pricesList").fadeOut();
		$(".alertDivBg2").fadeOut();
		$(".splitInfoDiv").fadeOut();
		$(".otherDiv").fadeOut();
		$(".changeRoomDiv").fadeOut();
		$(".depositDiv").fadeOut();
		$(".receivablesDiv").fadeOut();
		$(".LeavingHotelDiv").fadeOut();
	});
	/*分账设置*/
	$("#split").click(function(){
		//分账消费点
		$(".alertDivBg2").fadeIn();
		$(".splitInfoDiv").fadeIn();
	});
	/*房价列表二级弹出END*/
	
	/*押金*/
	$("#deposit").click(function(){
		$(".alertDivBg2").fadeIn();
		$(".depositDiv").fadeIn();
	});
	/*押金*/
	/*收款*/
	$("#receivables").click(function(){
		$(".alertDivBg2").fadeIn();
		$(".receivablesDiv").fadeIn();
	});
	/*收款*/
	/*离店*/
	$("#LeavingHotel").click(function(){
		$(".alertDivBg2").fadeIn();
		$(".LeavingHotelDiv").fadeIn();
	});
	/*离店*/

	/**
	 * grid数据加载
	 */
    grpGuestGrid = new wijmo.grid.FlexGrid('#grpGuestGrid', {
     	  autoGenerateColumns: false,
    	  columns: [
           { header: '序号', binding: 'sortNum',width:50 }, 
           { header: '房号', binding: 'room_id',width:50}, 
           { header: '房类', binding: 'code_namec',width:'*' }, 
           { header: '房租', binding: 'code_namec',width:'*' }, 
           { header: '中文名', binding: 'gst_namec' ,width:'*'},
           { header: '英文名', binding: 'gst_namee' ,width:'*'}, 
           { header: '状态', binding: 'chkStat',width:'*'},
           { header: '抵店日期', binding: 'reach_date',width:'*',format:'yyyy-MM-dd',width:'*'},
           { header: '离店日期', binding: 'leave_date',width:'*',format:'yyyy-MM-dd',width:'*' },
           { header: '账号', binding: 'billa_id',width:'*' },
           { header: '登记人', binding: 'chk_operid',width:'*' },
           { header: '登记号', binding: 'check_id',width:'*' }
       	],
        //itemsSource: view,
        headersVisibility:wijmo.grid.HeadersVisibility.Column,
        selectionMode:'Row',
        isReadOnly:true,
        deferResizing:true
    });
    
    $("#house_pay").prop('checked',true);
    $("#free_svc").prop('checked',true);
    $("#if_fgst").prop('checked',true);
});

function showGroupDetails(grpCheckId,withId,roomIds,bookRoomId){
	initSettle();
	initCountry();
	initFolk();
	//省市
	initCodes('008','native_');
	//客人分类
	initCodes('004','gstKind');
	loadConsume();
	initGstOri(1);
	initButton();
	var url ='';
	var data = {};
	if(checkInTag){
		url = '/group/grpCheckIn.do';
	    data.grpCheckId = grpCheckId;
	    data.withId = withId;
	    data.roomIds = roomIds;
	    data.bookRoomId = bookRoomId;
	}else{
		url = '/group/getGrpdocDetail.do';
	    data.grpCheckId = grpCheckId;
	}
	$.ajax({
		url:url,
		data:data,
		dataType:'json',
		type:'get',
		success:function(data){
			var detail = data.grpDocDetail;
			setForm('groupForm',detail);
			var view = new wijmo.collections.CollectionView(data.guestList);
			grpGuestGrid.itemsSource = view;
			$("#roomSumNum").val(data.roomNum);
			grpGuestGrid.itemsSource = view;
		},
		error:function(){
			altEerrMsg('查询异常');
		}
	});
}
/**
 * 加载团队详情
 */	
function loadForm(){
	
}

/**
 * 加载支付方式
 */
function initSettle(){
	$.ajax({
		url : '/guest/loadSettleAndMoneyKind.do',
		type : 'POST',
		cache : false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			if(data.sList){
				for(var i=0;i<data.sList.length;i++){
					$("select[name=payId]").append('<option value="'+$.trim(data.sList[i].codeId)+'" >'+data.sList[i].codeNamec+'</option>');
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}

function initGstOri(type){
	$("#gstOriId option").remove();
	$.ajax({
		url : '/guest/getGstOri.do',
		type : 'get',
		data:{checkType:type},
		cache : false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			if(data && data.length>0){
				for(var i=0;i<data.length;i++){
					$("#gstOriId").append('<option value="'+$.trim(data[i].code_id)+'" >'+data[i].code_namec+'</option>');
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}

function initGstOriAndIntype(codeId){
	$("#gstOriId option").remove();
	$.ajax({
		url : '/guest/getGstOriByOricode.do',
		type : 'get',
		data:{codeId:codeId},
		cache : false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			if(data.hgstOris){
				for(var i=0;i<data.hgstOris.length;i++){
					$("#gstOriId").append('<option value="'+$.trim(data.hgstOris[i].codeId)+'" >'+data.hgstOris[i].codeNamec+'</option>');
				}
				$("input[name=inType][value="+$.trim(data.inType)+"]").prop('checked',true);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}

/**
 * 合约单位
 * @param avj
 */
function hyUnitDataBind(avj){
	var tadVo={
		symbol:1	
	};
	$.ajax({
		url:"/marketing/searchTadoclist.do",
		type:"post",
		dataType:'json',
		data:tadVo,
		success:function(data){
			if(data!=undefined && data!=null){
				//alert(JSON.stringify(data));
				$('#theCompany').autocomplete(data,   
				         {  
							minChars: 0,
							max: 12,
							autoFill: true,
							mustMatch: false,
							matchContains: false,
							scrollHeight: 220,
							width: 400,
							formatItem: function(row, i, total) {
								return row.namec;
							},
							formatMatch: function(row) {
								return row.namec;
							},
							formatResult: function(row){
								return row.namecc;
							}
				    }).result(function(event, row, formatted){
				    	  $("#company_id").val(row.id);
				    	  $("#ta_type").val(row.taType);
				    	  buttonChange ();
				    });  
				if(!avj==undefined){
					
				}
			}
		}
	});
}

/**
 * 加载国家
 */
function initCountry(){
	$("#country option").remove();
    $.ajax({
    	url:'/hcountry/list.do',
    	type:'post',
    	dataType:'json',
    	success:function(data){
    		var list = JSON.parse(data.listJson);
    		for(var i=0;i<list.length;i++){
    			$("#country").append("<option value='"+$.trim(list[i].codeId)+"'>"+list[i].codeNamec+"</option>");
    		}
    	}
    });
}

/**
 * 加载民族
 */
function initFolk(){
    $.ajax({
    	url:'/hfolk/list.do',
    	type:'post',
    	dataType:'json',
    	success:function(data){
    		var list = JSON.parse(data.listJson);
    		for(var i=0;i<list.length;i++){
    			$("#folks").append("<option value='"+$.trim(list[i].codeId)+"'>"+list[i].codeNamec+"</option>");
    		}
    	}
    });
}

/**
 * 代码表加载
 * @param code
 * @param name
 */
function initCodes(code,name){
    $.ajax({
    	url:'/guest/getCodes.do',
    	data:{code:code},
    	dataType:'json',
    	success:function(data){
    		for(var i=0;i<data.length;i++){
    			$("select[name="+name+"]").append("<option value='"+$.trim(data[i].codeId)+"'>"+data[i].codeNamec+"</option>");
    		}
    	}
    });
}

/**
 * 详情分账设置加载
 */
function loadConsume(){
	$("#consumeA_ID li").remove();
	$("#consumeB_ID li").remove();
	$.ajax({
		url:'/guest/getAllConsumes.do',
		dataType:'json',
		type:'get',
		async:false,
		success:function(data){
			if(data){
				for(var i = 0;i<data.length;i++){
					$("#consumeA_ID").append("<li id='"+data[i].codeId+"' tag='false' onclick='consumeClick(this)'>"+data[i].codeNamec+"</li>");
				}
			}
		}
	});
}

/**
 * A帐点击事件
 * @param obj
 */
function consumeClick(obj){
	var tag = $(obj).attr('tag');
	$(obj).css("background",tag=='false'?'#96bc5b':'white');
	$(obj).attr('tag',tag=='false'?'true':'false');
}
/**
 * 详情分账设置向右事件
 */
function consumeToRight(){
	$("#consumeA_ID li").each(function(index,item){
		if($(item).attr('tag')=='true'){
			consumeClick(item);
			$("#consumeB_ID").append(item);
		}
	});
}
/**
 * 详情分账设置向左事件
 */
function consumeToLeft(){
	$("#consumeB_ID li").each(function(index,item){
		if($(item).attr('tag')=='true'){
			consumeClick(item);
			$("#consumeA_ID").append(item);
		}
	});
}

/**
 *描述：详情分账设置项目移动事件
 *@param: dir(right:右移, left:左移) 
 *@param: moveStr(moveAll:全部移动, movePart:选中部分移动)
 */
function consumeMove(dir,move){
	var dirStr = dir, moveStr = move, fromObj, toObj;
	if(dirStr == "right"){	//右移
		fromObj = $("#consumeA_ID");
		toObj = $("#consumeB_ID");
	}else{	//左移
		fromObj = $("#consumeB_ID");
		toObj = $("#consumeA_ID");	
	}

	fromObj.find("li").each(function(index,item){
		if(moveStr == "moveAll"){	// 全部移动
			$(item).css({"background":'white',"color":"#000"});
			$(item).attr('tag','false');
			toObj.append(item);
		}else{
			if($(item).attr('tag')=='true'){	//选中部分移动
				consumeClick(item);
				toObj.append(item);
			}
		}
	});
}

/**
 * 分账退出
 */
function consumeQuit(){
	$(".alertDivBg2").fadeOut();
	$(".splitInfoDiv").fadeOut();
}

/**
 * 账目分账放弃
 */
function consumeCancle(){
	loadConsume();
	$("#split_startDate").val('');
	$("#split_endDate").val('');
	$("#if_bate").prop('checked',true);
}

/**
 * 详情确定按钮
 */
function groupFormSubmit(){
	if($("#confirm").hasClass('disabledAButton')) return;
	var formData =getFormJson(document.getElementById("groupForm"));
	$("#guestChecks input").each(function(){
		var id = $(this).attr('id');
		var checked = $(this).prop('checked')
		formData[id] = checked;
	});
	if($("#isGstOri").prop('checked')){ 
		if($("#gstOriId").val()==''){
			altWaringMsg('请选择团队来源');
			return;
		}
	}
	if($("#isSplit").prop('checked')){
		var items = $("#consumeB_ID li");
		var ids='';
		if(items.length>0){
			if($("#split_startDate").val()==""){
				altWaringMsg('请选择起始日期');
				return;
			}
			if($("#split_endDate").val()==""){
				altWaringMsg('请选择终止日期');
				return;
			}
			$(items).each(function(index,item){
				ids+=item.id+",";
			});
			formData.cons=ids.substring(0, ids.length-1);
			formData.ifBdate= document.getElementById("if_bate").checked?'1':'0';
			formData.endDate= $("#split_endDate").val();
			formData.beginDate= $("#split_startDate").val();
		}else{
			formData.isSplit = false;
		}
	}
	formData.checkId = $("input[name=checkId]").val();
	formData.cityLedger=$("#city_ledger").prop('checked');
	formData.housePay=$("#house_pay").prop('checked');
	formData.freeSvc=$("#free_svc").prop('checked');
	formData.hideprice=$("#hideprice").prop('checked');
	formData.ifFgst=$("#if_fgst").prop('checked');
	$.ajax({
		url:'/group/updateGrpdocAndGuest.do',
		type:'post',
		data:formData,
		dataType:'json',
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				$.ajax({
					url:'/group/getGrpdocDetail.do',
					data:{grpCheckId:formData.checkId},
					dataType:'json',
					type:'get',
					success:function(data){
						var detail = data.grpDocDetail;
						setForm('groupForm',detail);
						var view = new wijmo.collections.CollectionView(data.guestList);
						grpGuestGrid.itemsSource = view;
						isEdit = false;
						initButton();
					},
					error:function(){
						altEerrMsg('查询异常');
					}
					
				});
			}else{
				altEerrMsg(data.msg);
			}
		}
	});
}

/**
 * 详情放弃
 */
function cancleEdit(){
	if($("#giveup").hasClass('disabledAButton')) return;
	$("#groupForm").resetForm();
	isEdit = false;
	initButton();
	$("#house_pay").prop('checked',true);
	$("#free_svc").prop('checked',true);
	$("#if_fgst").prop('checked',true);
}
/**
 * 详情退出
 */
function groupDetailQuit(){
	if($("#groupDetailQuit").hasClass('disabledAButton')) return;
	$(".alertDivBg").fadeOut();
	$(".TeamMecheckDetailsDiv").fadeOut();
	checkInTag = true;
}
function quitWindow(){
	cancleEdit();
	$(".alertDivBg").fadeOut();
	$(".TeamMecheckDetailsDiv").fadeOut();
	searchForm();
}
/**
 * 按钮切换
 */
function buttonChange(){
	$("#confirm").removeClass("disabledAButton");
	$("#giveup").removeClass("disabledAButton");
	$("#scanCard").addClass('disabledAButton');
	$("#sendRoomCard").addClass('disabledAButton');
	$("#otherInformation").addClass('disabledAButton');
	$("#groupDetailQuit").addClass('disabledAButton');
	isEdit=true;
}
/**
 * 团队办理入住
 */
function saveGroupCheckIn(){
	$(".userTab1").click();
	checkInTag = false;
	$("input[name=inType][value=1]").prop('checked',true);
	initGstOri(1);
	$(".alertDivBg").fadeIn();
	$(".TeamMecheckDetailsDiv").fadeIn();
	closeBookRegistration() ;
}
/**
 * 确定和放弃按钮初始化
 */
function initButton(){
	$("#confirm").addClass("disabledAButton");
	$("#giveup").addClass("disabledAButton");
	$("#scanCard").removeClass('disabledAButton');
	$("#sendRoomCard").removeClass('disabledAButton');
	$("#otherInformation").removeClass('disabledAButton');
	$("#groupDetailQuit").removeClass('disabledAButton');
}