package com.example.authchecksample.aop;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.plugins.RSocketInterceptor;
import org.springframework.boot.autoconfigure.security.rsocket.RSocketSecurityAutoConfiguration;
import org.springframework.messaging.rsocket.MetadataExtractor;
import org.springframework.messaging.rsocket.service.RSocketExchange;

import java.util.HashSet;
import java.util.Set;

public class SampleInterceptor implements RSocketInterceptor {
    @Override
    public RSocket apply(RSocket rSocket) {
        int i = 0;
        if (i==0){
            throw new RuntimeException("인터셉터 동작하긴 하네요");
        }

        return rSocket;
    }
}
