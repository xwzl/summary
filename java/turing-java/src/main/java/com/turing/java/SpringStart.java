package com.turing.java;

import com.turing.java.spring.Mark;
import com.turing.java.spring.SpringBeanLife;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringStart
 *
 * @Description
 * @date 2024-06-04 21:28 星期二 summary
 */
@ComponentScan
public class SpringStart {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringStart.class);
        //applicationContext.getBean(SpringBeanLife.class);
        applicationContext.getBean("springBeanLife");
        Mark mark =applicationContext.getBean(Mark.class);
        System.out.println(mark.getContent());
    }
}
