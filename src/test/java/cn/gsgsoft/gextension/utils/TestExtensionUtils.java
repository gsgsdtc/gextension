package cn.gsgsoft.gextension.utils;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextend.config.PropertiesAppConfigManager;
import cn.gsgsoft.gextend.extension.SPIExtensionLoader;
import cn.gsgsoft.gextension.extension.DefaultMockExtension;
import cn.gsgsoft.gextension.extension.MockExtension;

/**
 * 
 * @author guosg
 *
 */
public class TestExtensionUtils {
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
