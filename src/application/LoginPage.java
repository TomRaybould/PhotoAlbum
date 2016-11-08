package application;

import View.LoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginPage extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();
		
		LoginPageController loginController=loader.getController();
		loginController.start(primaryStage);
		Scene scene = new Scene(root);
	
		primaryStage.setTitle("Login Page");
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}