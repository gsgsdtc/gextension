package cn.gsgsoft.gextend.annotation;

import java.lang.reflect.Method;
import java.util.List;

import cn.gsgsoft.gextend.utils.AnnotationUtils;

/**
 * SPIParam的信息
 * 
 * @author guosg
 *
 */
public class SPIParamBean {
	private Method method;
	private String name;
	private boolean change;
	private Class<?> valueType;

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
		valueType = method.getParameterTypes()[0];
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
	
	

}
