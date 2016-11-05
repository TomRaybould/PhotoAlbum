package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class LoginPage extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("poop");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();
		
		LoginPageController loginController=loader.getController();
		//loginController.start(primaryStage);
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		System.out.println("poop");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}