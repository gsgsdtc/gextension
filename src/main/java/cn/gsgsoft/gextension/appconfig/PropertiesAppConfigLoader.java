package cn.gsgsoft.gextension.appconfig;

import java.io.IOException;
import java.util.Properties;

import cn.gsgsoft.gextension.exception.GExtensionException;
import cn.gsgsoft.gextension.utils.PropertiesLoaderUtils;

/**
 * 使用META-INF/appconfig.properties的配置文件
 * @author guosg
 *
 */
public class PropertiesAppConfigLoader implements AppConfigLoader{
	public static final String resource_name = "META-INF/appconfig.properties";
	
	public void load(AppConfigManager manager) {
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadAllProperties(resource_name, this.getClass().getClassLoader());
		} catch (IOException e) {
			throw new GExtensionException("配置"+resource_name+"文件加载异常"+e.getMessage(),e);
		}
		
		manager.addConfig(props);
	}

}
