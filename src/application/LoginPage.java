package application;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Model.Album;
import Model.User;
import View.LoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginPage extends Application {
	private static final String storeDirUser = "dat";
	private static final String storeFileUser= "Users.dat";
	private static final String storeDirAlbum = "dat";
	private static final String storeFileAlbum = "Albums.dat";
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/LoginPage.fxml"));
		
		AnchorPane root = (AnchorPane)loader.load();
		
		User.read();
		
			
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