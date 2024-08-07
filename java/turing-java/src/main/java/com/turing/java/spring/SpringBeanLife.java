package com.turing.java.spring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * SpringBeanLife
 *
 * @Description
 * @date 2024-06-04 21:25 星期二 summary
 */
@Component
public class SpringBeanLife implements BeanNameAware, BeanFactoryAware, InitializingBean, BeanClassLoaderAware/*, InstantiationAwareBeanPostProcessor*/, MergedBeanDefinitionPostProcessor, BeanFactoryPostProcessor {

    @Autowired
    private TestA testA;

    @Resource
    @Qualifier("testB")
    private TestB testB;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("setBeanClassLoader");
    }

    public void initMethod(){
        System.out.println("initMethod");
    }

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        if(beanName.equals("springBeanLife")){
            beanDefinition.setLazyInit(true);
            System.out.println("postProcessMergedBeanDefinition");
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition springBeanLife = beanFactory.getBeanDefinition("springBeanLife");
        springBeanLife.setLazyInit(true);
    }



}
