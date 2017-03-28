package com.cyw.mammoth.cywenum;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sun.reflect.ConstructorAccessor;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

/**
 * 动态构建枚举类
 * @author wexl
 *
 */
public class DynamicEnum {
	private static ReflectionFactory reflectionFactory = ReflectionFactory.getReflectionFactory();

	private static void setFailsafeFieldValue(Field field, Object target, Object value) throws NoSuchFieldException, IllegalAccessException {

		field.setAccessible(true);

		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		int modifiers = modifiersField.getInt(field);

		modifiers &= ~Modifier.FINAL;
		modifiersField.setInt(field, modifiers);

		FieldAccessor fa = reflectionFactory.newFieldAccessor(field, false);
		fa.set(target, value);
	}

	private static void blankField(Class<?> enumClass, String fieldName) throws NoSuchFieldException, IllegalAccessException {
		for (Field field : Class.class.getDeclaredFields()) {
			if (field.getName().contains(fieldName)) {
				AccessibleObject.setAccessible(new Field[] { field }, true);
				setFailsafeFieldValue(field, enumClass, null);
				break;
			}
		}
	}

	private static void cleanEnumCache(Class<?> enumClass) throws NoSuchFieldException, IllegalAccessException {
		blankField(enumClass, "enumConstantDirectory"); // Sun (Oracle?!?) JDK
														// 1.5/6
		blankField(enumClass, "enumConstants"); // IBM JDK
	}

	private static ConstructorAccessor getConstructorAccessor(Class<?> enumClass, Class<?>[] additionalParameterTypes) throws NoSuchMethodException {
		Class<?>[] parameterTypes = new Class[additionalParameterTypes.length + 2];
		parameterTypes[0] = String.class;
		parameterTypes[1] = int.class;
		System.arraycopy(additionalParameterTypes, 0, parameterTypes, 2, additionalParameterTypes.length);
		return reflectionFactory.newConstructorAccessor(enumClass.getDeclaredConstructor(parameterTypes));
	}

	private static Object makeEnum(Class<?> enumClass, String value, int ordinal, Class<?>[] additionalTypes, Object[] additionalValues) throws Exception {
		Object[] parms = new Object[additionalValues.length + 2];
		parms[0] = value;
		parms[1] = Integer.valueOf(ordinal);
		System.arraycopy(additionalValues, 0, parms, 2, additionalValues.length);
		return enumClass.cast(getConstructorAccessor(enumClass, additionalTypes).newInstance(parms));
	}

	/**
	 * Add an enum instance to the enum class given as argument
	 *
	 * @param <T>
	 *            the type of the enum (implicit)
	 * @param enumType
	 *            the class of the enum to be modified
	 * @param enumName
	 *            the name of the new enum instance to be added to the class.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<?>> void addEnum(Class<T> enumType, String enumName) {

		if (!Enum.class.isAssignableFrom(enumType)) {
			throw new RuntimeException("class " + enumType + " is not an instance of Enum");
		}

		Field valuesField = null;
		Field[] fields = TestEnum.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().contains("$VALUES")) {
				valuesField = field;
				break;
			}
		}
		AccessibleObject.setAccessible(new Field[] { valuesField }, true);

		try {

			T[] previousValues = (T[]) valuesField.get(enumType);
			List<T> values = new ArrayList<T>(Arrays.asList(previousValues));

			T newValue = (T) makeEnum(enumType, // The target enum class
					enumName, // THE NEW ENUM INSTANCE TO BE DYNAMICALLY ADDED
					values.size(), new Class<?>[] {}, // could be used to pass
														// values to the enum
														// constuctor if needed
					new Object[] {}); // could be used to pass values to the
										// enum constuctor if needed

			values.add(newValue);

			setFailsafeFieldValue(valuesField, null, values.toArray((T[]) Array.newInstance(enumType, 0)));

			cleanEnumCache(enumType);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static <T extends Enum<?>> void addEnum(Class<T> enumType, String enumName, Class<?>[] additionalTypes, Object[] additionalValues) {

		if (!Enum.class.isAssignableFrom(enumType)) {
			throw new RuntimeException("class " + enumType + " is not an instance of Enum");
		}

		Field valuesField = null;
		Field[] fields = enumType.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().contains("$VALUES")) {
				valuesField = field;
				break;
			}
		}
		AccessibleObject.setAccessible(new Field[] { valuesField }, true);

		try {

			T[] previousValues = (T[]) valuesField.get(enumType);
			List<T> values = new ArrayList<T>(Arrays.asList(previousValues));

			T newValue = (T) makeEnum(enumType, enumName, values.size(), additionalTypes, additionalValues);

			values.add(newValue);

			setFailsafeFieldValue(valuesField, null, values.toArray((T[]) Array.newInstance(enumType, 0)));

			cleanEnumCache(enumType);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	private static enum TestEnum {
		a, b, c;
	};
	
	private static  enum DynamicDataSourceKeyEnum {
		  a,b,c
	}

	private static enum InfoPairEnum {

		TEST1("A001", "测试1"), TEST2("A002", "测试2");
		private String code;
		private String name;

		private InfoPairEnum(String code, String name) {
			this.code = code;
			this.name = name;
		}

		@Override
		public String toString() {
			return "[" + code + " : " + name + "]";
		}
	};

	public static void main(String[] args) {

		// Dynamically add 3 new enum instances d, e, f to TestEnum
		 addEnum(TestEnum.class, "d");
		 addEnum(TestEnum.class, "e");
		 addEnum(TestEnum.class, "f");
		 System.out.println(Arrays.deepToString(TestEnum.values()));
		// Run a few tests just to show it works OK.
		// System.out.println(Arrays.deepToString(TestEnum.values()));
		// Shows : [a, b, c, d, e, f]
		// --------------------------
		//addEnum(InfoPairEnum.class, "TEST3", new Class[] { String.class, String.class }, new Object[] { "A003", "测试3" });
		//InfoPairEnum e3 = InfoPairEnum.valueOf("TEST3");
		//System.out.println(Arrays.deepToString(InfoPairEnum.values()));
		addEnum(DynamicDataSourceKeyEnum.class,"dataSource");
		addEnum(DynamicDataSourceKeyEnum.class,"dataSource2");
		 System.out.println(Arrays.deepToString(DynamicDataSourceKeyEnum.values()));
	}
}
