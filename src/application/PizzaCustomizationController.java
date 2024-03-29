package application;

import java.util.ArrayList;

import Pizzas.Order;
import Pizzas.Pizza;
import Pizzas.PizzaMaker;
import Pizzas.Size;
import Pizzas.Topping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PizzaCustomizationController {
	private String[] data;
	private MainMenuController stage;
	private CurrentOrderController passer;
	private String pizzaType;
	@FXML private Label lblOrderID;
	@FXML private CheckBox cbPEP;
	@FXML private CheckBox cbEXC;
	@FXML private CheckBox cbBAC;
	@FXML private CheckBox cbJAL;
	@FXML private CheckBox cbONI;
	@FXML private CheckBox cbCHC;
	@FXML private CheckBox cbSAG;
	@FXML private CheckBox cbPPR;
	@FXML private CheckBox cbBRO;
	@FXML private CheckBox cbRIC;
	@FXML private CheckBox cbPIN;
	@FXML private ComboBox<String> cbSize;
	@FXML private Label subTotal;
	private int amtToppings = 0;
	private ArrayList<Topping> toppings = new ArrayList<Topping>();
	private PizzaMaker pizzaMaker = new PizzaMaker();
	private Pizza valuePizza;
	private Pizza tempPizza;
	
	public void setMainController(MainMenuController stage) {
		this.stage = stage;
		this.data = (String[]) stage.getUserData();
		lblOrderID.setText(data[0]);
		this.pizzaType = data[1];
		populate();
		displaySubtotal();
	}
	
	public void setCurrentOrderController(CurrentOrderController controller) {
		tempPizza = passer.getSelectedPizza();
		this.passer = controller;
		this.data = (String[]) passer.getData();
		lblOrderID.setText(data[0]);
		this.pizzaType = data[1];
		populate();
		displaySubtotal();
	}
	
	public void displaySubtotal() {
		valuePizza = PizzaMaker.createPizza(pizzaType, parsers.parseSize(cbSize.getSelectionModel().getSelectedItem()), toppings);
		String o = String.format("%,.2f", valuePizza.price());
		subTotal.setText("$" + o);
	}
	
	@FXML
	public void addToOrder(ActionEvent event) {
		stage.addPizzatoOrder(valuePizza);
		if(passer != null) {
			stage.getOrder().removePizza(tempPizza);
			passer.populate();
		}else {
			stage.updateCartNum(1);
		}
		closeScene();
	}
	
	@FXML 
	public void updateToppings(ActionEvent event) {
		CheckBox source = (CheckBox)event.getSource();
		if(!source.isSelected()) {
			if(amtToppings > 0) {
				amtToppings -=1;
				toppings.remove(parsers.parseTopping(source.getText().toUpperCase()));
			}
		}else {
			if(amtToppings >= Constants.MAX_TOPPING) {
				source.setSelected(false);
			}else {
				toppings.add(parsers.parseTopping(source.getText().toUpperCase()));
				amtToppings +=1;
			}
		}
		displaySubtotal();
	}
	
	@FXML 
	public void updateSize(ActionEvent event) {
		displaySubtotal();
	}
	
	
	public void closeScene() {
		Stage closestage = (Stage) lblOrderID.getScene().getWindow();
		closestage.close();
	}
	
	@FXML
	public void shutdown() {
	   stage.reFocus();
	}
	
	public void populate() {
		ObservableList<String> sizes = FXCollections.observableArrayList("small", "medium", "large");
		cbSize.setItems(sizes);
		cbSize.setValue("small");
		if(pizzaType.equals(Constants.deluxe)) {
			cbCHC.setSelected(true);
			cbSAG.setSelected(true);
			cbPPR.setSelected(true);
			cbONI.setSelected(true);
			cbBAC.setSelected(true);
			loadToppings();
			amtToppings = Constants.deluxeIncludedToppings;
		}else if(pizzaType.equals(Constants.hawaiian)) {
			cbCHC.setSelected(true);
			cbPIN.setSelected(true);
			loadToppings();
			amtToppings = Constants.hawaiianIncludedToppings;
		}else {
			cbPEP.setSelected(true);
			loadToppings();
			amtToppings = Constants.pepperoniIncludedToppings;
		}
	}
	
	public void loadToppings() {
		int dataStart = 3;
		for(int x = dataStart; x < data.length; x++) {
			toppings.add(parsers.parseTopping(data[x].toUpperCase()));
		}
	}
	
}
