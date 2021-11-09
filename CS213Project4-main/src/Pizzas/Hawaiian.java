package Pizzas;

import java.util.ArrayList;

import application.Constants;

public class Hawaiian extends Pizza{
	private String pizzaType = "Hawaiian";
	/** 
	 * 
	 * @param SIZE - String size of pizza, small, medium, large
	 * @param TOPPING - - ArrayList<String> toppings of pizza max length 7, handled in pizza maker class
	 */
	public Hawaiian(Size SIZE, ArrayList<Topping> TOPPING) {
		this.size = SIZE;
		this.toppings = TOPPING;
		this.price = price();
	}
	/**
	 * Method for calculating price of Hawaiian pizza
	 */
	@Override
	public double price() {
		double price = Constants.baseHawaiianPrice;
		if(size.name().equals(Constants.SMALL.toUpperCase())) {
			price+= 0;
		}else if(size.name().equals(Constants.MEDIUM.toUpperCase())) {
			price += Constants.mediumPriceIncrease;
		}else if(size.name().equals(Constants.Large.toUpperCase())) {
			price+= Constants.largePriceIncrease;
		}
		int numToppings = toppings.size();
		if(numToppings > Constants.hawaiianIncludedToppings) {
			price += Constants.toppingPrice * (numToppings - Constants.hawaiianIncludedToppings);
		}
		int n = (int)(price * 100);
		price = (double) n/100;
		return price;
	}

	/**
	 * Method for returning Hawiian pizza to string
	 */
	@Override
	public String toString() {
		String str = "";
		str += pizzaType + ":" + size.name() +":Toppings" + toppings.toString() + ":Price[" + String.valueOf(price) + "]";
		return str;
	}
	/**
	 * Method for updating toppings for hawaiian pizza
	 */
	@Override
	public void updateToppings(ArrayList<Topping> TOPPINGS) {
		this.toppings = TOPPINGS;
		this.price = price();
	
	}
	/**
	 * Method for updating size for hawawiian pizza
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
		return "hawiian";
	}

}
