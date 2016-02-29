package cn.gsgsoft.gextension.annotation;

import cn.gsgsoft.gextension.annotation.SPI;
import cn.gsgsoft.gextension.annotation.SPIParam;

/**
 * 
 * @author guosg
 *
 */
@SPI(name="gextension.mockspi",def="gextension",multiImp=true)
public interface MockSPIInterface {
	
	@SPIParam
	public void setRole(String role);
}
