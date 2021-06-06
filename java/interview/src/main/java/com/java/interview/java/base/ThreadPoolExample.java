package com.java.interview.java.base;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池模型
 *
 * @author xuweizhi
 * @since 2021/06/01 13:51
 */
public class ThreadPoolExample {

    @Slf4j
    @SuppressWarnings("all")
    public static class PoolExample {

        private static int CORE_SIZE = Runtime.getRuntime().availableProcessors();
        private static int MAX_SIZE = CORE_SIZE * 2;

        @Test
        public void threadPool() throws InterruptedException {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                    new ThreadFactoryBuilder().setNameFormat("FutureTask-%d").setDaemon(true).build());
            CountDownLatch downLatch = new CountDownLatch(10);
            for (int i = 0; i < 10; i++) {
                executor.execute(() -> {
                    log.info("Back Pack");
                    downLatch.countDown();
                });
            }
            downLatch.await();
            executor.shutdown();
        }

        @Test
        public void threadPoolStatus() throws InterruptedException {
            LinkedBlockingQueue<Runnable> queue =
                    new LinkedBlockingQueue<Runnable>(5);
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, queue);
            CountDownLatch countDownLatch = new CountDownLatch(16);
            for (int i = 0; i < 16; i++) {
                threadPool.execute(() -> {
                    try {
                        Thread.sleep(3000);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                System.out.println("线程池中活跃的线程数： " + threadPool.getPoolSize());
                if (queue.size() > 0) {
                    System.out.println("----------------队列中阻塞的线程数" + queue.size());
                }
            }
            countDownLatch.await();
            threadPool.shutdown();
        }

        private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        private static final int COUNT_BITS = Integer.SIZE - 3;
        private static final int CAPACITY = (1 << COUNT_BITS) - 1;

        // runState is stored in the high-order bits
        private static final int RUNNING = -1 << COUNT_BITS;
        private static final int SHUTDOWN = 0 << COUNT_BITS;
        private static final int STOP = 1 << COUNT_BITS;
        private static final int TIDYING = 2 << COUNT_BITS;
        private static final int TERMINATED = 3 << COUNT_BITS;

        // Packing and unpacking ctl
        private static int runStateOf(int c) {
            return c & ~CAPACITY;
        }

        private static int workerCountOf(int c) {
            return c & CAPACITY;
        }

        private static int ctlOf(int rs, int wc) {
            return rs | wc;
        }

        @Test
        public void ctlTest() {
            // 111
            System.out.println(Integer.toBinaryString(RUNNING));
            // 000
            System.out.println(Integer.toBinaryString(SHUTDOWN));
            // 010
            System.out.println(Integer.toBinaryString(STOP));
            // 100
            System.out.println(Integer.toBinaryString(TIDYING));
            // 110
            System.out.println(Integer.toBinaryString(TERMINATED));
            // 高 3 位用于状态判断,低 29 位用与容量判断
            // 00011111111111111111111111111111
            // 11100000000000000000000000000000
            System.out.println(Integer.toBinaryString(CAPACITY));
            System.out.println(Integer.toBinaryString(~CAPACITY));

            // 高三位为 111,表示线程池运行状态
            System.out.println(Integer.toBinaryString(-1 << COUNT_BITS));

            // 初始化线程数
            System.out.println(Integer.toBinaryString(ctlOf(RUNNING, 0)));
            System.out.println(Integer.toBinaryString(workerCountOf(ctl.get())));
        }
    }

    /**
     * https://www.cnblogs.com/dongguacai/p/6038960.html
     */
    public static class MyCallableTask implements Callable<Integer> {
        @Override
        public Integer call()
                throws Exception {
            System.out.println("callable do something");
            Thread.sleep(5000);
            return new Random().nextInt(100);
        }
    }

    @SuppressWarnings("all")
    public static class CallableTest {
        @Test
        public void callableTest() throws Exception {
            Callable<Integer> callable = new MyCallableTask();
            // FutureTask 是对 future 的基本实现
            FutureTask<Integer> future = new FutureTask<Integer>(callable);
            Thread thread = new Thread(future);
            thread.start();
            Thread.sleep(100);
            //尝试取消对此任务的执行
            future.cancel(true);
            //判断是否在任务正常完成前取消
            System.out.println("future is cancel:" + future.isCancelled());
            if (!future.isCancelled()) {
                System.out.println("future is cancelled");
            }
            //判断任务是否已完成
            System.out.println("future is done:" + future.isDone());
            if (!future.isDone()) {
                System.out.println("future get=" + future.get());
            } else {
                //任务已完成
                System.out.println("task is done");
            }
        }
    }

    public static class CallableThread implements Callable<String> {
        @Override
        public String call()
                throws Exception {
            System.out.println("进入Call方法，开始休眠，休眠时间为：" + System.currentTimeMillis());
            Thread.sleep(10000);
            return "今天停电";
        }
        public static void main(String[] args) throws Exception {
            ExecutorService es = Executors.newSingleThreadExecutor();
            Callable<String> call = new CallableThread();
            Future<String> fu = es.submit(call);
            es.shutdown();
            Thread.sleep(5000);
            System.out.println("主线程休眠5秒，当前时间" + System.currentTimeMillis());
            String str = fu.get();
            System.out.println("Future已拿到数据，str=" + str + ";当前时间为：" + System.currentTimeMillis());
        }
    }
}
