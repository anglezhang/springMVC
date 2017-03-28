package com.zoomoor.common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**   
 * 类名称：Token   
 * 类描述： 自定义注解（防止重复提交）
 * 创建人：liuweixing
 * 创建时间：2015-8-10 下午2:27:06   
 * 修改人：liuweixing
 * 修改时间：2015-8-10 下午2:27:06   
 * 修改备注：  在需要生成token的controller上增加@Token(save=true)，而在需要检查重复提交的controller上添加@Token(remove=true)就可以了。 
 * @version       
 */ 
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionToken {
	boolean save() default false;
	boolean remove() default false;
}
