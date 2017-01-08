package cn.gsgsoft.gextension;

import java.util.Collection;

import cn.gsgsoft.gextension.annotation.SPI;

/**
 * 扩展点加载<p>
 * 加载的步骤<br>
 * <ul>
 * 	<li>设置ExtensionContext
 * 	<li>instantiate实例话对象
 * 	<li>fillParams 填入参数
 * 	<li>initialize进行初始化
 * </ul>
 * 但应用需要停止时，需要调用destroy销毁
 * 
 * @author guosg
 *
 */
@SPI(name="gextension.loader",def="spi")
public interface ExtensionLoader{
	
	/**
	 * 创建所有相关配置的对象
	 */
	public void instantiate();
	
	/**
	 * 为对象注入相应的参数
	 */
	public void fillParams();
	
	/**
	 * 初始化方法
	 * 此时所有的实现以及进行了依赖注入
	 */
	public void initialize();
	
	/**
	 * 销毁扩展点
	 */
	public void destroy();
	
	/**
	 * 
	 * @param type 扩展点的接口
	 * @param name	扩展点思想的名称
	 * @return
	 */
	public <T> T getExtension(Class<T> type,String name);
	
	/**
	 * 根据扩展点获得一个实现
	 * @param type 扩展点的接口
	 * @return
	 */
	public <T> T getExtension(Class<T> type);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public <T> Collection<T> getExtensions(Class<T> type);
	
	
	
	
	public void setExtensionContext(ExtensionContext contest);
	
}
