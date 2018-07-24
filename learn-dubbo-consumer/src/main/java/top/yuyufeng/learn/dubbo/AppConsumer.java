package top.yuyufeng.learn.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.yuyufeng.learn.dubbo.service.IHelloService;

/**
 * @author yuyufeng
 * @date 2018/7/23.
 */
public class AppConsumer {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        // Obtaining a remote service proxy
        IHelloService helloService= (IHelloService)context.getBean("helloService");
        // Executing remote methods
        String hello = helloService.saySomething("yuyufeng");
        // Display the call result
        System.out.println(hello);
    }
}
