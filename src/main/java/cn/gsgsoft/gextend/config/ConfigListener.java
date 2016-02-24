package cn.gsgsoft.gextend.config;

import java.util.Properties;

/**
 * 配置性变动的监听接口
 * @author guosg
 *
 */
public interface ConfigListener {
	
	/**
	 * 
	 * @param propertis 发生变化的配置项
	 */
	public void changed(Properties propertis);
}
