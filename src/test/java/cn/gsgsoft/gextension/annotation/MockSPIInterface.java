package cn.gsgsoft.gextension.annotation;

import cn.gsgsoft.gextend.annotation.SPI;
import cn.gsgsoft.gextend.annotation.SPIParam;

/**
 * 
 * @author guosg
 *
 */
@SPI(name="gextend.mockspi",def="gextend")
public interface MockSPIInterface {
	
	@SPIParam
	public void setRole(String role);
}
