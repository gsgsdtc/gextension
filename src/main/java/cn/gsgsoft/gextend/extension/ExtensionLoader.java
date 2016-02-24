package cn.gsgsoft.gextend.extension;

/**
 * 默认的工厂
 * @author guosg
 *
 */
public interface ExtensionLoader{
	
	/**
	 * 根据扩展点获得一个实现
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
	

}
