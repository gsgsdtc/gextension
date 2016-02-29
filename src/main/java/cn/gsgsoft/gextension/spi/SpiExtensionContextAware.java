package cn.gsgsoft.gextension.spi;

import cn.gsgsoft.gextension.ExtensionContext;

/**
 * 使用spi扩展点实现时，注入ExtensionContext的接口
 * @author guosg
 *
 */
public interface SpiExtensionContextAware {
	public void setExtensionContext(ExtensionContext extensionContext);
}
