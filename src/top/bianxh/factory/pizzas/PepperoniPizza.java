package top.bianxh.factory.pizzas;

/**
 * 意大利辣香肠比萨饼
 * 工厂加工工序：面团、酱、奶酪、蔬菜、意大利辣香肠
 */
public class PepperoniPizza extends Pizza {
	PizzaIngredientFactory ingredientFactory;
 
	public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
		this.ingredientFactory = ingredientFactory;
	}
 
	void prepare() {
		System.out.println("Preparing " + name);
		dough = ingredientFactory.createDough();
		sauce = ingredientFactory.createSauce();
		cheese = ingredientFactory.createCheese();
		veggies = ingredientFactory.createVeggies();
		pepperoni = ingredientFactory.createPepperoni();
	}
}
