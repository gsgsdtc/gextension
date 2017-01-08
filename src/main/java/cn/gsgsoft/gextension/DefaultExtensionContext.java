package cn.gsgsoft.gextension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 扩展点的context，通过context可以获得所有的扩展点的实现
 * @author guosg
 *
 */
public class DefaultExtensionContext implements ExtensionContext {
	private List<ExtensionLoader> lis = new ArrayList<ExtensionLoader>();
	private static final Logger logger = LoggerFactory.getLogger(DefaultExtensionContext.class);
	
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
			loader.initialize();
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.gsgsoft.gextension.ExtensionContext#destroy()
	 */
	public void destroy(){
		if(logger.isInfoEnabled()){
			logger.info("销毁ExtensionContext");
		}
		for(ExtensionLoader loader : lis){
			loader.destroy();
		}
	};
	
	/* (non-Javadoc)
	 * @see cn.gsgsoft.gextension.ExtensionContext#getInstance(java.lang.Class)
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
	
	/* (non-Javadoc)
	 * @see cn.gsgsoft.gextension.ExtensionContext#getInstances(java.lang.Class)
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
	
	/* (non-Javadoc)
	 * @see cn.gsgsoft.gextension.ExtensionContext#getInstance(java.lang.Class, java.lang.String)
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
