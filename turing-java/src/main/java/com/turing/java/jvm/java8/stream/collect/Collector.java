// package com.turing.java.jvm.java8.stream.collect;
//
// import java.util.Collections;
// import java.util.EnumSet;
// import java.util.Objects;
// import java.util.Set;
// import java.util.function.*;
//
// import java.util.stream.Collectors;
//
// /**
//  * 收集器接口，实现该接口可以定义自己的收集器
//  *
//  * @param < T > 待收集的流元素类型
//  * @param <A> 累加器的类型，累加器用于收集流元素
//  * @param <R> 收集器最终返回的结果类型，结果类型可以就是收集器类型，也可以是其他类型
//  */
// public interface Collector<T, A, R> {
//     /**
//      * @return 返回一个生产者。通过这个生产者的get方法可以获取一个累加器，是一个容器，也可以是对象
//      */
//     Supplier<A> supplier();
//
//     /**
//      * @return 返回一个二元消费者。第一个参数是获取的累加器，第二个参数就是流元素，
//      * 这里需要将流元素应用到累加器中进行各种自定义操作，比如添加、求和等等，最终还是返回一个累加器
//      * 这个返回的累加器作为下一次计算的第一个参数
//      */
//     BiConsumer<A, T> accumulator();
//
//     /**
//      * 并行模式的时候会调用该方法，因为并行模式下可能会将流拆分为多个子流分别计算，可能有多个累加器
//      * 这里需要将累加器的结果进行两两合并等自定义操作，最终会只返回一个累加器
//      *
//      * @return 返回一个二元操作器。将两个累加器合并，返回一个累加器，最终只会返回一个累加器。
//      */
//     BinaryOperator<A> combiner();
//
//     /**
//      * @return 返回一个函数。从累加器中获取要返回的最终数据
//      * 如果累加器就是要返回的数据，那么就不用转换
//      */
//     Function<A, R> finisher();
//
//     /**
//      * 每个收集器实例都有自己的特征，特征是一组描述收集器的对象，框架可以根据特征对收集器的计算进行适当优化
//      *
//      * @return 一组不可变的收集器特征集合，集合元素从内部的Characteristics枚举中选取
//      */
//     Set<Characteristics> characteristics();
//
//     /**
//      * 收集器的特征是一个枚举，描述了流是否可以并行归纳，以及可以使用的优化。
//      */
//     enum Characteristics {
//         /**
//          * 指示此收集器是多线程的，即accumulator支持多线程调用
//          * 这意味着结果容器可以支持与来自多个线程的相同结果容器同时调用的累加器函数
//          * <p>
//          * 如果收集器没有标为UNORDERED，那它仅在用于无序数据源时才可以并行归纳。
//          */
//         CONCURRENT,
//
//         /**
//          * 指示集合操作不承诺保留输入元素的顺序。
//          */
//         UNORDERED,
//
//         /**
//          * 这表明finisher方法返回的函数是一个恒等函数，即输入什么返回什么。
//          * 这种情况下，累加器对象将会直接用作归纳过程的最终结果。
//          */
//         IDENTITY_FINISH
//     }
//
//
//     /**
//      * 返回一个新的收集器实例，默认具有IDENTITY_FINISH的特征
//      *
//      * @param supplier        新收集器的supplier函数
//      * @param accumulator     新收集器的accumulator函数
//      * @param combiner        新收集器的combiner函数
//      * @param characteristics 新收集器的特征集合
//      * @param < T >             待收集的流元素类型
//      * @param <R>             累加器的类型，以及收集器最终返回的结果类型，即是同一个类型
//      * @return 新的累加器实例
//      * @throws NullPointerException 如果任意参数为null
//      */
//     public static <T, R> Collector<T, R, R> of(Supplier<R> supplier,
//                                                BiConsumer<R, T> accumulator,
//                                                BinaryOperator<R> combiner,
//                                                Characteristics... characteristics) {
//         Objects.requireNonNull(supplier);
//         Objects.requireNonNull(accumulator);
//         Objects.requireNonNull(combiner);
//         Objects.requireNonNull(characteristics);
//         //设置特征集合
//         Set<Characteristics> cs = (characteristics.length == 0)
//                 ? Collectors.CH_ID
//                 : Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,
//                 characteristics));
//         //返回一个Collectors内部实现的CollectorImpl收集器实例
//         return new Collectors.CollectorImpl<>(supplier, accumulator, combiner, cs);
//     }
//
//     /**
//      * 返回一个新的收集器实例
//      *
//      * @param supplier        新收集器的supplier函数
//      * @param accumulator     新收集器的accumulator函数
//      * @param combiner        新收集器的combiner函数
//      * @param finisher        新收集器的finisher函数
//      * @param characteristics 新收集器的特征集合
//      * @param < T >             待收集的流元素类型
//      * @param <A>             累加器的类型
//      * @param <R>             以及收集器最终返回的结果类型
//      * @return the new {@code Collector}
//      * @throws NullPointerException if any argument is null
//      */
//     public static <T, A, R> Collector<T, A, R> of(Supplier<A> supplier,
//                                                   BiConsumer<A, T> accumulator,
//                                                   BinaryOperator<A> combiner,
//                                                   Function<A, R> finisher,
//                                                   Characteristics... characteristics) {
//         //null校验
//         Objects.requireNonNull(supplier);
//         Objects.requireNonNull(accumulator);
//         Objects.requireNonNull(combiner);
//         Objects.requireNonNull(finisher);
//         Objects.requireNonNull(characteristics);
//         //设置特征集合
//         Set<Characteristics> cs = Collectors.CH_NOID;
//         if (characteristics.length > 0) {
//             cs = EnumSet.noneOf(Characteristics.class);
//             Collections.addAll(cs, characteristics);
//             cs = Collections.unmodifiableSet(cs);
//         }
//         //返回一个Collectors内部实现的CollectorImpl收集器实例
//         return new Collectors.CollectorImpl<>(supplier, accumulator, combiner, finisher, cs);
//     }
// }
//
//
// /**
//  * 收集器的简单实现类，作为Collectors的内部类
//  *
//  * @param < T > 待收集的流元素类型
//  * @param <A> 累加器的类型，累加器用于收集流元素
//  * @param <R> 收集器最终返回的结果类型，结果类型可以就是收集器类型，也可以是其他类型
//  */
// static class CollectorImpl<T, A, R> implements java.util.stream.Collector<T, A, R> {
//     //保存了传递的函数
//     private final Supplier<A> supplier;
//     private final BiConsumer<A, T> accumulator;
//     private final BinaryOperator<A> combiner;
//     private final Function<A, R> finisher;
//     private final Set<Characteristics> characteristics;
//
//     //两个构造器，接收各种函数
//
//     CollectorImpl(Supplier<A> supplier,
//                   BiConsumer<A, T> accumulator,
//                   BinaryOperator<A> combiner,
//                   Function<A, R> finisher,
//                   Set<Characteristics> characteristics) {
//         this.supplier = supplier;
//         this.accumulator = accumulator;
//         this.combiner = combiner;
//         this.finisher = finisher;
//         this.characteristics = characteristics;
//     }
//
//     CollectorImpl(Supplier<A> supplier,
//                   BiConsumer<A, T> accumulator,
//                   BinaryOperator<A> combiner,
//                   Set<Characteristics> characteristics) {
//         this(supplier, accumulator, combiner, castingIdentity(), characteristics);
//     }
//
//     //下面这些方法被调用的时候，直接返回我们自定义的函数就行了
//
//     @Override
//     public BiConsumer<A, T> accumulator() {
//         return accumulator;
//     }
//
//     @Override
//     public Supplier<A> supplier() {
//         return supplier;
//     }
//
//     @Override
//     public BinaryOperator<A> combiner() {
//         return combiner;
//     }
//
//     @Override
//     public Function<A, R> finisher() {
//         return finisher;
//     }
//
//     @Override
//     public Set<Characteristics> characteristics() {
//         return characteristics;
//     }
// }
//
// /**
//  * Collectors的的静态方法
//  * 较少参数的of方法的finisher函数就是这个函数，可以看到就是直接转换类型
//  *
//  * @return 返回自身
//  */
// @SuppressWarnings("unchecked")
// private static <I, R> Function<I, R> castingIdentity() {
//     return i -> (R) i;
// }
