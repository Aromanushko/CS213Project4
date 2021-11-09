package Pizzas;
import application.Constants;
import java.util.ArrayList;


public class Deluxe extends Pizza{
	private String pizzaType = "Deluxe";
	/**
	 * 
	 * @param SIZE - String size of pizza, small, medium, large
	 * @param TOPPINGS - ArrayList<String> toppings of pizza max length 7, handled in pizza maker class
	 */
	public Deluxe(Size SIZE, ArrayList<Topping> TOPPINGS) {
		this.size = SIZE;
		this.toppings = TOPPINGS;
		this.price = price();
	}
	/**
	 * Method for calculating the price of the Deluxe Pizza
	 */
	@Override
	public double price() {
		double price = Constants.baseDeluxePrice;
		if(size.name().equals(Constants.SMALL.toUpperCase())) {
			price += 0;
		}else if(size.name().equals(Constants.MEDIUM.toUpperCase())) {
			price += Constants.mediumPriceIncrease;
		}else if(size.name().equals(Constants.Large.toUpperCase())) {
			price += Constants.largePriceIncrease;
		}
		int numToppings = toppings.size();
		if(numToppings > Constants.deluxeIncludedToppings) {
			price += Constants.toppingPrice * (numToppings - Constants.deluxeIncludedToppings);
		}
		int n = (int)(price * 100);
		price = (double) n/100;
		return price;
	}
	/**
	 * Method for returning Deluxe pizza to string
	 */
	@Override
	public String toString() {
		String str = "";
		str += pizzaType + ":" + size.name() +":Toppings" + toppings.toString() + ":Price[" + String.valueOf(price) + "]";
		return str;
	}
	
	/**
	 * Method for updating toppings for deluxe pizza
	 */
	@Override
	public void updateToppings(ArrayList<Topping> TOPPINGS) {
		this.toppings = TOPPINGS;
		this.price = price();
	
	}
	
	/**
	 * Method for updating size for deluxe pizza
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
		return "deluxe";
	}
	
}
