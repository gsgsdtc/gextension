package cn.gsgsoft.gextension.config;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextend.config.PropertiesAppConfigManager;

/**
 * 
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
