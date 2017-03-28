$(function(){
		/*遮罩层DIV高度*/
		$(".alertDivBg").css("height",$(document).height());     
        $(".alertDivBg").css("width",$(document).width());  
		/*遮罩层DIV高度*/
		/*二级菜单点击选中事件*/
		$(".secondMenu li a").click(function(){
			$(".secondMenu li a").removeClass('thisSecMenu');
			$(this).addClass('thisSecMenu');
		});
		/*二级菜单点击选中事件 结束*/
		/*二级菜单关闭事件*/
		$(".secondMenu li a img").click(function(){
			$(this).parent().parent().prev().children().addClass("thisSecMenu")
			$(this).parent().parent().remove();
		});
		/*二级菜单关闭事件 结束*/
		/*右侧菜单点击*/
		$(".rightNav li a").click(function(){
			$(".rightNav li a").removeClass("rightNavPoint");
			$(this).addClass("rightNavPoint")
			//$(this).parent().parent().remove();
		});
		/*右侧菜单点击 结束*/
		/*添加标签页*/
		$(".nav .showMenu li").click(function(){
			var scdMenuName = $(this).html();
			if(ifHave(scdMenuName)) return;//判断是否已经打开标签
			$(".secondMenu li:last").after('<li><a href="javascript:;">'+scdMenuName+'<img src="../img/closeSeMenu.png"></a></li>');
			$(".secondMenu li a").click(function(){
				$(".secondMenu li a").removeClass('thisSecMenu');
				$(this).addClass('thisSecMenu');
			});
			$(".secondMenu li a img").click(function(){
				$(this).parent().parent().prev().children().addClass("thisSecMenu")
				$(this).parent().parent().remove();
			});
		});	
		//便利标签页
		function ifHave(text){
			var tabs = $('ul.secondMenu li');
			for(var i=0;i<tabs.length;i++)
			if($(tabs[i]).text() == text) return true;
		}
		//便利标签页END
		/*添加标签页 结束*/
		//建筑定义，新增

		$(".closeDiv").click(function(){
			$(".acquaintanceDiv").fadeOut();
			$(".alertDivBg").fadeOut();
		});
		//建筑定义，新增END
		
		/*table点击换行颜色*/
		
		$(".tabChangBg tr").click(function(){
			$(".tabChangBg tr td").removeClass("thisTrTd");
			$(this).find("td").addClass("thisTrTd");
		});
		
		/* /table点击换行颜色*/
	});


	//flexgrid
	var grid;
	var array=[];
	var view;
	$(function(){
		view = new wijmo.collections.CollectionView(array);
	    grid = new wijmo.grid.FlexGrid('#theGrid', {
	     	  autoGenerateColumns: false,
	     	  headersVisibility:wijmo.grid.HeadersVisibility.Column,
	     	  selectionMode:'Row',
	     	  itemsSource: view,
	    	  columns: [
							{ header: '序号', binding: 'cid',width:80,minWidth:80,align:'center',isReadOnly:true},
							{ header: 'id', binding: 'id',width:'*',isReadOnly:true,visible:false},
							{ header: '账号', binding: 'operId',width:100,minWidth:80,align:'left',isReadOnly:true},
							{ header: '密码', binding: 'passwd',width:100,minWidth:80,align:'left',isReadOnly:true}, 
							{ header: '操作员名称', binding: 'operName',width:150,minWidth:80,align:'left',isReadOnly:true},
							{ header: '职务', binding: 'post',width:120,minWidth:80,align:'left',isReadOnly:true}, 
							{ header: '所属工作组', binding: 'groupId',width:150,minWidth:80,align:'left',isReadOnly:true},
							/*{ header: '操作员级别', binding: 'rights',width:100,minWidth:60,align:'center',isReadOnly:true},*/
							{ header: '状态', binding: 'status',width:80,minWidth:80,align:'center',isReadOnly:true}						
	       	],
	      
	    });
	    grid.onSelectionChanged=function(e){
        	
        };
	    //
	    initElement();
	});
   var baseFormTitle="操作员资料";
	/**
	 * 初始化页面元素
	 */
	function initElement(){
		doSearch('#');

		$("#dataForm select,checkbox").change(inputListener);
		$("#dataForm input,textarea").keyup(inputListener);
		//
		codeLetterEventBind();
	    var host = grid.hostElement;
        host.addEventListener('dblclick', function (e) {
       	 console.log(e);
       	 formEdit();
         var ht = grid.hitTest(e);
            //check if cell is clicked
            if (ht.cellType == wijmo.grid.CellType.Cell) {
                //alert("Cell is clicked");
            }
        });

	}
function codeLetterEventBind(){
	$(".cabDiv a").click(function(){
		console.log($(this).get(0).innerText);
		doSearch($(this).get(0).innerText);
	});
}
/**
 * 新增表单
 */
	function showForm(){
		//显示弹出窗口
		$(".acquaintanceDiv").fadeIn();
		$(".alertDivBg").fadeIn();
	}
	function formClose(){
		//隐藏弹出窗口
		$(".acquaintanceDiv").fadeOut();
		$(".alertDivBg").fadeOut();
	}
/**
 * 清空查询表单
 */
function searchReset(){
	$(':input','#searchForm')
			.not(':button, :submit, :reset')
			.val('')
			.attr('checked',false)
			.removeAttr('selected');
}
/**
 * 放弃修改-恢复默认
 */
function formGiveUp(){
	var id = $("#operId").val();
	//编辑中的放弃
	if(id!=""){
		formEdit();
	}else{
		$("#dataForm").get(0).reset();
		btnStatusTriger('1');
	}
	//恢复特殊表单元素
	$("#theStatus").get(0).checked='checked';
}
/**
 * 表单验证
 */
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
	if(!$("#dataForm").isValid(lang, conf, false) ) {
		console.log(errors);
		if(errors.length>0){
			alert(errors[0].el.attr('label')+"  "+errors[0].error);
			return false;
		}
	} else {
		return true;
	}
}
	/**
	 * 表单提交
	 */
    function formSubmit(){
		console.log($("#dataForm").serialize());
		if(!formValidate()){return false;}
		console.log($.trim($("#theOperId").val()));
		var theOperId=$.trim($("#theOperId").val());
		if(theOperId.length>6){
			altWaringMsg("用户名最大长度6",function(){return false;});
			return false;
		}
		if(Utilities.RegValidate.isChinaZH(theOperId)){
			altWaringMsg("用户名不能包含中文",function(){return false;});
			return false;
		}
		$("#operId").val($("#theOperId").val());
		var id=$("#operId").val();
		$.ajax({
			url:"ajaxSave.do",
			type:"post",
			dataType:'json',
			data:$("#dataForm").serialize(),
			success:function(data){
				if(data.success){
					//
					console.log(data);
					//btnStatusTriger('2');
					refreshGrid('1');
					formEdit(data.obj);
				}else{
					alert(data.msg);
				}
			}
		});
	}
/**
 * 新增数据
 */
function formAdd(){
	$("#theOperId").removeAttr("disabled")
	$("#formTitle").text(baseFormTitle+"-新增");
	showForm();
	//
	$("#dataForm").get(0).reset();
	//清除除有效外的其他checkbox

	//
	btnStatusTriger('1');
	inputReadonlyTrigger(false);
	//
	$("#theStatus").attr("checked",true)
}
var formObj={};
var gridSelRowNum=0;
/**
 * 查看/编辑数据
 */
function formEdit(id){
	if(id==undefined)
		id=array[grid.selection.row].operId;gridSelRowNum=id;
	$.ajax({
		type: "post",
		url: "/operator/getOperator.do",
		data: {'id':id},
		dataType:'json',
		success: function(aj){
			if(aj.success){
				$("#formTitle").text(baseFormTitle+"-维护");
				// 初始化工作组
				initWorkGroup(aj.attributes);
				showForm();
				formObj=aj.obj;
				loadFormData(aj.obj);
				$("#theOperId").attr("disabled","disabled");
			}else{
				alert(aj.msg);
			}
		}
	});

}
function loadFormData(formObj){
	if(null!=formObj.lastDate){
		formObj.lastDate=new Date(formObj.lastDate).format('yyyy-MM-dd');
	}
	if(null!=formObj.updateDate){
		formObj.updateDate=new Date(formObj.updateDate).format('yyyy-MM-dd');
	}
	console.log(formObj);
	//form数据绑定
	//setForm('dataForm',formObj);
	setFormData('dataForm',formObj);
	//loadData(formObj);
	console.log("11111111");
	//特殊数据处理
	//
	$("#passwd1").val(formObj.passwd);
	$("#theOperId").val(formObj.operId);

	if(formObj.status==0){//有效
		btnStatusTriger('2');
		inputReadonlyTrigger(false);
	}else if(formObj.status==1){//无效
		btnStatusTriger('3');
		inputReadonlyTrigger(true);
	}
}
/**
 * 将JSON数据设置到Form,以name为键
 * @param $form
 * @param formJson
 */
function setFormData($form,formJson){
    var key,value,tagName,type,arr;
    for(x in formJson){
        key = x;
        value = formJson[x];
        $("#"+$form+" [name='"+key+"']").each(function(){
            tagName = $(this)[0].tagName;
            type = $(this).attr('type');
            if(tagName=='INPUT'){
                if(type=='radio'){
                    $(this).attr('checked',$(this).val()==value);
                }else if(type=='checkbox'){
                        if($(this).val()==value){
                            $(this).attr('checked',true);
                        }else{
                        	$(this).attr('checked',false);
                        }
                }else{
                    $(this).val($.trim(value));
                }
            }else if(tagName=='SELECT'){
                var options= $(this)[0].options;
        		for(var i=0;i<options.length;i++){
        			if(options[i].value==value){
        				$(this)[0].selectedIndex=i;
        				$(this)[0].value=value;
        				break;
        			}
        		}
            }else if(tagName=='TEXTAREA'){
            	
            }
             
        });
    }
}
//取消，将状态设为无效
function formDel(){
	var id = $('#operId').val();
	if(id!=""){
		var result = confirm('你确定要取消，并把该操作员的状态设为无效吗？');  
		if(result){
			$.ajax({
				url:"/operator/logicDelOperator.do",
				type:"post",
				dataType:'json',
				data:{id:id},
				success:function(data){
					if(data.success){
						closeForm();
						updateGridCell();
					}else{
						alert(data.msg);
					}
				}
			});
		}else return false;
	}
}
function updateGridCell(){
	var rowNum=grid.selection.row;
	grid.setCellData(rowNum,6,'无效');
}
/**
 * 刷新数据表格
 */
function refreshGrid(type){
	if(type==undefined){return false;}
	if(type=='1'){
		doSearch('#');
	}else if(type=='2'){
		doSearch();
	}
}
function inputReadonlyTrigger(r){
	var gry9="gry_9";
	if(r){
		$("#dataForm input").each(function(){
			$(this).attr('style',disable0);
			var type=$(this).attr('type');
			var clasz=$(this).attr('class');
			console.log("class="+clasz);
			console.log('type='+type);
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
		$("#dataForm select").each(function(){
			$(this).attr('style',disable0);
			$(this).attr('disabled','disabled');
		});
		//
		
	}else{
		$("#dataForm input").each(function(){
			$(this).attr('style',enable0);
			var type=$(this).attr('type');
			var clasz=$(this).attr('class');
			console.log("class="+clasz);
			console.log('type='+type);
			if(type=='select'){
				$(this).attr('disabled',false);
			}
			if(type=='checkbox'){
				$(this).attr('disabled',false);
			}
			console.log(clasz);
			//if(clasz.indexOf(gry9)==-1){
			//	$(this).removeAttr('readonly')
			//}
			if(!$(this).hasClass(gry9))
			   $(this).removeAttr('readonly')
		});
        $("#dataForm select").each(function(){
        	$(this).attr('style',enable0);
        	$(this).attr('disabled',false);
		});
        //
        
	}
}
/**
 * 数据检索
 */
function doSearch(code){
	var formData = {};
	if(code){
		formData.codeLetter=code;
	}else{
		formData = $("#searchId").formToArray();
		if(code){
			formData.push({name:'codeVal',value:code});
		}else{
			var flag = true;
			for(var i =0;i<formData.length;i++){
				if(formData[i].value!='' && formData[i].name!='chkStat'){
					flag=false;
				}
			}
			if(flag){
				alert("请输入查询条件");
				return;
			}
		}
	}
	console.log("0000="+formData);
	array.length=0;
	$.ajax({
		url:"ajaxList.do",
		type:"post",
		dataType:'json',
		data : formData,
		mimeType : "multipart/form-data",
		cache : false,
		success:function(data, textStatus, jqXHR){
			//alert(JSON.stringify(data));
			if(data){
				for (var i = 0; i < data.length; i++) {
					array.push({
						cid: i+1,
						operId:data[i].operId,
						operName:data[i].operName,
						passwd:data[i].passwd,
						gstNamee:data[i].passwd,
						groupId:data[i].groupId,
						deptId:data[i].deptId,
						rights:data[i].rights,
						post:data[i].post,
						status:data[i].status==0?"有效":"无效"
					});
				}
			}
			console.log(array);
			//
			view = new wijmo.collections.CollectionView(array);
			console.log(grid);
			grid.itemsSource = view;
			grid.refresh();
			//设置选中行
			if(gridSelRowNum!=undefined){
				grid.selection=new wijmo.grid.CellRange(gridSelRowNum, -1, -1, -1);
			}
		}
	});
}
var disable0='color: grey;cursor:not-allowed;';
var enable0='cursor:pointer,color:inherit;';
function btnStatusTriger(a){
	if(a=='1'){//
		$("div .GuestTabR a[class*='group1']").each(function() {
			console.log("groip1:"+$(this)[0].innerHTML);
			console.log("---"+$(this).attr('style'));
			$(this).removeAttr("disabled");
			$(this).attr('style',enable0);
			if($(this).attr('jack')!=''){
				$(this).attr('onclick',$(this).attr('jack'));
			}
			return true;
		});
		$("div .GuestTabR a[class*='group2']").each(function() {
			console.log("group2:"+$(this)[0].innerHTML);
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
	}else if(a=='2'){
		$("div .GuestTabR a[class*='group1']").each(function() {
			console.log("groip1:"+$(this)[0].innerHTML);
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
		$("div .GuestTabR a[class*='group2']").each(function() {
			console.log("group2:"+$(this)[0].innerHTML);
			$(this).removeAttr("disabled");
			$(this).attr('style',enable0);
			console.log("jack=",$(this).attr('jack'));
			if($(this).attr('jack')!=''){
				$(this).attr('onclick',$(this).attr('jack'));
			}
			return true;
		});
	}else if(a=='3'){
		$("div .GuestTabR a[class*='group1']").each(function() {
			console.log("groip1:"+$(this)[0].innerHTML);
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
		$("div .GuestTabR a[class*='group2']").each(function() {
			console.log("group2:"+$(this)[0].innerHTML);
			$(this).attr('disabled',true);
			$(this).attr('style',disable0);
			$(this).removeAttr("onclick");
			return true;
		});
	}
	//确定 OK
	//放弃 cancel
	//新增
	//取消
	//合同
	//合同价
	//签单人
	//消费统计
	//打印
	//退出
}
/**
 * 表单输入变化监听
 */
function inputListener(event){
	console.log(event);
	var id=$(this).attr('id');
	console.log('id='+id);
	btnStatusTriger('1');
}

function initWorkGroup(attrs){
	var workGroupList = attrs.workGroupList;
	var html = "" ;
	if(workGroupList){
		var length = workGroupList.length ;
		for ( var i = 0; i < length; i++) {
			var array_element = workGroupList[i];
			html += "<option value=\""+array_element.groupId+"\">"+array_element.groupName+"</option>"
		}
	}
	$("select#workGroup").html(html);
}