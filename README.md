# gextension
这个项目是为了一个应用需要有高可扩展性的基础包。他使用了java spi的机制实现
## 配置管理

- **支持多种配置存储方式**
1. 使用propertis文件进行配置
3. 使用zookeeper进行配置
4. 使用spring进行配置

- **支持动态变更配置**
在支持zookeeper配置时，可以监听配置项的变更，当zookeeper的配置变更后，将会进行比对，如果配置项有变更，将会通知监听的对象。

## 建立扩展点
可以通过SPI annotion来建立一个扩展点，扩展点在建立是默认是需要有一个默认的实现的。

**建立一个扩展点的接口**

``` java
package cn.xxx;
import cn.gsgsoft.extension.annotaction.SPI;

@SPI(name="ldtm.transport",def="thrift")
public class CoodinatorTransport {}
``` 
- name的扩展点的名称，命名规范
[应用名].[模块名].[扩展点名称]
模块名可以是0到多个，所有的名称使用小写
- def：是这个接口的默认的实现
- 扩展点的名称是接口的全名

**建立一个扩展点的实现**
建立一个properties文件
``` 
META-INF/extension/ldtm.properties
```

- ldtm是你的应用的名称
- 对实现进行配置

```
ldtm.transport.thrift=cn.xxx.ThriftTransport
```

- ldtm.transport是扩展点的名称
- thrift 扩展点的实现的名称
- cn.xxx.ThriftTransport 扩展点的具体的类路径

**使用扩展点**
``` java
CoodinatorTransport transport =ExtensionUtils.getExtensionLoader().getExtension(CoodinatorTransport.class);
```

获得一个扩展点的思想，这是获得器默认的实现。应用在使用是也可以通过配置来选择一个扩展点的实现

``` 
#META-INF/appconfig.properties
ldtm.transport=netty
```

##自动注入配置项

**在需要注入配置项的方法中，声明是需要注入配置项的**
``` java
@SPIParam("host")
public void setHost(String host)
```
在set方法上什么SPIParam注解，就说明这是需要配置的属性，配置的名称是host,你也可以不制定名称默认名称就是host

**配置配置文件**
``` 
#META-INF/appconfig.properties
ldtm.transport.host=192.168.1.212:2020
```
配置ldtm.transport的扩展点的实现都会自动注入host配置项

## 监听配置项的变化

**设置什么配置项需要监听**
``` java
@SPIParam(change=true)
public void setHost(String host)
```
默认情况下是不进行监听的，设置SPIParam chang=true 表示监听这个配置项的变化。

**思想变化监听接口**
``` java
public interface ConfigListener {
	
	/**
	 * 
	 * @param propertis 发生变化的配置项
	 */
	public void changed(Properties propertis);
}

```
