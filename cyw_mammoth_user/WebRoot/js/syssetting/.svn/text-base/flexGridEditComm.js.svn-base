// 如果flexGrid的某列处于排序状态的话，那么排序结束后，还原排序钱的样式
function sortEndShowStartCss(){
	var rows = flexGrid.rows ;
	var dataItem ;
	var index ;
	for (var i = 0; i < view.itemCount; i++) {
		dataItem = rows[i] ? rows[i].dataItem : null ;
		index = dataItem ? (dataItem.index?dataItem.index.toString():null) : null ;
		if(index){
			if(index.indexOf("*")!=-1){
				if(rows[i].cssClass!="select-row-yellow")
					rows[i].cssClass="select-row-yellow";
			}else if(index.indexOf("#")!=-1){
				if(rows[i].cssClass!="eidt-row-blue")
					rows[i].cssClass="eidt-row-blue";
			}
		}
	}
}
//起初光标到第一行
function outCursor(){
	flexGrid.focus();
	var itemCount = view ? view.itemCount : 0 ;
	flexGrid.select(itemCount, 0);
}
// 确定恢复操作
function goOkForBack(url){
	if(isNull(url))
		return ;
	// 恢复id
	var backIds = $("#backIds").val();
	if(isNull(backIds)){
		return;
	}else{
		$.ajax({
			url:url,
			type:"post",
			data:"backIds="+backIds,
			dataType:"json",
			success:function(data){
				if(data.success){
					getSearch() ;
				}else{
					altEerrMsg('操作失败',function(){});
				}
			},
			error:function(err){
				altEerrMsg('出现异常',function(){});
			}
		});
	}
}
//增加空白行
//@param selectCol 被选中的列
function goAdd(selectCol){
	goShowButton(true , false);
	var item = view.addNew();
	var itemCount = view.itemCount ;
	itemCount = (itemCount && itemCount > 0) ? itemCount : 1
   	flexGrid.select(new wijmo.grid.CellRange(itemCount-1, selectCol, itemCount-1, selectCol), true);
	flexGrid.focus();
	return item;
	//item.codeKindName="可修改";
}
// 放弃操作
function giveUpEdit(){
	view.refresh();
	view.clearChanges();
	getSearch();
	return ;
}
// 查询
function getSearch() {
	$("#searchId").submit();
}
// 取消操作
function goDel() {
	var tempFlag = true ;
	// status_search   有效数据才有删除功能
	var selection = getSelectionRanges();
	// 有效  允许删除
	if(status_search){
		var _delIds = $("#delIds").val() ;
		var ids = "" ;
		if(selection.length>0){
			altInfMsg("确定取消所选择条目吗？",function(){
				for(var i= 0;i<selection.length;i++){
					var rowNo = selection[i].rowNo 
					var dataItem = selection[i].dataItem ;
					var fgRow = flexGrid.rows[rowNo] ;
					var id = dataItem ? dataItem.id : null ;
					if(id){
						if(fgRow.isSelected && (!dataItem.codeKind || dataItem.codeKind == 0)){
							var indexVal = dataItem.index ;
							indexVal = indexVal.toString().replace(/#/g , '') ;
							// 设置样式
							fgRow.dataItem.index = (((indexVal.toString()).indexOf("*")==-1) ? ("*"+indexVal) : (indexVal)) ;
							fgRow.cssClass="select-row-yellow";
							fgRow.isReadOnly=true;
							ids += (id+",") ;
						}else{
							altWaringMsg('请选择需要取消的条目。\n注：状态为不可修改的条目不允许取消。',function(){});
						}
					}
					// else 新增的空白行  执行删除操作
					else{
						view.removeAt(rowNo);
					}
		        }
				goShowButton(true , false);
				// 记录选中删除的id
				$("#delIds").val(isNull(_delIds) ? ids : (_delIds+ids));
			},function(){});
		}
	}
}
// 恢复操作
function goBack() {
	var selection = getSelectionRanges();
	if(selection.topRow<0)
		return ;
	var tempFlag = true ;
	var _backIds = $("#backIds").val() ;
	var ids = "" ;
	if(selection.length>0){
		altInfMsg("确定恢复所选择条目吗？",function(){
			for(var i= 0;i<selection.length;i++){
				var rowNo = selection[i].rowNo 
				var dataItem = selection[i].dataItem ;
				var fgRow = flexGrid.rows[rowNo] ;
				fgRow.isReadOnly=true;
				var id = dataItem ? dataItem.id : null ;
				if(id){
					if(fgRow.isSelected){
						var indexVal = fgRow.dataItem.index ;
						indexVal = indexVal.toString().replace(/#/g , '') ;
						// 设置样式
						fgRow.dataItem.index = (((indexVal.toString()).indexOf("#")==-1) ? ("#"+indexVal) : (indexVal)) ;
						fgRow.cssClass="eidt-row-blue";
						ids += (id+",") ;
					}
				}
				else{
					view.removeAt(rowNo);
				}
		    }
			goShowButton(true , false);
			// 记录选中恢复的id
			$("#backIds").val(isNull(_backIds) ? ids : (_backIds+ids));
		},function(){});
	}
}
//获取选中区域
function getSelectionRanges() {
	var selectRangesArr = [] ;
	for(var r=0;r<flexGrid.rows.length;r++)
	{
	    if(flexGrid.rows[r].isSelected)
	    {
	        var item=flexGrid.rows[r].dataItem;
	        if(item){
	        	selectRangesArr.push({
	        		rowNo:r,
	        		dataItem:item
	        	});
	        }
	    }
	}
	return selectRangesArr ;
}
//根据Date.getDay() 返回的数字 获取对应的名称
function getWeekBy(num){
	var weekStr = "" ;
	switch (num) {
	case 0:
		weekStr = "周日";
		break;
	case 1:
		weekStr = "周一";
		break;
	case 2:
		weekStr = "周二";
		break;
	case 3:
		weekStr = "周三";
		break;
	case 4:
		weekStr = "周四";
		break;
	case 5:
		weekStr = "周五";
		break;
	case 6:
		weekStr = "周六";
		break;
	default:
		weekStr = "未知";
		break;
	}
	return weekStr ;
}
function getDateType(day , separator){
	var dtArr = day.split(separator);
	return new Date(dtArr[0], parseInt(dtArr[1])-1, dtArr[2]) ;
} 
/**
 * 是否是简单时间 YYYY-MM-DD
 */
function isSimpleDate(value){
	var text = /^(\d{4})\-(\d{2})\-(\d{2})$/;
	return text.test(value);
}