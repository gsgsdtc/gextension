package cn.gsgsoft.gextension;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;

/**
 * 使用spring来创建一个SpringContext
 * @author guosg
 *
 */
public class SpringExtensionContextBuilder implements FactoryBean<ExtensionContext>,DisposableBean{
	ExtensionContext extensionContext;
	public void destroy() throws Exception {
		extensionContext.destroy();
	}

	public ExtensionContext getObject() throws Exception {
		ExtensionContextBuilder builder = new ExtensionContextBuilder();
		extensionContext = builder.build();
		return extensionContext;
	}

	public Class<?> getObjectType() {
		return ExtensionContext.class;
	}

	public boolean isSingleton() {
		return true;
	}
	
	
	
}
