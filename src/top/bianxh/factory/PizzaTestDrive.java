package top.bianxh.factory;

import top.bianxh.factory.pizzas.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PizzaTestDrive {

	public static void main(String[] args) {
		List<PizzaStore> pizzaStores = Arrays.asList(new NYPizzaStore(), new ChicagoPizzaStore());
		List<String> pizzaTypes = Arrays.asList("cheese", "clam", "pepperoni", "veggie");
		List<String> consumers = Arrays.asList("Ethan", "Joel");

		Consumer consumer = new Consumer();
		while (true) {
			// 随机在比萨商店中生产某类型的比萨
			PizzaStore pizzaStore = pizzaStores.get(new Random().nextInt(pizzaStores.size()));
			String pizzaType = pizzaTypes.get(new Random().nextInt(pizzaTypes.size()));
			pizzaStore.submitProduceTask(pizzaType);

			PizzaStore consumeFromPizzaStore = pizzaStores.get(new Random().nextInt(pizzaStores.size()));
			String consumePizzaType = pizzaTypes.get(new Random().nextInt(pizzaTypes.size()));
			String consumerName = consumers.get(new Random().nextInt(consumers.size()));
			consumer.consume(consumeFromPizzaStore, consumePizzaType, consumerName);
		}
	}

	/**
	 * 制作比萨测试
	 */
	private static void test() {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();

		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("clam");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("Joel ordered a " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("Ethan ordered a " + pizza + "\n");

		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("Joel ordered a " + pizza + "\n");
	}
}
