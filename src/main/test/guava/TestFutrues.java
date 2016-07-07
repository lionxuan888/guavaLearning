package guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * Created by xiaoxuan.jin on 2016/6/29.
 */
public class TestFutrues {


    @Test
    public void test() throws Exception {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
        Callable<String> task = new Callable<String>() {
            public String call() throws Exception {
                return "异步任务执行";
            }
        };
        List<ListenableFuture<String>> futures = Lists.newArrayList();
        final CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            ListenableFuture<String> submit = listeningExecutorService.submit(new TestTask(i));
            futures.add(submit);
        }
        ListenableFuture<List<String>> listListenableFuture = Futures.successfulAsList(futures);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<String>>(){
            public void onSuccess(List<String> result) {
                System.out.println("on success");

            }
            public void onFailure(Throwable t) {
                System.out.println("on failure");
            }
        });
        System.out.println("waiting.....");
        System.out.println("任务执行成功");
    }

    public class TestTask implements Callable<String> {
        public Integer input;

        public TestTask(Integer input) {
            this.input = input;
        }

        public String call() throws Exception {
            if (input == 3) {
                throw new Exception("测试任务异常");
            }
            return "hello - " + input;
        }
    }
}
