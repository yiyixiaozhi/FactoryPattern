package top.bianxh.factory.pizzas;

import top.bianxh.factory.pizzas.Pizza;
import top.bianxh.factory.pizzas.PizzaStore;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 比萨消费者
 * 使用线程池默认用户随机消费行为
 */
public class Consumer {
    /**
     * 使用线程池赋能消费者不定时消费比萨的情况
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    /**
     * 随机消费
     * 用户在pizzaStore消费某种类型的pizza
     */
    public void consume(PizzaStore pizzaStore, String pizzaType, String consumer) {
        executorService.submit(new ConsumeTask(pizzaStore, pizzaType, consumer));
    }

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
            long start = System.currentTimeMillis();
            try {
                // 默认人数随机阻塞3000毫秒
                final int timeout = new Random().nextInt(3000);
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Pizza pizza = pizzaStore.consumePizza(pizzaType);
            System.out.println(Thread.currentThread().getName() + " - " + consumer
                    + " ordered a " + pizza + " by " + (System.currentTimeMillis() - start) + "ms");
            return pizza;
        }
    }
}
