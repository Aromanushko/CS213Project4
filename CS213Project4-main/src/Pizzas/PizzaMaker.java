package Pizzas;
import java.util.ArrayList;

import application.Constants;
public class PizzaMaker {
	
	/**
	 * 
	 * @param flavor - Flavor of pizza
	 * @param size - SIze enum of pizza
	 * @param TOPPINGS - ArrayList<Topping> toppings of pizza
	 * @return null if pizza flavor is invalid, returns instance of pizza otherwise
	 */
	public static Pizza createPizza(String flavor, Size size, ArrayList<Topping> TOPPINGS) {
		Pizza pizza = null;
		if(flavor.equals(Constants.deluxe)) {
			pizza = new Deluxe(size, TOPPINGS);
		}else if(flavor.equals(Constants.hawaiian)) {
			pizza = new Hawaiian(size, TOPPINGS);
		}else if(flavor.equals(Constants.pepperoni)){
			pizza = new Pepperoni(size, TOPPINGS);
		}
		return pizza;
	}

}
