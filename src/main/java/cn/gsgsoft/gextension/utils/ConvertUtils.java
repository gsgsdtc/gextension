package cn.gsgsoft.gextension.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.gsgsoft.gextension.exception.ExtensionException;
import cn.gsgsoft.gextension.exception.GexExceptionContract;


public class ConvertUtils {
	public static final Pattern MMYYDDHHMMSS = Pattern.compile("\\d*/\\d*/\\d* \\d*:\\d*:\\d*");
	public static Date convertDate(String str) throws ParseException{
		if(checkRegText(MMYYDDHHMMSS, str)){
			return (new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).parse(str);
		}else{
			return (new SimpleDateFormat("MM/dd/yyyy")).parse(str);
		}
	}
	public static String toString(Date date){
		return  (new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(date);
	}
	public static String toString4Time(long time){
		return  (new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(new Date(time));
	}
	public static Object convertValue(Class<?> clazz, String value) {
		Object obj = null;
		
		
		try {
			if (String.class.equals(clazz)) {
				obj = value==null?"":value;
			}else if(value == null || value.length() ==0){
				return null;
			}else if (Date.class.equals(clazz)) {
				obj = convertDate(value);
			}  else if (Boolean.class.equals(clazz) || boolean.class.equals(clazz)) {
				obj = new Boolean(value);
			} else if (Double.class.equals(clazz) || double.class.equals(clazz)) {
				obj = new Double(value);
			} else if (Float.class.equals(clazz)  || float.class.equals(clazz)) {
				obj = new Float(value);
			} else if (Integer.class.equals(clazz)  || int.class.equals(clazz)) {
				obj = new Integer(value);
			} else if (Long.class.equals(clazz) || long.class.equals(clazz)) {
				obj = new Long(value);
			} else if (Short.class.equals(clazz) || short.class.equals(clazz)) {
				obj = new Short(value);
			} else if (BigDecimal.class.equals(clazz) ){
				obj = new BigDecimal(value);
			} else if(Class.class.equals(clazz)){
				obj = Class.forName(value);
			}else{
			}
		} catch (Exception e) {
			throw new ExtensionException(GexExceptionContract.GEX_000016,e.getMessage(), e);
		}

		return obj;
	}
	
	public static String serialValue(Class<?> clazz, Object value) {
		String obj = null;
		
		try {
			if (String.class.equals(clazz)) {
				obj = (String) value;
			}else if(value == null){
				return null;
			}else if (Date.class.equals(clazz)) {
				obj = (new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")).format(value);
			} else if (BigDecimal.class.equals(clazz)) {
				obj = value.toString();
			} else if (Boolean.class.equals(clazz)) {
				obj = value.toString();
			} else if (Double.class.equals(clazz)) {
				obj = value.toString();
			} else if (Float.class.equals(clazz)) {
				obj = value.toString();
			} else if (Integer.class.equals(clazz)) {
				obj = value.toString();
			} else if (Long.class.equals(clazz)) {
				obj = value.toString();
			} else if (Short.class.equals(clazz)) {
				obj = value.toString();
			} 
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		return obj;
	}
	
	public static String getRegText(Pattern pattern,String s){
		Matcher m = pattern.matcher(s);
		String code = null;
		if(m.find()){
			code = m.group();
		}
		return code;
	}
	
	public static boolean checkRegText(Pattern pattern,String s){
		Matcher m = pattern.matcher(s);
		return m.matches();
	}
}	
