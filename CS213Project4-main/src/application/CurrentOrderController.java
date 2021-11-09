package application;


import java.util.ArrayList;

import javax.swing.event.ChangeListener;

import Pizzas.Order;
import Pizzas.Pizza;
import Pizzas.Topping;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CurrentOrderController {
	private MainMenuController stage;
	private Order focus;
	private String orderID;
	private Pizza selectedPizza;
	@FXML private ListView<String>lvOrder;
	@FXML private Label lblOrderID;
	
	
	public void setMainController(MainMenuController mainMenuController) {
		this.stage = mainMenuController;
		this.focus = stage.getOrder();
		orderID = focus.getOrderID();
		lblOrderID.setText(orderID);
		populate();
	}
	
	public Pizza getSelectedPizza() {
		return focus.getOrder().get(lvOrder.getSelectionModel().getSelectedIndex());
	}
	
	@FXML
	private void editPizza(ActionEvent event) {
		if(lvOrder.getSelectionModel().getSelectedItem() == null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("No pizza selected to edit");
			alert.show();
		}else {
			selectedPizza = focus.getOrder().get(lvOrder.getSelectionModel().getSelectedIndex());
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PizzaCustomizationView.fxml"));
				BorderPane PizzaCustomizer = (BorderPane) loader.load();
				PizzaCustomizationController pizzaController = loader.getController();
				pizzaController.setCurrentOrderController(this);
				Scene PizzaScene = new Scene(PizzaCustomizer,600,400);
				PizzaScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setOnHidden(e-> pizzaController.shutdown());
				primaryStage.initStyle(StageStyle.UTILITY);
				primaryStage.setTitle("Pizza Customizer");
				primaryStage.setScene(PizzaScene);
				primaryStage.show(); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void closeScene() {
		Stage closestage = (Stage) lblOrderID.getScene().getWindow();
		closestage.close();
	}
	
	@FXML
	public void shutdown() {
	   stage.reFocus();
	}
	
	public void populate() {
		ArrayList<Pizza> Pizzas = focus.getOrder();
		ObservableList<String> lvElem = FXCollections.observableArrayList();
		for(Pizza p : Pizzas) {
			lvElem.add(p.toString());
		}
		lvOrder.setItems(lvElem);
	}
	
	public String[] getData() {
		int numToppings = selectedPizza.getToppings().size();
		String[] data = new String[numToppings + 3];
		int dataStart = 3;
		ArrayList<Topping> topping = selectedPizza.getToppings();
		data[0] = focus.getOrderID();
		data[1] = selectedPizza.getType();
		data[2] = selectedPizza.getSize().name();
		for(int x = dataStart; x < data.length; x++) {
			data[x] = topping.get(x - dataStart).name();
		}
		return data;
	}
	
	@FXML
	private void placeOrder(ActionEvent event) {
		try {
			stage.addtoStoreOrders(focus);
			closeScene();
		}catch (NullPointerException e) {
			
		}
	}
	
	
}
