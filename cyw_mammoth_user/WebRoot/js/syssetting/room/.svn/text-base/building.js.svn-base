var array = [];
var flexGrid ;
var flexGrid ;
// 有效  true 否则 无效
var status_search = ( "${status eq 1}" == "false" ) ;
$(function() {
	var count = '${fn:length(buildList)}';
	var dataJson = "${buildListJson}" ;
	alert(dataJson);
	//var countries = 'United S,Germany,UK,Japan,Italy,Greece'.split(',');
	var data = eval('(' + dataJson + ')');
	for ( var i = 0; i < count; i++) {
		var codeKind = "";
		if (data[i].codeKind == 0) {
			codeKind = "可修改";
		} else if (data[i].codeKind == 1) {
			codeKind = "不可修改";
		}
		array.push({
			"序号": i+1,
			"ID号": data[i].codeId,
			"英文名": data[i].codeNamee,
			"中文名": data[i].codeNamec,
			"最小楼层": data[i].minNum,
			"最大楼层": data[i].maxNum,
			"状态": codeKind,
			"codeKind": data[i].codeKind,
			"status": data[i].status,
			"id": data[i].id
		});
	}
	/* if(array == null || array.length == 0)
		array.push({
			"数据提示": "无相关数据"
		}); */
	// create CollectionView on the data (to get events)
	view = new wijmo.collections.CollectionView(array);
	
	view.trackChanges = true;
	// initialize the grid
	flexGrid = new wijmo.grid.FlexGrid('#theGrid', {
			autoGenerateColumns: false,
			selectionMode : wijmo.grid.SelectionMode.RowRange,
			columns:[
			    { header:"id",binding:"id",name:"id" , isReadOnly:true,visible:false}, 
			    { header:"status",binding:"status",name:"status" ,isReadOnly:true,visible:false}, 
			    { header:"codeKind",binding:"codeKind",name:"codeKind" ,isReadOnly:true,visible:false}, 
			    { header:"序号",binding:"序号",width:'*',name:"h_index" ,isReadOnly:true},           
			    { header:"ID号", binding:"ID号",name:"h_codeId" ,width:'*',isReadOnly:!status_search }, 
			    { header:"英文名", binding:"英文名",name:"h_codeNamee" ,width:'*',isReadOnly:!status_search},
			    { header:"中文名",binding:"中文名",name:"h_codeNamec" , width:'*',isReadOnly:!status_search},
			    { header:"最小楼层",binding:"最小楼层",name:"h_minNum" ,width:'*',isReadOnly:!status_search},
			    { header:"最大楼层", binding: "最大楼层",name:"h_maxNum" ,width:'*',isReadOnly:!status_search},
			    { header:"状态",binding:"状态", name:"h_codeKind" ,width:'*',isReadOnly:true}
			],
			allowAddNew: status_search,
			allowDelete: status_search,
			allowResizing:wijmo.grid.AllowResizing.None,
			itemsSource : view
		}
	);
	
	flexGrid.itemFormatter = function(panel, r, c, cell) {
		if (panel.cellType == wijmo.grid.CellType.Cell) {
			var col = panel.columns[c];
			if (col.name == 'h_codeKind' && cell.innerHTML == "不可修改") {
				// 设置当前行只读；
				panel.rows[r].isReadOnly=true;
			}
		}
	};
	/* flexGrid.itemFormatter = function(panel, r, c, cell) {
	}; */
	/* flexGrid.hostElement.addEventListener('click', function (e) {
		var ht = flexGrid.hitTest(e.pageX, e.pageY);
	}); */
});
//便利标签页END
/*添加标签页 结束*/
//建筑定义，新增
$("#add").click(function() {
	//alertBuildingEditDiv();
	//$("div.wj-new").eq(1).dblclick(function(){return true;});
	if(status_search){
		view.addNew();
	}
});
function alertBuildingEditDiv(){
	$(".alertDivBg").fadeIn();
	$(".buildingAdd").fadeIn();
}
function closeDiv(){
	$(".alertDivBg").fadeOut();
	$(".buildingAdd").fadeOut();
	return ;
}
function giveUpEdit(){
	view.refresh();
	getSearch();
	return ;
}
function getSearch() {
	$("#searchId").submit();
}
function goAdd() {
	$.ajax({
		url:"${ctx}/building/save.do",
		type:"post",
		data:$("#buildingForm").serialize(),
		dataType:"json",
		success:function(data){
			if(data.success){
				alert(data.msg);
				closeDiv();
				window.location.href="${ctx}/building/list.do" ;
			}else{
				alert(data.msg);
			}
		},
		error:function(err){
			alert("操作失败!");
		}
	}); 
}
function goDel() {
	//$(".wj-state-selected").html(($(".wj-state-selected").html()).indexOf("*")==-1 ? ("*"+($(".wj-state-selected").html())) : ($(".wj-state-selected").html()));
	// status_search   有效数据才有删除功能
	if(status_search && confirm("确定删除所选择条目吗？")){
		/* 
		// 单条选中
		var item = view.currentItem;
		item.isReadOnly=true ; */
		var _delIds = $("#delIds").val() ;
		var selection = flexGrid.selection ;
		var ids = "" ;
		for(var i= selection.topRow;i<=selection.bottomRow;i++){
			flexGrid.rows[i].isReadOnly=true;
			// 最后一次取消分隔符
			if(i == selection.bottomRow)
				ids= ids + (flexGrid.rows[i].dataItem.id) ;
			else
				ids= (ids + (flexGrid.rows[i].dataItem.id) + "," ) ;
        }
		// 记录选中删除的id
		$("#delIds").val(isNull(_delIds) ? ids : (_delIds+","+ids));
		// 设置样式
		$(".wj-state-selected,.wj-state-multi-selected").css("color","yellow");
	}
	/* $.ajax({
		url:"${ctx}/building/del.do",
		type:"post",
		data:{ids:item.id},
		dataType:"json",
		success:function(data){
			if(data.success){
				view.remove(item);
			}else{
				alert("操作失败!");
			}
		},
		error:function(err){
			alert("操作失败!");
		}
	});*/
}
function isNull(str) {
	if (str == "") {
		return true;
	}
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
function goOk(){
	var addOrEditItemsArray = [];
	// 删除
	var delIds = $("#delIds").val();
	
	var removeItems = view.itemsRemoved;
	if(removeItems && removeItems.length > 0){
		for(var i=0 ; i < removeItems.length ; i++){
			delIds+=(","+removeItems[i].id) ;
		}
	}
	
	// 添加
	var addItems = view.itemsAdded;
	if(addItems && addItems.length > 0){
		for(var i=0 ; i < addItems.length ; i++){
			var codeId = addItems[i].ID号 ;
			var codeNamee = addItems[i].英文名 ;
			if(isNull(codeId) || isNull(codeNamee))
				continue ;
			addOrEditItemsArray.push({
				"codeId": codeId,
				"codeNamee": addItems[i].英文名,
				"codeNamec": addItems[i].中文名,
				"minNum": addItems[i].最小楼层,
				"maxNum": addItems[i].最大楼层,
				//"codeKind": addItems[i].codeKind,
				"codeKind": 0,
				"status": 0
			});
		}
	}
	
	// 修改
	var editItems = view.itemsEdited;
	if(editItems && editItems.length > 0){
		for(var i=0 ; i < editItems.length ; i++){
			addOrEditItemsArray.push({
				"codeId": editItems[i].ID号,
				"codeNamee": editItems[i].英文名,
				"codeNamec": editItems[i].中文名,
				"minNum": editItems[i].最小楼层,
				"maxNum": editItems[i].最大楼层,
				"codeKind": editItems[i].codeKind,
				"status": 0,
				"id":editItems[i].id
			});
		}
	}
	
	var addOrEditItemsData = JSON.stringify(addOrEditItemsArray);
	
	if(isNull(delIds) && isNull(addOrEditItemsData)){
		return;
	}else{
		$.ajax({
			url:"${ctx}/building/saveOrUpdateOrDel.do",
			type:"post",
			data:{params : addOrEditItemsData , delIds : delIds },
			dataType:"json",
			success:function(data){
				if(data.success){
					window.location.href="${ctx}/building/list.do" ;
				}else{
					alert("操作失败!");
				}
			},
			error:function(err){
				alert("操作失败!（可能因为ID号重复）");
			}
		});
	}
}
function goUpdate(){
	var item = view.itemsEdited;
	$.ajax({
		url:"${ctx}/building/update.do",
		type:"post",
		data:{id:item.id},
		dataType:"json",
		success:function(data){
			if(data.success){
				alert(data.val);
				alertBuildingEditDiv();
			}else{
				alert("操作失败!");
			}
		},
		error:function(err){
			alert("操作失败!");
		}
	});
}
/* document.onkeyup = function(event) {
	var e = event || window.event;
	var keyCode = e.keyCode || e.which;
	switch (keyCode) {
	case 65:
		$("#add").click();
		break;
	case 66:
		$("#update").click();
		break;
	case 68:
		$("#del").click();
		break;
	case 80:
		$("#print").click();
		break;
	default:
		break;
	}
}; */