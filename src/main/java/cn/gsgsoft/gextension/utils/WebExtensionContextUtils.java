package cn.gsgsoft.gextension.utils;

import javax.servlet.ServletContext;

import cn.gsgsoft.gextension.ExtensionContext;

/**
 * 使用getContext
 * @author guosg
 *
 */
public class WebExtensionContextUtils {
	public static final String GEXTENSION_CONTEXT_WEB_NAME="gextensionContextWebName";
	
	public static ExtensionContext getContext(ServletContext sc) {
		return (ExtensionContext) sc.getAttribute(GEXTENSION_CONTEXT_WEB_NAME);
	}
	
}
