package cn.gsgsoft.gextension.extension;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import cn.gsgsoft.gextension.DefaultExtensionContext;
import cn.gsgsoft.gextension.ExtensionContext;
import cn.gsgsoft.gextension.annotation.SPIImplBean;
import cn.gsgsoft.gextension.appconfig.AppConfigManager;

/**
 * 使用spirng来加载扩展
 * 
 * @author guosg
 *
 */
public class SpringExtensionLoader extends AbstractExtensionLoader{
	ConfigurableListableBeanFactory beanFactory;

	public SpringExtensionLoader(AppConfigManager appConfigManager,ConfigurableListableBeanFactory beanFactory,ExtensionContext context) {
		super(appConfigManager,context);
		this.beanFactory=beanFactory;
	}
	
	
	
	public SpringExtensionLoader(AppConfigManager appConfigManager,SpiConfigBean extensionConfigBean,ConfigurableListableBeanFactory beanFactory,ExtensionContext context){
		super(appConfigManager,extensionConfigBean,context);
		this.beanFactory=beanFactory;
	}
	
	
	@Override
	protected Object doInstantiate(SPIImplBean spiImplBean) {
		DefaultListableBeanFactory bf  = (DefaultListableBeanFactory)beanFactory;
		RootBeanDefinition implBeanDefi = new RootBeanDefinition(spiImplBean.getImplClass());
		bf.registerBeanDefinition(spiImplBean.getImplClass().getName(), implBeanDefi);
		return bf.getBean(spiImplBean.getImplClass());
	}
	
}
