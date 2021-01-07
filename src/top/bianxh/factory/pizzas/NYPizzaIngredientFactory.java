package top.bianxh.factory.pizzas;

import top.bianxh.factory.pizzas.cheese.Cheese;
import top.bianxh.factory.pizzas.cheese.ReggianoCheese;
import top.bianxh.factory.pizzas.clams.Clams;
import top.bianxh.factory.pizzas.clams.FreshClams;
import top.bianxh.factory.pizzas.dough.Dough;
import top.bianxh.factory.pizzas.dough.ThinCrustDough;
import top.bianxh.factory.pizzas.pepperoni.Pepperoni;
import top.bianxh.factory.pizzas.pepperoni.SlicedPepperoni;
import top.bianxh.factory.pizzas.sauce.MarinaraSauce;
import top.bianxh.factory.pizzas.sauce.Sauce;
import top.bianxh.factory.pizzas.veggies.*;

/**
 * 纽约匹萨配料厂
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
 
	public Dough createDough() {
		return new ThinCrustDough();
	}
 
	public Sauce createSauce() {
		return new MarinaraSauce();
	}
 
	public Cheese createCheese() {
		return new ReggianoCheese();
	}
 
	public Veggies[] createVeggies() {
		Veggies veggies[] = { new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
		return veggies;
	}
 
	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FreshClams();
	}
}
