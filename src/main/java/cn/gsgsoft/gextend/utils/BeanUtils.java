package cn.gsgsoft.gextend.utils;

import cn.gsgsoft.gextend.exception.GExtensionException;

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
		Class<?> clazz = null;
		try {
			clazz = Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			throw new GExtensionException(clazzName+ " 没有找到", e);
		}
		return instantiate(clazz);
	}
	
	
	
}
