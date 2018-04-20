package com.hj.aoplib.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Administrator on 2018/4/20.
 */
@Aspect
public class ExceptionAspect {

    @Pointcut("handler(Exception)")
    public void handlerException(){}
    @Pointcut("within(*..*Activity+)")
    public void codeInActivity() {}

    @Before("codeInActivity() && handlerException()")
    public void beforeException(JoinPoint joinPoint) {
        if (joinPoint != null) {
            Signature signature =joinPoint.getSignature();
            String className = signature.getDeclaringType().getSimpleName();
            Log.d(className, "beforeException");
        }
    }
}
