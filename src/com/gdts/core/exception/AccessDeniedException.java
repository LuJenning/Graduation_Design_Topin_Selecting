package com.gdts.core.exception;
/**
 * 数据访问权限不足异常�?
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ AccessDeniedException.java 2015-7-8 下午11:34:42
 */
public class AccessDeniedException extends BaseRuntimeException {

	private static final long serialVersionUID = -3775777284874712238L;

	public AccessDeniedException(String msg) {
		super(msg);
		
	}

}
