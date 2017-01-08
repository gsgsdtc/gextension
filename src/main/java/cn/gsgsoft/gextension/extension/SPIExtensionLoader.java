package cn.gsgsoft.gextension.extension;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.gsgsoft.gextension.DefaultExtensionContext;
import cn.gsgsoft.gextension.ExtensionContext;
import cn.gsgsoft.gextension.ExtensionLoader;
import cn.gsgsoft.gextension.annotation.SPIBean;
import cn.gsgsoft.gextension.annotation.SPIImplBean;
import cn.gsgsoft.gextension.annotation.SPIParamBean;
import cn.gsgsoft.gextension.appconfig.AppConfigManager;
import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;
import cn.gsgsoft.gextension.utils.BeanUtils;
import cn.gsgsoft.gextension.utils.ConvertUtils;

/**
 * spi扩展点加载器
 * @author guosg
 *
 */
public class SPIExtensionLoader extends AbstractExtensionLoader{
	
	
	public SPIExtensionLoader(AppConfigManager appConfigManager,ExtensionContext context){
		super(appConfigManager, context);
	}
	
	public SPIExtensionLoader(AppConfigManager appConfigManager,SpiConfigBean extensionConfigBean,ExtensionContext context){
		super(appConfigManager,context);
	}
	
	
	/**
	 * 实现
	 */
	@Override
	public Object doInstantiate(SPIImplBean spiImpl) {
		return BeanUtils.instantiate(spiImpl.getImplClass());
	}


}
