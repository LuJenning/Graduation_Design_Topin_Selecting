package com.gdts.core.context;

import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

public class SpringContextHolder {


	    private static ApplicationContext applicationContext;

	    public static ApplicationContext getApplicationContext() {
	        return applicationContext;
	    }

	    public static void setApplicationContext(ApplicationContext applicationContext) {
	        SpringContextHolder.applicationContext = applicationContext;
	    }
	    
	   /**
	    * 
	    * @param <T>
	    * @param requiredType
	    * @return
	    */
	    public static <T> T getBean(Class<T> requiredType) {
	        Assert.notNull(applicationContext);
	        return applicationContext.getBean(requiredType);
	    }


}
