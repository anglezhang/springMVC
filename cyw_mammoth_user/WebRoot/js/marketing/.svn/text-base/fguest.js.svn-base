//*页面初始化
$(function(){
		/*遮罩层DIV高度*/
		$(".alertDivBg").css("height",$(document).height());     
        $(".alertDivBg").css("width",$(document).width());  
		/*遮罩层DIV高度*/
		/*二级菜单点击选中事件*/
		
		/*二级菜单点击选中事件 结束*/
		
		/*小键盘点击切换背景*/
		$(".cabDiv a").click(function(){
			$(".cabDiv a").removeClass("pointCab");
			$(this).addClass("pointCab");
		});
		/*小键盘点击切换背景END*/
		/*订单详情显示弹出层*/
		$("#acquaintance").click(function(){
			$(".acquaintanceDiv").fadeIn();
			$(".alertDivBg").fadeIn();
		});
		$(".closeDiv").click(function(){
			$(".acquaintanceDiv").fadeOut();
			$(".alertDivBg").fadeOut();
		});
		$(".closeAlert").click(function(){
			$(".leaveTheRoom").fadeOut();
			$(".alertDivBg2").fadeOut();
			$(".otherDiv").fadeOut();
			$(".alertDivBg2").fadeOut();
			$(".featuresButton").fadeOut();
		});
		/*订单详情显示弹出层END*/
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
		
		/* /table点击换行颜色*/
		
		/*其他信息*/
		$("#otherInformation").click(function(){
			$(".otherDiv").fadeIn();
			$(".alertDivBg2").fadeIn();
		});
		/*其他信息END*/
		/*房间特征*/
		$(".featuresChooseButton").click(function(){
			$(".alertDivBg2").fadeIn();
			$(".featuresButton").fadeIn();
		});
		/*房间特征END*/
	});
	
//flexgrid
	var grid;
	var array=[];
	var view;
	$(function(){
		view = new wijmo.collections.CollectionView(array);
	    grid = new wijmo.grid.FlexGrid('#flexgrid', {
	     	  autoGenerateColumns: false,
	     	  headersVisibility:wijmo.grid.HeadersVisibility.Column,
	     	 allowDragging:wijmo.grid.AllowDragging.None,
	     	  selectionMode:'Row',
	     	  itemsSource: view,
	    	  columns: [
							{ header: '序号', binding: 'cid',width:80,minWidth:80,align:'center',isReadOnly:true},
							{ header: 'id', binding: 'gstId',width:'*',isReadOnly:true,visible:false},
							{ header: '熟客编号', binding: 'gstId',dataType:'Number',format:'f0',width:85,minWidth:80,align:'left',isReadOnly:true}, 
							{ header: '中文名', binding: 'gstNamec',width:100,minWidth:80,align:'left',isReadOnly:true},
							{ header: '英文名', binding: 'gstNamee',width:105,minWidth:80,align:'left',isReadOnly:true},
							{ header: '性别', binding: 'sex',width:60,minWidth:60,align:'center',isReadOnly:true}, 
							{ header: '联系电话', binding: 'mobile',width:125,minWidth:60,align:'center',isReadOnly:true},
							{ header: '证件号码', binding: 'crdId',width:190,minWidth:60,align:'center',isReadOnly:true},
							{ header: '合约单位', binding: 'namec',dataType:'Number',format:'f0',width:120,minWidth:60,align:'left',isReadOnly:true},
							{ header: '会员卡号', binding: '',width:110,minWidth:60,align:'left',isReadOnly:true},
							{ header: '状态', binding: 'status',width:78,minWidth:60,align:'center',isReadOnly:true}						
	       	],
	      
	    });
	    grid.onSelectionChanged=function(e){
        	
        };
	    //
	    initElement();
	});
	var baseFormTitle="熟客资料";
	/**
	 * 初始化页面元素
	 */
	function initElement(){
		//
		$("#searchForm input").inputmask();
		$("#dataForm input").inputmask();
		$("#otherForm input").inputmask();
		//初始化合約單位hyUnitDataBind
		hyUnitDataBind();
		//字母键盘绑定
		codeLetterEventBind();
		 $("#dataForm select,checkbox").change(inputListener);
		 $("#dataForm input,textarea").keyup(inputListener);
		 $("#dataForm input[type='checkbox']").not("input[readonly]").bind('click',inputListener);
		 //otherinputListener
		 $("#otherForm select,checkbox").change(otherinputListener);
		 $("#otherForm input,textarea").keyup(otherinputListener);
		 //
		//grid行双击事件监听
		 var host = grid.hostElement;
        //handle the click event
        host.addEventListener('dblclick', function (e) {
       	 console.log(e);
            var ht = grid.hitTest(e);
            //check if cell is clicked
            if (ht.cellType == wijmo.grid.CellType.Cell) {
            	formEdit();
            }
        });
        //客房特征
        chkAll();
	}
	function codeLetterEventBind(){
		$(".cabDiv a").click(function(){
			console.log($(this).get(0).innerText);
			doSearch($(this).get(0).innerText);
		});
	}
	/**
	 * 显示弹出框
	 */
	function showForm(){
		$(".acquaintanceDiv").fadeIn();
		$(".alertDivBg").fadeIn();
	}
	function closeForm(){
		$(".acquaintanceDiv").fadeOut();
		$(".alertDivBg").fadeOut();
	}
	function showOther(){
		$(".otherDiv").fadeIn();
		$(".alertDivBg").fadeIn();
		//加载dataForm数据
		 var data = $("#dataForm").serializeArray();
		 console.log(data);
		 setFormData2("otherForm",data);
		
	}
	/**
	 * 打开客房特征表单
	 */
	function showKeFangForm(){
		$(".featuresButton").fadeIn();
		$(".alertDivBg").fadeIn();
		$(".sonChk").each(function(){
			$(this).prop("checked",false);
		});
		//特征绑定
		var roomCharacter = parseInt($("#roomCharacter").val());
		if(NaN!=roomCharacter && roomCharacter>0){
			console.log($(".roomButtonFblock input[class*='sonChk']").length);
			$(".roomButtonFblock input[class*='sonChk']").each(function(){
				var pi=this.value;
				console.log(roomCharacter & (one << pi) != 0 ? true :false);
				$(this).prop("checked",(roomCharacter & (one << pi)) != 0 ? true :false);
			});
		}
	}
	/**
	 * 关闭客房特征表单
	 */
	function closeKeFangForm(){
		$(".featuresButton").fadeOut();
		$(".alertDivBg").fadeOut();
	}
	// 房间特征 checkbox 点击事件
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
	function limitConvert(){
		$("#limit").val(getDoubleStr($("#limit").val()));
	}
	// 保存选择的房间特征信息
	var one = parseInt(1);
	function saveCharaters(){
	    // 选择的房间特征综合
		var totalVal;
		// 循环判断选中的特征
		$(".sonChk").each(function(index, element){
			if($(element).prop("checked") == true){
				var val = $(element).val();
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
		    if($("#theStatus").attr("checked")!=undefined){
		    	  $("#roomCharacter").val(totalVal);
		    	  btnStatusTriger('1');
				  $("#fjtzChk").prop("checked",true);
		    }
		}else{
			    closeKeFangForm();
			    if($("#theStatus").attr("checked")!=undefined){
			    	 $("#roomCharacter").val(0);
			    	 btnStatusTriger('1');
					 $("#fjtzChk").prop("checked",false);
			    }
		}
	}
	/**
	 * 将其他表单序列化为数组加载到目标表单
	 * @param $form   form的ID
	 * @param jsonData  .serializeArray()
	 */
	function setFormData2($form,jsonData){
		 for(x in jsonData){
			 console.log(x);
			 console.log(jsonData[x].name);
			 var key=jsonData[x].name;
			 var value=jsonData[x].value;
			 $("#"+$form+" [name='"+key+"']").each(function(){
		            tagName = $(this)[0].tagName;
		            type = $(this).attr('type');
		            if(tagName=='INPUT'){
		                if(type=='radio'){
		                    $(this).attr('checked',$(this).val()==value);
		                }else if(type=='checkbox'){
		                        if($(this).val()==value){
		                            $(this).attr('checked',true);
		                        }
		                }else{
		                    $(this).val($.trim(value));
		                }
		            }else if(tagName=='SELECT'){
		            	console.log(this);
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
	function cloaseOther(){
		$(".otherDiv").fadeOut();
		$(".alertDivBg").fadeOut();
		
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
					console.log(data)
					//alert(JSON.stringify(data));
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
					    	  inputListener(event);
					    });  
					if(!avj==undefined){
						
					}
				}
			}
		});
	}
	/**
	 * 新增数据
	 */
	function formAdd(){
		$("#formTitle").text(baseFormTitle+"-新增");
		showForm();
		//
		$("#dataForm").get(0).reset();
		//清除除有效外的其他checkbox
		
		//
		btnStatusTriger('1');
		otherBtnTrig('1');
		inputReadonlyTrigger(false);
	}
	var formObj={};
	var gridSelRowNum=0;
	/**
	 * 查看/编辑数据
	 */
	function formEdit(id){
		if(id==undefined){
			var citem= grid.itemsSource.currentItem;
			if(null!=citem){
				id=citem.gstId;gridSelRowNum=grid.selection.row;
			}
		}
			$.ajax({
				   type: "post",
				   url: "/marketing/getFguest.do",
				   data: {'id':id},
				   dataType:'json',
				   success: function(aj){
				   	 if(aj.success){
				   		$("#formTitle").text(baseFormTitle+"-维护");
				   		showForm();
						formObj=aj.obj;
						loadFormData(aj.obj);
				   	 }else{
				   		altWaringMsg(aj.msg); 
				   	 }
				   }
			});
		
	}
	function loadFormData(formObj){
		if(null!=formObj.inDate){
			formObj.inDate=new Date(formObj.inDate).format('yyyy-MM-dd');
		}
		if(null!=formObj.birthday){
			formObj.birthday=new Date(formObj.birthday).format('yyyy-MM-dd');
		}
		if(null!=formObj.reachDate){
			formObj.reachDate=new Date(formObj.reachDate).format('yyyy-MM-dd');
		}
		if(null!=formObj.leaveDate){
			formObj.leaveDate=new Date(formObj.leaveDate).format('yyyy-MM-dd');
		}
		if(null!=formObj.operTime) formObj.operTime=new Date(formObj.operTime).format('yyyy-MM-dd hh:mm');
		console.log(formObj);
		setFormData('otherForm',formObj);
		//form数据绑定
		//setForm('dataForm',formObj);
		//setFormData('dataForm',formObj);
		setForm('dataForm',formObj);
		//loadData(formObj);
		console.log("11111111");
		//特殊数据处理
		$("#compUnit").val(formObj.unitNamec);
		$("#compId").val(formObj.compId);
		$("#compType").val(formObj.compType);
		$("#limit").val(getDoubleStr(formObj.limit));
		//
		if(null!=formObj.roomCharacter && formObj.roomCharacter>0){
			 $("#fjtzChk").prop("checked",true);
		}else{
			$("#fjtzChk").prop("checked",false);
		}
		//绑定下拉Select
		
		if(formObj.status==0){//有效
			btnStatusTriger('2');
			otherBtnTrig('2');
			inputReadonlyTrigger(false);
			//恢复特殊表单元素
			$("#theStatus").get(0).checked='checked';
		}else if(formObj.status==1){//无效
			btnStatusTriger('3');
			otherBtnTrig('3');
   	    	inputReadonlyTrigger(true);
	   	   //恢复特殊表单元素
	   	 	$("#theStatus").prop("checked",false);
		}
	}
	function bindSelect($el,$value){
		var options= $("#"+$el)[0].options;
		for(var i=0;i<options.length;i++){
			if(options[i]==$value){
				$("#"+$el)[0].selectedIndex=i;
				$("#"+$el)[0].value=$value;
				break;
			}
		}
	}
	function confrimOhterForm(){
		 var data = $("#otherForm").serializeArray();
		 console.log(data);
		//将other表单的数据更新到data表单
		setFormData2("dataForm",data);
		cloaseOther();
		btnStatusTriger('1');
		
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
	//取消，将状态设为无效
	function formDel(){
		var id = $('#gstId').val();
		if(id!=""){
			altInfMsg('确定将此熟客资料设为无效？',function(){
				$.ajax({
					url:"logicDelFguest.do",
					type:"post",
					dataType:'json',
					data:{id:id},
					success:function(data){
						if(data.success){
							closeForm();
							updateGridCell();
						}else{
							altWaringMsg(data.msg);
						}
					}
				});
			},function(){return false;});
			
		}
	}
	function updateGridCell(){
		var rowNum=grid.selection.row;
		grid.setCellData(rowNum,10,'无效');
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
            return value==""?true:text.test(value);
        },
        errorMessage : '不是日期格式',
        errorMessageKey: 'badCustomSimpleDate'
    });
	/**
	 * 表单验证
	 */
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
		if(!$("#dataForm").isValid(lang, conf, false) ) {
			console.log(errors);
			if(errors.length>0){
				altWaringMsg(errors[0].el.attr('label')+"  "+errors[0].error);
				return false;
			}
		   } else {
		     return true;
		}
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
	 * 提交表单/插入或更新数据
	 */
	function formSubmit(){
		console.log($("#dataForm").serialize());
		if(!formValidate()){return false;}
		$("#limit").val(getDoubleStr($("#limit").val()));
		var id=$("#gstId").val();
		$("#creditId").val($("#creditId").inputmask('unmaskedvalue'));
		$.ajax({
    		url:"/marketing/saveFguest.do",
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
					altWaringMsg(data.msg);
				}
			}
    	});
	}
	
	/**
	 * 表单只读切换
	 */
	function formReadonlyTrigger(r){
		if(r){
			$("#noguestData input").each(function(){
				$(this).attr('style',disable0);
				var type=$(this).attr('type');
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
			$("#noguestData select").each(function(){
				$(this).attr('style',disable0);
				$(this).attr('disabled','disabled');
			});
		}else{
			$("#noguestData input").each(function(){
				$(this).attr('style',enable0);
				var type=$(this).attr('type');
				console.log('type='+type);
				if(type=='select'){
					$(this).attr('disabled',false);
				}
				if(type=='checkbox'){
					$(this).attr('disabled',false);
				}
				$(this).removeAttr('readonly')
			});
            $("#noguestData select").each(function(){
            	$(this).attr('style',enable0);
            	$(this).attr('disabled',false);
			});
		}
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
	/**
	 * 清空查询表单
	 */
	function searchReset(){ 
		$(':input','#searchForm')  
		 .not(':button, :submit, :reset,:checkbox')  
		 .val('')  
		 //.attr('checked',false)  
		 .removeAttr('selected'); 
	}
	/**
	 * 放弃修改-恢复默认
	 */
	function formGiveUp(){
		var id = $("#gstId").val();
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
	 * 放弃修改其他信息-恢复默认
	 */
	function formGiveUpOtherForm(){
		var id = $("#gstId").val();
		//编辑中的放弃
		if(id!=""){
			showOther();
		}else{
			$("#otherForm").get(0).reset();
			//恢复特殊值
			$("#housePay").prop("checked",true);//可挂房账
			$("#freeSvc").prop("checked",true);//免服务费
		}
		
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
	function otherinputListener(event) {
	var data = $("#otherForm").serializeArray();
	// 将other表单的数据更新到data表单
	setFormData2("dataForm", data);
	otherBtnTrig('1');
	inputListener(event);
    }
	
	function otherBtnTrig(a){
		if(a=='1'){
			$("div .otherOperCls a[class*='group1']").each(function() {
				console.log("groip1:"+$(this)[0].innerHTML);
				$(this).removeAttr("disabled");
				$(this).attr('style',enable0);
				if($(this).attr('jack')!=''){
					$(this).attr('onclick',$(this).attr('jack'));
				}
				return true;
			});
			$("div .otherOperCls a[class*='group2']").each(function() {
				console.log("group2:"+$(this)[0].innerHTML);
				$(this).attr('disabled',true);
				$(this).attr('style',disable0);
				$(this).removeAttr("onclick");
				return true;
			});
		}else if(a=='2'){
			$("div .otherOperCls a[class*='group1']").each(function() {
				console.log("groip1:"+$(this)[0].innerHTML);
				$(this).attr('disabled',true);
				$(this).attr('style',disable0);
				$(this).removeAttr("onclick");
				return true;
			});
			$("div .otherOperCls a[class*='group2']").each(function() {
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
			$("div .otherOperCls a[class*='group1']").each(function() {
				console.log("groip1:"+$(this)[0].innerHTML);
				$(this).attr('disabled',true);
				$(this).attr('style',disable0);
				$(this).removeAttr("onclick");
				return true;
			});
			$("div .otherOperCls a[class*='group2']").each(function() {
				console.log("group2:"+$(this)[0].innerHTML);
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
			$("#otherForm input").each(function(){
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
			$("#otherForm select").each(function(){
				$(this).attr('style',disable0);
				$(this).attr('disabled','disabled');
			});
			//
			$(".featuresButton  input").each(function(){
				$(this).attr('style',disable0);
				var type=$(this).attr('type');
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
            $("#otherForm input").each(function(){
				$(this).attr('style',enable0);
				var type=$(this).attr('type');
				console.log('type='+type);
				if(type=='select'){
					$(this).attr('disabled',false);
				}
				if(type=='checkbox'){
					$(this).attr('disabled',false);
				}
				$(this).removeAttr('readonly')
			});
            $("#otherForm select").each(function(){
            	$(this).attr('style',enable0);
            	$(this).attr('disabled',false);
			});
            //
            $(".featuresButton  input").each(function(){
				$(this).attr('style',enable0);
				var type=$(this).attr('type');
				console.log('type='+type);
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
	/**
	 * 数据检索
	 */
	function doSearch(code){
		var formData = {};
		if(code){
			formData.codeLetter=code;
			formData.status=$('#searchForm input:radio[name=status]:checked').val();
		}else{
			formData = $("#searchForm").formToArray();
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
					altWaringMsg("请输入查询条件");
					return;
				}
			}
		}
		console.log("0000="+formData);
		array.length=0;
		$.ajax({
			url:"ajaxFguestList.do",
			type:"post",
			dataType:'json',
			data : formData,
			mimeType : "multipart/form-data",
			cache : false,
			success:function(data, textStatus, jqXHR){
				//alert(JSON.stringify(data));
				for (var i = 0; i < data.length; i++) {
					array.push({
						 cid: i+1,
						 gstId:data[i].gstId,
						 gstNamec:data[i].gstNamec,
						 gstNamee:data[i].gstNamee,
						 sex:data[i].sex=="0"?"男":"女",
						 mobile:data[i].mobile,
						 crdId:data[i].crdId,
						 bankId:data[i].bankId,
						 namec:data[i].namec,
						 notice:data[i].notice,
						 status:data[i].status==0?"有效":"无效"
					});
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