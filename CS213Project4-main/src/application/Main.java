package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import Pizzas.Order;
import Pizzas.StoreOrders;

public class Main extends Application {
	private StoreOrders StoreOrders;
	public Order focus;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MainMenuView.fxml"));
			Scene MainMenuscene = new Scene(root,600,400);
			MainMenuscene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Main Menu");
			primaryStage.setScene(MainMenuscene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
