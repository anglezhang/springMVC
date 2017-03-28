define(function(require, exports, module)
{	
	/**
	*@描述 关闭
	*/
	var closePannel = function()
	{
		$("#otherguestinfo_quti_btn").unbind("click");
		$("#otherguestinfo_quti_btn").bind("click",function()
		{
			$("#closeWindowsguesdocdetail_other_info").trigger('click');
		});
	};

	/**
	*@描述 值改变
	*/
	var valueChange = function()
	{
		var inputID = "#otherguestinfo_otherNamee"
				+ ",#otherguestinfo_otherCrdId"
				+ ",#otherguestinfo_otherSex"
				+ ",#otherguestinfo_otherCountry"
				+ ",#otherguestinfo_otherCrdkindId"
				+ ",#otherguestinfo_visakindId"
				+ ",#otherguestinfo_depart"
				+ ",#otherguestinfo_inPort";
		$(inputID).unbind("keyup change");
		$(inputID).bind("keyup change",function(event)
		{
			$("#guestinfo_gstNamec").trigger('keyup');
		});
	};

	/**
	*@描述
	*/
	var wDatepiker = function()
	{
		$("#otherguestinfo_crdVld,#otherguestinfo_inDate").unbind('click');
		$("#otherguestinfo_crdVld,#otherguestinfo_inDate").bind('click',function(event)
		{
			WdatePicker();
			$("#guestinfo_gstNamec").trigger('keyup');
		});
	};

	$(function()
	{
		setTimeout(function()
		{
			closePannel();//关闭按钮
			valueChange();//值改变
			wDatepiker();//日期
		},120);
	});
});