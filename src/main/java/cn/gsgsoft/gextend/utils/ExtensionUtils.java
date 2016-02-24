package cn.gsgsoft.gextend.utils;

import cn.gsgsoft.gextend.config.ExtendableAppConfigManager;
import cn.gsgsoft.gextend.extension.ExtensionLoader;
import cn.gsgsoft.gextend.extension.SPIExtensionLoader;

/**
 * 扩展点工具类
 * @author guosg
 *
 */
public class ExtensionUtils {
	private static ExtensionLoader extensionLoader;
	
	/**
	 * 获得一个单例的ExtensionLoader
	 * @return
	 */
	public static ExtensionLoader getExtensionLoader(){
		if(extensionLoader == null){
			instantiateExtensionLoader();
		}
		return extensionLoader;
	}
	
	/**
	 * 实例化ExtensionLoader
	 */
	private static synchronized void instantiateExtensionLoader(){
		if(extensionLoader == null){
			extensionLoader = new SPIExtensionLoader(ExtendableAppConfigManager.getInstance());
		}
	}
	
}
