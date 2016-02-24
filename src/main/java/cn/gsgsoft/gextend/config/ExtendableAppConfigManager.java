package cn.gsgsoft.gextend.config;

import java.util.Map;

import cn.gsgsoft.gextend.extension.ExtensionConfigBean;
import cn.gsgsoft.gextend.extension.SPIExtensionLoader;

/**
 * 管理多个配置加载器，并处理数据项监听的动作
 * @author guosg
 *
 */
public class ExtendableAppConfigManager extends AbstractAppConfigManager implements AppConfigManager{
	
	private static ExtendableAppConfigManager manager;
	
	
	private ExtendableAppConfigManager(){
		init();
	}
	
	protected void init() {
		PropertiesAppConfigManager configManager = new PropertiesAppConfigManager();
		
		SPIExtensionLoader extensionLoader = new SPIExtensionLoader(configManager);
		
		Map<String, String> config = ExtensionConfigBean.getInstance().getExtensionImpl("gextend.extension_config");
		
		for(String name :config.keySet()){
			AppConfigLoader loader = extensionLoader.getExtension(AppConfigLoader.class,name);
			loader.load(this);
		}
		
	}
	
	
	/**
	 * 获得一个单例的ExtensionConfigManager
	 * @return
	 */
	public static ExtendableAppConfigManager getInstance(){
		if(manager == null){
			instantiate();
		}
		return manager;
	}
	
	public static synchronized void instantiate(){
		if(manager==null){
			manager=new ExtendableAppConfigManager();
		}
	}
	
}
