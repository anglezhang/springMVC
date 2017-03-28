package com.cyw.common.util.bean;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 访问在当前类声明的private/protected成员变量及private/protected函数的BeanUtils.
 * 注意,因为必须为当前类声明的变量,通过继承获得的protected变量将不能访问, 需要转型到声明该变量的类才能访问. 反射的其他功能请使用Apache
 * Jarkarta Commons BeanUtils
 */
public class BeanUtils {
    /** Log日志 */
    private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 获取当前类声明的private/protected变量
     * 
     * @param object
     * @param propertyName
     * @return property
     */
    public static Object getProperty(Object object, String propertyName) {
        try {
            Field field = object.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
            logger.debug(object + "' property " + propertyName + " not exist!");
        } catch (NoSuchFieldException e) {
            logger.debug(object + "' property " + propertyName + " not exist!");
        }
        return null;
    }

    /**
     * 设置当前类声明的private/protected变量
     * 
     * @param object
     * @param propertyName
     * @param newValue
     */
    public static void setProperty(Object object, String propertyName, Object newValue) {
        try {
            if (newValue == null) {
                setPrivateProperty(object, propertyName, newValue);
            } else {
                org.apache.commons.beanutils.BeanUtils.setProperty(object, propertyName, newValue);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取当前类声明的private/protected变量
     * 
     * @param object
     * @param propertyName
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getPrivateProperty(Object object, String propertyName) throws IllegalAccessException,
            NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);
        return field.get(object);
    }

    /**
     * 设置当前类声明的private/protected变量
     * 
     * @param object
     * @param propertyName
     * @param newValue
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static void setPrivateProperty(Object object, String propertyName, Object newValue) {
        try {
            Field field = object.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            field.set(object, newValue);
        } catch (IllegalAccessException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 调用当前类声明的private/protected函数
     * 
     * @param object
     * @param methodName
     * @param params
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokePrivateMethod(Object object, String methodName, Object[] params)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?>[] types = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            types[i] = params[i].getClass();
        }
        Method method = object.getClass().getDeclaredMethod(methodName, types);
        method.setAccessible(true);
        return method.invoke(object, params);
    }

    /**
     * 调用当前类声明的private/protected函数
     * 
     * @param object
     * @param methodName
     * @param param
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object invokePrivateMethod(Object object, String methodName, Object param)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokePrivateMethod(object, methodName, new Object[] { param });
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     * 
     * @throws NoSuchFieldException 如果没有该Field时抛出.
     * @param object
     * @param propertyName
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getDeclaredField(Object object, String propertyName) {
        try {
            return getDeclaredField(object.getClass(), propertyName);
        } catch (NoSuchFieldException e) {
            logger.warn("没有找到指定的属性", e);
        }
        return null;
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     * 
     * @throws NoSuchFieldException 如果没有该Field时抛出.
     * @param clazz
     * @param propertyName
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getDeclaredField(Class<?> clazz, String propertyName) throws NoSuchFieldException {
        for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(propertyName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
                // logger.warn("getDeclaredField " + e);
                // DO nothing.
            }
        }
        throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
    }

    public static boolean hasDeclaredField(Class<?> clazz, String propertyName) {
        try {
            getDeclaredField(clazz, propertyName);
        } catch (NoSuchFieldException e) {
            return false;
        }
        return true;
    }

    /**
     * 暴力获取对象变量值,忽略private,protected修饰符的限制.
     * 
     * @throws NoSuchFieldException 如果没有该Field时抛出.
     * @param object
     * @param propertyName
     * @return
     * @throws NoSuchFieldException
     */
    public static Object forceGetProperty(Object object, String propertyName) {
        Field field = null;
        field = getDeclaredField(object, propertyName);
        if (field == null) {
            return null;
        }
        boolean accessible = field.isAccessible();
        field.setAccessible(true);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            logger.info("error wont' happen");
        }
        field.setAccessible(accessible);
        return result;
    }

    /**
     * 按Filed的类型取得Field列表.
     * 
     * @param object
     * @param type
     * @return
     */
    public static List<Field> getFieldsByType(Object object, Class<?> type) {
        List<Field> list = new ArrayList<Field>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (type.isAssignableFrom(field.getType())) {
                list.add(field);
            }
        }
        return list;
    }

    public static List<Field> getFields(Object object) {
        List<Field> list = new ArrayList<Field>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            list.add(field);
        }
        return list;
    }

    /**
     * 按FiledName获得Field的类型.
     * 
     * @param type
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    public static Class<?> getPropertyType(Class<?> type, String name) throws NoSuchFieldException {
        return getDeclaredField(type, name).getType();
    }

    /**
     * Bean值复制
     * 
     * @param dst
     * @param src
     */
    public static void beanCopys(Object dst, Object src) {
        if (src == null) {
            return;
        }
        try {
            converterInit();
            org.apache.commons.beanutils.BeanUtils.copyProperties(dst, src);
        } catch (IllegalAccessException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Bean肤浅值复制,并且过滤NULL及0
     * 
     * @param dst
     * @param src
     */
    public static void beanShallowCopy(Object dst, Object src) {
        if (src == null) {
            return;
        }
        try {
            String packageName = null;
            PropertyDescriptor origDescriptors[] = PropertyUtils.getPropertyDescriptors(src);
            Pattern pattern = Pattern.compile("com.cyw.*.[model|vo|domain|bean]", Pattern.CASE_INSENSITIVE);
            for (PropertyDescriptor propertyDescriptor : origDescriptors) {
                Package pkg = propertyDescriptor.getPropertyType().getPackage();
                packageName = "";
                if (pkg != null) {
                    packageName = pkg.getName();
                }
                Matcher matcher = pattern.matcher(packageName);
                if (!matcher.find()) {
                    if (PropertyUtils.isWriteable(dst, propertyDescriptor.getName())) {
                        Object propertyObject = PropertyUtils.getProperty(src, propertyDescriptor.getName());
                        if (!(propertyObject instanceof Collection)) {
                        	if(propertyObject!=null){
                        		if(propertyObject instanceof Integer && ((Integer)propertyObject).intValue()==0) continue;
                        		if(propertyObject instanceof Long && ((Long)propertyObject).intValue()==0) continue;
                        		 BeanUtils.copyProperty(dst, propertyDescriptor.getName(), PropertyUtils.getProperty(src,
                                         propertyDescriptor.getName()));
                        	}
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Bean属性值复制
     * 
     * @param dst
     * @param src
     */
    public static void copyProperty(Object dstBean, String propertyName, Object srcValue) {
        if (srcValue == null) {
            return;
        }
        try {
            converterInit();
            if(srcValue instanceof String && "".equals(((String)srcValue).trim())){
            	setEmptyPropertyValue(dstBean, propertyName, srcValue);
            	return;
            }
            org.apache.commons.beanutils.BeanUtils.copyProperty(dstBean, propertyName, srcValue);
        } catch (IllegalAccessException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            logger.error("system error", e);
            throw new RuntimeException(e);
        }
    }
    /**
     * 获取对象中值为NULL的数学
     * @param source
     * @return
     */
	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	/**
	 * 给对象中的属性直接设置值
	 * @param dstBean
	 * @param propertyName
	 * @param srcValue
	 */
	public static void setEmptyPropertyValue (Object dstBean, String propertyName, Object srcValue) {
		    if(null==dstBean) return;
		    if("".equals(propertyName)) return;
		    if(null==srcValue) return;
		    Class beanCla = (Class) dstBean.getClass(); 
		    try {
				Field fs = beanCla.getDeclaredField(propertyName);
				fs.set(dstBean, srcValue);
			} catch (Exception e) {
				return;
			}
		    
		}
    

    /**
     * <p>
     * 对象深度克隆。
     * </p>
     * 
     * @param src 克隆源
     * @return 新对象
     */
    public static Object deepClone(Object src) {
        Object obj = null;
        try {
            ByteArrayOutputStream byteArrOs = new ByteArrayOutputStream();
            ObjectOutputStream objOs = new ObjectOutputStream(byteArrOs);
            objOs.writeObject(src);

            ByteArrayInputStream byteArrIs = new ByteArrayInputStream(byteArrOs.toByteArray());
            ObjectInputStream objIs = new ObjectInputStream(byteArrIs);
            obj = objIs.readObject();
        } catch (IOException e) {
            throw new RuntimeException("deep Clone", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("deep Clone", e);
        }
        return obj;
    }

    /**
     * 设定类转换
     * 
     */
    private static void converterInit() {
        ConvertUtils.register(new DateConverter(), Date.class);
        ConvertUtils.register(new TimestampConverter(), Timestamp.class);
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new LongConverter(null), Long.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
        ConvertUtils.register(new StringConverter(), String.class);
        ConvertUtils.register(new CurrencyConverter(), BigDecimal.class);
    }
}
