package cn.gsgsoft.gextension.logger.jcl;

import java.io.File;

import org.apache.commons.logging.LogFactory;

import cn.gsgsoft.gextension.logger.Level;
import cn.gsgsoft.gextension.logger.Logger;
import cn.gsgsoft.gextension.logger.LoggerAdapter;
import cn.gsgsoft.gextension.logger.LoggerFactory;


public class JclLoggerAdapter implements LoggerAdapter{
	 
	public JclLoggerAdapter() {
	}

	public Logger getLogger(String key) {
		return new JclLogger(LogFactory.getLog(key));
	}

    public Logger getLogger(Class<?> key) {
        return new JclLogger(LogFactory.getLog(key));
    }

    private Level level;
    
    private File file;

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
   
}
