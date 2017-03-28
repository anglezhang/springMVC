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
	 * 获取记账类型（A账或B账）
	 */
	var _guestObj = {
		billId:function(){
			return $("#noguest_billid").val();
		},
		billType:function(){
			return '1';
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
		ajaxM.ajaxAction($,basePath+'/noguest/loadNoguestAccounts.do',function(data)
			{
				var view = new wijmo.collections.CollectionView(data);
				_accountGrid.flexGrid.itemsSource = view;
			}
			,{'billId':_guestObj.billId(),'showType':showType,'okFlag':okFlag,'startDate':startDate,'endDate':endDate,'isInvalid':isInvalid}
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
	};
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
	};
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
			billType:_guestObj.billType()
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
    	var topData = getTransferData('3',{id:$("#noguest_account_ID").val(),showType : 'A',okFlag : 'N'});
    	setTransferTopData(topData);
    	//加载下方数据
    	var selectedItem = _transferAccountsObj.flexGrid.itemsSource.currentItem;
    	var bottomSearchData = {
    		checkId : selectedItem.check_id,
    		showType : 'A',
    		okFlag : 'N'
    	};
    	if(type=='3' && selectedItem.id == $("#noguest_account_ID").val()){
    		altWaringMsg('转出目标与转入目标相同，请重新选择!');
    		return;
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
		if(data.detail){
			$("input[name=transferTopRadio][value=2]").prop('disabled','disabled');
			$("input[name=transferTopRadio][value=1]").prop('checked','checked');
			$("#transferTopNamec").val(data.detail.nogstName);
			$("#transferTopNamee").val('');
			$("#transferTopRoomId").val('');
			$("#transferTopNotice").val(data.detail.notes);
			$("#transferTopBillId").val(data.detail.billId);
			$("#transferTopBorrow").val(data.detail.nBorrow);
			$("#transferTopLent").val(data.detail.nLent);
			$("#transferTopRemain").val(changeTwoDecimal_f(parseFloat(data.detail.nBorrow)-parseFloat(data.detail.nLent)));
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
			this.flexGrid = accountGrid;
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
			formData.moneyKind = $("#receivablesMoneyKind").val();
			formData.billId = _guestObj.billId();
			formData.billType = _guestObj.billType();
			formData.type="N";
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
				$("#accountForm input").bind('keyup',function(event){
					if(event.keyCode!='13'){
						_accountedForObj.inputButton();
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
				$("#accountForm input").inputmask();
			},
			form : $("#accountForm"),
			show : function() {
				this.form.resetForm();
				var citem= grid1.itemsSource.currentItem;
				var noguestId=citem.id;
				ajaxM.ajaxAction($,basePath+'/noguest/loadNoguestAccount.do',function(data)
					{
						setFormData("accountForm", data.entity);
						$("#accountlimit").val(changeTwoDecimal_f(parseFloat($("#nLimit").val())-parseFloat($("#nRemain").val())));
					},{noguestId:noguestId});
				$(".alertDivBg2").fadeIn();
				$("#accountedForDiv").fadeIn();
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
				formData.billType = '1';
				formData.moneyKind = $("#accountExchange").val();
				formData.type = 'A';
				formData.checkType = 'N';
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
					},{ids:ids.join(','),bills:JSON.stringify(payArr),oddMoney:oddMoney,
					billId:_guestObj.billId(),billType:_guestObj.billType(),tipMoney:tipMoney});
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
					},{'id':_selected.dataItem.ID,'payNum':_selected.dataItem.payNum,'billId':_guestObj.billId(),'okFlag':_selected.dataItem.okFlag});
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
			$("#transferGuestInfo").html($("#f_nogstName").val());
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
				data:{topBillId:$("#transferTopBillId").val(),bottomBillId:$("#transferBottomBillId").val(),transferData:JSON.stringify(_transferAccountsObj.transferArr)},
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
	
	//初始完成后执行
	$(function(){
		_receivesObj.init();
		_accountGrid.init();
		_transferAccountsObj.init();
		_cancleBillObj.init();
		_accountedForObj.init();
		_checkOutObj.init();
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
	    $("#noguestAccountsQuit").unbind('click');
	    $("#noguestAccountsQuit").bind('click',function(){
	    	$(".alertDivBg").fadeOut();
			$("#nonResidentDiv").fadeOut();
	    });
	    $("#guest_theCompany").bind('keypress',function(){
	    	if( $("#guest_theCompany").val()==''){
	    		$("#guest_compId").val('');
	    	}
	    });
	});
});