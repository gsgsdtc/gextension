package cn.gsgsoft.gextension.appconfig;

/**
 * 使用properies来进行配置的管理器
 * @author guosg
 *
 */
public class PropertiesAppConfigManager extends AbstractAppConfigManager implements AppConfigManager{
	
	private static PropertiesAppConfigManager manager;
	
	
	
	private PropertiesAppConfigManager(){
		init();
	}
	
	private void init(){
		PropertiesAppConfigLoader loader = new PropertiesAppConfigLoader();
		loader.load(this);
	}
	
	/**
	 * 获得一个单例的ExtensionConfigManager
	 * @return
	 */
	public static PropertiesAppConfigManager getInstance(){
		if(manager == null){
			instantiate();
		}
		return manager;
	}
	
	private static synchronized void instantiate(){
		if(manager==null){
			manager=new PropertiesAppConfigManager();
		}
	}
}
