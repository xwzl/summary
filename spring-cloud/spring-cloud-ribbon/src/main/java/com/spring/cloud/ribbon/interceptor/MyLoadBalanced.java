package com.spring.cloud.ribbon.interceptor;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 */
@Documented
@Inherited
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier  // 限定符
public @interface MyLoadBalanced {
}
