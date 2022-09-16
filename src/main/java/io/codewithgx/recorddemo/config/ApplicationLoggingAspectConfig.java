package io.codewithgx.recorddemo.config;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.ZoneId;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import io.codewithgx.recorddemo.model.ApplicationLog;
import io.codewithgx.recorddemo.service.ApplicationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 16/09/2022
 */

@RequiredArgsConstructor
@Configuration
@Aspect
@Slf4j
public class ApplicationLoggingAspectConfig {
    
    private final ApplicationLogService applicationLogService;

    @Pointcut("@annotation(io.codewithgx.recorddemo.utils.Log)")
    public void pointcut() {
    }

    @Around("execution(* io.codewithgx.recorddemo.rest.*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object result = null;
        final long beginTime = System.currentTimeMillis();

        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        saveLog(joinPoint, beginTime);
        return result;
    }

    private void saveLog(final ProceedingJoinPoint joinPoint, final long time) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        Method method = methodSignature.getMethod();

        ApplicationLog applicationLog = new ApplicationLog();

        String className = joinPoint.getTarget().getClass().getName();

        String methodName = methodSignature.getName();

        applicationLog.setMethod(className + "." + methodName + "()");

        final Object[] args = joinPoint.getArgs();
        final LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer
                = new LocalVariableTableParameterNameDiscoverer();

        final String[] parameterNames = localVariableTableParameterNameDiscoverer.getParameterNames(method);

        if (args != null && parameterNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append(" ").append(parameterNames[i]).append(": ").append(args[i]);
            }

            applicationLog.setParams(params.toString());
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        applicationLog.setEndPoint(request.getServletPath());
        applicationLog.setUsername("Test User");

        applicationLog.setRequestTime(Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC")).toLocalDateTime());
        applicationLog.setOperation(request.getMethod());

        applicationLogService.saveApplicationLog(applicationLog);
    }

    @Before("execution(* io.codewithgx.recorddemo.repo.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Before {}", methodName);
    }

}
