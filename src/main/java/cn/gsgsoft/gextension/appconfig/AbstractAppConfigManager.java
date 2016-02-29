package cn.gsgsoft.gextension.appconfig;

import java.util.Properties;

/**
 * 
 * @author guosg
 *
 */
public abstract class AbstractAppConfigManager  implements AppConfigManager{
	Properties props = new Properties();
	
	/**
	 * 获得配置的值
	 * @param name
	 * @return
	 */
	public String getValue(String name){
		return props.getProperty(name);
	}
	
	public void addConfig(Properties config) {
		props.putAll(config);
	}
}
