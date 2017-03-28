define(function(require, exports, module)
{
	var t = require("../../frame/Tools");
	//房屋类型 该界面直接调用它的方法
	var roomType = require("./roomsType");
	/**
	*@描述：修改选中状态
	*/
	var checkedSet = function()
	{
		$("[name='rooms_hroomchr_value']").each(function(index, el)
		{
			var checkVals = $("#rooms_features_veiw").val();
			var checkArray = checkVals.split(",");
			var val = $(el).val();
			if($.inArray(val,checkArray)>-1)
			{
				$(el).prop("checked",true);
			}
		});
	};

	/**
	*@描述 点击选中
	*/
	var checkBoxSel = function()
	{
		$("[name='rooms_hroomchr_value']").unbind('click');
		$("[name='rooms_hroomchr_value']").bind('click',function(event)
		{
			var sel = $(this).prop("checked");
			if(!sel)
			{
				$("#rooms_hroomchr_value_checkall").prop("checked",false);
			}
			roomType.CheckBoxCheckAll('rooms_hroomchr_value_checkall','rooms_hroomchr_value');
		});
	};
	$(function()
	{
		setTimeout(function()
		{
			roomType.BtnConform('rooms_hroomchr_confriom','rooms_hroomchr_value','rooms_features','rooms_features_veiw');
			roomType.SetCheckedAll('rooms_hroomchr_value_checkall','rooms_hroomchr_value');
			roomType.btnCancle('rooms_hroomchr_cancle');
			checkedSet();
			checkBoxSel();//点击选中或者取消
			roomType.CheckBoxCheckAll('rooms_hroomchr_value_checkall','rooms_hroomchr_value');
		},200);
		
	});
});