package com.cyw.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ComparatorHelper.
 *
 * @param <T> the generic type
 * @ClassName: ComparatorHelper
 * @Title: 
 * @Description:TODO(这里用一句话描述这个类的作用) 
 * @Author:wangxiaojun 
 * @Since:2014-11-21下午12:43:54 
 * @Version:1.0 
 */
public class ComparatorHelper<T> {

    /**
     * Sort by number.
     *
     * @param collection the collection
     * @param asc the asc
     * @return the list
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })   
    public List sortByNumber(Collection<? extends T> collection, final boolean asc) {
        List list = new ArrayList();
        list.addAll(collection);
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                int result = 0;
                if (o1 instanceof Number && o1 instanceof Number) {
                    Number number1 = (Number) o1;
                    Number number2 = (Number) o2;
                    if (number1.hashCode() < number2.hashCode()) {
                        result = -1;
                    } else if (number1.hashCode() > number2.hashCode()) {
                        result = -1;
                    }
                    if (!asc) {
                        result = -result;
                    }
                }
                return result;
            }
        });
        return list;
    }

    /**
     * 按照日期进行排序 <br>
     * 2013-6-25 下午9:46:45.
     *
     * @param collection 要排序的集合
     * @param field      指定排序的时间字段
     * @param asc        是否按正序排序
     * @return List
     */
	@SuppressWarnings({"unchecked", "rawtypes" })   
    public List sortByDateTime(Collection<? extends T> collection, final Field field,
                               final boolean asc) {
        if (collection == null) {
            return null;
        }
        List list = new ArrayList();
        list.addAll(collection);
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Object object = invokeMethod(o1, field);
                Object object2 = invokeMethod(o2, field);
                if (object == null || object2 == null) {
                    return 0;
                }
                int value = 0;
                if (object instanceof Date) {
                    Date v1 = (Date) object;
                    Date v2 = (Date) object2;

                    if (v1.getTime() < v2.getTime()) {
                        value = -1;
                    } else if (v1.getTime() > v2.getTime()) {
                        value = 1;
                    }
                    if (!asc) {
                        value = -value;
                    }
                }
                return value;
            }

        });
        return list;
    }
    
//    public void Sort(List<T> list, final String method, final String sort){
//		Collections.sort(list, new Comparator() {			
//		    public int compare(Object a, Object b) {
//		    	int ret = 0;
//		    	try{
//			    	Method m1 = ((T)a).getClass().getMethod(method, null);
//			    	Method m2 = ((T)b).getClass().getMethod(method, null);
//			    	if(sort != null && "desc".equals(sort))//倒序
//			    		ret = m2.invoke(((T)b), null).toString().compareTo(m1.invoke(((T)a), null).toString());	
//			    	else//正序
//			    		ret = m1.invoke(((T)a), null).toString().compareTo(m2.invoke(((T)b), null).toString());
//		    	}catch(NoSuchMethodException ne){
//		    		System.out.println(ne);
//				}catch(IllegalAccessException ie){
//					System.out.println(ie);
//				}catch(InvocationTargetException it){
//					System.out.println(it);
//				}
//		    	return ret;
//		    }
//		 });
//	}

    /**
 * Sort by string.
 *
 * @param paralist the paralist
 * @param field the field
 * @param asc the asc
 * @return the list
 */
public List<T> sortByString(List<T> paralist, final Field field,
            final boolean asc) {
		if (paralist == null) {
		return null;
		}
		Collections.sort(paralist, new Comparator<T>() {
		@Override
		public int compare(T o1, T o2) {
		Object object = invokeMethod(o1, field);
		Object object2 = invokeMethod(o2, field);
		if (object == null || object2 == null) {
		return 0;
		}
		int value = 0;
		if (object instanceof String) {
		String v1 = (String) object;
		String v2 = (String) object2;
		if (v1.hashCode() < v2.hashCode()) {
		 value = -1;
		} else {
		 value = 1;
		}
		if (!asc) {
		 value = -value;
		}
		}
		return value;
		}
		
		});
		return paralist;
}
    
    /**
     * 按照字符串进行排序 <br>
     * 2013-6-25 下午9:46:45.
     *
     * @param collection 要排序的集合
     * @param field      指定排序的时间字段
     * @param asc        是否按正序排序
     * @return List
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })   
    public Collection sortByString(Collection<? extends T> collection, final Field field,
                                   final boolean asc) {
        if (collection == null) {
            return null;
        }
        List list = new ArrayList();
        list.addAll(collection);
        Collections.sort(list, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                Object object = invokeMethod(o1, field);
                Object object2 = invokeMethod(o2, field);
                if (object == null || object2 == null) {
                    return 0;
                }
                int value = 0;
                if (object instanceof String) {
                    String v1 = (String) object;
                    String v2 = (String) object2;
                    if (v1.hashCode() < v2.hashCode()) {
                        value = -1;
                    } else {
                        value = 1;
                    }
                    if (!asc) {
                        value = -value;
                    }
                }
                return value;
            }

        });
        return list;
    }

    /**
     * Invoke method.
     *
     * @param target the target
     * @param filed the filed
     * @return the object
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })   
    private Object invokeMethod(Object target, Field filed) {
        Object returnObject = null;
        Class clazz = target.getClass();
        String fieldName = filed.getName();
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
        try {
            Method method = clazz.getMethod(methodName);
            returnObject = method.invoke(target);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return returnObject;
    }

    /**
     * The Class Test.
     */
    static class Test {
        
        /** The i. */
        String i;

        /**
         * String.
         *
         * @return the i
         */
        public String getI() {
            return i;
        }

        /**
         * String.
         *
         * @param i the i to set
         */
        public void setI(String i) {
            this.i = i;
        }

        /**
         * <br>
         * 2013-9-11 下午12:01:52.
         *
         * @return the int
         * @see Object#hashCode()
         */
        @Override
        public int hashCode() {
            return i.hashCode();
        }

    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    @SuppressWarnings({"unchecked", "rawtypes" })   
    public static void main(String[] args) {
        Random random = new Random();
        Collection<Test> collection = new ArrayList<Test>();
        for (int i = 0; i < 100; i++) {
            String string = random.nextInt(1000) + "";
            Test test = new Test();
            test.setI(string);
            collection.add(test);
        }
        ComparatorHelper comparatorHelper = new ComparatorHelper<Test>();
        try {
            collection = comparatorHelper.sortByString(collection,
                    Test.class.getDeclaredField("i"), true);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        for (Iterator iterator = collection.iterator(); iterator.hasNext(); ) {
            Test test = (Test) iterator.next();
            System.out.println(test.getI());
        }
    }
}
