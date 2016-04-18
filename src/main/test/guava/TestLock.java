package guava;

import lock.Counter;
import org.junit.Test;

/**
 * Created by xiaoxuan.jin on 2016/4/18.
 */
public class TestLock {

    @org.junit.Test
    public void testLock() throws Exception {
        System.out.println("hello world");

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
}
