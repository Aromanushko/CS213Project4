package application;


import java.util.ArrayList;

import Pizzas.Order;
import Pizzas.Pizza;
import Pizzas.Size;
import Pizzas.StoreOrders;
import Pizzas.Topping;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainMenuController {
	@FXML private ToggleGroup tgPizzaType;
	@FXML private RadioButton rdbDeluxe;
	@FXML private RadioButton rdbHawaiian;
	@FXML private RadioButton rdbPepperoni;
	@FXML private Button btnNewOrder;
	@FXML private TextField txtOrderID;
	@FXML private TextArea outputArea;
	@FXML private Button btnCart;
	@FXML private Button btnStoreOrders;
	@FXML private ListView<String> lvOrders;
	private Order focus;
	private int cartNum = 0;
	private StoreOrders storeOrders = new StoreOrders();
	
	@FXML
	public void enableNewOrder(ActionEvent event) {
		endisableNewOrder(false);
	}
	
	@FXML
	public void disableNewOrder(ActionEvent event) {
		endisableNewOrder(true);
	}
	
	public void endisableNewOrder(boolean status) {
		btnNewOrder.setDisable(status);
		btnCart.setDisable(status);
	}
	
	public void endisableNewPizzaRDB(boolean status) {
		rdbDeluxe.setDisable(status);
		rdbHawaiian.setDisable(status);
		rdbPepperoni.setDisable(status);
		rdbPepperoni.setSelected(true);
		rdbPepperoni.setSelected(false);
	}
	
	@FXML
	private void viewCart(ActionEvent event) {
		if(cartNum > 0) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrderView.fxml"));
				BorderPane CurrentOrder = (BorderPane) loader.load();
				CurrentOrderController cartController = loader.getController();
				cartController.setMainController(this);
				Scene CartScene = new Scene(CurrentOrder,600,400);
				CartScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setOnHidden(e-> cartController.shutdown());
				primaryStage.initStyle(StageStyle.UTILITY);
				primaryStage.setTitle("Current Order");
				endisableNewOrder(true);
				endisableNewPizzaRDB(true);
				primaryStage.setScene(CartScene);
				primaryStage.show(); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void viewStoreOrders(ActionEvent event) {
		if(storeOrders.getOrders().size() >0) {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrdersView.fxml"));
				BorderPane StoreOrders = (BorderPane) loader.load();
				PizzaCustomizationController StoreOrdersController = loader.getController();
				StoreOrdersController.setMainController(this);
				Scene PizzaScene = new Scene(StoreOrders,600,400);
				PizzaScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setOnHidden(e-> StoreOrdersController.shutdown());
				primaryStage.setTitle("All Store Orders");
				endisableNewOrder(true);
				endisableNewPizzaRDB(true);
				primaryStage.setScene(PizzaScene);
				primaryStage.show(); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@FXML
	private void newOrder(ActionEvent event) {
		String errorMessage = validateOrder();
		if(errorMessage.equals("")){
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PizzaCustomizationView.fxml"));
				BorderPane PizzaCustomizer = (BorderPane) loader.load();
				PizzaCustomizationController pizzaController = loader.getController();
				pizzaController.setMainController(this);
				Scene PizzaScene = new Scene(PizzaCustomizer,600,400);
				PizzaScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				Stage primaryStage = new Stage();
				primaryStage.setOnHidden(e-> pizzaController.shutdown());
				primaryStage.setTitle("Pizza Customizer");
				endisableNewOrder(true);
				endisableNewPizzaRDB(true);
				if(focus == null) {
					focus = new Order(txtOrderID.getText());
					cartNum = 0;
				}
				primaryStage.setScene(PizzaScene);
				primaryStage.show(); 
			} catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			outputArea.setText(errorMessage);
		}
	}
	
	private String validateOrder() {
		int orderID = 0;
		String retMes = "";
		if(txtOrderID.getText().trim().length() == 0){
			retMes = "Please enter a phone number for an Order ID.";
		}else
		if(txtOrderID.getText().trim().matches("[0-9]+")) {
			if(txtOrderID.getText().trim().length() != 10) {
				retMes = "Invalid 10 digit phone number for order ID.";
			}
		}else {
			retMes = "Order ID phone number cannot have special characters.";
		}
		return retMes;
	}
	
	public String[] getUserData() {
		String orderID = txtOrderID.getText();
		String[] pass;
		if(rdbDeluxe.isSelected()) {
			pass = new String[] {orderID, "deluxe","small", "CHICKEN", "SAUSAGE", "PEPPERS", "ONION", "BACON"};
		}else if(rdbHawaiian.isSelected()) {
			pass = new String[] {orderID, "hawaiian", "small", "CHICKEN", "PINEAPPLE"};
		}else {
			pass = new String[] {orderID, "pepperoni","small", "PEPPERONI"};
		}
		return pass;
	}
	
	public Order getOrder() {
		return this.focus;
	}
	
	public StoreOrders getStoreOrders() {
		return this.storeOrders;
	}
	
	public void addtoStoreOrders(Order order) {
		storeOrders.addOrder(order);
	}
	
	public void clearFocus() {
		focus = null;
		cartNum = 0;
		btnCart.setText("Cart");
		txtOrderID.setText("");
	}
	
	public void updateCartNum(int num) {
		cartNum += num;
	}
	
	private void updateLVOrders() {
		if((storeOrders.getOrders()).size() != 0) {
			ArrayList<Order> Orders = storeOrders.getOrders();
			ObservableList<String> lvElem = FXCollections.observableArrayList();
			for(Order o : Orders) {
				lvElem.add(o.getOrderID());
			}
			lvOrders.setItems(lvElem);
		}
	}
	
	public void reFocus() {
		updateLVOrders();
		endisableNewPizzaRDB(false);
		if(cartNum == 0) {
			txtOrderID.setDisable(false);
			btnCart.setDisable(true);
		}else {
			txtOrderID.setDisable(true);
			btnCart.setText("Cart (" + cartNum + ")");
			btnCart.setDisable(false);
		}
	}
	
	public void addPizzatoOrder(Pizza pizza) {
		focus.addPizza(pizza);
	}
	
}
