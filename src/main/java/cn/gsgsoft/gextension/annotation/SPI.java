package cn.gsgsoft.gextension.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 扩展点接口的标识。<p/>
 * 声明实现一个扩展点的示例如下</>
 * <pre><code>META-INF/extension/[应用名].properties</code></pre><br/>
 * 配置文件的格式
 * <pre><code>ldtm.transport.thrift=cn.ThriftTransport</code></pre>
 * ldtm.Transport是配置spi的接口名
 * @author guosg
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {
	
	/**
	 * 扩展点的名称<p>
	 * 命名规范<p>
	 * [应用名].[模块名].[扩展点名称]<br>
	 * 模块名可以是0到多个，所有的名称使用小写</br>
	 * 注：这里原来是想要是用默认的[报名].[类]来做扩展名的，可是这样太长了，不好用。
	 * @return
	 */
	String name();
	
	/**
	 * 扩展点的缺省实现的名称。
	 * 此属性必输，在设计一个扩展点是，就应该设计一个默认的实现
	 * @return
	 */
	String def() ;
	
	/**
	 * 是否允许同时有多个实现
	 * @return
	 */
	boolean multiImp() default false;
	
}
