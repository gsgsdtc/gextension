package cn.gsgsoft.gextension.spi;

/**
 * 销毁扩展点
 * @author guosg
 *
 */
public interface SpiExtensionDisposable {
	
	/**
	 * 销毁
	 */
	void destroy();
}
