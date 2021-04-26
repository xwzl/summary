package com.turing.java.interview.java.base;

import com.turing.java.jvm.concurrent.utils.UnsafeInstance;
import lombok.Data;
import lombok.ToString;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 包装类型
 *
 * @author xuweizhi
 * @since 2021/04/13 17:52
 */
public class PackageClass {

    public static class IntegerTest {

        @Test
        public void baseCacheTest() {
            // new Integer 直接赋值给 value
            Integer x = new Integer(100);
            Integer y = new Integer(100);
            System.out.println(x.equals(y));
            System.out.println(x == y);

            // Integer.valueOf();
            Integer z = Integer.valueOf(123);
            Integer k = Integer.valueOf(123);
            // true
            System.out.println(z == k);
        }

        @Test
        public void creatNewTimeTest() {
            for (int i = 0; i < 5; i++) {
                timeStaticTemplate(() -> {
                    for (Integer j = 1; j < 2000000000; j++) {

                    }
                    return null;
                }, "装箱测试");
            }
            for (int i = 0; i < 5; i++) {
                timeStaticTemplate(() -> {
                    for (int j = 1; j < 2000000000; j++) {

                    }
                    return null;
                }, "基准测试");
            }

        }

        public <T> void timeStaticTemplate(Supplier<T> supplier, String productName) {
            long startTime = System.currentTimeMillis();
            T t = supplier.get();
            long timeInterval = System.currentTimeMillis() - startTime;
            System.out.println(productName + ":" + timeInterval);
        }

        @Test
        public void stringTest() throws NoSuchFieldException, IllegalAccessException {
            String school = "西南科技大学";
            Class<? extends String> stringClazz = school.getClass();
            Field charField = stringClazz.getDeclaredField("value");
            charField.setAccessible(true);

            char[] value = "北京大学".toCharArray();
            charField.set(school, value);
            System.out.println(school);

            Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
            long valueMemoryOffset = unsafe.objectFieldOffset(String.class.getDeclaredField("value"));
            unsafe.compareAndSwapObject(school, valueMemoryOffset, value, "清华大学".toCharArray());
            System.out.println(school);
            unsafe.compareAndSwapObject(school, valueMemoryOffset, "清华大学".toCharArray(), "咖啡宝贝");
            System.out.println(school);

            long pk = unsafe.objectFieldOffset(MemoryValueOffset.class.getDeclaredField("pk"));
            MemoryValueOffset memoryValueOffset = new MemoryValueOffset();
            memoryValueOffset.setPk("baby");
            unsafe.compareAndSwapObject(memoryValueOffset, pk, "baby", "coffee");
            System.out.println(memoryValueOffset);
        }

        @Test
        public void stringInternTest() {
            String s1 = new String("aaa");
            String s2 = new String("aaa");
            System.out.println(s1 == s2);
            String s3 = s1.intern();
            System.out.println(s1.intern() == s3);
            String s4 = "aaa";
        }

        @Test
        public void numberTest() {
            String number = "7.0000000000";
            System.out.println(Double.parseDouble(number) >= 7.0);
        }

        @Test
        public void typeConvertTest() {
            byte byteTest = 1;
            byteTest += 2;
        }

        @Test
        public void listTest() {
            Stream.of(new Book("北京"), new Book("上海")).filter(book -> book.getBookName().equals("北京"))
                    .forEach(System.out::println);

            System.out.println(System.currentTimeMillis());

            Book NANJING = new Book("南京");

            List<Book> arrayList = new ArrayList<>();
            arrayList.add(NANJING);
            arrayList.get(0).setBookName("史诗");
            ArrayList<Book> books = new ArrayList<>();

            System.out.println(books);
            Collections.copy(arrayList,books);
            System.out.println(books);
        }

        /**
         * 由于时间关系，写得比较粗糙
         */
        @Test
        public void humpConversion() {
            String sourceStr = "  `relationship_id` bigint(20) NOT NULL AUTO_INCREMENT,\n" +
                    "  `family_id` varchar(64) DEFAULT NULL,\n";
            int i = 0;
            while ((i = sourceStr.indexOf("`")) > 0) {
                sourceStr = sourceStr.substring(i + 1);
                // mysql 字段
                String mysqlColumn = sourceStr.substring(0, sourceStr.indexOf("`"));
                String[] s = mysqlColumn.split("_");
                // mysql 字段驼峰转换
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < s.length; j++) {
                    if (j == 0) {
                        stringBuilder.append(s[j]);
                    } else {
                        char[] chars = s[j].toCharArray();
                        chars[0] = (char) (chars[0] - 32);
                        stringBuilder.append(chars);
                    }
                }
                //System.out.println(String.format("%s as %s,",mysqlColumn,stringBuilder.toString()));
                System.out.println(String.format("#{item.%s,jdbcType=VARCHAR},", stringBuilder.toString()));
                sourceStr = sourceStr.substring(sourceStr.indexOf("`") + 1);
            }
        }

    }

    @Data
    @ToString
    public static class MemoryValueOffset {
        private String pk;
    }

    @Data
    public static class Book {
        private String bookName;

        public Book(String bookName) {
            this.bookName = bookName;
        }
    }


}
