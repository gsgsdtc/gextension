package cn.gsgsoft.gextension.utils;

import cn.gsgsoft.gextension.ExtensionContext;
import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;

/**
 * 扩展点工具类
 * @author guosg
 *
 */
public class ExtensionContextUtils {
	
	private static ExtensionContext extensionContext;
	
	/**
	 * 获得一个单例的ExtensionLoader
	 * @return
	 */
	public static ExtensionContext getExtensionLoader(){
		if(extensionContext == null){
			throw new ExtensionException(GexExceptionContract.GEX_000017);
		}
		return extensionContext;
	}
	
	public static void setExtensionLoader(ExtensionContext extensionContext){
		ExtensionContextUtils.extensionContext =  extensionContext;
	}
	
}
