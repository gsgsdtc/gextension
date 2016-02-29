package cn.gsgsoft.gextension.annotation;

import java.lang.reflect.Method;
import java.util.Collection;

import cn.gsgsoft.gextension.utils.AnnotationUtils;
import cn.gsgsoft.gextension.utils.BeanUtils;

/**
 * SPIParam的信息
 * 
 * @author guosg
 *
 */
public class SPIParamBean {
	private Method method; //对应的set方法
	private String name;	//配置项的名称，如果没有填写默认使用属性的名称
	private boolean change; //是否需要监听变化
	private Class<?> valueType;	//属性的类型
	private boolean simpleValueType = true; //是否是一个简单的对象
	private boolean multiImpl;//是否是填入多种实现
	private boolean defaultIpml;
	

	public SPIParamBean(Method method) {
		parse(method);
	}

	public SPIParamBean(SPIParam spiParam, Method method) {
		parse(spiParam, method);
	}

	public void parse(Method method) {
		SPIParam spip = AnnotationUtils.findAnnotation(method, SPIParam.class);
		parse(spip, method);
	}
	
	public void parse(SPIParam spiParam, Method method) {
		this.method = method;
		this.defaultIpml = spiParam.defaultIpml();
		valueType = spiParam.spiType();
		
		if(valueType.equals(SPI.class))
			valueType = method.getParameterTypes()[0];
		
		if(!BeanUtils.isSimpleValueType(valueType)){
			simpleValueType= false;
		}
		
		if(Collection.class.equals(method.getParameterTypes()[0])){
			multiImpl = true;
		}
		
		this.name = spiParam.value();
		if (name == null || name.length() == 0) {
			name = method.getName();
			name=name.substring(3);
			name=captureName(name);
		}
		
		this.change = spiParam.change();
	}

	private String captureName(String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

	/**
	 * 参数的名称是什么
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 是否监听变化
	 * 
	 * @return
	 */
	public boolean isChange() {
		return change;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setChange(boolean change) {
		this.change = change;
	}
	
	/**
	 * 获得值的类型
	 * @return
	 */
	public Class<?> getValueType() {
		return valueType;
	}

	public void setValueType(Class<?> valueType) {
		this.valueType = valueType;
	}

	public boolean isSimpleValueType() {
		return simpleValueType;
	}

	public void setSimpleValueType(boolean simpleValueType) {
		this.simpleValueType = simpleValueType;
	}

	public boolean isMultiImpl() {
		return multiImpl;
	}

	public void setMultiImpl(boolean multiImpl) {
		this.multiImpl = multiImpl;
	}

	public boolean isDefaultIpml() {
		return defaultIpml;
	}

	public void setDefaultIpml(boolean defaultIpml) {
		this.defaultIpml = defaultIpml;
	}
	
	

}
