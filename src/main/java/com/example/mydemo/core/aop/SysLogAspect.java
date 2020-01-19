package com.example.mydemo.core.aop;

import com.example.mydemo.core.bo.SysLogBO;
import com.example.mydemo.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Pointcut("@annotation(com.example.mydemo.core.annotations.SysLog)")
    public void syslogPointCut() {
    }

    @Around("syslogPointCut()")
    public Object syslogAfter(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret = joinPoint.proceed();
        SysLogBO sysLogBO = new SysLogBO();
        sysLogBO.setCreateDate(dateFormat.format(new Date()));
        String className = joinPoint.getTarget().getClass().getName();
        sysLogBO.setClassName(className);
        String methodName = joinPoint.getSignature().getName();
        sysLogBO.setMethodName(methodName);
        Object[] args = joinPoint.getArgs();
        sysLogBO.setParams(args.toString());
        sysLogService.save(sysLogBO);
        return ret;
    }

}
