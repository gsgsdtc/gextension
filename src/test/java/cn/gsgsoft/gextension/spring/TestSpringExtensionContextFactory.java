package cn.gsgsoft.gextension.spring;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import cn.gsgsoft.gextension.MockMutiImplInterface;


@ContextConfiguration(locations = {"/META-INF/spring2.xml"})
public class TestSpringExtensionContextFactory extends AbstractJUnit4SpringContextTests implements ApplicationContextAware{
	
	@Test
	public void test(){
		Assert.assertNotNull(applicationContext.getBean(MockMutiImplInterface.class));
	}

}
