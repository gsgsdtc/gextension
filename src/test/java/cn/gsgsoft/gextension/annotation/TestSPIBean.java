package cn.gsgsoft.gextension.annotation;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextension.annotation.SPI;
import cn.gsgsoft.gextension.annotation.SPIBean;
import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.utils.AnnotationUtils;

/**
 * 
 * 
 * @author guosg
 *
 */
public class TestSPIBean {
	
	@Test
	public void test(){
		SPIBean bean = new SPIBean(MockSPIInterface.class);
		Assert.assertEquals("gextension.mockspi", bean.getName());
		Assert.assertEquals("gextension", bean.getDef());
		Assert.assertTrue(bean.getMultiImp());
	}
	
	/**
	 * 测试接口没有配置annotaion的情况
	 */
	@Test
	public void testNoAnnotaionConfig(){
		try{
			SPIBean bean = new SPIBean(TestSPIBean.class);
			Assert.assertEquals("gextension.mockspi", bean.getName());
		}catch(ExtensionException ex){
			Assert.assertTrue(ex instanceof ExtensionException);
		}
		
	}
	
	@Test
	public void testAnnotationUtils(){
		SPI spi = AnnotationUtils.getAnnotation(MockSPIInterface.class,SPI.class );
		Assert.assertEquals("gextension.mockspi", spi.name());
	}
	
	
}
