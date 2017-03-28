//将form中的值转换为键值对。
function getFormJson(frm) {
   var o = {};
   var a = $(frm).serializeArray();
   $.each(a, function () {
       if (o[this.name] !== undefined) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
} else {
   o[this.name] = this.value || '';
       }
   });

   return o;
}
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
}; 
if (/(y+)/.test(format)) { 
format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); 
} 
for (var k in o) { 
if (new RegExp("(" + k + ")").test(format)) { 
format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)); 
} 
} 
return format; 
}; 
/** 
*转换日期对象为日期字符串 
* @param date 日期对象 
* @param isFull 是否为完整的日期数据, 
* 为true时, 格式如"2000-03-05 01:05:04" 
* 为false时, 格式如 "2000-03-05" 
* @return 符合要求的日期字符串 
*/ 
function getSmpFormatDate(date, isFull) { 
var pattern = ""; 
if (isFull == true || isFull == undefined) { 
pattern = "yyyy-MM-dd hh:mm:ss"; 
} else { 
pattern = "yyyy-MM-dd"; 
} 
return getFormatDate(date, pattern); 
} 
/** 
*转换当前日期对象为日期字符串 
* @param date 日期对象 
* @param isFull 是否为完整的日期数据, 
* 为true时, 格式如"2000-03-05 01:05:04" 
* 为false时, 格式如 "2000-03-05" 
* @return 符合要求的日期字符串 
*/ 
function getSmpFormatNowDate(isFull) { 
return getSmpFormatDate(new Date(), isFull); 
} 
/** 
*转换long值为日期字符串 
* @param l long值 
* @param isFull 是否为完整的日期数据, 
* 为true时, 格式如"2000-03-05 01:05:04" 
* 为false时, 格式如 "2000-03-05" 
* @return 符合要求的日期字符串 
*/ 
function getSmpFormatDateByLong(l, isFull) { 
return getSmpFormatDate(new Date(l), isFull); 
} 
/** 
*转换long值为日期字符串 
* @param l long值 
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss 
* @return 符合要求的日期字符串 
*/ 
function getFormatDateByLong(l, pattern) { 
return getFormatDate(new Date(l), pattern); 
} 
/** 
*转换日期对象为日期字符串 
* @param l long值 
* @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss 
* @return 符合要求的日期字符串 
*/ 
function getFormatDate(date, pattern) { 
if (date == undefined) { 
date = new Date(); 
} 
if (pattern == undefined) { 
pattern = "yyyy-MM-dd hh:mm:ss"; 
} 
return date.format(pattern); 
} 
/**
 * 表单数据加载
 * @param data(可以json字符串,或转换后的object)
 */
function setForm(formid,data) {
	var obj;
	if(typeof data == 'string'){
		obj = JSON.parse(data);
	}else if(typeof data=='object'){
		obj = data;
	}
	var key, value, tagName, type, arr;
	for (x in obj) {
		key = x;
		value = $.trim(obj[x]);
			$("form[id='"+formid+"']  [name='" + key + "']").each(function() {
				tagName = $(this)[0].tagName;
				type = $(this).attr('type');
				if (tagName == 'INPUT') {
					if (type == 'radio') {
						$(this).attr('checked', $(this).val() == value);
					} else if (type == 'checkbox') {
//						if(value){
//							arr = value.split(',');
//							for ( var i = 0; i < arr.length; i++) {
//								if ($(this).val() == arr[i]) {
//									$(this).attr('checked', true);
//									break;
//								}
//							}
//						}
					} else {
						$(this).val(value);
						//$(this).attr("defaultValue",value);
					}
				} else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
					$(this).val(value);
				}

			});
	}
}
/**
 * Json填充到Form中
 * */
function setJSONToForm($form,json){
	var jsonObj = json;
	if (typeof json === 'string') {
		jsonObj = $.parseJSON(json);
	}
	for (var key in jsonObj) {  //遍历json字符串
		var objtype = jsonObjType(jsonObj[key]); // 获取值类型
		if (objtype === "array") { //如果是数组，一般都是数据库中多对多关系
			var obj1 = jsonObj[key];
			for (var arraykey in obj1) {
				 var arrayobj = obj1[arraykey];
				 for (var smallkey in arrayobj) {
			          setCkb(key, arrayobj[smallkey]);
			          break;
			     }
				 
			}
		}else if(objtype === "object"){  //如果是对象，啥都不错，大多数情况下，会有 xxxId 这样的字段作为外键表的id
			
		}else if(objtype === "string"){  ////如果是字符串
			var str = jsonObj[key];
		    var date = new Date(str);
	        if(date.getDay()) {  //这种判断日期是本人懒，不想写代码了，大家慎用。
	        	//$("[name=" + key + "]", $form).val(date.format("yyyy-MM-dd hh:mm:ss"));
	        	$("[name=" + key + "]", $form).val(jsonObj[key]);
	        	continue;
	        }
	        
	        var tagobjs = $("[name=" + key + "]", $form);
	        if ($(tagobjs[0]).attr("type") == "radio") {//如果是radio控件 
	            $.each(tagobjs, function (keyobj,value) {
	              if ($(value).attr("val") == jsonObj[key]) {
	                value.checked = true;
	              }
	            });
	            continue;
	        }
	        
	        
	        $("[name=" + key + "]", $form).val(jsonObj[key]);
		}else if(objtype === "boolean"){
			 ////////////////
			 setCkb(key,jsonObj[key]);
		}else{
			//其他的直接赋值
			$("[name=" + key + "]", $form).val(jsonObj[key]);
		}
	}
}

//判断类型
function jsonObjType(obj) {
	  if (typeof obj === "object") {
	    var teststr = JSON.stringify(obj);
	    if (teststr[0] == '{' && teststr[teststr.length - 1] == '}') return "class";
	    if (teststr[0] == '[' && teststr[teststr.length - 1] == ']') return "array";
	  }
	  return typeof obj;
}
function setCkb(name, value) {
	//alert(name + " " + value);
	//$("[name=" + name + "][value=" + value + "]").attr("checked", "checked");  不知为何找不到具体标签;
	$("[name=" + name + "][value=" + value + "]").attr("checked", "checked");
}

//填充一个属性对应多个checkbox//
function fillckb(name, json) {
	  var jsonObj = json;
	  if (typeof json === 'string') {
	    jsonObj = $.parseJSON(json);
	  }
	  var str = jsonObj[name];
	  if (typeof str === "string") {
	    var array = str.split(",");
	    $.each(array, function (key, value) {
	      setCkb(name, value);
	    });
	  }
	}



/**
 *  json对象合并的方法
 *  用法如下：
 *  var a ={"a":"1","b":"2"}
	var b ={"c":"3","d":"4","e":"5"}
	var c = mergeJSON({}, [a,b]);
	alert(c.a); 
 * */
function mergeJSON(des, src, override){
    if(src instanceof Array){
        for(var i = 0, len = src.length; i < len; i++)
        	mergeJSON(des, src[i], override);
    }
    for( var i in src){
        if(override || !(i in des)){
            des[i] = src[i];
        }
    }
    return des;
}

/**
 * js端转换日期
 * @param date1 输入的日期值，
 * @param formatter 日期格式
 * @returns
 */
function formatterDate(date1,formatter) {
	var date = new Date(date1);
	 //console.debug(typeof date);
	 var str = formatter;   
     var Week = ['日','一','二','三','四','五','六'];  

     str=str.replace(/yyyy|YYYY/,date.getFullYear());   
     str=str.replace(/yy|YY/,(date.getYear() % 100)>9?(date.getYear() % 100).toString():'0' + (date.getYear() % 100));   

     str=str.replace(/MM/,(date.getMonth() + 1)>9?(date.getMonth() + 1).toString():'0' + (date.getMonth() + 1));   
     str=str.replace(/M/g,(date.getMonth() + 1));   

     str=str.replace(/w|W/g,Week[date.getDay()]);   

     str=str.replace(/dd|DD/,date.getDate()>9?date.getDate().toString():'0' + date.getDate());   
     str=str.replace(/d|D/g,date.getDate());   

     str=str.replace(/hh|HH/,date.getHours()>9?date.getHours().toString():'0' + date.getHours());   
     str=str.replace(/h|H/g,date.getHours());   
     str=str.replace(/mm/,date.getMinutes()>9?date.getMinutes().toString():'0' + date.getMinutes());   
     str=str.replace(/m/g,date.getMinutes());   

     str=str.replace(/ss|SS/,date.getSeconds()>9?date.getSeconds().toString():'0' + date.getSeconds());   
     str=str.replace(/s|S/g,date.getSeconds());   

     return str;    
}
/**
 * 判断字符串是否为空
 * 
 * "" --> true
 * undefined --> true
 * " " --> true
 * 
 */ 

function isNull(str) {
	if (typeof(str) == "undefined") {
		return true;
	}else if (str == ""&&"0"!=str) {
		return true;
	}
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

/**
 * 删除数组中指定的元素
 * @param arr
 * @param val
 */
function removeByValue(arr, val) {
  for(var i=0; i<arr.length; i++) {
    if(arr[i] == val) {
      arr.splice(i, 1);
      break;
    }
  }
}
/**
 * 数组去重复
 * @param arr
 * @returns {Array}
 */
function arrayUnique(arr) {
    var result = [], hash = {};
    for (var i = 0, elem; (elem = arr[i]) != null; i++) {
        if (!hash[elem]) {
            result.push(elem);
            hash[elem] = true;
        }
    }
    return result;
}

/*  
 * MAP对象，实现MAP功能  
 *  
 * 接口：  
 * size()     获取MAP元素个数  
 * isEmpty()    判断MAP是否为空  
 * clear()     删除MAP所有元素  
 * put(key, value)   向MAP中增加元素（key, value)   
 * remove(key)    删除指定KEY的元素，成功返回True，失败返回False  
 * get(key)    获取指定KEY的元素值VALUE，失败返回NULL  
 * element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL  
 * containsKey(key)  判断MAP中是否含有指定KEY的元素  
 * containsValue(value) 判断MAP中是否含有指定VALUE的元素  
 * values()    获取MAP中所有VALUE的数组（ARRAY）  
 * keys()     获取MAP中所有KEY的数组（ARRAY）  
 *  
 * 例子：  
 * var map = new Map();  
 *  
 * map.put("key", "value");  
 * var val = map.get("key")  
 * ……  
 *  
 */   
function Map() {   
    this.elements = new Array();   
     
    //获取MAP元素个数   
    this.size = function() {   
        return this.elements.length;   
    };   
     
    //判断MAP是否为空   
    this.isEmpty = function() {   
        return(this.elements.length < 1);   
    } ; 
     
    //删除MAP所有元素   
    this.clear = function() {   
        this.elements = new Array();   
    };   
     
    //向MAP中增加元素（key, value)    
    this.put = function(_key, _value) {   
        this.elements.push( {   
            key : _key,   
            value : _value   
        });   
    };   
     
    //删除指定KEY的元素，成功返回True，失败返回False   
    this.remove = function(_key) {   
        var bln = false;   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].key == _key) {   
                    this.elements.splice(i, 1);   
                    return true;   
                }   
            }   
        } catch(e) {   
            bln = false;   
        }   
        return bln;   
    };   
     
    //获取指定KEY的元素值VALUE，失败返回NULL   
    this.get = function(_key) {   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].key == _key) {   
                    return this.elements[i].value;   
                }   
            }   
        } catch(e) {   
            return null;   
        }   
    };   
     
    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL   
    this.element = function(_index) {   
        if(_index < 0 || _index >= this.elements.length) {   
            return null;   
        }   
        return this.elements[_index];   
    };   
     
    //判断MAP中是否含有指定KEY的元素   
    this.containsKey = function(_key) {   
        varbln = false;   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].key == _key) {   
                    bln = true;   
                }   
            }   
        } catch(e) {   
            bln = false;   
        }   
        return bln;   
    };   
     
    //判断MAP中是否含有指定VALUE的元素   
    this.containsValue = function(_value) {   
        var bln = false;   
        try{   
            for(i = 0; i < this.elements.length; i++) {   
                if(this.elements[i].value == _value) {   
                    bln = true;   
                }   
            }   
        } catch(e) {   
            bln = false;   
        }   
        return bln;   
    };   
     
    //获取MAP中所有VALUE的数组（ARRAY）   
    this.values = function() {   
        var arr = new Array();   
        for(i = 0; i < this.elements.length; i++) {   
            arr.push(this.elements[i].value);   
        }   
        return arr;   
    };   
     
    //获取MAP中所有KEY的数组（ARRAY）   
    this.keys = function() {   
        var arr = new Array();   
        for(i = 0; i < this.elements.length; i++) {   
            arr.push(this.elements[i].key);   
        }   
        return arr;   
    };   
}   

/**
 * 删除数组中的元素
 * @param obj
 */
Array.prototype.remove = function(obj) {
	for ( var i = 0; i < this.length; i++) {
		var temp = this[i];
		if (!isNaN(obj)) {
			temp = i;
		}
		if (temp == obj) {
			for ( var j = i; j < this.length; j++) {
				this[j] = this[j + 1];
			}
			this.length = this.length - 1;
		}
	}
} 

function startServer() {
	var ws=null;
	if(ws==null || ws==undefined){
		//ws=window.localStorage.cywws;
	}
	if(ws==null || ws==undefined){
		 // 设定WebSocket,注意协议是ws，请求是指向对应的WebSocketServlet的
	    var url = "ws://127.0.0.1:8080/echo.ws";
	    // 创建WebSocket实例，下面那个MozWebSocket是Firefox的实现
	    if ('WebSocket' in window) { 
	        ws = new WebSocket(url);
	    } else if ('MozWebSocket' in window) { 
	        ws = new MozWebSocket(url); 
	    } else { 
	        alert('Unsupported.'); 
	        return; 
	    }
	    window.localStorage.cywws=ws;
	}
	
	if(ws==null || ws==undefined) return;
    console.log(ws);
    // WebSocket握手完成，连接成功的回调
    ws.onopen = function() { 
        alert('Opened!'); 
    };

    // 收到服务器发送的文本消息, event.data表示文本内容
    ws.onmessage = function(event) { 
        alert('Receive message: ' + event.data); 
    };

    // 关闭WebSocket的回调
    ws.onclose = function() {
        alert('Closed!'); 
    };
}

function switchMenu(){
	var currUrl=window.location.pathname;
	var $alink= $(".secondMenuDiv .secondMenu a[href]").first();
	var chanRedirect=false;
	//console.log($(".secondMenuDiv .secondMenu a[noauthed]").length);
	$(".secondMenuDiv .secondMenu a[noauthed]").each(function(){
		//console.log($(this).attr('url'));
		var turl=$(this).attr('url');
		if(turl==currUrl){
			chanRedirect=true;
		}
	});
	//console.log($alink);
	//console.log($alink.attr("href"));
	var firsrUrl=$alink.attr("href");
	//console.log($alink.attr('noauthed'));
	if(chanRedirect){
		window.location.href=$alink.attr("href");
	}
}
