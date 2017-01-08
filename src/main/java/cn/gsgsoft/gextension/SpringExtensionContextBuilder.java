package cn.gsgsoft.gextension;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import cn.gsgsoft.gextension.appconfig.PropertiesAppConfigManager;
import cn.gsgsoft.gextension.extension.SpringExtensionLoader;

/**
 * 使用spring来创建一个SpringContext
 * @author guosg
 *
 */
public class SpringExtensionContextBuilder implements BeanFactoryPostProcessor,FactoryBean<ExtensionContext>,DisposableBean{
	private DefaultExtensionContext extensionContext;
	private SpringExtensionLoader springExtensionLoader;
	private String paths; //多个path使用,分割
	
	
	public void destroy() throws Exception {
		extensionContext.destroy();
	}

	
	/**
	 * 
	 */
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		DefaultListableBeanFactory bf  = (DefaultListableBeanFactory)beanFactory;
		
		String contextSpringName = ExtensionContext.class.getName();
		RootBeanDefinition contextDef = new RootBeanDefinition(DefaultExtensionContext.class);
		bf.registerBeanDefinition(contextSpringName, contextDef);
		extensionContext = (DefaultExtensionContext) bf.getBean(contextSpringName); 
		
		String[] ps = null;
		if(paths !=null && paths.length()>0){
			ps = paths.split(",");
		}
		
		springExtensionLoader = new SpringExtensionLoader(new PropertiesAppConfigManager(ps), beanFactory,extensionContext);
		springExtensionLoader.setExtensionContext(extensionContext);
		springExtensionLoader.instantiate();
		extensionContext.addExtensionLoader(springExtensionLoader);
		
		springExtensionLoader.fillParams(); 
		
		extensionContext.initialize();
	}


	@Override
	public ExtensionContext getObject() throws Exception {
		return extensionContext;
	}


	@Override
	public Class<?> getObjectType() {
		return ExtensionContext.class;
	}


	@Override
	public boolean isSingleton() {
		return true;
	}


	public String getPaths() {
		return paths;
	}


	public void setPaths(String paths) {
		this.paths = paths;
	}
	
	
	
}
