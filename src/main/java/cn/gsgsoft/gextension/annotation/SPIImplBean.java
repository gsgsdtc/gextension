package cn.gsgsoft.gextension.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.gsgsoft.gextension.utils.AnnotationUtils;

/**
 * spi实现类的annotation参数信息
 * @author guosg
 *
 */
public class SPIImplBean {
	private SPIBean spiBean;
	private List<SPIParamBean> params=new ArrayList<SPIParamBean>();
	private Class<?> implClass;
	
	/**
	 * 
	 * @param type spi的实现类
	 */
	public SPIImplBean(Class<?> type){
		this.implClass=type;
		parse(type);
	}
	
	public void parse(Class<?> type){
		Class<?> iclass=AnnotationUtils.findAnnotationDeclaringClass(SPI.class, type);
		spiBean=new SPIBean(iclass);
		
		for(Method method : type.getMethods()){
			if(isPropertieSetMethod(method)){
				SPIParam spip = AnnotationUtils.findAnnotation(method, SPIParam.class);
				if(spip!=null){
					params.add(new SPIParamBean(spip,method));
				}
			}
		}
	}
	/**
	 * 判断方法是不是一个属性的set方法
	 * @return
	 */
	private boolean isPropertieSetMethod(Method method){
		if(method.getReturnType() != void.class){
			return false;
		}
		if(method.getParameterCount()!=1){
			return false;
		}
		if(!method.getName().startsWith("set")){
			return false;
		}
		return true;
	}
	
	/**
	 * 获得实现类下所有的参数配置信息
	 * @return
	 */
	public List<SPIParamBean> getSPIParamBeans(){
		return params;
	}
	
	/**
	 * 获得spiBean
	 * @return
	 */
	public SPIBean getSpiBean() {
		return spiBean;
	}

	public void setSpiBean(SPIBean spiBean) {
		this.spiBean = spiBean;
	}

	public List<SPIParamBean> getParams() {
		return params;
	}

	public void setParams(List<SPIParamBean> params) {
		this.params = params;
	}

	public Class<?> getImplClass() {
		return implClass;
	}

	public void setImplClass(Class<?> implClass) {
		this.implClass = implClass;
	}
	
	
}
