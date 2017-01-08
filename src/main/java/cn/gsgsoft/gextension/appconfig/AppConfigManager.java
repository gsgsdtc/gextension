package cn.gsgsoft.gextension.appconfig;

import java.util.Properties;

/**
 * <p>应用配置管理器<p>
 * 
 * 
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
