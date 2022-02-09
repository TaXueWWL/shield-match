package com.snowalker.shield.match.disruptor;

import com.alibaba.fastjson.JSON;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import net.openhft.affinity.AffinityStrategies;
import net.openhft.affinity.AffinityThreadFactory;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author snowalker
 * @version 1.0
 * @date 2022/2/2 22:43
 * @className
 * @desc
 */
public class DisruptorEngine {

    private Disruptor disruptor;

    public void init() {
        disruptor = new Disruptor(
                // event Factory
                new EventWrapperFactory(),

                // ringbuffer size
                1024 *1024,

                // 线程池
                new AffinityThreadFactory("Aft-Core", AffinityStrategies.SAME_CORE),

                ProducerType.MULTI,

                new BlockingWaitStrategy()
        );

        // 全局异常处理类
        GlobalExceptionHandler<EventWrapper> exceptionHandler = new GlobalExceptionHandler<>(
                "disruptorEngine",
                (t, seq) -> {
                    System.err.println("exception thrown on seq:" + seq);
                    t.printStackTrace();
                }
        );

        disruptor.setDefaultExceptionHandler(exceptionHandler);

        disruptor.handleEventsWith(new ConsumerA()).then(new ConsumerB());

        disruptor.start();

        // 生产者
        new Timer().schedule(new ProducerTask(), 2000, 1000);


    }

    private ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();

    private class ProducerTask extends TimerTask {

        @Override
        public void run() {
            disruptor.getRingBuffer().publishEvent(
                    (EventTranslatorOneArg<EventWrapper<String>, String>) (event, sequence, arg0) -> {
                        event.setT(arg0);
                        event.setId(threadLocalRandom.nextInt());
                    }, "SnoWalker-" + UUID.randomUUID().toString());
        }
    }

    private class ConsumerA implements EventHandler<EventWrapper> {

        @Override
        public void onEvent(EventWrapper event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("Thread-ConsumerA-"+ Thread.currentThread().getName()  + " recv event: " + JSON.toJSONString(event));
            if (event.getId() % 2 == 0) {
                event.setAHandled(true);
            } else {
                event.setAHandled(false);
            }

        }
    }


    private class ConsumerB implements EventHandler<EventWrapper> {

        @Override
        public void onEvent(EventWrapper event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("Thread-ConsumerB" + " recv event: " + JSON.toJSONString(event));
            if (event.isAHandled) {
                event.setBHandled(true);
                System.out.println("Thread-ConsumerB-" + Thread.currentThread().getName() + " recv event: [A消费者处理-完成] 幂等处理" + JSON.toJSONString(event));
            } else {
                event.setBHandled(false);
                System.out.println("Thread-ConsumerB-" + Thread.currentThread().getName() + " recv event: [A消费者处理-失败] 幂等处理" + JSON.toJSONString(event));
            }
        }
    }

    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void put(String msg) {
        queue.offer(msg);
    }

    public String poll() throws InterruptedException {
        return queue.poll(1000, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        DisruptorEngine disruptorEngine = new DisruptorEngine();
        disruptorEngine.init();


        for (int i = 0; i < 1000; i++) {
            disruptorEngine.put("SnoWalker-MSG-" + i);
        }



        new Thread(() -> {
            boolean flag = true;

            while (flag) {
                try {
                    String poll = disruptorEngine.poll();
                    System.out.println("poll MSG:" + poll);

                    if (poll == null || poll == "") {
                        flag = false;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
