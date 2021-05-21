package com.turing.java.jvm.java8.functional;

/**
 * <h1>默认方法和静态方法</h1>
 * <p>
 * 此前，Java中的接口不能有非抽象方法，并且实现接口的类必须为接口中定义的每个方法提供一个实现，一旦类库的设计者需要更新接口，向其中加入新的方法，这种方式就会出现问题，这会导致所有的实现类必须实现新的方法，虽然我们可以提供一个骨干实现的抽象类，但是仍然不能根本的解决问题，比如其他Guava和Apache Commons提供的集合框架，会同时修改大量代码！
 * <p>
 * Java8开始，接口中新增的方法可以不需要实现类必须实现，因为接口支持两种新类型的方法及其实现，一种是静态方法，通过static关键字标识，表示这个方法可以通过接口直接调用，这个方法时是属于该接口的；另一个就是非常重要的默认方法，通过default关键字标识，并且接口提供了方法的默认实现，实现接口的类如果不显式地提供该方法的具体实现，就会自动继承默认的实现。这就类似于继承了一个普通方法，这样每次接口新增的方法可以设置为默认方法，它的实现类也不再需要改动代码，保证新方法和源代码的兼容！
 * <p>
 * 实际上静态方法和默认方法被大量的用来支持lambda表达式的复杂写法与复合逻辑，后面我们会介绍到！
 * <h2>问题及解决</h2>
 * 在Java8之前，一个类可以实现多个接口，即使有同名方法也没关系，因此抽象方法没有具体的行为，子类必须有针对抽象方法自己的实现。Java8之后，由于接口拥有了默认方法，也就是说接口提供了方法的默认行为，子类可以不选择实现而直接使用接口提供的实现。
 * <p>
 * 实际上Java8接口允许了默认方法之后，Java已经在某种程度上实现了多继承，所以不光带来了多重继承的好处，还带来了多重继承的问题。如果一个类实现的多个接口中都具有相同方法签名的默认方法，那么这个实现类将无法通过方法签名选择具体调用哪一个接口的默认实现，此时就可能会出现问题！
 * <p>
 * 如果一个类使用相同的函数签名从多个地方（比如另一个类或接口）继承了非抽象方法，通过下面规则尝试判断具体调用哪一个方法：
 * <ul>
 *     <li>本类重写的方法优先级最高。</li>
 *     <li>否则，一个类同时实现了类或者接口，并且类和接口具有相同的签名的方法，那么父类中声明的方法的优先级高于任何接口中声明的默认方法的优先级。</li>
 *     <li>否则，那么子接口的优先级更高：函数签名相同时，优先选择拥有最具体实现的默认方法的接口，即如果B接口继承了A接口，那么B接口就比A更加具体。</li>
 *     <li>否则，继承了多个接口的类必须显式指定的调用某个父接口的默认方法实现。</li>
 * </ul>
 * <p>
 * 其他注意：
 * <p>
 * 如果一个类实现了抽象类和接口，并且接口中具有和抽象类中的抽象方法同样方法签名的默认方法，此时子类任然需要实现这个抽象方法，而不能使用接口的默认方法作为实现！
 * <p>
 * 如果一个类实现的接口之间存在继承关系，那么该类可以手动选择调用最低级别接口的的默认实现，但是手动选择调用其他级别接口的的默认实现。
 * <p>
 * Java强大的编译机制帮助我们解决了菱形继承问题，我们自己不需要解决。什么是菱形继承问题：即有个接口A，有个默认方法a()，此时子接口B、C继承了A接口，随后实现类D同时实现了B、C接口，此时在D中调用a()方法不会有问题，但是c++就有问题！
 * <ul>
 *     <li>如果接口B或者C复写了方法a()，那么在D中调用的a()方法，就是B或者C的a()方法。</li>
 *     <li>如果接口B和C都复写了默认方法a()，那么就会出现冲突。</li>
 *     <li>如果接口B或者C复写了默认方法a()，但是变成了抽象方法，那么那么在D中必须实现该方法！</li>
 * </ul>
 *
 * @author xuweizhi
 * @since 2021/01/14 22:29
 */
public class InterfaceNewFeature {
    /**
     * 测试1  本类重写的方法优先级最高。
     */
    static class InterfaceTest1 extends InterfaceClass4 implements Interface3, Interface {
        public static void main(String[] args) {
            InterfaceTest1 interfaceTest = new InterfaceTest1();
            System.out.println(interfaceTest.handle());
        }

        /**
         * 自己重写的方法优先级最高
         */
        @Override
        public int handle() {
            return 5;
        }
    }

    /**
     * 测试2 父类中声明的方法的优先级高于任何接口中声明的默认方法的优先级。
     */
    static class InterfaceTest2 extends InterfaceClass4 implements Interface3, Interface {
        public static void main(String[] args) {
            InterfaceTest2 interfaceTest = new InterfaceTest2();
            //继承了InterfaceClass4父类，因此父类中相同方法签名的方法优先级最高
            System.out.println(interfaceTest.handle());

        }

    }

    /**
     * 测试3 最具体实现的默认方法的接口优先级最高，即最底层的子接口
     */
    static class InterfaceTest3 implements Interface3, Interface, Interface0 {
        public static void main(String[] args) {
            InterfaceTest3 interfaceTest = new InterfaceTest3();
            //Interface3接口继承了Interface借口路，因此Interface3的默认方法优先级最高
            System.out.println(interfaceTest.handle());
        }

    }

    /**
     * 测试4 上面的方式无法判断，并且编译不通过，只能手动指定
     */
    static class InterfaceTest4 implements Interface1, Interface2 {
        public static void main(String[] args) {
            InterfaceTest4 interfaceTest = new InterfaceTest4();
            //Interface3接口继承了Interface借口路，因此Interface3的默认方法优先级最高
            System.out.println(interfaceTest.handle());
        }

        /**
         * 通过 Interface1.super.handle();指定调用某个接口的默认方法
         */
        @Override
        public int handle() {
            return Interface1.super.handle();
        }
    }

    /**
     * 菱形继承问题，编译通过，运行正常
     * 这实际上就是c++的菱形继承问题，但是Java中帮助我们解决了，我们自己不需要解决
     * 它会自动递归向上查找，找到Interface4和Interface5的共同父接口Interface，然后调用里面的方法，而c++则会抛出异常
     */
    static class InterfaceTest5 implements Interface4, Interface5 {
        public static void main(String[] args) {
            InterfaceTest5 interfaceTest = new InterfaceTest5();
            //Interface3接口继承了Interface借口路，因此Interface3的默认方法优先级最高
            System.out.println(interfaceTest.handle());
        }
    }


    /**
     * 注意1  如果一个类实现了抽象类和接口，并且接口中具有和抽象类中的抽象方法同样方法签名的默认方法
     * 此时子类仍然需要实现这个抽象方法，而不能使用接口的默认方法作为实现，否则编译不通过！
     */
    static class InterfaceTestt1 extends InterfaceClass5 implements Interface3, Interface {
        public static void main(String[] args) {
            InterfaceTestt1 interfaceTest = new InterfaceTestt1();
            //继承了InterfaceClass4父类，因此父类中相同方法签名的方法优先级最高
            System.out.println(interfaceTest.handle());

        }


        //仍然需要实现这个抽象方法
        @Override
        public int handle() {
            return 0;
        }
    }

    /**
     * 注意2  2 如果一个类实现的接口之间存在继承关系
     * 那么该类可以手动选择调用最低级别接口的的默认实现，但是手动选择调用其他级别接口的的默认实现。
     */
    static class InterfaceTestt2 implements Interface3, Interface, Interface0 {
        public static void main(String[] args) {
            InterfaceTestt2 interfaceTest = new InterfaceTestt2();
            //继承了InterfaceClass4父类，因此父类中相同方法签名的方法优先级最高
            System.out.println(interfaceTest.handle());

        }

        /**
         * 可以手动选择调用Interface3的方法
         * 不能可以手动选择调用Interface和Interface0的方法
         */
        @Override
        public int handle() {
            return Interface3.super.handle();
//            return Interface.super.handle();
//            return Interface0.super.handle();
        }

    }


}

interface Interface0 {
    default int handle() {
        return -1;
    }
}

interface Interface extends Interface0 {
    @Override
    default int handle() {
        return 0;
    }
}

interface Interface2 {
    default int handle() {
        return 2;
    }
}

interface Interface1 {
    default int handle() {
        return 1;
    }
}

interface Interface3 extends Interface {
    @Override
    default int handle() {
        return 3;
    }
}

interface Interface4 extends Interface {

}

interface Interface5 extends Interface {

}


class InterfaceClass4 {
    public int handle() {
        return 4;
    }
}

abstract class InterfaceClass5 {
    abstract int handle();
}
