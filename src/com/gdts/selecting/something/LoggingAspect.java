package com.gdts.selecting.something;

import org.aspectj.lang.annotation.Aspect;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
/**
 * 切面类
 * @author 陆建宁
 *
 */
@Component
@Aspect
public class LoggingAspect {
	private Logger logger=Logger.getLogger(LoggingAspect.class);
	// execution(* com.gdts.selecting.service..*.*(..))
	// execution(* com.gdts.selecting.service.LoginServiceImpl.*(..))

	@Pointcut("execution (* com.gdts.selecting.service..*.*(..))")
	public void allMethods() {
	}

	@Before("allMethods()")
	public void doBefore(JoinPoint joinPoint) {
		String logStrOne = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String logStrTwo = joinPoint.getSignature().getName();
		logger.info("【前置通知】:"+logStrOne+"类中的"+logStrTwo+"方法开始执行了!");
	}
	@After("allMethods()")
	public void doAfter(JoinPoint joinPoint){
		String logStrOne = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String logStrTwo = joinPoint.getSignature().getName();
		logger.info("【后置通知】:"+logStrOne+"类中的"+logStrTwo+"方法正常，并成功返回!");
	}
	@AfterReturning(value="allMethods()",returning="result")
	public void doAfterReturning(JoinPoint joinPoint,Object result){
		String logStrOne = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String logStrTwo = joinPoint.getSignature().getName();
		logger.info("【增强通知】:"+logStrOne+"类中的"+logStrTwo+"方法正常结束了,"+"返回值是:"+result);
	}
	
	@AfterThrowing(value="allMethods()",throwing="expect")
	public void doAfterThrowing(JoinPoint joinPoint,Exception expect){
		String logStrOne = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String logStrTwo = joinPoint.getSignature().getName();
		logger.info("【异常通知】:"+logStrOne+"类中的"+logStrTwo+"方法执行遇到了异常,"+"异常是:"+expect);
	}
	
	
}
