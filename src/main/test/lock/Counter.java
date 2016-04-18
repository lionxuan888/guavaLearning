package lock;

import java.util.concurrent.atomic.AtomicInteger;

public final class Counter {

    private AtomicInteger value = new AtomicInteger(0);

    public int getValue() {
       return value.get();
    }

    public int  increment() {
        int v;
        do {
            v = value.get();
           // System.out.println("vvvv: " + v);
        }while (!value.compareAndSet(v, v + 1));
        return value.get();
    }
}