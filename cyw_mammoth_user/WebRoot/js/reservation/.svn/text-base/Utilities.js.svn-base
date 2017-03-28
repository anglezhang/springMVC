/**
 * 验证类 Utilities.js
 */
String.prototype.replaceAll = function(s1,s2){
   return this.replace(new RegExp(s1,"gm"),s2);
}
String.prototype.trim=function() {  
    return this.replace(/(^\s*)|(\s*$)/g,'');  
}; 
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
/**
 * 是否是简单时间 YYYY-MM-DD
 */
function isSimpleDate(value){
	var text = /^(\d{4})\-(\d{2})\-(\d{2})$/;
	return text.test(value);
}
function isSimpleTime(value){
	var text = /^(\d{2})\:(\d{2})$/;
	return text.test(value);
}

function isChinaZH(value){
	var text = /[\u4e00-\u9fa5]/;
	return text.test(value);
}

function isNumber(value){
	var text = /^[0-9]*$/;
	return text.test(value);
}
//数字,字母,下划线
function isSimplePwd(value){
	var text = /^[a-zA-Z]\/w{5,17}$/;
	return text.test(value);
}
function isSimpleUserName(value){
	var text = /^[a-zA-Z]\/w{5,17}$/;
	return text.test(value);
}

function getDoubleStr (num){
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
function simpleDate2Number(cdate){
	if(!isSimpleDate(cdate)){
		return 0;
	}else{
		return parseInt(cdate.replaceAll("-",""));
	}
}

var Utilities = window.Utilities || {};
Utilities.RegValidate = new function() {
	  var self = this;
	  self.isSimpleDate = isSimpleDate;
	  self.isSimpleTime = isSimpleTime;
	  self.isNumber = isNumber;
	  self.isSimplePwd = isSimplePwd;
	  self.isChinaZH = isChinaZH;
	  self.isSimpleUserName = isSimpleUserName;
};
Utilities.utils = new function(){
	var self = this;
	self.getDoubleStr=getDoubleStr;
	self.simpleDate2Number=simpleDate2Number;
}
//invoke:Utilities.Hello.sayHello();
