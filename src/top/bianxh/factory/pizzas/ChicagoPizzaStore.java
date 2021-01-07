package top.bianxh.factory.pizzas;

/**
 * 芝加哥比萨商店
 * 使用芝加哥匹萨配料厂的原料来制作：奶酪比萨、蔬菜比萨、蛤蜊比萨、意大利辣香肠比萨
 */
public class ChicagoPizzaStore extends PizzaStore {

	protected Pizza createPizza(String item) {
		Pizza pizza = null;
		PizzaIngredientFactory ingredientFactory =
		new ChicagoPizzaIngredientFactory();

		if (item.equals("cheese")) {

			pizza = new CheesePizza(ingredientFactory);
			pizza.setName("Chicago Style Cheese Pizza");

		} else if (item.equals("veggie")) {

			pizza = new VeggiePizza(ingredientFactory);
			pizza.setName("Chicago Style Veggie Pizza");

		} else if (item.equals("clam")) {

			pizza = new ClamPizza(ingredientFactory);
			pizza.setName("Chicago Style Clam Pizza");

		} else if (item.equals("pepperoni")) {

			pizza = new PepperoniPizza(ingredientFactory);
			pizza.setName("Chicago Style Pepperoni Pizza");

		}
		return pizza;
	}
}
