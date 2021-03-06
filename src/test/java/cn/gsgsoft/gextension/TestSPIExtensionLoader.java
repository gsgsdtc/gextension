package cn.gsgsoft.gextension;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextension.DefaultExtensionContext;
import cn.gsgsoft.gextension.ExtensionLoader;
import cn.gsgsoft.gextension.appconfig.PropertiesAppConfigManager;
import cn.gsgsoft.gextension.extension.SPIExtensionLoader;

/**
 * 
 * @author guosg
 *
 */
public class TestSPIExtensionLoader {
	
	@Test
	public void test(){
		DefaultExtensionContext context = new DefaultExtensionContext();
		SPIExtensionLoader loader = new SPIExtensionLoader(new PropertiesAppConfigManager(),context);
		context.addExtensionLoader(loader);
		loader.setExtensionContext(context);
		loader.instantiate();
		loader.fillParams();
		DefaultMockExtension impl = (DefaultMockExtension) loader.getExtension(MockExtension.class);
		Assert.assertEquals("name", impl.getName());
		Assert.assertEquals(new Integer(123), impl.getId());
		Assert.assertEquals(new Double("123"), impl.getAge());
		Assert.assertEquals(new BigDecimal("123.123"), impl.getBigDecimal());
		Assert.assertEquals(impl, impl.getMockExtension());
	}
	
	@Test
	public void testMultiImpl(){
		DefaultExtensionContext context = new DefaultExtensionContext();
		SPIExtensionLoader loader = new SPIExtensionLoader(new PropertiesAppConfigManager(),context);
		context.addExtensionLoader(loader);
		loader.setExtensionContext(context);
		loader.instantiate();
		loader.fillParams();
		
		
	}
}
