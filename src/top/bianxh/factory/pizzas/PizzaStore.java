package top.bianxh.factory.pizzas;

/**
 * 比萨商店抽象父类
 * 定义了比萨制作通用工序：准备、烘烤、切片、包装
 */
public abstract class PizzaStore {
 
	protected abstract Pizza createPizza(String item);
 
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		System.out.println("--- Making a " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
