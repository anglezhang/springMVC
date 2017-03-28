define(function (require, exports, module) 
{
	var t = require("../frame/Tools");
	var basePath = $("#path").val();
	var ajaxM = require("../frame/AjaxManager");
	/**
	 * 账目刷新
	 */
	var reloadAccount = function(_accountGrid,_guestObj)
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
	 * 账目刷新
	 */
	module.exports.ReLoadAcc = function(_accountGrid,_guestObj)
	{
		reloadAccount(_accountGrid,_guestObj);
	}
});