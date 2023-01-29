package com.example.authchecksample.aop;

import com.example.authchecksample.annotations.Policy;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.plugins.RSocketInterceptor;
import jdk.jfr.MetadataDefinition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.rsocket.server.RSocketServer;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import org.springframework.messaging.rsocket.service.RSocketExchange;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthCheck {

    private final RSocketStrategies strategies;

    @Value("${secret.key}")
    String secretKey;

    @Around("@annotation(com.example.authchecksample.annotations.Policy)")
    public void checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        /* 요청자가 가진 권한 획득 -> 오버헤드 가급적 줄이기 위해 HashSet 사용 */
        Set<String> permissions = (HashSet<String>)joinPoint.getArgs()[0];

        /* 옳지 않은 요청인 경우.  추후 핸들링 추가 */
        if (!permissions.contains(secretKey)) throw new RuntimeException("secretKey가 올바르지 않습니다");

        /* 타겟 메소드가 요구하는 권한 획득 */
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String requiredPermission = method.getAnnotation(Policy.class)
                                          .value();

        /* 권한 검증. 실패시 에러 */
        if(permissions.contains(requiredPermission)){
            joinPoint.proceed();
        } else {
            throw new RuntimeException("권한이 없는 요청입니다.");
        }

    }
}
