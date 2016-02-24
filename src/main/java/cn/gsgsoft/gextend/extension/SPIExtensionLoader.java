package cn.gsgsoft.gextend.extension;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import cn.gsgsoft.gextend.annotation.SPIBean;
import cn.gsgsoft.gextend.annotation.SPIImplBean;
import cn.gsgsoft.gextend.annotation.SPIParamBean;
import cn.gsgsoft.gextend.config.AppConfigManager;
import cn.gsgsoft.gextend.exception.GExtensionException;
import cn.gsgsoft.gextend.utils.BeanUtils;
import cn.gsgsoft.gextend.utils.ConvertUtils;

/**
 * spi扩展点加载器
 * @author guosg
 *
 */
public class SPIExtensionLoader implements ExtensionLoader{
	private AppConfigManager appConfigManager ;
	private ExtensionConfigBean extensionConfigBean = null;
	private Map<Class<?>, SPIBean> spiBeanMap = new ConcurrentHashMap<Class<?>, SPIBean>();
	private Map<Class<?>, SPIImplBean> spiImplBeanMap = new ConcurrentHashMap<Class<?>, SPIImplBean>();
	
	public SPIExtensionLoader(AppConfigManager appConfigManager){
		this.appConfigManager = appConfigManager;
		extensionConfigBean = ExtensionConfigBean.getInstance();
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T> T getExtension(Class<T> type, String name) {
		SPIBean bean = getSPIBean(type);
		String spiName = bean.getName();
		if(name == null){
			
			
			name = appConfigManager.getValue(bean.getName());
			if(name ==null || name.length()==0){
				name = bean.getDef();
			}
		}
		String className=extensionConfigBean.getExtensionImpl(spiName, name);
		if(className==null || className.length()==0){
			throw new GExtensionException("没有对应spiName:"+spiName+" name:"+name+" 的实现");
		}
		Object obj = BeanUtils.instantiate(className);
		fillParams(obj, spiName, name);
		return (T)obj;
	}
	
	/**
	 * 为创建的对象填入参数
	 * @param obj
	 * @param spiName
	 * @param implName
	 */
	protected void fillParams(Object obj,String spiName,String implName){
		SPIImplBean spiImpl = getSPIImplBean(obj.getClass());
		for(SPIParamBean spiParam : spiImpl.getParams()){
			String sv = appConfigManager.getValue(spiName+"."+implName+"."+spiParam.getName());
			if(sv==null || sv.length()==0)
				sv = appConfigManager.getValue(spiName+"."+spiParam.getName());
			if(sv!=null && sv.length()!=0){
				Object v = ConvertUtils.convertValue(spiParam.getValueType(), sv);
				invokeMethod(spiParam.getMethod(),obj,v);
			}
		}
	}
	
	protected void invokeMethod(Method method,Object target,Object value){
		try {
			method.invoke(target, value);
		} catch (Exception e) {
			throw new GExtensionException("对象"+target+"的方法"+method.getName()+"填入参数"+value,e);
		} 
	}
	
	/**
	 * 根据扩展点获得一个实现
	 * @param type 扩展点的接口
	 * @return
	 */
	public <T> T getExtension(Class<T> type){
		
		return getExtension(type, null);
	}
	
	
	public SPIBean getSPIBean(Class<?> type){
		SPIBean bean = spiBeanMap.get(type);
		if(bean == null){
			bean = new SPIBean(type);
			spiBeanMap.put(type, bean);
		}
		return bean;
	}
	
	public SPIImplBean getSPIImplBean(Class<?> type){
		SPIImplBean bean =  spiImplBeanMap.get(type);
		if(bean == null){
			bean = new SPIImplBean(type);
			spiImplBeanMap.put(type, bean);
		}
		return bean;
	}

}
