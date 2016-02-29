package cn.gsgsoft.gextension.annotation;

import cn.gsgsoft.gextension.annotation.SPI;
import cn.gsgsoft.gextension.annotation.SPIParam;

/**
 * mock
 * @author guosg
 *
 */
public class MockSpiImpl implements MockSPIInterface{
	private String name;
	private String password;
	private String role;
	
	/**
	 * 
	 * @param s
	 */
	@SPIParam(value="alias",change=true)
	public void setName(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
	
	@SPIParam
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public void save(){
		
	}
	
	public String save(String s){
		return null;
	}
	
}
