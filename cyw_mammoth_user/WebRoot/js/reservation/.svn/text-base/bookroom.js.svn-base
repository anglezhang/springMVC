//获取项目路径	
var path = $("#path").val();
var array=[];
var grid;
var view;
var gridSelRowNum=0;
var addBookRoomView = undefined;
var editBookRoomView = undefined;
var addBookRoomFlexGrid = undefined;
var editBookRoomFlexGrid = undefined;
var remainRoomFlexGrid = undefined;
var remainRoomView = undefined;
var currItem = undefined;
var bookRoomViewTemp = undefined;
var currBookRoomId = undefined;
//保存留房操作时候的房间号，已变知道该订单操作了那些房间
var roomIdArray = undefined;
var currOper = undefined;
//用来记录保存每条预定信息对应的选中的留房操作
var map = undefined;
var roomTypeList;
//选择的checkId
var selCheckId=undefined;
var orderIsValid = "true";   //此订单状态 默认是有效


/** 
*删除数组指定下标或指定对象 
*/ 

Array.prototype.remove = function(obj) {
	for (var i = 0; i < this.length; i++) {
		var temp = this[i];
		if (!isNaN(obj)) {
			temp = i;
		}
		if (temp == obj) {
			for (var j = i; j < this.length; j++) {
				this[j] = this[j + 1];
			}
			this.length = this.length - 1;
		}
	}
}

$(function() {
	//初始化判断房间设置复选框是否选中
    /*遮罩层DIV高度*/
    $(".alertDivBg").css("height", $(document).height());
    $(".alertDivBg").css("width", $(document).width());
    /*遮罩层DIV高度*/
    /*二级菜单点击选中事件*/
    $(".secondMenu li a").click(function() {
        $(".secondMenu li a").removeClass('thisSecMenu');
        $(this).addClass('thisSecMenu');
    }
    );
    /*二级菜单点击选中事件 结束*/
    
    /*小键盘点击切换背景*/
    $(".cabDiv a").click(function() {
        $(".cabDiv a").removeClass("pointCab");
        $(this).addClass("pointCab");
    }
    );
    /*小键盘点击切换背景END*/
    /*订单详情显示弹出层*/
    /*$("#orderDetails").click(function(){
			$(".orderDetailsDiv").fadeIn();
			$(".alertDivBg").fadeIn();
			bookDiaryGrid();
		});*/
    $(".closeDiv").click(function() {
        $(".orderDetailsDiv").fadeOut();
        $(".alertDivBg").fadeOut();
    }
    );
    $(".closeDiv .closeAlert").click(function(){
    	//alert();
    });
    /*订单详情显示弹出层END*/
    /*3级弹出框 - 留房*/
    $(".reservedRoom").click(function() {
    	$(".orderDetailsDiv").fadeOut();
        $(".alertDivBg").fadeOut();
    }
    );
    
	/*其他信息退出  */
	$(".closeAlert").click(function(){
		$(".leaveTheRoom").fadeOut();
		$(".alertDivBg2").fadeOut();
		$(".alertDivBg3").fadeOut();
		$(".otherInformationDiv").fadeOut();
	});
	/*其他信息退出   按钮*/
	$("#quitOtherInfo").click(function(){
		$(".leaveTheRoom").fadeOut();
		$(".alertDivBg2").fadeOut();
		$(".alertDivBg3").fadeOut();
		$(".otherInformationDiv").fadeOut();
		$(".featuresButton").fadeOut();
		$(".splitInfoDiv").fadeOut();
	});
	/*其他信息END*/
	/*特征*/
	/*$(".featuresChooseButton").click(function(){
		//回显数据
		$("input[name='rmAll']").prop("checked",false);
		var checkId = $("#viewId").val();
		var checkIdval = $("#isEdit").val();
		if(checkIdval){
			var rcValue = $("input[name='roomCharacter']").val();
			$("input[name='rooms_hroomchr_value']").prop("checked",false);
			$("input[name='rooms_hroomchr_value']").each(function(index,element){
				var p = $(element).val();
				var pVal = 1<<p;
				if((rcValue & pVal)!=0){
					$(element).prop("checked",true);
				}
			});
		}else{
			$("input[name='rooms_hroomchr_value']").each(function(index,element){
				$(element).prop("checked",false);
			});
		}
		
		
		$(".alertDivBg2").fadeIn();
		$(".featuresButton").fadeIn();
	});*/
	/*特征end*/
	/**房间特征全选,反选*/
	$("input[name='rmAll']").click(function(){
		if($(this).prop("checked")){
			$("input[name='rooms_hroomchr_value']").prop("checked", true);
		}else{
			$("input[name='rooms_hroomchr_value']").prop("checked", false);
		}
	});
	
	/**房间特征确定*/
	$("#rmOK").click(function(){
		var roomCharacter = new Array();
		var bool = false;
		$("input[name='rooms_hroomchr_value']:checked").each(function(index,element){
			bool = true;
			roomCharacter.push($(element).val());
		});
		if(bool == false){
			$("input[name='roomCharacterIS']").prop("checked",false);
		}else{
			$("input[name='roomCharacterIS']").prop("checked",true);
		}
		roomCharacter.join(",");
		var checkId = $("#viewId").val();
		$.ajax({
		   type: "POST",
		   url: path+"/bookroom/saveRoomFeature.do",
		   data: {roomCharacter:roomCharacter.toString(),checkId:checkId},
		   success: function(msg){
		      if(msg.success){
		    	  //alert(msg.msg);
		    	  $("input[name='roomCharacter']").val(msg.obj.roomCharacter);
		    	  initRoomCharaCheck();
		    	  //关闭窗口
		    	  closeAlert();
		      }
		   }
		});
	});
	
	/**房间特征取消*/
	$("#rmCancel").click(function(){
		closeAlert();
	});
	
	/*分账设置*/
	/*$("#split").click(function(){
		if($("#skyd input[name='checkId']").val()!=""){
			loadConsume();
			$(".alertDivBg3").fadeIn();
			$(".splitInfoDiv").fadeIn();			
		}
	});*/
	/*分账设置END*/
	
	/**
	 * 分账退出
	 */
    /*3级弹出框 - 留房*/
    /*房态小块*/
    /*$(".statusBlock").click(function(){
			$(this).nextAll(".pointStatus").eq(0).fadeIn();
			$(this).nextAll(".pointStatusRight").eq(0).fadeIn();
		});
		$(".pointStatusRight").click(function(){
			$(this).fadeOut();
			$(this).prev().fadeOut();
		});
		$(".pointStatus").click(function(){
			$(this).fadeOut();
			$(this).next().fadeOut();
		});*/
    /*房态小块END*/
    
    //初始化列表grid
    view = new wijmo.collections.CollectionView(array);
    grid = new wijmo.grid.FlexGrid('#ydtable',{
        autoGenerateColumns: false,
        allowMerging: wijmo.grid.AllowMerging.All,
        isReadOnly: true,
        allowDragging:wijmo.grid.AllowDragging.None,
        selectionMode: wijmo.grid.SelectionMode.RowRange,
        headersVisibility:wijmo.grid.HeadersVisibility.Column,
        //allowResizing: wijmo.grid.AllowResizing.None,
        columns: [
                  {header: '序号', binding: 'number', minWidth:40, width: 50,isReadOnly: true, allowMerging: true,cssClass:'fg_column_readOnly',align:'center'}, 
                  {header: '订单号',binding: 'bookList', minWidth:60, width: 100,allowMerging: true ,align:'center' }, 
                  {header: '中文名',binding: 'gstNamec',  minWidth:60, width: 100,allowMerging: true,align:'left'}, 
                  {header: '英文名',binding: 'gstNamee', minWidth:60, width: 93,allowMerging: true,align:'left'}, 
             //----------------bookroom info----------------------
             {
                 header: '房类',
                 binding: 'codeNamec',
                 allowSorting: false,
                 align:'center',
                 minWidth:40, width: 120
             }, 
             {
                 header: '抵店日期',
                 binding: 'breachDate',
                 minWidth:80, width: 100,
                 allowSorting: false,
                 align:'center',
                 format: 'yyyy-MM-dd'
             }, 
             {
                 header: '离店日期',
                 binding: 'bleaveDate',
                 minWidth:80, width: 100,
                 allowSorting: false,
                 align:'center',
                 format: 'yyyy-MM-dd'
             }, 
             {
                 header: '订房数',
                 binding: 'bookNum',
                 allowSorting: false,
                 align:'center',
                 minWidth:60, width: 70
             }, 
             {
                 header: '留房数',
                 binding: 'saveNum',
                 allowSorting: false,
                 align:'center',
                 minWidth:60, width: 70
             }, 
             {
                 header: '抵达数',
                 binding: 'reachNum',
                 allowSorting: false,
                 align:'center',
                 minWidth:60, width: 70
             }, 
             {
                 header: '状态',
                 binding: 'status',
                 allowSorting: false,
                 align:'center',
                 minWidth:40, width: 100,
                 allowMerging: true
             }, 
             {
                 header: '登记号',
                 binding: 'checkId',
                 allowSorting: false,
                 align:'center',
                 minWidth:60, width: 80,
                 format:'f0',
                 allowMerging: true
             }
        
        ],
        itemsSource: view
    });
    
    grid.itemFormatter = function(panel, r, c, cell) {
        cell.style.textAlign = 'center';
        var s = cell.style;
        // 由于pading = 3px; 所以 -6
		s.lineHeight = (parseInt(s.height.replace("px")) - 6)+"px";
    };
    //初始化form表单
    formInit();
}
);

/**
 * 关闭房间设置弹出框
 * */
function closeAlert(){
	$(".leaveTheRoom").fadeOut();
	$(".alertDivBg2").fadeOut();
	$(".alertDivBg3").fadeOut();
	$(".otherInformationDiv").fadeOut();
	$(".featuresButton").fadeOut();
	$(".splitInfoDiv").fadeOut();
}

/**
 * 初始化房间设置复选框
 * */
function initRoomCharaCheck(){
	var rmValue = $("input[name='roomCharacter']").val();
	if(rmValue!=null && rmValue>0){
		$("input[name='roomCharacterIS']").prop("checked",true);
	}else{
		$("input[name='roomCharacterIS']").prop("checked",false);
	}
	
}


function formInit(){
	//
	
	$("#bookform input").inputmask();
	$("#id_book_list").inputmask("Regex",{regex: "^[0-9a-zA-Z]*$"});
	$("#theBookList").inputmask("Regex",{regex: "^[0-9a-zA-Z]*$"});
	//$("#theGstNamec").inputmask("Regex",{regex: "[\u4e00-\u9fa5]"});
	$("#skyd input").inputmask();
	$("#skyd select,checkbox").change(inputListener);
	$("#skyd input,textarea").keyup(inputListener);
	$("#skyd input[type='checkbox']").not("input[readonly]").bind('click',inputListener);
	//合约单位
	hyUnitDataBind();
	//客房特征
    chkAll();
}
function inputListener(event){
	var id=$(this).attr('id');
	btnStatusTriger('1');
    var checkIdval = $("#isEdit").val();
    if(checkIdval=="true"){
    	$(".remainDiv").each(function(){
    		$(this).html("");
    	});
    }
}
var disable0='color: grey;cursor:not-allowed;';
var enable0='cursor:pointer,color:inherit;';
function btnStatusTriger(a){//console.log($("div .GuestTabR a[class*='group1']"));}
	if(a=='1'){//
		$("div .GuestTabR a[class*='group1']").each(function() {
			$(this).removeAttr("disabled");
			$(this).attr('style',enable0);
			if($(this).attr('jack')!=''){
				$(this).attr('onclick',$(this).attr('jack'));
			}
			return true;
		});
		$("div .GuestTabR a[class*='group2']").each(function() {
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
	}else if(a=='2'){
		$("div .GuestTabR a[class*='group1']").each(function() {
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
		$("div .GuestTabR a[class*='group2']").each(function() {
			$(this).removeAttr("disabled");
			$(this).attr('style',enable0);
			if($(this).attr('jack')!=''){
				$(this).attr('onclick',$(this).attr('jack'));
			}
			return true;
		});
	}else if(a=='3'){
		$("div .GuestTabR a[class*='group1']").each(function() {
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
		$("div .GuestTabR a[class*='group2']").each(function() {
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
	}
}
function inputReadonlyTrigger(r){
	var gry9="gry_9";
	if(r){
		$("#skyd input").each(function(){
			$(this).attr('style',disable0);
			var type=$(this).attr('type');
			var clasz=$(this).attr('class');
			if(type!='hidden'){
				$(this).attr('readonly','readonly');
				if(type=='select'){
					$(this).attr('disabled','disabled');
				}
				if(type=='checkbox'){
					$(this).attr('disabled','disabled');
				}
			}
		});
		$("#skyd select").each(function(){
			$(this).attr('style',disable0);
			$(this).attr('disabled','disabled');
		});
		//
		$("#splitInfoDiv input").each(function(){
			$(this).attr('style',disable0);
			var type=$(this).attr('type');
			var clasz=$(this).attr('class');
			if(type!='hidden'){
				$(this).attr('readonly','readonly');
				if(type=='select'){
					$(this).attr('disabled','disabled');
				}
				if(type=='checkbox'){
					$(this).attr('disabled','disabled');
				}
			}
		});
		$("#splitInfoDivOper a").each(function(){
			$(this).attr('style',disable0);
			var type=$(this).attr('type');
			var clasz=$(this).attr('class');
			$(this).removeAttr("onclick");
			
		});
		$("#featuresDivOper a").each(function(){
			$(this).attr('style',disable0);
			var type=$(this).attr('type');
			var clasz=$(this).attr('class');
			$(this).removeAttr("onclick");
			
		});
		$("#otherForm select").each(function(){
			$(this).attr('style',disable0);
			$(this).attr('disabled','disabled');
		});
		//
		$(".featuresButton  input").each(function(){
			$(this).attr('style',disable0);
			var type=$(this).attr('type');
			if(type!='hidden'){
				$(this).attr('readonly','readonly');
				if(type=='select'){
					$(this).attr('disabled','disabled');
				}
				if(type=='checkbox'){
					$(this).attr('disabled','disabled');
				}
			}
		});
	}else{
		$("#skyd input").each(function(){
			$(this).attr('style',enable0);
			var type=$(this).attr('type');
			var clasz=$(this).attr('class');
			if(type=='select'){
				$(this).attr('disabled',false);
			}
			if(type=='checkbox'){
				$(this).attr('disabled',false);
			}
			//if(clasz.indexOf(gry9)==-1){
			//	$(this).removeAttr('readonly')
			//}
			if(!$(this).hasClass(gry9))
			   $(this).removeAttr('readonly')
		});
        $("#skyd select").each(function(){
        	$(this).attr('style',enable0);
        	$(this).attr('disabled',false);
		});
        //
        $("#splitInfoDivOper a").each(function(){
			$(this).attr('style',enable0);
			var type=$(this).attr('type');
			var jack=$(this).attr('jack');
			$(this).attr('onclick',jack);
		});
        $("#featuresDivOper a").each(function(){
			$(this).attr('style',enable0);
			var type=$(this).attr('type');
			var jack=$(this).attr('jack');
			$(this).attr('onclick',jack);
		});
        $("#otherForm select").each(function(){
        	$(this).attr('style',enable0);
        	$(this).attr('disabled',false);
		});
        //
        $(".featuresButton  input").each(function(){
			$(this).attr('style',enable0);
			var type=$(this).attr('type');
			if(type=='select'){
				$(this).attr('disabled',false);
			}
			if(type=='checkbox'){
				$(this).attr('disabled',false);
			}
			$(this).removeAttr('readonly')
		});
	}
}
//------------------------------------Jquery初始化结束----------------------------------------------------//
/**
 * 详情分账设置加载
 */
function loadConsume(checked){
	
	var reachTime = $("#skyd input[name='reachDate']").val();
	var leaveTime = $("#skyd input[name='leaveDate']").val();
	
	$("#consumeA_ID li").remove();
	$("#consumeB_ID li").remove();
	$.ajax({
		url:path+'/guest/consumes.do',
		data:{checkId:$("#checkId").val()},
		dataType:'json',
		type:'post',
		async:false,
		success:function(data){
			$("#consumeA_ID").html("");
			$("#consumeB_ID").html("");
			if(data.consumes){
				for(var i = 0;i<data.consumes.length;i++){
					$("#consumeA_ID").append("<li id='"+data.consumes[i].codeId+"' tag='false' onclick='consumeClick(this)'>"+data.consumes[i].codeNamec+"</li>");
				}
			}
			if(data.blist){
				for(var i = 0;i<data.blist.length;i++){
					$("#consumeB_ID").append("<li id='"+data.blist[i].codeId+"' tag='false' onclick='consumeClick(this)'>"+data.blist[i].codeNamec+"</li>");
				}
			}
			$("#startDate_2").val(data.bpaied.beginDate);
			$("#endDate_2").val(data.bpaied.endDate);
			if($("#startDate_2").val()==""){
				$("#startDate_2").val(reachTime);
			}
			if($("#endDate_2").val()==""){
				$("#endDate_2").val(leaveTime);
			}
			document.getElementById("if_bate").checked=data.ifBate;
			if(checked){
				if(data.blist.length)$("#accountSetId").prop('checked',true);
				else $("#accountSetId").removeAttr('checked');
			}
		}
	});
	
}
function consumeShow(){
	
	if($("#skyd input[name='checkId']").val()!=""){
		loadConsume();
		$(".alertDivBg3").fadeIn();
		$(".splitInfoDiv").fadeIn();			
	}
}
function consumeQuit(){
	$(".alertDivBg3").fadeOut();
	$(".splitInfoDiv").fadeOut();	
}

function otherInformationFunc(){
	 /*其他信息弹出框*/
	var checkId = $("#viewId").val();
	$.ajax({
	   type: "POST",
	   url: path+"/bookroom/getGuestDocByCheckId.do",
	   data: {checkId:checkId},
	   dataType:'json',
	   success: function(msg){
		   //setJSONToForm($("#otherInfoForm"),msg.obj);
		   setFormData('otherInfoForm',msg.obj);
		   $("#otherInfoForm input[name='bookType']").val($("#bookTypeId").find("option:selected").text());
		   if(msg.obj.bookTime!=null){
			   $("#otherInfoForm input[name='bookTime']").val(formatterDate(msg.obj.bookTime,'yyyy-MM-dd hh:mm')); 
		   }
		   if(msg.obj.confirmDate!=null){
			   $("#otherInfoForm input[name='confirmDate']").val(formatterDate(msg.obj.confirmDate,'yyyy-MM-dd hh:mm'));
		   }
		   if(msg.obj.lastTime!=null){
			   $("#otherInfoForm input[name='lastTime']").val(formatterDate(msg.obj.lastTime,'yyyy-MM-dd hh:mm'));
		   }
		   $(".otherInformationDiv").fadeIn();
		   $(".alertDivBg3").fadeIn();
	   }
	});
}

/**
 * A帐点击事件
 * @param obj
 */
function consumeClick(obj){
	var tag = $(obj).attr('tag');
	$(obj).css({"background":tag=='false'?'green':'white',"color":tag=='false'?'#FFF':'#000'});
	$(obj).attr('tag',tag=='false'?'true':'false');
}

/**
 *描述：详情分账设置项目移动事件
 *@param: dir(right:右移, left:左移) 
 *@param: moveStr(moveAll:全部移动, movePart:选中部分移动)
 */
function consumeMove(dir,move){
	var dirStr = dir, moveStr = move, fromObj, toObj;
	if(dirStr == "right"){	//右移
		fromObj = $("#consumeA_ID");
		toObj = $("#consumeB_ID");
	}else{	//左移
		fromObj = $("#consumeB_ID");
		toObj = $("#consumeA_ID");	
	}

	fromObj.find("li").each(function(index,item){
		if(moveStr == "moveAll"){	// 全部移动
			$(item).css({"background":'white',"color":"#000"});
			$(item).attr('tag','false');
			toObj.append(item);
		}else{
			if($(item).attr('tag')=='true'){	//选中部分移动
				consumeClick(item);
				toObj.append(item);
			}
		}
	});
}


/**
 * 详情分账设置确定按钮
 */
function consumeSubmit(){
	var items = $("#consumeB_ID li");
	var ids='';
	var startDate=$("#startDate_2").val();
	var endDate=$("#endDate_2").val();
	if(items.length>0){
		if($("#startDate_2").val()==""){
			altWaringMsg('请选择起始日期');
			return false;
		}
		if($("#endDate_2").val()==""){
			altWaringMsg('请选择终止日期');
			return false;
		}
	}
	$(items).each(function(index,item){
		ids+=item.id+",";
	});
	var ifBate = document.getElementById("if_bate").checked?'1':'0';
	$.ajax({
		url:path+'/guest/addBpaied.do',
		type:'post',
		dataType:'json',
		cache:false,
		data:{checkId:$("#viewId").val(),cons:ids.substring(0, ids.length-1),beginDate:startDate,endDate:endDate,operId:'1',updateTimes:1,ifBate:ifBate},
		success:function(data){
			//btnStatusTriger('1');
			consumeQuit();
		}
	});
	if(items.length>0){
		$("#accountSetId").prop('checked','checked');
	}else{
		$("#accountSetId").removeAttr('checked');
	}
}

/**
 * 退出散客预定

$("#quitIndivial").click(function(){
	bookRoomViewTemp = undefined;
	var checkIdval = $("#isEdit").val();
	if (checkIdval == "true"){//edit
		refreshGrid(2);
	}else refreshGrid(1);
	$(".orderDetailsDiv").fadeOut();
	$(".alertDivBg").fadeOut();
}); */

function quitIndivialExit(){
	bookRoomViewTemp = undefined;
	var checkIdval = $("#isEdit").val();
	if (checkIdval == "true"){//edit
		refreshGrid(2);
	}else refreshGrid(1);
	$(".orderDetailsDiv").fadeOut();
	$(".alertDivBg").fadeOut();
}



/**
 * 验证添加、修改表单
 * */
function formValidate(){
	var errors = [];
	var conf = {
			  onError : function($form) {
			      alert('验证表单 '+$form.attr('id')+' 失败!');
			    },
			  onSuccess : function($form) {
			      alert('表单 '+$form.attr('id')+' 验证通过!');
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
	if(!$("#skyd").isValid(lang, conf, false) ) {
	    //displayErrors( errors );
		if(errors.length>0){
			//alert(errors[0].el.attr('label')+"  "+errors[0].error);
			altWaringMsg(errors[0].el.attr('label')+"  "+errors[0].error);
			return false;
		}
	   } else {
		    var gst_num = $("#skyd input[name='gstNum']").val();
		    if(gst_num!=""){
		    	if(gst_num<1){
					altWaringMsg("预计人数不能小于1");
					return false;
				}else{
					return true;
				}
		    }else{
		    	return true;
		    }
			
	     //return true;
	}
}

function viewAlert() {
    
    if (view == undefined || view.currentItem==null) {
        altWaringMsg("请先选中一个预订单");
    } else {
    	gridSelRowNum=grid.selection.row;
        goView(view.currentItem.checkId);
    }

}
function xxx(){
	
   
}
//------------------------------------------------------查询列表goSearch-------------------------------------------------------//	
function goSearch(code) {
	$("#keepSearch").val(code);
    $("#symbol").val("");
    $("#codeLetter").val("");
    if (code != 0) {
        if (code != "*" && code != "#") {
            $("#codeLetter").val(code);
        } else {
            if (code == "*") {
                //无效
                $("#symbol").val(1);
            }
            if (code == "#") {
                //有效
                $("#symbol").val(0);
            }
        }
    } else {
        var inputV = true;
        var bookStat = $("#search_bookStat").val();
        if (bookStat) {
            inputV = false;
        } else {
            $(".roomStatus input[type='text']").each(function() {
                if ($(this).val() != "") {
                    inputV = false;
                    return false;
                }
            }
            );
        }
        if (inputV) {
            altWaringMsg("请输入查询条件");
            return false;
        }
    
    }
    
    $.ajax({
        type: "POST",
        url: path + "/bookroom/guestdocbooklist.do",
        data: $("#bookform").serialize(),
        dataType: "json",
        success: function(msg) {
            if (msg.success) {
                var count = msg.obj.length;
                var data = msg.obj;
                var number = 0;
                var tempCheckId;
                var _bookOrderNo = "" ;
                array.length=0;
                for (var i = 0; i < count; i++) {
                	tempCheckId=data[0].checkId;
                    var status = "";
                    if (data[i].bookStat == "B") {
                        status = "未确认";
                    } else if (data[i].bookStat == "C") {
                        status = "取消";
                    } else if (data[i].bookStat == "O") {
                        status = "已确认";
                    } else if (data[i].bookStat == "R") {
                        status = "部分抵达";
                    } else if (data[i].bookStat == "A") {
                        status = "全部抵达";
                    } else if (data[i].bookStat == "N") {
                        status = "NO SHOW";
                    }
                    
                    if (tempCheckId != data[i].checkId) {
                        tempCheckId = data[i].checkId;
                        //number++;
                    }
                    var bookOrderNo = data[i].bookList ;
            		if(_bookOrderNo != bookOrderNo){
            			number++;
            		}
            		_bookOrderNo = bookOrderNo ;
                    array.push({
                    	number: number,
                         checkId: data[i].checkId,
                         bookList: data[i].bookList,
                         gstNamee: data[i].gstNamee,
                         gstNamec: data[i].gstNamec,
                         合约单位: data[i].taName,
                         电话: data[i].mobile,
                         status: status,
                         codeNamec: data[i].codeNamec,
                         bookNum: data[i].bookNum,
                         saveNum: data[i].saveNum,
                         breachDate: new Date(data[i].breachDate),
                         bleaveDate: new Date(data[i].bleaveDate),
                         房价: data[i].broomPrice,
                         取消数: data[i].cancelNum,
                         NOSHOW数: data[i].noShowNum,
                         reachNum: data[i].reachNum,
                    });
                
                }
                
                
                // create CollectionView on the data (to get events)
                
                view = new wijmo.collections.CollectionView(array);
                if(grid!=null){
                	grid.dispose();
                }
                grid = new wijmo.grid.FlexGrid('#ydtable',{
                    autoGenerateColumns: false,
                    allowMerging: wijmo.grid.AllowMerging.All,
                    allowDragging:wijmo.grid.AllowDragging.None,
                    isReadOnly: true,
                    selectionMode: wijmo.grid.SelectionMode.RowRange,
                    headersVisibility:wijmo.grid.HeadersVisibility.Column,
                    //allowResizing: wijmo.grid.AllowResizing.None,
                    columns: [
                         { header: '序号',binding: 'number', minWidth:40, width: 50,isReadOnly: true, allowMerging: true,cssClass:'fg_column_readOnly',align:'center'}, 
                         { header: '订单号',binding: 'bookList', minWidth:60, width: 100,allowMerging: true ,align:'center', }, 
                         {header: '中文名',binding: 'gstNamec',  minWidth:60, width: 100,allowMerging: true,align:'left',}, 
                         {header: '英文名',binding: 'gstNamee', minWidth:60, width: 93,allowMerging: true,align:'left',}, 
                    //----------------bookroom info----------------------
                    {
                        header: '房类',
                        binding: 'codeNamec',
                        allowSorting: false,
                        align:'center',
                        minWidth:40, width: 120
                    }, 
                    {
                        header: '抵店日期',
                        binding: 'breachDate',
                        minWidth:80, width: 100,
                        allowSorting: false,
                        align:'center',
                        format: 'yyyy-MM-dd'
                    }, 
                    {
                        header: '离店日期',
                        binding: 'bleaveDate',
                        minWidth:80, width: 100,
                        allowSorting: false,
                        align:'center',
                        format: 'yyyy-MM-dd'
                    }, 
                    {
                        header: '订房数',
                        binding: 'bookNum',
                        allowSorting: false,
                        align:'center',
                        minWidth:60, width: 70
                    }, 
                    {
                        header: '留房数',
                        binding: 'saveNum',
                        allowSorting: false,
                        align:'center',
                        minWidth:60, width: 70
                    }, 
                    {
                        header: '抵达数',
                        binding: 'reachNum',
                        allowSorting: false,
                        align:'center',
                        minWidth:60, width: 70
                    }, 
                    {
                        header: '状态',
                        binding: 'status',
                        allowSorting: false,
                        align:'center',
                        minWidth:40, width: 100,
                        allowMerging: true
                    }, 
                    {
                        header: '登记号',
                        binding: 'checkId',
                        allowSorting: false,
                        align:'center',
                        minWidth:60, width: 80,
                        format:'f0',
                        allowMerging: true
                    }
                    ],
                    itemsSource: view
                });
               
                //view.trackChanges = true;
                grid.allowMerging=wijmo.grid.AllowMerging.All;
            	grid.itemsSource = view;
                grid.mergeManager = new wijmo.grid.CustomMergeManager(grid, 'bookList');
                try
                {
                	grid.selectionChanged.addHandler(function(s, e) 
                	{
	                    if (grid.getMergedRange(e.panel, e.row, e.col) != null) {
	                        if (grid.getMergedRange(e.panel, e.row, e.col).isSingleCell == false) {
	                            grid.select(grid.getMergedRange(e.panel, e.row, e.col), true);
	                        }
	                    }
	                    else
	                    {
	                        for(var c=0;c<grid.columns.length;c++)
	                        {
	                            if(grid.getMergedRange(e.panel,e.row,c)!=null)
	                            {
	                                if (grid.getMergedRange(e.panel, e.row, c).isSingleCell == false) {
	                                    grid.select(grid.getMergedRange(e.panel, e.row, c), true);
	                                }
	                            }
	                        }
	                    }
	                    
	                });
                }catch(e){
                	cosole.error("error bookroom 1007");
                }
                
                
                var host = grid.hostElement;
                //双击时间
                host.addEventListener('dblclick', function (e) {
                	gridSelRowNum=grid.selection.row;
                    var ht = grid.hitTest(e);
                    if (ht.cellType == wijmo.grid.CellType.Cell) {
                        var checkId = grid.getCellData(grid.selection.row,11);
                        goView(checkId);
                        $("#viewId").val(checkId);
                    }
                });
                
                //$("#viewId").val(view.currentItem.checkId); 
                grid.itemFormatter = function(panel, r, c, cell) {
                    cell.style.textAlign = 'center';
                    var s = cell.style;
                    // 由于pading = 3px; 所以 -6
            		s.lineHeight = (parseInt(s.height.replace("px")) - 6)+"px";
                };
                
                //grid.select(0,0);
                // update details when current item changes
               /* view.currentChanged.addHandler(function(sender, args) {
                    updateDetails();
                });*/
                grid.selection=new wijmo.grid.CellRange(-1, -1, -1, -1);
                grid.refresh();
                grid.selection=new wijmo.grid.CellRange(-1, -1, -1, -1);
                //设置选中行
                //alert(gridSelRowNum);
				if(gridSelRowNum!=undefined){//有合并行时，定位选中行有问题
					 grid.selection=new wijmo.grid.CellRange(2, -1, -1, -1);
					 //grid.selection=new wijmo.grid.CellRange(-1, -1, -1, -1);
				}
                //view.currentPosition =4;
                // update the details when the CollectionView's currentItem changes
                function updateDetails() {
                	//alert(view.currentItem.checkId);
                    var item = view.currentItem;
                    $("#viewId").val(item.checkId);
                }
                /////////////////////////////////////////////
            }
        }
    });
    
    
}
//------------------------------------------------------查询列表goSearch   end-------------------------------------------------------//	
//清空条件	
function clearText() {
    $("#bookform").resetForm();
    //重新设置有效按钮选中
    //document.getElementById("effect").checked=true;
}
function limitConvert(){
	$("#biilbLimit").val(getDoubleStr($("#biilbLimit").val()));
}
var getDoubleStr=function(num){
	num=num+"";
	if(num=="") return "0.00";
	var index=num.indexOf(".");
	if(index!=-1){//有点号
		if((index+1)==num.length){
			num+='00';
		}else if((index+2)==num.length){
			num+='0';
		}
	}else{//没有点好
		num+='.00';
	}
	return num;
}
/**
 * 清空留房查询form表单
 */
function clearRemainConditionForm() {
    $("#remainBuildingName").val("0");
    $("#remainFloorNo").val("");
    $("#TMroomCharacter_show").val("");
    $("#TMroomCharacter").val("");
}
//检查订单号是否重复
function checkBookList(obj) {
    var bookNo = $(obj).val();
    var url = path + "/bookroom/checkNo.ajax";
    $.post(url, {
        bookNo: bookNo
    }, function(data) {
        if (data) {
            altWaringMsg("订单号已经存在！");
            $("#bookList").val(" ");
            return false;
        }
    }
    );
}
//加载留房
function goBookRoomList() {
    var checkId = $("#checkId").val();
    var url = path + "/bookroom/retain.do";
    $.post(url, {
        checkId: checkId
    }, function(data) {
        data = eval(data);
        var tHtml = "";
        for (var i = 0; i < data.length; i++) {
            tHtml += "<tr>";
            tHtml += "<td width='100'>" + (i + 1) + "</td>";
            tHtml += "<td width='100'>" + data[i].roomtypeId + "</td>";
            tHtml += "<td width='100'>" + data[i].bookNum + "</td>";
            tHtml += "<td width='100'>" + data[i].saveNum + "</td>";
            tHtml += "<td width='100'>" + data[i].reachDate + "</td>";
            tHtml += "<td width='100'>" + data[i].leaveDate + "</td>";
            tHtml += "</tr>";
        }
        $("#roomTbody").html(tHtml);
    }
    );
}

/**
 * 判断页面是否修改过
 * @param formId 
 * @param objArray 从后台加载的原始数据
 * @param dataObj 页面form表单中的数据
 * @returns {Boolean}
 */
function getChange(formId, objArray, dataObj) {
    var isChanged = false;
    var form = document.getElementById(formId);
    for (var i = 0; i < form.elements.length; i++) {
        var element = form.elements[i];
        var name = element.name;
        if (element.tagName == 'INPUT' || element.tagName == 'SELECT') {
            if ($.trim(objArray[name + '']) != $.trim(dataObj[name + ''])) {
                isChanged = true;
                break;
            }
        }
    
    }
    return isChanged;
}

var objArray;

/**
 * 确认
 */
function certain(){
///////------------------------------------------确定add Action---------------------------------------------
	if(!formValidate()){
		return false;
	}
	    var checkIdval = $("#isEdit").val();
	    var temp = [];
	    var bookBool=true;
	    var msg="留房数据不正确";
	    if (checkIdval == "true") {
	    	if(typeof(editBookRoomFlexGrid)!="undefined"){
	    		////验证留房列表数据变更及正确性
	    		var items =  editBookRoomFlexGrid.itemsSource.items;
	    		if(items.length==0){
	    			bookBool=false;
	    			altWaringMsg("请填写预定信息");
	    			return false;
	    		}
	    		if(items && items.length > 0){
	    			for(var i=0 ; i < items.length ; i++){
	    				var reachDate = items[i].reachDate;
	    				var leaveDate = items[i].leaveDate;
	    				var reachTime = items[i].reachTime;
	    				
	    				if(isNull(reachDate)){
	    					bookBool=false;
	    					msg="抵店日期不能为空!";
	    					break;
	    				}
	    				
	    				if( isNull(leaveDate)){
	    					bookBool=false;
	    					msg="离店日期不能为空!";
	    					break;
	    				}
	    				if(isNull(reachTime)){
	    					bookBool=false;
	    					msg="抵店时间不能为空!";
	    					break;
	    				}
	    				
	    				if(!Utilities.RegValidate.isSimpleDate(reachDate) || !Utilities.RegValidate.isSimpleDate(leaveDate) || !Utilities.RegValidate.isSimpleTime(reachTime)){
	    					bookBool=false;
	    					break;
	    				}
	    				var r1=parseInt(reachDate.replaceAll('-',''));
	    				var r2=parseInt(leaveDate.replaceAll('-',''));
	    				if(r1>r2){
	    					msg="抵店时间不能大于离店时间";
	    					bookBool=false;
	    					break;
	    				}
	    				var bookNum = items[i].bookNum;
	    				if(!Utilities.RegValidate.isNumber(bookNum)){
	    					msg="订房数必须是一个数字。";
	    					bookBool=false;
	    					break;
	    				}
	    				if(parseInt(bookNum) <= 0){
	    					msg="订房数不能小于1。";
	    					bookBool=false;
	    					break;
	    				}
	    			}
	    		}
	    		/*// 添加
	    		var addItems =  editBookRoomFlexGrid.itemsSource.itemsAdded;
	    		if(addItems && addItems.length > 0){
	    			for(var i=0 ; i < addItems.length ; i++){
	    				var reachDate = addItems[i].reachDate;
	    				var leaveDate = addItems[i].leaveDate;
	    				var reachTime = addItems[i].reachTime;
	    				if(isNull(reachDate) || isNull(reachDate) || isNull(reachTime)){
	    					bookBool=false;
	    					break;
	    				}
	    				if(!Utilities.RegValidate.isSimpleDate(reachDate) || !Utilities.RegValidate.isSimpleDate(leaveDate) || !Utilities.RegValidate.isSimpleTime(reachTime)){
	    					bookBool=false;
	    					break;
	    				}
	    				var r1=parseInt(reachDate.replaceAll('-',''));
	    				var r2=parseInt(leaveDate.replaceAll('-',''));
	    				if(r1>r2){
	    					msg="抵达时间不能大于离开时间";
	    					bookBool=false;
	    					break
	    				}
	    			}
	    		}
	    		// 修改
	    		var editItems =  editBookRoomFlexGrid.itemsSource.itemsEdited;
	    		if(editItems && editItems.length > 0){
	    			for(var i=0 ; i < editItems.length ; i++){
	    				var reachDate = editItems[i].reachDate;
	    				var leaveDate = editItems[i].leaveDate;
	    				var reachTime = editItems[i].reachTime;
	    				if(isNull(reachDate) || isNull(reachDate) || isNull(reachTime)){
	    					bookBool=false;
	    					break;
	    				}
	    				if(!Utilities.RegValidate.isSimpleDate(reachDate) || !Utilities.RegValidate.isSimpleDate(leaveDate) || !Utilities.RegValidate.isSimpleTime(reachTime)){
	    					bookBool=false;
	    					break;
	    				}
	    				var r1=parseInt(reachDate.replaceAll('-',''));
	    				var r2=parseInt(leaveDate.replaceAll('-',''));
	    				if(r1>r2){
	    					msg="抵店时间不能大于离店时间";
	    					bookBool=false;
	    					break
	    				}
	    			}
	    		}*/
	    		//end;
	    		if(!bookBool){
	    			 altWaringMsg(msg);
	    			return false;
	    		}
	    		temp.push(editBookRoomView.items);
	    	}
	    } else if (typeof addBookRoomFlexGrid != "undefined") {
	    	//验证留房列表数据变更及正确性
	    	// 添加
	    	var addItems = addBookRoomFlexGrid.itemsSource.itemsAdded;
	    	// 修改
	    	var editItems = addBookRoomFlexGrid.itemsSource.itemsEdited;
	    	var items = addBookRoomFlexGrid.itemsSource.items;
	    	if(items.length==0){
	    		 altWaringMsg("请填写预定信息");
	    		return false;
	    	}
	    	if(items && items.length > 0){
	    		msg="";
    			for(var i=0 ; i < items.length ; i++){
    				var reachDate = items[i].reachDate;
    				var leaveDate = items[i].leaveDate;
    				var reachTime = items[i].reachTime;
    				if(isNull(reachDate)){
    					bookBool=false;
    					msg="抵店日期不能为空!";
    					break;
    				}
    				
    				if( isNull(leaveDate)){
    					bookBool=false;
    					msg="离店日期不能为空!";
    					break;
    				}
    				if(isNull(reachTime)){
    					bookBool=false;
    					msg="抵店时间不能为空!";
    					break;
    				}
    				
    				if(!Utilities.RegValidate.isSimpleDate(reachDate) || !Utilities.RegValidate.isSimpleDate(leaveDate) || !Utilities.RegValidate.isSimpleTime(reachTime)){
    					bookBool=false;
    					msg="离店日期格式不正确";
    					break;
    				}
    				var r1=parseInt(reachDate.replaceAll('-',''));
    				var r2=parseInt(leaveDate.replaceAll('-',''));
    				if(r1>r2){
    					msg="抵店时间不能大于离店时间";
    					bookBool=false;
    					break;
    				}
    				
    			}
    		}
	    	if(!bookBool){
    			//altWaringMsg("留房数据不正确");
	    		altWaringMsg(msg);
    			return false;
    		}
	    	//end;
	    	temp.push(addBookRoomView.items);
	    }
	    
	    var isCopyOrder = $("#isCopyOrder").val();
	    if(isCopyOrder){
	    	if(typeof(editBookRoomFlexGrid)!="undefined"){
	    		temp.push(editBookRoomView.items);
	    	}
	    	$("#isCopyOrder").val("");
	    }
	    
	    if(typeof roomIdArray =="undefined"){
	    	roomIdArray = new Array();
	    }
	    //保存到服务器
	    var frm = $("#skyd");
	    var dataObj = getFormJson(frm);
	    dataObj.bookTime = dataObj.bookTime + ":00";
	    if(dataObj.confirmDate!=""){
	    	dataObj.confirmDate = dataObj.confirmDate +":00";
	    }
	    //var isChanged=getChange("skyd",objArray,dataObj);
	    //alert(isChanged);
	    if (true) {
	        //修改
	        if (checkIdval == "true") {
	            var url = path + "/bookroom/update.do";
	        } else {
	            //保存
	            var url = path + "/bookroom/save.do";
	        }
	        $.ajax({
	            url: url,
	            type: "post",
	            data: {
	                guestDocJson: JSON.stringify(dataObj),
	                bookRoomJson: JSON.stringify(temp),
	                bookState:$("#bookStatReal").val()==""?"B":$("#bookStatReal").val(),
	                roomIds:roomIdArray.join(","),
	                currBookRoomId:currBookRoomId
	            },
	            dataType: "json",
	            success: function(data) {
	                if (data.success) {
	                	roomIdArray = new Array();
	                    if (data.obj != "") {
	                        $("#billaId").val(data.obj.billaId);
	                        $("#billbId").val(data.obj.billbId);
	                        //设置时间
	                        $("#reachDate").val(formatterDate(data.obj.reachDate,'yyyy-MM-dd'));
	                        $("#leaveDate").val(formatterDate(data.obj.leaveDate,'yyyy-MM-dd'));
	                    }
	                    /*if(typeof(editBookRoomFlexGrid)!="undefined"){
	                    	currOper = "updated";
	                    	goView(data.obj.checkId);
	                    } else if (typeof addBookRoomFlexGrid != "undefined") {
	                    	currOper = "added";
	                    	goView(data.obj.checkId);
	                    }*/
	                    goView(data.obj.checkId);
	                    $(".ttrr").css("display" , "") ;
	                    var val = $("input[name='active']:checked").val();
	                    goSearch($("#keepSearch").val()!=""?$("#keepSearch").val():"0");
	                    if (checkIdval == "true") {
	        	            //编辑
	                    	refreshGrid('2');
	        	        } else {
	        	            //新增
	        	        	refreshGrid('1');
	        	        }
	                    //alert(data.msg);
	                } else {
	                	altWaringMsg(data.msg);
	                	return false;
	                }
	            },
	            error: function(err) {
	                altWaringMsg("操作失败!");
	            }
	        });
	    
	    } else {
	        altWaringMsg("您没有做任何修改！");
	    }
}

/**
 * 刷新数据表格
 */
function refreshGrid(type){
	if(type==undefined){return false;}
	if(type=='1'){
		goSearch('#');
	}else if(type=='2'){
		goSearch('0');
	}
}
/**
 * 显示预订单
 */
function showBookForm(){
	$(".orderDetailsDiv").fadeIn();
    $(".alertDivBg").fadeIn();
}
/**
 * 关闭预订单
 */
function closeBookForm(){
	$(".orderDetailsDiv").fadeOut();
    $(".alertDivBg").fadeOut();
}

/**
 * 确认订单
 */
function confirmOrder(){
	altInfMsg('是否将本订单更改为已确认？',function(){
        $.ajax({
            url: '/bookroom/confirmGuestDoc.do',
            type: "post",
            data: {
            	checkId: $("#checkId").val(),
            },
            dataType: "json",
            success: function(data) {
                if (data.success) {
                	
                    goSearch($("#keepSearch").val()!=""?$("#keepSearch").val():"0");
                    closeBookForm();
                } else {
                    alert(data.msg);
                }
            },
            error: function(err) {
                altWaringMsg("操作失败!");
            }
        });
	},function(){return false;});
	
}
/**
 * 取消订单
 */
function btn_cancelOrder(){
	altInfMsg('是否取消本订单？',function(){
		$.ajax({
            url: path + "/bookroom/cancelbookroom.do",
            type: "post",
            data: {
                check_id : selCheckId,
                cacelObject : "S"
            },
            dataType: "json",
            success: function(data) {
                if (data.issuccess) {
                	altSuccessMsg("订单已取消。");
                    $(".ttrr").css("display" , "") ;
                    var val = $("input[name='active']:checked").val();
                    goSearch($("#keepSearch").val()!=""?$("#keepSearch").val():"0");
                    
                    //关闭当前窗口
                    bookRoomViewTemp = undefined;
                	$(".orderDetailsDiv").fadeOut();
                	$(".alertDivBg").fadeOut();
                    //alert(data.msg);
                }
            },
            error: function(err) {
                altWaringMsg("操作失败!");
            }
        });
//		var checkIdval = $("#isEdit").val();
//	    var temp = [];
//	    var bookState="B";
//	    if(currOper!="added"){
//	    	bookState = "O";
//	    }
//	    if (checkIdval == "true") {
//	    	if(typeof(editBookRoomFlexGrid)!="undefined"){
//	    		
//	    		temp.push(editBookRoomView.items);
//	    	}
//	    } else if (typeof addBookRoomFlexGrid != "undefined") {
//	    	temp.push(addBookRoomView.items);
//	    }
//	    
//	    if(typeof roomIdArray =="undefined"){
//	    	roomIdArray = new Array();
//	    }
//	    
//	    //保存到服务器
//	    var frm = $("#skyd");
//	    var dataObj = getFormJson(frm);
//	    dataObj.bookTime = dataObj.bookTime + ":00";
//	    //var isChanged=getChange("skyd",objArray,dataObj);
//	    //alert(isChanged);
//	    if (true) {
//	        //修改
//	        if (checkIdval == "true") {
//	            var url = path + "/bookroom/update.do";
//	        } else {
//	            //保存
//	            var url = path + "/bookroom/save.do";
//	        }
//	        $.ajax({
//	            url: url,
//	            type: "post",
//	            data: {
//	                guestDocJson: JSON.stringify(dataObj),
//	                bookRoomJson: JSON.stringify(temp),
//	                bookState:'C',
//	                roomIds:roomIdArray.join(","),
//	                currBookRoomId:currBookRoomId
//	            },
//	            dataType: "json",
//	            success: function(data) {
//	                if (data.success) {
//	                	roomIdArray = new Array();
//	                    if (data.obj != "") {
//	                        $("#billaId").val(data.obj.billaId);
//	                        $("#billbId").val(data.obj.billbId);
//	                        //设置时间
//	                        $("#reachDate").val(formatterDate(data.obj.reachDate,'yyyy-MM-dd'));
//	                        $("#leaveDate").val(formatterDate(data.obj.leaveDate,'yyyy-MM-dd'));
//	                    }
//	                    if(typeof(editBookRoomFlexGrid)!="undefined"){
//	                    	goView(data.obj.checkId);
//	                    } else if (typeof addBookRoomFlexGrid != "undefined") {
//	                    	goView(data.obj.checkId);
//	                    }
//	                    $(".ttrr").css("display" , "") ;
//	                    var val = $("input[name='active']:checked").val();
//	                    goSearch($("#keepSearch").val()!=""?$("#keepSearch").val():"0");
//	                    
//	                    //关闭当前窗口
//	                    bookRoomViewTemp = undefined;
//	                	$(".orderDetailsDiv").fadeOut();
//	                	$(".alertDivBg").fadeOut();
//	                    //alert(data.msg);
//	                } else {
//	                    alert(data.msg);
//	                }
//	            },
//	            error: function(err) {
//	                altWaringMsg("操作失败!");
//	            }
//	        });
//	    
//	    } else {
//	        altWaringMsg("您没有做任何修改！");
//	    }
	},function(){return false;});
	
}
/**
 * 放弃订单
 */
function btn_giveup(){
	restForm();
	//alert("giveup Order");
}
/**
 * 复制订单
 * */
function copyOrder(){
	altInfMsg('是否复制本订单为另一个新增订单？',function(){
		//置空订单号
		$("#skyd input[name='bookList']").val("");
		$("#skyd input[name='bookList']").removeClass("gry_9").removeAttr("readonly");
		
		//充值房间特征
		$("input[name='roomCharacterIS']").prop("checked",false);
		
		//分账设置不可用
		$("#accountSetId").prop("checked",false);
		$("#split").addClass("disabledAButton").removeAttr("href");
		
		//置空checkId
		$("#skyd input[name='checkId']").val("");
		//重置
		 $("#isEdit").val("");
		//充值按钮灰显
		btnStatusTriger('1');
		$("#isCopyOrder").val("true");
		//重置FlexGrid
		if(typeof editBookRoomView !="undefined"){
			var value = editBookRoomView.items;
			for(var i=0;i<value.length;i++){
				value[i].id="";
				value[i].checkId="";
			}
			editBookRoomFlexGrid.itemsSource = editBookRoomView;
			editBookRoomFlexGrid.refresh();
			$(".ttrr").css("display","none") ;
			
		}
	},function(){return false;});
}
/**
 * 新增留房按钮事件
 * */
function remainAdd(row) {
	var bookRoomId = addBookRoomFlexGrid.getCellData(row,1);
	currBookRoomId = bookRoomId;
    var roomTypeId = addBookRoomFlexGrid.getCellData(row, 4);
    var bookNum = addBookRoomFlexGrid.getCellData(row, 5);
    var reachDate = addBookRoomFlexGrid.getCellData(row, 6);
    var leaveDate = addBookRoomFlexGrid.getCellData(row, 7);
    var col = addBookRoomFlexGrid.columns.getColumn('roomtypeId');
    var roomTypeName = col.dataMap.getDisplayValue(roomTypeId);
    //roomTypeId,roomTypeName
    if (roomTypeId == "" || roomTypeId == null ) {
        altWaringMsg("请选择房类");
        return;
    }
    if (bookNum == "" || bookNum == null ) {
        altWaringMsg("请输入定房数");
        return;
    }
    
    if (reachDate == "" || reachDate == null ) {
        altWaringMsg("请选择抵店日期");
        return;
    }
    
    if (leaveDate == "" || leaveDate == null ) {
        altWaringMsg("请选择抵店日期");
        return;
    }
    var checkId = "";
    remainRoom(bookRoomId,roomTypeId, roomTypeName, checkId, reachDate, leaveDate);
}


/////
//放弃:充值按钮
function restForm() {
    var checkId = $("#isEdit").val();
    if (checkId) {
        goView(selCheckId);
    } else {
        $("#skyd").resetForm();
        // 同时初始化 grid
        if (typeof(addBookRoomFlexGrid) != "undefined") {
        	addBookRoomView = new wijmo.collections.CollectionView(null);
            addBookRoomFlexGrid.itemsSource = addBookRoomView ;
            addBookRoomFlexGrid.refresh();
            addBookRoomFlexGrid.select(-1,-1);
        }
    }

}
//---------------------------------------打开新增预定弹出(主页面新增按钮事件)-----------ADD_Button----------------------------
function goAdd(checkId) {
	btnStatusTriger('1');
	inputReadonlyTrigger(false);
	selCheckId=undefined;
	$("input[name='bookList']").removeClass("gry_9").removeAttr("readonly","readonly");
	$("#bookStat").attr("value","未确认");
	/*$("#btn_giveup").removeClass("gry_9").attr("href","javascript:btn_giveup()");
	$("#btn_confirmOrder").addClass("disabledAButton").removeAttr("href");
	$("#btn_cancelOrder").addClass("disabledAButton").removeAttr("href");
	$("#insert").removeClass("disabledAButton").attr("href","javascript:certain()");*/
	$("#split").addClass("disabledAButton").removeAttr("href");
	
	btnStatusTriger('1');
    $("#isEdit").val("");
    $("#checkId").val("");
    if (typeof(editBookRoomFlexGrid) != "undefined") {
        editBookRoomFlexGrid.dispose();
        editBookRoomFlexGrid = undefined;
        editBookRoomView = undefined;
    }
    if (typeof(addBookRoomFlexGrid) != "undefined") {
        addBookRoomFlexGrid.dispose();
        addBookRoomFlexGrid = undefined;
        addBookRoomView = undefined;
    }
    var arrayP = new Array();
    $(".orderDetailsDiv").fadeIn();
    $(".alertDivBg").fadeIn();
    $("#skyd").resetForm();
    
    $("#skyd input[name='bookTime']").val(formatterDate(new Date,'yyyy-MM-dd hh:mm'));
    var editIndex = -1;
    //bookDiaryGrid();
    var url = path + "/bookroom/view.do";
    //加载表单数据
    $.ajax({
        type: "POST",
        url: url,
        data: {
            checkId: checkId
        },
        dataType: "json",
        success: function(obj) {
        	if(null!=obj){
        		//if(obj.bookTime!=null){
           		 obj.bookTime = new Date(obj.bookTime).format("yyyy-MM-dd hh:mm");
           	     //}
           		obj.reachTime = isNull(obj.reachTime) ? "" : (obj.reachTime == "00:00" ? "" : obj.reachTime) ;
               obj.reachDate = new Date(obj.reachDate).format("yyyy-MM-dd");
               obj.leaveDate = new Date(obj.leaveDate).format("yyyy-MM-dd");
               obj.confirmDate = obj.confirmDate ? new Date(obj.confirmDate).format("yyyy-MM-dd hh:mm") : "";
               var statMsg="";
               if(obj.bookStat == 'N'){
               	statMsg = "NO SHOW";
               }else if(obj.bookStat=='A'){
               	statMsg = "全部抵达";
               }else if(obj.bookStat=='R'){
               	statMsg = "部分抵达";
               }else if(obj.bookStat=='B'){
               	statMsg = "未确认";
               }else if(obj.bookStat=='C'){
               	statMsg = "取消";
               }else if(obj.bookStat=='O'){
               	statMsg = "已确认";
               }
               obj.bookStat = statMsg;
               //setForm("skyd",obj);
               setJSONToForm($("#skyd"), obj);
               objArray = obj;
        	}
            //
            clearLiuFangInfo();
        }
    });
    /***初始化散客预定弹出窗口的显示******/
    $.ajax({
        type: "POST",
        url: "/bookroom/getBookRoomListByCheckId.do",
        data: "checkId=" + checkId,
        dataType: "json",
        success: function(msg) {
            if (msg.success) {
                var editIndex = -1;
                roomTypeList=msg.attributes.roomTypeList;
                addBookRoomView = new wijmo.collections.CollectionView(msg.attributes.list);
                addBookRoomFlexGrid = new wijmo.grid.FlexGrid('#div_bookRoom',{
                    autoGenerateColumns: false,
                    allowMerging: wijmo.grid.AllowMerging.All,
                    isReadOnly: false,
                    selectionMode: wijmo.grid.SelectionMode.Row,
                    allowDragging:wijmo.grid.AllowDragging.None,
                    //allowResizing: wijmo.grid.AllowResizing.None,
                    headersVisibility:wijmo.grid.HeadersVisibility.Column,
                    allowAddNew: true,
                    columns: [
					{
					    header: '序号',
					    binding: "num",
					    width: 50,
					    isReadOnly: true,
					    visible: true,
					    isReadOnly:true,
						cssClass:'fg_column_readOnly',
						align:'center'
					},        
                    {
                        header: '主键',
                        binding: "id",
                        width: 50,
                        isReadOnly: true,
                        visible: false
                    }, 
                    {
                        header: '房类',
                        binding: "roomtypeId",
                        width: 119
                    }, 
                    {
                        header: '订房数',
                        binding: "bookNum",
                        align:'center',
                        width: '*'
                    }, 
                    
                    {
                        header: '抵店日期',
                        binding: "reachDate",
                        name:'reachDate',
                        align:'center',
                        width: 100
                    }, 
                    {
                        header: '离店日期',
                        binding: "leaveDate",
                        name:'leaveDate',
                        align:'center',
                        width: 100
                    }, 
                    {
                        header: '抵店时间',
                        binding:"reachTime",
                        align:'center',
                        mask:'00:00',
                        width: 80
                    }, 
                    {
                        header: '房价',
                        binding: "roomPrice",
                        isReadOnly:true,
                        align:'right',
                        width: '*'
                    }, 
                    {
                        header: '留房数',
                        binding: "saveNum",
                        name: "saveNum",
                        align:'center',
                        width: '*',
                        isReadOnly: true
                    }, 
                    {
                        header: '抵达数',
                        binding: "reachNum",
                        name:"reachNum",
                        align:'center',
                        width: '*',
                        isReadOnly: true
                    }, 
                    {
                        header: '操作',
                        binding: "buttons",
                        name: "buttons",
                        width: '*',
                        isReadOnly:true
                    }, 
                    ],
                    itemsSource: addBookRoomView
                });
                //
                addBookRoomFlexGrid.beginningEdit.addHandler(function(s, e){
                	console.log("beginningEdit");
                });
                addBookRoomFlexGrid.rowEditEnded.addHandler(function(s, e){
                	console.log("rowEditEnded");/*
                	//检查数据正确性
                	var fgRow = s.rows[e.row] ;
            		var fgCol = s.columns[e.col] ;
            		var finalCellVal = s.getCellData(e.row, e.col, true);
            		var indexVal = fgRow.dataItem.index;
            		//抵店日期
            		if(fgCol.binding == "reachDate" && !isNull(finalCellVal)){
            			if(!Utilities.RegValidate.isSimpleDate(finalCellVal)){
            				alert('抵店日期不合法');
            				return false;
            			}
            		}
            		//离店日期
            		if(fgCol.binding == "leaveDate" && !isNull(finalCellVal)){
            			if(!Utilities.RegValidate.isSimpleDate(finalCellVal)){
            				alert('离店日期不合法');
            				return false;
            			}
            		}
            		//抵店时间
            		if(fgCol.binding == "reachTime" && !isNull(finalCellVal)){
            			if(!Utilities.RegValidate.isSimpleTime(finalCellVal)){
            				alert('抵店时间不合法');
            				editBookRoomFlexGrid.setCellData(fgRow,fgCol,'00:00');
            				return false;
            			}
            		}
            		//房类
            		if(fgCol.binding == "roomtypeId" && !isNull(finalCellVal)){
            			if(!Utilities.RegValidate.isSimpleTime(finalCellVal)){
            				alert('抵店时间不合法');
            				editBookRoomFlexGrid.setCellData(fgRow,fgCol,'00:00');
            				return false;
            			}
            		}
            		*/
                });
                addBookRoomFlexGrid.cellEditEnded.addHandler(function(s, e){
                	console.log("cellEditEnded");
                	//检查数据正确性
                	var fgRow = s.rows[e.row] ;
            		var fgCol = s.columns[e.col] ;
            		var finalCellVal = s.getCellData(e.row, e.col, true);
            		var indexVal = fgRow.dataItem.index;
            		//抵店日期
            		if(fgCol.binding == "reachDate" && !isNull(finalCellVal)){
            			if(!Utilities.RegValidate.isSimpleDate(finalCellVal)){
            				altWaringMsg('抵店日期不合法');
            				return false;
            			}else{
            				var oldreachDate=$("#reachDate").val();
            				var viewLen=addBookRoomFlexGrid.itemsSource.items.length;
            				if(oldreachDate="" || (viewLen<=1)){
            					$("#reachDate").val(finalCellVal)
            				}
            				else {
            					var numNew=Utilities.utils.simpleDate2Number(finalCellVal);
            					var numOld=Utilities.utils.simpleDate2Number(oldreachDate);
            					if(numNew>numOld) $("#reachDate").val(finalCellVal);
            				}
            				getBookMinReachDate(1);
            			}
            		}
            		//离店日期
            		if(fgCol.binding == "leaveDate" && !isNull(finalCellVal)){
            			if(!Utilities.RegValidate.isSimpleDate(finalCellVal)){
            				altWaringMsg('离店日期不合法');
            				return false;
            			}else{
            				var oldleaveDate=$("#leaveDate").val();
            				var viewLen=addBookRoomFlexGrid.itemsSource.items.length;
            				if(oldleaveDate=="" || (viewLen<=1)){
            					$("#leaveDate").val(finalCellVal);
            				}else{
            					var numNew=Utilities.utils.simpleDate2Number(finalCellVal);
            					var numOld=Utilities.utils.simpleDate2Number(oldleaveDate);
            					if(numNew>numOld) $("#leaveDate").val(finalCellVal);
            				}
            				getBookMaxLeaveDate(1);
            			}
            		}
            		//抵店时间
            		if(fgCol.binding == "reachTime" && !isNull(finalCellVal)){
            			if(!Utilities.RegValidate.isSimpleTime(finalCellVal)){
            				altWaringMsg('抵店时间不合法');
            				addBookRoomFlexGrid.setCellData(fgRow,fgCol,'00:00');
            				return false;
            			}else{
            				
            			}
            		}
            		//房类
            		if(fgCol.binding == "roomtypeId" && !isNull(finalCellVal)){
            			if(finalCellVal.indexOf(":")!=-1){
            				var roomTypeCode=finalCellVal.split(":")[0];
            				if(roomTypeList!=undefined){
            					for(var i=0;i<roomTypeList.length;i++){
            						if($.trim(roomTypeCode)==$.trim(roomTypeList[i].codeId)){//roomTypeList[i].price
            							//var pricec= new Number(Utilities.utils.getDoubleStr(roomTypeList[i].price));
            							var pricec= Utilities.utils.getDoubleStr(roomTypeList[i].price);
            							//var price=parseFloat(''+Utilities.utils.getDoubleStr(roomTypeList[i].price));
            							addBookRoomFlexGrid.setCellData(e.row,e.col+5,pricec);
            							addBookRoomFlexGrid.setCellData(e.row,e.col+1,1);
            						}
            					}
            				}
            			}
            		}

            		//订房数
            		if(fgCol.binding == "bookNum" && !isNull(finalCellVal)){
            			if(finalCellVal != "" && parseInt(finalCellVal) <= 0){//
            				altWaringMsg('订房数不能小于1 ');
            				addBookRoomFlexGrid.setCellData(e.row, e.col,1);
            			}
            		}
                	
                });
                addBookRoomView.trackChanges = true;
                addBookRoomFlexGrid.itemFormatter = function(panel, r, c, cell) {
                    
                    if (panel.cellType == wijmo.grid.CellType.Cell) {
                        var col = panel.columns[c],html = cell.innerHTML;
                        if (r == editIndex) {
                        
                        } else {
                            switch (col.name) {
       
                            }
                        }
                    }
                };
                
                var col = addBookRoomFlexGrid.columns.getColumn('roomtypeId');
                col.dataMap = new wijmo.grid.DataMap(msg.attributes.roomTypeList,'codeId','codeNamec');
                createEditor(addBookRoomFlexGrid.columns.getColumn('reachDate'));
                createEditor(addBookRoomFlexGrid.columns.getColumn('leaveDate'));
            }
        }
    });
}

function  getBookMinReachDate(flag){
	if(flag==1){
		var numArray=[];
		if(addBookRoomFlexGrid==undefined) return false;
		var items= addBookRoomFlexGrid.itemsSource.items;
		if(items!=null && items!=undefined){
			for(var i=0;i<items.length;i++){
				var reachDate = items[i].reachDate;
				if(reachDate!=null && reachDate!=undefined){
					var reachDateNum= Utilities.utils.simpleDate2Number(reachDate);
					numArray.push(reachDateNum);
				}
			}
		}
		numArray.sort();
		if(numArray.length>0){
			var arr0=numArray[0].toString();
			var dstr=arr0.substr(0,4)+'-'+arr0.substr(4,2)+'-'+arr0.substr(6,2);
			$("#reachDate").val(dstr);
		}
	}else if(flag==2){
		var numArray=[];
		if(editBookRoomFlexGrid==undefined) return false;
		var items= editBookRoomFlexGrid.itemsSource.items;
		if(items!=null && items!=undefined){
			for(var i=0;i<items.length;i++){
				var reachDate = items[i].reachDate;
				if(reachDate!=null && reachDate!=undefined){
					var reachDateNum= Utilities.utils.simpleDate2Number(reachDate);
					numArray.push(reachDateNum);
				}
			}
		}
		numArray.sort();
		if(numArray.length>0){
			var arr0=numArray[0].toString();
			var dstr=arr0.substr(0,4)+'-'+arr0.substr(4,2)+'-'+arr0.substr(6,2);
			$("#reachDate").val(dstr);
		}
	}
}
function getBookMaxLeaveDate(flag){
	if(flag==1){
		var numArray=[];
		if(addBookRoomFlexGrid==undefined) return false;
		var items= addBookRoomFlexGrid.itemsSource.items;
		if(items!=null && items!=undefined){
			for(var i=0;i<items.length;i++){
				var reachDate = items[i].leaveDate;
				if(reachDate!=null && reachDate!=undefined){
					var reachDateNum= Utilities.utils.simpleDate2Number(reachDate);
					numArray.push(reachDateNum);
				}
			}
		}
		numArray.sort();
		if(numArray.length>0){
			var arr0=numArray[numArray.length-1].toString();
			var dstr=arr0.substr(0,4)+'-'+arr0.substr(4,2)+'-'+arr0.substr(6,2);
			$("#leaveDate").val(dstr);
		}
	}else if(flag==2){
		var numArray=[];
		if(editBookRoomFlexGrid==undefined) return false;
		var items= editBookRoomFlexGrid.itemsSource.items;
		if(items!=null && items!=undefined){
			for(var i=0;i<items.length;i++){
				var reachDate = items[i].leaveDate;
				if(reachDate!=null && reachDate!=undefined){
					var reachDateNum= Utilities.utils.simpleDate2Number(reachDate);
					numArray.push(reachDateNum);
				}
			}
		}
		numArray.sort();
		if(numArray.length>0){
			var arr0=numArray[numArray.length-1].toString();
			var dstr=arr0.substr(0,4)+'-'+arr0.substr(4,2)+'-'+arr0.substr(6,2);
			$("#leaveDate").val(dstr);
		}
	}
}

/**
 * 将JSON数据设置到Form,以name为键
 * @param $form
 * @param formJson
 */
function setFormData($form,formJson){
	document.getElementById($form).reset();
    var key,value,tagName,type,arr;
    for(x in formJson){
        key = x;
        value = formJson[x];
        $("#"+$form+" [name='"+key+"']").each(function(){
            tagName = $(this)[0].tagName;
            type = $(this).prop('type');
            if(tagName=='INPUT'){
                if(type=='radio'){
                    $(this).prop('checked',($(this).val().toString())===(value.toString()));
                }else if(type=='checkbox'){
                	$(this).prop('checked',($(this).val().toString())===(value.toString()));
                }else{
                    $(this).val($.trim(value));
                }
            }else if(tagName=='SELECT'){
            	value=$.trim(value.toString())
                var options= $(this)[0].options;
        		for(var i=0;i<options.length;i++){
        			if($.trim(options[i].value)==$.trim(value)){
        				$(this)[0].value=value;
        				$(this)[0].selectedIndex=i;
        				break;
        			}
        		}
            }else if(tagName=='TEXTAREA'){
            	
            }
             
        });
    }
}

//------------------------------------------------------查看详细(按钮订单详情事件)---------EDIT-------------------------------------//
function goView(checkId1) {
	selCheckId=checkId1;
	if(typeof map !="undefined"){
		map.clear();
	}else{
		map = new Map();		
	}
	$("input[name='bookList']").addClass("gry_9").attr("readonly","readonly");
	
	//$("#insert").addClass("gry_9").removeAttr("href");
	/*$("#insert").removeClass("disabledAButton").attr("href","javascript:certain()");
	$("#btn_giveup").addClass("disabledAButton").remove("href");
	$("#btn_confirmOrder").removeClass("disabledAButton").attr("href","javascript:confirmOrder()");
	$("#btn_cancelOrder").removeClass("disabledAButton").attr("href","javascript:btn_cancelOrder()");
	$("#btn_giveup").attr("disabled","disabled").addClass("disabledAButton");*/
	$("#split").removeClass("disabledAButton").attr("href","javascript:");
	var checkId="";
	if(checkId1!=""){
		checkId = checkId1;
	}else{
		checkId=$("#viewId").val();		
	}
    //$("#btn_confirmOrder").attr("href","javascript:confirmOrder();");
    if (typeof addBookRoomFlexGrid != "undefined") {
        addBookRoomFlexGrid.dispose();
        addBookRoomFlexGrid = undefined;
        addBookRoomView = undefined;
    }
    
    if (typeof editBookRoomFlexGrid != "undefined") {
        editBookRoomFlexGrid.dispose();
        editBookRoomFlexGrid = undefined;
        editBookRoomView = undefined;
    }
    var editIndex = -1;
    $("#isEdit").val("true");
    $("#checkId").val(checkId);
    var url = path + "/bookroom/view.do";
    //加载表单数据
    $.ajax({
        type: "POST",
        url: url,
        data: {
            checkId: checkId
        },
        dataType: "json",
        success: function(obj) {
        	if(obj.bookTime!=null)  obj.bookTime = new Date(obj.bookTime.time).format("yyyy-MM-dd hh:mm");
            //obj.reachtime = new Date(obj.reachDate).format("yyyy-MM-dd hh:mm:ss").substring(11, 16);
        	if(obj.reachDate!=null){
        		obj.reachDate = new Date(obj.reachDate.time).format("yyyy-MM-dd");
        	}
            obj.leaveDate = new Date(obj.leaveDate.time).format("yyyy-MM-dd");
            if(obj.confirmDate!=null){
            	obj.confirmDate = new Date(obj.confirmDate.time).format("yyyy-MM-dd hh:mm");
            }
            var statMsg="";
             orgBookStat=obj.bookStat;
            if(obj.bookStat == 'N'){
            	statMsg = "NO SHOW";
            }else if(obj.bookStat=='A'){
            	statMsg = "全部抵达";
            }else if(obj.bookStat=='R'){
            	statMsg = "部分抵达";
            }else if(obj.bookStat=='B'){
            	statMsg = "未确认";
            	obj.confirmDate = "";
            }else if(obj.bookStat=='C'){
            	statMsg = "取消";
            }else if(obj.bookStat=='O'){
            	statMsg = "已确认";
            }else if(obj.bookStat=='P'){
            	statMsg = "过期";
            }
            //obj.bookStat = statMsg;
            obj.bookStatTmp=statMsg;
            //setForm("skyd",obj);
            //setJSONToForm($("#skyd"), obj);
            setFormData('skyd',obj);
            
            //initRoomCharaCheck();
            if(null!=obj.roomCharacter && obj.roomCharacter>0){
	   			 $("#fjtzChk_dd").prop("checked",true);
	   		}else{
	   			$("#fjtzChk_dd").prop("checked",false);
	   		}
            //初始化分账复选框
            loadConsume(true);
            objArray = obj;
            if(orgBookStat=='C' || orgBookStat == 'N' || orgBookStat=='A' || orgBookStat == 'P'){
            	btnStatusTriger('3');
            	inputReadonlyTrigger(true); 
            	//特殊化处理分账设置和房价特征按钮禁用
            	$("#split").removeAttr("onclick");
            	$("#split").attr('style',disable0);
            	$("#featuresChooseButton").attr('style',disable0);
            	
            	$("#featuresChooseButton").removeAttr("onclick");
            }else{
            	btnStatusTriger('2');
            	$("#split").removeAttr('style');
            	$("#featuresChooseButton").removeAttr('style');
            	inputReadonlyTrigger(false);
            	//特殊化处理分账设置和房价特征按钮启用
            	var jack_split=$("#split").attr('jack');
    			$("#split").attr('onclick',jack_split);
    			var jack_fjtz=$("#featuresChooseButton").attr('jack');
    			$("#featuresChooseButton").attr('onclick',jack_fjtz);
            }
        }
    });
    
    //打开预定弹出
    $(".orderDetailsDiv").fadeIn();
    $(".alertDivBg").fadeIn();
    //加载预定列表数据	
    //bookDiaryGrid(checkId);
    //------加载详情页中的bookRoom的FlexGrid----------
    
    //var path=$("#path").val();
    if (checkId != "") {
        var url = path + "/bookroom/retain.do?checkId=" + checkId;
        $.ajax({
            type: "POST",
            url: url,
            data: "checkId=" + checkId,
            dataType: "json",
            success: function(msg) {
                if (msg.success) {
                	roomTypeList=msg.attributes.roomTypeList;
                    var data = msg.obj;
                    if (data != "" && data.length > 0) {
                        var array_book = [];
                        for(var i=0;i<data.length;i++){
                       	   array_book.push({
                       		 num:i+1,
                       		 id:data[i].id,
                       		 checkId :data[i].checkId,
                       		 bookRoomId: data[i].bookRoomId,
                       		 roomtypeId: $.trim(data[i].roomtypeId),
                       		 bookNum: data[i].bookNum,
                       		 saveNum:data[i].saveNum,
                       		 reachDate: formatterDate(data[i].reachDate,"yyyy-MM-dd"),
                       		 leaveDate: data[i].leaveDate,
                       		 reachTime: data[i].reachTime,
                       		 roomPrice: data[i].roomPrice,
                       		 reachNum: data[i].reachNum,
                       		 buttons: "留房",
                       		 roomNums:data[i].roomNums,
                       		 status:data[i].status
                       	   });
                       }
                        ///////  FlexGird    ///////
                        editBookRoomView = new wijmo.collections.CollectionView(array_book);
                        editBookRoomFlexGrid = new wijmo.grid.FlexGrid('#div_bookRoom',{
                            autoGenerateColumns: false,
                            selectionMode: wijmo.grid.SelectionMode.Row,
                            headersVisibility:wijmo.grid.HeadersVisibility.Column,
                            allowDragging:wijmo.grid.AllowDragging.None,
                            allowDelete:false,
                            //allowResizing: wijmo.grid.AllowResizing.None,
                            allowAddNew: true,
                            columns: [
                            {
                            	header:'序号',
                            	binding:'num',
                            	width:50,
								isReadOnly:true,
								cssClass:'fg_column_readOnly',
								align:'center'
                            },          
							{
								header:'id',
								binding:"id",
								width:50,
								isReadOnly:false,
								visible:false
							},
                            {
                            	header:'checkId',
                            	binding:"checkId",
                            	width:50,
                            	isReadOnly:false,
                            	visible:false
                            },
                            {
                                header: '主键',
                                binding: "bookRoomId",
                                width: 50,
                                isReadOnly: false,
                                visible: false
                            }, 
                            {
                                header: '房类',
                                binding: "roomtypeId",
                                width: 118
                            }, 
                            {
                                header: '订房数',
                                binding: "bookNum",
                                align:'center',
                                width: '*'
                            }, 
                            {
                                header: '抵店日期',
                                binding: "reachDate",
                                name:'reachDate',
                                format: 'yyyy-MM-dd',
                                align:'center',
                                width: 100
                            }, 
                            {
                                header: '离店日期',
                                binding: "leaveDate",
                                name:'leaveDate',
                                format: 'yyyy-MM-dd',
                                align:'center',
                                width: 100
                            }, 
                            {
                                header: '抵店时间',
                                binding:'reachTime',
                                name:'reachTime',
                                align:'center',
                                mask:'00:00',
                                width: 80
                            }, 
                            {
                                header: '房价',
                                binding: "roomPrice",
                                align:'right',
                                dataType:'Number',format:'f2',
                                isReadOnly: true,
                                width: '*'
                            }, 
                            {
                                header: '留房数',
                                binding: "saveNum",
                                name: "saveNum",
                                width: '*',
                                align:'center',
                                isReadOnly: true
                            }, 
                            {
                                header: '抵达数',
                                binding: "reachNum",
                                name:"reachNum",
                                width: '*',
                                align:'center',
                                isReadOnly: true	
                            }, 
                            {
                                header: '操作',
                                binding: "buttons",
                                name: "buttons",
                                align:'center',
                                width: '*',
                            	isReadOnly: true	
                            } ,{
                                header: 'status',
                                binding: "status",
                                width: 50,
                                isReadOnly: false,
                                visible: false
                            }                         
                            ],
                            itemsSource: editBookRoomView,
                        });
                        if(!isEnabled()){
                        	editBookRoomFlexGrid.selectionChanging = function(e) {
                                return false;
                            };
                            editBookRoomFlexGrid.isReadOnly=true;
                        }
                        
                        editBookRoomFlexGrid.beginningEdit.addHandler(function(s, e){
                        	console.log("beginningEdit");
                        });
                        editBookRoomFlexGrid.rowEditEnded.addHandler(function(s, e){
                        	console.log("rowEditEnded");
                        });
                        editBookRoomFlexGrid.cellEditEnded.addHandler(function(s, e){
                        	//检查数据正确性
                        	var fgRow = s.rows[e.row] ;
                    		var fgCol = s.columns[e.col] ;
                    		var finalCellVal = s.getCellData(e.row, e.col, true);
                    		var indexVal = fgRow.dataItem.index;
                    		//抵店日期
                    		if(fgCol.binding == "reachDate" && !isNull(finalCellVal)){
                    			if(!Utilities.RegValidate.isSimpleDate(finalCellVal)){
                    				altWaringMsg('抵店日期不合法');
                    				return false;
                    			}else{
                    				var oldreachDate=$("#reachDate").val();
                    				var viewLen=editBookRoomFlexGrid.itemsSource.items.length;
                    				if(oldreachDate="" || (viewLen<=1)){
                    					$("#reachDate").val(finalCellVal)
                    				}
                    				else {
                    					var numNew=Utilities.utils.simpleDate2Number(finalCellVal);
                    					var numOld=Utilities.utils.simpleDate2Number(oldreachDate);
                    					if(numNew>numOld) $("#reachDate").val(finalCellVal);
                    				}
                    				getBookMinReachDate(2);
                    			}
                    			inputListener();
                    		}
                    		//离店日期
                    		if(fgCol.binding == "leaveDate" && !isNull(finalCellVal)){
                    			if(!Utilities.RegValidate.isSimpleDate(finalCellVal)){
                    				altWaringMsg('离店日期不合法');
                    				return false;
                    			}else{
                    				var oldleaveDate=$("#leaveDate").val();
                    				var viewLen=editBookRoomFlexGrid.itemsSource.items.length;
                    				if(oldleaveDate=="" || (viewLen<=1)){
                    					$("#leaveDate").val(finalCellVal);
                    				}else{
                    					var numNew=Utilities.utils.simpleDate2Number(finalCellVal);
                    					var numOld=Utilities.utils.simpleDate2Number(oldleaveDate);
                    					if(numNew>numOld) $("#leaveDate").val(finalCellVal);
                    				}
                    				getBookMaxLeaveDate(2);
                    			}
                    			inputListener();
                    		}
                    		//抵店时间
                    		if(fgCol.binding == "reachTime" && !isNull(finalCellVal)){
                    			if(!Utilities.RegValidate.isSimpleTime(finalCellVal)){
                    				altWaringMsg('抵店时间不合法');
                    				editBookRoomFlexGrid.setCellData(fgRow,fgCol,'00:00');
                    				return false;
                    			}
                    			inputListener();
                    		}
                    		//房类
                    		if(fgCol.binding == "roomtypeId" && !isNull(finalCellVal)){
                    			if(finalCellVal.indexOf(":")!=-1){
                    				var roomTypeCode=finalCellVal.split(":")[0];
                    				if(roomTypeList!=undefined){
                    					for(var i=0;i<roomTypeList.length;i++){
                    						if($.trim(roomTypeCode)==$.trim(roomTypeList[i].codeId)){//roomTypeList[i].price
                    							//var pricec= new Number(Utilities.utils.getDoubleStr(roomTypeList[i].price));
                    							var pricec= Utilities.utils.getDoubleStr(roomTypeList[i].price);
                    							//var price=parseFloat(''+Utilities.utils.getDoubleStr(roomTypeList[i].price));
                    							editBookRoomFlexGrid.setCellData(e.row,e.col+5,pricec);
                    							if(editBookRoomFlexGrid.getCellData(e.row,e.col+6)==null){
                    								editBookRoomFlexGrid.setCellData(e.row,e.col+6,0);
                    							}
                    							if(editBookRoomFlexGrid.getCellData(e.row,e.col+7)==null){
                    								editBookRoomFlexGrid.setCellData(e.row,e.col+7,0);
                    							}
                    							if(editBookRoomFlexGrid.getCellData(e.row,e.col+1)==null){
                    								editBookRoomFlexGrid.setCellData(e.row,e.col+1,1);
                    							}
                    						}
                    					}
                    				}
                    			}
                    		}
                    		if(fgCol.binding == "bookNum" && !isNull(finalCellVal)){
                    			if(finalCellVal != "" && parseInt(finalCellVal) <= 0){//
                    				altWaringMsg('订房数不能小于1 ');
                    				editBookRoomFlexGrid.setCellData(e.row, e.col,1);
                    			}
                    			countLiuFangInfo();
                    			inputListener();
                    		}
                    		if(fgCol.binding == "roomtypeId" && !isNull(finalCellVal)){
                    			countLiuFangInfo();
                    		}
                    		//校验通过后
                        	inputListener();
                        });
                        editBookRoomFlexGrid.beginningEdit.addHandler(function(s, e){
                        	//检查数据正确性
                        	var fgRow = s.rows[e.row] ;
                    		var fgCol = s.columns[e.col] ;
                    		var finalCellVal = s.getCellData(e.row, e.col, true);
                    		var indexVal = fgRow.dataItem.index;
                    		var dataItem = fgRow.dataItem;
                    		//订房数
                    		if(fgCol.binding == "bookNum" && dataItem.saveNum>0){
                    			e.cancel=true;
                    		}
                    		//抵店日期
                    		if(fgCol.binding == "reachDate" && dataItem.saveNum>0){
                    			e.cancel=true;
                    		}
                    		//离店日期
                    		if(fgCol.binding == "leaveDate" && dataItem.saveNum>0){
                    			e.cancel=true;
                    		}
                    		//抵店时间
                    		if(fgCol.binding == "reachTime" && dataItem.saveNum>0){
                    			e.cancel=true;
                    		}
                    		//房类
                    		if(fgCol.binding == "roomtypeId" && dataItem.saveNum>0){
                    			e.cancel=true;
                    		}
                    		
                        });
                        //统计信息
                        countLiuFangInfo();
                        editBookRoomFlexGrid.itemFormatter = function(panel, r, c, cell) {
                    		if (panel.cellType == wijmo.grid.CellType.Cell) {
                    			var col = panel.columns[c];
                    			if (col.binding == 'buttons'){
                    				cell.style.color = "blue" ;
                    				cell.style.textDecoration = "underline" ;
                    			}
                    		}
                    	}; 
                        var host = editBookRoomFlexGrid.hostElement;
                        //handle the click event
                        host.addEventListener('click', function (e) {
                            var ht = editBookRoomFlexGrid.hitTest(e);
                            if(ht && ht.panel && ht.panel.rows){
                                var row = ht.panel.rows[ht.range.row];
                                var col = ht.panel.columns[ht.range.col];
                                if(row.isReadOnly == true){}else{
                                	var dataItem = row.dataItem ;
                                    //check if cell is clicked
                                    if (dataItem && dataItem.num && ht.cellType == wijmo.grid.CellType.Cell && col.binding=='buttons') {
                                    	// 抵达数大于0 或者 状态不等于 0(0正常1取消2全部抵达)  return 
                                    	if((dataItem.reachNum > 0) || (dataItem.status != 0 ))
                                    		return ;
                                    	editRow(ht.range.row) ;
                                	}
                                }
                            }
                       	});
                        /*editBookRoomFlexGrid.itemFormatter = function(panel, r, c, cell) {
                            if (panel.cellType == wijmo.grid.CellType.Cell) {
                                var col = panel.columns[c];
                                var html = "";
                                if (r == editIndex) {
                                	
                                } else {
                                    switch (col.name) {
                                    case 'buttons':
                                    	cell.style.padding = '3px';
                                    	var cellData=editBookRoomFlexGrid.getCellData(r,11);//抵达数
                                    	var ddd=editBookRoomFlexGrid.getCellData(r,4);
                                    	if(typeof(editBookRoomFlexGrid.getCellData(r,1))!="undefined" && editBookRoomFlexGrid.getCellData(r,1)!=null && cellData==0){
                                    		var xs=editBookRoomFlexGrid.getCellData(r,1);
                                    		cell.style.padding = '3px';
                                            html = '<div class="remainDiv" style="text-align: center;float:left;display:inline;margin-left: auto;margin-right: auto;width: 30px">' + 
                                            '&nbsp;&nbsp;' + 
                                            '<a href="javascript:void(0)"  class="btn btn-default btn-sm ttrr remainA" onclick="editRow(' + r + ')">' + 
                                            '<span class="glyphicon glyphicon-pencil remainSpan" style="margin-right:-3px"></span> 留房' + 
                                            '</a>' + 
                                            '</div>';
                                            cell.innerHTML=html;
                                    	}else if(cellData<=0){
                                    		cell.style.padding = '3px';
                                            html = '<div style="text-align: center;float:left;display:inline;margin-left: auto;margin-right: auto;width: 30px">' + 
                                            '&nbsp;&nbsp;' + 
                                            '<a href="javascript:void(0)" class="btn btn-default btn-sm ttrr" style="display:none" onclick="editRow(' + r + ')">' + 
                                            '<span class="glyphicon glyphicon-pencil" style="margin-right:-3px;"></span> 留房' + 
                                            '</a>' + 
                                            '</div>';
                                            cell.innerHTML=html;
                                    	}
                                        break;
                                    case 'saveNum':
                                        var val = panel.getCellData(r, c);
                                        if (val == null  || val == "") {
                                            //panel.setCellData(r, c, '0', false, false);
                                        }
                                        break;
                                    case 'reachNum':
                                        var val = panel.getCellData(r, c);
                                        if (val == null  || val == "") {
                                            //panel.setCellData(r, c, '0', false, false);
                                        }
                                        break;
                                    }
                                }
                            }
                        };*/
                        editBookRoomView.trackChanges = true;
                        var col = editBookRoomFlexGrid.columns.getColumn('roomtypeId');
                        col.dataMap = new wijmo.grid.DataMap(msg.attributes.roomTypeList,'codeId','codeNamec');
                        
                        createEditor(editBookRoomFlexGrid.columns.getColumn('reachDate'));
                        createEditor(editBookRoomFlexGrid.columns.getColumn('leaveDate'));
                    }
                }
            }
        });
    
    } else {
        altWaringMsg("加载默认列表");
    
    }

}

function isEnabled(){
	 if(orgBookStat=='C' || orgBookStat == 'N' || orgBookStat=='A' || orgBookStat == 'P'){
     	return false;
     }else{
     	return true;
     }
}

function createEditor(editColumn) {
    var grid = editColumn.grid;

    grid.formatItem.addHandler(function (s, e) {
        var editRange = grid.editRange,
            column = e.panel.columns[e.col];
        // check whether this is an editing cell of the wanted column
        if (!(e.panel.cellType === wijmo.grid.CellType.Cell && column === editColumn && editRange && editRange.row === e.row && editRange.col === e.col)) {
            return;
        }

        // hide standard editor (don't remove!)
        if (e.cell.firstChild) {
            e.cell.firstChild.style.display = 'none';
        }

        // add custom editor
        /*var editorRoot = document.createElement('div');
        var input;
        if (column.dataType === wijmo.DataType.Date) {
            input = new wijmo.input.InputDate(editorRoot);
        } else if (column.binding == "状态") {
            // as an ICollectionView
            var countries = ['已修改','未修改'];
            var combobox = new wijmo.input.ComboBox(editorRoot, {
                itemsSource: countries,
            });
            input = combobox;
        }
        else {
            input = new wijmo.input.InputNumber(editorRoot);
            input.step = 1;
            input.format = editColumn.format;
        }
        e.cell.appendChild(editorRoot);
        input.value = grid.getCellData(e.row, e.col, false);*/

		var editorRoot = "<input type=\"text\" id=\"holidayDate_"+e.row+"\"class=\" wj-grid-editor wj-form-control\" style=\"text-align: center;\">";
		e.cell.innerHTML = editorRoot;
		var hotelDateStr=$("#hotelDateStr").val();
		var hotelDateNum=Utilities.utils.simpleDate2Number($("#hotelDateStr").val());

		var ttrr = $("#holidayDate_"+e.row).inputmask("yyyy-mm-dd",{ "oncomplete": function(){
			var thisValueNum=Utilities.utils.simpleDate2Number(this.value);
			if(hotelDateNum>thisValueNum){
				 altWaringMsg("日期不能小于酒店日期,已自动更改为酒店时间");
				this.value=hotelDateStr;
			}
			
		} });
		$("#holidayDate_"+e.row).val(grid.getCellData(e.row, e.col, true));

        // cellEditEnding that updates cell with user's input
        var editEndingEH = function (s, args) {
            grid.cellEditEnding.removeHandler(editEndingEH);
            if (!args.cancel) {
                args.cancel = true;
                /*if (args.col == 4) {
                    cellValue = input.selectedItem;
                    grid.setCellData(e.row, e.col, cellValue);
                }
                else {*/
                    grid.setCellData(e.row, e.col, $("#holidayDate_"+e.row).val());
                //}
            }
        };

        // subscribe the handler to the cellEditEnding event
        grid.cellEditEnding.addHandler(editEndingEH);
    });
}
var liuFangRowNum=undefined;
function editRow(row) {
	  if(!isEnabled()){
      	return false;
      }else{
      	
      }
	liuFangRowNum=row;
	var bookRoomId = editBookRoomFlexGrid.getCellData(row, 1);
	currBookRoomId = bookRoomId;
    var roomTypeId = editBookRoomFlexGrid.getCellData(row, 4);
    var bookNum = editBookRoomFlexGrid.getCellData(row, 5);
    var reachDate = editBookRoomFlexGrid.getCellData(row, 6);
    var leaveDate = editBookRoomFlexGrid.getCellData(row, 7);
    var col = editBookRoomFlexGrid.columns.getColumn('roomtypeId');
    var roomTypeName = col.dataMap.getDisplayValue(roomTypeId);
    if (roomTypeId == "" || roomTypeId == null ) {
        altWaringMsg("请选择房类");
        return;
    }
    if (bookNum == "" || bookNum == null ) {
        altWaringMsg("请输入定房数");
        return;
    }
    if (reachDate == "" || reachDate == null ) {
        altWaringMsg("请选择抵店日期");
        return;
    }
    
    if (leaveDate == "" || leaveDate == null ) {
        altWaringMsg("请选择离店日期");
        return;
    }
    var checkId = $("#checkId").val();
    remainRoom(bookRoomId,roomTypeId, roomTypeName, checkId, reachDate, leaveDate);
}

//---------------------------------------------留房弹出框----------------------------------------------------
function roomFun(){
	$("#remainRoomUL .statusBlock").unbind("click");
	$("#remainRoomUL .statusBlock").click(function(){
		
	});
}
/**
 * 留房连接弹出层
 */
var count = 0;
var choicedCount=0;
var allSeledRoomIdMap=new Map();
var allSeledRoomIdArray=[];
function remainRoom(bookRoomId,roomTypeId, roomTypeName, checkId, reachDate, leaveDate) {
	var storeBookRoom = new Array();
	roomIdArray = new Array();
	allSeledRoomIdArray.length=0;
	//
/*	var allItems = editBookRoomFlexGrid.itemsSource.items;
	if(allItems && allItems.length > 0){
		for(var i=0 ; i < allItems.length ; i++){
			var checkId = allItems[i].checkId;
			var bookRoomId2 = allItems[i].bookRoomId;
			//if(bookRoomId==undefined)continue;
			if(bookRoomId==bookRoomId2)continue;
			var roomNums=allItems[i].roomNums;
			var tmpIdArray = new Array();
			if(roomNums instanceof Array){
				for(var i=0;i<roomNums.length;i++){
					tmpIdArray.push(roomNums[i].roomId)
					allSeledRoomIdArray.push(roomNums[i].roomId);
				}
			}
			allSeledRoomIdMap.put(bookRoomId2,tmpIdArray);
		}
	}*/
	
	$("input[name='bookId']").val(bookRoomId);
	reachDate = formatterDate(reachDate,"yyyy-MM-dd");
	leaveDate = formatterDate(leaveDate,"yyyy-MM-dd");
    $("input[name='remainCheckId']").val($("#checkId").val());
    //临时设置
    //roomTypeName="标准间";
    $("input[name='remainRoomTypeId']").val(roomTypeId); 
    count = 0;
    choicedCount=0;
    var isEdit = $("#isEdit").val();
    //var roomTypeId = "";
    if (typeof remainRoomFlexGrid != "undefined") {
        remainRoomFlexGrid.dispose();
        remainRoomFlexGrid = undefined;
        remainRoomView = undefined;
    }
    $.ajax({
        type: "POST",
        url: "/bookRoom/saleList.do",
        data: {
        	bookRoomId:bookRoomId,
            roomTypeId: roomTypeId,
            checkId: checkId,
            reachDate: reachDate,
            leaveDate: leaveDate
        },
        dataType: "json",
        success: function(msg) {
            if (msg.success) {
                var savedRooms = msg.attributes.savedRooms;
                var bkArray = map.get(bookRoomId);
                if(bkArray==null || bkArray==undefined){
            	  if (savedRooms != null  && savedRooms != "") {
            		  bkArray=[];
                      for (var k = 0; k < savedRooms.length; k++) {
                    	  var nowRoomId = $.trim(savedRooms[k].roomId);
                    	  bkArray.push(nowRoomId);
                    	  storeBookRoom.push(nowRoomId);
                      }
                  }
                }
                //初始化留房操作FlexGrid
                remainRoomFlexGrid = new wijmo.grid.FlexGrid('#remainRoomFlexGrid');
                var currItem = undefined;
                if (isEdit == "true") {
                    remainRoomView = editBookRoomView;
                } else {
                    remainRoomView = addBookRoomView;
                }
                currItem = remainRoomView.currentItem;
                var roomNums=currItem.roomNums;
                //清空房块
                $("#remainRoomUL").empty();
                $("#remainRoomNum").empty();
                //新方式展示房态
                var ftHtml="";

                var roomIdStr=allSeledRoomIdArray.toString();
                for(var i = 0; i < msg.extra.length; i++){
                	ftHtml+='<li class="statusLi2 floor" floorNo="'+msg.extra[i].floorNo+'"><img class="floorImg" width="45" src="../img/Floor_01.png"><span class="floorWord2">'+msg.extra[i].floorNo+'</span> </li>';
                	for(var j=0;j<msg.extra[i].rooms.length;j++){
                		var nowRoomId=$.trim(msg.extra[i].rooms[j].roomId);
                		/*if(allSeledRoomIdArray.indexOf(nowRoomId)!=-1){
                			continue;
                		}*/
                		ftHtml+='<li class="statusLi2 idle floor_'+msg.extra[i].floorNo+'" roomid="'+$.trim(msg.extra[i].rooms[j].roomId)+'">'
                			ftHtml +='<div class="statusBlock" roomid="'+msg.extra[i].rooms[j].roomId+'">'
                				ftHtml +='<h5>'+msg.extra[i].rooms[j].roomId+'</h5>'
                				ftHtml +='<h6>'+msg.extra[i].rooms[j].roomTypeName+'</h6>'
                				ftHtml +='<ul class="userStatusSmall">'
            						//ftHtml +='<li><img src="../img/st-02.png"></li>'
            					ftHtml +='</ul>'
            			    ftHtml  +='</div>'
                				//--绑定处理开始
                		            
	                                //回显之前选中过的房间
	                                if (savedRooms != null  && savedRooms != "") {
	                                    for (var k = 0; k < savedRooms.length; k++) {
	                                        if (nowRoomId == $.trim(savedRooms[k].roomId)) {
	                                        	roomIdArray.push(nowRoomId);
	                                            choiced = true;
	                                            choicedCount++;
	                                            break;
	                                        }
	                                    }
	                                }
	                                //--
            			    	    var choiced = false;
	                                var keepChoice = false;
	                                //回显当前用户之前选中过的房间.
	                                if(typeof bkArray!="undefined" && bkArray.length>0){
	                                	for(var m=0;m<bkArray.length;m++){
	                                		if(nowRoomId == bkArray[m]){
	                                			keepChoice = true;
	                                			//choicedCount++;
	                                			break;
	                                		}
	                                	}
	                                }
	                                //---
	                                if (choiced || keepChoice){//当前房间被选择
	                                	ftHtml+='<div class="pointStatus" id="pointStatus'+$.trim(msg.extra[i].rooms[j].roomId)+'" roomid="'+msg.extra[i].rooms[j].roomId+'" style="display:block"></div>';
		                            	ftHtml+='<img class="pointStatusRight" id="pointStatusRight'+$.trim(msg.extra[i].rooms[j].roomId)+'" roomid="'+msg.extra[i].rooms[j].roomId+'" style="display:block" src="../img/point-right.png">';
		                            	//
		                            	 var html = "<li id='" + nowRoomId + "' ondblclick='repeal(" + nowRoomId + ")'>" + nowRoomId + "</li>";
		                                 $("#remainRoomNum").append(html);
	                                }else{//未被选择
	                                	ftHtml+='<div class="pointStatus" id="pointStatus'+$.trim(msg.extra[i].rooms[j].roomId)+'" roomid="'+msg.extra[i].rooms[j].roomId+'"></div>';
	                            		ftHtml+='<img class="pointStatusRight" id="pointStatusRight'+$.trim(msg.extra[i].rooms[j].roomId)+'" roomid="'+msg.extra[i].rooms[j].roomId+'" src="../img/point-right.png">';
	                                }
                				//--end
                	   ftHtml +='</li>';
                	}
                }
                /*
                for (var i = 0; i < msg.obj.length; i++) {
                	//不显示非当前预定信息预定过的房间和之前已经选中的房间
                	var keys = map.keys();
                	if(typeof keys !="undefined"){
                		for(var j=0;j<keys.length;j++){
                			if(keys[j]!=bookRoomId){
                				var array = map.get(keys[j]);
                				if(typeof array !="undefined"){
                					
                					continue test1;
                					for(var j=0;j<array.length;j++){
                						if($.trim(msg.obj[i].roomId) == array[j]){
                							continue test1;
                						}
                					}
                				}
                			}
                		}
                	}
                	
                	
                    var html = "<li class='statusLi2 idle'>";
                    html += "<div class='statusBlock'>";
                    html += "<h5>" + msg.obj[i].roomId + "</h5>";
                    html += "<h6>" + msg.obj[i].hroomType + "</h6>";
                    
                    var choiced = false;
                    //回显之前选中过的房间
                    if (savedRooms != null  && savedRooms != "") {
                        for (var j = 0; j < savedRooms.length; j++) {
                            if (msg.obj[i].roomId == savedRooms[j].roomId) {
                            	roomIdArray.push($.trim(savedRooms[j].roomId));
                                choiced = true;
                                choicedCount++;
                                break;
                            }
                        }
                    }
                    var keepChoice = false;
                    //回显当前用户之前选中过的房间.
                    var bkArray = map.get(bookRoomId);
                    if(typeof bkArray!="undefined" && bkArray.length>0){
                    	for(var j=0;j<bkArray.length;j++){
                    		if($.trim(msg.obj[i].roomId) == bkArray[j]){
                    			keepChoice = true;
                    			choicedCount++;
                    			break;
                    		}
                    	}
                    }
                    
                    if (choiced || keepChoice) {
                    	html +="<ul class='userStatusSmall'>";
                    	html +="<li><img src='../img/st-02.png'></li>";
                    	html +="</ul>";
                    	html += "</div>";
                        html += "<div id='pointStatus" + $.trim(msg.obj[i].roomId) + "' class='pointStatus' style='display:block'></div>";
                        html += "<img id='pointStatusRight" + $.trim(msg.obj[i].roomId) + "' class='pointStatusRight' style='display:block' src='../img/point-right.png'>";
                        
                        var htmlChoiceBlock = "<li id='" + $.trim(msg.obj[i].roomId) + "' ondblclick='repeal(" + $.trim(msg.obj[i].roomId) + ")'>" + msg.obj[i].roomId + "</li>";
                        $("#remainRoomNum").append(htmlChoiceBlock);
                        choiced = false;
                    } else {
                    	html += "</div>";
                        html += "<div id='pointStatus" + $.trim(msg.obj[i].roomId) + "' class='pointStatus'></div>";
                        html += "<img id='pointStatusRight" + $.trim(msg.obj[i].roomId) + "' class='pointStatusRight' src='../img/point-right.png'>";
                    }
                    
                    html += "</li>";
                    //$("#remainRoomUL").append(html);
                   
                }
                */
                //设置房态图
                $("#remainRoomUL").html(ftHtml);
                
                $("#brCount").text("留房:"+choicedCount);
                $(".leaveTheRoom").fadeIn();
                
               
                remainRoomFlexGrid.initialize({
                    isReadOnly: true,
                    autoGenerateColumns: false,
                    isReadOnly: true,
                    selectionMode: wijmo.grid.SelectionMode.Row,
                    allowDragging:wijmo.grid.AllowDragging.None,
                    headersVisibility:wijmo.grid.HeadersVisibility.Column,
                    //allowResizing: wijmo.grid.AllowResizing.None,
                    columns: [
					{
					    header: '序号',
					    binding: "num",
					    width: 50,
					    isReadOnly: true,
					    visible: true,
					    isReadOnly:false,
						cssClass:'fg_column_readOnly',
						align:'center'
					},            
                    {
                        header: '主键',
                        binding: "id",
                        width: 50,
                        isReadOnly: true,
                        visible: false
                    }, 
                    /*{ header: '序号', width:50,isReadOnly:true}, */
                    {
                        header: '房类',
                        binding: "roomtypeId",
                        width: 109
                    }, 
                    {
                        header: '订房数',
                        binding: "bookNum",
                        width: '*',
                        align:'center'
                    }, 
                    {
                        header: '留房数',
                        binding: "saveNum",
                        width: '*',
                        align:'center'
                    }, 
                    {
                        header: '抵店日期',
                        binding: "reachDate",
                        format: 'yyyy-MM-dd',
                        width: 110,
                        align:'center'
                    }, 
                    {
                        header: '离店日期',
                        binding: "leaveDate",
                        format: 'yyyy-MM-dd',
                        width: 110,
                        align:'center'
                    }, 
                    ],
                    itemsSource: remainRoomView,
                });
                //定位光标
                remainRoomView.currentItem = currItem;
                
                remainRoomView.currentItem.saveNum = savedRooms.length;
                remainRoomFlexGrid.refresh();
                
                remainRoomFlexGrid.selectionChanging = function(e) {
                    return false;
                };
                var col = remainRoomFlexGrid.columns.getColumn('roomtypeId');
                col.dataMap = new wijmo.grid.DataMap(msg.attributes.roomTypeList,'codeId','codeNamec');
                
                
                /**
		    	  * 设定查询条件start
		    	  * **/
                $("input[name='remainRoomType']").val(roomTypeName);
                $("input[name='remainReachDate']").val(formatterDate(currItem.reachDate,"yyyy-MM-dd"));
                $("input[name='remainLeaveDate']").val(formatterDate(currItem.leaveDate,"yyyy-MM-dd"));
                
                $("#remainBuildingName").find("option[value!='0']").remove();
                $("#remainFloorNo").find("option[value!='']").remove();
                for (var i = 0; i < msg.attributes.listBuilding.length; i++) {
                    $("<option value='" + msg.attributes.listBuilding[i].codeId + "'>" + msg.attributes.listBuilding[i].codeNamec + "</option>").appendTo("#remainBuildingName");
                }
                
               /* for (var i = 0; i < msg.attributes.listFloorNo.length; i++) {
                    $("<option value='" + msg.attributes.listFloorNo[i] + "'>第" + msg.attributes.listFloorNo[i] + "层</option>").appendTo("#remainFloorNo");
                }*/
                /**
		    	  * 设定查询条件end
		    	  **/
                
                //
                //计数，计算用户总共选择了多少间房子
                
                //$("#remainRoomNum").empty();
                //楼层点击事件
                $(".floor").click(function(){
                	//---
                	var floorNo = $(this).attr("floorNo") ;
                	 var currBookNum = currItem.bookNum;
                	 var floorRooms=$(this).nextAll(".floor_"+floorNo).length;
                	if(floorRooms>currBookNum){
                		altWaringMsg("楼层房间数大于订房数");
                		return false;
                	}
                	$(this).nextAll(".floor_"+floorNo).each(function(){
                		var nowRoomId=$(this).attr("roomid");
                		var ifExits=false;
                		 if (savedRooms != null  && savedRooms != "") {
                             for (var k = 0; k < savedRooms.length; k++) {
                                 if (nowRoomId == $.trim(savedRooms[k].roomId)) {
                                	 ifExits=true;
                                     break;
                                 }
                             }
                         }
                		var roomJobj=$(this).children(".statusBlock").eq(0);
                		$(this).children(".pointStatus").each(function(){
                			if($(this).is(":hidden")){
                				//if(!ifExits){
                					 $(this).show();
                					 $(this).next().show();
                					 var html = "<li id='" + nowRoomId + "' ondblclick='repeal(" + nowRoomId + ")'>" + nowRoomId + "</li>";
                                     $("#remainRoomNum").append(html);
                					 //
                                     count++;
                                     choicedCount = parseInt(choicedCount)+1;
                                     $("#brCount").text("留房:"+choicedCount);
                                     roomIdArray.push(nowRoomId);
                                     storeBookRoom.push(nowRoomId);
                				//}
                			}else{
                				//if(!ifExits){
                					$(this).hide();
                					$(this).next().hide();
                					$("#" + nowRoomId).remove();
                				//}
                					//
                					removeByValue(roomIdArray,nowRoomId);
                                	removeByValue(storeBookRoom,nowRoomId);
                                    count--;
                                    choicedCount = parseInt(choicedCount)-1;
                                    $("#brCount").text("留房:"+choicedCount);
                			}
                		});
                		
                	});

                	//----
                });
                //房间点击处理-增加或解除绑定
                $(".statusBlock").click(function() {
                	var curroomId=$.trim($(this).attr('roomid'));
                    //当前项的定房数
                    var currBookNum = currItem.bookNum;
                    var currSaveNum = currItem.saveNum;
                    //选定房间数
                    var selectedBookNum = $("#remainRoomNum li").length;
                    if (selectedBookNum >= currBookNum) {
                        altWaringMsg("选房数不能超过定房数");
                        return;
                    }
                    //判断当前点击房号是否已经被选择
                    var isSeled=false;
                    $("#remainRoomNum li").each(function(){
                    	var fid=$.trim($(this).attr('id'));
                    	if(curroomId==fid){//已选择
                    		isSeled=true;
                    	}
                    });
                    if(!isSeled){//没有选择
                    	 count++;
                         choicedCount = parseInt(choicedCount)+1;
                         $("#brCount").text("留房:"+choicedCount);
                         roomIdArray.push(curroomId);
                         storeBookRoom.push(curroomId);
                         //选定房间，修改留房数
                         //remainRoomView.currentItem.saveNum = parseInt(currSaveNum) + 1;
                         //remainRoomFlexGrid.refresh();
                         $(this).nextAll(".pointStatus").eq(0).show();
                         $(this).nextAll(".pointStatusRight").eq(0).show();
                         //add bussiness.
                         var html = "<li id='" + curroomId + "' ondblclick='repeal(" + curroomId + ")'>" + curroomId + "</li>";
                         $("#remainRoomNum").append(html);
                    }else{//已选择 取消
                    	
                    }
                }
                );
                $(".pointStatusRight").click(function() {
                	removeByValue(roomIdArray,$.trim($(this).attr("roomid")));
                	removeByValue(storeBookRoom,$.trim($(this).attr("roomid")));
                    count--;
                    choicedCount = parseInt(choicedCount)-1;
                    $("#brCount").text("留房:"+choicedCount);
                    //移除当前选中的房间号
                    $("#" + $.trim($(this).attr("roomid"))).remove();
                    //remainRoomView.currentItem.saveNum = parseInt(currItem.saveNum) - 1;
                    //remainRoomFlexGrid.refresh();
                    $(this).hide();
                    $(this).prev().hide();
                }
                );
                
                $(".pointStatus").click(function() {
                	removeByValue(roomIdArray,$.trim($(this).attr("roomid")));
                	removeByValue(storeBookRoom,$.trim($(this).attr("roomid")));
                    count--;
                    choicedCount = parseInt(choicedCount)-1;
                    $("#brCount").text("留房:"+choicedCount);
                    //移除当前选中的房间号
                    $("#" + $.trim($(this).prev().find("h5").html())).remove();
                    //remainRoomView.currentItem.saveNum = parseInt(currItem.saveNum) - 1;
                    //remainRoomFlexGrid.refresh();
                    $(this).hide();
                    $(this).next().hide();
                }
                );
                
                //关闭按钮和退出按钮，FlexGrid的房间留房数还原
                $(".closeDiv2").click(function() {
                	/*if(currItem.saveNum!="" && currItem.saveNum!="0"){
                		remainRoomView.currentItem.saveNum = currItem.saveNum - count;
                	}
                	//初始化留房FlexGrid的信息
                	if (typeof(editBookRoomFlexGrid) != "undefined") {
                		editBookRoomView.currentItem.saveNum = choicedCount;
                		editBookRoomFlexGrid.refresh();
                    }
                    if (typeof(addBookRoomFlexGrid) != "undefined") {
                    	addBookRoomView.currentItem.saveNum = choicedCount;
                    	addBookRoomFlexGrid.refresh();
                    }
                	
                    remainRoomFlexGrid.refresh();*/
                    $(".leaveTheRoom").fadeOut();
                    $(".alertDivBg2").fadeOut();
                    //
                    $(".alertDivBg3").fadeOut();
					$("#selectRoomsAlert").fadeOut();
                }
                );
                
                /**
            	 * 留房操作退出
            	 */
                $("#remianQuit").click(function() {
                	//初始化留房FlexGrid的信息
                	/*if (typeof(editBookRoomFlexGrid) != "undefined") {
                		editBookRoomView.currentItem.saveNum = choicedCount;
                		editBookRoomFlexGrid.refresh();
                    }
                    if (typeof(addBookRoomFlexGrid) != "undefined") {
                    	addBookRoomView.currentItem.saveNum = choicedCount;
                    	addBookRoomFlexGrid.refresh();
                    }
                    $(".leaveTheRoom").css({
                        "display": "none"
                    });*/
                	  //关闭当前窗口
	                    $(".leaveTheRoom").fadeOut();
	                    $(".alertDivBg2").fadeOut();
                }
                );
                $("#remainRoomOK").unbind('click');
                //留房弹出框确定按钮 
                $("#remainRoomOK").click(function() {
                	//将留房数据设置到留房列表
                	var fRoomNums=[];
                	$("#remainRoomNum li").each(function(){
                      	var fid=$.trim($(this).attr('id'));
                      	storeBookRoom.push(fid);
                      	roomIdArray.push(fid);
                      	//
                      	var roomNumObj={};
						roomNumObj.bookId=bookRoomId;
						roomNumObj.checkId=selCheckId;
						roomNumObj.roomId=fid;
						fRoomNums.push(roomNumObj);
						
                    });
                	var items = remainRoomView.items;
                	for(var i=0 ; i < items.length ; i++){
                		var saveNum = items[i].saveNum;
    					var roomNums=items[i].roomNums;
    					var odlBookId=items[i].bookRoomId;
    					if(bookRoomId==odlBookId){
    						if(items[i].roomNums==null){
    							items[i].roomNums=[];
    						}
    						items[i].roomNums.length=0;
    						for(var j=0;j<storeBookRoom.length;j++){
								var roomNumObj={};
    							roomNumObj.bookId=bookRoomId;
    							roomNumObj.roomId=storeBookRoom[j];
    							items[i].roomNums.push(roomNumObj);
							}
    					}
                	}
                	var fCurrItem=undefined;
                	var fCurrItem=currItem;
                	if(fCurrItem.roomNums instanceof Array){
                		fCurrItem.roomNums.length=0;
                	}
                	fCurrItem.roomNums=fRoomNums;
                	//留房数据入库
                	$.ajax({
     				   type: "post",
     				   url: "/bookroom/submitBookRoomNum.do",
     				   data: {'checkId':selCheckId,'bookId':bookRoomId,'bookRoomJson':JSON.stringify(fCurrItem)},
     				   dataType:'json',
     				   success: function(aj){
     				   	 if(aj.success){
     				   		//$("#formTitle").text(baseFormTitle+"-维护");
     				   		//showForm();
     						//formObj=aj.obj;
     						//loadFormData(aj.obj);
     				   		storeBookRoom.length=0;
     	                	roomIdArray.length=0;
     	                	
     	                	allSeledRoomIdMap.put(bookRoomId,storeBookRoom);
     	                	
     	                	map.put(bookRoomId,storeBookRoom);
     	                    if (isEdit == "true") {
     	                        /*editBookRoomView = remainRoomView;
     	                        if (typeof editBookRoomFlexGrid != "undefined") {
     	                            editBookRoomFlexGrid.refresh();
     	                        }*/
     	                       goView(checkId);
     	                      refreshGrid('2');
     	                    } else {
     	                        addBookRoomView = remainRoomView;
     	                        if (typeof addBookRoomFlexGrid != "undefined") {
     	                            addBookRoomFlexGrid.refresh();
     	                        }
     	                    }
     	                    //关闭当前窗口
     	                    $(".leaveTheRoom").fadeOut();
     	                    $(".alertDivBg2").fadeOut();
     				   	 }else{
     				   		alert(aj.msg); 
     				   	 }
     				   }
     			    });
                	//--
                }
                );
            }
        }
    });
}

function getFloors(buildId){
	if(buildId==null || buildId==undefined){
		//清空
	}else{
		$.ajax({
			   type: "POST",
			   url: path+"/rooms/getfloors/"+buildId,
			   data: {},
			   dataType:'json',
			   success: function(data){
				  $("#remainFloorNo").html(); 
				  var dhtml='<option value="">全部</option>';
				  for(var i=0;i<data.length;i++){
					  dhtml+='<option value="'+data[i]+'">'+data[i]+'</option>';
				  }
				  $("#remainFloorNo").html(dhtml);
			   }
			});
	}
	
}


/**
 * 双击撤销选中的房间
 * @param roomNum
 */
function repeal(roomNum) {
    //取消选中的房子的遮罩层
    $("#pointStatus" + $.trim(roomNum)).css("display", "none");
    $("#pointStatusRight" + $.trim(roomNum)).css("display", "none");
    $("#" + roomNum).remove();
    removeByValue(roomIdArray,$.trim(roomNum));
    
    var brcount = $("#brCount").text();
    var countValue = brcount.replace("留房:","");
    countValue = countValue-1;
    $("#brCount").text("留房:"+countValue);
    count--;
    choicedCount--;
    //取消选定房间区域的房间号码
   /* if (typeof remainRoomFlexGrid != "undefined") {
        remainRoomView.currentItem.saveNum = remainRoomView.currentItem.saveNum - 1;
        remainRoomFlexGrid.refresh();
        count--;
    }*/

}

/**
 * 留房操作 页面的    查询按钮
 */
function remainRoomQuery(isAutoSetRoom) {
    //var checkId = $("input[name='remainCheckId']").val();
	var storeBookRoom = new Array();
	roomIdArray = new Array();
    $.ajax({
        type: "POST",
        url: "/bookRoom/queryRemainRoom.do",
        data: $("#remainConditionForm").serialize(),
        success: function(msg) {
        	 if (msg.success) {
                 $("#remainRoomUL").empty();
                 $("#remainRoomNum").empty();
                 var ftHtml="";
                 var savedRooms = msg.attributes.savedRooms;
                 var choicedCount=0;
                ///-----------------回填房态图
                 for(var i = 0; i < msg.extra.length; i++){
                 	ftHtml+='<li class="statusLi2 floor" floorNo="'+msg.extra[i].floorNo+'"><img class="floorImg" width="45" src="../img/Floor_01.png"><span class="floorWord2">'+msg.extra[i].floorNo+'</span> </li>';
                 	for(var j=0;j<msg.extra[i].rooms.length;j++){
                 		var nowRoomId=$.trim(msg.extra[i].rooms[j].roomId);
                 		/*if(allSeledRoomIdArray.indexOf(nowRoomId)!=-1){
                 			continue;
                 		}*/
                 		ftHtml+='<li class="statusLi2 idle">'
                 			ftHtml +='<div class="statusBlock" roomid="'+msg.extra[i].rooms[j].roomId+'">'
                 				ftHtml +='<h5>'+msg.extra[i].rooms[j].roomId+'</h5>'
                 				ftHtml +='<h6>'+msg.extra[i].rooms[j].roomTypeName+'</h6>'
                 				ftHtml +='<ul class="userStatusSmall">'
             						//ftHtml +='<li><img src="../img/st-02.png"></li>'
             					ftHtml +='</ul>'
             			    ftHtml  +='</div>'
                 				//--绑定处理开始
             			    	 var choiced = false;
 	                                //回显之前选中过的房间
 	                                if (savedRooms != null  && savedRooms != "") {
 	                                    for (var k = 0; k < savedRooms.length; k++) {
 	                                        if (nowRoomId == $.trim(savedRooms[k].roomId)) {
 	                                        	roomIdArray.push(nowRoomId);
 	                                            choiced = true;
 	                                            choicedCount++;
 	                                            break;
 	                                        }
 	                                    }
 	                                }
 	                                //--
 	                                var keepChoice = false;
 	                                //回显当前用户之前选中过的房间.
 	                                if(typeof bkArray!="undefined" && bkArray.length>0){
 	                                	for(var m=0;m<bkArray.length;m++){
 	                                		if(nowRoomId == bkArray[m]){
 	                                			keepChoice = true;
 	                                			choicedCount++;
 	                                			break;
 	                                		}
 	                                	}
 	                                }
 	                                //---
 	                                if (choiced || keepChoice){//当前房间被选择
 	                                	ftHtml+='<div class="pointStatus"  roomid="'+msg.extra[i].rooms[j].roomId+'" style="display:block"></div>';
 		                            	ftHtml+='<img class="pointStatusRight" roomid="'+msg.extra[i].rooms[j].roomId+'" style="display:block" src="../img/point-right.png">';
 		                            	//
 		                            	 var html = "<li id='" + nowRoomId + "' ondblclick='repeal(" + nowRoomId + ")'>" + nowRoomId + "</li>";
 		                                 $("#remainRoomNum").append(html);
 	                                }else{//未被选择
 	                                	ftHtml+='<div class="pointStatus"  roomid="'+msg.extra[i].rooms[j].roomId+'"></div>';
 	                            		ftHtml+='<img class="pointStatusRight" roomid="'+msg.extra[i].rooms[j].roomId+'" src="../img/point-right.png">';
 	                                }
                 				//--end
                 	   ftHtml +='</li>';
                 	}
                 }
                 var currItem = undefined;
                 currItem = remainRoomView.currentItem;
                 var roomNums=currItem.roomNums;
 		 //设置房态图
                 $("#remainRoomUL").html(ftHtml);
                 $("#brCount").text("留房:"+0);
                 $("#brCount").text("留房:"+choicedCount);
                 //$(".leaveTheRoom").fadeIn();
                //-------------------回填房态图结束
                 //重新绑定事件
                 //楼层点击事件
                 $(".floor").click(function(){
                 	//TODO 暂不处理
                 });
               //----------------------2015.12.04自助排房begin--------------------------
                 //判断方法参数isAuto为true则是自动排房，否则为条件查询
                   if(isAutoSetRoom){
                       //选定房间数
                       var selectedBookNum = $("#remainRoomNum li").length;
                       //当前项的定房数
                       var currBookNum = currItem.bookNum;
                       var currSaveNum = currItem.saveNum;
                       
                       if (selectedBookNum >= currBookNum) {
                       }else{
	                       $(".statusBlock").each(function(){
	                        	var curroomId=$.trim($(this).attr('roomid'));
	                        	//判断当前点击房号是否已经被选择
	                        	var isSeled=false;
	                        	$("#remainRoomNum li").each(function(){
	                              	var fid=$.trim($(this).attr('id'));
	                              	if(curroomId==fid){//已选择
	                              		isSeled=true;
	                              	}
	                            });
	                        	if(isSeled){
	                        		 return true;
	                        	}
	                          	var fid=$.trim($(this).attr('id'));
	                          	if(curroomId!=fid){
	                          		//没有选择
	  	                        	 count++;
	  	                             if(choicedCount+1 > currBookNum){
	  	                            	 return false;
	  	                             }
	  	                             choicedCount = parseInt(choicedCount)+1;
	  	                             roomIdArray.push(curroomId);
	  	                             storeBookRoom.push(curroomId);
	  	                             $(this).nextAll(".pointStatus").eq(0).show();
	  	                             $(this).nextAll(".pointStatusRight").eq(0).show();
	  	                             //add bussiness.
	  	                             var html = "<li id='" + curroomId + "' ondblclick='repeal(" + curroomId + ")'>" + curroomId + "</li>";
	  	                             $("#remainRoomNum").append(html);
	                          	}
	                       });
	                       $("#brCount").text("留房:"+choicedCount);
                             //选定房间，修改留房数
                             remainRoomView.currentItem.saveNum = parseInt(choicedCount);
                             remainRoomFlexGrid.refresh();
                       }
                   }
                   //----------------------2015.12.04自助排房begin--------------------------
                 //房间点击处理-增加或解除绑定
                 $(".statusBlock").click(function() {
                 	var curroomId=$.trim($(this).attr('roomid'));
                     //当前项的定房数
                     var currBookNum = currItem.bookNum;
                     var currSaveNum = currItem.saveNum;
                     //选定房间数
                     var selectedBookNum = $("#remainRoomNum li").length;
                     if (selectedBookNum >= currBookNum) {
                         altWaringMsg("选房数不能超过定房数");
                         return;
                     }
                     //判断当前点击房号是否已经被选择
                     var isSeled=false;
                     $("#remainRoomNum li").each(function(){
                     	var fid=$.trim($(this).attr('id'));
                     	if(curroomId==fid){//已选择
                     		isSeled=true;
                     	}
                     });
                     if(!isSeled){//没有选择
                     	 count++;
                          choicedCount = parseInt(choicedCount)+1;
                          $("#brCount").text("留房:"+choicedCount);
                          roomIdArray.push(curroomId);
                          storeBookRoom.push(curroomId);
                          //选定房间，修改留房数
                          remainRoomView.currentItem.saveNum = parseInt(currSaveNum) + 1;
                          remainRoomFlexGrid.refresh();
                          $(this).nextAll(".pointStatus").eq(0).show();
                          $(this).nextAll(".pointStatusRight").eq(0).show();
                          //add bussiness.
                          var html = "<li id='" + curroomId + "' ondblclick='repeal(" + curroomId + ")'>" + curroomId + "</li>";
                          $("#remainRoomNum").append(html);
                     }else{//已选择 取消
                     	
                     }
                 }
                 );
                 $(".pointStatusRight").click(function() {
                 	removeByValue(roomIdArray,$.trim($(this).attr("roomid")));
                 	removeByValue(storeBookRoom,$.trim($(this).attr("roomid")));
                     count--;
                     choicedCount = parseInt(choicedCount)-1;
                     $("#brCount").text("留房:"+choicedCount);
                     //移除当前选中的房间号
                     $("#" + $.trim($(this).attr("roomid"))).remove();
                     remainRoomView.currentItem.saveNum = parseInt(currItem.saveNum) - 1;
                     remainRoomFlexGrid.refresh();
                     $(this).hide();
                     $(this).prev().hide();
                 }
                 );
                 
                 $(".pointStatus").click(function() {
                 	removeByValue(roomIdArray,$.trim($(this).attr("roomid")));
                 	removeByValue(storeBookRoom,$.trim($(this).attr("roomid")));
                     count--;
                     choicedCount = parseInt(choicedCount)-1;
                     $("#brCount").text("留房:"+choicedCount);
                     //移除当前选中的房间号
                     $("#" + $.trim($(this).prev().find("h5").html())).remove();
                     remainRoomView.currentItem.saveNum = parseInt(currItem.saveNum) - 1;
                     remainRoomFlexGrid.refresh();
                     $(this).hide();
                     $(this).next().hide();
                 }
                 );
                 
                 //重新绑定事件结束
             } else {
                 altWaringMsg(msg.msg);
             }
        }
    });
}
/**
 * 计算留房信息
 */
function countLiuFangInfo(){
	var checkIdval = $("#isEdit").val();
	var items;
	 if (checkIdval == "true"){
		  items =  editBookRoomFlexGrid.itemsSource.items;
	 }else{
		  items =  addBookRoomFlexGrid.itemsSource.items;
	 }
	 var totalBookNum=0;
	 var totalSaveNum=0;
	 var totalPrice=0.00;
	 for(var i=0 ; i < items.length ; i++){
		    //订房数
			var bookNum = items[i].bookNum;
			var saveNum = items[i].saveNum;
			var price = items[i].roomPrice;
			if(!isNull(bookNum) && bookNum!=""){
				totalBookNum=totalBookNum+parseInt(bookNum);
			}
			if(!isNull(saveNum) && saveNum!=""){
				totalSaveNum=totalSaveNum+parseInt(saveNum);
			}
			if(!isNull(bookNum) && bookNum!="" && !isNull(price) && price!=""){
				totalPrice=totalPrice+(parseFloat(price)*parseInt(bookNum));
			}
	 }
	 $("#totalBookNum").text(parseInt(totalBookNum));
	 $("#totalSaveNum").text(parseInt(totalSaveNum));
	 $("#totalPrice").text(Utilities.utils.getDoubleStr(totalPrice));
}
/**
 * 清空统计信息
 */
function clearLiuFangInfo(){
	 $("#totalBookNum").text("0");
	 $("#totalSaveNum").text("0");
	 $("#totalPrice").text("0.00");
}

/**
 * 查看留房列表
 * @param ul_id
 */
function showCurrentRoomInfo(ul_id){
	countLiuFangInfo();
	//读取当前留房列表数据
	var roomIdArray= new Array();
	var checkIdval = $("#isEdit").val();
	 if (checkIdval == "true"){
		 var items =  editBookRoomFlexGrid.itemsSource.items;
			if(items && items.length > 0){
				for(var i=0 ; i < items.length ; i++){
					var checkId = items[i].checkId;
					var saveNum = items[i].saveNum;
					var roomNums=items[i].roomNums;
					if(roomNums instanceof Array){
						if(roomNums.length>0){
							for(var j=0;j<roomNums.length;j++){
								if(parseInt(roomNums[j].flag) != 0){
									continue;
								}
								roomIdArray.push(roomNums[j].roomId);
							}							
						}else{
							roomIdArray.push(-1);
						}
					}
				}
			}else{
				roomIdArray.push(-1);
			}
	 }else{
		 var items =  addBookRoomFlexGrid.itemsSource.items;
			if(items && items.length > 0){
				for(var i=0 ; i < items.length ; i++){
					var checkId = items[i].checkId;
					var saveNum = items[i].saveNum;
					if(!isNull(checkId) && !isNull(checkId) && saveNum>0 ){
						roomIdArray.push(checkId);
					}
				}
			}else{
				//altWaringMsg("没有留房数据");
				roomIdArray.push(-1);
				//return false;
			}
	 }
	roomIdArray.join(",");
	var checkIds =roomIdArray.toString();
	//if(checkIds){
		$.ajax({
			type: "POST",
			url:"/bookroom/findRoomsBy.do",
			dataType:"json",
			data:{roomIds:roomIdArray} ,
			async:false,
			success:function(data){
				var listJson = data.listJson ;
				var selectRoomsTr_html = "" ;
				var selectRoomsTr_index = 1 ;
				if(listJson && listJson.length > 0){
					for(var o = 0 ; o < listJson.length ; o++){  
						selectRoomsTr_html += "<tr>";
						selectRoomsTr_html += "    <td width=\"15%\">"+selectRoomsTr_index+"</td>";
						selectRoomsTr_html += "    <td width=\"15%\">"+listJson[o].buildName+"</td>";
						selectRoomsTr_html += "    <td width=\"15%\">"+listJson[o].floorNo+"</td>";
						selectRoomsTr_html += "    <td width=\"20%\">"+listJson[o].roomId+"</td>";
						selectRoomsTr_html += "    <td>"+listJson[o].roomTypeName+"</td>";
	                    selectRoomsTr_html += "</tr>";
	                    selectRoomsTr_index++ ;
				    }
				}
				$("#selectRoomsAlert table#selectRoomsTable tbody").html(selectRoomsTr_html);
				$("#selectRoomsAlert #selectRoomsNum_span").html(selectRoomsTr_index-1) ; 
				$(".alertDivBg3").fadeIn();
				$("#selectRoomsAlert").fadeIn();
			}
		});
	/*}else{
		altWaringMsg("没有留房数据");
		return false;
	}*/
}

var keFangtZflag=1;
/**
 * 打开客房特征表单
 */
function showKeFangForm(flag){
	keFangtZflag=flag;
	$(".featuresButton").fadeIn();
	$(".alertDivBg").fadeIn();
	$(".sonChk").each(function(){
		$(this).prop("checked",false);
	});
	//特征绑定
	if(flag==1){//订单列表
		var roomCharacter = parseInt($("#roomCharacter").val());
		if(NaN!=roomCharacter && roomCharacter>0){
			$(".roomButtonFblock input[class*='sonChk']").each(function(){
				var pi=this.value;
				$(this).prop("checked",(roomCharacter & (one << pi)) != 0 ? true :false);
			});
			$(".roomButtonFblock input[class*='sonChk']").each(function(){
				if(roomCharacter & (one << pi) == 0 ){
					$("#chkall").prop("checked",false);
					return false;
				}
			});
		}else{
			$("#chkall").prop("checked",false);
		}
	}else if(flag==2){//留房列表
		var roomCharacter = parseInt($("#TMroomCharacter").val());
		if(NaN!=roomCharacter && roomCharacter>0){
			$(".roomButtonFblock input[class*='sonChk']").each(function(){
				var pi=this.value;
				$(this).prop("checked",(roomCharacter & (one << pi)) != 0 ? true :false);
			});
		}
	}
	
}
/**
 * 关闭客房特征表单
 */
function closeKeFangForm(){
	$(".featuresButton").fadeOut();
	$(".alertDivBg").fadeOut();
	$(".alertDivBg2").fadeOut();
}

//房间特征 checkbox 点击事件
function chkAll(){
	var $chkeach = $(".sonChk");
	var $chkall = $("#chkall");
	// 全选按钮 checkbox 点击事件
	$chkall.unbind("click");
	$chkall.bind("click",function(event){
		var check = $(this).prop("checked");
		$chkeach.each(function(index, element) 
		{
			if(check){
				$(element).prop("checked",true);
			}else{
				$(element).prop("checked",false);
			}
		});
	});
	// 子项 checkbox 点击事件
	$chkeach.unbind("click");
	$chkeach.bind("click",function(event)
	{
		var $chks = $(".sonChk:checked");
		$chkall.prop("checked", $chks.length == $chkeach.length);
	});
	
	// 修改时，如果全部选中的时候，则选中  全选按钮
	var $chks = $(".sonChk:checked");
	$chkall.prop("checked", $chks.length == $chkeach.length);
}
//保存选择的房间特征信息
var one = parseInt(1);
function saveCharaters(){
    // 选择的房间特征综合
	var totalVal;
	var roomNames="";
	// 循环判断选中的特征
	$(".sonChk").each(function(index, element){
		if($(element).prop("checked") == true){
			var val = $(element).val();
			roomNames+=$(element).attr("label")+",";
			if(totalVal){
				totalVal = totalVal + (one << val) ;
			}else{
				totalVal = one << val ;
			}
		}
	});
	// if  有选中  else  无选中
	//alert($("#theStatus").attr("checked"));
	if(totalVal){
	          closeKeFangForm();
	          if(keFangtZflag==1){
	        	  $("#roomCharacter").val(totalVal);
	        	  $("#fjtzChk_dd").prop("checked",true);
	        	  btnStatusTriger('1');
	          }else{
	        	  $("#TMroomCharacter_show").val(roomNames);
	        	  $("#TMroomCharacter").val(totalVal)
	          }
	}else{
		    closeKeFangForm();
			 if(keFangtZflag==1){
				  $("#roomCharacter").val(0);
				  $("#fjtzChk_dd").prop("checked",false);
				  btnStatusTriger('1');
	          }else{
	        	  $("#TMroomCharacter_show").val("");
	        	  $("#TMroomCharacter").val(0)
	          }
	}
}

function remainRoomGiveUp(){
	editRow(liuFangRowNum);
}

function hyUnitDataBind(avj){
	var tadVo={
		symbol:1	
	};
	$.ajax({
		url:"/marketing/searchTadoclist.do",
		type:"post",
		dataType:'json',
		data:tadVo,
		success:function(data){
			if(data!=undefined && data!=null){
				$('#compUnit').autocomplete(data,   
				         {  
							minChars: 0,
							max: 12,
							autoFill: true,
							mustMatch: false,
							matchContains: false,
							scrollHeight: 220,
							width: 400,
							formatItem: function(row, i, total) {
								return row.namec;
							},
							formatMatch: function(row) {
								return row.namec;
							},
							formatResult: function(row){
								return row.namecc;
							}
				    }).result(function(event, row, formatted){
				    	  $("#compId").val(row.id);
				    	  $("#compType").val(row.taType);
				    	  // 设置房价方案
				    	  if(row.rateCode || !isNull(row.rateCode)){
				    		  $("#prcSchemeId").val($.trim(row.rateCode));
				    	  }
				    	  // 销售员
				    	  $("#saler").val(row.salemanName);
				    	  inputListener(event);
				    });  
			}
		}
	});
}

//~~~~~~~~~~~~~~~~~~~~~~~~~~~自定义form验证规则----------------------------
$.formUtils.addValidator({
    name : 'isEmail',
    validatorFunction : function(value, $el, config, language, $form) {
    	var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return value==""?true:email.test(value);
    },
    errorMessage : '电子邮件格式不正确',
    errorMessageKey: 'badCustomEmail'
});
function getKey(e){ 
	e = e || window.event; 
	var keycode = e.which ? e.which : e.keyCode; 
	if(keycode == 46 ){ //如果按下Del键 
	//在这里设置你想绑定的事件 
		
		//containsFocus
		
		if(editBookRoomFlexGrid!=undefined && editBookRoomFlexGrid.containsFocus()){
		
			var currentItem=editBookRoomFlexGrid.itemsSource.currentItem;
			if(currentItem==undefined){
				editBookRoomFlexGrid.itemsSource.removeAt(editBookRoomFlexGrid.selection.row);
				editBookRoomFlexGrid.refresh();
				return false;
			}
			var xh=editBookRoomFlexGrid.getCellData(editBookRoomFlexGrid.selection.row,editBookRoomFlexGrid.selection.col);
			if(xh==null){
				editBookRoomFlexGrid.itemsSource.removeAt(editBookRoomFlexGrid.selection.row);
				editBookRoomFlexGrid.refresh();
				return false;
			}
			var itemNum=editBookRoomFlexGrid.itemsSource.items.length;
			if(itemNum==1){
				 altWaringMsg("至少需要一条预定记录");
				 return false;
			}
			var tsaveNum=currentItem.saveNum==undefined?0:currentItem.saveNum;
			var treachNum=currentItem.reachNum==undefined?0:currentItem.reachNum;
			if(currentItem.bookRoomId==undefined){
				
				editBookRoomFlexGrid.itemsSource.removeAt(editBookRoomFlexGrid.selection.row);
				editBookRoomFlexGrid.refresh();
				return false;
			}
			else if(currentItem.reachNum!=undefined && currentItem.reachNum>0){
				altWaringMsg("本记录抵达数大于0，不能删除.");
				return false;
			}else{
				var bookRoomId=currentItem.bookRoomId;
				var msg="是否确认删除本记录?";
				if(tsaveNum>0){
					msg="本记录有留房，是否确认删除？";
				}
				altInfMsg(msg,function(){
					//--发起异步请求
					$.ajax({
						   type: "POST",
						   url: path+"/bookroom/delete.do",
						   data: {bookRoomId:bookRoomId},
						   dataType:'json',
						   success: function(ajaxResult){
							   if(ajaxResult.success){
								   editBookRoomFlexGrid.itemsSource.removeAt(editBookRoomFlexGrid.selection.row);
								   editBookRoomFlexGrid.refresh();
								   //-列表刷新
								   refreshGrid('2');
							   }else{
								   altWaringMsg(ajaxResult.msg);
								   return false; 
							   }
						   }
						});
					//---
				},function(){return false;});
				
			}
		}else{
			
			return false;
		}
	} 
} 

$(function(){
	listenKey();
});

function listenKey() {
	if (document.addEventListener) {
		document.addEventListener("keyup", getKey, false);
	} else if (document.attachEvent) {
		document.attachEvent("onkeyup", getKey);
	} else {
		document.onkeyup = getKey;
	}
} 
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~自定义form验证规则----------------------------
$.formUtils.addValidator({
    name : 'isMobile',
    validatorFunction : function(value, $el, config, language, $form) {
    	var mobile = /(^13\d{9}$)|(^14)[5,7]\d{8}$|(^15[0,1,2,3,5,6,7,8,9]\d{8}$)|(^17)[6,7,8]\d{8}$|(^18\d{9}$)/g;
        return value==""?true:mobile.test(value);
    },
    errorMessage : '格式不正确',
    errorMessageKey: 'badCustomMobile'
});
$.formUtils.addValidator({
    name : 'simpleDate',
    validatorFunction : function(value, $el, config, language, $form) {
    	var text = /^(\d{4})\-(\d{2})\-(\d{2})$/;
        return value==""?true:text.test(value);
    },
    errorMessage : '不是日期格式',
    errorMessageKey: 'badCustomSimpleDate'
});
$.formUtils.addValidator({
    name : 'simpleTime',
    validatorFunction : function(value, $el, config, language, $form) {
    	var text = /^(\d{4})\-(\d{2})\-(\d{2})$/;
        return value==""?true:Utilities.RegValidate.isSimpleTime(value);
    },
    errorMessage : '不是时间格式',
    errorMessageKey: 'badCustomSimpleDate'
});
$.formUtils.addValidator({
    name : 'ChinaZH',
    validatorFunction : function(value, $el, config, language, $form) {
    	var text = /[\\u4e00-\\u9fa5]+/;
        return value==""?true:!text.test(value);
    },
    errorMessage : '必须为中文',
    errorMessageKey: 'badCustomSimpleDate'
});


function setBookNum(){
}