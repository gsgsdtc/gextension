package cn.gsgsoft.gextend.config;

import java.util.Properties;

/**
 * 应用配置管理器
 * @author guosg
 *
 */
public interface AppConfigManager {
	
	/**
	 * 获得配置的值
	 * @param name
	 * @return
	 */
	public String getValue(String name);
	
	/**
	 * 增加一个配置
	 * @return
	 */
	public void addConfig(Properties config);
	
}
