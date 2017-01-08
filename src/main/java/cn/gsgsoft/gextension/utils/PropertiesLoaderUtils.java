package cn.gsgsoft.gextension.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * properties加载工具类
 * @author guosg
 *
 */
public class PropertiesLoaderUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesLoaderUtils.class);
	/**
	 * Load all properties from the specified class path resource
	 * (in ISO-8859-1 encoding), using the given class loader.
	 * <p>Merges properties if more than one resource of the same name
	 * found in the class path.
	 * @param resourceName the name of the class path resource
	 * @param classLoader the ClassLoader to use for loading
	 * (or {@code null} to use the default class loader)
	 * @return the populated Properties instance
	 * @throws IOException if loading failed
	 */
	public static Properties loadAllProperties(String resourceName, ClassLoader classLoader) throws IOException {
		ClassLoader clToUse = classLoader;
		if (clToUse == null) {
			clToUse = PropertiesLoaderUtils.class.getClassLoader();
		}
		Properties props = new Properties();
		Enumeration<URL> urls = clToUse.getResources(resourceName);
		while (urls.hasMoreElements()) {
			
			URL url = (URL) urls.nextElement();
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();
			if(logger.isDebugEnabled()){
				logger.debug("加载配置文件"+url.toString());
			}
			try {
				
				props.load(is);
			}
			finally {
				is.close();
			}
		}
		return props;
	}
	/**
	 * 加载目录下的所有properties配置文件
	 * @param dictoryName 
	 * @param classLoader
	 * @return
	 * @throws IOException
	 */
	public static Properties loadDictoryAllProperties(String dictoryName, ClassLoader classLoader) throws IOException{
		ClassLoader clToUse = classLoader;
		if (clToUse == null) {
			clToUse = PropertiesLoaderUtils.class.getClassLoader();
		}
		Properties props = new Properties();
		
		Enumeration<URL> urls = clToUse.getResources(dictoryName);
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			File file = new File(url.getFile());
			if(file.isDirectory()){
				for(File fp :file.listFiles()){
					if(fp.getName().endsWith(".properties")){
						FileInputStream is = new FileInputStream(fp);
						try{
							props.load(is);
						}finally{
							is.close();
						}
					}
				}
			}
		}
		return props;
	}
}
