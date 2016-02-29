package cn.gsgsoft.gextension.logger;

import org.junit.Test;

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
