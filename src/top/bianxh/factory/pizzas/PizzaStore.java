package top.bianxh.factory.pizzas;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 比萨商店抽象父类
 * 定义了比萨制作通用工序：准备、烘烤、切片、包装
 */
public abstract class PizzaStore {
	// 每个品类pizza库存最大限制
	private final int MAX_STORE_NUM = 50;

	// 每个品类pizza库存最小限制
	private final int MIX_STORE_NUM = 10;

	private Map<String, LinkedList<Pizza>> stockMap = new ConcurrentHashMap<>();

	private ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<>(1000), new ThreadPoolExecutor.DiscardPolicy());

	protected abstract Pizza createPizza(String item);

	/**
	 * 下单生产pizza
	 *
	 * @param type
	 * @return
	 */
	public Pizza orderPizza(String type) {
		if (isStockFull(type)) {
			System.out.println(this + "库存已满");
			return null;
		}
		Pizza pizza = createPizza(type);
		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}

	/**
	 * 消费者从店面取出pizza
	 * @param type
	 * @return
	 */
	public Pizza consumePizza(String type) {
		LinkedList<Pizza> pizzas = stockMap.get(type);
		if (pizzas != null && pizzas.size() > 0) {
			Pizza pizza = pizzas.get(0);
			pizzas.remove(pizza);
			if (pizzas.size() < MIX_STORE_NUM) {
				// 库存不够了，通知生产
				System.out.println(this + "库存紧张，通知生产");
				submitProduceTask(type);
			}
			return pizza;
		} else {
			// 没有库存，直接生产给消费者（等待生产结果，让消费者不落空）
			System.out.println(this + "库存空，直接等待生产");
			return produce(type);
		}
	}


	/**
	 * 异步生产
	 * @param pizzaType
	 * @return
	 */
	public void submitProduceTask(String pizzaType) {
		executorService.submit(new ProducePizzaTask(pizzaType));
	}

	/**
	 * 生产pizza
	 */
	public Pizza produce(String pizzaType) {
		Future<Pizza> future = executorService.submit(new ProducePizzaTask(pizzaType));
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

	/**
	 * 获取店面pizza库存
	 * @param pizzaStore
	 * @return
	 */
	public LinkedList<Pizza> getStocks(PizzaStore pizzaStore) {
		return stockMap.get(pizzaStore);
	}

	/**
	 * 是否库存已满
	 *
	 * @param type
	 * @return
	 */
	public boolean isStockFull(String type) {
		LinkedList<Pizza> pizzas = stockMap.get(type);
		if (pizzas == null) {
			return false;
		}
		if (pizzas.size() >= MAX_STORE_NUM) {
			return true;
		}
		return false;
	}

	/**
	 * 生产pizza
	 */
	public class ProducePizzaTask implements Callable<Pizza> {
		private String pizzaType;

		public ProducePizzaTask(String pizzaType) {
			this.pizzaType = pizzaType;
		}

		@Override
		public Pizza call() throws Exception {
			long start = System.currentTimeMillis();
			try {
				// 默认人数随机阻塞1-3秒钟
				final int timeout = new Random().nextInt(3);
				TimeUnit.SECONDS.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Pizza pizza = PizzaStore.this.orderPizza(pizzaType);
			if (pizza != null) {
				// 生产出来了pizza，更新库存
				LinkedList<Pizza> pizzas = stockMap.get(pizzaType);
				if (pizzas == null || pizzas.size() == 0) {
					pizzas = new LinkedList<>();
				}
				pizzas.addLast(pizza);
				stockMap.put(pizzaType, pizzas);
				System.out.println(Thread.currentThread().getName() + "制作完成, 耗时" + (System.currentTimeMillis() - start) + "ms");
			}
			return pizza;
		}
	}
}
