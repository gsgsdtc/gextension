package cn.gsgsoft.gextend.annotation;

import cn.gsgsoft.gextend.exception.GExtensionException;
import cn.gsgsoft.gextend.utils.AnnotationUtils;

/**
 * 将spi annation解析成一个可读的
 * @author guosg
 *
 */
public class SPIBean {
	private String name;
	private String def;
	private Boolean singleton = false;;
	
	/**
	 * 
	 * @param type
	 */
	public SPIBean(){
		
	}
	
	/**
	 * 
	 * @param type
	 */
	public SPIBean(Class<?> type){
		parse(type);
	}
	
	/**
	 * 
	 * @param type
	 */
	public SPIBean(SPI  spi){
		parse(spi);
	}
	
	public void parse(Class<?> type){
		SPI spi = AnnotationUtils.getAnnotation(type, SPI.class);
		if(spi==null){
			throw new GExtensionException(type+"没有配置SPI的annotation");
		}
		parse(spi);
	}
	
	public void parse(SPI  spi){
		if(spi==null){
			throw new NullPointerException ("spi不能为空");
		}
		name=spi.name();
		def=spi.def();
		singleton=spi.singleton();
	}
	
	/**
	 * 获得扩展点的名称
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 获得扩展点的默认配置
	 * @return
	 */
	public String getDef(){
		return def;
	}
	
	/**
	 * 是否是单例
	 * @return
	 */
	public Boolean getSingleton(){
		return singleton;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public void setSingleton(Boolean singleton) {
		this.singleton = singleton;
	}
	
	
	
	
}
