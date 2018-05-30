package com.my.q.three.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

@Aspect
public class TimeMeasureAspect {
	
	@Around("execution(* *(..))")
	public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

		StopWatch watch = new StopWatch();
		watch.start(joinPoint.getTarget().getClass().getName() + "." +joinPoint.getSignature().getName());
		joinPoint.proceed();
		watch.stop();
		TaskInfo[] listofTasks = watch.getTaskInfo();
		for (TaskInfo task : listofTasks) {
		    System.out.format("Method Name : [%s] took [%d] milliseconds to run\n", task.getTaskName(), task.getTimeMillis());
		}

	}
}