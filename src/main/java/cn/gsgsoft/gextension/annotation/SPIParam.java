package cn.gsgsoft.gextension.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展点的参数<p/>
 * 可以通过配置自动注入配置
 * <pre><code>
 * -@SpiParam
 * setHost(String host);
 * </code></pre>
 * META-INF/appconfig.properties
 *<pre><code>
 * ldtm.transport.hrift.host=192.168.1.1:2020
 * </code><pre>
 * @author guosg
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface SPIParam {
	
	/**
	 * 配置项的名称，如果没有填写默认使用属性的名称<p>
	 * <br>
	 * 这个参数有两个作用
	 * <ul>
	 * 	<li>根据名称注入特定的实现<br>
	 *  在一下两种情况是这个参数将被忽略<br>
	 *  1.如果属性的类型是Collection时这个参数将被忽略，此时将会注入所有实例化的思想。<br>
	 *  2.如果defaultIpml＝true是这个参数将被忽略，将会注入默认的实现<br>
	 *  可以在使用SPI.def设置的默认是实现也可以是appconfig.xml设置的实现，优先appconfig.xml设置的实现。
	 * 	<li>注入appconfig.xml的简单类型配置<br>
	 * 	如果是一个简单的类型，可以直接注入appconfig.xml配置的参数<br>	
	 * 	appcinfig.xml可以是[扩展点名称].[实现名称].[参数名称]＝[参数]，也可以是[扩展点名称].[参数名称]＝[参数]<br>
	 *  如果配置了两套，那么优先使用第一种配置<br>
	 *  支持的简单类型如下
	 *  <ul>
	 *  	<li>java.lang.String
	 *  	<li>java.util.Date 格式为MM/dd/yyyy HH:mm:ss或MM/dd/yyyy
	 *  	<li>java.math.BigDecimal
	 *  	<li>java.lang.Boolean
	 *  	<li>java.lang.Double
	 *  	<li>java.lang.Float
	 *  	<li>java.lang.Integer
	 *  	<li>java.lang.Long
	 *  	<li>java.lang.Short
	 *    	<li>java.lang.Class
	 *  </ul>
	 * </ul>
	 * @return
	 */
	public String value() default "";
	
	/**
	 * 是否使用SPI的默认实现，默认为true<p>
	 * 这个参数＝true是将会忽略value配置的实现，
	 * @return
	 */
	public boolean defaultIpml() default true;
	
	/**
	 * SPI的接口类<p>
	 * 注入多个实现时使用，注入多实现是属性的类型必须为Collection。例如
	 * <pre>
	 * 
	 * %spiParam(spiType=MockSPIInterface.class)
	 * public void setSpiName(Collection<T> spis);
	 * 
	 * </pre>
	 * <br>
	 */
	public Class<?> spiType() default SPI.class;
	
	/**
	 * 是否需要监听配置的变化
	 * 如果简体配置的变化，需要实现ConfigListener接口
	 * @return
	 */
	public boolean change() default false;
}
