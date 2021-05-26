package com.java.dubbo.my.framework.protocol.auto;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @date 2019/04/14 0:30
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@AutoComponent
public @interface AutoService {

    String name() default "";

}
