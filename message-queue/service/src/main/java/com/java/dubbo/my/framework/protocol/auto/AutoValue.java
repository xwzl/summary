package com.java.dubbo.my.framework.protocol.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @since 2021/05/25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoValue {

    /**
     * 用于注入 application.properties 属性值，假如你有指定的话
     */
    String value() default "";
}
