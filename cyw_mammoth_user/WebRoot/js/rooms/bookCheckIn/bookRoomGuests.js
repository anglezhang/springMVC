var guestGrid,tag,maxNum,flag,isAdd=false,isEdit=false,reachDate,leaveDate,roomTag,currentTxt,accountGrid,_nowprice,checkOutGrid,preAuthorizationGrid,transferAccountGrid,transferArr=[],customAccountsGrid,LeavingHotelGrid,checkInTag=true,moenyId;
$(function(){
	$("#form1 input").inputmask();
	$("#discount").inputmask("numeric", { min: 0,max:1 });
	$("#discount").css('text-align','left');
	$("#email").inputmask('Regex', { regex: "[a-zA-Z0-9._%-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,4}" });
	$("#tele").inputmask({mask:"9{11}",placeholder:""});
	/* 遮罩层DIV高度 */
	$(".alertDivBg").css("height",$(document).height());     
    $(".alertDivBg").css("width",$(document).width());  
	/*遮罩层DIV高度*/
	/*table点击换行颜色*/
	$(".tabChangBg tr").click(function(){
		$(".tabChangBg tr td").removeClass("thisTrTd");
		$(this).find("td").addClass("thisTrTd");
	});
	/* /table点击换行颜色*/
	//证件类型
	initCodes('006','crdkindId');
	initCodes('006','otherCrdkindId');
	//性别
	initCodes('026','sex');
	//性别
	initCodes('026','otherSex');
	//省市
	initCodes('008','native_');
	//支付方式
	initSettle();
	//签证类型
	initCodes('006','visakindId');
	//签发机关
	initCodes('011','depart');
	//入境口岸
	initCodes('012','inPort');
	//客人分类
	initCodes('004','gstKind');
	initCodes('023','gstOriId');
	initCountry();
	initFolk();
	initMoneyId();
	/**
	 * 表单控件的修改事件
	 */
	$("#form1 input[id!=accountSetId]").change(function(){
		buttonChange();
	});
	$("#form1 input[id!=accountSetId]").keyup(function(){
		buttonChange();
	});
	$("#form1 select").change(function(){
		buttonChange();
	});
	/*住客详情*/
	/*关闭弹出1*/
	$(".hideDivOne").click(function() {
		$(".alertDivBg").fadeOut();
		$(".checkDetailsDiv").fadeOut();
	});
	/*关闭弹出1*/
	$("#payman_flag").click(function(){
		var v = $(this).attr('checked');
		if(v) $(this).prop('disabled',true);
	});
	/*弹出层内TAB页切换*/
	$(".userTab1").click(function(e) {
		// 调用 杨军鹏 的方法
		var item ;
		if(guestGrid.itemsSource){
			item = guestGrid.itemsSource.currentItem;
		}
		getDetail(item);
		roomTag = item.room_id;
		initButton();
		//住客详情加载
		guestGrid.onSelectionChanging = function(e){
			return true;
		};
		$(".userDetails").show();
		$(".userCatalog").hide();
		$(".userTab1").addClass("point");
		$(".userTab2").removeClass("point");
	});
	$(".userTab2").click(function(){
		if(isAdd || isEdit){
			altWaringMsg("请先保存当前数据");
			return;
		}
		$("#account_namec option").remove();
		$("#account_namee option").remove();
		var item ;
		if(guestGrid.itemsSource){
			item = guestGrid.itemsSource.currentItem;
		}
		if(item){
			getAccountDetail(item);
			 $("#roomList2 li").each(function(){
            	if(item.room_id == $(this).text()){
            		$(this).addClass('roomListLi');
            	}
	        });
            $("#roomList2 li").click(function(){
            	  $(this).addClass("roomListLi");
    		      $(this).siblings().removeClass("roomListLi");
    	    });
		}
		$(".userDetails").hide();
		$(".userCatalog").show();
		$(".userTab1").removeClass("point");
		$(".userTab2").addClass("point");
	
	});
	/* /弹出层内TAB页切换*/

	/*房价列表二级弹出*/
	$("#pricesList").click(function() {
		$("#priceTable").children().remove();
		 var view = guestGrid.itemsSource;
		 var item = view.currentItem;
		 if(!item) return;
		 $("#houseInfo").html($("#roomId").val());
		 $.ajax({
		    	url:'/guest/gstprice.do',
		    	async:false,
		    	data:{'check_id':item.check_id},
		    	dataType:'json',
		    	success:function(data){
		    		if(data){
		    			var totalPrice = 0;
		    			for(var i=0;i<data.length;i++){
		    				var $tr =$("<tr></tr>");
		    				var tds = "<td width='110' align='center'>"+new Date(data[i].hotelDate).format("yyyy-MM-dd")+"</td>"
		    				  		+"<td width='80' align='center'>"+data[i].week+"</td>"
		    				        +"<td width='100' align='center'>"+data[i].price+"</td>";
		    				if(hotelDate > new Date(data[i].hotelDate).format("yyyy-MM-dd")){
		    					tds+="<td width='90' align='center'></td><td width='100' align='center'></td>";
		    				}else{
		    					tds+="<td width='90' align='center'>修改<input class='checkbox margin-left-5' type='checkbox' ></td><td width='100' align='center'><input class='input margin-r' id='price_"+data[i].id+"' type='number'/></td>";
		    				}
		    				var $td = $(tds);
		    				$td.appendTo($tr);  
		    				$tr.appendTo($("#priceTable")); 
		    				totalPrice+=data[i].price;
		    			}
		    			$("#totalPrice").html(totalPrice);
		    		}
		    	}
		});
		$(".pricesList").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	$("#priceList_2").click(function() {
		$("#priceTable").children().remove();
		 $("#houseInfo").html($("#account_roomid").val());
		 var checkId = $("#account_namec").val();
		 $.ajax({
		    	url:'/guest/gstprice.do',
		    	async:false,
		    	data:{'check_id':checkId},
		    	dataType:'json',
		    	success:function(data){
		    		if(data){
		    			var totalPrice = 0;
		    			for(var i=0;i<data.length;i++){
		    				var $tr =$("<tr></tr>");
		    				var tds = "<td width='110' align='center'>"+new Date(data[i].hotelDate).format("yyyy-MM-dd")+"</td>"
		    				  		+"<td width='80' align='center'>"+data[i].week+"</td>"
		    				        +"<td width='100' align='center'>"+data[i].price+"</td>";
		    				if(hotelDate > new Date(data[i].hotelDate).format("yyyy-MM-dd")){
		    					tds+="<td width='90' align='center'></td><td width='100' align='center'></td>";
		    				}else{
		    					tds+="<td width='90' align='center'>修改<input class='checkbox margin-left-5' type='checkbox' ></td><td width='100' align='center'><input class='input margin-r' id='price_"+data[i].id+"' type='number'/></td>";
		    				}
		    				var $td = $(tds);
		    				$td.appendTo($tr);  
		    				$tr.appendTo($("#priceTable")); 
		    				totalPrice+=data[i].price;
		    			}
		    			$("#totalPrice").html(totalPrice);
		    		}
		    	}
		});
		$(".pricesList").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	$("#otherGuestInformation").click(function() {
		$("#otherSex").val($("#sex").val());
		$("#otherNamee").val($("#gstNamee").val());
		$("#otherCountry").val($("#country").val());
		$("#otherCrdkindId").val($("#crdkindId").val());
		$("#otherCrdId").val($("#crdId").val());
		$("#otherDiv").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	$("#changeRoom").click(function() {
		$(".changeRoomDiv").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	$(".closeAlert").click(function(){
		$(".pricesList").fadeOut();
		$(".alertDivBg2").fadeOut();
		$(".splitInfoDiv").fadeOut();
		$("#otherDiv").fadeOut();
		$(".changeRoomDiv").fadeOut();
		$("#depositDiv").fadeOut();
		$("#receivablesDiv").fadeOut();
		$("#LeavingHotelDiv").fadeOut();
		$("#preAuthorizationDiv").fadeOut();
		$("#accountedForDiv").fadeOut();
		$("#checkOutDiv").fadeOut();
		$("#transferAccountsDiv").fadeOut();
		$("#guestSplitInfoDiv").fadeOut();
		$("#forALongTimeDiv").fadeOut();
		$("#checkOutOperationDiv").fadeOut();
		$("#customAccountsDiv").fadeOut();
		$("#accountDetermineDiv").fadeOut();
		$(".splitAccountsDiv").fadeOut();
	});
	/*自定义账目*/
	$("#customAccounts").click(function(){
		var checkId = $("#account_namec").val();
		var bill_type = $('input[name=radioAccount]:checked').val();
		var show_type = $('input[name=radioType]:checked').val();
		var okFlag = $('input[name=disturb]:checked').val();
		var billId;
		if(bill_type=='1'){
			billId = $("#account_billaid").val();
		}else{
			billId = $("#account_billbid").val();
		}
		$.ajax({
			url : '/guest/guestAccountInfo.do',
			type : 'POST',
			cache : false,
			data:{check_id:checkId,billId:billId,billType:bill_type,showType:'A',okFlag:'A'},
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				var view = new wijmo.collections.CollectionView(data.bills);
				customAccountsGrid.itemsSource = view;
				$("#customAccountName").val(data.detail.gstNamec);
				$("#customAccountRoomId").val(data.detail.roomId);
				$("#customAccountCompId").val(data.detail.compId);
				$("#customAccountReachDate").val(data.detail.reachDate);
				$("#customAccountLeaveDate").val(data.detail.leaveDate);
				$("#customAccountRoomPrice").val(data.detail.price);
				if(bill_type=='1'){
					$("#customAccountBorrow").val(data.detail.borrow);
					$("#customAccountLent").val(data.detail.lent);
					$("#customAccountRemain").val(changeTwoDecimal_f(parseFloat(data.detail.borrow)-parseFloat(data.detail.lent)));
				}else{
					$("#customAccountBorrow").val(data.detail.Bborrow);
					$("#customAccountLent").val(data.detail.Blent);
					$("#customAccountRemain").val(changeTwoDecimal_f(parseFloat(data.detail.Bborrow)-parseFloat(data.detail.Blent)));
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				//alert('查询异常');
				altEerrMsg('查询异常');
			}
		});
		$(".alertDivBg2").fadeIn();
		$("#customAccountsDiv").fadeIn();
	});
	/*/自定义账目*/
	/*退房*/
	$("#checkOutOperation").click(function(){
		//var grpChkid = $("input[name=grpChkid]").val();
		var length = $("#roomList2 li").length;
		if(length>1){
			$("#retreatSecondRoomId").text($("#account_roomid").val());
			$("#retreatRoomFirstDiv").hide();
			$("#retreatRoomSecondDiv").show();
		}else{
			$("#retreatFirstRoomId").text($("#account_roomid").val());
			$("#retreatRoomFirstDiv").show();
			$("#retreatRoomSecondDiv").hide();
		}
		$(".alertDivBg2").fadeIn();
		$("#checkOutOperationDiv").fadeIn();
	});
	/*/退房*/
	/*半日租-分账*/
	$("#forALongTime").click(function(){
		$("#forLongForm").resetForm();
		var checkId = $("#account_namec").val();
		var withId = $("#account_namec option:selected").attr("data-withid");
		var roomId = $("#account_roomid").val();
		var billId,billType;
		$("#forLongConsume option").remove();
		$("#forLongNamec option").remove();
		$("#forLongNamee option").remove();
		$("#forLongExchange option").remove();
		var opstion;
		$.ajax({
			url : '/guest/getLongTimeInfo.do',
			type : 'POST',
			cache : false,
			data:{checkId:checkId,roomId:roomId,withId:withId},
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				$("input[name=effective_forlong][value="+data.billType+"]").prop('checked',true);
				billType = $("input[name=effective_forlong]:checked").val();
				if(billType==1){
					billId = $("#account_namec option:selected").attr('data-billaid');
		    	}else{
		    		billId = $("#account_namec option:selected").attr('data-billbid');
		    	}
				if(data.guests){
					for(var i=0;i<data.guests.length;i++){
						$("#forLongNamec").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namec+'</option>');
						$("#forLongNamee").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namee+'</option>');
						if(checkId == data.guests[i].check_id){
							opstion = i;
							switch (billType) {
								case '1':
									$("#forLonglimit").val(data.guests[i].Alimit);
									break;
								case '2':
									$("#forLonglimit").val(data.guests[i].Blimit);
									break;
							}
						}
					}
				}
				if(data.consumes){
					for(var i=0;i<data.consumes.length;i++){
						$("#forLongConsume").append('<option data-svcRate="'+data.consumes[i].svcRate+'" value="'+data.consumes[i].codeId+'">'+data.consumes[i].codeNamec+'</option>');
					}
				}
				if(data.exchanges){
					for(var i=0;i<data.exchanges.length;i++){
						$("#forLongExchange").append('<option value="'+data.exchanges[i].codeId+'">'+data.exchanges[i].codeNamec+'</option>');
					}
				}
				$("#forLongConsume").val(data.longCode);
				$("#forLongConsume").prop('disabled',true);
				$("#forLongFormReset").click(function(){
					$("#forLongForm").resetForm();
					$("#forLongNamec").val(checkId);
					$("#forLongNamee").val(checkId);
					$("#forLongRoomId").val($("#account_roomid").val());
					$("#forLongBillId").val(billId);
					$("#forLongConsume").val(data.longCode);
					switch (billType) {
						case '1':
							$("#forLonglimit").val(data.guests[opstion].Alimit);
							break;
						case '2':
							$("#forLonglimit").val(data.guests[opstion].Blimit);
							break;
					}
				});
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		
		});
		$("#forLongNamec").val(checkId);
		$("#forLongNamee").val(checkId);
		$("#forLongRoomId").val($("#account_roomid").val());
		$("input[name=list_02][value=A]").attr('checked','checked');
		$("#forLongBillId").val(billId);
		$(".alertDivBg2").fadeIn();
		$("#forALongTimeDiv").fadeIn();
	});
	/*/半日租-分账*/
	/*客人账目-分账*/
	$("#guestSplitInfo").click(function(){
		loadAccountConsume();
		$(".alertDivBg2").fadeIn();
		$("#guestSplitInfoDiv").fadeIn();
	});
	/*/客人账目-分账*/
	/*转账*/
	$("#transferAccounts").click(function(){
		$("input[name=transferRadio_1][value=1]").prop('checked',true);
		$("input[name=transferRadio_2][value=I]").prop('checked',true);
		loadTransferGrid('1');
		$(".alertDivBg2").fadeIn();
		$("#transferAccountsDiv").fadeIn();
	});
	$("input[name=transferRadio_1]").click(function(){
		var value = $(this).val();
		switch (value) {
		case '1':
			$("#transferDiv_1").show();
			$("#transferDiv_2").hide();
			$("#transferDiv_3").hide();
			$("input[name=transferRadio_2][value=I]").prop('checked',true);
			loadTransferGrid('1');
			break;
		case '2':
			$("#transferDiv_1").hide();
			$("#transferDiv_2").show();
			$("#transferDiv_3").hide();
			$("input[name=transferRadio_3][value=I]").prop('checked',true);
			loadTransferGrid('2');
			break;
		case '3':
			$("#transferDiv_1").hide();
			$("#transferDiv_2").hide();
			$("#transferDiv_3").show();
			loadTransferGrid('3');
			break;
		default:
			break;
		}
	});
	/*转账*/
	/*结账设置*/
	$("#checkOut").click(function(){
		$("#checkOutForm").resetForm();
		//借项总额
		var borrow=0;
		//贷项总额
		var lent=0;
		for(var i=0;i<accountGrid.rows.length;i++){
			var row = accountGrid.rows[i];
			if(row.isSelected){
				if(row.dataItem.cname!=''){
					borrow+=parseFloat(row.dataItem.balance)+parseFloat(row.dataItem.svc_charge);
				}
				if(row.dataItem.sname!=''){
					lent+=parseFloat(row.dataItem.balance);
				}
			}
		}
		var paidMoney = changeTwoDecimal_f(borrow - lent);
		var odd = paidMoney.substring(paidMoney.indexOf(".")+1);
		if(odd.length>0){
			var temp ='';
			for(var i=0;i<odd.length;i++){
				temp+='0';
			}
			if(temp!=odd){
			   var roundMoney = Math.round(borrow - lent);
			   if(roundMoney>(borrow - lent)){
				   $("#checkOutOddment").val(Math.round((roundMoney-(borrow - lent))*100)/100);
			   }else{
				   $("#checkOutOddment").val('-0.'+odd);
			   }
			}
		}
		$("#checkOutRemain").val(changeTwoDecimal_f(Math.round(borrow - lent)));
		$("#checkOutBalance").val(changeTwoDecimal_f(Math.round(borrow - lent)));
		$("#checkOutSumBalance").val(changeTwoDecimal_f(Math.round(borrow - lent)));
		$("#checkOutSetlId option").remove();
		$("#checkOutmoneyKind option").remove();
		$.ajax({
			url : '/guest/loadSettleAndMoneyKind.do',
			type : 'POST',
			cache : false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(!data.msg && data.sList){
					for(var i=0;i<data.sList.length;i++){
						$("#checkOutSetlId").append('<option value="'+$.trim(data.sList[i].codeId)+'">'+data.sList[i].codeNamec+'</option>');
					}
				}
				if(data.eList){
					for(var i=0;i<data.eList.length;i++){
						$("#checkOutmoneyKind").append('<option value="'+data.eList[i].codeId+'">'+data.eList[i].codeNamec+'</option>');
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		//结账账目列表 
	    var checkOutCollectionView = new wijmo.collections.CollectionView([]);
	    if(!checkOutGrid){
    	   checkOutGrid =  new wijmo.grid.FlexGrid('#checkOutGrid', {
		    	autoGenerateColumns: false,
		  	    columns: [
		             { header: '贷方科目', binding: 'setlName',width:'*'}, 
		             { header: '金额', binding: 'balance' ,width:'*'},
		             { header: '外币', binding: 'foreignm' ,width:'*'}, 
		             { header: '币种', binding: 'moneykindName',width:'*'},
		             { header: '备注', binding: 'notes', width:'*' }
		     	 ],
		     	 itemsSource:checkOutCollectionView,
		     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
		     	 selectionMode:'Row',
		    });
	    }
	    checkOutGrid.itemsSource=checkOutCollectionView;
	    checkOutGrid.itemsSource.refresh();
	    if(Math.round($("#checkOutRemain").val())==0){
	    	$("#checkOutRemain").prop('disabled',true);
	    }else{
	    	$("#checkOutRemain").prop('disabled',false);
	    }
	    $("input[name=checkOut_print][value=1]").prop('checked',true);
	    $("input[name=checkOut_language][value=1]").prop('checked',true);
	    $("input[name=checkOut_type][value=1]").prop('checked',true);
		$(".alertDivBg2").fadeIn();
		$("#checkOutDiv").fadeIn();
	});
	/*结账设置*/
	/*入账设置*/
	$("#accountedFor").click(function(){
		var checkId = $("#account_namec").val();
		var withId = $("#account_namec option:selected").attr("data-withid");
		var roomId = $("#account_roomid").val();
		$("input[name=effective][value=1]").prop('checked',true);
		var billId;
		var billType = $("input[name=effective]:checked").val();
		if(billType==1){
    		billId = $("#account_namec option:selected").attr('data-billaid');
    	}else{
    		billId = $("#account_namec option:selected").attr('data-billbid');
    	}
		$("#accountConsume option").remove();
		$("#incomeNamec option").remove();
		$("#incomeNamee option").remove();
		$("#accountExchange option").remove();
		var opstion;
		$.ajax({
			url : '/guest/getAccountFor.do',
			type : 'POST',
			cache : false,
			data:{checkId:checkId,roomId:roomId,withId:withId,billType:billType},
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(data.guests){
					for(var i=0;i<data.guests.length;i++){
						$("#incomeNamec").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namec+'</option>');
						$("#incomeNamee").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namee+'</option>');
						if(checkId == data.guests[i].check_id){
							opstion = i;
							switch (billType) {
								case '1':
									$("#accountlimit").val(data.guests[i].Alimit);
									break;
								case '2':
									$("#accountlimit").val(data.guests[i].Blimit);
									break;
							}
						}
					}
				}
				if(data.consumes){
					for(var i=0;i<data.consumes.length;i++){
						$("#accountConsume").append('<option data-svcRate="'+data.consumes[i].svcRate+'" value="'+data.consumes[i].codeId+'">'+data.consumes[i].codeNamec+'</option>');
					}
				}
				if(data.exchanges){
					for(var i=0;i<data.exchanges.length;i++){
						$("#accountExchange").append('<option value="'+data.exchanges[i].codeId+'">'+data.exchanges[i].codeNamec+'</option>');
					}
				}
				$("#accountFormReset").click(function(){
					$("#accountForm").resetForm();
					$("#incomeNamec").val(checkId);
					$("#incomeNamee").val(checkId);
					$("#incomeRoomId").val($("#account_roomid").val());
					$("#accountBillId").val(billId);
					switch (billType) {
						case '1':
							$("#accountlimit").val(data.guests[opstion].Alimit);
							break;
						case '2':
							$("#accountlimit").val(data.guests[opstion].Blimit);
							break;
					}
				});
				changePaied();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		
		});
		$("#incomeNamec").val(checkId);
		$("#incomeNamee").val(checkId);
		$("#incomeRoomId").val($("#account_roomid").val());
		$("input[name=list_01][value=A]").attr('checked','checked');
		$("#accountBillId").val(billId);
		$(".alertDivBg2").fadeIn();
		$("#accountedForDiv").fadeIn();
	});
	/*入账设置*/
	/*押金*/
	$("#deposit").click(function(){
		$("#depositSettle option").remove();
		$("#depositMoneyKind option").remove();
		$.ajax({
			url : '/guest/loadSettleAndMoneyKind.do',
			type : 'POST',
			cache : false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(!data.msg && data.sList){
					for(var i=0;i<data.sList.length;i++){
						$("#depositSettle").append('<option value="'+$.trim(data.sList[i].codeId)+'" >'+data.sList[i].codeNamec+'</option>');
					}
					$("#depositSettle").val(data.deposit);
					$("#depositSettle").prop("disabled",true);
				}
				if(data.eList){
					for(var i=0;i<data.eList.length;i++){
						$("#depositMoneyKind").append('<option value="'+$.trim(data.eList[i].codeId)+'">'+data.eList[i].codeNamec+'</option>');
					}
					$("#depositMoneyKind").val($.trim(data.exchange));
					$("#depositMoneyKind").prop("disabled",true);
				}
				if($.trim(data.exchange) == $.trim(moneyId)){
					$("#depositForm input[name=foreignm]").prop('disabled',true);
				}else{
					$("#depositForm input[name=balance]").prop('disabled',true);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		$(".alertDivBg2").fadeIn();
		$("#depositDiv").fadeIn();
	
	});
	/*押金*/
	/*收款*/
	$("#receivables").click(function(){
		$("#receivablesSettle option").remove();
		$("#receivablesMoneyKind option").remove();
		$.ajax({
			url : '/guest/loadSettleAndMoneyKind.do',
			type : 'POST',
			cache : false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(!data.msg && data.sList){
					for(var i=0;i<data.sList.length;i++){
						$("#receivablesSettle").append('<option value="'+$.trim(data.sList[i].codeId)+'" data-moneyid="'+$.trim(data.sList[i].moneyId)+'">'+data.sList[i].codeNamec+'</option>');
					}
				}
				if(data.eList){
					for(var i=0;i<data.eList.length;i++){
						$("#receivablesMoneyKind").append('<option value="'+$.trim(data.eList[i].codeId)+'">'+data.eList[i].codeNamec+'</option>');
					}
				}
				var moneykind = $("#receivablesSettle option:selected").attr('data-moneyid');
				if($.trim(moneyId) == moneykind){
					$("#receivablesForm input[name=foreignm]").prop('disabled',true);
					$("#receivablesForm input[name=balance]").prop('disabled',false);
				}else{
					$("#receivablesForm input[name=balance]").prop('disabled',true);
					$("#receivablesForm input[name=foreignm]").prop('disabled',false);
				}
				$("#receivablesMoneyKind").val(moneykind);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		$(".alertDivBg2").fadeIn();
		$("#receivablesDiv").fadeIn();
	});
	/*收款*/
	//收款方式切换事件显示对应的币种 及设置本位币及外币disabled
	$("#receivablesSettle").change(function(){
		var moneyKind = $("#receivablesSettle option:selected").attr('data-moneyid');
		$("#receivablesMoneyKind").val(moneyKind);
		if(moneyKind == $.trim(moneyId)){
			$("#receivablesForm input[name=foreignm]").prop('disabled',true);
			$("#receivablesForm input[name=balance]").prop('disabled',false);
		}else{
			$("#receivablesForm input[name=balance]").prop('disabled',true);
			$("#receivablesForm input[name=foreignm]").prop('disabled',false);
		}
	});
	//入账币种切换时间
	$("#accountExchange").change(function(){
		var moneyKind = $(this).val();
		if(moneyKind == $.trim(moneyId)){
			$("#accountForm input[name=foreignm]").prop('disabled',true);
			$("#accountForm input[name=balance]").prop('disabled',false);
		}else{
			$("#accountForm input[name=balance]").prop('disabled',true);
			$("#accountForm input[name=foreignm]").prop('disabled',false);
		}
	});
	/*离店*/
	$("#LeavingHotel").click(function(){
		var roomId = $("#account_roomid").val();
		var withId = $("#account_namec").attr('data-withid');
		$.ajax({
			url : '/guest/searchGuest.do',
			type : 'POST',
			data : {roomId:roomId,withId:withId,chkStat:'I'},
			cache : false,
			success : function(data, textStatus, jqXHR) {
				var jsonObj = JSON.parse(data);
				var view = new wijmo.collections.CollectionView(jsonObj);
				LeavingHotelGrid.itemsSource = view;
				LeavingHotelGrid.refresh();
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		$("input[name=leavingHotelRadio][value=A]").prop('checked',true);
		$(".alertDivBg2").fadeIn();
		$("#LeavingHotelDiv").fadeIn();
	});
	/*离店*/
	$("input[name=leavingHotelRadio]").click(function(){
		var value = $(this).val();
		switch (value) {
		case 'A':
			$("#LeavingHotel").click();
			LeavingHotelGrid.selectionMode = 'None';
			break;
		case 'B':
			$("#LeavingHotel").click();
			$("input[name=leavingHotelRadio][value=B]").prop('checked',true);
			LeavingHotelGrid.selectionMode = 'ListBox';
		    break;
		case 'C':
			var roomId = $("#account_roomid").val();
			var withId = $("#account_namec").attr('data-withid');
			$.ajax({
				url : '/guest/searchGuest.do',
				type : 'POST',
				data : {roomId:roomId,withId:withId,leaveDate:new Date().format('yyyy-MM-dd'),chkStat:'I'},
				cache : false,
				dataType:'json',
				success : function(data, textStatus, jqXHR) {
					var view = new wijmo.collections.CollectionView(data);
					LeavingHotelGrid.itemsSource = view;
					LeavingHotelGrid.refresh();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					altEerrMsg('查询异常');
				}
			});
			LeavingHotelGrid.selectionMode = 'None';
			break;
		case 'D':
			var checkId = $("#account_namec").val();
			$.ajax({
				url : '/guest/searchGuest.do',
				type : 'POST',
				data : {checkId:checkId,chkStat:'I'},
				cache : false,
				dataType:'json',
				success : function(data, textStatus, jqXHR) {
					var view = new wijmo.collections.CollectionView(data);
					LeavingHotelGrid.itemsSource = view;
					LeavingHotelGrid.refresh();
				},
				error : function(jqXHR, textStatus, errorThrown) {
					altEerrMsg('查询异常');
				}
			});
			LeavingHotelGrid.selectionMode = 'None';
			break;
		default:
			break;
		}
	});
	/*预授权*/
	$("#preAuthorization").click(function(){
		//预授权列表 
		var checkId = $("#account_namec").val();
		$("input[name=preAuthRadio][value=0]").prop('checked',true);
		$.ajax({
			url:'/guest/getPreAuthorization.do',
			type:'get',
			data:{checkId:checkId,status:0},
			dataType:'json',
			success:function(data){
				if(data){
					var sum=0;
					for(var i=0;i<data.length;i++){
						sum+=parseFloat(data[i].balance);
					}
					$("#preAuthSumBalance").html(changeTwoDecimal_f(sum));
				}
				var view = new wijmo.collections.CollectionView(data);
				preAuthorizationGrid.itemsSource = view;
			}
		});
		$(".alertDivBg2").fadeIn();
		$("#preAuthorizationDiv").fadeIn();
	});
	/*分账设置*/
	$("#split").click(function() {
		loadConsume();
		$(".alertDivBg2").fadeIn();
		$(".splitInfoDiv").fadeIn();
	});
	/*弹出3层*/
	$(".closeAlert3").click(function(){
		$(".alertDivBg3").fadeOut();
		$(".addPreAwardDiv").fadeOut();
	});
	$("#addPreAward").click(function(){
		$(".alertDivBg3").fadeIn();
		$(".addPreAwardDiv").fadeIn();
	});
	$("input[name=preAuthRadio]").click(function(){
		reloadAuthGrid();
	});
	/*离店*/
	$("#LeavingHotel").click(function() {
		$(".alertDivBg2").fadeIn();
		$(".LeavingHotelDiv").fadeIn();
	});
    $("#gstNamec").keyup(function (){
        $("#gstNamee").val(pinyin.getFullChars($(this).val()));
    });
    $("input[name=radioAccount]").click(function(){
    	reloadAccount();
    });
    $("input[name=radioType]").click(function(){
    	reloadAccount();
    });
    $("input[name=disturb]").click(function(){
    	reloadAccount();
    });
    $("#incomeNamec").change(function(){
    	$("#incomeNamee").val($(this).val());
    	var bill_type = $("input[name=effective]:checked").val();
    	$("#accountBillId").val(bill_type=='1' ? $("#incomeNamec option:selected").attr('data-billaid') : $("#incomeNamec option:selected").attr('data-billbid'));
    	$("#accountlimit").val(bill_type=='1' ? $("#incomeNamec option:selected").attr('data-alimit') : $("#incomeNamec option:selected").attr('data-blimit'));
    	//loadAccountConsumes($(this).val());
    });
    $("#incomeNamee").change(function(){
    	$("#incomeNamec").val($(this).val());
    	var bill_type = $("input[name=effective]:checked").val();
    	$("#accountBillId").val(bill_type=='1' ? $("#incomeNamec option:selected").attr('data-billaid') : $("#incomeNamec option:selected").attr('data-billbid'));
    	$("#accountlimit").val(bill_type=='1' ? $("#incomeNamec option:selected").attr('data-alimit') : $("#incomeNamec option:selected").attr('data-blimit'));
    	//loadAccountConsumes($(this).val());
    });
    $("input[name=effective]").change(function(){
    	var bill_type = $(this).val();
    	var checkId = $("#incomeNamec").val();
    	$("#accountBillId").val(bill_type=='1' ? $("#incomeNamec option:selected").attr('data-billaid') : $("#incomeNamec option:selected").attr('data-billbid'));
    	$("#accountlimit").val(bill_type=='1' ? $("#incomeNamec option:selected").attr('data-alimit') : $("#incomeNamec option:selected").attr('data-blimit'));
    });
    $("input[name=effective_forlong]").click(function(){
    	var bill_type = $(this).val();
    	var checkId = $("#forLongNamec").val();
    	$("#forLongBillId").val(bill_type=='1' ? $("#forLongNamec option:selected").attr('data-billaid') : $("#forLongNamec option:selected").attr('data-billbid'));
    	$("#forLonglimit").val(bill_type=='1' ? $("#forLongNamec option:selected").attr('data-alimit') : $("#forLongNamec option:selected").attr('data-blimit'));
    });
    $("#isserve").click(function(){
    	var checked = this.checked;
    	if(checked){
    		$("#serveMoney").attr("disabled",false);
    		$("#serveMoney").focus();
    	}else{
    		$("#serveMoney").attr("disabled","disabled");
    	}
    });
    $("#accountDetermine").click(function(){
    	var checkId = $("#account_namec").val();
    	var view = transferAccountGrid.itemsSource;
    	var id = view.currentItem;
    	var type = $("input[name=transferRadio_1]:checked").val();
    	var transferType = $("input[name=transferTopRadio]:checked").val();
		var billId ;
		if(transferType=='1') billId = $("#account_billaid").val();
		else billId = $("#account_billbid").val();
		//加载转账页面上方数据
    	loadTransferTopData(checkId,billId,transferType);
    	//加载转账页面下方数据
    	loadTransferBottomData(type);
    	transferArr=[];
		$("#transferAccountsDiv").hide();
		$(".accountDetermineDiv").show();
	});
    $("input[name=transferTopRadio]").click(function(){
    	var transferType = $(this).val();
    	var checkId = $("#account_namec").val();
    	var billId ;
		if(transferType=='1') billId = $("#account_billaid").val();
		else billId = $("#account_billbid").val();
    	loadTransferTopData(checkId,billId,transferType);
    });
    $("input[name=transferBottomRadio]").click(function(){
    	var type = $("input[name=transferRadio_1]:checked").val();
    	loadTransferBottomData(type);
    });
    $("#transferToBottom").click(function(){
    	var view = transferTopGrid.itemsSource;
    	var view2 = transferBottomGrid.itemsSource;
    	var bottomData = view2.items;
    	for(var i=0;i<transferTopGrid.rows.length;i++){
			var row = transferTopGrid.rows[i];
			if(row.isSelected && !view2.contains(row.dataItem)){
				bottomData.push(row.dataItem);
				row.dataItem.transfer = !row.dataItem.transfer;
				if(row.dataItem.transfer){
					transferArr.push(row.dataItem);
				}else{
					transferArr.remove(row.dataItem);
				}
				view.remove(row.dataItem);
			}
		}
    	view2.refresh();
    });
    $("#transferToTop").click(function(){
    	var view = transferTopGrid.itemsSource;
    	var view2 = transferBottomGrid.itemsSource;
    	var topData = view.items;
    	for(var i=0;i<transferBottomGrid.rows.length;i++){
			var row = transferBottomGrid.rows[i];
			if(row.isSelected && !view.contains(row.dataItem)){
				topData.push(row.dataItem);
				row.dataItem.transfer = !row.dataItem.transfer;
				if(row.dataItem.transfer){
					transferArr.push(row.dataItem);
				}else{
					transferArr.remove(row.dataItem);
				}
				view2.remove(row.dataItem);
			}
		}
    	view.refresh();
    });
	/*拆分账目*/
	$("#splitAccounts").click(function(){
		$(".splitAccountsDiv").fadeIn();
		$(".alertDivBg2").fadeIn();
	});
	/*拆分账目END*/
	/*离店*/
//			$("#gstNamec").keyup(function (e) { 
//				 if(e.keyCode==13){
//					 currentTxt = $("#gstNamec").val();  
//					 pinyinCallback(); 
//				 }
//		       }).focus(function () {  
//		           this.select();  
//		     });
	
	//入住类型切换事件
	$("input[name=inType]").click(function(){
		var value = $(this).val();
		initGstOri(value);
		if(value=='3'){
			$("#reach_date").val(new Date().format('yyyy-MM-dd'));
			$("#leave_date").val(new Date().format('yyyy-MM-dd'));
			$("#nowPrice").val(_nowprice);
		}else{
			$("#reach_date").val(reachDate);
			$("#leave_date").val(leaveDate);
			if(value=='5'||value=='4'){
				$("#nowPrice").val(0.00);
			}else{
				$("#nowPrice").val(_nowprice);
			}
		}
	});

	//guest列表
    guestGrid = new wijmo.grid.FlexGrid('#guestRoomId', {
   	    autoGenerateColumns: false,
  	    columns: [
             { header: '序号', binding: 'sortNum',width:50 }, 
             { header: '中文名', binding: 'gst_namec' ,width:'*'},
             { header: '性别', binding: 'sexname' ,width:'*'}, 
             { header: '身份', binding: 'chkExt',width:'*'},
             { header: '房租', binding: 'room_price', width:'*' },
             { header: '状态', binding: 'chkStat',width:'*' },
             { header: '登记号', binding: 'check_id',width:'*' }
     	 ],
     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 selectionMode:'Row',
     	 isReadOnly:true
    });
    
    //客人账目列表
    accountGrid =  new wijmo.grid.FlexGrid('#accountGrid', {
   	    autoGenerateColumns: false,
  	    columns: [
  	         { header: 'ID', binding: 'ID',width:50,visible:false}, 
             { header: '序号', binding: 'sortNum',width:50 }, 
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
             { header: '身份', binding: 'ext_name',width:100 },
             { header: '备注', binding: 'notes',width:100 },
             { header: 'ID号', binding: 'ID',width:100 },
             { header: '结账号', binding: 'pay_num',width:100 }
     	 ],
     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 selectionMode:'ListBox',
    }); 
    
 	preAuthorizationGrid =  new wijmo.grid.FlexGrid('#preAuthorizationGrid', {
    	autoGenerateColumns: false,
  	    columns: [
  	          { header: 'ID', binding: 'ID', width:'*',visible:false },
  	         { header: '授权号', binding: 'auth_id', width:'*' },
             { header: '授权金额', binding: 'balance' ,width:'*'},
             { header: '有效期', binding: 'expired' ,width:'*'}, 
             { header: '状态', binding: 'status',width:'*'},
             { header: '操作员', binding: 'oper_name', width:'*' },
             { header: '授权时间', binding: 'oper_time', width:'*' },
             { header: '信用卡号', binding: 'credit_id', width:'*' },
             { header: '持卡人', binding: 'credit_holder', width:'*' }
     	 ],
     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 selectionMode:'ListBox',
    });
 	
    //客人账目列表
 	transferAccountGrid =  new wijmo.grid.FlexGrid('#transferAccountGrid', {
   	    autoGenerateColumns: false,
  	    columns: [
  	         { header: 'checkId', binding: 'check_id',width:50,visible:false}, 
             { header: '序号', name:'sort', binding: 'sortNum',width:50 }, 
             { header: '中文名',name:'namec', binding: 'gst_namec' ,width:100},
             { header: '房号', binding: 'room_id' ,width:100}, 
             { header: '摘要', binding: 'chkExt',width:100},
             { header: '团代码', binding: 'grp_id', width:100 },
             { header: 'A账余额', binding: 'remainA',width:100 },
             { header: 'B账余额', binding: 'remainB',width:70 },
             { header: '抵店日期', binding: 'reach_date',width:100 },
             { header: '离店日期', binding: 'leave_date',width:100 },
             { header: '公司', binding: 'company',width:100 },
             { header: '账号', name:"billId",binding: 'billa_id',width:100 }
     	 ],
     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 selectionMode:'ListBox',
    });
    //客人账目列表
 	transferTopGrid =  new wijmo.grid.FlexGrid('#transferTopGrid', {
   	    autoGenerateColumns: false,
  	    columns: [
	             { header: '借项', binding: 'cname' ,width:100}, 
	             { header: '贷项', binding: 'sname',width:100},
	             { header: '单号', binding: 'acco_id', width:100 },
	             { header: '金额', binding: 'balance', width:100 },
	             { header: '外币', binding: 'foreignm',width:100 },
	             { header: '币种', binding: 'code_namec',width:100 },
	             { header: '服务费', binding: 'svc_charge',width:100 },
	             { header: '时间', binding: 'oper_time',width:100 },
	             { header: '摘要', binding: 'ext_name',width:100 },
	             { header: '房号', binding: 'room_id',width:70 },
	             { header: '备注', binding: 'notes',width:100 }
     	 ],
     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 selectionMode:'ListBox',
    });
 	
 	transferBottomGrid =  new wijmo.grid.FlexGrid('#transferBottomGrid', {
   	    autoGenerateColumns: false,
  	    columns: [
	             { header: '借项', binding: 'cname' ,width:100}, 
	             { header: '贷项', binding: 'sname',width:100},
	             { header: '单号', binding: 'acco_id', width:100 },
	             { header: '金额', binding: 'balance', width:100 },
	             { header: '外币', binding: 'foreignm',width:100 },
	             { header: '币种', binding: 'code_namec',width:100 },
	             { header: '服务费', binding: 'svc_charge',width:100 },
	             { header: '时间', binding: 'oper_time',width:100 },
	             { header: '摘要', binding: 'ext_name',width:100 },
	             { header: '房号', binding: 'room_id',width:70 },
	             { header: '备注', binding: 'notes',width:100 }
     	 ],
     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 selectionMode:'ListBox',
    }); 
    //改单账目
 	customAccountsGrid =  new wijmo.grid.FlexGrid('#customAccountsGrid', {
   	    autoGenerateColumns: false,
  	    columns: [
  	         { header: 'ID', binding: 'ID',width:50,visible:false}, 
             { header: '序号', binding: 'sortNum',width:50 }, 
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
             { header: 'ID号', binding: 'ID',width:100 },
             { header: '结账号', binding: 'pay_num',width:100 }
     	 ],
     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 selectionMode:'ListBox',
    });
 	LeavingHotelGrid = new wijmo.grid.FlexGrid('#LeavingHotelGrid', {
     	  autoGenerateColumns: false,
    	  columns: [
           { header: '中文名', binding: 'gst_namec' ,width:'*'},
           { header: '英文名', binding: 'gst_namee' ,width:'*'}, 
           { header: '房号', binding: 'room_id',width:50}, 
           { header: '付款人', binding: 'paymanFlag',width:'*'},
           { header: '摘要', binding: 'chkExt', width:'*' },
           { header: '离店日期', binding: 'leave_date',width:'*',format:'yyyy-MM-dd',width:'*' },
           { header: '登记日期', binding: 'chk_time',width:'*' }
       	],
        //itemsSource: view,
        headersVisibility:wijmo.grid.HeadersVisibility.Column,
        selectionMode:'None',
        isReadOnly:true,
        deferResizing:true
    });
 	hyUnitDataBind();
 	hyUnitDataBindNoguest();
	hyUnitDataBindGuest();
 	hyUnitDataBindGroup();
	$("input[name=radioAccount][value=1]").attr('checked','checked');
	$("input[name=radioType][value=A]").attr('checked','checked');
	$("input[name=disturb][value=N]").attr('checked','checked');
});
function searchTransferGrid(){
	var type = $("input[name=transferRadio_1]:checked").val();
	loadTransferGrid(type);
}
/**
 * A帐点击事件
 * @param obj
 */
function consumeClick(obj){
	var tag = $(obj).attr('tag');
	$(obj).css("background",tag=='false'?'green':'white');
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
 * 详情分账设置双击事件
 */
function consumeDblclick(obj){
	var pObj = $(obj).parent();
	if($(pObj).attr('id')=='consumeA_ID'){
		$("#consumeB_ID").append(obj);
	}else{
		$("#consumeA_ID").append(obj);
	}
}
/**
 * 账目分账设置向右事件
 */
function accountConsumeToRight(){
	$("#account_consumeAID li").each(function(index,item){
		if($(item).attr('tag')=='true'){
			consumeClick(item);
			$("#account_consumeBID").append(item);
		}
	});
}
/**
 * 账目分账设置向左事件
 */
function accountConsumeToLeft(){
	$("#account_consumeBID li").each(function(index,item){
		if($(item).attr('tag')=='true'){
			consumeClick(item);
			$("#account_consumeAID").append(item);
		}
	});
}
/**
 * 详情分账设置双击事件
 */
function accountConsumeDblclick(obj){
	var pObj = $(obj).parent();
	if($(pObj).attr('id')=='account_consumeAID'){
		$("#account_consumeBID").append(obj);
	}else{
		$("#account_consumeAID").append(obj);
	}
}
/**
 * 详情分账设置确定按钮
 */
function consumeSubmit(){
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
	}
	$(items).each(function(index,item){
		ids+=item.id+",";
	});
	var ifBate = document.getElementById("if_bate").checked?'1':'0';
	$.ajax({
		url:'/guest/addBpaied.do',
		type:'post',
		dataType:'json',
		cache:false,
		data:{checkId:$("#checkId").val(),cons:ids.substring(0, ids.length-1),beginDate:$("#split_startDate").val(),endDate:$("#split_endDate").val(),operId:'1',updateTimes:1,ifBate:ifBate},
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				$(".alertDivBg2").fadeOut();
				$("#splitInfoDiv").fadeOut();
			}else{
				altEerrMsg(data.msg);
			}
		}
	});
	if(items.length>0){
		$("#accountSetId").prop('checked','checked');
	}else{
		$("#accountSetId").removeAttr('checked');
	}
}

/**
 * 账目分账设置确定按钮
 */
function accountConsumeSubmit(){
	var items = $("#account_consumeBID li");
	var ids='';
	if(items.length>0){
		if($("#acc_startDate").val()==""){
			altWaringMsg('请选择起始日期');
			return;
		}
		if($("#acc_endDate").val()==""){
			altWaringMsg('请选择终止日期');
			return;
		}
	}
	$(items).each(function(index,item){
		ids+=item.id+",";
	});
	$.ajax({
		url:'/guest/addBpaied.do',
		type:'post',
		dataType:'json',
		cache:false,
		data:{checkId:$("#account_namec").val(),cons:ids.substring(0, ids.length-1),beginDate:$("#acc_startDate").val(),endDate:$("#acc_endDate").val(),operId:'1',updateTimes:1},
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				$(".alertDivBg2").fadeOut();
				$("#guestSplitInfoDiv").fadeOut();
			}else{
				altEerrMsg(data.msg);
			}
		}
	});
}

/**
 * 账目分账取消
 */
function accountConsumeCancle(){
	$("#guestSplitInfo").click();
}

/**
 * 账目分账退出
 */
function accountConsumeQuit(){
	$(".alertDivBg2").fadeOut();
	$("#guestSplitInfoDiv").fadeOut();
}

/**
 * 入住类型radio点击
 */
function timeCheck(){
	$("#reach_date").val(new Date().format('yyyy-MM-dd'));
	$("#leave_date").val(new Date().format('yyyy-MM-dd'));
}

/**
 * 入住类型radio点击
 */
function noTimeCheck(){
	$("#reach_date").val(reachDate);
	$("#leave_date").val(leaveDate);
}


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
    			$("#otherCountry").append("<option value='"+$.trim(list[i].codeId)+"'>"+list[i].codeNamec+"</option>");
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
 * grid
 * @param jsonObj
 */
//		function setGridData(jsonObj) {
//			for ( var i = 0; i < jsonObj.length; i++) {
//				jsonObj[i].reach_date = new Date(jsonObj[i].reach_date);
//				jsonObj[i].leave_date = new Date(jsonObj[i].leave_date);
//				jsonObj[i].chk_stat = jsonObj[i].chk_stat == 'I' ? '入住'
//						: (jsonObj[i].chk_stat == 'O' ? '离店' : '');
//			}
//		}

/**
 * 查询
 * @param code
 */
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
				altEerrMsg("请输入查询条件");
				return;
			}
		}
	}
	$.ajax({
		url : '/guest/searchGuest.do',
		type : 'POST',
		data : formData,
		cache : false,
		success : function(data, textStatus, jqXHR) {
			var jsonObj = JSON.parse(data);
  			//setGridData(jsonObj);
			var view = new wijmo.collections.CollectionView(jsonObj);
			grid.itemsSource = view;
			grid.refresh();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}

//条件清空
function doClear() {
	$("#searchForm").resetForm();
}


/**
 * 新增客人按钮事件
 */
function addGuest(){
	if(isAdd || isEdit) return;
	var cv = guestGrid.itemsSource;
    var data = cv.items;
    data.push({
      	 sortNum: maxNum+1,
         gst_namec: "unknown",
         room_id:roomTag,
    	 sexname: "男",
     	 chkExt: "同住",
         price: "",
    	 chkStat: "在住",
   		 check_id: "",
   		 chk_ext:'0',
   		 chk_stat:'I'
    });
    cv.refresh();
    maxNum = maxNum+1;
    $("#payman_flag").attr('checked',false);
    $("#payman_flag").prop('disabled',false);
    //$("#reach_date").val(new Date().format('yyyy-MM-dd'));
    $("#reachDate").val(new Date().format('yyyy-MM-dd'));
	$("#confirm").addClass("disabledAButton");
	$("#giveup").addClass("disabledAButton");
    cv.moveCurrentToLast();
    $.ajax({
        url:'/guest/getHotelDate.do',
        type:'GET',
        dataType:'json',
        cache:true,
        data:{},
        async:false,//syncronized
        success:function(data,textStatus,jqXHR){
     	   var response=data;
     	   if(response.success){
     		   var hotleDate=response.attributes.hotleDate;
     		   var hotelDateStr = response.attributes.hotelDateStr;
     		   $("#reach_date").val(hotelDateStr);
     	   }
        },
        
     });
    isAdd = true;
    guestGrid.onSelectionChanging = function(e){
		return false;
	};
}
/**
 * 验证添加、修改表单
 * */
function formValidate(){
	var errors = [];
	var conf = {
			  onError : function($form) {
			      alert('验证表单 '+$form.attr('id')+' 失败!');
			    },
			  onSuccess : function($form) {
			      alert('表单 '+$form.attr('id')+' 验证通过!');
			      return false; // Will stop the submission of the form
			  },
			  onElementValidate : function(valid, $el, $form, errorMess) {
		    	  if( !valid ) {
				      errors.push({el: $el, error: errorMess});
				     }
		         console.log('Input ' +$el.attr('name')+ ' is ' + ( valid ? 'VALID':'NOT VALID') );
			   }
			};
	var lang = {
			requiredFields:"不能为空",
			badEmail:"不是正确的邮箱格式",
	};
	if(!$("#form1").isValid(lang, conf, false) ) {
		if(errors.length>0){
			altWaringMsg(errors[0].el.attr('label')+"  "+errors[0].error);
			return false;
		}
	   } else {
	     return true;
	}
}
$.formUtils.addValidator({
    name : 'isEmail',
    validatorFunction : function(value, $el, config, language, $form) {
    	var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return value==""?true:email.test(value);
    },
    errorMessage : '电子邮件格式不正确',
    errorMessageKey: 'badCustomEmail'
});
$.formUtils.addValidator({
    name : 'isHanZi',
    validatorFunction : function(value, $el, config, language, $form) {
    	var text = /[^\u4e00-\u9fa5]/;
        return !text.test(value);
    },
    errorMessage : '格式不正确',
    errorMessageKey: 'badCustomHanZi'
});
$.formUtils.addValidator({
    name : 'simpleDate',
    validatorFunction : function(value, $el, config, language, $form) {
    	var text = /^(\d{4})\-(\d{2})\-(\d{2})$/;
        return value==""?true:text.test(value);
    },
    errorMessage : '不是日期格式',
    errorMessageKey: 'badCustomSimpleDate'
});

/**
 * 确定
 */
function savaGuest(){
	//判断是否可以提交
	if($("#confirm").hasClass('disabledAButton')) return;
	if(!formValidate()){return false;}
	var cardKind = $("#crdkindId").val();
	if(cardKind=='006001'){
		var cardValue = $("#crdId").val();
		var text = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
		if(cardValue!="" && !text.test(cardValue)){
			altWaringMsg('身份证格式不正确');
			return;
		}
	}
	altInfMsg("是否保存客户信息？",function(){
		var formData =getFormJson(document.getElementById("form1"));
		formData.paymanFlag=document.getElementById("payman_flag").checked?"1":"0";
		formData.cityLedger=document.getElementById("city_ledger").checked;
		formData.housePay=document.getElementById("house_pay").checked ;
		formData.freeSvc=document.getElementById("free_svc").checked;
		formData.hideprice=document.getElementById("hideprice").checked;
		formData.ifFgst=document.getElementById("if_fgst").checked;
		formData.Alimit = $("#Alimit").val()==''?"0":$("#Alimit").val();
		formData.Blimit = $("#Blimit").val()==''?"0":$("#Blimit").val();
		formData.chkStat = 'I';
		formData.compId = $("input[name=compId][type=hidden]").val();
		formData.compType = $("input[name=compType][type=hidden]").val();
//		if(formData.paymanFlag=='1'){
//			var flag = true;
//			$.ajax({
//				url:'/guest/checkPaymanFlag.do',
//				data:{withId:$("input[name=withId]").val(),check_id:formData.checkId},
//				async:false,
//				dataType:'json',
//				success:function(data){
//					flag = data.success;
//				}
//			});
//			if(!flag){
//				altEerrMsg("当前付费人已存在");
//	    		return;
//			}
//		}
		var url;
		if(formData.checkId){
			url = "/guest/updateGuest.do";
		}else{
			var grpChkid = $("input[name=grpChkid]").val();
			if(grpChkid!=''){
				formData.gstFlag = 'G';
				formData.grpChkid = grpChkid;
			}else{
				formData.gstFlag = 'F';
			}
			url = "/guest/saveGuest.do";
		}
		var position = guestGrid.itemsSource.currentPosition;
		$.ajax({
			url : url,
			type : 'POST',
			data : formData, 
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(data.success){
					altSuccessMsg(data.msg);
					var item = guestGrid.itemsSource.currentItem;
					if(item.check_id==''){
						item.check_id = data.obj;
					}
					item.with_id = $("input[name=withId]").val();
					getDetail(item,position);
					initButton();
					isAdd = false;
					isEdit = false;
					$("#addGuest").removeClass('disabledAButton');
					$("#copyGuest").removeClass('disabledAButton');
					$("#scanCard").removeClass('disabledAButton');
					$("#sendRoomCard").removeClass('disabledAButton');
					$("#changeRoom").removeClass('disabledAButton');
					$("#guestDetailQuit").removeClass('disabledAButton');
				}else{
					altEerrMsg(data.msg);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
				
			}
		});
		guestGrid.onSelectionChanging = function(e){
			return true;
		};
	},function(){
		cancleAdd();
	});
}

/**
 * 放弃
 */
function cancleAdd(){
	if($("#giveup").hasClass('disabledAButton')) return;
	var view = guestGrid.itemsSource;
	if(isAdd){
		for(var i = maxNum-1;i>=tag;i--){
			view.removeAt(i);
		}
		view.refresh();
		maxNum = tag;
		$("#form1").resetForm();
		isAdd = false;
	}
	if(isEdit){
		var item = view.currentItem;
		$.ajax({
			url : '/guest/guestDocDetail.do',
			type : 'POST',
			cache : false,
			data:{check_id:item.check_id},
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				//表单加载
				loadForm(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		isEdit = false;
	}
	guestGrid.onSelectionChanging = function(e){
		return true;
	};
	$("#addGuest").removeClass('disabledAButton');
	$("#copyGuest").removeClass('disabledAButton');
	$("#scanCard").removeClass('disabledAButton');
	$("#sendRoomCard").removeClass('disabledAButton');
	$("#changeRoom").removeClass('disabledAButton');
	$("#guestDetailQuit").removeClass('disabledAButton');
	view.moveCurrentToFirst();
	initButton();
}

/**
 * 窗口关闭
 */
function closeWindow(){
 	$(".alertDivBg").fadeOut();
	$(".checkDetailsDiv").fadeOut();
	checkInTag = true;
	searchForm();
}
/**
 * 点击右上叉号退出
 */
function quitWindow(){
	checkInTag = true;
	cancleAdd();
	$(".alertDivBg").fadeOut();
	$(".checkDetailsDiv").fadeOut();
	searchForm();
}
/**
 * 加载客人信息
 * @param roomId
 */
function loadGuest(roomId){
	if(isEdit) return;
	if(roomId!=$("#room_id").val()){
		var item = guestGrid.itemsSource.currentItem;
		var withId = item.with_id;
		$.ajax({
			url:'/guest/list.do',
			data:{roomId:roomId,withId:withId},
			async:false,
			dataType:'json',
			success:function(data){
				var view = new wijmo.collections.CollectionView(data);
				guestGrid.itemsSource = view;
				var item = view.currentItem;
				tag = data.length;
				//住客详情加载
				$("#form1").resetForm();
				if(data && data.length>0){
					$.ajax({
						url : '/guest/guestDocDetail.do',
						type : 'POST',
						cache : false,
						data:{check_id:data[0].check_id},
						async:false,
						dataType:'json',
						success : function(data, textStatus, jqXHR) {
							//表单加载
							loadForm(data);
							viewChange(view, item, data);
						},
						error : function(jqXHR, textStatus, errorThrown) {
							altEerrMsg('查询异常');
						}
					});
				}
			}
		});
	}
	roomTag = roomId;
	maxNum = tag;
}

/**
 * 按账目中房间号加载信息
 */
function loadAccountInfoByRoom(roomId){
	if(roomId!=$("#account_roomid").val()){
		var withId = $("#account_namec option:selected").attr('data-withid');
		$.ajax({
			url:'/guest/list.do',
			data:{roomId:roomId,withId:withId},
			async:false,
			dataType:'json',
			success:function(data){
				$("#account_namec option").remove();
				$("#account_namee option").remove();
				loadAccountGuests(data);
				//住客详情加载
				loadAccountInfoByName("account_namec","account_namee");
			}
		});
	}
}

/**
 * 加载表单
 * @param data
 */
function loadForm(data){
	if(data.detail && JSON.stringify(data.detail)!='{}'){
		if(data.detail.gstOriId ){
			initGstOriAndIntype(data.detail.gstOriId);
		}
//		if(data.detail.gstOriId && data.detail.gstOriId.length>5){
//			data.detail.inType = data.detail.gstOriId.substring(0,5);
//		}
//		if(data.detail.gstOriId!='*')initCodes(data.detail.inType,data.detail.gstOriId);
		setForm('form1',data.detail);
		if(data.detail['chkStat']==='O')
		{
			$("#guestdetal_info_title").html("客单详情（离店）");
		}else
		{
			$("#guestdetal_info_title").html("客单详情（在住）");
		}
		$("#reach_date").val(new Date(data.detail.reachDate).format('yyyy-MM-dd'));
		$("#leave_date").val(new Date(data.detail.leaveDate).format('yyyy-MM-dd'));
		$("#reachDate").val(new Date(data.detail.reachDate).format('yyyy-MM-dd'));
		$("#leaveDate").val(new Date(data.detail.leaveDate).format('yyyy-MM-dd'));
		$("#guest_room_id").val(data.detail.roomId);
		$("#payman_flag").prop('checked',1==data.detail.paymanFlag?'checked':false);
		$("#payman_flag").prop('disabled',1==data.detail.paymanFlag?true:false);
		$("#city_ledger").prop('checked',data.detail.cityLedger?'checked':false);
		$("#house_pay").prop('checked',data.detail.housePay?'checked':false);
		$("#free_svc").prop('checked',data.detail.freeSvc?'checked':false);
		$("#hideprice").prop('checked',data.detail.hideprice?'checked':false);
		$("#if_fgst").prop('checked',data.detail.ifFgst?'checked':false);
		$("#remainA").val(changeTwoDecimal_f(Number(data.detail.borrow-data.detail.lent)));
		$("#remainB").val(changeTwoDecimal_f(Number(data.detail.Bborrow-data.detail.Blent)));
		$("#nowPrice").prop('disabled',(data.detail.chkExt == '0'));
		$("#discount").prop('disabled',(data.detail.chkExt == '0'));
		$("#theCompany").val(data.detail.namec);
		$("#company_id").val(data.detail.compId);
   	    $("#ta_type").val(data.detail.compType);
		_nowprice = data.detail.roomPrice;
	}
}

/**
 * 确定和放弃按钮初始化
 */
function initButton(){
	$("#confirm").addClass("disabledAButton");
	$("#giveup").addClass("disabledAButton");
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
 * 折扣价回车事件
 * @param e
 */
function calPress(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	if(keynum==13){
		var discount = $("#discount").val();
		if(!discount) return;
		var price = Number($("#normalPrice").val());
		var nowprice = price*Number(discount);
		$("#nowPrice").val(nowprice);
	}
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
		var obj = {};
		if(roomId && roomId.charAt(0)=='*'){
			roomId = roomId.substring(0);
			obj.chkStat = 'O';
		}
		obj.roomId = roomId;
		//$("#split").attr('disabled',true).css("color","grey");
		initButton();
		$.ajax({
			url:'/guest/list.do',
			data:obj,
			async:false,
			dataType:'json',
			success:function(data){
				var item = {};
				if(data && data.length>0){
					item.check_id = data[0].check_id;
					item.room_id = data[0].room_id;
					item.with_id = data[0].with_id;
					getDetail(item);
					loadConsume(true);
					$(".alertDivBg").fadeIn();
					$(".checkDetailsDiv").fadeIn();
					$(".userDetails").show();
					$(".userCatalog").hide();
					$(".userTab2").removeClass("point");
					$(".userTab1").addClass("point");
				}else{
					altWaringMsg("当前房间无客户信息");
				}
			}
		});
		//$(".userTab1").click();
	}
}

/**
 * 折扣价回车事件
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
		$("#roomList li").remove();
		$("#roomList2 li").remove();
		$("#account_namec option").remove();
		$("#account_namee option").remove();
		$.ajax({
			url:'/guest/searchByAccount.do',
			data:{bill_id:$("#searchaAccountId").val()},
			async:false,
			success:function(data){
				if(data){
					data = JSON.parse(data);
					loadAccountInfo(data.detail);
					loadAccountGuests(data.guests);
					loadAccountView(data.bills);
		            for(var i=0;i<data.rooms.length;i++){
						$("#roomList").append("<li><a href='javascript:;' onclick='loadGuest("+data.rooms[i].room_id+")'>"+data.rooms[i].room_id+"</a></li>");
						$("#roomList2").append("<li><a href='javascript:;' onclick='loadAccountInfoByRoom("+data.rooms[i].room_id+")'>"+data.rooms[i].room_id+"</a></li>");
		            }
		            $("input[name=radioAccount][value="+data.billType+"]").attr('checked','checked');
		            $(".alertDivBg").fadeIn();
					$(".checkDetailsDiv").fadeIn();
					$(".userDetails").hide();
					$(".userCatalog").show();
					$(".userTab1").removeClass("point");
					$(".userTab2").addClass("point");
				}else{
					altWaringMsg("当前帐号无客户信息");
				}
			},
			error:function(){
				altEerrMsg("查询异常");
			}
		});
		//$(".userTab2").click();
	}
}

/**
 * 列表切换事件
 * @param view
 * @param item
 * @param data
 */
function viewChange(view,item,data){
    view.currentChanged.addHandler(function (sender, args) {
		var citem = view.currentItem;
		if(citem.sortNum>tag){
			$("#form1").resetForm();
			var obj ={
				 checkId:null,
		         gstNamec: "unknown",
		         room_id:roomTag,
		    	 roomId: roomTag,
		     	 codeNamec: item.code_namec,
		     	 reachDate: new Date().format('yyyy-MM-dd'),
		     	 leaveDate:new Date(data.detail.leaveDate).format('yyyy-MM-dd'),
		     	 reach_date: new Date().format('yyyy-MM-dd'),
		     	 leave_date:new Date(data.detail.leaveDate).format('yyyy-MM-dd'),
		     	 Blimit:data.detail.Blimit,
		     	 Alimit:0
		    };
			setForm("form1",obj);
			$("#payman_flag").attr('checked',false);
			$("#city_ledger").attr('checked',false);
			$("#house_pay").attr('checked',true);
			$("#free_svc").attr('checked',true);
			$("#hideprice").attr('checked',false);
			$("#if_fgst").attr('checked',true);
			$("#confirm").removeClass("disabledAButton");
			$("#giveup").removeClass("disabledAButton");
		  //  $("#split").attr('disabled',true).css("color","grey");
		    $("#addGuest").addClass('disabledAButton');
		    $("#copyGuest").addClass('disabledAButton');
		    $("#sendRoomCard").addClass('disabledAButton');
		    $("#changeRoom").addClass('disabledAButton');
		    $("#guestDetailQuit").addClass('disabledAButton');
		    $("#scanCard").addClass('disabledAButton');
		    $("#nowPrice").attr('disabled',true);
		    $("#discount").attr('disabled',true);
		}else{
    		$.ajax({
				url : '/guest/guestDocDetail.do',
				type : 'POST',
				cache : false,
				data:{check_id:citem.check_id},
				dataType:'json',
				success : function(data, textStatus, jqXHR) {
					//表单加载
					loadForm(data);
					setForm('form2',data.detail);
					loadConsume(true);
				},
				error : function(jqXHR, textStatus, errorThrown) {
					altEerrMsg('查询异常');
				}
			});
    		initButton();
    		$("#addGuest").removeClass("disabledAButton");
    		$("#copyGuest").removeClass("disabledAButton");
    		$("#sendRoomCard").removeClass('disabledAButton');
		    $("#changeRoom").removeClass('disabledAButton');
		    $("#scanCard").removeClass('disabledAButton');
		    $("#guestDetailQuit").removeClass('disabledAButton');
		}
		
    });
}
/**
 * 复制客人
 */
function copyGuest() {
	if(isAdd || isEdit) return;
	 altInfMsg("确定要复制当前客人吗？",function(){
			var positon = guestGrid.collectionView.items.length;
			$.ajax({
				url : '/guest/copyGuest.do',
				type : 'POST',
				data : {checkId:$("#checkId").val()},
				dataType:'json',
				success : function(data, textStatus, jqXHR) {
					if(data.success){
						altSuccessMsg(data.msg);
						var item = {};
						item.check_id = data.obj.guestdoc.checkId;
						item.with_id = data.obj.guestdoc.withId;
						item.room_id = data.obj.guestdoc.roomId;
						getDetail(item);
					}else{
						altEerrMsg('复制失败');
					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					altEerrMsg('查询异常');
					
				}
			});
			$("#discount").val('');
	 },function(){
	 });
}

/**
 * 修改其他信息
 */
function otherConfirm(){
	var formData = getFormJson(document.getElementById("form2"));
	formData.checkId = $("#checkId").val();
	$.ajax({
		url : '/guest/updateOther.do',
		type : 'POST',
		data : formData,
		success : function(data, textStatus, jqXHR) {
			altInfMsg(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}
/**
 * 其他信息-放弃
 */
function otherGiveUp(){
	$.ajax({
		url : '/guest/guestDocDetail.do',
		type : 'POST',
		cache : false,
		data:{check_id:$("#checkId").val()},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			//表单加载
			setForm('form2', data.detail);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}
/**
 * 其他信息页面退出
 */
function otherQuit(){
	$("#sex").val($("#otherSex").val());
	$("#gstNamee").val($("#otherNamee").val());
	$("#country").val($("#otherCountry").val());
	$("#crdkindId").val($("#otherCrdkindId").val());
	$("#crdId").val($("#otherCrdId").val());
	$("#otherDiv").fadeOut();
	$(".alertDivBg2").fadeOut();
}
/**
 * 房价列表修改
 */
function priceConfirm(){
	var inputs = $("#priceTable input[type=number]");
	var arr = [];
	for(var i=0;i<inputs.length;i++){
		if($(inputs[i]).val()){
			arr.push({id:inputs[i].id.split('_')[1],value:$(inputs[i]).val()});
		}
	}
	if(arr.length==0)return;
	$.ajax({
		url:'/guest/updatePrice.do',
		type:'post',
		data:{data:JSON.stringify(arr)},
		async:'false',
		dataType:'json',
		success:function(data){
			altInfMsg(data.msg);
		}
		
	});
}

/**
 * 获取详情
 * @param item
 */
function getDetail(item,position,type){
	var url = '';
	if(checkInTag){
		if(type=='fit'){
			url = '/guest/getGuestDocDetailIn.do';
		}else if(type=='group'){
			url = '/group/grpCheckIn.do';
		}
	}else{
		url = '/guest/guestDocDetail.do';
	}
	$.ajax({
		url :url,
		type : 'POST',
		cache : false,
		data:{check_id:item.check_id,room_id:item.room_id,with_id:item.with_id,roomIds:JSON.stringify(item.roomIds),bookRoomId:item.bookRoomId},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			//表单加载
			loadForm(data);
			//grid加载
			guestGrid.onSelectionChanging = function(e){
				return true;
			};
			if(!position){
				if(data.guests){
					for(var i=0;i<data.guests.length;i++){
						if(data.guests[i].check_id == item.check_id || data.guests[i].check_id == data.checkId){
							position = i;
							break;
						}
					}
				}
			}
		    var view = new wijmo.collections.CollectionView(data.guests);
		    guestGrid.itemsSource = view;
		    view.moveCurrentToPosition(position);
		    maxNum = data.guests.length;
		    tag=maxNum;
		    reachDate = new Date(data.detail.reachDate).format('yyyy-MM-dd');
		    leaveDate = new Date(data.detail.leaveDate).format('yyyy-MM-dd');
		    _nowprice = data.detail.roomPrice;
		    //grid行切换事件
		    viewChange(view, item, data);
		    $("#roomList li").remove();
		    $("#roomList2 li").remove();
            //加载房间列表
            for(var i=0;i<data.rooms.length;i++){
				$("#roomList").append("<li><a href='javascript:;' onclick='loadGuest("+data.rooms[i].room_id+")'>"+data.rooms[i].room_id+"</a></li>");
				$("#roomList2").append("<li><a href='javascript:;' onclick='loadAccountInfoByRoom("+data.rooms[i].room_id+")'>"+data.rooms[i].room_id+"</a></li>");
            }
            $("#roomList li").each(function(){
            	if(item.room_id == $(this).text()){
            		$(this).addClass('roomListLi');
            	}
            });
            $("#roomList li").click(function(){
            	$(this).addClass("roomListLi");
    		    $(this).siblings().removeClass("roomListLi");
    	    });
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}
/**
 * 详情分账设置加载
 */
function loadConsume(checked){
	$("#consumeA_ID li").remove();
	$("#consumeB_ID li").remove();
	$.ajax({
		url:'/guest/consumes.do',
		data:{checkId:$("#guestCheckId").val()},
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			if(data.consumes){
				for(var i = 0;i<data.consumes.length;i++){
					$("#consumeA_ID").append("<li id='"+data.consumes[i].codeId+"' tag='false' ondblclick='consumeDblclick(this)' onclick='consumeClick(this)'>"+data.consumes[i].codeNamec+"</li>");
				}
			}
			if(data.blist){
				for(var i = 0;i<data.blist.length;i++){
					$("#consumeB_ID").append("<li id='"+data.blist[i].codeId+"' tag='false' ondblclick='consumeDblclick(this)' onclick='consumeClick(this)'>"+data.blist[i].codeNamec+"</li>");
				}
			}
			$("#split_startDate").val(data.bpaied.beginDate);
			$("#split_endDate").val(data.bpaied.endDate);
			document.getElementById("if_bate").checked=data.ifBate;
			if(checked){
				if(data.blist.length)$("#accountSetId").prop('checked',true);
				else $("#accountSetId").removeAttr('checked');
			}
		}
	});
	
}

/**
 * 账目分账设置加载
 */
function loadAccountConsume(checked){
	$("#account_consumeAID li").remove();
	$("#account_consumeBID li").remove();
	$.ajax({
		url:'/guest/consumes.do',
		data:{checkId:$("#account_namec").val()},
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			if(data.consumes){
				for(var i = 0;i<data.consumes.length;i++){
					$("#account_consumeAID").append("<li id='"+data.consumes[i].codeId+"' tag='false' ondblclick='accountConsumeDblclick(this)' onclick='consumeClick(this)'>"+data.consumes[i].codeNamec+"</li>");
				}
			}
			if(data.blist){
				for(var i = 0;i<data.blist.length;i++){
					$("#account_consumeBID").append("<li id='"+data.blist[i].codeId+"' tag='false' ondblclick='accountConsumeDblclick(this)' onclick='consumeClick(this)'>"+data.blist[i].codeNamec+"</li>");
				}
			}
			$("#acc_startDate").val(data.bpaied.beginDate);
			$("#acc_endDate").val(data.bpaied.endDate);
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
 * 房价列表取消
 */
function priceCancle(){
	$(".pricesList").fadeOut();
	$(".alertDivBg2").fadeOut();
}

/**
 * 中文回车事件
 * @param e
 */
function namePress(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	var checked = document.getElementById("if_fgst").checked;
	if(keynum==13 && checked){
	}
}

/**
 * 根据身份证号获取生日
 * @param iIdNo
 */
function getBirthdatByIdNo(iIdNo) {
	if ($("#crdkindId").val() != '006001')
		return;
	var tmpStr = "";
	var idDate = "";
	var tmpInt = 0;
	var strReturn = "";

	iIdNo = $.trim(iIdNo);
	if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
		strReturn = "输入的身份证号位数错误";
		altEerrMsg(strReturn);
		return;
	}
	if (iIdNo.length == 15) {
		tmpStr = iIdNo.substring(6, 12);
		tmpStr = "19" + tmpStr;
		tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-"
				+ tmpStr.substring(6);

	} else {
		tmpStr = iIdNo.substring(6, 14);
		tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-"
				+ tmpStr.substring(6);
	}
	$("#birthday").val(tmpStr);
}

/**
 * 账目中选择中文和英文名事件
 * @param fid
 * @param sid
 */
function loadAccountInfoByName(fid,sid){
	console.debug('fid = ' + fid + ",sid =" + sid);
	var check_id=$("#"+fid).val();
	var bill_type = $("input[name=radioAccount]:checked").val();
	var option = $("#"+fid).find("option:selected");
	var bill_id = (bill_type=='1' ? (option.attr('data-billaid')) : (option.attr('data-billbid')));
	var show_type = $('input[name=radioType]:checked').val();
	var cons_id = $('#showTypeId').val();
	var okFlag = $('input[name=disturb]:checked').val();
	$.ajax({
		url : '/guest/guestAccountInfo.do',
		type : 'POST',
		cache : false,
		data:{check_id:check_id,billId:bill_id,billType:bill_type,showType:show_type,okFlag:okFlag},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			//表单加载
			loadAccountInfo(data.detail);
			loadAccountView(data.bills);
			$("#"+sid).val(check_id);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}

/**
 * 账目页详情
 * @param item
 */
function getAccountDetail(item){
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
		data:{checkId:item.check_id,roomId:item.room_id,withId:item.with_id,billType:billId,billType:bill_type,showType:show_type,okFlag:okFlag},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			loadAccountInfo(data.detail);
			loadAccountGuests(data.guests,item);
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
			$("#account_namec").append('<option data-withid="'+data[i].with_id+'" data-billaid="'+data[i].billa_id+'" data-billbid="'+data[i].billb_id+'" value="'+data[i].check_id+'">'+data[i].gst_namec+'</option>');
			$("#account_namee").append('<option data-withid="'+data[i].with_id+'" data-billaid="'+data[i].billa_id+'" data-billbid="'+data[i].billb_id+'" value="'+data[i].check_id+'">'+data[i].gst_namee+'</option>');
		}
	}
	if(item){
		$("#account_namec").val(item.check_id);
		$("#account_namee").val(item.check_id);
	}
}

/**
 * 账目列表加载
 * @param data
 */
function loadAccountView(data){
	var view = new wijmo.collections.CollectionView(data);
	accountGrid.itemsSource = view;
}



function changeTwoDecimal_f(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		//alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	var f_x = Math.round(x * 100) / 100;
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
}

/**
 * 账目列表加载
 */
function reloadAccount(){
	var billId;
	var billType = $("input[name=radioAccount]:checked").val();
	var showType = $('input[name=radioType]:checked').val();
	var okFlag = $('input[name=disturb]:checked').val();
	var startDate = $("#account_startDate").val();
	var endDate = $("#account_endDate").val();
	if(billType==1){
		billId = $("#account_namec option:selected").attr('data-billaid');
	}else{
		billId = $("#account_namec option:selected").attr('data-billbid');
	}
	$.ajax({
		url:'/guest/loadAccounts.do',
		type:'post',
		async:false,
		dataType:'json',
		data:{billType:billType,billId:billId,showType:showType,okFlag:okFlag,startDate:startDate,endDate:endDate},
		success:function(data){
			loadAccountView(data);
		}
	});
}


/**
 * 结束时期回车事件
 * @param e
 */
function searchAccount(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	if(keynum==13){
		var startDate = $("#account_startDate").val();
		var endDate = $("#account_endDate").val();
		if(!startDate){
			altEerrMsg("请输入开始日期");
			return;
		}
		if(!endDate){
			altEerrMsg("请输入结束日期");
			return;
		}
		reloadAccount();
	}
}

/**
 * 入账页面加载消费点
 * @param checkId
 */
function loadAccountConsumes(checkId){
	var bill_type = $("input[name=effective]:checked").val();
	$("#accountConsume option").remove();
	$.ajax({
		url:'/guest/consumes.do',
		data:{checkId:checkId},
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			if(bill_type==1){
				for(var i=0;i<data.consumes.length;i++){
					$("#accountConsume").append('<option value="'+data.consumes[i].codeId+'">'+data.consumes[i].codeNamec+'</option>');
				}
			}else{
				for(var i=0;i<data.blist.length;i++){
					$("#accountConsume").append('<option value="'+data.blist[i].codeId+'">'+data.blist[i].codeNamec+'</option>');
				}
			}
		}
	});
}

/**
 * 入账保存
 */
function billConfirm(){
	var data = getFormJson(document.getElementById("accountForm"));
	data.billId = $("#accountForm input[name=billId]").val();
	data.type = $("#accountForm input[name=list_01]:checked").val();
	data.billType = $("#accountForm input[name=effective]:checked").val();
	data.roomId = $("#accountForm input[name=roomId]").val();
 	$.ajax({
		url:'/guest/accountForSaveBill.do',
		data:data,
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			altInfMsg(data.msg);
			reloadAccount();
		}
	});
}

/**
 * 入账退出
 */
function billQuit(){
	$(".alertDivBg2").fadeOut();
	$("#accountedForDiv").fadeOut();
}
/**
 * 半全日退出
 */
function forLongBillQuit(){
	$(".alertDivBg2").fadeOut();
	$(".accountedForDiv").fadeOut();
}
/**
 * 半全日保存
 */
function forLongbillConfirm(){
	var data = getFormJson(document.getElementById("forLongForm"));
	data.billId = $("#forLongForm input[name=billId]").val();
	data.type = $("input[name=list_02]:checked").val();
	data.billType = $("input[name=effective_forlong]:checked").val();
	data.roomId = $("#forLongForm input[name=roomId]").val();
	data.consId = $("#forLongForm #forLongConsume").val();
 	$.ajax({
		url:'/guest/accountForSaveBill.do',
		data:data,
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			altInfMsg(data.msg);
		}
	});
}

/**
 * 入账页面选择消费点切换A,B账
 * @param checkId
 */
function changePaied(){
	var checkId = $("#account_namec").val();
	var consId = $("#accountConsume").val();
	$.ajax({
		url:'/guest/getAorBbyConsumeId.do',
		data:{consId:consId,checkId:checkId},
		dataType:'json',
		type : 'get',
		success:function(data){
			if(data.obj=='A'){
				$("input[name=effective][value=1]").prop('checked',true);
				$("#accountBillId").val($("#incomeNamec option:selected").attr('data-billaid') );
		    	$("#accountlimit").val($("#incomeNamec option:selected").attr('data-alimit') );
			}else{
				$("input[name=effective][value=2]").prop('checked',true);
				$("#accountBillId").val($("#incomeNamec option:selected").attr('data-billbid') );
		    	$("#accountlimit").val($("#incomeNamec option:selected").attr('data-blimit') );
			}
		}
	});
}

/**
 * 入账本位币回车事件，计算服务费
 */
function calServeMoney(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	if(keynum==13){
		var isserve = document.getElementById("isserve");
		if(isserve.checked){
			var money = $("#balance").val();
			var rate = $("#accountConsume option:selected").attr('data-svcrate');
			$("#balance").val(changeTwoDecimal_f($("#balance").val()));
			if(rate!='0'){
				var serveMoney = changeTwoDecimal_f(money*rate/100);
				$("#serveMoney").val(serveMoney);
			}
		}
	}
}

/**
 * 半全日租本位币回车事件，计算服务费
 */
function calServeMoneyForLong(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	if(keynum==13){
		var isserve = document.getElementById("forLongIsserve");
		if(isserve.checked){
			var money = $("#forLongBalance").val();
			var rate = $("#forLongConsume option:selected").attr('data-svcrate');
			$("#forLongBalance").val(changeTwoDecimal_f($("#forLongBalance").val()));
			if(rate!='0'){
				var serveMoney = changeTwoDecimal_f(money*rate/100);
				$("#forLongServeMoney").val(serveMoney);
			}
		}
	}
}
/**
 * 押金保存
 */
function depositSave(){
	var data = getFormJson(document.getElementById("depositForm"));
	data.billType = $("input[name=radioAccount]:checked").val();
	if(data.billType=='1'){
		data.billId = $("#account_billaid").val();
	}else{
		data.billId = $("#account_billbid").val();
	}
	data.setlId = $("#depositForm select[name=setlId]").val();
	data.roomId = $("#account_roomid").val();
 	$.ajax({
		url:'/guest/depositSaveBill.do',
		data:data,
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				reloadAccount();
			}else{
				altEerrMsg('保存失败');
			}
		
		}
	});
}
/**
 * 押金放弃
 */
function depositGiveUp(){
	$("#depositForm").resetForm();
}
/**
 * 押金退出
 */
function depositQuit(){
	$(".alertDivBg2").fadeOut();
	$("#depositDiv").fadeOut();
	depositGiveUp();
}

/**
 * 收款保存
 */
function receivaSave(){
	var data = getFormJson(document.getElementById("receivablesForm"));
	data.billType = $("input[name=radioAccount]:checked").val();
	if(data.billType=='1'){
		data.billId = $("#account_billaid").val();
	}else{
		data.billId = $("#account_billbid").val();
	}
	data.setlId = $("#receivablesForm select[name=setlId]").val();
	data.roomId = $("#account_roomid").val();
 	$.ajax({
		url:'/guest/depositSaveBill.do',
		data:data,
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				reloadAccount();
			}else{
				altEerrMsg('保存失败');
			}
		}
	});
}

/**
 * 收款退出
 */
function receivaQuit(){
	$(".alertDivBg2").fadeOut();
	$("#receivablesDiv").fadeOut();
	receivaGiveUp();
}

/**
 * 收款放弃
 */
function receivaGiveUp(){
	$("#receivablesForm").resetForm();
}

/**
 * 半/全日租退出
 */
function forLongBillQuit(){
	$(".alertDivBg2").fadeOut();
	$("#forALongTimeDiv").fadeOut();
}
/**
 * 刷新
 */
function accountRefresh(){
	if(guestGrid.itemsSource){
		item = guestGrid.itemsSource.currentItem;
	}
	if(item){
		getAccountDetail(item);
	}
}

/*
 * 结账计算金额
 */
function checkOutMoney(e){
	var keynum;
	if (window.event){ // IE
		keynum = e.keyCode;
	}else if (e.which){ // Netscape/Firefox/Opera
		keynum = e.which;
	}
	if(keynum==13){
		var cv = checkOutGrid.itemsSource;
	    var data = cv.items;
		var billType = $("input[name=radioAccount]:checked").val();
		var billId;
		if(billType=='1'){
			billId = $("#account_billaid").val();
		}else{
			billId = $("#account_billbid").val();
		}
		data.push({
			setlName:$("#checkOutSetlId option:selected").text(),
			balance:$("#checkOutRemain").val(),
			foreignm:'',
			moneykindName:$("#checkOutmoneyKind option:selected").text(),
			notes:$("#checkOutNotes").val(),
			moneykind:$("#checkOutmoneyKind").val(),
			setlId:$("#checkOutSetlId").val(),
			billId:billId,
			billType:billType,
			roomId:$("#account_roomid").val()
		});
		cv.refresh();
		var money = 0;
		for(var i=0;i<data.length;i++){
			money+=parseFloat(data[i].balance);
		}
		$("#checkOutPaidMoney").val(changeTwoDecimal_f(money));
		$("#checkOutHadPaid").val(changeTwoDecimal_f(money));
		$("#checkOutRemain").val('');
		$("#checkOutBalance").val(changeTwoDecimal_f(parseFloat($("#checkOutSumBalance").val())-parseFloat($("#checkOutPaidMoney").val())));
		if(Math.round($("#checkOutBalance").val())==0){
			$("#checkOutRemain").prop('disabled','disabled');
		}
	}
}

/**
 * 结账确定
 */
function checkOutSubmit(){
	var money = parseFloat($("#checkOutRemain").val());
	if(money>0){
		altEerrMsg('当前账目未结清');
		return;
	}
	var checkId = $("#account_namec").val();
	var billType = $("input[name=radioAccount]:checked").val();
	var billId;
	if(billType=='1'){
		billId = $("#account_billaid").val();
	}else{
		billId = $("#account_billbid").val();
	}
	var roomId = $("#account_roomid").val();
	//选择的账目信息
	var ids = [];
	var length = accountGrid.rows.length;
	if(length==0) return;
	for(var i=0;i<accountGrid.rows.length;i++){
		var row = accountGrid.rows[i];
		if(row.isSelected){
			ids.push(row.dataItem.ID);
		}
	}
	var oddMoney = $("#checkOutOddment").val();
	//结账的账目信息
	var rows = checkOutGrid.rows;
	var payArr = [];
	for(var i=0;i<rows.length;i++){
		payArr.push(rows[i].dataItem);
	}
	$.ajax({
		url:'/guest/checkOutAccount.do',
		type:'get',
		data:{checkId:checkId,ids:ids.join(','),bills:JSON.stringify(payArr),oddMoney:oddMoney,billId:billId,billType:billType,roomId:roomId},
		dataType:'json',
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				reloadAccount();
			}else{
				altEerrMsg('保存失败');
			}
		}
	});
	//alert('结账成功');
}

/**
 * 结账放弃
 */
function checkOutGiveUp(){
	$("#checkOut").click();
	$("#checkOutRemain").removeAttr('disabled');
	var data = [];
	var view = new wijmo.collections.CollectionView(data);
	checkOutGrid.itemsSource = view;
	checkOutGrid.itemsSource.refresh();
}
/**
 * 预授权保存
 */
function savePreAuth(){
	var data = getFormJson(document.getElementById("addPreAwardForm"));
	var billType = $("input[name=radioAccount]:checked").val();
	if(billType=='1'){
		data.billId = $("#account_billaid").val();
	}else{
		data.billId = $("#account_billbid").val();
	}
	var checkId = $("#account_namec").val();
	data.checkId = checkId;
	$.ajax({
		url:'/guest/savePreAuth.do',
		data:data,
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				reloadAuthGrid();
			}else{
				altEerrMsg('保存失败');
			}
		}
	});
}

/**
 * 新增预授权-放弃
 */
function giveUpPreAuth(){
	$("#addPreAwardForm").resetForm();
}

/**
 * 新增预授权-退出
 */
function quitPreAuth(){
	$(".alertDivBg3").fadeOut();
	$(".addPreAwardDiv").fadeOut();
	giveUpPreAuth();
}

/**
 * 完成预授权
 */
function finishAuth(){
	var ids = [];
	for(var i=0;i<preAuthorizationGrid.rows.length;i++){
		var row = preAuthorizationGrid.rows[i];
		if(row.isSelected){
			ids.push(row.dataItem.ID);
		}
	}
	$.ajax({
		url:'/guest/changeAuthStat.do',
		data:{ids:ids.join(','),authStat:1},
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				$("input[name=preAuthRadio][value=1]").prop('checked',true);
				reloadAuthGrid();
			}else{
				altEerrMsg('保存失败');
			}
		}
	});
}

/**
 * 取消预授权
 */
function cancleAuthStat(){
	var ids = [];
	for(var i=0;i<preAuthorizationGrid.rows.length;i++){
		var row = preAuthorizationGrid.rows[i];
		if(row.isSelected){
			ids.push(row.dataItem.ID);
		}
	}
	if(ids.length==0){
		altWaringMsg("请选择需取消的预授权");
		return;
	}
	$.ajax({
		url:'/guest/changeAuthStat.do',
		data:{ids:ids.join(','),authStat:0},
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				$("input[name=preAuthRadio][value=1]").prop('checked',true);
				reloadAuthGrid();
			}else{
				altEerrMsg('取消失败');
			}
		}
	});
}

/**
 * 取消预授权
 */
function cancleAuth(){
	var ids = [];
	for(var i=0;i<preAuthorizationGrid.rows.length;i++){
		var row = preAuthorizationGrid.rows[i];
		if(row.isSelected){
			ids.push(row.dataItem.ID);
		}
	}
	if(ids.length==0){
		altEerrMsg("请选择需取消的预授权");
		return;
	}
	$.ajax({
		url:'/guest/cancleAuth.do',
		data:{ids:ids.join(',')},
		type:'post',
		dataType:'json',
		async:false,
		success:function(data){
			altInfMsg(data.msg);
			reloadAuthGrid();
		}
	});
}

/**
 * 预授权退出
 */
function quitAuth(){
	$(".alertDivBg2").fadeOut();
	$("#preAuthorizationDiv").fadeOut();
}
/**
 * 预授权列表加载
 */
function reloadAuthGrid(){
	var checkId = $("#account_namec").val();
	var status = $("input[name=preAuthRadio]:checked").val();
	$.ajax({
		url:'/guest/getPreAuthorization.do',
		type:'get',
		data:{checkId:checkId,status:status},
		dataType:'json',
		success:function(data){
			if(data){
				var sum=0;
				for(var i=0;i<data.length;i++){
					sum+=parseFloat(data[i].balance);
				}
				$("#preAuthSumBalance").html(changeTwoDecimal_f(sum));
			}
			var view = new wijmo.collections.CollectionView(data);
			preAuthorizationGrid.itemsSource = view;
		}
	});
}

/**
 * 转账列表加载
 * @param type
 */
function loadTransferGrid(type){
	var url = '';
	var formData;
	switch (type) {
	case '1':
		url = '/guest/searchGuest.do';
		formData = getFormJson(document.getElementById("transferForm_1"));
		var chkStat = $("input[name=transferRadio_2]:checked").val();
		formData.chkStat = chkStat;
		formData.compId = formData.guest_compId;
		transferAccountGrid.columns.getColumn('namec').binding = 'gst_namec';
		transferAccountGrid.columns.getColumn('billId').binding = 'billa_id';
		break;
	case '2':
		url = '/guest/searchGroup.do',
		formData = getFormJson(document.getElementById("transferForm_2"));
		var chkStat = $("input[name=transferRadio_3]:checked").val();
		formData.chkStat = chkStat;
		formData.compId = formData.group_compId;
		transferAccountGrid.columns.getColumn('namec').binding = 'lead_namec';
		transferAccountGrid.columns.getColumn('billId').binding = 'bill_id';
		break;
	case '3':
		url = '/noguest/selectNoguestCond.do';
		formData = getFormJson(document.getElementById("transferForm_3"));
		formData.compId = formData.noguest_compId;
		transferAccountGrid.columns.getColumn('namec').binding = 'nogstName';
		transferAccountGrid.columns.getColumn('billId').binding = 'bill_id';
		break;
	default:
		break;
	}
	$.ajax({
		url : url,
		type : 'POST',
		data : formData,
		cache : false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			if(type=='3'){
				var array = [];
				for (var i = 0; i < data.length; i++) {
					array.push({
						 sortNum: i+1,
						 nogstName:data[i].nogst_name,
						 remainA:changeTwoDecimal_f(data[i].balance),
						 namec:data[i].namec,
						 check_id:data[i].nogst_id,
						 billId:data[i].bill_id
					});
				}
				data = array;
			}
			var view = new wijmo.collections.CollectionView(data);
			view.currentChanged.addHandler(function (sender, args){
				transferChange(view,type);
			});
			if(data && data.length>0){
				transferChange(view,type);
			}
			transferAccountGrid.itemsSource = view;
			transferAccountGrid.refresh();
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}
/**
 * 转账列表切换
 * @param view
 * @param type
 */
function transferChange(view,type){
	var citem = view.currentItem;
	if(type=='1'){
		$("#transferRoomId").val(citem.room_id );
		$("#transferBillId").val(citem.billa_id );
	}else if(type=='2'){
		$("#transferRoomId").val(citem.room_id );
		$("#transferBillId").val(citem.bill_id );
	}else if(type=='3'){
		$("#transferRoomId").val('');
		$("#transferBillId").val(citem.bill_id );
	}
}
/**
 * 转账列表清空条件
 */
function clearTransferForm(){
	$("#transferForm_1").resetForm();
	$("#transferForm_2").resetForm();
	$("#transferForm_3").resetForm();
}
/**
 * 转账确定页面topgrid加载
 * @param billType
 */
function loadTransferTopData(checkId,billId,billType){
	$.ajax({
		url : '/guest/guestAccountInfo.do',
		type : 'POST',
		cache : false,
		data:{check_id:checkId,billId:billId,billType:billType,showType:'A'},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			if(data.bills && data.bills.length>0){
				for(var i=0;i<data.bills.length;i++){
					data.bills[i].transfer = false;
					data.bills[i].top = true;
				}
			}
			var view = new wijmo.collections.CollectionView(data.bills);
			transferTopGrid.itemsSource = view;
			if(data.detail){
				$("#transferTopNamec").val(data.detail.gstNamec);
				$("#transferTopNamee").val(data.detail.gstNamee);
				$("#transferTopRoomId").val(data.detail.roomId);
				$("#transferTopNotice").val(data.detail.notice);
				if(billType=='1'){
					$("#transferTopBillId").val(data.detail.billaId);
					$("#transferTopBorrow").val(data.detail.borrow);
					$("#transferTopLent").val(data.detail.lent);
					$("#transferTopRemain").val(changeTwoDecimal_f(parseFloat(data.detail.borrow)-parseFloat(data.detail.lent)));
				}else{
					$("#transferTopBillId").val(data.detail.billbId);
					$("#transferTopBorrow").val(data.detail.Bborrow);
					$("#transferTopLent").val(data.detail.Blent);
					$("#transferTopRemain").val(changeTwoDecimal_f(parseFloat(data.detail.Bborrow)-parseFloat(data.detail.Blent)));
				}
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}


/**
 * 
 * @param type（个人，团体，非住客）
 * @param billType（A账,B账）
 */
function loadTransferBottomData(type){
	var item = transferAccountGrid.itemsSource.currentItem;
	switch (type) {
	case '1':
		var checkId = item.check_id;
		var billType = $("input[name=transferBottomRadio]:checked").val();
		var billId =(billType=='1' ? item.billa_id : item.billb_id);
		$.ajax({
			url : '/guest/guestAccountInfo.do',
			type : 'POST',
			cache : false,
			data:{check_id:checkId,billId:billId,billType:billType,showType:'A'},
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(data.bills && data.bills.length>0){
					for(var i=0;i<data.bills.length;i++){
						data.bills[i].transfer = false;
						data.bills[i].top = false;
					}
				}
				var view = new wijmo.collections.CollectionView(data.bills);
				transferBottomGrid.itemsSource = view;
				if(data.detail){
					$("#transferBottomNamec").val(data.detail.gstNamec);
					$("#transferBottomNamee").val(data.detail.gstNamee);
					$("#transferBottomRoomId").val(data.detail.roomId);
					$("#transferBottomNotice").val(data.detail.notice);
					if(billType=='1'){
						$("#transferBottomBillId").val(data.detail.billaId);
						$("#transferBottomBorrow").val(data.detail.borrow);
						$("#transferBottomLent").val(data.detail.lent);
						$("#transferBottomRemain").val(changeTwoDecimal_f(parseFloat(data.detail.borrow)-parseFloat(data.detail.lent)));
					}else{
						$("#transferBottomBillId").val(data.detail.billbId);
						$("#transferBottomBorrow").val(data.detail.Bborrow);
						$("#transferBottomLent").val(data.detail.Blent);
						$("#transferBottomRemain").val(changeTwoDecimal_f(parseFloat(data.detail.Bborrow)-parseFloat(data.detail.Blent)));
					}
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		break;
	case '2':
		var checkId = item.check_id;
		$("input[name=transferBottomRadio][value=1]").prop('disabled','disabled');
		$("input[name=transferBottomRadio][value=2]").prop('checked','checked');
		$.ajax({
			url : '/guest/getGroupAccountInfo.do',
			type : 'POST',
			cache : false,
			data:{checkId:checkId},
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(data.bills && data.bills.length>0){
					for(var i=0;i<data.bills.length;i++){
						data.bills[i].transfer = false;
						data.bills[i].top = false;
					}
				}
				var view = new wijmo.collections.CollectionView(data.bills);
				transferBottomGrid.itemsSource = view;
				if(data.bills){
					var borrow=0;
					//贷项总额
					var lent=0;
					for(var i=0;i<data.bills.length;i++){
						var row = data.bills[i];
						if(row.cname!=''){
							borrow+=parseFloat(row.balance)+parseFloat(row.svc_charge);
						}
						if(row.sname!=''){
							lent+=parseFloat(row.balance);
						}
					}
					$("#transferBottomBorrow").val(borrow);
					$("#transferBottomLent").val(lent);
					$("#transferBottomRemain").val(changeTwoDecimal_f(parseFloat(borrow)-parseFloat(lent)));
				}
				if(data.detail){
					$("#transferBottomNamec").val(data.detail.leadNamec);
					$("#transferBottomNamee").val(data.detail.leadNamee);
					$("#transferBottomRoomId").val('');
					$("#transferBottomNotice").val(data.detail.notice);
					$("#transferBottomBillId").val(data.detail.billId);
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		break;
	case '3':
		var checkId = item.check_id;
		$("input[name=transferBottomRadio][value=2]").prop('disabled','disabled');
		$("input[name=transferBottomRadio][value=1]").prop('checked','checked');
		$.ajax({
			url : '/guest/getNoguestAccountInfo.do',
			type : 'POST',
			cache : false,
			data:{nogstId:checkId},
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(data.bills && data.bills.length>0){
					for(var i=0;i<data.bills.length;i++){
						data.bills[i].transfer = false;
						data.bills[i].top = false;
					}
				}
				var view = new wijmo.collections.CollectionView(data.bills);
				transferBottomGrid.itemsSource = view;
				if(data.detail){
					$("#transferBottomNamec").val(data.detail.nogstName);
					$("#transferBottomNamee").val('');
					$("#transferBottomRoomId").val('');
					$("#transferBottomNotice").val(data.detail.notes);
					$("#transferBottomBillId").val(data.detail.billId);
					$("#transferBottomBorrow").val(data.detail.borrow);
					$("#transferBottomLent").val(data.detail.lent);
					$("#transferBottomRemain").val(changeTwoDecimal_f(parseFloat(data.detail.borrow)-parseFloat(data.detail.lent)));
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				altEerrMsg('查询异常');
			}
		});
		break;
	default:
		break;
	}
}

/**
 * 转账提交
 */
function transferConfirm(){
	var topCheckId = $("#account_namec").val();
	var bottomCheckId = transferAccountGrid.itemsSource.currentItem.check_id;
	var type = $("input[name=transferRadio_1]:checked").val();
	if(topCheckId==bottomCheckId && type==1){
		var topType = $("input[name=transferTopRadio]:checked").val();
		var bottomType = $("input[name=transferBottomRadio]:checked").val();
		if(topType == bottomType){
			altWaringMsg("当前客人不能在同账页下互转,请重新操作");
			return;
		}
	}
	$.ajax({
		url : '/guest/transferConfirm.do',
		type : 'POST',
		cache : false,
		data:{topBillId:$("#transferTopBillId").val(),bottomBillId:$("#transferBottomBillId").val(),transferData:JSON.stringify(transferArr)},
		async:false,
		dataType:'json',
		success : function(data, textStatus, jqXHR) {
			if(data.success){
				altSuccessMsg(data.msg);
			}else{
				altEerrMsg('操作失败');
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}

/**
 * 转账放弃
 */
function transferGiveUp(){
	 $("#accountDetermine").click();
}

/**
 * 转账刷新
 */
function transferRefresh(){
	$("#accountDetermine").click();
}

/**
 * 转账退出
 */
function transferQuit(){
	$("#accountDetermineDiv").hide();
}

/**
 * 改单放弃
 */
function customAccountGiveUp(){
	$("#customAccounts").click();
}

/**
 * 改单添加
 */
function customAccountAdd(){
	var cv = customAccountsGrid.itemsSource;
    var data = cv.items;
    data.push({
      	
    });
    cv.refresh();
}

/**
 * 改单删除
 */
function customAccountDelete(){
	var rows = customAccountsGrid.rows;
	var cv = customAccountsGrid.itemsSource;
    var data = cv.items;
	if(rows && rows.length>0){
		for(var i=0;i<rows.length;i++){
			if(rows[i].isSelected){
				cv.removeAt(i);
			}
		}
	}
	cv.refresh();
}

/**
 * 改单退出
 */
function customAccountQuit(){
	$(".alertDivBg2").fadeOut();
	$("#customAccountsDiv").fadeOut();
}

/**
 * 结账退出
 */
function checkOutQuit(){
	$(".alertDivBg2").fadeOut();
	$("#checkOutDiv").fadeOut();
}

/**
 * 离店确定
 */
function leavingHotelConfirm(){
	var type = $("input[name=leavingHotelRadio]").val();
	var roomIds = [];
	var checkIds = [];
	var rows = LeavingHotelGrid.rows;
	var arr = [];
	switch (type) {
	case 'B':
		for(var i=0;i<rows.length;i++){
			if(rows[i].isSelected){
				checkIds.push(rows[i].dataItem.check_id);
				roomIds.push(rows[i].dataItem.room_id);
			}
		}
		break;
	default:
		for(var i=0;i<rows.length;i++){
			checkIds.push(rows[i].dataItem.check_id);
			roomIds.push(rows[i].dataItem.room_id);
		}
		break;
	}
	roomIds = unique(roomIds);
	$.ajax({
		url:'/guest/checkBillsInleaveHotel.do',
		data:{checkIds:checkIds.join(",")},
		async:false,
		dataType:'json',
		success:function(data){
			if(!data.success){
				var info = "客人【"+data.obj.substring(0,data.obj.length-1)+"】的A账未结清，是否继续？";
				altInfMsg(info,function(){
					leaveHotel(checkIds.join(","), roomIds.join(","));
				},function(){});
			}else{
				leaveHotel(checkIds.join(","), roomIds.join(","));
			}
		}
	});
}

/**
 * 离店修改客人及房间状态
 * @param checkIds
 * @param roomIds
 */
function leaveHotel(checkIds,roomIds){
	$.ajax({
		url:'/guest/leaveHotel.do',
		data:{roomIds:roomIds,checkIds:checkIds},
		dataType:'JSON',
		type:'post',
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
			}else{
				altEerrMsg('操作失败');
			}
		}
	});
}
/**
 * 全部退房
 */
function retreatAllRoom(){
	var roomIds = [];
	var checkIds = [];
	$("#roomList2 li").each(function(index,item){
		var id = $(item).text();
		roomIds.push(id);
	});
	$.ajax({
		url:'/guest/searchGuest.do',
		data:{withId:$("#account_namec option:selected").attr("data-withid"),chkStat:'I'},
		async:false,
		dataType:'json',
		success:function(data){
			if(data && data.length>0){
				for(var i=0;i<data.length;i++){
					checkIds.push(data[i].check_id);
				}
			}
			checkBills(roomIds.join(","),checkIds.join(","));
		}
	});
}	
	
/**
 * 非团体房退房
 */
function retreatRoom(){
	var checkId = $("#account_namec").val();
	var checkIds = [];
	$("#account_namec option").each(function(index,item){
		checkIds.push($(item).attr('value'));
	});
	var roomId = $("#account_roomid").val();
	checkBills(roomId,checkIds.join(","));
}

/**
 * 退房取消
 */
function retreatQuit(){
	$(".alertDivBg2").fadeOut();
	$("#checkOutOperationDiv").fadeOut();
}

/**
 * 离店退房检查客人账目信息
 * @param roomIds
 */
function checkBills(roomIds,checkIds){
	$.ajax({
		url:'/guest/checkBills.do',
		data:{roomIds:roomIds},
		dataType:'json',
		type:'post',
		success:function(data){
			if(!data.success){
				if(data.obj){
					altInfMsg(data.msg,function(){
						updateRoomStatus(roomIds,checkIds);
					},function(){});
				}
			}else{
				updateRoomStatus(roomIds,checkIds);
			}
		}
	});
}

/**
 * 退房后更改房间状态
 */
function updateRoomStatus(roomIds,checkIds){
	$.ajax({
		url:'/guest/checkOutRoom.do',
		data:{roomIds:roomIds,checkIds:checkIds},
		dataType:'JSON',
		type:'post',
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
				$("#checkOutOperationDiv").fadeOut();
				$(".alertDivBg2").fadeOut();
				closeWindow();
			}else{
				altWaringMsg(data.msg);
			}
		}
	});
}
function unique(arr) {
    var result = [], hash = {};
    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
        if (!hash[elem]) {
            result.push(elem);
            hash[elem] = true;
        }
    }
    return result;
}
/**
 * 取消
 */
function cancleBill(){
	var rows = accountGrid.rows;
	var ids = [];
	var payNums = [];
	if(rows && rows.length>0){
		for(var i=0;i<row.length;i++){
			if(rows[i].isSelected){
				ids.push(rows[i].dataItem.ID);
				payNums.push(rows[i].dataItem.pay_num);
			}
		}
	}
	if(ids.length==0){
		altWaringMsg('请选择需要取消的账目');
		return;
	}
	payNums = unique(payNums);
	var billType = $("input[name=radioAccount]:checked").val();
	var billId;
	if(billType=='1'){
		billId = $("#radioBillId").val();
	}else{
		billId = $("#radioBillId").val();
	}
	$.ajax({
		url:'/guest/cancleBill.do',
		data:{ids:ids,payNums:checkIds,billId:billId},
		dataType:'JSON',
		type:'post',
		success:function(data){
			if(data.success){
				altSuccessMsg(data.msg);
			}else{
				altEerrMsg(data.msg);
			}
		}
	});
}

/**
 * 离店退出
 */
function leavingHotelQuit(){
	$(".alertDivBg2").fadeOut();
	$("#LeavingHotelDiv").fadeOut();
}

/**
 * 散客办理入住
 */
function saveFitCheckIn(type){
	isAdd = false;isEdit = false;
	//初始化为客单详情
	var item ={};
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
				}
				if(index==0){
					item.room_id = roomId;
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
			roomIds = [] ;
		}
		if(bookIdArr.length == 0){
			altInfMsg("请选定房间");
			return;
		}
		item.check_id = checkId;
		item.roomIds = bookIdArr;
		item.with_id = withId;
		item.bookRoomId = $("#bookId").val();
	}else{
		item = guestGrid.itemsSource.currentItem;
	}
	getDetail(item,null,type);
	console.log("=======checkid======"+$("#checkId").val()) ;
	roomTag = item.room_id;
	initButton();
	//住客详情加载
	guestGrid.onSelectionChanging = function(e){
		return true;
	};
	$(".userDetails").show();
	$(".userCatalog").hide();
	$(".userTab1").addClass("point");
	$(".userTab2").removeClass("point");
	loadConsume(true);
	$(".checkDetailsDiv").fadeIn();
	closeBookRegistration() ;
	$(".alertDivBg").fadeIn();
	$("#crdPhoto").attr('src','/guest/loadImage.do?tableName=Citizen&colName=id_bmp&where=where Check_ID='+item.check_id+'&r='+Math.random());
	checkInTag = false;
	$("input[name=inType][value=1]").prop('checked',true);
	initGstOri(1);
	//2015-12-07 入住登记后设置默认值
	$("select[name=payId]").val($("select[name=payId] option").first().attr('value'));
	$("select[name=gstKind]").val($("select[name=gstKind] option").first().attr('value'));
	$("select[name=folk] option").each(function(){
		if($(this).text().indexOf('汉族')!=-1){
			$("select[name=folk]").val($(this).attr('value'));
		}
	});
	$("select[name=ntId] option").each(function(){
		if($(this).text()=='中国'){
			$("select[name=ntId]").val($(this).attr('value'));
		}
	});
}

/**
 * 按钮切换
 */
function buttonChange (){
	$("#confirm").removeClass("disabledAButton");
	$("#giveup").removeClass("disabledAButton");
	isEdit=true;
	guestGrid.onSelectionChanging = function(e){
		return false;
	};
	$("#addGuest").addClass('disabledAButton');
	$("#copyGuest").addClass('disabledAButton');
	$("#scanCard").addClass('disabledAButton');
	$("#sendRoomCard").addClass('disabledAButton');
	$("#changeRoom").addClass('disabledAButton');
	$("#guestDetailQuit").addClass('disabledAButton');
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
				    });  
				if(!avj==undefined){
					
				}
			}
		}
	});
}

/**
 * 非住客合约单位
 * @param avj
 */
function hyUnitDataBindNoguest(avj){
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
				//altInfMsg(JSON.stringify(data));
				$('#noguest_theCompany').autocomplete(data,   
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
				    	  $("#noguest_company_id").val(row.id);
				    	  $("#noguest_ta_type").val(row.taType);
				    });  
				if(!avj==undefined){
					
				}
			}
		}
	});
}
/**
 * 转账个人合约单位
 * @param avj
 */
function hyUnitDataBindGuest(avj){
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
				//altInfMsg(JSON.stringify(data));
				$('#guest_theCompany').autocomplete(data,   
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
				    	  $("#guest_company_id").val(row.id);
				    	  $("#guest_ta_type").val(row.taType);
				    }); 
				if(!avj==undefined){
					
				}
			}
		}
	});
}
/**
 * 非住客合约单位
 * @param avj
 */
function hyUnitDataBindGroup(avj){
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
				//altInfMsg(JSON.stringify(data));
				$('#group_theCompany').autocomplete(data,   
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
				    	  $("#group_company_id").val(row.id);
				    	  $("#group_ta_type").val(row.taType);
				    });  
				if(!avj==undefined){
					
				}
			}
		}
	});
}
/**
 * 转账退出
 */
function quitTransferForm(){
	$(".alertDivBg2").fadeOut();
	$("#transferAccountsDiv").fadeOut();
}

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

function initMoneyId(){
	$.ajax({
		url : '/guest/getMoneyId.do',
		type : 'get',
		cache : false,
		success : function(data, textStatus, jqXHR) {
			moneyId = data;
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
				$("#gstOriId").val(codeId);
				$("input[name=inType][value="+data.inType+"]").prop('checked',true);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altEerrMsg('查询异常');
		}
	});
}
/**
 * 扫描身份证
 */
function scanCard(data){
	$(".alertDivBg3").fadeIn();
	// 20010,1003|20011,1004
	var startScanCard = Boolean($("#guestinfo_hairRoomCard").val());
	var rtnBool = guest_room_utils.ScanOutIdCard(data,startScanCard);
	return rtnBool;
}
function startScanCard(){
	var item = guestGrid.itemsSource.currentItem;
	if(!item){
		altInfMsg('请选择一条住客信息');
		return;
	}
	item.with_id = $("input[name=withId]").val();
	
	var checkId=item.check_id;
	var roomId=item.room_id;
	var result = scanCard(checkId+","+roomId);
	if(result=="true"){
		//alert("扫描身份证成功");
	}else if(result=="false"){
		altWaringMsg("身份证扫描失败");						
	}else{
		//
	}
	//

	getDetail(item);
	//初始化为客单详情
	//$(".userTab1").click();
	//loadConsume(true);
	$(".alertDivBg").fadeIn();
	$(".alertDivBg3").fadeOut();
	//$(".checkDetailsDiv").fadeIn();
	$("#crdPhoto").attr('src','/guest/loadImage.do?tableName=Citizen&colName=id_bmp&where=where Check_ID='+item.check_id+'&r='+Math.random());
}