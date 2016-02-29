package cn.gsgsoft.gextension.config;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextension.appconfig.PropertiesAppConfigManager;


/**
 * 测试加载appconfig.properties
 * @author guosg
 *
 */
public class TestPropertiesAppConfigManager {
	
	@Test
	public void test(){
		PropertiesAppConfigManager m = new PropertiesAppConfigManager();
		Assert.assertEquals("impl", m.getValue("gextension.mock.properties_load"));
	}
}
