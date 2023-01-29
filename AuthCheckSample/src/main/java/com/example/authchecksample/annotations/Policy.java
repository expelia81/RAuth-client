package com.example.authchecksample.annotations;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 권한을 속성 기반으로 하는 것을 궁극의 목적으로 함.
 *      권한 기반에서 속성 기반으로 바꿀 수 있는 이점
 *          고객 요구사항을 모두 수용가능
 *              ex) 현재 시간을 :: 특정 시간이 지나면 접근 불가
 *              ex) 서버의 위치를 :: 특정 거리 이상 떨어진 위치에서는 권한을 박탈
 *              ex) 특정 교육 이수 여부 :: 교육이 완료된 경우에만 접근 가능
 *              ex) 특정 인스턴스 전원 :: 종료된 상태의 인스턴스에만 접근 가능
 *              ex) 특정 IP :: 특정 IP만 권한 부여
 *              ex) 결재권자의 승인 :: 결재권자 승인이 있는 경우에만 접근 가능
 *              ex) 권한의 레벨링 :: 특정 레벨 이상의 권한을 가진 사람만 접근 가능
 *              ex) 자격증 :: 자격증(혹은 특정 문서)을 제출한 사람만 접근 가능
 *
 *
 * 현재 목표
 * RBAC 기반으로, 각 권한
 */



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)  /** to do    Target을 class로 잡았을 시, 메소드들에 추가한 @과도 동기화가 되는지.
                                 또한, AOP에서 해당 어노테이션이 실행될 때만 실행하게 할 수 있는지. */
public @interface Policy {

    String value();
}


