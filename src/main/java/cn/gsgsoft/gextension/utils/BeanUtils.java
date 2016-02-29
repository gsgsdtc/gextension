package cn.gsgsoft.gextension.utils;

import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.Locale;

import cn.gsgsoft.gextension.exception.GExtensionException;

public class BeanUtils {
	
	/**
	 * 实例化一个没有参数的构造方法的实例
	 * @param clazz
	 * @return
	 */
	public static <T> T instantiate(Class<T> clazz)  {
		
		if (clazz.isInterface()) {
			throw new GExtensionException(clazz+" 是一个接口不能实例化");
		}
		try {
			return clazz.newInstance();
		}
		catch (InstantiationException ex) {
			throw new GExtensionException(clazz+ " 是一个虚拟方法不能实例化", ex);
		}
		catch (IllegalAccessException ex) {
			throw new GExtensionException(clazz+ " 没有无参的构造方法", ex);
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
				clazz.equals(Locale.class) || clazz.equals(Class.class);
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
			throw new GExtensionException(clazzName+ " 没有找到", e);
		}
		return clazz;
	}
	
	
}
