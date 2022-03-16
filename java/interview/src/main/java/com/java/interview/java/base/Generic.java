package com.java.interview.java.base;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 泛型演示
 *
 * @author xuweizhi
 * @since 2022/03/15 13:58
 */
@Slf4j
public class Generic {

    @Data
    public static class Context {
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Dog extends Context {
        private int age;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class Cat extends Context {
        private int age;
    }


    public static void main(String[] args) {

        // 未限定泛型边界
        List<GenericTemplate<? extends Object>> genericList = Lists.newArrayList();
        DogTemplate dogTemplate = new DogTemplate();
        CatTemplate catTemplate = new CatTemplate();

        genericList.add(dogTemplate);
        genericList.add(catTemplate);

        genericList.forEach(genericTemplate -> {
            log.info(genericTemplate.getClass().toString());
            Object obj = createGeneric(genericTemplate);
            log.info(obj.getClass().toString());
        });

        // 限定泛型边界
        List<LimitGeneric<? extends Context>> limitList = Lists.newArrayList();
        CatLimit catLimit = new CatLimit();
        DogLimit dogLimit = new DogLimit();
        limitList.add(catLimit);
        limitList.add(dogLimit);

        limitList.forEach(limitGeneric -> {
            log.info(limitGeneric.getClass().toString());
            Context context = createLimit(limitGeneric);
            log.info(context.getClass().toString());
        });


    }

    public static <T> T createGeneric(GenericTemplate<T> genericTemplate) {
        return genericTemplate.create();
    }


    public static <T extends Context> T createLimit(LimitGeneric<T> limitGeneric) {
        return limitGeneric.createContext();
    }

    public static class DogTemplate implements GenericTemplate<Dog> {

        @Override
        public Dog create() {
            return new Dog();
        }
    }

    public static class CatTemplate implements GenericTemplate<Cat> {

        @Override
        public Cat create() {
            return new Cat();
        }
    }

    /**
     * 未限定泛型边界
     *
     * @param <T>
     */
    public static interface GenericTemplate<T> {

        /**
         * 创建 T
         *
         * @return 环境
         */
        T create();
    }

    /**
     * 限定泛型边界
     *
     * @param <S> //
     */
    public static interface LimitGeneric<S extends Context> {

        /**
         * 创建上下文
         *
         * @return //
         */
        S createContext();
    }

    public static class CatLimit implements LimitGeneric<Cat> {

        @Override
        public Cat createContext() {
            return new Cat();
        }
    }

    public static class DogLimit implements LimitGeneric<Dog> {

        @Override
        public Dog createContext() {
            return new Dog();
        }
    }


}
