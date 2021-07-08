package com.java.interview.java.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.Map;
import java.util.Optional;

/**
 * 反射
 *
 * @author xuweizhi
 * @since 2021/04/30 11:10
 */
@Slf4j
@SuppressWarnings("all")
public class ReflectionExample {

    public static class Animal<T> {

        protected T data;

        protected Class clazz;

        public Animal() {
            clazz = this.getClass();

            Type[] genericInterfaces = clazz.getGenericInterfaces();
            for (Type genericInterface : genericInterfaces) {
                resolveType((ParameterizedType) genericInterface);
            }

            log.info("解析父类泛型参数");
            resolveType((ParameterizedType) clazz.getGenericSuperclass());

        }


        public T getData() {
            Optional.ofNullable(data).ifPresent(value -> {
                log.info("data 实际类型 {}", data.getClass());
            });
            return data;
        }

        protected void resolveType(ParameterizedType param) {
            Type[] types = param.getActualTypeArguments();
            for (Type type : types) {
                log.info(type.toString());
            }
        }

        public Animal(T data) {
            this.data = data;
        }
    }

    public static class Cat extends Animal<Cat> {


        public Cat() {
        }

        @Test
        public void catTest() {
            Cat cat = new Cat();
        }
    }


    /**
     * 由于泛型擦除，无法在运行时获取泛型参数的类型，采用折中的方式
     */
    public static class Dog<T> extends Animal<T> {

        public Dog() {

        }


    }

    public static class RelectionTest {
        @Test
        public void test() {
            Dog<String> str = new Dog<>();
            Dog<Integer> integerDog = new Dog<>();
            integerDog.data = 1;
            System.out.println(integerDog.getData());
        }

        @Test
        public void reflectionApiTest() throws ClassNotFoundException {
            Class<?> aClass = Class.forName("com.java.interview.java.base.ConstructEnum");
        }

        @Test
        public void constructTest() throws Exception {

            Class<?> clazz = null;

            //获取Class对象的引用
            clazz = Class.forName("com.turing.java.interview.java.base.ReflectionExample$User");

            //第一种方法，实例化默认构造方法，User必须无参构造函数,否则将抛异常
            User user = (User) clazz.newInstance();
            user.setAge(20);
            user.setName("Jack");
            System.out.println(user);

            System.out.println("--------------------------------------------");

            //获取带String参数的public构造函数
            Constructor cs1 = clazz.getConstructor(String.class);
            //创建User
            User user1 = (User) cs1.newInstance("hiway");
            user1.setAge(22);
            System.out.println("user1:" + user1.toString());

            System.out.println("--------------------------------------------");

            //取得指定带int和String参数构造函数,该方法是私有构造private
            Constructor cs2 = clazz.getDeclaredConstructor(int.class, String.class);
            //由于是private必须设置可访问
            cs2.setAccessible(true);
            //创建user对象
            User user2 = (User) cs2.newInstance(25, "hiway2");
            System.out.println("user2:" + user2.toString());

            System.out.println("--------------------------------------------");

            //获取所有构造包含private
            Constructor<?> cons[] = clazz.getDeclaredConstructors();
            // 查看每个构造方法需要的参数
            for (int i = 0; i < cons.length; i++) {
                //获取构造函数参数类型
                Class<?> clazzs[] = cons[i].getParameterTypes();
                System.out.println("构造函数[" + i + "]:" + cons[i].toString());
                System.out.print("参数类型[" + i + "]:(");
                for (int j = 0; j < clazzs.length; j++) {
                    if (j == clazzs.length - 1)
                        System.out.print(clazzs[j].getName());
                    else
                        System.out.print(clazzs[j].getName() + ",");
                }
                System.out.println(")");
            }
        }

        @Test
        public void constructApiTest() throws NoSuchMethodException {
            Constructor cs3 = User.class.getDeclaredConstructor(int.class, String.class);
            System.out.println("-----getDeclaringClass-----");
            Class uclazz = cs3.getDeclaringClass();
            //Constructor对象表示的构造方法的类
            System.out.println("构造方法的类:" + uclazz.getName());

            System.out.println("-----getGenericParameterTypes-----");
            //对象表示此 Constructor 对象所表示的方法的形参类型
            Type[] tps = cs3.getGenericParameterTypes();
            for (Type tp : tps) {
                System.out.println("参数名称tp:" + tp);
            }
            System.out.println("-----getParameterTypes-----");
            //获取构造函数参数类型
            Class<?> clazzs[] = cs3.getParameterTypes();
            for (Class claz : clazzs) {
                System.out.println("参数名称:" + claz.getName());
            }
            System.out.println("-----getName-----");
            //以字符串形式返回此构造方法的名称
            System.out.println("getName:" + cs3.getName());

            System.out.println("-----getoGenericString-----");
            //返回描述此 Constructor 的字符串，其中包括类型参数。
            System.out.println("getoGenericString():" + cs3.toGenericString());

        }

        @Test
        public void fieldTest() throws ClassNotFoundException, NoSuchFieldException, InstantiationException, IllegalAccessException {
            Class<?> clazz = Student.class;
            //获取指定字段名称的Field类,注意字段修饰符必须为public而且存在该字段,
            // 否则抛NoSuchFieldException
            Field field = clazz.getField("age");
            System.out.println("field:" + field);

            //获取所有修饰符为public的字段,包含父类字段,注意修饰符为public才会获取
            Field fields[] = clazz.getFields();
            for (Field f : fields) {
                System.out.println("f:" + f.getDeclaringClass());
            }

            System.out.println("================getDeclaredFields====================");
            //获取当前类所字段(包含private字段),注意不包含父类的字段
            Field fields2[] = clazz.getDeclaredFields();
            for (Field f : fields2) {
                System.out.println("f2:" + f.getDeclaringClass());
            }
            //获取指定字段名称的Field类,可以是任意修饰符的自动,注意不包含父类的字段
            Field field2 = clazz.getDeclaredField("desc");
            System.out.println("field2:" + field2);

            //获取Class对象引用
            Student st = (Student) clazz.newInstance();
            //获取父类public字段并赋值
            Field ageField = clazz.getField("age");
            ageField.set(st, 18);
            Field nameField = clazz.getField("name");
            nameField.set(st, "Lily");

            //只获取当前类的字段,不获取父类的字段
            Field descField = clazz.getDeclaredField("desc");
            descField.set(st, "I am student");
            Field scoreField = clazz.getDeclaredField("score");
            //设置可访问，score是private的
            scoreField.setAccessible(true);
            scoreField.set(st, 88);
            System.out.println(st.toString());

            //输出结果：Student{age=18, name='Lily ,desc='I am student', score=88}
            //获取字段值
            System.out.println(scoreField.get(st));
        }

        @Test
        public void methodTest() throws ClassNotFoundException, NoSuchMethodException {
            Class clazz = Circle.class;

            //根据参数获取public的Method,包含继承自父类的方法
            Method method = clazz.getMethod("draw", int.class, String.class);
            System.out.println("method:" + method);

            //获取所有public的方法:
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                System.out.println("m::" + m);
            }
            System.out.println("=========================================");

            //获取当前类的方法包含private,该方法无法获取继承自父类的method
            Method method1 = clazz.getDeclaredMethod("drawCircle");
            System.out.println("method1::" + method1);
            //获取当前类的所有方法包含private,该方法无法获取继承自父类的method
            Method[] methods1 = clazz.getDeclaredMethods();
            for (Method m : methods1) {
                System.out.println("m1::" + m);
            }
        }

    }


    class Shape {
        public void draw() {
            System.out.println("draw");
        }

        public void draw(int count, String name) {
            System.out.println("draw " + name + ",count=" + count);
        }

    }

    class Circle extends Shape {

        private void drawCircle() {
            System.out.println("drawCircle");
        }

        public int getAllCount() {
            return 100;
        }

    }

    public static class Person {
        public int age;
        public String name;
        //省略set和get方法
    }

    public static class Student extends Person {
        public String desc;
        private int score;
        //省略set和get方法
    }


    public static class User {
        private int age;
        private String name;

        public User() {
            super();
        }

        public User(String name) {
            super();
            this.name = name;
        }

        /**
         * 私有构造
         *
         * @param age
         * @param name
         */
        private User(int age, String name) {
            super();
            this.age = age;
            this.name = name;
        }

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

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Data
        @ToString
        public static class Baby {
            private String babyName;
            private Integer age;

            @Test
            public void babyTest() throws NoSuchFieldException, IllegalAccessException {
                Baby baby = new Baby();
                Class<Baby> babyClass = Baby.class;
                Field age = babyClass.getDeclaredField("age");
                //Map<String,Object> xx = JSON.parse();
                age.setAccessible(true);
                Object ageInstance1 = 1111;
                Class<?> type = age.getType();
                if (ageInstance1.getClass().equals(type)) {
                    age.set(baby, ageInstance1);
                }
                System.out.println(type.getName());
                Object ageInstance2 = "1111";
                if (ageInstance2.getClass().equals(type)) {
                    age.set(baby, ageInstance2);
                }

                System.out.println(baby);

                Baby baby1 = new Baby();
                baby1.setBabyName("11");
                baby1.setAge(11);
                String x = JSON.toJSONString(baby1);
                Map map = JSON.parseObject(x, Map.class);
                // age1 age2 age3
                map.put("age", map.get("age").toString());
                String x1 = JSON.toJSONString(map);
                Baby x2 = JSON.parseObject(x1, Baby.class);
                System.out.println(x2);
            }
        }


        public static class inner {

            @Test
            public void innnerNameTest() throws ClassNotFoundException {
                //普通类
                System.out.println(Test.class.getSimpleName()); //Test
                System.out.println(Test.class.getName()); //com.cry.Test
                System.out.println(Test.class.getCanonicalName()); //com.cry.Test
                //内部类
                System.out.println(inner.class.getSimpleName()); //inner
                System.out.println(inner.class.getName()); //com.cry.Test$inner
                System.out.println(inner.class.getCanonicalName()); //com.cry.Test.inner
                //数组
                //System.out.println(args.getClass().getSimpleName()); //String[]
                //System.out.println(args.getClass().getName()); //[Ljava.lang.String;
                //System.out.println(args.getClass().getCanonicalName()); //java.lang.String[]
                //我们不能用getCanonicalName去加载类对象，必须用getName
                //Class.forName(inner.class.getCanonicalName()); 报错
                Class.forName(inner.class.getName());
            }
        }


    }

}

