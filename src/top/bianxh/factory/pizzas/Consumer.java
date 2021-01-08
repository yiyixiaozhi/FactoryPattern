package top.bianxh.factory.pizzas;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 比萨消费者
 * 使用线程池默认用户随机消费行为
 */
public class Consumer {
    // 消费者最大消费量
    private final int MAX_CONSUME_NUM = 100;
    /**
     * 使用线程池赋能消费者不定时消费比萨的情况
     */
    private ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(MAX_CONSUME_NUM), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 随机消费
     * 用户在pizzaStore消费某种类型的pizza
     */
    public void consume(PizzaStore pizzaStore, String pizzaType, String consumer) {
        try {
            executorService.submit(new ConsumeTask(pizzaStore, pizzaType, consumer));
        } catch (RejectedExecutionException ex) {
//            System.out.println(consumer + "消费超过了" + MAX_CONSUME_NUM);
//            ex.printStackTrace();
        }
    }

    // 消费者的总消费次数
    private int currentCosumeNum;

    public class ConsumeTask implements Callable<Pizza> {
        private PizzaStore pizzaStore;
        private String pizzaType;
        // 消费者的名字
        private String consumer;

        public ConsumeTask(PizzaStore pizzaStore, String pizzaType, String consumer) {
            this.pizzaStore = pizzaStore;
            this.pizzaType = pizzaType;
            this.consumer = consumer;
        }

        @Override
        public Pizza call() {
            currentCosumeNum++;
            System.out.println(consumer + "总消费次数" + currentCosumeNum);
            long start = System.currentTimeMillis();
            try {
                // 默认人数随机阻塞300毫秒
                final int timeout = new Random().nextInt(300);
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Pizza pizza = pizzaStore.consumePizza(pizzaType);
            System.out.println(Thread.currentThread().getName() + " - " + consumer
                    + " ordered a " + pizzaType + " by " + (System.currentTimeMillis() - start) + "ms\n"
                    + "get " + pizza);
            return pizza;
        }
    }
}
