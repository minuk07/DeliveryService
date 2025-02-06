package org.delivery.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//어노테이션 생성

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) //실행중에 적용
@Service
public @interface Business { //@Business가 달린 어노테이션도 스프링 빈으로 등록 -> 비지니스로직을 처리하기 위한 어노테이션

    @AliasFor(annotation = Service.class) //속성 재정의 -> 똑같이 빈 이름 지정
    String value() default "";
}
