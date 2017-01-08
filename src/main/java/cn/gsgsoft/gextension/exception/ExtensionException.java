package cn.gsgsoft.gextension.exception;


/**
 * Extension异常
 * @author guosg
 *
 */
public class ExtensionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private String code; // 异常代码

	/**
	 * 构造一个异常信息
	 * @param errCode 异常代码
	 */
	public ExtensionException(String errCode) {
		super(ExceptionMessageHelper.getExMsg(errCode));
		this.code = errCode;
	}
	
    /**
	 * 构造一个异常信息
	 * @param errCode 异常代码
	 * @param message 异常信息
	 */
	public ExtensionException(String errCode, String message) {
		this(errCode, null, message, null);
	}
	
	/**
	 * 构造一个异常信息
	 * @param errCode 异常代码
	 * @param cause 异常来源
	 */
	public ExtensionException(String errCode, Throwable cause) {
		this(errCode, null, null, cause);
	}
	
    /**
	 * 构造一个异常信息
	 * @param errCode 异常代码
	 * @param params 要替换到异常常量信息中的自定义消息
	 */
	public ExtensionException(String errCode, Object[] params) {
		this(errCode, params, null, null);
	}
	
	/**
	 * 构造一个异常信息
	 * @param errCode 异常代码
	 * @param message 异常信息
	 * @param cause 异常来源
	 */
	public ExtensionException(String errCode, String message, Throwable cause) {
		this(errCode, null, message, cause);
	}
	
	/**
	 * 构造一个异常信息，这个构造的Exception将不会将errCode转成消息。
	 * @param errCode 异常代码
	 * @param message 异常信息
	 * @param cause 异常来源
	 * @param getMsg 
	 */
	public ExtensionException(String errCode, String message, Throwable cause,boolean getMsg) {
		super(message,cause);
		code=errCode;
	}
	
	
	/**
	 * 构造一个异常信息
	 * @param errCode 异常代码
	 * @param params 要替换到异常常量信息中的自定义消息
	 * @param cause 异常来源
	 */
	public ExtensionException(String errCode, Object[] params, Throwable cause) {
		this(errCode, params, null, cause);
	}
	
	/**
	 * 构造一个异常信息
	 * @param errCode 异常代码
	 * @param params 要替换到异常常量信息中的自定义消息
	 * @param cause 异常来源
	 */
	public ExtensionException(String errCode, Object[] params,String message,Throwable cause) {
		super(buildMsg(errCode, params, message), cause);
		this.code = errCode;
	}
	/**
	 * 构建异常消息
	 * @param errCode
	 * @param params
	 * @param message
	 * @return
	 */
	private static String buildMsg(String errCode, Object[] params,String message){
		String msg = null;
		if(params!=null){
			msg = ExceptionMessageHelper.getExMsg(errCode, params);
		}else{
			msg = ExceptionMessageHelper.getExMsg(errCode);
		}
		if(message!=null && !"".equals(message)){
			msg = msg+":"+message;
		}
		return msg;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
