package com.java.dubbo.my.framework.protocol.auto;

import java.lang.annotation.*;

/**
 * 定义包扫描注解
 *
 * @author xuweizhi
 * @since 2021/05/25
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AutoComponent {

    /**
     * 注册别名，默认为当前类全类名路径
     */
    String name() default "";
}
