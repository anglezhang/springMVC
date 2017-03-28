var view,grid,grpGuestGrid,isEdit=false,guestDetaiAccFlexGrid ;	
$(function(){
	
	$("#groupForm input,select").change(function(){
		buttonChange();
	});
	$("textarea").keyup(function(){
		buttonChange();
	});
	$("#groupForm input").keyup(function(){
		buttonChange();
	});
	/*遮罩层DIV高度*/
	$(".alertDivBg").css("height",$(document).height());     
    $(".alertDivBg").css("width",$(document).width());  
	/*遮罩层DIV高度*/
	
	//房间号及帐号输入限制
	$("#searchGroupRoomId").inputmask('Regex',{regex:"[0-9]*$"});
	$("#searchaAccountId").inputmask('Regex',{regex:"[0-9]*$"});
	$("#searchRoomId").inputmask('Regex',{regex:"[*]?[0-9]*$"});
	/*小键盘点击切换背景*/
	$(".cabDiv a").click(function(){
		$(".cabDiv a").removeClass("pointCab");
		$(this).addClass("pointCab");
		doSearch($(this).html());
	});
	/*小键盘点击切换背景END*/
	/*table点击换行颜色*/
	
	$(".tabChangBg tr").click(function(){
		$(".tabChangBg tr td").removeClass("thisTrTd");
		$(this).find("td").addClass("thisTrTd");
	});
	/* /table点击换行颜色*/
	/*个人入住*/
//	$("#checkDetails").click(function(){
//		showGroupDetails();
//		$(".alertDivBg").fadeIn();
//		$(".TeamMecheckDetailsDiv").fadeIn();
//	});
	/*个人入住*/
	/*关闭弹出1*/
	$(".hideDivOne").click(function(){
		$(".alertDivBg").fadeOut();
		$(".TeamMecheckDetailsDiv").fadeOut();
	});
	/*关闭弹出1*/
	/*弹出层内TAB页切换*/
	$(".userTab1").click(function(){
		$(".userDetails").show();
		$(".userCatalog").hide();
		$(".userTab1").addClass("point");
		$(".userTab2").removeClass("point");
	});
	$(".userTab2").click(function(){
		var item = grpGuestGrid.itemsSource.currentItem;
		if(!item) return;
		getAccountDetail(item);
		$(".userDetails").hide();
		$(".userCatalog").show();
		$(".userTab1").removeClass("point");
		$(".userTab2").addClass("point");
	});
	/* /弹出层内TAB页切换*/
	
	
	/*房价列表二级弹出*/
//	$("#pricesList").click(function(){
//		$(".pricesList").fadeIn();
//		$(".alertDivBg2").fadeIn();
//	});
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
	$("input[name=inType]").click(function(){
		var v = $(this).val();
		initGstOri(v);
	});
	
	//加载grid数据
    grid = new wijmo.grid.FlexGrid('#groupGrid', {
     	  autoGenerateColumns: false,
    	  columns: [
           { header: '序号', binding: 'sortNum',align:'center',width:40,minWidth:40 }, 
           { header: 'checkid', binding: 'check_id',visible:false,width:80,minWidth:100 }, 
           { header: '团代码', binding: 'grp_id',width:80,minWidth:83},
           { header: '团名', binding: 'grp_name',align:'center',width:80,minWidth:80},
           { header: '房数', binding: 'roomNum',width:80,minWidth:80}, 
           { header: '中文名', binding: 'lead_namec' ,width:80,minWidth:80},
           { header: '英文名', binding: 'lead_namee' ,width:80,minWidth:80},
           { header: '抵店日期', binding: 'reach_date',align:'center',width:80,minWidth:100,format:'yyyy/MM/dd'},
           { header: '离店日期', binding: 'leave_date',align:'center',width:80,minWidth:100,format:'yyyy/MM/dd'},
           { header: '合约单位', binding: 'namec',width:80,minWidth:90},
           { header: '状态', binding: 'chkStat',align:'center',width:40,minWidth:80},
           { header: '账号', binding: 'bill_id',width:80,minWidth:80},
           { header: '登记人', binding: 'oper_name',width:80,minWidth:80},
           { header: '登记号', binding: 'check_id',width:80,minWidth:80}
       	],
        headersVisibility:wijmo.grid.HeadersVisibility.Column,
        allowDragging:wijmo.grid.AllowDragging.None,
        selectionMode:'Row',
        isReadOnly:true,
        deferResizing:true
    });
    var host = grid.hostElement;
    //双击进入 办理入住页面
    host.addEventListener('dblclick', function (e) {
        var ht = grid.hitTest(e);
        if (ht.cellType == wijmo.grid.CellType.Cell) {
        	showGroupDetails();
        }
        e.preventDefault();
    });
	/**
	 * grid数据加载
	 */
    grpGuestGrid = new wijmo.grid.FlexGrid('#grpGuestGrid', {
     	  autoGenerateColumns: false,
    	  columns: [
    	   { header: 'b帐号', binding: 'billb_id',visible:false }, 
    	   { header: 'checkid', binding: 'check_id',visible:false  }, 
    	   { header: 'withid', binding: 'with_id',visible:false  }, 
           { header: '序号', binding: 'sortNum',width:50 }, 
           { header: '房号', binding: 'room_id',width:50}, 
           { header: '房类', binding: 'code_namec',width:'*' }, 
           { header: '房租', binding: 'room_price',width:'*' }, 
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
	guestDetaiAccFlexGrid =  new wijmo.grid.FlexGrid('#groupaccountGrid', {
	    	autoGenerateColumns: false,
	    	columns: [
	         { header: 'setlId', binding: 'setlId',visible:false}, 
  	         { header: 'operId', binding: 'operId',visible:false}, 
  	         { header: 'okFlag', binding: 'okFlag',visible:false}, 
             { header: '序号', binding: 'sortNum',width:50 }, 
             { header: 'ID号', binding: 'ID',width:100 },
             { header: '单号', binding: 'acco_id' ,width:100},
             { header: '借项', binding: 'cname' ,width:100}, 
             { header: '贷项', binding: 'sname',width:100},
             { header: '金额', binding: 'balance', width:100 },
             { header: '服务费', binding: 'svc_charge',width:100 },
             { header: '房号', binding: 'room_id',width:70 },
             { header: '时间', binding: 'oper_time',width:100 },
             { header: '外币', binding: 'foreignm',width:100 },
             { header: '币种', binding: 'code_namec',width:100 },
             { header: '状态', binding: 'status',width:100 },
             { header: '操作员', binding: 'oper_name',width:100 },
             { header: '摘要', binding: 'ext_name',width:100 },
             { header: '备注', binding: 'notes',width:100 },
             { header: '结账号', binding: 'payNum',width:100 }
 	 	],
 	 	headersVisibility:wijmo.grid.HeadersVisibility.Column,
 	 	selectionMode:'ListBox',
 	 	isReadOnly:true
	 	});
    $("#house_pay").prop('checked',true);
    $("#free_svc").prop('checked',true);
    $("#if_fgst").prop('checked',true);
});

/**
 * 账目页详情
 * @param item
 */
function getAccountDetail(item){
	$("#roomList2").empty();
	var bill_type = $('input[name=radioAccount]:checked').val();
	var show_type = $('input[name=radioType]:checked').val();
	var okFlag = $('input[name=disturb]:checked').val();
	var billId;
	if(bill_type=='1'){
		billId = item.billa_id;
	}else{
		billId = item.billb_id;
	}
	$.ajax({
		url : '/guest/guestAccountInfo.do',
		type : 'POST',
		cache : false,
		data:{checkId:item.check_id,roomId:item.room_id,withId:item.with_id,billId:billId,billType:bill_type,showType:show_type,okFlag:okFlag},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			loadAccountInfo(data.detail);
			loadAccountGuests(data.guests,item);
			loadAccountView(data.bills);
			if(data.roomBills){
				for(var i=0;i<data.roomBills.length;i++){
					if(data.roomBills[i].payman_flag=='0'){
						$("#roomList2").append("<li><a href='javascript:;' onclick='loadAccountInfoByRoom("+$.trim(data.roomBills[i].room_id)+")' guestinfo-roomid='"+$.trim(data.roomBills[i].room_id)+"'>"+data.roomBills[i].room_id+"("+data.roomBills[i].sum+") </a>");
					}
					if(data.roomBills[i].payman_flag=='1'){
						$("#roomList2").append("<li><a href='javascript:;' onclick='loadAccountInfoByRoom("+$.trim(data.roomBills[i].room_id)+")' guestinfo-roomid='"+$.trim(data.roomBills[i].room_id)+"'>"+"$("+data.roomBills[i].sum+") </a>");
					}
					$("#roomList2 li").each(function(){
		            	if(item.room_id == $(this).attr('guestinfo-roomid')){
		            		$(this).addClass('roomListLi');
		            	}
		            });
		            $("#roomList2 li").click(function(){
		            	 $(this).addClass("roomListLi");
		    		     $(this).siblings().removeClass("roomListLi");
		    	    });
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}

/**
 * 按账目中房间号加载信息
 */
function loadAccountInfoByRoom(roomId){
	if(roomId!=$("#account_roomid").val()){
		var withId = $("#account_namec option:selected").attr('data-withid');
		$.ajax({
			url:'list.do',
			data:{roomId:roomId,withId:withId},
			async:false,
			dataType:'json',
			success:function(data){
				loadAccountGuests(data);
				//住客详情加载
				setAccountInfo(roomId);
			}
		});
		 $("#roomList2 li").click(function(){
			    $("#roomList2 li").eq($(this).index()).addClass("roomListLi").siblings().removeClass("roomListLi");
		 });
	}
}

function setAccountInfo(roomId){
	var check_id=$("#account_namec").val();
	var bill_type = $("input[name=radioAccount]:checked").val();
	var option = $("#account_namec").find("option:selected");
	var bill_id = (bill_type=='1' ? (option.attr('data-billaid')) : (option.attr('data-billbid')));
	var show_type = $('input[name=radioType]:checked').val();
	var okFlag = $('input[name=disturb]:checked').val();
	$.ajax({
		url : '/guest/guestAccountInfo.do',
		type : 'POST',
		cache : false,
		data:{checkId:check_id,roomId:roomId,billId:bill_id,billType:bill_type,showType:show_type,okFlag:okFlag},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			//表单加载
			loadAccountInfo(data.detail);
			loadAccountView(data.bills);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}
/**
 * 表单信息
 * @param data
 */
function loadAccountInfo(data){
	if(data){
		$("#account_roomid").val(data.roomId);
		$("#account_billaid").val(data.billaId);
		$("#account_billbid").val(data.billbId);
		$("#account_price").val(data.price);
		$("#account_roomprice").val(data.roomPrice);
		$("#account_inDate").val(data.reachDate);
		$("#account_leavedate").val(data.leaveDate);
		$("#account_notice").val(data.notice);
		$("#account_paytype").val(data.codeNamec);
		$("#account_borrowA").val(data.borrow);
		$("#account_lentA").val(data.lent);
		$("#account_remainA").val(changeTwoDecimal_f(parseFloat(data.borrow)-parseFloat(data.lent)));
		$("#account_totalA").val(changeTwoDecimal_f(parseFloat(data.Alimit)+ parseFloat(data.auth_balance)));
		$("#account_borrowB").val(data.Bborrow);
		$("#account_lentB").val(data.Blent);
		$("#account_remainB").val(changeTwoDecimal_f(parseFloat(data.Bborrow)-parseFloat(data.Blent)));
		$("#account_totalB").val(changeTwoDecimal_f(parseFloat(data.Blimit)+ parseFloat(data.Bauth_balance)));
		$("#hideRoomPrice").attr('checked',data.hideprice);
	}
}

/**
 * 加载中文名及英文名的
 * @param data
 * @param item
 */
function loadAccountGuests(data,item){
	$("#account_namec option").remove();
	$("#account_namee option").remove();
	if(data){
		for(var i=0;i<data.length;i++){
			$("#account_namec").append('<option data-withid="'+data[i].with_id+'" data-billaid="'+data[i].billa_id+'" data-billbid="'+data[i].billb_id+'" data-chkstat="'+data[i].chk_stat+'" value="'+data[i].check_id+'">'+data[i].gst_namec+'</option>');
			$("#account_namee").append('<option data-withid="'+data[i].with_id+'" data-billaid="'+data[i].billa_id+'" data-billbid="'+data[i].billb_id+'" data-chkstat="'+data[i].chk_stat+'" value="'+data[i].check_id+'">'+data[i].gst_namee+'</option>');
		}
	}
	if(item){
		$("#account_namec").val(item.check_id);
		$("#account_namee").val(item.check_id);
	}
	var chkstat = $("#account_namec option:selected").attr('data-chkstat');
	if(chkstat == 'O'){
		$("#leavingHotel").addClass('disabledAButton');
		$("#checkOutOperation").addClass('disabledAButton');
		$("#addPreAward").addClass('disabledAButton');
	}else{
		$("#leavingHotel").removeClass('disabledAButton');
		$("#checkOutOperation").removeClass('disabledAButton');
		$("#addPreAward").removeClass('disabledAButton');
	}
}

/**
 * 账目列表加载
 * @param data
 */
function loadAccountView(data){
	var view = new wijmo.collections.CollectionView(data);
	guestDetaiAccFlexGrid.itemsSource = view;
}

/**
 * 账目页面中文及英文名切换事件
 */
function loadAccountDetail(){
	var item = {};
	item.check_id= $("#account_namec").val();
	item.room_id = $("#account_roomid").val();
	item.with_id = $("#account_billbid").val();
	item.billa_id = $("#account_namec option:selected").attr('data-billaid');
	item.billb_id = $("#account_billbid option:selected").attr('data-billaid');
	getAccountDetail(item);
	var chkstat = $("#account_namec option:selected").attr('data-chkstat');
	if(chkstat == 'O'){
		$("#leavingHotel").addClass('disabledAButton');
		$("#checkOutOperation").addClass('disabledAButton');
		$("#addPreAward").addClass('disabledAButton');
	}else{
		$("#leavingHotel").removeClass('disabledAButton');
		$("#checkOutOperation").removeClass('disabledAButton');
		$("#addPreAward").removeClass('disabledAButton');
	}
}
/**
 * 团队详情展示
 */
function showGroupDetails(){
	if(!grid.itemsSource)return;
	var item = grid.itemsSource.currentItem;
	if(!item){
		altWaringMsg('请选择一个团队');
		return;
	}
	var grpCheckId = item.check_id;
	loadConsume();
	$("input[name=inType][value=1]").prop('checked',true);
	initGstOri(1);
	initButton();
	$.ajax({
		url:'/group/getGrpdocDetail.do',
		data:{grpCheckId:item.check_id},
		dataType:'json',
		type:'get',
		success:function(data){
			var detail = data.grpDocDetail;
			var statMsg="";
            if(detail.bookStat == 'N'){
            	statMsg = "NO SHOW";
            }else if(detail.bookStat=='A'){
            	statMsg = "全部抵达";
            }else if(detail.bookStat=='R'){
            	statMsg = "部分抵达";
            }else if(detail.bookStat=='B'){
            	statMsg = "未确认";
            }else if(detail.bookStat=='C'){
            	statMsg = "取消";
            }else if(detail.bookStat=='O'){
            	statMsg = "已确认";
            }
            detail.bookStat = statMsg;
			setForm('groupForm',detail);
			setForm('otherForm',detail);
			var view = new wijmo.collections.CollectionView(data.guestList);
			grpGuestGrid.itemsSource = view;
			$("#roomSumNum").val(data.roomNum);
			$(".alertDivBg").fadeIn();
			$(".TeamMecheckDetailsDiv").fadeIn();
		},
		error:function(){
			altEerrMsg('查询异常');
		}
		
	});
}

/**
 * 房价列表
 */
function openRoomPriceList()
{
	var basePath = $("#path").val();
	var url = basePath + "/guestdetail/getPriceList.do";
	var item = grpGuestGrid.itemsSource.currentItem;
	if(!item) return;
	var checkId = item.check_id;
	var withId = item.with_id;
	var roomId = item.room_id;
	var sendData = {'withId':withId,'checkId':checkId,'roomId':roomId};
	var params = {type:'page',title:'房价列表'
		,id:'guestdetail_roompricelist'
		,content:url
		,titleClass:'alertDiv moveBar2 alertDiv2 pricesList'
		,data:sendData
		,isUpdate:true
		,width:620
		,top:10
		,show:function()
		{
			$.fn.alertDialogShow(params.id);
		}
	};
	$.fn.alertDialog(params);
};
//条件查询
function doSearch(code) {
	var formData = {};
	if(code && (code=='#' || code=='*')){
		formData.chkStat=(code=='#'?'I':'O');
	}else{
		formData = $("#searchForm").formToArray();
		if(code){
			formData.push({name:'codeVal',value:code});
		}else{
			var flag = true;
			for(var i =0;i<formData.length;i++){
				if(formData[i].value!='' && formData[i].name!='chkStat'){
					flag=false;
				}
			}
			if(flag){
				alert("请输入查询条件");
				return;
			}
		}
	}
	$.ajax({
		url : 'searchGroup.do',
		type : 'POST',
		data : formData,
		mimeType : "multipart/form-data",
		cache : false,
		success : function(data, textStatus, jqXHR) {
			var jsonObj = JSON.parse(data);
			var view = new wijmo.collections.CollectionView(jsonObj);
			grid.itemsSource = view;
			grid.refresh();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('查询异常');
		}
	});
}
//清空条件
function doClear() {
	$("#searchForm").resetForm();
}

function initGstOri(type){
	$("#gstOriId option").remove();
	$.ajax({
		url : 'getGstOri.do',
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
		url : 'getGstOriByOricode.do',
		type : 'get',
		data:{codeId:codeId},
		cache : false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			if(data.hgstOris){
				for(var i=0;i<data.hgstOris.length;i++){
					$("#gstOriId").append('<option value="'+$.trim(data.hgstOris[i].codeId)+'" >'+data.hgstOris[i].codeNamec+'</option>');
				}
				$("input[name=grouInType][value="+$.trim(data.inType)+"]").prop('checked',true);
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
 * 详情分账设置加载
 */
function loadConsume(){
	$("#consumeA_ID li").remove();
	$("#consumeB_ID li").remove();
	$.ajax({
		url:'getAllConsumes.do',
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
		if(!$("#gstOriId").val()){
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
	showGroupDetails();
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
	doSearch('#');
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

/**
 * 房号回车事件
 * @param e
 */
function searchByRoom(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	if(keynum==13){
		var roomId = $("#searchRoomId").val();
		//$("#split").attr('disabled',true).css("color","grey");
		loadConsume();
		$("input[name=inType][value=1]").prop('checked',true);
		initGstOri(1);
		initButton();
		$.ajax({
			url:'/group/getGrpdocDetailByRoom.do',
			data:{roomId:roomId},
			async:false,
			dataType:'json',
			success:function(data){
				if(!data){
					altInfMsg('当前房间无入住客人');
					return;
				}
				var detail = data.grpDocDetail;
				setForm('groupForm',detail);
				var view = new wijmo.collections.CollectionView(data.guestList);
				grpGuestGrid.itemsSource = view;
				$("#roomSumNum").val(data.roomNum);
				$(".alertDivBg").fadeIn();
				$(".TeamMecheckDetailsDiv").fadeIn();
			}
		});
	}
}

/**
 * 帐号回车事件
 * @param e
 */
function searchByAccount(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	if(keynum==13){
		var billId = $("#searchaAccountId").val();
		//$("#split").attr('disabled',true).css("color","grey");
		loadConsume();
		$("input[name=inType][value=1]").prop('checked',true);
		initGstOri(1);
		initButton();
		$.ajax({
			url:'/group/getGrpdocDetailByBill.do',
			data:{billId:billId},
			async:false,
			dataType:'json',
			success:function(data){
				if(!data){
					altInfMsg('当前帐号无团队信息');
					return;
				}
				var detail = data.grpDocDetail;
				setForm('groupForm',detail);
				var view = new wijmo.collections.CollectionView(data.guestList);
				grpGuestGrid.itemsSource = view;
				$("#roomSumNum").val(data.roomNum);
				$(".alertDivBg").fadeIn();
				$(".TeamMecheckDetailsDiv").fadeIn();
			}
		});
	}
}

/**
 * 账目刷新按钮
 */
function refreshAccountDetail(){
	var item = {};
	item.check_id= $("#account_namec").val();
	item.room_id = $("#account_roomid").val();
	item.with_id = $("#account_billbid").val();
	item.billa_id = $("#account_billaid").val();
	item.billb_id = $("#account_billbid").val();
	getAccountDetail(item);
}

/**
 * 账目退出
 */
function accountQuit(){
	$(".alertDivBg").fadeOut();
	$(".TeamMecheckDetailsDiv").fadeOut();
	doSearch('#');
}
/**
*@保留2位小数
*/
var changeTwoDecimal_f = function(number)
{
	var f_x = parseFloat(number);
	if (isNaN(f_x)) {
		//alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	var f_x = Math.round(number * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
};