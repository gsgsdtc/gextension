package cn.gsgsoft.gextension.appconfig;

import cn.gsgsoft.gextension.annotation.SPIParam;

/**
 * 从zookeeper中进行配置文件的加载
 * @author guosg
 *
 */
public class ZookeeperAppConfigLoader implements AppConfigLoader{
	
	private String host;

	public void load(AppConfigManager manager) {
	}
	
	@SPIParam()
	public void setHost(String host){
		this.host = host;
	}
	
	public String getHost(){
		return this.host;
	}
	
	

}
