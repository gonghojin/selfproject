package org.hojin.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
/*	@Before("execution(* org.hojin.controller.*Controller.*(..))")
	public void startLog(JoinPoint jp){
		logger.info("-------------------");
		logger.info("-------------------");
		logger.info(Arrays.toString(jp.getArgs()));
		logger.info(jp.getSignature().getName());
		logger.info("-------------------");
		
	}*/
	
	/*@Around("execution(* org.hojin.controller.*Controller.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable{
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		logger.info(pjp.getSignature().getName());
		
		Object result = pjp.proceed();
		
		return result;
	}*/
}
