package cn.gsgsoft.gextension.logger;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author guosg
 *
 */
public class TestLoggerFactory {
	private static final Logger logger = LoggerFactory.getLogger(TestLoggerFactory.class.getName());
	
	/**
	 * 
	 */
	@Test
	public void test(){
		logger.debug("hello world!!");
	}
}
