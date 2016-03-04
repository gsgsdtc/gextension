package cn.gsgsoft.gextension.utils;

import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;

public class BeanUtils {
	
	/**
	 * 实例化一个没有参数的构造方法的实例
	 * @param clazz
	 * @return
	 */
	public static <T> T instantiate(Class<T> clazz)  {
		
		if (clazz.isInterface()) {
			throw new ExtensionException(GexExceptionContract.GEX_000012,new Object[]{clazz});
		}
		try {
			return clazz.newInstance();
		}
		catch (InstantiationException ex) {
			throw new ExtensionException(GexExceptionContract.GEX_000013,new Object[]{clazz}, ex);
		}
		catch (IllegalAccessException ex) {
			throw new ExtensionException(GexExceptionContract.GEX_000014,new Object[]{clazz}, ex);
		}
	}
	
	
	/**
	 * 实例化一个没有参数的构造方法的实例
	 * @param clazz
	 * @return
	 */
	public static Object instantiate(String clazzName)  {
		
		return instantiate(classForName(clazzName));
	}
	
	/**
	 * Check if the given type represents a "simple" value type:
	 * a primitive, a String or other CharSequence, a Number, a Date,
	 * a URI, a URL, a Locale or a Class.
	 * @param clazz the type to check
	 * @return whether the given type represents a "simple" value type
	 */
	public static boolean isSimpleValueType(Class<?> clazz) {
		return  clazz.isEnum() ||
				CharSequence.class.isAssignableFrom(clazz) ||
				Number.class.isAssignableFrom(clazz) ||
				Date.class.isAssignableFrom(clazz) ||
				clazz.equals(URI.class) || clazz.equals(URL.class) ||
				clazz.equals(Locale.class) || clazz.equals(Class.class) || clazz.equals(int.class)
				 || clazz.equals(long.class)
				 || clazz.equals(short.class)
				 || clazz.equals(byte.class)
				 || clazz.equals(char.class)
				 || clazz.equals(boolean.class)
				 || clazz.equals(float.class)
				 || clazz.equals(double.class);
	}

	
	/**
	 * 
	 * @param clazzName
	 * @return
	 */
	public static Class<?> classForName(String clazzName){
		Class<?> clazz = null;
		try {
			clazz = Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			throw new ExtensionException(GexExceptionContract.GEX_000015, new Object[]{clazzName}, e);
		}
		return clazz;
	}
	
	
}
