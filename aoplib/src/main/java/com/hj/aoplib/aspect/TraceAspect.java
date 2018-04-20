package com.hj.aoplib.aspect;

import android.util.Log;

import com.hj.aoplib.StopWatch;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Administrator on 2018/4/20.
 */
@Aspect
public class TraceAspect {
    private static final String POINTCUT_METHED = "execution(@com.hj.aoplib.annotation.DebugTrace * *(..))";//含DebugTrace注解的方法，任意返回值，任意名字，任意参数
    private static final String POINTCUT_CONSTRUCTOR = "execution(@com.hj.aoplib.annotation.DebugTrace *.new(..))";//含DebugTrace注解的类，构造函数，任意参数

    @Pointcut(POINTCUT_METHED)
    public void methodAnnotatedWithDebugTrace() {}
    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedWithDebugTrace() {}

    @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedWithDebugTrace()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        if (joinPoint != null) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String className = methodSignature.getDeclaringType().getSimpleName();
            String methodName = methodSignature.getMethod().getName();

            Log.d(className, methodName + "weaveJoinPoint");
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object obj = joinPoint.proceed();
            stopWatch.stop();
            //记录方法耗时并输出
            Log.d(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));
            return obj;
        }
        return null;
    }

    private static String buildLogMessage(String methodName, long methodDuration) {
        StringBuilder message = new StringBuilder();
        message.append("耗时 --> ");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(methodDuration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }
}
