package com.example.demo.aop;

import com.example.demo.annotations.Policy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class AuthCheck {
    @Around("@annotation(com.example.demo.annotations.Policy)")
    public void checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Policy policy = method.getAnnotation(Policy.class);
        log.info(policy.value());


        Boolean pass = false;
        Arrays.stream(joinPoint.getArgs());
//                .forEach(value -> pass = value.equals(policy.value())? true : pass);

        if (pass)
        joinPoint.proceed();
    }

    private boolean policyCheck(Object value){
        if(value.getClass().equals(String.class)){
            if (value.equals("test")) return true;
        }
        return false;
    }
}
