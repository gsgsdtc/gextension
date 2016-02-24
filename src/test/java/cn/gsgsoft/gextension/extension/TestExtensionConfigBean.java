package cn.gsgsoft.gextension.extension;

import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextend.extension.ExtensionConfigBean;
import cn.gsgsoft.gextend.utils.PropertiesLoaderUtils;

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
		ExtensionConfigBean bean = ExtensionConfigBean.getInstance();
		Assert.assertEquals("cn.gsgsoft.gextend.config.PropertiesExtensionConfig", bean.getExtensionImpl("gextend.extension_config", "propertes"));
		Assert.assertEquals("cn.gsgsoft.gextension.extension.TestExtensionConfigLoader",bean.getExtensionImpl("gextend.mock.properties_load", "impl"));
	}
	
	/**
	 * 测试properties的加载
	 * @throws IOException 
	 */
	@Test
	public void testPropertiesLoad() throws IOException{
		
		Properties props = PropertiesLoaderUtils.loadDictoryAllProperties(ExtensionConfigBean.extension_config_dic, null);
		String s = props.getProperty("gextend.extension_config.propertes");
		Assert.assertEquals("cn.gsgsoft.gextend.config.PropertiesExtensionConfig", s);
		
		//配置文件
		s = props.getProperty("gextend.mock.properties_load.impl");
		Assert.assertEquals("cn.gsgsoft.gextension.extension.TestExtensionConfigLoader", s);
		
	}
	/**
	 * 测试分离实现和扩展点名
	 */
	@Test
	public void testSplitPointName(){
		String pn;//extension point name
		String in;//impl name
		String n = "gextend.extension_config.propertes";
		int i = n.lastIndexOf(".");
		pn = n.substring(0,i);
		in = n.substring(i+1);
		
		Assert.assertEquals("gextend.extension_config", pn);
		Assert.assertEquals("propertes", in);
	}
}
