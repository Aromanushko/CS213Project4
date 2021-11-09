package Pizzas;

import java.util.ArrayList;

public class StoreOrders {
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public ArrayList<Order> getOrders(){
		return this.orders;
	}
	
	public void addOrder(Order e) {
		orders.add(e);
	}
	
	
}
