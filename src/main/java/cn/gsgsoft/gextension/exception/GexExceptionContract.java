package cn.gsgsoft.gextension.exception;

public abstract class GexExceptionContract {
	/**
	 * {0}不是一个SPI接口
	 */
	public static final String GEX_000001="GEX_000001";
	
	/**
	 * 配置{0}文件加载异常
	 */
	public static final String GEX_000002="GEX_000002";
	
	/**
	 * 配置文件加载异常
	 */
	public static final String GEX_000003="GEX_000003";
	
	/**
	 * GEX_000004={0}的实现不止一个，不能直接通过getExtension(Class<T> type)获取实现
	 */
	public static final String GEX_000004="GEX_000004";
	
	/**
	 * GEX_000005=没有对应spiName {0}没有任何的实现
	 */
	public static final String GEX_000005="GEX_000005";
	
	/**
	 * GEX_000006=扩展点{0}的实现 {1}的类 {2} 没有找到对应的的接口
	 */
	public static final String GEX_000006="GEX_000006";
	
	/**
	 * "实现"+o.getClass()+"没有实现SPI接口"+spiBean.getType()
	 */
	public static final String GEX_000007="GEX_000007";
	
	/**
	 * GEX_000008=扩展点{0}的实现{1}的实现类{2}初始化异常"
	 */
	public static final String GEX_000008="GEX_000008";
	
	/**
	 * GEX_000009=多实现的扩展点SPIParam.defaultIpml不允许＝true
	 */
	public static final String GEX_000009="GEX_000009";
	
	/**
	 * GEX_000010=扩展点{0}的实现{1}的实现类{2}的方法{3}异常
	 */
	public static final String GEX_000010="GEX_000010";
	
	/**
	 * GEX_000011=对象{0}的方法{1}填入参数{2}
	 */
	public static final String GEX_000011="GEX_000011";
	
	/**
	 * GEX_000012={0}是一个接口不能实例化
	 */
	public static final String GEX_000012="GEX_000012";
	
	/**
	 * GEX_000013={0}是一个虚拟方法不能实例化
	 */
	public static final String GEX_000013="GEX_000013";
	
	/**
	 * GEX_000014={0}没有无参的构造方法
	 */
	public static final String GEX_000014="GEX_000014";
	
	/**
	 * GEX_000015={0}没有找到
	 */
	public static final String GEX_000015="GEX_000015";
	
	/**
	 * GEX_000016=数据类型转换异常
	 */
	public static final String GEX_000016="GEX_000016";
	
	/**
	 * GEX_000017=请先使用ExtensionContextBuiler创建一个Context
	 */
	public static final String GEX_000017="GEX_000017";
}
