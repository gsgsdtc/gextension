package cn.gsgsoft.gextension.appconfig;

import java.io.IOException;
import java.util.Properties;

import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;
import cn.gsgsoft.gextension.utils.PropertiesLoaderUtils;

/**
 * 使用META-INF/appconfig.properties的配置文件
 * @author guosg
 *
 */
public class PropertiesAppConfigLoader implements AppConfigLoader{
	public static final String resource_name = "META-INF/appconfig.properties";
	
	private String[] paths;
	
	public PropertiesAppConfigLoader() {
	}
	
	public PropertiesAppConfigLoader(String[] paths) {
		this.paths = paths;
	}
	
	public void load(AppConfigManager manager) {
		Properties props = null;
		//加载默认的配置文件路径
		try {
			props = PropertiesLoaderUtils.loadAllProperties(resource_name, this.getClass().getClassLoader());
			manager.addConfig(props);
		} catch (IOException e) {
			throw new ExtensionException(GexExceptionContract.GEX_000002,new Object[]{resource_name},e);
		}
		
		//加载自定义的配置文件路径
		if(paths !=null){
			for(String path : paths){
				try {
				Properties p  = PropertiesLoaderUtils.loadAllProperties(path, this.getClass().getClassLoader());
				manager.addConfig(p);
				} catch (IOException e) {
					throw new ExtensionException(GexExceptionContract.GEX_000002,new Object[]{path},e);
				}
			}
		}
	}

	public String[] getPaths() {
		return paths;
	}

	public void setPaths(String[] paths) {
		this.paths = paths;
	}

	
	

}
