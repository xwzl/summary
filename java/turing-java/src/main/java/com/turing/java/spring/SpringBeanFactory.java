package com.turing.java.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * SpringBeanFactory
 *
 * @Description
 * @date 2024-06-04 22:27 星期二 summary
 */
@Component
public class SpringBeanFactory implements FactoryBean<Mark> {

    @Override
    public Mark getObject() throws Exception {
        Mark mark = new Mark();
        mark.setContent("hello spring FactoryBean");
        return mark;
    }

    @Override
    public Class<?> getObjectType() {
        return Mark.class;
    }
}
