package cn.gsgsoft.gextension.extension;

import java.math.BigDecimal;

import cn.gsgsoft.gextend.annotation.SPIParam;

/**
 * 
 * @author guosg
 *
 */
public class DefaultMockExtension implements MockExtension{
	private Integer id;
	private String name;
	private Double age;
	private BigDecimal bigDecimal;
	
	public Integer getId() {
		return id;
	}
	
	@SPIParam
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@SPIParam
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getBigDecimal() {
		return bigDecimal;
	}
	@SPIParam
	public void setBigDecimal(BigDecimal bigDecimal) {
		this.bigDecimal = bigDecimal;
	}
	public Double getAge() {
		return age;
	}
	@SPIParam
	public void setAge(Double age) {
		this.age = age;
	}
	
	
	
}
