package com.turing.java.jvm.java8.functional;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class CompositeTest {

    ArrayList<User> userArrayList = new ArrayList<>();

    @Before
    public void beforeTest() {
        User user4 = new User(20, "da");
        User user1 = new User(25, "张三");
        User user3 = new User(20, "张小三");
        User user2 = new User(25, "tom");

        userArrayList.add(user1);
        userArrayList.add(user2);
        userArrayList.add(user3);
        userArrayList.add(user4);
    }


    /**
     * 比较器复合
     */
    @Test
    public void test() {

        /*1 首先是通过user的age比较大小并顺序排序，我们使用JDK8提供的comparingInt静态方法*/
        Comparator<User> userComparator = Comparator.comparingInt(User::getAge);
        userArrayList.sort(userComparator);
        System.out.println("age顺序：" + userArrayList);

        //上面的comparingInt方法就相当于下面的两个表达式
        //实际上这里的ToIntFunction用来提取需要比较的两个对象的的属性
        //只不过这两个操作被封装到了comparingInt方法中，因此我们只需要传递一个ToIntFunction的函数即可
        ToIntFunction<User> toIntFunction = value -> value.getAge();
        Comparator<User> userComparator1 = (c1, c2) -> Integer.compare(toIntFunction.applyAsInt(c1), toIntFunction.applyAsInt(c2));


        /*
         * 2 现在如果我们需要进行逆序排序
         * 此时我们只需要将以前的比较器顺序反过来就行了
         * 比较器调用reversed方法，返回的比较器将会使用和调用比较器相反的顺序。
         */
        Comparator<User> reversed = userComparator.reversed();
        userArrayList.sort(reversed);
        System.out.println("age逆序：" + userArrayList);

        /*
         * 3 有时候我们需要进行多个参数的比较
         * 现在我们需要在age相等的基础上再比较姓名长度并顺序排序
         * 此时我们可以调用thenComparing方法，传递一个比较器这表示将两个比较器复合
         * 调用方法的比较器被看作主要比较器，参数的比较器看作副比较器
         * 或者更进一步，我们类似于第一个获取比较器的方式，传递一个能共提取比较的键的ToIntFunction
         */
        Comparator<User> userComparator2 = reversed.thenComparingInt(o -> o.getName().length());
        userArrayList.sort(userComparator2);
        System.out.println("age逆序-name长度顺序：" + userArrayList);

    }

    /**
     * 函数复合 andThen计算
     */
    @Test
    public void andThen() {
        User user1 = new User(20, "小花");
        //这个函数根据传入的user获取age
        Function<User, Integer> f1 = User::getAge;
        //这个函数根据传入的int值创建一个user
        Function<Integer, User> f2 = integer -> new User(integer++);


        //将函数f1和f2复合，即获取传入user的age创建一个新的user，获得新的函数f3
        //可以看到f3的参数就是f1的参数，f3的返回类型，就是f2的返回类型，相当于将f1和f2串联了起来
        //先执行f1，然后将结果传给f2，最后执行f2
        Function<User, User> f3 = f1.andThen(f2);
        System.out.println(f3.apply(user1));
    }


    /**
     * 函数复合 compose计算
     */
    @Test
    public void compose() {
        //这个函数根据传入的user获取age
        Function<User, Integer> f1 = User::getAge;
        //这个函数根据传入的int值创建一个user
        Function<Integer, User> f2 = integer -> new User(integer++);


        //将函数f1和f2复合，即获取传入user的age创建一个新的user，获得新的函数f3
        //可以看到f3的参数就是f2的参数，f3的返回类型就是f1的返回类型，相当于将f2和f1串联了起来
        //先执行f2，然后将结果传给f1，最后执行f1
        Function<Integer, Integer> f3 = f1.compose(f2);

        System.out.println(f3.apply(10));
    }

    /**
     * 消费复合 Consumer的andThen计算
     */
    @Test
    public void andThenCom() {
        User user = new User(20, "小花");
        //一个Consumer设置名字
        Consumer<User> c1 = o -> o.setName("花小");
        //一个Consumer设置年龄
        Consumer<User> c2 = o -> o.setAge(10);
        //组合
        //先在调用者里面执行参数计算，然后将参数传给after，最后执行在after里面执行参数计算。
        Consumer<User> c3 = c1.andThen(c2);
        c3.accept(user);
        System.out.println(user);

        //链式编程写法
        c1.andThen(o -> o.setAge(10)).accept(user);
    }

    /**
     * 断言复合
     */
    @Test
    public void predicate() {
        User user = new User(20, "小花");
        //一个断言用于判断年龄是否大于20
        Predicate<User> p1 = (User u) -> u.getAge() >= 20;
        //一个断言用于判断姓名长度是否大于等于3
        Predicate<User> p2 = (User u) -> u.getName().length() >= 3;

        //组合
        //&&
        Predicate<User> and = p1.and(p2);
        System.out.println("年龄大于等于20并且姓名长度大于等于3：---" + and.test(user));

        //||
        Predicate<User> or = p1.or(p2);
        System.out.println("年龄大于等于20或者姓名长度大于等于3：---" + or.test(user));

        //!
        Predicate<User> negate = p1.negate();
        System.out.println("年龄小于20：---" + negate.test(user));



        User user2 = new User(19, "花花花");
        //多重组合
        //年龄大于等于20并且年龄大于3，或者姓名等于花花花
        System.out.println(p1.and((User u) -> u.getName().length() > 3).or((User u) -> "花花花".equals(u.getName())).test(user2));
        //年龄大于等于20并且年龄大于等于3，或者姓名等于花花花
        System.out.println(p1.and((User u) -> u.getName().length() >= 3).or((User u) -> "花花花".equals(u.getName())).test(user2));
    }




    public class User {


        public User(int age) {
            this.age = age;
        }

        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public User() {
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}