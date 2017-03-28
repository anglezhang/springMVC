define(function (require, exports, module) 
{
	/**
	 *@住客详情 前台处理逻辑
	 */
	var t = require("../frame/Tools");
	var basePath = $("#path").val();
	var template = require("template");
	var ajaxM = require("../frame/AjaxManager");
	var DateUtil = require("../frame/DateTool");
	$.formUtils.addValidator(
	{
	    name : 'simpleDate',
	    validatorFunction : function(value, $el, config, language, $form) {
	    	var text = /^(\d{4})\-(\d{2})\-(\d{2})$/;
	        return value==""?true:text.test(value);
	    },
	    errorMessage : '不是日期格式',
	    errorMessageKey: 'badCustomSimpleDate'
	});
	
	$.formUtils.addValidator(
			{
			    name : 'positiveMoney',
			    validatorFunction : function(value, $el, config, language, $form) {
			    	var text = /^(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
			        return value==""?true:text.test(value);
			    },
			    errorMessage : '格式不正确',
			    errorMessageKey: 'badpositiveMoney'
			});
	$.formUtils.addValidator(
			{
			    name : 'isMoney',
			    validatorFunction : function(value, $el, config, language, $form) {
			    	var text = /^(-)?(([1-9]{1}\d*)|([0]{1}))(\.(\d){1,2})?$/;
			        return value==""?true:text.test(value);
			    },
			    errorMessage : '格式不正确',
			    errorMessageKey: 'badMoney'
			});
	$.formUtils.addValidator(
			{
			    name : 'isNum',
			    validatorFunction : function(value, $el, config, language, $form) {
			    	var text = /^[0-9]*$/;
			        return value==""?true:text.test(value);
			    },
			    errorMessage : '格式不正确',
			    errorMessageKey: 'badnum'
			});
	$.formUtils.addValidator(
			{
			    name : 'justNumAndWord',
			    validatorFunction : function(value, $el, config, language, $form) {
			    	var text = /^[A-Za-z0-9]+$/;
			        return value==""?true:text.test(value);
			    },
			    errorMessage : '格式不正确',
			    errorMessageKey: 'badWord'
			});
	/**
	 * 数据去重
	 * @param {Object} arr
	 */
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
	
	/**
	*@描述 加载下拉框选项
	*@param code
	*@param name
	*/
	var createOption = function(code,name)
	{
		ajaxM.ajaxAction($,basePath + '/guest/getCodes.do',function(data)
		{
			var selectObj = $("[name='" + name + "']");
			selectObj.empty();
			var optionHtml = template("guestinfo_guestorc_option",{'LIST':data});
			selectObj.append(optionHtml);
		},{'code':code});
	};
	/**
	 * @描述 设置form表单
	 * */
	var guestInfoSetform = function (formid,data) {
		var obj;
		if (typeof data == 'string') {
			obj = JSON.parse(data);
		} else if (typeof data == 'object') {
			obj = data;
		}
		var key, value, tagName, type, arr;
		for (x in obj) {
			key = x;
			value = $.trim(obj[x]);
			$("form[id='" + formid + "']  [name='" + key + "']").each(function () {
				tagName = $(this)[0].tagName;
				type = $(this).attr('type');
				if (tagName == 'INPUT') {
					if (type == 'radio') {
						$(this).attr('checked', $(this).val() == value);
					} else {
						$(this).val(value);
					}
				} else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
					$(this).val(value);
				}

			});
		}
	};
	
	/**
	 * 账目刷新
	 */
	var reloadAccount = function()
	{
		var showType = $('input[name=radioType]:checked').val();
		var okFlag = $('input[name=disturb]:checked').val();
		var startDate = $("#account_startDate").val();
		var endDate = $("#account_endDate").val();
		var isInvalid = $("#isInvalid").prop('checked')?'N':'Y';
		ajaxM.ajaxAction($,basePath+'/guest/loadAccounts.do',function(data)
			{
				var view = new wijmo.collections.CollectionView(data);
				_accountGrid.flexGrid.itemsSource = view;
			}
			,{'billType':_guestObj.billType(),'billId':_guestObj.billId()
			,'showType':showType,'okFlag':okFlag,'startDate':startDate,'endDate':endDate,'isInvalid':isInvalid}
		);
	};

	/**
	 * 表单验证
	 * @param {Object} form
	 */
	var formValid = function(form){
		var lang = formValiData.lang;
		var conf = formValiData.conf;
		var errors = formValiData.errors;
		if(!form.isValid(lang, conf, false) ) {
			if(errors.length>0){
				altWaringMsg(errors[0].el.attr('label')+"  "+errors[0].error);
				formValiData.errors = [];
				return false;
			}
		} else {
			return true;
		}
	}

	var initSettle = function(selectId)
	{
		var selectObj = $("#" + selectId);
		selectObj.empty();
		var url = basePath + "/guest/loadSettleAndMoneyKind.do";
		$.ajax(
		{
	    	url:url,
	    	type:'post',
	    	dataType:'json',
	    	success:function(data)
	    	{
	    		var list = JSON.parse(data.listJson);
	    		var temData = {'LIST':list};
	    		var optionHtml = template("guestinfo_guestorc_option",temData);
	    		selectObj.append(optionHtml);
	    	}
	    });
	};
	
	/**
	 * 入账页面选择消费点切换A,B账
	 */
	var changePaied = function (checkId,consId){
		ajaxM.ajaxAction($,basePath+'/guest/getAorBbyConsumeId.do',function(data)
				{
			    var _selected = $("#incomeNamec option:selected");
				if(data.obj=='A'){
					$("input[name=effective][value=1]").prop('checked',true);
					$("#accountBillId").val(_selected.attr('data-billaid') );
					$("#accountlimit").val(changeTwoDecimal_f(parseFloat(_selected.attr('data-authbalancea'))+parseFloat(_selected.attr('data-alimit'))-parseFloat(_selected.attr('data-remaina'))));
				}else{
					$("input[name=effective][value=2]").prop('checked',true);
					$("#accountBillId").val(_selected.attr('data-billbid') );
					$("#accountlimit").val(changeTwoDecimal_f(parseFloat(_selected.attr('data-authbalanceb'))+parseFloat(_selected.attr('data-blimit'))-parseFloat(_selected.attr('data-remainb'))));
				}
				},{'consId':consId,'checkId':checkId});
	};
	
	/**
	 * 半日租页面选择消费点切换A,B账
	 */
	var changeForLongPaied = function (checkId,consId){
		ajaxM.ajaxAction($,basePath+'/guest/getAorBbyConsumeId.do',function(data)
				{
			    var _selected = $("#forLongNamec option:selected");
				if(data.obj=='A'){
					$("input[name=effective_forlong][value=1]").prop('checked',true);
					$("#forLongBillId").val(_selected.attr('data-billaid') );
					$("#forLonglimit").val(parseFloat(_selected.attr('data-authbalancea'))+parseFloat(_selected.attr('data-alimit'))-parseFloat(_selected.attr('data-remaina')));
				}else{
					$("input[name=effective_forlong][value=2]").prop('checked',true);
					$("#forLongBillId").val(_selected.attr('data-billbid') );
					$("#forLonglimit").val(parseFloat(_selected.attr('data-authbalanceb'))+parseFloat(_selected.attr('data-blimit'))-parseFloat(_selected.attr('data-remainb')));
				}
				},{'consId':consId,'checkId':checkId});
	};
	
	var checkAuth = function(){
		    var result;
			$.ajax({
				url: basePath + '/guest/checkAuth.do',
				data: {
					checkId: _guestObj.checkId()
				},
				dataType: 'json',
				type: 'post',
				async: false,
				success: function(data) {
					result = data.success;
				}
			});
			return result;
	};
	/**
	 * 入账房间回车事件
	 */
	var accountRoomPress = function(roomId){
		if(roomId=='')return;
		$("#incomeNamec").empty();
		$("#incomeNamee").empty();
		ajaxM.ajaxAction($,basePath+'/guest/list.do',function(data)
				{
					if(data.length){
						for(var i=0;i<data.length;i++){
							$("#incomeNamec").append('<option data-withid="'+data[i].with_id+'" data-billaid="'+data[i].billa_id+'" data-billbid="'+data[i].billb_id+'" data-alimit="'+data[i].Alimit+'" data-blimit="'+data[i].Blimit+'" data-authBalanceA="'+data[i].authBalanceA+'" data-remainA="'+data[i].remainA+'" data-remainB="'+data[i].remainB+'" data-authBalanceB="'+data[i].authBalanceB+'" value="'+data[i].check_id+'">'+data[i].gst_namec+'</option>');
							$("#incomeNamee").append('<option data-withid="'+data[i].with_id+'" data-billaid="'+data[i].billa_id+'" data-billbid="'+data[i].billb_id+'" data-alimit="'+data[i].Alimit+'" data-blimit="'+data[i].Blimit+'" data-authBalanceA="'+data[i].authBalanceA+'" data-remainA="'+data[i].remainA+'" data-remainB="'+data[i].remainB+'" data-authBalanceB="'+data[i].authBalanceB+'" value="'+data[i].check_id+'">'+data[i].gst_namee+'</option>');
						}
						changePaied($("#incomeNamec").val(),$("#accountExchange").val());
					}
				},{'roomId':roomId});
	}
	/**
	 * 结账金额回车事件
	 */
	var checkOutMoneyPress = function(){
		var cv = _checkOutObj.flexGrid.itemsSource;
		var _inputMoney = parseFloat($("#checkOutRemain").val());
		var _totalMoney = parseFloat($("#checkOutBalance").val());
		if(_inputMoney>_totalMoney){
			altInfMsg('是否将多余金额转化为小费？',function(){
				_checkOutMoneyPressPublic(cv,_inputMoney);
				$("#checkOutTipMoney").val(changeTwoDecimal_f(_inputMoney-_totalMoney));
			},function(){});
		}else{
			_checkOutMoneyPressPublic(cv,_inputMoney);
		}
	}
	/**
	 * 结账金额回车事件--计算金额
	 */
	var _checkOutMoneyPressPublic = function(cv,inputMoney){
			var data = cv.items;
			data.push({
			setlName:$("#checkOutSetlId option:selected").text(),
			balance:inputMoney,
			foreignm:'',
			moneykindName:$("#checkOutmoneyKind option:selected").text(),
			notes:$("#checkOutNotes").val(),
			moneykind:$("#checkOutmoneyKind").val(),
			setlId:$("#checkOutSetlId").val(),
			billId:_guestObj.billId(),
			billType:_guestObj.billType(),
			roomId:_guestObj.roomId()
		});
		cv.refresh();
		var money = 0;
		for(var i=0;i<data.length;i++){
			money+=parseFloat(data[i].balance);
		}
		$("#checkOutPaidMoney").val(changeTwoDecimal_f(money<0?-money:money));
		$("#checkOutHadPaid").val(changeTwoDecimal_f(money<0?-money:money));
		var remainMoney = parseFloat($("#checkOutSumBalance").val())-(parseFloat($("#checkOutPaidMoney").val()==''?0:$("#checkOutPaidMoney").val()));
		if(remainMoney<0) remainMoney = 0.00;
		$("#checkOutBalance").val(changeTwoDecimal_f(remainMoney));
		$("#checkOutRemain").val(changeTwoDecimal_f(remainMoney));
		$("#checkOutRemain").prop('disabled',changeTwoDecimal_f($("#checkOutBalance").val())=='0.00');
	};
	
	var getTransferFormData = function(){
		var formData;
		var type = $("input[name=transferRadio_1]:checked").val();
		switch (type) {
		case '1':
			formData = getFormJson(document.getElementById("transferForm_1"));
			var chkStat = $("input[name=transferRadio_2]:checked").val();
			formData.chkStat = chkStat;
			formData.compId = formData.guest_compId;
			break;
		case '2':
			formData = getFormJson(document.getElementById("transferForm_2"));
			var chkStat = $("input[name=transferRadio_3]:checked").val();
			formData.chkStat = chkStat;
			formData.compId = formData.group_compId;
			break;
		case '3':
			formData = getFormJson(document.getElementById("transferForm_3"));
			formData.compId = formData.noguest_compId;
			break;
		}
		return formData;
	};
	/**
	 * 转账列表加载
	 * @param type
	 */
	var loadTransferGrid = function (type,formData){
			var url = '';
			switch (type) {
			case '1':
				url = basePath+'/guest/searchGuest.do';
				_transferAccountsObj.flexGrid.columns.getColumn('namec').binding = 'gst_namec';
				_transferAccountsObj.flexGrid.columns.getColumn('billId').binding = 'billa_id';
				break;
			case '2':
				url = basePath+'/guest/searchGroup.do',
				_transferAccountsObj.flexGrid.columns.getColumn('namec').binding = 'lead_namec';
				_transferAccountsObj.flexGrid.columns.getColumn('billId').binding = 'bill_id';
				break;
			case '3':
				url = basePath+'/noguest/selectNoguestCond.do';
				_transferAccountsObj.flexGrid.columns.getColumn('namec').binding = 'nogstName';
				_transferAccountsObj.flexGrid.columns.getColumn('billId').binding = 'bill_id';
				break;
			}
			ajaxM.ajaxAction($,url,function(data)
				{
					if(type=='3'){
						var array = [];
						for (var i = 0; i < data.length; i++) {
							array.push({
								 id:data[i].id,
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
					_transferAccountsObj.flexGrid.itemsSource = view;
					_transferAccountsObj.flexGrid.refresh();
				
				},formData);
	}
	
	/**
	 * 转账列表切换事件
	 * @param {Object} view
	 * @param {Object} type
	 */
	var transferChange = function (view,type){
		var citem = view.currentItem;
		if(type=='1'){
			$("#transferInInfo").html('转账目标：住客（'+citem.gst_namec+'）');
		}else if(type=='2'){
			$("#transferInInfo").html('转账目标：团队（'+citem.lead_namec+'）');
		}else if(type=='3'){
			$("#transferInInfo").html('转账目标：非住客（'+citem.nogstName+'）');
		}
	}
	
	/**
	 * 转账上方radio切换事件
	 * 
	 */
	var transferTopClick = function(){
		var data = getTransferData('1',_transferTopObj);
		setTransferTopData(data);
	};
	
	/**
	 * 转账下方radio切换事件
	 */
	var transferBottomClick = function(){
		var type = $("input[name=transferRadio_1]:checked").val();
		var selectedItem = _transferAccountsObj.flexGrid.itemsSource.currentItem;
    	var bottomSearchData = {
    		checkId : selectedItem.check_id,
    		showType : 'A',
    		okFlag : 'N'
    	};
    	bottomSearchData.billType = $("input[name=transferBottomRadio]:checked").val();
		bottomSearchData.billId = (bottomSearchData.billType=='1' ? selectedItem.billa_id : selectedItem.billb_id);
		var bottomData = getTransferData(type,bottomSearchData);
    	setTransferBottomData(type,bottomData);
	};
	
	/**
	 * 转账按钮点击事件
	 */
	var topBindClick = function(){
		$("input[name=transferTopRadio]").click(function(){
			$("input[name=transferBottomRadio][value!="+$(this).val()+"]").prop('checked',true);
			transferBottomClick();
		});
		$("input[name=transferBottomRadio]").click(function(){
			$("input[name=transferTopRadio][value!="+$(this).val()+"]").prop('checked',true);
			transferTopClick();
		});
	}
	
	/**
	 * 转账向下按钮点击事件
	 */
	var transferToBottom = function(){
    	var topView = _transferAccountsObj.topFlexGrid.itemsSource;
    	var bottomView = _transferAccountsObj.bottomFlexGrid.itemsSource;
    	var bottomData = bottomView.items;
    	for(var i=0;i<_transferAccountsObj.topFlexGrid.rows.length;i++){
			var row = _transferAccountsObj.topFlexGrid.rows[i];
			if(row.isSelected && !bottomView.contains(row.dataItem)){
				bottomData.push(row.dataItem);
				row.dataItem.transfer = !row.dataItem.transfer;
				if(row.dataItem.transfer){
					_transferAccountsObj.transferArr.push(row.dataItem);
				}else{
					_transferAccountsObj.transferArr.remove(row.dataItem);
				}
				topView.remove(row.dataItem);
			}
		}
    	bottomView.refresh();
	};
	
	/**
	 * 转账向上按钮点击事件
	 */
	var transferToTop = function(){
    	var topView = _transferAccountsObj.topFlexGrid.itemsSource;
    	var bottomView = _transferAccountsObj.bottomFlexGrid.itemsSource;
    	var topData = topView.items;
    	for(var i=0;i<_transferAccountsObj.bottomFlexGrid.rows.length;i++){
			var row = _transferAccountsObj.bottomFlexGrid.rows[i];
			if(row.isSelected && !topView.contains(row.dataItem)){
				topData.push(row.dataItem);
				row.dataItem.transfer = !row.dataItem.transfer;
				if(row.dataItem.transfer){
					_transferAccountsObj.transferArr.push(row.dataItem);
				}else{
					_transferAccountsObj.transferArr.remove(row.dataItem);
				}
				bottomView.remove(row.dataItem);
			}
		}
    	topView.refresh();
    };
	/**
	 * 转账父窗口页面确定
	 */
	var loadTransferConfirmWindow = function(){
		var item = _transferAccountsObj.flexGrid.itemsSource.currentItem;
		if(!item){
			altWaringMsg('请选择转账目标');
			return;
		} 
    	var type = $("input[name=transferRadio_1]:checked").val();
    	//加载上方数据
    	var topData = getTransferData('1',_transferTopObj);
    	setTransferTopData(topData);
    	//加载下方数据
    	var selectedItem = _transferAccountsObj.flexGrid.itemsSource.currentItem;
    	var bottomSearchData = {
    		checkId : selectedItem.check_id,
    		showType : 'A',
    		okFlag : 'N'
    	};
    	if(selectedItem.check_id == _transferTopObj.checkId()){
    		topBindClick();
    		$("input[name=transferBottomRadio][value='2']").prop('checked',true);
    	}
    	switch (type){
    		case '1':
    			bottomSearchData.billType = $("input[name=transferBottomRadio]:checked").val();
    			bottomSearchData.billId = (bottomSearchData.billType=='1' ? selectedItem.billa_id : selectedItem.billb_id);
    			break;
    		case '3':
    			bottomSearchData.id = selectedItem.id;
    			break;
    		default:
    			break;
    	}
    	var bottomData = getTransferData(type,bottomSearchData);
    	setTransferBottomData(type,bottomData);
    	_transferAccountsObj.transferArr = [];
		$("#transferAccountsDiv").fadeOut();
		$("#accountDetermineDiv").fadeIn();
	}
	
	/**
	 * 获取转账上下数据
	 */
	var getTransferData = function (type,searchData){
		var result = {};
		var url;
		switch (type){
			case '1':
				url = basePath+'/guest/guestAccountInfo.do';
				break;
			case '2':
				url = basePath+'/guest/getGroupAccountInfo.do';
				break;
			case '3':
				url = basePath+'/guest/getNoguestAccountInfo.do';
				break;
			default:
				break;
		}
		$.ajax({
			url : url,
			type : 'POST',
			cache : false,
			data:searchData,
			async:false,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				if(data.bills && data.bills.length>0){
					for(var i=0;i<data.bills.length;i++){
						data.bills[i].transfer = false;
						data.bills[i].top = true;
					}
				}
				result = data;
			}
		});
		return result;
	}
	
	/**
	 * 设置转账确定页面上方数据
	 */
	var setTransferTopData = function(data){
		var view = new wijmo.collections.CollectionView(data.bills);
		_transferAccountsObj.topFlexGrid.itemsSource = view;
		 // 设置居中
		_transferAccountsObj.topFlexGrid.itemFormatter = function(panel, r, c, cell) {
	    	// reset attributes we are about to customize
			var s = panel.rows[r];
			// 由于pading = 3px; 所以 -6
			s.height = 20 ;
			cell.style.fontSize = "8px";
	    };
		var billType = $("input[name=transferTopRadio]:checked").val();
		if(data.detail){
			$("#transferTopNamec").val(data.detail.gstNamec);
			$("#transferTopNamee").val(data.detail.gstNamee);
			$("#transferTopRoomId").val(data.detail.roomId);
			$("#transferTopNotice").val(data.detail.notice);
			$("#transferTopBillId").val(billType=='1' ? data.detail.billaId : data.detail.billbId);
			$("#transferTopBorrow").val(billType=='1' ? data.detail.borrow : data.detail.Bborrow);
			$("#transferTopLent").val(billType=='1' ? data.detail.lent : data.detail.Blent);
			$("#transferTopRemain").val(billType=='1' ? changeTwoDecimal_f(parseFloat(data.detail.borrow)-parseFloat(data.detail.lent)) : changeTwoDecimal_f(parseFloat(data.detail.Bborrow)-parseFloat(data.detail.Blent)));
		}
	}
	
	/**
	 * 设置转账确定页面下方数据
	 */
	var setTransferBottomData = function(type,data){
		var view = new wijmo.collections.CollectionView(data.bills);
		_transferAccountsObj.bottomFlexGrid.itemsSource = view;
		_transferAccountsObj.bottomFlexGrid.itemFormatter = function(panel, r, c, cell) {
	    	// reset attributes we are about to customize
			var s = panel.rows[r];
			// 由于pading = 3px; 所以 -6
			s.height = 20 ;
			cell.style.fontSize = "8px";
	    };
		var billType = $("input[name=transferBottomRadio]:checked").val(); 
		switch (type){
			case '1':
				$("#transferBottomNamec").val(data.detail.gstNamec);
				$("#transferBottomNamee").val(data.detail.gstNamee);
				$("#transferBottomRoomId").val(data.detail.roomId);
				$("#transferBottomNotice").val(data.detail.notice);
				$("#transferBottomBillId").val(billType=='1' ? data.detail.billaId : data.detail.billbId);
				$("#transferBottomBorrow").val(billType=='1' ? data.detail.borrow : data.detail.Bborrow);
				$("#transferBottomLent").val(billType=='1' ? data.detail.lent : data.detail.Blent);
				$("#transferBottomRemain").val(billType=='1' ? changeTwoDecimal_f(parseFloat(data.detail.borrow)-parseFloat(data.detail.lent)) : changeTwoDecimal_f(parseFloat(data.detail.Bborrow)-parseFloat(data.detail.Blent)));
				break;
			case '2':
				$("input[name=transferBottomRadio][value=1]").prop('disabled','disabled');
				$("input[name=transferBottomRadio][value=2]").prop('checked','checked');
				//借项金额
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
				$("#transferBottomNamec").val(data.detail.leadNamec);
				$("#transferBottomNamee").val(data.detail.leadNamee);
				$("#transferBottomRoomId").val('');
				$("#transferBottomNotice").val(data.detail.notice);
				$("#transferBottomBillId").val(data.detail.billId)
				$("#transferBottomBorrow").val(borrow);
				$("#transferBottomLent").val(lent);
				$("#transferBottomRemain").val(changeTwoDecimal_f(parseFloat(borrow)-parseFloat(lent)));
				break;
			case '3':
				$("input[name=transferBottomRadio][value=2]").prop('disabled','disabled');
				$("input[name=transferBottomRadio][value=1]").prop('checked','checked');
				$("#transferBottomNamec").val(data.detail.nogstName);
				$("#transferBottomNamee").val('');
				$("#transferBottomRoomId").val('');
				$("#transferBottomNotice").val(data.detail.notes);
				$("#transferBottomBillId").val(data.detail.billId);
				$("#transferBottomBorrow").val(data.detail.nBorrow);
				$("#transferBottomLent").val(data.detail.nLent);
				$("#transferBottomRemain").val(changeTwoDecimal_f(parseFloat(data.detail.nBorrow)-parseFloat(data.detail.nLent)));
				break;
			default:
				break;
		}
	}
	
	/**
	 * 加载离店grid
	 * @param {Object} searchData
	 */
	var loadLeaveRoomGrid = function(searchData){
		ajaxM.ajaxAction($,basePath+'/guest/searchGuest.do',function(data){
			var view = new wijmo.collections.CollectionView(data);
			_leaveRoomObj.flexGrid.itemsSource = view;
			_leaveRoomObj.flexGrid.refresh();
		},searchData);
	}
	
	/**
	 * 离店页面按钮切换事件
	 * @param {Object} value
	 */
	var leaveRoomRadioChange = function(value){
		var searchData = {
			chkStat:'I'
		};
		switch (value) {
		case 'A':
			searchData.withId = _guestObj.withId();
			_leaveRoomObj.flexGrid.selectionMode = 'None';
			break;
		case 'B':
			searchData.withId = _guestObj.withId();
			_leaveRoomObj.flexGrid.selectionMode = 'ListBox';
		    break;
		case 'C':
			searchData.withId = _guestObj.withId();
			searchData.leaveDate = new Date().format('yyyy-MM-dd');
			_leaveRoomObj.flexGrid.selectionMode = 'None';
			break;
		case 'D':
			searchData.checkId = _guestObj.checkId();
			_leaveRoomObj.flexGrid.selectionMode = 'None';
			break;
		}
		loadLeaveRoomGrid(searchData);
	}
	
	/**
	 * 离店确定
	 */
	var leavingHotelConfirm = function(){
		var type = $("input[name=leavingHotelRadio]").val();
		var roomIds = [];
		var checkIds = [];
		var rows = _leaveRoomObj.flexGrid.rows;
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
		ajaxM.ajaxAction($,basePath+'/guest/checkBillsInleaveHotel.do',function(data){
			if(!data.success){
				var info = "客人【"+data.obj.substring(0,data.obj.length-1)+"】的A账未结清，是否继续？";
				altInfMsg(info,function(){
					leaveHotel(checkIds.join(","), roomIds.join(","));
				},function(){});
			}else{
				leaveHotel(checkIds.join(","), roomIds.join(","));
			}
		},{checkIds:checkIds.join(",")});
	}
	
	/**
	 * 离店操作
	 */
	var leaveHotel = function(checkIds,roomIds){
		ajaxM.ajaxAction($,basePath+'/guest/leaveHotel.do',function(data){
			if(data.success){
				altSuccessMsg(data.msg);
			}else{
				altEerrMsg('操作失败');
			}
		},{roomIds:roomIds,checkIds:checkIds});
	}
	
	/**
	 * 全部退房
	 */
	var retreatAllRoom = function(){
		var roomIds = [];
		var checkIds = [];
		$("#guestinfo_roomList a").each(function(index,item){
			var id = $(item).attr('guestinfo-roomid');
			roomIds.push(id);
		});
		ajaxM.ajaxAction($,basePath+'/guest/searchGuest.do',function(data)
			{
				if(data && data.length>0){
					for(var i=0;i<data.length;i++){
						checkIds.push(data[i].check_id);
					}
				}
				checkBills(roomIds.join(","),checkIds.join(","));
			},{withId:_guestObj.withId(),chkStat:'I'});
	};
	
	/**
	 * 仅退此房
	 */
	function retreatRoom(){
		var checkIds = [];
		$("#account_namec option").each(function(index,item){
			checkIds.push($(item).attr('value'));
		});
		checkBills(_guestObj.roomId(),checkIds.join(","));
	};
	/**
	 * 检查退房客人账目
	 * @param {Object} roomIds
	 * @param {Object} checkIds
	 */
	var checkBills =function (roomIds,checkIds){
		ajaxM.ajaxAction($,basePath+'/guest/checkBills.do',function(data)
			{
				if(!data.success){
					if(data.obj){
						altInfMsg(data.msg,function(){
							updateRoomStatus(roomIds,checkIds);
						},function(){});
					}
				}else{
					updateRoomStatus(roomIds,checkIds);
				}
			},{roomIds:roomIds});
	}
	
	/**
	 * 退房后更改房间状态
	 */
	var updateRoomStatus = function (roomIds,checkIds){
		ajaxM.ajaxAction($,basePath+'/guest/checkOutRoom.do',function(data)
			{
				if(data.success){
					altSuccessMsg(data.msg);
					_checkOutRoomObj.quit();
					//closeWindow();
				}else{
					altWaringMsg(data.msg);
				}
			},{roomIds:roomIds,checkIds:checkIds});
	};
	/**
	 * @描述 表单验证
	 * */
	var formValiData = 
	{
		 errors:[],
		 conf : {
			onError : function($form) {
			},
			onSuccess : function($form) {
				formValiData.errors = [];
				return false; // Will stop the submission of the form
			},
			onElementValidate : function(valid, $el, $form, errorMess) {
				if( !valid ) {
					formValiData.errors.push({el: $el, error: errorMess});
				}
				console.log('Input ' +$el.attr('name')+ ' is ' + ( valid ? 'VALID':'NOT VALID') );
			}
		},
		lang : {
			requiredFields:"不能为空",
			badEmail:"不是正确的邮箱格式",
		}
	};
	/**
	 * 获取记账类型（A账或B账）
	 */
	var _guestObj = {
		checkId : function(){
			 return $("#account_namec").val();
		},
		withId :function(){
			return $("#guestinfo_withId").val() || $("#account_billbid").val();
		},
		roomId : function(){
			return $("#account_roomid").val();
		},
		billType:function(){
			return $("input[name=radioAccount]:checked").val();
		},
		billId:function(){
			return _guestObj.billType() == '1' ? $("#account_billaid").val() : $("#account_billbid").val();
		},
		gstName:function(){
			return $("#account_namec option:selected").text();
		}
	};
	
	var _transferTopObj = {
		checkId : function(){
			return _guestObj.checkId();
		},
		billType : function(){
			return $("input[name=transferTopRadio]:checked").val();
		},
		billId : function(){
			return _transferTopObj.billType()=='1' ? $("#account_billaid").val() : $("#account_billbid").val();
		},
		showType :'A',
		okFlag:'N'
	};
	/**
	 * 账目grid
	 */
	var _accountGrid = {
		flexGrid:null,
		init:function(){
			this.flexGrid = guestDetaiAccFlexGrid;
		},
		getSelected:function(){
			var map = new Map();
			var rowArr = [];
			var ids = [];
			var payNums = [];
			for (var i = 0; i < this.flexGrid.rows.length; i++) {
				var row = this.flexGrid.rows[i];
				if(row.isSelected){
					rowArr.push(row);
					ids.push(row.dataItem.ID);
					payNums.push(row.dataItem.payNum);
				}
			}
			map.put("rowArr",rowArr);
			map.put("ids",ids);
			map.put("payNums",payNums);
			map.put("size",rowArr.length);
			return map;
		}
	};
	
	/**
	 * 押金
	 */
	var _depositObj = {
		init:function(){
			this._unbindEvent();
			this._bindEvent();
		},
		_unbindEvent:function(){
			$("#deposit").unbind('click');
			$("#depositSubmit").unbind('click');
			$("#depositCancle").unbind('click');
			$("#depositQuit").unbind('click');
			$("#depositAcco").unbind('click');
			$("#depositBalance").unbind('click');
			$("#depositClose").unbind('click');
		},
		_bindEvent:function(){
			$("#deposit").bind('click', function() {
				_depositObj.show();
			});
			$("#depositSubmit").bind('click', function() {
				_depositObj.confirm();
			});
			$("#depositCancle").bind('click', function() {
				_depositObj.cancle();
			});
			$("#depositQuit").bind('click', function() {
				_depositObj.quit();
			});
			$("#depositAcco").bind('keypress',function(event){
				if(event.keyCode == '13'){
					$("#depositBalance").focus();
				}
			});
			$("#depositBalance").bind('keypress',function(event){
				if(event.keyCode == '13'){
					//$("#depositBalance").focus();
				}
			});
			$("#depositForm input").bind('keyup',function(){
				_depositObj.inputButton();
			});
			$("#depositClose").bind('click',function(){
				$(".alertDivBg2").fadeOut();
				$("#depositDiv").fadeOut();
			});
			$("#depositForm input").inputmask();
		},
		form : $("#depositForm"),
		show : function() {
			$(".alertDivBg2").fadeIn();
			$("#depositDiv").fadeIn();
			$("#depositAcco").focus();
			this.form.resetForm();
			this.initButton();
		},
		confirm : function() {
			if($("#depositSubmit").hasClass('disabledAButton')) return;
			if(!formValid(this.form)) return false;
			var formData =  getFormJson(document.getElementById("depositForm"));
			formData.setlId = $("#depositForm select[name=setlId]").val();
			formData.roomId = _guestObj.roomId();
			formData.moneyKind = $("#depositMoneyKind").val();
			formData.billId = _guestObj.billId();
			formData.billType = _guestObj.billType();
			formData.checkId = _guestObj.checkId();
			ajaxM.ajaxAction($,basePath+'/guest/depositSaveBill.do',function(data)
					{
						if(data.success)
						{
							altSuccessMsg(data.msg);
							_depositObj.cancle();
							reloadAccount();
							$("#depositAcco").focus();
							_depositObj.initButton();
						}else
						{
							altEerrMsg(data.msg);
						}
					},formData);
		},
		cancle : function() {
			if($("#depositCancle").hasClass('disabledAButton')) return;
			this.form.resetForm();
			this.initButton();
		},
		quit:function(){
			if($("#depositQuit").hasClass('disabledAButton')) return;
			$(".alertDivBg2").fadeOut();
			$("#depositDiv").fadeOut();
		},
		initButton:function(){
			$("#depositSubmit").addClass('disabledAButton');
			$("#depositCancle").addClass('disabledAButton');
			$("#depositQuit").removeClass('disabledAButton');
		},
		inputButton:function(){
			$("#depositSubmit").removeClass('disabledAButton');
			$("#depositCancle").removeClass('disabledAButton');
			$("#depositQuit").addClass('disabledAButton');
		}
		
	};
	
	/**
	 * 收款
	 */
	var _receivesObj = {
		init:function(){
			this._unbindEvent();
			this._bindEvent();
		},
		_unbindEvent:function(){
			$("#receivables").unbind('click');
			$("#receivesSubmit").unbind('click');
			$("#receivesCancle").unbind('click');
			$("#receivesQuit").unbind('click');
			$("#receivablesSettle").unbind('click');
			$("#receivablesAcco").unbind('click');
			$("#receivablesBalance").unbind('click');
			$("#receivablesClose").unbind('click');
		},
		_bindEvent:function(){
			$("#receivables").bind('click', function() {
				_receivesObj.show();
			});
			$("#receivesSubmit").bind('click', function() {
				_receivesObj.confirm();
			});
			$("#receivesCancle").bind('click', function() {
				_receivesObj.cancle();
			});
			$("#receivesQuit").bind('click', function() {
				_receivesObj.quit();
			});
			$("#receivablesSettle").bind('change',function(){
				_receivesObj.settleChange();
			});
			$("#receivablesAcco").bind('keypress', function(event) {
				if(event.keyCode == '13'){
					$("#receivablesBalance").focus();
				}
			});
			$("#receivablesBalance").bind('keypress',function(event){
				if(event.keyCode == '13'){
					//$("#receivablesBalance").focus();
				}
			});
			$("#receivablesClose").bind('click', function() {
				$(".alertDivBg2").fadeOut();
				$("#receivablesDiv").fadeOut();
			});
			$("#receivablesForm input,select").bind('keyup',function(){
				_receivesObj.inputButton();
			});
			$("#receivablesForm input").inputmask();
		},
		form : $("#receivablesForm"),
		show : function() {
			$(".alertDivBg2").fadeIn();
			$("#receivablesDiv").fadeIn();
			$("#receivablesAcco").focus();
			this.form.resetForm();
			this.initButton();
			this.settleChange();
		},
		confirm : function() {
			if($("#receivesCancle").hasClass('disabledAButton')) return;
			if(!formValid(this.form)) return false;
			var formData =  getFormJson(document.getElementById("receivablesForm"));
			formData.setlId = $("#receivablesForm select[name=setlId]").val();
			formData.roomId = _guestObj.roomId();
			formData.moneyKind = $("#receivablesMoneyKind").val();
			formData.billId = _guestObj.billId();
			formData.billType = _guestObj.billType();
			formData.checkId = _guestObj.checkId();
			ajaxM.ajaxAction($,basePath+'/guest/depositSaveBill.do',function(data)
					{
						if(data.success)
						{
							altSuccessMsg(data.msg);
							_receivesObj.cancle();
							reloadAccount();
							$("#receivablesAcco").focus();
						}else
						{
							altEerrMsg(data.msg);
						}
					},formData);
		},
		cancle : function() {
			if($("#receivesCancle").hasClass('disabledAButton')) return;
			this.form.resetForm();
			this.initButton();
		},
		quit:function(){
			if($("#receivesQuit").hasClass('disabledAButton')) return;
			$(".alertDivBg2").fadeOut();
			$("#receivablesDiv").fadeOut();
		},
		settleChange:function(){
			var moneyKind = $("#receivablesSettle option:selected").attr('data-moneyid');
			$("#receivablesMoneyKind").val(moneyKind);
			$("#receivablesForm input[name=foreignm]").prop('disabled',moneyKind == $.trim(moneyId));
			$("#receivablesForm input[name=balance]").prop('disabled',!(moneyKind == $.trim(moneyId)));
		},
		initButton:function(){
			$("#receivesSubmit").addClass('disabledAButton');
			$("#receivesCancle").addClass('disabledAButton');
			$("#receivesQuit").removeClass('disabledAButton');
		},
		inputButton:function(){
			$("#receivesSubmit").removeClass('disabledAButton');
			$("#receivesCancle").removeClass('disabledAButton');
			$("#receivesQuit").addClass('disabledAButton');
		}
	};
	
	/**
	 * 预授权
	 */
	var _preAuthorizationObj = {
		flexGrid:null,
		initGrid:function(){
			var preAuthorizationGrid =  new wijmo.grid.FlexGrid('#preAuthorizationGrid', {
		    	autoGenerateColumns: false,
		  	    columns: [
		  	         { header: 'ID', binding: 'ID', width:'*',visible:false },
		  	         { header: 'bill_id', binding: 'bill_id', width:'*',visible:false },
		  	         { header: 'finish_balance', binding: 'finish_balance', width:'*',visible:false },
		  	         { header: 'status', binding: 'status', width:'*',visible:false },
		  	         { header: '授权号', binding: 'auth_id', width:'*' },
		             { header: '授权金额', binding: 'balance' ,width:'*'},
		             { header: '有效期', binding: 'expired' ,width:'*'}, 
		             { header: '状态', binding: 'status0',width:'*'},
		             { header: '操作员', binding: 'oper_name', width:'*' },
		             { header: '授权时间', binding: 'oper_time', width:'*' },
		             { header: '信用卡号', binding: 'credit_id', width:'*' },
		             { header: '持卡人', binding: 'credit_holder', width:'*' }
		     	 ],
		     	 headersVisibility:wijmo.grid.HeadersVisibility.Column,
		     	 selectionMode:'ListBox',
		     	 isReadOnly:true
			}); 
			this.flexGrid = preAuthorizationGrid;
		},
		_bindEvent:function(){
			$("#preAuthorization").bind('click', function() {
				if($(this).hasClass('disabledAButton')) return;
				_preAuthorizationObj.show();
			});
			$("#quitPreAuth").bind('click', function() {
				_preAuthorizationObj.quit();
			});
			$("#preAuthCancle").bind('click', function() {
				_preAuthorizationObj.preAuthCancle();
			});
			$("#canclePreAuth").bind('click', function() {
				_cancleAuthObj.show();
			});
			$("#finishPreAuth").bind('click',function(){
				_finishAuthObj.form.resetForm();
				_finishAuthObj.show();
			});
			$("#addPreAward").bind('click',function(){
				if($("#addPreAward").hasClass('disabledAButton')) return;
				_preAuthorizationObj.showAddPreAward();
			});
			$("#savePreAward").bind('click',function(){
				_preAuthorizationObj.submitForm();
			});
			$("#canclePreAward").bind('click',function(){
				_preAuthorizationObj.cancle();
			});
			$("#quitPreAward").bind('click',function(){
				_preAuthorizationObj.quitAddPreAward();
			});
			$("#addPreAwardForm input").each(function(index,item){
				$(item).bind('keypress',function(event){
					if(event.keyCode==13){
						if(index == $("#addPreAwardForm input").length-1){
							$("#addPreAwardForm input").get(0).focus();
						}else{
							$("#addPreAwardForm input").get(index+1).focus();
						}
					}
				});
			});
			$("#addPreAwardForm input").bind('keyup',function(){
				_preAuthorizationObj.inputButton();
			});
			$("#preAuthorizationClose").bind('click',function(){
				_preAuthorizationObj.quit();
			});
			$("#addPreAwardClose").bind('click',function(){
				$(".alertDivBg3").fadeOut();
				$("#addPreAwardDiv").fadeOut();
			});
			$("input[name=preAuthRadio]").bind('click',function(){
				_preAuthorizationObj.loadAuthGrid();
			});
			$("#addPreAwardForm input").inputmask();
		},
		_unbindEvent:function(){
			$("#preAuthorization").unbind('click');
			$("#quitPreAuth").unbind('click');
			$("#preAuthCancle").unbind('click');
			$("#canclePreAuth").unbind('click');
			$("#finishPreAuth").unbind('click');
			$("#addPreAward").unbind('click');
			$("#savePreAward").unbind('click');
			$("#canclePreAward").unbind('click');
			$("#quitPreAward").unbind('click');
			$("#preAuthorizationClose").unbind('click');
		},
		init:function(){
			this.initGrid();
			this._unbindEvent();
			this._bindEvent();
		},
		form : $("#addPreAwardForm"),
		show : function() {
			_preAuthorizationObj.loadAuthGrid();
			$(".alertDivBg2").fadeIn();
			$("#preAuthorizationDiv").fadeIn();
		},
		showAddPreAward:function(){
			this.form.resetForm();
			$(".alertDivBg3").fadeIn();
			$("#addPreAwardDiv").fadeIn();
			this.initButton();
			$("#addPreAwardForm input").get(0).focus();
		},
		preAuthCancle:function(){
			var item = _preAuthorizationObj.flexGrid.itemsSource.currentItem;
			if(!item || item.length>1){
				altWaringMsg('请选择一条数据');
				return;
			}
			ajaxM.ajaxAction($,basePath+'/guest/updateAuthStatus.do',function(data)
			{
				if(data.success){
					altSuccessMsg(data.msg);
					_preAuthorizationObj.loadAuthGrid();
				}else{
					altEerrMsg('操作失败');
				}
			},{'id':item.ID,balance:item.balance,billId:item.bill_id});
		},
		submitForm : function() {
			if($("#savePreAward").hasClass('disabledAButton'))return;
			if(!formValid(this.form)) return false;
			var formData =  getFormJson(document.getElementById("addPreAwardForm"));
			var checkId = _guestObj.checkId();
			formData.checkId = checkId;
			formData.billId = _guestObj.billId();
			ajaxM.ajaxAction($,basePath+'/guest/savePreAuth.do',function(data)
					{
						if(data.success)
						{
							debugger;
							altSuccessMsg(data.msg);
							_preAuthorizationObj.cancle();
							_preAuthorizationObj.loadAuthGrid();
							$("#addPreAwardForm input").get(0).focus();
							$("#guest_bill_refush_smbtn").trigger('click');
						}else
						{
							altEerrMsg(data.msg);
						}
					},formData);
		},
		cancle : function() {
			if($("#canclePreAward").hasClass('disabledAButton'))return;
			this.form.resetForm();
			this.initButton();
		},
		quit:function(){
			$(".alertDivBg2").fadeOut();
			$("#preAuthorizationDiv").fadeOut();
		},
		quitAddPreAward:function(){
			if($("#quitPreAward").hasClass('disabledAButton'))return;
			$(".alertDivBg3").fadeOut();
			$("#addPreAwardDiv").fadeOut();
		},
		initButton:function(){
			$("#savePreAward").addClass('disabledAButton');
			$("#canclePreAward").addClass('disabledAButton');
			$("#quitPreAward").removeClass('disabledAButton');
		},
		inputButton:function(){
			$("#savePreAward").removeClass('disabledAButton');
			$("#canclePreAward").removeClass('disabledAButton');
			$("#quitPreAward").addClass('disabledAButton');
		},
		loadAuthGrid:function(){
			var status = $("input[name=preAuthRadio]:checked").val();
			ajaxM.ajaxAction($,basePath+'/guest/getPreAuthorization.do',function(data)
					{
						if(data){
							var sum=0;
							for(var i=0;i<data.length;i++){
								sum+=parseFloat(data[i].balance);
							}
							$("#preAuthSumBalance").html(changeTwoDecimal_f(sum));
							var view = new wijmo.collections.CollectionView(data);
							if(_preAuthorizationObj.flexGrid)_preAuthorizationObj.flexGrid.itemsSource = view;
						}
					},{'checkId':_guestObj.checkId(),'status':status,'billId':_guestObj.billId()});
		}
	};
	
	/**
	 * 完成预授权
	 */
	var _finishAuthObj = {
		init:function(){
			this._unbindEvent();
			this._bindEvent();
		},
		_unbindEvent:function(){
			$("#finishAuthConfirm").unbind('click');
			$("#finishAuthCancle").unbind('click');
			$("#finishAuthQuit").unbind('click');
			$("#finishAuthClose").unbind('click');
		},
		_bindEvent:function(){
			$("#finishAuthConfirm").bind('click',function(){
				_finishAuthObj.confirm();
			});
			$("#finishAuthCancle").bind('click',function(){
				_finishAuthObj.form.resetForm();
			});
			$("#finishAuthQuit").bind('click',function(){
				_finishAuthObj.close();
			});
			$("#finishAuthClose").bind('click',function(){
				_finishAuthObj.close();
			});
		},
		show:function(){
			var item = _preAuthorizationObj.flexGrid.itemsSource.currentItem;
			if(!item || item.length>1){
				altWaringMsg('请选择一条数据');
				return;
			}
			if(item.status==1){
				altWaringMsg('请选择有效的预授权');
				return;
			}
			$(".alertDivBg3").fadeIn();
			$("#finishAuthDiv").fadeIn();
			$("#finishAuthBalance").val(item.balance);
		},
		form:$("#finishAuthForm"),
		confirm:function(){
			if(!formValid(this.form)) return;
			var item = _preAuthorizationObj.flexGrid.itemsSource.currentItem;
			var finishBalance = item.finish_balance == ''?0:parseFloat(item.finish_balance);
			var balance = parseFloat($("#finishBalance").val());
			var totalMoney = parseFloat($("#finishAuthBalance").val());
			if(balance > totalMoney-finishBalance){
				altWaringMsg('完成金额应小于剩余金额');
				return;
			}
			ajaxM.ajaxAction($,basePath+'/guest/finishAuth.do',function(data)
					{
						if(data.success)
						{
							altSuccessMsg(data.msg);
							_finishAuthObj.close();
							//reloadAccount();
							$("#guest_bill_refush_smbtn").trigger('click');
						}else
						{
							altEerrMsg(data.msg);
						}
					},{'id':item.ID,'balance':balance,'finishNo':$("#finishNo").val(),'billId':item.bill_id,'billType':_guestObj.billType()});
		},
		cancle:function(){
			this.form.resetForm();
		},
		close:function(){
			$(".alertDivBg3").fadeOut();
			$("#finishAuthDiv").fadeOut();
		}
	};
	
	/**
	 * 取消预授权
	 */
	var _cancleAuthObj = {
		init:function(){
			this._unbindEvent();
			this._bindEvent();
		},
		_unbindEvent:function(){
			$("#cancleAuthConfirm").unbind('click');
			$("#cancleAuthCancle").unbind('click');
			$("#cancleAuthQuit").unbind('click');
			$("#cancleAuthClose").unbind('click');
		},
		_bindEvent:function(){
			$("#cancleAuthConfirm").bind('click',function(){
				_cancleAuthObj.confirm();
			});
			$("#cancleAuthCancle").bind('click',function(){
				_cancleAuthObj.form.resetForm();
			});
			$("#cancleAuthQuit").bind('click',function(){
				_cancleAuthObj.close();
			});
			$("#cancleAuthClose").bind('click',function(){
				_cancleAuthObj.close();
			});
		},
		show:function(){
			var item = _preAuthorizationObj.flexGrid.itemsSource.currentItem;
			if(!item || item.length>1){
				altWaringMsg('请选择一条数据');
				return;
			}
			if(item.status==1){
				altWaringMsg('请选择有效的预授权');
				return;
			}
			$(".alertDivBg3").fadeIn();
			$("#cancleAuthDiv").fadeIn();
			$("#cancleAuthBalance").val(item.balance);
		},
		form:$("#cancleAuthForm"),
		confirm:function(){
			if(!formValid(this.form)) return;
			var item = _preAuthorizationObj.flexGrid.itemsSource.currentItem;
			ajaxM.ajaxAction($,basePath+'/guest/cancleAuth.do',function(data)
					{
						if(data.success)
						{
							altSuccessMsg(data.msg);
							_cancleAuthObj.close();
							//reloadAccount();
							$("#guest_bill_refush_smbtn").trigger('click');
						}else
						{
							altEerrMsg(data.msg);
						}
					},{'id':item.id,'balance':item.balance,'cancelNo':$("#cancelNo").val(),'billId':item.bill_id});
		},
		cancle:function(){
			this.form.resetForm();
		},
		close:function(){
			$(".alertDivBg3").fadeOut();
			$("#cancleAuthDiv").fadeOut();
		}
	};
	/**
	 * 入账
	 */
	var _accountedForObj = {
			init:function(){
				this._unbindEvent();
				this._bindEvent();
			},
			_unbindEvent:function(){
				$("#accountedFor").unbind('click');
				$("#accountForSubmit").unbind('click');
				$("#accountForReset").unbind('click');
				$("#accountForQuit").unbind('click');
				$("#accountConsume").unbind('change');
				$("#accountBalance").unbind('keypress');
			},
			_bindEvent:function(){
				$("#accountedFor").bind('click', function() {
					_accountedForObj.show();
				});
				$("#accountForSubmit").bind('click', function() {
					_accountedForObj.confirm();
				});
				$("#accountForReset").bind('click', function() {
					$("#accountAcco").val('');
					$("#accountBalance").val('');
					$("#accountForeignm").val('');
					$("#serveMoney").val('');
					$("#accountNotes").val('');
					_accountedForObj.initButton();
				});
				$("#accountForQuit").bind('click', function() {
					_accountedForObj.quit();
				});
				$("#accountConsume").bind('change',function(){
					_accountedForObj.settleChange();
				});
				$("#accountBalance").bind('keypress',function(event){
					if(event.keyCode==13){
						var isserve = document.getElementById("isserve");
						if(isserve.checked){
							var money = $(this).val();
							var rate = $("#accountConsume option:selected").attr('data-svcrate');
							if(rate!='0'){
								var serveMoney = changeTwoDecimal_f(money*rate/100);
								$("#serveMoney").val(serveMoney);
							}
						}
					}
				});
				$("#accountRoomId").on({
					keyup:function(event){
						if(event.keyCode!=13){
							_accountedForObj.initButton();
						}
					},
					keypress:function(event){
						if(event.keyCode==13){
							accountRoomPress($(this).val());
							_accountedForObj.inputButton();
						}
					}
				});
				$("#accountAcco").bind('keypress', function(event) {
					if(event.keyCode == '13'){
						$("#accountBalance").focus();
					}
				});
				$("#accountBalance").bind('keypress',function(event){
					if(event.keyCode == '13'){
						//$("#receivablesBalance").focus();
					}
				});
				$("#accountClose").bind('click', function() {
					$(".alertDivBg2").fadeOut();
					$("#accountedForDiv").fadeOut();
				});
				$("#accountForm input[id!=accountRoomId]").bind('keyup',function(event){
					if(event.keyCode!='13'){
						_accountedForObj.inputButton();
					}
				});
				$("input[name=effective]").bind('click',function(){
					var bill_type = $(this).val();
			    	var checkId = $("#incomeNamec").val();
			    	var _selected = $("#incomeNamec option:selected");
			    	$("#accountBillId").val(bill_type=='1' ? _selected.attr('data-billaid') : _selected.attr('data-billbid'));
			    	$("#accountlimit").val(bill_type=='1' ? parseFloat(_selected.attr('data-authbalancea'))+parseFloat(_selected.attr('data-alimit'))-parseFloat(_selected.attr('data-remaina'))
			    											: parseFloat(_selected.attr('data-authbalanceb'))+parseFloat(_selected.attr('data-blimit'))-parseFloat(_selected.attr('data-remainb')));
				});
				$("#accountForm input").inputmask();
			},
			form : $("#accountForm"),
			show : function() {
				this.form.resetForm();
				$("#incomeNamec").empty();
				$("#incomeNamee").empty();
				ajaxM.ajaxAction($,basePath+'/guest/getAccountFor.do',function(data)
					{
						if(data.guests){
							for(var i=0;i<data.guests.length;i++){
								$("#incomeNamec").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" data-authBalanceA="'+data.guests[i].authBalanceA+'" data-remainA="'+data.guests[i].remainA+'" data-remainB="'+data.guests[i].remainB+'" data-authBalanceB="'+data.guests[i].authBalanceB+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namec+'</option>');
								$("#incomeNamee").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" data-authBalanceA="'+data.guests[i].authBalanceA+'" data-remainA="'+data.guests[i].remainA+'" data-remainB="'+data.guests[i].remainB+'" data-authBalanceB="'+data.guests[i].authBalanceB+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namee+'</option>');
							}
						}
						changePaied(_guestObj.checkId(),$("#accountExchange").val());
					},_guestObj);
				$(".alertDivBg2").fadeIn();
				$("#accountedForDiv").fadeIn();
				$("#incomeNamec").val(_guestObj.checkId());
				$("#incomeNamee").val(_guestObj.checkId());
				$("#accountRoomId").val(_guestObj.roomId());
				$("#accountBillId").val(_guestObj.billId());
				var moneykind = $("#accountExchange").val();
				$("#accountBalance").prop('disabled',!(moneykind == $.trim(moneyId)));
				$("#accountForeignm").prop('disabled',moneykind == $.trim(moneyId));
				this.initButton();
				$("#accountAcco").focus();
			},
			confirm : function() {
				if($("#accountForSubmit").hasClass("disabledAButton")) return;
				if(!formValid(this.form)) return;
				var formData =  getFormJson(document.getElementById("accountForm"));
				formData.billId = $("#accountForm input[name=billId]").val();
				formData.type = $("input[name=accountRadio]:checked").val();
				formData.billType = $("#accountForm input[name=effective]:checked").val();
				formData.roomId = $("#accountForm input[name=roomId]").val();
				formData.moneyKind = $("#accountExchange").val();
				formData.checkId = _guestObj.checkId();
				if(parseFloat($("#accountlimit").val())-parseFloat(formData.balance)<0){
					altInfMsg('本位币金额大于可用金额，是否继续？',function(){
						_accountedForObj.addBill(formData);
					},function(){});
				}else{
					_accountedForObj.addBill(formData);
				}
			},
			cancle : function() {
				if($("#accountForQuit").hasClass('disabledAButton'))return;
				this.initButton();
				this.form.resetForm();
			},
			quit:function(){
				if($("#accountForQuit").hasClass('disabledAButton'))return;
				$(".alertDivBg2").fadeOut();
				$("#accountedForDiv").fadeOut();
			},
			initButton:function(){
				$("#accountForSubmit").addClass('disabledAButton');
				$("#accountFormReset").addClass('disabledAButton');
				$("#accountForQuit").removeClass('disabledAButton');
			},
			inputButton:function(){
				$("#accountForSubmit").removeClass('disabledAButton');
				$("#accountFormReset").removeClass('disabledAButton');
				$("#accountForQuit").addClass('disabledAButton');
			},
			settleChange:function(){
				var moneyKind = $("#accountConsume option:selected").attr('data-moneyid');
				$("#accountExchange").val(moneyKind);
				$("#accountForm input[name=foreignm]").prop('disabled',moneyKind == $.trim(moneyId));
				$("#accountForm input[name=balance]").prop('disabled',!(moneyKind == $.trim(moneyId)));
			},
			addBill:function(formData){
				ajaxM.ajaxAction($,basePath+'/guest/accountForSaveBill.do',function(data)
						{
							if(data.success)
							{
								altSuccessMsg(data.msg);
								_accountedForObj.initButton();
								$("#accountAcco").val('');
								$("#accountBalance").val('');
								reloadAccount();
								$("#accountAcco").focus();
							}else
							{
								altEerrMsg(data.msg);
							}
						},formData);
			}
	};
	
	/**
	 * 结账
	 */
	var _checkOutObj = {
		    flexGrid:null,
		    initGrid:function(){
		    	var checkOutCollectionView = [];
		    	var checkOutGrid =  new wijmo.grid.FlexGrid('#checkOutGrid', {
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
				     	 selectionMode:'Row'
				    });
		    	this.flexGrid = checkOutGrid;
		    },
			init:function(){
				this._unbindEvent();
				this._bindEvent();
				this.initGrid();
			},
			_unbindEvent:function(){
				$("#checkOut").unbind('click');
				$("#checkOutSubmit").unbind('click');
				$("#checkOutCancle").unbind('click');
				$("#checkOutQuit").unbind('click');
				$("#checkOutClose").unbind('click');
			},
			_bindEvent:function(){
				$("#checkOut").bind('click', function() {
					_checkOutObj.show();
				});
				$("#checkOutSubmit").bind('click', function() {
					_checkOutObj.confirm();
				});
				$("#checkOutCancle").bind('click', function() {
					_checkOutObj.cancle();
				});
				$("#checkOutQuit").bind('click', function() {
					_checkOutObj.quit();
				});
				$("#checkOutClose").bind('click',function(){
					_checkOutObj.close();
				});
				$("#checkOutRemain").bind('keypress',function(event){
					if(event.keyCode==13){
						var money = $(this).val();
						if(money=='') return;
						if(money==0){
							altWaringMsg('不能输入为0的数字');
							return;
						}
						checkOutMoneyPress();
						_checkOutObj.inputButton();
						
					}
				});
				$("#checkOutSetlId").bind('change',function(){
					_checkOutObj.settleChange();
				});
				$("#checkOutRemain").inputmask("numeric");
				$("#checkOutRemain").css('text-align','left');
			},
			form : $("#checkOutForm"),
			show : function() {
				var isAuth = checkAuth();
				if(!isAuth){
					altWaringMsg('有未完成的预授权，请先处理');
					return;
				}
				var disturbType = $("input[name=disturb]:checked").val();
				var map = _accountGrid.getSelected();
				if(map.get("rowArr").length==0){
					altWaringMsg('请选择需结账的账单');
					return;
				}
				if(disturbType=='Y'){
					altWaringMsg('请选择未结账单');
					return;
				}else if(disturbType=='A'){
					for(var i=0;i<map.get("rowArr").length;i++){
						var row = map.get("rowArr")[i];
						if(row.dataItem.okFlag !='0'){
							altWaringMsg('请选择未结账单');
							return;
						}
					}
				}
				var checkOutCollectionView = new wijmo.collections.CollectionView([]);
			    if(!this.flexGrid){
			    	this.initGrid();
			    }else{
			    	this.flexGrid.itemsSource=checkOutCollectionView;
			    }
			    //借项总额
				var borrow=0;
				//贷项总额
				var lent=0;
				var _accountSelected = map.get('rowArr');
				for(var i=0;i<_accountSelected.length;i++){
					var row = _accountSelected[i];
						if(row.dataItem.cname!=''){
							borrow+=parseFloat(row.dataItem.balance)+parseFloat(row.dataItem.svc_charge);
						}
						if(row.dataItem.sname!=''){
							lent+=parseFloat(row.dataItem.balance);
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
				var _blMoney = changeTwoDecimal_f(Math.round(borrow - lent));
				$("#checkOutRemain").val(_blMoney);
				$("#checkOutBalance").val(_blMoney);
				$("#checkOutSumBalance").val(_blMoney);
				if(_blMoney<0){
					$("#checkOutSetlId").val(refundCode);
					this.settleChange();
				}
				$("#checkOutRemain").prop('disabled',Math.round($("#checkOutRemain").val())==0);
				$(".alertDivBg2").fadeIn();
				$("#checkOutDiv").fadeIn();
				_checkOutObj.initButton();
				_checkOutObj.settleChange();
			},
			confirm : function() {
				if($("#checkOutSubmit").hasClass('disabledAButton'))return;
				var money = parseFloat($("#checkOutRemain").val());
				if(money>0){
					altWaringMsg('当前账目未结清');
					return;
				}
				//选择的账目信息
				var ids = _accountGrid.getSelected().get("ids");
				var oddMoney = $("#checkOutOddment").val();
				var tipMoney = $("#checkOutTipMoney").val();
				//结账的账目信息
				var rows = _checkOutObj.flexGrid.rows;
				var payArr = [];
				for(var i=0;i<rows.length;i++){
					payArr.push(rows[i].dataItem);
				}
				ajaxM.ajaxAction($,basePath+'/guest/checkOutAccount.do',function(data)
					{
						if(data.success)
						{
							reloadAccount();
							_checkOutObj.close();
							_checkOutObj.initButton();
						}else
						{
							altEerrMsg(data.msg);
						}
					},{checkId:_guestObj.checkId(),ids:ids.join(','),bills:JSON.stringify(payArr),oddMoney:oddMoney,
					billId:_guestObj.billId(),billType:_guestObj.billType(),roomId:_guestObj.roomId(),tipMoney:tipMoney});
			},
			cancle : function() {
				if($("#checkOutCancle").hasClass('disabledAButton'))return;
				$("#checkOut").click();
				$("#checkOutRemain").removeAttr('disabled');
				var data = [];
				var view = new wijmo.collections.CollectionView(data);
				_checkOutObj.flexGrid.itemsSource = view;
				_checkOutObj.flexGrid.itemsSource.refresh();
			},
			quit:function(){
				if($("#checkOutQuit").hasClass('disabledAButton'))return;
				this.close();
			},
			close:function(){
				$(".alertDivBg2").fadeOut();
				$("#checkOutDiv").fadeOut();
			},
			initButton:function(){
				$("#checkOutSubmit").addClass('disabledAButton');
				$("#checkOutCancle").addClass('disabledAButton');
				$("#checkOutPrint").addClass('disabledAButton');
				$("#checkOutQuit").removeClass('disabledAButton');
			},
			inputButton:function(){
				$("#checkOutSubmit").removeClass('disabledAButton');
				$("#checkOutCancle").removeClass('disabledAButton');
				$("#checkOutPrint").removeClass('disabledAButton');
				$("#checkOutQuit").addClass('disabledAButton');
			},
			settleChange:function(){
				var moneyKind = $("#checkOutSetlId option:selected").attr('data-moneyid');
				$("#checkOutmoneyKind").val(moneyKind);
				$("#checkOutForm input[name=foreignm]").prop('disabled',moneyKind == $.trim(moneyId));
				$("#checkOutForm input[name=balance]").prop('disabled',!(moneyKind == $.trim(moneyId)));
			},
	};
	
	/**
	 * 取消
	 */
	var _cancleBillObj = {
		init:function(){
			this._unbindEvent();
			this._bindEvent();
		},
		_unbindEvent:function(){
			$("#cancleBill").unbind('click');
		},
		_bindEvent:function(){
			$("#cancleBill").bind('click',function(){
				_cancleBillObj.cancleBill();
			});
		},
		cancleBill:function(){
			var length = _accountGrid.getSelected().get("size");
			if(length==0 || length>1){
				altWaringMsg("请选择一条数据");
				return;
			}
			var _selected = _accountGrid.getSelected().get('rowArr')[0];
			ajaxM.ajaxAction($,basePath+'/guest/cancleBill.do',function(data)
					{
						if(data.success)
						{
							altSuccessMsg(data.msg);
							reloadAccount();
						}else
						{
							altEerrMsg(data.msg);
						}
					},{'id':_selected.dataItem.ID,'payNum':_selected.dataItem.payNum,'billId':_guestObj.billId(),'okFlag':_selected.dataItem.okFlag,'checkId':_guestObj.checkId()});
		}
	};
	
	/**
	 * 转账
	 */
	var _transferAccountsObj = {
		msg:'请选择转账目标',
		flexGrid:null,
		topFlexGrid:null,
		bottomFlexGrid:null,
		transferArr:[],
		init:function(){
			this._unbindEvent();
			this._bindEvent();
			this.initGrid();
		},
		initGrid:function(){
	    	//客人账目列表
		 	var transferAccountGrid =  new wijmo.grid.FlexGrid('#transferAccountGrid', {
		   	    autoGenerateColumns: false,
		  	    columns: [
		  	         { header: 'ID', binding: 'id',width:50,visible:false}, 
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
		     	 isReadOnly:true
		    });
		    //转账上方列表
		 	var transferTopGrid =  new wijmo.grid.FlexGrid('#transferTopGrid', {
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
		     	 isReadOnly:true
		    });
		    //转账下方列表
		    var transferBottomGrid =  new wijmo.grid.FlexGrid('#transferBottomGrid', {
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
		     	 isReadOnly:true
		    }); 
	    	this.flexGrid = transferAccountGrid;
	    	this.topFlexGrid = transferTopGrid;
	    	this.bottomFlexGrid = transferBottomGrid;
		},
		_unbindEvent:function(){
			$("#transferAccounts").unbind('click');
			$("#searchTransferGrid").unbind('click');
			$("#clearTransferForm").unbind('click');
			$("#quitTransferForm").unbind('click');
			$("#accountDetermine").unbind('click');
			$("#transferToBottom").unbind('click');
			$("#transferToTop").unbind('click');
			$("input[name=transferTopRadio]").unbind('click');
			$("input[name=transferBottomRadio]").unbind('click');
			$("#transferRoomId").unbind('click');
			$("#transferBillId").unbind('click');
		},
		_bindEvent:function(){
			$("#transferAccounts").bind('click', function() {
				_transferAccountsObj.show();
			});
			$("#searchTransferGrid").bind('click', function() {
				var type = $("input[name=transferRadio_1]:checked").val();
				var formData = getTransferFormData();
				loadTransferGrid(type,formData);
			});
			$("#clearTransferForm").bind('click', function() {
				_transferAccountsObj.formReset();
			});
			$("#quitTransferForm").bind('click', function() {
				_transferAccountsObj.quitFirstWindow();
			});
			$("#transferAccountsClose").bind('click', function() {
				_transferAccountsObj.quitFirstWindow();
			});
			$("#accountDetermine").bind('click',function(){
				loadTransferConfirmWindow();
			});
			$("input[name=transferRadio_1]").bind('click',function(){
				_transferAccountsObj.formReset();
				$("#transferRoomId").val('');
				$("#transferBillId").val('');
				_transferAccountsObj.showForm($(this).val());
			});
			$("input[name=transferTopRadio]").bind('click',function(){
				transferTopClick();
			});
			$("input[name=transferBottomRadio]").bind('click',function(){
				transferBottomClick();
			});
			$("#transferToBottom").bind('click',function(){
				transferToBottom();
			});
			$("#transferToTop").bind('click',function(){
				transferToTop();
			});
			$("#accountDetermineClose").bind('click',function(){
				_transferAccountsObj.quitSecondWindow();
			});
			$("#accountDetermineConfirm").bind('click',function(){
				_transferAccountsObj.confirm();
			});
			$("#accountDetermineCancle").bind('click',function(){
				_transferAccountsObj.cancle();
			});
			$("#accountDetermineRefresh").bind('click',function(){
				_transferAccountsObj.cancle();
			});
			$("#accountDetermineQuit").bind('click',function(){
				_transferAccountsObj.quitSecondWindow();
			});
			$("#transferRoomId").bind('keypress',function(event){
				if(event.keyCode == 13){
					var roomId = $(this).val();
					if(roomId=='') return;
					var type = $("input[name=transferRadio_1]:checked").val();
					var formData = {};
					formData.roomId = roomId;
					loadTransferGrid(type,formData);
				}
			});
			$("#transferBillId").bind('keypress',function(event){
				if(event.keyCode == 13){
					var billId = $(this).val();
					if(billId=='') return;
					var type = $("input[name=transferRadio_1]:checked").val();
					var formData = {};
					formData.billId = billId;
					loadTransferGrid(type,formData);
				}
			});
			$("#accountDetermineQuit").bind('click',function(){
				_transferAccountsObj.quitSecondWindow();
			});
		},
		guestForm : $("#transferForm_1"),
		groupForm : $("#transferForm_2"),
		noguestForm : $("#transferForm_3"),
		show : function() {
			var view = new wijmo.collections.CollectionView([]);
			this.flexGrid.itemsSource = view;
			$(".alertDivBg2").fadeIn();
			$("#transferAccountsDiv").fadeIn();
			$("input[name=transferRadio_1][value=1]").prop('checked',true);
			$("input[name=transferRadio_2][value=I]").prop('checked',true);
			$("#transferGuestInfo").html(_guestObj.gstName()+','+_guestObj.roomId());
			$("#transferInInfo").html(_transferAccountsObj.msg);
			$("#transferRoomId").val('');
			$("#transferBillId").val('');
			this.formReset();
		},
		confirm : function() {	
			$.ajax({
				url : basePath+'/guest/transferConfirm.do',
				type : 'POST',
				cache : false,
				data:{topBillId:$("#transferTopBillId").val(),bottomBillId:$("#transferBottomBillId").val(),transferData:JSON.stringify(_transferAccountsObj.transferArr),checkId:_guestObj.checkId()},
				async:false,
				dataType:'json',
				success : function(data, textStatus, jqXHR) {
					if(data.success){
						altSuccessMsg(data.msg);
						reloadAccount();
					}else{
						altEerrMsg('操作失败');
					}
				}
			});
		},
		formReset : function() {
			this.guestForm.resetForm();
			this.groupForm.resetForm();
			this.noguestForm.resetForm();
		},
		quitFirstWindow:function(){
			$(".alertDivBg2").fadeOut();
			$("#transferAccountsDiv").fadeOut();
		},
		quitSecondWindow:function(){
			$(".alertDivBg2").fadeOut();
			$("#accountDetermineDiv").fadeOut();
		},
		initButton:function(){
			$("#depositSubmit").addClass('disabledAButton');
			$("#depositCancle").addClass('disabledAButton');
			$("#depositQuit").removeClass('disabledAButton');
		},
		inputButton:function(){
			$("#depositSubmit").removeClass('disabledAButton');
			$("#depositCancle").removeClass('disabledAButton');
			$("#depositQuit").addClass('disabledAButton');
		},
		showForm:function(value){
			switch (value) {
			case '1':
				$("#transferDiv_1").show();
				$("#transferDiv_2").hide();
				$("#transferDiv_3").hide();
				$("input[name=transferRadio_2][value=I]").prop('checked',true);
				break;
			case '2':
				$("#transferDiv_1").hide();
				$("#transferDiv_2").show();
				$("#transferDiv_3").hide();
				$("input[name=transferRadio_3][value=I]").prop('checked',true);
				break;
			case '3':
				$("#transferDiv_1").hide();
				$("#transferDiv_2").hide();
				$("#transferDiv_3").show();
				break;
			default:
				break;
			}
		},
		cancle:function(){
			 $("#accountDetermine").trigger('click');
		}
	};
	
	/**
	 * 半日租
	 */
	var _forLongObj = {
			init:function(){
				this._unbindEvent();
				this._bindEvent();
			},
			_unbindEvent:function(){
				$("#forALongTime").unbind('click');
				$("#forLongSubmit").unbind('click');
				$("#forLongFormReset").unbind('click');
				$("#forLongQuit").unbind('click');
				$("#forLongBalance").unbind('keypress');
			},
			_bindEvent:function(){
				$("#forALongTime").bind('click', function() {
					_forLongObj.show();
				});
				$("#forLongSubmit").bind('click', function() {
					_forLongObj.confirm();
				});
				$("#forLongFormReset").bind('click', function() {
					$("#forLongAcco").val('');
					$("#forLongBalance").val('');
					$("#forLongForeignm").val('');
					$("#forLongServeMoney").val('');
					$("#forLongNotes").val('');
					_forLongObj.initButton();
				});
				$("#forLongQuit").bind('click', function() {
					_forLongObj.quit();
				});
				$("#forLongBalance").bind('keypress',function(event){
					if(event.keyCode==13){
						var isserve = document.getElementById("isserve");
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
				});
				$("#forLongRoomId").on({
					keyup:function(event){
						if(event.keyCode!=13){
							_forLongObj.initButton();
						}
					},
					keypress:function(event){
						if(event.keyCode==13){
							accountRoomPress($(this).val());
							_forLongObj.inputButton();
						}
					}
				});
				$("#forLongAcco").bind('keypress', function(event) {
					if(event.keyCode == '13'){
						$("#accountBalance").focus();
					}
				});
				$("#forLongBalance").bind('keypress',function(event){
					if(event.keyCode == '13'){
						//$("#receivablesBalance").focus();
					}
				});
				$("#forLongClose").bind('click', function() {
					$(".alertDivBg2").fadeOut();
					$("#forALongTimeDiv").fadeOut();
				});
				$("input[name=effective_forlong]").bind('click',function(){
					var bill_type = $(this).val();
			    	var checkId = $("#forLongNamec").val();
			    	var _selected = $("#forLongNamec option:selected");
			    	$("#forLongBillId").val(bill_type=='1' ? _selected.attr('data-billaid') : _selected.attr('data-billbid'));
			    	$("#forLonglimit").val(bill_type=='1' ? parseFloat(_selected.attr('data-authbalancea'))+parseFloat(_selected.attr('data-alimit'))-parseFloat(_selected.attr('data-remaina'))
			    											: parseFloat(_selected.attr('data-authbalanceb'))+parseFloat(_selected.attr('data-blimit'))-parseFloat(_selected.attr('data-remainb')));
				});
				$("#forLongForm input[id!=forLongRoomId]").bind('keyup',function(event){
					if(event.keyCode!='13'){
						_forLongObj.inputButton();
					}
				});
				$("#forLongForm input").inputmask();
			},
			form : $("#forLongForm"),
			show : function() {
				$("#forLongNamec").empty();
				$("#forLongNamee").empty();
				ajaxM.ajaxAction($,basePath+'/guest/getAccountFor.do',function(data)
					{
						if(data.guests){
							for(var i=0;i<data.guests.length;i++){
								$("#forLongNamec").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" data-authBalanceA="'+data.guests[i].authBalanceA+'" data-remainA="'+data.guests[i].remainA+'" data-remainB="'+data.guests[i].remainB+'" data-authBalanceB="'+data.guests[i].authBalanceB+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namec+'</option>');
								$("#forLongNamee").append('<option data-withid="'+data.guests[i].with_id+'" data-billaid="'+data.guests[i].billa_id+'" data-billbid="'+data.guests[i].billb_id+'" data-alimit="'+data.guests[i].Alimit+'" data-blimit="'+data.guests[i].Blimit+'" data-authBalanceA="'+data.guests[i].authBalanceA+'" data-remainA="'+data.guests[i].remainA+'" data-remainB="'+data.guests[i].remainB+'" data-authBalanceB="'+data.guests[i].authBalanceB+'" value="'+data.guests[i].check_id+'">'+data.guests[i].gst_namee+'</option>');
							}
						}
						changeForLongPaied(_guestObj.checkId(),$("#forLongExchange").val());
					},_guestObj);
				$(".alertDivBg2").fadeIn();
				$("#forALongTimeDiv").fadeIn();
				$("#forLongNamec").val(_guestObj.checkId());
				$("#forLongNamee").val(_guestObj.checkId());
				$("#forLongRoomId").val(_guestObj.roomId());
				$("#forLongBillId").val(_guestObj.billId());
				$("#forLongAcco").focus();
				_forLongObj.initButton();
				var moneykind = $("#forLongExchange").val();
				$("#forLongForm input[name=foreignm]").prop('disabled',(moneykind == $.trim(moneyId)));
				$("#forLongForm input[name=balance]").prop('disabled',!(moneykind == $.trim(moneyId)));
			},
			confirm : function() {
				if($("#forLongSubmit").hasClass("disabledAButton")) return;
				if(!formValid(this.form)) return false;
				var formData =  getFormJson(document.getElementById("forLongForm"));
				formData.billId = $("#forLongForm input[name=billId]").val();
				formData.type = $("input[name=forLongRadio]:checked").val();
				formData.billType = $("input[name=effective_forlong]:checked").val();
				formData.roomId = $("#forLongForm input[name=roomId]").val();
				formData.moneyKind = $("#forLongExchange").val();
				formData.consId = $("#forLongConsume").val();
				formData.checkId = _guestObj.checkId();
				var limit = parseFloat($("#forLonglimit").val());
				if(limit-parseFloat(formData.balance)<0){
					altInfMsg('本位币金额大于可用金额，是否继续？',function(){
						_forLongObj.addBill(formData);
					},function(){});
				}else{
					_forLongObj.addBill(formData);
				}
			},
			cancle : function() {
				if($("#forLongFormReset").hasClass('disabledAButton'))return;
				this.initButton();
				this.form.resetForm();
			},
			quit:function(){
				if($("#forLongQuit").hasClass('disabledAButton'))return;
				$(".alertDivBg2").fadeOut();
				$("#forALongTimeDiv").fadeOut();
			},
			initButton:function(){
				$("#forLongSubmit").addClass('disabledAButton');
				$("#forLongFormReset").addClass('disabledAButton');
				$("#forLongQuit").removeClass('disabledAButton');
			},
			inputButton:function(){
				$("#forLongSubmit").removeClass('disabledAButton');
				$("#forLongFormReset").removeClass('disabledAButton');
				$("#forLongQuit").addClass('disabledAButton');
			},
			addBill:function(formData){
				ajaxM.ajaxAction($,basePath+'/guest/accountForSaveBill.do',function(data)
						{
							if(data.success)
							{
								altSuccessMsg(data.msg);
								_forLongObj.form.resetForm();
								_forLongObj.initButton();
								reloadAccount();
								$("#forLongAcco").focus();
							}else
							{
								altEerrMsg(data.msg);
							}
						},formData);
			}
	};
	
	/**
	 * 离店
	 */
	var _leaveRoomObj = {
		flexGrid : null,
		initGrid :function(){
			var leavingHotelGrid = new wijmo.grid.FlexGrid('#LeavingHotelGrid', {
	     		autoGenerateColumns: false,
	    		columns: [
	          	 { header: '中文名', binding: 'gst_namec' ,width:'*'},
	          	 { header: '英文名', binding: 'gst_namee' ,width:'*'}, 
	          	 { header: '房号', binding: 'room_id',width:50}, 
		         { header: '付款人', binding: 'paymanFlag',width:70},
		         { header: '摘要', binding: 'chkExt', width:'*' },
		         { header: '离店日期', binding: 'leave_date',width:'*',format:'yyyy-MM-dd',width:100 },
		         { header: '登记日期', binding: 'chk_time',width:100 }
		       	],
		        headersVisibility:wijmo.grid.HeadersVisibility.Column,
		        selectionMode:'None',
		        isReadOnly:true,
		        deferResizing:true
	   		 });
	    	this.flexGrid = leavingHotelGrid;
		},
		init:function(){
			this._unbindEvent();
			this._bindEvent();
			this.initGrid();
		},
		_unbindEvent:function(){
			$("#leavingHotel").unbind('click');
			$("#leavingHotelConfirm").unbind('click');
			$("#leavingHotelCancle").unbind('click');
			$("#leavingHotelQuit").unbind('click');
			$("#leavingHotelRadio").unbind('click');
		},
		_bindEvent:function(){
			$("#leavingHotel").bind('click',function(){
				if($(this).hasClass('disabledAButton')) return;
				_leaveRoomObj.show();
			});
			$("#leavingHotelConfirm").bind('click',function(){
				_leaveRoomObj.confirm();
			});
			$("#leavingHotelCancle").bind('click',function(){
				_leaveRoomObj.cancle();
			});
			$("#leavingHotelQuit").bind('click',function(){
				_leaveRoomObj.quit();
			});
			$("#leavingHotelClose").bind('click',function(){
				_leaveRoomObj.quit();
			}); 
			$("input[name=leavingHotelRadio]").bind('click',function(){
				var value = $(this).val();
				leaveRoomRadioChange(value);
			}); 
		},
		show:function(){
			$(".alertDivBg2").fadeIn();
			$("#leavingHotelDiv").fadeIn();
			var value = $("input[name=leavingHotelRadio]:checked").val();
			leaveRoomRadioChange(value);
		},
		confirm:function(){
			leavingHotelConfirm();
		},
		cancle:function(){
			
		},
		quit:function(){
			$(".alertDivBg2").fadeOut();
			$("#leavingHotelDiv").fadeOut();
		}
	};
	
	/**
	 * 退房
	 */
	var _checkOutRoomObj = {
		init:function(){
			this._unbindEvent();
			this._bindEvent();
		},
		_unbindEvent:function(){
			$("#checkOutOperation").unbind('click');
			$("#retreatAllRoom").unbind('click');
			$("#retreatRoom").unbind('click');
			$("#retreatQuit").unbind('click');
			$("#retreatRoomConfirm").unbind('click');
			$("#retreatRoomClose").unbind('click');
		},
		_bindEvent:function(){
			$("#checkOutOperation").bind('click',function(){
				if($(this).hasClass('disabledAButton')) return;
				_checkOutRoomObj.show();
			});
			$("#retreatAllRoom").bind('click',function(){
				retreatAllRoom();
			});
			$("#retreatRoom").bind('click',function(){
				retreatRoom();
			});
			$("#retreatQuit").bind('click',function(){
				_checkOutRoomObj.quit();
			});
			$("#retreatRoomConfirm").bind('click',function(){
				retreatRoom();
			});
			$("#retreatRoomQuit").bind('click',function(){
				_checkOutRoomObj.quit();
			});
			$("#retreatRoomClose").bind('click',function(){
				_checkOutRoomObj.quit();
			});
		},
		show:function(){
			var grpChkid = $("input[name=grpChkid]").val();
			var length = $("#guestinfo_roomList li").length;
			if(length>1){
				$("#retreatSecondRoomId").text($("#account_roomid").val());
				$("#retreatRoomFirstDiv").hide();
				$("#retreatRoomSecondDiv").show();
				if(t.isEmpty(grpChkid)){
					$("#checkoutIsGroup").text('联房');
					
				}else{
					$("#checkoutIsGroup").text('团队房');
				}
			}else{
				$("#retreatFirstRoomId").text($("#account_roomid").val());
				$("#retreatRoomFirstDiv").show();
				$("#retreatRoomSecondDiv").hide();
			}
			$(".alertDivBg2").fadeIn();
			$("#checkOutOperationDiv").fadeIn();
		},
		quit:function(){
			$(".alertDivBg2").fadeOut();
			$("#checkOutOperationDiv").fadeOut();
		}
	};
	
	/**
	 * 改单
	 */
	var _customAccountsObj = {
		flexGrid:null,
		initGrid:function(){
			   //改单账目
		 	var customAccountsGrid =  new wijmo.grid.FlexGrid('#customAccountsGrid', {
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
		    this.flexGrid = customAccountsGrid;
		},
		init:function(){
			this._unbindEvent();
			this._bindEvent();
			this.initGrid();
		},
		_unbindEvent:function(){
			$("#customAccounts").unbind('click');
			$("#customAccountAdd").unbind('click');
			$("#customAccountCancle").unbind('click');
			$("#customAccountDelete").unbind('click');
			$("#customAccountQuit").unbind('click');
		},
		_bindEvent:function(){
			$("#customAccounts").bind('click',function(){
				_customAccountsObj.show();
			});
			$("#customAccountQuit").bind('click',function(){
				_customAccountsObj.quit();
			});
			$("#customAccountAdd").bind('click',function(){
				_customAccountsObj.add();
			});
			$("#customAccountCancle").bind('click',function(){
				_customAccountsObj.cancle();
			});
			$("#customAccountDelete").bind('click',function(){
				_customAccountsObj.remove();
			});
			$("#customAccountsClose").bind('click',function(){
				_customAccountsObj.quit();
			});
		},
		show:function(){
			ajaxM.ajaxAction($,basePath+'/guest/guestAccountInfo.do',function(data)
				{
					console.log(JSON.stringify(data))
					var view = new wijmo.collections.CollectionView(data.bills);
					_customAccountsObj.flexGrid.itemsSource = view;
					$("#customAccountName").val(data.detail.gstNamec);
					$("#customAccountRoomId").val(data.detail.roomId);
					$("#customAccountCompId").val(data.detail.compId);
					$("#customAccountReachDate").val(data.detail.reachDate);
					$("#customAccountLeaveDate").val(data.detail.leaveDate);
					$("#customAccountRoomPrice").val(data.detail.price);
					$("#customAccountBorrow").val('1'==_guestObj.billType() ? changeTwoDecimal_f(data.detail.borrow) :data.detail.Bborrow);
					$("#customAccountLent").val('1'==_guestObj.billType() ? changeTwoDecimal_f(data.detail.lent) :changeTwoDecimal_f(data.detail.Blent));
					$("#customAccountRemain").val('1'==_guestObj.billType() ? changeTwoDecimal_f(parseFloat(data.detail.borrow)-parseFloat(data.detail.lent)) : changeTwoDecimal_f(parseFloat(data.detail.Bborrow)-parseFloat(data.detail.Blent)));
				},{checkId:_guestObj.checkId(),billId:_guestObj.billId(),billType:_guestObj.billType(),showType:'A',okFlag:'A',noGuest:'Y'});
			$(".alertDivBg2").fadeIn();
			$("#customAccountsDiv").fadeIn();
		},
		quit:function(){
			$(".alertDivBg2").fadeOut();
			$("#customAccountsDiv").fadeOut();
		},
		add:function(){
			var cv = this.flexGrid.itemsSource;
		    var data = cv.items;
		    data.push({
		      	
		    });
		    cv.refresh();
		},
		remove:function(){
			var rows = this.flexGrid.rows;
			var cv = this.flexGrid.itemsSource;
		    var data = cv.items;
			if(rows && rows.length>0){
				for(var i=0;i<rows.length;i++){
					if(rows[i].isSelected){
						cv.removeAt(i);
					}
				}
			}
			cv.refresh();
		},
		cancle:function(){
			this.show();
		}
	};
	
	/**
	 * 分账
	 */
	var _guestSplitInfoObj = {
		init:function(){
			this._unbindEvent();
			this._bindEvent();
		},
		_unbindEvent:function(){
			$("#guestSplitInfo").unbind('click');
			$("#guestSplitQuit").unbind('click');
			$("#guestSplitClose").unbind('click');
		},
		_bindEvent:function(){
			$("#guestSplitInfo").bind('click',function(){
				_guestSplitInfoObj.show();
			});
			$("#guestSplitQuit").bind('click',function(){
				_guestSplitInfoObj.quit();
			});
			$("#guestSplitClose").bind('click',function(){
				_guestSplitInfoObj.quit();
			});
		},
		show:function(){
			$("#account_consumeAID").empty();
			$("#account_consumeBID li").empty();
			ajaxM.ajaxAction($,basePath+'/guest/consumes.do',function(data)
				{
				if(data.consumes){
					for(var i = 0;i<data.consumes.length;i++){
						$("#account_consumeAID").append("<li id='"+data.consumes[i].codeId+"' >"+data.consumes[i].codeNamec+"</li>");
					}
				}
				if(data.blist){
					for(var i = 0;i<data.blist.length;i++){
						$("#account_consumeBID").append("<li id='"+data.blist[i].codeId+"'>"+data.blist[i].codeNamec+"</li>");
					}
				}
				$("#acc_startDate").val(data.bpaied.beginDate);
				$("#acc_endDate").val(data.bpaied.endDate);
			},{checkId:_guestObj.checkId()});
			$(".alertDivBg2").fadeIn();
			$("#guestSplitInfoDiv").fadeIn();
		},
		quit:function(){
			$(".alertDivBg2").fadeOut();
			$("#guestSplitInfoDiv").fadeOut();
		}
	};
	//初始完成后执行
	module.exports.load = function()
	{
		_depositObj.init();
		_receivesObj.init();
		_preAuthorizationObj.init();
		_finishAuthObj.init();
		_cancleAuthObj.init();
		_accountedForObj.init();
		_accountGrid.init();
		_transferAccountsObj.init();
		_cancleBillObj.init();
		_forLongObj.init();
		_leaveRoomObj.init();
		_checkOutObj.init();
		_checkOutRoomObj.init();
		_customAccountsObj.init();
		_guestSplitInfoObj.init();
		$("#accountsQuit").unbind('click');
		$("accountsQuit").bind('click',function(){
			$(".alertDivBg").fadeOut();
			$(".checkDetailsDiv").fadeOut();
		});
		$("#isInvalid").bind('click',function(){
			reloadAccount();
		});
	    $("input[name=radioAccount]").bind('click',function(){
	    	reloadAccount();
	    });
	    $("input[name=radioType]").bind('click',function(){
	    	reloadAccount();
	    });
	    $("input[name=disturb]").bind('click',function(){
	    	reloadAccount();
	    });
	    $("#account_endDate").bind('keypress',function(event){
	    	if(event.keyCode==13){
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
	    });
	    $("#accountsQuit").unbind('click');
	    $("#accountsQuit").bind('click',function(){
	    	$("#closeWindowsroom_guestdetail_info").trigger('click');
	    });
	    $("#guest_theCompany").bind('keypress',function(){
	    	if( $("#guest_theCompany").val()==''){
	    		$("#guest_compId").val('');
	    	}
	    });
	};
});