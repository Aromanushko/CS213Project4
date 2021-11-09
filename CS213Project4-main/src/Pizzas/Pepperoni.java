package Pizzas;

import java.util.ArrayList;

import application.Constants;

public class Pepperoni extends Pizza{
	private String pizzaType = "Pepperoni";
	/** 
	 * 
	 * @param SIZE - String size of pizza, small, medium, large
	 * @param TOPPING - - ArrayList<String> toppings of pizza max length 7, handled in pizza maker class
	 */
	public Pepperoni(Size SIZE, ArrayList<Topping> TOPPING) {
		this.size = SIZE;
		this.toppings = TOPPING;
		this.price = price();
	}
	/**
	 * Method for calculating price of Pepperoni pizza
	 */
	@Override
	public double price() {
		double price = Constants.basePepperoniPrice;
		if(size.name().equals(Constants.SMALL.toUpperCase())) {
			price+= 0;
		}else if(size.name().equals(Constants.MEDIUM.toUpperCase())) {
			price += Constants.mediumPriceIncrease;
		}else if(size.name().equals(Constants.Large.toUpperCase())) {
			price+= Constants.largePriceIncrease;
		}
		int numToppings = toppings.size();
		if(numToppings > Constants.pepperoniIncludedToppings) {
			price += Constants.toppingPrice * (numToppings - Constants.pepperoniIncludedToppings);
		}
		int n = (int)(price * 100);
		price = (double) n/100;
		return price;
	}

	/**
	 * Method for returning Pepperoni pizza to string
	 */
	@Override
	public String toString() {
		String str = "";
		str += pizzaType + ":" + size.name() +":Toppings" + toppings.toString() + ":Price[" + String.valueOf(price) + "]";
		return str;
	}
	/**
	 * Method for updating toppings for pepperoni pizza
	 */
	@Override
	public void updateToppings(ArrayList<Topping> TOPPINGS) {
		this.toppings = TOPPINGS;
		this.price = price();
	
	}
	/**
	 * Method for updating size for pepperoni pizza
	 */
	@Override
	public void updateSize(Size SIZE) {
		this.size = SIZE;
		this.price = price();
		
	}
	
	@Override
	public ArrayList<Topping> getToppings() {
		return this.toppings;
	}
	
	@Override
	public Size getSize() {
		return this.size;
	}
	@Override
	public String getType() {
		return "pepperoni";
	}
	
}
