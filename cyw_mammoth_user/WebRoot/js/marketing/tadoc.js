//扩展Date的format方法   
Date.prototype.format = function (format) {  
	var o = {  
		"M+": this.getMonth() + 1,  
		"d+": this.getDate(),  
		"h+": this.getHours(),  
		"m+": this.getMinutes(),  
		"s+": this.getSeconds(),  
		"q+": Math.floor((this.getMonth() + 3) / 3),  
		"S": this.getMilliseconds()  
	}  
	if (/(y+)/.test(format)) {  
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	}  
	for (var k in o) {  
		if (new RegExp("(" + k + ")").test(format)) {  
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
		}  
	}  
	return format;  
} 

//调用
//var date = new Date(parseInt("1347497754133"));
//date.format("yyyy-MM-dd");
var grid,view,unitObj;
var array=[];
var thePhone;
var theBankNum;
var theLimit;
var thePeriod;
var theExpiryDate;
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
		
		/*小键盘点击切换背景*/
		$(".cabDiv a").click(function(){
			$(".cabDiv a").removeClass("pointCab");
			$(this).addClass("pointCab");
		});
		/*小键盘点击切换背景END*/
		/*订单详情显示弹出层*/
		$("#unitDetail").click(function(){
			//
			$("#del").get(0).style="cursor:pointer,color:inherit;";
			var id=array[grid.selection.row].id;
			var citem= grid.itemsSource.currentItem;
			if(null!=citem){
				id=citem.id;gridSelRowNum=grid.selection.row;
			}
			
			$.ajax({
				   type: "post",
				   url: "/marketing/getTaDoc.do",
				   data: {'id':id},
				   dataType:'json',
				   success: function(aj){
				   	 if(aj.success){
				   		$(".unitDetailDiv").fadeIn();
						$(".alertDivBg").fadeIn();
						//alert(aj.obj.namec);
						unitObj=aj.obj;
						/**房价方案**/
						var hroomPlanList = aj.attributes.hroomPlanList ;
						if(hroomPlanList && hroomPlanList.length > 0){
							var html = "<option value=''  selected='selected'></option>" ;
							for ( var i = 0; i < hroomPlanList.length; i++) {
								html += "<option value='"+hroomPlanList[i].hroomPlan.codeId.trim()+"' "
									+((aj.obj.rateCode?(hroomPlanList[i].hroomPlan.codeId.trim() ==aj.obj.rateCode.trim()  ? "selected='selected'" : "") : ""))+">"
										+hroomPlanList[i].hroomPlan.codeNamec+"</option>" ;
							}
							$("#hroomPlanList").html(html);
						}
						/**房价方案**/
						/**销售员**/
						var saleManList = aj.attributes.saleManList ;
						if(saleManList && saleManList.length > 0){
							var _html = "" ;
							for ( var i = 0; i < saleManList.length; i++) {
								_html += "<option value='"+saleManList[i].codeId.trim()+"' "
									+((aj.obj.rateCode?(saleManList[i].codeId.trim() ==aj.obj.salemanId.trim()  ? "selected='selected'" : "") : ""))+">"
										+saleManList[i].codeNamec+"（"+saleManList[i].codeId.trim()+"）"+"</option>" ;
							}
							$("#saleManList").html(_html);
						}
						/**销售员**/
						loadUnitForm(aj.obj);
						$("#flag").val("2");
						$("#theStatus").click(function(){return false;});
						//btnStatusTriger(2);
				   	 	//关闭弹出窗口
				   	 }else{
				   		altWaringMsg(aj.msg); 
				   	 }
				   }
			});
		});
		$(".closeDiv").click(function(){
			$(".unitDetailDiv").fadeOut();
			$(".alertDivBg").fadeOut();
		}); 
		$(".closeDiv2").click(function(){
			$(".leaveTheRoom").fadeOut();
			$(".alertDivBg2").fadeOut();
		});
		/*订单详情显示弹出层END*/
		
		/*新增合约单位弹出层*/
		$("#addTadoc").click(function(){
			$.ajax({
			    type : "post",
				url : "/marketing/initTaDoc.do",
				dataType : 'json',
				success : function(data) {
					
					/**房价方案**/
					var hroomPlanList = data.attributes.hroomPlanList ;
					if(hroomPlanList && hroomPlanList.length > 0){
						var html = "<option value=''  selected='selected'></option>" ;
						for ( var i = 0; i < hroomPlanList.length; i++) {
							html += "<option value='"+hroomPlanList[i].hroomPlan.codeId.trim()+"'>"+hroomPlanList[i].hroomPlan.codeNamec+"</option>" ;
						}
						$("#hroomPlanList").html(html);
					}
					/**房价方案**/
					/**销售员**/
					var saleManList = data.attributes.saleManList ;
					if(saleManList && saleManList.length > 0){
						var _html = "" ;
						for ( var i = 0; i < saleManList.length; i++) {
							_html += "<option value='"+saleManList[i].codeId.trim()+"'>"+saleManList[i].codeNamec+"（"+saleManList[i].codeId.trim()+"）"+"</option>" ;
						}
						$("#saleManList").html(_html);
					}
					/**销售员**/
					
					$(".unitDetailDiv").fadeIn();
					$(".alertDivBg").fadeIn();
					$("#flag").val("1");
					$("#addTADocForm").get(0).reset();
					$("#theId").val('');
					// 功能按钮可用禁用
					btnStatusTriger('1');
					inputReadonlyTrigger(false);
					$("#del").attr('disabled', 'disabled');
					$("#del").get(0).style = "color: grey;cursor:not-allowed;";
					$("#showId").attr("disabled",true);
					// setDisabled('del',true,setStatus);
					$("#theStatus").attr('checked', 'checked');
					$("#theStatus").click(function() {
						return false;
					});
					//$("#del").addClass("disable01");
				}
			});
		});
		$(".closeDiv").click(function(){
			$(".unitDetailDiv").fadeOut();
			$(".alertDivBg").fadeOut();
		}); 
		$(".closeDiv2").click(function(){
			$(".leaveTheRoom").fadeOut();
			$(".alertDivBg2").fadeOut();
		});
		
		/*新增合约单位弹出层END*/
		
		
		/*3级弹出框 - 留房*/
		$("#reservedRoom").click(function(){
			$(".alertDivBg2").fadeIn();
			$(".leaveTheRoom").fadeIn();			
		});
		
		/*3级弹出框 - 留房*/
		/*房态小块*/
		$(".statusBlock").click(function(){
			$(this).nextAll(".pointStatus").eq(0).fadeIn();
			$(this).nextAll(".pointStatusRight").eq(0).fadeIn();
		});
		$(".pointStatusRight").click(function(){
			$(this).fadeOut();
			$(this).prev().fadeOut();
		});
		/*房态小块END*/
		
		/*table点击换行颜色*/
		
		$(".tabChangBg tr").click(function(){
			$(".tabChangBg tr td").removeClass("thisTrTd");
			$(this).find("td").addClass("thisTrTd");
		});
		//设置周期
		var expiryDate=$("#expiryDate").inputmask("yyyy-mm-dd");
		var period= $("#period").inputmask("9{0,5}");
		var limit=$("#limit").inputmask("9{0,10}.9{0,2}");
		//var bankNum=$("#bankNum").inputmask('mask', { mask: "9999 9999 9999 9999 9{0,3}",greedy:false});
		//var phone=$("#phone").inputmask({'mask':'9{11}','greedy':true});//手机
		 
		//取消
		 //$("#del").bind('click',setStatus);
		
		//右侧按钮事件
		
		//$("#OK").bind('click', formSubmit);
		//$("#cancel").bind('click',formCancel);
		
		   //合约单位表格初始化
	        var view = new wijmo.collections.CollectionView(array);
	        // initialize the grid
	        grid = new wijmo.grid.FlexGrid('#ydtable', {
	              		autoGenerateColumns: false,
	             	  columns: [
	                    { header: '序号', binding: 'cid',width:80,minWidth:80,align:'center',isReadOnly:true}, 
	                    { header: '单位简称', binding: 'compId',width:85 ,minWidth:80,isReadOnly:true}, 
	                    { header: '中文名', binding: 'namec' ,width:100,minWidth:80,isReadOnly:true},
	                    { header: '单位电话', binding: 'tele' ,width:120,minWidth:80,isReadOnly:true},
	                    { header: '联系人', binding: 'connector',width:100,minWidth:100 ,isReadOnly:true}, 
	                    { header: '联系手机', binding: 'phone' ,name:'lxrPhone',align:'center',width:120,minWidth:100,isReadOnly:true},
	                    { header: '有效期限', binding: 'expiryDate',name:'expiryDate',width:100,minWidth:100,align:'center',dataType: "Date",format:'yyyy-MM-dd',width:'*',isReadOnly:true},
	                    { header: '合同号', binding: 'id',dataType:'Number',format:'f0',width:120,minWidth:80,align:'left',isReadOnly:true},
	                    { header: '副合同号', binding: 'taCompCd',dataType:'Number',format:'f0',width:'*' ,align:'left',isReadOnly:true},
	                    { header: '状态', binding: 'status' ,width:80,minWidth:80,align:'center',isReadOnly:true}
	                ],
	                 itemsSource: view,
	                 allowDragging:wijmo.grid.AllowDragging.None,
	                 headersVisibility:wijmo.grid.HeadersVisibility.Column,
	                 selectionMode:'Row'
	             });
	        grid.itemFormatter=function(panel, r, c, cell) {
	        	if (panel.cellType == wijmo.grid.CellType.Cell) {
	        	    var col = panel.columns[c];
	        	    if (col.name == 'expiryDate') {
	        	    }
	        	  }
	        };
	        grid.onSelectionChanged=function(e){
	        	//console.log('2222'+e.col+"-"+e.row);
	        	//console.log('data='+grid.getCellData(e.row,e.col));
	        	//console.log('id=',array[e.row].id);
	        };
	        view.currentChanged.addHandler(function (sender, args) {
	            //updateDetails();
	        });
	       // update the details when the CollectionView's currentItem changes
	        function updateDetails() {
	            //var item = view.currentItem;
	            //$("#viewId").val(item.checkId);
	        } 
	        formInit();
		/* /table点击换行颜色*/
	});
	function formInit(){
		 $("#searchForm input").inputmask();
		 $("#addTADocForm input").inputmask();
		 //
		 $("#theStatus").click(function(){return false;});
		 //
		 $("#addTADocForm select,checkbox").change(inputListener);
		 $("#addTADocForm input,textarea").keyup(inputListener);
		 //
		 var host = grid.hostElement;
         //handle the click event
         host.addEventListener('dblclick', function (e) {
        	 console.log(e);
             var ht = grid.hitTest(e);
             //check if cell is clicked
             if (ht.cellType == wijmo.grid.CellType.Cell) {
            	 $("#unitDetail")[0].click();
             }
         });
	}


	$.formUtils.addValidator({
        name : 'isEmail',
        validatorFunction : function(value, $el, config, language, $form) {
        	var email = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
            return value==""?true:email.test(value);
        },
        errorMessage : '电子邮件格式不正确',
        errorMessageKey: 'badCustomEmail'
    });
	$.formUtils.addValidator({
        name : 'isHanZi',
        validatorFunction : function(value, $el, config, language, $form) {
        	var text = /[^\u4e00-\u9fa5]/;
            return !text.test(value);
        },
        errorMessage : '请输入中文',
        errorMessageKey: 'badCustomHanZi'
    });
	$.formUtils.addValidator({
        name : 'simpleDate',
        validatorFunction : function(value, $el, config, language, $form) {
        	var text = /^(\d{4})\-(\d{2})\-(\d{2})$/;
            return text.test(value);
        },
        errorMessage : '不是日期格式',
        errorMessageKey: 'badCustomSimpleDate'
    });
	$.formUtils.addValidator({
        name : 'simpleMobile',
        validatorFunction : function(value, $el, config, language, $form) {
        	var text = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
            return value==""?true:text.test(value);
        },
        errorMessage : '不是正确的手机号码格式',
        errorMessageKey: 'badCustomSimpleMobile'
    });
function formValidate(){
	var errors = [];
	var conf = {
			  onError : function($form) {
			      altWaringMsg('验证表单 '+$form.attr('id')+' 失败!');
			    },
			  onSuccess : function($form) {
			      altWaringMsg('表单 '+$form.attr('id')+' 验证通过!');
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
	if(!$("#addTADocForm").isValid(lang, conf, false) ) {
	    //displayErrors( errors );
		console.log(errors);
		if(errors.length>0){
			altWaringMsg(errors[0].el.attr('label')+"  "+errors[0].error);
			return false;
		}
	   } else {
	     return true;
	}
}
var gridSelRowNum=0;
//查看表单详情
function formDetail(id){
	if(id==undefined)
	id=array[grid.selection.row].id;gridSelRowNum=grid.selection.row;
	/*var citem= grid.itemsSource.currentItem;
	if(null!=citem){
		id=citem.id;gridSelRowNum=grid.selection.row;
	}*/
	$.ajax({
		   type: "post",
		   url: "/marketing/getTaDoc.do",
		   data: {'id':id},
		   dataType:'json',
		   success: function(aj){
		   	 if(aj.success){
		   		$(".unitDetailDiv").fadeIn();
				$(".alertDivBg").fadeIn();
				unitObj=aj.obj;
				loadUnitForm(aj.obj);
				/**房价方案**/
				var hroomPlanList = aj.attributes.hroomPlanList ;
				if(hroomPlanList && hroomPlanList.length > 0){
					var html = "<option value=''  selected='selected'></option>" ;
					for ( var i = 0; i < hroomPlanList.length; i++) {
						html += "<option value='"+hroomPlanList[i].hroomPlan.codeId.trim()+"' "
							+((aj.obj.rateCode?(hroomPlanList[i].hroomPlan.codeId.trim() ==aj.obj.rateCode.trim()  ? "selected='selected'" : "") : ""))+">"+hroomPlanList[i].hroomPlan.codeNamec+"</option>" ;
					}
					$("#hroomPlanList").html(html);
				}
				/**房价方案**/
				$("#flag").val("2");
				$("#theStatus").click(function(){return false;});
		   	 }else{
		   		altWaringMsg(aj.msg); 
		   	 }
		   }
	});
}
function limitConvert(){
	$("#limit").val(getDoubleStr($("#limit").val()));
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
//确定-提交表单	
function formSubmit(){
	if(!formValidate()){return false;}
	 //设置真值
    var bankNumRaw= $("#bankNum").inputmask('unmaskedvalue');
	$("#bankNum").val(bankNumRaw);
	$("#limit").val(getDoubleStr($("#limit").val()));
	//alert($("#phone").val());return false;
	//alert(bankNumRaw);alert($("#limit").val());return false;
    var param=$("#addTADocForm").serialize();
	$.ajax({
	   type: "post",
	   url: "/marketing/saveTadoc.do",
	   data: param,
	   dataType:'json',
	   success: function(data){
	   	 if(data.success){
			//alert(data.msg);
	        //btnStatusTriger('2');
	        //inputReadonlyTrigger(true);
			formDetail(data.obj);
	   	 	//关闭弹出窗口
			//刷新grid
			if($("#flag").val()=='1'){
				refreshGrid('1');
			}else{
				refreshGrid('2');
			}
	   	 }else{
	   		altWaringMsg(data.msg); 
	   	 }
	   }
	});
}
function formClear(){
	$(':input','#addTADocForm')  
	 .not(':button, :submit, :reset,:checkbox')  
	 .val('')  
	 .attr('checked',false)  
	 .removeAttr('selected'); 
	//恢复特殊表单元素
	$("#theStatus").attr('checked',true);
	$("#theStatus").get(0).checked='checked';
	$("#theStatus").get(0).value="1"
	$("#theTaType")[0].selectedIndex=0;
	$("#hroomPlanList")[0].selectedIndex=0;
	btnStatusTriger('1');
	inputReadonlyTrigger(false);
}
//放弃-重置表单
function formCancel(){
	if($("#flag").val()=="2" && $("#theId").val()!=""){
		loadUnitForm(unitObj);
		btnStatusTriger('2');
	}else{//新增重置
		$("#addTADocForm").get(0).reset();
	}
}

function setDisabled(e,d){
	if(d){
		$("#"+e).attr('disabled','disabled');
		$("#"+e).get(0).style="color: grey;cursor:not-allowed;";
		$("#"+e).removeAttr("onclick");
	}
	else {
		$("#"+e).removeAttr('disabled');
		//$("#"+e).get(0).style="cursor:pointer,color:inherit;";
		$("#"+e).attr('style',enable0);
		if($("#"+e).attr('jack')!=''){
			$("#"+e).attr('onclick',$("#"+e).attr('jack'));
		}
	}
	
}
//取消-表单/设置为无效
function setStatus(){
	 if(!$("#theStatus").get(0).checked){
		 return false;
	 }
	/* var result = confirm('你确定要取消，并把该合约单位的状态设为无效吗？');  
	 if(!result){
		 return false;
	 }*/
	 
	 altInfMsg('确定将此合约单位设为无效？',function(){
		 //当前数据ID
		 var id=$("#theId").val();
		 if(id==null || id==undefined || id=="undefined"){
			 altWaringMsg('数据记录不存在');
			 return false;
		 }
		 //设置为无效
		 $.ajax({
			   type: "post",
			   url: "/marketing/setStatus.do",
			   data: {'id':id,'status':0},
			   dataType:'json',
			   success: function(data){
			   	 if(data.success){
			   	    //关闭弹出窗口
			   	 	$(".unitDetailDiv").fadeOut();   //fadeOut-----hide()????
			        $(".alertDivBg").fadeOut();
			        //refreshGrid(2);
			        updateGridCell();
			   	 }else{
			   		altWaringMsg(data.msg); 
			   	 }
			   }
	    });
	 },function(){return false;});
	 
}
function updateGridCell(){
	var rowNum=grid.selection.row;
	grid.setCellData(rowNum,6,'无效');
}
var path=$("#path").val();
function inputReadonlyTrigger(r){
	if(r){
		$("#addTADocForm input,textarea").each(function(){
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
				if(type=='textarea'){
					$(this).attr('disabled','disabled');
				}
			}
		});
		$("#addTADocForm select").each(function(){
			$(this).attr('style',disable0);
			$(this).attr('disabled','disabled');
		});
	}else{
		$("#addTADocForm input,textaera").each(function(){
			$(this).attr('style',enable0);
			var type=$(this).attr('type');
			if(type=='select'){
				$(this).attr('disabled',false);
			}
			if(type=='checkbox'){
				$(this).attr('disabled',false);
			}
			if(type=='textarea'){
				$(this).attr('disabled',false);
			}
			$(this).removeAttr('readonly');
			$(this).removeAttr('disabled');
		});
        $("#addTADocForm select").each(function(){
        	$(this).attr('style',enable0);
        	$(this).attr('disabled',false);
		});
	}
}
function loadUnitForm(obj){
	if(obj==undefined) return;
	if(obj.expiryDate!=null){
		obj.expiryDate=new Date(obj.expiryDate).format('yyyy-MM-dd');
	}
	setForm('addTADocForm',obj );
	$("#limit").val(getDoubleStr(obj.limit));
	$("#showId").val(obj.id);
	$("#theId").val(obj.id);
	var theTaTypeSel=$("#theTaType").get(0);
	for(var i=0;i<theTaType.options.length;i++){
		if(theTaType.options[i].value==obj.taType){
			theTaType.selectedIndex=i;
		}
	}
	if(obj.status==1){
		btnStatusTriger('2');
		inputReadonlyTrigger(false);
		$("#theStatus").attr('checked','checked');
		$("#theStatus")[0].checked='checked';
	}else{
		btnStatusTriger('3');
		inputReadonlyTrigger(true);
		$("#theStatus").attr('checked',false);
	}
	$("#showId").attr("disabled",true);
	return;
	
}
function doClear(){
	 //var effective=$("#effective").attr("checked");
	 //var uneffective=$("#uneffective").attr("checked");
	var effective=document.getElementById("effective").checked;
	var uneffective=document.getElementById("uneffective").checked;
	$("#searchForm").get(0).reset();
	document.getElementById("effective").checked=effective; 
	document.getElementById("uneffective").checked=uneffective; 
}
//查询列表	
function goSearch(code){
if(code!=0){
	if(code!="*"&&code!="#"){
		$("#codeLetter").val(code);
	}else{
		if(code=="*"){
			$("#codeLetter").val(code);
		}
		if(code=="#"){
			$("#codeLetter").val(code);
		}
	}
}
	$("#tadocform").submit();
}

//清空条件	
function clearText(){
	$("#tadocform").clearForm();
}

/**
 * 窗口关闭 */
//退出-关闭窗口
function closeUnitWindow(){
	$(".unitDetailDiv").fadeOut();
	$(".alertDivBg").fadeOut();
}
//刷新grid
function refreshGrid(type){
	if(type==undefined){return false;}
	if(type=='1'){
		doSearch('#');
	}else if(type=='2'){
		doSearch();
	}
}
/**
 * 查询
 * @param code
 */
function doSearch(code) {
	var formData = {};
	if(code){
		formData.codeLetter=code;
		formData.chkStat=$('#searchForm input:radio[name=chkStat]:checked').val();
	}else{
		formData = $("#searchForm").formToArray();
		if(code){
			formData.push({name:'codeVal',value:code});
		}else{
			var flag = true;
			
			for(var i =0;i<formData.length;i++){
				if(formData[i].value!='' && (formData[i].name!='chkStat' || formData[i].name!='taType')){
					flag=false;
				}
			}
			if(flag){
				altWaringMsg("请输入查询条件");
				return;
			}
		}
	}
	array.length=0;
	$.ajax({
		url : 'searchTadoclist.do',
		type : 'POST',
		data : formData,
		mimeType : "multipart/form-data",
		cache : false,
		success : function(data, textStatus, jqXHR) {
			var jsonObj = JSON.parse(data);
			var len=jsonObj.length;
			//
			 for (var i = 0; i < len; i++) {
				console.log(jsonObj[i].expiryDate);
				if(jsonObj[i].expiryDate!=null){
					jsonObj[i].expiryDate=new Date(jsonObj[i].expiryDate).format('yyyy-MM-dd');
				}
			 	var status="";
			 	if(jsonObj[i].status=="0"){
				    status="无效";
		        }else if(jsonObj[i].status=="1"){
		        	status="有效";
		        }
	            array.push({
		                    cid: i+1,
		                    checkId: jsonObj[i].checkId,
		                    compId: jsonObj[i].compId,
		                    namec: jsonObj[i].namec,
		                    namee: jsonObj[i].namee,
		                    connector :jsonObj[i].connector,
		                    phone :jsonObj[i].phone,
		                    tele: jsonObj[i].tele,
		                    expiryDate: jsonObj[i].expiryDate,
		                    id: jsonObj[i].id,
		                    taCompCd:jsonObj[i].taCompCd,           
		                    status:status,
	                   });
		               
		      }
			console.log(array);
			//
			var view = new wijmo.collections.CollectionView(array);
			grid.itemsSource = view;
			grid.refresh();
			//设置选中行
			if(gridSelRowNum!=undefined){
				grid.selection=new wijmo.grid.CellRange(gridSelRowNum, -1, -1, -1);
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			altWaringMsg('查询异常');
		}
	});
}
var disable0='color: grey;cursor:not-allowed;';
var enable0='cursor:pointer,color:inherit;';
function btnStatusTriger(a){//console.log($("div .GuestTabR a[class*='group1']"));
	if(a=='1'){//
		$("div .GuestTabR a[class*='group1']").each(function() {
			console.log("groip1:"+$(this)[0].innerHTML);
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
	//退出	$("#addTADocForm input")
}
function inputListener(event){
	console.log(event);
	var id=$(this).attr('id');
	console.log('id='+id);
	btnStatusTriger('1');
}