package cn.gsgsoft.gextension.appconfig;

/**
 * 使用properies来进行配置的管理器
 * @author guosg
 *
 */
public class PropertiesAppConfigManager extends AbstractAppConfigManager implements AppConfigManager{
	public PropertiesAppConfigManager(){
		init();
	}
	
	private void init(){
		PropertiesAppConfigLoader loader = new PropertiesAppConfigLoader();
		loader.load(this);
	}
}
