package cn.gsgsoft.gextension;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextension.spi.SpiExtensionConfigBean;
import cn.gsgsoft.gextension.utils.PropertiesLoaderUtils;

/**
 * 
 * @author guosg
 *
 */
public class TestExtensionConfigBean {
	
	/**
	 * 
	 */
	@Test
	public void test(){
		SpiExtensionConfigBean bean = SpiExtensionConfigBean.getInstance();
		Assert.assertEquals("cn.gsgsoft.gextension.appconfig.PropertiesAppConfigLoader", bean.getExtensionImpl("gextension.appconfig.loader", "properties"));
	}
	
	/**
	 * 测试properties的加载
	 * @throws IOException 
	 */
	@Test
	public void testPropertiesLoad() throws IOException{
		
		Properties props = PropertiesLoaderUtils.loadDictoryAllProperties(SpiExtensionConfigBean.extension_config_dic, null);
		String s = props.getProperty("gextension.appconfig.loader.properties");
		Assert.assertEquals("cn.gsgsoft.gextension.appconfig.PropertiesAppConfigLoader", s);
		
	}
	/**
	 * 测试分离实现和扩展点名
	 */
	@Test
	public void testSplitPointName(){
		String pn;//extension point name
		String in;//impl name
		String n = "gextend.extension_config.properties";
		int i = n.lastIndexOf(".");
		pn = n.substring(0,i);
		in = n.substring(i+1);
		
		Assert.assertEquals("gextend.extension_config", pn);
		Assert.assertEquals("properties", in);
	}
}
