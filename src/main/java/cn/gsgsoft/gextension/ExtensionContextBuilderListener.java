package cn.gsgsoft.gextension;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.gsgsoft.gextension.utils.WebExtensionContextUtils;

/**
 * 应用使用Servlet可以是用此类进行context创建
 * @author guosg
 *
 */
public class ExtensionContextBuilderListener implements ServletContextListener{
	
	
	ExtensionContext context = null;
	
	public void contextInitialized(ServletContextEvent sce) {
		context = (new DefaultExtensionContextBuilder()).build();
		sce.getServletContext().setAttribute(WebExtensionContextUtils.GEXTENSION_CONTEXT_WEB_NAME, context);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		context.destroy();
	}

}
