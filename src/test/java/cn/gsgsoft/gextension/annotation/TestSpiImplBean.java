package cn.gsgsoft.gextension.annotation;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import cn.gsgsoft.gextension.annotation.SPIBean;
import cn.gsgsoft.gextension.annotation.SPIImplBean;
import cn.gsgsoft.gextension.annotation.SPIParamBean;

/**
 *  测试
 * @author guosg
 *
 */
public class TestSpiImplBean {
	
	@Test
	public void test(){
		SPIImplBean bean = new SPIImplBean(MockSpiImpl.class);
		SPIBean spi = bean.getSpiBean();
		Assert.assertEquals("gextension.mockspi", spi.getName());
		Assert.assertEquals("gextension", spi.getDef());
		Assert.assertEquals(3, bean.getParams().size());
		SPIParamBean nameBean = getBean(bean.getParams(), "alias");
		Assert.assertEquals("setName", nameBean.getMethod().getName());
		Assert.assertEquals("alias", nameBean.getName());
		Assert.assertEquals(true, nameBean.isChange());
		Assert.assertEquals(String.class, nameBean.getValueType());
		
		SPIParamBean passwordBean = getBean(bean.getParams(), "password");
		Assert.assertEquals("setPassword", passwordBean.getMethod().getName());
		Assert.assertEquals("password", passwordBean.getName());
		Assert.assertEquals(false, passwordBean.isChange());
		
		SPIParamBean roleBean = getBean(bean.getParams(), "role");
		Assert.assertEquals("setRole", roleBean.getMethod().getName());
		Assert.assertEquals("role", roleBean.getName());
		Assert.assertEquals(false, roleBean.isChange());
	}
	
	private SPIParamBean getBean(List<SPIParamBean> lis,String name){
		for(SPIParamBean  b : lis){
			if(b.getName().equals(name)){
				return b;
			}
		}
		return null;
	}
}
