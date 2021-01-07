package top.bianxh.factory.pizzas;

import top.bianxh.factory.pizzas.cheese.Cheese;
import top.bianxh.factory.pizzas.cheese.MozzarellaCheese;
import top.bianxh.factory.pizzas.clams.Clams;
import top.bianxh.factory.pizzas.clams.FrozenClams;
import top.bianxh.factory.pizzas.dough.Dough;
import top.bianxh.factory.pizzas.dough.ThickCrustDough;
import top.bianxh.factory.pizzas.pepperoni.Pepperoni;
import top.bianxh.factory.pizzas.pepperoni.SlicedPepperoni;
import top.bianxh.factory.pizzas.sauce.PlumTomatoSauce;
import top.bianxh.factory.pizzas.sauce.Sauce;
import top.bianxh.factory.pizzas.veggies.*;

/**
 * 芝加哥比萨配料厂
 */
public class ChicagoPizzaIngredientFactory 
	implements PizzaIngredientFactory 
{

	public Dough createDough() {
		return new ThickCrustDough();
	}

	public Sauce createSauce() {
		return new PlumTomatoSauce();
	}

	public Cheese createCheese() {
		return new MozzarellaCheese();
	}

	public Veggies[] createVeggies() {
		Veggies veggies[] = { new BlackOlives(),
		                      new Spinach(),
		                      new Eggplant() };
		return veggies;
	}

	public Pepperoni createPepperoni() {
		return new SlicedPepperoni();
	}

	public Clams createClam() {
		return new FrozenClams();
	}
}
