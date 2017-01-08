package cn.gsgsoft.gextension.extension;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;
import cn.gsgsoft.gextension.utils.PropertiesLoaderUtils;

/**
 * 扩展相关的配置的加载<p>
 * classpath:META-INF/extension/*.properties 所有配置文件
 * 
 * @author guosg
 *
 */
public class SpiExtensionConfigBean implements SpiConfigBean {
	
	public static final String extension_config_dic="META-INF/extension/";
	private Properties props = new Properties();
	private static SpiExtensionConfigBean loader = null;
	
	/**
	 * 
	 */
	Map<String/**扩展点名称*/, Map<String/**实现名称*/,String/**实现class*/>> extensionMap = new HashMap<String, Map<String,String>>();
	
	
	private SpiExtensionConfigBean(){
		
	}
	
	
	/* (non-Javadoc)
	 * @see cn.gsgsoft.gextension.spi.SpiConfigBean#getExtensionImpl(java.lang.String)
	 */
	public Map<String, String> getExtensionImpl(String spiName){
		return extensionMap.get(spiName);
	}
	/* (non-Javadoc)
	 * @see cn.gsgsoft.gextension.spi.SpiConfigBean#getSpiNames()
	 */
	public Collection<String> getSpiNames(){
		return extensionMap.keySet();
	}
	
	/* (non-Javadoc)
	 * @see cn.gsgsoft.gextension.spi.SpiConfigBean#getExtensionImpl(java.lang.String, java.lang.String)
	 */
	public String getExtensionImpl(String spiName,String implName){
		Map<String, String> im = extensionMap.get(spiName);
		if(im!=null){
			return im.get(implName);
		}
		return null;
	}
	
	public void load(){
		try{
			props = PropertiesLoaderUtils.loadDictoryAllProperties(extension_config_dic, this.getClass().getClassLoader());
			Enumeration<?> e =  props.propertyNames();
			while(e.hasMoreElements()){
				String n = (String) e.nextElement();
				String c = props.getProperty(n);//class name
				
				int i = n.lastIndexOf(".");
				String pn = n.substring(0,i);//extension point name
				String in = n.substring(i+1);//impl name
				Map<String, String> pmap = extensionMap.get(pn);
				if(pmap==null){
					pmap = new HashMap<String, String>();
					extensionMap.put(pn,pmap);
				}
				pmap.put(in, c);
			}
		}catch(IOException ex ){
			throw new ExtensionException(GexExceptionContract.GEX_000003,ex);
		}
	}
	
	
	
	/**
	 * 获得一个单例的对象
	 * @return
	 */
	public static SpiConfigBean getInstance(){
		if(loader==null){
			instantiate();
		}
		return loader;
	}
	
	/**
	 * 实例化
	 */
	public static synchronized void instantiate(){
		if(loader==null){
			loader = new SpiExtensionConfigBean();
			loader.load();
		}
	}
	
}
