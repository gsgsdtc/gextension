package cn.gsgsoft.gextension;

import java.util.Collection;

/**
 * Gextension的上下文，
 * @author guosg
 *
 */
public interface ExtensionContext {
	
	/**
	 * 初始化方法
	 */
	public void initialize();

	/**
	 * 销毁
	 */
	void destroy();

	/**
	 * 根据一个扩展点的接口获得实现
	 * @param type
	 */
	<T> T getInstance(Class<T> type);

	/**
	 * 根据一个扩展点的接口获得实现
	 * @param type
	 */
	<T> Collection<T> getInstances(Class<T> type);

	/**
	 * 根据一个扩展点的接口和实现的名称获得实现
	 * @param name
	 */
	<T> T getInstance(Class<T> type, String name);

}