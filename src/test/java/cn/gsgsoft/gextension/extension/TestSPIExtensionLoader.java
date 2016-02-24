package cn.gsgsoft.gextension.extension;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextend.config.PropertiesAppConfigManager;
import cn.gsgsoft.gextend.extension.SPIExtensionLoader;

/**
 * 
 * @author guosg
 *
 */
public class TestSPIExtensionLoader {
	
	@Test
	public void test(){
		SPIExtensionLoader loader = new SPIExtensionLoader(new PropertiesAppConfigManager());
		DefaultMockExtension impl = (DefaultMockExtension) loader.getExtension(MockExtension.class);
		Assert.assertEquals("name", impl.getName());
		Assert.assertEquals(new Integer(123), impl.getId());
		Assert.assertEquals(new Double("123"), impl.getAge());
		Assert.assertEquals(new BigDecimal("123.123"), impl.getBigDecimal());
	}
}
