package com.turing.java.jvm.java8.functional;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Lambda的语法非常简洁，完全没有面向对象复杂的束缚。但是使用时有几个问题需要特别注意：
 *
 * <ul>
 *     <li>使用Lambda必须具有接口，且要求接口中有且仅有一个抽象方法。</li>
 *     <li>使用Lambda必须具有上下文推断。也就是方法的参数或局部变量类型必须为Lambda对应的接口类型，才能使用Lambda作为该接口的实例。</li>
 *     <li>在 Lambda 表达式中不允许声明一个与局部变量同名的参数或者局部变量。</li>
 *     <li>在 Lambda 表达式中，允许引用最终变量、静态变量、局部变量，但是只允许修改静态变量，以及对象类型的局部变量的属性（这要求后面的代码不会修改这个局部变量的引用指向），
 *     对于局部变量本身的引用指向以及基本类型的变量则不允许修改</li>
 *     <li>对应第四条的另一种解释，lambda表达式的局部变量可以不用声明为final，但是实际上是具有隐式的final的的语义，即必须不可被后面的代码修改，否则会编译错误。</li>
 * </ul>
 * 为什么会对局部变量有这些限制呢？
 * <p>
 * 主要是因为对象类型局部变量的引用以及基本类型的局部变量都保存栈上，存在某一个线程之中，如果Lambda可以直接访问并修改栈上的变量，并且Lambda是在另一个线程中使用的，那么使用Lambda
 * 的线程可能会在分配该变量的线程将这个变量收回之后，继续去访问该变量。
 * <p>
 * 因此，Java在访问栈上的局部变量时，实际上是在访问它的副本，而不是访问原始变量，从而造成线程不安全的可能，特别是并行运算的时候。但是如果局部变量仅仅被最开始赋值一次，以后不会再次变
 * 动，那就没有这种隐患了——因此就有了这个限制，即局部变量除了最开始的赋值之后都是读操作，而没有写操作，那么可以读取这个局部变量，相当于final的语义了。
 * <p>
 * 由于对局部变量的限制，Lambda表达式在 Java 中又称为闭包或匿名函数。它们可以作为参数传递给方法，并且可以访问其作用域之外的变量。但是它们不能修改定义Lambda的方法的局部变量的内容，
 * 这些变量必须是隐式最终的。因此可以认为Lambda是对值封闭，而不是对变量封闭，因为可以访问局部变量，但不可修改值。为什么对象类型的额局部变量的属性可以修改呢？
 * <p>
 * 因为它们保存在堆中，而堆是在线程之间共享的！因此我们如果需要在lambda中修改某个基本变量，那么可以使用该变量的包装类。然后再修改属性值即可。
 *
 * @author xuweizhi
 * @since 2021/01/14 22:19
 */
public class LambdaTest {

    //静态全局变量
    static int k = 1;

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    @Test
    public void test3() {
        int i = 1;
        Object o = new Object();
        AtomicInteger integer = new AtomicInteger(1);
        //使用lambda表达式标准语法
        Comparator<Integer> comparable = (Integer o1, Integer o2) -> {
            //在后面的语句中不会修改这个局部变量的值时，可以在lambda中访问基本局部变量，但是不可操作值
            int b = i;
            System.out.println(i);

            //在后面的语句中不会修改这个对象局部变量的引用指向时，可以操作或者访问这个对象的属性
            integer.addAndGet(1);
            integer.get();
            integer.set(10);

            //静态变量的引用指向可以修改
            atomicInteger = new AtomicInteger(2);
            //静态变量的值可以修改
            k = 2;
            return o1 - o2;
        };

        //在后面的语句中改变基本局部变量的值之后，lambda中对该变量的任何访问操作都将编译不通过
        // i = 2;

        //在后面的语句中改变对象局部变量的引用指向之后，lambda中对该变量的任何访问操作都将编译不通过
        // integer= new AtomicInteger(1);

        //在后面的语句中可以操作或者访问这个对象的属性
        integer.set(15);

        //静态变量的引用指向可以修改
        atomicInteger = new AtomicInteger(3);
        //静态变量的值可以修改
        k = 3;
    }

}
