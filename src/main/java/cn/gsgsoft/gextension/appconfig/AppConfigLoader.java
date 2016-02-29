package cn.gsgsoft.gextension.appconfig;

import cn.gsgsoft.gextension.annotation.SPI;

/**
 * 将配置加载到管理器
 * @author guosg
 *
 */
@SPI(name="gextension.appconfig.loader",def="properties")
public interface AppConfigLoader {
	
	/**
	 * 加载配置信息
	 * @return
	 */
	public void load(AppConfigManager manager);
}
