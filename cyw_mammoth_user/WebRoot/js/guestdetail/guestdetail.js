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
	var RoomsUtil = require("../rooms/plugins/RoomsUtils");
	var guestJS = require("./guestdetailSP");//guestJS 补充
	//var guestJS = null;

	//var guestJS = require("./guestdetailSP");//guestJS 补充
	require.async("./wards_continuedtolive.js");

	//添加验证
	$.formUtils.addValidator(
	{
	    name : 'isEmail',
	    validatorFunction : function(value, $el, config, language, $form) {
	    	var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	        return value==""?true:email.test(value);
	    },
	    errorMessage : '电子邮件格式不正确',
	    errorMessageKey: 'badCustomEmail'
	});
	$.formUtils.addValidator(
	{
	    name : 'isHanZi',
	    validatorFunction : function(value, $el, config, language, $form) {
	    	var text = /[^\u4e00-\u9fa5]/;
	        return !text.test(value);
	    },
	    errorMessage : '格式不正确',
	    errorMessageKey: 'badCustomHanZi'
	});
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

	/**
	*@描述 获取身份证信息
	*/
	var getBirthdatByIdNo = function(iIdNo)
	{
		if ($("#guestinfo_crdkindId").val() != '006001')
		return;
		var tmpStr = "";
		var idDate = "";
		var tmpInt = 0;
		var strReturn = "";

		iIdNo = $.trim(iIdNo);
		if(iIdNo=="") return;
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
		$("#guestinfo_birthday").val(tmpStr);
	};
	/**
	*@描述 添加客户信息
	*/
	var addGuest = function(flexGird)
	{
		var isChanged = rooms_model_cach['isChanged'];
		if(isChanged)
		{
			return;
		}else
		{

			flexGird.onSelectionChanging = function(e)
		    {
				return true;
			};
			rooms_model_cach['isChanged'] = true;
			var cv = flexGird.itemsSource;
			var data = cv.items;
			var roomId = $("#guestinfo_room_id").val();
			data.push({
				sortNum: data.length+1,
				gst_namec: "unknown",
				room_id:roomId,
				sexname: "男",
				chkExt: "同住",
				price: "",
				chkStat: "在住",
				check_id: "",
				chk_ext:'0',
				chk_stat:'I'
			});
			//用户信息
			$("#guestinfo_gstNamec").val("unknown");
			$("#guestinfo_gstNamee").val('');
	    	cv.refresh();
	    	cv.moveCurrentToLast();
	    	var paymanFlagObj = $("#guestinfo_payman_flag");
	    	paymanFlagObj.prop('checked',false);
		    paymanFlagObj.prop('disabled',false);
		    //设置离店时间和住店时间 住店时间为系统酒店日期
		    var hotelDateTime = $("#guestinfo_hoteldate").val();
		    hotelDateTime = hotelDateTime - 0;
		    var hotelDate = new Date();
		    hotelDate.setTime(hotelDateTime);
		    var leaveDate = new Date();
		    leaveDate.setTime(hotelDateTime + 24*60*60*1000);
		    //格式化离店日期和住店日期
		    var comeDate = new DateUtil(hotelDate);
		    var leave_Date = new DateUtil(leaveDate);
		    $("#guestinfo_reach_date").val(comeDate.format('yyyy-MM-dd'));
		    $("#guestinfo_reachDate").val(comeDate.format('yyyy-MM-dd'));
//		    $("#guestinfo_leave_date").val(leave_Date.format('yyyy-MM-dd'));
//		    $("#guestinfo_leaveDate").val(leave_Date.format('yyyy-MM-dd'));
		    $("#guestinfo_leave_date").val(new Date(extLeaveDate).format('yyyy-MM-dd'));
		    $("#guestinfo_leaveDate").val(new Date(extLeaveDate).format('yyyy-MM-dd'));
			buttonChange(flexGird);
		    flexGird.onSelectionChanging = function(e)
		    {
				return false;
			};
			$("#guestinfo_checkId").val('');
		}
	};

	/**
	*@描述 切换按钮可以状态
	*@param isAdd true 放弃确定可用 其余不可用 false 放弃确定不可用 其余可用
	*/
	var buttonSwtchAble = function(isAdd)
	{
		if(isAdd)
		{
			var guestChckId = $("#guestinfo_checkId").val();
			rooms_model_cach['guestChckId'] = guestChckId;
			$("#guestinfo_confirm").removeClass("disabledAButton");
			$("#guestinfo_giveup").removeClass("disabledAButton");
			$("#guestinfo_addGuest").addClass('disabledAButton');
			$("#guestinfo_copyGuest").addClass('disabledAButton');
			$("#guestinfo_scanCardbtn").addClass('disabledAButton');
			$("#guestinfo_sendRoomCard").addClass('disabledAButton');
			$("#guestinfo_changeRoom").addClass('disabledAButton');
			$("#guestinfo_guestDetailQuit").addClass('disabledAButton');
			$("#closeWindowsroom_guestdetail_info").hide();
		}else
		{
			$("#guestinfo_confirm").addClass("disabledAButton");
			$("#guestinfo_giveup").addClass("disabledAButton");
			$("#guestinfo_addGuest").removeClass('disabledAButton');
			$("#guestinfo_copyGuest").removeClass('disabledAButton');
			$("#guestinfo_scanCardbtn").removeClass('disabledAButton');
			$("#guestinfo_sendRoomCard").removeClass('disabledAButton');
			$("#guestinfo_changeRoom").removeClass('disabledAButton');
			$("#guestinfo_guestDetailQuit").removeClass('disabledAButton');
			$("#closeWindowsroom_guestdetail_info").show();
		}
	};
	/**
	*@描述 放弃当前操作
	*/
	var giveUpModify = function(view,flexGrid)
	{
		if($("#guestinfo_giveup").hasClass('disabledAButton'))
		{
			return;
		}
		//还原数据
		var dataList = rooms_model_cach['girdDatalist'];
		var sourceArray = new Array();
		t.copyToArray(dataList,sourceArray);
		rooms_model_cach['girdDatalist'] = sourceArray;
		var guestChckId = rooms_model_cach['guestChckId'];
		$("#guestinfo_checkId").val(guestChckId);
		var guestInfo = rooms_model_cach['guestInfo_' + guestChckId];
		rooms_model_cach['isChanged'] = false;//放弃后 数据修改为false
		RoomsUtil.UpdateWijomFlexGrid(flexGrid,dataList);
		guestInfoSetform('guestinfo_infoform',guestInfo);
		//buttonSwtchAble(false);
		guestJS.setBtnEnable(true);
		$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").unbind('change keyup');
		setGuestVlues(guestInfo);
		setGuestInfInputTxt(guestInfo);
		$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").bind('change keyup'
		,function()
		{
			buttonChange(flexGrid);
		});
	};

	/**
	*@描述 根据flexGrid对象创建数据对象
	*/
	var createNewGuestobject = function(guestInfoData)
	{
		var copyData = new Object();
		//创建复制用户信息 
		copyData['reachDate'] = guestInfoData['reach_date'];
		copyData['leaveDate'] =  guestInfoData['leave_date'];
		copyData['roomId'] = guestInfoData['room_id'];
		copyData['paymanFlag'] = guestInfoData['payman_flag'];
		copyData['cityLedger'] = guestInfoData['city_ledger'];
		copyData['housePay'] = guestInfoData['house_pay'];
		copyData['housePay'] = guestInfoData['free_svc'];
		copyData['hideprice'] = guestInfoData['hideprice'];
		copyData['fgst'] = guestInfoData['if_fgst'];
		var lentobject = new Object();
		lentobject['lent'] = 0 ;
		var borrowObject = new Object();
		borrowObject['Blent'] = 0;
		copyData['borrow-initData'] = lentobject;
		copyData['Bborrow-initData'] = borrowObject;
		copyData['chkExt'] = 0;
		copyData['namec'] = guestInfoData['namec'];
		copyData['compId'] = guestInfoData['comp_id'];
		copyData['compType'] = '';
		copyData['checkId'] = guestInfoData['check_id'];
		copyData['gstNamec'] = guestInfoData['gst_namec'];
		copyData['gstNamee'] = guestInfoData['gst_namee'];
		copyData['addr'] = guestInfoData['addr'];
		copyData['tele'] = guestInfoData['tele'];
		copyData['plateNumber'] = guestInfoData['plate_number'];
		copyData['crdId'] = guestInfoData['crd_id'];
		copyData['email'] = guestInfoData['email'];
		copyData['notice'] = guestInfoData['notice'];
		copyData['sexname'] = guestInfoData['sexname'];
		copyData['folk'] = guestInfoData['folk'];
		return copyData;
	};
	/**
	*@描述 复制客户信息
	*/
	var copyGuestInfo = function(flexGrid)
	{
		var isChanged = rooms_model_cach['isChanged'];
		if(isChanged) return;
		altInfMsg("确定要复制当前客人吗？",function(){
			//复制客人
			var sendDate = {'checkId':$("#guestinfo_checkId").val()};
			var url = basePath + "/guest/copyGuest.do";
			ajaxM.ajaxAction($,url,function(data)
			{
				t.debug("data=" + t.jsonToStr(data));
				if(data.success)
				{
					var cv = flexGrid.itemsSource;
					var datalist = cv.items;
					var guestInfoData = data.obj['guestdoc'];
					//创建复制用户信息 
					if(guestInfoData['sex']==='0260  ')
					{
						guestInfoData['sexname'] = '男';
					}else
					{
						guestInfoData['sexname'] = '女';
					}
					setGuestInfInputTxt(guestInfoData);
					$("#guestinfo_checkId").val(guestInfoData['checkId']);
					//更新gird
					flexGrid.onSelectionChanging = function(e)
				    {
						return true;
					};
					rooms_model_cach['isChanged'] = true;
					var cv = flexGrid.itemsSource;
					var data = cv.items;
					var roomId = $("#guestinfo_room_id").val();
					data.push({
						sortNum: data.length+1,
						gst_namec: guestInfoData['gstNamec'],
						room_id:guestInfoData['compId'],
						sexname: guestInfoData['sex']==='0260  '?'男':'女',
						chkExt: "同住",
						price: guestInfoData["roomPrice"],
						chkStat: "在住",
						check_id: "",
						chk_ext:'0',
						chk_stat:'I'
					});
					//用户信息
					$("#guestinfo_gstNamec").val(guestInfoData['gstNamec']);
					$("#guestinfo_gstNamee").val(guestInfoData['gst_namee']);
			    	cv.refresh();
			    	cv.moveCurrentToLast();
			    	var paymanFlagObj = $("#guestinfo_payman_flag");
			    	paymanFlagObj.prop('checked',false);
				    paymanFlagObj.prop('disabled',false);
					buttonChange(flexGrid);
				    flexGrid.onSelectionChanging = function(e)
				    {
						return false;
					};
				}else
				{
					altEerrMsg('复制失败');
				}
			},sendDate);
		},function()
		{
		});
	};
	/**
	*@描述 修改
	*/
	var buttonChange = function(flexGirg)
	{
		flexGirg.onSelectionChanging = function(e){
			return false;
		};
		//buttonSwtchAble(true);
		guestJS.setBtnEnable(false);
		rooms_model_cach['isChanged'] = true;
		guestChineseNameToEnglis(flexGirg);//中文名称转化英文
	};

	/**
	* @描述 初始化国家
	* @param selectId下拉框ID
	* @param url
	*/
	var initSelect = function(selectId,url)
	{
		var selectObj = $("#" + selectId);
		selectObj.empty();
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
	*@描述 gird监听事件的改变
	*@param flexGrid gird对象
	*@param canChange 是否可改变 true 可以 false 不可
	*/
	var setFlexGridChange = function(view,flexGrid,canChange)
	{
		if(canChange)
		{
			flexGrid.onSelectionChanging = function(e)
		    {
				return true;
			};
			view.currentChanged.addHandler(function (sender, args)
			{
				var currData = view.currentItem;
				var checkId = currData['check_id'];
				if(t.isEmpty(checkId))
				{
					return;
				}
				var guestInfoData = createNewGuestobject(currData);
				//防止因为设置新值而导致的问题
				$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").unbind('change keyup');
				setGuestInfInputTxt(guestInfoData);
				setGuestVlues(guestInfoData);
				$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").bind('change keyup'
				,function()
				{
					buttonChange(flexGrid);
				});
			});
		}else
		{
			flexGrid.onSelectionChanging = function(e)
		    {
				return false;
			};
		}
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
	*@描述 中英文名称转化
	*/
	var guestChineseNameToEnglis = function(flexGrid)
	{
		var enlishNameInput = $("#guestinfo_gstNamee");
		$("#guestinfo_gstNamec").unbind("keyup");
		$("#guestinfo_gstNamec").bind("keyup",function(event)
		{
			enlishNameInput.val(pinyin.getFullChars($(this).val()));
		});
	};
	/**
	 * @描述 将表单中输入框 转化成json格式
	 * */
	var getFormJson = function(frm)
	{
		var o = {};
		var a = $(frm).serializeArray();
		$.each(a, function () {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [o[this.name]];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});

		return o;
	};

	/**
	*@获取修改客户信息
	*/
	var getSaveguestData = function()
	{
		var formData = getFormJson(document.getElementById("guestinfo_infoform"));
		formData.paymanFlag=document.getElementById("guestinfo_payman_flag").checked?"1":"0";
		formData.cityLedger=document.getElementById("guestinfo_city_ledger").checked;
		formData.housePay=document.getElementById("guestinfo_house_pay").checked ;
		formData.freeSvc=document.getElementById("guestinfo_free_svc").checked;
		formData.hideprice=document.getElementById("guestinfo_hideprice").checked;
		formData.ifFgst=document.getElementById("guestinfo_if_fgst").checked;
		formData.Alimit = $("#guestinfo_Alimit").val()==''?"0":$("#guestinfo_Alimit").val();
		formData.Blimit = $("#guestinfo_Blimit").val()==''?"0":$("#guestinfo_Blimit").val();
		formData.chkStat = 'I';
		formData.compId = $("input[name=compId][type=hidden]").val();
		formData.compType = $("input[name=compType][type=hidden]").val();
		formData.isRoomPlan = $("#guestinfo_isRoomPlan").val();
		return formData;
	};

	/**
	*@描述 行切换事件
	*/
	var gridItemChanged = function(gridView)
	{
		gridView.currentChanged.addHandler(function(sender, args)
		{
			var currData = gridView.currentItem;
			t.debug("currData" + t.jsonToStr(currData));
		});
	};
	/**
	*根据现有信息判断是保存还是修改
	*@param view列表 
	*@param grid flexGirg
	*/
	var saveOrUpdateMsg = function(view,grid)
	{
		altInfMsg("是否保存客户信息？"
			,function()
				{
					var formData = getSaveguestData();
					var url;
					if(formData.checkId)
					{
						url = basePath + "/guestdetail/updateGuest.do";
					}else
					{
						var grpChkid = $("input[name=grpChkid]").val();
						if(grpChkid!=''){
							formData.gstFlag = 'G';
							formData.grpChkid = grpChkid;
						}else{
							formData.gstFlag = 'F';
						}
						url = basePath + "/guestdetail/saveGuest.do";
					}
					//根据checkID 检索 列表位置
					var thisGuestCheckId = formData['checkId'];
					ajaxM.ajaxAction($,url,function(data)
					{
						if(data.success)
						{
							$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").unbind('change keyup');
							altSuccessMsg(data.msg);
							view = RoomsUtil.UpdateWijomFlexGrid(grid,data.guestList);
							//设置gird表格指针位置
							var position = 0;
							if(t.isEmpty(thisGuestCheckId))
							{
								position = data.guestList.length -1;
							}else
							{
								for(var i=0;i<data.guestList.length;i++)
								{
									var guestEntity = data.guestList[i];
									if(thisGuestCheckId===guestEntity['check_id'])
									{
										position = i;
										break;
									}
								}
							}
							//移动至修改位置
							view.moveCurrentToPosition(position);
							var gridDateList = new Array();
							t.copyToArray(data.guestList,gridDateList);
							rooms_model_cach['girdDatalist'] = gridDateList;
							var guestChckId = data.guestDoc['checkId'];
							rooms_model_cach['guestInfo_' + guestChckId] = data.guestDoc;
							rooms_model_cach['guestChckId'] = guestChckId;
							rooms_model_cach['isChanged'] = false;
							guestJS.setBtnEnable(true);//设置可用
							setFlexGridChange(view,grid,true)
							$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").bind('change keyup'
							,function()
							{
								buttonChange(grid);
							});
						}else
						{
							altEerrMsg(data.msg);
						}
					},formData);
				}
				,function()
				{
				}
		);
	}
	/**
	 * @描述 保存客户信息
	 * @param view列表 
	 * @param grid flexGirg
	 * */
	var saveGuestInfo = function (view,grid)
	{
		$("#guestinfo_confirm").unbind("click");
		$("#guestinfo_confirm").bind("click", function (event)
		{
			//是否可点击
			if($(this).hasClass('disabledAButton'))
			{
				return;
			}
			//是否表单验证通过
			if(!formValiData())
			{
				return;
			}
			//身份证格式验证
			var cardKind = $("#guestinfo_crdkindId").val();
			if(cardKind=='006001')
			{
				var cardValue = $("#guestinfo_crdId").val();
				var text = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
				if(cardValue!="" && !text.test(cardValue))
				{
					altWaringMsg('身份证格式不正确');
					return;
				}
			}
			saveOrUpdateMsg(view,grid);
		});
	};

	/**
	 * @描述 表单验证
	 * */
	var formValiData = function()
	{
		var errors = [];
		var conf = {
			onError : function($form) {
				//alert('验证表单 '+$form.attr('id')+' 失败!');
			},
			onSuccess : function($form) {
				//alert('表单 '+$form.attr('id')+' 验证通过!');
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
		if(!$("#guestinfo_infoform").isValid(lang, conf, false) ) {
			if(errors.length>0){
				altWaringMsg(errors[0].el.attr('label')+"  "+errors[0].error);
				return false;
			}
		} else {
			return true;
		}
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
	 * @描述 初始化客户来源下拉框
	 * */
	var initGuestOrc = function (guestOrcId)
	{
		var guestObj = $("#guestinfo_gstOriId");
		guestObj.empty();
		var url = basePath + "/guest/getGstOriByOricode.do";
		ajaxM.ajaxAction($,url, function (data)
		{
			var optionHtml = template('guestinfo_guestorc_option',{'LIST':data});
			guestObj.append(optionHtml);
			guestObj.val(guestOrcId);
		},{'codeId':guestOrcId});
	}

	/**
	*@描述  设置客户信息
	*/
	var setGuestInfInputTxt = function(guestInfo)
	{	var gstNamec = t.trim(guestInfo['gstNamec']);//中文名称
		if(gstNamec.indexOf("*")!=-1)
		{
			gstNamec = gstNamec.substring(1,gstNamec.length);
			$("#page_titleroom_guestdetail_info").html("客单详情（离店）");
			guestJS.SetBillBtnEnable(false);
		}else
		{
			$("#page_titleroom_guestdetail_info").html("客单详情（在住）");
			guestJS.SetBillBtnEnable(true);
		}
		$("#guestinfo_gstNamec").val(gstNamec);
		$("#guestinfo_gstNamee").val(t.trim(guestInfo['gstNamee']));
		$("[name='tele']").val(t.trim(guestInfo['tele']));
		$("[name='email']").val(t.trim(guestInfo['email']));
		$("[name='plateNumber']").val(guestInfo['plateNumber']);
		$("[name='crdId']").val(guestInfo['crdId']);
		$("[name='addr']").val(guestInfo['addr']);
		$("[name='notice']").val(guestInfo['notice']);
		getBirthdatByIdNo(guestInfo['crdId']);
		var gender = guestInfo['sexname'];
		if(gender==='男')
		{
			$("#guestinfo_sex").val('0260  ');
		}else
		{
			$("#guestinfo_sex").val('0261  ');
		}
		if(!t.isEmpty(guestInfo['folk']))
		{
			$("#guestinfo_folks").val(t.trim(guestInfo['folk']));
		}
		$("#guestinfo_country").val(t.trim(guestInfo['ntId']));
		$("[name='native_']").val(guestInfo['native_']);
		$("#guestinfo_guestRoomId").val(guestInfo['checkId']);
		$("#guestinfo_gstNamec").trigger('keyup');
	};
	/**
	*@描述 获取改房屋客户信息
	*/
	var getRoomGuestInfo = function(roomId,flexGird)
	{
		var url = basePath + "/guestdetail/getRoomGuestInfo.do";
		var sendData = {'roomId':roomId};
		ajaxM.ajaxAction($,url,function(data)
		{
			$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").unbind('change keyup');
			var gridDatalist = data.guestList;
			var guestDoc = data.guestDoc;
			//重新缓存数据
			rooms_model_cach = new Array();
			var gridDateList = new Array();
			t.copyToArray(gridDatalist,gridDateList);
			//更新gird
			var view = RoomsUtil.UpdateWijomFlexGrid(flexGird,gridDateList);
			setFlexGridChange(view,flexGird,true)
			rooms_model_cach['girdDatalist'] = gridDateList;
			rooms_model_cach['guestInfo_' + guestDoc['checkId']] = guestDoc;
			rooms_model_cach['guestChckId'] = guestDoc['checkId'];
			setGuestVlues(guestDoc);
			setGuestInfInputTxt(guestDoc);
			var gstNameeSelHtml = template('guestinfo_guestorc_gstNemee',{'LIST':gridDatalist});
			var accouteObj = $("#account_namee");
			accouteObj.empty();
			accouteObj.append(gstNameeSelHtml);
			var gstNamecSelHtml = template('guestinfo_guestorc_gstNemec',{'LIST':gridDatalist});
			var accoutcObj = $("#account_namec");
			accoutcObj.empty();
			accoutcObj.append(gstNamecSelHtml);
			$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").bind('change keyup'
			,function()
			{
				buttonChange(flexGird);
			});
			guestJS.setBtnEnable(true);
			//$("#guestinfo_gstNamec").trigger('keyup');
			view.moveCurrentToFirst();
			guestJS.RoomGuestPriceScheme();
			$("#guestdetail_prcSchemeId").trigger('change',['0']);//执行房价方案
		},sendData);
	};

	/**
	 * @描述 设置input 值
	 * */
	var setGuestVlues = function (initData)
	{
		$("#guestinfo_reach_date").val(new DateUtil(new Date(initData.reachDate)).format('yyyy-MM-dd'));
		$("#guestinfo_leave_date").val(new DateUtil(new Date(initData.leaveDate)).format('yyyy-MM-dd'));
		$("#guestinfo_reachDate").val(new DateUtil(new Date(initData.reachDate)).format('yyyy-MM-dd'));
		$("#guestinfo_leaveDate").val(new DateUtil(new Date(initData.leaveDate)).format('yyyy-MM-dd'));
		$("#guestinfo_room_id").val(initData.roomId);
		$("#guestinfo_roomId").val(initData.roomId)
		$("#account_roomid").val(initData.roomId);
		$("#guestinfo_payman_flag").prop('checked',1==initData.paymanFlag?'checked':false);
		$("#guestinfo_payman_flag").prop('disabled',1==initData.paymanFlag?true:false);
		$("#guestinfo_city_ledger").prop('checked',initData.cityLedger?'checked':false);
		$("#guestinfo_house_pay").prop('checked',initData.housePay?'checked':false);
		$("#guestinfo_free_svc").prop('checked',initData.freeSvc?'checked':false);
		$("#guestinfo_hideprice").prop('checked',initData.hideprice?'checked':false);
		$("#guestinfo_if_fgst").prop('checked',initData.fgst?'checked':false);
		//a账余额
		var remainA = $("#guestinfo_remainA");
		remainA.removeProp('disabled');
		remainA.val(t.ChangeTwoDecimalNumber(Number(initData.borrow-initData.lent)));
		remainA.prop('disabled',true);
		var remainB = $("#guestinfo_remainB");
		remainB.removeProp('disabled');
		remainB.val(t.ChangeTwoDecimalNumber(Number(initData.Bborrow-initData.Blent)));
		remainB.prop('disabled',true);
		$("#guestinfo_nowPrice").prop('disabled',(initData.chkExt == '0'));
		$("#guestinfo_discount").prop('disabled',(initData.chkExt == '0'));
		$("#guestinfo_theCompany").val(initData.namec);
		$("#guestinfo_company_id").val(initData.compId);
		$("#guestinfo_ta_type").val(initData.compType);
		$("#guestinfo_checkId").val(initData.checkId);
		RoomsUtil.InitPictureSrc("crdPhoto",initData.checkId);
		$("#guestinfo_country").val(initData.ntId);
		//$("#guestinfo_folks").val(initData.folk);
	}

	/**
	*@描述 打开房间方案
	*/
	var openRoomPriceList = function()
	{
		var url = basePath + "/guestdetail/getPriceList.do";
		var checkId = $("#guestinfo_checkId").val();
		var withId = $("#guestinfo_withId").val();
		var roomId = $("#guestinfo_room_id").val();
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

	/**
	 * @初始化操作
	 * */
	var initLoad = function (guests)
	{
		//初始化客户来源
		var guestGrid =  new wijmo.grid.FlexGrid('#guestinfo_guestRoomId', {
			autoGenerateColumns: false,
			columns: [
				{ header: '序号', binding:'sortNum',width:50 },
				{ header: '中文名', binding:'gst_namec' ,width:'*'},
				{ header: '性别', binding:'sexname' ,width:'*'},
				{ header: '身份', binding:'chkExt',width:'*'},
				{ header: '房租', binding:'room_price', width:'*' },
				{ header: '状态', binding:'chkStat',width:'*' },
				{ header: '登记号', binding:'check_id',width:'*' }
			],
			headersVisibility:wijmo.grid.HeadersVisibility.Column,
			selectionMode:'Row',
			isReadOnly:true
		});
		var view = new wijmo.collections.CollectionView(guests);
		guestGrid.itemsSource = view;
		saveGuestInfo(view,guestGrid);
		gridItemChanged(view);
		//缓存数据
		var gridDateList = new Array();
		t.copyToArray(guests,gridDateList);
		rooms_model_cach['girdDatalist'] = gridDateList;
		var guestInfo = getSaveguestData();
		var guestChckId = $("#guestinfo_checkId").val();
		rooms_model_cach['guestInfo_' + guestChckId] = guestInfo;
		rooms_model_cach['guestChckId'] = guestChckId;
		//绑定取消
		$("#guestinfo_giveup").unbind('click');
		$("#guestinfo_giveup").bind("click",function(event)
		{
			giveUpModify(view,guestGrid);
		});
		//监听修改啊
		$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").unbind('change keyup');
		$("#guestinfo_infoform input[id!=accountSetId],#guestinfo_infoform select[id!=guestinfo_gstOriId]").bind('change keyup'
			,function()
			{
				buttonChange(guestGrid);
			});
		//添加住客信息 copyGuestInfo
		$("#guestinfo_addGuest").unbind('click');
		$("#guestinfo_addGuest").bind('click',function(event)
		{
			addGuest(guestGrid);
		});
		//复制用户信息
		$("#guestinfo_copyGuest").unbind("click");
		$("#guestinfo_copyGuest").bind('click',function(event)
		{
			copyGuestInfo(guestGrid);
		});
		//点击房间刷新页面
		$("[guestinfo-roomid]").unbind("click");
		$("[guestinfo-roomid]").bind("click",function(event)
		{
			//判断是否可点
			if($(this).hasClass('clickNo'))
			{
				return;
			}
			$("[guestinfo-roomid]").removeClass('roomListLi');
			$(this).addClass('roomListLi');
			var roomId = $(this).attr("guestinfo-roomid");
			getRoomGuestInfo(roomId,guestGrid);
		});
		view = RoomsUtil.UpdateWijomFlexGrid(guestGrid,guests);
		setFlexGridChange(view,guestGrid,true);
		view.moveCurrentToFirst();
		//设置账目显示格式 2015-1-4
		$("#guestinfo_normalPrice").val(t.ChangeTwoDecimalNumber(detail.price));
		$("#guestinfo_remainA").val(t.ChangeTwoDecimalNumber(detail.borrow-detail.lent));
		$("#guestinfo_Alimit").val(t.ChangeTwoDecimalNumber(detail.alimit));
		$("#guestinfo_nowPrice").val(t.ChangeTwoDecimalNumber(detail.roomPrice));
		$("#guestinfo_remainB").val(t.ChangeTwoDecimalNumber(detail.bborrow-detail.blent));
		$("#guestinfo_Blimit").val(t.ChangeTwoDecimalNumber(detail.blimit));
		guestJS.load();
		//$("#guestinfo_gstNamec").trigger('keyup');
	};

	/**
	*@描述 监听扫描身份证按钮
	*/
	var scanIDCard = function()
	{
		$("#guestinfo_scanCard").unbind("click");
		$("#guestinfo_scanCard").bind('click',function(event)
		{
			var roomId = $("#guestinfo_room_id").val();
			var checkId = $("#guestinfo_checkId").val();
			var result = RoomsUtil.ScanOutIdCard(checkId + "," + roomId,true);
			if(result==='true')
			{
				//idcard.runIDCard(checkId + "," + roomId);
				RoomsUtil.InitPictureSrc("crdPhoto",checkId);
				$(".roomListLi").trigger('click');
			}else if(result==='false')
			{
				altWaringMsg("身份证扫描失败");
			}
		});
	};

	/**
	*@描述 生产出生日期
	*/
	var createBrithDay = function()
	{
		$("#guestinfo_crdId").unbind("blur");
		$("#guestinfo_crdId").bind("blur",function(event)
		{
			var cardId = $(this).val();
			getBirthdatByIdNo(cardId);
		});
	};
	/**
	*@描述  关闭面板后设置值
	*/
	var closePannelSetValues = function()
	{
		var gstNamee = $("#otherguestinfo_otherNamee").val();//英文名称
		var gender = $("#otherguestinfo_otherSex").val();//性别
		var country = $("#otherguestinfo_otherCountry").val();//国家
		var crdKind = $("#otherguestinfo_otherCrdkindId").val();//证件类型
		var cardNumber = $("#otherguestinfo_otherCrdId").val();//证件名称
		var visaType = $("#otherguestinfo_visakindId").val();//签证类型
		var visaNumber = $("#otherguestinfo_visaId").val();//签证号码
		var depart = $("#otherguestinfo_depart").val();//签证部门
		var crdVld = $("#otherguestinfo_crdVld").val();//有效日期
		var inDate = $("#otherguestinfo_inDate").val();//入境日期
		var inPort = $("#otherguestinfo_inPort").val();//入境口岸
		$("#guestinfo_gstNamee").val(gstNamee);
		$("#guestinfo_sex").val(gender);
		$("#guestinfo_country").val(country);
		$("#guestinfo_crdkindId").val(crdKind);
		$("#guestinfo_visakindId").val(visaType);
		$("#guestinfo_visaId").val(visaNumber);
		$("#guestinfo_depart").val(depart);
		$("#guestinfo_crdVld").val(crdVld);
		$("#guestinfo_inDate").val(inDate);
		$("#guestinfo_inPort").val(inPort);
		$("#guestinfo_crdId").val(cardNumber);
		getBirthdatByIdNo(cardNumber);
	};

	/**
	*@打开其他信息界面
	*/
	var openOtherInfo = function()
	{
		$("#otherInformation").unbind("click");
		$("#otherInformation").bind("click",function(event)
		{
			var url = basePath + "/guestdetail/getOtherGuestdocInfo.do";
			//中文名 英文名 性别 证件号码 证件类型 国籍
			var checkId = $("#guestinfo_checkId").val();
			var gstNamee = $("#guestinfo_gstNamee").val();
			var gender = $("#guestinfo_sex").val();
			var idNumber = $("#guestinfo_crdId").val();
			var crdkindId = $("#guestinfo_crdkindId").val();
			var nid = $("#guestinfo_country").val();
			var sendData = {"checkId":checkId,'gstNamee':gstNamee
							,'sex':gender,'crdId':idNumber
							,'ntId':nid,'crdkindId':crdkindId};
			var params = {type:'page',title:'其他信息'
						,id:'guesdocdetail_other_info'
						,isUpdate:true
						,width:830
						,content:url
						,titleClass:'alertDiv moveBar2 alertDiv2 otherDiv'
						,data:sendData
						,show:function()
						{
							$.fn.alertDialogShow(params.id);
						}
						,cancel:function()
						{
							closePannelSetValues();
						}};
			$.fn.alertDialog(params); 
		});
	};
	//初始完成后执行
	$(function()
	{
		setTimeout(function()
		{
			initLoad(guests);//初始化
			scanIDCard();
			createBrithDay();//出生日期
			//房间列表
			$("#guestinfo_pricesList").unbind('click');
			$("#guestinfo_pricesList").bind('click',function()
			{
				openRoomPriceList();
			});
			var checkId = $("#guestinfo_checkId").val();
			RoomsUtil.InitPictureSrc("crdPhoto",checkId);
			openOtherInfo();//其他信息按钮监听
		},100);
	});
});