package cn.gsgsoft.gextension;

import cn.gsgsoft.gextension.annotation.SPI;

/**
 * 模拟一个扩展点测试
 * @author guosg
 *
 */
@SPI(name="gextension.extension",def="default")
public interface MockExtension {
	
}
