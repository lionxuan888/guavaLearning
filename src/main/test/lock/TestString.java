package lock;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by logan on 2020/1/3.
 */
public class TestString {

    public static void main(String[] args) {

        String s = "金";
        System.out.println(s.getBytes().length);

        int size = 10000;
        int[][] arraA = new int[size][size];
        int[][] arraB = new int[size][size];
        Stopwatch startedA = Stopwatch.createStarted();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arraA[i][j] = 1;
            }
        }
        System.out.println("A耗时:" + startedA.elapsed(TimeUnit.MILLISECONDS));
        Stopwatch startedB = Stopwatch.createStarted();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                arraB[i][j] = 1;
            }
        }
        System.out.println("B耗时:" + startedB.elapsed(TimeUnit.MILLISECONDS));
    }

    @Test
    public void testCapacity() throws Exception {
        // count 16  value = 20 insert 5
        int i = newCapacity(21);
        System.out.println("newCapacity:" + i);
    }

    private int newCapacity(int minCapacity) {
        // overflow-conscious code
        int newCapacity = (20 << 1) + 2;
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        return (newCapacity <= 0 || MAX_ARRAY_SIZE - newCapacity < 0)
                ? hugeCapacity(minCapacity)
                : newCapacity;
    }

    private int hugeCapacity(int minCapacity) {
        if (Integer.MAX_VALUE - minCapacity < 0) { // overflow
            throw new OutOfMemoryError();
        }
        int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        return (minCapacity > MAX_ARRAY_SIZE)
                ? minCapacity : MAX_ARRAY_SIZE;
    }
}
