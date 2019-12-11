package guava;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Test;

import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by logan on 2019/5/17.
 */
public class TestRateLimiter {

    @Test
    public void testRateLimiter() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final RateLimiter rateLimiter = RateLimiter.create(0.1);
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(10);
            executorService.submit(new Runnable() {
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    boolean b = rateLimiter.tryAcquire(1);
                    if(b) {
                        System.out.println(threadName + "获取到令牌,开始执行");
                    } else {
                        //System.out.println(threadName + "未获取到令牌,程序运行完成");
                    }
                }
            });
        }
        System.out.println("打印完成");
        Uninterruptibles.sleepUninterruptibly(100000, TimeUnit.SECONDS);
    }
}
