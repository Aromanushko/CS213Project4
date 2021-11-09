package Pizzas;

import java.util.ArrayList;
import application.Constants;
public abstract class Pizza {
	
	protected ArrayList<Topping> toppings = new ArrayList<Topping>();
	protected Size size;
	protected double price;
	
	public abstract double price();
	
	@Override
	public abstract String toString();
	
	public abstract void updateToppings(ArrayList<Topping> TOPPINGS);
	
	public abstract void updateSize(Size size);
	
	public abstract ArrayList<Topping> getToppings();
	
	public abstract Size getSize();
	
	public abstract String getType();
}
