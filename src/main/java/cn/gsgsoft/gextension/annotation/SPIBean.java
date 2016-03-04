package cn.gsgsoft.gextension.annotation;

import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;
import cn.gsgsoft.gextension.utils.AnnotationUtils;

/**
 * 将spi annation解析成一个可读的
 * @author guosg
 *
 */
public class SPIBean {
	private String name;
	private String def;
	private Boolean multiImp = false;
	private Class<?> type;
	
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
	
	public void parse(Class<?> type){
		this.type=type;
		SPI spi = AnnotationUtils.getAnnotation(type, SPI.class);
		if(spi==null){
			throw new ExtensionException(GexExceptionContract.GEX_000001,new Object[]{type});
		}
		parse(spi);
	}
	
	public void parse(SPI  spi){
		if(spi==null){
			throw new NullPointerException ("spi不能为空");
		}
		name=spi.name();
		def=spi.def();
		multiImp=spi.multiImp();
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
	
	public void setName(String name) {
		this.name = name;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public Boolean getMultiImp() {
		return multiImp;
	}

	public void setMultiImp(Boolean multiImp) {
		this.multiImp = multiImp;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}
	
}
