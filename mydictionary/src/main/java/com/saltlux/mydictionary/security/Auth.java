package com.saltlux.mydictionary.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME) // 인터셉터가 사용하는 어노테이션
@Target({ METHOD, TYPE })
public @interface Auth {
	public String role() default "user"; // 항상 디폴트
}
