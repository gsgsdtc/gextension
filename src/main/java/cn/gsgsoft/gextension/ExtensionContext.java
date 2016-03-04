package cn.gsgsoft.gextension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.gsgsoft.gextension.logger.Logger;
import cn.gsgsoft.gextension.logger.LoggerFactory;

/**
 * 扩展点的context，通过context可以获得所有的扩展点的实现
 * @author guosg
 *
 */
public class ExtensionContext {
	private List<ExtensionLoader> lis = new ArrayList<ExtensionLoader>();
	private static final Logger logger = LoggerFactory.getLogger(ExtensionContext.class);
	public void addExtensionLoader(ExtensionLoader loader){
		lis.add(loader);
	}
	
	/**
	 * 初始化方法
	 */
	public void initialize(){
		if(logger.isInfoEnabled()){
			logger.info("初始化ExtensionContext");
		}
		for(ExtensionLoader loader : lis){
			loader.setExtensionContext(this);
			loader.instantiate();
		}
		
		for(ExtensionLoader loader : lis){
			loader.fillParams();
		}
		
		for(ExtensionLoader loader : lis){
			loader.initialize();
		}
	};
	
	/**
	 * 销毁
	 */
	public void destroy(){
		if(logger.isInfoEnabled()){
			logger.info("销毁ExtensionContext");
		}
		for(ExtensionLoader loader : lis){
			loader.destroy();
		}
	};
	
	/**
	 * 获得
	 * @param name
	 */
	public <T> T getInstance(Class<T> type){
		for(ExtensionLoader loader : lis){
			T r = loader.getExtension(type);
			if(r !=null){
				return r;
			}
		}
		return null;
	}
	
	/**
	 * 获得
	 * @param name
	 */
	public <T> Collection<T> getInstances(Class<T> type){
		for(ExtensionLoader loader : lis){
			Collection<T> rs = loader.getExtensions(type);
			if(rs!=null){
				return rs;
			}
		}
		return null;
	}
	
	/**
	 * 获得
	 * @param name
	 */
	public <T> T getInstance(Class<T> type,String name){
		for(ExtensionLoader loader : lis){
			T r = loader.getExtension(type,name);
			if(r!=null){
				return r;
			}
		}
		return null;
	}
}
