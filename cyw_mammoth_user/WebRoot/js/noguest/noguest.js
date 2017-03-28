var path = $('#spath').val();
	var grid1;
	var array=[];
	var view;
	var myDate = new Date();
	var d=myDate.getDate().toString();
	if(d.length==1){
		d="0"+d;
	}
	var nowDate = myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+d;
	
	//查詢#或者*的數據源信息
	var gridH = undefined;
	var viewH = undefined;
	
	//條件查詢的數據源信息
	var gridA = undefined;
	var viewA = undefined;
	$(function(){
		$('#creaTimeEnd').val(nowDate);
		document.getElementById("effective").checked=true; 
		view = new wijmo.collections.CollectionView(array);
	    grid1 = new wijmo.grid.FlexGrid('#noguestdiv', {
	     	  autoGenerateColumns: false,
	     	  headersVisibility:wijmo.grid.HeadersVisibility.Column,
	     	  allowDragging:wijmo.grid.AllowDragging.None,
	     	  selectionMode:'Row',
	     	  itemsSource: view,
	    	  columns: [
							{ header: '序号', binding: 'cid',width:80,minWidth:80,align:'center',isReadOnly:true},
							{ header: 'id', binding: 'id',width:'*',isReadOnly:true,visible:false},
							{ header: '非住客简称', binding: 'nogstId',width:100,minWidth:80,align:'left',isReadOnly:true}, 
							{ header: '非住客全称', binding: 'nogstName',width:120,minWidth:80,align:'left',isReadOnly:true},
							{ header: '联系人', binding: 'connector',width:80,minWidth:80,align:'left',isReadOnly:true},
							{ header: '联系手机', binding: 'mobile',width:85,minWidth:80,align:'left',isReadOnly:true}, 
							{ header: '自用', binding: 'hotelFlag',width:80,minWidth:60,align:'center',isReadOnly:true},
							{ header: '有效', binding: 'status',width:80,minWidth:60,align:'center',isReadOnly:true},
							{ header: '账号', binding: 'billId',dataType:'Number',format:'f0',width:120,minWidth:60,align:'center',isReadOnly:true},
							{ header: '余额', binding: 'balance',width:80,minWidth:60,align:'right',isReadOnly:true},
							{ header: '合约单位', binding: 'namec',width:150,minWidth:80,align:'left',isReadOnly:true},
							{ header: '单位电话', binding: 'tele',width:100,minWidth:60,align:'left',isReadOnly:true},
							{ header: '备注', binding: 'notes',width:150,minWidth:100,align:'center',isReadOnly:true},
							{ header: '创建日期', binding: 'creaTime',width:150,minWidth:100,dataType: "Date",format:'yyyy-MM-dd',isReadOnly:true}
	       	],
	      
	    });
	    grid1.onSelectionChanged=function(e){
        	//console.log('2222'+e.col+"-"+e.row);
        	//console.log('data='+grid.getCellData(e.row,e.col));
        	//console.log('id=',array[e.row].id);
        };
	    //
	    initElement();
	    accountGrid =  new wijmo.grid.FlexGrid('#noguestAccountGrid', {
   	    	autoGenerateColumns: false,
  	    	columns: [
  	         	 { header: 'setlId', binding: 'setlId',visible:false}, 
	  	         { header: 'operId', binding: 'operId',visible:false}, 
	  	         { header: 'okFlag', binding: 'okFlag',visible:false}, 
	             { header: '序号', binding: 'sortNum',width:50 }, 
	             { header: 'ID号', binding: 'ID',width:100 },
	             { header: '单号', binding: 'acco_id' ,width:100},
	             { header: '借项', binding: 'cname' ,width:100}, 
	             { header: '贷项', binding: 'sname',width:100},
	             { header: '金额', binding: 'balance', width:100 },
	             { header: '服务费', binding: 'svc_charge',width:100 },
	             { header: '时间', binding: 'oper_time',width:100 },
	             { header: '外币', binding: 'foreignm',width:100 },
	             { header: '币种', binding: 'code_namec',width:100 },
	             { header: '状态', binding: 'status',width:100 },
	             { header: '操作员', binding: 'oper_name',width:100 },
	             { header: '摘要', binding: 'ext_name',width:100 },
	             { header: '备注', binding: 'notes',width:100 },
	             { header: '结账号', binding: 'payNum',width:100 }
     	 	],
     	 	headersVisibility:wijmo.grid.HeadersVisibility.Column,
     	 	selectionMode:'ListBox',
     	 	isReadOnly:true
 	 	});
	    $("#nogst_accrefresh_btn").unbind('click');
	    $("#nogst_accrefresh_btn").bind('click',function(){
	    	loadNoguestAccount();
	    });
	});
	
	function initElement(){
		//--非住客列表
		 $("#noguestform input").inputmask();
		 //var theBillId=$("#theBillId").inputmask("9{2,20}");
		//theBillId=$("#theBillId").inputmask("yyyy-mm-dd");
		//--非住客详情-非住客资料
		//var nogst_id=$("#nogst_id").inputmask("a{2,10}");
		var mobile=$("#mobile").inputmask({mask:"9{11}",placeholder:""});//手机
		var fax=$("#fax").inputmask({mask:"9{7,12}",placeholder:""});//传真
		var phone = $("#phone").inputmask({mask:"9{7,11}",placeholder:""});//固定电话
//		var email=$("#email").inputmask('Regex', { regex: "[a-zA-Z0-9._%-]+@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,4}" });//邮箱
		//--非住客资料--账目资料
		//付款截止日期
		var payDate=$("#payDate").inputmask("yyyy-mm-dd");
		//信用卡号
		var theBankno=$("#theBankno").inputmask({ mask: "9999 9999 9999 9999 9{0,3}",placeholder:""});
		var limit=$("#limit").inputmask("9{0,10}.9{0,2}");
		hyUnitDataBind();
		codeLetterEventBind();
		 $("#noguestData select,checkbox").change(inputListener);
		 $("#noguestData input,textarea").keyup(inputListener);
		 $("#noguestData input[type='checkbox']").not("input[readonly]").bind('click',inputListener);
		 //grid行双击事件监听
		 var host = grid1.hostElement;
         //handle the click event
         host.addEventListener('dblclick', function (e) {
        	 console.log(e);
             var ht = grid1.hitTest(e);
             //check if cell is clicked
             if (ht.cellType == wijmo.grid.CellType.Cell) {
            	 nogeustdetail();
             }
         });

		
		
	}
	function codeLetterEventBind(){
		$(".cabDiv a").click(function(){
			console.log($(this).get(0).innerText);
			doSearch($(this).get(0).innerText);
		});
	}
	function hyUnitDataBind(avj){
		var emails = [  
		                { id:10011,namee: "XianCunYouWang", namec: "西安村游网",taType:"T",compId:'cyw' },  
		                { id:10012,namee: "HuaWeiGongSi", namec: "华为公司",taType:"C",compId:'hw' },  
		                { id:10013,namee: "ZhongXingGongSi", namec: "中兴公司",taType:"C",compId:'zte' },  
		                { id:10014,namee: "XianHauShangWang", namec: "西安华商网" ,taType:"T",compId:'hsw'}
	                ]; 
		var tadVo={
			symbol:1	
		};
		$.ajax({
			url:path+"/marketing/searchTadoclist.do",
			type:"post",
			dataType:'json',
			data:tadVo,
			success:function(data){
				if(data!=undefined && data!=null){
					console.log(data)
					//alert(JSON.stringify(data));
					$('#theCompany').autocomplete(data,   
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
					    	  $("#company_id").val(row.id);
					    	  $("#ta_type").val(row.taType);
					    });  
					if(!avj==undefined){
						
					}
				}
			}
		});
	}
	
	//清空条件
	function clearCond(){
		 var effective=$("#effective").attr("checked");
		 var uneffective=$("#uneffective").attr("checked");
		$('#noguestform').get(0).reset();
		$('#creaTimeEnd').val(nowDate);
		document.getElementById("effective").checked=effective; 
		document.getElementById("uneffective").checked=uneffective; 
	}
	function addEditBtnTrigg(){
		var id = $('#id').val();
		if(id==""){//新增
			$("#theDel").get(0).style="color: grey;cursor:not-allowed;";
			 $("#theDel").unbind('click',delNoguest);
		}else{//详情
			$("#theDel").get(0).style="cursor:pointer;color:inherit;";
			 //$("#theDel").bind('click',delNoguest);
		}
	}
	//放弃操作
	function giveUp(){
		var id = $('#id').val();
		//编辑中的放弃
		if(id!=""){
			nogeustdetail();
		}else{
			//$("#noguestData").clearForm();
			$("#noguestData").get(0).reset();
		}
	}
	//取消，将状态设为无效
	function delNoguest(){
		var id = $('#id').val();
		if(id!=""){
			altInfMsg('确定将此非住客资料设为无效？',function(){
				$.ajax({
					url:path+"/noguest/updateStatusCancle.do",
					type:"post",
					dataType:'json',
					data:{id:id},
					success:function(data){
						if(data.isSuccess){
							document.getElementById("noguestStatus").checked = false;
							exportNoguest();
							//refreshGrid('2');
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
		var rowNum=grid1.selection.row;
		grid1.setCellData(rowNum,7,'否');
	}
	//退出操作
	function exportNoguest(){
		$(".alertDivBg").fadeOut();
		$(".nonResidentDiv").fadeOut();
		$("#noguestData").clearForm();
		$('#id').val("");
	}
	//非住客詳情
	function nogeustdetail(){
		//var noguestId=array[grid1.selection.row].id;
		var citem= grid1.itemsSource.currentItem;
		if(!citem) return;
		var noguestId=citem.id;
		console.log("noguestId="+noguestId);
		if(noguestId!=undefined){
			$.ajax({
				url:path+"/noguest/selectSignleNoguest.do",
				type:"post",
				dataType:'json',
				async:false,
				data:{noguestId:noguestId},
				success:function(result){
					if(result.success){
						var data =result.obj;
						if(null!=data.creaTime) data.creaTime=formatterDate(data.creaTime,'yyyy-MM-dd hh:mm');
						if(null!=data.modiTime) data.modiTime=formatterDate(data.modiTime,'yyyy-MM-dd hh:mm');
						console.log(data);
						//setForm('noguestData',data);
						setFormData('noguestData',data);
						loadNoGuestCustomData(data);
						if(data.hotel_flag){
							document.getElementById("hotel_flag").checked = true;
						}
						var tempstatus = "有效";
						if(data.status==0){
							tempstatus = "有效";
							document.getElementById("noguestStatus").checked = true;
							$('#noguestStatus').val("0");
						}else{
							tempstatus = "无效";
							document.getElementById("noguestStatus").checked = false;
							$('#noguestStatus').val("1");
						}
						$('#titleId').text("非住客资料维护("+tempstatus+")");
						$('#id').val(data.id);
						$('#company_id').val(data.comp_id);
						$('#ta_type').val(data.compType);
						$('#typeData').val(1);//ID位typeData状态为0新增，为1修改
						$("#payDate").val(new Date(data.payDate).format('yyyy-MM-dd'));
						loadNoGuestForm(data);
						//copyData(data);
						$("#limit").val(changeTwoDecimal_f(data.nLimit));
						$(".alertDivBg").fadeIn();
						$("#nonResidentDiv").fadeIn();
						$(".userTab1").click();
						//
						//addEditBtnTrigg();
					}
				}
			});
			
		}else{
			altWaringMsg("请查询并且选中一条数据");
		}
		
	}
	
	//非住客账目
	function loadNoguestAccount(){
		var noguestId=$("#id").val();
		if(noguestId){
			$.ajax({
				url:path+"/noguest/loadNoguestAccount.do",
				type:"post",
				dataType:'json',
				async:false,
				data:{noguestId:noguestId},
				success:function(data){
						setFormData("noguestAccountForm", data.entity);
						var view = new wijmo.collections.CollectionView(data.bills);
						accountGrid.itemsSource = view;
					}
				});
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
	//
	function nogeustdetail2(noguestId){
		//var noguestId=array[grid1.selection.row].id;
		if(noguestId==undefined) return false;
		console.log("noguestId="+noguestId);
		if(noguestId!=undefined){
			$.ajax({
				url:path+"/noguest/selectSignleNoguest.do",
				type:"post",
				dataType:'json',
				data:{noguestId:noguestId},
				success:function(result){
					if(result.success){
						var data = result.obj;
						console.log(data);
						//setForm('noguestData',data);
						setFormData('noguestData',data);
						loadNoGuestCustomData(data);
						if(data.hotel_flag){
							document.getElementById("hotel_flag").checked = true;
						}
						var tempstatus = "有效";
						if(data.status==0){
							tempstatus = "有效";
							document.getElementById("noguestStatus").checked = true;
							$('#noguestStatus').val("0");
						}else{
							tempstatus = "无效";
							document.getElementById("noguestStatus").checked = false;
							$('#noguestStatus').val("1");
						}
						$(".alertDivBg").fadeIn();
						$(".nonResidentDiv").fadeIn();
						$('#titleId').text("非住客资料维护("+tempstatus+")");
						$('#id').val(data.id);
						$('#company_id').val(data.comp_id);
						$('#ta_type').val(data.compType);
						$('#typeData').val(1);//ID位typeData状态为0新增，为1修改
						$("#payDate").val(new Date(data.payDate).format('yyyy-MM-dd'));
						$("#limit").val(changeTwoDecimal_f(data.nLimit));
						loadNoGuestForm(data);
						//copyData(data);
						//
						//addEditBtnTrigg();
					}
				}
			});
			
			
		}else{
			altWaringMsg("请查询并且选中一条数据");
		}
		
	}
	function loadNoGuestForm(obj){
		$("#theCompany").val(obj.unitNamec);
		$("#company_id").val(obj.comp_id);
   	    $("#ta_type").val(obj.compType);
   	    //pay_id
   	    $payopts=$("#pay_id")[0].options;
   	    for(var i=0;i<$payopts.length;i++){
   	    	console.log("11-"+$.trim($payopts[i].value)+"-11")
   	    	if($.trim($payopts[i].value)==obj.payId){
   	    		$("#pay_id")[0].selectedIndex=i;
   	    		break;
   	    	}
   	    }
   	    //hotel_flag
   	    $("#hotel_flag").attr("checked", obj.hotel_flag);
   	    if(obj.hotel_flag){
   	    	$("#hotel_flag")[0].checked=true;
   	    }
   	    console.log(typeof obj.hotel_flag);
   	    console.log(typeof obj.status);
   	    if(obj.status==1){//无效
   	    	btnStatusTriger('3');
   	    	inputReadonlyTrigger(true);
   	    }else{//有效
   	    	btnStatusTriger('2');
   	    	inputReadonlyTrigger(false);
   	    }
   	    
 	
	}
	function inputReadonlyTrigger(r){
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
				$("#theCompany").css("width","84%");
				$("#notes").css("width","99%");
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
	function refreshGrid(type){
		if(type==undefined){return false;}
		if(type=='1'){
			doSearch('#');
		}else if(type=='2'){
			doSearch();
		}
	}
	//条件查询
	function doSearch(code){
		var formData = {};
		if(code){
			formData.codeLetter=code;
			var effective=$("#effective").attr("checked");
			var uneffective=$("#uneffective").attr("checked");
			var chkStat=0;
			if(effective!=undefined && effective=="checked"){
				chkStat=$("#effective").val();
			}
			if(uneffective!=undefined && uneffective=="checked"){
				chkStat=$("#uneffective").val();
			}
			formData.chkStat=chkStat;
		}else{
			formData = $("#noguestform").formToArray();
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
		//alert(JSON.stringify(formData));
		console.log("0000="+formData);
		array.length=0;
		$.ajax({
			url:path+"/noguest/selectNoguestCond.do",
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
						 id:data[i].id,
						 nogstId:data[i].nogst_id,
						 nogstName:data[i].nogst_name,
						 connector:data[i].connector,
						 mobile:data[i].mobile,
						 hotelFlag:data[i].hotel_flag,
						 status:data[i].status,
						 billId:data[i].bill_id,
						 balance:data[i].balance,
						 namec:data[i].namec,
						 tele:data[i].tele,
						 notes:data[i].notes,
						 creaTime:data[i].crea_time==undefined?null:new Date(data[i].crea_time)
					});
				}
				console.log(array);
				//
				view = new wijmo.collections.CollectionView(array);
				console.log(grid1);
				grid1.itemsSource = view;
				grid1.refresh();
			}
		});
	}
	//条件查询
	function selectCond(){
		if(typeof gridA != "undefined"){
			gridA.dispose();
			gridA = undefined;
			viewA = undefined;
		}
		
		if(typeof grid1 != "undefined"){
			grid1.dispose();
			grid1 = undefined;
		}
		if(typeof gridH != "undefined"){
			gridH.dispose();
			gridH = undefined;
			viewH = undefined;
		}
		$.ajax({
			url:path+"/noguest/selectNoguestCond.do",
			type:"post",
			dataType:'json',
			data:$("#noguestform").serialize(),
			success:function(data){
				for (var i = 0; i < data.length; i++) {
					array.push({
						 cid: i+1,
						 id:data[i].id,
						 nogstId:data[i].nogst_id,
						 nogstName:data[i].nogst_name,
						 connector:data[i].connector,
						 mobile:data[i].mobile,
						 hotelFlag:data[i].hotel_flag,
						 status:data[i].status,
						 billId:data[i].bill_id,
						 balance:data[i].balance,
						 namec:data[i].namec,
						 tele:data[i].tele,
						 notes:data[i].notes,
						 creaTime:data[i].crea_time
					});
				}
				console.log(array);
				//
				view = new wijmo.collections.CollectionView(array);
				//alert(grid1);
				grid1.itemsSource = view;
				grid1.refresh();
			}
		});
	}
	
	//设置日期格式及数据显示
	function setGridData(data) {
		for ( var i = 0; i < data.length; i++) {
			data[i].crea_time = new Date(data[i].crea_time);
		}
	}
	//加载非住客资料Form的个性化数据
	function loadNoGuestCustomData(data){
		$("#theBankno").val(data.bankno);
		$("#nogst_id").val(data.nogstId);
		$("#limit").val(getDoubleStr(data.nLimit));
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
		if(!$("#noguestData").isValid(lang, conf, false) ) {
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
	function saveData(){
		if(!formValidate()){return false;}
		//
		var banknoRaw= $("#theBankno").inputmask('unmaskedvalue')
		$("#bankno").val(banknoRaw);
		console.log($("#noguestData").serialize());
		var tempNum = $('#typeData').val();//0新增，为1修改
		if(tempNum=="0"){
			$.ajax({
	    		url:path+"/noguest/insertnoguest.do",
	    		type:"post",
				dataType:'json',
				data:$("#noguestData").serialize(),
				success:function(data){
					if(data.success){
						//
						console.log(data);
						//btnStatusTriger('2');
						refreshGrid('1');
						nogeustdetail2(data.obj);
					}else{
						altWaringMsg(data.msg);
					}
				}
	    	});
		}else{
			$.ajax({
				url:path+"/noguest/updateSigleNoguest.do",
				type:"post",
				dataType:'json',
				data:$("#noguestData").serialize(),
				success:function(data){
					if(data.isSuccess){
                        //
						btnStatusTriger('2');
						refreshGrid('2');
					}else{
						altWaringMsg(data.msg);
					}
				}
			});
		}
		
		
	}
	function heyue(e){
		var keyCode = e.keyCode || e.which;
	    switch (keyCode) {
	        case 13:
	        	$.ajax({
	        		url:path+"/noguest/selectcompany.do",
	        		type:"post",
	    			dataType:'json',
	    			data:{comp_type:$('#comp_type').val()},
	    			success:function(data){
	    				$("#comptbid").empty();
	    				$(".searchDiv").fadeIn();
	    				for(var i=0;i<data.length;i++){
	    					$("#comptbid").append("<li id="+data[i].id+" value="+data[i].ta_type+">"+data[i].namec+"</li>");
	    					
	    				}
	    				$(".searchDiv li").click(function(){
    						$(".searchDiv").fadeOut();
    						$("#comp_type").val($(this).text());
    					});
	    				var ul = document.getElementById("comptbid");  
	    				var lis = ul.getElementsByTagName("li");
	    				for(var i = 0; i < lis.length;i++){  
	    				  lis[i].onclick=function(){  
	    				     //this就代表的是相应的li
	    				     $('#ta_type').val(document.getElementById(this.id).getAttribute("value"));
	    				     $('#company_id').val(this.id);
	    				  };
	    				}
	    				
	    			}
	        	});
	            break;
	        default:
	            break;
	    }
	}

	/**
	*@描述：合约单位下拉框自动补全
	*/
	$(function()
	{
		$.ajax({
			url:path+"/noguest/selectcompany.do"
			,type:"post"
			,dataType:'json'
			,data:{comp_type:""}
			,success:function(data)
			{
				var selArray = new Array();//下拉框数据
				for(var i=0;i<data.length;i++)
				{
					var arr = data[i];
					selArray.push(arr['namec']);
				}
				//搜索下拉框设置
				$('#no_guest_comp_type').autocomplete({
				hints: selArray//下拉框的数组
				,width: 200//input的长度
				,height: 30//input的宽度
				,name:'comp_type'//扩展的 input名称
				,showButton:false//是否显示搜索按钮
				,placeholder:''
				,onSubmit: function(text)
					{
						//设置 ta_type 和 company_id
						$.each(data,function(index, n) {
							if(n.namec===text)
							{
								$('#ta_type').val(n.ta_type);
								$('#company_id').val(n.id);
							}
						});
					}
				});
				//主界面快速选择
				$('#no_guest_comp_type_compId').autocomplete({
					hints: selArray//下拉框的数组
					,width: 200//input的长度
					,height: 30//input的宽度
					,name:'comp_type'//扩展的 input名称
					,showButton:false//是否显示搜索按钮
					,placeholder:''
					,onSubmit: function(text)
						{
							//设置 ta_type 和 company_id
							$.each(data,function(index, n) {
								if(n.namec===text)
								{
									$('#ta_type').val(n.ta_type);
									$('#company_id').val(n.id);
								}
							});
						}
					});
				//样式操作
				var comp_type_obj = $("[name='comp_type']");
				comp_type_obj.attr("id","comp_type");
				comp_type_obj.removeClass('autocomplete-input');
				comp_type_obj.addClass('input');
				var autocomplete = $(".autocomplete-container");
				autocomplete.removeClass('autocomplete-container');
				autocomplete.height(30);
			}	
		});
	});

	//新增非住客资料
	function addGuest(){//ID位typeData状态为0新增，为1修改
		$("#noguestData").clearForm();
		$(".alertDivBg").fadeIn();
		$(".nonResidentDiv").fadeIn();
		$('#titleId').text("非住客资料(新增)");
		$('#typeData').val(0);
		document.getElementById("noguestStatus").checked = true;
		//addEditBtnTrigg();
		btnStatusTriger('1');
		/**解决bug#1913 思小鹏**/
		inputReadonlyTrigger(false);
		/**解决bug#1913 思小鹏**/
		$("#pay_id")[0].selectedIndex=0;
	}

	$(function(){
		/*遮罩层DIV高度*/
		$(".alertDivBg").css("height",$(document).height());     
        $(".alertDivBg").css("width",$(document).width());  
		/*遮罩层DIV高度*/
		
		/*小键盘点击切换背景*/
		$(".cabDiv a").click(function(){
			$(".cabDiv a").removeClass("pointCab");
			$(this).addClass("pointCab");
		});
		/*小键盘点击切换背景END*/
		/*table点击换行颜色*/
		
		$(".tabChangBg tr").click(function(){
			$(".tabChangBg tr td").removeClass("thisTrTd");
			$(this).find("td").addClass("thisTrTd");
		});
		/* /table点击换行颜色*/
		/*关闭弹出1*/
		$(".hideDivOne").click(function(){
			$("#noguestData").clearForm();
			$('#id').val("");
			$(".alertDivBg").fadeOut();
			$(".nonResidentDiv").fadeOut();
		});
		/*关闭弹出1*/
		/*弹出层内TAB页切换*/
		$(".userTab1").click(function(){
			$(".userDetails").show();
			$(".userCatalog").hide();
			$(".userTab1").addClass("point");
			$(".userTab2").removeClass("point");
		});
		$(".userTab2").click(function(){
			if($("#id").val()==''){return false;}
			loadNoguestAccount();
			$(".userDetails").hide();
			$(".userCatalog").show();
			$(".userTab1").removeClass("point");
			$(".userTab2").addClass("point");
		});
		/* /弹出层内TAB页切换*/
		
		
		/*房价列表二级弹出*/
		$("#pricesList").click(function(){
			$(".pricesList").fadeIn();
			$(".alertDivBg2").fadeIn();
		});
		$("#otherInformation").click(function(){
			$(".otherDiv").fadeIn();
			$(".alertDivBg2").fadeIn();
		});
		$("#changeRoom").click(function(){
			$(".changeRoomDiv").fadeIn();
			$(".alertDivBg2").fadeIn();
		});
		$(".closeAlert").click(function(){
			$(".pricesList").fadeOut();
			$(".alertDivBg2").fadeOut();
			$(".splitInfoDiv").fadeOut();
			$(".otherDiv").fadeOut();
			$(".changeRoomDiv").fadeOut();
			$(".depositDiv").fadeOut();
			$(".receivablesDiv").fadeOut();
			$(".LeavingHotelDiv").fadeOut();
		});
		/*分账设置*/
		$("#split").click(function(){
			$(".alertDivBg2").fadeIn();
			$(".splitInfoDiv").fadeIn();
		});
		/*房价列表二级弹出END*/
		
		/*押金*/
		$("#deposit").click(function(){
			$(".alertDivBg2").fadeIn();
			$(".depositDiv").fadeIn();
		});
		/*押金*/
		/*收款*/
		/*收款*/
		/*离店*/
		$("#LeavingHotel").click(function(){
			$(".alertDivBg2").fadeIn();
			$(".LeavingHotelDiv").fadeIn();
		});
		/*离店*/
	});
	
	function loadData(jsonStr){
	    var obj = eval("("+jsonStr+")");
	    var key,value,tagName,type,arr;
	    for(x in obj){
	        key = x;
	        value = obj[x];
	         
	        $("[name='"+key+"'],[name='"+key+"[]']").each(function(){
	            tagName = $(this)[0].tagName;
	            type = $(this).attr('type');
	            if(tagName=='INPUT'){
	                if(type=='radio'){
	                    $(this).attr('checked',$(this).val()==value);
	                }else if(type=='checkbox'){
	                    arr = value.split(',');
	                    for(var i =0;i<arr.length;i++){
	                        if($(this).val()==arr[i]){
	                            $(this).attr('checked',true);
	                            break;
	                        }
	                    }
	                }else{
	                    $(this).val(value);
	                }
	            }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
	                $(this).val(value);
	            }
	             
	        });
	    }
	}
	function copyData(obj){
		if(obj==undefined) return false;
	    //var obj = eval("("+jsonStr+")");
	    var key,value,tagName,type,arr;
	    for(x in obj){
	        key = x;
	        value = obj[x];
	        var $el=$("#f_"+key+"");console.log($el);
	        if($el==undefined) continue;
	        //
	        console.log($el[0]);
	        if($el[0]==undefined) continue;
	        tagName = $el[0].tagName;
            type = $el.attr('type');
            if(tagName=='INPUT'){
                if(type=='radio'){
                	$el.attr('checked',$(this).val()==value);
                }else if(type=='checkbox'){
                    arr = value.split(',');
                    for(var i =0;i<arr.length;i++){
                        if($$el.val()==arr[i]){
                            $$el.attr('checked',true);
                            break;
                        }
                    }
                }else{
                	$el.val(value);
                }
            }else if(tagName=='SELECT' || tagName=='TEXTAREA'){
            	$el.val(value);
            }
	    }
	}
	function formClear(){ 
		$(':input','#noguestData')  
		 .not(':button, :submit, :reset')  
		 .val('')  
		 .attr('checked',false)  
		 .removeAttr('selected'); 
		//恢复特殊表单元素
		$("#noguestStatus").get(0).checked='checked';
		$("#pay_id")[0].selectedIndex=0;
		btnStatusTriger('1');
	}
	
	var disable0='color: grey;cursor:not-allowed;';
	var enable0='cursor:auto;color:inherit;';
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
	
	function changeTwoDecimal_f(x) {
		var f_x = parseFloat(x);
		if (isNaN(f_x)) {
			//alert('function:changeTwoDecimal->parameter error');
			return false;
		}
		var f_x = Math.round(x * 100) / 100;
		var s_x = f_x.toString();
		var pos_decimal = s_x.indexOf('.');
		if (pos_decimal < 0) {
			pos_decimal = s_x.length;
			s_x += '.';
		}
		while (s_x.length <= pos_decimal + 2) {
			s_x += '0';
		}
		return s_x;
	}
	
	