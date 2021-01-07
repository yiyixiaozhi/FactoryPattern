package top.bianxh.factory.pizzas;

/**
 * 蛤蜊比萨
 * 工厂加工工序：面团、酱、奶酪、蛤蜊
 */
public class ClamPizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
 
	public ClamPizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}
 
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		clam = ingredientFactory.createClam();
	}
}
