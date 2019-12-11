package jdk.concurrent;

import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * Created by logan on 2019/8/14.
 */
public class TestJdkFuture {


    @Test
    public void test() throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new Runnable() {
            public void run() {
                System.out.println("running ");
            }
        });

    }






    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(new Runnable() {
            public void run() {
                for (int i = 0; i < 100; i++) {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                    System.out.println("i'am running!");
                }
            }
        });
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        System.out.println("退出主进程!");

    }


    @Test
    public void testFuture() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        System.out.println("nanoTime:" + System.nanoTime());
        System.out.println("currentTimeMillis:" + System.currentTimeMillis());
        Future<String> future = executor.submit(new Callable<String>() {
            public String call() {
                Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
                return "success";
            }
        });

        String s = future.get();

        System.out.println("返回结果:" + s);

    }
}
