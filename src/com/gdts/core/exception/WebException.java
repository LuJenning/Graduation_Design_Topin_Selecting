package com.gdts.core.exception;
/**
 * web异常类封装action的异常信�?
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ WebException.java 2015-7-8 下午11:47:14
 */
public class WebException extends BaseRuntimeException {

	private static final long serialVersionUID = 343429387410330920L;

	public WebException(String msg) {
		super(msg);

	}

	public WebException(String msg, Throwable cause) {
		super(msg, cause);

	}

}
