/**
* 格式化金额 ￥##,###,###.##
* @param num 数值(Number或者String) 
* @return 金额格式的字符串,如'1,234,567.4' 
* @type String 
*/
function formatCurrencyTenThou(num)
{
	num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
    num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*10+0.50000000001);  
    cents = num%10;  
    num = Math.floor(num/10).toString();  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}
/**
* 删除当前行
*/
function deleteLine(obj)
{
	var line = $(obj).parent().parent();
	line.remove();
}

/**
*@param msg log消息
*/
function bugMsg(msg)
{
	console.debug(msg);
}
/**
* 关闭当前弹出框
*/
function closeWindow()
{
    $.pdialog.closeCurrent();
}

/**
* 描述:设置选中tabs下标
* 如果使用该函数 给li 添加属性 currenIndex 从0开始
* 设置隐藏区 id= tabCurrenIndex 保存当前 下标 name根据表单需要设置
*/
function setCurrtIndexVal(fun)
{
    $(".tabsHeaderContent").bind('click',function(event) {
        var obj = $(this).find("li.selected");
        $("[tabCurrenIndex]").each(function(index, el) {
            $(el).val(obj.attr("data-currenIndex"));
            fun();
        });
    });
   
}
/**
* 选中tab
*/
function setIndexSelected () 
{
    var currenIndex = parseInt($("#currenIndex").val());
    $("[data-currenIndex]").each(function(index, el) {
            $(el).removeClass('selected');
            var thisIndex = parseInt($(el).attr("data-currenIndex"));
            if(thisIndex==currenIndex)
            {
                $(el).trigger('click');
            }
        });
}
/**
* 描述:返回特殊属性集合
* @param attrName：属性名称
* @return 属性值集合
*/
function getAttrbuteCollection(attrName)
{
    try{
         var attrArray = new Array();
        $("[" + attrName + "]").each(function(index, el) {
            var obj = $(el);
            var objVal = obj.attr(attrName);
            attrArray.push(objVal);
        });
        return attrArray;
    }catch(e)
    {
        console.error("getAttrbuteCollection commomjs");
    }
   
}
/**
* 判断字非空
* @param true 空 false非空
*/
function isEmptyObj(obj)
{
    if(obj===null)return true;
    if(obj==="")return true;
    if(obj==="undefined")return true;
    if(obj===undefined)return true;
    if(obj==="null")return true;
    if(obj.toString().trim()===null)return true;
    if(obj.toString().trim().length===0)return true;
    return false;
}
//=========只能输入小数或数字===========
function clearNoNum(event,obj){ 
    //响应鼠标事件，允许左右方向键移动 
    event = window.event||event; 
    if(event.keyCode == 37 | event.keyCode == 39){ 
        return; 
    } 
    //先把非数字的都替换掉，除了数字和. 
    obj.value = obj.value.replace(/[^\d.]/g,""); 
    //必须保证第一个为数字而不是. 
    obj.value = obj.value.replace(/^\./g,""); 
    //保证只有出现一个.而没有多个. 
    obj.value = obj.value.replace(/\.{2,}/g,"."); 
    //保证.只出现一次，而不能出现两次以上 
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
} 
function checkNum(obj){ 
    //为了去除最后一个. 
    obj.value = obj.value.replace(/\.$/g,""); 
}
/**
* 描述:利用模版创建table行
* @param dateObj 数据对象
* @param tbodyId tbodyID
* @param templateId 模版id
* @param beforefun添加完成前执行函数
* @afterfun添加完成后执行函数
*/
function createTableTr(dateObj,tbodyId,templateId,beforefun,afterfun)
{
    try{
        beforefun();
        template.helper('formatNumber',function(num)
        {
            num = parseFloat(num);
            var newNum = formatCurrencyTenThou(num);
            return "￥" + newNum;
        });
        var tableHtml = template(templateId, dateObj);
        $("#" + tbodyId).append(tableHtml);
        afterfun();
    }catch(e)
    {
        console.error("createTableTr commomjs");
    }
}
/**
* 描述:设置href参数
* @param params 需要传递的参数集合
* @param lookupid lookup标签主键
* @param pramName 参数名称
*/
function setlookupParams(params,lookupid,pramName)
{
    var lookObj = $("#" + lookupid);
    var hrefVal = lookObj.prop("href");
    if(hrefVal.indexOf("?") != -1)
    {
        hrefVal = hrefVal.substr(0,hrefVal.indexOf("?"));
        
        
    }
    hrefVal += "?" + pramName + "=";
    for(var i=0;i<params.length;i++)
    {
        hrefVal += params[i] + ","
    }
    if(hrefVal.indexOf(",") != -1)
    {
        hrefVal = hrefVal.substr(0,hrefVal.length-1);
    }
    lookObj.prop("href",hrefVal);
}
/**
* 描述:移除Array 中 值为value的序列
* @param array 数组
* @param val 本次移除的值
*/
function removeAtValue(array,val)
{
    var value = parseInt(val);
    var lenght = array.length;
    for(var i=0;i<lenght;i++)
    {
        var id = parseInt(array[i]);
        if(id==value)
        {
            array.splice(i,1);
        }
    }
}

/**
* 描述：打印
*/
function printHtml()
{
    window.print();
}
