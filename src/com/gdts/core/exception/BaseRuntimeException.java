package com.gdts.core.exception;

import org.springframework.core.NestedRuntimeException;
/**
 *  The basic BaseRuntimeException extends NestedRuntimeException from Spring framework
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ BaseRuntimeException.java 2015-7-8 下午10:07:00
 */
public abstract class BaseRuntimeException extends NestedRuntimeException {

	private static final long serialVersionUID = -693646241406754585L;
	public BaseRuntimeException(String msg) {
		super(msg);
		
	}
	public BaseRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
		
	}

	

}
