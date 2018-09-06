package com.gdts.core.model;

import java.io.Serializable;
/**
 * 模型基类，所有子类继承于它并覆盖toString方法
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ BaseModel.java 2015-7-9 上午08:33:16
 */
public abstract class BaseModel implements Serializable{
	private static final long serialVersionUID = -6388545626195096054L;
	public abstract String toString();
}
