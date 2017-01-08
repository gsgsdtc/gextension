package cn.gsgsoft.gextension.exception;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.gsgsoft.gextension.utils.PropertiesLoaderUtils;



public class ExceptionMessageHelper {
	private static final String EXCEPTION_PATH="META-INF/exceptionMessage.properties";
	private static Logger logger = LoggerFactory.getLogger(ExceptionMessageHelper.class);
	private static Properties properties = null;

	static {
		try {
			properties = PropertiesLoaderUtils.loadAllProperties(EXCEPTION_PATH,ExceptionMessageHelper.class.getClassLoader());
		} catch (IOException e) {
			logger.error("异常消息初始化异常"+e.getMessage(),e);
		}
	}
	
	/**
	 * 获得一个加载了classpath:META-INF/exceptionMessage.properties的所有的文件的properties
	 * @return
	 */
	public static Properties getProperties() {
		if(properties == null){
			properties = new Properties();
		}
		return properties;
	}

	/**
	 * 根据异常代码获得异常消息
	 * @param code
	 * @return
	 */
	public static String getExMsg(String code) {
		if (null == code) {
			return "";
		}
		
		String msg = null;
		try {
			msg = getProperties().getProperty(code);
			if (null == msg) {
				msg =code+ ":未知异常代码" ;
			} else {
				msg = code + ":" + msg;
			}
			 msg = new
			 String(getProperties().getProperty(code));
		} catch (Exception e) {
			logger.error("获取异常码"+code+"异常："+e.getMessage(),e);
			msg = code+":为定义异常码";
		} 
		return msg;
	}

	/**
	 * 通过错误代码来获取错误消息 当有传入args时，将会替换“测试{0}测试{1}测试{2}”中的“{x}”(X为从0开始的整数)
	 * 
	 * @param code
	 *            错误代码
	 * @param args
	 *            要替换“{x}”(x为从0开始的整数)的值
	 * @return 错误消息
	 */
	public static String getExMsg(String code, Object[] args) {
		String msg = getExMsg(code);
		msg = MessageFormat.format(msg, args);
		return msg;
	}
}
