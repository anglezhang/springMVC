define(function(require, exports, module)
{
	var t = require("../../frame/Tools");

	/**
	*@描述 全选 or 全部取消
	*@param btnId 按钮ID
	*@param InputName 多选框按钮
	*/
	var selectAll = function(btnId,InputName)
	{
		$("#"+btnId).unbind('click');
		$("#"+btnId).bind('click',function(event)
		{
			var check = $(this).prop("checked");
			$("[name='" + InputName + "']").each(function(index, el) 
			{
				if(check)
					$(el).prop("checked",true);
				else
					$(el).prop("checked",false);
			});
		});
	};

	/**
	*@描述 确定按钮
	*@param btnId 按钮ID
	*@param InputName 多选框按钮
	*@param txtInptId 要设置的text InputId
	*@param viewtextId 展示的input
	*/
	var confromBtn = function(btnId,InputName,txtInptId,viewtextId)
	{
		$("#"+btnId).unbind('click');
		$("#"+btnId).bind("click",function(event)
		{
			event.stopPropagation();
			//处理
			var closeWindowId = $(this).prev().attr("closewindow");
			$("#" + closeWindowId).trigger('click');
			var types = "";
			var values = "";
			$("[name='" + InputName + "']:checked").each(function(index, el) {
				types += $(el).val() + ",";
				values += $(el).attr("data-text") + ",";
			});
			types = types.substring(0,types.length-1);
			values = values.substring(0,values.length-1);
			$("#" + txtInptId).val(values);
			$("#" + viewtextId).val(types);
		});
	};

	/**
	*@描述 全选或者取消全选
	*/
	var setSelAll = function()
	{
		$("#rooms_type_selecall").unbind("click");
		$("#rooms_type_selecall").bind("click",function(event)
		{
			var check = $(this).prop("checked");
			$("[name='rooms_types_value']").each(function(index, el) 
			{
				var txtInput = $(el).parent().next().find("[name='rooms_types_number']");
				var count = txtInput.parent().next().attr("room-count") - 0 ;
				if(check)
				{
					$(el).prop("checked",true);
					txtInput.val("1");
				}
				else
				{
					$(el).prop("checked",false);
					txtInput.val("0");
				}
				var txtVal = txtInput.val();
				txtVal = txtVal - 0;//转整数
				if(txtVal>count)
					txtInput.addClass('errorColor');
				else
					txtInput.removeClass('errorColor');
			});
		});
	};

	/**
	* 房类数字填充
	*/
	var setFangleiNumber = function()
	{
		//数字填充
		if(!t.isEmpty(typeValues))
		{
			var array = t.strToJson(typeValues);
			
			$("[name='rooms_types_value']").each(function(index, el)
			{
				for(var i=0;i<array.length;i++)
				{
					var obj = array[i];
					for(k in obj)
					{
						var elVal = $(el).val();
						if(elVal===k)
						{
							$(el).parent().next().find("[name='rooms_types_number']").val(obj[k]);
							$(el).prop("checked",true);
						}
					}
				}
			});
		}
	};
	/**
	*@描述 数量输入校验 只可输入数字
	*/
	var inputNumberCheck = function()
	{
		var allCheckBox = $("#rooms_type_selecall");
		//输入验证
		$("[name='rooms_types_number']").unbind("keyup");
		$("[name='rooms_types_number']").bind("keyup",function(event)
		{
			var regex = /^\s*(\+|-)?((\d+([\.,]\d+)?)|([\.,]\d+))\s*$/;//数字验证
			var inputObj = $(this);
			var count = inputObj.parent().next().attr("room-count") - 0 ;
			if(!regex.test(inputObj.val()) && inputObj.val() != '')
			{
				inputObj.val('');
			}
			//输入数字 大于 总数
			var txtVal = inputObj.val();
			txtVal = txtVal - 0;//转整数
			if(txtVal<=count)
			{
				inputObj.removeClass('errorColor');
			}else
			{
				//inputObj.addClass('errorColor');
				inputObj.val(count);
			}
		});
		//选中未选中状态切换
		$("[name='rooms_types_number']").unbind("focusout");
		$("[name='rooms_types_number']").bind("focusout",function(event)
		{
			var inputObj = $(this);
			var numberVal = inputObj.val();
			var inputCheckBox = inputObj.parent().prev().find("[name='rooms_types_value']");
			if(t.isEmpty(numberVal) || numberVal==='0')
			{
				inputCheckBox.prop("checked",false);
				//allCheckBox.prop("checked",false);
			}else
			{
				inputCheckBox.prop("checked",true);
			}
			var checkedInputs = $("[name='rooms_types_value']:not(:checked)");
			if(checkedInputs.length>0)
			{
				allCheckBox.prop("checked",false);
			}
		});
		//选中txt
		/*$("[name='rooms_types_number']").unbind('focus');
		$("[name='rooms_types_number']").bind('focus',function(event)
		{
			$(this).select();
		});*/
	};


	/**
	*@描述 房间类型点击按钮
	*/
	var confirmBtnClick = function()
	{
		$("#rooms_types_confirm").unbind("click");
		$("#rooms_types_confirm").bind("click",function(event)
		{
			var autoRowRooms = $("#rooms_btn_rowhouse");//自动排房按钮
			autoRowRooms.attr("able-stat","false");
			autoRowRooms.attr("style","color: grey;cursor:not-allowed;");
			//对数字的验证
			var Names = "";
			$(".errorColor").each(function(index, el) {
				//房类名称
				var name = $(el).parent().prev().prev().html();
				Names += name + " 输入数量太大 <br>";
				return;
			});
			if(!t.isEmpty(Names))
			{
				var params = {type:'error',content:Names,title:'告警提示'
                ,id:'roomtype_tips_erroemsg'};
				$.fn.alertDialog(params); 
			    $.fn.alertDialogShow(params.id);
			    return;
			}else
			{
				$("#closeWindowsroom_typeinfo_list").trigger('click');
				var types = "";
				var typesVal = "";
				//缓存数量和对象名称
				var typeArray = new Array();
				$("[name='rooms_types_value']:checked").each(function(index, el) {
					var obj = new Object();
					var key = $(el).val();
					var value = $(el).attr("room-codeid");
					types += key + ",";
					typesVal += value + ",";
					var number = $(el).parent().next().find("[name='rooms_types_number']").val();
					obj[key]=number;
					typeArray.push(obj);
				});
				types = types.substring(0,types.length-1);
				typesVal = typesVal.substring(0,typesVal.length-1);
				typeValues = t.jsonToStr(typeArray);
				$("#rooms_roomchoose_typevalues").val(t.jsonToStr(typeArray));
				$("#rooms_roomchoose").val(types);
				$("#rooms_roomchoose_value").val(typesVal);		
				//将自动排房设置为可用
				autoRowRooms.attr("able-stat","true");
				autoRowRooms.removeAttr("style");
			}
		});
	};

	/**
	*@描述 单点选中未选中
	*/
	var switchCheckbox = function()
	{
		$("[name='rooms_types_value']").unbind("click");
		$("[name='rooms_types_value']").bind("click",function(event)
		{
			event.stopPropagation();
			var sel = $(this).prop("checked");
			var numberInput = $(this).parent().next().find("[name='rooms_types_number']");
			var roomCount = $(this).parent().next().next().attr("room-count");
			/*roomCount-0;
			if(roomCount==0)
			{
				return;
			}*/
			if(sel)
			{
				numberInput.val('1');
				if(roomCount==0)
				{
					numberInput.addClass('errorColor');
				}
			}else
			{
				numberInput.val('0');
				numberInput.removeClass('errorColor');
				$("#rooms_type_selecall").prop("checked",false);
			}
			checkedAll('rooms_type_selecall','rooms_types_value');
		});
	};

	/**
	*@描述 取消按钮
	*@param btnId 按钮id
	*/
	var clickCancleBtn = function(btnId)
	{
		$("#"+btnId).unbind('click');
		$("#"+btnId).bind('click',function(event) {
			var closeBtnId = $(this).attr("closewindow");
			$("#" + closeBtnId).trigger('click');
		});
	};

	/**
	*@描述 判断是否全部选中
	*@param allName 选中全部checkbox的主键
	*@param checkName 选中的box
	*/
	var checkedAll = function(allName,checkName)
	{
		var checkBoxNumbers = $("[name='" + checkName + "']").length;
		var checkedNumbers = 0;
		$("[name='" + checkName + "']").each(function(index, el) 
		{
			var isCheck = $(el).prop("checked");
			if(isCheck)
			{
				checkedNumbers++;
			}
		});
		if(checkBoxNumbers==checkedNumbers)
		{
			$("#" + allName ).prop("checked",true);
		}
	};

	/**
	*@描述 判断是否全部选中
	*@param allName 选中全部checkbox的主键
	*@param checkName 选中的box name属性
	*/
	module.exports.CheckBoxCheckAll = function(allName,checkName)
	{
		checkedAll(allName,checkName);
	};

	/**
	*@描述 确定按钮
	*@param btnId 按钮ID
	*@param InputName 多选框按钮
	*@param txtInptId 要设置的text InputId
	*/
	module.exports.BtnConform = function(btnId,InputName,txtInptId,viewtextId)
	{
		confromBtn(btnId,InputName,txtInptId,viewtextId);
	};

	/**
	*@描述 全选 or 全部取消
	*@param btnId 按钮ID
	*@param InputName 多选框按钮
	*/
	module.exports.SetCheckedAll = function(btnId,InputName)
	{
		selectAll(btnId,InputName);
	};

	/**
	*@描述 取消
	*@param btnId
	*/
	module.exports.btnCancle = function(btnId)
	{
		clickCancleBtn(btnId);
	};

	$(function()
	{
		setTimeout(function()
		{
			//selectAll('rooms_type_selecall','rooms_types_value');//全选按钮
			//confromBtn('rooms_types_confirm','rooms_types_value','rooms_roomchoose');//确定按钮
			setSelAll();
			clickCancleBtn('rooms_types_cancle');//取消
			confirmBtnClick();//确定按钮
			inputNumberCheck();
			switchCheckbox();//选中状态
			setFangleiNumber();//选中状态和数目
			checkedAll('rooms_type_selecall','rooms_types_value');
		},200);
	});
});