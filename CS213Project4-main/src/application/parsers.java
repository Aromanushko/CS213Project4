package application;

import Pizzas.Size;
import Pizzas.Topping;

public class parsers {

	public static Topping parseTopping(String topping) {
		Topping t;
		t = Topping.valueOf(topping.toUpperCase());
		return t;
	}
	
	public static Size parseSize(String size) {
		Size s;
		s = Size.valueOf(size.toUpperCase());
		return s; 
	}

}
