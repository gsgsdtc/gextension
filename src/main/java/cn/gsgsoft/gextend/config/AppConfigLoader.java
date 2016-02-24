package cn.gsgsoft.gextend.config;

import cn.gsgsoft.gextend.annotation.SPI;

/**
 * 将配置加载到管理器
 * @author guosg
 *
 */
@SPI(name="gextend.extension_config",def="properties")
public interface AppConfigLoader {
	
	/**
	 * 加载配置信息
	 * @return
	 */
	public void load(AppConfigManager manager);
}
