package com.club.mileage.triple.aop;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Component
@Aspect
@Slf4j
public class LoggerAop {
		@Pointcut("execution(* com.club.mileage.triple.controller.*Controller.*(..)) || execution(* com.club.mileage.triple.service.*ServiceImpl.*(..)) || execution(* com.club.mileage.triple.mapper.*Mapper.*(..))")
		private void loggerTarget() { }
		
		@Around("loggerTarget()")
		public Object logPrinter(ProceedingJoinPoint joinPoint) throws Throwable {
			String type = "";
			String className = joinPoint.getSignature().getDeclaringTypeName();
			String methodName = joinPoint.getSignature().getName();
			String args = Arrays.toString(joinPoint.getArgs());
			if (className.indexOf("Controller") > 0) {
			type = "Controller";
			} else if (className.indexOf("Service") > 0) {
			type = "Service";
			} else if (className.indexOf("Mapper") > 0) {
			type = "Mapper";
			}
			log.debug("[" + type + "] " + className + "." + methodName + "(" + args + ")");
			return joinPoint.proceed();
		}

}
