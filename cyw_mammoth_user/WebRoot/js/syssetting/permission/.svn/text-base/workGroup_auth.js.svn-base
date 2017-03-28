function functionTypeChange(val){
	var groupId = $("#workGroupId").val();
	$.ajax({
		url:"/workGroup/findFunctionListBy.do",
		type:"post",
		data:{funcType : val , groupId : groupId},
		dataType:"json",
		success:function(data){
			if(data.success){
				var listJson = eval('('+data.listJson+')') ;
				fillLeftFunctionControl(listJson);
				fillRightFunctionControl(listJson);
				
				goShowButtonBy(false);
			}
		},
		error:function(err){
			altEerrMsg('出现异常',function(){});
		}
	});
}
function fillLeftFunctionControl(_listJson){
	var listJson = _listJson ;
	var html = "" ;
	if(listJson){
		for ( var i = 0; i < listJson.length; i++) {
			var left_div_html = "" ;
			var fun = listJson[i].hfunction ;
			var hfunctionControlVOs = listJson[i].hfunctionControlVOs ;
			if(hfunctionControlVOs && hfunctionControlVOs.length > 0){
				var obj = fillLeftFuncLi(fun.id , fun.functionName, hfunctionControlVOs) ;
				
				left_div_html += "<div id=\"left_func_"+fun.id+"\" class=\"bodyLiTitle left_bodyLiTitle\" data-id=\""+fun.id+"\" style=\""+(obj.flag == 0 ? "display:none;" : "" )+"\">" ;
				left_div_html += "	<img class=\"titleList\" src=\"/img/tree_01.png\"> " ;
				left_div_html += "	<span class=\"titleName\" >"+fun.functionName+"</span>" ;
				left_div_html += "</div>" ;
				html += (left_div_html + obj.html);
			}
		}
	}
	
	$("#left_tree_div").html(html) ;
	$(".left_bodyLiTitle").bind("dblclick" , leftFuncDivDBLClick);
	$(".left_scdLi .left_tree_li").bind("dblclick" , leftFuncCtrlLiDBLClick);
	$(".left_bodyLiTitle").bind("click" , funcDivClick);
	$(".left_scdLi .left_tree_li").bind("click" , funcCtrlLiClick);
	
}
function fillLeftFuncLi(_pid , _pfuncName , _hfunctionControlVOs){

	var left_div_html = "<ul class=\"scdLi left_scdLi\" id=\"left_scdLi_ul_"+_pid+"\">" ;
	var hfunctionControlVOs = _hfunctionControlVOs;
	var _flag = 0 ;
	for ( var j = 0; j < hfunctionControlVOs.length; j++) {
		var hfunctionControl = hfunctionControlVOs[j].hfunctionControl ;
		var flag = hfunctionControlVOs[j].flag ;
		var ctrlTypeName = hfunctionControlVOs[j].ctrlTypeName ;
		flag = parseInt(flag);
		_flag = (_flag + flag) ;   
		left_div_html += "	<li id=\"left_func_ctrl_li_"+hfunctionControl.id+"\" class=\"left_tree_li\" style=\""+(flag == 1 ? "" : "display:none;" )+"\""
								+" data-id=\""+hfunctionControl.id+"\" data-funcid=\""+hfunctionControl.functionId+"\" data-pid=\""+hfunctionControl.parentId+"\" data-funcName=\""+hfunctionControl.functionName+"\" data-ctrlType=\""+hfunctionControl.ctrlType+"\" data-fgroup=\""+hfunctionControl.fgroup+"\">" ;
		left_div_html += "		<a class=\"treeLink\" href=\"javascript:;\"> " ;
		left_div_html += "			<img class=\"scdLiList\" src=\"/img/scd_list.png\"> " ;
		left_div_html += "			<span class=\"scdLiName\">"+hfunctionControl.functionName+"("+ctrlTypeName+")"+"</span> " ;
		left_div_html += "		</a>" ;
		left_div_html += "	</li>" ;
	}
	left_div_html += "</ul> " ;
	var obj = new Object ;
	obj.html = left_div_html ;
	obj.flag = _flag;
	return obj ;
}



function fillRightFunctionControl(_listJson){
	var listJson = _listJson ;
	var html = "" ;
	if(listJson){
		for ( var i = 0; i < listJson.length; i++) {
			var left_div_html = "" ;
			var fun = listJson[i].hfunction ;
			var hfunctionControlVOs = listJson[i].hfunctionControlVOs ;
			if(hfunctionControlVOs && hfunctionControlVOs.length > 0){
				var obj = fillRightFuncLi(fun.id , hfunctionControlVOs) ;
				left_div_html += "<div id=\"right_func_"+fun.id+"\" class=\"bodyLiTitle right_bodyLiTitle\" data-id=\""+fun.id+"\" style=\""+(obj.flag == hfunctionControlVOs.length ? "display:none;" : "" )+"\">" ;
				left_div_html += "	<img class=\"titleList\" src=\"/img/tree_01.png\"> " ;
				left_div_html += "	<span class=\"titleName\">"+fun.functionName+"</span>" ;
				left_div_html += "</div>" ;
				
				html += (left_div_html + obj.html);
			}
		}
	}
	$("#right_tree_div").html(html) ;
	$(".right_bodyLiTitle").bind("dblclick" , rightFuncDivDBLClick);
	$(".right_scdLi .right_tree_li").bind("dblclick" , rightFuncCtrlLiDBLClick);
	
	$(".right_bodyLiTitle").bind("click" , funcDivClick);
	$(".right_scdLi .right_tree_li").bind("click" , funcCtrlLiClick);
	
}
function funcDivClick(event){
	var currObj = $(this) ;
	if(currObj.hasClass("point")){
		if(event.ctrlKey){
			funDivRemoveClass(currObj);
			currObj.next("ul.scdLi").children("li").each(function(index , element){
				$(element).removeClass("splitPoint");
				$(element).children("a").removeClass("pointThisA");
			}); 
		}else{
			funDivRemoveClass();
			hiddenLi("scdLi");
			currObj.next("ul.scdLi").children("li").each(function(index , element){
				if($(element).is(":visible")){
					$(element).removeClass("splitPoint");
					$(element).children("a").removeClass("pointThisA");
				}
			}); 
		}
	}else{
		if(event.ctrlKey){
			selectDiv(currObj);
			currObj.next("ul.scdLi").children("li").each(function(index , element){
				$(element).addClass("splitPoint");
				$(element).children("a").addClass("pointThisA");
			}); 
		}
		else{
			funDivRemoveClass();
			hiddenLi("scdLi");
			selectDiv(currObj);
			currObj.next("ul.scdLi").children("li").each(function(index , element){
				if($(element).is(":visible") && !$(element).hasClass("splitPoint")){
					$(element).addClass("splitPoint");
					$(element).children("a").addClass("pointThisA");
				}
			}); 
		}
	}
}
function funcCtrlLiClick(event){
	if(event.ctrlKey){
		if($(this).hasClass("splitPoint")){
			hiddenOnlyLi($(this).prop("id"));
			var dataPid = $(this).attr("data-pid");
			if($(this).hasClass("left_tree_li")){
				if($("#left_scdLi_ul_"+dataPid + " .splitPoint").length == 0){
					$("#left_func_"+dataPid).removeClass("point");
				}
			}else{
				if($("#right_scdLi_ul_"+dataPid + " .splitPoint").length == 0){
					$("#right_func_"+dataPid).removeClass("point");
				}
			}
		}else{
			selectLi($(this));
			selectDiv($(this).parent("ul").prev("div"));
		}
	}
	else{
		hiddenLi("scdLi");
		funDivRemoveClass();
		selectLi($(this));
		selectDiv($(this).parent("ul").prev("div"));
	}
}

function fillRightFuncLi(_id , _hfunctionControlVOs){
	var left_div_html = "<ul class=\"scdLi right_scdLi\" id=\"right_scdLi_ul_"+_id+"\">" ;
	var hfunctionControlVOs = _hfunctionControlVOs;
	var _flag = 0 ;
	for ( var j = 0; j < hfunctionControlVOs.length; j++) {
		var hfunctionControl = hfunctionControlVOs[j].hfunctionControl ;
		var flag = hfunctionControlVOs[j].flag ;
		var ctrlTypeName = hfunctionControlVOs[j].ctrlTypeName ;
		flag = parseInt(flag);
		_flag = (_flag + flag) ;
		left_div_html += "	<li id=\"right_func_ctrl_li_"+hfunctionControl.id+"\" class=\"right_tree_li\" style=\""+(flag == 0 ? "" : "display:none;" )+"\""
								+" data-id=\""+hfunctionControl.id+"\" data-funcid=\""+hfunctionControl.functionId+"\" data-pid=\""+hfunctionControl.parentId+"\" data-funcName=\""+hfunctionControl.functionName+"\" data-ctrlType=\""+hfunctionControl.ctrlType+"\" data-fgroup=\""+hfunctionControl.fgroup+"\">" ;
		left_div_html += "		<a class=\"treeLink\" href=\"javascript:;\"> " ;
		left_div_html += "			<img class=\"scdLiList\" src=\"/img/scd_list.png\"> " ;
		left_div_html += "			<span class=\"scdLiName\">"+hfunctionControl.functionName+"("+ctrlTypeName+")"+"</span> " ;
		left_div_html += "		</a>" ;
		left_div_html += "	</li>" ;
	}
	left_div_html += "</ul> " ;
	var obj = new Object ;
	obj.html = left_div_html ;
	obj.flag = _flag;
	return obj ;
}

function leftFuncDivDBLClick(event){
	var currObj = $(this) ;
	var dataId = currObj.attr("data-id");
	
	$("#right_func_"+dataId).css("display" , "") ;
	$("#right_scdLi_ul_"+dataId).css("display" , "") ;
	$("#right_scdLi_ul_"+dataId +" li").css("display" , "") ;
	
	
	$("#left_func_"+dataId).css("display" , "none") ;
	$("#left_scdLi_ul_"+dataId).css("display" , "none") ;
	$("#left_scdLi_ul_"+dataId +" li").css("display" , "none") ;
	
	$("#left_scdLi_ul_"+dataId +" li").removeClass("splitPoint");
	$("#left_scdLi_ul_"+dataId +" li a").removeClass("pointThisA");
	
	goShowButtonBy(true);
	
}
function leftFuncCtrlLiDBLClick(event){
	var currObj = $(this) ;
	var dataId = currObj.attr("data-id");
	var dataPid = currObj.attr("data-pid");
	leftFuncHiddenAnd(dataId, dataPid) ;
	funDivRemoveClass($(this).parent("ul").prev("div"));
	
}
function leftFuncHiddenAnd(dataId , dataPid){
	$("#right_func_"+dataPid).css("display" , "") ;
	$("#right_scdLi_ul_"+dataPid).css("display" , "") ;
	$("#right_func_ctrl_li_"+dataId).css("display" , "") ;
	
	$("#left_func_ctrl_li_"+dataId).css("display" , "none") ;
	$("#left_func_ctrl_li_"+dataId).removeClass("splitPoint");
	$("#left_func_ctrl_li_"+dataId +" a").removeClass("pointThisA");
	
	if(findUl_li_not_hidden_length("left_scdLi_ul_"+dataPid) == 0){
		$("#left_scdLi_ul_"+dataPid).css("display" , "none") ;
		$("#left_func_"+dataPid).css("display" , "none") ;
	}
	goShowButtonBy(true);
}
function findUl_li_not_hidden_length(ulId){
	return $("#"+ulId +" li:not(:hidden)").length;
}
function selectLi(_currObj){
	var currObj = _currObj ;
	if(currObj.hasClass("splitPoint")){}else{
		currObj.addClass("splitPoint");
		currObj.children("a").addClass("pointThisA");
	}
}
function selectDiv(_currObj){
	var currObj = _currObj ;
	if(currObj.hasClass("point")){}else{
		currObj.addClass("point");
	}
}
function hiddenLi(scdLiClassName){
	$("."+scdLiClassName+" li").removeClass("splitPoint");
	$("."+scdLiClassName+" li a").removeClass("pointThisA");
}
function hiddenOnlyLi(liId){
	$("#"+liId).removeClass("splitPoint");
	$("#"+liId+" a").removeClass("pointThisA");
}
function funDivRemoveClass(obj){
	if(obj){
		obj.removeClass("point");
	}else{
		$(".bodyLiTitle").removeClass("point");
	}
	
}
function rightFuncDivDBLClick(event){
	var currObj = $(this) ;
	var dataId = currObj.attr("data-id");
	
	$("#right_func_"+dataId).css("display" , "none") ;
	$("#right_scdLi_ul_"+dataId).css("display" , "none") ;
	$("#right_scdLi_ul_"+dataId +" li").css("display" , "none") ;
	
	$("#left_func_"+dataId).css("display" , "") ;
	$("#left_scdLi_ul_"+dataId).css("display" , "") ;
	$("#left_scdLi_ul_"+dataId +" li").css("display" , "") ;
	
	goShowButtonBy(true);
}
function rightFuncCtrlLiDBLClick(event){
	var currObj = $(this) ;
	var dataId = currObj.attr("data-id");
	var dataPid = currObj.attr("data-pid");
	rightFuncHiddenAnd(dataId, dataPid) ;
	funDivRemoveClass($(this).parent("ul").prev("div"));
	
}
function rightFuncHiddenAnd(dataId , dataPid){
	$("#left_func_"+dataPid).css("display" , "") ;
	$("#left_scdLi_ul_"+dataPid).css("display" , "") ;
	$("#left_func_ctrl_li_"+dataId).css("display" , "") ;
	
	$("#right_func_ctrl_li_"+dataId).css("display" , "none") ;
	$("#right_func_ctrl_li_"+dataId).removeClass("splitPoint");
	$("#right_func_ctrl_li_"+dataId +" a").removeClass("pointThisA");
	
	if(findUl_li_not_hidden_length("right_scdLi_ul_"+dataPid) == 0){
		$("#right_scdLi_ul_"+dataPid).css("display" , "none") ;
		$("#right_func_"+dataPid).css("display" , "none") ;
	}
	
	goShowButtonBy(true);
}
function rightMoveFun(){
	$(".right_tree_li").each(function(index , element){
		if($(element).hasClass("splitPoint")){
			var dataId = $(element).attr("data-id");
			var dataPid = $(element).attr("data-pid");
			rightFuncHiddenAnd(dataId, dataPid) ;
			funDivRemoveClass($(element).parent("ul").prev("div"));
		}
	});
}
function leftMoveFun(){
	$(".left_tree_li").each(function(index , element){
		if($(element).hasClass("splitPoint")){
			var dataId = $(element).attr("data-id");
			var dataPid = $(element).attr("data-pid");
			leftFuncHiddenAnd(dataId, dataPid) ;
			funDivRemoveClass($(element).parent("ul").prev("div"));
		}
	}); 
}
function giveUpAuth(){
	functionTypeChange($("#functionType").val());
	goShowButtonBy(false);
}
function saveAuth(){
	var funcCtrlArr = [] ;
	var funcCtrlObj = null ;
	var workGroupId = $("#workGroupId").val();
	var fgroup = $("#functionType").val();
	$(".right_tree_li").each(function(index , element){
		if($(element).is(":visible")){
			funcCtrlObj = new Object ;
			var funcid = $(element).attr("data-funcid");
			var ctrlType = $(element).attr("data-ctrlType");
			var _fgroup = $(element).attr("data-fgroup");
			
			funcCtrlObj.hfunctionId = funcid;
			funcCtrlObj.workGroupId = workGroupId;
			funcCtrlObj.hfunctionType = ctrlType;
			funcCtrlObj.hfunctionGroup = _fgroup;
			funcCtrlArr.push(funcCtrlObj);
		}
	});
	$.ajax({
		url:"/workGroup/saveWorkGroupAuth.do",
		type:"post",
		data:{wgAuthJson : JSON.stringify(funcCtrlArr) , workGroupId :workGroupId , hfunctionGroup : fgroup  },
		dataType:"json",
		success:function(data){
			altInfMsg("授权成功，是否继续授权？",function(){},function(){
				parent.closeDiv('workGroupAuthListAlert');
			});
		},
		error:function(err){
			altEerrMsg('出现异常',function(){});
		}
	});
}
function goShowButtonBy(bool){
	if(bool){
		$("a#save,a#giveUp").css({"cursor":"pointer","color":"inherit"});
		$("a#save").prop("href","javascript:saveAuth();");
		$("a#giveUp").prop("href","javascript:giveUpAuth();");
	}else{
		$("a#save,a#giveUp").css({"cursor":"not-allowed","color":"grey"});
		$("a#save,a#giveUp").prop("href","javascript:;");
	}
}