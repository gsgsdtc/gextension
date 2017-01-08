package cn.gsgsoft.gextension;

import java.util.ArrayList;
import java.util.List;

import cn.gsgsoft.gextension.appconfig.PropertiesAppConfigManager;
import cn.gsgsoft.gextension.extension.SPIExtensionLoader;
import cn.gsgsoft.gextension.utils.ExtensionContextUtils;

/**
 * ExtensionContextBuilder的创建者<p>
 * 创建一个ExtensionContext时，也需要考虑怎么销毁一个ExtensionContext<br>
 * 销毁一个上下文可以使用extensionContext.destroy()方法。
 * 
 * @author guosg
 *
 */
public class DefaultExtensionContextBuilder {
	private List<SPIExtensionLoader> loaders = new ArrayList<SPIExtensionLoader>();
	
	public DefaultExtensionContext build(){
		DefaultExtensionContext extensionContext = new DefaultExtensionContext();
		SPIExtensionLoader loader = new SPIExtensionLoader(new PropertiesAppConfigManager(),extensionContext);
		extensionContext.addExtensionLoader(loader);
		loader.instantiate();
		loader.fillParams();
		extensionContext.initialize();
		ExtensionContextUtils.setExtensionLoader(extensionContext);
		return extensionContext;
	}
	
	public void addSPIExtensionLoader(SPIExtensionLoader loader){
		loaders.add(loader);
	}
	
}
