package cn.gsgsoft.gextension.extension;

import java.util.Collection;
import java.util.Map;

/**
 * spi配置的相关配置
 * @author guosg
 *
 */
public interface SpiConfigBean {

	/**
	 * 获得扩展点名称下的所有的实现
	 * @param spiName 扩展点的名称
	 * @return 获得 key=实现的名称 value=实现的类名
	 */
	Map<String, String> getExtensionImpl(String spiName);

	/**
	 * 获得所有的扩展点的名称
	 * @return
	 */
	Collection<String> getSpiNames();

	/**
	 * 获得扩展的实现
	 * @param spiName	扩展点的名称
	 * @param implName	扩展点的实现
	 * @return
	 */
	String getExtensionImpl(String spiName, String implName);

}