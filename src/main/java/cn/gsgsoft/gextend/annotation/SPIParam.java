package cn.gsgsoft.gextend.annotation;

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
	 * 配置项的名称
	 * @return
	 */
	public String value() default "";
	/**
	 * 是否需要监听配置的变化
	 * 如果简体配置的变化，需要实现ConfigListener接口
	 * @return
	 */
	public boolean change() default false;
}
