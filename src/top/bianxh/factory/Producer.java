package top.bianxh.factory;

import top.bianxh.factory.pizzas.Pizza;
import top.bianxh.factory.pizzas.PizzaStore;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 比萨生产线
 * 使用线程池生产更多比萨
 */
public class Producer {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 随机生产
     */
    public Pizza produce(PizzaStore pizzaStore, String pizzaType) {
        Future<Pizza> future = executorService.submit(new ProducePizzaTask(pizzaStore, pizzaType));
        Pizza pizza = null;
        try {
            pizza = future.get();
        } catch (InterruptedException e) {
            // 如果任务执行过程中发生中断异常则重新设置线程的中断状态
            // 这样做可以让wait中的线程唤醒
            Thread.currentThread().interrupt();
            // 同时取消任务的执行,参数false表示在线程在执行不中断
            future.cancel(true);
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            try {
                throw new Throwable(e.getCause());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return pizza;
    }

    public class ProducePizzaTask implements Callable<Pizza> {
        private PizzaStore pizzaStore;
        private String pizzaType;

        public ProducePizzaTask(PizzaStore pizzaStore, String pizzaType) {
            this.pizzaStore = pizzaStore;
            this.pizzaType = pizzaType;
        }

        @Override
        public Pizza call() throws Exception {
            long start = System.currentTimeMillis();
            try {
                // 默认人数随机阻塞1-10秒钟
                final int timeout = new Random().nextInt(10);
                TimeUnit.SECONDS.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Pizza pizza = pizzaStore.orderPizza(pizzaType);
            System.out.println("制作完成, 耗时" + (System.currentTimeMillis() - start) + "ms");
            return pizza;
        }
    }
}
