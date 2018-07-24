package top.yuyufeng.learn.dubbo.service.impl;

import com.alibaba.dubbo.common.logger.LoggerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import top.yuyufeng.learn.dubbo.service.IHelloService;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * @author yuyufeng
 * @date 2018/7/23.
 */
public class HelloServiceImpl implements IHelloService {
    private AtomicInteger ai = new AtomicInteger();
    private int timeout = 1000;

    public HelloServiceImpl(int timeout) {
        this.timeout = timeout;
        System.out.println("响应时间：" + timeout);
    }

    @Override
    public String saySomething(String words) {
        int i = ai.incrementAndGet();
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("你好:" + words + " 累计收到：" + i);
        return "你好:" + words;
    }
}
