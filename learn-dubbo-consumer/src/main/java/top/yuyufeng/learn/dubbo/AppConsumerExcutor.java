package top.yuyufeng.learn.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.yuyufeng.learn.dubbo.service.IHelloService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuyufeng
 * @date 2018/7/23.
 */
public class AppConsumerExcutor {
    private static int request = 10000;


    public static void main(String[] args) throws InterruptedException {
        long benginTime = System.currentTimeMillis();
        AtomicInteger ai = new AtomicInteger();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"consumer.xml"});
        context.start();
        CountDownLatch countDownLatch = new CountDownLatch(request);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < request; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // Obtaining a remote service proxy
                    IHelloService helloService = (IHelloService) context.getBean("helloService");
                    // Executing remote methods
                    int aiNow = ai.incrementAndGet();

                    String hello = helloService.saySomething("yuyufeng 累计收到：" + aiNow + " 速度：" + (float) aiNow / (float) ((System.currentTimeMillis() - benginTime) / 1000));
                    // Display the call result
                    System.out.println(hello);
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        executorService.shutdown();
    }
}
