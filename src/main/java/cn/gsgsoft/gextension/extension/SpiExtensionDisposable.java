package cn.gsgsoft.gextension.extension;

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
