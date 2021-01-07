package top.bianxh.factory;

import top.bianxh.factory.pizzas.ChicagoPizzaStore;
import top.bianxh.factory.pizzas.NYPizzaStore;
import top.bianxh.factory.pizzas.Pizza;
import top.bianxh.factory.pizzas.PizzaStore;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PizzaTestDrive {
	public static void main(String[] args) {
		List<PizzaStore> pizzaStores = Arrays.asList(new NYPizzaStore(), new ChicagoPizzaStore());
		List<String> pizzaTypes = Arrays.asList("cheese", "clam", "pepperoni", "veggie");
		List<String> consumers = Arrays.asList("Ethan", "Joel");
		Producer producer = new Producer();
		for (int index = 0; index < 10; index++) {
			PizzaStore pizzaStore = pizzaStores.get(new Random().nextInt(pizzaStores.size() - 1));
			String pizzaType = pizzaTypes.get(new Random().nextInt(pizzaTypes.size() - 1));
			Pizza pizza = producer.produce(pizzaStore, pizzaType);
			String consumer = consumers.get(new Random().nextInt(consumers.size() - 1));
			System.out.println(consumer + " ordered a " + pizza + "\n");
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
