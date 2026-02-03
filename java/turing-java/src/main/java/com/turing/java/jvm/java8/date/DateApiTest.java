package com.turing.java.jvm.java8.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 8 date api 测试
 *
 * @author xuweizhi
 * @since 2021/03/31 10:32
 */
@Slf4j
public class DateApiTest {

    private String year;

    private String month;

    private String day;

    private String hour;

    private String minute;

    private String second;

    SecureRandom secureRandom = new SecureRandom();

    public void initValue() {
        year = secureRandom.nextInt(10000) + "";
        month = secureRandom.nextInt(13) + "";
        day = secureRandom.nextInt(31) + "";
        hour = secureRandom.nextInt(24) + "";
        minute = secureRandom.nextInt(61) + "";
        second = secureRandom.nextInt(61) + "";
    }

    @Test
    public void stringConvertDate() {
        String yearMonth = year + "-" + month;
        log.info(yearMonth);

        yearMonth = "2021-03";
        YearMonth start = YearMonth.parse(yearMonth);

        yearMonth = "2021-03";
        YearMonth end = YearMonth.parse(yearMonth);

        // 计算两个年份之间的月数差
        log.info((start.until(end, ChronoUnit.MONTHS) + 1) + "");

        log.info(YearMonth.parse(yearMonth).toString());
        log.info(LocalDate.parse(yearMonth + "-03").toString());
    }

    /**
     * 1. Random 类使用线性同余法 linear congruential formula 来生成伪随机数。
     * 2. 两个 Random 实例，如果使用相同的种子 seed，那他们产生的随机数序列也是一样的。
     * 3. Random 是线程安全的，你的程序如果对性能要求比较高的话，推荐使用 ThreadLocalRandom。
     * 4. Random 不是密码学安全的，加密相关的推荐使用 SecureRandom。
     */
    @Test
    public void randomTest() {
        Random random1 = new Random(100L);
        Random random2 = new Random(100L);

        for (int i = 0; i < 10; i++) {
            log.info("random1 第 {} 次生成的随机数：{}", i, random1.nextDouble());
            log.info("random2 第 {} 次生成的随机数：{}", i, random2.nextDouble());
        }
        // Stream.iterate(0, i -> i + 1).limit(10).forEach(i -> {
        //     log.info("random1 第 {} 次生成的随机数：{}", i, random1.nextDouble());
        // });
        // Stream.iterate(0, i -> i + 1).limit(10).forEach(i -> {
        //     log.info("random2 第 {} 次生成的随机数：{}", i, random2.nextDouble());
        // });
    }


    @Test
    public void test(){
        List<String> list = new ArrayList<>();
        List<String> collect = Stream.of("1", "2", "3", "4", "5", "6").collect(Collectors.toList());




    }
}
