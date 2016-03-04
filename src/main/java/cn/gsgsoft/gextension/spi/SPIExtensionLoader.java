package cn.gsgsoft.gextension.spi;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.gsgsoft.gextension.ExtensionContext;
import cn.gsgsoft.gextension.ExtensionLoader;
import cn.gsgsoft.gextension.annotation.SPIBean;
import cn.gsgsoft.gextension.annotation.SPIImplBean;
import cn.gsgsoft.gextension.annotation.SPIParamBean;
import cn.gsgsoft.gextension.appconfig.AppConfigManager;
import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;
import cn.gsgsoft.gextension.utils.BeanUtils;
import cn.gsgsoft.gextension.utils.ConvertUtils;

/**
 * spi扩展点加载器
 * @author guosg
 *
 */
public class SPIExtensionLoader implements ExtensionLoader{
	private AppConfigManager appConfigManager ;
	private SpiExtensionConfigBean extensionConfigBean = null;
	private Map<Class<?>, SPIBean> spiBeanMap = new ConcurrentHashMap<Class<?>, SPIBean>();
	private Map<Class<?>, SPIImplBean> spiImplBeanMap = new ConcurrentHashMap<Class<?>, SPIImplBean>();
	private ExtensionContext context = null;
	Map<Class<?>, Map<String,Object> > typeMap = new ConcurrentHashMap<Class<?>,Map<String,Object>>();
	
	public SPIExtensionLoader(AppConfigManager appConfigManager){
		this.appConfigManager = appConfigManager;
		extensionConfigBean = SpiExtensionConfigBean.getInstance();
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public <T> T getExtension(Class<T> type, String name){
		Map<String, Object> map =  getSpiNameMap(type);
		if(map == null)
			return null;
		return (T)map.get(name);

	}
	
	/**
	 * 
	 */
	public <T> T getExtension(Class<T> type){
		Collection<T> rs = getExtensions(type);
		if(rs == null){
			return null;
		}else if(rs.size()==1){
			return rs.iterator().next();
		}else if(rs.size()>1){
			throw new ExtensionException(GexExceptionContract.GEX_000004,new Object[]{type});
		}
		return null;
	}
	
	/**
	 * 获得
	 * @param name
	 */
	@SuppressWarnings("unchecked")
	public <T> Collection<T> getExtensions(Class<T> type){
		Map<String, Object> map = getSpiNameMap(type);
		if(map==null){
			return null;
		}
		return (Collection<T>) typeMap.get(type).values();
	}
	
	private Map<String, Object> getSpiNameMap(Class<?> type){
		return typeMap.get(type);
	}
	
	/**
	 * 实例化所有的
	 */
	public void instantiate() {
		Collection<String> spiNames = extensionConfigBean.getSpiNames();
		
		for(String spiName : spiNames){
			Map<String, String> implMap = extensionConfigBean.getExtensionImpl(spiName);
			
			if(implMap==null || implMap.size()==0){
				throw new ExtensionException(GexExceptionContract.GEX_000005,new Object[]{spiName});
			}
			
			Map<String, Object> implInstanceMap = new HashMap<String, Object>();
			
			Map.Entry<String, String> tentry = implMap.entrySet().iterator().next();
			String implClassName = tentry.getValue();
			String implName = tentry.getKey();
			SPIImplBean spiImplBean = null;
			SPIBean spiBean = null;
			Class<?> implClass = null;
			try{
				implClass = BeanUtils.classForName(implClassName);
				spiImplBean = getSPIImplBean(implClass);
				spiBean = spiImplBean.getSpiBean();
			}catch(Exception ex){
				throw new ExtensionException(GexExceptionContract.GEX_000006,new Object[]{spiName,implName,implClass},ex);
			}
			
			String curName = null;
			String curImplClassName = null;
			try{
				if(spiBean.getMultiImp()){
					for(Map.Entry<String, String> entry : implMap.entrySet()){
						curName = entry.getKey();
						curImplClassName = entry.getValue();
						Object o =BeanUtils.instantiate(curImplClassName);
						
						if(o.getClass().isAssignableFrom(spiBean.getType())){
							throw new ExtensionException(GexExceptionContract.GEX_000007);
						}
						implInstanceMap.put(curName, o);
					}
				}else{
					curName = appConfigManager.getValue(spiName);
					if(curName==null || "".equals(curName)){
						curName = spiBean.getDef();
					}
					
					curImplClassName = implMap.get(curName);
					
					if(curImplClassName==null || curImplClassName.length()==0){
						throw new NullPointerException("实现类为空");
					}
					
					Object o =BeanUtils.instantiate(curImplClassName);
					if(o.getClass().isAssignableFrom(spiBean.getType())){
						throw new ExtensionException(GexExceptionContract.GEX_000007);
					}
					implInstanceMap.put(curName, o);
				}
			}catch(Exception ex){
				throw new ExtensionException(GexExceptionContract.GEX_000008,new Object[]{spiName,curName,curImplClassName},ex.getMessage(),ex);
			}
			typeMap.put(spiBean.getType(), implInstanceMap);
		}
		
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
			try{
				Object paramValue = null;
				if(spiParam.isSimpleValueType()){
					String sv = appConfigManager.getValue(spiName+"."+implName+"."+spiParam.getName());
					if(sv==null || sv.length()==0)
						sv = appConfigManager.getValue(spiName+"."+spiParam.getName());
					if(sv!=null && sv.length()!=0){
						paramValue = ConvertUtils.convertValue(spiParam.getValueType(), sv);
					}
				}else{
					if(spiParam.isMultiImpl()){
						paramValue = context.getInstances(spiParam.getValueType());
					}else{
						if(spiParam.isDefaultIpml()){
							SPIBean spiBean = getSPIBean(spiParam.getValueType());
							if(spiBean.getMultiImp()){
								throw new ExtensionException(GexExceptionContract.GEX_000009);
							}
							paramValue = context.getInstance(spiParam.getValueType());
						}else{
							paramValue = context.getInstance(spiParam.getValueType(),spiParam.getName());
						}
					}
				}
				if(paramValue!=null){
					invokeMethod(spiParam.getMethod(),obj,paramValue);
				}
			}catch(Exception ex){
				throw new ExtensionException(GexExceptionContract.GEX_000010
						,new Object[]{spiName,implName,obj.getClass(),spiParam.getMethod().getName()},
						ex.getMessage(),
						ex);
			}
		}
	}
	
	protected void invokeMethod(Method method,Object target,Object value){
		try {
			method.invoke(target, value);
		} catch (Exception e) {
			throw new ExtensionException(GexExceptionContract.GEX_000011,new Object[]{target,method.getName(),value},e);
		} 
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
	
	public void fillParams() {
		for(Map.Entry<Class<?>,Map<String, Object>> entry : typeMap.entrySet()){
			Class<?> type = entry.getKey();
			SPIBean spi = getSPIBean(type);
			for(Map.Entry<String, Object> implEntry : entry.getValue().entrySet()){
				String implName = implEntry.getKey();
				Object target = implEntry.getValue();
				fillParams(target, spi.getName(), implName);
			}
		}
	}
	
	

	public void setExtensionContext(ExtensionContext context) {
		this.context = context;
	}

	public void initialize() {
		for(Map.Entry<Class<?>,Map<String, Object>> entry : typeMap.entrySet()){
			for(Map.Entry<String, Object> implEntry : entry.getValue().entrySet()){
				Object target = implEntry.getValue();
				if(target instanceof SpiExtensionContextAware){
					((SpiExtensionContextAware) target).setExtensionContext(context);
				}
				if(target instanceof SpiExtensionInitializable){
					((SpiExtensionInitializable) target).initialize();
				}
			}
		}
	}

	public void destroy() {
		for(Map.Entry<Class<?>,Map<String, Object>> entry : typeMap.entrySet()){
			for(Map.Entry<String, Object> implEntry : entry.getValue().entrySet()){
				Object target = implEntry.getValue();
				if(target instanceof SpiExtensionDisposable){
					((SpiExtensionDisposable) target).destroy();
				}
			}
		}
		
	}

	

}
