package Twenty.Feb;

import com.google.common.util.concurrent.Uninterruptibles;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by logan on 2020/2/25.
 */
public class TestWait {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition conditionA = reentrantLock.newCondition();
    private Condition conditionB = reentrantLock.newCondition();


    public void ww() {
        Runnable runnableA = new Runnable() {
            @Override
            public void run() {
                testWait();
            }
        };
        Runnable runnableB = new Runnable() {
            @Override
            public void run() {
                testNotify();
            }
        };
        Thread threadA = new Thread(runnableA);
        Thread threadB = new Thread(runnableB);
        threadA.start();
        threadB.start();
    }


    public void testWait() {

        synchronized (this) {
            System.out.println("wait aaaa");
            try {
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("wait bbb");
        }
    }

    public void testNotify() {
        synchronized (this) {
            System.out.println("notify aaaa");

            notify();

            System.out.println("notify bbb");
        }
    }

    public static void main(String[] args) {
        TestWait testWait = new TestWait();
        testWait.ww();
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.MINUTES);
    }
}
