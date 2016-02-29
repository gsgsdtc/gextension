package cn.gsgsoft.gextension;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 应用使用Servlet可以是用此类进行context创建
 * @author guosg
 *
 */
public class ExtensionContextBuilderListener implements ServletContextListener{
	ExtensionContext context = null;
	public void contextInitialized(ServletContextEvent sce) {
		context = (new ExtensionContextBuilder()).build();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		context.destroy();
	}

}
