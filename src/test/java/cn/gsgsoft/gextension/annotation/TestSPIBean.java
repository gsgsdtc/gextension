package cn.gsgsoft.gextension.annotation;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextend.annotation.SPI;
import cn.gsgsoft.gextend.annotation.SPIBean;
import cn.gsgsoft.gextend.exception.GExtensionException;
import cn.gsgsoft.gextend.utils.AnnotationUtils;

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
		Assert.assertEquals("gextend.mockspi", bean.getName());
		Assert.assertEquals("gextend", bean.getDef());
	}
	
	/**
	 * 测试接口没有配置annotaion的情况
	 */
	@Test
	public void testNoAnnotaionConfig(){
		try{
			SPIBean bean = new SPIBean(TestSPIBean.class);
			Assert.assertEquals("gextend.mockspi", bean.getName());
		}catch(GExtensionException ex){
			Assert.assertTrue(ex instanceof GExtensionException);
		}
		
	}
	
	@Test
	public void testAnnotationUtils(){
		SPI spi = AnnotationUtils.getAnnotation(MockSPIInterface.class,SPI.class );
		Assert.assertEquals("gextend.mockspi", spi.name());
	}
	
	
}
