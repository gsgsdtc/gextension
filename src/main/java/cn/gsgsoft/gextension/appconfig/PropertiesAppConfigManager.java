package cn.gsgsoft.gextension.appconfig;

/**
 * <p>使用properies来进行配置的管理器<p>
 * 
 * 使用 {@link PropertiesAppConfigLoader}来进行配置文件的加载<br>
 * 这个管理器是为了进行自举时使用<br>
 * 
 * @author guosg
 *
 */
public class PropertiesAppConfigManager extends AbstractAppConfigManager implements AppConfigManager{
	
	
	private String[] paths;
	
	
	public PropertiesAppConfigManager(){
		this(null);
	}
	
	/**
	 * 
	 * @param path 加载文件的路径，文件路径必须在 classpath下能够找到
	 */
	public PropertiesAppConfigManager(String[] paths){
		this.paths = paths;
		init();
	}
	
	private void init(){
		PropertiesAppConfigLoader loader = new PropertiesAppConfigLoader(paths);
		loader.load(this);
	}
	

	public String[] getPaths() {
		return paths;
	}

	public void setPaths(String[] paths) {
		this.paths = paths;
	}

	
	
}
