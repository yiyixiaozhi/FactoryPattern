package top.bianxh.factory.pizzas;

import top.bianxh.factory.pizzas.cheese.Cheese;
import top.bianxh.factory.pizzas.clams.Clams;
import top.bianxh.factory.pizzas.dough.Dough;
import top.bianxh.factory.pizzas.pepperoni.Pepperoni;
import top.bianxh.factory.pizzas.sauce.Sauce;
import top.bianxh.factory.pizzas.veggies.Veggies;

/**
 * 比萨配料工厂
 */
public interface PizzaIngredientFactory {
 
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
 
}
