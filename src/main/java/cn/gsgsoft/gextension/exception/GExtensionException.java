package cn.gsgsoft.gextension.exception;

/**
 * 没有这个Annotation的异常
 * @author guosg
 *
 */
public class GExtensionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GExtensionException() {
		super();
	}

	public GExtensionException(String message) {
		super(message);
	}

	public GExtensionException(String message, Throwable cause) {
		super(message, cause);
	}

	public GExtensionException(Throwable cause) {
		super(cause);
	}

	protected GExtensionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
