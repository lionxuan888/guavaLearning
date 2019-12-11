package guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.Uninterruptibles;
import lock.Counter;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaoxuan.jin on 2016/4/18.
 */
public class TestLock {

    @org.junit.Test
    public void testLock() throws Exception {

        System.out.println("hello world");

        final ReentrantLock reentrantLock = new ReentrantLock(false);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 3; i++) {
            Thread threadA = getThread(reentrantLock, countDownLatch);
            threadA.start();
        }
        countDownLatch.await();
        System.out.println("任务全部执行完成......");
    }

    private Thread getThread(final ReentrantLock reentrantLock, final CountDownLatch countDownLatch) {
        return new Thread(new Runnable() {
            public void run() {
                Thread thread = Thread.currentThread();
                System.out.println("进入线程" + thread.getName());
                reentrantLock.lock();
                System.out.println("线程" + thread.getName() + "获取到锁.");
                Uninterruptibles.sleepUninterruptibly(20000, TimeUnit.SECONDS);
                System.out.println("线程" + thread.getName() + "任务执行完成.");
                reentrantLock.unlock();
                countDownLatch.countDown();
            }
        });
    }


    @Test
    public void testCounter() throws Exception {
        final Counter counter = new Counter();

        System.out.println(counter.getValue());
        int i = 0;
        while (i < 10) {
            i++;
            System.out.println("i = " + i);
            Runnable runnable = new Runnable() {
                public void run() {
                    int j = 0;
                    while (j < 1000) {
                        j++;
                        counter.increment();
                    }
                }
            };
            new Thread(runnable).start();
        }
        Thread.sleep(5000);
        System.out.println(counter.getValue());

    }

    @Test
    public void testCache() throws Exception {
        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.expireAfterWrite(10, TimeUnit.SECONDS);
        LoadingCache<String, String> loadingCache = cacheBuilder.build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                System.out.println("查询数据开始,key=" + key);
                if (key.equals("a")) {
                    return "A";
                } else {
                    return "X";
                }
            }
        });
        String a1 = loadingCache.getUnchecked("a");
        String o1 = loadingCache.getUnchecked("o");
        System.out.println("第一次获取a的结果:" + a1);
        System.out.println("第一次获取o的结果:" + o1);

        String a2 = loadingCache.getUnchecked("a");
        String o2 = loadingCache.getUnchecked("o");
        System.out.println("第二次获取a的结果:" + a2);
        System.out.println("第二次获取o的结果:" + o2);

        Uninterruptibles.sleepUninterruptibly(11, TimeUnit.SECONDS);
        String a3 = loadingCache.getUnchecked("a");
        String o3 = loadingCache.getUnchecked("o");
        System.out.println("第三次获取a的结果:" + a3);
        System.out.println("第三次获取o的结果:" + o3);
    }
}
